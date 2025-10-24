# ===================================================================
# Dockerfile para la aplicacion Padron WS
# ===================================================================
#
# Este Dockerfile construye una imagen contenedorizada para la aplicacion
# web Padron, empaquetada como un archivo WAR. Utiliza un enfoque de
# construccion multi-etapa (multi-stage build) para optimizar el tamano
# y la seguridad de la imagen final.
#
# Etapa 1: Compilacion del proyecto con Maven y JDK 21.
# Etapa 2: Creacion de la imagen final con Tomcat 10.1 y JRE 21.
#
# @author lsanabria
#

# ===================================================================
# STAGE 1: Build (Maven + JDK 21)
# @description Esta etapa se encarga de compilar el codigo fuente Java,
#              descargar las dependencias necesarias y empaquetar la
#              aplicacion como un archivo .war. Utiliza una imagen
#              base que incluye Maven y un JDK completo.
# @alias build
# ===================================================================

##
# @instruction FROM
# @description Define la imagen base para esta etapa de construccion.
# @param {string} maven:3.9-eclipse-temurin-21 - Imagen oficial que contiene Maven 3.9 y JDK 21 (Temurin).
# @param {string} AS build - Asigna el alias 'build' a esta etapa para poder referenciarla posteriormente.
FROM maven:3.9-eclipse-temurin-21 AS build

##
# @instruction WORKDIR
# @description Establece el directorio de trabajo dentro del contenedor para las siguientes instrucciones.
# @param {string} /workspace - Ruta del directorio de trabajo. Si no existe, se crea.
WORKDIR /workspace

##
# @instruction COPY (POMs)
# @description Copia los archivos pom.xml del proyecto desde el contexto local al directorio de trabajo del contenedor.
#              Se copian primero solo los POMs para aprovechar el cache de capas de Docker. Si estos archivos
#              no cambian, la capa de descarga de dependencias puede ser reutilizada.
# @param {string} pom.xml . - Copia el POM padre.
# @param {string} <Module>/pom.xml <Module>/pom.xml - Copia el POM de cada modulo a su respectivo subdirectorio.
# --- 1. Copia solo los POMs ---
COPY pom.xml .
COPY ILiferay/pom.xml ILiferay/pom.xml
COPY InfosComun/pom.xml InfosComun/pom.xml
COPY PadronDAL/pom.xml PadronDAL/pom.xml
COPY PadronBL/pom.xml PadronBL/pom.xml
COPY PadronWS/pom.xml PadronWS/pom.xml

##
# @instruction RUN (mvn dependency:go-offline)
# @description Ejecuta Maven para descargar todas las dependencias del proyecto (definidas en los POMs)
#              y los plugins necesarios al repositorio local de Maven dentro del contenedor.
# @param {string} mvn - Comando de Maven.
# @param {string} -B - Modo Batch, optimizado para ejecucion en scripts.
# @param {string} dependency:go-offline - Objetivo (goal) de Maven que resuelve y descarga dependencias.
# @notes Esta capa se cachea si los archivos pom.xml copiados anteriormente no han cambiado.
# --- 2. Descarga todas las dependencias ---
RUN mvn -B dependency:go-offline

##
# @instruction COPY (Codigo Fuente)
# @description Copia todo el contenido del directorio local (contexto de build) al directorio
#              de trabajo `/workspace` dentro del contenedor. Esto incluye el codigo fuente Java,
#              archivos de recursos, configuraciones, etc.
# @param {string} . - Representa el directorio del contexto de build local.
# @param {string} . - Representa el directorio de trabajo actual (`/workspace`) en el contenedor.
# @notes Si cualquier archivo en el contexto local cambia, esta capa (y las siguientes) se invalidaran.
# --- 3. Copia TODO el codigo fuente ---
COPY . .

##
# @instruction RUN (mvn package)
# @description Compila el codigo fuente copiado y empaqueta la aplicacion.
# @param {string} mvn - Comando de Maven.
# @param {string} -B - Modo Batch.
# @param {string} -DskipTests - Omite la ejecucion de tests unitarios para acelerar el build.
# @param {string} clean - Fase de Maven que elimina artefactos de builds anteriores (directorio 'target').
# @param {string} package - Fase de Maven que compila y empaqueta el proyecto (genera el .war en PadronWS/target/).
# @notes Gracias al POM padre, Maven construye los modulos en el orden correcto. Las dependencias ya
#        fueron descargadas en el paso anterior, haciendo esta compilacion mas rapida.
# --- 4. Construye el proyecto ---
RUN mvn -B -DskipTests clean package

# ===================================================================
# STAGE 2: Runtime (Tomcat + JDK 21)
# @description Esta etapa crea la imagen final que se utilizara para ejecutar la aplicacion.
#              Utiliza una imagen base de Tomcat optimizada que incluye solo el JRE necesario.
#              Copia unicamente los artefactos necesarios (el .war y configuraciones) desde la
#              etapa 'build', resultando en una imagen mas pequena y segura.
# ===================================================================

##
# @instruction FROM
# @description Define la imagen base para la etapa de ejecucion final.
# @param {string} tomcat:11-jdk21-temurin - Imagen oficial de Apache Tomcat 11 con JRE 21 (Temurin).
#              Tomcat 11 es compatible con Jakarta EE 9+ (usado por Spring 6).
FROM tomcat:11-jdk21-temurin

##
# @instruction RUN (Limpieza de webapps)
# @description Elimina las aplicaciones web predeterminadas de Tomcat (manager, examples, etc.).
# @notes Es una buena practica de seguridad y reduce el tamano de la imagen.
# 1. Limpiamos las apps de ejemplo
RUN rm -rf /usr/local/tomcat/webapps/*

##
# @instruction COPY (context.xml)
# @description Copia el archivo de configuracion `context.xml` personalizado (que define el DataSource JNDI)
#              desde el contexto local al directorio de configuracion de Tomcat en el contenedor.
# @param {string} docker/tomcat/conf/context.xml - Ruta local del archivo context.xml.
# @param {string} /usr/local/tomcat/conf/context.xml - Ruta de destino dentro del contenedor.
# @notes Tomcat cargara esta configuracion al iniciar. Las variables (${DB_HOST}, etc.)
#        seran sustituidas por las variables de entorno proporcionadas al contenedor (e.g., via docker-compose).
# ===================================================================
# 2. Copia tu configuracion JNDI (context.xml)
# ===================================================================
COPY docker/tomcat/conf/context.xml /usr/local/tomcat/conf/context.xml

##
# @instruction COPY (Archivo WAR)
# @description Copia el archivo .war generado en la etapa 'build' al directorio de despliegue de Tomcat.
# @param {string} --from=build - Especifica que el origen esta en la etapa con alias 'build'.
# @param {string} /workspace/PadronWS/target/PadronWS-*.war - Ruta del archivo .war en la etapa 'build'. El `*` maneja nombres variables (e.g., version).
# @param {string} /usr/local/tomcat/webapps/app.war - Ruta de destino en la imagen final. Renombrar a `app.war`
#              hace que Tomcat despliegue la aplicacion en el context path `/app`.
# 3. Copia el .war construido del STAGE 1
COPY --from=build /workspace/PadronWS/target/PadronWS-*.war /usr/local/tomcat/webapps/app.war

##
# @instruction EXPOSE
# @description Documenta el puerto en el que la aplicacion dentro del contenedor escuchara.
# @param {number} 8080 - Puerto estandar de Tomcat.
# @notes No publica el puerto; esto se hace con `docker run -p` o `docker-compose.yml`.
# 4. Expone el puerto de Tomcat
EXPOSE 8080

# @notes El CMD para iniciar Tomcat (catalina.sh run) esta heredado de la imagen base `tomcat:*`,
#        por lo que no es necesario especificarlo aqui.
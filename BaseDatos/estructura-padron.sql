/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.13-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: padron
-- ------------------------------------------------------
-- Server version	10.11.13-MariaDB-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `_rol`
--

DROP TABLE IF EXISTS `_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `_rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idSistema` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `activo` bit(1) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime NOT NULL,
  `usuarioCreacion` varchar(100) NOT NULL,
  `usuarioModificacion` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `_pagina_UNIQUE_1` (`id`,`idSistema`,`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `_sistema`
--

DROP TABLE IF EXISTS `_sistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `_sistema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  `sigla` varchar(5) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `activo` bit(1) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime NOT NULL,
  `usuarioCreacion` varchar(100) NOT NULL,
  `usuarioModificacion` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `_sistema_UNIQUE_1` (`nombre`),
  UNIQUE KEY `_sistema_UNIQUE_2` (`sigla`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `_sistema_usuario`
--

DROP TABLE IF EXISTS `_sistema_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `_sistema_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idSistema` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime NOT NULL,
  `usuarioCreacion` varchar(100) NOT NULL,
  `usuarioModificacion` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `_sistema_usuario_UNIQUE_1` (`id`,`idSistema`,`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `_usuario`
--

DROP TABLE IF EXISTS `_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(100) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido1` varchar(50) NOT NULL,
  `apellido2` varchar(50) NOT NULL,
  `telefonoTrabajo` varchar(8) DEFAULT NULL,
  `telefonoCelular` varchar(8) DEFAULT NULL,
  `telefonoPersonal` varchar(8) DEFAULT NULL,
  `correoTrabajo` varchar(50) NOT NULL,
  `correoPersonal` varchar(50) DEFAULT NULL,
  `contrasena` varchar(256) NOT NULL,
  `cambiarContrasena` bit(1) NOT NULL,
  `intentoLogin` int(11) NOT NULL,
  `fecUltimoLogin` datetime DEFAULT NULL,
  `fecLogin` datetime DEFAULT NULL,
  `ipUltimoLogin` varchar(25) DEFAULT NULL,
  `ipLogin` varchar(25) DEFAULT NULL,
  `fecModificacionContrasena` datetime DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime NOT NULL,
  `usuarioCreacion` varchar(100) NOT NULL,
  `usuarioModificacion` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `_usuario_UNIQUE_1` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `_usuario_rol`
--

DROP TABLE IF EXISTS `_usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `_usuario_rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `idRol` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `_usuario_rol_UNIQUE_1` (`id`,`idUsuario`,`idRol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ciudadano`
--

DROP TABLE IF EXISTS `ciudadano`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudadano` (
  `cedula` int(11) NOT NULL,
  `codDirElectoral` int(11) NOT NULL,
  `fechaCaducidad` date NOT NULL,
  `junta` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido1` varchar(26) NOT NULL,
  `apellido2` varchar(26) NOT NULL,
  `fecCreacion` datetime NOT NULL,
  `fecModificacion` datetime NOT NULL,
  `usrCreacion` varchar(100) NOT NULL,
  `usrModificacion` varchar(100) NOT NULL,
  PRIMARY KEY (`cedula`),
  KEY `ciudadano_FK` (`codDirElectoral`),
  CONSTRAINT `ciudadano_FK` FOREIGN KEY (`codDirElectoral`) REFERENCES `direccion_electoral` (`codDirElectoral`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `direccion_electoral`
--

DROP TABLE IF EXISTS `direccion_electoral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion_electoral` (
  `codDirElectoral` int(11) NOT NULL,
  `provincia` varchar(10) NOT NULL,
  `canton` varchar(30) NOT NULL,
  `distritoElectoral` varchar(34) NOT NULL,
  `fecCreacion` datetime NOT NULL,
  `fecModificacion` datetime NOT NULL,
  `usrCreacion` varchar(100) NOT NULL,
  `usrModificacion` varchar(100) NOT NULL,
  PRIMARY KEY (`codDirElectoral`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-25  9:40:28

/**
 * Derechos reservados
 * Desarrollado por Infosgroup Costa Rica -
 * https://www.infosgroup.cr
 * 2025
 * @author lsanabria
 */
package cr.infosgroup.padron.bl;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cr.infosgroup.padron.bl.adm.interfaces.IAdmCiudadano;
import cr.infosgroup.padron.bl.adm.interfaces.IAdmDireccionElectoral;
import cr.infosgroup.padron.bl.audit.AuditoriaProviderBL;
import cr.infosgroup.padron.dto.CiudadanoDTO;
import cr.infosgroup.padron.dto.DireccionElectoralDTO;

/**
 * Clase main utilizada para ejecutar administradores en forma manual
 * 
 * @author lsanabria
 *
 */
public class Main {
	
	 public static void main(String[] args) {
	        try {
	            System.out.println("<MAIN>");
	            
	            System.out.println("<Contexto>");
	            try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApplicationContextBL.class)) {
	                System.out.println("</Contexto>");

	                boolean mostrarInfoAdicional = false;
	                if (mostrarInfoAdicional) {
	                    System.out.println("<ContextoInfoAdicional>");
	                    System.out.println("Context DisplayName: " + context.getDisplayName());
	                    System.out.println("Context Id: " + context.getId());
	                    System.out.println("Context Start Date: " + new Date(context.getStartupDate()).toString());
	                    System.out.println("Context BeanDefinitionCount: " + context.getBeanDefinitionCount());

	                    System.out.println("<BeanDefinitionNames>");
	                    for (String beanName : context.getBeanDefinitionNames()) {
	                        System.out.println(beanName);
	                    }
	                    System.out.println("</BeanDefinitionNames>");
	                    System.out.println("</ContextoInfoAdicional>");
	                }

	                IAdmCiudadano admCiudadano = context.getBean("admCiudadano", IAdmCiudadano.class);
	                IAdmDireccionElectoral admDireccionElectoral = context.getBean("admDireccionElectoral", IAdmDireccionElectoral.class);
/*
	                // Listado completo
	                List<CiudadanoDTO> ciudadanos = admCiudadano.getAll();
	                int i = 0;
	                for (CiudadanoDTO c : ciudadanos) {
	                    System.out.println(++i + " " + c.getNombre() );
	                    System.out.println(c.getDireccionElectoral().getProvincia());
	                    System.out.println(c.getDireccionElectoral().getCanton());
	                    System.out.println(c.getDireccionElectoral().getDistrito());
	                    System.out.println(c.getDireccionElectoral().getCodDirElectoral());
	                    System.out.println();
	                }

	                // Búsqueda por cédula
	                System.out.println("----- Busqueda por cedula");
	                CiudadanoDTO ciudadano = admCiudadano.getByCedula(105610071);
	                System.out.println(ciudadano.getNombre() + " " + ciudadano.getApellido1() + " " + ciudadano.getApellido2() + " " + ciudadano.getCedula());
	                System.out.println(ciudadano.getDireccionElectoral().getProvincia());
	                System.out.println(ciudadano.getDireccionElectoral().getCanton());
	                System.out.println(ciudadano.getDireccionElectoral().getDistrito());
	                System.out.println(ciudadano.getDireccionElectoral().getCodDirElectoral());
	                System.out.println();
*/
	                
	                //GUARDAR	      
	                AuditoriaProviderBL.setUsuarioAuditoria("pruebaBL");	                
	               
	                CiudadanoDTO ciudadanoDTO = new CiudadanoDTO();
	                
	                ciudadanoDTO.setCedula(111420777);
	                ciudadanoDTO.setApellido1("SANABRIA");
	                ciudadanoDTO.setApellido2("VEGA");
	                ciudadanoDTO.setNombre("LUIS");
	                ciudadanoDTO.setJunta(0);
	                ciudadanoDTO.setFechaCaducidad(LocalDate.of(2030, 7, 7));
	                
	                DireccionElectoralDTO direccionElectoralDTO = admDireccionElectoral.getById(301027);
	                ciudadanoDTO.setDireccionElectoral(direccionElectoralDTO);
	                
	                ciudadanoDTO = admCiudadano.guardar(ciudadanoDTO);
           

	                
	                //Update
	                
//	                CiudadanoDTO ciudadanoDTO = admCiudadano.getById(111420777);
//	                ciudadanoDTO.setNombre("LUIS");
//	                admCiudadano.guardar(ciudadanoDTO);	                
	                
	                
	                //Eliminar	                
//	                CiudadanoDTO ciudadanoDTO = admCiudadano.getById(111420777);
//	                admCiudadano.eliminar(ciudadanoDTO);
	                
	                AuditoriaProviderBL.clear();
	                
	            }

	            System.out.println("</MAIN>");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}

package cr.infosgroup.integraciones.liferay;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cr.infosgroup.integraciones.liferay.jsonws.model.Role;

public class Main {
	
	 public static void main(String[] args) {
	        try {
	            System.out.println("<MAIN>");
	            
	            System.out.println("<Contexto>");
	            try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApplicationContext.class)) {
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

	    			ILiferay liferay = (ILiferay) context.getBean("liferay");

	    			String correoUsuario = "lsanabria@infosgroup.cr";
	    			String claveUsuario = "infos";
	    			
	    			System.out.println( liferay.autenticar(correoUsuario,claveUsuario));
	    			
	    			for(String rol : liferay.findUserRolesLiferay(correoUsuario)) {
	    				System.out.println(rol);
	    			}
	    			
	    			Role role = liferay.getRoleByName("User");
	    			System.out.println(role.getName());
	                
	    			
	            }

	            System.out.println("</MAIN>");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }	

}

package cr.infosgroup.comun.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utileria para manejo de Json y Objetos
 * Utiliza libreria jackson
 * 
 * @author lsanabria
 *
 */
public class JsonUtil {
	
	/**
	 * Log de eventos
	 */
	private static final Logger log = LogManager.getLogger(JsonUtil.class);	
	
	/**
	 * Convierte un objeto en un string en formato json
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static String getJson(Object object) throws Exception{
		try {
			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			String json = mapper.writeValueAsString(object);
			return json; 
		} 
		catch (JsonProcessingException e) {
			log.error(e);
			throw e;
		}
	}

	/**
	 * Convierte un objeto en un string en formato json
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static String getJsonPrettyPrinter(Object object) throws Exception{
		try {
			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			return json; 
		} 
		catch (JsonProcessingException e) {
			log.error(e);
			throw e;
		}
	}	
	
	/**
	 * Convierte un String en formato json en un objeto T
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T getObject(String json, Class<T> clazz) throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			//Esta propiedad se coloca para que en caso de venir mas propiedades en el JSON no falle
		    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		    
//			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			T object =  mapper.readValue(json, clazz);
			return object;
		}
		catch (JsonProcessingException e) {
			log.error(e);
			throw e;
		}		
	}
	
	/**
	 * Convierte un String en formato json en un objeto T<K>
	 * 
	 * @param <T>
	 * @param json
	 * @param typeReference  Por ejemplo new TypeReference<Response<Ciudadano>>() {}
	 * @return
	 * @throws Exception
	 */
	public static <T> T getObject(String json,TypeReference<T> typeReference) throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			//Esta propiedad se coloca para que en caso de venir mas propiedades en el JSON no falle
		    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		    T object =  mapper.readValue(json, typeReference);
			return object;
		}
		catch (JsonProcessingException e) {
			log.error(e);
			throw e;
		}		
	}
	
	/**
	 * Main de ejemplo
	 * @param args
	 * @throws Exception
	 */
	/*public static void main(String[] args) throws Exception {
		
		Response<Ciudadano> response = new Response<Ciudadano>();
		
		Ciudadano c = new Ciudadano();
		c.setCedula("111420777");
		response.setData(c);
		
		String str = getJson(response);
		System.out.println(str);
		
		Response<Ciudadano> response2 = getObject(str, new TypeReference<Response<Ciudadano>>() {});
		
		Ciudadano c2 = new Ciudadano();
		c2 = response2.getData();
		
		System.out.println(c2.getCedula());
	}*/
	
	
	public static void main (String[] args) {		
		try {
			Persona p = new Persona();
			p.setNombre("Luis");
			p.setApellido("Sanabria");
			
			List<Persona> personas = new ArrayList<Persona>();
			personas.add(p);
			
			String json = JsonUtil.getJson(personas);
			
			List<Persona> personas2  = JsonUtil.getObject(json, new TypeReference<List<Persona>>() {});
			
			for (Persona persona : personas2) {
				System.out.println(persona.getNombre());
			}
			
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
		
}
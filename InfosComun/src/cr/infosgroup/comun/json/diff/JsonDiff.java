package cr.infosgroup.comun.json.diff;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.json.Json;
import jakarta.json.JsonMergePatch;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;
import jakarta.json.JsonWriterFactory;
import jakarta.json.stream.JsonGenerator;

public class JsonDiff {
	
	/**
	 * Log de eventos
	 */
	private static final Logger log = LogManager.getLogger(JsonDiff.class);	
		
	/**
	 * Compara dos Json y construye un objeto con las diferencias
	 * @param strJsonAnterior
	 * @param strJsonNuevo
	 * @return
	 */
	public static JsonDiffResponse jsonDiff(String strJsonAnterior,String strJsonNuevo) {
		JsonDiffResponse respuesta = new JsonDiffResponse();
		try {
			JsonValue jsonAnterior = Json.createReader(new StringReader(strJsonAnterior)).readValue();
			JsonValue jsonNuevo = Json.createReader(new StringReader(strJsonNuevo)).readValue();
			JsonMergePatch diffAnterior = Json.createMergeDiff(jsonNuevo.asJsonObject(),jsonAnterior.asJsonObject());
			JsonMergePatch diffNuevo = Json.createMergeDiff(jsonAnterior.asJsonObject(), jsonNuevo.asJsonObject());
		
			//Set de la respuesta
			respuesta.setJsonDiffAnterior(JsonDiff.format(diffAnterior.toJsonValue()));
			respuesta.setJsonDiffNuevo(JsonDiff.format(diffNuevo.toJsonValue()));
		}
		catch(Exception e) {
			log.error(e);
		}
		return respuesta;
	}
	
	public static String jsonFormat(String strJson) {
		String respuesta = new String();
		try {
			JsonValue json = Json.createReader(new StringReader(strJson)).readValue();
			//Set de la respuesta
			respuesta = JsonDiff.format(json);
		}
		catch(Exception e) {
			log.error(e);
		}
		return respuesta;
	}
	
	/**
	 * Da formato al json
	 * @param json
	 * @return
	 */
	private static String format(JsonValue json) {
		try {	    
			StringWriter stringWriter = new StringWriter();
			JsonDiff.prettyPrint(json, stringWriter);
			return stringWriter.toString();
		}
		catch(Exception e) {
			log.warn(e);
			return json.toString();
		}
	}

	/**
	 * Da formato al json
	 * @param json
	 * @param writer
	 */
	private static void prettyPrint(JsonValue json, Writer writer) {
		try {
			Map<String, Object> config = Collections.singletonMap(JsonGenerator.PRETTY_PRINTING, true);
			JsonWriterFactory writerFactory = Json.createWriterFactory(config);
		    try (JsonWriter jsonWriter = writerFactory.createWriter(writer)) {
		        jsonWriter.write(json);
		    }
		}
		catch(Exception e) {
			log.warn(e);
		}
	}
}

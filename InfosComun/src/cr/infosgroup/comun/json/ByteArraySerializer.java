package cr.infosgroup.comun.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ByteArraySerializer extends JsonSerializer<byte[]> {

	@Override
	public void serialize(byte[] bytes, JsonGenerator jgen, SerializerProvider provider)throws IOException, JsonProcessingException {
		jgen.writeStartArray();

		for (byte b : bytes) {
			jgen.writeNumber(b);
		}
		jgen.writeEndArray();
	}
}

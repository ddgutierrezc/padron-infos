package cr.infosgroup.comun.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class ByteArrayDeserializer extends JsonDeserializer<byte[]> {

	@Override
	public byte[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		//Toma el nodo
		JsonNode node = p.getCodec().readTree(p);
		ArrayList<Byte> arrayList = new ArrayList<Byte>();
		//Crea el iterador
		Iterator<JsonNode> it = node.elements();
		//Lo recorre
		while(it.hasNext()) {
			arrayList.add( new Byte(it.next().toString()) );
		}
		
		byte[] bytes = new byte[arrayList.size()];
		int i = 0;
		for (Byte b : arrayList) {
			bytes[i++] = b;
		}
		return bytes;
	}
}

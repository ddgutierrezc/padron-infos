package cr.infosgroup.comun.json;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
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
	
	/*
	public static void main (String[] args) {		
		try {
			Persona p = new Persona();
			p.setNombre("Luis");
			p.setApellido("Sanabria");
			
			String json = JsonUtil.getJson(p);
			
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	public static void main (String[] args) {		
		try {
			Eje eje1 = new Eje();
			eje1.setEje("e1");
			
			Eje eje2 = new Eje();
			eje2.setEje("e2");
			
			Eje eje3 = new Eje();
			eje3.setEje("e3");
			
			Eje eje4 = new Eje();
			eje4.setEje("e4");
			
			Eje eje5 = new Eje();
			eje5.setEje("e5");
			
			Eje eje6 = new Eje();
			eje6.setEje("e6");
			
			Eje eje7 = new Eje();
			eje7.setEje("e7");
			
			
			
			List<Eje> ge1 = new ArrayList<Eje>();
			ge1.add(eje1);
			
			List<Eje> ge2 = new ArrayList<Eje>();
			ge2.add(eje2);
			
			Definicion c2_plus = new Definicion();
			c2_plus.setGe1(ge1);
			c2_plus.setGe2(ge2);
			
			
		
			String json = JsonUtil.getJson(c2_plus);
			
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

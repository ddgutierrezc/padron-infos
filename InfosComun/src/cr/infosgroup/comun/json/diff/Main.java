package cr.infosgroup.comun.json.diff;

public class Main {
/*	
	public static void main(String args[]) {
		
		String strAnterior = "{\"id\":\"lsanabria\",\"nombre\":\"Luis\",\"apellidos\":\"Sanabria Vega\",\"correo\":\"lsanabria@infosgroup.cr\",\"activo\":true,\"roles\":[{\"rol\":\"scpydDiagramador\",\"descripcion\":\"Diagramador\",\"estado\":true,\"usuarios\":[]}],\"estaciones\":[{\"id\":1,\"nombre\":\"Ochomogo\",\"activo\":true,\"ips\":\"127.0.0.1\",\"acronimo\":\"OCH\",\"usuarios\":[]}]}";
		String strNuevo = "{\"id2\":\"lsanabria\",\"id\":\"lsanabria\",\"nombre\":\"LUIS\",\"apellidos\":\"SANABRIA VEGA\",\"correo\":\"lsanabria@infosgroup.cr\",\"activo\":true,\"roles\":[{\"rol\":\"scpydDiagramador\",\"descripcion\":\"Diagramador\",\"estado\":true,\"usuarios\":[]}],\"estaciones\":[{\"id\":1,\"nombre\":\"OCHOMOGO\",\"activo\":true,\"ips\":\"127.0.0.1\",\"acronimo\":\"OCH\",\"usuarios\":[]}]}";

		System.out.println("Json Original");
		System.out.println(strAnterior);
		System.out.println();
		
		System.out.println("Json Modificado");
		System.out.println(strNuevo);
		System.out.println();
		
		//Calcula las diferencias
		JsonDiffResponse jsonDiffResponse = JsonDiff.jsonDiff(strAnterior, strNuevo);

		System.out.println("Datos modificados en el original:");
		System.out.println(jsonDiffResponse.getJsonDiffAnterior());
		System.out.println();

		System.out.println("Datos modificados:");
		System.out.println(jsonDiffResponse.getJsonDiffNuevo());
		System.out.println();
	}
*/
	
	
	public static void main(String args[]) {
		
		String strOriginal = "{\"id\":\"lsanabria\",\"nombre\":\"Luis\",\"apellidos\":\"Sanabria Vega\"}";
		String strModificado = "{\"id\":\"lsanabria\",\"nombre\":\"LUIS\",\"apellidos\":\"Sanabria Vega\"}";

		System.out.println("Json Original");
		System.out.println(strOriginal);
		System.out.println();
		
		System.out.println("Json Modificado");
		System.out.println(strModificado);
		System.out.println();
		
		//Calcula las diferencias
		JsonDiffResponse jsonDiffResponse = JsonDiff.jsonDiff(strOriginal, strModificado);

		//System.out.println("Datos modificados en el original:");
		//System.out.println(jsonDiffResponse.getJsonDiffAnterior());
		//System.out.println();

		System.out.println("Datos modificados:");
		System.out.println(jsonDiffResponse.getJsonDiffNuevo());
		System.out.println();
	}	
	
}

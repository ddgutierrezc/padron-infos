package cr.infosgroup.padron.dto.archivo;

/**
 * Clase base para la transferencia de un archivo
 * 
 * @author lsanabria
 */
public class Archivo {
	
	private String nombre;
	private String dataBase64;
		
	public Archivo() {	
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDataBase64() {
		return dataBase64;
	}

	public void setDataBase64(String dataBase64) {
		this.dataBase64 = dataBase64;
	}
}

package cr.infosgroup.security.jwt.enums;

/**
 * Enum para definir los canales de consumo del api
 * 
 * @author lsanabria
 *
 */
public enum Canales {

	AppMovil("AppMovil"),
	AppWeb("AppWeb"),
	;
	
	private Canales(String nombre) {
		this.nombre = nombre;
	}
	
	// nombre
	private String nombre;
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Nombre: ");
		str.append(this.nombre);
		return str.toString();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}

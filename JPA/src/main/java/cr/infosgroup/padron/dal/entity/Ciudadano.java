package cr.infosgroup.padron.dal.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ciudadano database table.
 * 
 */
@Entity
@Table(name="ciudadano")
@NamedQuery(name="Ciudadano.findAll", query="SELECT c FROM Ciudadano c")
public class Ciudadano  {
	private static final long serialVersionUID = 1L;

	@Id
	private int cedula;

	private String apellido1;

	private String apellido2;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;

	@Temporal(TemporalType.DATE)
	private Date fechaCaducidad;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecModificacion;

	private int junta;

	private String nombre;

	private String usrCreacion;

	private String usrModificacion;

	//bi-directional many-to-one association to DireccionElectoral
	@ManyToOne
	@JoinColumn(name="codDirElectoral")
	private DireccionElectoral direccionElectoral;

	public Ciudadano() {
	}

	public int getCedula() {
		return this.cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Date getFecCreacion() {
		return this.fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public Date getFechaCaducidad() {
		return this.fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Date getFecModificacion() {
		return this.fecModificacion;
	}

	public void setFecModificacion(Date fecModificacion) {
		this.fecModificacion = fecModificacion;
	}

	public int getJunta() {
		return this.junta;
	}

	public void setJunta(int junta) {
		this.junta = junta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsrCreacion() {
		return this.usrCreacion;
	}

	public void setUsrCreacion(String usrCreacion) {
		this.usrCreacion = usrCreacion;
	}

	public String getUsrModificacion() {
		return this.usrModificacion;
	}

	public void setUsrModificacion(String usrModificacion) {
		this.usrModificacion = usrModificacion;
	}

	public DireccionElectoral getDireccionElectoral() {
		return this.direccionElectoral;
	}

	public void setDireccionElectoral(DireccionElectoral direccionElectoral) {
		this.direccionElectoral = direccionElectoral;
	}

}
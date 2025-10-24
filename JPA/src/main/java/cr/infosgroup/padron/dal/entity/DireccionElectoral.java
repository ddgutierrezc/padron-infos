package cr.infosgroup.padron.dal.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the direccion_electoral database table.
 * 
 */
@Entity
@Table(name="direccion_electoral")
@NamedQuery(name="DireccionElectoral.findAll", query="SELECT d FROM DireccionElectoral d")
public class DireccionElectoral  {
	private static final long serialVersionUID = 1L;

	@Id
	private int codDirElectoral;

	private String canton;

	private String distritoElectoral;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecModificacion;

	private String provincia;

	private String usrCreacion;

	private String usrModificacion;

	//bi-directional many-to-one association to Ciudadano
	@OneToMany(mappedBy="direccionElectoral")
	private List<Ciudadano> ciudadanos;

	public DireccionElectoral() {
	}

	public int getCodDirElectoral() {
		return this.codDirElectoral;
	}

	public void setCodDirElectoral(int codDirElectoral) {
		this.codDirElectoral = codDirElectoral;
	}

	public String getCanton() {
		return this.canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getDistritoElectoral() {
		return this.distritoElectoral;
	}

	public void setDistritoElectoral(String distritoElectoral) {
		this.distritoElectoral = distritoElectoral;
	}

	public Date getFecCreacion() {
		return this.fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public Date getFecModificacion() {
		return this.fecModificacion;
	}

	public void setFecModificacion(Date fecModificacion) {
		this.fecModificacion = fecModificacion;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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

	public List<Ciudadano> getCiudadanos() {
		return this.ciudadanos;
	}

	public void setCiudadanos(List<Ciudadano> ciudadanos) {
		this.ciudadanos = ciudadanos;
	}

	public Ciudadano addCiudadano(Ciudadano ciudadano) {
		getCiudadanos().add(ciudadano);
		ciudadano.setDireccionElectoral(this);

		return ciudadano;
	}

	public Ciudadano removeCiudadano(Ciudadano ciudadano) {
		getCiudadanos().remove(ciudadano);
		ciudadano.setDireccionElectoral(null);

		return ciudadano;
	}

}
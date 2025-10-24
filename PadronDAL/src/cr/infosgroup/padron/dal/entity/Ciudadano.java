package cr.infosgroup.padron.dal.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Audited
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "ciudadano")
public class Ciudadano {

    @Id
    private Integer cedula;

    private Integer junta;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private LocalDate fechaCaducidad;

    @CreatedDate
    private LocalDateTime fecCreacion;

    @LastModifiedDate
    private LocalDateTime fecModificacion;

    @CreatedBy
    private String usrCreacion;

    @LastModifiedBy
    private String usrModificacion;  
    
    @ManyToOne
	@JoinColumn(name="codDirElectoral")
    private DireccionElectoral direccionElectoral;

	public Ciudadano() {
	}

	public Integer getCedula() {
		return cedula;
	}

	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}

	public Integer getJunta() {
		return junta;
	}

	public void setJunta(Integer junta) {
		this.junta = junta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public LocalDateTime getFecCreacion() {
		return fecCreacion;
	}

	public void setFecCreacion(LocalDateTime fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public LocalDateTime getFecModificacion() {
		return fecModificacion;
	}

	public void setFecModificacion(LocalDateTime fecModificacion) {
		this.fecModificacion = fecModificacion;
	}

	public String getUsrCreacion() {
		return usrCreacion;
	}

	public void setUsrCreacion(String usrCreacion) {
		this.usrCreacion = usrCreacion;
	}

	public String getUsrModificacion() {
		return usrModificacion;
	}

	public void setUsrModificacion(String usrModificacion) {
		this.usrModificacion = usrModificacion;
	}

	public DireccionElectoral getDireccionElectoral() {
		return direccionElectoral;
	}

	public void setDireccionElectoral(DireccionElectoral direccionElectoral) {
		this.direccionElectoral = direccionElectoral;
	}
}

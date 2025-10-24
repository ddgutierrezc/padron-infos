package cr.infosgroup.padron.dal.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Audited
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "direccion_electoral")
public class DireccionElectoral {

    @Id
    private Integer codDirElectoral;

    private String provincia;

    private String canton;

    private String distritoElectoral;

    @CreatedDate
    private LocalDateTime fecCreacion;

    @LastModifiedDate
    private LocalDateTime fecModificacion;

    @CreatedBy
    private String usrCreacion;

    @LastModifiedBy
    private String usrModificacion;      
    
    // Relación con Ciudadano
    @OneToMany(mappedBy="direccionElectoral")
	private List<Ciudadano> ciudadanos;
    
	public DireccionElectoral() {
	}
    
    // Getters y setters
    public Integer getCodDirElectoral() {
        return codDirElectoral;
    }

    public void setCodDirElectoral(Integer codDirElectoral) {
        this.codDirElectoral = codDirElectoral;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistritoElectoral() {
        return distritoElectoral;
    }

    public void setDistritoElectoral(String distritoElectoral) {
        this.distritoElectoral = distritoElectoral;
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

	public List<Ciudadano> getCiudadanos() {
        return ciudadanos;
    }

    public void setCiudadanos(List<Ciudadano> ciudadanos) {
        this.ciudadanos = ciudadanos;
    }
}

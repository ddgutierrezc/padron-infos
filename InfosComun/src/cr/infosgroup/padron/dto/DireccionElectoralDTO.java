package cr.infosgroup.padron.dto;

public class DireccionElectoralDTO {

    private Integer codDirElectoral;
    private String nombre;
    private String canton;
    private String distrito;
    private String provincia;

    // Getters y Setters
    public Integer getCodDirElectoral() {
        return codDirElectoral;
    }

    public void setCodDirElectoral(Integer codDirElectoral) {
        this.codDirElectoral = codDirElectoral;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}

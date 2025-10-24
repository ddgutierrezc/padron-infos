package cr.infosgroup.integraciones.liferay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelo RadioProgram - Entidad Compuesta de 4 Componentes en Liferay
 *
 * Un "Programa de Radio" está compuesto por:
 * 1. STRUCTURED-CONTENT-FOLDER (carpeta contenedora)
 * 2. TAXONOMY-CATEGORY (categoría del programa)
 * 3. STRUCTURED-CONTENT - Descripción (contentStructureId: 39448)
 * 4. STRUCTURED-CONTENT - Publicador (contentStructureId: 39487)
 *
 * Este modelo encapsula toda la información de un programa completo.
 *
 * @author crodriguez, lsanabria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RadioProgram {

    // ==================================================
    // IDs de los componentes en Liferay
    // ==================================================
    private Long structuredContentFolderId;     // ID del folder del programa
    private String taxonomyCategoryId;          // ID de la categoría (String porque API retorna String)
    private Long descripcionContentId;          // ID del structured-content descripción (39448)
    private Long publicadorContentId;           // ID del structured-content publicador (39487)

    // ==================================================
    // Información básica del programa
    // ==================================================
    private String nombrePrograma;              // Nombre del programa (usado en folder, category, y contents)
    private String descripcionPrograma;         // Descripción del programa (usado en folder y category)

    // ==================================================
    // Información de la descripción (contentStructureId: 39448)
    // ==================================================
    private String seccion;                     // "[\"color-radio-universidad\"]"
    private Long imagenProgramaDocumentId;      // ID del documento (ej: 38502)
    private String diaHora;                     // "Miércoles, 5:00pm"
    private String descripcion;                 // Descripción larga del programa
    private String tituloProduccion;            // "Productor"
    private String nombreProductor;             // "Radioemisoras UCR"
    private String descripcionProductor;        // Descripción del productor
    private String correo;                      // "correo@ucr.ac.cr"
    private String urlFacebook;                 // "radiouniversidadcr"
    private String urlXTwitter;                 // "radiouniversidadcr"
    private String urlInstagram;                // "radiouniversidadcr"
    private String urlYoutube;                  // "RadioUniversidadCR"

    // ==================================================
    // Metadatos
    // ==================================================
    private String dateCreated;
    private String dateModified;

    // ==================================================
    // Constructores
    // ==================================================
    public RadioProgram() {}

    /**
     * Constructor con datos mínimos requeridos
     */
    public RadioProgram(String nombrePrograma, String descripcionPrograma) {
        this.nombrePrograma = nombrePrograma;
        this.descripcionPrograma = descripcionPrograma;
    }

    // ==================================================
    // Getters y Setters - IDs de componentes
    // ==================================================

    public Long getStructuredContentFolderId() {
        return structuredContentFolderId;
    }

    public void setStructuredContentFolderId(Long structuredContentFolderId) {
        this.structuredContentFolderId = structuredContentFolderId;
    }

    public String getTaxonomyCategoryId() {
        return taxonomyCategoryId;
    }

    public void setTaxonomyCategoryId(String taxonomyCategoryId) {
        this.taxonomyCategoryId = taxonomyCategoryId;
    }

    public Long getDescripcionContentId() {
        return descripcionContentId;
    }

    public void setDescripcionContentId(Long descripcionContentId) {
        this.descripcionContentId = descripcionContentId;
    }

    public Long getPublicadorContentId() {
        return publicadorContentId;
    }

    public void setPublicadorContentId(Long publicadorContentId) {
        this.publicadorContentId = publicadorContentId;
    }

    // ==================================================
    // Getters y Setters - Información básica
    // ==================================================

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getDescripcionPrograma() {
        return descripcionPrograma;
    }

    public void setDescripcionPrograma(String descripcionPrograma) {
        this.descripcionPrograma = descripcionPrograma;
    }

    // ==================================================
    // Getters y Setters - Información de descripción
    // ==================================================

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Long getImagenProgramaDocumentId() {
        return imagenProgramaDocumentId;
    }

    public void setImagenProgramaDocumentId(Long imagenProgramaDocumentId) {
        this.imagenProgramaDocumentId = imagenProgramaDocumentId;
    }

    public String getDiaHora() {
        return diaHora;
    }

    public void setDiaHora(String diaHora) {
        this.diaHora = diaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTituloProduccion() {
        return tituloProduccion;
    }

    public void setTituloProduccion(String tituloProduccion) {
        this.tituloProduccion = tituloProduccion;
    }

    public String getNombreProductor() {
        return nombreProductor;
    }

    public void setNombreProductor(String nombreProductor) {
        this.nombreProductor = nombreProductor;
    }

    public String getDescripcionProductor() {
        return descripcionProductor;
    }

    public void setDescripcionProductor(String descripcionProductor) {
        this.descripcionProductor = descripcionProductor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUrlFacebook() {
        return urlFacebook;
    }

    public void setUrlFacebook(String urlFacebook) {
        this.urlFacebook = urlFacebook;
    }

    public String getUrlXTwitter() {
        return urlXTwitter;
    }

    public void setUrlXTwitter(String urlXTwitter) {
        this.urlXTwitter = urlXTwitter;
    }

    public String getUrlInstagram() {
        return urlInstagram;
    }

    public void setUrlInstagram(String urlInstagram) {
        this.urlInstagram = urlInstagram;
    }

    public String getUrlYoutube() {
        return urlYoutube;
    }

    public void setUrlYoutube(String urlYoutube) {
        this.urlYoutube = urlYoutube;
    }

    // ==================================================
    // Getters y Setters - Metadatos
    // ==================================================

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    // ==================================================
    // Métodos de utilidad
    // ==================================================

    /**
     * Verifica si el programa tiene todos los IDs necesarios para operaciones de actualización/eliminación
     */
    public boolean hasAllIds() {
        return structuredContentFolderId != null
            && taxonomyCategoryId != null
            && descripcionContentId != null
            && publicadorContentId != null;
    }

    /**
     * Verifica si el programa tiene la información mínima requerida para creación
     */
    public boolean hasMinimumData() {
        return nombrePrograma != null && !nombrePrograma.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "RadioProgram{" +
                "folderId=" + structuredContentFolderId +
                ", categoryId='" + taxonomyCategoryId + '\'' +
                ", descripcionId=" + descripcionContentId +
                ", publicadorId=" + publicadorContentId +
                ", nombrePrograma='" + nombrePrograma + '\'' +
                ", descripcionPrograma='" + descripcionPrograma + '\'' +
                ", seccion='" + seccion + '\'' +
                ", diaHora='" + diaHora + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateModified='" + dateModified + '\'' +
                '}';
    }
}

package cr.infosgroup.integraciones.liferay.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelo que representa el creador/autor del contenido en Liferay
 * Contiene información del usuario que creó el programa o temporada
 *
 * @author crodriguez
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Creator {

    private String additionalName;
    private String contentType;
    private String familyName;
    private String givenName;
    private Long id;
    private String name;

    public Creator() {}

    public Creator(String additionalName, String contentType, String familyName, String givenName, Long id, String name) {
        this.additionalName = additionalName;
        this.contentType = contentType;
        this.familyName = familyName;
        this.givenName = givenName;
        this.id = id;
        this.name = name;
    }

    public String getAdditionalName() {
        return additionalName;
    }

    public void setAdditionalName(String additionalName) {
        this.additionalName = additionalName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Creator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
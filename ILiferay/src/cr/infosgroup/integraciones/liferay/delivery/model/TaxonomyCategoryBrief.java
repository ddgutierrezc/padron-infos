package cr.infosgroup.integraciones.liferay.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelo que representa una categoría de taxonomía en Liferay
 * Usado para clasificación y etiquetado de contenido
 *
 * @author crodriguez
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxonomyCategoryBrief {

    private Long taxonomyCategoryId;
    private String taxonomyCategoryName;

    public TaxonomyCategoryBrief() {}

    public TaxonomyCategoryBrief(Long taxonomyCategoryId, String taxonomyCategoryName) {
        this.taxonomyCategoryId = taxonomyCategoryId;
        this.taxonomyCategoryName = taxonomyCategoryName;
    }

    public Long getTaxonomyCategoryId() {
        return taxonomyCategoryId;
    }

    public void setTaxonomyCategoryId(Long taxonomyCategoryId) {
        this.taxonomyCategoryId = taxonomyCategoryId;
    }

    public String getTaxonomyCategoryName() {
        return taxonomyCategoryName;
    }

    public void setTaxonomyCategoryName(String taxonomyCategoryName) {
        this.taxonomyCategoryName = taxonomyCategoryName;
    }

    @Override
    public String toString() {
        return "TaxonomyCategoryBrief{" +
                "taxonomyCategoryId=" + taxonomyCategoryId +
                ", taxonomyCategoryName='" + taxonomyCategoryName + '\'' +
                '}';
    }
}
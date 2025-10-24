package cr.infosgroup.integraciones.liferay.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelo que representa contenido renderizado de Liferay
 * Contiene información sobre plantillas y URLs de contenido renderizado
 *
 * @author crodriguez
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RenderedContent {

    private String contentTemplateId;
    private String contentTemplateName;
    private String renderedContentURL;

    public RenderedContent() {}

    public RenderedContent(String contentTemplateId, String contentTemplateName, String renderedContentURL) {
        this.contentTemplateId = contentTemplateId;
        this.contentTemplateName = contentTemplateName;
        this.renderedContentURL = renderedContentURL;
    }

    public String getContentTemplateId() {
        return contentTemplateId;
    }

    public void setContentTemplateId(String contentTemplateId) {
        this.contentTemplateId = contentTemplateId;
    }

    public String getContentTemplateName() {
        return contentTemplateName;
    }

    public void setContentTemplateName(String contentTemplateName) {
        this.contentTemplateName = contentTemplateName;
    }

    public String getRenderedContentURL() {
        return renderedContentURL;
    }

    public void setRenderedContentURL(String renderedContentURL) {
        this.renderedContentURL = renderedContentURL;
    }

    @Override
    public String toString() {
        return "RenderedContent{" +
                "contentTemplateId='" + contentTemplateId + '\'' +
                ", contentTemplateName='" + contentTemplateName + '\'' +
                ", renderedContentURL='" + renderedContentURL + '\'' +
                '}';
    }
}
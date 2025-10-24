package cr.infosgroup.integraciones.liferay.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentFieldValue {

    private String data;
    private Document document;
    private StructuredContentLink structuredContentLink;

    public ContentFieldValue() {}

    public ContentFieldValue(String data) {
        this.data = data;
    }

    // Getters y Setters
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public StructuredContentLink getStructuredContentLink() {
        return structuredContentLink;
    }

    public void setStructuredContentLink(StructuredContentLink structuredContentLink) {
        this.structuredContentLink = structuredContentLink;
    }

    // Clase interna para Document
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Document {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    // Clase interna para StructuredContentLink
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StructuredContentLink {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
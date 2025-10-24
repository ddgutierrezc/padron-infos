package cr.infosgroup.integraciones.liferay.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentField {
    
    private ContentFieldValue contentFieldValue;
    private String dataType;
    private String inputControl;
    private String label;
    private String name;
    private List<ContentField> nestedContentFields;
    private boolean repeatable;
    
    public ContentField() {}
    
    // Getters y Setters
    public ContentFieldValue getContentFieldValue() {
        return contentFieldValue;
    }
    
    public void setContentFieldValue(ContentFieldValue contentFieldValue) {
        this.contentFieldValue = contentFieldValue;
    }
    
    public String getDataType() {
        return dataType;
    }
    
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
    public String getInputControl() {
        return inputControl;
    }
    
    public void setInputControl(String inputControl) {
        this.inputControl = inputControl;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<ContentField> getNestedContentFields() {
        return nestedContentFields;
    }
    
    public void setNestedContentFields(List<ContentField> nestedContentFields) {
        this.nestedContentFields = nestedContentFields;
    }
    
    public boolean isRepeatable() {
        return repeatable;
    }
    
    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }
}

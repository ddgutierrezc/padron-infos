package cr.infosgroup.integraciones.liferay.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StructuredContent {
    
    private Long id;
    private String key;
    private String title;
    private String description;
    private String uuid;
    private Long siteId;
    private Long contentStructureId;
    private String dateCreated;
    private String dateModified;
    private String datePublished;
    private String friendlyUrlPath;
    private List<String> availableLanguages;
    private List<ContentField> contentFields;
    private Creator creator;
    private List<Object> customFields;
    private List<Object> keywords;
    private int numberOfComments;
    private List<Object> relatedContents;
    private List<RenderedContent> renderedContents;
    private boolean subscribed;
    private List<TaxonomyCategoryBrief> taxonomyCategoryBriefs;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getUuid() {
        return uuid;
    }
    
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public Long getSiteId() {
        return siteId;
    }
    
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
    
    public Long getContentStructureId() {
        return contentStructureId;
    }
    
    public void setContentStructureId(Long contentStructureId) {
        this.contentStructureId = contentStructureId;
    }
    
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
    
    public String getDatePublished() {
        return datePublished;
    }
    
    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }
    
    public String getFriendlyUrlPath() {
        return friendlyUrlPath;
    }
    
    public void setFriendlyUrlPath(String friendlyUrlPath) {
        this.friendlyUrlPath = friendlyUrlPath;
    }
    
    public List<String> getAvailableLanguages() {
        return availableLanguages;
    }
    
    public void setAvailableLanguages(List<String> availableLanguages) {
        this.availableLanguages = availableLanguages;
    }
    
    public List<ContentField> getContentFields() {
        return contentFields;
    }
    
    public void setContentFields(List<ContentField> contentFields) {
        this.contentFields = contentFields;
    }
    
    public Creator getCreator() {
        return creator;
    }
    
    public void setCreator(Creator creator) {
        this.creator = creator;
    }
    
    public List<Object> getCustomFields() {
        return customFields;
    }
    
    public void setCustomFields(List<Object> customFields) {
        this.customFields = customFields;
    }
    
    public List<Object> getKeywords() {
        return keywords;
    }
    
    public void setKeywords(List<Object> keywords) {
        this.keywords = keywords;
    }
    
    public int getNumberOfComments() {
        return numberOfComments;
    }
    
    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }
    
    public List<Object> getRelatedContents() {
        return relatedContents;
    }
    
    public void setRelatedContents(List<Object> relatedContents) {
        this.relatedContents = relatedContents;
    }
    
    public List<RenderedContent> getRenderedContents() {
        return renderedContents;
    }
    
    public void setRenderedContents(List<RenderedContent> renderedContents) {
        this.renderedContents = renderedContents;
    }
    
    public boolean isSubscribed() {
        return subscribed;
    }
    
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
    
    public List<TaxonomyCategoryBrief> getTaxonomyCategoryBriefs() {
        return taxonomyCategoryBriefs;
    }

    public void setTaxonomyCategoryBriefs(List<TaxonomyCategoryBrief> taxonomyCategoryBriefs) {
        this.taxonomyCategoryBriefs = taxonomyCategoryBriefs;
    }
}


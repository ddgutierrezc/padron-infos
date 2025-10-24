package cr.infosgroup.integraciones.liferay.taxonomy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxonomyVocabulary {

    private Long id;
    private String name;
    private String description;
    private String dateCreated;
    private String dateModified;
    private Creator creator;
    private Long numberOfTaxonomyCategories;
    private Long siteId;

    public TaxonomyVocabulary() {}

    public TaxonomyVocabulary(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Long getNumberOfTaxonomyCategories() {
        return numberOfTaxonomyCategories;
    }

    public void setNumberOfTaxonomyCategories(Long numberOfTaxonomyCategories) {
        this.numberOfTaxonomyCategories = numberOfTaxonomyCategories;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        return "TaxonomyVocabulary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", numberOfTaxonomyCategories=" + numberOfTaxonomyCategories +
                ", siteId=" + siteId +
                '}';
    }
}
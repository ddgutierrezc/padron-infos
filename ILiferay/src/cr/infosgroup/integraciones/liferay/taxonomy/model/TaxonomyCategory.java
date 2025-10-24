package cr.infosgroup.integraciones.liferay.taxonomy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxonomyCategory {

    private String id;
    private String name;
    private String description;
    private String dateCreated;
    private String dateModified;
    private Creator creator;
    private TaxonomyVocabulary parentTaxonomyVocabulary;
    private Long numberOfTaxonomyCategories;
    private Long taxonomyCategoryUsageCount;
    private List<TaxonomyCategory> taxonomyCategories;

    public TaxonomyCategory() {}

    public TaxonomyCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public TaxonomyVocabulary getParentTaxonomyVocabulary() {
        return parentTaxonomyVocabulary;
    }

    public void setParentTaxonomyVocabulary(TaxonomyVocabulary parentTaxonomyVocabulary) {
        this.parentTaxonomyVocabulary = parentTaxonomyVocabulary;
    }

    public Long getNumberOfTaxonomyCategories() {
        return numberOfTaxonomyCategories;
    }

    public void setNumberOfTaxonomyCategories(Long numberOfTaxonomyCategories) {
        this.numberOfTaxonomyCategories = numberOfTaxonomyCategories;
    }

    public Long getTaxonomyCategoryUsageCount() {
        return taxonomyCategoryUsageCount;
    }

    public void setTaxonomyCategoryUsageCount(Long taxonomyCategoryUsageCount) {
        this.taxonomyCategoryUsageCount = taxonomyCategoryUsageCount;
    }

    public List<TaxonomyCategory> getTaxonomyCategories() {
        return taxonomyCategories;
    }

    public void setTaxonomyCategories(List<TaxonomyCategory> taxonomyCategories) {
        this.taxonomyCategories = taxonomyCategories;
    }

    @Override
    public String toString() {
        return "TaxonomyCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", numberOfTaxonomyCategories=" + numberOfTaxonomyCategories +
                ", taxonomyCategoryUsageCount=" + taxonomyCategoryUsageCount +
                '}';
    }
}
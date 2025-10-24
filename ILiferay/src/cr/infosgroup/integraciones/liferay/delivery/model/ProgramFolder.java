package cr.infosgroup.integraciones.liferay.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelo para representar una carpeta de programa de radio en Liferay
 * Corresponde a las carpetas dentro de "Programas" que contienen temporadas
 *
 * @author crodriguez
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramFolder {

    private Long id;
    private String name;
    private String description;
    private String dateCreated;
    private String dateModified;
    private Long parentStructuredContentFolderId;
    private Long siteId;
    private int numberOfStructuredContents;
    private int numberOfStructuredContentFolders;
    private Creator creator;
    private boolean subscribed;
    private Long radioCategoryId;
    private Long programCategoryId;

    public ProgramFolder() {}

    public ProgramFolder(Long id, String name, String description) {
        this.id = id;
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

    public Long getParentStructuredContentFolderId() {
        return parentStructuredContentFolderId;
    }

    public void setParentStructuredContentFolderId(Long parentStructuredContentFolderId) {
        this.parentStructuredContentFolderId = parentStructuredContentFolderId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public int getNumberOfStructuredContents() {
        return numberOfStructuredContents;
    }

    public void setNumberOfStructuredContents(int numberOfStructuredContents) {
        this.numberOfStructuredContents = numberOfStructuredContents;
    }

    public int getNumberOfStructuredContentFolders() {
        return numberOfStructuredContentFolders;
    }

    public void setNumberOfStructuredContentFolders(int numberOfStructuredContentFolders) {
        this.numberOfStructuredContentFolders = numberOfStructuredContentFolders;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Long getRadioCategoryId() {
        return radioCategoryId;
    }

    public void setRadioCategoryId(Long radioCategoryId) {
        this.radioCategoryId = radioCategoryId;
    }

    public Long getProgramCategoryId() {
        return programCategoryId;
    }

    public void setProgramCategoryId(Long programCategoryId) {
        this.programCategoryId = programCategoryId;
    }

    @Override
    public String toString() {
        return "ProgramFolder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", numberOfStructuredContents=" + numberOfStructuredContents +
                ", numberOfStructuredContentFolders=" + numberOfStructuredContentFolders +
                ", siteId=" + siteId +
                '}';
    }
}
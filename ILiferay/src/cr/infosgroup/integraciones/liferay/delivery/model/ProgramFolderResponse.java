package cr.infosgroup.integraciones.liferay.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Modelo para representar la respuesta de la API que contiene una lista de programas
 * Usado para el endpoint que obtiene todos los programas de radio
 *
 * @author crodriguez
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramFolderResponse {

    private List<ProgramFolder> items;
    private int page;
    private int pageSize;
    private int lastPage;
    private int totalCount;
    private List<Object> facets;

    public ProgramFolderResponse() {}

    public ProgramFolderResponse(List<ProgramFolder> items, int totalCount) {
        this.items = items;
        this.totalCount = totalCount;
    }

    public List<ProgramFolder> getItems() {
        return items;
    }

    public void setItems(List<ProgramFolder> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Object> getFacets() {
        return facets;
    }

    public void setFacets(List<Object> facets) {
        this.facets = facets;
    }

    @Override
    public String toString() {
        return "ProgramFolderResponse{" +
                "totalCount=" + totalCount +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", lastPage=" + lastPage +
                ", items=" + (items != null ? items.size() + " programs" : "null") +
                '}';
    }
}
package cr.infosgroup.integraciones.liferay.taxonomy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

/**
 * Respuesta paginada de categorías de taxonomía de Liferay
 *
 * @author crodriguez
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxonomyCategoryResponse {

    private List<TaxonomyCategory> items;
    private Long page;
    private Long pageSize;
    private Long totalCount;
    private Long lastPage;
    private Map<String, Object> actions;

    public TaxonomyCategoryResponse() {}

    public List<TaxonomyCategory> getItems() {
        return items;
    }

    public void setItems(List<TaxonomyCategory> items) {
        this.items = items;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getLastPage() {
        return lastPage;
    }

    public void setLastPage(Long lastPage) {
        this.lastPage = lastPage;
    }

    public Map<String, Object> getActions() {
        return actions;
    }

    public void setActions(Map<String, Object> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "TaxonomyCategoryResponse{" +
                "totalCount=" + totalCount +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", items=" + (items != null ? items.size() : 0) + " categories" +
                '}';
    }
}

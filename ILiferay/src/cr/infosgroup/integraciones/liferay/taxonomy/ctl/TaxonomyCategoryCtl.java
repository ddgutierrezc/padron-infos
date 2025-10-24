package cr.infosgroup.integraciones.liferay.taxonomy.ctl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cr.infosgroup.integraciones.liferay.ctl.BaseCtl;
import cr.infosgroup.integraciones.liferay.exception.LiferayJsonApiException;
import cr.infosgroup.integraciones.liferay.taxonomy.model.TaxonomyCategoryResponse;

public class TaxonomyCategoryCtl extends BaseCtl {

    private static final Logger log = LogManager.getLogger(TaxonomyCategoryCtl.class);
    
    /**
     * URL base del servicio
     */
    private static String SERVICIO = BaseCtl.ROOT_API_TAXONOMY + "taxonomy-categories/";
   
    // URLS de las operaciones
    
    private static String URL_GET_CATEGORIES_BY_PARENT_TAXONOMY =  SERVICIO + "{parentCategoryId}/taxonomy-categories";

        
    public TaxonomyCategoryCtl(String urlRootLiferay, String userLiferay, String passwordLiferay) {
        super(urlRootLiferay, userLiferay, passwordLiferay);
    }
    
    /**
     * GET /taxonomy-categories/{parentCategoryId}/taxonomy-categories
     * Obtiene subcategorías de una categoría padre
     */
    public TaxonomyCategoryResponse getCategoriesByParentTaxonomy(Long parentCategoryId)  throws LiferayJsonApiException  {
    	log.info("GET subcategorías de categoría padre ID: {}", parentCategoryId);
            
    	String url = URL_GET_CATEGORIES_BY_PARENT_TAXONOMY
            					.replace("{parentCategoryId}", String.valueOf(parentCategoryId));

    	TaxonomyCategoryResponse respuesta = super.getObjectFromJson(url, TaxonomyCategoryResponse.class);
    	log.info("Subcategorías obtenidas exitosamente. Total: {}", respuesta.getTotalCount());

        return respuesta;
    }    


}
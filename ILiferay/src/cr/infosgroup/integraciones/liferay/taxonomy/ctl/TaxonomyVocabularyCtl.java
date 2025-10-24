package cr.infosgroup.integraciones.liferay.taxonomy.ctl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cr.infosgroup.integraciones.liferay.ctl.BaseCtl;
import cr.infosgroup.integraciones.liferay.exception.LiferayJsonApiException;
import cr.infosgroup.integraciones.liferay.taxonomy.model.TaxonomyCategoryResponse;

public class TaxonomyVocabularyCtl extends BaseCtl {

    private static final Logger log = LogManager.getLogger(TaxonomyVocabularyCtl.class);
    
    /**
     * URL base del servicio
     */
    private static String SERVICIO = BaseCtl.ROOT_API_TAXONOMY + "taxonomy-vocabularies/";
   
    // URLS de las operaciones
    
    private static String URL_GET_CATEGORIES_BY_VOCABULARY_TAXONOMY =  SERVICIO + "{vocabularyId}/taxonomy-categories" ;

        
    public TaxonomyVocabularyCtl(String urlRootLiferay, String userLiferay, String passwordLiferay) {
        super(urlRootLiferay, userLiferay, passwordLiferay);
    }
    
    /**
     * GET /taxonomy-vocabularies/{vocabularyId}/taxonomy-categories
     * Obtiene categorías de un vocabulario
     */
    public TaxonomyCategoryResponse getCategoriesByVocabularyTaxonomy(Long vocabularyId)throws LiferayJsonApiException  {
        log.info("GET categorías de vocabulario ID: {}", vocabularyId);
            
    	String url = URL_GET_CATEGORIES_BY_VOCABULARY_TAXONOMY
            					.replace("{vocabularyId}", String.valueOf(vocabularyId));

    	TaxonomyCategoryResponse respuesta = super.getObjectFromJson(url, TaxonomyCategoryResponse.class);
        log.info("Categorías obtenidas exitosamente. Total: {}", respuesta.getTotalCount());

        return respuesta;
    }


 
    

}
package cr.infosgroup.integraciones.liferay.delivery.ctl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cr.infosgroup.integraciones.liferay.ctl.BaseCtl;
import cr.infosgroup.integraciones.liferay.delivery.model.StructuredContent;
import cr.infosgroup.integraciones.liferay.exception.LiferayJsonApiException;

public class DeliveryCtl extends BaseCtl {

    private static final Logger log = LogManager.getLogger(DeliveryCtl.class);
    
    /**
     * StructuredContent
     */
    private static String STRUCTURED_CONTENTS = BaseCtl.ROOT_API_DELIVERY + "structured-contents/";
  
    // URLS de las operaciones
    
    // /structured-contents/{structuredContentId}
    private static String STRUCTURED_CONTENT_ID = STRUCTURED_CONTENTS + "{structuredContentId}";
    
    
    /**
     * StructuredContentFolder
     */
    private static String STRUCTURED_CONTENT_FOLDERS = BaseCtl.ROOT_API_DELIVERY + "structured-content-folders/";
   
      
    public DeliveryCtl(String urlRootLiferay, String userLiferay, String passwordLiferay) {
        super(urlRootLiferay, userLiferay, passwordLiferay);
    }

    //StructuredContent

	public StructuredContent getStructuredContents(Long contentStructureId) throws LiferayJsonApiException {
		String url = STRUCTURED_CONTENT_ID.replace("{structuredContentId}",
				String.valueOf(contentStructureId));

		log.info("GET {}", url);
		StructuredContent respuesta = super.getObjectFromJson(url, StructuredContent.class);
		log.info("Descripción obtenida exitosamente");

		return respuesta;
	}
    
	public StructuredContent putStructuredContents(StructuredContent  structuredContent) throws LiferayJsonApiException {
		String url = STRUCTURED_CONTENT_ID.replace("{structuredContentId}",
				String.valueOf(structuredContent.getContentStructureId()));

		log.info("PUT {}", url);
		StructuredContent respuesta = super.putObjectFromJson(url, StructuredContent.class);
		log.info("Descripción obtenida exitosamente");

		return respuesta;
	}
	
	public StructuredContent deleteStructuredContents(Long contentStructureId) throws LiferayJsonApiException {
		String url = STRUCTURED_CONTENT_ID.replace("{structuredContentId}",
				String.valueOf(contentStructureId));

		log.info("DELETE {}", url);
		StructuredContent respuesta = super.deleteObjectFromJson(url, StructuredContent.class);
		log.info("Descripción obtenida exitosamente");

		return respuesta;
	}
    
    
  
}
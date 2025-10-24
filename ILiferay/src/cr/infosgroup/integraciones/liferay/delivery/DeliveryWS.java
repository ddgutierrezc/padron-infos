package cr.infosgroup.integraciones.liferay.delivery;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cr.infosgroup.integraciones.liferay.ctl.BaseCtl;
import cr.infosgroup.integraciones.liferay.delivery.ctl.DeliveryCtl;
import cr.infosgroup.integraciones.liferay.delivery.model.ContentField;
import cr.infosgroup.integraciones.liferay.delivery.model.ContentFieldValue;
import cr.infosgroup.integraciones.liferay.delivery.model.StructuredContent;
import cr.infosgroup.integraciones.liferay.exception.LiferayJsonApiException;
import cr.infosgroup.integraciones.liferay.model.SeasonUpdateRequest;

public class DeliveryWS extends BaseCtl  {

    private static final Logger log = LogManager.getLogger(DeliveryWS.class);
    
    private final Long seasonContentStructureId;
    
    private final DeliveryCtl deliveryCtl;
	
    public DeliveryWS(String urlRootLiferay, String userLiferay, String passwordLiferay, Long seasonContentStructureId) {
        super(urlRootLiferay, userLiferay, passwordLiferay);

        this.seasonContentStructureId = seasonContentStructureId;
        
        this.deliveryCtl = new DeliveryCtl(urlRootLiferay, userLiferay, passwordLiferay);

    }    
    
    /**
     * GET /structured-content-folders/{programId}/structured-contents
     * Obtiene todas las temporadas de un programa
     */
    public StructuredContent getSeasonStructuredContent(Long seasonId) throws LiferayJsonApiException {
        try {
            log.info("GET temporadas de programa ID: {}", seasonId);
            StructuredContent structuredContent = this.deliveryCtl.getStructuredContents(seasonId);
            
            
            log.info("Temporadas obtenidas exitosamente. Total: {}", structuredContent.getContentStructureId());
            return structuredContent;

        } 
        catch (LiferayJsonApiException ex) {
            log.error("Error httpStatusCode {}", ex.getStatusCode());
            throw ex;
        } catch (Exception e) {
            log.error("Error");
            throw new LiferayJsonApiException();
        }
    }
    
    /**
     * PUT /structured-contents/{seasonId}
     * Actualiza información básica de una temporada (título y descripción)
     */
    public StructuredContent updateSeasonStructuredContent(Long seasonId, SeasonUpdateRequest request) throws LiferayJsonApiException {
        try {
            log.info("PUT actualizar temporada ID: {}", seasonId);

            // Primero obtener la temporada actual para preservar episodios
            StructuredContent currentSeason = this.deliveryCtl.getStructuredContents(seasonId);
            
            StructuredContent  structuredContent = new StructuredContent();
            
            structuredContent.setContentStructureId(this.seasonContentStructureId);
            structuredContent.setTitle(request.getSeasonTitle());
            
            boolean firstField = true;
            List<ContentField> contentFields = new ArrayList<ContentField>();
            ContentField contentField;
            ContentFieldValue contentFieldValue;
            for (ContentField cf : currentSeason.getContentFields()) {
                if (cf.getName().equals("descripcionTemporada")) {
                	
                	contentField = new ContentField();
                	contentField.setName("descripcionTemporada");

                	contentFieldValue = new ContentFieldValue();
                	contentFieldValue.setData( request.getSeasonDescription() != null ? request.getSeasonDescription() : "" );
                	contentField.setContentFieldValue(contentFieldValue);
                }
                else if (cf.getName().equals("episodio")) {
                	contentField = appendExistingEpisodeToJson(cf);
                } 
                else {
                	contentField = appendBasicFieldToJson(cf);
                }
                contentFields.add(contentField);
            }
            structuredContent.setContentFields(contentFields);
            
            StructuredContent updated = this.deliveryCtl.putStructuredContents(structuredContent);
            
            return updated;
 /*           
            RadioProgram updatedSeason = this.convertToRadioProgram(updated);

            log.info("Temporada actualizada exitosamente con ID: {}", updatedSeason.getDescripcionContentId());
            return updatedSeason;
*/
        } 
        catch (LiferayJsonApiException ex) {
            log.error("Error httpStatusCode {}", ex.getStatusCode());
            throw ex;
        } catch (Exception e) {
            log.error("Error");
            throw new LiferayJsonApiException();
        }
    } 

    private ContentField appendExistingEpisodeToJson(ContentField field) {
    	
    	ContentField contentField = new ContentField();
    	contentField.setName("episodio");
    	
    	if (field.getContentFieldValue() != null && field.getContentFieldValue().getData() != null) {
        	ContentFieldValue contentFieldValue = new ContentFieldValue();
        	contentFieldValue.setData(field.getContentFieldValue().getData());
        	contentField.setContentFieldValue(contentFieldValue);
        } 
        
    	if (field.getNestedContentFields() != null) {
    		List<ContentField> nestedContentFields = new ArrayList<ContentField>();
    		ContentField nestedContentField; 
    		for (ContentField cf : field.getNestedContentFields()) {
    			nestedContentField = new ContentField();
    			nestedContentField.setName(cf.getName());
    	    	if (cf.getContentFieldValue() != null && cf.getContentFieldValue().getData() != null) {
    	        	ContentFieldValue contentFieldValue = new ContentFieldValue();
    	        	contentFieldValue.setData(cf.getContentFieldValue().getData());
    	        	contentField.setContentFieldValue(contentFieldValue);
    	        } 
    	    	nestedContentFields.add(nestedContentField);
            }
    		contentField.setNestedContentFields(nestedContentFields);
    	}
    	return contentField;
    }
    
    private ContentField appendBasicFieldToJson(ContentField field) {
    	ContentField contentField = new ContentField();
    	contentField.setName(field.getName());
    	if (field.getContentFieldValue() != null && field.getContentFieldValue().getData() != null) {
        	ContentFieldValue contentFieldValue = new ContentFieldValue();
        	contentFieldValue.setData(field.getContentFieldValue().getData());
        	contentField.setContentFieldValue(contentFieldValue);
        } 
    	return contentField;
    }
    

    

    
}

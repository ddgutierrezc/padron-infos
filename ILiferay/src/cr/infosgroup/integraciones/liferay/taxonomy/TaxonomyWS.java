package cr.infosgroup.integraciones.liferay.taxonomy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cr.infosgroup.integraciones.liferay.ctl.BaseCtl;
import cr.infosgroup.integraciones.liferay.taxonomy.ctl.TaxonomyCategoryCtl;
import cr.infosgroup.integraciones.liferay.taxonomy.ctl.TaxonomyVocabularyCtl;

public class TaxonomyWS extends BaseCtl {
	
    private static final Logger log = LogManager.getLogger(TaxonomyWS.class);	
    
    private final TaxonomyCategoryCtl taxonomyCategoryCtl;
    private final TaxonomyVocabularyCtl taxonomyVocabularyCtl;
    
    public TaxonomyWS(String urlRootLiferay, String userLiferay, String passwordLiferay) {
        super(urlRootLiferay, userLiferay, passwordLiferay);
        this.taxonomyCategoryCtl = new TaxonomyCategoryCtl(urlRootLiferay, userLiferay, passwordLiferay);
        this.taxonomyVocabularyCtl = new TaxonomyVocabularyCtl(urlRootLiferay, userLiferay, passwordLiferay);
    }  

}

package cr.infosgroup.padron.dal.finder;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BaseFinder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1362315420537271260L;

	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger(BaseFinder.class);	
	
    @PersistenceContext
    protected EntityManager entityManager;
	
}

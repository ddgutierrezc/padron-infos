package cr.infosgroup.padron.dal.finder;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cr.infosgroup.padron.dal.entity.DireccionElectoral;
import cr.infosgroup.padron.dto.DireccionElectoralDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;



@Component("finderDireccionElectoral")
@Transactional("transactionManager")
public class FinderDireccionElectoral extends BaseFinder implements Serializable {
		
	private static final long serialVersionUID = -2345041299908704545L;
	
	/**
	 * 
	 */
	private static final Logger log = LogManager.getLogger(FinderDireccionElectoral.class);
	
	
	/**
	 * 
	 * @param cedula
	 * @return
	 */
	public List<DireccionElectoral> findByFiltro(DireccionElectoralDTO filtro){
		try{
			CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();
			CriteriaQuery<DireccionElectoral> criteriaQuery = criteriaBuilder.createQuery(DireccionElectoral.class);
			Root<DireccionElectoral> root = criteriaQuery.from(DireccionElectoral.class);
			
			criteriaQuery.select(root)
				.where(criteriaBuilder.equal(root.get("codDirElectoral"), filtro.getCodDirElectoral()))
				.orderBy(criteriaBuilder.asc(root.get("codDirElectoral")));
			
			List<DireccionElectoral> resultado = super.entityManager.createQuery(criteriaQuery).getResultList();			
			return resultado;
		}
		catch(RuntimeException re){
			log.error(re);
			re.printStackTrace();
			throw re;
		}
	}	
	
	/**
	 * 
	 * @param provincia
	 * @return
	 */
	public List<DireccionElectoral> findByProvincia(String provincia){
		try{
			CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();
			CriteriaQuery<DireccionElectoral> criteriaQuery = criteriaBuilder.createQuery(DireccionElectoral.class);
			Root<DireccionElectoral> root = criteriaQuery.from(DireccionElectoral.class);
			
			criteriaQuery.select(root)
				.where(criteriaBuilder.equal(root.get("provincia"), provincia))
				.orderBy(criteriaBuilder.asc(root.get("provincia")));
			
			List<DireccionElectoral> result = super.entityManager.createQuery(criteriaQuery).getResultList();	
			return result;
		}catch(RuntimeException re){
			log.error(re);
			re.printStackTrace();
			throw re;
		}
	}
}

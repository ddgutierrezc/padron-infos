package cr.infosgroup.padron.dal.finder;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cr.infosgroup.padron.dal.entity.Ciudadano;
import cr.infosgroup.padron.dto.CiudadanoDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Component("finderCiudadano")
@Transactional("transactionManager")
public class FinderCiudadano extends BaseFinder implements Serializable {
	
	private static final long serialVersionUID = -7505962088503998075L;
	
	/**
	 * 
	 */
	private static final Logger log = LogManager.getLogger(FinderCiudadano.class);
	
	/**
	 * 
	 * @param cedula
	 * @return
	 */
	public List<Ciudadano> findByFiltro(CiudadanoDTO filtro){
		try{
			CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();
			CriteriaQuery<Ciudadano> criteriaQuery = criteriaBuilder.createQuery(Ciudadano.class);
			Root<Ciudadano> root = criteriaQuery.from(Ciudadano.class);
			
			criteriaQuery.select(root)
				.where(criteriaBuilder.equal(root.get("cedula"), filtro.getCedula()))
				.orderBy(criteriaBuilder.asc(root.get("cedula")));
			
			List<Ciudadano> resultado = super.entityManager.createQuery(criteriaQuery).getResultList();			
			return resultado;
		}
		catch(RuntimeException re){
			log.error(re);
			re.printStackTrace();
			throw re;
		}
	}
}

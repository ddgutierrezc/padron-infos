package cr.infosgroup.padron.dal.dao;

import org.springframework.stereotype.Repository;

import cr.infosgroup.padron.dal.BaseDAO;
import cr.infosgroup.padron.dal.entity.DireccionElectoral;

@Repository
public class DireccionElectoralDAO extends BaseDAO<DireccionElectoral, Integer> {

    public DireccionElectoralDAO() {
        super(DireccionElectoral.class);
    }
	
}

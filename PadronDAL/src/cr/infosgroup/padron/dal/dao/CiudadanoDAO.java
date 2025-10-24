package cr.infosgroup.padron.dal.dao;

import cr.infosgroup.padron.dal.BaseDAO;
import org.springframework.stereotype.Repository;
import cr.infosgroup.padron.dal.entity.Ciudadano;

@Repository
public class CiudadanoDAO extends BaseDAO<Ciudadano, Integer> {

    public CiudadanoDAO() {
        super(Ciudadano.class);
    }
    
}

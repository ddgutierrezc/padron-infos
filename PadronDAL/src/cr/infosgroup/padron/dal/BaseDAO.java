package cr.infosgroup.padron.dal;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BaseDAO<T, K> implements IBaseDAO<T, K> {
	
    @PersistenceContext
    protected EntityManager entityManager;
    
    private final Class<T> type;
    
    public BaseDAO(Class<T> type) {
        this.type = type;
    }

    @Override
    public void persist(T entity) {
        this.entityManager.persist(entity);
    }

    @Override
    public T insert(T entity) {
    	this.entityManager.persist(entity);
        return entity;
    }

    @Override
    public void merge(T entity) {
    	this.entityManager.merge(entity);
    }

    @Override
    public T update(T entity) {
        return this.entityManager.merge(entity);
    }    
    
    @Override
    public void remove(T entity) {
    	this.entityManager.remove(this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
    }
    
    @Override
    public T findById(K id) {
        return this.entityManager.find(type, id);
    }

    @Override
    public List<T> findAll() {
        return this.entityManager.createQuery("FROM " + type.getName(), type).getResultList();
    }
}

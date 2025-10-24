package cr.infosgroup.padron.dal;

import java.util.List;

public interface IBaseDAO<T,K>  {
	
    public void persist(T entity);

    public T insert(T entity);

    public void merge(T entity);

    public T update(T entity);
    
    public void remove(T entity);

    public T findById(K id);

    public List<T> findAll();
    
}

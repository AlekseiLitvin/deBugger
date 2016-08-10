package litvin.dao;

import java.util.List;

public interface GenericDao<T> {
    void saveEntity(T entity);
    T findById(int id);
    void updateEntity(T entity);
    List<T> getAllEntities(String source);
    Long getEntitiesNumber();
    List<T> getGroupOfEntities(String source, int first, int max);
}

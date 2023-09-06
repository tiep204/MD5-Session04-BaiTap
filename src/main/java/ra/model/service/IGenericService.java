package ra.model.service;

import java.util.List;

public interface IGenericService<T,E> {
    List<T> findAll();
    T save(T t);
    void delete(E id);
    T findById(E id);
}
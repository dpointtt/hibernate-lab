package ua.com.transitapp.dao;

import java.util.List;

public interface GeneralDao<T> {

    void save(T Item);
    void update(T Item);
    List<T> findAll();
    T findById(Long Id);
    void deleteAll();
    void deleteById(Long Id);
    void delete(T Item);

}

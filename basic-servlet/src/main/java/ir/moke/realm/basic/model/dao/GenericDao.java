package ir.moke.realm.basic.model.dao;

import java.util.List;

public interface GenericDao<T> {
    void insert(T t);

    void update(T t);

    void delete(long id);

    T select(long id);

    List<T> select();
}

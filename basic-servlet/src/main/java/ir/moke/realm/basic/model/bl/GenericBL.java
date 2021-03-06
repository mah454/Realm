package ir.moke.realm.basic.model.bl;

import java.util.List;

public interface GenericBL<T> {
    void register(T t);

    void modify(T t);

    void remove(long id);

    List<T> find();

    T find(long id);
}

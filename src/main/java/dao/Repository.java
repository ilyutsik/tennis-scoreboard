package dao;

import model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {

    E save(E entity);

    void update(E entity);

    void delete(E entity);

    Optional<E> findById(K id);

    List<E> findAll();

}

package dao;

import model.BaseEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {

    protected final Session session;

    private final Class<E> entityClass;

    public BaseRepository(Session session, Class<E> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public E save(E entity) {
        session.save(entity);
        return entity;
    }

    @Override
    public void update(E entity) {
        session.merge(entity);
    }

    @Override
    public void delete(E entity) {
        session.delete(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(session.find(entityClass, id));
    }

    @Override
    public List<E> findAll() {
        var criteria = session.getCriteriaBuilder().createQuery(entityClass);
        criteria.from(entityClass);
        return session.createQuery(criteria).getResultList();
    }

}

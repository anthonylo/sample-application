package com.tinkers.core.dao;

import com.tinkers.core.beans.Core;
import com.tinkers.core.dao.exceptions.EntityDoesNotExistException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public abstract class AbstractRepository<T extends Core<K>, K extends Serializable>
    implements IRepository<T, K> {

  @Autowired protected SessionFactory sessionFactory;

  protected Class<T> clazz;

  /** Set the Class for the sessionFactory. */
  protected abstract void setClazz();

  public AbstractRepository() {
    setClazz();
  }

  @Override
  public void saveEntity(final T entity) {
    sessionFactory.getCurrentSession().save(entity);
  }

  @Override
  public Set<T> retrieveById(final K id) {
    final List<T> listResults =
        sessionFactory.getCurrentSession().byMultipleIds(clazz).multiLoad(id);
    final Set<T> results = new LinkedHashSet<>(listResults);

    if (results.size() == 0) {
      throw new EntityDoesNotExistException(
          "The " + clazz.getSimpleName() + " with ID " + id.toString() + " does not exist.",
          id.getClass(),
          id);
    }

    return results;
  }

  @Override
  public T retrieveUniqueById(final K id) {
    final T result = sessionFactory.getCurrentSession().byId(clazz).load(id);

    if (result == null) {
      throw new EntityDoesNotExistException(
          "The " + clazz.getSimpleName() + " with ID " + id.toString() + " does not exist.",
          id.getClass(),
          id);
    }

    return result;
  }

  @Override
  public List<T> retrieveAll() {
    return sessionFactory.getCurrentSession().getCriteriaBuilder().createQuery(clazz).
  }

  @Override
  public List<T> retrieveSubsetOfEndpoint(final int startIdx, final int count) {
    return sessionFactory
        .getCurrentSession()
        .createCriteria(clazz)
        .setFirstResult(startIdx)
        .setMaxResults(count)
        .addOrder(Order.asc("id"))
        .list();
  }

  @Override
  public boolean doesEntityExistById(final K id) {
    final Long count =
        (Long)
            sessionFactory
                .getCurrentSession()
                .createCriteria(clazz)
                .add(Restrictions.eq("id", id))
                .setProjection(Projections.rowCount())
                .uniqueResult();

    return count > 0;
  }

  @Override
  public boolean tableEmpty() {
    return retrieveCount() == 0;
  }

  @Override
  public Long retrieveCount() {
    return (Long)
        sessionFactory
            .getCurrentSession()
            .createCriteria(clazz)
            .setProjection(Projections.rowCount())
            .uniqueResult();
  }

  @Override
  public void updateEntity(final T entity) {
    sessionFactory.getCurrentSession().merge(entity);
  }

  @Override
  public void deleteEntityById(final K id) {
    if (!doesEntityExistById(id)) {
      throw new EntityDoesNotExistException(
          "The " + clazz.getSimpleName() + " with ID " + id.toString() + " does not exist.",
          id.getClass(),
          id);
    }
    final T entity = retrieveUniqueById(id);
    sessionFactory.getCurrentSession().delete(entity);
  }

  public void setSessionFactory(final SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
}

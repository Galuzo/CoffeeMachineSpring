package by.nc.training.dev3.coffee.dao;

import by.nc.training.dev3.coffee.dao.interfaces.IDao;
import by.nc.training.dev3.coffee.entities.AbstractEntity;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Win on 04.05.2017.
 */
public abstract class AbstractDao <T extends AbstractEntity> implements IDao<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDao.class);

    protected SessionFactory sessionFactory;
    private Class persistentClass;

    protected AbstractDao(Class persistentClass,SessionFactory sessionFactory){
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
    }
    private String errorMessage="Error was thrown in DAO: ";



    public Serializable save(T entity) throws DaoException {
        Serializable id;
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(entity);
            id = session.getIdentifier(entity);
        }
        catch(HibernateException e) {
            LOGGER.error( errorMessage+ e);
            throw new DaoException(errorMessage,e);
        }
        return id;
    }

    public void update(T entity) throws DaoException {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            session.update(entity);

        } catch (Exception e) {
            LOGGER.error(errorMessage + e);
            throw new DaoException(errorMessage,e);
        }
    }

    public T getById(int entityId) throws DaoException {
        Session session;
        T entity ;
        try {
            session = sessionFactory.getCurrentSession();
            entity = (T)session.get(persistentClass, entityId);
        } catch (Exception e) {
            LOGGER.error(errorMessage + e);
            throw new DaoException(errorMessage,e);
        }
        return entity;
    }


    public List<T> getAll() throws DaoException {
        Session session;
        List entities;
        try {
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(persistentClass);
            criteria.from(persistentClass);
            entities = session.createQuery(criteria).getResultList();

        } catch (Exception e) {
            LOGGER.error(errorMessage + e);
            throw new DaoException(errorMessage,e);
        }
        return entities;
    }

    public void delete(T entity) throws DaoException {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            session.delete(entity);
        } catch (Exception e) {
            LOGGER.error(errorMessage + e);
            throw new DaoException(errorMessage,e);
        }
    }
}

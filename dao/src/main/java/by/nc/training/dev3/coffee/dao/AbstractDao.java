package by.nc.training.dev3.coffee.dao;

import by.nc.training.dev3.coffee.dao.interfaces.IDao;
import by.nc.training.dev3.coffee.entities.AbstractEntity;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Win on 04.05.2017.
 */
public abstract class AbstractDao <T extends AbstractEntity> implements IDao<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    @Autowired
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
    @Transactional
    public T getById(int entityId) throws DaoException {
        Session session;
        T entity ;
        try {
             session = sessionFactory.getCurrentSession();
            System.out.println(session);
            entity = (T)session.get(persistentClass, entityId);
        } catch (Exception e) {
            LOGGER.error(errorMessage + e);
            throw new DaoException(errorMessage,e);
        }
        System.out.println("success");
        return entity;
    }

    public List<T> getAll() throws DaoException {
        Session session;
        List entities;
        try {
            session = sessionFactory.getCurrentSession();
            entities = session.createCriteria(persistentClass).list();
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

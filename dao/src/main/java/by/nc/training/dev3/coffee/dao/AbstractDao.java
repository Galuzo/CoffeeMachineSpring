package by.nc.training.dev3.coffee.dao;

import by.nc.training.dev3.coffee.dao.interfaces.IDao;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win on 04.05.2017.
 */
public class AbstractDao <T > implements IDao<T> {
    private static Logger logger = Logger.getLogger(AbstractDao.class);
    private Class persistentClass;
    protected static HibernateUtil util = HibernateUtil.getInstance();
    protected AbstractDao(Class persistentClass){
        this.persistentClass = persistentClass;
    }
    private String errorMessage="Error was thrown in DAO: ";

    public Serializable save(T entity) throws DaoException {
        Serializable id;
        try {
            Session session = util.getSession();
            session.save(entity);
            id = session.getIdentifier(entity);
        }
        catch(HibernateException e) {
            logger.error( errorMessage+ e);
            throw new DaoException(errorMessage,e);
        }
        return id;
    }

    public void update(T entity) throws DaoException {
        Session session;
        try {
            session = util.getSession();
            session.update(entity);

        } catch (Exception e) {
            logger.error(errorMessage + e);
            throw new DaoException(errorMessage,e);
        }
    }

    public T getById(int entityId) throws DaoException {
        Session session;
        T entity ;
        try {
            session = util.getSession();
            entity = (T)session.get(persistentClass, entityId);
        } catch (Exception e) {
            logger.error(errorMessage + e);
            throw new DaoException(errorMessage,e);
        }
        return entity;
    }

    public List<T> getAll() throws DaoException {
        Session session;
        List entities;
        try {
            session = util.getSession();
            entities = session.createCriteria(persistentClass).list();
        } catch (Exception e) {
            logger.error(errorMessage + e);
            throw new DaoException(errorMessage,e);
        }
        return entities;
    }

    public void delete(T entity) throws DaoException {
        Session session;
        try {
            session = util.getSession();
            session.delete(entity);
        } catch (Exception e) {
            logger.error(errorMessage + e);
            throw new DaoException(errorMessage,e);
        }
    }
}

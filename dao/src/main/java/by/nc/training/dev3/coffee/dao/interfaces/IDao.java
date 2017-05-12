package by.nc.training.dev3.coffee.dao.interfaces;

import by.nc.training.dev3.coffee.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Win on 04.05.2017.
 */
public interface IDao<T> {
    Serializable save(T entity) throws DaoException;
     void update(T entity) throws DaoException;
     T getById(int entityId) throws DaoException;
     List<T> getAll() throws DaoException;
     void delete(T entity) throws DaoException;
}

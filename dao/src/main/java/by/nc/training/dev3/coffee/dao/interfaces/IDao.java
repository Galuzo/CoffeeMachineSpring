package by.nc.training.dev3.coffee.dao.interfaces;

import by.nc.training.dev3.coffee.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Win on 04.05.2017.
 */
public interface IDao<T> {
    public Serializable save(T entity) throws DaoException;
    public void update(T entity) throws DaoException;
    public T getById(int entityId) throws DaoException;
    public List<T> getAll() throws DaoException;
    public void delete(T entity) throws DaoException;
}

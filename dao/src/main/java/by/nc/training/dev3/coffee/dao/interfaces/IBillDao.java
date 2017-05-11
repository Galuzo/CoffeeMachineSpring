package by.nc.training.dev3.coffee.dao.interfaces;

import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.exceptions.DaoException;

/**
 * Created by Win on 05.05.2017.
 */
public interface IBillDao extends IDao<Bill> {
    public Bill getByUser(User user) throws DaoException;
}

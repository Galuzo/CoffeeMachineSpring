package by.nc.training.dev3.coffee.dao.interfaces;

import by.nc.training.dev3.coffee.entities.Account;
import by.nc.training.dev3.coffee.exceptions.DaoException;

/**
 * Created by Win on 05.05.2017.
 */
public interface IUserDao extends IDao<Account> {
    public Account getByLogin(String login) throws DaoException;
}

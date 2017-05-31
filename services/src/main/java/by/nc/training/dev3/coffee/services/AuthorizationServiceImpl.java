package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.interfaces.IUserDao;
import by.nc.training.dev3.coffee.entities.Account;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Win on 31.05.2017.
 */
@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    IUserDao userDao;

    @Override
    public Account getByLogin(String login) throws ServiceException {
        Account account;
        try {
            account=userDao.getByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return account;

    }
}

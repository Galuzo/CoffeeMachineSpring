package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.interfaces.IBillDao;
import by.nc.training.dev3.coffee.dao.interfaces.IRoleDao;
import by.nc.training.dev3.coffee.dao.interfaces.IUserDao;
import by.nc.training.dev3.coffee.dto.UserForRegisterDto;
import by.nc.training.dev3.coffee.entities.Account;
import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.entities.Role;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.RegisterException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Created by Win on 31.05.2017.
 */
@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);
    private String message;

    @Autowired
    IUserDao userDao;

    @Autowired
    IRoleDao roleDao;

    @Autowired
    IBillDao billDao;

    @Override
    public Account getByLogin(String login) throws ServiceException {
        Account account;
        try {
            account=userDao.getByLogin(login);
        } catch (DaoException e) {
            LOGGER.error(e.toString());
            throw new ServiceException(e);
        }
        return account;

    }

    public void registration(UserForRegisterDto userForRegisterDto)  throws RegisterException{

        try {
            if (checkIsNewUser(userForRegisterDto.getLogin())) {
                if (userForRegisterDto.getPassword().equals( userForRegisterDto.getRepeatedPassword())) {
                    try {
                        Role role = roleDao.getById(2);
                        Account account = new Account();
                        Bill bill = new Bill();
                        account.setLogin(userForRegisterDto.getLogin());
                        account.setPassword(userForRegisterDto.getPassword());
                        account.setRole(role);
                        bill.setUser(account);
                        userDao.save(account);
                        billDao.save(bill);
                    } catch (DaoException e) {
                        LOGGER.error(e.toString());
                        throw new RegisterException(e);
                    }
                }
                else
                {
                    message="passwords don't match";
                    LOGGER.error(message);
                    throw new RegisterException(message);
                }
            }
            else
            {
                throw new RegisterException(message);
            }
        } catch (ServiceException e) {
            LOGGER.error(e.toString());
            throw new RegisterException(e);        }

    }
    private boolean checkIsNewUser(String login) throws ServiceException {
        boolean isNew = false;
        try {
            Account user = userDao.getByLogin(login);
            if(user == null){
                isNew = true;
            }
        }
        catch (DaoException e) {
            LOGGER.error(e.toString());
            throw new ServiceException( e);
        }
        return isNew;
    }
}

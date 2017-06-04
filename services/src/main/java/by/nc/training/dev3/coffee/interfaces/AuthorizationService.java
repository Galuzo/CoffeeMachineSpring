package by.nc.training.dev3.coffee.interfaces;

import by.nc.training.dev3.coffee.dto.UserForRegisterDto;
import by.nc.training.dev3.coffee.entities.Account;
import by.nc.training.dev3.coffee.exceptions.ServiceException;

/**
 * Created by Win on 31.05.2017.
 */
public interface AuthorizationService {
    Account getByLogin(String login) throws ServiceException;
     void registration(UserForRegisterDto userForRegisterDto)  throws ServiceException;
}

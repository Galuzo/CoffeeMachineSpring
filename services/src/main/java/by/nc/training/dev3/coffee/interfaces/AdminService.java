package by.nc.training.dev3.coffee.interfaces;

import by.nc.training.dev3.coffee.enums.ContentType;
import by.nc.training.dev3.coffee.exceptions.ServiceException;

/**
 * Created by Win on 11.05.2017.
 */
public interface AdminService {
    int addExistContentInMachine(ContentType contentType, int id, int count) throws ServiceException;

    int addNewContentInMachine(ContentType contentType, String title, double cost, int count) throws ServiceException;

    boolean removeContentFromMachine(ContentType contentType, int id) throws ServiceException;
}

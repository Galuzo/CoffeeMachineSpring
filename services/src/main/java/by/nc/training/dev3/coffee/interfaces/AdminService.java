package by.nc.training.dev3.coffee.interfaces;

import by.nc.training.dev3.coffee.dto.ContentForIncDto;
import by.nc.training.dev3.coffee.entities.Content;
import by.nc.training.dev3.coffee.enums.ContentType;
import by.nc.training.dev3.coffee.exceptions.ServiceException;

/**
 * Created by Win on 11.05.2017.
 */
public interface AdminService {
    int addExistContentInMachine(ContentType contentType, ContentForIncDto contentForIncDto) throws ServiceException;

    int addNewContentInMachine(ContentType contentType, Content content) throws ServiceException;

    void removeContentFromMachine(ContentType contentType, int id) throws ServiceException;
}

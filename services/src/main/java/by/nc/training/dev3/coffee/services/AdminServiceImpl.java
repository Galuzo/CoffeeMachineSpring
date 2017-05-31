package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.dao.interfaces.IDao;
import by.nc.training.dev3.coffee.dao.interfaces.IIngredientDao;
import by.nc.training.dev3.coffee.entities.Content;
import by.nc.training.dev3.coffee.enums.ContentType;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.AdminService;
import by.nc.training.dev3.coffee.utils.Tools;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Win on 06.05.2017.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
    private static  String message;

    @Autowired
    private IBeverageDao beverageDao;

    @Autowired
    private IIngredientDao ingredientDao;




    public int addExistContentInMachine(ContentType contentType, int id, int count) throws ServiceException
    {
        int newValue;
        IDao dao = defineContentDao(contentType);
        Content content;

        try {
            content=(Content)dao.getById(id);
            content.setCount(Tools.incrementValue(content.getCount(),count));
            newValue = content.getCount();
            dao.update(content);
        } catch (DaoException e) {
            message="Transaction is failed(addExistContent)";
            LOGGER.error(message+e);
            throw new ServiceException(message,e);
        }
        return newValue;
    }

    public int addNewContentInMachine(ContentType contentType, Content content) throws ServiceException {
        int id;
        IDao dao =defineContentDao(contentType);
        try {
            id=(Integer)dao.save(content);
        } catch (DaoException e) {
            message="Transaction is failed(addNewContent)";
            LOGGER.error(message+e);
            throw new ServiceException(message,e);
        }
        return id;
    }

    public void removeContentFromMachine(ContentType contentType, int id) throws ServiceException{
        IDao dao = defineContentDao(contentType);
        try {
            Content beverage = (Content) dao.getById(id);
            dao.delete(beverage);
        } catch (DaoException e) {
            message="Transaction is failed(remove content)";
            LOGGER.error(message+e);
            throw new ServiceException(message,e);
        }

    }
    private IDao defineContentDao(ContentType contentType) {
        switch (contentType) {
            case BEVERAGE:
                return beverageDao;
            case INGREDIENT:
                return  ingredientDao;
        }
        return null;
    }

}

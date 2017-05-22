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
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Win on 06.05.2017.
 */
@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);
    private static  String message;

    @Autowired
    private IBeverageDao beverageDao;
    @Autowired
    private IIngredientDao ingredientDao;

    @Autowired
    private SessionFactory sessionFactory;



    public int addExistContentInMachine(ContentType contentType, int id, int count) throws ServiceException
    {
        int newValue;
        IDao dao = defineContentDao(contentType);
        Content content;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction=session.beginTransaction();
        try {
            content=(Content)dao.getById(id);
            content.setCount(Tools.incrementValue(content.getCount(),count));
            newValue = content.getCount();
            dao.update(content);
            transaction.commit();
        } catch (DaoException e) {
            message="Transaction is failed(addExistContent)";
            LOGGER.error(message+e);
            transaction.rollback();
            throw new ServiceException(message,e);
        }
        return newValue;
    }

    public int addNewContentInMachine(ContentType contentType, String title, double cost, int count) throws ServiceException {
        int id;
        IDao dao =defineContentDao(contentType);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Content content =Tools.defineContent(contentType);
            content.setTitle(title);
            content.setCost(cost);
            content.setCount(count);
            id=(Integer)dao.save(content);
            transaction.commit();
        } catch (DaoException e) {
            message="Transaction is failed(addNewContent)";
            LOGGER.error(message+e);
            transaction.rollback();
            throw new ServiceException(message,e);
        }
        return id;
    }

    public boolean removeContentFromMachine(ContentType contentType, int id) throws ServiceException{
        boolean isSuccess;
        IDao dao = defineContentDao(contentType);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Content beverage = (Content) dao.getById(id);
            dao.delete(beverage);
            transaction.commit();
            isSuccess=true;
        } catch (DaoException e) {
            message="Transaction is failed(remove content)";
            LOGGER.error(message+e);
            transaction.rollback();
            throw new ServiceException(message,e);
        }
        return isSuccess;

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

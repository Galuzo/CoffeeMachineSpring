package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.impl.BeverageDaoImpl;
import by.nc.training.dev3.coffee.dao.impl.IngredientDaoImpl;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Content;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.enums.ContentType;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.AdminService;
import by.nc.training.dev3.coffee.utils.HibernateUtil;
import by.nc.training.dev3.coffee.utils.Tools;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

/**
 * Created by Win on 06.05.2017.
 */
public class AdminServiceImpl implements AdminService {
    private static AdminService instance;
    private static Logger logger = Logger.getLogger(AdminServiceImpl.class);
    private static  String message;


    private AdminServiceImpl(){}
    public static synchronized AdminService getInstance(){
        if(instance == null){
            instance = new AdminServiceImpl();
        }
        return instance;
    }

    public int addExistContentInMachine(ContentType contentType, int id, int count) throws ServiceException
    {
        int newValue;
        AbstractDao dao = Tools.defineContentDao(contentType);
        Content content;
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        try {
            content=(Content)dao.getById(id);
            content.setCount(Tools.incrementValue(content.getCount(),count));
            newValue = content.getCount();
            dao.update(content);
            transaction.commit();
        } catch (DaoException e) {
            message="Transaction is failed(addExistContent)";
            logger.error(message+e);
            transaction.rollback();
            throw new ServiceException(message,e);
        }
        return newValue;
    }

    public int addNewContentInMachine(ContentType contentType, String title, double cost, int count) throws ServiceException {
        int id;
        AbstractDao dao = Tools.defineContentDao(contentType);
        Session session = HibernateUtil.getInstance().getSession();
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
            logger.error(message+e);
            transaction.rollback();
            throw new ServiceException(message,e);
        }
        return id;
    }

    public boolean removeContentFromMachine(ContentType contentType, int id) throws ServiceException{
        boolean isSuccess;
        AbstractDao dao = Tools.defineContentDao(contentType);
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Content beverage = (Content) dao.getById(id);
            dao.delete(beverage);
            transaction.commit();
            isSuccess=true;
        } catch (DaoException e) {
            message="Transaction is failed(remove content)";
            logger.error(message+e);
            transaction.rollback();
            throw new ServiceException(message,e);
        }
        return isSuccess;

    }
}

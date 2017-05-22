package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.BeverageDaoImpl;
import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.dao.interfaces.IBillDao;
import by.nc.training.dev3.coffee.dao.interfaces.IIngredientDao;
import by.nc.training.dev3.coffee.dao.interfaces.IOrderDao;
import by.nc.training.dev3.coffee.entities.*;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.ClientService;
import by.nc.training.dev3.coffee.utils.Tools;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Win on 07.05.2017.
 */
@Service
public class ClientServiceImpl implements ClientService {
    private static Logger logger = Logger.getLogger(ClientServiceImpl.class);
    private static  String message;

    @Autowired
    private IBillDao billDao;
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IBeverageDao beverageDao;

    @Autowired
    private IIngredientDao ingredientDao;

    @Autowired
    private SessionFactory sessionFactory;

    private ClientServiceImpl(){}


    public int addBeverageInBill(User user, int idBeverage) throws ServiceException{
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Bill bill;
        Order order;
        int returnId;
        Beverage beverageInMachine;
        try {
            beverageInMachine = beverageDao.getById(idBeverage);
            if (beverageInMachine.getCount() > 0) {
                bill = billDao.getByUser(user);
                beverageInMachine.setCount(Tools.decrementValue(beverageInMachine.getCount(), 1));
                order = new Order();
                order.setBill(bill);
                order.setBeverage(beverageInMachine);
                beverageDao.update(beverageInMachine);
                returnId=(Integer)orderDao.save(order);
                transaction.commit();
            } else {
                transaction.rollback();
                message="The beverage count is less then 0";
                throw new ServiceException(message);
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return returnId;
    }

    public void addIngredient(User user, int idOrder, int idIngredient) throws ServiceException {
        Session session = null;
        Transaction transaction = session.beginTransaction();
        try {
            Ingredient ingredient=ingredientDao.getById(idIngredient);
            Order order=orderDao.getById(idOrder);
            if(ingredient.getCount()>0)
            {
                if(order.getBill().getId()==user.getBill().getId()) {
                    ingredient.setCount(Tools.decrementValue(ingredient.getCount(), 1));
                    order.getIngredientSet().add(ingredient);
                    ingredientDao.update(ingredient);
                    orderDao.update(order);
                    transaction.commit();
                }
                else
                {
                    message="The bill doesn`t match";
                    throw new ServiceException(message);
                }
            }
            else
            {
                message="The ingredient count is less then 0";
                throw new ServiceException(message);
            }
        } catch (DaoException e) {
            logger.error(e);
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    public void removeBeverageFromBill(User user, int idOrder) throws ServiceException
    {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Order order=orderDao.getById(idOrder);
            if (user.getBill().getId() == order.getBill().getId()) {
                Set<Ingredient> ingredientSet=order.getIngredientSet();
                for (Ingredient ingredient : ingredientSet) {
                    ingredient.setCount(Tools.incrementValue(ingredient.getCount(),1));
                    ingredientSet.remove(ingredient);
                    ingredientDao.update(ingredient);
                }
                Beverage beverage = order.getBeverage();
                beverage.setCount(Tools.incrementValue(beverage.getCount(),1));
                beverageDao.update(beverage);
                orderDao.delete(order);
                transaction.commit();
            }
            else {
                message="The bill doesn`t match";
                throw new ServiceException(message);
            }
        } catch (DaoException e) {
            logger.error(e);
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    public void removeIngredient(User user, int idOrder, int idIngredient) throws ServiceException
    {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction=session.beginTransaction();
        try {
            Order order = orderDao.getById(idOrder);
            Ingredient deletedIngredient = ingredientDao.getById(idIngredient);
            if (order.getBill().getId() == user.getBill().getId()) {
                Set<Ingredient> ingredientSet=order.getIngredientSet();
                for (Ingredient ingredient : ingredientSet) {
                    if(ingredient==deletedIngredient)
                    {
                        ingredient.setCount(Tools.incrementValue(ingredient.getCount(),1));
                        ingredientSet.remove(ingredient);
                        ingredientDao.update(ingredient);
                    }
                }
                orderDao.update(order);
                transaction.commit();
            }
            else {
                message="The bill doesn`t match";
                throw new ServiceException(message);
            }
        } catch (DaoException e) {
            logger.error(e);
            transaction.rollback();
            throw new ServiceException(e);
        }

    }

    public void  payBill(User user) throws ServiceException {
        Transaction transaction = sessionFactory.openSession().beginTransaction();
        try {
            Bill bill=billDao.getByUser(user);
            List<Order> orders=orderDao.getByBill(bill);
            for (Order order : orders) {
               orderDao.delete(order);
            }
            transaction.commit();
        } catch (DaoException e) {
            logger.error(e);
            transaction.rollback();
            throw new ServiceException(e);
        }
    }
}

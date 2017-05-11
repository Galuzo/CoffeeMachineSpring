package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.BeverageDaoImpl;
import by.nc.training.dev3.coffee.dao.impl.BillDaoImpl;
import by.nc.training.dev3.coffee.dao.impl.IngredientDaoImpl;
import by.nc.training.dev3.coffee.dao.impl.OrderDaoImpl;
import by.nc.training.dev3.coffee.entities.*;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.utils.HibernateUtil;
import by.nc.training.dev3.coffee.utils.Tools;
import net.sf.ehcache.search.expression.Or;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

/**
 * Created by Win on 07.05.2017.
 */
public class ClientService{
    private static ClientService instance;
    private static Logger logger = Logger.getLogger(ClientService.class);
    private static  String message;

    private ClientService(){}
    public static synchronized ClientService getInstance(){
        if(instance == null){
            instance = new ClientService();
        }
        return instance;
    }

    public int addBeverageInBill(User user, int idBeverage) throws ServiceException{
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        BeverageDaoImpl beverageDao = BeverageDaoImpl.getInstance();
        BillDaoImpl billDao = BillDaoImpl.getInstance();
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        Bill bill = null;
        Order order=null;
        int returnId;
        Beverage beverageInMachine=null;
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
                throw new ServiceException("The beverage count is less then 0");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return returnId;
    }

    public void addIngredient(User user,int idOrder, int idIngredient) throws ServiceException {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        IngredientDaoImpl ingredientDao = IngredientDaoImpl.getInstance();
        Session session = HibernateUtil.getInstance().getSession();
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
                    throw new ServiceException("The bill doesn`t match");
                }
            }
            else
            {
                throw new ServiceException("The ingredient count is less then 0");
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);

        }
    }

    public void removeBeverageFromBill(User user,int idOrder) throws ServiceException
    {
        IngredientDaoImpl ingredientDao = IngredientDaoImpl.getInstance();
        BeverageDaoImpl beverageDao = BeverageDaoImpl.getInstance();
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        Session session = HibernateUtil.getInstance().getSession();
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
                throw new ServiceException("The bill doesn`t match");
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    public void removeIngredient(User user,int idOrder,int idIngredient) throws ServiceException
    {
        Session session = HibernateUtil.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        IngredientDaoImpl ingredientDao = IngredientDaoImpl.getInstance();
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
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
                throw new ServiceException("The bill doesn`t match");
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }

    }

    public void  payBill(User user) throws ServiceException {
        Transaction transaction = HibernateUtil.getInstance().getSession().beginTransaction();
        BillDaoImpl billDao = BillDaoImpl.getInstance();
        try {
            Bill bill=billDao.getByUser(user);
            OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
            List<Order> orders=orderDao.getByBill(bill);
            for (Order order : orders) {
               orderDao.delete(order);
            }
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }
}

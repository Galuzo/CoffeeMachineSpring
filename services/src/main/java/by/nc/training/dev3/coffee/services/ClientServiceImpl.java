package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.interfaces.*;
import by.nc.training.dev3.coffee.dto.OrderDto;
import by.nc.training.dev3.coffee.dto.IngredientInOrderDto;
import by.nc.training.dev3.coffee.entities.*;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.ClientService;
import by.nc.training.dev3.coffee.utils.Tools;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Win on 07.05.2017.
 */
@Service
@Transactional
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
    private IUserDao userDao;

    @Autowired
    private IIngredientDao ingredientDao;



    private ClientServiceImpl(){}


    public int addBeverageInBill(OrderDto orderDto) throws ServiceException{
        Bill bill;
        Order order;
        int returnId;
        Beverage beverageInMachine;
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Account account = userDao.getByLogin(user.getUsername());
            beverageInMachine = beverageDao.getById(orderDto.getBeverageId());
            if (beverageInMachine.getCount() > 0) {
                bill = billDao.getByUser(account);
                beverageInMachine.setCount(Tools.decrementValue(beverageInMachine.getCount(), 1));
                order = new Order();
                order.setBill(bill);
                order.setBeverage(beverageInMachine);
                beverageDao.update(beverageInMachine);
                returnId=(Integer)orderDao.save(order);
            } else {
                message="The beverage count is less then 0";
                throw new ServiceException(message);
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return returnId;
    }

    public void addIngredient(IngredientInOrderDto ingredientInOrderDto) throws ServiceException {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Account account = userDao.getByLogin(user.getUsername());
            Ingredient ingredient=ingredientDao.getById(ingredientInOrderDto.getIngredientId());
            Order order=orderDao.getById(ingredientInOrderDto.getOrderId());
            if(ingredient.getCount()>0)
            {
                if(order.getBill().getId()==account.getBill().getId()) {
                    ingredient.setCount(Tools.decrementValue(ingredient.getCount(), 1));
                    order.getIngredientSet().add(ingredient);
                    ingredientDao.update(ingredient);
                    orderDao.update(order);
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
            throw new ServiceException(e);
        }
    }

    public void removeBeverageFromBill( int idOrder) throws ServiceException
    {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Account account = userDao.getByLogin(user.getUsername());
            Order order=orderDao.getById(idOrder);
            if (account.getBill().getId() == order.getBill().getId()) {
                Set<Ingredient> ingredientSet=order.getIngredientSet();
                for (Ingredient ingredient : ingredientSet) {
                    ingredient.setCount(Tools.incrementValue(ingredient.getCount(),1));
                    ingredientDao.update(ingredient);
                }
                ingredientSet.clear();
                Beverage beverage = order.getBeverage();
                beverage.setCount(Tools.incrementValue(beverage.getCount(),1));
                beverageDao.update(beverage);
                orderDao.delete(order);
            }
            else {
                message="The bill doesn`t match";
                throw new ServiceException(message);
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public void removeIngredient( int idOrder, int idIngredient) throws ServiceException
    {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Account account = userDao.getByLogin(user.getUsername());
            Order order = orderDao.getById(idOrder);
            Ingredient deletedIngredient = ingredientDao.getById(idIngredient);
            if (order.getBill().getId() == account.getBill().getId()) {
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
            }
            else {
                message="The bill doesn`t match";
                throw new ServiceException(message);
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

    }

    public void  payBill(Account user) throws ServiceException {
        try {
            Bill bill=billDao.getByUser(user);
            List<Order> orders=orderDao.getByBill(bill);
            for (Order order : orders) {
               orderDao.delete(order);
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}

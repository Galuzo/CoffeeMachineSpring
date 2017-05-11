package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.BillDaoImpl;
import by.nc.training.dev3.coffee.dao.impl.OrderDaoImpl;
import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.entities.Order;
import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Win on 09.05.2017.
 */
public class BillService {
    private static BillService instance;
    private static Logger logger = Logger.getLogger(ClientService.class);
    private static  String message;

    private BillService(){}
    public static synchronized BillService getInstance(){
        if(instance == null){
            instance = new BillService();
        }
        return instance;
    }

    public void showBill(User user) throws ServiceException
    {
        BillDaoImpl billDao = BillDaoImpl.getInstance();
        try {
            Bill bill=billDao.getById(user.getBill().getId());
            OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
            List<Order> list=orderDao.getByBill(bill);
            for (Order order : list) {
                System.out.println(order.getBeverage());
                for (Ingredient ingredient : order.getIngredientSet()) {
                    System.out.println(ingredient);
                }
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

    }
}

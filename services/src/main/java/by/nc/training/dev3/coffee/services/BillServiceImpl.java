package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.BillDaoImpl;
import by.nc.training.dev3.coffee.dao.impl.OrderDaoImpl;
import by.nc.training.dev3.coffee.dao.interfaces.IBillDao;
import by.nc.training.dev3.coffee.dao.interfaces.IOrderDao;
import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.entities.Order;
import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.BillService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Win on 09.05.2017.
 */
public class BillServiceImpl implements BillService {
    private static BillService instance;
    private static Logger logger = Logger.getLogger(ClientServiceImpl.class);
    private static  String message;

    private BillServiceImpl(){}
    public static synchronized BillService getInstance(){
        if(instance == null){
            instance = new BillServiceImpl();
        }
        return instance;
    }

    public void showBill(User user) throws ServiceException
    {
        IBillDao billDao = BillDaoImpl.getInstance();
        try {
            Bill bill=billDao.getById(user.getBill().getId());
            IOrderDao orderDao = OrderDaoImpl.getInstance();
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

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Win on 09.05.2017.
 */
@Service
public class BillServiceImpl implements BillService {
    private static Logger logger = Logger.getLogger(ClientServiceImpl.class);
    private static  String message;

    @Autowired
    private IBillDao billDao;
    @Autowired
    private IOrderDao orderDao;

    private BillServiceImpl(){}

    public void showBill(User user) throws ServiceException
    {
        try {
            Bill bill=billDao.getById(user.getBill().getId());
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

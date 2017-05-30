package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.interfaces.IBillDao;
import by.nc.training.dev3.coffee.dao.interfaces.IOrderDao;
import by.nc.training.dev3.coffee.dao.interfaces.IUserDao;
import by.nc.training.dev3.coffee.dto.ContentDto;
import by.nc.training.dev3.coffee.dto.DetailOrderDto;
import by.nc.training.dev3.coffee.entities.*;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.BillService;
import by.nc.training.dev3.coffee.utils.DtoBuiler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Win on 09.05.2017.
 */
@Service
@Transactional
public class BillServiceImpl implements BillService {
    private static Logger logger = Logger.getLogger(ClientServiceImpl.class);
    private static  String message;

    @Autowired
    private IBillDao billDao;
    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IUserDao userDao;

    private BillServiceImpl(){}

    public List<ContentDto> showBeveragesInBill(int userId) throws ServiceException
    {
        List<ContentDto> beverages = new ArrayList<ContentDto>();
        List<Order> list = getOrders(userId);
        ContentDto contentDto;
        for(Order order:list)
        {
            contentDto = DtoBuiler.contentDtoBuilder(order.getBeverage());
            beverages.add(contentDto);
        }
        return beverages;
    }

    @Override
    public Set<ContentDto> showIngredientsInBeverage(int orderId) throws ServiceException {
        Set<ContentDto> ingredients = new HashSet<ContentDto>();
        ContentDto contentDto;
        try {
            Order order = orderDao.getById(orderId);
            for(Ingredient ingredient:order.getIngredientSet())
            {
                contentDto = DtoBuiler.contentDtoBuilder(ingredient);
                ingredients.add(contentDto);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return ingredients;
    }

    private List<Order> getOrders(int userId) {
        List<Order> orders=null;
        try {
            User user = userDao.getById(userId);
            Bill bill=billDao.getById(user.getBill().getId());
            orders= orderDao.getByBill(bill);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<DetailOrderDto> showOrders(int userId) {
        List<DetailOrderDto> orders = new ArrayList<DetailOrderDto>();
        DetailOrderDto detailOrderDto;
        for (Order order : getOrders(userId)) {
            detailOrderDto = DtoBuiler.detailOrderDtoBuilder(order);
            orders.add(detailOrderDto);
        }
        return orders;
    }

}

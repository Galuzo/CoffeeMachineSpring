package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.interfaces.IBillDao;
import by.nc.training.dev3.coffee.dao.interfaces.IOrderDao;
import by.nc.training.dev3.coffee.dao.interfaces.IUserDao;
import by.nc.training.dev3.coffee.dto.BillDto;
import by.nc.training.dev3.coffee.dto.ContentDto;
import by.nc.training.dev3.coffee.dto.DetailOrderDto;
import by.nc.training.dev3.coffee.entities.*;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.BillService;
import by.nc.training.dev3.coffee.utils.DtoBuiler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Win on 09.05.2017.
 */
@Service
@Transactional
public class BillServiceImpl implements BillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);
    private static  String message;

    @Autowired
    private IBillDao billDao;
    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IUserDao userDao;

    private BillServiceImpl(){}

    public List<ContentDto> showBeveragesInBill() throws ServiceException
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ContentDto> beverages = new ArrayList<ContentDto>();
        try {
            Account account = userDao.getByLogin(user.getUsername());
            List<Order> list = getOrders(account.getId());
            ContentDto contentDto;
            for(Order order:list)
            {
                contentDto = DtoBuiler.contentDtoBuilder(order.getBeverage());
                beverages.add(contentDto);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
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
            Account user = userDao.getById(userId);
            Bill bill=billDao.getById(user.getBill().getId());
            orders= orderDao.getByBill(bill);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<DetailOrderDto> showOrders() throws ServiceException {
        List<DetailOrderDto> orders = new ArrayList<DetailOrderDto>();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Account account = userDao.getByLogin(user.getUsername());
            DetailOrderDto detailOrderDto;
            for (Order order : getOrders(account.getId())) {
                detailOrderDto = DtoBuiler.detailOrderDtoBuilder(order);
                orders.add(detailOrderDto);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    public BillDto showGeneralCost() throws ServiceException
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Account account = userDao.getByLogin(user.getUsername());
            Bill bill = billDao.getByUser(account);
            BillDto billDto = new BillDto();
            billDto.setDate(bill.getDate());
            billDto.setGeneralCost(bill.getGeneralCost());
            return billDto;

        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

}

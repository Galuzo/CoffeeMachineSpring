package by.nc.training.dev3.coffee.dao.interfaces;

import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.entities.Order;
import by.nc.training.dev3.coffee.exceptions.DaoException;

import java.util.List;

/**
 * Created by Win on 07.05.2017.
 */
public interface IOrderDao extends IDao<Order> {
    List<Order> getByBill(Bill bill)throws DaoException;
}

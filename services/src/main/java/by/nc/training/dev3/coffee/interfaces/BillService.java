package by.nc.training.dev3.coffee.interfaces;

import by.nc.training.dev3.coffee.dto.BillDto;
import by.nc.training.dev3.coffee.dto.ContentDto;
import by.nc.training.dev3.coffee.dto.DetailOrderDto;
import by.nc.training.dev3.coffee.entities.*;
import by.nc.training.dev3.coffee.exceptions.ServiceException;

import java.util.List;
import java.util.Set;

/**
 * Created by Win on 11.05.2017.
 */
public interface BillService {
    List<ContentDto> showBeveragesInBill() throws ServiceException;
    Set<ContentDto> showIngredientsInBeverage(int orderId) throws ServiceException;
    List<DetailOrderDto> showOrders() throws ServiceException;
    BillDto showGeneralCost() throws ServiceException;

}

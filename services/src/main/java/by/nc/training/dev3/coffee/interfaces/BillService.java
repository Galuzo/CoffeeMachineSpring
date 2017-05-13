package by.nc.training.dev3.coffee.interfaces;

import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.exceptions.ServiceException;

/**
 * Created by Win on 11.05.2017.
 */
public interface BillService {
    void showBill(User user) throws ServiceException;
}

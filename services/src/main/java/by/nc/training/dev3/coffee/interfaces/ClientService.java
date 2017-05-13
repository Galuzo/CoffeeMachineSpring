package by.nc.training.dev3.coffee.interfaces;

import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.exceptions.ServiceException;

/**
 * Created by Win on 11.05.2017.
 */
public interface ClientService {
    int addBeverageInBill(User user, int idBeverage) throws ServiceException;

    void addIngredient(User user, int idOrder, int idIngredient) throws ServiceException;

    void removeBeverageFromBill(User user, int idOrder) throws ServiceException;

    void removeIngredient(User user, int idOrder, int idIngredient) throws ServiceException;

    void  payBill(User user) throws ServiceException;
}

package by.nc.training.dev3.coffee.interfaces;

import by.nc.training.dev3.coffee.dto.IngredientForRemoveFromBeverageDto;
import by.nc.training.dev3.coffee.dto.OrderDto;
import by.nc.training.dev3.coffee.dto.IngredientInOrderDto;
import by.nc.training.dev3.coffee.entities.Account;
import by.nc.training.dev3.coffee.exceptions.ServiceException;

/**
 * Created by Win on 11.05.2017.
 */
public interface ClientService {
    int addBeverageInBill(OrderDto orderDto) throws ServiceException;

    void addIngredient(IngredientInOrderDto ingredientInOrderDto) throws ServiceException;

    void removeBeverageFromBill(int idOrder) throws ServiceException;

    void removeIngredient(IngredientForRemoveFromBeverageDto ingredientForRemoveFromBeverageDto) throws ServiceException;

    void  payBill(Account user) throws ServiceException;
}

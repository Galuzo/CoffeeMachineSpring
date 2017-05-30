package by.nc.training.dev3.coffee.interfaces;

import by.nc.training.dev3.coffee.dto.ContentDto;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.exceptions.ServiceException;

import java.util.List;

/**
 * Created by Win on 11.05.2017.
 */
public interface CoffeeMachineService {
    List<ContentDto> showBeverageInMachine()throws ServiceException;

    List<ContentDto> showIngredientsInMachine()throws ServiceException;
}

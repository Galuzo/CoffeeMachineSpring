package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.BeverageDaoImpl;
import by.nc.training.dev3.coffee.dao.impl.IngredientDaoImpl;
import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.dao.interfaces.IIngredientDao;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.CoffeeMachineService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by Win on 07.05.2017.
 */
@Service
public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    private static Logger logger = Logger.getLogger(CoffeeMachineServiceImpl.class);
    private static  String message;
    @Autowired
    private IBeverageDao beverageDao;
    @Autowired
    private IIngredientDao ingredientDao;

    private CoffeeMachineServiceImpl(){}


    public List<Beverage> showBeverageInMachine()throws ServiceException {
        List<Beverage> beverages;
        try {
            beverages=beverageDao.getAll();
        } catch (DaoException e) {
            logger.error(message+e);
            message="The beverages was not found";
            throw new ServiceException(message, e);
        }
        return beverages;
    }

    public List<Ingredient> showIngredientsInMachine()throws ServiceException {
        List<Ingredient> ingredients;
        try {
            ingredients=ingredientDao.getAll();
        } catch (DaoException e) {
            message="The ingredients was not found";
            throw new ServiceException(message, e);
        }
        return ingredients;
    }
}

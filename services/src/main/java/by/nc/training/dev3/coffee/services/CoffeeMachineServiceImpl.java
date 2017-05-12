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


import java.util.List;

/**
 * Created by Win on 07.05.2017.
 */
public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    private static CoffeeMachineService instance;
    private static Logger logger = Logger.getLogger(CoffeeMachineServiceImpl.class);
    private static  String message;

    private CoffeeMachineServiceImpl(){}
    public static synchronized CoffeeMachineService getInstance(){
        if(instance == null){
            instance = new CoffeeMachineServiceImpl();
        }
        return instance;
    }

    public List<Beverage> showBeverageInMachine()throws ServiceException {
        IBeverageDao beverageDao = BeverageDaoImpl.getInstance();
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
        IIngredientDao ingredientDao = IngredientDaoImpl.getInstance();
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

package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.BeverageDaoImpl;
import by.nc.training.dev3.coffee.dao.impl.IngredientDaoImpl;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;

/**
 * Created by Win on 07.05.2017.
 */
public class CoffeeMachineService {

    private static CoffeeMachineService instance;
    private static Logger logger = Logger.getLogger(CoffeeMachineService.class);
    private static  String message;

    private CoffeeMachineService(){}
    public static synchronized CoffeeMachineService getInstance(){
        if(instance == null){
            instance = new CoffeeMachineService();
        }
        return instance;
    }

    public List<Beverage> showBeverageInMachine()throws ServiceException {
        BeverageDaoImpl beverageDao = BeverageDaoImpl.getInstance();
        List<Beverage> beverages=null;
        try {
            beverages=beverageDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("The beverages was not found", e);
        }
        return beverages;
    }

    public List<Ingredient> showIngredientsInMachine()throws ServiceException {
        IngredientDaoImpl ingredientDao = IngredientDaoImpl.getInstance();
        List<Ingredient> ingredients=null;
        try {
            ingredients=ingredientDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("The ingredients was not found", e);
        }
        return ingredients;
    }
}

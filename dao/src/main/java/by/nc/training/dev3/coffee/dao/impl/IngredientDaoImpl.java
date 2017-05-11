package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.dao.interfaces.IIngredientDao;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import org.apache.log4j.Logger;

/**
 * Created by Win on 05.05.2017.
 */
public class IngredientDaoImpl extends AbstractDao<Ingredient> implements IIngredientDao {
    private static IngredientDaoImpl instance;

    protected IngredientDaoImpl() {
        super(Ingredient.class);
    }
    public static synchronized IngredientDaoImpl getInstance(){
        if(instance == null){
            instance = new IngredientDaoImpl();
        }
        return instance;
    }


}

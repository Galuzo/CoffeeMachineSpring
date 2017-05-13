package by.nc.training.dev3.coffee.utils;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.impl.BeverageDaoImpl;
import by.nc.training.dev3.coffee.dao.impl.IngredientDaoImpl;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Content;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.enums.ContentType;

/**
 * Created by Win on 06.05.2017.
 */
public class Tools {
    public static int incrementValue(int value,int count) {
        return value+count;
    }
    public static int decrementValue(int value,int count) {
        value = value - count;
        if (value < 0)
        {
            value=0;
        }
        return value;
    }

    public static AbstractDao defineContentDao(ContentType contentType) {
        switch (contentType) {
            case BEVERAGE:
                return BeverageDaoImpl.getInstance();
            case INGREDIENT:
                return IngredientDaoImpl.getInstance();
        }
        return null;
    }

    public static Content defineContent(ContentType contentType) {
        switch (contentType) {
            case BEVERAGE:
                return new Beverage();
            case INGREDIENT:
                return new Ingredient();
        }
        return null;
    }
}

package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.dao.interfaces.IIngredientDao;
import by.nc.training.dev3.coffee.dto.ContentDto;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.CoffeeMachineService;
import by.nc.training.dev3.coffee.utils.DtoBuiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win on 07.05.2017.
 */
@Service
@Transactional
public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoffeeMachineServiceImpl.class);
    private static  String message;
    @Autowired
    private IBeverageDao beverageDao;
    @Autowired
    private IIngredientDao ingredientDao;

    private CoffeeMachineServiceImpl(){}


    public List<ContentDto> showBeverageInMachine()throws ServiceException {
        List<ContentDto> beverages = new ArrayList<ContentDto>();
        ContentDto contentDto;
        try {
            for (Beverage beverage : beverageDao.getAll()) {
                contentDto = DtoBuiler.contentDtoBuilder(beverage);
                beverages.add(contentDto);
            }
        } catch (DaoException e) {
            message="The beverages was not found";
            LOGGER.error(message+e);

            throw new ServiceException(message, e);
        }
        return beverages;
    }

    public List<ContentDto> showIngredientsInMachine()throws ServiceException {
        ContentDto contentDto;
        List<ContentDto> ingredients=new ArrayList<ContentDto>();
        try {
            for (Ingredient ingredient : ingredientDao.getAll()) {
                contentDto = DtoBuiler.contentDtoBuilder(ingredient);
                ingredients.add(contentDto);

            }
        } catch (DaoException e) {
            message="The ingredients was not found";
            LOGGER.error(message+e);
            throw new ServiceException(message, e);
        }
        return ingredients;
    }
}

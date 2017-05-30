package by.nc.training.dev3.coffee.controllers;

import by.nc.training.dev3.coffee.dto.ContentDto;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.CoffeeMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * Created by Win on 24.05.2017.
 */
@RestController
@RequestMapping("/machine")
public class CoffeeMachineController {

    @Autowired
    CoffeeMachineService coffeeMachineService;

    @RequestMapping(value = "/beverages",method = RequestMethod.GET)
    public Collection<ContentDto> getAllBeverages()
    {
        List<ContentDto> beverages=null;
        try {
            beverages=coffeeMachineService.showBeverageInMachine();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return beverages;
    }

    @RequestMapping(value = "/ingredients",method = RequestMethod.GET)
    public Collection<ContentDto> getAllIngredients()
    {
        List<ContentDto> ingredients=null;
        try {
            ingredients=coffeeMachineService.showIngredientsInMachine();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

}

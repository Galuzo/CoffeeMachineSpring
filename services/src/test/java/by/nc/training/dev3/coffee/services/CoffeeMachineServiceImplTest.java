package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dto.ContentDto;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.interfaces.CoffeeMachineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Win on 07.05.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/test-services-context.xml")
@ComponentScan
public class CoffeeMachineServiceImplTest {
    @Autowired
    private CoffeeMachineService coffeeMachineService;
    @Test
    public List<ContentDto> showBeverageInMachine() throws Exception {
        List<ContentDto> beverageList= coffeeMachineService.showBeverageInMachine();
       return beverageList;
    }

    @Test
    public List<ContentDto> showIngredientsInMachine() throws Exception {
        List<ContentDto> ingredients= coffeeMachineService.showIngredientsInMachine();
        return ingredients;
    }

}
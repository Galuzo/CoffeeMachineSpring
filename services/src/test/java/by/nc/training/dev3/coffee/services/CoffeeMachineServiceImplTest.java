package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.interfaces.CoffeeMachineService;
import org.junit.Test;

import java.util.List;

/**
 * Created by Win on 07.05.2017.
 */
public class CoffeeMachineServiceImplTest {
    @Test
    public void showBeverageInMachine() throws Exception {
        CoffeeMachineService coffeeMachineService = CoffeeMachineServiceImpl.getInstance();
        List<Beverage> beverageList= coffeeMachineService.showBeverageInMachine();
        for (Beverage beverage : beverageList) {
            System.out.println(beverage);
        }
    }

    @Test
    public void showIngredientsInMachine() throws Exception {
        CoffeeMachineService coffeeMachineService = CoffeeMachineServiceImpl.getInstance();
        List<Ingredient> ingredients= coffeeMachineService.showIngredientsInMachine();
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient);
        }
    }

}
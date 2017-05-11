package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Ingredient;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Win on 07.05.2017.
 */
public class CoffeeMachineServiceTest {
    @Test
    public void showBeverageInMachine() throws Exception {
        CoffeeMachineService coffeeMachineService = CoffeeMachineService.getInstance();
        List<Beverage> beverageList=coffeeMachineService.showBeverageInMachine();
        for (Beverage beverage : beverageList) {
            System.out.println(beverage);
        }
    }

    @Test
    public void showIngredientsInMachine() throws Exception {
        CoffeeMachineService coffeeMachineService = CoffeeMachineService.getInstance();
        List<Ingredient> ingredients=coffeeMachineService.showIngredientsInMachine();
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient);
        }
    }

}
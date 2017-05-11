package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.BeverageDaoImpl;
import by.nc.training.dev3.coffee.dao.impl.UserDaoImpl;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.enums.ContentType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Win on 06.05.2017.
 */
public class AdminServiceTest {
    private static AdminService adminService;

    @BeforeClass
    public static void init() {
         adminService = AdminService.getInstance();

    }

    @Test
    public void addExistIngredientShouldReturnCount() throws Exception {
        final int incCount=5;
        final int countNewIngredient=5;
        int expectedCount = countNewIngredient + incCount;
        int id=adminService.addNewContentInMachine(ContentType.INGREDIENT, "milk", 1.3, countNewIngredient);
        int newCount=adminService.addExistContentInMachine(ContentType.INGREDIENT, id, incCount);
        adminService.removeContentFromMachine(ContentType.INGREDIENT, id);
        assertEquals(expectedCount, newCount);
    }

    @Test
    public void addExistBeverageShouldReturnCount() throws Exception {
        final int incCount=5;
        final int countNewBeverage=5;
        int expectedCount = countNewBeverage + incCount;
        int id=adminService.addNewContentInMachine(ContentType.BEVERAGE, "coffee", 1.3, countNewBeverage);
        int newCount=adminService.addExistContentInMachine(ContentType.BEVERAGE, id, incCount);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        assertEquals(expectedCount, newCount);
    }

    @Test
    public void addNewBeverageInMachine() throws Exception
    {
        final int countNewBeverage=5;
        int id=adminService.addNewContentInMachine(ContentType.BEVERAGE, "coffee", 1.3, countNewBeverage);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        assertNotNull(id);
    }

    @Test
    public void addNewIngredientInMachine() throws Exception {
        final int countNewIngredient=5;
        int id=adminService.addNewContentInMachine(ContentType.INGREDIENT, "coffee", 1.3, countNewIngredient);
        adminService.removeContentFromMachine(ContentType.INGREDIENT, id);
        assertNotNull(id);
    }

    @Test
    public void removeBeverage() throws Exception {
        final int countNewBeverage=5;
        int id=adminService.addNewContentInMachine(ContentType.BEVERAGE, "coffee", 1.3, countNewBeverage);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        BeverageDaoImpl dao = BeverageDaoImpl.getInstance();
        assertNull(dao.getById(id));
    }

}
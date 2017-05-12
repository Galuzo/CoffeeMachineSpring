package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.BeverageDaoImpl;
import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.enums.ContentType;
import by.nc.training.dev3.coffee.interfaces.AdminService;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Win on 06.05.2017.
 */
public class AdminServiceImplTest {
    private static AdminService adminService;
    private final String TITLE = "coffee";
    private final double COST=1.3;

    @BeforeClass
    public static void init() {
         adminService = AdminServiceImpl.getInstance();

    }

    @Test
    public void addExistIngredientShouldReturnCount() throws Exception {
        int incCount=5;
        int countNewIngredient=5;
        int expectedCount = countNewIngredient + incCount;
        int id= adminService.addNewContentInMachine(ContentType.INGREDIENT, TITLE, COST, countNewIngredient);
        int newCount= adminService.addExistContentInMachine(ContentType.INGREDIENT, id, incCount);
        adminService.removeContentFromMachine(ContentType.INGREDIENT, id);
        assertEquals(expectedCount, newCount);
    }

    @Test
    public void addExistBeverageShouldReturnCount() throws Exception {
        int incCount=5;
        int countNewBeverage=5;
        int expectedCount = countNewBeverage + incCount;
        int id= adminService.addNewContentInMachine(ContentType.BEVERAGE, TITLE, COST, countNewBeverage);
        int newCount= adminService.addExistContentInMachine(ContentType.BEVERAGE, id, incCount);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        assertEquals(expectedCount, newCount);
    }

    @Test
    public void addNewBeverageInMachine() throws Exception
    {
        int countNewBeverage=5;
        int id= adminService.addNewContentInMachine(ContentType.BEVERAGE, TITLE, COST, countNewBeverage);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        assertNotNull(id);
    }

    @Test
    public void addNewIngredientInMachine() throws Exception {
        int countNewIngredient=5;
        int id= adminService.addNewContentInMachine(ContentType.INGREDIENT, TITLE, COST, countNewIngredient);
        adminService.removeContentFromMachine(ContentType.INGREDIENT, id);
        assertNotNull(id);
    }

    @Test
    public void removeBeverage() throws Exception {
        int countNewBeverage=5;
        int id= adminService.addNewContentInMachine(ContentType.BEVERAGE, TITLE, COST, countNewBeverage);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        IBeverageDao dao = BeverageDaoImpl.getInstance();
        assertNull(dao.getById(id));
    }

}
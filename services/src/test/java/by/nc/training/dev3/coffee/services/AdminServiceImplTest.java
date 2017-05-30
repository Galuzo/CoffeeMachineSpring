package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.BeverageDaoImpl;
import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.enums.ContentType;
import by.nc.training.dev3.coffee.interfaces.AdminService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Win on 06.05.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/test-services-context.xml")
@ComponentScan
public class AdminServiceImplTest {
    private final String TITLE = "coffee";
    private final double COST=1.3;

    @Autowired
    private  AdminService adminService;

    @Autowired
    private IBeverageDao beverageDao;


    @Test
    public void addExistIngredientShouldReturnCount() throws Exception {
        int incCount=5;
        int countNewIngredient=5;
        int expectedCount = countNewIngredient + incCount;
       // int id= adminService.addNewContentInMachine(ContentType.INGREDIENT, TITLE, COST, countNewIngredient);
       // int newCount= adminService.addExistContentInMachine(ContentType.INGREDIENT, id, incCount);
        //adminService.removeContentFromMachine(ContentType.INGREDIENT, id);
        //assertEquals(expectedCount, newCount);
    }

    @Test
    public void addExistBeverageShouldReturnCount() throws Exception {
        int incCount=5;
        int countNewBeverage=5;
        int expectedCount = countNewBeverage + incCount;
       /* int id= adminService.addNewContentInMachine(ContentType.BEVERAGE, TITLE, COST, countNewBeverage);
        int newCount= adminService.addExistContentInMachine(ContentType.BEVERAGE, id, incCount);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);*/
        //assertEquals(expectedCount, newCount);
    }

    @Test
    public void addNewBeverageInMachine() throws Exception
    {
        int countNewBeverage=5;
       /* int id= adminService.addNewContentInMachine(ContentType.BEVERAGE, TITLE, COST, countNewBeverage);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        assertNotNull(id);*/
    }

    @Test
    public void addNewIngredientInMachine() throws Exception {
        /*int countNewIngredient=5;
        int id= adminService.addNewContentInMachine(ContentType.INGREDIENT, TITLE, COST, countNewIngredient);
        adminService.removeContentFromMachine(ContentType.INGREDIENT, id);
        assertNotNull(id);*/
    }

    @Test
    public void removeBeverage() throws Exception {
       /* int countNewBeverage=5;
        int id= adminService.addNewContentInMachine(ContentType.BEVERAGE, TITLE, COST, countNewBeverage);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        assertNull(beverageDao.getById(id));*/
    }

}
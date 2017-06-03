package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.BeverageDaoImpl;
import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.dto.ContentForIncDto;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Content;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.enums.ContentType;
import by.nc.training.dev3.coffee.interfaces.AdminService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Win on 06.05.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/test-services-context.xml")
@Transactional
@Rollback(false)
@ComponentScan
public class AdminServiceImplTest {
    private final String TITLE = "coffee";
    private final double COST=1.3;
    private final int COUNT=5;

    @Autowired
    private  AdminService adminService;

    @Autowired
    private IBeverageDao beverageDao;


    @Test
    public void addExistIngredientShouldReturnCount() throws Exception {
        int incCount=5;
        int expectedCount = COUNT + incCount;
        Ingredient content = new Ingredient();
        content.setCount(COUNT);
        content.setTitle(TITLE);
        content.setCost(COST);
        int id= adminService.addNewContentInMachine(ContentType.INGREDIENT,content);
        ContentForIncDto contentForIncDto = new ContentForIncDto();
        contentForIncDto.setId(id);
        contentForIncDto.setCount(incCount);
        int newCount= adminService.addExistContentInMachine(ContentType.INGREDIENT,contentForIncDto);
        adminService.removeContentFromMachine(ContentType.INGREDIENT, id);
        assertEquals(expectedCount, newCount);
    }

    @Test
    public void addExistBeverageShouldReturnCount() throws Exception {
        int incCount=5;
        Beverage content = new Beverage();
        content.setTitle(TITLE);
        content.setCount(COUNT);
        content.setCost(COST);
        int expectedCount = COUNT + incCount;
        int id= adminService.addNewContentInMachine(ContentType.BEVERAGE, content);
        ContentForIncDto contentForIncDto = new ContentForIncDto();
        contentForIncDto.setId(id);
        contentForIncDto.setCount(incCount);
        int newCount= adminService.addExistContentInMachine(ContentType.BEVERAGE,contentForIncDto);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        assertEquals(expectedCount, newCount);
    }

    @Test
    public void addNewBeverageInMachine() throws Exception
    {
        Beverage content = new Beverage();
        content.setTitle(TITLE);
        content.setCost(COST);
        content.setCount(COUNT);
       int id= adminService.addNewContentInMachine(ContentType.BEVERAGE, content);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        assertNotNull(id);
    }

    @Test
    public void addNewIngredientInMachine() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setCost(COST);
        ingredient.setTitle(TITLE);
        ingredient.setCount(COUNT);
        int id= adminService.addNewContentInMachine(ContentType.INGREDIENT,ingredient);
        adminService.removeContentFromMachine(ContentType.INGREDIENT, id);
        assertNotNull(id);
    }

    @Test
    public void removeBeverage() throws Exception {
        Beverage beverage = new Beverage();
        beverage.setCost(COST);
        beverage.setTitle(TITLE);
        beverage.setCount(COUNT);
        int id= adminService.addNewContentInMachine(ContentType.BEVERAGE, beverage);
        adminService.removeContentFromMachine(ContentType.BEVERAGE, id);
        assertNull(beverageDao.getById(id));
    }


}
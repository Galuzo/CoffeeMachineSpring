package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.entities.Beverage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by Win on 09.05.2017.
 */
@ContextConfiguration("/test-dao-context.xml")
@Transactional
@Rollback(false)
@RunWith(SpringRunner.class)
public class BeverageDaoImplTest {

    private final String TITLE = "latte";
    private final double COST=1.3;
    private final int COUNT=5;

    @Autowired
    private IBeverageDao beverageDao;

    @Test public void saveShouldReturnId() throws Exception
    {
        Beverage beverage = createNewBeverage();
        int result=(Integer) beverageDao.save(beverage);
        beverage.setId(result);
        beverageDao.delete(beverage);
        assertNotNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowException() throws Exception
    {
         beverageDao.save(null);
    }

    @Test
    public void updateShouldChangeObject() throws Exception{
        String updatedTitle = "updated";
        Beverage beverage = createNewBeverage();
        int result=(Integer) beverageDao.save(beverage);
        beverage.setId(result);

        Beverage expectedBeverage =beverageDao.getById(result);
        expectedBeverage.setTitle(updatedTitle);
        beverageDao.update(expectedBeverage);
        Beverage realResult=beverageDao.getById(result);
        beverageDao.delete(beverage);
        assertEquals(expectedBeverage,realResult);
    }

    @Test
    public void deleteShouldDeleteObject() throws Exception
    {
        Beverage beverage = createNewBeverage();
        int result=(Integer) beverageDao.save(beverage);
        beverage.setId(result);
        beverageDao.delete(beverage);
        Beverage newBeverage=beverageDao.getById(result);
        assertNull(newBeverage);
    }

    @Test
    public void getByIdShouldReturnObject() throws Exception {
        Beverage beverage = createNewBeverage();
        int result=(Integer) beverageDao.save(beverage);
        beverage.setId(result);
        Beverage resultBeverage = beverageDao.getById(result);
        beverageDao.delete(beverage);
        assertEquals(resultBeverage,beverage);
    }

    @Test()
    public void getByIdShouldReturnNull() throws Exception
    {
        int idBeverage=40;
        Beverage result=beverageDao.getById(idBeverage);
        assertNull(result);
    }

    @Test
    public void getAllShouldReturnList() throws Exception {
        Beverage beverage = createNewBeverage();
        int result=(Integer) beverageDao.save(beverage);
        beverage.setId(result);
        Beverage beverage1 = createNewBeverage();
        int result1=(Integer) beverageDao.save(beverage1);
        beverage1.setId(result1);
        List<Beverage> list=beverageDao.getAll();
        beverageDao.delete(beverage);
        beverageDao.delete(beverage1);
        assertNotNull(list.size());
    }


    private Beverage createNewBeverage() {
        Beverage beverage = new Beverage();
        beverage.setTitle(TITLE);
        beverage.setCount(COUNT);
        beverage.setCost(COST);
        return beverage;

    }
}
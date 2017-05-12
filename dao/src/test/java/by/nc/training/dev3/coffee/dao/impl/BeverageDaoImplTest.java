package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Win on 09.05.2017.
 */
public class BeverageDaoImplTest {
    private AbstractDao dao;
    private Transaction transaction;
    private  Session session;
    private final String TITLE = "latte";
    private final double COST=1.3;
    private final int COUNT=5;

    @BeforeClass
    public static void  initTest(){}
    {
        dao = BeverageDaoImpl.getInstance();
    }
    @Before
    public void setup(){
        session=HibernateUtil.getInstance().getSession();
        transaction=session.beginTransaction();
    }

    @Test public void saveShouldReturnId() throws Exception
    {
        Beverage beverage = createNewBeverage();
        int result=(Integer) dao.save(beverage);
        beverage.setId(result);
        dao.delete(beverage);
        assertNotNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowException() throws Exception
    {
        int result=(Integer) dao.save(null);
    }

    @Test
    public void updateShouldChangeObject() throws Exception{
        String updatedTitle = "updated";
        Beverage beverage = createNewBeverage();
        int result=(Integer) dao.save(beverage);
        beverage.setId(result);

        Beverage expectedBeverage = (Beverage)dao.getById(result);
        expectedBeverage.setTitle(updatedTitle);
        dao.update(expectedBeverage);
        Beverage realResult=(Beverage)dao.getById(result);
        dao.delete(beverage);
        assertEquals(expectedBeverage,realResult);
    }

    @Test
    public void deleteShouldDeleteObject() throws Exception
    {
        Beverage beverage = createNewBeverage();
        int result=(Integer) dao.save(beverage);
        beverage.setId(result);
        dao.delete(beverage);
        Beverage newBeverage=(Beverage)dao.getById(result);
        assertNull(newBeverage);
    }

    @Test
    public void getByIdShouldReturnObject() throws Exception {
        Beverage beverage = createNewBeverage();
        int result=(Integer) dao.save(beverage);
        beverage.setId(result);
        Beverage resultBeverage = (Beverage) dao.getById(result);
        dao.delete(beverage);
        assertEquals(resultBeverage,beverage);
    }

    @Test()
    public void getByIdShouldReturnNull() throws Exception
    {
        int idBeverage=40;
        Beverage result=(Beverage)dao.getById(idBeverage);
        assertNull(result);
    }

    @Test
    public void getAllShouldReturnList() throws Exception {
        Beverage beverage = createNewBeverage();
        int result=(Integer) dao.save(beverage);
        beverage.setId(result);
        Beverage beverage1 = createNewBeverage();
        int result1=(Integer) dao.save(beverage1);
        beverage1.setId(result1);
        List<Beverage> list=dao.getAll();
        dao.delete(beverage);
        dao.delete(beverage1);
        assertNotNull(list.size());
    }

    @After
    public void tearDown() throws Exception{
        transaction.commit();
        transaction=null;
    }

    private Beverage createNewBeverage() {
        Beverage beverage = new Beverage();
        beverage.setTitle(TITLE);
        beverage.setCount(COUNT);
        beverage.setCost(COST);
        return beverage;

    }
}
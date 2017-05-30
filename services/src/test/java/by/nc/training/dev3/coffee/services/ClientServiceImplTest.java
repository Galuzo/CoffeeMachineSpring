package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.*;
import by.nc.training.dev3.coffee.dao.interfaces.*;
import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.entities.Ingredient;
import by.nc.training.dev3.coffee.entities.Order;
import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.interfaces.ClientService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Win on 09.05.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/test-services-context.xml")
@ComponentScan
public class ClientServiceImplTest {
    @Autowired
    private  ClientService clientService;
    private  User user;
    @Autowired
    private  IUserDao userDao;
    @Autowired
    private  IIngredientDao ingredientDao;

    @Autowired
    private  IOrderDao orderDao;
    @Autowired
    private IBillDao billDao;

    @Before
    public void setUp() throws Exception
    {
        int idUser=1;
        user = userDao.getById(idUser);
    }


   /* @Test
    public void addBeverageInBill() throws Exception {
        int idBeverage=33;
        int result= clientService.addBeverageInBill(user, idBeverage);
        Order order=orderDao.getById(result);
        //clientService.removeBeverageFromBill(user,result);
        assertNotNull(order);
    }

    @Test
    public void addIngredient() throws Exception {


        int idIngredient=1;
        int idBeverage=1;
        int idOrder= clientService.addBeverageInBill(user, idBeverage);
        clientService.addIngredient(user, idOrder, idIngredient);
        Order order=orderDao.getById(idOrder);
        Ingredient ingredient= ingredientDao.getById(idIngredient);
        boolean result=order.getIngredientSet().contains(ingredient);
       // clientService.removeBeverageFromBill(user,idOrder);
        assertTrue(result);

    }*/

    /*@Test
    public void removeBeverageFromBill() throws Exception {
        int idBeverage=1;
        int idOrder= clientService.addBeverageInBill(user, idBeverage);
       // clientService.removeBeverageFromBill(user,idOrder);
        Order order=orderDao.getById(idOrder);
        assertNull(order);
    }

    @Test
    public void payBill() throws Exception
    {
        clientService.payBill(user);
        Bill bill=billDao.getByUser(user);
        List<Order> orderList = orderDao.getByBill(bill);
        boolean result=orderList.isEmpty();
        assertTrue(result);
    }

    @Test
    public void removeIngredient() throws Exception {
        int idIngredient=1;
        int idBeverage=1;
        int idOrder= clientService.addBeverageInBill(user, idBeverage);
        clientService.addIngredient(user, idOrder, idIngredient);

        Ingredient ingredient = ingredientDao.getById(idIngredient);
       // clientService.removeIngredient(user,idOrder,idIngredient);
        Order order=orderDao.getById(idOrder);
        boolean result = order.getIngredientSet().contains(ingredient);
        assertFalse(result);
    }*/

}
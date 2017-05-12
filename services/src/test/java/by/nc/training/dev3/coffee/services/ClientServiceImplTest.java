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

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Win on 09.05.2017.
 */
public class ClientServiceImplTest {
    private static ClientService clientService;
    private  User user;
    private static IUserDao userDao;
    private static IIngredientDao ingredientDao;
    private static IBeverageDao beverageDao;
    private static IOrderDao orderDao;


    @BeforeClass
    public static void init(){
        clientService = ClientServiceImpl.getInstance();
        userDao = UserDaoImpl.getInstance();
        ingredientDao = IngredientDaoImpl.getInstance();
        beverageDao = BeverageDaoImpl.getInstance();
        orderDao = OrderDaoImpl.getInstance();
    }

    @Before
    public void setUp() throws Exception
    {
        int idUser=1;
        user = userDao.getById(idUser);
    }




    @Test
    public void addBeverageInBill() throws Exception {
        int idBeverage=2;
        int result= clientService.addBeverageInBill(user, idBeverage);
        Order order=orderDao.getById(result);
        clientService.removeBeverageFromBill(user,result);
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
        clientService.removeBeverageFromBill(user,idOrder);
        assertTrue(result);

    }

    @Test
    public void removeBeverageFromBill() throws Exception {
        int idBeverage=1;
        int idOrder= clientService.addBeverageInBill(user, idBeverage);
        clientService.removeBeverageFromBill(user,idOrder);
        Order order=orderDao.getById(idOrder);
        assertNull(order);
    }

    @Test
    public void payBill() throws Exception
    {
        clientService.payBill(user);
        IBillDao billDao = BillDaoImpl.getInstance();
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
        clientService.removeIngredient(user,idOrder,idIngredient);
        Order order=orderDao.getById(idOrder);
        boolean result = order.getIngredientSet().contains(ingredient);
        assertFalse(result);
    }

}
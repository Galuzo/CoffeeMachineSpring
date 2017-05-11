package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.UserDaoImpl;
import by.nc.training.dev3.coffee.entities.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Win on 09.05.2017.
 */
public class BillServiceTest {
    private BillService billService;

    @Before
    public void setUp(){
        billService = BillService.getInstance();
    }
    @Test
    public void showBill() throws Exception {
        final int idUser=1;
        User user = UserDaoImpl.getInstance().getById(idUser);
        billService.showBill(user);
    }

}
package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.UserDaoImpl;
import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.interfaces.BillService;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Win on 09.05.2017.
 */
public class BillServiceImplTest {
    private BillService billService;

    @Before
    public void setUp(){
        billService = BillServiceImpl.getInstance();
    }
    @Test
    public void showBill() throws Exception {
        int idUser=1;
        User user = UserDaoImpl.getInstance().getById(idUser);
        billService.showBill(user);
    }

}
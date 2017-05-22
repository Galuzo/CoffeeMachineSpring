package by.nc.training.dev3.coffee.services;

import by.nc.training.dev3.coffee.dao.impl.UserDaoImpl;
import by.nc.training.dev3.coffee.dao.interfaces.IUserDao;
import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.interfaces.BillService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Win on 09.05.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/test-services-context.xml")
@ComponentScan
public class BillServiceImplTest {

    @Autowired
    private BillService billService;
    @Autowired
    private IUserDao userDao;
    @Test
    public void showBill() throws Exception {
        int idUser=1;
        User user = userDao.getById(idUser);
        billService.showBill(user);
    }

}
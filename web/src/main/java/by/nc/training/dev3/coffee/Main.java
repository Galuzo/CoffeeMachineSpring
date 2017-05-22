package by.nc.training.dev3.coffee;

import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
public class Main {

    @Autowired
    IBeverageDao beverageDao;

    @RequestMapping("/")
    @Transactional
    String home() {
        Beverage beverage=null;
        try {
            beverage=beverageDao.getById(1);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        System.out.println(beverage);
        return "hello";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }




}
package by.nc.training.dev3.coffee;
import by.nc.training.dev3.coffee.services.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class})
public  class Main {



    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }




}
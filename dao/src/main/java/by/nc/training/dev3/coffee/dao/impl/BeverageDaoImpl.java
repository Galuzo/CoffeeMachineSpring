package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.entities.Beverage;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Win on 04.05.2017.
 */
@Component
public class BeverageDaoImpl extends AbstractDao<Beverage> implements IBeverageDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeverageDaoImpl.class);

    @Autowired
    protected BeverageDaoImpl(SessionFactory sessionFactory) {
        super(Beverage.class,sessionFactory);
    }



}

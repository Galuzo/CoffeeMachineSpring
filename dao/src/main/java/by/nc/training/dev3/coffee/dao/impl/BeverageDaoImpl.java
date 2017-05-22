package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Win on 04.05.2017.
 */
@Component
public class BeverageDaoImpl extends AbstractDao<Beverage> implements IBeverageDao {
    private static final Logger LOGGER = Logger.getLogger(BeverageDaoImpl.class);

    @Autowired
    protected BeverageDaoImpl(SessionFactory sessionFactory) {
        super(Beverage.class,sessionFactory);
    }


    public Beverage getByTitle(String title) throws DaoException {
        Session session;
        Beverage beverage;
        try {
            session = sessionFactory.getCurrentSession();
            Query query=session.createQuery("from Beverage where title = :title");
            query.setParameter("title", title);
             beverage= (Beverage) query.uniqueResult();

        } catch (Exception e) {
            LOGGER.error("Error was thrown in BeverageDaoIml"+e);
            throw new DaoException();
        }
        return beverage;
    }
}

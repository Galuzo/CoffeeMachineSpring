package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IBeverageDao;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by Win on 04.05.2017.
 */
public class BeverageDaoImpl extends AbstractDao<Beverage> implements IBeverageDao {
    private static BeverageDaoImpl instance;
    private static Logger logger = Logger.getLogger(BeverageDaoImpl.class);

    protected BeverageDaoImpl() {
        super(Beverage.class);
    }
    public static synchronized BeverageDaoImpl getInstance(){
        if(instance == null){
            instance = new BeverageDaoImpl();
        }
        return instance;
    }

    public Beverage getByTitle(String title) throws DaoException {
        Session session=null;
        Beverage beverage;
        try {
            session = HibernateUtil.getInstance().getSession();
            Query query=session.createQuery("from Beverage where title = :title");
            query.setParameter("title", title);
             beverage= (Beverage) query.uniqueResult();

        } catch (Exception e) {
            logger.error("Error was thrown in BeverageDaoIml"+e);
            throw new DaoException();
        }
        return beverage;
    }
}

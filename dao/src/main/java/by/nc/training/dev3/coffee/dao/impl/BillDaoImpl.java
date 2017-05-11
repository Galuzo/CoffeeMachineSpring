package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IBillDao;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Win on 05.05.2017.
 */
public class BillDaoImpl extends AbstractDao<Bill> implements IBillDao {
    private static BillDaoImpl instance;
    private static Logger logger = Logger.getLogger(BillDaoImpl.class);


    protected BillDaoImpl() {
        super(Bill.class);
    }
    public static synchronized BillDaoImpl getInstance(){
        if(instance == null){
            instance = new BillDaoImpl();
        }
        return instance;
    }



    public Bill getByUser(User user) throws DaoException {
        Bill bill;
        try {
            Session session = util.getSession();
            Criteria criteria=session.createCriteria(Bill.class);
            bill=(Bill) criteria.add(Restrictions.eq("user", user)).uniqueResult();
        }
        catch(HibernateException e){
            logger.error("Error was thrown in DAO: " + e);
            throw new DaoException(e.getMessage());
        }
        return bill;
    }
}

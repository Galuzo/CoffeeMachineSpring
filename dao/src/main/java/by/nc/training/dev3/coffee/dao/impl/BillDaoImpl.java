package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IBillDao;
import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.entities.Account;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Win on 05.05.2017.
 */
@Repository
public class BillDaoImpl extends AbstractDao<Bill> implements IBillDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillDaoImpl.class);

    @Autowired
    protected BillDaoImpl(SessionFactory sessionFactory) {
        super(Bill.class,sessionFactory);
    }



    public Bill getByUser(Account user) throws DaoException {
        Bill bill;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria=session.createCriteria(Bill.class);
            bill=(Bill) criteria.add(Restrictions.eq("user", user)).uniqueResult();
        }
        catch(HibernateException e){
            LOGGER.error("Error was thrown in DAO: " + e);
            throw new DaoException(e.getMessage());
        }
        return bill;
    }
}

package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IOrderDao;
import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.entities.Order;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win on 07.05.2017.
 */
@Repository
public class OrderDaoImpl extends AbstractDao<Order> implements IOrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Autowired
    protected OrderDaoImpl(SessionFactory sessionFactory) {
        super(Order.class,sessionFactory);
    }

    public List<Order> getByBill(Bill bill)throws DaoException {
        List<Order> list;
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria=session.createCriteria(Order.class);
            list=criteria.add(Restrictions.eq("bill", bill)).list();
        }
        catch(HibernateException e){
            LOGGER.error("Error was thrown in DAO: " + e);
            throw new DaoException(e.getMessage());
        }
        return list;
    }
}

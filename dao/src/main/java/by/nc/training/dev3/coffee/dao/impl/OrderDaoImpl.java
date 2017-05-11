package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IOrderDao;
import by.nc.training.dev3.coffee.entities.Bill;
import by.nc.training.dev3.coffee.entities.Order;
import by.nc.training.dev3.coffee.entities.Role;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Win on 07.05.2017.
 */
public class OrderDaoImpl extends AbstractDao<Order> implements IOrderDao {
    private static Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private static OrderDaoImpl instance;

    protected OrderDaoImpl() {
        super(Order.class);
    }
    public static synchronized OrderDaoImpl getInstance(){
        if(instance == null){
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    public List<Order> getByBill(Bill bill)throws DaoException {
        List<Order> list = new ArrayList<Order>();
        try {
            Session session = util.getSession();
            Criteria criteria=session.createCriteria(Order.class);
            list=criteria.add(Restrictions.eq("bill", bill)).list();
        }
        catch(HibernateException e){
            logger.error("Error was thrown in DAO: " + e);
            throw new DaoException(e.getMessage());
        }
        return list;
    }
}

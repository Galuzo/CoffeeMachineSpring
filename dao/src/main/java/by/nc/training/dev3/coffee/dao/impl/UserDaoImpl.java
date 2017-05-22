package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IUserDao;
import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by Win on 05.05.2017.
 */
@Repository
public class UserDaoImpl extends AbstractDao<User> implements IUserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    static String message;

    @Autowired
    protected UserDaoImpl(SessionFactory sessionFactory) {
        super(User.class,sessionFactory);
    }


    public User getByLogin(String login) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(User.class);
            User user = (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
            return user;
        } catch (HibernateException e) {
            message="Unable to get User by login";
            LOGGER.error(message + e);
            throw new DaoException(message, e);
        }

    }
}

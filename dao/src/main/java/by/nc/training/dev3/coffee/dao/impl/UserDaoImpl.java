package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IUserDao;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.User;
import by.nc.training.dev3.coffee.exceptions.DaoException;
import by.nc.training.dev3.coffee.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Win on 05.05.2017.
 */
public class UserDaoImpl extends AbstractDao<User> implements IUserDao {
    private static UserDaoImpl instance;
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);
    static String message;


    protected UserDaoImpl() {
        super(User.class);
    }
    public static synchronized UserDaoImpl getInstance(){
        if(instance == null){
            instance = new UserDaoImpl();
        }
        return instance;
    }

    public User getByLogin(String login) throws DaoException {
        try {
            Session session = HibernateUtil.getInstance().getSession();
            Criteria criteria = session.createCriteria(User.class);
            User user = (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
            return user;
        } catch (HibernateException e) {
            message="Unable to get User by login";
            logger.error(message + e);
            throw new DaoException(message, e);
        }

    }
}

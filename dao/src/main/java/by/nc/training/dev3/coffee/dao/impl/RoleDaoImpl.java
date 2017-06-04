package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IRoleDao;
import by.nc.training.dev3.coffee.entities.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Win on 05.05.2017.
 */
@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements IRoleDao {
    @Autowired
    protected RoleDaoImpl(SessionFactory sessionFactory) {
        super(Role.class,sessionFactory);
    }

}

package by.nc.training.dev3.coffee.dao.impl;

import by.nc.training.dev3.coffee.dao.AbstractDao;
import by.nc.training.dev3.coffee.dao.interfaces.IRoleDao;
import by.nc.training.dev3.coffee.entities.Beverage;
import by.nc.training.dev3.coffee.entities.Role;
import org.apache.log4j.Logger;

/**
 * Created by Win on 05.05.2017.
 */
public class RoleDaoImpl extends AbstractDao<Role> implements IRoleDao {
    private static RoleDaoImpl instance;

    protected RoleDaoImpl() {
        super(Role.class);
    }
    public static synchronized RoleDaoImpl getInstance(){
        if(instance == null){
            instance = new RoleDaoImpl();
        }
        return instance;
    }
}

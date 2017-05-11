package by.nc.training.dev3.coffee.utils;


import by.nc.training.dev3.coffee.entities.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    private static HibernateUtil util;
    private SessionFactory sessionFactory;
    private final ThreadLocal <Session> sessions = new ThreadLocal<Session>();

    private HibernateUtil() {
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        Metadata metadata = new MetadataSources( standardRegistry )
                .addAnnotatedClass(Role.class ).
                        addAnnotatedClass(User.class).
                        addAnnotatedClass(Bill.class).
                        addAnnotatedClass(Beverage.class).
                        addAnnotatedClass(Ingredient.class).
                        addAnnotatedClass(Order.class)

                // You can add more entity classes here like above
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
                .build();

         sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static HibernateUtil getInstance(){
        if (util == null){
            util = new HibernateUtil();
        }
        return util;
    }
    public Session getSession(){
        Session session = sessions.get();
        if(session == null){
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    public void releaseSession(Session session){
        if(session != null){
            try{
                //session.close();
                sessions.remove();
            }
            catch(HibernateException e){
                logger.error(e);
            }
        }
    }


}

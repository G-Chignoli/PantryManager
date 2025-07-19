package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  private static StandardServiceRegistry service_registry;
  private static SessionFactory session_factory;
  private static Configuration configuration;

  public static SessionFactory getSessionFactory() {
	  
      if (session_factory == null) {
          try {
        	  configuration = new Configuration().configure().addAnnotatedClass(controller.Product.class) ;
        	  service_registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build(); 
        	  session_factory = configuration.buildSessionFactory(service_registry);
        	  
          } catch (Exception e) {
              if (service_registry != null) {
                  StandardServiceRegistryBuilder.destroy(service_registry);
              }
          }
      }
      return session_factory;
  }

  public static void shutdown() {
      if (service_registry != null) {
          StandardServiceRegistryBuilder.destroy(service_registry);
      }
  }
}

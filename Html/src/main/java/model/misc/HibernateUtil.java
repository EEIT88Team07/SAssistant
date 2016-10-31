package model.misc;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static StandardServiceRegistry serviceRegistry;
	private static SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory() {
		try {
			serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
			return new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		} catch(Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void closeSessionFactory() {
		if(sessionFactory!=null) {
			sessionFactory.close();
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
}

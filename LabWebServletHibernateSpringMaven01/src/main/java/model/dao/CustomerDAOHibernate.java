package model.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.CustomerBean;
import model.CustomerDAO;

public class CustomerDAOHibernate implements CustomerDAO {
	private SessionFactory ssessionFactory = null;
	public CustomerDAOHibernate(SessionFactory ssessionFactory) {
		this.ssessionFactory = ssessionFactory;
	}
	public Session getSession() {
		return ssessionFactory.getCurrentSession();
	}
	@Override
	public CustomerBean select(String custid) {
		return (CustomerBean)
				this.getSession().get(CustomerBean.class, custid);
	}
	@Override
	public boolean update(byte[] password,
			String email, Date birth, String custid) {
		CustomerBean result = (CustomerBean)
				this.getSession().get(CustomerBean.class, custid);
		if(result!=null) {
			result.setPassword(password);
			result.setEmail(email);
			result.setBirth(birth);
			return true;
		}
		return false;
	}
}

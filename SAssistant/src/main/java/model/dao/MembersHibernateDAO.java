package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.MembersBean;
import model.MembersDAO;

public class MembersHibernateDAO implements MembersDAO {
	private SessionFactory sessionFactory = null;// bean

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public MembersHibernateDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public MembersBean select(String account) {
		return this.getSession().get(MembersBean.class, account);// 一般儲存方法
	}

	@Override
	public MembersBean insert(MembersBean bean) {
		MembersBean result = (MembersBean) this.getSession().get(MembersBean.class, bean.getAccount()); // hibernate要先select
																										// 才能儲存
		if (result == null) {
			this.getSession().save(bean);
		}
		return bean;
	}

	@Override
	public boolean update(String password, String name, Integer gender, String phone, String email, Date birthday,
			String account) {
		MembersBean result = (MembersBean) this.getSession().get(MembersBean.class, account);
		if (result != null) {
			result.setAccount(account);
			result.setBirthday(birthday);
			result.setEmail(email);
			result.setGender(gender);
			result.setName(name);
			result.setPassword(password);
			result.setPhone(phone);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String account) {
		MembersBean result = (MembersBean) this.getSession().get(MembersBean.class, account);
		if (result != null) {
			this.getSession().delete(result);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MembersBean> select() {
		Query query = this.getSession().createQuery("from MembersBean");
		return (List<MembersBean>) query.list();
	}

}

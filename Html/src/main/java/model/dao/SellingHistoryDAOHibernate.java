package model.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.SellingHistoryBean;
import model.SellingHistoryDAO;

public class SellingHistoryDAOHibernate implements SellingHistoryDAO {
	private SessionFactory sessionFactory = null;

	public SellingHistoryDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public SellingHistoryBean select(int sellingNumber) {
		return (SellingHistoryBean) this.getSession().get(SellingHistoryBean.class, sellingNumber);
	}

	@SuppressWarnings("unchecked")
	public List<SellingHistoryBean> select() {
		Query query = this.getSession().createQuery("from SellingHistoryBean");
		return (List<SellingHistoryBean>) query.list();
	}

	public SellingHistoryBean insert(SellingHistoryBean bean) {
		SellingHistoryBean temp = this.getSession().get(SellingHistoryBean.class, bean.getSellingNumber());
		if (temp == null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}

	public SellingHistoryBean update(Double sellingPrice, Double sellingQuantity, Date dateOfSelling,
			Double cost, Double income, Double netIncome, Double netProfitMargin, int sellingNumber) {
		SellingHistoryBean result = (SellingHistoryBean) this.getSession().get(SellingHistoryBean.class, sellingNumber);
		if (result != null) {
			result.setSellingPrice(sellingPrice);
			result.setSellingQuantity(sellingQuantity);
			result.setDateOfSelling(dateOfSelling);
			result.setCost(cost);
			result.setIncome(income);
			result.setNetIncome(netIncome);
			result.setNetProfitMargin(netProfitMargin);
		}
		return result;
	}

	public boolean delete(int sellingNumber) {
		SellingHistoryBean bean = this.getSession().get(SellingHistoryBean.class, sellingNumber);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}
}

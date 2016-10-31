package model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.PurchaseHistoryBean;
import model.PurchaseHistoryBeanDAO;

public class PurchaseHistoryDAOHibernate implements PurchaseHistoryBeanDAO {
	private SessionFactory sessionFactory = null;

	public PurchaseHistoryDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public PurchaseHistoryBean select(PurchaseHistoryBean bean) {
		return (PurchaseHistoryBean) this.getSession().get(PurchaseHistoryBean.class, bean);
	}

	@SuppressWarnings("unchecked")
	public List<PurchaseHistoryBean> select() {
		Query query = this.getSession().createQuery("from PurchaseHistoryBean");
		return (List<PurchaseHistoryBean>) query.list();
	}

	public PurchaseHistoryBean insert(PurchaseHistoryBean bean) {
		PurchaseHistoryBean temp = this.getSession().get(PurchaseHistoryBean.class, bean.getPurchaseNumber());
		if (temp == null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}

	public PurchaseHistoryBean update(PurchaseHistoryBean bean) {
		PurchaseHistoryBean result = (PurchaseHistoryBean) this.getSession().get(PurchaseHistoryBean.class, bean.getPurchaseNumber());
		if (result != null) {
			result.setPurchasePrice(bean.getPurchasePrice());
			result.setPurchaseQuantity(bean.getPurchaseQuantity());
			result.setInvestment(bean.getInvestment());
			result.setStopLossLimit(bean.getStopLossLimit());
			result.setTakeProfitLimit(bean.getTakeProfitLimit());
			result.setDividendYield(bean.getDividendYield());
			result.setStockId(bean.getStockId());
			result.setDateOfPurchase(bean.getDateOfPurchase());
		}
		return result;
	}

	public boolean delete(PurchaseHistoryBean bean) {
		PurchaseHistoryBean temp = this.getSession().get(PurchaseHistoryBean.class, bean.getPurchaseNumber());
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}
}

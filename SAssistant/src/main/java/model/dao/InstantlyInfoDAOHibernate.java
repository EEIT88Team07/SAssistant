package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.InstantlyInfoBean;
import model.InstantlyInfoDAO;

public class InstantlyInfoDAOHibernate implements InstantlyInfoDAO {

	private SessionFactory sessionFactory = null;

	public InstantlyInfoDAOHibernate(SessionFactory sessionfactory) {
		this.sessionFactory = sessionfactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<InstantlyInfoBean> select() {
		Query query = this.getSession().createQuery("from InstantlyInfoBean");
		return (List<InstantlyInfoBean>) query.list();
	}

	public InstantlyInfoBean select(String stockIdName) {
		return (InstantlyInfoBean) this.getSession().get(InstantlyInfoBean.class, stockIdName);
	}

	public InstantlyInfoBean insert(InstantlyInfoBean instantlyInfoBean) {
		InstantlyInfoBean result = (InstantlyInfoBean) this.getSession().get(InstantlyInfoBean.class, instantlyInfoBean.getStockIdName());
		if (result == null) {
			this.getSession().save(instantlyInfoBean);
			return instantlyInfoBean;
		}
		return null;
	}

	public boolean delete(String stockId) {
		InstantlyInfoBean bean = new InstantlyInfoBean();
		bean = (InstantlyInfoBean) this.getSession().get(InstantlyInfoBean.class, stockId);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	public InstantlyInfoBean update(String stockIdName, Date time, String finalPrice, Integer volume, String yestPrice, String buy, String sell, String openPrice, String high, String low) {
		InstantlyInfoBean bean = (InstantlyInfoBean) this.getSession().get(InstantlyInfoBean.class, stockIdName);

		if (bean != null) {
			bean.setStockIdName(stockIdName);
			bean.setTime(time);
			bean.setFinalPrice(finalPrice);
			bean.setVolume(volume);
			bean.setYestPrice(yestPrice);
			bean.setBuy(buy);
			bean.setSell(sell);
			bean.setOpenPrice(openPrice);
			bean.setHigh(high);
			bean.setLow(low);

		}
		return bean;
	}
}

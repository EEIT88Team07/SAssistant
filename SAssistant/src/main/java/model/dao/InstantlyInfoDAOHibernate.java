package model.dao;

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

	public InstantlyInfoBean select(String stockId) {
		return (InstantlyInfoBean) this.getSession().get(InstantlyInfoBean.class, stockId);
	}

	public InstantlyInfoBean insert(InstantlyInfoBean instantlyInfoBean) {
		InstantlyInfoBean result = (InstantlyInfoBean) this.getSession().get(InstantlyInfoBean.class,
				instantlyInfoBean.getStockId());
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

	@Override
	public InstantlyInfoBean update(String stockName, Double finalPrice, Integer temporalVolume, Integer volume,
			String infomationTime, String infomationDate, Double high, Double low, Double openPrice, String a, String f,
			String b, String g, String stockId) {
		InstantlyInfoBean bean = (InstantlyInfoBean) this.getSession().get(InstantlyInfoBean.class, stockId);
		if (bean != null) {
			bean.setStockId(stockId);
			bean.setStockName(stockName);
			bean.setFinalPrice(finalPrice);
			bean.setTemporalVolume(temporalVolume);
			bean.setVolume(volume);
			bean.setInfomationTime(infomationTime);
			bean.setInfomationDate(infomationDate);
			bean.setHigh(high);
			bean.setLow(low);
			bean.setOpenPrice(openPrice);
			bean.setA(a);
			bean.setF(f);
			bean.setB(b);
			bean.setG(g);
		}
		return bean;
	}
}

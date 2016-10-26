package model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.StockInfoBean;
import model.StockInfoDAO;

public class StockInfoDAOHibernate implements StockInfoDAO {
	private SessionFactory sessionFactory = null;

	public StockInfoDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public StockInfoBean select(String stockId) {
		return (StockInfoBean) this.getSession().get(StockInfoBean.class, stockId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockInfoBean> select() {
		Query query = this.getSession().createQuery("from StockInfoBean");
		return (List<StockInfoBean>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> select_All_Id() {
		Query query = this.getSession().createQuery("select stockId from StockInfoBean");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> select_All_Group() {
		Query query = this.getSession().createQuery("select distinct groupName from StockInfoBean");
		return query.list();
	}

	@Override
	public StockInfoBean insert(StockInfoBean bean) {
		StockInfoBean temp = this.getSession().get(StockInfoBean.class, bean.getStockId());
		if(temp == null){
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}

	@Override
	public StockInfoBean update(String stockName, String groupName, String isinCode, String stockId) {
		StockInfoBean temp = this.getSession().get(StockInfoBean.class, stockId);
		if(temp != null){
			temp.setStockName(stockName);
			temp.setGroupName(groupName);
			temp.setIsinCode(isinCode);
		}
		return temp;
	}

	@Override
	public boolean delete(String stockId) {
		StockInfoBean temp = this.getSession().get(StockInfoBean.class, stockId);
		if(temp != null){
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}
}

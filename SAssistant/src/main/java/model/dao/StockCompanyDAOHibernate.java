package model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.StockCompanyBean;
import model.StockCompanyDAO;

public class StockCompanyDAOHibernate implements StockCompanyDAO{
	private SessionFactory sessionFactory = null;
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public StockCompanyDAOHibernate(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	@Override
	public StockCompanyBean select(String stockId) {
		return this.getSession().get(StockCompanyBean.class, stockId);
	}

	@Override
	public StockCompanyBean insert(StockCompanyBean bean) {
		StockCompanyBean result = this.getSession().get(StockCompanyBean.class, bean.getStockId());
		if(result == null){
			this.getSession().save(bean);
		}
		return bean;
	}
	
	@Override
	public StockCompanyBean update(StockCompanyBean bean) {
		StockCompanyBean temp = this.getSession().get(StockCompanyBean.class, bean.getStockId());
		if(temp != null){
			temp.setStockId(bean.getStockId());
			temp.setEstablishDate(bean.getEstablishDate());
			temp.setPublicCompanyDate(bean.getPublicCompanyDate());
			temp.setChairman(bean.getChairman());
			temp.setGeneralManager(bean.getGeneralManager());
			temp.setSpokesman(bean.getSpokesman());
			temp.setCapitalStock(bean.getCapitalStock());
			temp.setCashDividend(bean.getCashDividend());	
			temp.setStockDividend(bean.getStockDividend());	
			temp.setSdre(bean.getSdre());
			temp.setSdcr(bean.getSdcr());
			temp.setSmDate(bean.getSmDate());
			temp.setServiceAgent(bean.getServiceAgent());
			temp.setCompanyPhone(bean.getCompanyPhone());	
			temp.setOfRevenue(bean.getOfRevenue());		
			temp.setUrl(bean.getUrl());
			temp.setFactory(bean.getFactory());			
		}
		return temp;
	}

	@Override
	public boolean delete(String stockId) {
		StockCompanyBean result = this.getSession().get(StockCompanyBean.class, stockId);
		if(result != null){
			this.getSession().delete(stockId);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockCompanyBean> select() {
		Query query = this.getSession().createQuery("from StockCompanyBean");
		return (List<StockCompanyBean>)query.list();
	}
}

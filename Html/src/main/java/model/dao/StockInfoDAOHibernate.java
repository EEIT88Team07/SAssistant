package model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.StockInfoBean;
import model.StockInfoDAO;
import model.StockInfoService;

public class StockInfoDAOHibernate implements StockInfoDAO {
	private SessionFactory sessionFactory = null;
	public StockInfoDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
		
			
			StockInfoDAOHibernate stockInfoDAOHibernate = (StockInfoDAOHibernate) context.getBean("stockInfoDAOHibernate");
			
			System.out.println(stockInfoDAOHibernate.select());
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}
	
	public StockInfoBean select(String stockId) {
		return (StockInfoBean) this.getSession().get(StockInfoBean.class, stockId);
	}

	@SuppressWarnings("unchecked")
	public List<StockInfoBean> select() {
		Query query = this.getSession().createQuery("from StockInfoBean");
		return (List<StockInfoBean>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> select_All_Id() {
		Query query = this.getSession().createQuery("select stockId from StockInfoBean");
		return (List<String>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<String> select_All_Group() {
		Query query = this.getSession().createQuery("select distinct groupName from StockInfoBean");
		return (List<String>) query.list();
	}
	
	public StockInfoBean insert(StockInfoBean bean) {
		StockInfoBean temp = this.getSession().get(StockInfoBean.class, bean.getStockId());
		if (temp == null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}
	
	public StockInfoBean update(String stockName, String groupName, String isinCode, String stockId) {
		StockInfoBean result = (StockInfoBean) this.getSession().get(StockInfoBean.class, stockId);
		if (result != null) {
			result.setStockName(stockName);
			result.setGroupName(groupName);
			result.setIsinCode(isinCode);
		}
		return result;
	}

	public boolean delete(String stockId) {
		StockInfoBean bean = this.getSession().get(StockInfoBean.class, stockId);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}
}

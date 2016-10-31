package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.SellingHistoryBean;
import model.SellingHistoryDAO;

public class SellingHistoryDAOHibernate implements SellingHistoryDAO {
	private SessionFactory sessionFactory = null;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();

			SellingHistoryDAO dao = (SellingHistoryDAO) context.getBean("sellingHistoryDAO");

			SellingHistoryBean bean = new SellingHistoryBean();
			bean.setSellingNumber("2");

//			System.out.println(dao.update("2", dateOfPurchase, sellingPrice, sellingQuantity, dateOfSelling, cost, income, netIncome, netProfitMargin, account, sellingNumber));

			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	public SellingHistoryDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public SellingHistoryBean select(String sellingNumber) {
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

	public SellingHistoryBean update(Double sellingPrice, Double sellingQuantity, Date dateOfSelling, Double cost, Double income, Double netIncome,
			Double netProfitMargin, String purchaseNumber, String sellingNumber) {
		SellingHistoryBean result = (SellingHistoryBean) this.getSession().get(SellingHistoryBean.class, sellingNumber);
		if (result != null) {
			result.setSellingPrice(sellingPrice);
			result.setSellingQuantity(sellingQuantity);
			result.setDateOfSelling(dateOfSelling);
			result.setCost(cost);
			result.setIncome(income);
			result.setNetIncome(netIncome);
			result.setNetProfitMargin(netProfitMargin);
			result.setPurchaseNumber(purchaseNumber);
		}
		return result;
	}

	public boolean delete(String sellingNumber) {
		SellingHistoryBean bean = this.getSession().get(SellingHistoryBean.class, sellingNumber);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}
}

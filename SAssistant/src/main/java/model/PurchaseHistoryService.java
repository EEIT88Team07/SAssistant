package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PurchaseHistoryService {
	private PurchaseHistoryBeanDAO purchaseHistoryBeanDAO = null;

	public PurchaseHistoryService(PurchaseHistoryBeanDAO purchaseHistoryBeanDAO) {
		this.purchaseHistoryBeanDAO = purchaseHistoryBeanDAO;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();

//			PurchaseHistoryService service = (PurchaseHistoryService) context.getBean("purchaseHistoryService");
//			PurchaseHistoryBean bean = new PurchaseHistoryBean();
//			Date date = new Date(1,1,1);
//			bean.setPurchasePrice(132.03);
//			bean.setPurchaseQuantity(12.01);
//			bean.setDateOfPurchase(date);
//			bean.setInvestment(14.01);
//			bean.setStopLossLimit(13.01);
//			bean.setTakeProfitLimit(10.01);
//			bean.setDividendYield(12.01);
//			bean.setStockId("1101");
//			bean.setAccount("2");
//			bean.setPurchaseNumber(2);
//			service.insert(bean);			
//			service.update(bean);			
//			service.delete(bean);			
//			System.out.println(service.select(null));
					
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		} finally{
			((ConfigurableApplicationContext) context).close();
		}
	}

	public List<PurchaseHistoryBean> select(PurchaseHistoryBean bean) {
		List<PurchaseHistoryBean> result = null;
		if (bean != null) {
			PurchaseHistoryBean temp = purchaseHistoryBeanDAO.select(bean);
			if (temp != null) {
				result = new ArrayList<PurchaseHistoryBean>();
				result.add(temp);
			}
		} else {
			result = purchaseHistoryBeanDAO.select();
		}
		return result;
	}

	public PurchaseHistoryBean insert(PurchaseHistoryBean bean) {
		PurchaseHistoryBean result = null;
		if (bean != null) {
			result = purchaseHistoryBeanDAO.insert(bean);
		}
		return result;
	}

	public PurchaseHistoryBean update(PurchaseHistoryBean bean) {
		PurchaseHistoryBean result = null;
		if (bean != null) {
			result = purchaseHistoryBeanDAO.update(bean);
		}
		return result;
	}

	public boolean delete(PurchaseHistoryBean bean) {
		boolean result = false;
		if (bean != null) {
			result = purchaseHistoryBeanDAO.delete(bean);
		}
		return result;
	}
}
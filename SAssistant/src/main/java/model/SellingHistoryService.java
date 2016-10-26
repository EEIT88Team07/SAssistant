package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SellingHistoryService {
	private SellingHistoryDAO sellingHistoryDao = null;

	public SellingHistoryService(SellingHistoryDAO sellingHistoryDao) {
		this.sellingHistoryDao = sellingHistoryDao;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();

			SellingHistoryService service = (SellingHistoryService) context.getBean("sellingHistoryService");
			SellingHistoryBean bean = new SellingHistoryBean();
			
			bean.setSellingNumber(132);
			bean.setCost(12.01);
			bean.setDateOfPurchase(new Date(0));
			bean.setDateOfSelling(new Date(0));
			bean.setIncome(14.01);
			bean.setNetIncome(13.01);
			bean.setNetProfitMargin(14.01);
			bean.setSellingPrice(14.01);
			bean.setSellingQuantity(12.01);
			bean.setStockId("1103");
//			bean.setAccount("1");
//			service.insert(bean);
			
			service.update(bean);
			
//			service.delete(bean);
			
//			System.out.println(service.select(null));
			
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		} finally{
			((ConfigurableApplicationContext) context).close();
		}
	}

	public List<SellingHistoryBean> select(SellingHistoryBean bean) {
		List<SellingHistoryBean> result = null;
		if (bean != null && bean.getSellingNumber() != 0) {
			SellingHistoryBean temp = sellingHistoryDao.select(bean.getSellingNumber());
			if (temp != null) {
				result = new ArrayList<SellingHistoryBean>();
				result.add(temp);
			}
		} else {
			result = sellingHistoryDao.select();
		}
		return result;
	}

	public SellingHistoryBean insert(SellingHistoryBean bean) {
		SellingHistoryBean result = null;
		if (bean != null) {
			result = sellingHistoryDao.insert(bean);
		}
		return result;
	}

	public SellingHistoryBean update(SellingHistoryBean bean) {
		SellingHistoryBean result = null;
		if (bean != null) {
			result = sellingHistoryDao.update(bean.getSellingPrice(), bean.getSellingQuantity(),
					bean.getDateOfSelling(), bean.getCost(), bean.getIncome(), bean.getNetIncome(),
					bean.getNetProfitMargin(), bean.getSellingNumber());
		}
		return result;
	}

	public boolean delete(SellingHistoryBean bean) {
		boolean result = false;
		if (bean != null) {
			result = sellingHistoryDao.delete(bean.getSellingNumber());
		}
		return result;
	}
}
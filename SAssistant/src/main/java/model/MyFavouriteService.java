package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyFavouriteService {
	private MyFavouriteDAO myFavouriteDAO;

	public MyFavouriteService(MyFavouriteDAO myFavouriteDAO) {
		this.myFavouriteDAO = myFavouriteDAO;
	}// spring 控制反轉

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = null;
		try {		
			sessionFactory = (SessionFactory) context.getBean("sessionFactory");
			sessionFactory.getCurrentSession().getTransaction().begin();

//			MyFavouriteService service = (MyFavouriteService) context.getBean("myFavouriteService");
//
//			MyFavouriteBean bean = new MyFavouriteBean();
//			bean.setStockId("1101");
//			bean.setAccount("1");
//			bean.setFinalPrice(11.11);
//			service.insert(bean);
//			service.update(bean);
//			System.out.println(service.select(bean));
//			System.out.println(service.select(null));
//			service.delete(bean);

			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		} finally{
			((ConfigurableApplicationContext) context).close();
		}
	}

	public List<MyFavouriteBean> select(MyFavouriteBean bean) {
		List<MyFavouriteBean> result = null;
		if (bean != null && bean.getAccount().trim() != null) {
			MyFavouriteBean temp = myFavouriteDAO.select(bean);
			if (temp != null) {
				result = new ArrayList<MyFavouriteBean>();
				result.add(temp);
			}
		} else {
			result = myFavouriteDAO.select();
		}
		return result;
	}

	public MyFavouriteBean insert(MyFavouriteBean bean) {
		MyFavouriteBean result = null;
		if (bean != null) {
			result = myFavouriteDAO.insert(bean);
		}
		return result;
	}

	public boolean update(MyFavouriteBean bean) {
		// String password,String name,Integer Gender,String Phone,Integer
		// Permissions,Integer number, String email, java.util.Date birth,
		// String account
		if (bean != null) {
			myFavouriteDAO.update(bean);
			return true;
		}
		return false;
	}

	public boolean delete(MyFavouriteBean bean) {
		boolean result = false;
		if (bean != null) {
			result = myFavouriteDAO.delete(bean);
		}
		return result;
	}
}

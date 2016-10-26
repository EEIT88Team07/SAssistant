package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MembersService {
	private MembersDAO membersDAO;

	public MembersService(MembersDAO membersDAO) {
		this.membersDAO = membersDAO;
	}// spring 控制反轉

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().getTransaction().begin();

			MembersService service = (MembersService) context.getBean("membersService");
			MembersBean bean = new MembersBean();
			bean.setAccount("2");
			bean.setBirthday(new Date(0));
			bean.setEmail("3afasfasfasfafs32f1a");
			bean.setGender(1);
			bean.setName("13535153");
			bean.setPassword("afad3gf132");
			bean.setPhone("0495641321");
			service.insert(bean);
			// service.update(bean);
			// System.out.println(service.select(bean));
			// System.out.println(service.select(null));
			// service.delete("1");

			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}
	
	public MembersBean checkIDPassword(String account, String password) {
		// 透過變數dao，呼叫它的select()方法，要傳入參數 id。將傳回值放入變數
		// MemberBean mb 內。
		MembersBean mb = membersDAO.select(account);
		// 如果mb不等於 null 而且參數 password等於mb內的password) {
		if (mb != null && password.equals(mb.getPassword())) {
			// 傳回 mb物件，同時結束本方法
			return mb;
		}
		// 傳回null物件
		return null;
	}

	public List<MembersBean> select(MembersBean bean) {
		List<MembersBean> result = null;
		if (bean != null && bean.getAccount().trim() != null) {
			MembersBean temp = membersDAO.select(bean.getAccount());
			if (temp != null) {
				result = new ArrayList<MembersBean>();
				result.add(temp);
			}
		} else {
			result = membersDAO.select();
		}
		return result;
	}

	public MembersBean insert(MembersBean bean) {
		MembersBean result = null;
		if (bean != null) {
			result = membersDAO.insert(bean);
		}
		return result;
	}

	public boolean update(MembersBean bean) {
		// String password,String name,Integer Gender,String Phone,Integer
		// Permissions,Integer number, String email, java.util.Date birth,
		// String account
		if (bean != null) {
			membersDAO.update(bean.getPassword(), bean.getName(), bean.getGender(), bean.getPhone(), bean.getEmail(),
					bean.getBirthday(), bean.getAccount());
			return true;
		}
		return false;
	}

	public boolean delete(String account) {
		boolean result = false;
		if (account != null && account.trim() != null) {
			result = membersDAO.delete(account);
		}
		return result;
	}
}

package model.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.GroupInfoBean;
import model.GroupInfoDAO;

public class GroupInfoDAOHibernate implements GroupInfoDAO {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");

		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		GroupInfoDAOHibernate DAO = new GroupInfoDAOHibernate(sessionFactory);

		// DAO.update("1", new Date());

		GroupInfoBean bean = new GroupInfoBean();
		
		bean.setGroupName("test");
		bean.setGroupId("1");
		DAO.insert(bean);

//		DAO.update("1", "tessst", null, null, null, null, null, null, null, null, null, null, null, null, null, null,
//				null);
//
//		DAO.delete("2");
		
		System.out.println(DAO.select());

		sessionFactory.getCurrentSession().beginTransaction().commit();
		((ConfigurableApplicationContext) context).close();

	}
	
	
	
	
	private SessionFactory sessionFactory = null;

	public GroupInfoDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<GroupInfoBean> select() {
		Query query = this.getSession().createQuery("from GroupInfoBean");
		return (List<GroupInfoBean>) query.list();
	}

	public GroupInfoBean select(String groupName) {
		return (GroupInfoBean) this.getSession().get(GroupInfoBean.class, groupName);
	}

	public GroupInfoBean insert(GroupInfoBean groupInfoBean) {
		GroupInfoBean result = (GroupInfoBean) this.getSession().get(GroupInfoBean.class, groupInfoBean.getGroupName());
		if (result == null) {
			this.getSession().save(groupInfoBean);
			return groupInfoBean;
		}
		return null;
	}

	public boolean delete(String groupName) {
		GroupInfoBean bean = new GroupInfoBean();
		bean = (GroupInfoBean) this.getSession().get(GroupInfoBean.class, groupName);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	public GroupInfoBean update(String groupName, String groupId) {
		GroupInfoBean bean = new GroupInfoBean();
		bean = (GroupInfoBean) this.getSession().get(GroupInfoBean.class, groupName);
		if (bean != null) {
			bean.setGroupId(groupId);

		}
		return bean;
	}

}

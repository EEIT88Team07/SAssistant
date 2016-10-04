package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Entity
@Table(name="CUSTOMER")
public class CustomerBean {
	@Id
	private String custid;
	private byte[] password;
	private String email;
	private java.util.Date birth;
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");

		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
//			CustomerBean insert = new CustomerBean();
//			insert.setCustid("Xxx");
//			insert.setPassword("X".getBytes());
//			insert.setEmail("xxx@iii.org.tw");
//			insert.setBirth(new java.util.Date());
//			session.save(insert);
			
			CustomerBean select = (CustomerBean) session.get(CustomerBean.class, "Alex");
			System.out.println("select="+select);
			
//			select.setPassword("O".getBytes());
//			select.setEmail("ooo@iii.org.tw");
//			select.setBirth(new java.util.Date(0));
		
//			session.delete(select);

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}
	@Override
	public String toString() {
		return "CustomerBean [custid=" + custid + ", email=" + email + ", birth=" + birth + "]";
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.util.Date getBirth() {
		return birth;
	}
	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}
}

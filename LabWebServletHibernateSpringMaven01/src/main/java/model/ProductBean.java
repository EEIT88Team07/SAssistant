package model;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Entity
@Table(name="PRODUCT")
public class ProductBean implements Serializable {
	@Id
	private int id;
	private String name;
	private double price;
	private java.util.Date make;
	private int expire;
	@OneToOne(
			mappedBy="product",
			cascade = { CascadeType.REMOVE }
	)
	private DetailBean detail;
	public DetailBean getDetail() {
		return detail;
	}
	public void setDetail(DetailBean detail) {
		this.detail = detail;
	}
	
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
//			ProductBean insert = new ProductBean();
//			insert.setId(100);
//			insert.setName("xxxx");
//			insert.setPrice(200);
//			insert.setMake(new java.util.Date());
//			insert.setExpire(300);
//			session.save(insert);
			
			ProductBean select = (ProductBean) session.get(ProductBean.class, 1);
			System.out.println("select="+select);
			DetailBean detail = select.getDetail();
			System.out.println("detail="+detail);
			byte[] photo = detail.getPhoto();
			if(photo!=null && photo.length!=0) {
				FileOutputStream fos = new FileOutputStream("C:/Users/Student/Desktop/"+detail.getPhotoid()+".png");
				fos.write(photo);
				fos.close();
			}
			
//			select.setName("oooo");
//			select.setPrice(400);
//			select.setMake(new java.util.Date(0));
//			select.setExpire(5000);
		
//			session.delete(select);
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}
	@Transient
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public String toString() {
		String temp = "";
		if(make!=null) {
			temp = sFormat.format(make);
		}
		return "{"+id+":"+name+":"+price+":"+temp+":"+expire+"}";
	}
	@Override
	public boolean equals(Object obj) {
		if(obj!=null && (obj instanceof ProductBean)) {
			ProductBean temp = (ProductBean) obj;
			if(this.id == temp.id) {
				return true;
			}
		}
		return false;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public java.util.Date getMake() {
		return make;
	}
	public void setMake(java.util.Date make) {
		this.make = make;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
}

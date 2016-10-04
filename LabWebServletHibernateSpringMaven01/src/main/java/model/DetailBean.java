package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Entity
@Table(name="DETAIL")
public class DetailBean {
	@Id
	private int photoid;
	private byte[] photo;
	@OneToOne
	@JoinColumn(
			name = "PHOTOID",
			referencedColumnName="ID",
			insertable=false,
			updatable=false
	)
	private ProductBean product;
	public ProductBean getProduct() {
		return product;
	}
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	
	public static void main(String[] args) throws Exception {
		File input = new File("C:/JavaFramework/01.jpg");
		byte[] inputPhoto = new byte[(int) input.length()];
		FileInputStream fis = new FileInputStream(input);
		fis.read(inputPhoto);
		fis.close();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();

//			DetailBean insert = new DetailBean();
//			insert.setPhotoid(100);
//			insert.setPhoto(inputPhoto);
//			session.save(insert);

			DetailBean select = session.get(DetailBean.class, 5);
			System.out.println("select="+select);
			byte[] photo = select.getPhoto();
			if(photo!=null && photo.length!=0) {
				FileOutputStream fos = new FileOutputStream("C:/Users/Student/Desktop/"+select.getPhotoid()+".png");
				fos.write(photo);
				fos.close();
			}
			ProductBean product = select.getProduct();
			System.out.println("product="+product);
			
//			select.setPhoto(inputPhoto);
//			session.delete(select);

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}
	@Override
	public String toString() {
		return "DetailBean [photoid=" + photoid + "]";
	}
	public int getPhotoid() {
		return photoid;
	}
	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}

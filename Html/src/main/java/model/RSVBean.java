package model;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RSVBean implements Serializable {


	public static void main(String[] args) throws Exception {

		ApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("beans.config.xml");

			SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
			sessionFactory.getCurrentSession().beginTransaction();

			Session session = sessionFactory.getCurrentSession();

			RSVBean rSVBean = (RSVBean) session.get(RSVBean.class, "1101");

			System.out.println(rSVBean);

			sessionFactory.getCurrentSession().beginTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private String stockId;
	private String BuildDate;
	private Double rSV;
	private Double k;
	private Double d;

	
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "RSVBean [stockId=" + stockId + ", BuildDate=" + BuildDate + ", rSV=" + rSV + ", k=" + k + ", d=" + d
				+ "]";
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getBuildDate() {
		return BuildDate;
	}

	public void setBuildDate(String buildDate) {
		BuildDate = buildDate;
	}

	public Double getRSV() {
		return rSV;
	}

	public void setRSV(Double rSV) {
		this.rSV = rSV;
	}

	public Double getK() {
		return k;
	}

	public void setK(Double k) {
		this.k = k;
	}

	public Double getD() {
		return d;
	}

	public void setD(Double d) {
		this.d = d;
	}

}

package misc;

import java.util.TimerTask;

import javax.servlet.ServletContextEvent;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.InstantlyInfoService;
import model.StockInfoService;

public class AutoRun extends TimerTask {

	ApplicationContext applicationContext;
	InstantlyInfoService instantlyInfoService;
	StockInfoService stockInfoService;

	public AutoRun(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void run() {

		System.out.println("===========================================");
		SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		stockInfoService = (StockInfoService) applicationContext.getBean("stockInfoService");
		instantlyInfoService = (InstantlyInfoService) applicationContext.getBean("instantlyInfoService");

		instantlyInfoService.updateYahoo(stockInfoService.select_ALL_Id());

		sessionFactory.getCurrentSession().getTransaction().commit();
	}

}

package Listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import misc.AutoRun;

public class TaskManager implements ServletContextListener {

	private Timer timer;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		System.out.println("========================================TaskManager="+context.getBean("dataSource"));
		
		timer = new Timer();
		timer.schedule(new AutoRun(context), 0, 10000);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
	}
}

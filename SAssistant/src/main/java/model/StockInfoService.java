package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StockInfoService {
	private StockInfoDAO stockInfoDAO = null;

	public StockInfoService(StockInfoDAO stockInfoDAO) {
		this.stockInfoDAO = stockInfoDAO;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			StockInfoService service = (StockInfoService) context.getBean("stockInfoService");
//			StockInfoBean bean = new StockInfoBean();
//			bean.setStockId("1101");
//			bean.setStockName("台泥");
//			bean.setGroupName("水泥工業");
//			bean.setIsinCode("TW0001101004");
			service.refreshStockInfo();
//			System.out.println(service.select_ALL_Id());
//			service.update(bean);
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	public void refreshStockInfo() throws IOException {
		String url = "http://isin.twse.com.tw/isin/class_main.jsp?owncode=&stockname=&isincode=&market=1&issuetype=1&industry_code=&Page=1&chklike=Y";
		Elements elements = Jsoup.connect(url).get().select("tr");
		for (int i = 1; i < elements.size(); i++) {
			StockInfoBean bean = new StockInfoBean();
			bean.setStockId(elements.get(i).child(2).text());
			bean.setStockName(elements.get(i).child(3).text());
			bean.setGroupName(elements.get(i).child(6).text());
			bean.setIsinCode(elements.get(i).child(1).text());
			this.insert(bean);
		}
	}

	public List<StockInfoBean> select(StockInfoBean bean) {
		List<StockInfoBean> result = null;
		if (bean != null && bean.getStockId() != null) {
			StockInfoBean temp = stockInfoDAO.select(bean.getStockId());
			if (temp != null) {
				result = new ArrayList<StockInfoBean>();
				result.add(temp);
			}
		} else {
			result = stockInfoDAO.select();
		}
		return result;
	}

	public List<String> select_ALL_Group() {
		return stockInfoDAO.select_All_Group();
	}

	public List<String> select_ALL_Id() {
		return stockInfoDAO.select_All_Id();
	}

	public StockInfoBean insert(StockInfoBean bean) {
		if (bean != null) {
			return stockInfoDAO.insert(bean);
		}
		return null;
	}

	public StockInfoBean update(StockInfoBean bean) {
		if (bean != null) {
			return stockInfoDAO.update(bean.getStockName(), bean.getGroupName(), bean.getIsinCode(), bean.getStockId());
		}
		return null;
	}

	public boolean delete(String stockId) {
		if (stockId != null) {
			return stockInfoDAO.delete(stockId);
		}
		return false;
	}
}

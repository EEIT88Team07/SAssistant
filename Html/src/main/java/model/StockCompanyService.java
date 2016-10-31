package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.misc.CrawlerPack;

public class StockCompanyService {
	private StockCompanyDAO stockCompanyDAO = null;

	public StockCompanyService(StockCompanyDAO stockCompanyDAO) {
		this.stockCompanyDAO = stockCompanyDAO;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().getTransaction().begin();
			StockCompanyService service = (StockCompanyService) context.getBean("stockCompanyService");

			service.updateCrawler();

			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	public List<StockCompanyBean> select(StockCompanyBean bean) {
		List<StockCompanyBean> result = null;
		if (bean != null && bean.getStockId() != null) {
			StockCompanyBean temp = stockCompanyDAO.select(bean.getStockId());
			if (temp != null) {
				result = new ArrayList<StockCompanyBean>();
				result.add(temp);
			}
		} else {
			result = stockCompanyDAO.select();
		}
		return result;
	}

	public StockCompanyBean insert(StockCompanyBean bean) {
		if (bean != null) {
			return stockCompanyDAO.insert(bean);
		}
		return null;
	}

	public StockCompanyBean update(StockCompanyBean bean) {
		if (bean != null) {
			return stockCompanyDAO.update(bean);
		}
		return null;
	}

	public boolean delete(String stockId) {
		if (stockId != null) {
			return stockCompanyDAO.delete(stockId);
		}
		return false;
	}

	public void updateCrawler() {
		Elements elements = null;
		String url = "http://isin.twse.com.tw/isin/class_main.jsp?owncode=&stockname=&isincode=&market=1&issuetype=1&industry_code=&Page=1&chklike=Y";
		try {
			Elements elements1 = Jsoup.connect(url).get().select("tr");
			for (int i = 1; i < elements1.size(); i++) {
				try {
					url = "https://tw.stock.yahoo.com/d/s/company_" + elements1.get(i).child(2).text() + ".html";
					elements = CrawlerPack.start().setRemoteEncoding("Big5_HKSCS").getFromHtml(url)
							.select("table:eq(6)");
					StockCompanyBean bean = new StockCompanyBean();
					bean.setStockId(elements1.get(i).child(2).text());
					bean.setEstablishDate(elements.select("td:eq(1)").get(2).text());
					bean.setPublicCompanyDate(elements.select("td:eq(1)").get(3).text());
					bean.setChairman(elements.select("td:eq(1)").get(4).text());
					bean.setGeneralManager(elements.select("td:eq(1)").get(5).text());
					bean.setSpokesman(elements.select("td:eq(1)").get(6).text());
					bean.setCapitalStock(elements.select("td:eq(1)").get(7).text());
					bean.setServiceAgent(elements.select("td:eq(1)").get(8).text());
					bean.setCompanyPhone(elements.select("td:eq(1)").get(9).text());
					bean.setOfRevenue(elements.select("td:eq(1)").get(10).text());
					bean.setUrl(elements.select("td:eq(1)").get(11).text());
					bean.setFactory(elements.select("td:eq(1)").get(12).text());
					bean.setCashDividend(elements.select("td:eq(3)").get(0).text());
					bean.setStockDividend(elements.select("td:eq(3)").get(1).text());
					bean.setSdre(elements.select("td:eq(3)").get(2).text());
					bean.setSdcr(elements.select("td:eq(3)").get(3).text());
					bean.setSmDate(elements.select("td:eq(3)").get(4).text());
					this.insert(bean);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

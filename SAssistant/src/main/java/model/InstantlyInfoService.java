package model;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.hibernate.SessionFactory;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.misc.CrawlerPack;

public class InstantlyInfoService {
	private InstantlyInfoDAO instantlyInfoDAO;

	public InstantlyInfoService(InstantlyInfoDAO instantlyInfoDAO) {
		this.instantlyInfoDAO = instantlyInfoDAO;
	}

	public static void main(String[] args) throws HttpException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			InstantlyInfoService instantlyInfoService = (InstantlyInfoService) context.getBean("instantlyInfoService");
			StockInfoService stockInfoService = (StockInfoService) context.getBean("stockInfoService");

			instantlyInfoService.updateYahoo(stockInfoService.select_ALL_Id());

			// instantlyInfoBean.setStockId("1101");

			// System.out.println(instantlyInfoService.select(instantlyInfoBean));

			// InstantlyInfoBean bean = new InstantlyInfoBean();
			// bean.setStockId("1101");
			// System.out.println(instantlyInfoService.select(bean));

			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			sessionFactory.getCurrentSession().getTransaction().rollback();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	public void updateYahoo(List<String> stockIds) {

		List<String> stocks = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			stocks.add(stockIds.get(i));
		}

		Collections.sort(stocks);

		for (int i = 0; i < stocks.size(); i++) {

			String url = "https://tw.stock.yahoo.com/q/q?s=" + stocks.get(i);
			Elements elements = CrawlerPack.start().setRemoteEncoding("Big5_HKSCS").getFromHtml(url).select("table:eq(0)");
			Node node = elements.get(4).childNode(1).childNode(2);

			String stockIdName = node.childNode(1).childNode(0).childNode(0).toString();

			if (stockIdName != null && stockIdName.trim().length() != 0) {

				InstantlyInfoBean test = instantlyInfoDAO.select(stockIdName);

				if (test != null) {
					String finalPrice = node.childNode(5).childNode(0).childNode(0).toString();
					Integer volume = 0;
					try {
						volume = Integer.parseInt(node.childNode(12).childNode(0).toString());
					} catch (NumberFormatException e) {

						volume = 0;
					}
					String yestPrice = node.childNode(14).childNode(0).toString();
					String buy = node.childNode(7).childNode(0).toString();
					String sell = node.childNode(9).childNode(0).toString();
					String openPrice = node.childNode(16).childNode(0).toString();
					String high = node.childNode(18).childNode(0).toString();
					String low = node.childNode(20).childNode(0).toString();

					instantlyInfoDAO.update(stockIdName, new Date(), finalPrice, volume, yestPrice, buy, sell, openPrice, high, low);
				}

				if (test == null) {

					InstantlyInfoBean instantlyInfoBean = new InstantlyInfoBean();
					instantlyInfoBean.setStockIdName(stockIdName);
					instantlyInfoBean.setFinalPrice(node.childNode(5).childNode(0).childNode(0).toString());
					try {
						instantlyInfoBean.setVolume(Integer.parseInt(node.childNode(12).childNode(0).toString()));
					} catch (NumberFormatException e) {

						instantlyInfoBean.setVolume(0);
					}
					instantlyInfoBean.setYestPrice(node.childNode(14).childNode(0).toString());
					instantlyInfoBean.setBuy(node.childNode(7).childNode(0).toString());
					instantlyInfoBean.setSell(node.childNode(9).childNode(0).toString());
					instantlyInfoBean.setOpenPrice(node.childNode(16).childNode(0).toString());
					instantlyInfoBean.setHigh(node.childNode(18).childNode(0).toString());
					instantlyInfoBean.setLow(node.childNode(20).childNode(0).toString());
					instantlyInfoBean.setTime(new Date());
					instantlyInfoDAO.insert(instantlyInfoBean);
				}
			}
		}
	}

	public List<InstantlyInfoBean> select(InstantlyInfoBean bean) {
		List<InstantlyInfoBean> result = null;
		if (bean != null && bean.getStockIdName() != null) {
			InstantlyInfoBean temp = instantlyInfoDAO.select(bean.getStockIdName());
			if (temp != null) {
				result = new ArrayList<InstantlyInfoBean>();
				result.add(temp);
			}
		} else {
			result = instantlyInfoDAO.select();
		}
		return result;
	}

	public InstantlyInfoBean insert(InstantlyInfoBean bean) {
		InstantlyInfoBean result = null;
		if (bean != null) {
			result = instantlyInfoDAO.insert(bean);
		}
		return result;
	}

	public InstantlyInfoBean update(InstantlyInfoBean bean) {
		if (bean != null) {
			return instantlyInfoDAO.update(bean.getStockIdName(), bean.getTime(), bean.getFinalPrice(), bean.getVolume(), bean.getYestPrice(), bean.getBuy(), bean.getSell(), bean.getOpenPrice(),
					bean.getHigh(), bean.getLow());
		}
		return null;
	}

	public boolean delete(String stockId) {
		if (stockId != null) {
			return instantlyInfoDAO.delete(stockId);
		}
		return false;
	}

}

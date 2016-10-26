package model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InstantlyInfoService {

	public static void main(String[] args) throws HttpException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			InstantlyInfoService instantlyInfoService = (InstantlyInfoService) context.getBean("instantlyInfoService");

			instantlyInfoService.insertData();
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

	public void insertData() {
		HttpClient client = null;
		HttpMethod method = null;
		JSONObject msgArray = null;
		String url = null;
		Elements elements = null;
		Date date = new Date();
		Date fdate = new Date();
		try {
			url = "http://isin.twse.com.tw/isin/class_main.jsp?owncode=&stockname=&isincode=&market=1&issuetype=1&industry_code=&Page=1&chklike=Y";
			elements = Jsoup.connect(url).get().select("tr");

			for (int i = 1; i < elements.size(); i++) {
				url = "http://mis.twse.com.tw/stock/fibest.jsp?stock=" + elements.get(i).child(2).text()
						+ "&lang=zh-TW";
				client = new HttpClient();
				client.executeMethod(new GetMethod(url));
				try {
					url = "http://mis.twse.com.tw/stock/api/getStockInfo.jsp?json=1&delay=0&ex_ch=tse_"
							+ elements.get(i).child(2).text() + ".tw&_=" + date.getTime();
					System.out.println(url);
					method = new GetMethod(url);
					method.getParams().setContentCharset("utf-8");
					client.executeMethod(method);
					msgArray = (JSONObject) new JSONObject(method.getResponseBodyAsString()).getJSONArray("msgArray")
							.get(0);

					InstantlyInfoBean instantlyInfoBean = new InstantlyInfoBean();
					instantlyInfoBean.setStockId(((String) msgArray.get("ch")).substring(0, 4));
					instantlyInfoBean.setStockName((String) msgArray.get("n"));
					try {
						instantlyInfoBean.setFinalPrice(Double.parseDouble((String) msgArray.get("z")));
					} catch (Exception e) {
						instantlyInfoBean.setFinalPrice(0.0);
					}
					try {
						instantlyInfoBean.setTemporalVolume(Integer.parseInt((String) msgArray.get("tv")));
					} catch (Exception e) {
						instantlyInfoBean.setTemporalVolume(0);
					}
					try {
						instantlyInfoBean.setVolume(Integer.parseInt((String) msgArray.get("v")));
					} catch (Exception e) {
						instantlyInfoBean.setVolume(0);
					}
					try {
						fdate.setTime(Long.parseLong((String) msgArray.get("tlong")));
					} catch (Exception e) {
						fdate.setTime(0);
					}
					try {
						instantlyInfoBean.setInfomationTime(new SimpleDateFormat("HH:mm:ss").format(fdate));
					} catch (Exception e) {
						instantlyInfoBean.setInfomationTime(null);
					}
					try {
						instantlyInfoBean.setInfomationDate(new SimpleDateFormat("yyyy-MM-dd").format(fdate));
					} catch (Exception e) {
						instantlyInfoBean.setInfomationDate(null);
					}
					try {
						instantlyInfoBean.setHigh(Double.parseDouble((String) msgArray.get("h")));
					} catch (Exception e) {
						instantlyInfoBean.setHigh(0.0);
					}
					try {
						instantlyInfoBean.setLow(Double.parseDouble((String) msgArray.get("l")));
					} catch (Exception e) {
						instantlyInfoBean.setLow(0.0);
					}
					try {
						instantlyInfoBean.setOpenPrice(Double.parseDouble((String) msgArray.get("o")));
					} catch (Exception e) {
						instantlyInfoBean.setOpenPrice(0.0);
					}
					instantlyInfoBean.setA((String) msgArray.get("a"));
					instantlyInfoBean.setF((String) msgArray.get("f"));
					instantlyInfoBean.setB((String) msgArray.get("b"));
					instantlyInfoBean.setG((String) msgArray.get("g"));

					this.update(instantlyInfoBean);
					System.out.println(elements.get(i).child(2).text());	
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						System.out.println(e);
					}
				} catch (JSONException e) {
					System.out.println(e);
				} finally {
					method.releaseConnection();
				}
			}
		} catch (HttpException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public List<InstantlyInfoBean> select(InstantlyInfoBean bean) {
		List<InstantlyInfoBean> result = null;
		if (bean != null && bean.getStockId() != null) {
			InstantlyInfoBean temp = instantlyInfoDAO.select(bean.getStockId());
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
			return instantlyInfoDAO.update(bean.getStockName(), bean.getFinalPrice(), bean.getTemporalVolume(),
					bean.getVolume(), bean.getInfomationTime(), bean.getInfomationDate(), bean.getHigh(), bean.getLow(),
					bean.getOpenPrice(), bean.getA(), bean.getF(), bean.getB(), bean.getG(), bean.getStockId());
		}
		return null;
	}

	public boolean delete(String stockId) {
		if (stockId != null) {
			return instantlyInfoDAO.delete(stockId);
		}
		return false;
	}

	private InstantlyInfoDAO instantlyInfoDAO;

	public InstantlyInfoService(InstantlyInfoDAO instantlyInfoDAO) {
		this.instantlyInfoDAO = instantlyInfoDAO;
	}

}

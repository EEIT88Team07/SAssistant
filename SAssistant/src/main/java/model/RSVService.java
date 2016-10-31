package model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RSVService {

	public static void main(String[] args) throws Exception {

//		Calendar c = Calendar.getInstance();
//
//		c.setTime(new Date("2016/07/21"));

//		for (int i = 1; i <= 90; i++) {
//			c.add(Calendar.DATE, 1);

			ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
			try {		
				SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
				sessionFactory.getCurrentSession().beginTransaction();

				RSVService rSVService = (RSVService) context.getBean("rSVService");
				DataAnalysisService dataAnalysisService = (DataAnalysisService) context.getBean("dataAnalysisService");
				StockInfoService stockInfoService = (StockInfoService) context.getBean("stockInfoService");

				
				System.out.println(rSVService.selectD("1101", new Date("2016/10/21")));
				
//				rSVService.insert(c.getTime(), dataAnalysisService);

				sessionFactory.getCurrentSession().getTransaction().commit();
			} finally {
				((ConfigurableApplicationContext) context).close();
			}
		}
//	}

	private RSVDAO rSVDAO;

	public RSVService(RSVDAO rSVDAO) {
		this.rSVDAO = rSVDAO;
	}

	public Double selectK(String stockId, Date date) {

		String sql = "select top 2 K from RSV R where R.stockId ='" + stockId + "' AND R.buildDate <='"
				+ new SimpleDateFormat("yyyy/MM/dd").format(date) + "' order by R.buildDate desc";
		List<Object> ks = rSVDAO.selectBySQL(sql);
		Double k = null;
		if (ks.size() == 2) {
			k = (Double) ks.get(1);
		}
		return k;
	}

	public Double selectD(String stockId, Date date) {

		String sql = "select top 2 D from RSV R where R.stockId ='" + stockId + "' AND R.buildDate <='"
				+ new SimpleDateFormat("yyyy/MM/dd").format(date) + "' order by R.buildDate desc";
		List<Object> ds = rSVDAO.selectBySQL(sql);
		Double d = null;
		if (ds.size() == 2) {
			d = (Double) ds.get(1);
		}
		return d;
	}

	public void insert(Date date, DataAnalysisService dataAnalysisService) {

		DecimalFormat df = new DecimalFormat("##.00");
		DateFormat d = new SimpleDateFormat("yyyy/MM/dd");

		// RSV(今日收盤價-最近9天最低價)/(最近9天最高價-最近9天最低價)
		double valueRsv = 0.0;
		double valueK = 0.0;
		double valueD = 0.0;
		List<DataAnalysisBean> databeans = dataAnalysisService.selectByFilter(null, date, date);

		// 跑每一筆股票
		if (databeans.size() != 0) {
			for (int j = 0; j < databeans.size(); j++) {
				String stockId = databeans.get(j).getStockId();
				double closingPrice = 0.0;
				double min = 0.0;
				double max = 0.0;
				if (databeans.size() != 0) {
					DataAnalysisBean databean = databeans.get(j);
					if (databean.getClosingPrice() != null) {
						closingPrice = databean.getClosingPrice();
					}

					HashMap<String, Double> values = dataAnalysisService.getValues(stockId, date);
					if (values != null) {
						if (values.get("min") != null) {
							min = values.get("min");
						}
						if (values.get("max") != null) {
							max = values.get("max");
						}
					}
					if (max != min) {
						valueRsv = 100 * (closingPrice - min) / (max - min);
					} else {
						valueRsv = 0;
					}

					if (this.selectK(stockId, date) != null) {
						valueK = this.selectK(stockId, date) * 2 / 3 + valueRsv * 1 / 3;
					} else {
						valueK = 50.0;
					}
					if (this.selectD(stockId, date) != null) {
						valueD = this.selectD(stockId, date) * 2 / 3 + valueK * 1 / 3;
					} else {
						valueD = 50.0;
					}
					RSVBean rSVBean = new RSVBean();
					rSVBean.setStockId(stockId);
					rSVBean.setBuildDate(d.format(date));
					rSVBean.setRSV(Double.parseDouble(df.format(valueRsv)));
					rSVBean.setK(Double.parseDouble(df.format(valueK)));
					rSVBean.setD(Double.parseDouble(df.format(valueD)));
					rSVDAO.insert(rSVBean);

				}
			}
		}
	}
}

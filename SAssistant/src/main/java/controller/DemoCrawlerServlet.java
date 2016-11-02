package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.DataAnalysisBean;
import model.DataAnalysisDAO;
import model.DataAnalysisService;
import model.GroupInfoService;
import model.StockInfoService;
import model.dao.DataAnalysisDAOHibernate;

/**
 * Servlet implementation class DataAnalysisBean
 */
@WebServlet("/demoCrawler.controller")
public class DemoCrawlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataAnalysisService dataAnalysisService;
	private GroupInfoService groupInfoService;
	private StockInfoService stockInfoService;

	// springinit直接拿取
	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		dataAnalysisService = (DataAnalysisService) context.getBean("dataAnalysisService");
		groupInfoService = (GroupInfoService) context.getBean("groupInfoService");
		stockInfoService = (StockInfoService) context.getBean("stockInfoService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收請求,內容標題設為UTF8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收資料,發出請求時尋找name名稱存入string

		String temp1 = request.getParameter("days");

		int days = Integer.parseInt(temp1);

		Calendar calendar = Calendar.getInstance();

		for (int i = 1; i <= days; i++) {
			calendar.add(Calendar.DATE, -1);
			dataAnalysisService.Crawler(calendar.getTime(), groupInfoService.getGroupInfos(stockInfoService.select(null)), groupInfoService.select(null));

		}

		request.getRequestDispatcher("/pages/investment/stockinquiries.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}

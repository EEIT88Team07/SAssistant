package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.dao.DataAnalysisDAOHibernate;

/**
 * Servlet implementation class DataAnalysisBean
 */
@WebServlet("/dataAnalysis.controller")
public class DataAnalysisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataAnalysisService dataAnalysisService;
	private	GroupInfoService groupInfoService;
	//springinit直接拿取
	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		dataAnalysisService = (DataAnalysisService) context.getBean("dataAnalysisService");
		groupInfoService = (GroupInfoService)context.getBean("groupInfoService");
	}
	//判斷日期格式
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收請求,內容標題設為UTF8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收資料,發出請求時尋找name名稱存入string
		 
		String temp1 = request.getParameter("selectstockid");
		String temp2 = request.getParameter("startDate");
		String temp3 = request.getParameter("endDate");
		String datanysis = request.getParameter("datanysis");
		
		//轉換資料
		
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		String stockId = null;
		if (temp1 != null && temp1.trim().length() != 0 ) {
			try {
				stockId = temp1.substring(0,4);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("stockid", "Id必須是為四個數字");
			}
		}
		//判斷data日期邏輯
		Date stratDate = new Date();
		
			if (temp2 == "") {
				stratDate = null;
			} else if (temp2 != null &&temp2.trim().length()!= 0) {
				try {
					stratDate = sFormat.parse(temp2);
				} catch (Exception e) {
					e.printStackTrace();
					errors.put("stratDate", "stratDate必須是日期:yyyy-MM-dd");
				}
			}
		
		Date endDate = null;
		if (temp3 != null && temp3.trim().length() != 0) {
			try {
				endDate = sFormat.parse(temp3);
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("endDate", "endDate必須是日期:yyyy-MM-dd");
			}
		}
		
		
		
		//驗證資料,如果有錯誤資料不做select方法
		 
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"/pages/investment/stockinquiries.jsp").forward(request, response);
			return;
		}
		
		// 呼叫Model, 根據Model執行結果顯示View
		 
		List<DataAnalysisBean> result = null;
		if ("讀取資料".equals(datanysis)) {
			result = dataAnalysisService.selectByFilter(stockId, stratDate, endDate);
			request.setAttribute("select", result);
		}
		request.setAttribute("groups", groupInfoService.select(null));
		// 跳轉到資料 dataAnalysis.jsp
		 
		request.getRequestDispatcher("/pages/investment/stockinquiries.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

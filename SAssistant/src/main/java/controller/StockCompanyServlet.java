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

import model.StockCompanyBean;
import model.StockCompanyService;
import model.dao.DataAnalysisDAOHibernate;

/**
 * Servlet implementation class DataAnalysisBean
 */
@WebServlet("/stockCompany.controller")
public class StockCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private StockCompanyService stockCompanyService ;
	@Override
	public void init() throws ServletException {
		ApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		stockCompanyService = (StockCompanyService) context.getBean("stockCompanyService");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.setContentType("text/plain; charset=UTF-8");
//		PrintWriter out = response.getWriter();
		/*
		 * 接收資料
		 */
		String temp1 = request.getParameter("selectstockid");
		String datanysis = request.getParameter("datanysis");
		
		
		/*
		 * 轉換資料
		 */
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		StockCompanyBean bean = new StockCompanyBean();
		if(temp1!=null && temp1.trim().length()!=0 ) {
			
				bean.setStockId(temp1.substring(0,4));
			} else {
				errors.put("stockId", "Id必須是為四個數字");
			}
		
		/*
		 * 呼叫Model, 根據Model執行結果顯示View
		 */
		//驗證資料,如果有錯誤資料不做select方法
				if(errors!=null && !errors.isEmpty()) {
					request.getRequestDispatcher(
							"/pages/investment/company.jsp").forward(request, response);
					return;
		}
				
		List<StockCompanyBean> result = null;
		if("Select".equals(datanysis)) {
			result = stockCompanyService.select(bean);
			if(result == null){
				errors.put("stockId", "無此股票代碼");
				request.getRequestDispatcher(
						"/pages/investment/company.jsp").forward(request, response);
				return;
			}else{
				request.setAttribute("select",result);
			}
		}		
		/*
		 * 跳轉到資料 dataAnalysis.jsp
		 */
		request.getRequestDispatcher("/pages/investment/company.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}

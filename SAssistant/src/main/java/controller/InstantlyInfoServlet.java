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

import model.InstantlyInfoBean;
import model.InstantlyInfoService;

/**
 * Servlet implementation class DataAnalysisBean
 */
@WebServlet("/instantlyInfo.controller")
public class InstantlyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private InstantlyInfoService instantlyInfoService ;
	@Override
	public void init() throws ServletException {
		ApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		instantlyInfoService = (InstantlyInfoService) context.getBean("instantlyInfoService");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.setContentType("text/plain; charset=UTF-8");
//		PrintWriter out = response.getWriter();
		/*
		 * 接收資料
		 */
		String temp1 = request.getParameter("stockId");
		String datanysis = request.getParameter("datanysis");
		
		
		/*
		 * 轉換資料
		 */
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		InstantlyInfoBean bean = new InstantlyInfoBean();
		if(temp1!=null && temp1.trim().length()!=0 && temp1.trim().length()==4) {
				bean.setStockId(temp1);
			} else {
				errors.put("stockId", "Id必須是為四個數字");
			}
				
		
//		DataAnalysisBean bean = new DataAnalysisBean();
//		bean.setStockId(stockId);
//		bean.setBuildDate(buildDate);
		
		/*
		 * 呼叫Model, 根據Model執行結果顯示View
		 */
		List<InstantlyInfoBean> result = null;
		if("Select".equals(datanysis)) {
			result = instantlyInfoService.select(bean);
			if(result == null){
				errors.put("stockId", "無此股票代碼");
			}else{
				request.setAttribute("select",result);
			}		
		} 
		/*
		 * 轉換json格式可以使用
		 */
//		JsonArrayBuilder builder = Json.createArrayBuilder();
//		DataAnalysisBean data = result.get(0);
//		JsonObject obj2 = Json.createObjectBuilder()
//				.add("stockId", data.getStockId())
//				.add("buildDate", data.getBuildDate())
//				.add("openPrice", data.getOpenPrice())
//				.add("closingPrice", data.getClosingPrice()).build();
//		builder.add(obj2);
//		out.write(builder.build().toString());
//		out.close();
		
//		request.getRequestDispatcher("/dataAnalysis.controller").forward(request, response);
		
		/*
		 * 跳轉到資料 dataAnalysis.jsp
		 */
		request.getRequestDispatcher("/instantlyInfo.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}

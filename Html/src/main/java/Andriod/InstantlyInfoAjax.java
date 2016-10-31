package Andriod;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.DataAnalysisBean;
import model.DataAnalysisService;
import model.InstantlyInfoBean;
import model.InstantlyInfoService;
import model.MembersBean;
import model.MyFavouriteBean;

/**
 * Servlet implementation class InstantlyInfoAjax
 */
@WebServlet("/InstantlyInfoAjax.do")
public class InstantlyInfoAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InstantlyInfoService instantlyInfoService;

	// init取得dataAnalysisService 在servlet關掉時traction
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		instantlyInfoService = (InstantlyInfoService) context.getBean("instantlyInfoService");

	}
    public InstantlyInfoAjax() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getMethod();
		String action = request.getParameter("action");
		System.out.println("action="+action+", method="+method+", "+request.getRequestURI());
//			if(action!=null && action.equals("groupname")) {
			this.getall(request, response);
//			} else if(action!=null && action.equals("stockids")) {
//				this.writeJsonOutstockid(request, response);
//			} else {
//				throw new ServletException("使用GET呼叫必須輸入action參數值：groupname, stockid");
//			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void getall(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		List<InstantlyInfoBean> list= instantlyInfoService.select(null);
		System.out.println(list.get(0).getStockId());
	
		JsonArrayBuilder jsonArray_builder = Json.createArrayBuilder();
		JsonObject jsonobj =null;
			for (int j = 0; j < list.size(); j++) {
			jsonobj = Json.createObjectBuilder().add(
						list.get(j).getStockId(), 
						Json.createObjectBuilder()
						.add("stockName", list.get(j).getStockName())
						.add("finalPrice", list.get(j).getFinalPrice())
						.add("temporalVolume", list.get(j).getTemporalVolume())
						.add("volume", list.get(j).getVolume())
						.add("infomationTime", list.get(j).getInfomationTime())
						.add("infomationDate", list.get(j).getInfomationDate())
						.add("high", list.get(j).getHigh())
						.add("low", list.get(j).getLow())
						.add("openPrice", list.get(j).getOpenPrice())).build();
						jsonArray_builder.add(jsonobj);
			}
		out.write(jsonArray_builder.build().toString());
		out.close();
		return;
	}
}

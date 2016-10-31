package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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
import model.MembersBean;
import model.MembersService;
import model.MyFavouriteBean;
import model.MyFavouriteService;

@WebServlet("/myfavouriteajax.view")
public class MyFavouriteAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MembersService membersService;
	DataAnalysisService dataAnalysisService;
	MyFavouriteService myFavouriteService;
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		dataAnalysisService = (DataAnalysisService) context.getBean("dataAnalysisService");
		membersService = (MembersService) context.getBean("membersService");
		myFavouriteService = (MyFavouriteService) context.getBean("myFavouriteService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getMethod();
		String action = request.getParameter("action");
		
		System.out.println("action=" + action + ", method=" + method + ", " + request.getRequestURI());

		if(action!=null && action.equals("onload")) {
			this.onload(request, response);
		} else if(action!=null && action.equals("getmyfav")) {
			this.getMyFav(request, response);
		} else if(action!=null && action.equals("addfavorite") ){
			this.addfavorite(request, response);
		} else if(action!=null && action.equals("unfavorite")){
			this.unfavorite(request, response);
		} else if(action!=null && action.equals("checkfavorite")){
			this.checkfavorite(request, response);
		}
	}

	private void onload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain; charset=UTF-8");

		int num = -5;

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");
		MembersBean membersBean = new MembersBean();
		membersBean.setAccount(mb.getAccount());
		
		membersBean = membersService.select(membersBean).get(0);
		Iterator<MyFavouriteBean> myFavBeans = membersBean.getMyFavourite().iterator();

		List<String> myFavs = new ArrayList<String>();
		while (myFavBeans.hasNext()) {
			String s =myFavBeans.next().getStockId();
			myFavs.add(s);
		}
		Collections.sort(myFavs);
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DATE, num);
		
		JsonArrayBuilder jsonArray_builder = Json.createArrayBuilder();
		for (int i = 0; i < myFavs.size(); i++) {
			List<DataAnalysisBean> databeans = dataAnalysisService.selectByFilter(myFavs.get(i), calendar.getTime(),
					new Date());
			JsonObject json_databean = null;
			for (int j = 0; j < databeans.size(); j++) {
				json_databean = Json.createObjectBuilder().add("stockId", databeans.get(j).getStockId())
						.add("buildDate", databeans.get(j).getBuildDate())
						.add("openPrice", databeans.get(j).getOpenPrice())
						.add("closingPrice", databeans.get(j).getClosingPrice())
						.add("turnOverInValue", databeans.get(j).getTurnOverInValue())
						.add("changeInPrice", databeans.get(j).getChangeInPrice())
						.add("tradingVolume", databeans.get(j).getTradingVolume())
						.add("numberOfTransactions", databeans.get(j).getNumberOfTransactions())
						.add("highestPrice", databeans.get(j).getHighestPrice())
						.add("lowestPrice", databeans.get(j).getLowestPrice()).build();
				jsonArray_builder.add(json_databean);
			}
		}

		JsonArray jsonArray = jsonArray_builder.build();
		JsonObject jsonobj = Json.createObjectBuilder().add("data", jsonArray).build();


		out.write(jsonobj.toString());
		out.close();
		return;

	}
	private void getMyFav(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收資料,發出請求時尋找name名稱存入string
		PrintWriter out = response.getWriter();
		String temp1 = request.getParameter("selectstockid");
		String temp2 = request.getParameter("startDate");
		String temp3 = request.getParameter("endDate");
		String datanysis = request.getParameter("datanysis");
		

		// 轉換資料

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		String stockId = null;
		if (temp1 != null && temp1.trim().length() != 0) {
			try {
				stockId = temp1.substring(0, 4);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("stockid", "Id必須是為四個數字");
			}
		}
		// 判斷data日期邏輯
		Date stratDate = new Date();

		if (temp2 == "") {
			stratDate = null;
		} else if (temp2 != null && temp2.trim().length() != 0) {
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

		// 驗證資料,如果有錯誤資料不做select方法

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/info1.jsp").forward(request, response);
			return;
		}

		// 呼叫Model, 根據Model執行結果顯示View

		List<DataAnalysisBean> result = null;
		if ("讀取資料".equals(datanysis)) {
			result = dataAnalysisService.selectByFilter(stockId, stratDate, endDate);

		}

		
		JsonArrayBuilder jsonArray_builder = Json.createArrayBuilder();

		JsonObject json_databean = null;
		for (int j = 0; j < result.size(); j++) {
			json_databean = Json.createObjectBuilder().add("stockId", result.get(j).getStockId())
					.add("buildDate", result.get(j).getBuildDate()).add("openPrice", result.get(j).getOpenPrice())
					.add("closingPrice", result.get(j).getClosingPrice())
					.add("turnOverInValue", result.get(j).getTurnOverInValue())
					.add("changeInPrice", result.get(j).getChangeInPrice())
					.add("tradingVolume", result.get(j).getTradingVolume())
					.add("numberOfTransactions", result.get(j).getNumberOfTransactions())
					.add("highestPrice", result.get(j).getHighestPrice())
					.add("lowestPrice", result.get(j).getLowestPrice()).build();
			jsonArray_builder.add(json_databean);
		}

		JsonArray jsonArray = jsonArray_builder.build();
		JsonObject jsonObject = Json.createObjectBuilder().add("data", jsonArray).build();
		// 跳轉到資料 dataAnalysis.jsp

		
		
		out.write(jsonObject.toString());
		out.close();
		return;

	}
	private void checkfavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain; charset=UTF-8");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String temp = request.getParameter("id");
		HttpSession session = request.getSession();
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");
		MembersBean membersBean = new MembersBean();
		membersBean.setAccount(mb.getAccount());
		
		membersBean = membersService.select(membersBean).get(0);
		Iterator<MyFavouriteBean> myFavBeans = membersBean.getMyFavourite().iterator();
		JsonArrayBuilder jsonArray_builder = Json.createArrayBuilder();
		
		while (myFavBeans.hasNext()) {
			JsonObject json_databean = null;
			String stockId =myFavBeans.next().getStockId();
			json_databean = Json.createObjectBuilder().add("stockId", stockId)
					.build();
			jsonArray_builder.add(json_databean);
		}
		
		
		JsonArray jsonArray = jsonArray_builder.build();
		//前面在加data
		JsonObject jsonobj = Json.createObjectBuilder().add("data", jsonArray).build();
		out.write(jsonobj.toString());
		out.close();
		return;
	}
	private void addfavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain; charset=UTF-8");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收資料,發出請求時尋找name名稱存入string
		PrintWriter out = response.getWriter();
		
		String temp = request.getParameter("id");
	
		HttpSession session = request.getSession();
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");
		MembersBean membersBean = new MembersBean();
		membersBean.setAccount(mb.getAccount());
		
		membersBean = membersService.select(membersBean).get(0);
		Iterator<MyFavouriteBean> myFavBeans = membersBean.getMyFavourite().iterator();
		JsonArrayBuilder jsonArray_builder = Json.createArrayBuilder();
		
		/*
		 * 塞入ID jsoncreateobject
		 */
		while (myFavBeans.hasNext()) {
			JsonObject json_databean = null;
			String stockId =myFavBeans.next().getStockId();
			json_databean = Json.createObjectBuilder().add("stockId", stockId)
					.build();
			jsonArray_builder.add(json_databean);
		}
		
		
		JsonObject json_databean = null;
		MyFavouriteBean bean = new MyFavouriteBean();
		
		bean.setAccount(mb.getAccount());
		bean.setStockId(temp);
		myFavouriteService.insert(bean);
		
		
		json_databean=Json.createObjectBuilder().add("stockId", temp)
				.build();
		jsonArray_builder.add(json_databean);
		
		
		JsonArray jsonArray = jsonArray_builder.build();
		//前面在加data
		JsonObject jsonobj = Json.createObjectBuilder().add("data", jsonArray).build();

		
		out.write(jsonobj.toString());
		out.close();
		return;

	}
	private void unfavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain; charset=UTF-8");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收資料,發出請求時尋找name名稱存入string
		PrintWriter out = response.getWriter();
		
		String temp = request.getParameter("id");
		
		HttpSession session = request.getSession();
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");
		MembersBean membersBean = new MembersBean();
		membersBean.setAccount(mb.getAccount());
		
		membersBean = membersService.select(membersBean).get(0);
		Iterator<MyFavouriteBean> myFavBeans = membersBean.getMyFavourite().iterator();
		JsonArrayBuilder jsonArray_builder = Json.createArrayBuilder();
		
		MyFavouriteBean bean = new MyFavouriteBean();
		bean.setAccount(mb.getAccount());
		bean.setStockId(temp);
		myFavouriteService.delete(bean);
		/*
		 * 塞入ID jsoncreateobject
		 */
		while (myFavBeans.hasNext()) {
			JsonObject json_databean = null;
			String stockId =myFavBeans.next().getStockId();
			json_databean = Json.createObjectBuilder().add("stockId", stockId)
					.build();
			jsonArray_builder.add(json_databean);
		}
		
		JsonArray jsonArray = jsonArray_builder.build();
		//前面在加data
		JsonObject jsonobj = Json.createObjectBuilder().add("data", jsonArray).build();

	
		out.write(jsonobj.toString());
		out.close();
		
		return;

	}

}

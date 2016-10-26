package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.DataAnalysisBean;
import model.DataAnalysisService;
import model.MembersBean;
import model.MembersService;
import model.MyFavouriteBean;

@WebServlet("/MyFavouriteAjax.view")
public class MyFavouriteAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MembersService membersService;
	DataAnalysisService dataAnalysisService;

	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		dataAnalysisService = (DataAnalysisService) context.getBean("dataAnalysisService");
		membersService = (MembersService) context.getBean("membersService");
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

		if (action != null && action.equals("getMyFav")) {
			this.getMyFav(request, response);
		} else if (action == null && method.equals("POST")) {
			this.getMyFav(request, response);
		} else {
			throw new ServletException("使用GET呼叫必須輸入action參數值");
		}

	}

	private void getMyFav(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain; charset=UTF-8");

		int num = -2;

		PrintWriter out = response.getWriter();

		MembersBean membersBean = new MembersBean();
		membersBean.setAccount(request.getParameter("account"));
		membersBean = membersService.select(membersBean).get(0);
		Iterator<MyFavouriteBean> myFavBeans = membersBean.getMyFavourite().iterator();

		List<String> myFavs = new ArrayList<String>();
		while (myFavBeans.hasNext()) {
			myFavs.add(myFavBeans.next().getStockId());
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

		System.out.println(jsonArray);

		out.write(jsonArray.toString());
		out.close();
		return;

	}

}

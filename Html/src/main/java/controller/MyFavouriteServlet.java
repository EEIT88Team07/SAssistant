package controller;

import java.io.IOException;
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
import javax.json.JsonArrayBuilder;
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
import model.GroupInfoService;
import model.MembersBean;
import model.MembersService;
import model.MyFavouriteBean;


@WebServlet("/myfavourite.controller")
public class MyFavouriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataAnalysisService dataAnalysisService;
	private GroupInfoService groupInfoService;
	private MembersService membersService;

	// springinit直接拿取
	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		dataAnalysisService = (DataAnalysisService) context.getBean("dataAnalysisService");
		groupInfoService = (GroupInfoService) context.getBean("groupInfoService");
		membersService = (MembersService) context.getBean("membersService");
	}

	// 判斷日期格式
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收請求,內容標題設為UTF8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收資料,發出請求時尋找name名稱存入string
		String datanysis = request.getParameter("datanysis");
		
		
		if ("讀取資料".equals(datanysis)) {
			this.getDatanysis(request, response);
		} else if ("onload".equals(datanysis)) {
			this.getMyFav(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	private void getDatanysis(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收資料,發出請求時尋找name名稱存入string

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

JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		
		
		List<DataAnalysisBean> result = null;
		if ("讀取資料".equals(datanysis)) {
			result = dataAnalysisService.selectByFilter(stockId, stratDate, endDate);
			request.setAttribute("select", result);
		}
		request.setAttribute("groups", groupInfoService.select(null));
		// 跳轉到資料 dataAnalysis.jsp
		
		
		
		

		request.getRequestDispatcher("/pages/member/focus.jsp").forward(request, response);

	}

	@SuppressWarnings("null")
	private void getMyFav(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		
		HttpSession session = request.getSession();
		
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");
		
		
		
		
		int num = -2;
		MembersBean membersBean = new MembersBean();
		// 改寫為取得session id
		membersBean.setAccount(mb.getAccount());
		membersBean = membersService.select(membersBean).get(0);

		Iterator<MyFavouriteBean> myFavBeans = membersBean.getMyFavourite().iterator();

		List<String> myFavs = new ArrayList<String>();
		while (myFavBeans.hasNext()) {
			myFavs.add(myFavBeans.next().getStockId());
		}
		Collections.sort(myFavs);

		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DATE, num);

		List<DataAnalysisBean> databeans = dataAnalysisService.selectByStockIds(myFavs, calendar.getTime(), new Date());
		
		
		request.setAttribute("select", databeans);
		request.setAttribute("groups", groupInfoService.select(null));
		request.getRequestDispatcher("/pages/member/focus.jsp").forward(request, response);
	}

}

package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.PurchaseHistoryBean;
import model.PurchaseHistoryService;
import model.SellingHistoryBean;
import model.SellingHistoryService;
import model.StockInfoBean;
import model.StockInfoService;

@WebServlet("/sellinghistory.controller")
public class SellingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PurchaseHistoryService purchaseHistoryService;
	private StockInfoService stockInfoService;
	private SellingHistoryService sellingHistoryService;
	private DateFormat dateformat;

	// springinit直接拿取
	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		purchaseHistoryService = (PurchaseHistoryService) context.getBean("purchaseHistoryService");
		sellingHistoryService = (SellingHistoryService) context.getBean("sellingHistoryService");
		stockInfoService = (StockInfoService) context.getBean("stockInfoService");
		dateformat = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收請求,內容標題設為UTF8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收資料,發出請求時尋找name名稱存入string
		String action = request.getParameter("action");
		System.out.println(action);
		if ("賣出".equals(action)) {
			this.showSelling(request, response);
		} else if ("送出".equals(action)) {
			this.insertSelling(request, response);
		} else if ("更新".equals(action)) {
			this.update(request, response);
		} else if ("刪除".equals(action)) {
			this.delete(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		String purchaseNumber = request.getParameter("purchaseNumber");

		String sellingNumber = request.getParameter("sellingNumber");
		PurchaseHistoryBean select = new PurchaseHistoryBean();
		select.setPurchaseNumber(purchaseNumber);
		PurchaseHistoryBean phbean = purchaseHistoryService.select(select).get(0);

		String temp1 = request.getParameter("sellingQuantity");
		Double sellingQuantity = 0.0;
		if (temp1 != null && temp1.trim().length() != 0) {

			try {
				sellingQuantity = Double.parseDouble(temp1);

			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("sellingQuantity", "賣出數量必須是數字且不可為空白");
			}

		}
		if (sellingQuantity <= 0) {
			errors.put("sellingQuantity", "賣出數量必須大於0");
		}

		String temp2 = request.getParameter("sellingPrice");
		Double sellingPrice = 0.0;
		if (temp2 != null && temp2.trim().length() != 0) {

			try {
				sellingPrice = Double.parseDouble(temp2);

			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("sellingQuantity", "賣出金額必須是數字且不可為空白");
			}

		}
		if (sellingPrice <= 0) {
			errors.put("sellingQuantity", "賣出金額必須大於0");
		}

		String temp3 = request.getParameter("dateOfSelling");
		Date dateOfSelling = null;
		if (temp3 != null && temp3.trim().length() != 0) {

			try {
				dateOfSelling = dateformat.parse(temp3);
			} catch (ParseException e) {
				e.printStackTrace();
				errors.put("dateOfSelling", "賣出日期必須符合yyyy-MM-dd格式");
			}
		}

		double cost = 0;
		double income = 0;
		double netIncome = 0;
		String netProfitMargin = null;
		double netvalue = 0;
		if (sellingQuantity > 0) {
			double purchase = phbean.getPurchasePrice();
			cost = sellingQuantity * purchase*1000;
			income = sellingQuantity * sellingPrice*1000;
			netIncome = income - cost;
			try {
				netvalue = netIncome / cost * 100;
			} catch (Exception e) {
				e.printStackTrace();
			}

			DecimalFormat df = new DecimalFormat("#.##");
			netProfitMargin = df.format(netvalue);
		}

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/pages/member/sellinghistory.jsp").forward(request, response);
			return;
		}

		SellingHistoryBean update = new SellingHistoryBean();

		update.setSellingNumber(sellingNumber);
		update.setSellingPrice(sellingPrice);
		update.setSellingQuantity(sellingQuantity);
		update.setDateOfSelling(dateOfSelling);
		update.setCost(cost);
		update.setIncome(income);
		update.setNetIncome(netIncome);
		update.setNetProfitMargin(Double.parseDouble(netProfitMargin));
		update.setPurchaseNumber(purchaseNumber);

		SellingHistoryBean result = sellingHistoryService.update(update);

		if (result == null) {
			errors.put("action", "更新資料失敗");
		} else {
			request.setAttribute("insert", result);
		}
		request.getRequestDispatcher("/pages/member/sellinghistory.jsp").forward(request, response);

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/pages/member/sellinghistory.jsp").forward(request, response);
			return;
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		SellingHistoryBean bean = new SellingHistoryBean();

		bean.setSellingNumber(request.getParameter("sellingNumber").toString());
		sellingHistoryService.delete(bean);
		request.getRequestDispatcher("/pages/member/sellinghistory.jsp").forward(request, response);
	}

	private void insertSelling(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		String purchaseNumber = request.getParameter("purchaseNumber");

		PurchaseHistoryBean select = new PurchaseHistoryBean();
		select.setPurchaseNumber(purchaseNumber);
		PurchaseHistoryBean phbean = purchaseHistoryService.select(select).get(0);
		Iterator<SellingHistoryBean> sellings = phbean.getSellingHistory().iterator();

		String temp1 = request.getParameter("sellingQuantity");
		Double sellingQuantity = 0.0;
		if (temp1 != null && temp1.trim().length() != 0) {

			try {
				sellingQuantity = Double.parseDouble(temp1);

			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("sellingQuantity", "賣出數量必須是數字且不可為空白");
			}

		}
		if (sellingQuantity <= 0) {
			errors.put("sellingQuantity", "賣出數量必須大於0");
		}

		String temp2 = request.getParameter("sellingPrice");
		Double sellingPrice = 0.0;
		if (temp2 != null && temp2.trim().length() != 0) {

			try {
				sellingPrice = Double.parseDouble(temp2);

			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("sellingQuantity", "賣出金額必須是數字且不可為空白");
			}

		}
		if (sellingPrice <= 0) {
			errors.put("sellingQuantity", "賣出金額必須大於0");
		}

		String temp3 = request.getParameter("dateOfSelling");
		Date dateOfSelling = null;
		if (temp3 != null && temp3.trim().length() != 0) {

			try {
				dateOfSelling = dateformat.parse(temp3);
			} catch (ParseException e) {
				e.printStackTrace();
				errors.put("dateOfSelling", "賣出日期必須符合yyyy-MM-dd格式");
			}
		}

		double cost = 0;
		double income = 0;
		double netIncome = 0;
		String netProfitMargin = null;
		double netvalue = 0;
		if (sellingQuantity > 0) {
			double purchase = phbean.getPurchasePrice();
			cost = sellingQuantity * purchase*1000;
			income = sellingQuantity * sellingPrice*1000;
			netIncome = income - cost;
			try {
				netvalue = netIncome / cost * 100;
			} catch (Exception e) {
				e.printStackTrace();
			}

			DecimalFormat df = new DecimalFormat("#.##");
			netProfitMargin = df.format(netvalue);
		}

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/pages/member/sellinghistory.jsp").forward(request, response);
			return;
		}

		SellingHistoryBean insert = new SellingHistoryBean();

		insert.setSellingNumber(UUID.randomUUID().toString());
		insert.setSellingPrice(sellingPrice);
		insert.setSellingQuantity(sellingQuantity);
		insert.setDateOfSelling(dateOfSelling);
		insert.setCost(cost);
		insert.setIncome(income);
		insert.setNetIncome(netIncome);
		insert.setNetProfitMargin(Double.parseDouble(netProfitMargin));
		insert.setPurchaseNumber(purchaseNumber);

		SellingHistoryBean result = sellingHistoryService.insert(insert);

		if (result == null) {
			errors.put("action", "新增資料失敗");
		} else {
			request.setAttribute("insert", result);
		}
		request.getRequestDispatcher("/pages/member/sellinghistory.jsp").forward(request, response);

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/pages/member/sellinghistory.jsp").forward(request, response);
			return;
		}

	}

	private void showSelling(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String purchaseNumber = request.getParameter("purchaseNumber");

		PurchaseHistoryBean select = new PurchaseHistoryBean();
		select.setPurchaseNumber(purchaseNumber);
		PurchaseHistoryBean bean = purchaseHistoryService.select(select).get(0);
		StockInfoBean stockInfoBean = new StockInfoBean();
		stockInfoBean.setStockId(bean.getStockId());

		HashMap<String, String> result = new HashMap<String, String>();

		result.put("purchaseNumber", bean.getPurchaseNumber());
		result.put("stockId", bean.getStockId());
		result.put("stockName", stockInfoService.select(stockInfoBean).get(0).getStockName());
		result.put("dateOfPurchase", bean.getDateOfPurchase().toString());
		result.put("purchasePrice", bean.getPurchasePrice().toString());
		result.put("purchaseQuantity", bean.getPurchaseQuantity().toString());

		request.setAttribute("result", result);
		// 跳轉到資料 dataAnalysis.jsp

		request.getRequestDispatcher("/pages/member/selling.jsp").forward(request, response);

	}

}

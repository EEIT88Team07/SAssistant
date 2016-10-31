package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.MembersBean;
import model.MembersService;
import model.PurchaseHistoryBean;
import model.PurchaseHistoryService;

@WebServlet("/transhistory.controller")
public class TransHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PurchaseHistoryService purchaseHistoryService;
	private MembersService membersService;

	// springinit直接拿取
	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		purchaseHistoryService = (PurchaseHistoryService) context.getBean("purchaseHistoryService");
		membersService = (MembersService) context.getBean("membersService");
	}

	// 判斷日期格式
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收請求,內容標題設為UTF8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收資料,發出請求時尋找name名稱存入string
		String action = request.getParameter("action");

		System.out.println(action);
		if ("更新".equals(action)) {
			this.update(request, response);
		} else if ("刪除".equals(action)) {
			this.delete(request, response);
		} else if ("新增".equals(action)) {
			this.insert(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");
		MembersBean membersBean = new MembersBean();
		membersBean = membersService.select(mb).get(0);

		String temp1 = request.getParameter("stockId");
		String temp2 = request.getParameter("dateOfPurchase");
		String temp3 = request.getParameter("purchasePrice");
		String temp4 = request.getParameter("purchaseQuantity");
		String temp5 = request.getParameter("stopLossLimit");
		String temp6 = request.getParameter("takeProfitLimit");

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);

		Integer stockId = 0;
		if (temp1 != null && temp1.trim().length() != 0) {
			try {
				stockId = Integer.parseInt(temp1);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("id", "股票代碼必須是整數且不可為空白");
			}

			if (temp1.trim().length() != 4) {
				errors.put("id", "股票代碼必須是四個數字");
			}
		}
		Date dateOfPurchase = new Date();
		if (temp2 == "") {
			dateOfPurchase = null;
		} else if (temp2 != null && temp2.trim().length() != 0) {
			try {
				dateOfPurchase = sFormat.parse(temp2);
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("dateOfPurchase", "購買日期必須是:yyyy-MM-dd且不可為空白");
			}
		}
		Double purchasePrice = null;
		if (temp3 != null && temp3.trim().length() != 0) {
			try {
				purchasePrice = Double.parseDouble(temp3);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("purchasePrice", "購買金額必須是數字且不可為空白");
			}
		}
		Double purchaseQuantity = null;
		if (temp4 != null && temp4.trim().length() != 0) {
			try {
				purchaseQuantity = Double.parseDouble(temp4);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("purchaseQuantity", "購買數量必須是數字且不可為空白");
			}
		}

		Double stopLossLimit = null;
		if (temp5 != null && temp5.trim().length() != 0) {
			try {
				stopLossLimit = Double.parseDouble(temp5);
			} catch (NumberFormatException e) {
				stopLossLimit = null;
			}
		}
		Double takeProfitLimit = null;
		if (temp6 != null && temp6.trim().length() != 0) {
			try {
				takeProfitLimit = Double.parseDouble(temp6);
			} catch (NumberFormatException e) {
				takeProfitLimit = null;
			}
		}

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/pages/member/transhistory.jsp").forward(request, response);
			return;
		}

		PurchaseHistoryBean bean = new PurchaseHistoryBean();

		bean.setDateOfPurchase(dateOfPurchase);
		bean.setStockId(stockId.toString());
		bean.setPurchasePrice(purchasePrice);
		bean.setPurchaseQuantity(purchaseQuantity);
		bean.setStopLossLimit(stopLossLimit);
		bean.setTakeProfitLimit(takeProfitLimit);
		bean.setInvestment(purchasePrice * purchaseQuantity);
		bean.setAccount(membersBean.getAccount());
		bean.setPurchaseNumber(UUID.randomUUID().toString());

		PurchaseHistoryBean result = purchaseHistoryService.insert(bean);

		if (result == null) {
			errors.put("action", "新增資料失敗");

		} else {
			request.setAttribute("insert", result);
		}

		request.getRequestDispatcher("/pages/member/transhistory.jsp").forward(request, response);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PurchaseHistoryBean bean = new PurchaseHistoryBean();

		bean.setPurchaseNumber(request.getParameter("purchaseNumber").toString());

		purchaseHistoryService.delete(bean);

		request.getRequestDispatcher("/pages/member/transhistory.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");
		MembersBean membersBean = new MembersBean();
		membersBean = membersService.select(mb).get(0);

		String temp1 = request.getParameter("stockId");
		String temp2 = request.getParameter("dateOfPurchase");
		String temp3 = request.getParameter("purchasePrice");
		String temp4 = request.getParameter("purchaseQuantity");
		String temp5 = request.getParameter("stopLossLimit");
		String temp6 = request.getParameter("takeProfitLimit");
		String purchaseNumber = request.getParameter("purchaseNumber");
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);

		Integer stockId = 0;
		if (temp1 != null && temp1.trim().length() != 0) {
			try {
				stockId = Integer.parseInt(temp1);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("id", "股票代碼必須是整數且不可為空白");
			}

			if (temp1.trim().length() != 4) {
				errors.put("id", "股票代碼必須是四個數字");
			}
		}
		Date dateOfPurchase = new Date();
		if (temp2 == "") {
			dateOfPurchase = null;
		} else if (temp2 != null && temp2.trim().length() != 0) {
			try {
				dateOfPurchase = sFormat.parse(temp2);
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("dateOfPurchase", "購買日期必須是:yyyy-MM-dd且不可為空白");
			}
		}
		Double purchasePrice = null;
		if (temp3 != null && temp3.trim().length() != 0) {
			try {
				purchasePrice = Double.parseDouble(temp3);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("purchasePrice", "購買金額必須是數字且不可為空白");
			}
		}
		Double purchaseQuantity = null;
		if (temp4 != null && temp4.trim().length() != 0) {
			try {
				purchaseQuantity = Double.parseDouble(temp4);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("purchaseQuantity", "購買數量必須是數字且不可為空白");
			}
		}

		Double stopLossLimit = null;
		if (temp5 != null && temp5.trim().length() != 0) {
			try {
				stopLossLimit = Double.parseDouble(temp5);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("stopLossLimit", "停損點必須是數字且不可為空白");
			}
		}
		Double takeProfitLimit = null;
		if (temp6 != null && temp6.trim().length() != 0) {
			try {
				takeProfitLimit = Double.parseDouble(temp6);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("takeProfitLimit", "停利點必須是數字且不可為空白");
			}
		}

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/pages/member/transhistory.jsp").forward(request, response);
			return;
		}

		PurchaseHistoryBean bean = new PurchaseHistoryBean();

		bean.setDateOfPurchase(dateOfPurchase);
		bean.setStockId(stockId.toString());
		bean.setPurchasePrice(purchasePrice);
		bean.setPurchaseQuantity(purchaseQuantity);
		bean.setStopLossLimit(stopLossLimit);
		bean.setTakeProfitLimit(takeProfitLimit);
		bean.setInvestment(purchasePrice * purchaseQuantity);
		bean.setPurchaseNumber(purchaseNumber);

		bean.setAccount(membersBean.getAccount());

		PurchaseHistoryBean result = purchaseHistoryService.update(bean);

		if (result == null) {
			errors.put("action", "修改資料失敗");

		} else {
			request.setAttribute("update", result);
		}

		request.getRequestDispatcher("/pages/member/transhistory.jsp").forward(request, response);
	}

}

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

@WebServlet("/sellinghistory.controller")
public class SellingServlet extends HttpServlet {
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
		if ("賣出".equals(action)) {
			this.insertSelling(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

	private void insertSelling(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");
		MembersBean membersBean = new MembersBean();
		membersBean = membersService.select(mb).get(0);
		
		String purchaseNumber = request.getParameter("purchaseNumber");
		
		System.out.println(purchaseNumber);


		PurchaseHistoryBean bean = new PurchaseHistoryBean();

//		bean.setDateOfPurchase(dateOfPurchase);
//		bean.setStockId(stockId.toString());
//		bean.setPurchasePrice(purchasePrice);
//		bean.setPurchaseQuantity(purchaseQuantity);
//		bean.setStopLossLimit(stopLossLimit);
//		bean.setTakeProfitLimit(takeProfitLimit);
//		bean.setInvestment(purchasePrice * purchaseQuantity);
//		bean.setAccount(membersBean.getAccount());
//		bean.setPurchaseNumber(UUID.randomUUID().toString());
//
//		PurchaseHistoryBean result = purchaseHistoryService.insert(bean);
//
//		if (result == null) {
//			errors.put("action", "新增資料失敗");
//
//		} else {
//			request.setAttribute("insert", result);
//		}
//
//		request.getRequestDispatcher("/pages/member/transhistory.jsp").forward(request, response);

	}

}

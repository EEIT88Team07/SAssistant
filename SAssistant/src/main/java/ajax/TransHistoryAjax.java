package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.MembersBean;
import model.MembersService;
import model.PurchaseHistoryBean;
import model.PurchaseHistoryService;
import model.StockInfoBean;
import model.StockInfoService;

@WebServlet("/transajax.view")
public class TransHistoryAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StockInfoService stockInfoService;
	private PurchaseHistoryService purchaseHistoryService;
	private MembersService membersService;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		stockInfoService = (StockInfoService) context.getBean("stockInfoService");
		purchaseHistoryService = (PurchaseHistoryService) context.getBean("purchaseHistoryService");
		membersService = (MembersService) context.getBean("membersService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method = request.getMethod();
		String action = request.getParameter("action");

		System.out.println("action=" + action + ", method=" + method + ", " + request.getRequestURI());

		if (action != null && action.equals("getPurchaseHistory")) {
			this.getPurchaseHistory(request, response);
		}

	}

	private void getPurchaseHistory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain; charset=UTF-8");

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");
		MembersBean membersBean = new MembersBean();
		membersBean = membersService.select(mb).get(0);

		List<PurchaseHistoryBean> phbeans = purchaseHistoryService.selectByAccount(membersBean.getAccount());

		JsonArrayBuilder jsonArray_builder = Json.createArrayBuilder();
		if (phbeans.size() != 0) {

			for (int i = 0; i < phbeans.size(); i++) {
				PurchaseHistoryBean phbean = phbeans.get(i);
				StockInfoBean stockInfoBean = new StockInfoBean();
				stockInfoBean.setStockId(phbean.getStockId());

				JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
				jsonObjBuilder.add("purchaseNumber", phbean.getPurchaseNumber()).add("stockId", phbean.getStockId()).add("stockName", stockInfoService.select(stockInfoBean).get(0).getStockName())
						.add("dateOfPurchase", phbean.getDateOfPurchase().toString()).add("purchasePrice", phbean.getPurchasePrice()).add("purchaseQuantity", phbean.getPurchaseQuantity());
				if (phbean.getInvestment() != null) {
					jsonObjBuilder.add("investment", phbean.getInvestment());
				} else {
					jsonObjBuilder.add("investment", "");

				}

				if (phbean.getStopLossLimit() != null) {
					jsonObjBuilder.add("stopLossLimit", phbean.getStopLossLimit());
				} else {
					jsonObjBuilder.add("stopLossLimit", "");
				}

				if (phbean.getTakeProfitLimit() != null) {
					jsonObjBuilder.add("takeProfitLimit", phbean.getTakeProfitLimit());
				} else {
					jsonObjBuilder.add("takeProfitLimit", "");
				}
				jsonArray_builder.add(jsonObjBuilder.build());

			}

		}
		JsonArray jsonArray = jsonArray_builder.build();
		JsonObject jsonobj = Json.createObjectBuilder().add("data", jsonArray).build();

		out.write(jsonobj.toString());
		out.close();
		return;

	}
}

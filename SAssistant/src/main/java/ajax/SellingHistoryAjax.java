package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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
import model.SellingHistoryBean;
import model.SellingHistoryService;
import model.StockInfoBean;
import model.StockInfoService;

@WebServlet("/sellingajax.view")
public class SellingHistoryAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PurchaseHistoryService purchaseHistoryService;
	private StockInfoService stockInfoService;
	private SellingHistoryService sellingHistoryService;
	private MembersService membersService;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		purchaseHistoryService = (PurchaseHistoryService) context.getBean("purchaseHistoryService");
		sellingHistoryService = (SellingHistoryService) context.getBean("sellingHistoryService");
		stockInfoService = (StockInfoService) context.getBean("stockInfoService");
		membersService = (MembersService) context.getBean("membersService");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method = request.getMethod();
		String action = request.getParameter("action");

		System.out.println("action=" + action + ", method=" + method + ", " + request.getRequestURI());

		if (action != null && action.equals("getSellingHistory")) {
			this.getSellingHistory(request, response);
		}

	}

	private void getSellingHistory(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

				Iterator<SellingHistoryBean> shbeans = phbean.getSellingHistory().iterator();

				while (shbeans.hasNext()) {

					SellingHistoryBean shbean = shbeans.next();

					JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
					jsonObjBuilder.add("sellingNumber", shbean.getSellingNumber()).add("purchaseNumber", phbean.getPurchaseNumber()).add("stockId", phbean.getStockId())
							.add("stockName", stockInfoService.select(stockInfoBean).get(0).getStockName()).add("dateOfPurchase", phbean.getDateOfPurchase().toString())
							.add("sellingPrice", shbean.getSellingPrice()).add("sellingQuantity", shbean.getSellingQuantity()).add("dateOfSelling", shbean.getDateOfSelling().toString());

					
					jsonObjBuilder.add("cost", shbean.getCost()).add("income", shbean.getIncome()).add("netIncome", shbean.getNetIncome()).add("netProfitMargin",
							shbean.getNetProfitMargin());

					jsonArray_builder.add(jsonObjBuilder.build());
				}
			}
		}
		JsonArray jsonArray = jsonArray_builder.build();
		JsonObject jsonobj = Json.createObjectBuilder().add("data", jsonArray).build();
		out.write(jsonobj.toString());
		out.close();
		return;

	}
}

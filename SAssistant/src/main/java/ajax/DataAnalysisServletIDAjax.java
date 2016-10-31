package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
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

//資料庫json網址
@WebServlet(urlPatterns = { "/dataAnalysis.view" })

public class DataAnalysisServletIDAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataAnalysisService dataAnalysisService;

	// init取得dataAnalysisService 在servlet關掉時traction
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		dataAnalysisService = (DataAnalysisService) context.getBean("dataAnalysisService");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action != null && action.equals("getCandPara")) {
			this.getCandlestick(request, response);
		} else {
			throw new ServletException("使用GET呼叫必須輸入action參數值");
		}

	}

	private void getCandlestick(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String id = request.getParameter("id");

		if (id.length() == 0) {
			id = null;
		}

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -90);

		Date start = cal.getTime();
		List<DataAnalysisBean> results = dataAnalysisService.selectByFilter(id, start, new Date());

		HashMap<String, List<Double>> datas = new HashMap<String, List<Double>>();

		for (int i = 0; i < results.size(); i++) {
			List<Double> data = new ArrayList<Double>();
			DataAnalysisBean bean = results.get(i);

			// 指定圖表的配置相和數據,開盤，收盤，最低，最高
			data.add(bean.getOpenPrice());
			data.add(bean.getClosingPrice());
			data.add(bean.getLowestPrice());
			data.add(bean.getHighestPrice());
			datas.put(bean.getBuildDate(), data);

		}
		JSONObject json = new JSONObject(datas);

		
		out.write(json.toString());
		out.close();

		return;

	}

	private void writeJsonOutput(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 要印出頁面
		List<DataAnalysisBean> result = dataAnalysisService.selectByFilter(null, null, null);
		// 用迭帶器把他撈出來(一種習慣~)
		Iterator<DataAnalysisBean> iter = result.iterator();
		JsonArrayBuilder builder = Json.createArrayBuilder();

		while (iter.hasNext()) {
			// 把資料塞到json格是裡面 key value
			JsonObject obj = Json.createObjectBuilder().add("stockId", iter.next().getStockId()).build();
			builder.add(obj);
		}
		// 印出來
		out.write(builder.build().toString());
		out.close();
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}

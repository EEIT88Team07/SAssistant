package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.json.Json;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.DataAnalysisBean;
import model.DataAnalysisService;
import model.GroupInfoBean;
import model.GroupInfoService;
import model.StockInfoBean;
import model.StockInfoService;



//資料庫json網址
@WebServlet(
		urlPatterns={"/groupInfo.view"}
)

public class GroupInfoIDAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GroupInfoService groupInfoService;
	private StockInfoService stockInfoService;
	//init取得dataAnalysisService 在servet關掉時traction
	public void init() throws ServletException {
		ApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		groupInfoService = (GroupInfoService) context.getBean("groupInfoService");
		stockInfoService = (StockInfoService)context.getBean("stockInfoService");
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String method = request.getMethod();
			String action = request.getParameter("action");
			System.out.println("action="+action+", method="+method+", "+request.getRequestURI());
			if(action!=null && action.equals("groupname")) {
				this.writeJsonOutputgroupname(request, response);
			} else if(action!=null && action.equals("stockids")) {
				this.writeJsonOutstockid(request, response);
			} else {
				throw new ServletException("使用GET呼叫必須輸入action參數值：groupname, stockid");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void writeJsonOutputgroupname(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//要印出頁面 
		List<GroupInfoBean> groups = groupInfoService.select(null);
		
		//製作jsonarray
		JsonArrayBuilder builder = Json.createArrayBuilder();
//		Iterator<String> iter= groups.values().stream().findFirst().get().iterator();
		
		//慢慢撈出來
//		Iterator<GroupInfoBean> industryCategory =groups.stream().collect(Collectors.toSet()).iterator();
		Iterator<GroupInfoBean> industryCategory = groups.iterator();
		while(industryCategory.hasNext()){
			GroupInfoBean bean =industryCategory.next();//產業類別
			builder.add(Json.createObjectBuilder()
					.add("industryCategory",bean.getGroupName())	//把資料塞到json格是裡面 key value
					.build());
			}
		//印出來
		out.write(builder.build().toString());
		out.close();
		return;
	}
	private void writeJsonOutstockid(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		//要印出頁面 
		PrintWriter out = response.getWriter();
		HashMap<String, List<String>> groups = groupInfoService.getGroupInfos(stockInfoService.select(null));
		
		
		//製作jsonarray
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
//		for(String e: s){
//			優化速度位以下方法
//		Iterator<String> industryCategory = groups.keySet().iterator();產業全部
//		}
		
		
		StockInfoBean bean = new StockInfoBean();
		/* 把資料塞到json格是裡面 key value
		 * stream jdk8方法 優化讀取速度
		 * 慢慢撈出來industryCategory 產業類別
		 */
		groups.keySet().stream().forEach(industryCategory->{
             //	String str =s產業類別
			//groups.get(str).stream()比對產業類別,用stream把他撈出來
			groups.get(industryCategory).stream().forEach(element-> {
				bean.setStockId(element);
				 builder.add(Json.createObjectBuilder()
							.add(industryCategory,element+" "+stockInfoService.select(bean).get(0).getStockName())//s=指定產業類別裡面的物件
							.build());
            	});
		});
		//印出來
		out.write(builder.build().toString());
		out.close();
		return;
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}

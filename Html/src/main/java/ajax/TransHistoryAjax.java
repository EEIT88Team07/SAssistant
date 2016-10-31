package ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.MyFavouriteDAO;
import model.MyFavouriteService;


@WebServlet("/transhistoryajax.view")
public class TransHistoryAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MyFavouriteService myFavouriteService;
	
	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		myFavouriteService = (MyFavouriteService) context.getBean("myFavouriteService");
		System.out.println("hahahahaha");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("hahahahaha");
		String action = request.getParameter("action");
		
		if(action!=null && action.equals("getPurchaseHistory")) {
			this.getPurchaseHistory(request, response);
		}else if(action!=null && action.equals("updatePurchaseHistory")) {
			this.updatePurchaseHistory(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void getPurchaseHistory(HttpServletRequest request, HttpServletResponse response){
		System.out.println("getPurchaseHistory");
		
		
	}
	
	private void updatePurchaseHistory(HttpServletRequest request, HttpServletResponse response){
		System.out.println("updatePurchaseHistory");
		
	}

}

package misc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MembersBean;

@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "url_1", value = "/pages/member/*"),
				@WebInitParam(name = "url_2", value = "/login.jsp")
		})
public class LoginFilter implements Filter {
	Collection<String> url = new ArrayList<String>();

    public LoginFilter() {
        
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse){
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			String servletPath = req.getServletPath();
			System.out.println("111--->" + servletPath);
			if(mustLogin(servletPath)){
				if(checkLogin(req)){
					System.out.println("222--->需要Login，已經Login");
					chain.doFilter(request, response);
				}else{
					HttpSession session = req.getSession();
					session.setAttribute("target", req.getServletPath());
					System.out.println("333--->需要Login，尚未Login，servletPath=" + req.getServletPath());
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(req, resp);
				}
			}else{
				System.out.println("444--->不需要Login");
				chain.doFilter(request, response);
			}
		}else{
			throw new ServletException("Request/Response Type Error");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		Enumeration<String> e = fConfig.getInitParameterNames();
		while(e.hasMoreElements()){
			String name = e.nextElement();
			url.add(fConfig.getInitParameter(name));
		}
	}
	
	private boolean mustLogin(String servletPath){
		boolean login = false;
		for(String sURL : url){
			if(sURL.endsWith("*")){
				sURL = sURL.substring(0, sURL.length() - 1);
				if(servletPath.startsWith(sURL)){
					login = true;
					break;
				}
			}else{
				if(servletPath.equals(sURL)){
					login = true;
					break;
				}
			}
		}
		return login;
	}
	
	private boolean checkLogin(HttpServletRequest request){
		HttpSession session = request.getSession();
		MembersBean loginToken = (MembersBean)session.getAttribute("LoginOK");
		if(loginToken == null){
			return false;
		}else{
			return true;
		}
	}

}

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.InstantlyInfoBean;
import model.InstantlyInfoService;

/**
 * Servlet implementation class DataAnalysisBean
 */
@WebServlet("/instantlyInfo.controller")
public class InstantlyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InstantlyInfoService instantlyInfoService;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		instantlyInfoService = (InstantlyInfoService) context.getBean("instantlyInfoService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		request.getRequestDispatcher("/pages/investment/realtime.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}

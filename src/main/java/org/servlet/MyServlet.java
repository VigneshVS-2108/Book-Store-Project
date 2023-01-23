package org.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "ms",value = "/ms")
public class MyServlet implements Servlet
{

	public MyServlet()
	{
		System.out.println("MyServlet Object has been created");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		System.out.println("resources are initialised");
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("hello this is my servlet...");
		
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		System.out.println("MyServlet object destroyed successfully");
		
	}
	
}

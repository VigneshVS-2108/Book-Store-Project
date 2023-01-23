package org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="reg",value = "/reg")
public class RegisterServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter pw=res.getWriter();
		String name=req.getParameter("nm");
		double edition=Double.parseDouble(req.getParameter("ed"));
		double price=Double.parseDouble(req.getParameter("pr"));
		Connection con=null;
		PreparedStatement pst=null;
		pw.println("<html><body>");
		pw.println("<head><link rel='stylesheet' href='reg.css'/></head>");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/book_store","root","Python#2121");
			pst=con.prepareStatement("insert into book(name,edition,price) values(?,?,?)");
			pst.setString(1, name);
			pst.setDouble(2,edition);
			pst.setDouble(3, price);
			int row=pst.executeUpdate();
			if(row==1)
			{
				pw.println("<h2>Book registered successfully");
			}
			else
			{
				pw.println("<h2>Book not registered successfully");
			}
		}
		catch(ClassNotFoundException|SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(pst!=null)
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			pw.println("<a href='home.html'>Click here to go to Home page</a>");
			pw.println("<a href='booklist'>Click here to view all the Book List</a>");
			
			pw.println("</body></html>");
		}
	}
}

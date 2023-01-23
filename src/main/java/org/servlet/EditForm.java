package org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/edit")
public class EditForm extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter pw=res.getWriter();
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		int id=Integer.parseInt(req.getParameter("id"));
		
		try
		{
			pw.println("<html><head><link rel='stylesheet' href='edit.css'/></head><body>");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/book_store","root","Python#2121");
			pst=con.prepareStatement("select name,edition,price from book where id=?");
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next())
			pw.println("<form action='editurl?id="+id+"' method='post'>");
			pw.println("<table>");
			
			pw.println("<tr>");
			pw.println("<td>Book Name</td>");
			pw.println("<td><input type='text' name='nm' value='"+rs.getString(1)+"'/></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td>Book Edition</td>");
			pw.println("<td><input type='text' name='ed' value='"+rs.getDouble(2)+"'/></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td>Book Price</td>");
			pw.println("<td><input type='text' name='pr' value='"+rs.getDouble(3)+"'/></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td colspan='2'><input type='submit' value='edit'/>");
			pw.println("<input type='reset' value='cancel'/></td>");
			pw.println("</tr>");
			
			pw.println("</table>");
			pw.println("</form>");
			
			pw.println("<a href='home.html'>Click here to go to Home page</a>");
			pw.println("<a href='booklist'>Click here to view all the book list</a>");
			pw.println("</body></html>");
			
			
	
		}
		catch(ClassNotFoundException|SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
				try {
					if(con!=null) 
					con.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			
				try {
					if(pst!=null)
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
	}
}

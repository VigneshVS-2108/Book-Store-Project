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

@WebServlet(value="/delete")
public class DeleteServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter pw=res.getWriter();
		Connection con=null;
		PreparedStatement pst=null;
		int id=Integer.parseInt(req.getParameter("id"));
		
		try
		{
			pw.println("<html><head><link rel='stylesheet' href='del.css'/></head><body>");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/book_store","root","Python#2121");
			pst=con.prepareStatement("delete from book where id=?");
			pst.setInt(1, id);
		
			int row=pst.executeUpdate();
			if(row==1)
			{
				pw.println("<h2>Book deleted successfuly</h2>");
			}
			else
			{
				pw.println("<h2>Book not deleted successfuly</h2>");
			}
			pw.println("<a href='home.html'>Click here to go to Home page</a>");
			pw.println("<a href='booklist'>Click here to view all the Book List</a>");
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

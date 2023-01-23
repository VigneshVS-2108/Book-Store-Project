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

@WebServlet(name="bl",value="/booklist")
public class BookList extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter pw=res.getWriter();
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		pw.println("<html><head><link rel='stylesheet' href='bl.css'/></head><body>");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3307/book_store","root","Python#2121");
			pst=con.prepareStatement("select * from book");
			rs=pst.executeQuery();
			pw.println("<h2>Book Details</h2>");
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<th>Book Id</th>");
			pw.println("<th>Book Name</th>");
			pw.println("<th>Book Edition</th>");
			pw.println("<th>Book Price</th>");
			pw.println("<th>Edit Book</th>");
			pw.println("<th>Delete Book</th>");
			pw.println("</tr>");
			while(rs.next())
			{
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getDouble(3)+"</td>");
				pw.println("<td>"+rs.getDouble(4)+"</td>");
                pw.println("<td><a href='edit?id="+rs.getInt(1)+"'>Edit</a></td>");				
                pw.println("<td><a href='delete?id="+rs.getInt(1)+"'>Delete</a></td>");				
				
				pw.println("</tr>");
			}
			pw.println("</table>");
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
			pw.println("<a id='a1' href='home.html'>Click here to go to Home page</a>");
			pw.println("</body></html>");
		}
	}
}

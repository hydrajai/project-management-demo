package com.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchRecord")
public class SearchRecordServlet extends HttpServlet 
{
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		try
		{
			String pid= request.getParameter("pid");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet10","root","123456789");
			System.out.println("Connection created!");
			PreparedStatement ps=cn.prepareStatement("select * from products where pid=?");
			ps.setString(1, pid);
			ResultSet rst=ps.executeQuery();
			PrintWriter pw=response.getWriter();
			pw.println("<html");
			pw.println("<body>");
			
			
			if(rst.next())
			{
				pw.println("<table style='border-collapse:collapse;width:50%; margin:auto' border='1'>");
				
			   	pw.println("<tr>");
			   	pw.println("<th style='text-align:left'>Product Id</th>");
			   	pw.println("<td>"+rst.getString(1)+"</td>");
			   	pw.println("</tr>");
			   	
			  	pw.println("<tr>");
			   	pw.println("<th style='text-align:left'>Product name</th>");
			   	pw.println("<td>"+rst.getString(2)+"</td>");
			   	pw.println("</tr>");
			   	
			  	pw.println("<tr>");
			   	pw.println("<th style='text-align:left'>Product brand</th>");
			   	pw.println("<td>"+rst.getString(3)+"</td>");
			   	pw.println("</tr>");
			   	
			  	pw.println("<tr>");
			   	pw.println("<th style='text-align:left'>Product price</th>");
			   	pw.println("<td>"+rst.getString(4)+"</td>");
			   	pw.println("</tr>");
			   	pw.println("</table>");
			}
			else
			{
				pw.println("<div style='text-align:center'>");
				pw.println("<h1 style='color:red'>Product with id "+pid+" not found</h1>");
				pw.println("</div>");
			}
			pw.println("</body>");
			pw.println("</html>");
			rst.close();
			ps.close();
			cn.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}

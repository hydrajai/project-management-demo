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

@WebServlet("/productList")
public class ProductListServlet extends HttpServlet 
{
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet10","root","123456789");
			System.out.println("Connection created!");
			Statement st=cn.createStatement();
			ResultSet rst=st.executeQuery("select * from products");
			PrintWriter pw=response.getWriter();
			pw.println("<html");
			pw.println("<body>");
			pw.println("<table style='border-collapse:collapse;width:50%; margin:auto' border='1'>");
			pw.println("<tr><th>Product Id</th><th>Product Name</th><th>Product Brand</th><th>Product Price</th></tr>");
			
			while(rst.next())
			{
			   	pw.println("<tr>");
			  	pw.println("<td>"+rst.getString(1)+"</td>");
			  	pw.println("<td>"+rst.getString(2)+"</td>");
			  	pw.println("<td>"+rst.getString(3)+"</td>");
			  	pw.println("<td>"+rst.getString(4)+"</td>");
			  	pw.println("</tr>");
			}
			pw.println("</table>");

			pw.println("</body>");
			pw.println("</html>");
			rst.close();
			st.close();
			cn.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}

package com.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveRecord")
public class SaveRecordServlet extends HttpServlet 
{
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String productId=request.getParameter("pid");
		String productName=request.getParameter("name");
		String productBrand=request.getParameter("brand");
		String productPrice=request.getParameter("price");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet10","root","123456789");
			System.out.println("Connection created!");
			PreparedStatement ps=cn.prepareStatement("insert into products values(?, ?, ?, ?)"); 
			ps.setString(1, productId);
			ps.setString(2, productName);
			ps.setString(3, productBrand);
			ps.setString(4, productPrice);
			ps.executeUpdate();
			ps.close();
			cn.close();
			response.sendRedirect("save-success.html");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}

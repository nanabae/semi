package com.kh.app.drama.header.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.drama.header.service.HeaderService;

@WebServlet("/header")
public class HeaderRegisterController extends HttpServlet {
	
	private HeaderService hs = new HeaderService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try{
			String headerName = req.getParameter("headerName");
			System.out.println(headerName);
			int result = hs.regHeader(headerName);
			
		
			if(result == 1) {
				resp.setCharacterEncoding("UTF-8");
				PrintWriter out = resp.getWriter();
				out.write(headerName);
			}
			
		}catch(Exception e )	{
			System.out.println("Header Reg error");
			e.printStackTrace();
		}
		
		
	}

}

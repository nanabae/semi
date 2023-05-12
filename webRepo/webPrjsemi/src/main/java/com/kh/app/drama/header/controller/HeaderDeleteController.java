package com.kh.app.drama.header.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.drama.header.service.HeaderService;
import com.kh.app.drama.header.vo.HeaderVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/header/delete")
public class HeaderDeleteController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		    MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
		    String memNum = loginMember.getMemNum();
			String headerNum = req.getParameter("headerNum");
			
			HeaderVo vo = new HeaderVo();
			vo.setHeaderNum(headerNum);
			vo.setMemNum(memNum);
			
			HeaderService hs = new HeaderService();
			
			int result = hs.deleteHeader(vo);
			
			//화면 == 문자열 내보내기
			PrintWriter out = resp.getWriter();
			if(result == 1) {
				out.write("ok");
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] HeaderDeleteController.doPost errr~~~");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "header delete fail ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
		
	}
}



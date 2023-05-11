package com.kh.app.drama.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.drama.service.DramaService;
import com.kh.app.drama.vo.DramaVo;
import com.kh.app.member.vo.MemberVo;


@WebServlet("/drama/write")
public class DramaWriteController extends HttpServlet{
	
	private final DramaService ds = new DramaService();

	//작성하기 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
		if(loginMember != null) {
			req.getRequestDispatcher("/WEB-INF/views/drama/write.jsp").forward(req, resp);
		}else {
			req.getSession().setAttribute("alertMsg", "로그인을 먼저 해주세요");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
	}
	
	//작성하기
	// insert ~~~
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			//데꺼
			String headerNum = "";
			String catNum = req.getParameter("catNum");
			String headerNum_ = req.getParameter("header");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			String memNum = loginMember.getMemNum();
			
			if(headerNum_!= null || headerNum_ != "" ) {
				headerNum =headerNum_;
			}
			System.out.println(headerNum);
			//데뭉
			DramaVo vo = new DramaVo();
			vo.setDramaWriter(memNum);
			vo.setCatNum(catNum);
			vo.setHeaderNum(headerNum);
			vo.setTitle(title);
			vo.setContent(content);
			
			//서비스
			int result = ds.write(vo);
			
			//화면
			if(result == 1) {
				resp.sendRedirect(req.getContextPath() + "/drama/list");
			}else {
				throw new Exception();
			}
			
		}catch(Exception e) {
			System.out.println("[ERROR] drama write doPost errr....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "drama write fail ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp");
		}
		
	}
	
}//class



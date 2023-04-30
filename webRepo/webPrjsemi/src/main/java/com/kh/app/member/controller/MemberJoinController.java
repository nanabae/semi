package com.kh.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;


@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet{
	
	//회원가입 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/join-form.jsp").forward(req, resp);
	}
	
	//회원가입
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
   
			//데꺼
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");
			String memberNick = req.getParameter("memberNick");
			String memberBirth = req.getParameter("memberBirth");
			String memberPhon = req.getParameter("memberPhon");
			
			//데뭉
			MemberVo vo = new MemberVo();
			vo.setId(memberId);
			vo.setPwd(memberPwd);
			vo.setNick(memberNick);
			vo.setBirth(memberBirth);
			vo.setPhon(memberPhon);
			
			//서비스
			MemberService ms = new MemberService();
			int result = ms.join(vo);
			
			//화면
			if(result == 1) {
				String root = req.getContextPath();
				req.getSession().setAttribute("alertMsg", "회원가입을 축하드립니다!");
				resp.sendRedirect(root + "/home");
			}else {
				throw new Exception("[ERROR] MemberJoinController.doPost...");
			}
			
		}catch(Exception e) {
			System.out.println("[ERROR] join fail ...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "회원가입 실패...");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
		
	}
	
}//class



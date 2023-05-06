package com.kh.app.mypage.memo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.member.vo.MemberVo;
import com.kh.app.mypage.memo.service.MemoService;
import com.kh.app.mypage.memo.vo.MemoVo;

@WebServlet("/memo/write")
public class MemoWriteController extends HttpServlet{
	
	private final MemoService ms = new MemoService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//데이터 꺼내오기 닉네임 필요한지? 나중에 삭제?
			String writerName = req.getParameter("writerName"); 
			String dramaWriter = req.getParameter("dramaWriter");
			String memoContent = req.getParameter("memoContent");
			
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			String memoWriter = loginMember.getMemNum();
			
			if(loginMember == null) {
				resp.sendRedirect(req.getContextPath() + "/member/login");
			}
			
	        //데이터 뭉치기
			MemoVo vo = new MemoVo();
			vo.setMemoWriter(memoWriter);
			vo.setMemoTarget(dramaWriter);
			vo.setMemoContent(memoContent);
			
			//서비스
			int result = ms.write(vo);
			
			//화면
			if(result == 1) {
				resp.sendRedirect(req.getContextPath() + "/drama/list");
			}else {
				throw new Exception();
			}

			
		}catch(Exception e) {
			System.out.println("[ERROR] memo write errr....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "memo write fail ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp");
		}

	}
	
	

}

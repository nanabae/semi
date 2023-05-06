package com.kh.app.mypage.memo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.mypage.memo.service.MemoService;
import com.kh.app.mypage.memo.vo.MemoVo;

@WebServlet("/memo/search")
public class MemoSearchController extends HttpServlet{
	
	MemoService ms = new MemoService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String dramaNum = req.getParameter("dramaNum");
			
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			String memoWriter = loginMember.getMemNum();
			
			MemoVo vo = ms.selectMemoOneByno(memoWriter,dramaNum);
			req.getSession().setAttribute("memoVo", vo);
			//자바객체를 JSON 형태의 문자열로 변환
			Gson gson = new Gson();
			String jsonStr = gson.toJson(vo);
			//문자열 내보내기
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			out.write(jsonStr);
			
		}catch(Exception e) {
			System.out.println("[ERROR] memo search errr~~~");
			e.printStackTrace();
		}

	}
	
	

}

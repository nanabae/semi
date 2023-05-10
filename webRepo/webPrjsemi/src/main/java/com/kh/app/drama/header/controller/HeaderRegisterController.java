package com.kh.app.drama.header.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.app.drama.header.service.HeaderService;
import com.kh.app.drama.header.vo.HeaderVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/header")
public class HeaderRegisterController extends HttpServlet {
	
	private HeaderService hs = new HeaderService();
	
	//말머리 불러오기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVo loginMember = (MemberVo)req.getSession().getAttribute("loginMember");
		String memNum = loginMember.getMemNum();
		String headerName = req.getParameter("headerName");
		System.out.println(headerName);
		HeaderVo vo = new HeaderVo();
		vo.setMemNum(memNum);
		vo.setHeaderName(headerName);
		
		try {
			List<HeaderVo> listVo = hs.selectListHeader(vo);

			if(listVo != null) {
				//자바객체를 JSON 형태의 문자열로 변환
				Gson gson = new Gson();
				String jsonStr = gson.toJson(listVo);

				//문자열 내보내기
				resp.setCharacterEncoding("UTF-8");
				PrintWriter out = resp.getWriter();
				out.write(jsonStr);
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Header search list doServie error");
			e.printStackTrace();
		}
	}
	

	//말머리 등록	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try{
			MemberVo loginMember = (MemberVo)req.getSession().getAttribute("loginMember");
			String memNum = loginMember.getMemNum();
			String headerName = req.getParameter("headerName");

			HeaderVo vo = new HeaderVo();
			vo.setMemNum(memNum);
			vo.setHeaderName(headerName);
			
			HeaderVo dbVo = hs.regHeader(vo);

			if(dbVo != null) {
				//자바객체를 JSON 형태의 문자열로 변환
				Gson gson = new Gson();
				String jsonStr = gson.toJson(dbVo);
				
				//문자열 내보내기
				resp.setCharacterEncoding("UTF-8");
				PrintWriter out = resp.getWriter();
				out.write(jsonStr);
			}else {
				throw new Exception();
			}
			
		}catch(Exception e )	{
			System.out.println("Header Register error");
			e.printStackTrace();
		}
		
		
	}

}

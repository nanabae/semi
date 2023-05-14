package com.kh.app.drama.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.app.drama.service.DramaService;
import com.kh.app.drama.vo.DramaReplyVo;



@WebServlet("/drama/reply/list")
public class DramaReplyListController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String dramaNum = req.getParameter("dramaNum");
			
			DramaService ds = new DramaService();
			List<DramaReplyVo> list = ds.selectReplyList(dramaNum);
			
			//자바객체를 JSON 형태의 문자열로 변환
			Gson gson = new Gson();
			String jsonStr = gson.toJson(list);
			
			//문자열 내보내기
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			out.write(jsonStr);
		}catch(Exception e) {
			System.out.println("[ERROR] drama reply errr~~~");
			e.printStackTrace();
		}


	}
	
	

}

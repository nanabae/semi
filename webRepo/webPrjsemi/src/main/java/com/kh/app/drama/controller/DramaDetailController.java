package com.kh.app.drama.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.drama.service.DramaService;
import com.kh.app.drama.vo.DramaVo;

@WebServlet("/drama/detail")
public class DramaDetailController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			DramaService ds = new DramaService();
			String dramaNum = req.getParameter("dramaNum");
			
			DramaVo vo = ds.selectDramaOneByNum(dramaNum);

			//화면
			if(vo != null) {
				req.setAttribute("vo", vo);
				req.getRequestDispatcher("/WEB-INF/views/drama/detail.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
		 }catch(Exception e) {
			System.out.println("[ERROR] drama detail errr....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "상세조회 실패");
			req.getRequestDispatcher("~").forward(req, resp);
		 }
	
	}

}

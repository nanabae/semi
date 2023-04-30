 package com.kh.app.drama.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.common.page.PageVo;
import com.kh.app.drama.service.DramaService;
import com.kh.app.drama.vo.DramaVo;


@WebServlet("/drama/list")
public class DramaListController extends HttpServlet {
	private final DramaService ds = new DramaService();
	
	//공지사항 목록
	// SQL ~~~
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			//데이터 준비
			String catNum = req.getParameter("catNum");
			System.out.println(catNum);
			int listCount = ds.selectCnt(catNum);
			String page = req.getParameter("page");
			if(page == null) page = "1";
			int currentPage = Integer.valueOf(page);
			int pageLimit = 10;
			int boardLimit = 20;
			PageVo pv = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			
			//서비스
			List<DramaVo> list = null;
			if(catNum == null || catNum.equals("") || catNum.equals("0")) {
				list = ds.selectDramaList(pv);
			}else {
				list = ds.selectDramaList(pv,catNum);
			}
			System.out.println(pv);
			System.out.println(list);
			//화면
			req.setAttribute("pv" , pv);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/views/drama/list.jsp").forward(req, resp);
			
			
		}catch(Exception e) {
			System.out.println("[ERROR] drama list errrr....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "드라마 목록 조회 실패 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
}

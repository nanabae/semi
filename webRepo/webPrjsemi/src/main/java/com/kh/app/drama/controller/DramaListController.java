 package com.kh.app.drama.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			//데이터 준비 catNum이 널일 경우 listCount가 0
			String catNum = ""; 
			String headerNum = "";
			String catNum_ = req.getParameter("catNum");
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			String headerNum_ = req.getParameter("headerNum");
			
			
			if(catNum_ != null) catNum = catNum_ ;			
			if(headerNum_ != null) headerNum = headerNum_ ;			

			int listCount = ds.getDramaListCnt(catNum,searchType,searchValue,headerNum);
			String page = req.getParameter("page");
			if(page == null || page == "") page = "1";
			int currentPage = Integer.valueOf(page);
			int pageLimit = 10;
			int boardLimit = 20;
			PageVo pv = new PageVo(listCount, currentPage, pageLimit, boardLimit);

			//서비스
			List<DramaVo> list = null;
			list = ds.selectDramaList(pv,catNum,searchType,searchValue,headerNum);


			Map<String, String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			
			//화면
			//req.setAttribute("catNum", catNum);
			//req.setAttribute("headerNum", headerNum);
			req.setAttribute("searchVo", map);
			req.setAttribute("pv" , pv);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/views/drama/list.jsp").forward(req, resp);
			
			
		}catch(Exception e) {
			System.out.println("[ERROR] drama list errrr....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "드라마 목록 조회,검색 실패 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
}

package com.kh.app.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.common.page.PageVo;
import com.kh.app.notice.service.NoticeService;
import com.kh.app.notice.vo.NoticeVo;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
	
	private final NoticeService ns = new NoticeService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");		
			String page = req.getParameter("page");
			int currentPage = Integer.valueOf(page);
			int listCount = ns.getNoticeListCnt(searchType ,searchValue);
			
			int pageLimit = 5;
			int boardLimit = 10;
			PageVo pv = new PageVo(listCount, currentPage, pageLimit, boardLimit);
			
			//서비스  
			List<NoticeVo> list = ns.selectNoticeList(pv ,searchType ,searchValue);
			
			req.setAttribute("pv" , pv);
			req.setAttribute("list", list);
			
			req.getRequestDispatcher("/WEB-INF/views/notice/list.jsp").forward(req, resp);
			
		}catch(Exception e) {
			System.out.println("[ERROR] notice list errrr....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "공지사항 목록 조회 실패 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}


	}

}

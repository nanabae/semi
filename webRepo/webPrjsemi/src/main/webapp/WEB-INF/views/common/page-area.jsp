<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


	${request.getRequestURI()}


	<link rel="stylesheet" href="${root}/static/css/common/page-area.css">
    	<c:if test="${pv.startPage > 10 }">
      	<a  href="${root}/drama/list?page=${pv.startPage - 1}&catNum=${param.catNum}&headerNum=${param.headerNum}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">이전</a>
    	</c:if>
      	<c:forEach begin="${pv.startPage}" end="${pv.endPage}" step="1" var="i">
      		<c:if test="${pv.currentPage != i}">
				<a href="${root}/${extractedPath}?page=${i}&catNum=${param.catNum}&headerNum=${param.headerNum}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">${i}</a>

      		</c:if>
      		<c:if test="${pv.currentPage == i}">
          	<a style="color: rgb(113, 209, 18);">${i}</a>
      		</c:if>
      	</c:forEach>
      <c:if test="${pv.startPage < pv.maxPage-9}">
      	<a href="${root}/drama/list?page=${pv.endPage + 1}&catNum=${param.catNum}&headerNum=${param.headerNum}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">다음</a>
      </c:if>
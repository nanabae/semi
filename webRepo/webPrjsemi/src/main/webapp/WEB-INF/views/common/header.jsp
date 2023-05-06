<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
   
 <!-- 제이쿼리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="${root}/static/js/modal.js"></script> 

<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/common/reset.css">
<link rel="stylesheet" href="${root}/static/css/common/header.css">
<link rel="stylesheet" href="${root}/static/css/common/nav.css">   

<c:if test="${ not empty alertMsg }">
	<script>
	    alert('${alertMsg}');
	</script>
</c:if>
<c:remove var="alertMsg" scope="session"/>

<header>
	<div></div>
	<nav>
		<ul>
			<li><a href="${root}/drama/list?page=1">드라마방</a></li>
			<li><a href="${root}/board/list?page=1">수다방</a></li>
			<li><a href="${root}/notice/list">공지</a></li>
			<c:if test="${!empty loginMember}">
				<li><a href="${root}/member/edit">마이페이지</a></li>
				<li><a href="${root}/member/logout">로그아웃</a></li>
			</c:if>
			<c:if test="${empty loginMember}">
			<li><a href="${root}/member/join">회원가입</a></li>
			<li><a href="${root}/member/login">로그인</a></li>
			</c:if>
		</ul>
	</nav>
   
</header>



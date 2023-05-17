<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
   <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
   <c:set var="path" value="${pageContext.request.servletPath}"></c:set>
   <!-- <c:set var="extractedPath" value="${fn:substringAfter(path, '/WEB-INF/views/') }"></c:set> -->

  
<!-- 부트스트랩 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
   
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
			<li><a href="${root}/notice/list?page=1">공지</a></li>
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


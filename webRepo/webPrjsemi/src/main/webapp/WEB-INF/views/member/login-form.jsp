<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="wrap">

        <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <div id="member-area">
  
	    	<form action="${root}/member/login" method="POST">
	         <input type="text" name="memberId" placeholder="아이디">
	         <input type="password" name="memberPwd" placeholder="비밀번호">
	         <input type="submit" id="login-btn" value="로그인">
	    	</form>


    </div>
		 
	</div>	 
</body>
</html>
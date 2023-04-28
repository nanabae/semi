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

        <main>

            <form action="${pageContext.request.contextPath}/member/join" method="post" >

                <div id="join-area">
                    <span>아이디</span>
                    <input type="text" name="memberId">
                    <span>비밀번호</span>
                    <input type="password" name="memberPwd">
                    <span>비밀번호확인</span>
                    <input type="password" name="memberPwd2">
                    <span>닉네임</span>
                    <input type="text" name="memberNick">
                    <span>생년월일</span>
                    <input type="text" name="memberBirth">
                    <span>폰번호</span>
                    <input type="text" name="memberPhon">
                  

                    <input type="reset" value="초기화">
                    <input type="submit" value="회원가입">
                </div>
      
            </form>

        </main>

    </div>

</body>
</html>
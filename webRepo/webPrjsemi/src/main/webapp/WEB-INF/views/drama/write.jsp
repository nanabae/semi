<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>드라마 게시글 작성</title>
<style>

	#form-area{
		font-size: 32px;
		margin-top: 30px;
		display: grid;
		row-gap: 20px;
	}

	#form-area > input {
		width: 100%;
		height: 40px;
		border: 3px solid black;
	}

	#form-area > textarea {
		width: 100%;
		height: 350px;
		border: 2px solid black;
		resize: none;
	}

</style>
</head>
<body>

	<div id="wrap">
	
		<%@ include file="/WEB-INF/views/common/header.jsp" %>
		
		<main>
		
			<h1 align="center">드라마방 게시글 작성</h1>

			<form action="${pageContext.request.contextPath}/drama/write" method="POST">
				<div id="form-area">
					<select name="catNum">
            			<option value="1">잡담</option>
            			<option value="2">리뷰</option>
            			<option value="3">정보</option>
            		</select>
					<input type="text" name="title" placeholder="제목을 입력하세요">
					<textarea name="content" placeholder="내용을 입력하세요"></textarea>
					<input type="submit" value="작성하기">
				</div>
			</form>
		
		</main>
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>

</body>
</html>

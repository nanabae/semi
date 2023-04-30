<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    main table {
        width: 800px;
		height: 650px;
        margin: auto;
        font-size: 24px;
    }

    main table th , 
    main table td {
        border: 1px solid black;
    }

	.td3:hover {
		cursor: pointer;
	}	
</style>

</head>
<body>
	<div id="wrap">

        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <main>

			<%@ include file="/WEB-INF/views/common/nav.jsp" %>
    		<table>

				<thead>
					<tr>
						<th>번호</th>
						<th>분류</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
						<th>조회</th>
					</tr>
				</thead>

				<tbody>
					
					<c:forEach items="${ list }" var="drama">
						<tr>
							<td class="td1">${ drama.dramaNum }</td>
							<td>${ drama.catName }</td>
							<td class="td3">${ drama.title }</td>
							<td>${ drama.writerName }</td>
							<td>${ drama.enrollDate }</td>
							<td>${ drama.hit }</td>
						</tr>
					</c:forEach>
				
				</tbody>

			</table>    
			
			<div id="page-area">
				<%@ include file="/WEB-INF/views/common/page-area.jsp"%>
			</div>
            
        </main>
       
            <%@ include file="/WEB-INF/views/common/footer.jsp"%>
       
    </div>
</body>
</html>
<script>
	const td3Arr = document.querySelectorAll('.td3');
	const td1Arr = document.querySelectorAll('.td1');
	for(i= 0 ; i < td3Arr.length ; i++){
		td3Arr[i].addEventListener('click', function(e) {
			const dramaNum = e.target.parentNode.children[0].innerText;
			location.href = "${root}/drama/detail?dramaNum=" + dramaNum;
		});
	}
</script>


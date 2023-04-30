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
			<div id="search-area">
            	<form action="${root}/drama/search" method="get">
            		<input type="hidden" name="page" value="1">
            		<!-- <input type="hidden" name="catNum" value="0"> -->
            		<select name="searchType">
						<option value="all">전체</option>
            			<option value="title">제목</option>
            			<option value="writer">작성자</option>
            			<option value="content">내용</option>
            		</select>
            		<input type="text" name="searchValue" value="${searchVo.searchValue}">
            		<input type="submit" value="검색">
            	</form>
            </div>
			
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

	const searchType = '${searchVo.searchType}';
	const searchValue = '${searchVo.searchValue}';
	
	const searchValueInputTag = document.querySelector("input[name='searchValue']");
	
	if(searchType.length > 1){
		initSearchType();
	}
	
	// 검색 타입 초기셋팅
	function initSearchType(){
		const x = document.querySelector('select > option[value="' + searchType + '"]');
		x.selected = true;
	}


</script>


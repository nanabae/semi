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
        margin: auto;
		line-height: 30px;
    }
	main > table tr {
		height: 30px;
	}

    main table th , 
    main table td {
        border: 1px solid black;
	}
	.td3 a{
		color : rgb(126, 181, 17);

	}
	.td4:hover {
		cursor: pointer;
	}	
	td>a{
		text-decoration: none;
	}

	#modal-wrap{
	    width: 100vw;
	    height: 100vh;
	    background-color: rgb(126, 181, 17);
	    position: fixed;
	    top: 0px;
	    left: 0px;
	    display: none;
	    justify-content: center;
	    align-items: center;
	}
	
	#modal-wrap.active{
	    display: flex;
	}
	
	#modal{
	    width: 30vw;
	    height: 70vh;
	    border: 5px solid black;
	    box-sizing: border-box;
	}

	.modal-body{
		width: 200px;
	    height: 300px;
	    border: 1px solid black;
	    box-sizing: border-box;

		margin: auto;
		
	}

	#write-btn-area{
		display : flex;
		flex-direction: row-reverse;
		width:800px;
		margin:auto;

	}

	#search-area{
		display : flex;
		justify-content: center;
		width:800px;
		margin:auto;
	}
</style>

</head>
<body>
	<div id="wrap">

        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <main>
			<nav>
			<%@ include file="/WEB-INF/views/common/nav.jsp" %>
			</nav>

    		<table>

				<thead>
					<tr>
						<th>번호</th>
						<th>분류</th>
						<th>말머리</th>
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
							<c:if test="${empty drama.headerName }"><td> </td></c:if>
							<c:if test="${!empty drama.headerName }"><td class="td3"><a href="${root}/drama/list?page=1&catNum=${drama.catNum}&headerNum=${drama.headerNum}" >${ drama.headerName }</a></td></c:if>
							
							<td class="td4">${ drama.title }</td>
							<c:if test="${empty loginMember }"><td>${ drama.writerName }</td></c:if>
							<c:if test="${!empty loginMember }">
								<td><a href="javascript:toggleModal('${ drama.writerName }','${ drama.dramaWriter }','${ drama.dramaNum }')">${ drama.writerName }</a>
							    <div id="modal-wrap">
									<div id="modal">
										<%@ include file="/WEB-INF/views/common/member-inform-modal.jsp" %>
									</div>

								</div>
								</td>
							</c:if>
							
							<td>${ drama.enrollDate }</td>
							<td>${ drama.hit }</td>
						</tr>
					</c:forEach>
 
				</tbody>

			</table>    
			<div>
				<c:if test="${ !empty loginMember}">
					<div id="write-btn-area">
						<a  class="btn btn-dark" href="${root}/drama/write?page=${param.page}">작성하기</a>
					</div>
				</c:if>
			</div>
			<div id="search-area">
            	<form action="${root}/drama/list" method="get">
            		<input type="hidden" name="page" value="1">
            		<input type="hidden" name="catNum" value="${param.catNum}">
            		<input type="hidden" name="headerNum" value="${param.headerNum}">
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
			${pv}
	   
        </main>
            <%@ include file="/WEB-INF/views/common/footer.jsp"%>	  
    </div>
</body>
</html>
<script>
	const td3Arr = document.querySelectorAll('.td3');
	const td4Arr = document.querySelectorAll('.td4');
	const td1Arr = document.querySelectorAll('.td1');

	//상세 조회
	for (i = 0; i < td4Arr.length; i++) {
		td4Arr[i].addEventListener('click', function(e) {
			const dramaNum = e.target.parentNode.children[0].innerText;
			location.href = "${root}/drama/detail?page=${pv.currentPage}&dramaNum=" + dramaNum;
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
	
	//서치타입 변경 시 함수 실행+검색 값 비우기
	const searchTypeTag = document.querySelector('select[name="searchType"]');
	searchTypeTag.addEventListener("change" , function(){
		searchValueInputTag.value = '';
	});

</script>


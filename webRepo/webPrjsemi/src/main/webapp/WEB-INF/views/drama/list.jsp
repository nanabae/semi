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
        font-size: 24px;
		line-height: 30px;
    }
	main > table tr {
		height: 30px;
	}

    main table th , 
    main table td {
        border: 1px solid black;
    }

	.td3:hover {
		cursor: pointer;
	}	
	td>a{
		text-decoration: none;
	}

	#modal-wrap{
	    width: 100vw;
	    height: 100vh;
	    background-color: rgba(211, 211, 211, 0.702);
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
							<c:if test="${empty loginMember }"><td>${ drama.writerName }</td></c:if>
							<c:if test="${!empty loginMember }">
								<td><a href="javascript:toggleModal('${ drama.writerName }','${ drama.dramaWriter }')">${ drama.writerName }</a>
							    <div id="modal-wrap">
									<div id="modal">
										<button onclick="toggleModal();">창닫기</button>
										<div class="modal-body">
											<p><strong>닉네임:</strong>  <span class="writer-name"></span></p>
											<p><strong>포인트:</strong> </p>
											<form action="${root}/memo/write" method="post">
												<input type="hidden" class="drama-writer" name="dramaWriter">
												<input type="hidden" class="writer-name" name="writerName" >
												<!--회원 메모가 존재하면 수정/삭제버튼 나오게/회원 메모가 없을 경우에만 등록버튼  필요-->
												<p><strong>회원 메모:</strong> </p>
												<input type="text" name="memoContent" value='${ vo.memoContent }'>
												<input type="submit" value="등록">
											</form>

										</div>
										<button>쪽지보내기</button>
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
            		<input type="hidden" name="catNum" value="${catNum}">
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

	//상세 조회
	for (i = 0; i < td3Arr.length; i++) {
		td3Arr[i].addEventListener('click', function(e) {
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


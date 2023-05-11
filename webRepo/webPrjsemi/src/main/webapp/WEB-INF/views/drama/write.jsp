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

			<form action="${root}/drama/write" method="POST">
				<div id="form-area">
					<label>
						말머리:
						<select name="header" class="select-header">

						</select>
						<input type="text" class="header-input">
						<button type="button" onclick="checkHeader();">말머리 추가</button>
						<button type="button">말머리 삭제</button>
					</label>
					<select name="catNum">
            			<option value="1">잡담</option>
            			<option value="2">리뷰</option>
            			<option value="3">정보</option>
            		</select>

					<input type="text" name="title" placeholder="제목을 입력하세요">
					<textarea name="content" placeholder="내용을 입력하세요"></textarea>
					<input class="btn btn-dark" type="submit" value="작성하기"> </input>
				</div>
			</form>
			<div> <a class="btn btn-dark" href='${root}/drama/list?page=${param.page}'>돌아가기</a></div>
		
		</main>
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>

</body>
</html>

<script>
	let sHeader= document.querySelector(".select-header");

	//말머리 불러오기
	function loadHeader() {

		$.ajax({
			url: "${root}/header",
			type: "GET",
			success: function(data) {
				x = JSON.parse(data);
				// 옵션값을 반복문으로 생성해서 select 태그에 추가
				for (var i = 0; i < x.length; i++) {
				var option = document.createElement("option");  
				option.value = x[i].headerNum;
				option.text = x[i].headerName;
				sHeader.appendChild(option);
				}
			},
			error: function() {
				console.log();
			}
			});
		}
	loadHeader();

	//말머리 유무 검사 후 존재X않는 경우에만 말머리 등록하기.
	function checkHeader() {
		const optionArr = document.querySelectorAll(".select-header option");
		const headInputVal = document.querySelector(".header-input").value;

		let isValid = true;

		for (const element of optionArr) {
			if (element.text === headInputVal) {
			isValid = false;
			break;
			}
		}

		if (isValid) {
			regHeader();
		}else{
			alert("이미 등록된 말머리 존재가 존재합니다.");
		}
	
	}


	// 말머리 등록
	function regHeader(){
		//초기화 해 줄 것(페이지 로드 시점에 사용자 입력값이 아닌 빈값)
		let headInputVal = document.querySelector('.header-input').value;
		// 새로운 option element 만들기
		const newOption = document.createElement("option");

		// option을 select element에 추가하기
		sHeader.appendChild(newOption);

		$.ajax({
			url: "${root}/header",
			type: "post",
			data: { headerName : headInputVal},
			
			success: function(data) {
				x = JSON.parse(data);
				console.log(x);
				// option의 value 값과 내용을 설정하기
				newOption.value = x.headerNum
				newOption.textContent = x.headerName; 
			},
			error: function() {
				console.log();
			},
		})


	}

	

</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>드라마 게시글 작성</title>
<style>

	#form-area{
		font-size: 22px;
		margin-top: 30px;
		display: grid;
		row-gap: 20px;
	}

	#form-area > input {
		width: 100%;
		height: 40px;
		border: 1px solid black;
	}

	#form-area > textarea {
		width: 100%;
		height: 350px;
		border: 1px solid black;
		resize: none;
	}

	#form-area > label {
		width: 100%;
		display: flex;
		align-items: center;
		margin-left: 20px;

	}

	#form-area>select , .title-input{
		width: 100%;
		display: flex;
		align-items: center;
		margin-left: 20px;
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
						<div>말머리:</div>
						<select name="header" class="select-header">
							<option value="" class="header-default">말머리</option>

						</select>
						<input type="text" class="header-input">
						<button type="button" onclick="checkHeader();">말머리 추가</button>
						<button type="button" onclick="checkedHeader();">말머리 삭제</button>
					</label>
					<div>
						<select name="catNum">
							<option value="1">잡담</option>
							<option value="2">리뷰</option>
							<option value="3">정보</option>
						</select>
						<input type="text" width="800px" name="title" placeholder="제목을 입력하세요">

					</div>

					
					<textarea name="content" placeholder="내용을 입력하세요"></textarea>
					
					<div>
						<span>첨부파일</span>
						<input type="file" multiple name ="f">
					</div>
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
	const sHeader= document.querySelector(".select-header");
	
	//말머리 불러오기
	function loadHeader() {

		$.ajax({
			url: "${root}/drama/header/list",
			type: "GET",
			success: function(data) {
				x = JSON.parse(data);
				// 옵션값을 반복문으로 생성해서 select 태그에 추가
				for (var i = 0; i < x.length; i++) {
				var option = document.createElement("option");  
				option.value = x[i].headerNum;
				option.textContent = x[i].headerName;
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
		const headInputVal = document.querySelector('.header-input').value;
		// 새로운 option element 만들기
		const newOption = document.createElement("option");

		// option을 select element에 추가하기
		sHeader.appendChild(newOption);

		$.ajax({
			url: "${root}/drama/header/register",
			type: "post",
			data: { headerName : headInputVal},
			
			success: function(data) {
				x = JSON.parse(data);
				// option의 value 값과 내용을 설정하기
				newOption.value = x.headerNum
				newOption.textContent = x.headerName; 
			},
			error: function() {
				console.log();
			},
		})


	}


	//셀렉트 값 변경시 변경된 값 input에 넣어주기.
	sHeader.addEventListener("change" , function(){
		let selectedOption = sHeader.querySelector('select option:checked');
		headInput = document.querySelector('.header-input');
		headInput.value =selectedOption.textContent;
	});

	//체크되어 있는 말머리만 삭제하기(기봅값인 말머리 선택시 알람 띄우기)
	function checkedHeader() {
		let selectedOption = sHeader.querySelector('select option:checked');

		if (selectedOption.value == '' ) {
			alert("삭제할 말머리를 선택하세요");
		}else{
			deleteHeader();
		}
	}

	//말머리 삭제
	function deleteHeader(){
		let selectedOption = sHeader.querySelector('select option:checked');
		headInput = document.querySelector('.header-input');
		const headerNum = selectedOption.value;
		
		$.ajax({
			url: "${root}/header/delete",
			type: "post",
			data: { headerNum : headerNum},
			
			success: function(x) {
				if(x == 'ok'){
					selectedOption.remove();
					headInput.value = '';
				}

			},
			error: function() {
				console.log();
			},
		})

	}

	

</script>
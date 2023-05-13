
	
	function toggleModal(writerName,dramaWriter,dramaNum ){
		//모달 가져오기
		const mw = document.querySelector("#modal-wrap");
	   
		//active 클래스 토글하기
		
		mw.classList.toggle("active");
        // 닉네임 정보 가져오기 .모달창에 반복문에서 젤 처음 출력되는 닉네임,회원번호 표기되는거 해결
		const nickname = mw.querySelector(".writer-name");
		const dramaWriterInput = mw.querySelector("input[name='dramaWriter']");
		const writerNameInput = mw.querySelector("input[name='writerName']");
		const memoContentInput = mw.querySelector("input[name='memoContent']");
		nickname.innerHTML = writerName;
		dramaWriterInput.value= dramaWriter;
		writerNameInput.value = writerName;
		// memoContentInput.value = ;

	}
	
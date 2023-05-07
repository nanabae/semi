<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #drama-area{
        width: 800px;
        height: 400px;
        border: 2px solid black;
        box-sizing: border-box;
        margin: auto;

        display: grid;
        grid-template-columns: 100px 1fr 100px;
        grid-template-rows: 50px 30px 30px 1fr;
        align-items: center;
    }

    #drama-area > div {
		border: 1px solid black;
		box-sizing: border-box;
		width: 100%;
		height: 100%;
	}
    #drama-area > div:nth-child(4) {
		grid-column: span 2;
	}
    #drama-area > div:nth-child(6) {
		grid-column: span 3;
       
	}

    #drama-area > div:nth-child(7) {
		grid-column: span 3;
       
	}
    #drama-area ul{
        display: flex;
        justify-content: left;
        align-items: center;
    }
    .memo-area ,.btnWrite , .btnEdit {
        display: none;
    }

    .active{display: block;}

    .modal-wrap{
	    width: 200px;
	    height: 50px;
	    background-color: rgba(211, 211, 211, 0.702);
	    position: fixed;
	    top: 0px;
	    left: 0px;
	    display: none;
	    justify-content: center;
	    align-items: center;
	}
	
	.modal-edit.active , .modal-delete.active{
	    display: flex;
	}
	
	.modal-body{
		width: 70px;
	    height: 30px;
	    border: 1px solid black;
	    box-sizing: border-box;

		margin: auto;
		
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

	#modal-body{
		width: 200px;
	    height: 300px;
	    border: 1px solid black;
	    box-sizing: border-box;

		margin: auto;
		
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

            <div id="drama-area">
                <div>${ vo.catName }</div>
                <div>${ vo.title }</div>
                <c:if test="${empty loginMember }"><div>${ vo.writerName }</div></c:if>
                <c:if test="${!empty loginMember }">
                <div><a href="javascript:toggleModal('${ vo.writerName }','${ vo.dramaWriter }')">${ vo.writerName }</a>
                    <div id="modal-wrap">
                        <div id="modal">
                            <button onclick="toggleModal();">창닫기</button>
                            <div id="modal-body">
                                <p><strong>닉네임:</strong>  <span class="writer-name"></span></p>
                                <p><strong>포인트:</strong> </p>
                                                          
                                    <form  class="myForm" action="${root}/memo/write" method="post">
                                    <input type="hidden" class="drama-writer" name="dramaWriter">
                                    <input type="hidden" class="writer-name" name="writerName" >
                                    <input type="hidden"  name="dramaNum" value='${ vo.dramaNum }' >
                                    <!--회원 메모가 존재하면 수정/삭제버튼 나오게/회원 메모가 없을 경우에만 등록버튼  필요-->
                                    <p><strong>회원 메모:</strong> </p>
                                    
                                        <input type="text" class="innerMemo btnWrite"  name="memoContent"  value=>
                                        <input class="btnWrite" type="submit" value="등록">
                                    </form>

                                    <form class="myForm" action="${root}/memo/edit" method="post" onsubmit="return validEditMemo()">
                                        <input type="hidden" class="memo-num" name="memoNum" > 
                                        <input type="hidden"  name="dramaNum" value='${ vo.dramaNum }'> 
                                        <input type="text"  class="editContent innerMemo btnEdit"  name="memoContent">
                                        <input class="btnEdit" type="submit" value="수정">
                                    </form>
                                    
                                    <form class="myForm" action="${root}/memo/delete" method="post" onsubmit="return confirmDelMemo()">
                                        <input type="hidden" class="memo-num" name="memoNum"> 
                                        <input type="hidden"  name="dramaNum" value='${ vo.dramaNum }'> 
                                        <input class="btnEdit" type="submit" value="삭제">
                                    </form>
                               

                            </div>
                            <button>쪽지보내기</button>
                        </div>

                    </div>
                </div>
            </c:if>
            
                <div>${ vo.enrollDate }</div>     
                
                <div>${ vo.hit }</div> 
                <div>
                    <ul>
                    <ul>
                        <li class="memo-area memo-text"></li>
                        <input type="hidden" class="memo-num">
                        <li class="memo-area"><button class="modify-button" onclick="editModal();" >수정하기</button></li>
                        <div class="modal-wrap modal-edit">
                            
                                <div class="modal-body">
                                    <form action="${root}/memo/edit" method="post" onsubmit="return validEditMemo()">
                                        <input type="hidden" class="memo-num" name="memoNum"> 
                                        <input type="hidden"  name="dramaNum" value='${ vo.dramaNum }'> 
                                        <input type="text" class="editContent" name="memoContent" >
                                        <input type="submit" value="수정">
                                    </form>

                                </div>
                            
                        </div>
                        <li class="memo-area"><button class="delete-button" onclick="deleteModal();">삭제하기</button></li>
                        <div class="modal-wrap modal-delete">
                            
                            <div class="modal-body">
                                <form action="${root}/memo/delete" method="post" onsubmit="return confirmDelMemo()">
                                    <input type="hidden" class="memo-num" name="memoNum"> 
                                    <input type="hidden"  name="dramaNum" value='${ vo.dramaNum }'> 
                                    <input type="submit" value="삭제">
                                </form>

                            </div>
                        
                    </div>
                    </ul>    
                    
                </div>      
             
                <div>${ vo.content }</div>
            </div>

            <c:if test="${ !empty loginMember}">
                <div id="drama-btn-area">
                    <a  href="${root}/drama/edit?dramaNum=${vo.dramaNum}">수정하기</a>
                    <a  href="${root}/drama/delete?dramaNum=${vo.dramaNum}">삭제하기</a>
                </div>
            </c:if>

        <div id="reply-area">
            <input type="hidden" name="dramaNum" value="${vo.dramaNum}">
            <div id="reply-form-area">
                <input type="text" name="content" placeholder="댓글을 입력하세요">
                <input type="button" value="댓글쓰기" onclick="writeComment();">
            </div>
            <div id="reply-list-area">
                <table>
                    <thead>
                        <tr>
                            <th>댓글내용</th>
                            <th>작성자</th>
                            <th>작성일시</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
    
        </main>

        <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    </div>    

</body>
</html>
<script>
const memoText = document.querySelector(".memo-text");
const memoAreaArr = document.querySelectorAll(".memo-area");
const memoNumArr = document.querySelectorAll(".memo-num");
const innerMemoArr = document.querySelectorAll('.innerMemo');
const btnWriteArr = document.querySelectorAll(".btnWrite");
const btnEditArr= document.querySelectorAll(".btnEdit");

// 드디어 해결. 읽어오는 속도때문에 문제였다. 코드에는 문제가 없었는데 
if("${loginMember}" != null && "${loginMember}" !=""){
    loadMemo('${vo.dramaNum}');
    setTimeout(showBtn,500)          
}


function loadMemo(dramaNum) {
    $.ajax({
        url: "/app/memo/search",
        type: "post",
        data: { dramaNum: dramaNum },
        success: function(data) {
             x = JSON.parse(data);
             if(x != null && x != ""){
                memoAreaArr.forEach((area) => {
                area.classList.add("active");
            });
                
                memoText.innerHTML = x.memoContent;
                innerMemoArr.forEach((innerMemo) => {
                    innerMemo.value= x.memoContent;
            });
               // innerMemo.value= x.memoContent;
                memoNumArr.forEach((memoNumV) => {
                    memoNumV.value = x.memoNum;
            });
                
        }
        return x;
        },
        error: function() {
            console.log();
        },
    })
}


function showBtn(){
    innerMemoArr.forEach((element) => {
   if(element.value.length > 1){
            btnEditArr.forEach((btnEdArr) => {
            btnEdArr.classList.add("active");
        });
            btnWriteArr.forEach((btnWrArr) => {
            btnWrArr.classList.remove("active");
        });

        }else{
            btnWriteArr.forEach((btnWrArr) => {
            btnWrArr.classList.add("active");
        });
            btnEditArr.forEach((btnEdArr) => {
            btnEdArr.classList.remove("active");
        });
        }

});
}
  

function editModal(){
    //모달 가져오기
    const me = document.querySelector(".modal-edit");
    
    //active 클래스        
    me.classList.add("active");
}

function deleteModal(){
    //모달 가져오기
    const md = document.querySelector(".modal-delete");
    
    //active 클래스        
    md.classList.add("active");
}
//메소드 제대로 실행 안됨.
// function validEditMemo(){
//     const editContentArr = document.querySelectorAll('.editContent01');
//     if (editContentArr.value === '') {
//     alert('수정할 내용을 입력해주세요.');
//     return false;
//   } 
//   return true;
// }

function validEditMemo() {
  const editContentArr = document.querySelectorAll('.editContent');
  
  editContentArr.forEach(editContent => {
    if (editContent.value.trim() === '') {
      alert('수정할 내용을 입력해주세요.');
      return false;
    }
  });
  return isValid;
}



function confirmDelMemo(){
    if (confirm("정말로 삭제하시겠습니까?")) {
        return true;
  
    } else {
        location.href = "${root}/drama/detail?dramaNum=${vo.dramaNum}";
        return false;
}

}

</script>  







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
    .memo-area {
        display: none;
    }

    .active{display: block;}

    #modal-wrap{
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
	
	#modal-wrap.active{
	    display: flex;
	}
	
	.modal-body{
		width: 70px;
	    height: 30px;
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
                <div>${ vo.writerName }</div>
                <div>${ vo.enrollDate }</div>     
                
                <div>${ vo.hit }</div> 
                <div>
                    <ul>
                    <ul>
                        <li class="memo-area memo-text"></li>
                        <input type="hidden" class="memo-num">
                        <li class="memo-area"><button class="modify-button" onclick="editModal();" >수정하기</button></li>
                        <div id="modal-wrap">
                            
                                <div class="modal-body">
                                    <form action="${root}/memo/edit" method="post" onsubmit="return validMemo()">
                                        <input type="hidden" class="memo-num" name="memoNum"> 
                                        <input type="hidden"  name="dramaNum" value='${ vo.dramaNum }'> 
                                        <input type="text" name="memoContent" >
                                        <input type="submit" value="수정">
                                    </form>

                                </div>
                            
                        </div>
                        <li class="memo-area"><button class="delete-button" >삭제하기</button></li>
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
if("${loginMember}" != null && "${loginMember}" !=""){
    loadMemo('${vo.dramaNum}');
}

const memoText = document.querySelector(".memo-text");
const memoAreaArr = document.querySelectorAll(".memo-area");
const memoNumArr = document.querySelectorAll(".memo-num");

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
                memoNumArr.forEach((memoNumV) => {
                    memoNumV.value = x.memoNum;
            });
                
        }

        },
        error: function() {
            console.log();
        },
    })
}

function editModal(){
    	//모달 가져오기
		const mw = document.querySelector("#modal-wrap");
	   
       //active 클래스        
       mw.classList.add("active");
}


function validMemo(){
    const memoContent = document.querySelector('input[name="memoContent"]').value;
  if (memoContent === '') {
    alert('수정할 내용을 입력해주세요.');
    return false;
  }
  return true;
}

</script>  







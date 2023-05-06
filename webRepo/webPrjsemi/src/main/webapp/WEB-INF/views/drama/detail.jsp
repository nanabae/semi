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
                        <li class="memo-area memo-text"></li>
                        <li class="memo-area"><button class="modify-button" >수정하기</button></li>
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
let memoContent;
function loadMemo(dramaNum) {
    $.ajax({
        url: "/app/memo/search",
        type: "post",
        data: { dramaNum: dramaNum },
        success: function(data) {
            const x = JSON.parse(data);
            if(x != null && x != ""){
                memoAreaArr.forEach((area) => {
                area.classList.add("active");
            });
                
                memoText.innerHTML = x.memoContent;
            }

        },
        error: function() {
            console.log();
        },
    });
}

</script>  







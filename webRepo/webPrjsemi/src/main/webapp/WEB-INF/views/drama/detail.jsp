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
    height: 600px;
    border: 1px solid black;
    box-sizing: border-box;
    margin: auto;

    display: grid;
    grid-template-columns: 100px 100px 1fr 100px;
    grid-template-rows: 50px 30px 30px 1fr;
    align-items: center;
}

#drama-area > div {
    border: 1px solid black;    
    box-sizing: border-box;
    width: 100%;
    height: 100%;
}
#drama-area > div:nth-child(5) {
    grid-column: span 3;
}
#drama-area > div:nth-child(7) {
    grid-column: span 4;
   
}

#drama-area > div:nth-child(8) {
    grid-column: span 4;
   
}
#drama-area ul{
    display: flex;
    justify-content: left;
    align-items: center;
}
.memo-area ,.btnWrite , .btnEdit ,.re-re-comment {
    display: none;
}

.active , .re-re-comment.active{display: block;}

.modal-wrap{
    width: 300px;
    height:100px;
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
    justify-content: center;
}

.modal-body{
    width: 70px;
    height: 30px;
    border: 1px solid black;
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    
}

#drama-btn-area{
    width: 800px;
    margin: auto;
    display: grid;
    grid-template-columns: 3fr 1fr;
    grid-template-rows: 1fr;
}

#drama-btn-area > div:nth-child(2){
    justify-self: end;
}

#reply-form-area {
    width: 800px;
    margin: auto;
    border-left: 1px solid black;
    border-right: 1px solid black;
    box-sizing: border-box;
    padding-left: 10px;
    padding-right: 10px;
}

#reply-form-area > input:first-child {
    width: 680px;
   
}

#reply-list-area > ul {
    width: 800px;
    margin: auto;
}

#reply-list-area  li {
    border-bottom : 1px solid rgba(128, 128, 128, 0.466);
}
textarea{
    width: 700px;
}

.red{
    color: red;
}



</style>

</head>
<body>
    <div id="wrap" style="border-bottom:none;border-left:none; border-right:none;">

        <%@ include file="/WEB-INF/views/common/header.jsp" %>
      
        <main>
			<nav>
			<%@ include file="/WEB-INF/views/common/nav.jsp" %>
			</nav>
            <div id="drama-area">
                <div>${ vo.catName }</div>
                <div>${ vo.headerName }</div>
                <div>${ vo.title }</div>
                <c:if test="${empty loginMember }"><div>${ vo.writerName }</div></c:if>
                <c:if test="${!empty loginMember }">
                    <!-- 회원정보 모달창 -->
                <div><a href="javascript:toggleModal('${ vo.writerName }','${ vo.dramaWriter }')">${ vo.writerName }</a>
                    <div id="modal-wrap">
                        <div id="modal">
                            <%@ include file="/WEB-INF/views/common/member-inform-modal.jsp" %>
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
                        <li class="memo-area"><button class="modify-button" onclick="editModal();" >수정</button></li>
                        <!-- 수정하기 모달창 -->
                        <div class="modal-wrap modal-edit">
                            
                                <div class="modal-body">
                                    <form action="${root}/memo/edit" method="post" onsubmit="return validEditMemo()">
                                        <input type="hidden" class="memo-num" name="memoNum"> 
                                        <input type="hidden"  name="dramaNum" value='${ vo.dramaNum }'> 
                                        <input type="text" class="editContent innerMemo" name="memoContent" >  <br>                        
                                        <input class="btn btn-dark" type="submit" value="수정">
                                        <button type="button" class="btn btn-dark" onclick="editModal();">취소
                                    </form>
                                    
                                </div>   

                            
                        </div>
                        <!--삭제하기 모달창 -->
                        <li class="memo-area"><button class="delete-button" onclick="deleteModal();">삭제</button></li>
                        <div class="modal-wrap modal-delete">
                            
                            <div class="modal-body">
                                <form action="${root}/memo/delete" method="post" onsubmit="return confirmDelMemo()">
                                    <input type="hidden" class="memo-num" name="memoNum"> 
                                    <input type="hidden"  name="dramaNum" value='${ vo.dramaNum }'> 
                                    <input class="btn btn-dark" type="submit" value="삭제">
                                    <!-- <input type="submit" value="삭제"> -->
                                    <button type="button" class="btn btn-dark" onclick="deleteModal();">취소</button>
                                </form>


                            </div>
                        
                        </div>
                    </ul>    
                    
                </div>      
             
                <div>${ vo.content }</div>
            </div>
            <div id="drama-btn-area">  
                <div >
                    <a class="btn btn-dark" href= "${root}/drama/list?page=${param.page}">목록</a>
                </div>
               
                <c:if test="${ loginMember.memNum == vo.dramaWriter}">
                    <div>                       
                        <a class="btn btn-dark" href="${root}/drama/edit?dramaNum=${vo.dramaNum}">수정</a>
                        <a  class="btn btn-dark" href="${root}/drama/delete?dramaNum=${vo.dramaNum}">삭제</a> 
                    </div>
                </c:if>
            </div>

			<div id="reply-area">
				<div id="reply-list-area">
                    <ul>

                    </ul>
				</div>
				<div id="reply-form-area">
                    <textarea name="comment" placeholder="댓글을 입력하세요" style="resize: none;"></textarea>
					<input type="button" value="댓글쓰기" onclick="writeComment();">
                  

				</div>
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

// 읽어오는 속도때문에 문제
if("${loginMember}" != null && "${loginMember}" !=""){
    loadMemo('${vo.dramaNum}');
    setTimeout(showBtn,1000)          
}


function showBtn(){
    const innerMemoArr = document.querySelectorAll('.innerMemo');
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
  


//메모 읽어오기
function loadMemo(dramaNum) {
    $.ajax({
        url: "${root}/memo/search",
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
    const me = document.querySelector(".modal-edit");
    
    //active 클래스        
    me.classList.toggle("active");
}

function deleteModal(){
    //모달 가져오기
    const md = document.querySelector(".modal-delete");
    
    //active 클래스        
    md.classList.toggle("active");
}

function validEditMemo() {
  const editContentArr = document.querySelectorAll('.editContent');
  let isValid = true;
  
  editContentArr.forEach(editContent => {
    if (editContent.value.trim() === '') {
      alert('수정할 내용을 입력해주세요.');
      isValid = false;
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

//댓글 작성
function writeComment(reRef){
    const comment = document.querySelector("textarea[name=comment]").value;
    $.ajax({
        url : "${root}/drama/reply/write" ,
        type : "POST" ,
        data : {
            dramaNum : '${vo.dramaNum}' ,
            comment : comment ,
            reRef : reRef,
        } ,
        success : (x)=>{
            if(x == 'ok'){
                console.log(x);
                 document.querySelector("textarea[name=comment]").value = '';
                loadComment();
            }else{
                alert("댓글 작성 실패...");
            }
        } ,
        error : (x)=>{
            console.log(x);
        } ,
    });
}

//댓글 불러오기 
function loadComment(){

    $.ajax({
        url : '${root}/drama/reply/list' ,
        type : "GET" ,
        data : {
            dramaNum : '${vo.dramaNum}' , 
        } ,
        success : function(data){
            //JSON 형태로 받아서, 화면에 보여주기
            const x = JSON.parse(data);
            const ul = document.querySelector('#reply-list-area ul');
            ul.textContent = "";

            for (let i = 0; i < x.length; i++) {
                const li = document.createElement('li');
                const input1 = document.createElement('input')
                const div1 = document.createElement('div');
                const div2 = document.createElement('div');
                const btn = document.createElement('button');
                input1.type = 'hidden';
                input1.value = x[i].reRef;
                li.appendChild(input1);
                
                if (x[i].reNo === x[i].reRef) {
                    div1.textContent = x[i].writerName + ' ' + x[i].reEnrolldate;
                    div2.textContent = x[i].reContent;
                    btn.textContent = '대댓글';

                    // 댓글 영역 생성
                    const commentArea = document.createElement('div');
                    commentArea.classList.add('re-re-comment');
                    //li.appendChild(commentArea);

                    btn.addEventListener('click', function(e) {
                        const reRef = e.target.parentNode.querySelector('input[type="hidden"]').value;
                        //버튼 클릭시 무한 증식 해결
                        commentArea.textContent = "";
                        const textarea = document.createElement('textarea');
                        textarea.name = 'comment';
                        textarea.placeholder = '대댓글을 입력하세요';
                        textarea.style.resize = 'none';

                        const submitBtn = document.createElement('input');
                        submitBtn.type = 'button';
                        submitBtn.value = '대댓글쓰기';
                        
                        commentArea.appendChild(textarea);
                        commentArea.appendChild(submitBtn);

                        // 댓글 영역에 삽입
                        li.appendChild(commentArea);
                        commentArea.classList.toggle('active');
                        submitBtn.addEventListener('click', function() {
                            console.log(reRef);
                            writeComment(reRef);

                         });

                    });

                }else{
                    const tabIcon = document.createElement('i');
                    tabIcon.classList.add('bi', 'bi-arrow-return-right' ,'red' , 'large-icon');
                    
                    div1.appendChild(tabIcon);
                    div1.appendChild(document.createTextNode(x[i].writerName + ' ' + x[i].reEnrollEate));
                    
                    div2.appendChild(document.createTextNode('\u00A0\u00A0\u00A0\u00A0'));
                    div2.appendChild(document.createTextNode(x[i].reContent));
                }
                li.appendChild(div1);
                li.appendChild(div2); 
                if(x[i].reNo === x[i].reRef){
                    li.appendChild(btn);                   

                }
                ul.appendChild(li);
            }

        } ,
        error : function(e){
            console.log(e);
        } ,
    });
}

loadComment();

</script>






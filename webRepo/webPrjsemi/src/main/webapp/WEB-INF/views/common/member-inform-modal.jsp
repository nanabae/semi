<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${root}/static/css/common/member-inform-modal.css">

<button onclick="toggleModal();">창닫기</button>
<div id="modal-body">
    <p><strong>닉네임:</strong>  <span class="writer-name"></span></p>
    <p><strong>포인트:</strong> </p>
                              
        <form  class="myForm" action="${root}/memo/write" method="post">
            <input type="hidden" class="drama-writer" name="dramaWriter">
            <input type="hidden" class="writer-name" name="writerName" >
            <input type="hidden"  name="dramaNum" value='${ vo.dramaNum }' >
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
<button>쪽지</button>
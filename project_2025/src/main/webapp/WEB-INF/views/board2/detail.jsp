<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>일상공유 커뮤니티</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
	<link href="/resources/static/css/search_bar.css" rel="stylesheet">
	<link href="/resources/static/css/header.css" rel="stylesheet"> 
</head>
<body>
	<header class="p-3 text-bg-dark">
		<jsp:include page="../includes/header.jsp" />
	</header>
	<div id="article-main" class="container">
    <div id="article-header" class="py-5 text-center">
      <h1>${item.title}</h1>
    </div>

    <div class="row g-5">
      <section class="col-md-3 col-lg-4 order-md-last">
        <aside>
          <p><span id="nickname">${item.userId}</span></p>
          <p><a id="email" href="mailto:${item.member.email}">${item.member.email}</a></p>
          <p><time id="created-at" ><fmt:formatDate value="${item.regDt}" pattern="yyyy-MM-dd"/></time></p>
          <p><span id="hashtag" class="badge text-bg-secondary mx-1"><a class="text-reset">#java</a></span></p>
        </aside>
      </section>

      <article id="article-content" class="col-md-9 col-lg-8">
        <pre>${item.postContent} postId = ${item.postId}</pre>
      </article>
    </div>

    <div class="row g-5" id="article-buttons">
      <form id="delete-article-form">
        <div class="pb-5 d-grid gap-2 d-md-block">
          <a href="update/${item.postId}" class="btn btn-success me-md-2" role="button" id="update-article">수정</a>
          <a href="delete/${item.postId}"><button class="btn btn-danger me-md-2" type="button" id="btn-del">삭제</button></a>
          <button class="btn btn-primary" type="button" id="list-btn">목록</button>
        </div>
      </form>
    </div>

	<!-- 댓글 시작 -->
    <div class="row g-5">
      <section>
        <form class="row g-3 comment-form">
		  <input type="hidden" id="postId" name="postId" value="${item.postId}">
		  <input type="hidden" name="userId" value="${item.userId}">
		  <input type="hidden"  name="replyClass" value="0">
          <input type="hidden"  name="replyGroup" value="0">
          <input type="hidden"  name="replyOrder" value="0">
          <div class="col-md-9 col-lg-8">
            <label for="comment-textbox" hidden>댓글</label>
            <textarea class="form-control comment-textbox" id="comment-textbox" name="replyContent" placeholder="댓글 쓰기.." rows="3" required></textarea>
          </div>
          <div class="col-md-3 col-lg-4">
            <label for="comment-submit" hidden>댓글 쓰기</label>
            <button class="btn btn-primary" id="comment-submit" type="submit">쓰기</button>
          </div>
        </form>

        <div id="replyArea"></div>
      </section>
    </div>
    <!-- 댓글 끝 -->

    <div class="row g-5">
      <nav id="pagination" aria-label="Page navigation">
        <ul class="pagination">
          <li class="page-item">
          	<c:choose>
          	  <c:when test="${item.prevPostId != 9999}">
          		<a class="page-link" href="${item.prevPostId}?page=${param.page}&condition=${param.condition}&search=${param.search}" aria-label="Previous">
	              <span aria-hidden="true">&laquo; prev</span>
	            </a>
          	  </c:when>
          	  <c:otherwise>
          		이전글이 없습니다.
          	  </c:otherwise>
          	</c:choose>
          </li>
          <li class="page-item">
            <c:choose>
          	  <c:when test="${item.nextPostId != 9999}">
          		<a class="page-link" href="${item.nextPostId}?page=${pager.page}${pager.query}" aria-label="Next">
	              <span aria-hidden="true">next &raquo;</span>
	            </a>
          	  </c:when>
          	  <c:otherwise>
          		다음글이 없습니다.
          	  </c:otherwise>
          	</c:choose>
          </li>
        </ul>
      </nav>
    </div>
  </div>
	
	<footer class="py-3 my-4">
		<jsp:include page="../includes/footer.jsp" />
	</footer>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>	
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript">
// 게시글 삭제 알림창
$(function(){
	$("#btn-del").click(function(){
		if (!confirm("게시글을 삭제하시겠습니까?")) {
			return false;
		}
	});
});

// 목록버튼 클릭시 이전페이지로 이동
$("#list-btn").on("click", function(){
	history.back();
});

// 댓글 조회
var contextPath = "${pageContext.request.contextPath}";
var postId = "${item.postId}";

function selectReplyList() {
  $.ajax({
    url: contextPath + "/reply/selectReplyList",
    data: { postId: postId },
    dataType: "json",
    success: function(data) {
      //console.log("data is array:", Array.isArray(data));
      console.log("댓글 데이터:", data);
    	
      $("#replyArea").empty();

      if (!data || data.length === 0) {
        $("#article-comments").html("<li>댓글이 없습니다.</li>");
        return;
      }
      
      for (let reply of data) {
        //console.log("단일 댓글:", reply);
        
        if (reply.replyClass === 0) {
        	let repliesHtml = '';
	        repliesHtml += `
	          <ul id="article-comments" class="row col-md-10 col-lg-8 pt-3">
	        	<li class="parent-comment" id="parent-comment\${reply.replyId}">
	            <form class="comment-delete-form">
	              <input type="hidden" class="article-id" name="replyId" value="\${reply.replyId}">
	              <div class="row">`
	                if (reply.delYn.trim() === 'N') {
	        		repliesHtml += `
	              	  <div class="col-md-10 col-lg-9">
	                    <strong>\${reply.userId}</strong>
	                    <small><time>\${reply.regDt}</time></small>
	                    <p class="mb-1">\${reply.replyContent.replace(/(?:\r\n|\r|\n)/g, '<br/>')}</p>
	                  </div>`
	              	}
	                  if (reply.delYn.trim() === 'Y') {
		        		repliesHtml += `
		              	  <div class="col-md-10 col-lg-9">
		        			<strong>\${reply.userId}</strong>
		                    <small><time>\${reply.regDt}</time></small>
		                    <p class="mb-1" style="color:red">삭제된 댓글입니다.</p>
		                  </div>`
		              	}
	              	repliesHtml += `
	                <div class="col-2 mb-3 align-self-center">
	                  <button type="button" class="reply-del btn btn-outline-danger">삭제</button>
	                </div>
	              </div>
	            </form>
	            
	            <div class="row">
	              <details class="col-md-10 col-lg-9 mb-4">
	                <summary>댓글 달기</summary>
	                <form class="comment-form">
	                <input type="hidden"  id="postId" name="postId" value="\${reply.postId}">
	                <input type="hidden" class="parent-comment-id" name="replyGroup" value="\${reply.replyId}">
	                <input type="hidden" class="parent-comment-id" name="replyClass" value="\${reply.replyClass + 1}">
	                <input type="hidden" class="parent-comment-id" name="replyOrder" value="\${reply.replyOrder}">
	                <input type="hidden"  name="userId">
	                  <textarea class="form-control comment-textbox" name="replyContent" id="answer-textbox" placeholder="댓글 쓰기.." rows="2" required></textarea>
	                  <button class="form-control btn btn-primary mt-2" type="button" onclick="fnc_Chiled_Reply_Insert(this)">쓰기</button>
	                </form>
	              </details>
	            </div>
	            </li>
	          </ul>`;
	          
	        $("#replyArea").append(repliesHtml);
	        
        } else if(reply.replyClass === 1){
        	let replyParentHtml = '';
        	replyParentHtml += `
        	  <ul class="row me-0">
                <li class="child-comment">
                  <form class="comment-delete-form">
                    <input type="hidden" class="article-id" value="\${reply.replyId}">
                    <div class="row">
                      <div class="col-md-10 col-lg-9">
                        <strong>\${reply.userId}</strong>
                        <small><time>\${reply.regDt}</time></small>
                        <p class="mb-1">\${reply.replyContent.replace(/(?:\r\n|\r|\n)/g, '<br/>')}</p>
                      </div>
                      <div class="col-2 mb-3 align-self-center">
                        <button type="button" class="reply-del btn btn-outline-danger">삭제</button>
                      </div>
                    </div>
                  </form>
                </li>
              </ul>`;
            
        	$('#parent-comment'+reply.replyGroup).append(replyParentHtml); 
        }
      }
    }, error: function() {
    	alert("댓글 조회 실패");
    }
  });
}

$(function() {
  selectReplyList();
});

// 댓글 등록
$(".comment-form").on("submit", function(ev){
	ev.preventDefault();
	
	if(!confirm("댓글을 작성하시겠습니까?")){
		return false;
	}
	
	let replyContent = $("#comment-textbox").val();
	
	let formData = {
			postId: $("input[name='postId']").val(),
			userId: $("input[name='userId']").val(),
			replyContent: replyContent
		};
	
	$.ajax({
		url: contextPath + "/reply/insertReply",
		type: "POST",
		contentType: "application/json; charset=UTF-8",
		data: JSON.stringify(formData),
		success: function(data){
			if (data === "success") {
				$("#comment-textbox").val("");
				selectReplyList();
			} else {
				alert("댓글 등록 실패");
			}
		},
		error: function(){
			alert("서버 오류");
		}
	});
});

// 댓글 삭제
$(document).on("click", ".reply-del", function(ev){
	ev.preventDefault();
	
	if(!confirm("댓글을 삭제하시겠습니까?")) {
		return false;
	}
	
	let replyId = $(this).closest("form").find(".article-id").val();
	
	$.ajax({
		url: contextPath + "/reply/deleteReply",
		type: "POST",
		data: { replyId: replyId },
		success: function(data){
			if (data === "success") {
				selectReplyList();
			} else {
				alert("댓글 삭제 실패");
			}
		},
		error: function(error){
			alert("에러: " + error.status);
		}
	});
});

// 답글 등록
function fnc_Chiled_Reply_Insert(_that){
	
	if(!confirm("댓글을 작성하시겠습니까?")){
		return false;
	}
	
	let reply_add = $(_that.closest('form')).serialize();
	
	$.ajax({
	    type: "post",
	    url: contextPath + "/reply/insertReplyAnswer",
	    data: reply_add,
	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	    success: function(data){
	  	  //console.log(data);
	  	  $("#answer-textbox").val("");
	  	  selectReplyList();
	    },
	    error: function(a,b,c){ // ajax실패시 원인
	 	   alert(c);
	    }
	}); 
}
</script>
</body>
</html>
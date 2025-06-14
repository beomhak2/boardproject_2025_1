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
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
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
        <pre>${item.postContent}</pre>
      </article>
    </div>

    <div class="row g-5" id="article-buttons">
      <form id="delete-article-form">
        <div class="pb-5 d-grid gap-2 d-md-block">
          <a href="update/${item.postId}" class="btn btn-success me-md-2" role="button" id="update-article">수정</a>
          <a href="delete/${item.postId}"><button class="btn btn-danger me-md-2" type="button" id="btn-del">삭제</button></a>
          <button class="btn btn-primary" type="button" onclick="history.back();">목록</button>
        </div>
      </form>
    </div>

	<!-- 댓글 시작 -->
    <div class="row g-5">
      <section>
        <form class="row g-3 comment-form">
			<input type="hidden" name="postId" value="${item.postId}">
			<input type="hidden" name="userId" value="${item.userId}">
          <div class="col-md-9 col-lg-8">
            <label for="comment-textbox" hidden>댓글</label>
            <textarea class="form-control comment-textbox" id="comment-textbox" name="replyContent" placeholder="댓글 쓰기.." rows="3" required></textarea>
          </div>
          <div class="col-md-3 col-lg-4">
            <label for="comment-submit" hidden>댓글 쓰기</label>
            <button class="btn btn-primary" id="comment-submit" type="submit">쓰기</button>
          </div>
        </form>

        <ul id="article-comments" class="row col-md-10 col-lg-8 pt-3">
			<!-- 댓글 조회 ajax -->
        </ul>
      </section>
    </div>
    <!-- 댓글 끝 -->

    <div class="row g-5">
      <nav id="pagination" aria-label="Page navigation">
        <ul class="pagination">
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo; prev</span>
            </a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
              <span aria-hidden="true">next &raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
	
	<footer class="py-3 my-4">
		<jsp:include page="../includes/footer.jsp" />
	</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>	
<script type="text/javascript">
$(function(){
	$("#btn-del").click(function(){
		if (!confirm("삭제하시겠습니까?")) {
			return false;
		}
	});
});

// 댓글 조회
var contextPath = "${pageContext.request.contextPath}";
var postId = "${item.postId}";

function selectReplyList() {
  $.ajax({
    url: contextPath + "/reply/selectReplyList",
    data: { postId: postId },
    dataType: "json",
    success: function(result) {
    	//console.log("result is array:", Array.isArray(result));
    	//console.log("댓글 데이터:", result);

      if (!result || result.length === 0) {
        $("#article-comments").html("<li>댓글이 없습니다.</li>");
        return;
      }

      let repliesHtml = "";

      for (let reply of result) {
        //console.log("단일 댓글:", reply);
        repliesHtml += `
        	<li class="parent-comment">
            <form class="comment-delete-form">
              <input type="hidden" class="article-id" value="\${reply.replyId}">
              <div class="row">
                <div class="col-md-10 col-lg-9">
                  <strong>\${reply.userId}</strong>
                  <small><time>\${reply.regDt}</time></small>
                  <p class="mb-1">\${reply.replyContent}</p>
                </div>
                <div class="col-2 mb-3 align-self-center">
                  <button type="submit" class="btn btn-outline-danger">삭제</button>
                </div>
              </div>
            </form>
          </li>`;
      }

      //console.log("HTML:", repliesHtml);
      $("#article-comments").html(repliesHtml);
    },
    error: function() {
      alert("댓글 조회 실패");
    }
  });
}

$(function() {
  selectReplyList();
});

//댓글 등록
$(".comment-form").on("submit", function(e){
	e.preventDefault();
	
	let replyContent = $("#comment-textbox").val();
	if (!replyContent.trim()){
		alert("댓글 내용을 입력하세요.");
		return;
	}
	
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
		success: function(result){
			if (result === "success") {
				$("#comment-textbox").val("");
				selectReplyList();
			} else {
				alert("댓글 등록 실패");
			}
		},
		error: function(){
			alert("서버 오류로 댓글 등록 실패");
		}
	});
});

</script>
</body>
</html>
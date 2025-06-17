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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
	<header class="p-3 text-bg-dark">
		<jsp:include page="../includes/header.jsp" />
	</header>
	<!-- 제목 -->
	<main id="article-main" class="container">
	    <section id="article-header" class="py-5 text-center">
	      <h1>${post.title}</h1>
	    </section>
		<!-- 유저, 메일, 작성일 및 수정일 -->
	    <section class="row g-5">
	        <aside class="col-md-3 col-lg-4 order-md-last">
	          <p><span id="nickname">${post.userId}</span></p>
	          <p><a id="email" href="mailto:${post.member.email}">${post.member.email}</a></p>
	          <c:set var="displayDate" value="${not empty post.mdfDt ? post.mdfDt : post.regDt}" />
	          <c:set var="formattedDate">
	          	<fmt:formatDate value="${displayDate}" pattern="yyyy-MM-dd"/>
	          </c:set>
	          <p><time id="created-at" datetime="${formattedDate}">${formattedDate}</time></p>
	          <p><span id="hashtag" class="badge text-bg-secondary mx-1"><a class="text-reset">#java</a></span></p>
	        </aside>
	        
	        <!-- 내용 -->	
		    <article id="article-content" class="col-md-9 col-lg-8">
		        <pre>${post.postContent}</pre>
		    </article>
	    </section>

		<!-- 버튼 -->
	    <section class="row g-5" id="article-buttons">
	      <form id="delete-article-form" action="delete/${post.postId}" method="post">
	        <div class="pb-5 d-grid gap-2 d-md-block">
	          <a href="/board1/update/${post.postId}" class="btn btn-success me-md-2">수정</a>
	          <button id="btn-del" class="btn btn-danger me-md-2" type="submit">삭제</button>
	          <a href="<c:url value='/board1/list'/>?${pager.getQueryString(pager.page)}" role="button" class="btn btn-success me-md-2">목록</a>
	        </div>
	      </form>
	    </section>

	    <section class="row g-5">
	     	<!-- 댓글 쓰기 및 버튼 -->
	       <form class="row g-3" id="comment-form" method="post">
	         <input type="hidden" name="postId" value="${post.postId}" />
	         <input type="hidden" name="userId" value="${post.userId}" />
	         <input type="hidden" id="parentReplyId" name="parentReplyId" value="" />
	         
	         <div class="col-md-9 col-lg-8">
	           <label for="replyContent" class="visually-hidden">댓글</label>
	           <textarea 
	           		id="replyContent"
	           		name="replyContent"
	           		class="form-control" 
	           		placeholder="댓글 쓰기.." 
	           		rows="3" 
	           		required
	       		></textarea>
	         </div>
	         <div class="col-md-3 col-lg-4 d-flex align-items-start gap-2">
	           <button class="btn btn-primary" id="comment-submit" type="submit">쓰기</button>
	           <button type="button" id="cancel-reply" class="btn btn-secondary" style="display:none;">취소</button>
	         </div>
	       </form>
	       
			<!-- 댓글 목록 보여주기 -->
	       <ul id="article-comments" class="row col-md-10 col-lg-8 pt-3 list-unstyled">
	       		<c:choose>
	       			<c:when test="${empty replyList}">
	       				<li>댓글이 없습니다.</li>
	       			</c:when>
	       			<c:otherwise>
	       				<c:forEach var="reply" items="${replyList}">
	       					<li class="list-group-item"
	       						style="padding-left: ${reply.replyClass * 20}px;"
	       						data-reply-id="${reply.replyId}"
	       						data-parent-reply-id="${reply.parentReplyId}">
	       						<strong>${reply.userId}</strong>
	       						<small class="text-muted ms-2">
	       							<fmt:formatDate value="${reply.regDt}" pattern="yyyy-MM-dd"/>
	       						</small>
	       						<p style="white-space: pre-wrap;">${reply.replyContent}</p>
	       						<button type="button" class="btn btn-link btn-sm btn-reply" data-reply-id="${reply.replyId}">답글</button>
	       					</li>
	       				</c:forEach>
	       			</c:otherwise>
	       		</c:choose>
	       </ul>
	     </section>

		<!-- 댓글 페이징 (필요시 확장) -->
	    <section class="row g-5">
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
	    </section>
  </main>
	
	<footer class="py-3 my-4">
		<jsp:include page="../includes/footer.jsp" />
	</footer>

<script>
	const contextPath = "${pageContext.request.contextPath}";
	const postId = "${post.postId}";
	
	function loadComments(){
		$.ajax({
			url: `${contextPath}/reply/list`,
			type: 'GET',
			data: { postId },
			dataType: "html",
			success: function(html) {
				const $list = $("#article-comments");
				$list.html(html.trim() ? html : "<li>댓글이 없습니다.</li>");	
			},
			error: function() {
				alert("댓글 조회 실패");
			}
		});
	}
	
	$("#comment-form").on("submit", function(e){
		e.preventDefault();
		
		const replyContent = $("#replyContent").val().trim();
		if(!replyContent){
			alert("댓글 내용을 입력해주세요.");
			return;
		}
		
		const formData = {
			postId: postId,
			replyContent: replyContent,
			parentReplyId: $("#parentReplyId").val() || null
		};
		
		$.ajax({
			url: `${contextPath}/reply/write`,
			type: "POST",
			data: formData,
			success: function(response) {
				$("#replyContent").val("");
				$("#parentReplyId").val("");
				$("#cancel-reply").hide();
				loadComments();
			},
			error: function () {
				alert("댓글 작성에 실패했습니다.");
			}
		});
	});

	// 답글 버튼 클릭 시 부모 댓글 id 설정 및 textarea에 포커스
	$(document).on("click", ".btn-reply", function(){
		const parentReplyId = $(this).data("reply-id");
		$("#parentReplyId").val(parentReplyId);
		$("#replyContent").focus();
		$("#cancel-reply").show();
	});

	// 답글 작성 취소 버튼 동작
	$("#cancel-reply").on("click", function(){
		$("#parentReplyId").val("");
		$("#replyContent").val("");
		$(this).hide();
	});

	$(document).ready(function() {
		loadComments();
	});
</script>

<script 
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"
></script>	
</body>
</html>

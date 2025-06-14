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
	      <form id="delete-article-form" action="delete/${postId}" method="post">
	        <div class="pb-5 d-grid gap-2 d-md-block">
	          <a href="$update/${item.postId}" class="btn btn-success me-md-2">수정</a>
	          <button id="btn-del" class="btn btn-danger me-md-2" type="submit">삭제</button>
	          <a href="/board1/list" role="button" class="btn btn-success me-md-2">목록</a>
	        </div>
	      </form>
	    </section>
	
	     <section class="row g-5">
	     	<!-- 댓글 쓰기 및 버튼 -->
	       <form class="row g-3" id="comment-form" method="post">
	         <input type="hidden" name="postId" value="${item.postId}" />
	         <input type="hidden" name="userId" value="${item.userId}" />
	         
	         <div class="col-md-9 col-lg-8">
	           <label for="comment-textbox" class="visually-hidden">댓글</label>
	           <textarea 
	           		id="comment-textbox"
	           		name="replyContent"
	           		class="form-control" 
	           		placeholder="댓글 쓰기.." 
	           		rows="3" 
	           		required 
	       		></textarea>
	         </div>
	         <div class="col-md-3 col-lg-4">
	           <button class="btn btn-primary" id="comment-submit" type="submit">쓰기</button>
	         </div>
	       </form>
	       
		<!-- 댓글 목록 보여주기 -->
	       <ul id="article-comments" class="row col-md-10 col-lg-8 pt-3 list-unstyled">
				<li>댓글 로딩 중</li>
	       </ul>
	     </section>
		<!-- 댓글 먼기기 버튼 -->
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
	
<script 
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"
></script>
<script>
	$(function() {
		const contextPath = '${pageContext.request.contextPath}';
		const postId = '${item.postId}';
		
		function loadComments(){
			$.ajax({
				url: contextPath + "/reply/list",
				type: 'get',
				data: { postId: postId },
				dataType: "html",
				success: function(html) {
					$("#article-comments").html(html);
					if(!html.trim()){
						$("#article-comments").html("<li>댓글이 없습니다.</li>");	
					}
				},
				error: function() {
					alert("댓글 조회 실패");
				},
			});
		}
		
	});
</script>	
</body>
</html>

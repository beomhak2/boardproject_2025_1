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
	      <h1><c:out value="${post.title}" /></h1>
	    </section>
		<!-- 유저, 메일, 작성일 및 수정일 -->
	    <section class="row g-5">
	        <aside class="col-md-3 col-lg-4 order-md-last">
	          <span id="nickname"><c:out value="${post.userId}" /></span>
				<a href="mailto:<c:out value="${post.member.email}" />">
				  <c:out value="${post.member.email}" />
				</a>
	          <c:set var="displayDate" value="${not empty post.mdfDt ? post.mdfDt : post.regDt}" />
	          <c:set var="formattedDate">
	          	<fmt:formatDate value="${displayDate}" pattern="yyyy-MM-dd"/>
	          </c:set>
	          <p><time id="created-at" datetime="${formattedDate}">${formattedDate}</time></p>
	          <p><span id="hashtag" class="badge text-bg-secondary mx-1"><a class="text-reset">#java</a></span></p>
	        </aside>
	        
	        <!-- 내용 -->	
		    <article id="article-content" class="col-md-9 col-lg-8">
		        <pre><c:out value="${post.postContent}" /></pre>
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
   				<!-- 댓글 조회 ajax -->
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
<script id="reply-template" type="text/x-handlebars-template">
	<li class="list-group-item" style="margin-left: {{indent}}px">
		<div>
			<strong>{{userId}}</strong>
			<small class="text-muted">{{regDt}}</small>
			<p style="white-space: pre-wrap;">{{replyContent}}</p>

			{{#if canReply}}
			<button class="replyBtn btn btn-link btn-sm" data-reply-id="{{replyId}}">답글</button>
			<div class="replyForm mt-2" id="form-{{replyId}}" style="display:none;">
				<textarea class="form-control childContent mb-1" rows="2"></textarea>
				<button class="submitReply btn btn-sm btn-primary" data-parent="{{replyId}}">등록</button>
			</div>
			{{/if}}
		</div>
	</li>
</script>

<script>
$(function() {
  const postId = <c:out value="${post.postId}"/>;
  const contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
  const baseUrl = contextPath + '/' + postId + '/reply';

  const source = $("#reply-template").html();
  const template = Handlebars.compile(source);

  function loadReplies() {
    $.ajax({
      url: baseUrl + "/list",
      method: 'GET',
      success: function(data) {
        const container = $("#article-comments");
        container.empty();

        if (!data || data.length === 0) {
          container.append('<p>댓글이 없습니다.</p>');
          return;
        }
        renderReplies(data, container);
      },
      error: function() {
        alert("댓글 목록 불러오기 실패");
      }
    });
  }

  // 재귀 렌더링 함수
  function renderReplies(replies, container) {
    replies.forEach(reply => {
      // indent : replyClass * 20px 들여쓰기 (replyClass = replyDTO.getReplyClass())
      reply.indent = reply.replyClass * 20;

      // 답글 버튼 노출 조건 : replyClass가 0 이상 (최상위도 답글 가능하면 0 이상으로 수정 가능)
      reply.canReply = reply.replyClass >= 0;

      const html = template(reply);
      container.append(html);

      if (reply.childReplies && reply.childReplies.length > 0) {
        renderReplies(reply.childReplies, container);
      }
    });
  }

  function submitReply(replyContent, parentId = null, callback) {
    if (!replyContent.trim()) {
      alert("댓글 내용을 입력해주세요.");
      return;
    }
    $.ajax({
      url: baseUrl + "/write",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify({
        postId: postId,
        replyContent: replyContent,
        parentId: parentId
      }),
      success: function(res) {
        if (res.status === "success") {
          if (callback) callback();
        } else {
          showAlert("등록에 실패했습니다.");
        }
      },
      error: function() {
       	  showAlert("댓글 등록에 실패했습니다. 다시 시도해주세요.");
      }
    });
  }

  $("#comment-form").submit(function() {
    const content = $("#replyContent").val();
    const $btn = $("#comment-submit");
    $btn.prop("disabled", true);

    submitReply(content, null, function() {
      $("#replyContent").val('').focus();
      $btn.prop("disabled", false);
      loadReplies();
    });
  });

  $(document).on("click", ".replyBtn", function() {
    $(".replyForm").hide();
    const id = $(this).data("reply-id");
    $(`#form-${id}`).toggle();
  });

  $(document).on("click", ".submitReply", function() {
    const parentId = $(this).data("parent");
    const $textarea = $(this).siblings("textarea");
    const content = $textarea.val();

    submitReply(content, parentId, function() {
      $textarea.val('');
      $(".replyForm").hide();
      loadReplies();
    });
  });

  loadReplies();
});
</script>


<script 
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"
></script>	
</body>
</html>

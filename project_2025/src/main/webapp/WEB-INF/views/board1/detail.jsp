<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>일삼공유 커뮤니티</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="/resources/static/css/search_bar.css" rel="stylesheet">
	<link href="/resources/static/css/header.css" rel="stylesheet"> 
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/handlebars@4.7.7/dist/handlebars.min.js"></script>
	<style>.hidden {display: none;}</style>
</head>
<body>
<header class="p-3 text-bg-dark">
	<jsp:include page="../includes/header.jsp" />
</header>
<main id="article-main" class="container">
<section id="article-header" class="py-5 text-center">
  <h1><c:out value="${post.title}" /></h1>
</section>
<section class="row g-5">
  <aside class="col-md-3 col-lg-4 order-md-last">
    <span id="nickname"><c:out value="${post.userId}" /></span><br>
    <c:if test="${not empty post.member.email}">
      <a href="mailto:${post.member.email}">${post.member.email}</a>
    </c:if>
    <c:set var="displayDate" value="${not empty post.mdfDt ? post.mdfDt : post.regDt}" />
    <fmt:formatDate var="formattedDate" value="${displayDate}" pattern="yyyy-MM-dd" />
    <p><time datetime="${formattedDate}">${formattedDate}</time></p>
  </aside>
  <article id="article-content" class="col-md-9 col-lg-8">
    <pre><c:out value="${post.postContent}" /></pre>
  </article>
</section>
<section class="row g-5" id="article-buttons">
  <form id="delete-article-form" action="delete/${post.postId}" method="post">
    <div class="pb-5 d-grid gap-2 d-md-block">
      <a href="/board1/update/${post.postId}" class="btn btn-success me-md-2">수정</a>
      <button id="btn-del" class="btn btn-danger me-md-2" type="submit">삭제</button>
      <a href="<c:url value='/board1/list'/>?${pager.getQueryString(pager.page)}" class="btn btn-success me-md-2">목록</a>
    </div>
  </form>
</section>
<section class="row g-5">
  <form class="row g-3 comment-form" id="comment-form">
    <div class="col-md-9 col-lg-8">
      <textarea class="form-control comment-textbox" id="replyContent" name="replyContent" placeholder="댓글 쓰기.." rows="3" required></textarea>
    </div>
    <div class="col-md-3 col-lg-4">
      <button class="btn btn-primary" id="comment-submit" type="submit">쓰기</button>
    </div>
  </form>
  <ul id="comments-list" class="list-group"></ul>
</section>
</main>
<footer class="py-3 my-4">
	<jsp:include page="../includes/footer.jsp" />
</footer>
<!-- 댓글 템플레이트 -->
<script id="reply-partial" type="text/x-handlebars-template">
<li class="list-group-item" style="margin-left:{{calcIndent this}}px">
  <div>
    <strong>{{userId}}</strong>
    <small class="text-muted ms-2">{{regDt}}</small>
    <p style="white-space:pre-wrap;">{{replyContent}}</p>
    <button class="btn btn-link btn-sm reply-btn" data-reply-id="{{this.replyId}}">답글</button>
  </div>
  {{#if childReplies}}
  <ul class="list-group mt-2">
    {{#each childReplies}}
      {{> replyPartial}}
    {{/each}}
  </ul>
  {{/if}}
</li>
</script>
<%--  --%>
<script id="comment-template" type="text/x-handlebars-template">
{{#if replies.length}}
  {{#each replies}}
    {{> replyPartial}}
  {{/each}}
{{else}}
  <p class="text-muted">댓글이 없습니다.</p>
{{/if}}
</script>
<!-- 답글 템플레이트 -->
<script id="reply-form-template" type="text/x-handlebars-template">
<div class="reply-form mt-2" id="replyForm-{{replyId}}">
  <textarea class="form-control reply-text mb-1" rows="2" placeholder="답글 입력..."></textarea>
  <button class="btn btn-sm btn-primary submit-reply-btn" data-parent="{{replyId}}">등록</button>
</div>
</script>
<script>
$(function() {
  const postId = "${post.postId}";
  const contextPath = "${pageContext.request.contextPath}";
  const apiBase = `${contextPath}/board1/${postId}/replies`;
  const loginUserId = "${loginUser.userId}" || "USER";

  Handlebars.registerHelper('calcIndent', function(reply) {
    return reply.parentId && reply.parentId != 0 ? 20 : 0;
  });

  Handlebars.registerPartial('replyPartial', $('#reply-partial').html());
  const commentTemplate = Handlebars.compile($('#comment-template').html());
  const replyFormTemplate = Handlebars.compile($('#reply-form-template').html());

  function loadComments() {
    $.get(apiBase, function(data) {
      const html = commentTemplate({ replies: data });
      $('#comments-list').html(html);
    }).fail(function() {
      alert('댓글을 불러오는 중 오류가 발생했습니다.');
    });
  }

  function submitReply(content, parentId, done) {
    const payload = {
      replyContent: content,
      parentId: parentId || 0,
      userId: loginUserId
    };

    $.ajax({
      url: apiBase,
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(payload),
      success: function(res) {
        if (res.status === 'success') {
          done && done(res.reply);
        } else {
          alert(res.message || '댓글 등록 실패');
        }
      },
      error: function() {
        alert('댓글 등록 실패');
      }
    });
  }

  $('#comment-form').submit(function(e) {
    e.preventDefault();
    const content = $('#replyContent').val().trim();
    if (!content) return;

    const $btn = $(this).find('button[type="submit"]');
    $btn.prop('disabled', true);

    submitReply(content, 0, () => {
      $('#replyContent').val('');
      $btn.prop('disabled', false);
      loadComments();
    });
  });

  $(document).on('click', '.reply-btn', function() {
    const replyId = $(this).data('reply-id');
    $('.reply-form').remove();
    const $parent = $(this).closest('li');
    if ($(`#replyForm-${replyId}`).length) return;
    const formHtml = replyFormTemplate({ replyId });
    $parent.append(formHtml);
  });

  $(document).on('click', '.submit-reply-btn', function() {
    const parentId = $(this).data('parent');
    const $textarea = $(this).siblings('textarea.reply-text');
    const content = $textarea.val().trim();
    const $btn = $(this);

    if (!content) return;
    $btn.prop('disabled', true);

    submitReply(content, parentId, () => {
      $btn.prop('disabled', false);
      $textarea.val('');
      loadComments();
    });
  });

  loadComments();
});
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

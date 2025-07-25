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
          <p>
          	<time id="created-at" datetime="${item.regDt}">
  					<fmt:formatDate value="${item.regDt}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</time></p>
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
          <a href="/board3/update/${item.postId}" class="btn btn-success me-md-2" role="button" id="update-article">수정</a>
          <a href="/board3/delete/${item.postId}"><button class="btn btn-danger me-md-2" type="button">삭제</button></a>
          <%-- http://localhost:9090/board3/list?search=1&keyword=next 주소가 이런식으로 나오기때문에 list 페이지에서 값을 넘기고 여기서 받는형식으로 구상중 검색기록은 어쩌지..?--%>
          <a href="/board3/list?page=${page != null ? page : 1}" class="btn btn-success me-md-2" role="button" id="update-article">목록</a>
        </div>
      </form>
    </div>
    <div class="row g-5">
      <section>
        <form class="row g-3 comment-form">
          <input type="hidden" class="article-id">
          <div class="col-md-9 col-lg-8">
            <label for="comment-textbox" hidden>댓글</label>
            <textarea class="form-control comment-textbox" id="comment-textbox" placeholder="댓글 쓰기.." rows="3" required></textarea>
          </div>
          <div class="col-md-3 col-lg-4">
            <label for="comment-submit" hidden>댓글 쓰기</label>
            <button class="btn btn-primary" id="comment-submit" type="submit">쓰기</button>
          </div>
        </form>

        <ul id="article-comments" class="row col-md-10 col-lg-8 pt-3">
          <li class="parent-comment">
            <form class="comment-delete-form">
              <input type="hidden" class="article-id">
              <div class="row">
                <div class="col-md-10 col-lg-9">
                  <strong>Uno</strong>
                  <small>
                  		<time id="created-at" datetime="${item.regDt}">
  							<fmt:formatDate value="${item.regDt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</time>
					</small>
                  <p class="mb-1">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                    Lorem ipsum dolor sit amet
                  </p>
                </div>
                <div class="col-2 mb-3 align-self-center">
                  <button type="submit" class="btn btn-outline-danger">삭제</button>
                </div>
              </div>
            </form>

            <ul class="row me-0">
              <li class="child-comment">
                <form class="comment-delete-form">
                  <input type="hidden" class="article-id">
                  <div class="row">
                    <div class="col-md-10 col-lg-9">
                      <strong>Uno</strong>
                      <small><time>2022-01-01</time></small>
                      <p class="mb-1">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                        Lorem ipsum dolor sit amet
                      </p>
                    </div>
                    <div class="col-2 mb-3 align-self-center">
                      <button type="submit" class="btn btn-outline-danger">삭제</button>
                    </div>
                  </div>
                </form>
              </li>
            </ul>

            <div class="row">
              <details class="col-md-10 col-lg-9 mb-4">
                <summary>댓글 달기</summary>
                <form class="comment-form">
                  <input type="hidden" class="article-id">
                  <input type="hidden" class="parent-comment-id">
                  <textarea class="form-control comment-textbox" placeholder="댓글 쓰기.." rows="2" required></textarea>
                  <button class="form-control btn btn-primary mt-2" type="submit">쓰기</button>
                </form>
              </details>
            </div>
          </li>
        </ul>
      </section>
    </div>

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
</body>
</html>
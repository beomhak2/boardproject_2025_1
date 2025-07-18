<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fn" %>    
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
	
	<div class="container">
	<div id="article-form-header" class="py-5 text-center">
      <h1>게시글 작성</h1>
    </div>
    
    <form id="article-form" method="post" action="/board3/update/${item.postId}">
      <div class="row mb-3 justify-content-md-center">
        <label for="title" class="col-sm-2 col-lg-1 col-form-label text-sm-end">제목</label>
        <div class="col-sm-8 col-lg-9">
          <input type="text" class="form-control" id="title" name="title" value="${item.title}" required>
        </div>
      </div>
      <div class="row mb-3 justify-content-md-center">
        <label for="postContent" class="col-sm-2 col-lg-1 col-form-label text-sm-end">본문</label>
        <div class="col-sm-8 col-lg-9">
          <textarea class="form-control" id="postContent" name="postContent" rows="5" required>${item.postContent}</textarea>
        </div>
      </div>
      <div class="row mb-5 justify-content-md-center">
        <div class="col-sm-10 d-grid gap-2 d-sm-flex justify-content-sm-end">
          <button type="submit" class="btn btn-primary" id="submit-button">저장</button>
          <a href="../list"><button type="button" class="btn btn-secondary" id="cancel-button">취소</button></a>
        </div>
      </div>
    </form>
  </div>
	
	<footer class="py-3 my-4">
		<jsp:include page="../includes/footer.jsp" />
	</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>	
</body>
</html>
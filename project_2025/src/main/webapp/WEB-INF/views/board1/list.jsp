<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>일상공유 커뮤니티</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
	<link href="/resources/static/css/search_bar.css" rel="stylesheet">
	<link href="/resources/static/css/header.css" rel="stylesheet"> 
	<script src="https://code.jquery.com/jquery-latest.min.js"></script> 
	
</head>
<body>
	<header class="p-3 text-bg-dark">
		<jsp:include page="../includes/header.jsp" />
	</header>
	
	<div class="container">
	<div id="article-header" class="py-5 text-center">
		<h1>일상 게시판</h1>
    </div>
	<div class="row">
	    <div class="col-lg-12 card-margin">
	        <div class="card search-form">
	            <div class="card-body p-0">
	                <form id="search-form" method="get">
	                    <div class="row">
	                        <div class="col-12">
	                            <div class="row no-gutters">
	                                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
	                                    <select class="form-control" name="search">
	                                    	<option value="1">전체</option>
	                                        <option value="2">제목</option>
	                                        <option value="3">작성자</option>
	                                    </select>
	                                </div>
	                                <div class="col-lg-8 col-md-6 col-sm-12 p-0">
	                                    <input type="text" placeholder="검색" class="form-control" id="search" name="keyword"  value="${fn:escapeXml(pager.keyword)}">
	                                </div>
	                                <div class="col-lg-1 col-md-3 col-sm-12 p-0">
	                                    <button type="submit" class="btn btn-base">
	                                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
	                                    </button>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>
	<table class="table search-body">
		<thead>
	    	<tr>
				<th scope="col">#</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">날짜</th>
				<th scope="col">조회수</th>
	    	</tr>
		</thead> 
	<tbody>
		<c:forEach var="item" items="${list}">
		    <tr>
				<th scope="row">${item.rnum}</th>
				<td>
					<c:choose>
				        <c:when test="${fn:length(item.title) > 30}">
				            <a href="<c:url value='/board1/detail/${item.postId}'/>">
				                <c:out value="${fn:substring(item.title, 0, 30)}..." />
				            </a>
				        </c:when>
				        <c:otherwise>
		      				<a href="<c:url value='/board1/detail/${item.postId}${pager.getQueryString()}'/>">
		      					<c:out value="${item.title}"/>
		      				</a>
				        </c:otherwise>
				    </c:choose>
	      		</td>
		        <td>${item.userId}</td>
		        <td><fmt:formatDate value="${item.regDt}" pattern="yyyy-MM-dd"/></td>
		        <td>${item.viewCnt}</td>
		    </tr>
	    </c:forEach>
	    
	  </tbody>
	</table>
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	    <li class="page-item ${pager.page == 1 ? 'disabled' : ''}">
	      <a class="page-link" href="list?page=1${pager.getQueryString()}" aria-label="First">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    <li class="page-item ${pager.page == 1 ? 'disabled' : ''}">
	      <a class="page-link" href="list?page=${pager.getPrev()}${pager.getQueryString()}" aria-label="Previous">
	        <span aria-hidden="true">prev</span>
	      </a>
	    </li>
	    <c:forEach var="page" items="${pager.list}">
	    	<li class="page-item ${pager.page == page ? 'active' : ''}">
	    		<a class="page-link" href="list?page=${page}${pager.getQueryString()}">
	    			${page}
    			</a>
   			</li>
	    </c:forEach>
	    <li class="page-item ${pager.page == pager.list ? 'disabled' : ''}">
	      <a class="page-link" href="list?page=${pager.getNext()}${pager.getQueryString()}" aria-label="Next">
	        <span aria-hidden="true">next</span>
	      </a>
	    </li>
	    <li class="page-item ${pager.page == pager.list ? 'disabled' : ''}">
	      <a class="page-link" href="list?page=${pager.getLast()}${pager.getQueryString()}" aria-label="Last">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	  </ul>
	</nav>
	</div>
	<div class="row mb-5 justify-content-md-center">
       <div class="col-sm-10 d-grid gap-2 d-sm-flex justify-content-sm-end">
       		<a href="/board1/add" class="btn btn-primary" >등록</a>
       </div>
     </div>
	
	<footer class="py-3 my-4">
		<jsp:include page="../includes/footer.jsp" />
	</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous">
	$(document).ready(function() {
		$('#search').keydown(function(ev){
			if(ev.key === "Enter") {
				ev.preventDefault();
				
				$('#keyword').submit();
			}
		});
	});
	
</script>
</body>
</html>

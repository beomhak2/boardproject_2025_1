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
	    <!-- jquery cdn -->
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	
</head>
<body>
	<header class="p-3 text-bg-dark">
		<jsp:include page="../includes/header.jsp" />
	</header>
	
	<div id="article-main" class="container">
    <div id="article-header" class="py-5 text-center">
      <h1>${vo.title }</h1>
    </div>

    <div class="row g-5">
      <section class="col-md-3 col-lg-4 order-md-last">
        <aside>
          <p><span id="nickname">${vo.userId} </span></p>
          <p><a id="email" href="mailto:${vo.member.email }">${vo.member.email }</a></p>
          <fmt:formatDate  value="${vo.regDt}" var="regDateValue" pattern="yyyy-MM-dd"/>
          <p><time id="created-at" >${regDateValue }</time></p>
          <p><span id="hashtag" class="badge text-bg-secondary mx-1"><a class="text-reset">#java</a></span></p>
        </aside>
      </section>
      <article id="article-content" class="col-md-9 col-lg-8">
        <pre>${vo.postContent}</pre>
      </article>
    </div>

    <div class="row g-5" id="article-buttons">
      <form id="delete-article-form">
        <div class="pb-5 d-grid gap-2 d-md-block">
          <a class="btn btn-success me-md-2" role="button" id="update-article" href="/board4/update?postId=${vo.postId }">수정</a>
          <a class="btn btn-danger me-md-2" href="/board4/delete?postId=${vo.postId }">삭제</a>
          <c:choose>
          	<c:when test="${not empty param.search and not empty param.keyword}">
	          <a class="btn btn-primary" role="button" id="update-article" href="/board4/list?page=${param.page }&search=${param.search}&keyword=${param.keyword}">목록</a>
          	</c:when>
          	<c:otherwise>
          		<a class="btn btn-primary" role="button" id="update-article" href="/board4/list?page=${param.page }">목록</a>
          	</c:otherwise>
          </c:choose>
        </div>
      </form>
    </div>

    <div class="row g-5">
      <section>
        <form class="row g-3 comment-form" name="firstReplyAddForm" action="">
          <input type="hidden"  name="userId" value="${vo.userId }">
          <input type="hidden"  id="postId" name="postId" value="${vo.postId }">
          <input type="hidden"  name="replyClass" value="0">
          <input type="hidden"  name="replyGroup" value="0">
          <input type="hidden"  name="replyOrder" value="0">
          <div class="col-md-9 col-lg-8">
            <label for="comment-textbox" hidden>댓글</label>
            <textarea class="form-control comment-textbox" id="replyContent" name="replyContent" placeholder="댓글 쓰기.." rows="3" required cols="100" wrap="hard"></textarea>
          </div>
          <div class="col-md-3 col-lg-4">
            <label for="first_comment_submit" hidden>댓글 쓰기</label>
            <button type="button" class="btn btn-primary" id="first_comment_submit">쓰기</button>
          </div>
        </form>
		
		
		<div id="replyArea"></div>
      </section>
    </div>
    <div class="row g-5">
      <nav id="pagination" aria-label="Page navigation">
        <ul class="pagination">
          <li class="page-item">
          	<c:choose>
          	<c:when test="${vo.prevPostId != 9999 }">
	            <a class="page-link" href="/board4/detail?postId=${vo.prevPostId }&page=${param.page }&search=${param.search}&keyword=${param.keyword}" aria-label="Previous">
	              <span aria-hidden="true">&laquo; prev(<c:out value="${vo.prevPostContent }"/>)</span>
	            </a>
	            
          	</c:when>
          	<c:otherwise>
          		이전글이 없습니다.
          	</c:otherwise>
            </c:choose>
          </li>
          <li class="page-item">
          	<c:choose>
	          	<c:when test="${vo.nextPostId != 9999 }">
		            <a class="page-link" href="/board4/detail?postId=${vo.nextPostId }&page=${param.page }&search=${param.search}&keyword=${param.keyword}" aria-label="Next">
		              <span aria-hidden="true">next(<c:out value="${vo.nextPostContent }"/>) &raquo;</span>
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
<script type="text/javascript">
function fnc_Chiled_Reply_Insert(_that){
	console.log($(_that.closest('form')).serialize());
	
	if(!confirm("댓글을 작성하시겠습니까?")){
		return false;
	}
	
	var reply_add = $(_that.closest('form')).serialize();
	
	$.ajax({
	    type:"post",
	    url:"/board4/addReply",
	    data: reply_add,
	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	    success:function(data){
	  	  console.log(data);
	  	  getReplylist();
	    },
	    error:function(a,b,c){ // ajax실패시 원인
	 	   alert(c);
	    }
	}); 
}

function fnc_Reply_Delete(_that){
	console.log($(_that.closest('form')).serialize());
	
	if(!confirm("댓글을 삭제하시겠습니까?")){
		return false;
	}
	
	var reply_del = $(_that.closest('form')).serialize();
	
	$.ajax({
	    type:"post",
	    url:"/board4/deleteReply",
	    data: reply_del,
	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	    success:function(data){
	  	  console.log(data);
	  	  getReplylist();
	    },
	    error:function(a,b,c){ // ajax실패시 원인
	 	   alert(c);
	    }
	}); 
}

function getReplylist(){
	var postId = {postId : $('#postId').val()};
	$.ajax({
	    type:'post',
	    url:'/board4/replyList',
	    data : postId, 
	    dataType: "json",
	    success:function(data){
	  	  	console.log(data);
	  	  	$('#replyArea').empty();
	  		data.forEach(function(e){
	  			if (e.replyClass == 0) {
	  				var replyHtml = '';
		  			replyHtml += '<ul id="article-comments" class="row col-md-10 col-lg-8 pt-3">			  ';
		  			replyHtml += '<li class="parent-comment" id="parent-comment'+e.replyId+'">';	
		  			replyHtml += '<form class="comment-delete-form">                                          ';
		  			replyHtml += '      <input type="hidden" name="replyId" value="'+e.replyId+'"> ';
					replyHtml += '  	<div class="row">                                                     '; 
					replyHtml += '        <div class="col-md-10 col-lg-9">                                    ';
					replyHtml += '          <strong>'+e.userId+'</strong>                                     ';
					var date = new Date(e.regDt);
					var year = date.getFullYear();
					var month =	(date.getMonth()+1).toString().padStart(2,'0');
					var day = date.getDate().toString().padStart(2,'0')
									+'  '+ date.getHours()
									+':'+ date.getMinutes().toString().padStart(2,'0')
									+':'+ date.getSeconds().toString().padStart(2,'0');
					replyregDt = year+'-'+month+'-'+day;
					replyHtml += '          <small><time>'+replyregDt+'</time></small>                        '; 
					replyHtml += '          <p class="mb-1"><pre>'+e.replyContent+'</pre></p>                 ';                                  
					replyHtml += '        </div>                                                              ';
					replyHtml += '        <div class="col-2 mb-3 align-self-center">                          ';
					replyHtml += '          <button type="button" class="btn btn-outline-danger" onclick="fnc_Reply_Delete(this)">삭제</button> ';
					replyHtml += '        </div>                                                              ';
				    replyHtml += '    </div>                                                                  ';
			        replyHtml += '</form>                                                                     ';
			        replyHtml += '      <div class="row">';
				    replyHtml += '       <details class="col-md-10 col-lg-9 mb-4">';
				    replyHtml += '         <summary>답글 달기</summary>';
				    replyHtml += '         <form class="comment-form">';
				    replyHtml += '           <input type="hidden"  id="postId" name="postId" value="'+e.postId+'">    ';
				    replyHtml += '           <input type="hidden" class="parent-comment-id" name="replyGroup" value="'+e.replyId+'"> ';
			 	    replyHtml += '           <input type="hidden" class="parent-comment-id" name="replyClass" value="'+(e.replyClass+1)+'">';
				    replyHtml += '           <input type="hidden" class="parent-comment-id" name="replyOrder" value="'+(e.replyOrder+1)+'"> ';
				    replyHtml += '           <input type="hidden"  name="userId" value="'+e.userId+'">';
				    replyHtml += '           <textarea class="form-control comment-textbox" placeholder="댓글 쓰기.." rows="2" ';
				    replyHtml += '      	name="replyContent" cols="100" wrap="hard" required></textarea>';
				    replyHtml += '           <button class="form-control btn btn-primary mt-2" type="button"';
				    replyHtml += '           					onclick="fnc_Chiled_Reply_Insert(this)">쓰기</button>';
				    replyHtml += '         </form>';
				    replyHtml += '       </details>';
				    replyHtml += '      </div>  ';
				    replyHtml += '	</li>																		  ';	
			        replyHtml += '</ul>																		  ';
			        $('#replyArea').append(replyHtml);
				}else if(e.replyClass == 1){
	  				var replyParentHtml = '';
	  				replyParentHtml += '      <ul class="row me-0">';
	  				replyParentHtml += '      <li class="child-comment" id="parent-comment'+e.replyId+'">';
	  				replyParentHtml += '        <form class="comment-delete-form">';
	  				replyParentHtml += '          <input type="hidden" name="replyId" value="'+e.replyId+'"> ';
			        replyParentHtml += '          <div class="row">';
			        replyParentHtml += '            <div class="col-md-10 col-lg-9">';
			        replyParentHtml += '              <strong>'+e.userId+'</strong>';
			        var date = new Date(e.regDt);
					var year = date.getFullYear();
					var month =	(date.getMonth()+1).toString().padStart(2,'0');
					var day = date.getDate().toString().padStart(2,'0')
									+'  '+ date.getHours()
									+':'+ date.getMinutes().toString().padStart(2,'0')
									+':'+ date.getSeconds().toString().padStart(2,'0');
					replyregDt = year+'-'+month+'-'+day;
			        replyParentHtml += '              <small><time>'+replyregDt+'</time></small>';
			        replyParentHtml += '              <p class="mb-1"><pre>'+e.replyContent+'</pre></p>';
			        replyParentHtml += '            </div>';
			        replyParentHtml += '            <div class="col-2 mb-3 align-self-center">';
			        replyParentHtml += '              <button type="button" class="btn btn-outline-danger" onclick="fnc_Reply_Delete(this)">삭제</button>';
			        replyParentHtml += '            </div>';
			        replyParentHtml += '          </div>';
			        replyParentHtml += '        </form>';
			        replyParentHtml += '      <div class="row">';
				    replyParentHtml += '       <details class="col-md-10 col-lg-9 mb-4">';
				    replyParentHtml += '         <summary>답글 달기</summary>';
				    replyParentHtml += '         <form class="comment-form">';
				    replyParentHtml += '           <input type="hidden"  id="postId" name="postId" value="'+e.postId+'">    ';
				    replyParentHtml += '           <input type="hidden" class="parent-comment-id" name="replyGroup" value="'+e.replyId+'"> ';
			 	    replyParentHtml += '           <input type="hidden" class="parent-comment-id" name="replyClass" value="'+(e.replyClass+1)+'">';
				    replyParentHtml += '           <input type="hidden" class="parent-comment-id" name="replyOrder" value="'+(e.replyOrder+1)+'"> ';
				    replyParentHtml += '           <input type="hidden"  name="userId" value="'+e.userId+'">';
				    replyParentHtml += '           <textarea class="form-control comment-textbox" placeholder="댓글 쓰기.." rows="2" ';
				    replyParentHtml += '      	name="replyContent" cols="100" wrap="hard" required></textarea>';
				    replyParentHtml += '           <button class="form-control btn btn-primary mt-2" type="button"';
				    replyParentHtml += '           					onclick="fnc_Chiled_Reply_Insert(this)">쓰기</button>';
				    replyParentHtml += '         </form>';
				    replyParentHtml += '       </details>';
				    replyParentHtml += '      </div>  ';
			        replyParentHtml += '		</li>																		  ';
			        replyParentHtml += '		</ul>																		  ';
			        $('#parent-comment'+e.replyGroup+'').append(replyParentHtml);
	  			}else if(e.replyClass == 2){
	  				var replyParentHtml = '';
	  				replyParentHtml += '      <ul class="row me-0">';
	  				replyParentHtml += '      <li class="child-comment"  id="parent-comment'+e.replyId+'">';
	  				replyParentHtml += '        <form class="comment-delete-form">';
	  				replyParentHtml += '          <input type="hidden" name="replyId" value="'+e.replyId+'"> ';
			        replyParentHtml += '          <div class="row">';
			        replyParentHtml += '            <div class="col-md-10 col-lg-9">';
			        replyParentHtml += '              <strong>'+e.userId+'</strong>';
			        var date = new Date(e.regDt);
					var year = date.getFullYear();
					var month =	(date.getMonth()+1).toString().padStart(2,'0');
					var day = date.getDate().toString().padStart(2,'0')
									+'  '+ date.getHours()
									+':'+ date.getMinutes().toString().padStart(2,'0')
									+':'+ date.getSeconds().toString().padStart(2,'0');
					replyregDt = year+'-'+month+'-'+day;
			        replyParentHtml += '              <small><time>'+replyregDt+'</time></small>';
			        replyParentHtml += '              <p class="mb-1"><pre>'+e.replyContent+'</pre></p>';
			        replyParentHtml += '            </div>';
			        replyParentHtml += '            <div class="col-2 mb-3 align-self-center">';
			        replyParentHtml += '              <button type="button" class="btn btn-outline-danger" onclick="fnc_Reply_Delete(this)">삭제</button>';
			        replyParentHtml += '            </div>';
			        replyParentHtml += '          </div>';
			        replyParentHtml += '        </form>';
			        replyParentHtml += '      <div class="row">';
				    replyParentHtml += '       <details class="col-md-10 col-lg-9 mb-4">';
				    replyParentHtml += '         <summary>답글 달기</summary>';
				    replyParentHtml += '         <form class="comment-form">';
				    replyParentHtml += '           <input type="hidden"  id="postId" name="postId" value="'+e.postId+'">    ';
				    replyParentHtml += '           <input type="hidden" class="parent-comment-id" name="replyGroup" value="'+e.replyId+'"> ';
			 	    replyParentHtml += '           <input type="hidden" class="parent-comment-id" name="replyClass" value="'+(e.replyClass+1)+'">';
				    replyParentHtml += '           <input type="hidden" class="parent-comment-id" name="replyOrder" value="'+(e.replyOrder+1)+'"> ';
				    replyParentHtml += '           <input type="hidden"  name="userId" value="'+e.userId+'">';
				    replyParentHtml += '           <textarea class="form-control comment-textbox" placeholder="댓글 쓰기.." rows="2" ';
				    replyParentHtml += '      	name="replyContent" cols="100" wrap="hard" required></textarea>';
				    replyParentHtml += '           <button class="form-control btn btn-primary mt-2" type="button"';
				    replyParentHtml += '           					onclick="fnc_Chiled_Reply_Insert(this)">쓰기</button>';
				    replyParentHtml += '         </form>';
				    replyParentHtml += '       </details>';
				    replyParentHtml += '      </div>  ';
			        replyParentHtml += '		</li>																		  ';
			        replyParentHtml += '		</ul>																		  ';
			        $('#parent-comment'+e.replyGroup+'').append(replyParentHtml);
	  			}else if(e.replyClass == 3){
	  				var replyParentHtml = '';
	  				replyParentHtml += '      <ul class="row me-0">';
	  				replyParentHtml += '      <li class="child-comment">';
	  				replyParentHtml += '        <form class="comment-delete-form">';
	  				replyParentHtml += '          <input type="hidden" name="replyId" value="'+e.replyId+'"> ';
			        replyParentHtml += '          <div class="row">';
			        replyParentHtml += '            <div class="col-md-10 col-lg-9">';
			        replyParentHtml += '              <strong>'+e.userId+'</strong>';
			        var date = new Date(e.regDt);
					var year = date.getFullYear();
					var month =	(date.getMonth()+1).toString().padStart(2,'0');
					var day = date.getDate().toString().padStart(2,'0')
									+'  '+ date.getHours()
									+':'+ date.getMinutes().toString().padStart(2,'0')
									+':'+ date.getSeconds().toString().padStart(2,'0');
					replyregDt = year+'-'+month+'-'+day;
			        replyParentHtml += '              <small><time>'+replyregDt+'</time></small>';
			        replyParentHtml += '              <p class="mb-1"><pre>'+e.replyContent+'</pre></p>';
			        replyParentHtml += '            </div>';
			        replyParentHtml += '            <div class="col-2 mb-3 align-self-center">';
			        replyParentHtml += '              <button type="button" class="btn btn-outline-danger" onclick="fnc_Reply_Delete(this)">삭제</button>';
			        replyParentHtml += '            </div>';
			        replyParentHtml += '          </div>';
			        replyParentHtml += '        </form>';
			        replyParentHtml += '		</li>																		  ';
			        replyParentHtml += '		</ul>																		  ';
			        $('#parent-comment'+e.replyGroup+'').append(replyParentHtml);
	  			}
	  		});                                                                                           
	        
	    },
	    error:function(a,b,c){ // ajax실패시 원인
	 	   alert(c);
	    }
	}); 
}

$(document).ready(function(){
	getReplylist();
	$('#first_comment_submit').click(function(){
		if(!confirm("댓글을 작성하시겠습니까?")){
			return false;
		}
		
		/* var str = $('#replyContent').val();
		str = str.replace(/(?:\r\n|\r|\n)/g, '<br />'); */
		
		var first_reply_add = $('form[name=firstReplyAddForm]').serialize();
		$.ajax({
		    type:"post",
		    url:"/board4/addReply",
		    data: first_reply_add,
		    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		    success:function(data){
		  	  console.log(data);
		  	  $('#replyContent').val('');
		  	  getReplylist();
		    },
		    error:function(a,b,c){ // ajax실패시 원인
		 	   alert(c);
		    }
		}); 
	});
});
</script>	
</body>
</html>
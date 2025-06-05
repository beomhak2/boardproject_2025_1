<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
	<div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="/board1/list" class="nav-link px-2 text-secondary">일상</a></li>
          <li><a href="/board2/list" class="nav-link px-2 text-white">취미</a></li>
          <li><a href="/board3/list" class="nav-link px-2 text-white">운동</a></li>
          <li><a href="/board4/list" class="nav-link px-2 text-white">맛집</a></li>
        </ul>

      <!--   <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
          <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">
        </form> -->

        <div class="text-end">
          <button type="button" class="btn btn-outline-light me-2">Login</button>
          <button type="button" class="btn btn-warning">Sign-up</button>
        </div>
      </div>
    </div>
<script>
  const links = document.querySelectorAll("a.nav-link");
  const currentPath = window.location.pathname;

  // 자동으로 현재 페이지에 해당하는 메뉴에 text-secondary 적용
  let matched = false;
  links.forEach(link => {
    if (link.getAttribute("href") === currentPath) {
      link.classList.remove("text-white");
      link.classList.add("text-secondary");
      matched = true;
    } else {
      link.classList.remove("text-secondary");
      link.classList.add("text-white");
    }
  });

  // 클릭 시에도 텍스트 스타일 변경
  links.forEach(link => {
    link.addEventListener("click", function () {
      links.forEach(l => {
        l.classList.remove("text-secondary");
        l.classList.add("text-white");
      });

      this.classList.remove("text-white");
      this.classList.add("text-secondary");
    });
  });
  
</script>

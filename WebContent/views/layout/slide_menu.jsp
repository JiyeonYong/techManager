<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/dashboard.css">



<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
	<a class="navbar-brand col-sm-8 col-md-2 mr-0" href="/noticeList.do">Tech Manager</a>
	<%if(session.getAttribute("employee") != null) {%>
	<div class = "nav-item">
		<a class = " col-sm-3 col-md-2 mr-0" href= "/logout.do">로그아웃</a>
	</div>
	<%} %>
</nav>

<div class="container-fluid">
	<div class="row">
		<nav class="col-md-2 d-none d-md-block bg-light sidebar">
			<div class="sidebar-sticky">
				<ul class="nav flex-column">
					<li class="nav-item"><a class="nav-link active"
						href="/noticeList.do"> <span data-feather="home"></span>
							공지사항 <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="/views/support/supportList.jsp">
							<span data-feather="file"></span> 기술지원
					</a></li>
					<li class="nav-item"><a class="nav-link" href="/views/share/shareList.jsp"> <span
							data-feather="file"></span> 기술공유
					</a></li>
				</ul>

				<h6
					class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
					<span>관리</span>
				</h6>
				<ul class="nav flex-column mb-2">
					<li class="nav-item"><a class="nav-link" href="/views/user/enterUserInfo.jsp"> <span
							data-feather="file-text"></span> 마이페이지
					</a></li>
					<li class="nav-item" ><a class="nav-link" href="#" style = "visibility:hidden" id = "adminPage"> <span
							data-feather="file-text"></span> 관리자 페이지
					</a></li>
				</ul>
			</div>
		</nav>
	</div>
</div>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/js/jquery-slim.min.js"><\/script>')</script>
<script src="/js/popper.min.js"></script>
<script src="/vendor/dist/js/bootstrap.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
      feather.replace()
    </script>
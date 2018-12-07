<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "com.techmgr.share.model.vo.*"
    import = "com.techmgr.employee.model.vo.*"
    import = "java.util.ArrayList"%>

	<%
	//Controller(Servlet)에서 보내준 값 가져오기
	PageData pd = (PageData)request.getAttribute("pageData");

	ArrayList<Share> list = pd.getList();	//현재 페이지의 글 목록
	String pageNavi = pd.getPageNavi();		//현재 navi Bar
	
	session = request.getSession(false);
	
	Employee employee = (Employee)session.getAttribute("employee");
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기술 공유 게시판</title>
</head>
<body>
	<script src="/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="/vendor/bootstrap/js/popper.min.js"></script>
	<script src="/vender/bootstrap/js/bootstrap.min.js"></script>


	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file = "/views/layout/slide_menu.jsp" %>
	
	
	<main role = "main" class = "col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
		<center>
		<br><b><h5>기술공유 게시판</h5></b><br>
		<div class = "container">
			
			
			<% if(list.isEmpty()) {%>
				기술게시판 글이 없습니다.
			
			<%} else {%>
			
			<table class = "table table-hover">
			<thead>
			<tr>
				<th>글번호</th><th>글제목</th><th>작성자</th><th>작성일</th><th>댓글</th><th>조회</th><th>상태</th>
			</tr>
			</thead>
			<tbody>
			<%for(Share s : list){%>
				<tr>
					<td><%=s.getShareId() %></td>
					<td><a href="share.do?shareId=<%=s.getShareId() %>"><b><%=s.getTitle() %></b></a></td>
					
					<% if(s.getAuthorId().equals("hp_admin")) {%>
						<td>홈페이지 관리자</td>
					<%} else if(s.getAuthorId().equals("supplier_admin")){%>
						<td>제조사 관리자</td>
					<%} else {%>
						<td><%=s.getAuthorId() %></td>
					<%} %>
					<td><%=s.getRegDate() %></td>
					<td><%=s.getComments() %></td>
					<td><%=s.getViews() %></td>
					
					<% if(s.getIsBest().equals("y")) {%>
						<td><span class="badge badge-danger">답변완료</span></td>
					<%} else {%>
						<td><span class="badge badge-info">진행중</span></td>
					<%} %>
				</tr>
			<%}%>
			</tbody>
		</table>
		<button class = "btn btn-success float-right btn-sm" onclick= "window.location.href='/views/share/shareWrite.jsp'">글쓰기</button>
		
		<br>
		
		<ul class = "pagination justify-content-center" style="margin:20px">
			<%=pageNavi %>
		</ul>
		</div>
		
		<%} %>
		
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		
		
	</center>
	</main>
</body>
</html>
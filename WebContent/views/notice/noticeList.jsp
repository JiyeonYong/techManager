<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "com.techmgr.notice.model.vo.*"
    import = "com.techmgr.employee.model.vo.*"
    import = "java.util.ArrayList"%>
    
    <%
	//Controller(Servlet)에서 보내준 값 가져오기
	PageData pd = (PageData)request.getAttribute("pageData");

	ArrayList<Notice> list = pd.getList();	//현재 페이지의 글 목록
	String pageNavi = pd.getPageNavi();		//현재 navi Bar
	
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
</head>
<body>
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="vendor/bootstrap/js/popper.min.js"></script>
	<script src="vender/bootstrap/js/bootstrap.min.js"></script>


	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<%@ include file = "/views/layout/slide_menu.jsp" %>
	<main role = "main" class = "col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
		<center>
		<h1>공지사항</h1>
		<table border = "1">
			<tr>
				<th>글번호</th><th>글제목</th><th>작성자</th><th>작성일</th>
			</tr>
			<%for(Notice n : list){%>
				<tr>
					<td><%=n.getNoticeId() %></td>
					<td><%=n.getTitle() %></td>
					<td><%=n.getAuthorId() %></td>
					<td><%=n.getRegDate() %></td>
				</tr>
			<%}%>
		</table>
		
		<ul class = "pagination justify-content-center" style="margin:20px">
			<%=pageNavi %>
		</ul>
		
		
		<form style = "display:inline" action = "/noticeSearch.do" method = "get">
			<input type = "text" name = "search"/>
			<input type = "submit" value = "검색"/>
		</form>
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		
		
	</center>
	</main>
	
</body>
</html>
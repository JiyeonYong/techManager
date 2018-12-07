<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 작성 완료</title>
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
</head>
<body>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <script src="/js/bootbox.min.js"></script>
    
	<script>
		bootbox.alert("기술공유 작성이 완료되었습니다.");
    	location.href="/shareList.do";
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 작성 실패</title>
</head>
<body>
	<script src = "/js/bootbox.min.js"></script>
	<script>
		bootbox.prompt({
	    	message: "공지사항 작성이 실패했습니다. 다시 작성해주세요.",
	    	size: 'small'
		});

    	location.href="/shareList.do"
	</script>
</body>
</html>
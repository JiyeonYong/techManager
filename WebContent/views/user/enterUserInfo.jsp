<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "com.techmgr.employee.model.vo.*"
    %>
    
    <%
	session = request.getSession(false);
	Employee emp = (Employee)session.getAttribute("employee");
	%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이페이지</title>
</head>
<body>
	<script src="/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="/vendor/bootstrap/js/popper.min.js"></script>
	<script src="/vender/bootstrap/js/bootstrap.min.js"></script>


	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file = "/views/layout/slide_menu.jsp" %>
	
	
	<main role = "main" class = "col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
		<center>
		<br><b><h5>본인 확인</h5></b><br>
		
		<div class="card bg-light text-dark">
			<div class ="card-body">
				
				비밀번호를 입력해주세요.
				<br><br>
					<form>
				    <div class="input-group mb-3 col-sm-5">
				      <div class="input-group-prepend">
				        <span class="input-group-text">비밀번호</span>
				      </div>
				      <input id = "userPwd" type ="password" class="form-control"/>
				      <input id = "userId" type ="hidden" class="form-control" value = "<%=emp.getUserId()%>"/>
				      
				    </div>
			
				    <button id = "enterBtn" class = "btn btn-success btn-sm" onclick = "enterUserInfo();">입력</button>
				    <input class = "btn btn-danger btn-sm" type = "reset" value = "취소"/>
				    </form>
		  	</div>
  		</div>
	</center>
	</main>
	
	
  		
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	
  	<script>
	  	function enterUserInfo() {
			var userPwd = document.getElementById("userPwd").value;
			var userId = document.getElementById("userId").value;

			$.ajax({
				url : "/checkUser.do",
				data : {userId:userId, userPwd:userPwd},
				type : "post",
				success : function(isUser){
					if(isUser == 1){
						alert("본인 확인 완료되었습니다.");
						location.href="/views/user/userInfo.jsp";
						
					}else {
						alert("본인 확인에 실패했습니다. 비밀번호를 확인해주세요.");
						location.reload();
					}
				},
				error : function(){
					alert("문제가 발생했습니다.");
				}
			});
		}
  	</script>
</body>
</html>
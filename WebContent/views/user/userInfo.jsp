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
		<br><b><h5>마이페이지</h5></b><br>
		
		<div class="card bg-light text-dark">
			<div class ="card-body">
					<form>
					<div class= "continer mt-3">
					    <div class="input-group mb-3 col-sm-5">
					      <div class="input-group-prepend">
					        <span class="input-group-text">이름</span>
					      </div>
					      <input class="form-control" type="text" value="<%=emp.getUserName()%>" disabled/>
					    </div>
					    
					    <div class="input-group mb-3 col-sm-5">
					      <div class="input-group-prepend">
					        <span class="input-group-text">아이디</span>
					      </div>
					      <input id = "userId" class="form-control" type="text" value="<%=emp.getUserId()%>" disabled/>
					    </div>
					    
					    <div class="input-group mb-3 col-sm-5">
					      <div class="input-group-prepend">
					        <span class="input-group-text">비밀번호</span>
					      </div>
					      <input id = "userPwd" type ="password" class="form-control" value = "<%=emp.getUserPwd()%>"/>
					    </div>
					    
					    <div class="input-group mb-3 col-sm-5">
					      <div class="input-group-prepend">
					        <span class="input-group-text">전화번호</span>
					      </div>
					      <input id ="phone" class="form-control" type="text" value="<%=emp.getPhone()%>"/>
					    </div>
					    
					    <div class="input-group mb-3 col-sm-5">
					      <div class="input-group-prepend">
					        <span class="input-group-text">이메일</span>
					      </div>
					      <input id = "email" class="form-control" type="text" value="<%=emp.getEmail()%>"/>
					    </div>
				    </div>
				    
				    <button id = "modifyBtn" class = "btn btn-success btn-sm" onclick = "modifyUserInfo();">입력</button>
				    <input class = "btn btn-danger btn-sm" type = "reset" value = "취소"/>
				    </form>
		  	</div>
  		</div>
	</center>
	</main>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script>
	  	function modifyUserInfo() {
	  		var userId = document.getElementById("userId").value;
	  		var userPwd = document.getElementById("userPwd").value;
			var phone = document.getElementById("phone").value;
			var email = document.getElementById("email").value;

			$.ajax({
				url : "/modifyUser.do",
				data : {userId:userId, userPwd:userPwd, phone:phone, email:email},
				type : "post",
				success : function(result){
					if(result > 0) {
						alert("정보가 수정되었습니다.");
						location.reload();
						
					}else {
						alert("정보 수정에 실패했습니다. 다시 시도해주세요.");
						location.href="/views/user/enterUserInfo.jsp";
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
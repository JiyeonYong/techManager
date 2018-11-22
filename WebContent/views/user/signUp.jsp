<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="/fonts/iconic/css/material-design-iconic-font.min.css">
	<link rel="stylesheet" type="text/css" href="/vendor/animate/animate.css">	
	<link rel="stylesheet" type="text/css" href="/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="/css/util.css">
	<link rel="stylesheet" type="text/css" href="/css/main.css">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap-select.css">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap-select.min.css">
	<link rel="stylesheet" type="text/css" href="/css/bootstrap-select.min.css">
	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.5.0/css/all.css' integrity='sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU' crossorigin='anonymous'>
	
<title>회원가입</title>
</head>
<body>
<div class="limiter">
		<div class="container-login100" style="background-image: url('/images/bg-01.jpg');">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
				<form action = "/signUp.do" id = "login100" method = "post" class="login100-form">
					<span class="login100-form-title p-b-49">
						Sign Up
					</span>
					
					<div id = "validate-input" class=" validate-input m-b-23">
						<span class="label-input100">소속</span>
						<br>
						<center>
							<select class="selectpicker test" data-live-search="true" style = "width : 75%">
								<optgroup label="제조사">
  								<option name = "company">제조사</option>
								</optgroup>
								
								<optgroup label="협력사">
  								<option name = "company">DBMaster</option>
  								<option name = "company">Solutions</option>
  								<option name = "company">BestWeb</option>
  								<option name = "company">Designers</option>
								</optgroup>
							</select>
						</center>
					</div>
					
					<div id = "validate-input" class="wrap-input100 validate-input m-b-23" data-validate="ID를 입력하세요">
						<span class="label-input100">ID</span>
						<input id = "idCheck" class="input100" type="text" name="userId" placeholder="아이디">
						<span class="focus-input100" data-symbol="&#xf206;"></span>
					</div>
					
					<div class="text-left p-t-8 p-b-31">
						<a>
							중복 확인 >
						</a>
						<span id="checkIdMsg"></span>
					</div>
					
					<div id = "validate-input" class="wrap-input100 validate-input" data-validate="비밀번호를 입력하세요">
						<span class="label-input100">Password</span>
						<input id = "userPwd" class="input100" type="password" name="userPwd" placeholder="비밀번호">
						<span class="focus-input100" data-symbol="&#xf190;"></span>
					</div>
					<div id = "validate-input" class="wrap-input100 validate-input" data-validate="비밀번호를 입력하세요">
						<br>
						<input id = "checkUserPwd" class="input100" type="password" placeholder="비밀번호 확인">
						<span class="focus-input100" data-symbol="&#xf190;"></span>
					</div>
					
					<div class="text-left p-t-8 p-b-31">
						<a>영문 대소문자, 숫자 조합 4~12자리만 허용</a>
					</div>
					
					<div id = "validate-input" class="wrap-input100 validate-input m-b-23">
						<span class="label-input100">Name</span>
						<input class="input100" type="text" name="userName" placeholder="이름">
						<span class="focus-input100" data-symbol="&#9999;"></span>
					</div>
					
					<div id = "validate-input" class="wrap-input100 validate-input m-b-23">
						<span class="label-input100">Phone</span>
						<input class="input100" type="text" name="phone" placeholder="전화번호 (형식 : 01088881111)">
						<span class="focus-input100" data-symbol="&#9742;"></span>
					</div> 
					
					<div id = "validate-input" class="wrap-input100 validate-input m-b-23">
						<span class="label-input100">Email</span>
						<input class="input100" type="email" name="email" placeholder="이메일">
						<span class="focus-input100" data-symbol="&#9993;"></span>
					</div>
					
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button id = "signUpBtn" type = "submit" class="login100-form-btn">
								회원가입
							</button>
						</div>
					</div>

					<div class="txt1 text-center p-t-54 p-b-20">
						<span><b>관리자 승인</b> 후 이용이 가능합니다.</span><br>
						<span>최대 3일 소요될 수 있습니다.</span><br><br>
						<span>문의</span><br>
						<sapn><b>cs@techmgr.com</b></sapn>
					</div>

					<div class="flex-col-c p-t-155">
						<a href="/index.html" class="txt2">
							홈으로
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<script>
		$(function() {
			$("#signUpBtn").click(function(){
				var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
			    var getCheck= RegExp(/^[a-zA-Z0-9]{4,12}$/);
			    var getName= RegExp(/^[가-힣]+$/);
				
			    //아이디 공백 확인
			      if($("#userId").val() == ""){
			        alert("아이디를 입력해주세요.");
			        $("#userId").focus();
			        return false;
			      }
			 
			      //이름의 유효성 검사
			      if(!getCheck.test($("#userName").val())){
			        alert("이름을 다시 확인해주세요.");
			        $("#userName").val("");
			        $("#userName").focus();
			        return false;
			      }
			 
			      //비밀번호
			      if(!getCheck.test($("#userPwd").val())) {
			      alert("비밀번호 형식에 맞지 않습니다.");
			      $("#userPwd").val("");
			      $("#userPwd").focus();
			      return false;
			      }
			 
			      //아이디랑 비밀번호랑 같은지
			      if ($("#userId").val()==($("#userPwd").val())) {
			      alert("비밀번호와 아이디가 같습니다. ");
			      $("#tbPwd").val("");
			      $("#tbPwd").focus();
			    }
			 
			      //비밀번호 똑같은지
			      if($("#userPwd").val() != ($("#checkUserPwd").val())){ 
			      alert("입력한 비밀번호가 다릅니다.");
			      $("#tbPwd").val("");
			      $("#cpass").val("");
			      $("#tbPwd").focus();
			      return false;
			     }
			 
			     //이메일 공백 확인
			      if($("#email").val() == ""){
			        alert("이메일을 입력해주세요");
			        $("#email").focus();
			        return false;
			      }
			           
			      //이메일 유효성 검사
			      if(!getMail.test($("#email").val())){
			        alert("이메일형식에 맞게 입력해주세요")
			        $("#email").val("");
			        $("#email").focus();
			        return false;
			      }
			 
			      //이름 유효성
			      if (!getName.test($("#userName").val())) {
			        alert("이름 똑띠 쓰세용");
			        $("#userName").val("");
			        $("#userName").focus();
			        return false;
			      }
			 
			    return true;
				
				
				/* $.ajax({
					url : "/login.do",
					data : {userId:userId, userPwd:userPwd},
					type : "post",
					success : function(isEmployee){
						if(isEmployee == "1"){
							alert("로그인 성공");
						}
						else if(isEmployee == "0"){
							$("#userId").val("");
							$("#userPwd").val("");
						}
					},
					error : function(){
						alert("문제가 발생했습니다.");
					}
					
				}); */
				
			});
		});
	</script>
	
	<script>
	$("#idCheck").blur(function(){
		var userId = $("#idCheck").val();
		var msg = $("#checkIdMsg");
		var getCheck= RegExp(/^[a-zA-Z0-9]{4,12}$/);
		$.ajax({
			url : "/checkId.do",
			type : "get",
			data : {userId:userId},
			success : function(isUserId){
			var isUserId = isUserId;
			if(isUserId == "true") {
                msg.attr("style","color:red");
				msg.html("ID사용 불가");
			} 
			else if(isUserid == "false" && ){
               	msg.attr("style","color:blue");
				msg.html("ID사용 가능");
			}
				
				
			},
			error : function(){console.log("ajax 통신 에러");}
		});
		
	});
	</script>
	
	<script>
	
	
	</script>
	

	<div id="dropDownSelect1"></div>
	<!-- <script src="vendor/jquery/jquery-3.2.1.min.js"></script> -->
	<script src="/vendor/animsition/js/animsition.min.js"></script>
	<script src="/vendor/bootstrap/js/popper.js"></script>
	<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="/vendor/select2/select2.min.js"></script>
	<script src="/vendor/daterangepicker/moment.min.js"></script>
	<script src="/vendor/daterangepicker/daterangepicker.js"></script>
	<script src="/vendor/countdowntime/countdowntime.js"></script>
	<script src="/js/bootstrap-select.js"></script>
	<script src="/js/bootstrap-select.min.js"></script>
	
</body>
</html>
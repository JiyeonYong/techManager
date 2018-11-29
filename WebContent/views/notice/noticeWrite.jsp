<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.techmgr.notice.model.vo.*"
	import="com.techmgr.employee.model.vo.*" import="java.util.ArrayList"%>

<%
	session = request.getSession(false);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/css/filebox.css">
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 작성</title>
</head>
<body>
	<script src="/js/jquery-3.1.0.min.js"></script>
	<script src="/js/popper.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<%@ include file="/views/layout/slide_menu.jsp"%>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

	<center>
		<br>
		<h6>공지사항 작성</h6>
		<br>
		<form action = "/noticeWrite.do" method="post" onsubmit="return checkExt()" enctype = "multipart/form-data">
		<div class="card">
			<div class="card-body">
				<table class= "table table-brdered">
					<tbody>
						<tr>
							<th> 제목 </th>
							<td> 
							<input type="text" class="form-control" placeholder="글 제목을 입력해주세요" name = "title">
							</td>
						</tr>	
							<th> 글내용 </th>
							<td> 
							<textarea type="text" rows = "20" class="form-control" name = "contents"></textarea>
							</td>
						<tr>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td>
								<div class = "filebox">
									<input class="upload-name form-control col-sm-9" value="일반문서, 이미지, zip 파일만 가능" disabled="disabled">
									<label for="ex_filename" style = "margin-bottom:0px">불러오기</label>
									<input type="file" id="ex_filename" class="upload-hidden" name = "uploadFile"> 
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="card-footer">
				<center>
					<input class="btn btn-secondary btn-sm float-left" type="button"
						value="목록" onclick="return back();" />
					<div class="float-right">
						<input class="btn btn-success btn-sm" type="submit" value="업로드" />
						<input class="btn btn-success btn-sm" type="reset" value="취소" />
					</div>
				</center>
			</div>
		</div>
		
			</form>

	</center>
	</main>


	<script>
		
		function back() {
			history.go(-1);
			return false;
		}
		
		$(document).ready(function(){ 
			var fileTarget = $('.filebox .upload-hidden');
				
			fileTarget.on('change', function(){
				if(window.FileReader){ 
					var filename = $(this)[0].files[0].name; 
				} else { 
					var filename = $(this).val().split('/').pop().split('\\').pop();
				}
				$(this).siblings('.upload-name').val(filename);
			}); 
		});
		
		
		function checkExt() {
			var fileExt = $('.upload-name').val().slice($('.upload-name').val().indexOf(".") + 1).toLowerCase();
			
			if(fileExt != "docx" && fileExt != "jpg" && fileExt != "png" && fileExt != "hwp" && fileExt != "zip" && fileExt != "txt" && fileExt==""){
				alert("일반문서, 이미지, zip 파일만 첨부할 수 있습니다.");
				return false;
			}
			
			return true;
		}
		
	</script>

</body>
</html>
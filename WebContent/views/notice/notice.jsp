<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "com.techmgr.notice.model.vo.*"
    import = "com.techmgr.employee.model.vo.*"
    import = "java.util.ArrayList"
    import = "com.techmgr.file.model.vo.*"
    %>
    
<%
	NoticeData nd = (NoticeData)request.getAttribute("noticeData");
	Notice n = nd.getNotice();
	ArrayList<NoticeComment> list = nd.getList();
	Employee emp = (Employee)session.getAttribute("employee");
	FileData fd = (FileData)request.getAttribute("fileData");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="/vendor/bootstrap/js/popper.min.js"></script>
	<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>


	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file = "/views/layout/slide_menu.jsp" %>
	
	
	<main role = "main" class = "col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<center>
		<div class = "container">
		
		<input class="btn btn-secondary btn-sm float-left" type="button" value="목록" onclick="return back();"/>
		
		<% if(emp.getUserId().equals(n.getAuthorId())) {%>
		<div class = "float-right">
		<button id = "modifyBtn" class = "btn btn-success btn-sm" onclick = "modifyNoticeActive(<%=n.getNoticeId()%>);">수정</button>
		<button id = "deleteBtn" class = "btn btn-danger btn-sm" onclick = "deleteNotice(<%=n.getNoticeId()%>);">삭제</button>
		</div>
		<%} %>
		<br><br>
		
		<table class="table">
		   <thead class="thead-light">
		     <tr>
		       <th><%=n.getTitle()%></th>
		   	 	<% if(n.getAuthorId().equals("hp_admin")) {%>
						<td>홈페이지 관리자</td>
					<%} else if(n.getAuthorId().equals("supplier_admin")){%>
						<td>제조사 관리자</td>
					<%} %>
		       <td><%=n.getRegDate() %></td>
		     </tr>
		   </thead>
		   <tbody>
		   	<tr>
		   		<td colspan="3" height="400"> 
					<p id = "<%=n.getNoticeId()%>_notice"><%=n.getContents() %> </p>
		   			<center><textarea class= "form-control" rows="20" style = "display:none; resize:none; width : 800px;" id = "<%=n.getNoticeId()%>_notice_fd"></textarea></center>
		   		</td>
		   	</tr>
		   	<%if (fd != null) {%>
		   		<tr>
		   			<td colspan="3">
		   				<div class="card bg-light text-dark ">
    						<div class="card-body"><form action="/fileDown.do" method="post">
									<input type="hidden" name="fileName" value="<%=fd.getFileName()%>"/>
									<%=fd.getFileName()%>&nbsp;&nbsp;&nbsp;<input class ="btn btn-success btn-sm" type="submit" value="다운로드" />
								</form>
    						</div>
  						</div>
		   			</td>
		   		</tr>
		   	<%} %>
		   	<tr>
		   		<td colspan="3">조회 <%=n.getViews() %> | 댓글 <%=n.getComments() %> </td>
		   	</tr>
		   </tbody>
  		</table>
  		
  		<form action = "/noticeCommentWrite.do" method = "get">
  		<input type = "hidden" id = "noticeBoardId" name = "noticeBoardId" value = "<%=n.getNoticeId()%>"/>
  		<div class="media border p-3">
  			<div class = "media-body">
  			<table>
  			  <tr>
  			  	<td><textarea name = "comments" class="form-control" rows="2"style = "resize:none; width:800px "></textarea></td>
			 	<td><input class = "btn btn-success btn-sm" type = "submit" value = "댓글등록" style = "margin-left:10px"/></td>  	
			  </tr>
  			</table>
  			</div>
  		</div>
  		</form>
  		
  		<%if(!list.isEmpty()) {
  			for(NoticeComment nc : list) {%>
  			<div class="media border p-3 bg-light">
		    <img src="/images/commentUsers/5.png" alt="John Doe" class="mr-3 rounded-circle" style="width:70px; margin-top:0;">
		    <div class="media-body">
		      <h6 class="float-left">
			        <% if(nc.getAuthorId().equals("hp_admin")) {%>
							<b>홈페이지 관리자</b>
					<%} else if(nc.getAuthorId().equals("supplier_admin")){%>
							<b>제조사 관리자</b> 
					<%} else {%>
							<b><%=nc.getAuthorId()%></b>
					<%} %>
   						&nbsp;&nbsp;&nbsp;
   						<small><i><%=nc.getRegDate() %></i></small>&nbsp;&nbsp;&nbsp;
   						
   						<% if (nc.getAuthorId().equals(emp.getUserId())) {%>
   						<button type = "button" class = "btn btn-info btn-sm" id = "<%=nc.getId() %>_ModifyActive" onclick = "modifyActive(<%=nc.getId()%>)"><small>수정</small></button>
   						<button type = "button" class = "btn btn-danger btn-sm" id = "<%=nc.getId() %>_DeleteBtn" onclick = "deleteComment(<%=nc.getId()%>)"><small>삭제</small></button>
   						<%} %> 
		      </h6>
		      <br>
		      <br>
		      <p id = "<%=nc.getId()%>" class = "float-left"><%=nc.getComments() %></p>
		      <input class= "form-control" type = "hidden" id = "<%=nc.getId()%>_fd"/>
		    </div>
  			</div>
  		<%	} 
  		}%>
  		<br><br><br><br><br><br><br><br>
	</center>
	</main>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<script>
	
		function modifyNoticeActive(noticeId){
			document.getElementById(noticeId+"_notice").style.display="none";
			document.getElementById(noticeId+"_notice_fd").style.display = "inline";
			
			document.getElementById(noticeId+"_notice_fd").innerHTML = 
				document.getElementById(noticeId+"_notice").innerHTML;
			
			document.getElementById("modifyBtn").innerHTML="수정완료";
			document.getElementById("modifyBtn").onclick=function(){noticeModifySubmit(noticeId)};
			
			document.getElementById("deleteBtn").innerHTML="취소";
			document.getElementById("deleteBtn").onclick=function(){noticeModifyCancel(noticeId)};
		}
		
		function noticeModifyCancel(noticeId) {
			document.getElementById(noticeId+"_notice").style.display="inline";
			document.getElementById(noticeId+"_notice_fd").style.display = "none";
			document.getElementById(noticeId+"_notice_fd").innerHTML = document.getElementById(noticeId+"_notice").innerHTML;
			
			document.getElementById("modifyBtn").innerHTML="수정";
			document.getElementById("modifyBtn").onclick=function(){modifyNoticeActive(noticeId)};
			
			document.getElementById("deleteBtn").innerHTML="삭제";
			document.getElementById("deleteBtn").onclick=function(){deleteNotice(noticeId)};
		}
		
		function noticeModifySubmit(noticeId) {
			var contents = document.getElementById(noticeId+"_notice_fd").value;
			
			console.log(contents);
			$.ajax({
				url : "/noticeModify.do",
				data : {noticeId:noticeId, contents:contents},
				type : "post",
				success : function(result){
					
					if(result > 0){
						alert("공지사항이  수정되었습니다.");
						location.reload();
						
					}else {
						alert("공지사항 수정에 실패했습니다. 다시 시도해주세요.");
					}
				},
				error : function(){
					alert("문제가 발생했습니다.");
				}
			});
			
		}
		
	
		function modifyActive(commentId) {
			document.getElementById(commentId).style.display="none";
			document.getElementById(commentId+"_fd").type = "text";
			
			document.getElementById(commentId+"_fd").value = 
				document.getElementById(commentId).innerHTML;
			
			document.getElementById(commentId+"_ModifyActive").innerHTML="<small>수정완료</small>";
			document.getElementById(commentId+"_ModifyActive").onclick=function(){modifySubmit(commentId)};
			
			document.getElementById(commentId+"_DeleteBtn").innerHTML="<small>취소</small>";
			document.getElementById(commentId+"_DeleteBtn").onclick=function(){modifyCancel(commentId)};
			
		}
		
		function modifyCancel(commentId) {
			document.getElementById(commentId).style.display="inline";
			document.getElementById(commentId+"_fd").type = "hidden";
			document.getElementById(commentId+"_fd").value = document.getElementById(commentId).innerHTML;
			
			document.getElementById(commentId+"_ModifyActive").innerHTML="<small>수정</small>";
			document.getElementById(commentId+"_ModifyActive").onclick=function(){modifyActive(commentId)};
			
			document.getElementById(commentId+"_DeleteBtn").innerHTML="<small>삭제</small>";
			document.getElementById(commentId+"_DeleteBtn").onclick=function(){deleteComment(commentId)};
		}
		
		function modifySubmit(commentId) {
			var comments = document.getElementById(commentId+"_fd").value;
			
			$.ajax({
				url : "/noticeCommentModify.do",
				data : {commentId:commentId, comments:comments},
				type : "post",
				success : function(result){
					
					if(result > 0){
						alert("댓글이 수정되었습니다.");
						location.reload();
						
					}else {
						alert("댓글 수정에 실패했습니다. 다시 시도해주세요.");
					}
				},
				error : function(){
					alert("문제가 발생했습니다.");
				}
			});
		}
	
		function back() {
			location.href="/noticeList.do";
		}
		
		function deleteNotice() {
			location.href= "/noticeDelete.do?noticeId=<%=n.getNoticeId()%>&authorId=<%=n.getAuthorId()%>";
		}
		
		function deleteComment(commentId){
			var noticeId = $("#noticeBoardId").val();
			$.ajax({
				url : "/noticeCommentDelete.do",
				data : {commentId:commentId, noticeId:noticeId},
				type : "post",
				success : function(result){
					
					if(result > 0){
						alert("댓글이 삭제되었습니다.");
						location.reload();
						
					}else {
						alert("댓글 삭제에 실패했습니다. 다시 시도해주세요.");
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
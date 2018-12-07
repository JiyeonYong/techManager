<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "com.techmgr.share.model.vo.*"
    import = "com.techmgr.employee.model.vo.*"
    import = "java.util.ArrayList"
    import = "com.techmgr.file.model.vo.*"
    %>
    
    <%
	ShareData sd = (ShareData)request.getAttribute("shareData");
	Share s = sd.getShare();
	ArrayList<ShareComment> list = sd.getList();
	Employee emp = (Employee)session.getAttribute("employee");
	FileData fd = (FileData)request.getAttribute("fileData");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기술공유게시판</title>
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
		
		<% if(emp.getUserId().equals(s.getAuthorId())) {%>
		<div class = "float-right">
		<button id = "modifyBtn" class = "btn btn-success btn-sm" onclick = "modifyShareActive(<%=s.getShareId()%>);">수정</button>
		<button id = "deleteBtn" class = "btn btn-danger btn-sm" onclick = "deleteShare(<%=s.getShareId()%>);">삭제</button>
		</div>
		<%} %>
		<br><br>
		
		<table class="table">
		   <thead class="thead-light">
		     <tr>
		       <th><%=s.getTitle()%></th>
		   	 	<% if(s.getAuthorId().equals("hp_admin")) {%>
						<td>홈페이지 관리자</td>
					<%} else if(s.getAuthorId().equals("supplier_admin")){%>
						<td>제조사 관리자</td>
					<%} else {%>
						<td><%=s.getAuthorId() %>
					<%} %>
		       <td><%=s.getRegDate() %></td>
		     </tr>
		   </thead>
		   <tbody>
		   	<tr>
		   		<td colspan="3" height="400"> 
					<p id = "<%=s.getShareId()%>_share"><%=s.getContents() %> </p>
		   			<center><textarea class= "form-control" rows="20" style = "display:none; resize:none; width : 800px;" id = "<%=s.getShareId()%>_share_fd"></textarea></center>
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
		   		<td colspan="3">조회 <%=s.getViews() %> | 댓글 <%=s.getComments() %> </td>
		   	</tr>
		   </tbody>
  		</table>
  		
  		<form action = "/shareCommentWrite.do" method = "get">
  		<input type = "hidden" id = "shareBoardId" name = "shareBoardId" value = "<%=s.getShareId()%>"/>
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
  			for(ShareComment sc : list) {%>
  			
  			<% if(sc.getBestcomment().equals("n")) {%>
  			<div class="media border p-3 bg-light">
		    <img src="/images/commentUsers/5.png" alt="John Doe" class="mr-3 rounded-circle" style="width:70px; margin-top:0;">
		    <div class="media-body">
		      <h6 class="float-left">
			        <% if(sc.getAuthorId().equals("hp_admin")) {%>
							<b>홈페이지 관리자</b>
					<%} else if(sc.getAuthorId().equals("supplier_admin")){%>
							<b>제조사 관리자</b> 
					<%} else {%>
							<b><%=sc.getAuthorId()%></b>
							<% if(sc.getpCode().equals("p1")) {%>
								&nbsp;&nbsp;<span class="badge badge-secondary">DBMaster</span>
							<%} else if(sc.getpCode().equals("p2")) {%>
								&nbsp;&nbsp;<span class="badge badge-secondary">Solutions</span>
							<%} else if(sc.getpCode().equals("p3")) {%>
								&nbsp;&nbsp;<span class="badge badge-secondary">BestWeb</span>
							<%} else if(sc.getpCode().equals("p4")) {%>
								&nbsp;&nbsp;<span class="badge badge-secondary">Designers</span>
							<%} %>
					<%} %>
   						&nbsp;&nbsp;&nbsp;
   						<small><i><%=sc.getRegDate() %></i></small>&nbsp;&nbsp;&nbsp;
   						
   						<% if (sc.getAuthorId().equals(emp.getUserId())) {%>
   						<button type = "button" class = "btn btn-info btn-sm" id = "<%=sc.getId() %>_ModifyActive" onclick = "modifyActive(<%=sc.getId()%>)"><small>수정</small></button>
   						<button type = "button" class = "btn btn-danger btn-sm" id = "<%=sc.getId() %>_DeleteBtn" onclick = "deleteComment(<%=sc.getId()%>)"><small>삭제</small></button>
   						<%} %> 
   						
   						<% if(emp.getUserId().equals(s.getAuthorId())) {%>
   						<button type = "button" class = "btn btn-warning btn-sm" id = "<%=sc.getId() %>_checkBest" onclick = "checkBest(<%=sc.getId() %>)"><small>답변채택</small></button>
   						<%} %>
		      </h6>
		      <br>
		      <br>
		      <p id = "<%=sc.getId()%>" class = "float-left"><%=sc.getComments() %></p>
		      <input class= "form-control" type = "hidden" id = "<%=sc.getId()%>_fd"/>
		    </div>
  			</div>
  			<%} else { %>
  			<div class="media border p-3 bg-warning">
		    <img src="/images/commentUsers/5.png" alt="John Doe" class="mr-3 rounded-circle" style="width:70px; margin-top:0;">
		    <div class="media-body">
		      <h6 class="float-left">
			        <% if(sc.getAuthorId().equals("hp_admin")) {%>
							<b>홈페이지 관리자</b>
					<%} else if(sc.getAuthorId().equals("supplier_admin")){%>
							<b>제조사 관리자</b> 
					<%} else {%>
							<b><%=sc.getAuthorId()%></b>
							<% if(sc.getpCode().equals("p1")) {%>
								&nbsp;&nbsp;<span class="badge badge-secondary">DBMaster</span>
							<%} else if(sc.getpCode().equals("p2")) {%>
								&nbsp;&nbsp;<span class="badge badge-secondary">Solutions</span>
							<%} else if(sc.getpCode().equals("p3")) {%>
								&nbsp;&nbsp;<span class="badge badge-secondary">BestWeb</span>
							<%} else if(sc.getpCode().equals("p4")) {%>
								&nbsp;&nbsp;<span class="badge badge-secondary">Designers</span>
							<%} %>
					<%} %>
   						&nbsp;&nbsp;&nbsp;
   						<small><i><%=sc.getRegDate() %></i></small>&nbsp;&nbsp;&nbsp;
   						<span class="badge badge-danger">채택된 답변</span>
   						
   						<% if (sc.getAuthorId().equals(emp.getUserId())) {%>
   						<button type = "button" class = "btn btn-info btn-sm" id = "<%=sc.getId() %>_ModifyActive" onclick = "modifyActive(<%=sc.getId()%>)"><small>수정</small></button>
   						<button type = "button" class = "btn btn-danger btn-sm" id = "<%=sc.getId() %>_DeleteBtn" onclick = "deleteComment(<%=sc.getId()%>)"><small>삭제</small></button>
   						<%} %>
		      </h6>
		      <br>
		      <br>
		      <p id = "<%=sc.getId()%>" class = "float-left"><%=sc.getComments() %></p>
		      <input class= "form-control" type = "hidden" id = "<%=sc.getId()%>_fd"/>
		    </div>
  			</div>
  			<%} %>
  		<%	} 
  		}%>
  		<br><br><br><br><br><br><br><br>
	</center>
	</main>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<script>
	
		function modifyShareActive(shareId){
			document.getElementById(shareId+"_share").style.display="none";
			document.getElementById(shareId+"_share_fd").style.display = "inline";
			
			document.getElementById(shareId+"_share_fd").innerHTML = 
				document.getElementById(shareId+"_share").innerHTML;
			
			document.getElementById("modifyBtn").innerHTML="수정완료";
			document.getElementById("modifyBtn").onclick=function(){shareModifySubmit(shareId)};
			
			document.getElementById("deleteBtn").innerHTML="취소";
			document.getElementById("deleteBtn").onclick=function(){shareModifyCancel(shareId)};
		}
		
		function shareModifyCancel(shareId) {
			document.getElementById(shareId+"_share").style.display="inline";
			document.getElementById(shareId+"_share_fd").style.display = "none";
			document.getElementById(shareId+"_share_fd").innerHTML = document.getElementById(shareId+"_share").innerHTML;
			
			document.getElementById("modifyBtn").innerHTML="수정";
			document.getElementById("modifyBtn").onclick=function(){modifyShareActive(shareId)};
			
			document.getElementById("deleteBtn").innerHTML="삭제";
			document.getElementById("deleteBtn").onclick=function(){deleteShare(shareId)};
		}
		
		function shareModifySubmit(shareId) {
			var contents = document.getElementById(shareId+"_share_fd").value;
			
			console.log(contents);
			$.ajax({
				url : "/shareModify.do",
				data : {shareId:shareId, contents:contents},
				type : "post",
				success : function(result){
					
					if(result > 0){
						alert("기술공유 게시글이  수정되었습니다.");
						location.reload();
						
					}else {
						alert("기술공유 게시글 수정에 실패했습니다. 다시 시도해주세요.");
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
				url : "/shareCommentModify.do",
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
			location.href="/shareList.do";
		}
		
		function deleteShare() {
			location.href= "/shareDelete.do?shareId=<%=s.getShareId()%>&authorId=<%=s.getAuthorId()%>";
		}
		
		function deleteComment(commentId){
			var shareId = $("#shareBoardId").val();
			$.ajax({
				url : "/shareCommentDelete.do",
				data : {commentId:commentId, shareId:shareId},
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
		
		function checkBest(commentId){
			var shareId = $("#shareBoardId").val();
			$.ajax({
				url : "/shareCommentBest.do",
				data : {commentId:commentId, shareId:shareId},
				type : "post",
				success : function(result){
					
					if(result > 0){
						alert("해당 댓글을 답변으로 채택했습니다.");
						location.reload();
						
					}else {
						alert("해당 댓글을 답변으로 채택하는데 실패했습니다. 다시 시도해주세요!");
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
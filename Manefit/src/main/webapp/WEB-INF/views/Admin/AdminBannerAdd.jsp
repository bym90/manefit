<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/Header.jsp"%>
 <script>
  	$(document).ready(function(){
 		$("#add").click(function(){
 			$("#addfrm").submit();
 		});
 	});
  	
  	function deleteBanner(name)
  	{
  		
  		
		if(sweetAlert({title: "",   
			text: "선택된 배너를 삭제 하시겠습니가?",
			type: "warning",   
			showCancelButton: true,   
			confirmButtonColor: "#00b33c",   
			confirmButtonText: "삭제",
			cancelButtonText: "취소",
			closeOnConfirm: false }, 
			function(){
				
				$("#savename").val(name);
				$("#addfrm").attr("action","../Admin/deleteBanner.com");  
				$("#addfrm").submit();})){
		}
		else{
			return;
		}
  		
		
  	}
  	
  	
 </script>

	<div class="container">
		<form method="post" action="../Admin/AdminProc.com" id="addfrm" enctype="multipart/form-data">
			<input type = "hidden" id = "savename" name = "savename">
			<table class = "table">
				<tr>
					<td>
						<h2>적용된 배너</h2>	
					</td>
				</tr>
				
			<c:forEach var = "list" items ="${LIST}" varStatus = "st">
				<tr>
								
					<td>
						<span style = "margin-right:30px">${st.index+1}.</span> 
						<span style = "margin-right:30px"><img src = "../resources/img/${list.savename}" width = "110" height = "100"></span> ${list.savename}
					</td>
					<td>
						<input type="button" id="del1" name="del" value="삭제" class="applyBtn Btn1" onclick = "deleteBanner('${list.savename}')">
					</td>	
				</tr>
			</c:forEach>
				
			</table>
			
			<table class="table">
				<tr>
					<td colspan="3"><h2>배너 등록</h2></td>
				</tr>
				<tr>
					<td>배너 등록</td>
					<td><input type="file" id="upimage1" name="upimage" ></td>
				</tr>
				<tr>
					<td colspan="2" align="right"></td>
					<td><input type="button" id="add" name="add" value="적용하기" class="applyBtn Btn1"></td>
				</tr>
			</table>
			
		</form>
</div>

<%@ include file="../inc/Footer.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ include file="../inc/Header.jsp"%>
<script>
$(document).ready(function(){
	var count = ${GOODSCOUNT};
	if(count != 0){
		sweetAlert("", "상품이 이미 등록되 있습니다.", "warning");
// 		alert("상품이 이미 등록되 있습니다.");
	}
});

</script>

	<form method="post" action="" id="addfrm1">
		<div class="container">
		<h2>카테고리 등록</h2><br><br>
			<table class="table">
				<tr>
					<td width="10%">대 분류</td>
					<td><input type="text" id="lcate" name="lcate" class="form-control W70"></td>
					<td><input class="applyBtn Btn1" type="button" id="lBtn"
						name="lBtn" value="등록하기"></td>
				</tr>
				<tr>
					<td>중 분류</td>
					<td><select id="lcate1" name="lcate1" class="form-control W70">
							<option value="0">선택하세요</option>
							<c:forEach var="data" items="${LLIST}">
								<option value="${data.cate}">${data.name}</option>
							</c:forEach>
					</select> <input type="text" id="mcate" name="mcate" class="form-control W70"></td>
					<td><input class="applyBtn Btn1" type="button" id="mBtn"
						name="mBtn" value="등록하기"></td>
				</tr>
				<tr>
					<td>소 분류</td>
					<td><select id="lcate2" name="lcate2" onChange="changeList1()" class="form-control W70">
							<option value="0">선택하세요</option>
							<c:forEach var="data" items="${LLIST}">
								<option value="${data.cate}">${data.name}</option>
							</c:forEach>
					</select> <select id="mcate2" name="mcate2" class="form-control W70">
							<option value="0">선택하세요</option>
					</select> <input type="text" id="scate" name="scate" class="form-control W70"></td>
					<td><input class="applyBtn Btn1" type="button" id="sBtn"
						name="sBtn" value="등록하기"></td>
				</tr>
			</table>
		</div>
	</form>
	<form method = "post" id = "addfrm2" enctype ="multipart/form-data">
<div class = "container">
	<h2>상품 등록</h2><br><br>
	<table id="addTable" class="table">
		<tr>
			<td width = "10%">
				카테고리
			</td>	
			<td colspan="2">
				<select id = "lcate3" name = "lcate3"  onChange ="changeList2()" class="SELECTSTYLE1">
					<option value = "0">선택하세요</option>
					<c:forEach var = "data" items = "${LLIST}">
						<option value = "${data.cate}">${data.name}</option>
						
					</c:forEach>
				</select>
				<select id = "mcate1" name = "mcate1" onChange = "changeList3()"  class="SELECTSTYLE1">
					<option value = "0">선택하세요</option>
				</select>
				<select id = "scate1" name = "scate1"  class="SELECTSTYLE1">
					<option value = "0">선택하세요</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>가격</td>
			<td colspan="2"><input type = "text" id = "price" name = "price" class = "modifybox"> 원</td>
		</tr>
		<tr>
			<td>적립금</td>
			<td colspan="2"><input type = "number" id = "save" name = "save" class = "modifybox" value ="0" min="0"> %</td>
		</tr>
		<tr>
			<td>할인</td>
			<td colspan="2"><input type = "number" id = "discount" name = "discount" class = "modifybox" value= "0" min="0"> %</td>
		</tr>
		<tr>
			<td>스타일</td>
			<td colspan="2">
				<select id = "tema" name ="tema"  class="SELECTSTYLE1">
					<option value = "0">선택하세요</option>
					<option value = "1">스트릿</option>
					<option value = "2">캐주얼</option>
					<option value = "3">정장</option>
					<option value = "4">스포츠</option>
					<option value = "5">빈티지</option>
					<option value = "6">힙합</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>메인 이미지</td>
			<td colspan="2"><input type = "file" id = "mainimage" name = "mainimage"></td>
		</tr>
		<tr>
			<td>상세보기 이미지</td>
			<td width ="110"><input type = "file" id = "subImage" name = "upimage"></td>
			<td><a href="#"><img src ="../resources/img/add.png" onClick='insRow(); return false;' width ="42" height ="40"></a></td>
		</tr>
	</table>
	<table>
		<tr>
			<td class="TAR">
				<input class="applyBtn Btn1" type="button" id="addBtn"
					name="addBtn" value="상품 등록하기" style='margin-right:35px'>
			</td>
		</tr>
	</table>
	
</div>
</form>
 
<%@ include file="../inc/Footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/Header.jsp"%>
<!-- <script src="../resources/js/jquery/input-file.js"></script> -->
<!-- <link rel="stylesheet" href="../resources/css/input-file.css"> -->
<script>

	function Modify(size, color, id,kind,q2){
		
		var quantity = document.getElementById(id).value;
		var quantity2 = document.getElementById(q2).value;
	
						
		$("#modifyQ").val(quantity);
		$("#modifyQ2").val(quantity2);
		$("#modifyS").val(size);
		$("#modifyC").val(color);
		$("#kind").val(kind);
		
		if(kind == 1){
// 			if(window.confirm("사이즈 : "+size+", 색상 : "+color+"의 수량을  "+quantity+"개로 변경 하시겠습니까?")){
				
// 				$("#SCQfrm").submit();
// 			}
			if(sweetAlert({title: "",   
				text: "사이즈 : "+size+", 색상 : "+color+"의 수량 : "+quantity+"개로 변경 하시겠습니까?", 
				type: "warning",   
				showCancelButton: true,   
				confirmButtonColor: "#00b33c",   
				confirmButtonText: "변경",
				cancelButtonText: "취소",
				closeOnConfirm: false }, 
				function(){
				$("#SCQfrm").submit(); })){
			}
			else{
				return;
			}
		}
		else if(kind == 2){
// 			if(window.confirm("사이즈 : "+size+", 색상 : "+color+", 수량 : "+quantity+"을 삭제 하시겠습니까?")){
				
// 				$("#SCQfrm").submit();
// 			}
			if(sweetAlert({title: "",   
				text: "사이즈 : "+size+", 색상 : "+color+", 수량 : "+quantity+"을 삭제 하시겠습니까?", 
				type: "warning",   
				showCancelButton: true,   
				confirmButtonColor: "#DD6B55",   
				confirmButtonText: "삭제",
				cancelButtonText: "취소",
				closeOnConfirm: false }, 
				function(){
				$("#SCQfrm").submit(); })){
			}
			else{
				return;
			}
		}
	
	}
	
	function modifyInfo(kind){
	
		$("#kind").val(kind);
		
		$("#SCQfrm").submit();
	}
	
	function imageChange(name,kind){
		
		$("#kind").val(kind);
		$("#existingimage").val(name);
		$("#SCQfrm").attr("action","../Admin/AdminImageChange.com");
		$("#SCQfrm").submit();
	}
</script>


<form id = "SCQfrm" method = "post" action = "../Admin/AdminStockSCQ.com" enctype="multipart/form-data">
<div class = "container">
<input type = "hidden" name = "modifyQ2" id = "modifyQ2" >
	<table id = "Modify" class = "table" >
			<tr>
			<td colspan = "8">
				<h2>재고 수정</h2>
			</td>
		</tr>
		<tr>
			<td height = "60" width="5%">번호</td>
			<td width="15%">상품 이름</td>
			<td width="12%">가격</td>
			<td width="*">적립금</td>
			<td width="15%">할인</td>
			<td width="15%">스타일</td>
			<td width="15%">상품 상태</td>
			<td width="10%">수정</td>
		</tr>
	
		<tr>
			<td>
				${LIST.no}
			</td>
			<td>
				<input type = "text" id = "name" name = "name" value ="${LIST.name}" class = "SELECTSTYLE1 modifybox">
			</td>
			<td>
				<input type = "text" id = "price" name = "price" value ="${LIST.price}" class = "SELECTSTYLE1 modifybox">원
			</td>
			<td>
				<input type = "text" id = "save" name = "save" value ="${LIST.save}" class = "SELECTSTYLE1 modifybox">%
			</td>
			<td>
				<input type = "text" id = "discount" name = "discount" value ="${LIST.discount}" class = "SELECTSTYLE1 modifybox">%
			</td>
			<td>
				<select id = "tema" name ="tema" class="SELECTSTYLE1">
					<option value = '0'>선택하세요</option>
					<option value = '1'>스트릿</option>
					<option value = '2'>캐주얼</option>
					<option value = '3'>정장</option>
					<option value = '4'>스포츠</option>
					<option value = '5'>빈티지</option>
					<option value = '6'>힙합</option>
				</select>
			</td>
			<td>
				<select id = "status" name ="status" class="SELECTSTYLE1">
					<option value = '0'>선택하세요</option>
					<option value = '1'>신상품</option>
					<option value = '2'>판매중</option>
					<option value = '3'>품절</option>
					<option value = '4'>재고준비중</option>
					<option value = '5'>판매완료</option>
				</select>
			</td>
			<td>
				<a class="btn btn-danger btn-sm" onclick ="modifyInfo(0)" ><i class="fa fa-refresh fa-1x" aria-hidden="true">수정</i></a><br>
			</td>
		</tr>
		<tr>
			<td height = "1" colspan = "8">
				
			</td>
		</tr>
	</table>
	
	<table class="table">
	<tr>
			<td width="10%">
				메인 이미지
			</td>
			<td height = "100" width="20%">
				<img src = "../resources/img/Goods/${LIST.savename}" width = "110" height = "100">
				<input type = "hidden" name = "existingimage" id = "existingimage" value = "${LIST.savename}">
			</td>
			<td width="*">
				(${LIST.savename})
				<input type = "hidden" name = "savename" id = "savename" value = "${LIST.savename}">
			</td>
			<td width="40%">
				<div class="filebox bs3-primary preview-image" id="main">
                      	<input type="file" id="upimage5" name="upimage"> 
                </div>
			</td>
			<td width="15%">
				<a class="btn btn-danger btn-sm" onClick="imageChange('${LIST.savename}','M')"><i class="fa fa-file-image-o fa-1x" aria-hidden="true">이미지 수정하기</i></a><br>
			</td>
		</tr>
		<tr>
			<td colspan = "5">
				
			</td>
		</tr>


	<c:forEach var = "I" items = "${IMAGE}" varStatus="stat">
		<tr>
			<td>
				서브 이미지 
			</td>
			<td width = "120" height = "100">
				<img src = "../resources/img/Goods/${I.savename}" width = "110" height = "100">
			</td>
			<td>
				(${I.savename})
			</td>
			<td width = "150">
				<div class="filebox bs3-primary preview-image">
                	<input type="file" id="upimage${stat.index}" name="upimage">
                   </div>
			</td>
			<td>
				<a class="btn btn-danger btn-sm" onClick="imageChange('${I.savename}','S')"><i class="fa fa-file-image-o fa-1x" aria-hidden="true">이미지 수정하기</i></a><br>
			</td>
		</tr>
		
	</c:forEach>
		<tr>
			<td align="right" colspan="5">
				<a class="btn btn-default btn-sm" href="../Admin/AdminStock.com?nowPage=${nowPage}"><i class="fa fa-list fa-1x" aria-hidden="true">목록보기</i></a><br>
			</td>
		</tr>
	</table>
</div>		

	<div class = "container">
	<table class = "table">
		<tr>
			<td colspan="5" align="center"><h4>총 수량 : <input type = "text" name = "totalquantity" id = "totalquantity" value ="${TOTAL}" readonly style="border:0px;width:70px;"></h4></td>
		</tr>
		<tr>
			<td></td>
			<td >
				사이즈 : 
				<select id = "size" name = "size" class ="SELECTSTYLE1">
					<option value = "0">선택하세요</option>
					<option value = "S">S</option>
					<option value = "M">M</option>
					<option value = "L">L</option>
					<option value = "XL">XL</option>
				</select>
			</td>
			<td>
				색상 : 
				<select id = "color" name = "color" class ="SELECTSTYLE1">
					<option value = "0">선택하세요</option>
					<option value = "Black">Black</option>
					<option value = "White">White</option>
					<option value = "Red">Red</option>
					<option value = "Brown">Brown</option>
					<option value = "Yellow">Yellow</option>
					<option value = "Pink">Pink</option>
					<option value = "Green">Green</option>
				</select>
			</td>
			<td>
				수량 : 
				<input type = "text" name = "quantity" id = "quantity" class ="SELECTSTYLE1 modifybox" value = "0">
			</td>
			<td>
				<a class="btn btn-danger btn-sm" id="SCQBtn"><i class="fa fa-refresh fa-1x" aria-hidden="true">적용하기</i></a>
<!-- 				<input type = "button" name = "SCQBtn" id = "SCQBtn" value = "적용하기" > -->
			</td>
		</tr>
		
	<c:forEach var = "list" items = "${LIST2}" varStatus = "st">
		<tr>
			<td align ="center" width="20%">
				${st.index+1}.
			</td>
			<td width="20%">
				사이즈 : <input type = "text" name = "s" id = "s" value = "${list.size2}" class ="SELECTSTYLE1 modifybox"  readonly style="border:0px" >
			</td>
			<td width="20%">
				색상 : <input type = "text" name = "c" id = "c" value = "${list.color2}" class ="SELECTSTYLE1 modifybox"  readonly style="border:0px">
			</td>
			<td width="20%">
				수량 : <input type = "text" name = "q" id = "q${st.index}" value = "${list.quantity2}" class ="SELECTSTYLE1 modifybox" style="border:0px">
					 <input type = "hidden" name = "q2" id = "q2${st.index}" value = "${list.quantity2}">
			</td>
			<td width="20%">
				<a class="btn btn-danger btn-sm" onclick ="Modify('${list.size2}','${list.color2}','q${st.index}','1','q2${st.index}')" id = "modifyQ${st.index}"><i class="fa fa-refresh fa-1x" aria-hidden="true">수정</i></a>
				<a class="btn btn-danger btn-sm" onclick ="Modify('${list.size2}','${list.color2}','q${st.index}','2','q2${st.index}')" id = "deleteBtn${st.index}"><i class="fa fa-trash fa-1x" aria-hidden="true">삭제</i></a>
<%-- 				<input type ="button" name = "modifyQ" id = "modifyQ${st.index}" value="수량 변경" onclick ="Modify('${list.size2}','${list.color2}','q${st.index}','1')" > --%>
<%-- 				<input type ="button" name = "deleteBtn" id = "deleteBtn${st.index}" value="삭제" onclick ="Modify('${list.size2}','${list.color2}','q${st.index}','2')"> --%>
			</td>
		</tr>
	</c:forEach>	
	
	</table>

</div>
	
	
	<input type = "hidden" name = "modifyC" id = "modifyC" value = "${LIST.no}">
	<input type = "hidden" name = "kind" id = "kind" value = "1111">
	<input type = "hidden" name = "modifyQ" id = "modifyQ" value = "${LIST.no}">
	<input type = "hidden" name = "modifyS" id = "modifyS" value = "${LIST.no}">
	<input type = "hidden" name = "temp" id = "temp" value = "${LIST.temp}">

</form>	

<%@ include file="../inc/Footer.jsp"%>
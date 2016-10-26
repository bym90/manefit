<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP</title>
</head>
<link rel="stylesheet" href="../resources/css/bootstrap/bootstrap-theme.css">
<link rel="stylesheet" href="../resources/css/bootstrap/bootstrap.css">
<link rel="stylesheet" href="../resources/css/common.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
	function parcelInsert(ono){
		var ono = ono;
		var oparcel = $("#parcel").val();
		var nowPage = ${nowPage};
		var reg = /^\d+$/;
		if(!reg.test(oparcel)){
			sweetAlert("", "숫자만 입력하세요", "warning");
// 			alert("숫자만 입력하세요");
			return;
		}
		
		window.opener.document.location.href="../Admin/ParcelUpdate.com?ono="+ono+"&oparcel="+oparcel+"&nowPage="+nowPage;
		window.close();
	}
</script>
<body>
<form method="POST" id="pfrm" action="">
<input type="hidden" id="ono" name="ono" value="${ono }">
<div class="container" style="padding-left: 50px; padding-right: 20px; padding-top: 20px; ">
<input type="text" id="parcel" name="parcel" class="INPUTSTYLE1">
<input type="button" id="pInsert" name="pInsert" value="운송장번호 부여" class="btn btn-warning" onclick="parcelInsert('${ono }')">
</div>
</form>
</body>
</html>

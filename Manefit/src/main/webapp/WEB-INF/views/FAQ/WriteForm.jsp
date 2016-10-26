<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../inc/Header.jsp" %>

<script>
	$(document).ready(function() {
		$("#wBtn").click(function() {
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);	
			$("#wfrm").attr("action", "../FAQ/WriteProc.com")
			$("#wfrm").submit();
		});
		$("#cBtn").click(function() {
			$(location).attr("href", "../FAQ/List.com");
			
		});
	});
</script>


<div class="container">
	<form method="POST" action="" id="wfrm">
	<table class="table">
		<thead>
			<tr>
				<th colspan="2">공지사항 글쓰기</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" id="title" class="INPUTSTYLE1"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td> <textarea name="ir1" id="ir1" style="width:100%; height:412px; display:none;"></textarea></td>
		</tr>
		<tr>
			<td align="right" colspan="2">
				<input type="button" class="btn btn-info" id="wBtn" value="글쓰기">
				<input type="button" class="btn btn-info" id="cBtn" value="취소">
			</td>
		</tr>
		</tbody>
	</table>
	</form>
</div>


<script type="text/javascript">
var oEditors = [];

// 추가 글꼴 목록
//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ir1",
	sSkinURI: "/www/resources/se/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});

</script>

	
<%@ include file="../inc/Footer.jsp" %>

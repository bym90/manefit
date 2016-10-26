<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ include file="../inc/Header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/LoginForm.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="../resources/js/jquery/daum-postcode.js"></script>
<script src="../resources/js/jquery/singUpForm.js"></script>
<script>
var maxChecked = 2;
var totalChecked = 0;
function countCheck(field) {
	if (field.checked)
		totalChecked += 1;
	else
		totalChecked -= 1;

	if (totalChecked > maxChecked) {
		sweetAlert("", "최대"+maxChecked+"개 까지만 가능합니다.", "warning");
// 		alert ("최대"+maxChecked+"개 까지만 가능합니다.");
	field.checked = false;
	totalChecked -= 1;
	}
}
</script>
<div class="form-box">
	<div class="head">Manefit</div>
	<form method="POST" action="#" id="login-form">
		<div class="form-group">
			<label class="label-control"> <span class="label-text">ID</span>
			</label> <input type="text" name="id" id="id" class="form-control" />
		</div>
		<small id="idconf" style="color: red;"></small>
		<div class="form-group">
			<label class="label-control"> <span class="label-text">Password</span>
			</label> <input type="password" name="pw" id="pw" class="form-control" />
		</div>
		<div class="form-group">
			<label class="label-control"> <span class="label-text">Password 확인</span>
			</label> <input type="password" name="pwcheck" id="pwcheck" class="form-control" />
		</div>
		<small id="pwconf" style="color: red;"></small>
		<div class="form-group">
			<label class="label-control"> <span class="label-text">NAME</span>
			</label> <input type="text" name="name" id="name" class="form-control" />
		</div>
		<div class="form-group">
			<label class="label-control"> <span class="label-text">E-MAIL</span>
			</label> <input type="text" name="email" id="email" class="form-control" />
		</div>
		<div class="form-group">
			<label class="label-control"> <span class="label-text">BIRTH</span>
			</label> <input type="text" name="birth" id="birth" class="form-control" />
		</div>
		<div class="form-group">
			<label class="label-control"> <span class="label-text">TEL</span>
			</label> <input type="text" name="tel" id="tel" class="form-control" />
		</div>
		<div class="form-group">
			<label> <span class="label-text2">옷 스타일 선택</span></label>
			<br>
			<input type="checkbox" name="prefer" id="prefer1" value="1" onclick="countCheck(this)">스트릿
			<input type="checkbox" name="prefer" id="prefer2" value="2" onclick="countCheck(this)" style="margin-left: 10px;">캐주얼
			<input type="checkbox" name="prefer" id="prefer3" value="3" onclick="countCheck(this)" style="margin-left: 10px;">정장
			<input type="checkbox" name="prefer" id="prefer4" value="4" onclick="countCheck(this)" style="margin-left: 10px;">스포츠
			<input type="checkbox" name="prefer" id="prefer5" value="5" onclick="countCheck(this)" style="margin-left: 10px;">빈티지
			<input type="checkbox" name="prefer" id="prefer6" value="6" onclick="countCheck(this)" style="margin-left: 10px;">힙합
		</div> 
		<input type="button" value="주소 검색" class="btn" id="addrSearch" onclick="openDaumPostcode()"/>
		<div class="form-group">
			<label> <span class="label-text2">POST-CODE</span>
			</label> <input readonly type="text" name="pc" id="pc" class="form-control label-control2" />
		</div>
		<div class="form-group">
			<label> <span class="label-text2">ADDRESS</span>
			</label> <input readonly type="text" name="addr1" id="addr1" class="form-control label-control2"/>
		</div>
		<div class="form-group">
			<label> <span class="label-text2">상세 주소</span>
			</label> <input type="text" name="addr2" id="addr2" class="form-control label-control2" placeholder="상세주소 입력"/>
		</div>
		<input type="button" value="Sign Up" class="btn" id="join"/>
		<input type="hidden">
	</form>
</div>

<script type="text/javascript">
$(window).load(function(){
  $('.form-group input').on('focus blur', function (e) {
      $(this).parents('.form-group').toggleClass('active', (e.type === 'focus' || this.value.length > 0));
  }).trigger('blur');
});
</script>



<%@ include file="../inc/Footer.jsp"%>
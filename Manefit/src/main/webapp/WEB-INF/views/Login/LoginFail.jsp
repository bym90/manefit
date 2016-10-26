<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ include file="../inc/Header.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/LoginForm.css">

<script>
	$(document).ready(function(){
		sweetAlert("로그인을 실패했습니다.", "아이디와 비밀번호를 확인해주세요", "error");
// 		alert("로그인을 실패했습니다. 아이디와 비밀번호를 확인해주세요");
		$("#Login").click(function(){
			$("#login-form").attr("action", "../Login/LoginProc.com");
			$("#login-form").submit();
		});
	});
</script>

<div class="form-box">
	<div class="head">Manefit</div>
	<form method="POST" action="#" id="login-form">
		<div class="form-group">
			<label class="label-control"> <span class="label-text">ID</span>
			</label> <input type="email" name="id" id="id" class="form-control" />
		</div>
		<div class="form-group">
			<label class="label-control"> <span class="label-text">Password</span>
			</label> <input type="password" name="pw" id="pw" class="form-control" />
		</div>
		<input type="button" value="Login" class="btn" id="Login"/>
		<p class="text-p">
			Don't have an account? <a href="../Login/SignUpForm.com">Sign up</a>
		</p>
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
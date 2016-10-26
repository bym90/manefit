$(document).ready(function(){
	$("#join").click(function(){
		var regName = /^[가-힣]{2,4}$/;
		var regEmail = /[a-z0-9]{2,}@[a-z0-9-]{2,}.[a-z0-9]{2,}/i; 
		var regDate = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
		var regTel = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;

		var name = $("#name").val();
		var email = $("#email").val();
		var birth = $("#birth").val();
		var tel = $("#tel").val();
		var addr1 = $("#addr1").val();
		var addr2 = $("#addr2").val();
		var id = $("#id").val();
		var pw = $("#pw").val();
		var idconf = $("#idconf").html();
		var pwconf = $("#pwconf").html();
		
		if(idconf == "중복된 아이디입니다." || id == "") {
			sweetAlert("", "아이디를 확인하고 입력해주세요", "warning");
//			alert("아이디를 확인하고 입력해주세요");
			$("#id").focus();
			return;
		}
		
		if(pw == "") {
			sweetAlert("", "비밀번호를 확인하고 입력해주세요", "warning");
//			alert("비밀번호를 확인하고 입력해주세요");
			return;
		}
		
		if(pwconf == "비밀번호가 다릅니다." || pw == ""){
			sweetAlert("", "비밀번호를 확인하고 입력해주세요", "warning");
//			alert("비밀번호를 확인하고 입력해주세요");
			$("#pwcheck").focus();
			return;
		}
	
		if(!regName.test(name)) {
			sweetAlert("", "이름을 2자리 이상 5자리 이하 한글로 입력하세요", "warning");
//			alert("이름을 2자리 이상 5자리 이하 한글로 입력하세요.");
			$("#name").val("");
			$("#name").focus();
			return;
		}
		
		if(!regEmail.test(email)) {
			sweetAlert("", "이메일을 @ 포함한 형식에 맞게 입력하세요.", "warning");
//			alert("이메일을 @ 포함한 형식에 맞게 입력하세요.");
			$("#email").val("");
			$("#email").focus();
			return;
		}
		
		if(!regDate.test(birth)) {
			sweetAlert("날짜를 형식에 맞게 입력하세요.", "예시>1990-12-31", "warning");
//			alert("날짜를 형식에 맞게 입력하세요. \n예시>1990-12-31");
			$("#birth").val("");
			$("#birth").focus();
			return;
		}
		
		if(!regTel.test(tel)) {
			sweetAlert("전화번호를 - 포함한 숫자만 입력하세요.", "예시>010/016/017/018/019-xxxx-xxxx", "warning");
//			alert("전화번호를 - 포함한 숫자만 입력하세요.\n예시>010/016/017/018/019-xxxx-xxxx");
			$("#tel").val("");
			$("#tel").focus();
			return;
		}
		
		if(addr1.length == 0) {
			sweetAlert("", "주소를 검색하세요.", "warning");
//			alert("주소를 검색하세요.");
			$("#addr1").val("");
			$("#addr1").focus();
			return;
		}
		
		if(addr2.length == 0) {
			sweetAlert("", "상세주소를 입력하세요.", "warning");
//			alert("상세주소를 입력하세요.");
			$("#addr2").val("");
			$("#addr2").focus();
			return;
		}
		
		$("#login-form").attr("action", "../Login/SignUpProc.com");
		$("#login-form").submit();
	})
});



$(document).ready(function(){
	$("#id").change(function(){
		var id = $("#id").val();
		$.ajax({
			url : "../Login/IDCheck.com",
			data : "id=" + id + "&temp=" + new Date(),
			type : "post",
			dataType : "json",
			success : function(data){
				var regId = /^[A-Za-z0-9]{4,20}$/;
				if(data.RESULT == "FAIL") {
					$("#idconf").html("중복된 아이디입니다.")
				} 
				else {
					if(!regId.test(id)){
						sweetAlert("", "아이디를 4자리 이상 20자리 이하 영문이나 숫자만 입력하세요.", "warning");
//						alert("아이디를 4자리 이상 20자리 이하 영문이나 숫자만 입력하세요.");
						$("#id").val("");
					 	$("#id").focus();
					} else{
						$("#idconf").html("사용 가능한 아이디입니다.");
					}
				}	
						
			},
			error : function(xhr, code, msg	){
				sweetAlert("", "실패", "error");
//				alert("실패");
			}
		});
	});
	
	$("#pw").change(function(){
		var pw = $("#pw").val();
		var regPw = /^[A-Za-z0-9]{4,20}$/;
		
		if(!regPw.test(pw)) {
			sweetAlert("", "비밀번호를 4자리 이상 20자리 이하 영문이나 숫자만 입력하세요.", "warning");
//			alert("비밀번호를 4자리 이상 20자리 이하 영문이나 숫자만 입력하세요.");
			$("#pw").val("");
			$("#pw").focus();
			return;
		}
	});
	
	$("#pwcheck").change(function(){
		var pw = $("#pw").val();
		var pwcheck = $("#pwcheck").val();
		var regPw = /^[A-Za-z0-9]{4,20}$/;
	
		if(!regPw.test(pwcheck)) {
			sweetAlert("", "비밀번호 확인을 4자리 이상 20자리 이하 영문이나 숫자만 입력하세요.", "warning");
//			alert("비밀번호 확인을 4자리 이상 20자리 이하 영문이나 숫자만 입력하세요.");
			$("#pwcheck").val("");
			$("#pwconf").html("");
			$("#pwcheck").focus();
			return;
		} else {
			
			if(pw == pwcheck){
				$("#pwconf").html("비밀번호가 같습니다.");
			} else {
				$("#pwconf").html("비밀번호가 다릅니다.");
			}	
		}
		
	});
});	
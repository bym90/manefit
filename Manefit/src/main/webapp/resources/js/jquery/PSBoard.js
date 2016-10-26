$(document).ready(function(){
		id = $("#id").val();
		$("#sBtn1").click(function(){
			var score = $("#score").val();
			var scroll = $("body").scrollTop();
			var ps = $("#ps").val();
			if(id == ""){
				sweetAlert("", "로그인 해주세요", "warning");
				$("#signin").focus();
				return;
			}
			if(score == "0"){
				sweetAlert("", "별점을 매겨주세요", "warning");
				return;
			}
			if(ps == ""){
				sweetAlert("", "구매후기를 작성해주세요", "warning");
				return;
			}
			
			$("#efrm").attr("action", "../Board/PWriteProc.com?score="+score+"&scroll="+scroll);
			$("#efrm").submit();
		});
		$("#ps").click(function(){
			if(id == ""){
				sweetAlert("", "로그인 해주세요", "warning");
				$("#signin").focus();
				return;
			}
		});
	});
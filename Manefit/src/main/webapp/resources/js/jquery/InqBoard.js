function pwCheck(no, pw, result, cate, pwconf){
			var no = no;
			var pw = document.getElementById(pw).value;
			$.ajax({
				url : "../Board/IpwCheck.com?no="+no+"&pw="+pw+"&cate="+cate,
//				data : "id=" + id + +"&pw=" + pw + "&cate=" + cate + "&temp=" + new Date() ,
				data : "pw=" + pw + "&cate=" + cate + "&temp=" + new Date() ,
				type : "post",
				dataType : "json",
				success : function(data){
					if(data.RESULT == "SUCCESS") {
						if(data.RBODY == ""){
							$("#"+result).html(data.BODY);
						}
						else{
							$("#"+result).html(data.BODY+"<br><br><font color='green'><b>답변 : </b></font>"+data.RBODY);
						}
						
					}
					else {
						$("#"+pwconf).html("<font color='red'>비밀번호를 다시 입력하세요.</font>");
					}
				},
				error : function(xhr, code, msg	){
					sweetAlert("", "실패", "error");
				}
			});
			
		}
		
	$(document).ready(function(){
		$("#report tr:odd").addClass("odd");
        $("#report tr:not(.odd)").hide(); 
        $("#report tr:first-child").show(); //열머리글 보여주기
        
        $("#report tr.odd").click(function(){      
            $(this).next("tr").toggle();
            $(this).find(".arrow").toggleClass("up");
        });
		
		$("#sBtn2").click(function(){
			var scroll = $("body").scrollTop();
			var pw = $("#pw").val();
			var title = $("#title").val();
			var inq = $("#inq").val();
			if(id == ""){
				sweetAlert("", "로그인 해주세요", "warning");
//				alert("로그인 해주세요");
				$("#signin").focus();
				return;
			}
			if(pw == ""){
				sweetAlert("", "글 비밀번호를 입력해주세요", "warning");
//				alert("글 비밀번호를 입력해주세요");
				return;
			}
			if(title == ""){
				sweetAlert("", "로그인 해주세요", "warning");
//				alert("로그인 해주세요");
				return;
			}
			if(inq == ""){
				sweetAlert("", "문의를 작성해주세요", "warning");
//				alert("문의를 작성해주세요");
				return;
			}
			
			$("#ifrm").attr("action", "../Board/IWriteProc.com?scroll="+scroll);
			$("#ifrm").submit();
		});
		$("#inq").click(function(){
			if(id == ""){
				sweetAlert("", "로그인 해주세요", "warning");
//				alert("로그인 해주세요");
				$("#signin").focus();
				return;
			}
		});
	});
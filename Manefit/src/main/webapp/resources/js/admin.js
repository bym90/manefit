
$(document).ready(function(){
		$("#lBtn").click(function(){
			$("#addfrm1").attr("action","../Admin/AdminLcateAdd.com");
			$("#addfrm1").submit();
		});
		
		$("#mBtn").click(function(){
			$("#addfrm1").attr("action","../Admin/AdminMcateAdd.com");
			$("#addfrm1").submit();
		});
		$("#sBtn").click(function(){
			$("#addfrm1").attr("action","../Admin/AdminScateAdd.com");
			$("#addfrm1").submit();
		});
		$("#addBtn").click(function(){
			$("#addfrm2").attr("action","../Admin/AdminAddGoods.com");
			$("#addfrm2").submit();
		});	
		
		$("#SCQBtn").click(function(){
			$("#SCQfrm").submit();
		});
	});
	
	
function changeList1(){
		var	code = $("#lcate2 option:selected").val();
		$.ajax({
			url : "../Admin/AdminGetMcate.com",
			type : "get",
			data : "lcate2="+code + "&temp="+new Date(),
			dataType : "json",
			success : function(data){
			
				$("#mcate2 option").remove();
			    $("#mcate2").append("<option value='0'>선택하세요</option>");
		
				var list = data.mcate;
			
				$.each(list,  function(index){
					var code = list[index].code;
					var title = list[index].title;
					var op = "<option value = '"+code+"''>" +
					title + "</option>";
					$("#mcate2").append(op);
				});					
			},
			error : function(){
				sweetAlert("", "에러", "error");
//				alert("에러"); 
			}
		});
}	
	

function changeList2(){
	var	code = $("#lcate3 option:selected").val();
	$.ajax({
		url : "../Admin/AdminGetMcate.com",
		type : "get",
		data : "lcate3="+code + "&temp="+new Date(),
		dataType : "json",
		success : function(data){
		
			$("#mcate1 option").remove();
		    $("#mcate1").append("<option value='0'>선택하세요</option>");
	
			var list = data.mcate;
		
			$.each(list,  function(index){
				var code = list[index].code;
				var title = list[index].title;
				var op = "<option value = '"+code+"''>" +
				title + "</option>";
				$("#mcate1").append(op);
			});					
		},
		error : function(){
			alert("에러"); 
		}
	});
}	


function changeList3(){
	var	code = $("#mcate1 option:selected").val();
	$.ajax({
		url : "../Admin/AdminGetScate.com",
		type : "get",
		data : "mcate1="+code + "&temp="+new Date(),
		dataType : "json",
		success : function(data){
		
			$("#scate1 option").remove();
		    $("#scate1").append("<option value='0'>선택하세요</option>");
	
			var list = data.scate;
		
			$.each(list,  function(index){
				var code = list[index].code;
				var title = list[index].title;
				var op = "<option value = '"+code+"''>" +
				title + "</option>";
				$("#scate1").append(op);
			});					
		},
		error : function(){
			alert("에러"); 
		}
	});
}	


var oTbl;
var count = 1;
//Row 추가
function insRow() {
oTbl = document.getElementById("addTable");
var oRow = oTbl.insertRow();
oRow.onmouseover=function(){oTbl.clickedRowIndex=this.rowIndex}; //clickedRowIndex - 클릭한 Row의 위치를 확인;
var oCell = oRow.insertCell();

var frmTag = "서브"
oCell.innerHTML = frmTag;

var oCell = oRow.insertCell();

//삽입될 Form Tag
var frmTag = "<input type='file' name='upimage' id='imageFile'>";
oCell.innerHTML = frmTag;
var oCell = oRow.insertCell();
frmTag ="<a href='#'><img src ='../resources/img/deleteBtn.png' onClick='removeRow(); return false;' width ='38' height ='40'></a>"
oCell.innerHTML = frmTag;
}
//Row 삭제
function removeRow() {
oTbl.deleteRow(oTbl.clickedRowIndex);
}



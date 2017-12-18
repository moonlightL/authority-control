$(function() {
	
	$("#submit-btn").on("click",function() {
		var $userName = $("#user-name").val();
		var $password = $("#password").val();
		
		if ($userName == "") {
			alert("用户名不能为空!");
			return;
		}
		
		if ($password == "") {
			alert("用户名不能为空!");
			return;
		}
		
		$.ajax({
			"type": "post",
			"url": "/login",
			"data":{"userName":$userName,"password":$password},
			"success" : function(resp) {
				if (resp.code == 200) {
					window.location.href = resp.obj;
				} else {
					alert(resp.msg);
					return;
				}
			}
		});
	});
	
	$(document).on("keydown",function(e) {
		if (e.keyCode == 13) {
			$("#submit-btn").trigger("click");
		}
	});
	
});
$(function() {
	$("#save-btn").on("click",function() {
		if ($("#userName").val() == "") {
			layer.msg("用户名不能为空", {
				offset : 't',
				anim : 6
			});
			return;
		}
		
		if ($("input[type=radio]:checked").size() == 0) {
			layer.msg("请选择用户状态", {
				offset : 't',
				anim : 6
			});
			return;
		}
		
		$("#save-form").submit();
	});
});
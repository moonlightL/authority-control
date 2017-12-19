$(function() {
	$("#save-btn").on("click",function() {
		if ($("#name").val() == "") {
			layer.msg("权限名称不能为空", {
				offset : 't',
				anim : 6
			});
			return;
		}
		
		if ($("#type").val() != 3 && $("#pid").val() == "0") {
			layer.msg("该权限类型必须设置父级权限", {
				offset : 't',
				anim : 6
			});
			return;
		}
		$("#save-form").submit();
	});
});
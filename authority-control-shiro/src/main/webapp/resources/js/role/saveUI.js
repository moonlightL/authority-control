$(function() {
	$("#save-btn").on("click",function() {
		if ($("#name").val() == "") {
			layer.msg("角色名称不能为空", {
				offset : 't',
				anim : 6
			});
			return;
		}
		$("#save-form").submit();
	});
});
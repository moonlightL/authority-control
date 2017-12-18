$(function() {
	// iframe 自适应高度
	var ifm= document.getElementById("contentPanel");
	ifm.height=document.documentElement.clientHeight - 100;

	// 注销事件
	$("#logout-btn").on("click",function() {
		$.ajax({
			"type":"get",
			"url":"/logout",
			"success": function(resp) {
				if (resp.code == 200) {
					window.location.href = resp.obj;
				} else {
					alert(resp.msg);
				}
			}
		});
	});
	
	// 菜单点击事件
	$(".menu-click").on("click",function() {
		// 改变菜单导航标题
		var category_id = $(this).data("category");
		$("#category").text($("#"+category_id).text().trim());
		$("#menu").text($(this).text().trim());
		// 切换菜单对应的界面
		var url = $(this).data("url");
		$("#contentPanel").attr("src",url);
	});
	
});
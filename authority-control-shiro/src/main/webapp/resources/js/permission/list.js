$(function() {
	// 查询数据
	var tableObj = commonUtil.initTable({
		treeShowField: 'name',
        url : "/permission/list",
        search : true,
        detailView : true,
        pagination : true,
        pageSize : 100,
        pageList: [100],
        columns: [
            {checkbox: true},
            {field: 'id', visible: false, title: 'ID'},
            {field: 'name',align: 'center', title: '名称',width:150},
            {field: 'parentName',align: 'center', title: '父级名称',width:200},
            {field: 'type',title: '类型',align: 'center',width: 100,valign: 'middle', formatter: function(type, index) {
                    if (type === 1) {
                        return '<span class="label label-primary">目录</span>';
                    }
                    if (type === 2) {
                        return '<span class="label label-success">菜单</span>';
                    }
                    if (type === 3) {
                        return '<span class="label label-warning">按钮</span>';
                    }
                }
            },
            {field: 'url',align: 'center', title: 'URL',width:300},
            {field : 'code', title : '权限代码',align: 'center',width:100 }
        ],
        onExpandRow : function(index, row, $detail) {
//        	if (row.type != 3) {
//        		console.log($detail)
//        	}
        }
    });
	
	// 新增方法
	var addData = function() {
		window.location.href = "/permission/saveUI";
	}
	
	// 修改方法
	var updateData = function() {
		var list = tableObj.bootstrapTable("getSelections");
        if (list.length ==0) {
            layer.msg("请选择一条记录进行编辑",{
				offset : 't',
				anim : 6
			});
            return;
        }
        if (list.length > 1) {
             layer.msg("一次只能编辑一条记录",{
				offset : 't',
				anim : 6
			});
            return;
        }
		window.location.href = "/permission/saveUI?id="+list[0]["id"];
	}

	// 删除方法
	var deleteData = function() {
		var list = tableObj.bootstrapTable("getSelections");
        if (list.length != 1) {
            layer.msg("请选择一条记录进行删除",{
				offset : 't',
				anim : 6
			});
            return;
        }
        var ids = [];
        for(var i=0; i<list.length; i++) {
            ids[i] = list[i]["id"];
        }

		layer.confirm("确定要删除该数据吗?", {
			icon : 3,
			title : '提示'
		}, function(index) {
			$.ajax({
				"type" : "GET",
				"url" : "/permission/delete/" + list[0]["id"],
				"dataType" : "json",
				"success" : function(resp) {
					if (resp.code == 200) {
						layer.close(index);
						tableObj.bootstrapTable('refresh');
					} else {
						layer.alert(resp.msg, {
							"icon" : 2
						});
					}
				}
			});
		});
	}

	// 绑定按钮事件
	var btns = $("#btns").find("a");
	btns.each(function(index, domEle) {
		var btn = $(domEle);
		var code = btn.data("code");
		if (code == "permission:add") {
			btn.on("click", addData);
		} else if(code == "permission:update"){
			btn.on("click", updateData);
		}else if (code == "permission:delete") {
			btn.on("click", deleteData);
		}
	});

});
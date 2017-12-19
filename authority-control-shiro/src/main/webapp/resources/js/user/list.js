$(function() {
	
	// 查询数据
	var tableObj = commonUtil.initTable({
        url : "/user/list",
        search : true,
        detailView : true,
        pagination : true,
        columns: [
            {checkbox: true},
            {field: 'id', visible: false, title: 'ID'},
            {field: 'userName',align: 'center', title: '用户名',width:150},
            {field: 'phone',align: 'center', title: '手机号码',width:200},
            {field: 'email',align: 'center', title: '邮箱地址',width:300},
            {field : 'status', title : '状态',align: 'center',width:100, formatter : function(value) {return value == 1 ? "<span class='label label-success'>开启</span>" : "<span class='label label-danger'>禁用</span>"; } },
            {field : 'createTime', align: 'center',title : '创建时间'},
            {field : 'updateTime',align: 'center', title : '修改时间'}
        ]
    });
	
	// 设置角色方法
	var setRole = function() {
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
        
     // 发送请求
	    $.ajax({
			"type" : "GET",
			"url" : "/role/getRoleListWithSelected/"+list[0].id,
			"dataType" : "json",
			"success" : function(resp) {
				if (resp.code == 200) {
					var roleList = resp.obj;
					$('#roleIds').empty();
					var html = [];
					for(var i=0; i<roleList.length; i++) {
						var obj = roleList[i];
						if (obj.selected) {
							html.push("<option value="+obj.id+" selected='selected'>"+obj.name+"</option>");
						} else {
							html.push("<option value="+obj.id+">"+obj.name+"</option>");
						}
					}
					$('#roleIds').html(html.join(""));
					
					layer.open({
				          title: "【"+list[0].userName+'】关联角色',
				    	  type: 1,
				    	  content: $("#roleUI"),
				    	  area: ['400px', '250px'],
				    	  btn: ['保存', '取消'],
				    	  yes: function(index, layero){
				    		  	var ids = $("#roleIds").val();
				        	    // 发送请求
				        	    $.ajax({
				    				"type" : "POST",
				    				"url" : "/user/setRole",
				    				"data":{userId:list[0].id,roleIds:ids.join(",")},
				    				"dataType" : "json",
				    				"success" : function(resp) {
				    					if (resp.code == 200) {
				    						layer.msg("设置成功!")
				    						layer.close(index);
				    						tableObj.bootstrapTable('refresh');
				    					} else {
				    						layer.alert(resp.msg, {
				    							"icon" : 2
				    						});
				    					}
				    				}
				    			});
					         }
				        });
				} else {
					layer.alert(resp.msg, {
						"icon" : 2
					});
					return;
				}
			}
		});
        
	}
	
	// 新增方法
	var addData = function() {
		window.location.href = "/user/saveUI";
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
		window.location.href = "/user/saveUI?id="+list[0]["id"];
	}

	// 删除方法
	var deleteData = function() {
		var list = tableObj.bootstrapTable("getSelections");
        if (list.length == 0) {
            layer.msg("请选择至少一条记录进行删除",{
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
				"url" : "/user/delete/" + ids.join(","),
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
		if (code == "user:setRole") {
			btn.on("click", setRole);
		} else if (code == "user:add") {
			btn.on("click", addData);
		} else if(code == "user:update"){
			btn.on("click", updateData);
		}else if (code == "user:delete") {
			btn.on("click", deleteData);
		}
	});

});
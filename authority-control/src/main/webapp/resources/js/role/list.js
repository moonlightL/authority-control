$(function() {
	
	// 查询数据
	var tableObj = commonUtil.initTable({
        url : "/role/list",
        search : true,
        detailView : true,
        pagination : true,
        columns: [
            {checkbox: true},
            {field: 'id', visible: false, title: 'ID'},
            {field: 'name',align: 'center', title: '角色名称',width:150},
            {field: 'descr',align: 'center', title: '描述',width:220}
        ]
    });
	
	var ztreeObj = null;
	// 设置权限方法
	var setPermission = function() {
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
        
        $.ajax({
			"type" : "GET",
			"url" : "/permission/getPermissionListWithChecked/" + list[0].id,
			"dataType" : "json",
			"success" : function(resp) {
				if (resp.code == 200) {
					// 生成树
					ztreeObj = $.fn.zTree.init($("#permissionTree"), getZTreeSetting(), resp.obj);
					
					layer.open({
			          title: "【"+list[0].name+'】关联权限',
			    	  type: 1,
			    	  content: $("#permissionUI"),
			    	  area: ['400px', '350px'],
			    	  btn: ['保存', '取消'],
			    	  yes: function(index, layero){
			    		  var nodes = ztreeObj.getCheckedNodes(true);
			              var permissionIds = [];
			              if (nodes.length > 0) {
			                  for (var i = 0; i < nodes.length; i++) {
			                	  permissionIds.push(nodes[i].id);
			                  }
			              }
			        	    // 发送请求
			        	    $.ajax({
			    				"type" : "POST",
			    				"url" : "/role/setPermission",
			    				"data":{roleId:list[0].id,permissionIds:permissionIds.join(",")},
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
		window.location.href = "/role/saveUI";
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
		window.location.href = "/role/saveUI?id="+list[0]["id"];
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
				"url" : "/role/delete/" + ids.join(","),
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
	
	//ztree设置
    function getZTreeSetting() {
        return {
            check: {
                enable: true,
                chkboxType: { "Y": "p", "N": "s" }
            },
            view: {
                dblClickExpand: false,
                showLine: true,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pid",
                    rootPId: ""
                }
            },
            callback: {
                beforeClick: function(treeId, treeNode) {
                    var zTree = $.fn.zTree.getZTreeObj("permissionTree");
                    if (treeNode.isParent) {
                        zTree.expandNode(treeNode);
                    }
                    return false;
                }
            }
        };
    }

	// 绑定按钮事件
	var btns = $("#btns").find("a");
	btns.each(function(index, domEle) {
		var btn = $(domEle);
		var code = btn.data("code");
		if (code == "role:setPermission") {
			btn.on("click", setPermission);
		} else if (code == "role:add") {
			btn.on("click", addData);
		} else if(code == "role:update"){
			btn.on("click", updateData);
		}else if (code == "role:delete") {
			btn.on("click", deleteData);
		}
	});
});
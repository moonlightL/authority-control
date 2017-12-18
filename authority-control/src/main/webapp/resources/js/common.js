var commonUtil = {
	initTable : function(obj) {
		var id = obj.id || "table";
		var table = $('#' + id).bootstrapTable({
			treeShowField : obj.treeShowField,
			url : obj.url,
			xhrFields : {
				withCredentials : true
			},
			crossDomain : true,
			// striped: true,
			search : obj.search,
			height : obj.height,
			showRefresh : true,
			showColumns : true,
			minimumCountColumns : 2,
			clickToSelect : true,
			detailView : obj.detailView,
			detailFormatter : commonUtil.detailFormatter,
			pagination : obj.pagination,
			paginationLoop : false,
			sidePagination : 'server',
			silentSort : false,
			smartDisplay : false,
			escape : true,
			searchOnEnterKey : true,
			idField : 'id',
			maintainSelected : true,
			pageSize: obj.pageSize,
			pageList: obj.pageList,
			responseHandler : function(resp) {
				if (resp.code != 200) {
					layer.msg(resp.msg);
					return;
				}
				return {
					"total" : resp.obj.total, // 总页数
					"rows" : resp.obj.list // 数据
				};
			},
			columns : obj.columns,
			onClickCell : obj.onClickCell,
			onExpandRow : obj.onExpandRow
		});
		return table;
	},
	// 数据表格展开内容
     detailFormatter: function(index, row) {
        var html = [];
        $.each(row, function(key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }
}

$.ajaxSetup({
    dataType: "json",
    cache: false,
    xhrFields: { withCredentials: true },//设置后，请求会携带cookie
    crossDomain: true,
    complete: function(xhr) {
        if (xhr.responseJSON) {
            if (xhr.responseJSON.code != 200) {
                layer.msg(xhr.responseJSON.msg);
            }
        } else {
        	console.log(xhr.status != 200)
            layer.msg(xhr.responseText);
        }
    }
});
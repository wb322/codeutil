
/**
 @Name：Machine JS
 */
layui.define(['table', 'form'], function(exports) {
	var $ = layui.$,
		table = layui.table,
		form = layui.form,
		moduleUrl = "/machine/";
	table.render({
		elem: '#list-table',
		url: moduleUrl + 'page',
		method: 'post',
        contentType: 'application/json;UTF-8',
		toolbar: '#table-toolbar-head',
		cols: [[
			{type: 'checkbox',fixed: 'left'},
            {field: 'id', title: '设备ID',width:100},
            {field: 'machineId', title: 'IP',width:150,templet:function (d) {
				return d.machineMaintenance.ip;
            }},
            {field: 'name', title: '主机名',minWidth: 300},
            {field: 'system', title: '操作系统'},
			{field: 'cpu', title: 'CPU',width:100,templet:function (d) {
					return d.cpu == null?'':d.cpu + " 核";
			}},
            {field: 'disk', title: '硬盘',width:100,templet:function (d) {
					return d.disk == null?'':d.disk + " GB";
            }},
            {field: 'memory', title: '内存',width:100,templet:function (d) {
				return d.memory == null?'':d.memory + " G";
            }},
            {field: 'network', title: '网卡'},
			{title: '操作',minWidth: 150,align: 'center',fixed: 'right',toolbar: '#table-toolbar-column'},
		]],
		page: true,
		limit: 10,
		limits: [10, 15, 20, 25, 30],
		text: {
			none: '无相关数据'
		},
		size:'lg',
		where:{
			params:{
				cdn: parent.$('#cdn').prop('checked')
			}
		}
	});
	//监听搜索
	form.on('submit(list-table-search)', function(data) {
		var field = data.field;
		$.each(field,function (k, v) {
        	if (v === ""){
        		delete field[k];
        	}
        });
		table.reload('list-table', {
			where: {
				params: field
			},
			page:{
			    curr: 1
			},
			done:function (res,curr,count) {
				this.where = {};
			}
		});
		return false;
	});
	//监听重置
	form.on('submit(list-table-reset)', function(data) {
		data.form.reset();
		return false;
	});
	//监听头工具条
	table.on('toolbar(list-table)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		if (obj.event === 'add') {
			layer.open({
				type: 2,
				title: '添加',
				content: '/menu/machine/form',
				maxmin: true,
				area: ['550px', '550px'],
				btn: ['确定', '取消'],
				yes: function(index, layero) {
					var iframeWindow = window['layui-layer-iframe' + index],
						submit = layero.find('iframe').contents().find("#form-add-submit");
					iframeWindow.layui.form.on('submit(form-add-submit)', function(data) {
						var field = data.field;
						wb.post(moduleUrl, field, function(d) {
							var t = wb.alert(d);
							if (t.f) {
							    layer.close(index);
								table.reload('list-table');
							}
						});
						form.render();
					});
					submit.trigger('click');
				}
			});
		} else if (obj.event === 'batchDel') {
			var checkData = checkStatus.data;
			if (checkData.length === 0) {
				return layer.msg('请选择数据');
			}
			layer.confirm('确定删除吗？', function(index) {
				var data = [];
				checkStatus.data.forEach(function(v) {
					data.push(v. id);
				});
				wb.delete(moduleUrl, data, function(d) {
					var t = wb.alert(d);
					if (t.f) {
						table.reload('list-table');
					}
				});
			});
		}else if (obj.event === 'examineAll'){
			var checkData = checkStatus.data;
			if (checkData.length === 0) {
				return layer.msg('未选择数据');
			}

		}
	});
	//监听列工具条
	table.on('tool(list-table)', function(obj) {
		var data = obj.data;
		if (obj.event === 'del') {
			layer.confirm('确定删除吗？', function(index) {
				wb.delete(moduleUrl + data. id, null, function(d) {
					var t = wb.alert(d);
					if (t.f) {
						table.reload('list-table');
					}
				});
			});
		} else if (obj.event === 'examine') {
			var indexMsg = layer.msg('正在重新检测', {
				icon: 16
				,shade: 0.01
			});
			wb.get(moduleUrl + "examine/" + data.id,null,function (d) {
				if (d.code == 0){
					table.reload('list-table');
				}
			});
			layer.close(indexMsg);
		}
	});
	exports('custom-machine', {})
});

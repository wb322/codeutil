/**
 @Name：MachineApp JS
 */
layui.define(['table', 'form'], function(exports) {
	var $ = layui.$,
		table = layui.table,
		form = layui.form,
		moduleUrl = "/machine_app/";
	table.render({
		elem: '#list-table',
		url: moduleUrl + 'page',
		method: 'post',
        contentType: 'application/json;UTF-8',
		toolbar: '#table-toolbar-head',
		cols: [[
			{type: 'checkbox',fixed: 'left'}, 
            {field: 'machineId', title: '设备',width:200,templet:function (d) {
				return d.machineMaintenance.ip;
            }},
			{field: 'status', title: '健康状态',templet:function (d) {
					return getTemplete(d.status,"status",d.machineId,'status');
			}},
            {field: 'dpi', title: 'DPI系统',templet:function (d) {
				return getTemplete(d.dpi,"install",d.machineId,'dpi') + "&nbsp;" + getTemplete(d.dpiConfig,"config",d.machineId,'dpi') + "&nbsp;" + getTemplete(d.webConfig,"sync",d.machineId);
            }},
            {field: 'cache', title: '缓存系统',templet:function (d) {
            	return getTemplete(d.cache,"install",d.machineId,'cache') + "&nbsp;" + getTemplete(d.cacheConfig,"config",d.machineId,'cache') + "&nbsp;" + getTemplete(d.webConfig,"sync",d.machineId);
            }},
            {field: 'web', title: 'WEB管理',templet:function (d) {
            	return getTemplete(d.web,"install",d.machineId,'web');
            }},
            /*{field: 'gslb', title: '负载均衡',templet:function (d) {
            	return getTemplete(d.gslb,false) + "&nbsp;" + getTemplete(d.gslb,true);
            }},*/

			{title: '操作',minWidth: 220,align: 'center',fixed: 'right',toolbar: '#table-toolbar-column'},
		]],
		page: true,
		limit: 10,
		limits: [10, 15, 20, 25, 30],
		text: {
			none: '无相关数据'
		},
		size:'lg',

	});
	var getTemplete = function(n,type,machineId,system){
		var str;
		if (type == "config"){
			switch (n) {
				case 0 : str = '<button type="button" onclick="startConfig('+machineId+',\''+system+'\')" class="layui-btn layui-btn-sm layui-btn-primary">未配置</button>';break;
				case 1 : str = '<button type="button" class="layui-btn layui-btn-sm layui-btn-normal">已配置</button>';break;
				default : str = '<button type="button" onclick="startConfig('+machineId+',,\''+system+'\')" class="layui-btn layui-btn-sm layui-btn-danger">配置失败</button>';break;
			}
		}else if(type == "install"){
			switch (n) {
				case 0 : str = '<button type="button" onclick="installApp('+machineId+',\''+system+'\')" class="layui-btn layui-btn-sm layui-btn-primary">未安装</button>';break;
				case 1 : str = '<button type="button" class="layui-btn layui-btn-sm layui-btn-normal">已安装</button>';break;
				default : str = '<button type="button" onclick="installApp('+machineId+',\''+system+'\')" class="layui-btn layui-btn-sm layui-btn-danger">安装失败</button>';break;
			}
		}else if(type == "sync"){
			switch (n) {
				case 0 : str = '<button type="button" class="layui-btn layui-btn-sm layui-btn-primary">未同步</button>';break;
				case 1 : str = '<button type="button" class="layui-btn layui-btn-sm layui-btn-normal">已同步</button>';break;
				default : str = '<button type="button" class="layui-btn layui-btn-sm layui-btn-danger">同步失败</button>';break;
			}
		}else if(type == "status"){
			switch (n) {
				case 0 : str = '<button type="button" class="layui-btn layui-btn-sm layui-btn-primary">未知</button>';break;
				case 1 : str = '<button type="button" class="layui-btn layui-btn-sm layui-btn-normal">正常</button>';break;
				case 2 : str = '<button type="button" class="layui-btn layui-btn-sm layui-btn-danger">异常</button>';break;
				default : str = '<button type="button" class="layui-btn layui-btn-sm layui-btn-danger">其它</button>';break;
			}
		}
		return str;
	}
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
				content: '/menu/machine_app/form',
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
		}else if (obj.event === 'sync'){
			layer.load();
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
		} else if (obj.event === 'init') {
			layer.confirm('确定要还原配置吗?',{icon: 3, title:'警告'}, function(index){
				wb.delete(moduleUrl + "criteria",{machineId:data.machineId});
			});
		}else if (obj.event === 'examine') {
			var indexMsg = layer.msg('正在重新检测', {
				icon: 16
				,shade: 0.01
			});
			wb.get(moduleUrl + "examine/" + data.id,null,function (d) {
				if (d.code == 0){
					table.reload('list-table');
				}else{
					wb.alert(d);
				}
			});
			layer.close(indexMsg);
		} else if (obj.event === 'edit') {
			layer.open({
				type: 2,
				title: '编辑',
				content: '/menu/machine_app/form',
				maxmin: true,
				area: ['550px', '550px'],
				btn: ['确定', '取消'],
				yes: function(index, layero) {
					var iframeWindow = window['layui-layer-iframe' + index],
						submit = layero.find('iframe').contents().find("#form-edit-submit");
					iframeWindow.layui.form.on('submit(form-edit-submit)', function(formData) {
						var resultData = formData.field;
						$.each(resultData, function(k, v) {
							if (v == data[k].toString() && k != 'id') {
								delete resultData[k];
							}
						});

						wb.put(moduleUrl, resultData, function(d) {
							var t = wb.alert(d);
							if (t.f) {
							    layer.close(index);
								table.reload('list-table');
							}
						});
						form.render();
					});
					submit.trigger('click');
				},
				success: function(layero, index) {
					var form2 = layero.find('iframe').contents().find("#form-add-edit");
					$.each(data, function(k, v) {
						var att = form2.find("[name = '" + k + "']")[0];
						if (att != undefined) {
							att.value = v;
						}
					});
				}
			});
		}
	});
	exports('custom-machineApp', {})
});
function startConfig(machineId,type) {
	if (type === 'dpi'){
		wb.open("DPI系统","/menu/machine_dpi/form/" + machineId,500,650);
	}else if (type === 'cache'){
		wb.open("缓存系统","/menu/machine_cache/form/"+ machineId,650,650,true);
	}
}
function installApp(machineId,type) {
	return;
	wb.get("/machine_app/install/" + type + "/" + machineId,null,function (d){

	});
}
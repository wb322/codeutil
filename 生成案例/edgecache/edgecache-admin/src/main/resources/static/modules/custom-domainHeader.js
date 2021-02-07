/**
 @Name：DomainHeader JS
 */
layui.define(['table', 'form'], function(exports) {
	var $ = layui.$,
		table = layui.table,
		form = layui.form,
		moduleUrl = "/domain_header/";
	table.render({
		elem: '#list-table',
		url: moduleUrl + 'page',
		method: 'post',
        contentType: 'application/json;UTF-8',
		toolbar: '#table-toolbar-head',
		cols: [[
			{type: 'checkbox',fixed: 'left'},
            {field: 'id', title: 'Id'},
            {field: 'domainId', title: '域名',templet:function (d) {
            	return d.domain.url;
            }},
            {field: 'headerKey', title: '键'},
            {field: 'headerValue', title: '值'},
            {field: 'status', title: '同步信息'},

			{title: '操作',minWidth: 150,align: 'center',fixed: 'right',toolbar: '#table-toolbar-column'},
		]],
		page: true,
		limit: 10,
		limits: [10, 15, 20, 25, 30],
		text: {
			none: '无相关数据'
		},
		size:'lg',
		where: {
			params: {
				domainId:$("#key").val(),
				cdn: parent.$('#cdn').prop('checked')
			}
		},
	});
	//搜索栏
	wb.get("/domain",null,function (d) {
		if (d.code == 0){
			$.each(d.data,function (i,v) {
				$("#domainId").append("<option value='"+v.id+"'>"+v.url+"</option>");
			});
		}
		form.render('select');
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
				content: '/domain_header/form/' + $("#key").val(),
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
		} else if (obj.event === 'edit') {
			layer.open({
				type: 2,
				title: '编辑',
				content: '/menu/domain_header/form',
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
							if (k == 'headerKey'){
								$(att).empty();
								$(att).append("<option value='"+ v +"'>"+v+"</option>");
							}else{
								att.value = v;
							}
						}
						if (k == 'domain'){
							att = form2.find("[name = 'domainId']")[0];
							$(att).append("<option value='"+ v.id +"'>"+v.url+"</option>");
						}
					});
				}
			});
		}
	});
	exports('custom-domainHeader', {})
});

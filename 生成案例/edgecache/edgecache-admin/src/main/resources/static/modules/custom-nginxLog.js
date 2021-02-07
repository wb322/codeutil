/**
 @Name：NginxLog JS
 */
layui.define(['table', 'form'], function(exports) {
	var $ = layui.$,
		table = layui.table,
		form = layui.form,
		moduleUrl = "/nginx_log/";
	table.render({
		elem: '#list-table',
		url: moduleUrl + 'page',
		method: 'post',
        contentType: 'application/json;UTF-8',
		toolbar: '#table-toolbar-head',
		cols: [[
            {field: 'clientIp', title: '用户IP'},
            {field: 'msec', title: '时间',hide:true},
            {field: 'serverProtocol', title: '请求协议',hide:true},
            {field: 'host', title: '域名'},
            {field: 'status', title: 'Http响应状态码'},
            {field: 'cacheCode', title: '缓存状态码',hide:true},
            {field: 'bytesSent', title: '响应大小',hide:true},
            {field: 'requestMethod', title: 'HTTP方法',hide:true},
            {field: 'url', title: '请求URL'},
            {field: 'upstreamAddr', title: '后端信息',hide:true},
            {field: 'upstreamStatus', title: '后端HTTP状态码',hide:true},
            {field: 'rbsize', title: '请求大小',hide:true},
            {field: 'hier', title: '上游缓存状态码',hide:true},
            {field: 'serverAddr', title: '服务IP',hide:true},
            {field: 'contentType', title: '内容类型',hide:true},
            {field: 'httpReferer', title: 'Refer',hide:true},
            {field: 'httpUserAgent', title: '客户端类型',hide:true},
            {field: 'requestTime', title: '下载时长',hide:true},
            {field: 'parentIp', title: '上游IP',hide:true},
            {field: 'httpCookie', title: 'COOKIE',hide:true},
            {field: 'httpRange', title: '分片',hide:true},
            {field: 'parentRespCode', title: '上游HTTP状态码',hide:true},
            {field: 'requestid', title: '请求ID',hide:true},
            {field: 'requestCompletion', title: '事务完成状态',hide:true},
            {field: 'requestHop', title: '跳数',hide:true},
            {field: 'remoteAddr', title: '下游IP',hide:true},
            {field: 'denyType', title: '防护类型',templet:function (d){
            	var str = '正常';
            	switch (d.deny){
					case 1: str = "IP黑名单"; break;
					case 2: str = "URL拦截"; break;
					case 3: str = "ARG拦截"; break;
					case 4: str = "COOKIE拦截"; break;
					case 5: str = "POST拦截"; break;
					case 6: str = "USERAGENT拦截"; break;
					case 7: str = "CC攻击"; break;
					case 8: str = "其他"; break;
					default:str = "正常";
				}
				return str;
            }},
            {field: 'clength', title: '响应头-文件大小',hide:true},

			/*{title: '操作',minWidth: 150,align: 'center',fixed: 'right',toolbar: '#table-toolbar-column'},*/
		]],
		page: true,
		limit: 10,
		limits: [10, 15, 20, 25, 30],
		text: {
			none: '无相关数据'
		},
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
				content: moduleUrl + 'form',
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
				content: moduleUrl + 'form',
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


	//自定义事件
    var active = {
        route: function(){
            $("#route_content").text("");
            wb.get(moduleUrl + "route/192.168.74.28",null,function (d) {
                $.each(d.data,function (i,v) {
                    $("#route_content").append(v + "<br/>");
                });
            })
        },

    };
    //点击后触发data-method事件,
    $('.layui-btn').on('click', function(){
        let field = $(this), method = field.data('method');
        active[method] ? active[method].call(this, field) : '';
    });
	exports('custom-nginxLog', {})
});

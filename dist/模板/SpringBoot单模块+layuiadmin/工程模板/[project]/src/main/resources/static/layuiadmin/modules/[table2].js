/**
 @Name：[Table2] JS
 */
layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;
  var moduleUrl = [table2] + "/";
  //文章管理
  table.render({
    elem: '#list-table'
    ,url: moduleUrl + 'page'
    ,toolbar:'#table-toolbar-head'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
<html-list-table.txt>
      ,{title: '操作', minWidth: 150, align: 'center', fixed: 'right', toolbar: '#table-toolbar-column'}
    ]]
    ,page: true
    ,limit: 10
    ,limits: [10, 15, 20, 25, 30]
    ,text: '对不起，加载出现异常！'
  });
  //监听搜索
  form.on('submit(list-table-search)', function (data) {
    var field = data.field;
    table.reload('list-table', {
      where: field
    });
    return false;
  });
  //监听重置
  form.on('submit(list-table-reset)', function (data) {
    data.form.reset();
    return false;
  });
  //监听头工具条
  table.on('toolbar(list-table)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    if (obj.event === 'add') {
      layer.open({
        type: 2,
        title: '添加',
        content: 'menu/[table2]/form',
        maxmin: true,
        area: ['550px', '550px'],
        btn: ['确定', '取消'],
        yes: function (index, layero) {
          var iframeWindow = window['layui-layer-iframe'+ index];
          var data = iframeWindow.layui.form.val("form-add-edit");
          wb.post(moduleUrl,data);
          layer.close(index);
        }
      });
    }else if (obj.event === 'batchDel'){
      var data = [];
      checkStatus.data.forEach(function (v) {
        data.push(v.[key2]);
      });
      wb.delete(moduleUrl,data);
    }
  });
  //监听列工具条
  table.on('tool(list-table)', function(obj){
    var data = obj.data;
    if (obj.event === 'del') {
      layer.confirm('确定删除吗？', function (index) {
        wb.delete(moduleUrl + data.[key2],null,function (d) {
          var f = wb.alert(d);
          if (f){
            obj.del();
            layer.close(index);
          }
        });
      });
    } else if (obj.event === 'edit') {
      layer.open({
        type: 2,
        title: '编辑',
        content: 'menu/[table2]/form',
        maxmin: true,
        area: ['550px', '550px'],
        btn: ['确定', '取消'],
        yes: function (index, layero) {
          var iframeWindow = window['layui-layer-iframe'+ index];
          var data = iframeWindow.layui.form.val("form-add-edit");
          wb.put(moduleUrl,data,function (d) {
            var f = wb.alert(d);
            if (f){
              obj.update(field);
              layer.close(index);
            }
          });
        },
        success:function (layero, index) {
          var iframeWindow = window['layui-layer-iframe'+ index];
          iframeWindow.layui.form.val("form-add-edit",data);
        }
      });
    }
  });
  exports('[table2]', {})
});
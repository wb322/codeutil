/**

 @Name：layuiAdmin 公共业务
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */
 
layui.define(function(exports){
  var $ = layui.$
  ,layer = layui.layer
  ,laytpl = layui.laytpl
  ,setter = layui.setter
  ,view = layui.view
  ,admin = layui.admin
  
  //公共业务的逻辑处理可以写在此处，切换任何页面都会执行
  //……
  
  
  
  //退出
  admin.events.logout = function(){
    //执行退出接口
    admin.req({
      url: "/sys_user/logout",
      type: 'get',
      data: {},
      done: function(res){
        admin.exit(function(){
          location.href = '/login';
        });
      }
    });
  };
  //退出
  admin.events.password = function(){
    layer.prompt({
      formType: 1,
      value: '',
      title: '请输入要修改的密码',
    }, function(value, index, elem){
      if (value.length < 6){
        layer.msg("密码长度不能小于6个字符",{icon: 5});
        return;
      }
      layer.close(index);
      admin.req({
        url: "/sys_user/password/" + value,
        type: 'get',
        data: {},
        done: function(res){
          admin.exit(function(){
            location.href = '/login';
          });
        }
      });
    });
  };

  
  //对外暴露的接口
  exports('common', {});
});
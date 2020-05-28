;!function ($, win, doc, undefined) {
    var wb = function(){
        this.v = '1.0.0'; //版本号
    }
    /**---------------------------------------------ajax---------------------------------------------------*/

    /**
     * [Ajax全局事件]
     */
    /*Java
    if (!StringUtils.isEmpty (request.getHeader ("x-requested-with"))){
        response.setStatus (302);
        response.setHeader ("redirect","/login");
    }else{
        response.sendRedirect("/login");
    }
    */
    $(document).ajaxStart(function(){
        layer.load(1);
    }).ajaxStop(function(){
        layer.closeAll('loading');
    }).ajaxComplete(function (event,XMLHttpRequest,ajax) {
        var status = XMLHttpRequest.status;
        if (status == 302){
            location.href = XMLHttpRequest.getResponseHeader("redirect");
        }else if (status != 200){
            var statusText = XMLHttpRequest.statusText;
            if(statusText=='timeout'){
                layer.alert('请求超时', {icon: 5});
            }else if (statusText=='error') {
                layer.alert('请求失败', {icon: 5});
            }
        }
    });
    /**
     * [Ajax请求]
     * @param {string}  type        请求方式
     * @param {string}  url         请求路径
     * @param {json}    data        请求参数
     * @param {fn}      callback    回调函数,只有返回数据中数据符合要求才会回调,为空时表示不处理信息只提示
     * @param {json}    config      Ajax配置数据,已存在将覆盖,否则添加
     */
    wb.prototype.ajax = function (type,url,data,callback,config){
        var ajaxConfig = {
            type:type,
            url:url,
            data:JSON.stringify(data),
            dataType:"JSON",
            timeout:20000,
            contentType:"application/json;utf-8",
            success:function(d){
                if (callback != null) {
                    callback(d);
                }else{
                    wb.prototype.alert(d);
                }
            }
        };
        if (config){
            $.each(config,function (v) {
                ajaxConfig[v] = config[v];
            })
        }
        $.ajax(ajaxConfig);
    }
    wb.prototype.get = function (url,data,callback,config){
        wb.prototype.ajax("GET",url,data,callback,config);
    };
    wb.prototype.post = function(url,data,callback,config){
        wb.prototype.ajax("POST",url,data,callback,config);
    };
    wb.prototype.put = function(url,data,callback,config){
        wb.prototype.ajax("PUT",url,data,callback,config);
    };
    wb.prototype.delete = function(url,data,callback,config){
        wb.prototype.ajax("DELETE",url,data,callback,config);
    };
    /**
     * [Ajax调用后提示]
     * @param {json}       d              数据
     * @param
     */
    wb.prototype.alert = function(d){
        var name = layui.setter.response.statusName;
        var code = layui.setter.response.statusCode.ok;
        var msg = layui.setter.response.msgName;
        var data = layui.setter.response.dataName;
        if (d[name] == code) {
            layer.alert(d[msg], {icon: 6});
            return {f:true,d:d[data]};
        }else{
            layer.alert(d[msg], {icon: 5});
            return {f:false,d:d[data]};
        }
    };
    /**---------------------------------------------layui---------------------------------------------------*/
    layui.config({
        base: '/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    });
    /**---------------------------------------------layer---------------------------------------------------*/

    /**
     * [open 打开弹出层]
     * @param  {string}  title [弹出层标题]
     * @param  {string}  url   [弹出层地址]
     * @param  {int}      w     [宽]
     * @param  {int}      h     [高]
     * @param  {boolean} full  [全屏]
     * @return {string}        [description]
     */
    wb.prototype.open = function (title,url,w,h,full) {
        if (title == null || title == '') {
            var title=false;
        };
        if (url == null || url == '') {
            var url="404.html";
        };
        if (w == null || w == '') {
            var w=($(window).width()*0.9);
        };
        if (h == null || h == '') {
            var h=($(window).height() - 50);
        };
        var index = layer.open({
            type: 2,
            area: [w+'px', h +'px'],
            fix: false, //不固定
            maxmin: true,
            shadeClose: true,
            shade:0.4,
            title: title,
            content: url
        });
        if(full){
            layer.full(index);
        }
    }
    /**
     * [close 关闭弹出层]
     * @return {[type]} [description]
     */
    wb.prototype.close = function() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    };
    /**
     * [close 关闭弹出层父并刷新]
     * @return {[type]} [description]
     */
    wb.prototype.father_reload = function() {
        parent.location.reload();
    };

    win.wb = new wb();
}(jQuery, window, document);

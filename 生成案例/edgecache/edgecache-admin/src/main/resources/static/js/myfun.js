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
            data:data == null ? null:JSON.stringify(data),
            dataType:"JSON",
            timeout:300000,
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
        var layui2 = layui.setter;
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
            shadeClose: false,
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


    /**---------------------------------------------Date的扩展---------------------------------------------------*/
    /**
     * 对Date的扩展，将 Date 转化为指定格式的String
     * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
     * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
     */
    Date.prototype.format = function(fmt) {
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "H+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    };

    /**
     * 获取日期区间
     *
     * S:调用时间
     * @param start 在S的基础上从哪天开始操作,0代表调用的时间当天
     * @param total 共几天,默认为1,正数为以后时间,负数为过去的时间
     * @param format 时间格式化，默认为 yyyy-MM-dd
     */
    Date.prototype.getRangeDate = function(start,total,format){
        total = total === undefined || total === 0 ? 1 : total;
        format = format === undefined ? "yyyy-MM-dd" : format;
        let startDay = this.setDate(this.getDate() + start);
        let startDayStr = new Date(startDay).format(format);
        var i = total > 0 ? -1 : 1;
        let baseDay = this.setDate(this.getDate() + total + (total > 0 ? -1 : 1));
        let baseDayStr = new Date(baseDay).format(format);

        if (total >0){
            return {start:startDayStr,end:baseDayStr};
        }else{
            return {start:baseDayStr,end:startDayStr};
        }
    };


    /**---------------------------------------------Echarts的扩展---------------------------------------------------*/
    /**
     * echarts无数据时的效果
     * 无数据时使用：echarts实例.showLoading(wb.loadingNoData);
     * 有数据隐藏： echarts实例.hideLoading();
     */
    wb.prototype.loadingNoData = {
        text: '无数据',
        textColor: '#009688',
        maskColor: 'rgba(255, 255, 255, 0.8)',
        fontSize: 20,
        showSpinner: false,
    };

    wb.prototype.getFlowUnit = function (flow){
        if (flow === 0){
            return 0;
        }
        var unit = flow/1024/1024;
        if (unit >= 1){
            return unit.toFixed(2) + " GB";
        }
        unit = flow/1024;
        if (unit >= 1){
            return unit.toFixed(2) + " MB";
        }
        return flow + " KB"
    };


    win.wb = new wb();
}(jQuery, window, document);

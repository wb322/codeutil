
layui.define(['element','form'], function(exports) {
    var $ = layui.$,
        element = layui.element,
        form = layui.form,
        moduleUrl = "/question/";


    if ($("#key").val() == 1){
        getDataAndStart();
    }

    $("#restart_test").on('click',function () {
        location.href="/question/analysis/" + $("#machineSel").val() + "/1";
    })

    $("#start_test").on('click',getDataAndStart);

    async function getDataAndStart(){
        var machineId = $("#machineSel").val();
        $("#start_test").addClass("layui-disabled");
        var data = [];

        wb.get(moduleUrl + "machineStatus/" + machineId,null,function (d){
            if (d.code == 0){
                 data = d.data;
            }
        },{async:false})
        await test_success("machine",data);

        wb.get(moduleUrl + "appStatus/" + machineId,null,function (d){
            if (d.code == 0){
                data = d.data;
            }
        },{async:false})
        await test_success("app",data);

        wb.get(moduleUrl + "network/" + machineId,null,function (d){
            if (d.code == 0){
                data = d.data;
            }
        },{async:false})
        await test_success("network",data);

        end();
    }



    function end() {
        var html_i = $("#ok > i");
        var html_h3 = $("#ok h3");
        html_i.removeClass("layui-icon-circle");
        html_i.addClass("layui-anim-scale layui-icon-ok");
        html_i.css("color","#5FB878");
        html_h3.css("color","#5FB878");
        element.progress('demo', "100%");
        $("#start_test").remove();
        $("#restart_test").removeClass("layui-hide");
    }

    var progress_count = 0;
    function test_success(name,data) {

        return new Promise((resolve, reject) => {
            var html_i = $("#" + name + "> i");
            var html_h3 = $("#" + name + " h3")
            //开始转圈
            html_i.removeClass("layui-icon-circle");
            html_i.addClass("layui-anim layui-anim-rotate layui-anim-loop layui-icon-loading-1");
            var t = true;
            var time = 0;
            $.each(data,function (i,v) {
                setTimeout(function () {
                    element.progress('demo', "" + (progress_count += 10) + "%");
                    var html_li = $("#" + name + " li:eq(" + i + ")");
                    if (v.code ==1){
                        html_li.css("color","#5FB878");
                    }else{
                        html_li.css("color","red");
                        html_li.append("<blockquote class='layui-elem-quote layui-quote-nm layui-anim layui-anim-scaleSpring' style='width: 500px;margin-top: 10px;color: #0C0C0C'>" +
                            v.msg + "</blockquote>");
                        t = false;
                        html_h3.css("color","red");
                    }
                    if (i == data.length -1){
                        html_i.removeClass("layui-anim-rotate layui-anim-loop layui-icon-loading-1");
                        if (t){
                            html_i.addClass("layui-anim-scale layui-icon-ok");
                            html_i.css("color","#5FB878");
                            html_h3.css("color","#5FB878");
                        }else{
                            html_i.addClass("layui-anim-scale layui-icon-close");
                            html_i.css("color","red");
                        }
                    }
                },time += 500);
            });
            setTimeout(function (){
                resolve(time);
            },time)
        });

    }


    exports('custom-questionAnalysis', {})
});

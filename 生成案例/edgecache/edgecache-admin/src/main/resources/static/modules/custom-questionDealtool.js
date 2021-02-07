layui.define(['code'], function (exports) {
    var $ = layui.$,
        moduleUrl = "/question/";

    //自定义事件
    var active = {
        route: function(){
            $("#route_content").empty();
            let ip = $("#route_ip").val();
            if (ip == ''){
                layer.tips('不能为空', '#route_ip',{tips: 3});
                return;
            }
            wb.get(moduleUrl + "route/" + ip,null,function (d) {
                $.each(d.data,function (i,v) {
                    $("#route_content").append(v + "<br/>");
                });

            })
        },
        curl: function(){
            $("#curl_content").empty();
            let ip = $("#curl_ip").val();
            if (ip == ''){
                layer.tips('不能为空', '#curl_ip',{tips: 3});
                return;
            }
            wb.get(moduleUrl + "curl/" + ip,null,function (d) {
                let str = '';
                $.each(d.data,function (i,v) {
                    str += v + "\n";

                });
                $("#curl_content").append("<pre id='code1' class='layui-code'></pre>")
                $("#code1").text(str);
                layui.code({
                    elem: 'pre',
                    about: false,
                    height: '550px',
                });
            })
        },
    };
    //点击后触发data-method事件,
    $('.layui-btn').on('click', function(){
        let field = $(this), method = field.data('method');
        active[method] ? active[method].call(this, field) : '';
    });
    exports('custom-questionDealtool', {})
});

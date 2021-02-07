/**
 @Name：WafConfig JS
 */
layui.define(['table', 'form', 'laytpl','echarts','element','laydate'], function (exports) {
    var $ = layui.$,
        table = layui.table,
        form = layui.form,
        moduleUrl = "/waf_config/",
        element = layui.element,
        laydate = layui.laydate,
        laytpl = layui.laytpl;
    let config_id = 1;
    table.render({
        elem: '#list-table',
        url: moduleUrl + config_id,
        method: 'get',
        cols: [[
            {field: 'key', width: 400, title: '配置名称'},
            {field: 'value', title: '值'},
            {
                title: '说明', width: 400, templet: function (d) {
                    let name = d.key;
                    let result;
                    if (name == 'config_waf_enable') {
                        result = '防火墙总开关';
                    } else if (name == 'config_white_url_check') {
                        result = 'URL白名单开关';
                    } else if (name == 'config_white_ip_check') {
                        result = 'IP白名单开关';
                    } else if (name == 'config_black_ip_check') {
                        result = 'IP黑名单开关';
                    } else if (name == 'config_url_check') {
                        result = 'URL检查开关';
                    } else if (name == 'config_url_args_check') {
                        result = 'URL请求参数检查开关';
                    } else if (name == 'config_user_agent_check') {
                        result = 'USER AGENT检查开关';
                    } else if (name == 'config_cookie_check') {
                        result = 'COOKIE检查开关';
                    } else if (name == 'config_cc_check') {
                        result = 'CC攻击检查开关';
                    } else if (name == 'config_cc_rate') {
                        result = 'CC攻击比率设置，示例：10/60';
                    } else if (name == 'config_post_check') {
                        result = 'POST请求检查开关';
                    } else if (name == 'config_waf_model') {
                        result = 'WAF拦截模式，html/redirect';
                    } else if (name == 'config_waf_redirect_url') {
                        result = 'WAF拦截后，重定向的URL地址';
                    } else if (name == 'config_output_html') {
                        result = 'WAF拦截后，展示的页面内容';
                    } else if (name == 'config_log_dir') {
                        result = 'WAF拦截日志的存放目录';
                    } else if (name == 'config_rule_dir') {
                        result = 'WAF规则的存放目录';
                    } else if (name == 'sync_status') {
                        let r;
                        switch (d.value) {
                            case 1:
                                r = "待同步";
                                break;
                            case 2:
                                r = "同步成功";
                                break;
                            case 3:
                                r = "同步失败";
                                break;
                        }
                        result = '状态';
                    } else if (name == 'version') {
                        result = '版本';
                    }
                    return result;
                }
            },
            {title: '操作', width: 100, align: 'center', fixed: 'right', toolbar: '#table-toolbar-column'},
        ]],
        page: false,
        text: {
            none: '无相关数据'
        }
    });
    form.on('switch(value)', function (data) {
        var key = data.elem.id;
        var value = data.elem.checked == true ? "on" : "off";
        wb.put(moduleUrl + "field", {[key]: value}, function (d) {
            if (d.code == 0) {
                table.reload("list-table");
            } else {
                layer.msg(d.msg, {icon: 5});
            }
        })

    });
    //监听列工具条
    table.on('tool(list-table)', function (obj) {
        var data = obj.data;
        var keystr = data.key;
        if (obj.event === 'edit') {
            layer.prompt({
                formType: 2,
                value: data.value,
                title: keystr,
            }, function (value, index, elem) {
                wb.put(moduleUrl + "field", {[keystr]: value}, function (d) {
                    if (d.code == 0) {
                        obj.update({value: value});
                        layer.close(index);
                    } else {
                        layer.msg(d.msg, {icon: 5});
                    }
                })

            });
        }
    })


    //chart
    const format = "yyyy-MM-dd HH:mm:ss";
    const todayBegin = new Date(new Date().setHours(0,0,0,0)).format(format);
    const todayNow = new Date().format(format);
    $("#time").val(todayBegin + " - " + todayNow);
    laydate.render({
        elem: '#time',
        type: 'datetime',
        range: true,
        theme: 'molv',

        value:todayBegin + " - " + new Date().format(format),
        done: function (value, date, endDate) {
            $("#time").val(value);
            init();
        }
        ,min: -6
        ,max: todayNow
    });
    $(".change-date-button").on('click',function () {
        let nowDate = new Date();
        let days = $(this).data('days');
        let total = $(this).data('total');
        var nowstr = (nowDate.format(format)).substr(-9);
        let range = nowDate.getRangeDate(days, total);
        if (total === 1) {
            $("#time").val(range.start + " 00:00:00" + " - " + range.end + " 23:59:59");
        } else {
            $("#time").val(range.start + " 00:00:00" + " - " + range.end + nowstr);
        }
        init();
        return false;
    });
    var echartsApp = echarts.init(document.getElementById("chart1"), layui.echartsTheme);
    var echartsApp2 = echarts.init(document.getElementById("chart2"), layui.echartsTheme);
    function initCharts(data) {
        var chartData = data.chart;
        var source = {
            'time': data.time,
            'IP黑名单': chartData[0],
            'CC攻击': chartData[6],
            'ARG拦截': chartData[2],
            'URL拦截': chartData[1],
            'POST拦截': chartData[4],
            'COOKIE拦截': chartData[3],
            'USERAGENT拦截': chartData[5],
            '其他': chartData[7],
        };
        var index = 1;
        var seriesArr = [];
        $.each(source,function (k,v){
            if (k != 'time'){
                var n = {};
                n.name= k;
                n.type= 'line';
                n.smooth= false;
                n.encode= {x: 0, y: index++};
                seriesArr.push(n);
            }
        })

        var option1 = {
            title: {
                text: 'WAF概览',
                x: 'center',
                textStyle: {
                    fontSize: 14
                }
            },
            grid: {

            },
            dataset: {
                source: source
            },
            legend: {
                bottom: 0,
            },
            xAxis: {
                type: 'time',
                boundaryGap: false,
            },
            yAxis: {
                type: 'value',
            },
            tooltip: {
                trigger: 'axis',
                formatter:function(params) {
                    var relVal = new Date(params[0].axisValue).format("yyyy-MM-dd HH:mm");
                    for (var i = 0, l = params.length; i < l; i++) {
                        var t = params[i].encode.y[0]
                        relVal += '<br/>' + params[i].marker + params[i].seriesName + ' : ' + params[i].value[t];
                    }
                    return relVal;
                }
            },
            series: seriesArr
        }
        echartsApp.setOption(option1);
        var option2 = {
            title: {
                text: '攻击分布',
                x: 'center',
                textStyle: {
                    fontSize: 14
                }
            },
            dataset:{
                source: data.total
            },
            legend: {
                bottom: 0,
            },
            tooltip: {
                trigger: 'item',
                //formatter: '{c},{d}%'
            },
            series: [
                {
                    type: 'pie',
                    radius: ['50%', '70%'],
                }
            ],
        }
        echartsApp2.setOption(option2);
    }

    init();

    function init() {
        var dateArr = $("#time").val().split(" - ");
        wb.post("/nginx_log/waf", null, function (d) {
            initCharts(d.data);
        }, {
            contentType: "application/x-www-form-urlencoded",
            data: {start:dateArr[0],end:dateArr[1]}
        })
    }

    element.on('tab(charts)', function (data) {
        echartsApp.resize();
        echartsApp2.resize();
    });


    exports('custom-wafConfig', {})
});

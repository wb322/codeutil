layui.define(function (exports) {

    layui.use(['echarts', 'form', 'laydate', 'element'], function () {
        var $ = layui.$
            , form = layui.form
            , laydate = layui.laydate
            , element = layui.element
            , echarts = layui.echarts;


        const format = "yyyy-MM-dd HH:mm:ss";
        const todayBegin = new Date(new Date().setHours(0, 0, 0, 0)).format(format);
        const todayNow = new Date().format(format);
        $("#time").val(todayBegin + " - " + todayNow);
        laydate.render({
            elem: '#time',
            type: 'datetime',
            range: true,
            theme: 'molv',
            value: todayBegin + " - " + new Date().format(format),
            done: function (value, date, endDate) {
                $("#time").val(value);
                init();
            }
            , min: -6
            , max: todayNow
        });
        $(".change-date-button").on('click', function () {
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
        wb.get("/machine", null, function (d) {
            if (d.code == 0) {
                $.each(d.data, function (i, v) {
                    $("#machine").append("<option value='" + v.machineMaintenance.ip + "'>" + v.name + "</option>");
                });
            }
            form.render('select');
        });

        form.on('select(machine)', function (data) {
            init();
        });

        var echartsApp = echarts.init(document.getElementById("redirectCount"), layui.echartsTheme);
        //var echartsApp2 = echarts.init(document.getElementById("redirectDomain"), layui.echartsTheme);
        var echartsApp3 = echarts.init(document.getElementById("redirectDomain"), layui.echartsTheme);
        var echartsApp4 = echarts.init(document.getElementById("httpCode"), layui.echartsTheme);


        function initCharts1(data) {
            var option = {
                title: {
                    text: '重定向次数',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    formatter:function(params) {
                        var relVal = new Date(params[0].axisValue).format("yyyy-MM-dd HH:mm");
                        for (var i = 0, l = params.length; i < l; i++) {
                            var t = params[i].encode.y[0];
                            relVal += '<br/>' + params[i].marker + params[i].seriesName + ' : ' + params[i].value[t];
                        }
                        return relVal;
                    }
                },
                dataset: {
                    source:{
                        'time':data.time,
                        'http':data.http,
                        'dns':data.dns
                    }
                },
                legend: {
                    bottom: 0,
                },
                xAxis: {
                    type: 'time',
                    boundaryGap: false,
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: "HTTP重定向",
                        type: 'line',
                        smooth: false,
                        encode: {x: 0, y: 1},
                    },{
                        name: "DNS 重定向",
                        type: 'line',
                        smooth: false,
                        encode: {x: 0, y: 2},
                    }
                ]
            }
            echartsApp.setOption(option);
        }

        function initCharts234(data) {

            var option2 = {
                title: {
                    text: '流量带宽',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    formatter:function(params) {
                        var relVal = new Date(params[0].axisValue).format("yyyy-MM-dd HH:mm");
                        for (var i = 0, l = params.length; i < l; i++) {
                            var t = params[i].encode.y[0];
                            relVal += '<br/>' + params[i].marker + params[i].seriesName + ' : ' + params[i].value[t];
                        }
                        return relVal;
                    }
                },
                dataset: {
                    source:{
                        'time':data.time,
                        'server':data.server,
                        'source':data.source
                    }
                },
                legend: {
                    bottom: 0,
                },
                xAxis: {
                    type: 'time',
                    boundaryGap: false,
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: "服务带宽",
                        type: 'line',
                        smooth: false,
                        encode: {x: 0, y: 1},
                    },{
                        name: "回源带宽",
                        type: 'line',
                        smooth: false,
                        encode: {x: 0, y: 2},
                    }
                ]
            }
            //echartsApp2.setOption(option2);


            var option3 = {
                title: {
                    text: '重定向域名分布',
                        x: 'center',
                        textStyle: {
                        fontSize: 14
                    }
                },
                grid: {
                    left: '20%'
                },
                tooltip: {},
                xAxis: {
                    boundaryGap: false,
                },
                yAxis: {
                    type: 'category',
                },
                dataset: {
                    source: data.domain
                },
                series: [{
                    type: 'bar',
                    barWidth: 20,
                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                }]
            }
            echartsApp3.setOption(option3);

            var option4 = {
                title: {
                    text: '状态码分布',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    }
                },
                dataset: {
                    source:data.code
                },
                legend: {
                    bottom: 0,
                },
                tooltip: {
                    trigger: 'item',
                    formatter:function(params, ticket, callback) {
                        var relVal = params.marker + params.name + '：' + params.data[1] + "次，占比：" + params.percent + "%";
                        return relVal;
                    }
                },
                series: [
                    {
                        type: 'pie',
                        radius: ['50%', '70%'],
                    }
                ],
            }
            echartsApp4.setOption(option4);
            if (data.code.length == 0){
                echartsApp4.showLoading(wb.loadingNoData);
            }else{
                echartsApp4.hideLoading();
            }
        }


        init();

        function init() {
            var dateArr = $("#time").val().split(" - ");

            wb.get("/statistics/redirect/" + todayBegin + "/" + todayNow,null,function (d){
                if (d.code === 0) {
                    var data = d.data;
                    $("#http_count").text(wb.getFlowUnit(data.http_total));
                    $("#dns_count").text(wb.getFlowUnit(data.dns_total));
                    initCharts1(data);
                }
            })

            wb.post("/nginx_log/chart", null, function (d) {
                initCharts234(d.data);
            }, {
                contentType: "application/x-www-form-urlencoded",
                data: {start: dateArr[0], end: dateArr[1], ip: $("#machine").val()}
            })
        }

        element.on('tab(charts)', function (data) {
            if (data.index == 0) {
                echartsApp.resize();
            } else {
                echartsApp3.resize();
                echartsApp4.resize();
            }
        });
    });

    exports('custom-analysisServer', {})
});
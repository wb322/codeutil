/**
 @Name: 主页控制台
 */


layui.define(function (exports) {

    const format = "yyyy-MM-dd HH:mm:ss";
    const todayBegin = new Date(new Date().setHours(0, 0, 0, 0)).format(format);
    const todayNow = new Date().format(format);



    //区块轮播切换
    layui.use(['admin', 'carousel'], function () {
        var $ = layui.$
            , admin = layui.admin
            , carousel = layui.carousel
            , element = layui.element
            , device = layui.device();

        //轮播切换
        $('.layadmin-carousel').each(function () {
            carousel.render({
                elem: this
                , width: '100%'
                , arrow: 'none'
                , interval: 3000
                , autoplay: false
                , trigger: (device.ios || device.android) ? 'click' : 'hover'
                , anim: 'default'
            });
        });
        element.render('progress');
    });


    //数据概览
    layui.use(['admin','carousel', 'echarts'], function () {

        var $ = layui.$
            , admin = layui.admin
            , carousel = layui.carousel
            , echarts = layui.echarts;

        var echartsApp = echarts.init(document.getElementById("chart1"), layui.echartsTheme);
        var echartsApp2 = echarts.init(document.getElementById("chart2"), layui.echartsTheme);
        var echartsApp3 = echarts.init(document.getElementById("chart3"), layui.echartsTheme);



        wb.post("/nginx_log/chart", null, function (d) {
            if (d.code === 0) {
                var data = d.data;
                $("#server_max").text(wb.getFlowUnit(data.server_max));
                $("#source_max").text(wb.getFlowUnit(data.source_max));
                initCharts23(data);
            }
        }, {
            contentType: "application/x-www-form-urlencoded",
            data: {start: todayBegin, end: todayNow}
        });

        wb.get("/statistics/redirect/" + todayBegin + "/" + todayNow,null,function (d){
            if (d.code === 0) {
                var data = d.data;
                $("#http_count").text(wb.getFlowUnit(data.http_total));
                $("#dns_count").text(wb.getFlowUnit(data.dns_total));
                initCharts1(data);
            }
        })

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

        function initCharts23(data) {

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
            echartsApp2.setOption(option2);


            var option3 = {
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
            echartsApp3.setOption(option3);
            if (data.code.length == 0){
                echartsApp3.showLoading(wb.loadingNoData);
            }else{
                echartsApp3.hideLoading();
            }
        }



        function resize(){
            echartsApp.resize();
            echartsApp2.resize();
            echartsApp3.resize();
        }

        var carouselIndex = 1;
        carousel.on('change(LAY-index-dataview)', function(obj){
            if (obj.index == carouselIndex){
                resize();
            }
        });
        //监听侧边伸缩
        layui.admin.on('side', function () {
            resize();
        });
    });

    exports('console', {})
});
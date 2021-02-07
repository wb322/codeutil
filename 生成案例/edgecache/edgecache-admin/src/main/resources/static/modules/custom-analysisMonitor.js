layui.define(function (exports) {

    layui.use(['echarts', 'form', 'laydate', 'element'], function () {
        const $ = layui.$
            , form = layui.form
            , laydate = layui.laydate
            , echarts = layui.echarts;


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
        wb.get("/machine",null,function (d) {
            if (d.code == 0){
                $.each(d.data,function (i,v) {
                    $("#machine").append("<option value='"+v.machineMaintenance.ip+"'>"+v.name+"</option>");
                });
            }
            form.render('select');
        });
        form.on('select(machine)', function(data){
            init();
        });
        const echartsApp = echarts.init(document.getElementById("cpu"), layui.echartsTheme);
        /*const echartsApp2 = echarts.init(document.getElementById("disk"), layui.echartsTheme);
        const echartsApp3 = echarts.init(document.getElementById("serverLoad"), layui.echartsTheme);
        const echartsApp4 = echarts.init(document.getElementById("traffic"), layui.echartsTheme);*/
        let options = [
            {
                title: {
                    text: 'CPU负载',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    },
                    show:false
                },
                tooltip: {
                    trigger: 'axis',
                    formatter:function(params) {
                        var relVal = new Date(params[0].axisValue).format("yyyy-MM-dd HH:mm");
                        for (var i = 0, l = params.length; i < l; i++) {
                            var t = params[i].encode.y[0]
                            relVal += '<br/>' + params[i].marker + params[i].seriesName + ' : ' + params[i].value[t]+" %";
                        }
                        return relVal;
                    }
                },
                dataset: {
                    source: null
                },
                legend: {
                    top:10,
                    bottom: 0,
                },
                xAxis: {
                    type: 'time',
                    boundaryGap: false,
                },
                yAxis: {
                    type: 'value',
                    max:100,
                    min:0,
                    splitNumber: 11,
                    axisLabel:{
                        formatter: '{value}%'
                    }
                },
                toolbox: {
                    show:true,
                    feature:{
                        dataZoom: {
                            yAxisIndex:"none"
                        },
                    }
                },
                series: [
                    {
                        type: 'line',
                        smooth: false,
                        encode: {x: 3, y: 2},
                        name:'CPU使用率',
                    },
                    {
                        type: 'line',
                        smooth: false,
                        encode: {x: 3, y: 1},
                        name:'内存使用率'
                    },
                    {
                        type: 'line',
                        smooth: false,
                        encode: {x: 3, y: 0},
                        name:'磁盘使用率'
                    },
                ]
            }
        ];

        function initCharts(data) {
            options[0].dataset = {source:data};
            options[0].title.text = "服务器负载";
            echartsApp.setOption(options[0]);

            /*options[0].title.text = "硬盘";
            echartsApp2.setOption(options[0]);


            options[0].title.text = '负载';
            echartsApp3.setOption(options[0]);

            options[0].title.text = '流量';
            echartsApp4.setOption(options[0]);*/
        }
        init();
        function init(){
            var dateArr = $("#time").val().split(" - ");
            wb.post("/machine_rate/chart", null, function (d) {
                initCharts(d.data);
            },{contentType:"application/x-www-form-urlencoded",data:{start:dateArr[0],end:dateArr[1],ip:$("#machine").val()}})
        }
    });

    exports('custom-analysisMonitor', {})
});
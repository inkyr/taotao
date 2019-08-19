<%--
  Created by IntelliJ IDEA.
  User: Crain
  Date: 2019/8/18
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div>
    <input type="text" id="year" />
    <button id="sub" type="button">查询</button>
</div>
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '各年消费金额统计'
        },
        tooltip: {},
        legend: {
            data:['销售额']
        },
        toolbox:{
            show:true,
            mark:{
              show:true
            },
            dataView:{
              show:true,
              type:["line", "bar"]
            },
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '销售额',
            type: 'bar',
            data: []
        }]
    };

    $("#sub").click(function () {
        var year = $("#year").val();
        if (year == null || year == "") {
            return;
        }
        $.ajax({
            url:"/findDataByYear",
            type: "post",
            dataType:"json",
            data:{year:year},
            success:function (data) {
                myChart.setOption({
                    xAxis: {
                        data: data.categories
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '销售额',
                        data: data.data
                    }]
                });
            }
        })
    });

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

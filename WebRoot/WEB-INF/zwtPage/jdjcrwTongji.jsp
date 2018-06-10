<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>监督检查任务统计</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8" />
    <link href="${ctx}/webResources/zwt/css/chosen.min.css" rel="stylesheet">
    <link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/windowOnMove.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="${ctx}/webResources/zwt/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/highcharts/highmaps.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/highcharts/highstock.js"></script>

<!-- easyui css -->
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/easyui/themes/icon.css">


    <!--[if lt IE 8]>
    <![endif]-->
    <script type="text/javascript">
    $(function () {
    $('#container').highcharts({

        chart: {
            type: 'column'
        },

        title: {
            text: '计划任务、临时任务完成量'
        },

        xAxis: {
            categories: ['2015-10', '2015-11', '2015-12', '2016-1', '2016-2']
        },

        yAxis: {
            allowDecimals: false,
            min: 0,
            title: {
                text: '任务数量'
            }
        },

        tooltip: {
            formatter: function() {
                return '<b>'+ this.x +'</b><br/>'+
                    this.series.name +': '+ this.y +'<br/>'+
                    'Total: '+ this.point.stackTotal;
            }
        },

        plotOptions: {
            column: {
                stacking: 'normal'
            }
        },

        series: [{
            name: '计划未完成',
            data: [51, 23, 24, 17,22],
            stack: 'male'
        }, {
            name: '计划完成',
            data: [321, 432, 421, 243, 521],
            stack: 'male'
        }, {
            name: '临时未完成',
            data: [21, 15, 16, 22, 21],
            stack: 'female'
        }, {
            name: '临时完成',
            data: [341, 132, 124, 14, 53],
            stack: 'female'
        }]
    });
});
        
    </script>
</head>
<body>
    <div class="sipac_head">
        	<a href="#" class="s_h_logo"><i></i><span>管理系统名称</span></a>
            <div class="s_h_nav">
                <ul>
                	<li>
                		<a  href="#">企业安全基本信息查询</a>
                    </li>
                	<li>
                		<a class="active" href="#">监督检查任务统计</a>
                    </li>
                	<li>
                		<a href="#">栏目三</a>
                	</li>
                </ul>
            </div>
<div id="container">
</div>

</div>
</body>
</html>

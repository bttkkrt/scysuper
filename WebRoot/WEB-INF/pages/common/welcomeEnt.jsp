<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>智慧安监管理平台</title>
	<link href="${ctx}/webResources/style/css/index/style.css" rel="stylesheet" type="text/css" />
	<%@include file="/h-ui/lib-css.jsp" %>
</head>

<body>

	<!--wrap-->
	<div class="wrap">  
		<div class="pad20">
			<div class="alert">欢迎来到智慧安监管理平台!<a href="#" class="close"><img src="${ctx}/webResources/style/images/index/icon_02.png"/></a></div>
	        <!--模块-->
	        <div class="menubigbox">
	        	<a href="javascript:void(0)" onclick="clickModule(this,1)" _href="${ctx}/indexEnt.action?entBaseInfoId=${entBaseInfoId}" data-title="企业基本信息">
	                <div class="infobox-icon"><img src="${ctx}/webResources/style/images/index/08.png"></div>
	                <div class="infobox-data">
	                    <h2 style="color:#de6566">企业基本信息管理</h2>
	                    <p>综合性企业信息管理平台</p>
	                </div>
	            </a>
	            <a href="javascript:void(0)" onclick="clickModule(this,1)" _href="/yqzhaj/jsp/yjyl/emeDriList.action" data-title="应急演练">
	                <div class="infobox-icon"><img src="${ctx}/webResources/style/images/index/07.png"/></div>
	                <div class="infobox-data">
	                    <h2 style="color:#de6566">应急管理</h2>
	                    <p>上行下行畅游互通</p>
	                </div>
	            </a>
	        	<a href="javascript:void(0)" onclick="clickModule(this,1)" _href="/yqzhaj/jsp/wzInfoManage/wzInfoManageList.action?wzInfoManage.infoType=7&moduleNames=网站管理,信息公开" data-title="政务公开">
	                <div class="infobox-icon"><img src="${ctx}/webResources/style/images/index/02.png"/></div>
	                <div class="infobox-data">
	                    <h2 style="color:#a487e9">公共服务</h2>
	                    <p>开放式的对外服务</p>
	                </div>
	            </a>
	        	<a href="javascript:void(0)" onclick="clickModule(this,1)" _href="/yqzhaj/jsp/lawLib/lawLibList.action" data-title="法律法规">
	                <div class="infobox-icon"><img src="${ctx}/webResources/style/images/index/03.png"/></div>
	                <div class="infobox-data">
	                    <h2 style="color:#94b23a">辅助决策</h2>
	                    <p>智能化办公辅助管理</p>
	                </div>
	            </a>
	            <a href="javascript:void(0)" onclick="clickModule(this,1)" _href="/yqzhaj/jsp/wsgl/instrumentsInfoList.action" data-title="公文管理">
	                <div class="infobox-icon"><img src="${ctx}/webResources/style/images/index/05.png"/></div>
	                <div class="infobox-data">
	                    <h2 style="color:#e89529">行政管理</h2>
	                    <p>综合性行政管理平台</p>
	                </div>
	            </a>
	         	
	         	
	         <%-- 	<a href="javascript:void(0)" onclick="clickModule(this,1)" _href="/yqzhaj/jsp/carDoneInfo/getDevList.action" data-title="视频管理">
	                <div class="infobox-icon"><img src="${ctx}/webResources/style/images/index/01.png"/></div>
	                <div class="infobox-data">
	                    <h2 style="color:#1099e2">视频中心</h2>
	                    <p>清晰快捷企业视频管理</p>
	                </div>
	            </a>     
	         	<a href="javascript:void(0)" onclick="clickModule(this,1)" _href="/yqzhaj/jsp/qyjbxx/entBaseInfoList.action" data-title="企业基本信息">
	                <div class="infobox-icon"><img src="${ctx}/webResources/style/images/index/06.png"/></div>
	                <div class="infobox-data">
	                    <h2  style="color:#49a110">日常监管</h2>
	                    <p>清晰快捷三方电话会议</p>
	                </div>
	            </a>
	        	
	            <a href="javascript:void(0)" onclick="clickModule(this,1)" _href="/yqzhaj/jsp/qyjbxx/entBaseInfoList.action" data-title="企业基本信息">
	                <div class="infobox-icon"><img src="${ctx}/webResources/style/images/index/04.png"/></div>
	                <div class="infobox-data">
	                    <h2 style="color:#6dafda">数据中心</h2>
	                    <p>大数据多角度数据挖掘</p>
	                </div>
	            </a>            --%>                             
	        </div>  
			<!--line-->     
			 <div class="line"></div>   
			<!--通知公告 宣传片-->
			<div class="box2">
			   <div class="left">
			       <div class="column">通知公告<span><a href="#">更多>></a></span></div>
			       <ul class="content">
			            <li><a  class="jiantou" href="javascript:void(0)" onclick="clickModule(this,1)" data-title="国家安监局安全生产信息化设计方案" _href="http://www.chinasafety.gov.cn/newpage/Contents/Channel_6288/2017/0113/282328/content_282328.htm" title="国家安全监管总局关于印发安全生产信息化总体建设方案及相关技术文件的通知">
			            	国家安全监管总局关于印发安全生产信息化...</a><span>2016-12-27</span>
			            </li>
			            <li><a class="jiantou" href="#">6月份信息采用情况6月份信息采用情况</a><span>2016-06-18</span></li>
			            <li><a class="jiantou" href="#">6月份信息采用情况（校对稿）6月份信况（校对稿）</a><span>2016-06-18</span></li>
			            <li><a class="jiantou" href="#">一季度信息工作情况及6月份参加一季度信及6月份参加...</a><span>2016-06-18</span></li>
			            <li><a class="jiantou" href="#">网上绩效考评系统操作手册网上绩效考评系手册</a><span>2016-06-18</span></li>
			            <li><a class="jiantou" href="#">厅机关副处级领导干部职位报名厅机关干部职位报名...</a><span>2016-06-18</span></li>  
			       </ul>
			   </div> 
			   <div class="left2">
			       <div class="column2">待办事项<span><a href="#">更多>></a></span></div>
			       <ul class="content">
			            <li><a class="jiantou" href="#">网上绩效考评系统操作手册网上绩效考评系手册</a><span>2016-06-18</span></li>
			            <li><a class="jiantou" href="#">6月份信息采用情况6月份信息采用情况</a><span>2016-06-18</span></li>
			            <li><a class="jiantou" href="#">6月份信息采用情况（校对稿）6月份信况（校对稿）</a><span>2016-06-18</span></li>
			            <li><a class="jiantou" href="#">一季度信息工作情况及6月份参加一季度信及6月份参加...</a><span>2016-06-18</span></li>
			            <li><a class="jiantou" href="#">网上绩效考评系统操作手册网上绩效考评系手册</a><span>2016-06-18</span></li>
			            <li><a class="jiantou" href="#">厅机关副处级领导干部职位报名厅机关干部职位报名...</a><span>2016-06-18</span></li>  
			       </ul>
			   </div>
			</div>
			<!--line-->     
			 <div class="line"></div>  
			<!--刷卡数据 统计表-->        
			<div class="thrbox">
	            <div class="spth_left">
	                  <div class="column">最新门禁刷卡数据<span><a href="#">更多>></a></span></div>
	                  <div class="cell">
	                      <table>
	                        <tr>
	                          <th width="15%">姓名</th>
	                          <th width="35%">单位名称</th>
	                          <th width="15%">时间</th>
	                          <th width="15%" style="border-right:none">通道</th>
	                        </tr>
	                        <tr bgcolor="#f9f9f9">
	                          <td>吴彦祖</td>
	                          <td>江苏富涂建设工程有限公司</td>
	                          <td>11:03</td>
	                          <td style="border-right:none">通道4出</td>
	                        </tr>
	                        <tr>
	                          <td>王传国</td>
	                          <td>江苏电建三</td>
	                          <td>11:03</td>
	                          <td style="border-right:none">通道4出</td>
	                        </tr>
	                         <tr bgcolor="#f9f9f9">
	                          <td>蒲公英</td>
	                          <td>江苏富涂建设工程有限公司</td>
	                          <td>11:03</td>
	                          <td style="border-right:none">通道4出</td>
	                        </tr> 
	                        <tr>
	                          <td>周华</td>
	                          <td>江苏电建三</td>
	                          <td>11:03</td>
	                          <td style="border-right:none">通道4出</td>
	                        </tr>
	                        <tr bgcolor="#f9f9f9">
	                          <td>胡洋洋</td>
	                          <td>通州十建</td>
	                          <td>11:03</td>
	                          <td style="border-right:none">通道4出</td>
	                        </tr>
	                         <tr>
	                          <td>张嘉佳</td>
	                          <td>盐城一建</td>
	                          <td>11:03</td>
	                          <td style="border-right:none">通道4出</td>
	                        </tr>                                                                                                                           
	                      </table>        
	      			</div>
	            </div>
	            <div class="tj_right">
	                    <!-- <div class="column3">最新门禁刷卡数据统计表<span><a href="#">更多>></a></span></div> -->
	                    <div class="cell" id="myChart1" style="width:698px;height:324px;"></div>
	            </div>
			</div>
			<!--line-->     
			 <div class="line"></div>  
			<!--违章检查 专项检查-->        
			<div class="thrbox3">
			            <div class="wzjc_left">
			            	<div class="cell" id="myChart2" style="width:698px;height:424px;"></div>
			                   <%--  <div class="column">违章检查记录统计<span><a href="#">更多>></a></span></div>
			                    <div class="cell"><img src="${ctx}/webResources/style/images/index/tjb.png"></div> --%>
			            </div>
			            <div class="zxjc_right">
			            	<div class="cell" id="myChart3" style="width:650px;height:424px;"></div>
			                   <%--  <div class="column3">专项检查记录统计<span><a href="#">更多>></a></span></div>
			                    <div class="cell"><img src="${ctx}/webResources/style/images/index/tjb.png"></div> --%>
			            </div>
			</div>
		<!--end-->
        </div>        
	</div>
<%@include file="/h-ui/lib-js.jsp" %>
<script src="${ctx}/webResources/echarts/echarts.js"></script>
<script type="text/javascript">

	
	var myChart = echarts.init(document.getElementById('myChart1'));
	myChart.showLoading();
	$.get('${ctx}/jsp/echarts/getSbqyCharts.action').done(function (data2) {
		if(""==data2){
			myChart.hideLoading();
			return false;
		}
		var data = eval('('+ data2 +')');
		myChart.hideLoading();
	    myChart.setOption({
	        title: {
	            text: '企业上报统计图',
	            textStyle: {
		            color: '#4383c4'
		        }
	        },
	        tooltip : {
	            trigger: 'axis'
	        },
	        legend: {
    	        data:['企业总数','上报企业数']
    	    },
    	    toolbox: {
    	        show : true,
    	        feature : {
    	            dataView : {show: true, readOnly: false},
    	            magicType : {show: true, type: ['line', 'bar']},
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : true,
    	    dataZoom: [
    	               {   // 这个dataZoom组件，默认控制x轴。
    	                   type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
    	                   start: 10,      // 左边在 10% 的位置。
    	                   end: 60         // 右边在 60% 的位置。
    	               },
    	               {   // 这个dataZoom组件，也控制x轴。
    	                   type: 'inside', // 这个 dataZoom 组件是 inside 型 dataZoom 组件
    	                   start: 10,      // 左边在 10% 的位置。
    	                   end: 60         // 右边在 60% 的位置。
    	               }
    	           ],
	        xAxis: {
	        	type : 'category',
	            data: data.xAxisArr
	        },
	        yAxis : [
	                 {
	                     type : 'value'
	                 }
	             ],
	        series: [
						{
						    name:'企业总数',
						    type:'bar',
						    data:data.usedArr,
						    markLine : {
						        data : [
						            {type : 'average', name: '平均值'}
						        ]
						    }
						},
						{
						    name:'上报企业数',
						    type:'bar',
						    data:data.restArr,
						    markLine : {
						        data : [
						            {type : 'average', name : '平均值'}
						        ]
						    }
						}
	                 ]
	    });
	 	// 处理点击事件并且跳转到相应的百度搜索页面
	    myChart.on('click', function (params) {
	        window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
	    });
	});
	
	// Schema:
	// 基于准备好的dom，初始化echarts实例
	var myChart2 = echarts.init(document.getElementById('myChart2'));
	var dataSD = [
	    [1,91,45,125,0.82,34,23,"良"],
	    [2,65,27,78,0.86,45,29,"良"],
	    [3,83,60,84,1.09,73,27,"良"],
	    [4,109,81,121,1.28,68,51,"轻度污染"],
	    [5,106,77,114,1.07,55,51,"轻度污染"],
	    [6,109,81,121,1.28,68,51,"轻度污染"],
	    [7,106,77,114,1.07,55,51,"轻度污染"],
	    [8,89,65,78,0.86,51,26,"良"],
	    [9,53,33,47,0.64,50,17,"良"],
	    [10,80,55,80,1.01,75,24,"良"],
	    [11,117,81,124,1.03,45,24,"轻度污染"],
	    [12,99,71,142,1.1,62,42,"良"],
	    [13,95,69,130,1.28,74,50,"良"],
	    [14,116,87,131,1.47,84,40,"轻度污染"],
	    [15,108,80,121,1.3,85,37,"轻度污染"],
	    [16,134,83,167,1.16,57,43,"轻度污染"],
	    [17,79,43,107,1.05,59,37,"良"],
	    [18,71,46,89,0.86,64,25,"良"],
	    [19,97,71,113,1.17,88,31,"良"],
	    [20,84,57,91,0.85,55,31,"良"],
	    [21,87,63,101,0.9,56,41,"良"],
	    [22,104,77,119,1.09,73,48,"轻度污染"],
	    [23,87,62,100,1,72,28,"良"],
	    [24,168,128,172,1.49,97,56,"中度污染"],
	    [25,65,45,51,0.74,39,17,"良"],
	    [26,39,24,38,0.61,47,17,"优"],
	    [27,39,24,39,0.59,50,19,"优"],
	    [28,93,68,96,1.05,79,29,"良"],
	    [29,188,143,197,1.66,99,51,"中度污染"],
	    [30,174,131,174,1.55,108,50,"中度污染"],
	    [31,187,143,201,1.39,89,53,"中度污染"]
	];

	var schema = [
	    {name: 'date', index: 0, text: '日期'},
	    {name: 'AQIindex', index: 1, text: 'AQI'},
	    {name: 'PM25', index: 2, text: 'PM2.5'},
	    {name: 'PM10', index: 3, text: 'PM10'},
	    {name: 'CO', index: 4, text: ' CO'},
	    {name: 'NO2', index: 5, text: 'NO2'},
	    {name: 'SO2', index: 6, text: 'SO2'},
	    {name: '等级', index: 7, text: '等级'}
	];

	var lineStyle = {
	    normal: {
	        width: 1,
	        opacity: 0.8
	    }
	};

	myChart2.setOption({
	    title: {
	    	 textStyle: {
		            color: '#4383c4'
		        },
	        left: 'center',
            text: '空气质量报告(当月)'
        },
	    legend: {
	        bottom: 30,
	        data: ['苏州'],
	        itemGap: 20,
	        textStyle: {
	            color: 'black',
	            fontSize: 14
	        }
	    },
	    tooltip: {
	        padding: 10,
	        borderColor: '#777',
	        borderWidth: 1,
	        formatter: function (obj) {
	            var value = obj[0].value;
	            return '<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
	                + obj[0].seriesName + ' ' + value[0] + '日期：'
	                + value[7]
	                + '</div>'
	                + schema[1].text + '：' + value[1] + '<br>'
	                + schema[2].text + '：' + value[2] + '<br>'
	                + schema[3].text + '：' + value[3] + '<br>'
	                + schema[4].text + '：' + value[4] + '<br>'
	                + schema[5].text + '：' + value[5] + '<br>'
	                + schema[6].text + '：' + value[6] + '<br>';
	        }
	    },
	    // dataZoom: {
	    //     show: true,
	    //     orient: 'vertical',
	    //     parallelAxisIndex: [0]
	    // },
	    parallelAxis: [
	        {dim: 0, name: schema[0].text, inverse: true, max: 31, nameLocation: 'start'},
	        {dim: 1, name: schema[1].text},
	        {dim: 2, name: schema[2].text},
	        {dim: 3, name: schema[3].text},
	        {dim: 4, name: schema[4].text},
	        {dim: 5, name: schema[5].text},
	        {dim: 6, name: schema[6].text},
	        {dim: 7, name: schema[7].text,
	        type: 'category', data: ['优', '良', '轻度污染', '中度污染', '重度污染', '严重污染']}
	    ],
	    visualMap: {
	        show: true,
	        min: 0,
	        max: 150,
	        dimension: 2,
	        inRange: {
	            color: ['#d94e5d','#eac736','#50a3ba'].reverse(),
	            // colorAlpha: [0, 1]
	        }
	    },
	    parallel: {
	        left: '5%',
	        right: '18%',
	        bottom: 100,
	        parallelAxisDefault: {
	            type: 'value',
	            name: 'AQI指数',
	            nameLocation: 'end',
	            nameGap: 20,
	            nameTextStyle: {
	                fontSize: 12
	            },
	            axisLine: {
	                lineStyle: {
	                }
	            },
	            axisTick: {
	                lineStyle: {
	                }
	            },
	            splitLine: {
	                show: false
	            },
	            axisLabel: {
	                textStyle: {
	                }
	            }
	        }
	    },
	    series: [
	        {
	            name: '苏州',
	            type: 'parallel',
	            lineStyle: lineStyle,
	            data: dataSD
	        }
	    ]
	});
	
	var myChart3 = echarts.init(document.getElementById('myChart3'));
	myChart3.setOption({

		    title: {
		        text: '各区镇企业总数比重图',
		        left: 'center',
		        top: 20,
		        textStyle: {
		            color: '#4383c4'
		        }
		    },

		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },

		    visualMap: {
		        show: false,
		        min: 80,
		        max: 600,
		        inRange: {
		            colorLightness: [0, 1]
		        }
		    },
		    series : [
		        {
		            name:'访问来源',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '50%'],
		            data:[
		                {value:12, name:'国际商贸区'},
		                {value:224, name:'湖东'},
		                {value:0, name:'阳澄半岛度假区'},
		                {value:130, name:'东沙湖'},
		                {value:2, name:'科教创新区'},
		                {value:103, name:'娄葑'},
		                {value:84, name:'斜塘'},
		                {value:73, name:'唯亭'},
		                {value:222, name:'胜浦'},
		                {value:135, name:'湖西'}
		            ].sort(function (a, b) { return a.value - b.value}),
		            roseType: 'angle',
		            label: {
		                normal: {
		                    textStyle: {
		                        
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    lineStyle: {
		                       
		                    },
		                    smooth: 0.2,
		                    length: 10,
		                    length2: 20
		                }
		            },
		            itemStyle: {
		                normal: {
		                	color: '#c23531',
		                    shadowBlur: 200,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            },

		            animationType: 'scale',
		            animationEasing: 'elasticOut',
		            animationDelay: function (idx) {
		                return Math.random() * 200;
		            }
		        }
		    ]
		});

	function clickModule(obj,index){
		var url = $(obj).attr("_href");
		parent.location.href=url;
		//window.open(url);
		//window.parent.clickModule(obj,index);
	}
	
   
   
	
</script> 
</body>
</html>

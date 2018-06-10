<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>企业自查统计</title>
    <%@include file="/common/jsLib.jsp"%>
    <link class="include" rel="stylesheet" type="text/css"
		href="${ctx}/css/jquery.jqplot.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/examples.min.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/shCoreDefault.min.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/shThemejqPlot.min.css" />

	<script language="javascript" type="text/javascript"
		src="${ctx}/js/excanvas.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/js/jquery-1.4.4.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/js/jquery.jqplot.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/shCore.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/shBrushJScript.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/shBrushXml.min.js"></script>
	<!-- Additional plugins go here -->

	<script class="include" type="text/javascript"
		src="${ctx}/js/jqplot.barRenderer.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/js/jqplot.pieRenderer.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/js/jqplot.categoryAxisRenderer.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/js/jqplot.pointLabels.min.js"></script> <script class="code" type="text/javascript">
    $(document).ready(function(){

    var plot1 = $.jqplot('pie1', [[['未上报企业',${tjyhBean.wsbqy}],['非零隐患企业',${tjyhBean.noZeroCount}],['零隐患企业',${tjyhBean.zeroCount}]]], {

        gridPadding: {top:0, bottom:38, left:0, right:0},

        seriesDefaults:{

            renderer:$.jqplot.PieRenderer, 

            trendline:{ show:false }, 

            rendererOptions: { padding: 8, showDataLabels: true }

        },

        legend:{

            show:true, 

            placement: 'outside', 

            rendererOptions: {

                numberRows: 1

            }, 

            location:'s',

            marginTop: '15px'

        }       

    });

});</script>
	<script>
		var rad = "1";
        function addNew(){
        	var dt=new Date();
        	window.open("${ctx}/jsp/zcyhsb/jshxZcyhsbInitEdit.action?flag=add&dt="+dt.getTime());
        }
        function changeDiv(obj){
              rad = obj;
        	 var queryJhwcsjStart = $("#queryJhwcsjStart").val();
        	 var queryJhwcsjEnd = $("#queryJhwcsjEnd").val();
        	 var szzid = $("#areaName").val();
        	 var qymc = $("#qymc").val();
        	 var whpqylx = $("#whpqylx").val();
        	 var aqbzdbjb = $("#aqbzdbjb").val();
       		 document.getElementById("tj_div"+obj).style.display="block";
        	for(var i=1;i<6;i++){
        		if(obj != i){
        			document.getElementById("tj_div"+i).style.display="none";
        		}
        		
        	}
        	if(obj == '2'){
        		document.getElementById('aa').style.display="none";
        		$("#pie2_data").load("${ctx}/jsp/zcyhsb/jshxZcyhsbDataPie.action?queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd+"&jshxZcyhsb.szzid="+szzid+"&jshxZcyhsb.qymc="+encodeURIComponent(qymc)+"&jshxZcyhsb.whpqylx="+whpqylx+"&jshxZcyhsb.aqbzdbjb="+aqbzdbjb);
        	}
        	if(obj == '3'){
        		document.getElementById('aa').style.display="none";
        		$("#chart33").load("${ctx}/jsp/zcyhsb/jshxZcyhsbChart3.action?queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd+"&jshxZcyhsb.szzid="+szzid+"&jshxZcyhsb.qymc="+encodeURIComponent(qymc)+"&jshxZcyhsb.whpqylx="+whpqylx+"&jshxZcyhsb.aqbzdbjb="+aqbzdbjb);
        	}
        	if(obj=='1'){//企业上报情况统计
        		document.getElementById('aa').style.display="none";
        		window.location.href="${ctx}/jsp/zcyhsb/jshxZcyhsbTjList.action?queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd+"&jshxZcyhsb.szzid="+szzid+"&jshxZcyhsb.qymc="+encodeURIComponent(qymc)+"&jshxZcyhsb.whpqylx="+whpqylx+"&jshxZcyhsb.aqbzdbjb="+aqbzdbjb;
        	}else if(obj =='4'){//部门统计
        		document.getElementById('aa').style.display="";
        		$("#tj_div4").load("${ctx}/jsp/zcyhsb/jshxZcyhsbDataDept.action?queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd+"&jshxZcyhsb.szzid="+szzid+"&jshxZcyhsb.qymc="+encodeURIComponent(qymc)+"&jshxZcyhsb.whpqylx="+whpqylx+"&jshxZcyhsb.aqbzdbjb="+aqbzdbjb);
        	}else if(obj =='5'){//行业类别统计
        		document.getElementById('aa').style.display="";
        		$("#tj_div5").load("${ctx}/jsp/zcyhsb/jshxZcyhsbDataHyfl.action?queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd+"&jshxZcyhsb.szzid="+szzid+"&jshxZcyhsb.qymc="+encodeURIComponent(qymc)+"&jshxZcyhsb.whpqylx="+whpqylx+"&jshxZcyhsb.aqbzdbjb="+aqbzdbjb);
        	}else{
        		$.ajax({
	                	url : "jshxZcyhsbTjType.action?obj="+obj+"&queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd+"&jshxZcyhsb.szzid="+szzid+"&jshxZcyhsb.qymc="+encodeURIComponent(qymc)+"&jshxZcyhsb.whpqylx="+whpqylx+"&jshxZcyhsb.aqbzdbjb="+aqbzdbjb,
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    error: function(){
	                    	$.messager.alert('错误','删除时出错！');
	                    },
	                    success: function(data){
	                    		if(data.data1== -1){//隐患类别
	                    			 tjYhType(data.data2,data.data3);
	                    			 document.getElementById("sp2_1").innerHTML = data.data2;
	                    			 document.getElementById("sp2_2").innerHTML = data.data2_2;
	                    			 document.getElementById("sp2_3").innerHTML = data.data2_3;
	                    			 document.getElementById("sp2_4").innerHTML = data.data2_4;
	                    			 document.getElementById("sp2_5").innerHTML = data.data2_5;
	                    			 document.getElementById("sp3_1").innerHTML = data.data3;
	                    			 document.getElementById("sp3_2").innerHTML = data.data3_2;
	                    			 document.getElementById("sp3_3").innerHTML = data.data3_3;
	                    			 document.getElementById("sp3_4").innerHTML = data.data3_4;
	                    			 document.getElementById("sp3_5").innerHTML = data.data3_5;
	                    			  divChart11();
							       	  divChart1(data.data_jc,data.name_jc);
	                    		}else{//检查类别
	                    			tjType(data.data1,data.data2,data.data3);
	                    		}
	                    		
	                    	}
		                });
        			}
        }
        function tjType(i,j,k){
			var plot2 = $.jqplot('pie3', [[['企业自查',i],['专家检查',j],['企业互评互查',k]]], {

        	gridPadding: {top:0, bottom:38, left:0, right:0},

       		 seriesDefaults:{

            renderer:$.jqplot.PieRenderer,

            trendline:{ show:false }, 

            rendererOptions: { padding: 8, showDataLabels: true }

       		 },

        legend:{

            show:true, 

            placement: 'outside', 

            rendererOptions: {

                numberRows: 1

            }, 

            location:'s',

            marginTop: '15px'

        	}    
       	  });
        	
        }
         function tjYhType(i,j){
			var plot2 = $.jqplot('pie5', [[['基础管理',i],['现场管理',j]]], {

        	gridPadding: {top:0, bottom:38, left:0, right:0},

       		 seriesDefaults:{

            renderer:$.jqplot.PieRenderer,

            trendline:{ show:false }, 

            rendererOptions: { padding: 8, showDataLabels: true }

       		 },

        legend:{

            show:true, 

            placement: 'outside', 

            rendererOptions: {

                numberRows: 1

            }, 

            location:'s',

            marginTop: '15px'

        	}    
       	  });
        	
        }
        function divChart11(){
        	$("#chart1").empty();
        } 
        function divChart22(){
        	$("#chart3").empty();
        }
        function divChart1(i,j){
        	var s1 = i.split(',');
       		var ticks = j.split(',');
        	plot1 = $.jqplot('chart1', [s1], {
        	title: '基础检查中类',      //设置当前图的标题 
            seriesDefaults:{
                renderer:$.jqplot.BarRenderer,
                pointLabels: { show: true }
            },
            axes: {
                xaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer,
                    ticks: ticks
                }
            },
            highlighter: { show: false }
        }); 
        }
         function divChart2(i,j){
        	 var s1 = i.split(',');
       		 var ticks = j.split(',');
        
        	plot1 = $.jqplot('chart3', [s1], {
        	title: '现场检查中类',      //设置当前图的标题 
            seriesDefaults:{
                renderer:$.jqplot.BarRenderer,
                pointLabels: { show: true }
                
            },
            
            axes: {
                xaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer,
                    ticks: ticks
                }
            },  
            
            highlighter: { show: false }
        }); 
        }
        
        
        function search_tj(){
          	 changeDiv(rad);
        }
         //清除查询表单中的搜索条件
        function clear_form(ff){
            var elements = ff.elements;
            for(i=0;i<elements.length;i++){
                var element = elements[i];
                if(element.type=="text"){
                    element.value = "";
                }else if(element.options!=null){
                	element.options[0].selected  = true;
                }
            }
        }
        
        function exportData()
        {
        	var type = document.getElementsByName("type");
        	var obj;
			for(var i=0;i<type.length;i++){
				if(type[i].checked) 
				{	
					obj = type[i].value;
				}
			}
        	if(obj == '4')
        	{
        		document.myform.action = "${ctx}/jsp/zcyhsb/jshxZcyhsbDataDeptExport.action";
        		document.myform.submit();
        	}
        	else if(obj == '5')
        	{
        		document.myform.action = "${ctx}/jsp/zcyhsb/jshxZcyhsbDataHyflExport.action";
        		document.myform.submit();
        	}
        }
        
        function querySzc(obj){
			var szzid = $('#szzid').val();
			if(szzid == ""){
				$('#areaName').val($('#county').val());
			}else{
				$('#areaName').val($('#szzid').val());
			}
	    }
		function querySzz(obj)
	    {
			$('#areaName').val($('#county').val());
	    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#szzid'); 
						selectContainer.empty();
						var option = $('<option></option>').text("").val(""); 
						selectContainer.append(option); 
		  				for(var i=0; i<json.length; i++){
					    	option = $('<option></option>').text(json[i].name).val(json[i].id);
							selectContainer.append(option); 
					 	}
					},
					dateType:"json"
				});
	    } 
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
<form name="myform" method="post">
	<input type="hidden" id="qyid" value="${jshxZcyhsb.qyid}"/>
	<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">所属地区</th>
					<td width="35%">
						<input type="hidden" id="areaName">
						<cus:SelectOneTag property="county" style="width:37%;"   defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"/>
						<select id="szzid" name="jshxZcyhsb.szzid" style="width:37%;" onchange="querySzc(this.value);"><option value="">请选择</option></select>
					</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="jshxZcyhsb.qymc" id="qymc" value="${jshxZcyhsb.qymc}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">检查时间</th>
				<td width="35%"><input name="queryJhwcsjStart" id="queryJhwcsjStart" value="<fmt:formatDate type='date' value='${queryJhwcsjStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJhwcsjEnd\')}'})" >
					-<input name="queryJhwcsjEnd" id="queryJhwcsjEnd" value="<fmt:formatDate type='date' value='${queryJhwcsjEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJhwcsjStart\')}'})" ></td>
				<th width="15%">危险化学品从业单位</th>
				<td width="35%"><cus:SelectOneTag property="jshxZcyhsb.whpqylx" defaultText='请选择' codeName="危化品企业类型" value="${jshxZcyhsb.whpqylx}"/></td>
			</tr>
			<tr>
				<th width="15%">安全生产标准化达标级别</th>
				<td width="35%"><s:select id="aqbzdbjb" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','1':'一级','2':'二级','3':'三级'}" name="jshxZcyhsb.aqbzdbjb" value="{jshxZcyhsb.aqbzdbjb}"/></td>
				<th width="15%">是否直属</th>
					<td width="35%" >
						<cus:SelectOneTag property="jshxZcyhsb.ifzsqy" style="width:37%;" defaultText='请选择' codeName="是否直属" value="${jshxZcyhsb.ifzsqy}" />
					</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center;">
					<s:radio theme="simple" list="#{'1':'企业上报情况','2':'检查类别','3':'隐患类别','4':'所属部门','5':'行业类别'}" name="type" value="1" onclick="changeDiv(this.value)"/>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
					<a href="###" class="easyui-linkbutton" onclick="search_tj()" iconCls="icon-search">查询</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;	
					<a id="aa" style="display:none" href="###" class="easyui-linkbutton" onclick="exportData()" iconCls="icon-add">导出</a>&nbsp;							
				</td>
			</tr>
		</table>
	</div>
	<br>
		<center>
			<div id="tj" style="display:block;">
					<div id="tj_div1">
					<table cellspacing="0" cellpadding="0" width="85%" border="0">
						<tr>
							<td>
								<div id="pie1" style="margin-top:20px; width:500px; height:350px;float:left;"></div>
								<div id="pie2" style="margin-top:20px;margin-left:10px; width:500px; height:350px;float:left;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr style="background-color:#507CD1;text-align:center;">
											<td height="40px;" colspan="2"><font style="font-family:'幼圆';color:white;font-size:20px">企业隐患检查</font></td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">企业总数</td>
											<td>${tjyhBean.qyTotal}</td>
										</tr>
										<tr style="text-align:center;">
											<td height="40px;">上报企业数</td>
											<td>${tjyhBean.sbqy}</td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">未上报企业数</td>
											<td>${tjyhBean.wsbqy}</td>
										</tr>
										<tr  style="text-align:center;">
											<td height="40px;">隐患总数</td>
											<td>${tjyhBean.yhTotal}</td>
										</tr >
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">整改完成数</td>
											<td>${tjyhBean.zgwc}</td>
										</tr>
										<tr style="text-align:center;">
											<td height="40px;">整改未完成数</td>
											<td>${tjyhBean.zgwwc}</td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">整改率</td>
											<td>${tjyhBean.zgl}</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div id="tj_div2" style="display:none;">
					<table cellspacing="0" cellpadding="0" width="85%" border="0">
						<tr>
							<td>
								<div id="pie3" style="margin-top:20px; width:500px; height:350px;float:left;"></div>
								<div id="pie4" style="margin-top:20px;margin-left:10px; width:500px; height:350px;float:left;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr style="background-color:#507CD1;text-align:center;">
											<td height="40px;" colspan="2"><font style="font-family:'幼圆';color:white;font-size:20px">企业隐患检查</font></td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">企业总数</td>
											<td>${tjyhBean.qyTotal}</td>
										</tr>
										<tr style="text-align:center;">
											<td height="40px;">上报企业数</td>
											<td>${tjyhBean.sbqy}</td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">未上报企业数</td>
											<td>${tjyhBean.wsbqy}</td>
										</tr>
										<tr  style="text-align:center;">
											<td height="40px;">隐患总数</td>
											<td>${tjyhBean.yhTotal}</td>
										</tr >
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">整改完成数</td>
											<td>${tjyhBean.zgwc}</td>
										</tr>
										<tr style="text-align:center;">
											<td height="40px;">整改未完成数</td>
											<td>${tjyhBean.zgwwc}</td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">整改率</td>
											<td>${tjyhBean.zgl}</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td height="20px;"></td>
						</tr>
					</table>
					<div id="pie2_data">
						
					</div>
				</div>
				
				<div id="tj_div3" style="display:none;">
					<table cellspacing="0" cellpadding="0" width="85%" border="0">
						<tr>
							<td>
								<div id="pie5" style="margin-top:20px; width:500px; height:350px;float:left;"></div>
								<div id="pie6" style="margin-top:20px;margin-left:10px; width:500px; height:350px;float:left;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr style="background-color:#507CD1;text-align:center;">
											<td height="40px;" colspan="2"><font style="font-family:'幼圆';color:white;font-size:20px">企业隐患检查</font></td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">企业总数</td>
											<td>${tjyhBean.qyTotal}</td>
										</tr>
										<tr style="text-align:center;">
											<td height="40px;">上报企业数</td>
											<td>${tjyhBean.sbqy}</td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">未上报企业数</td>
											<td>${tjyhBean.wsbqy}</td>
										</tr>
										<tr  style="text-align:center;">
											<td height="40px;">隐患总数</td>
											<td>${tjyhBean.yhTotal}</td>
										</tr >
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">整改完成数</td>
											<td>${tjyhBean.zgwc}</td>
										</tr>
										<tr style="text-align:center;">
											<td height="40px;">整改未完成数</td>
											<td>${tjyhBean.zgwwc}</td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">整改率</td>
											<td>${tjyhBean.zgl}</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div id="chart11"></div>
								<div id="chart1" style="margin-top:20px; width:500px; height:350px;float:left;"></div>
								<div id="chart2" style="margin-top:20px;margin-left:10px; width:500px; height:350px;float:left;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr style="background-color:#507CD1;text-align:center;">
											<td height="40px;" colspan="2"><font style="font-family:'幼圆';color:white;font-size:20px">基础管理</font></td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">隐患总数</td>
											<td><span id="sp2_1"></td>
										</tr>
										<tr style="text-align:center;">
											<td height="40px;">整改完成数</td>
											<td><span id="sp2_3"></td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">整改未完成数</td>
											<td><span id="sp2_2"></td>
										</tr>
										<tr  style="text-align:center;">
											<td height="40px;">整改率</td>
											<td><span id="sp2_4"></td>
										</tr >
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">占所有隐患</td>
											<td><span id="sp2_5"></td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div id="chart33"></div>
								<div id="chart4" style="margin-top:20px;margin-left:10px; width:500px; height:350px;float:left;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr style="background-color:#507CD1;text-align:center;">
											<td height="40px;" colspan="2"><font style="font-family:'幼圆';color:white;font-size:20px">现场管理</font></td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">隐患总数</td>
											<td><span id="sp3_1"></span></td>
										</tr>
										<tr style="text-align:center;">
											<td height="40px;">整改完成数</td>
											<td><span id="sp3_3"></span></td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">整改未完成数</td>
											<td><span id="sp3_2"></span></td>
										</tr>
										<tr  style="text-align:center;">
											<td height="40px;">整改率</td>
											<td><span id="sp3_4"></span></td>
										</tr >
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="40px;">占所有隐患</td>
											<td><span id="sp3_5"></span></td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="tj_div4">
				
			</div>
			<div id="tj_div5">
				
			</div>
		</center>
</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

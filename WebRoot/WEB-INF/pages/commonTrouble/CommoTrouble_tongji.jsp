<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>一般安全隐患统计</title>
	<%@include file="/common/jsLib.jsp"%>
    <link class="include" rel="stylesheet" type="text/css" href="${ctx}/css/jquery.jqplot.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/examples.min.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/shCoreDefault.min.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/shThemejqPlot.min.css" />

	<script language="javascript" type="text/javascript" src="${ctx}/js/excanvas.js"></script>
	
	<script class="include" type="text/javascript" src="${ctx}/js/jquery.jqplot.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/shCore.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/shBrushJScript.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/shBrushXml.min.js"></script>
	<!-- Additional plugins go here -->

	<script class="include" type="text/javascript" src="${ctx}/js/jqplot.barRenderer.min.js"></script>
	<script class="include" type="text/javascript" src="${ctx}/js/jqplot.pieRenderer.min.js"></script>
	<script class="include" type="text/javascript" src="${ctx}/js/jqplot.categoryAxisRenderer.min.js"></script>
	<script class="include" type="text/javascript" src="${ctx}/js/jqplot.pointLabels.min.js"></script> 
	
	<script class="code" type="text/javascript">
	
    $(document).ready(function(){
    	var plot1 = $.jqplot('pie1', [[['现场检查记录',${zfwsData.a1}],['责令限期整改指令书',${zfwsData.a2}],['强制措施决定书',${zfwsData.a3}],['整改复查意见书',${zfwsData.a4}]]], {

        gridPadding: {top:0, bottom:38, left:0, right:0},

        seriesDefaults:{

            renderer:$.jqplot.PieRenderer, 

            trendline:{ show:true }, 

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

});
	</script>
	<script>
		var rad = "1";
       
        function changeDiv(obj){
              rad = obj;
        	 var queryJhwcsjStart = $("#queryJhwcsjStart").val();
        	 var queryJhwcsjEnd = $("#queryJhwcsjEnd").val();
	        	for(var i=1;i<6;i++){
	        		if(obj != i){
	        			document.getElementById("tj_div"+i).style.display="none";
	        		}
	        	}
	        	 document.getElementById("tj_div"+obj).style.display="block";
	        	if(obj =='1'){//执法文书统计
	        		document.getElementById('aa').style.display="none";
	        		window.location.href = "${ctx}/jsp/commonTrouble/commoTroubleTongJi.action?queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd;
	        	}else if(obj =='2'){//按所属部门统计
	        		document.getElementById('aa').style.display="";
	        		$("#tj_div2").load("${ctx}/jsp/commonTrouble/commoTroubleDeptData.action?queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd);
	        	}else if(obj =='3'){//行业类别统计
	        		document.getElementById('aa').style.display="";
	        		$("#tj_div3").load("${ctx}/jsp/commonTrouble/commoTroubleDataQylx.action?queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd);
	        	}else if(obj =='4'){//按企业统计
	        		document.getElementById('aa').style.display="";
	        		$("#tj_div4").load("${ctx}/jsp/commonTrouble/commoTroubleDataQyList.action?queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd);
	        	}else if(obj =='5'){//按上报 部门统计
	        		document.getElementById('aa').style.display="";
	        		$("#tj_div5").load("${ctx}/jsp/commonTrouble/commoTroubleDeptData.action?queryJhwcsjStart="+queryJhwcsjStart+"&queryJhwcsjEnd="+queryJhwcsjEnd+"&data=5");
	        	}
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
        	if(obj == '2')
        	{
        		document.myform.action = "${ctx}/jsp/commonTrouble/commoTroubleDeptDataExport.action";
        		document.myform.submit();
        	}
        	else if(obj == '3')
        	{
        		document.myform.action = "${ctx}/jsp/commonTrouble/commoTroubleDataQylxExport.action";
        		document.myform.submit();
        	}
        	else if(obj == '4')
        	{
        		document.myform.action = "${ctx}/jsp/commonTrouble/commoTroubleDataQyExport.action";
        		document.myform.submit();
        	}
        	else if(obj == '5')
        	{
        		document.myform.action = "${ctx}/jsp/commonTrouble/commoTroubleDeptDataExport.action?data=5";
        		document.myform.submit();
        	}
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
				<th width="15%">查询时间</th>
				<td width="35%"><input name="queryJhwcsjStart" id="queryJhwcsjStart" value='${queryJhwcsjStart}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJhwcsjEnd\')}'})" >
					-<input name="queryJhwcsjEnd" id="queryJhwcsjEnd" value='${queryJhwcsjEnd}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJhwcsjStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">查询类别</th>
				<td width="35%">
					<s:radio theme="simple" list="#{'1':'执法文书','2':'所属地区','3':'企业类型','4':'按企业','5':'上报部门'}" name="type" value="1" onclick="changeDiv(this.value)"/>
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
		
				<div id="tj_div1">
				<center>
					<table cellspacing="0" cellpadding="0" width="85%" border="0">
						<tr>
							<td>
								<div id="pie1" style="margin-top:20px; width:500px; height:350px;float:left;"></div>
								<div id="pie2" style="margin-top:20px;margin-left:10px; width:500px; height:350px;float:left;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr style="background-color:#507CD1;text-align:center;">
											<td height="40px;" colspan="2"><font style="font-family:'幼圆';color:white;font-size:20px">按执法文书统计记录</font></td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="65px;">现场检查记录</td>
											<td>${zfwsData.a1}</td>
										</tr>
										<tr style="text-align:center;">
											<td height="65px;">责令限期整改指令书</td>
											<td>${zfwsData.a2}</td>
										</tr>
										<tr  style="background-color:#E5ECF6;text-align:center;">
											<td height="65px;">强制措施决定书</td>
											<td>${zfwsData.a3}</td>
										</tr>
										<tr  style="text-align:center;">
											<td height="65px;">整改复查意见书</td>
											<td>${zfwsData.a4}</td>
										</tr >
									</table>
								</div>
							</td>
						</tr>
					</table>
					</center>
				</div>
			<div id="tj_div2">
				
			</div>
			<div id="tj_div3">
				
			</div>
			<div id="tj_div4">
			</div>
			<div id="tj_div5">
			</div>
</form>
</body>
</html>

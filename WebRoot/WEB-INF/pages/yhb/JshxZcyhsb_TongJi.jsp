<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>企业自查统计</title>
    
    <link rel="stylesheet" href="${ctx}/webResources/chosen-0.13/chosen.css">
    <link class="include" rel="stylesheet" type="text/css"
		href="${ctx}/webResources/css/jquery.jqplot.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/css/examples.min.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/webResources/css/shCoreDefault.min.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/webResources/css/shThemejqPlot.min.css" />

	<script language="javascript" type="text/javascript"
		src="${ctx}/webResources/js/excanvas.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/webResources/js/jquery-1.4.4.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/webResources/js/jquery.jqplot.min.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/shCore.min.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/shBrushJScript.min.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/shBrushXml.min.js"></script>
	<!-- Additional plugins go here -->

	<script class="include" type="text/javascript"
		src="${ctx}/webResources/js/jqplot.barRenderer.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/webResources/js/jqplot.pieRenderer.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/webResources/js/jqplot.categoryAxisRenderer.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/webResources/js/jqplot.pointLabels.min.js"></script> <script class="code" type="text/javascript">
    $(document).ready(function(){
    	$("#tj_div2").load("${ctx}/jsp/yhb/jshxZcyhsbDataDept.action?tongjiType=dept");
    	$(document.body).attr("style","overflow : auto");
		});

		

</script>
<%@include file="/common/jsLibWithoutJq183.jsp"%>
	<script>
		var rad = "2";
        
        function changeDiv(obj){
             rad = obj;
             var starttime = $("#starttime").val();
        	 var endtime = $("#endtime").val();
        	 var areaId = $("#areaId").val();
        	 var companyName = $("#companyName").val();
        	 if(obj=='2'){
     	        $("#tj_div2").show();
     	       $("#tj_div1").hide();
     	       $("#tj_div3").hide();
     	        $("#comList").hide();
     	      $("#tj_div2").load("${ctx}/jsp/yhb/jshxZcyhsbDataDept.action?tongjiType=dept&starttime="+starttime+"&endtime="+endtime+"&troMan.areaId="+areaId+"&troMan.companyName="+companyName);
     	    
     	     }else if(obj=='1'){
     	        $("#tj_div1").show();
     	       $("#tj_div2").hide();
     	       $("#comList").hide();
     	       $("#tj_div3").hide();
     	      $.ajax({
              	url : "getCounts.action",
              	 data:{ 
		                    	starttime:starttime,
		                    	endtime:endtime
		                    },
              	type: 'post',
                  dataType: 'json',
                  async : false,
                  error: function(){
                  	$.messager.alert('错误','查询时出错！');
                  },
                  success: function(data){
                  			 tjYhType(data.data2,data.data3);
                  			
                  		
                  	}
	                });
     	     }else if(obj=='3'){
     	        $("#tj_div3").show();
     	       $("#tj_div1").hide();
     	       $("#tj_div2").hide();
     	       $("#comList").hide();
     	      $("#tj_div3").load("${ctx}/jsp/yhb/jshxZcyhsbDataDept.action?tongjiType=grid&starttime="+starttime+"&endtime="+endtime+"&troMan.areaId="+areaId+"&troMan.companyName="+companyName);
     	     }else if(obj=='4'){
     	     	$("#tj_div3").hide();
     	        $("#tj_div2").hide();
     	       $("#tj_div1").hide();
     	       $("#comList").show();
     	      pag();
     	      //$("#tj_div2").load("${ctx}/jsp/yhb/jshxZcyhsbDataCompany.action?starttime="+starttime+"&endtime="+endtime+"&troMan.areaId="+areaId+"&troMan.companyName="+companyName);
     	    
     	     }
        	
        	
        	
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
                }else if(element.type=="checkbox"){
                	element.checked = false;
                }else if(element.options!=null){
                	element.options[0].selected  = true;
                	$("#qytest").trigger("liszt:updated");
                }
            }
        }
        
          function pag(){
        	var toolbar = [];
        	var frozen=[];
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'企业隐患统计',
				url:'companyTroManTongji.action',
				queryParams:{
					"troMan.companyName": $("#companyName").val(),
					"starttime":$("#starttime").val(),
			        "endtime":$("#endtime").val()
				},
				frozenColumns:frozen,
				columns:[[
				{field:'dwdz',title:'企业名称',width:100},
{field:'yhTotal',title:'隐患总数',width:100},
{field:'zgwc',title:'整改完成数',width:100},
{field:'zgwwc',title:'未整改数',width:100},
{field:'zgl',title:'整改率',width:100}
				        ]],
				toolbar:toolbar 
			}));
		}
        

        
     
    </script>
</head>

<body >
<div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
<form name="myform" method="post">
<s:if test = "isshow == 1">
	<input type="hidden" id="qyid" value="${jshxZcyhsb.qyid}"/>
	</s:if>
	<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
		   <tr>
				<th width="15%">所在区域</th>
				<td width="35%">
					<cus:SelectOneTag property="troMan.areaId" style="width:50%;" defaultText='请选择' codeName="企业属地" value="${troMan.areaId}" />
				</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="troMan.companyName" id="companyName" style="width: 50%"  value="${troMan.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">上报时间</th>
				<td width="35%"><input name="starttime" id="starttime" value="${starttime}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endtime\')}'})" >
					-<input name="endtime" id="endtime" value="${endtime}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'starttime\')}'})" ></td>
				
			</tr>
			<%-- <tr>
				<td colspan="4" style="text-align:center;">
					<s:radio theme="simple" list="#{'2':'所属部门','3':'所属网格','4':'所属企业','1':'隐患类别'}" name="type" value="2" onclick="changeDiv(this.value)"/>
				</td>
			</tr> --%>
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
					<a href="###" class="easyui-linkbutton" onclick="search_tj()" iconCls="icon-search">查询</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;	
					
				</td>
			</tr>
		</table>
	</div>
	<br>
		<center>
			
				
				
				<div id="tj_div1" style="display:none;">
					<table cellspacing="0" cellpadding="0" width="85%" border="0">
						<tr>
							<td>
								<div id="pie5" style="margin-top:20px; width:500px; height:350px;float:left;"></div>
								
							</td>
						</tr>
					
					</table>
				</div>
			
			<div id="tj_div2" style="display:;">
				查询中，请稍等！
			</div>
			<div id="tj_div3" style="display:none;">
				查询中，请稍等！
			</div>
			
	
	
	
	
		</center>
		<script src="${ctx}/webResources/chosen-0.13/chosen.jquery.js" type="text/javascript"></script>
<script src="${ctx}/webResources/chosen-0.13/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>



				<div id="comList" style="display:none">
				<div id="pagination" >
				</div>
				</div>
				</div></div></div>
</form>
</body>
</html>

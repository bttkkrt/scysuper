<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>安全生产“三同时”及安全生产许可证持证情况</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        //清除查询表单中的搜索条件
        function clear_form(ff){
            var elements = ff.elements;
            for(i=0;i<elements.length;i++){
                var element = elements[i];
                if(element.type=="text"){
                    element.value = "";
                }else if(element.type=="radio" || element.type=="checkbox"){
                	element.checked = false;
                }else if(element.options!=null){
                	element.options[0].selected  = true;
                }
            }
        }
        
        function search_zywsxzxk(){
        	document.myform.action = "${ctx}/jsp/zywsxzxk/zywsxzxkLists.action";
        	document.myform.submit();
        }
        
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">上报时间</th>
				<td width="35%"><input name="createTimeStart" id="createTimeStart" value="<fmt:formatDate type='date' value='${createTimeStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createTimeEnd\')}'})" >
					-<input name="createTimeEnd" id="createTimeEnd" value="<fmt:formatDate type='date' value='${createTimeEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createTimeStart\')}'})" ></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				<a href="###" class="easyui-linkbutton" onclick="search_zywsxzxk()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				</td>
			</tr>
		</table>
	</div>
</form>
<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0">
		<table style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
			<tr style="border:1px solid #d5dbdc;height:24px">
				<th width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">项目</th>
				<th width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">项目内容</th>
				<th width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">计量单位</th>
				<th width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">数量</th>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" rowspan="4" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">危险化学品建设项目</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">本期内新建、改建、扩建项目总数</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data1}</td>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">其中：1）通过安全设施设立审查项目</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data2}</td>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">2）通过安全设施审查项目</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data3}</td>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">3）通过安全设计竣工验收项目</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data4}</td>
			</tr>
			
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" rowspan="3" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">危险化学品生产企业</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">本期内持有安全生产许可证企业</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data5}</td>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">其中：新发证企业</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data6}</td>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">延期换证企业</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data7}</td>
			</tr>
			
			
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" rowspan="4" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">职业病危害建设项目</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">本期内新建、改建、扩建项目总数 </td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data8}</td>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">其中：1）通过职业危害预评价审查项目</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data9}</td>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">2）通过职业病防护设施审查项目</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data10}</td>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">3）通过职业病防护设施竣工验收</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">个</td>
				<td width="25%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;background:#fff"">${aqscxzcfglb.data11}</td>
			</tr>
		</table>
	</div>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

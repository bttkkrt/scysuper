<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>主要负责人、安全管理员安全培训和职业卫生培训管理</title>
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
        function search_jshxZazPxb()
        {
        	document.myform.action = "${ctx}/jsp/zazPxb/jshxZazPxbLists.action";
        	document.myform.submit();
        }
        
        function exportData()
        {
        	document.myform.action = "${ctx}/jsp/zazPxb/jshxZazPxbExport.action";
        	document.myform.submit();
        }
    </script>
</head>

<body>

<form name="myform" method="post">
<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">培训时间</th>
				<td width="35%"><input name="queryPxsjStart" id="queryPxsjStart" value="${queryPxsjStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryPxsjEnd\')}'})" >
					-<input name="queryPxsjEnd" id="queryPxsjEnd" value="${queryPxsjEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryPxsjStart\')}'})" ></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				<a href="###" class="easyui-linkbutton" onclick="search_jshxZazPxb()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<a href="###" class="easyui-linkbutton" onclick="exportData();" iconCls="icon-remove">导出</a>
				</td>
			</tr>
		</table>
	</div>
</form>
<form>
	<style>
	th
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 2px 0;
		white-space: nowrap;
	}
	td
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 2px 0;
		background:#fff;
		white-space: nowrap;
	}
	tr
	{
		border:1px solid #d5dbdc;
		height:24px
	}
	</style>
<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0;overflow:scroll;width:100%;">
		<table style="border-collapse:collapse;border:1px solid #d5dbdc;">
			<tr>
				<th rowspan="2">序号</th>
				<th rowspan="2">镇、街道</th>
				<th colspan="8">法定代表人</th>
				<th colspan="8">主要负责人</th>
				<th colspan="8">安全管理员</th>
				<th rowspan="2">职业卫生管理员</th>
				<th rowspan="2">主要负责人、安全管理员、职业卫生管理员总计</th>
				<th colspan="5">特种作业人员</th>
				<th rowspan="2">特种设备作业人员</th>
				<th rowspan="2">特种作业人员和特种设备作业人员累计</th>
				<th rowspan="2">总计</th>
			</tr>
			<tr>
					<th>化工生产企业数</th>
					<th>化工生产企业培训数</th>
					<th>危险化学品经营企业数</th>
					<th>危险化学品经营企业培训数</th>
					<th>烟花爆竹经营企业数</th>
					<th>烟花爆竹经营企业培训数</th>
					<th>其它工贸企业</th>
					<th>合计</th>
					<th>化工生产企业数</th>
					<th>化工生产企业培训数</th>
					<th>危险化学品经营企业数</th>
					<th>危险化学品经营企业培训数</th>
					<th>烟花爆竹经营企业数</th>
					<th>烟花爆竹经营企业培训数</th>
					<th>其它工贸企业</th>
					<th>合计</th>
					<th>化工生产企业数</th>
					<th>化工生产企业培训数</th>
					<th>危险化学品经营企业数</th>
					<th>危险化学品经营企业培训数</th>
					<th>烟花爆竹经营企业数</th>
					<th>烟花爆竹经营企业培训数</th>
					<th>其它工贸企业</th>
					<th>合计</th>
					<th>化工生产企业</th>
					<th>危险化学品经营企业</th>
					<th>烟花爆竹经营企业</th>
					<th>其它工贸企业</th>
					<th>合计</th>
			</tr>
			
			<c:forEach var="item" items="${pxlist}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${item.szzname}</td>
					<td>${item.data1}</td>
					<td>${item.data2}</td>
					<td>${item.data3}</td>
					<td>${item.data4}</td>
					<td>${item.data5}</td>
					<td>${item.data6}</td>
					<td>${item.data7}</td>
					<td>${item.data8}</td>
					<td>${item.data9}</td>
					<td>${item.data10}</td>
					<td>${item.data11}</td>
					<td>${item.data12}</td>
					<td>${item.data13}</td>
					<td>${item.data14}</td>
					<td>${item.data15}</td>
					<td>${item.data16}</td>
					<td>${item.data17}</td>
					<td>${item.data18}</td>
					<td>${item.data19}</td>
					<td>${item.data20}</td>
					<td>${item.data21}</td>
					<td>${item.data22}</td>
					<td>${item.data23}</td>
					<td>${item.data24}</td>
					<td>${item.data25}</td>
					<td>${item.data26}</td>
					<td>${item.data27}</td>
					<td>${item.data28}</td>
					<td>${item.data29}</td>
					<td>${item.data30}</td>
					<td>${item.data31}</td>
					<td>${item.data32}</td>
					<td>${item.data33}</td>
					<td>${item.data34}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</form>
</body>
</html>

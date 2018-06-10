<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>企业自查统计</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function search_tj(){
          	document.myform.action ="${ctx}/jsp/zcyhsb/jshxZcyhsbWhpList.action";
          	document.myform.submit();
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
        
        function exportdata()
        {
        	document.myform.action = "${ctx}/jsp/zcyhsb/jshxZcyhsbDataWhpExport.action";
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
				<th width="15%">检查时间</th>
				<td width="35%"><input name="queryJhwcsjStart" id="queryJhwcsjStart" value="<fmt:formatDate type='date' value='${queryJhwcsjStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJhwcsjEnd\')}'})" >
					-<input name="queryJhwcsjEnd" id="queryJhwcsjEnd" value="<fmt:formatDate type='date' value='${queryJhwcsjEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJhwcsjStart\')}'})" ></td>
			</tr>
			
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
					<a href="###" class="easyui-linkbutton" onclick="search_tj()" iconCls="icon-search">查询</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;	
					<a href="###" class="easyui-linkbutton" onclick="exportdata()" iconCls="icon-add">导出</a>&nbsp;							
				</td>
			</tr>
		</table>
	</div>
	
	
</form>


<form>
	<title></title>
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
		white-space: nowrap;
		background:#fff
	}
	tr
	{
		border:1px solid #d5dbdc;
		height:24px
	}
	</style>
				<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0;overflow:scroll;width:100%;">
				<table style="border-collapse:collapse;border:1px solid #d5dbdc;">
					<tr >
						<th rowspan="2">序号</th>
						<th rowspan="2">镇、街道</th>
						<th rowspan="2">危险化学品企业总数</th>
						<th rowspan="2">上报企业数</th>
						<th rowspan="2">隐患排查数</th>
						<th rowspan="2">隐患整改数</th>
						<th rowspan="2">整改率</th>
						<th colspan="6">化工生产企业</th>
						<th colspan="6">危险化学品经营企业</th>
						<th colspan="6">危险化学品使用企业</th>
					</tr>
					
					<tr>
					    <th>化工生产企业数</th>
						<th>上报企业数</th>
						<th>隐患排查数</th>
						<th>隐患整改数</th>
						<th>未整改隐患数</th>
						<th>隐患整改率</th>
					
						<th>危险化学品经营企业数</th>
						<th>上报企业数</th>
						<th>隐患排查数</th>
						<th>隐患整改数</th>
						<th>未整改隐患数</th>
						<th>隐患整改率</th>
					
						<th>危险化学品使用企业数</th>
						<th>上报企业数</th>
						<th>隐患排查数</th>
						<th>隐患整改数</th>
						<th>未整改隐患数</th>
						<th>隐患整改率</th>
					</tr>
					
					<s:iterator value="whpList" id="whp" status="sta">
						<tr>
						    <td><s:property value='#sta.count'/></td>
							<td><s:property value='#whp.dwdz'/></td>
							<td><s:property value='#whp.qyTotal'/></td>
							<td><s:property value='#whp.sbqy'/></td>
							<td><s:property value='#whp.yhTotal'/></td>
							<td><s:property value='#whp.zgwc'/></td>
							<td><s:property value='#whp.zgl'/></td>
							<td><s:property value='#whp.hgscqyTotal'/></td>
							<td><s:property value='#whp.hgscsbqy'/></td>
							<td><s:property value='#whp.hgscyhTotal'/></td>
							<td><s:property value='#whp.hgsczgwc'/></td>
							<td><s:property value='#whp.hgsczgwwc'/></td>
							<td><s:property value='#whp.hgsczgl'/></td>
							<td><s:property value='#whp.whpjyqyTotal'/></td>
							<td><s:property value='#whp.whpjysbqy'/></td>
							<td><s:property value='#whp.whpjyyhTotal'/></td>
							<td><s:property value='#whp.whpjyzgwc'/></td>
							<td><s:property value='#whp.whpjyzgwwc'/></td>
							<td><s:property value='#whp.whpjyzgl'/></td>
							<td><s:property value='#whp.whpsyqyTotal'/></td>
							<td><s:property value='#whp.whpsysbqy'/></td>
							<td><s:property value='#whp.whpsyyhTotal'/></td>
							<td><s:property value='#whp.whpsyzgwc'/></td>
							<td><s:property value='#whp.whpsyzgwwc'/></td>
							<td><s:property value='#whp.whpsyzgl'/></td>
							
						</tr>
					</s:iterator>
					<tr>
						<th colspan="2">合计</th>
						<th>${whpBean.qyTotal}</th>
						<th>${whpBean.sbqy}</th>
						<th>${whpBean.yhTotal}</th>
						<th>${whpBean.zgwc}</th>
						<th>${whpBean.zgl}</th>
						<th>${whpBean.hgscqyTotal}</th>
						<th>${whpBean.hgscsbqy}</th>
						<th>${whpBean.hgscyhTotal}</th>
						<th>${whpBean.hgsczgwc}</th>
						<th>${whpBean.hgsczgwwc}</th>
						<th>${whpBean.hgsczgl}</th>
						<th>${whpBean.whpjyqyTotal}</th>
						<th>${whpBean.whpjysbqy}</th>
						<th>${whpBean.whpjyyhTotal}</th>
						<th>${whpBean.whpjyzgwc}</th>
						<th>${whpBean.whpjyzgwwc}</th>
						<th>${whpBean.whpjyzgl}</th>
						<th>${whpBean.whpsyqyTotal}</th>
						<th>${whpBean.whpsysbqy}</th>
						<th>${whpBean.whpsyyhTotal}</th>
						<th>${whpBean.whpsyzgwc}</th>
						<th>${whpBean.whpsyzgwwc}</th>
		                <th>${whpBean.whpsyzgl}</th>		
					</tr>
				</table>
			</div>
</form>
</body>
</html>

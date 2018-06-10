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
          	document.myform.action="${ctx}/jsp/zcyhsb/jshxZcyhsbZywhList.action";
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
        	document.myform.action = "${ctx}/jsp/zcyhsb/jshxZcyhsbDataZywhExport.action";
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
		padding:0 2px 0 0;
	}
	td
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 0 0 2px;
		background:#fff
	}
	tr
	{
		border:1px solid #d5dbdc;
		height:24px
	}
	</style>
				<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0">
				<table style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
					<tr>
						<th>序号</th>
						<th>镇、街道</th>
						<th>职业危害企业</th>
						<th>上报企业数</th>
						<th>未上报企业数</th>
						<th>隐患排查数</th>
						<th>整改隐患数</th>
						<th>未整改隐患数</th>
						<th>整改率</th>
					</tr>
					<s:iterator value="zywhList" id="zywh" status="sta">
						<tr>
							<td><s:property value='#sta.count'/></td>
							<td><s:property value='#zywh.dwdz'/></td>
							<td><s:property value='#zywh.qyTotal'/></td>
							<td><s:property value='#zywh.sbqy'/></td>
							<td><s:property value='#zywh.wsbqy'/></td>
							<td><s:property value='#zywh.yhTotal'/></td>
							<td><s:property value='#zywh.zgwc'/></td>
							<td><s:property value='#zywh.zgwwc'/></td>
							<td><s:property value='#zywh.zgl'/></td>
						</tr>
					</s:iterator>
					<tr>
						<th colspan="2">合计</th>
						<th>${zywhBean.qyTotal}</th>
						<th>${zywhBean.sbqy}</th>
						<th>${zywhBean.wsbqy}</th>
						<th>${zywhBean.yhTotal}</th>
						<th>${zywhBean.zgwc}</th>
						<th>${zywhBean.zgwwc}</th>
						<th>${zywhBean.zgl}</th>
					</tr>
				</table>
			</div>
</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

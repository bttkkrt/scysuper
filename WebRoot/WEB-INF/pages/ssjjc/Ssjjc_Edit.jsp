<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	function selectAll(obj)
	{
		var ryids = document.getElementsByName('ssjjc.qylx');
		if(obj.checked)
		{
			for(var i = 0; i < ryids.length; i++)
			{
				ryids[i].checked = true;
			}
		}
		else
		{	
			for(var i = 0; i < ryids.length; i++)
			{
				ryids[i].checked = false;
			}
		}
	}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="ssjjcSave.action">
		<s:token />
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">检查时间</th>
					<td width="35%"><input name="ssjjc.jcsj" style="width:60%;" value="<fmt:formatDate pattern="yyyy-MM-dd" type='both' value='${ssjjc.jcsj}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">检查企业比例</th>
					<td width="35%"><input name="ssjjc.jcbl" style="width:60%;" value="${ssjjc.jcbl}" type="text" maxlength="127">%</td>
				</tr>
				<tr>
					<th width="15%">是否领导带队</th>
					<td width="35%"><s:select  cssStyle="width:60%" name="ssjjc.sflddd" list="#{'1':'是','0':'否'}" theme="simple" /></td>
					<th width="15%">企业数/组</th>
					<td width="35%"><input name="ssjjc.zrs" style="width:60%;" value="${ssjjc.zrs}" type="text" maxlength="127"/></td>
				</tr>
				<tr>
					<th width="15%">企业类型</th>
					<td width="85%" colspan="3">
					<input type="checkbox" onclick="selectAll(this)">全选&nbsp;
					<cus:hxcheckbox property="ssjjc.qylx" codeName="双随机企业分类" value="${ssjjc.qylx}" maxlength="127"/></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >开始<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_ssjjc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

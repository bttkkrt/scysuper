<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	function selectAll(obj)
	{
		var ryids = document.getElementsByName("jcx"+obj);
		var jcxlx = "";
		for(var i = 0; i < ryids.length; i++)
		{
			if(ryids[i].checked)
			{
				jcxlx += ryids[i].value + ";";
			}
		}
		document.getElementById('jcxlx'+obj).value = jcxlx;
	}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="ssjjcTaskSave.action">
		<s:token />
			<input type="hidden" name="ssjjc.id" value="${ssjjc.id}">
			<table width="100%" border="0">	
				<tr>
					<th width="10%" style="text-align:center">检查时间</th>
					<th width="10%" style="text-align:center">检查人员</th>
					<th width="40%" style="text-align:center">检查企业</th>
					<th width="40%" style="text-align:center">检查项类型</th>
				</tr>
				<c:forEach var="bean" items="${sjList}">
				<tr>
					<td width="10%"><input type="hidden" name="ssjjcBean.id" value="${bean.id}">${bean.jcsj}</td>
					<td width="10%">${bean.jcry}</td>
					<td width="40%">${bean.jcqy}</td>
					<td width="40%"><input type="hidden" id="jcxlx${bean.id}" name="ssjjcBean.jcxlx" value="${ssjjcBean.jcxlx}"><cus:hxcheckbox  property="jcx${bean.id}"   codeSql="select t.row_id,t.PATROL_TYPE_NAME from PAT_TYP_MAN t where t.delflag = 0" value="${ssjjcBean.jcxlx}"  datatype="*"  nullmsg='检查项类型不能为空！' sucmsg='检查项类型填写正确！' onclick="selectAll('${bean.id}')"/><font style='color:red'>*</font></td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >生成任务<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_ssjjc');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

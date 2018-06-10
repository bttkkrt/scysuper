<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	function save()
	{
		document.myform1.action = "${ctx}/jsp/companyScore/companyScoreInitEdit.action?flag=add";
		document.myform1.submit();
	}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: ">
	<form name="myform1" method="post" enctype="multipart/form-data">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">开始时间</th>
					<td width="35%">
						<input id="startTime" name="companyScore.startTime" value="<fmt:formatDate type='date' value='${companyScore.startTime}'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')}'})" datatype="*1-127" errormsg='开始时间必须是1到127位字符！' nullmsg='开始时间不能为空！' sucmsg='开始时间填写正确！'  maxlength="127">
						<font style='color:red'>*</font>
					</td>
					<th width="15%">结束时间</th>
					<td width="35%">
						<input id="endTime" name="companyScore.endTime" value="<fmt:formatDate type='date' value='${companyScore.endTime}'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})" datatype="*1-127" errormsg='结束时间必须是1到127位字符！' nullmsg='结束时间不能为空！' sucmsg='结束时间填写正确！'  maxlength="127">
						<font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" onclick="save()" >下一步<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_companyScore');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

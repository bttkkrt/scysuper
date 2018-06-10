<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
		
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="wzInfoManageSaveMove.action">
		<s:token />
		<input type="hidden" name="ids" value="${ids}">
		<input type="hidden" name="type" value="${type}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">移动至</th>
					<td width="35%">
						<select name="infoType">
							<option value="1">安委会工作</option>
							<option value="2">政务互动</option>
							<option value="3">专题报道</option>
							<option value="4">媒体关注</option>
							<option value="5">职业卫生</option>
							<option value="6">组织结构</option>
							<option value="7">信息公开</option>
							<option value="9">办事流程</option>
							<option value="10">通知公告</option>
							<option value="11">工作动态</option>
							<option value="12">安全生产</option>
							<option value="13">综合执法</option>
							<option value="14">城市管理</option>
							<option value="15">机关党建</option>
						</select>
					</td>
					<th width="15%">是否保留</th>
					<td width="35%">
						<input type="radio" value="0" checked  name="keep">是 <input type="radio" value="1" name="keep">否 
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_wzInfoManage');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

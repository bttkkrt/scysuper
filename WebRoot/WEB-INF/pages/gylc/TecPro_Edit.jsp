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
	<form name="myform1" method="post" enctype="multipart/form-data" action="tecProSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="tecPro.id" value="${tecPro.id}">
		<input type="hidden" name="tecPro.createTime" value="<fmt:formatDate type="both" value="${tecPro.createTime}" />">
		<input type="hidden" name="tecPro.updateTime" value="${tecPro.updateTime}">
		<input type="hidden" name="tecPro.createUserID" value="${tecPro.createUserID}">
		<input type="hidden" name="tecPro.updateUserID" value="${tecPro.updateUserID}">
		<input type="hidden" name="tecPro.deptId" value="${tecPro.deptId}">
		<input type="hidden" name="tecPro.delFlag" value="${tecPro.delFlag}">
		
			<table width="100%" border="0">
				<tr>
				<th width="15%">生产工艺</th>
					<td colspan="3"><cus:hxcheckbox property="tecPro.productionProcess" codeName="生产工艺" value="${tecPro.productionProcess}" datatype="*"  nullmsg='生产工艺不能为空！'/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_tecPro');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

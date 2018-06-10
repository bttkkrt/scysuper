<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script type="text/javascript">
		$(function(){
			//$("#fbqk").load("{ctx}/jsp/zybwhysfbqk/occDisList.action");
		});
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="occHazBasSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="occHazBas.id" value="${occHazBas.id}">
		<input type="hidden" name="occHazBas.createTime" value="<fmt:formatDate type="both" value="${occHazBas.createTime}" />">
		<input type="hidden" name="occHazBas.updateTime" value="${occHazBas.updateTime}">
		<input type="hidden" name="occHazBas.createUserID" value="${occHazBas.createUserID}">
		<input type="hidden" name="occHazBas.updateUserID" value="${occHazBas.updateUserID}">
		<input type="hidden" name="occHazBas.deptId" value="${occHazBas.deptId}">
		<input type="hidden" name="occHazBas.delFlag" value="${occHazBas.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">粉尘类</th>
					<td width="35%"><cus:SelectOneTag style="width:60%" property="occHazBas.ifDust" defaultText='请选择' codeName="有或无" value="${occHazBas.ifDust}" /></td>
					<th width="15%">粉尘类接触人数</th>
					<td width="35%"><input name="occHazBas.dustContactNumber"  style="width:60%"  value="${occHazBas.dustContactNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">化学物质类</th>
					<td width="35%"><cus:SelectOneTag style="width:60%" property="occHazBas.ifChemical" defaultText='请选择' codeName="有或无" value="${occHazBas.ifChemical}" /></td>
					<th width="15%">化学物质类接触人数</th>
					<td width="35%"><input name="occHazBas.chemicalContactNumber"  style="width:60%" value="${occHazBas.chemicalContactNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">物理因素类</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="occHazBas.ifPhysical" defaultText='请选择' codeName="有或无" value="${occHazBas.ifPhysical}" /></td>
					<th width="15%">物理因素类接触人数</th>
					<td width="35%"><input name="occHazBas.physicalContactNumber" style="width:60%"  value="${occHazBas.physicalContactNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">放射性物质类</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="occHazBas.ifRadioactivity" defaultText='请选择' codeName="有或无" value="${occHazBas.ifRadioactivity}" /></td>
					<th width="15%">放射性物质类接触人数</th>
					<td width="35%"><input name="occHazBas.radioactivityContactNumber" style="width:60%"  value="${occHazBas.radioactivityContactNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">其他</th>
					<td width="35%"><cus:SelectOneTag style="width:60%" property="occHazBas.ifOther" defaultText='请选择' codeName="有或无" value="${occHazBas.ifOther}" /></td>
					<th width="15%">其他接触人数</th>
					<td width="35%"><input name="occHazBas.otherContactNumber" style="width:60%"  value="${occHazBas.otherContactNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">接触职业病危害总人数</th>
					<td width="35%"><input name="occHazBas.totalNumber" style="width:60%"  value="${occHazBas.totalNumber}" type="text" maxlength="127"></td>
					<th width="15%">合计</th>
					<td width="35%"><input name="occHazBas.total" style="width:60%"  value="${occHazBas.total}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4">
						<div id="fbqk">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_occHazBas');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

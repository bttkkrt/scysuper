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
	<form name="myform1" method="post" enctype="multipart/form-data" action="penaltyNoticeSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="penaltyNotice.id" value="${penaltyNotice.id}">
		<input type="hidden" name="penaltyNotice.createTime" value="<fmt:formatDate type="both" value="${penaltyNotice.createTime}" />">
		<input type="hidden" name="penaltyNotice.updateTime" value="${penaltyNotice.updateTime}">
		<input type="hidden" name="penaltyNotice.createUserID" value="${penaltyNotice.createUserID}">
		<input type="hidden" name="penaltyNotice.updateUserID" value="${penaltyNotice.updateUserID}">
		<input type="hidden" name="penaltyNotice.deptId" value="${penaltyNotice.deptId}">
		<input type="hidden" name="penaltyNotice.delFlag" value="${penaltyNotice.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">违法行为</th>
					<td width="85%" colspan="3">
						<textarea name="penaltyNotice.wfxw" style="width:100%;height:120px"  errormsg='违法行为必须是1到2000位字符！' nullmsg='违法行为不能为空！' sucmsg='违法行为填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${penaltyNotice.wfxw}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">违反规定</th>
					<td width="85%" colspan="3">
						<input id="proName1" name="penaltyNotice.proName" value="${penaltyNotice.proName}" type="text"  errormsg='违反规定必须是1到127位字符！' nullmsg='违反规定不能为空！' sucmsg='违反规定填写正确！'  maxlength="127">
						<input type="button" onclick="queryGd('1');" value="选择"/>
						<input type="hidden" id="proId1" name="penaltyNotice.provision" value="${penaltyNotice.provision}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">执法依据</th>
					<td width="85%" colspan="3">
						<input id="lawName3" name="penaltyNotice.lawName" value="${penaltyNotice.lawName}" type="text"  errormsg='执法依据必须是1到127位字符！' nullmsg='执法依据不能为空！' sucmsg='执法依据填写正确！'  maxlength="127">
						<input type="button" onclick="queryYj('3');" value="选择"/>
						<input type="hidden" id="lawBasic3" name="penaltyNotice.lawBasic" value="${penaltyNotice.lawBasic}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">行政处罚</th>
					<td width="85%" colspan="3">
						<textarea name="penaltyNotice.adminPenality" style="width:100%;height:120px"  errormsg='行政处罚必须是1到2000位字符！' nullmsg='行政处罚不能为空！' sucmsg='行政处罚填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${penaltyNotice.adminPenality}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">联系人</th>
					<td width="35%"><input name="penaltyNotice.contact" value="${penaltyNotice.contact}" type="text"  errormsg='联系人必须是1到127位字符！' nullmsg='联系人不能为空！' sucmsg='联系人填写正确！'  maxlength="127"></td>
					<th width="15%">联系电话</th>
					<td width="35%"><input name="penaltyNotice.tele" value="${penaltyNotice.tele}" type="text"  errormsg='联系电话必须是1到127位字符！' nullmsg='联系电话不能为空！' sucmsg='联系电话填写正确！'  maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_penaltyNotice');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

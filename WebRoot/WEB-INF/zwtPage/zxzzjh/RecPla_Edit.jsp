<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="recPlaSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="recPla.id" value="${recPla.id}">
		<input type="hidden" name="recPla.createTime" value="<fmt:formatDate type="both" value="${recPla.createTime}" />">
		<input type="hidden" name="recPla.updateTime" value="${recPla.updateTime}">
		<input type="hidden" name="recPla.createUserID" value="${recPla.createUserID}">
		<input type="hidden" name="recPla.updateUserID" value="${recPla.updateUserID}">
		<input type="hidden" name="recPla.deptId" value="${recPla.deptId}">
		<input type="hidden" name="recPla.delFlag" value="${recPla.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">计划名称</th>
					<td width="85%" colspan="3"><input name="recPla.planName" type="text" style="width: 80%" value="${recPla.planName }"  datatype="*1-127" errormsg='计划名称必须是1到127位字符！' nullmsg='计划名称不能为空！' sucmsg='计划名称填写正确！'  maxlength="127"/> <font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">整治开始时间</th>
					<td width="35%">
						<input name="recPla.workTimeStart" style="width: 60%" id="queryWorkTimeStart" value="<fmt:formatDate  pattern="yyyy-MM-dd" type='both' value='${recPla.workTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryWorkTimeEnd\')}'})">
					</td>
					<th width="15%">整治结束时间</th>
					<td width="35%">
						<input name="recPla.workTimeEnd" style="width: 60%" id="queryWorkTimeEnd" value="<fmt:formatDate pattern="yyyy-MM-dd"  type='both' value='${recPla.workTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryWorkTimeStart\')}'})">
					</td>
				</tr>
				<tr>
					<th width="15%">工作目标</th>
					<td width="85%" colspan="3" ><textarea name="recPla.workGoal" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${recPla.workGoal}</textarea></td>
					
				</tr>
				<tr>
					<th width="15%">工作内容</th>
					<td width="85%" colspan="3" ><textarea name="recPla.workContent" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${recPla.workContent}</textarea></td>
					
				</tr>
				<tr>
					<th width="15%">具体措施</th>
					<td width="85%" colspan="3" ><textarea name="recPla.workMeasure"  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${recPla.workMeasure}</textarea></td>
					
				</tr>
				<tr>
					<th width="15%">实施步骤</th>
					<td width="85%" colspan="3" ><textarea name="recPla.workStep"  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${recPla.workStep}</textarea></td>
					
				</tr>
				<tr>
					<th width="15%">工作要求</th>
					<td width="85%" colspan="3" ><textarea name="recPla.workClaim"  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" style="width:96%;height:60px">${recPla.workClaim}</textarea></td>
					
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

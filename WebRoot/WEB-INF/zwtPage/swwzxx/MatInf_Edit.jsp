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
	<form name="myform1" method="post" enctype="multipart/form-data" action="matInfSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="matInf.id" value="${matInf.id}">
		<input type="hidden" name="matInf.createTime" value="<fmt:formatDate type="both" value="${matInf.createTime}" />">
		<input type="hidden" name="matInf.updateTime" value="${matInf.updateTime}">
		<input type="hidden" name="matInf.createUserID" value="${matInf.createUserID}">
		<input type="hidden" name="matInf.updateUserID" value="${matInf.updateUserID}">
		<input type="hidden" name="matInf.deptId" value="${matInf.deptId}">
		<input type="hidden" name="matInf.delFlag" value="${matInf.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">危险源单元名称</th>
					<td width="35%"><input name="matInf.dangerouSourceName" style="width:60%;" errormsg='危险源单元名称必须是1到127位字符！' nullmsg='危险源单元名称不能为空！' sucmsg='危险源单元名称填写正确！'  datatype="*1-127"  value="${matInf.dangerouSourceName}" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">危险化学品名称</th>
					<td width="35%"><input name="matInf.dangerousChemicalName" style="width:60%;" errormsg='危险化学品名称必须是1到127位字符！' nullmsg='危险化学品名称不能为空！' sucmsg='危险化学品名称填写正确！' datatype="*1-127"  value="${matInf.dangerousChemicalName}" type="text" maxlength="127"><font style='color:red'>*</font></td>
					
				</tr>
				<tr>
					<th width="15%">是否剧毒化学品</th>
					<td width="35%"><cus:SelectOneTag style="width:60%;" datatype="*" property="matInf.ifToxicChemicals" defaultText='请选择' codeName="是或否" value="${matInf.ifToxicChemicals}" /><font style='color:red'>*</font></td>
					<th width="15%">危规号</th>
					<td width="35%"><input name="matInf.riskGauge" style="width:60%;" value="${matInf.riskGauge}" errormsg='危规号必须是1到127位字符！' nullmsg='危规号不能为空！' sucmsg='危规号填写正确！' datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">所处装置</th>
					<td width="35%"><input name="matInf.device" style="width:60%;" value="${matInf.device}" errormsg='所处装置必须是1到127位字符！' nullmsg='所处装置不能为空！' sucmsg='所处装置填写正确！' datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">存在量</th>
					<td width="35%"><input name="matInf.existenceQuantity" style="width:60%;" value="${matInf.existenceQuantity}" errormsg='存在量必须是1到127位字符！' nullmsg='存在量不能为空！' sucmsg='存在量填写正确！' datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">临界值</th>
					<td width="35%"><input name="matInf.criticalValue" style="width:60%;" value="${matInf.criticalValue}" errormsg='临界值必须是1到127位字符！' nullmsg='临界值不能为空！' sucmsg='临界值填写正确！' datatype="*1-127"  type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">校正系数</th>
					<td width="35%"><input name="matInf.correctionFactor" style="width:60%;" value="${matInf.correctionFactor}" errormsg='校正系数必须是1到127位字符！' nullmsg='校正系数不能为空！' sucmsg='校正系数填写正确！' datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">涉及危险工艺</th>
					<td width="35%"><input name="matInf.hazardousProcess" style="width:60%;" value="${matInf.hazardousProcess}" errormsg='涉及危险工艺必须是1到127位字符！' nullmsg='涉及危险工艺不能为空！' sucmsg='涉及危险工艺填写正确！' datatype="*1-127"  type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">操作人员情况</th>
					<td width="35%"><input name="matInf.operatingPersonnel" style="width:60%;" value="${matInf.operatingPersonnel}" errormsg='操作人员情况必须是1到127位字符！' nullmsg='操作人员情况不能为空！' sucmsg='操作人员情况填写正确！' datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">运输注意事项</th>
					<td width="85%" colspan="3"><textarea name="matInf.transportationNote"   style="width:80%;height:60px" errormsg='运输注意事项必须是1到2000位字符！' nullmsg='运输注意事项不能为空！' sucmsg='运输注意事项填写正确！' datatype="*1-2000" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${matInf.transportationNote}</textarea><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3"><textarea name="matInf.remark" maxlength="2000" style="width:80%;height:60px"  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${matInf.remark}</textarea></td>
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

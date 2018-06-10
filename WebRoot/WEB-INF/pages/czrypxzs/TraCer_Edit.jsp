<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
	function queryQy()
		{
			var szzid = document.getElementById('areaId').value;
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=idmcqy&entBaseInfo.enterprisePossession="+szzid, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="traCerSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="traCer.id" value="${traCer.id}">
		<input type="hidden" name="traCer.createTime" value="<fmt:formatDate type="both" value="${traCer.createTime}" />">
		<input type="hidden" name="traCer.updateTime" value="${traCer.updateTime}">
		<input type="hidden" name="traCer.createUserID" value="${traCer.createUserID}">
		<input type="hidden" name="traCer.updateUserID" value="${traCer.updateUserID}">
		<input type="hidden" name="traCer.deptId" value="${traCer.deptId}">
		<input type="hidden" name="traCer.delFlag" value="${traCer.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="traCer.areaId" style="width: 60%" defaultText='请选择' codeName="企业属地" value="${traCer.areaId}" onchange="clearCompany()"  datatype="*1-127" errormsg='所在区域必须是1到127位字符！' nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'  maxlength="127"/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="traCer.companyName" style="width: 60%" value="${traCer.companyName}" type="text" readonly="readonly" onclick="queryQy()"/>
						<input type="hidden" id="companyId" name="traCer.companyId" value="${traCer.companyId}"  datatype="*1-127" errormsg='企业名称必须是1到127位字符！' nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'  maxlength="127"/><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="traCer.certificateName" style="width: 60%" value="${traCer.certificateName}" type="text"  datatype="*1-127" errormsg='姓名必须是1到127位字符！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">性别</th>
					<td width="35%"><cus:SelectOneTag property="traCer.sex" style="width: 60%" defaultText='请选择' codeName="性别" value="${traCer.sex}" /></td>
				</tr>
				<tr>
					<th width="15%">职位</th>
					<td width="35%"><input name="traCer.position" style="width: 60%" value="${traCer.position}" type="text" maxlength="127"></td>
					<th width="15%">文化程度</th>
					<td width="35%"><cus:SelectOneTag property="traCer.education" style="width: 60%" defaultText='请选择' codeName="学历" value="${traCer.education}" /></td>
				</tr>
				<tr>
					<th width="15%">电话</th>
					<td width="35%"><input name="traCer.tele" value="${traCer.tele}" style="width: 60%" type="text" maxlength="127"></td>
					<th width="15%">身份证</th>
					<td width="35%"><input name="traCer.idCard" value="${traCer.idCard}" style="width: 60%" type="text"  datatype="idcard" errormsg='身份证号格式错误！' nullmsg='身份证不能为空！' sucmsg='身份证填写正确！'  maxlength="18"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">地址</th>
					<td width="35%"><input name="traCer.address" value="${traCer.address}" style="width: 60%" type="text" maxlength="127"></td>
					<th width="15%">职称</th>
					<td width="35%"><input name="traCer.jobTitle" value="${traCer.jobTitle}" style="width: 60%" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">首次领证时间</th>
					<td width="35%"><input name="traCer.firstLicense" id="firstLicense" style="width: 60%" value="<fmt:formatDate type='date' value='${traCer.firstLicense}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'certificaateValid\')}'})"></td>
					<th width="15%">合格证号</th>
					<td width="35%"><input name="traCer.certificateNo" style="width: 60%" value="${traCer.certificateNo}" type="text"  datatype="*1-127" errormsg='合格证号必须是1到127位字符！' nullmsg='合格证号不能为空！' sucmsg='合格证号填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">证书有效期</th>
					<td width="35%"><input name="traCer.certificaateValid" id="certificaateValid" style="width: 60%" value="${traCer.certificaateValid}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'firstLicense\')}'})"  ></td>
					<th width="15%">证书类别</th>
					<td width="35%"><cus:SelectOneTag property="traCer.certificateType" style="width: 60%" defaultText='请选择' codeName="持证类别" value="${traCer.certificateType}" /></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_traCer');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

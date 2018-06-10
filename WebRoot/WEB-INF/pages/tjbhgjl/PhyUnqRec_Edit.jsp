<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
		<script>
		function queryQy()
		{
			var szzid = document.getElementById('areaId').value;
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=idmcqy&entBaseInfo.enterprisePossession="+szzid, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		  function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
   
	</script>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="phyUnqRecSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="phyUnqRec.id" value="${phyUnqRec.id}">
		<input type="hidden" name="phyUnqRec.createTime" value="<fmt:formatDate type="both" value="${phyUnqRec.createTime}" />">
		<input type="hidden" name="phyUnqRec.updateTime" value="${phyUnqRec.updateTime}">
		<input type="hidden" name="phyUnqRec.createUserID" value="${phyUnqRec.createUserID}">
		<input type="hidden" name="phyUnqRec.updateUserID" value="${phyUnqRec.updateUserID}">
		<input type="hidden" name="phyUnqRec.deptId" value="${phyUnqRec.deptId}">
		<input type="hidden" name="phyUnqRec.delFlag" value="${phyUnqRec.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="phyUnqRec.jshxName" style="width:60%" value="${phyUnqRec.jshxName}" type="text" maxlength="127" datatype="*1-127" errormsg='姓名错误！' nullmsg='姓名不能为空！' sucmsg='姓名填写正确！'><font style='color:red'>*</font></td>
					<th width="15%">身份证</th>
					<td width="35%"><input name="phyUnqRec.identification" style="width:60%" value="${phyUnqRec.identification}" type="text"  maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">体检机构</th>
					<td width="35%"><input name="phyUnqRec.medicalInstitution" style="width:60%" value="${phyUnqRec.medicalInstitution}" type="text" maxlength="127"></td>
					<th width="15%">体检类型</th>
					<td width="35%"><cus:SelectOneTag property="phyUnqRec.physicalExaminatioType" style="width:60%" defaultText='请选择' codeName="体检类型" value="${phyUnqRec.physicalExaminatioType}" datatype="*"  nullmsg='类型不能为空！'/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">体检日期</th>
					<td width="35%"><input name="phyUnqRec.medicalExaminationDate" style="width:60%" value="<fmt:formatDate type='date' value='${phyUnqRec.medicalExaminationDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">体检结果</th>
					<td width="35%"><input name="phyUnqRec.physicalExaminatioResults" style="width:60%" value="${phyUnqRec.physicalExaminatioResults}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">职业禁忌岗位</th>
					<td width="35%"><input name="phyUnqRec.occupationalTabooPost" style="width:60%" value="${phyUnqRec.occupationalTabooPost}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_phyUnqRec');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

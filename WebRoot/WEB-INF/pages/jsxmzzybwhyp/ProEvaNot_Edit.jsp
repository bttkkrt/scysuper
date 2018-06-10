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
			<div  style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="proEvaNotSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="proEvaNot.id" value="${proEvaNot.id}">
		<input type="hidden" name="proEvaNot.createTime" value="<fmt:formatDate type="both" value="${proEvaNot.createTime}" />">
		<input type="hidden" name="proEvaNot.updateTime" value="${proEvaNot.updateTime}">
		<input type="hidden" name="proEvaNot.createUserID" value="${proEvaNot.createUserID}">
		<input type="hidden" name="proEvaNot.updateUserID" value="${proEvaNot.updateUserID}">
		<input type="hidden" name="proEvaNot.deptId" value="${proEvaNot.deptId}">
		<input type="hidden" name="proEvaNot.delFlag" value="${proEvaNot.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 22.0pt">
						建设项目职业病危害预评价报告
					</td>	
				</tr>
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 22.0pt">
						备案通知书
					</td>	
				</tr>
				<tr>
					<td height="20px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋_GB2312;font-size: 14.0pt">
						（<input name="proEvaNot.proNo" value="${proEvaNot.proNo}" type="text" style="width:25px" datatype="*1-127" errormsg='必须是1到127位字符！' nullmsg='不能为空！' sucmsg='填写正确！'  maxlength="127"><font style='color:red'>*</font>）安职预备字〔<input name="proEvaNot.preparatoryWord" value="${proEvaNot.preparatoryWord}" type="text" style="width:25px" datatype="*1-127" errormsg='必须是1到127位字符！' nullmsg='不能为空！' sucmsg='填写正确！'  maxlength="127"><font style='color:red'>*</font>〕第  <input name="proEvaNot.arranging" value="${proEvaNot.arranging}" type="text" style="width:25px" datatype="*1-127" errormsg='必须是1到127位字符！' nullmsg='不能为空！' sucmsg='填写正确！'  maxlength="127"><font style='color:red'>*</font> 号&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<hr style="height:5px;border:none;border-top:5px double;"/>
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					<cus:SelectOneTag property="proEvaNot.areaId" defaultText='请选择' codeName="企业属地" value="${proEvaNot.areaId}" onchange="clearCompany()" datatype="*1-127" errormsg='企业属地必须是1到127位字符！' nullmsg='企业属地不能为空！' sucmsg='企业属地填写正确！'  maxlength="127"/><font style='color:red'>*</font>
						<input id="companyName" name="proEvaNot.companyName" value="${proEvaNot.companyName}" type="text" readonly="readonly" onclick="queryQy()"/>
						<input type="hidden" id="companyId" name="proEvaNot.companyId" value="${proEvaNot.companyId}" datatype="*1-127" errormsg='企业名称必须是1到127位字符！' nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'  maxlength="127"/><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<td height="20px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					&nbsp;&nbsp;&nbsp;你单位<input name="proEvaNot.proDate"  value="${proEvaNot.proDate}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  >
					提请备案的<input name="proEvaNot.projectName" value="${proEvaNot.projectName}" type="text" datatype="*1-127" errormsg='名称必须是1到127位字符！' nullmsg='名称不能为空！' sucmsg='名称填写正确！'  maxlength="127"><font style='color:red'>*</font>职业病危害预评价报告、自行组织评审意见和情况报告以及其他相关文件、资料收悉。经核实，提交的文件、资料齐全，符合有关规定，现予以备案。
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					经 办 人：<input name="proEvaNot.conPeople" value="${proEvaNot.conPeople}" type="text" datatype="*1-127" errormsg='经 办 人必须是1到127位字符！' nullmsg='经 办 人不能为空！' sucmsg='经 办 人填写正确！'  maxlength="127"><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					联系电话：<input name="proEvaNot.conPhone" value="${proEvaNot.conPhone}" type="text" maxlength="127">
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋_GB2312;font-size: 16.0pt">
						备案部门名称（盖章）
					</td>
				</tr>
				<tr>
					<td>
						<hr style="height:5px;border:none;border-top:3px double;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 10.5pt">
						&nbsp;&nbsp;本文书一式3份。一份送申请单位，一份送所在地安监部门，一份备案部门存档。
					</td>
				</tr>
				<tr>
					<td  height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_proEvaNot');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

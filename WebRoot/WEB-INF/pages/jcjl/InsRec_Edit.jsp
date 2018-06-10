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
	<form name="myform1" method="post" enctype="multipart/form-data" action="insRecSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="insRec.id" value="${insRec.id}">
		<input type="hidden" name="insRec.createTime" value="<fmt:formatDate type="both" value="${insRec.createTime}" />">
		<input type="hidden" name="insRec.updateTime" value="${insRec.updateTime}">
		<input type="hidden" name="insRec.createUserID" value="${insRec.createUserID}">
		<input type="hidden" name="insRec.updateUserID" value="${insRec.updateUserID}">
		<input type="hidden" name="insRec.deptId" value="${insRec.deptId}">
		<input type="hidden" name="insRec.delFlag" value="${insRec.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">案件来源</th>
					<td width="35%"><cus:SelectOneTag property="insRec.caseSource" defaultText='请选择' codeName="案件来源" value="${insRec.caseSource}" datatype="*1-127" errormsg='案件来源必须是1到127位字符！' nullmsg='案件来源不能为空！' sucmsg='案件来源填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font></td>
					<th width="15%">文书类型</th>
					<td width="35%"><cus:SelectOneTag property="insRec.instrumentType" defaultText='请选择' codeName="文书类型" value="${insRec.instrumentType}" datatype="*1-127" errormsg='文书类型必须是1到127位字符！' nullmsg='文书类型不能为空！' sucmsg='文书类型填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">检查记录</th>
					<td width="85%" colspan="3">
						<textarea name="insRec.inspectionRecord" style="width:96%;height:120px" datatype="*1-2000" errormsg='检查记录必须是1到2000位字符！' nullmsg='检查记录不能为空！' sucmsg='检查记录填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${insRec.inspectionRecord}</textarea><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_insRec');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				var qymc = document.getElementById("qymc").value;
        		var qyid = document.getElementById("qyid").value;
        		var qymc1 = "${zjjtzsb.qymc}";
        		var qyid1 = "${zjjtzsb.qyid}";
        		if(qymc1 != null && qymc1 != "" && qyid1 != null && qyid1 != "" && qymc != null && qymc != "" && qyid != null && qyid != "")
        		{
        			if(qymc != qymc1 && qyid == qyid1)
        			{
        				document.getElementById("qyid").value = "";
        			}
        		}
				document.myform1.action="zjjtzsbSave.action";
				document.myform1.submit();
			}
		}
		
		function queryQy()
		{
			var szzid = document.getElementById('szzid').value;
			var OpenWindow = window.showModalDialog("${ctx}/jsp/company/queryCompanyList.action?company.dwdz1="+szzid, '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
			if(typeof(OpenWindow) != 'undefined')
			{
				var sonValue = OpenWindow.split(";");
				document.getElementById('qyid').value = sonValue[0];
				document.getElementById('qymc').value = sonValue[1];
				document.getElementById('szzid').value = sonValue[2];
			}
		}
		
		function clearCompany(){
        	document.getElementById("qymc").value="";
        	document.getElementById("qyid").value="";
        }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zjjtzsb.id" value="${zjjtzsb.id}">
		<input type="hidden" name="zjjtzsb.createTime" value="<fmt:formatDate type="both" value="${zjjtzsb.createTime}" />">
		<input type="hidden" name="zjjtzsb.updateTime" value="${zjjtzsb.updateTime}">
		<input type="hidden" name="zjjtzsb.createUserID" value="${zjjtzsb.createUserID}">
		<input type="hidden" name="zjjtzsb.updateUserID" value="${zjjtzsb.updateUserID}">
		<input type="hidden" name="zjjtzsb.deptId" value="${zjjtzsb.deptId}">
		<input type="hidden" name="zjjtzsb.delFlag" value="${zjjtzsb.delFlag}">
		<input type="hidden" name="zjjtzsb.state" value="${zjjtzsb.state}">
		<input type="hidden" name="zjjtzsb.remark" value="${zjjtzsb.remark}">
		<input type="hidden" name="zjjtzsb.isFirst" value="${zjjtzsb.isFirst}">
		<input type="hidden" name="zjjtzsb.shbs" value="${zjjtzsb.shbs}">
		<input type="hidden" name="zjjtzsb.linkId" value="${zjjtzsb.linkId}"/>
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">
						<cus:SelectOneTag property="zjjtzsb.szzid" defaultText='请选择' codeName="相城地址" value="${zjjtzsb.szzid}" onchange="clearCompany()" dataType="Require" msg="此项为必填" /><font style="color:red">*</font>
					</td>
					<th width="15%">单位名称</th>
					<td width="35%">
					<input id="qymc" name="zjjtzsb.qymc" type="text" dataType="Require" msg="此项为必填"  maxlength="255" value="${zjjtzsb.qymc}"><font style="color:red">*</font>
					<input name="zjjtzsb.qyid" value="${zjjtzsb.qyid}"  type="hidden" id="qyid" type="text" maxlength="255">
					<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>
					</td>
				</tr>
				<tr>
					<th width="15%">设备类别</th>
					<td width="35%"><input name="zjjtzsb.sblb" value="${zjjtzsb.sblb}" type="text" maxlength="255"></td>
					<th width="15%">设备档案号</th>
					<td width="35%"><input name="zjjtzsb.sbdah" value="${zjjtzsb.sbdah}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">注册代码</th>
					<td width="35%"><input name="zjjtzsb.zcdm" value="${zjjtzsb.zcdm}" type="text" maxlength="255"></td>
					<th width="15%">检验日期</th>
					<td width="35%">
						<input id="jyrq" name="zjjtzsb.jyrq"  value='${zjjtzsb.jyrq}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				<tr>
					<th width="15%">检验结论</th>
					<td width="85%" colspan="3"><input name="zjjtzsb.jyjl" value="${zjjtzsb.jyjl}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">检查日期</th>
					<td width="35%">
						<input id="jcrq" name="zjjtzsb.jcrq"  value='${zjjtzsb.jcrq}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
					<th width="15%">检查人员</th>
					<td width="35%"><input name="zjjtzsb.jcry" value="${zjjtzsb.jcry}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">单位联系人</th>
					<td width="35%"><input name="zjjtzsb.dwlxr" value="${zjjtzsb.dwlxr}" type="text" maxlength="255"></td>
					<th width="15%">电话</th>
					<td width="35%"><input name="zjjtzsb.dh" value="${zjjtzsb.dh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">主要问题</th>
					<td width="85%" colspan="3">
						<textarea name="zjjtzsb.zywt" style="width:100%;height:120px">${zjjtzsb.zywt}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3">
						<textarea name="zjjtzsb.bz" style="width:100%;height:120px">${zjjtzsb.bz}</textarea>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

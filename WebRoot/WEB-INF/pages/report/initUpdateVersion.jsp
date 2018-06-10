<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报表版本更新</title>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">
			function beforeSubmitCallback(){
				var fileOfDef = document.getElementById("fileOfDef").value;
				var reportName = document.getElementById("reportName").value;
				var origFileName = document.getElementById("deployFileName").value;
				
				if("" == fileOfDef){
					$.messager.alert('提示',"请选择.rptdesign报表设计文件！");
					return false;
				}
				
				if(fileOfDef.lastIndexOf(".")!= -1){
					var fileType = (fileOfDef.substring(fileOfDef.lastIndexOf(".")+1, fileOfDef.length)).toLowerCase();
					var fileName = fileOfDef.substring(fileOfDef.lastIndexOf("\\")+1,fileOfDef.length);
					if("rptdesign" != fileType){
						$.messager.alert('提示',"报表设计文件应该为.rptdesign文件！");
						return false;					
					}else if(fileName != origFileName){
						$.messager.alert('提示',"报表设计文件名必须和原文件名一致，以保证调用标签页面不需做修改！");
						return false;						
					}
				}			
			}			
			function checkDeploy(){
				var flag=document.getElementById("deployFlag").value;
				if(1 == flag){
					alert("报表版本更新成功！");
				}	
			}
		</script>
	</head>

	<body onload="checkDeploy()" validform="true">
		<c:set var="curr_path" value="报表版本更新"></c:set>
		<form action="updateVersion.action" method="post" id="form" enctype="multipart/form-data">
		<input type="hidden" name="deployFlag" value="${deployFlag}" />
			<div class="submitdata">
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<th style="text-align:left;">报表名称</th>
					<td style="text-align:left;">
						<input type="text" name="report.reportName" id="reportName" 
							value="${report.reportName}" class="input_text" readOnly>
					</td>
				</tr>
				<tr>
					<th style="text-align:left;">报表文件名</th>
					<td style="text-align:left;">
						<input type="text" name="report.deployFileName" id="deployFileName" 
							value="${report.deployFileName}" class="input_text" readOnly>
					</td>
				</tr>
				<tr>
					<th style="text-align:left;">
						<font color="red">.rptdesign报表设计文件</font>
					</th>
					<td style="text-align:left;">
						<input type="file" name="file" id="fileOfDef"  class="input_text"/>
					</td>
				</tr>
				<tr>
					<th style="text-align:left;">SQL脚本</th>
					<td style="text-align:left;">
						<textarea name="report.sqlScript" cols="100%" rows="10%"></textarea>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;" valign="middle" colspan="4">
						<a href="###" class="easyui-linkbutton" iconCls="icon-save" type="submit">更新报表设计文件</a>
						<a href="###" class="easyui-linkbutton" onclick="window.open('${ctx}/report/initList.action','_self');" iconCls="icon-back">返回</a>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>
</html>
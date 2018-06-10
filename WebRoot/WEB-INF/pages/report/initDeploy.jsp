<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报表发布</title>
		<%@include file="/common/jsLib.jsp"%>
		
		<script type="text/javascript">
			function beforeSubmitCallback(){
				var fileOfDef = document.getElementById("fileOfDef").value;
				if("" == fileOfDef){
					$.messager.alert('提示',"请选择.rptdesign报表设计文件！");
					return false;
				}
				
				if(fileOfDef.lastIndexOf(".")!= -1){
					var fileType = (fileOfDef.substring(fileOfDef.lastIndexOf(".")+1, fileOfDef.length)).toLowerCase();
					if("rptdesign" != fileType){
						$.messager.alert('提示',"报表设计文件应该为.rptdesign文件！");
						return false;
					}
				}				
			}
			function checkDeploy(){
				var flag=document.getElementById("deployFlag").value;
				if(1 == flag){
					$.messager.alert('提示',"报表发布成功！","info",function(){
						ajaxReturnRefresh();
						parent.close_win();						
					});
				}
			}
		</script>
	</head>

	<body onload="checkDeploy()" validform="true">
		<c:set var="curr_path" value="报表发布"></c:set>
		<form action="deploy.action" method="post" id="form" enctype="multipart/form-data">
		<input type="hidden" name="deployFlag" value="${deployFlag}" />
			<div class="submitdata">
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<th>报表名称</th>
					<td style="text-align:left;">
						<input type="text" name="report.reportName" id="reportName" class="input_text"
							datatype="s1-32" errormsg="不能为空，报表名称长度不能超过32个字符！" ajaxurl="checkReportName.action">
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">.rptdesign报表设计文件</font>
					</th>
					<td style="text-align:left;">
						<input type="file" name="file" id="fileOfDef" class="input_text"/>
					</td>
				</tr>
				<tr>
					<th>SQL脚本</th>
					<td style="text-align:left;">
						<textarea name="report.sqlScript" cols="35%" rows="10%"></textarea>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;" valign="middle" colspan="4">
						<a href="###" class="easyui-linkbutton" iconCls="icon-save" type="submit">发布报表设计文件</a>
						<a href="###" class="easyui-linkbutton" onclick="parent.close_win('deploy_report');" iconCls="icon-back">关闭</a>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>
</html>
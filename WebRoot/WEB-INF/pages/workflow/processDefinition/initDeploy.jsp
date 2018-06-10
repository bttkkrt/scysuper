<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>

<html>
	<head>
		<title>工作流管理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
              href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
              <script type="text/javascript"
			src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
		<script type="text/javascript"
			src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script src='<c:url value="/webResources/js/common.js"/>'></script>
		<script type="text/javascript">
	
	function doDeploy() {//发布新流程
		var fileOfDef = document.getElementById("fileOfDef").value;
		if("" == fileOfDef){
			$.messager.alert("警告",'请选择流程定义.zip文件!','warning');
			return;
		}
		
		if(fileOfDef.lastIndexOf(".")!= -1){
			var fileType = (fileOfDef.substring(fileOfDef.lastIndexOf(".")+1, fileOfDef.length)).toLowerCase();
			if("zip" != fileType){
					$.messager.alert("警告","流程定义文件应该为.zip文件！",'warning');
				return;
			}else{
			    var form = document.getElementById("form");
			    form.action="deploy.action";
			    form.submit();			
			}
		}
	}
	
	function checkDeploy(){
		var flag=document.getElementById("deployFlag").value;
		if(1 == flag){
			$.messager.alert("提醒","流程发布成功！",'info',function(){
				ajaxReturnRefresh();
				parent.close_win();
			});
		}	
	}
</script>
	</head>

	<body onload="checkDeploy()">
		<c:set var="curr_path" value="工作流发布"></c:set>
		<%@include file="/WEB-INF/template/content_title.jsp"%>

		<!--  <span id="checkId" style="display:none;font-size: 16;color: red;" >流程发布成功</span> -->
		<form action="" method="post" id="form" enctype="multipart/form-data">
			  <s:token />
			<input type="hidden" name="deployFlag" value="${deployFlag}" />
			<div class="submitdata">
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td>
						<input type="file" name="file" id="fileOfDef" class="input_text"/>
					</td>
					<td>
						<font color="red">.zip定义文件最大为15M</font>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div align="left">
							<BR>
							<a href="###" class="easyui-linkbutton" onclick="doDeploy()" iconCls="icon-add">发布</a>&nbsp;
						</div>
					</td>
				</tr>
			</table>
			</div>
		</form>
	</body>

</html>
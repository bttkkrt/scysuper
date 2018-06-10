<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<c:set var="curr_path" value="查看记录"/>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<%@include file="/common/jsLib.jsp"%>
		
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/webResources/themes/${curr_user.cssId}/css/style.css">

		<script>

 function open_file(attachId)
 {
            window.open("readAttFile.action?rowguid="+attachId,"_blank");
        }  



</script>
	</head>

	<body>

		<c:set var="curr_path" value="查看"></c:set>
		<form name="myform" method="post">
			<input type="hidden" name="tableId" value="${tableId }" />
<div class="submitdata">
			<table width="100%">
				<tr>
					<edp:viewRecord codeMapName="codeMap"
						columnListName="columnList" valueMapName="valueMap"
						tablePhyName="${tablePhyName}" tableId="${tableId}" rowId="${rowId}"/>
				</tr>
				<tr>
					<td colspan="4" align="center" height="10px"
						style="color: red; font-size: 14px; font-weight: bold">
						<s:property value="msg" />
					</td>
				</tr>
				<tr>
					<th colspan="4" height="100px" class="set_c">
					    <a href="#" class="easyui-linkbutton" onclick="parent.close_win();" iconCls="icon-close">关闭</a>						
					</th>
				</tr>
			</table>
</div>
		</form>
	</body>
</html>

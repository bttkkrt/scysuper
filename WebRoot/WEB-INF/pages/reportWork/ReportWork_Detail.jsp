<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script> 
        //下载附件
        function downloadFile(attachId){
                location.href = "${ctx}/jsp/reportWork/download.action?fileId="+attachId;
        }
        
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="10%">工作标题</th>
					<td colspan="3">${reportWork.workTitle}</td>
				</tr>
				<tr>
					<th width="10%">用户名称</th>
					<td colspan="3">${reportWork.userName}</td>
				</tr>
				<tr>
					<th width="10%">工作内容</th>
					<td colspan="3">${reportWork.workContent}</td>
				</tr>
				
				<tr>
					<th width='10%'>接收人</th>
					<td colspan='3'>${userNames}</td>
				</tr>
				<tr>
					<th width="10%">附件</th>
					<td width="70%">
						<div style="color:green;overflow:auto;height:160px;">
						<table id="more">
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">${item.fileName}</td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align: center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

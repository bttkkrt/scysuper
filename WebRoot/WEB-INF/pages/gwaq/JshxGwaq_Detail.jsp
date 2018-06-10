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
                location.href = "${ctx}/jsp/gwaq/download.action?fileId="+attachId;
        }
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="30%">所在镇名称</th>
					<td width="70%">${jshxGwaq.szzname}</td>
				</tr>
				<tr>
					<th width="30%">企业名称</th>
					<td width="70%">${jshxGwaq.qymc}</td>
				</tr>
				<tr>
					<th width="30%">岗位名称</th>
					<td width="70%">${jshxGwaq.gwmc}</td>
				</tr>
				<tr>
					<th width="30%">岗位员工数</th>
					<td width="70%">${jshxGwaq.gwygs}</td>
				</tr>
				<tr>
					<th width="30%">最大工作时间</th>
					<td width="70%">${jshxGwaq.zdgzsj}小时</td>
				</tr>
				<tr>
					<th width="30%">是否倒班</th>
					<td width="70%"><cus:hxlabel codeName="是或否" itemValue="${jshxGwaq.sfdb}" /></td>
				</tr>
				<tr>
					<th width="30%">倒班总人数</th>
					<td width="70%">${jshxGwaq.dbzrs}</td>
				</tr>
				<tr>
					<th width="30%">本岗位安全操作规程</th>
					<td width="70%">
						<div style="color:green;overflow:auto;height:200px;">
						<table>
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
					<td colspan="2" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

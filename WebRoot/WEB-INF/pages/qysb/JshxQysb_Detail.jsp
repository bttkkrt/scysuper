<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
<script>	    
		function download(type)
        {
        	location.href = "${ctx}/jsp/qysb/fileDownload.action?type=" + type;
        }
	    function savepic(i,j){
       		window.location.href="${ctx}/jsp/safetysheet/safesheetDownFile.action?picName="+i+"&fileName="+j;
		}
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%">${jshxQysb.qymc}</td>
					<th width="15%">所在镇</th>
					<td width="35%">${jshxQysb.szzname}</td>
				</tr>
				<tr>
					<th width="15%">上传时间</th>
					<td width="35%"><fmt:formatDate type="both" value="${jshxQysb.createTime}"/></td>
				</tr>
					<tr>
						<th width="15%">已上传文件</th>
					    <td width="85%" colspan="3">
					    		<div style="color:green;overflow:auto;height:200px;">
								<table id="aqjsbz" width="100%">
						
													<c:forEach var="item" items="${list}" varStatus="status">  
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																${item.fileName}
															</td>
															<td width="30%">
																<a style="color:red" href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
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
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

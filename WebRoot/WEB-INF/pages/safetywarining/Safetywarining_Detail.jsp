<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        	window.open("${ctx}/jsp/safetysheet/safesheetUpload.action?safetysheet.linkid=${safetywarining.linkid}&type="+obj);
        	}
        	 function savepic(i,j){
        		window.location.href="${ctx}/jsp/safetysheet/safesheetDownFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "${ctx}/jsp/safetysheet/safesheetImgDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
        }
        	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<s:if test="role!=1">
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%">${safetywarining.qymc}</td>
					<th width="15%">所在镇</th>
					<td width="35%">${safetywarining.szz}</td>
				</tr>
				</s:if>
				<tr>
					<th width="15%">上传时间</th>
					<td width="35%"><fmt:formatDate type="both" value="${safetywarining.createTime}" /></td>
				</tr>
					<tr>
						<th width="15%">已上传文件</th>
				<td width="85%" colspan="3">
							<div style="color:green;overflow:auto; height:200px;">
										  <table id="aqjsbz" width="80%">
													<c:forEach var="item" items="${list}" varStatus="status">  
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																&nbsp;${item.fileName}
															</td>
															<td width="30%">
																<a style="color:red" href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
															</td>
														</tr>
													</c:forEach>
											</table></div>
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

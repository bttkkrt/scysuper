<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.css" media="screen" />
	<script type="text/javascript">
		$(document).ready(function() {
			$("a[rel=example_group]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over'
			});

		});
		function savepic(i,j){
        	window.location.href="${ctx}/jsp/zycsqk/zycsqkSaveFile.action?picName="+i+"&fileName="+j;
        }
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">${zycsqk.szzname}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${zycsqk.qymc}</td>
				</tr>
				<tr>
					<th width="15%">车间名称</th>
					<td width="35%">${zycsqk.cjmc}</td>
					<th width="15%">无尘车间</th>
					<td width="35%">${zycsqk.wccj}</td>
				</tr>
				<tr>
					<th width="15%">岗位</th>
					<td width="35%">${zycsqk.gw}</td>
					<th width="15%">职业病危害因素名称</th>
					<td width="35%">${zycsqk.zywhysmc}</td>
				</tr>
				<tr>
					<th width="15%">设备状态</th>
					<td width="35%">${zycsqk.sbzt}</td>
					<th width="15%">操作方式</th>
					<td width="35%">${zycsqk.czfs}</td>
				</tr>
				<tr>
					<th width="15%">操作接触人数</th>
					<td width="35%">${zycsqk.jcrs}</td>
					<th width="15%">当班累积接触时间</th>
					<td width="35%">${zycsqk.ljjcsj}</td>
				</tr>
				<tr>
					<th width="15%">防护设施名称</th>
					<td width="35%">${zycsqk.fhssmc}</td>
					<th width="15%">个人防护用品</th>
					<td width="35%">${zycsqk.grfhyp}</td>
				</tr>
				<tr>
					<th width="15%">岗位图片</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td width="100%">
									<div style="color:green;overflow:auto;height:200px;">
									   	<div>
										  	<table>
												<c:forEach var="item" items="${picList}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
															<a href="/upload/file/${item.picName}" rel="example_group">
															<img src="/upload/file/${item.picName}"
															border='0' width='220' height='150'/>
															</a>
														</td>
														<td width="30%">
															<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
														</td>
													</tr>
												</c:forEach>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
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

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
					<th width="30%">所在镇</th>
					<td width="70%">${jshxZzbw.szzname}</td>
				</tr>
				<tr>
					<th width="30%">企业名称</th>
					<td width="70%">${jshxZzbw.qymc}</td>
				</tr>
				<tr>
					<th width="30%">装置、重点部位</th>
					<td width="70%">${jshxZzbw.gjzzmc}</td>
				</tr>
				<tr>
					<th width="30%">主要危险因素</th>
					<td width="70%">${jshxZzbw.zywxys}</td>
				</tr>
				<tr>
					<th width="30%">责任人</th>
					<td width="70%">${jshxZzbw.zrr}</td>
				</tr>
				<tr>
					<th width="30%">岗位员工数量</th>
					<td width="70%">${jshxZzbw.gwygsl}</td>
				</tr>
				<tr>
					<th width="30%">安全操作规程</th>
					<td width="70%">
						<div style="color:green;overflow:auto; height:200px;">
						<table>
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">${item.fileName}</td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;</td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="30%">现场图片</th>
					<td width="70%">
						<div style="color:green;overflow:auto; height:200px;">
						<table>
							  <c:forEach var="item" items="${picList1}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   <a href="/upload/file/${item.picName}" rel="example_group">	
								   <img src="/upload/file/${item.picName}"
										border='0' width='220' height='150'/>
									</a>	
									</td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;</td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
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

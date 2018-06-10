<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">粉尘</th>
					<td colspan="3"><cus:hxmulselectlabel codeName="粉尘类" itemValue="${whysjbqk.fc}" /></td>
				</tr>
				<tr>
					<th width="15%">化学毒物</th>
					<td colspan="3"><cus:hxmulselectlabel codeName="化学毒物类" itemValue="${whysjbqk.hxdw}" /></td>
				</tr>
				<tr>
					<th width="15%">物理因素</th>
					<td colspan="3"><cus:hxmulselectlabel codeName="物理因素类" itemValue="${whysjbqk.wlys}" /></td>
				</tr>
				<tr>
					<th width="15%">生物性因素</th>
					<td colspan="3"><cus:hxmulselectlabel codeName="生物因素类" itemValue="${whysjbqk.swxys}" /></td>
				</tr>
				<tr>
					<th width="15%">其它分类</th>
					<td width="85%" colspan="3">
						<div id="more">
						<table>
							<tr>
								<th  style="text-align:center">分类</th>
								<th style="text-align:center">其它危害因素</th>
							</tr>	
							<c:forEach var="glb" items="${qts}">
								<tr style="text-align: center">
									<td style="text-align: center">${glb.fl}</td>
									<td style="text-align: center">${glb.qtwhys}</td>
								</tr>
							</c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px"  style="text-align:center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

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
				<s:if test="role==0">
				<tr>
				    <th width="15%">所在街道</th>
					<td width="35%">${dangerused.szz}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${dangerused.qymc}</td>
				</tr>
				</s:if>
				<tr>
					<th width="15%">危险化学品名称</th>
					<td width="35%">${dangerused.dangername}</td>
					<th width="15%">危规号</th>
					<td width="35%">${dangerused.dannum}</td>
				</tr>
				<tr>
					<th width="15%">年使用量(t)</th>
					<td width="35%">${dangerused.yearuser}</td>
					<th width="15%">最大贮存量(t)</th>
					<td width="35%">${dangerused.maxstorge}</td>
				</tr>
				<tr>
					<th width="15%">贮存方式</th>
					<td width="35%">${dangerused.storgerway}</td>
					<th width="15%">贮存地点</th>
					<td width="35%">${dangerused.storgeaddress}</td>
				</tr>
				<tr>
					<th width="15%">包装方式</th>
					<td width="35%">${dangerused.packingway}</td>
					<th width="15%">是否是易制毒化学品</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${dangerused.sfsyzdhxp}" /></td>
				</tr>
				<tr>
					<th width="15%">是否是重点监管化学品</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${dangerused.sfzdjghxp}" /></td>
					<th width="15%">是否是易制爆化学品</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${dangerused.sfyzbhxp}" /></td>
				</tr>
				<tr>
					<th width="15%">是否是剧毒化学品</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${dangerused.sfjdhxp}" /></td>
					<th width="15%">企业类型</th>
					<td width="35%"><cus:hxmulselectlabel codeName="危化品企业类型" itemValue="${dangerused.qylx}" /></td>
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

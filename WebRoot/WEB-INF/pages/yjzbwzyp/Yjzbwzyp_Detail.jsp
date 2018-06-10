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
					<th width="15%">所在区域</th>
					<td width="35%">${yjzbwzyp.szz}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${yjzbwzyp.qymc}</td>
				</tr>
				</s:if>
				<tr>
				
					<th width="15%">应急类型</th>
					<td width="35%"><cus:hxlabel codeName="应急类型" itemValue="${yjzbwzyp.yjlx}" /></td>
						<th width="15%">装备名称</th>
					<td width="35%">${yjzbwzyp.zbmc}</td>
				</tr>
				<tr>
					<th width="15%">储存地点</th>
					<td width="35%" colspan=3>${yjzbwzyp.ccdd}</td>
				</tr>
				<tr>
					<th width="15%">储备数量</th>
					<td width="35%">${yjzbwzyp.cbsl}</td>
					<th width="15%">主要用途</th>
					<td width="35%">${yjzbwzyp.zyyt}</td>
				</tr>
				<tr>
					<th width="15%">保管人</th>
					<td width="35%">${yjzbwzyp.bgr}</td>
					<th width="15%">联系电话</th>
					<td width="35%">${yjzbwzyp.lxdh}</td>
				</tr>
				<tr>
					<th width="15%">填报人</th>
					<td width="35%">${yjzbwzyp.tbr}</td>
				
					<th width="15%">填报时间</th>
						<td width="35%">${yjzbwzyp.tbsj}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan=3><textarea readOnly name="yjzbwzyp.remark" style="width:100%;height:120px">${yjzbwzyp.remark}</textarea></td>
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

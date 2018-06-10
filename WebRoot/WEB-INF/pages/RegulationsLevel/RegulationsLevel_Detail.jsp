<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
	//页面展示办公用品类别树
		$(function(){
			getSuppliesTypeTree('mfSuppliesTypeParentId','citySel');
		});
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">上级级别</th>
					<td width="35%">
						<input id="citySel" type="text" value="${regulationsLevel.uplevelId }" readonly="readonly"></input>
						<input type="hidden" name="regulationsLevel.uplevelId"
							id="mfSuppliesTypeParentId"
							value="${regulationsLevel.uplevelId }">
					</td>
				</tr>
				<tr>
					<th width="15%">级别名称</th>
					<td width="35%"><input id="levelName"  style="width: 62.4%" name="regulationsLevel.levelName" value="${regulationsLevel.levelName}" type="text" dataType="Require" msg="此项为必填" maxlength="100" readonly="readonly"></td>
				</tr>
				<tr>
					<th width="15%">排序</th>
					<td width="35%">
						<input style="width: 62.4%" id="numbercard" readonly="readonly"
							name="regulationsLevel.numbercard"
							value="${regulationsLevel.numbercard}" type="text"
							datatype="n1-10" errormsg='此项请填入整数' ignore="ignore">
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
						<center>
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
						</center>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>

	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr style="background-color: #507CD1; text-align: center;">
				<td>
					&nbsp;
				</td>
				<td>
					<font style="font-family: '幼圆'; color: white; font-size: 15px">隐患数</font>
				</td>
				<td>
					<font style="font-family: '幼圆'; color: white; font-size: 15px">已整改数</font>
				</td>
				<td>
					<font style="font-family: '幼圆'; color: white; font-size: 15px">未整改数</font>
				</td>
				<td>
					<font style="font-family: '幼圆'; color: white; font-size: 15px">整改率</font>
				</td>
			</tr>
			<s:iterator id="type" value="types">
				<tr style="background-color: #E5ECF6; text-align: center;">
					<td>
						<s:property value="#type.jclbName" />
					</td>
					<td>
						<s:property value="#type.lbCount" />
					</td>
					<td>
						<s:property value="#type.yzgCount" />
					</td>
					<td>
						<s:property value="#type.wzgCount" />
					</td>
					<td>
						<s:property value="#type.zgl" />
					</td>
				</tr>
				<tr style="text-align: center;">
					<td height="20px;" colspan="5"></td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>

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
			<table width="100%" border="0">
				<tr>
					<th  width="15%" style="text-align:center;">职业病危害因素种类</th>
					<th width="10%" style="text-align:center;">有或无</th>
					<th width="15%" style="text-align:center;">接触人数</th>
					<th width="15%" style="text-align:center;">接触职业病危害总人数</th>
					<th width="15%" style="text-align:center;">其中女工数</th>
				</tr>
				<tr>
					<th width="15%">粉尘类</th>
					<td width="10%">
						<s:radio list="#{'1':'有','0':'无'}" theme="simple" name="zybwhzl.fclyw" disabled="true"  value="#{zybwhzl.fclyw}"/>
					</td>
					<td width="15%">${zybwhzl.fcljcrs}</td>
					<td width="15%">${zybwhzl.fclzrs}</td>
					<td width="15%">${zybwhzl.fclngs}</td>
				</tr>
				<tr>
					<th width="15%">化学物质类</th>
					<td width="10%">
						<s:radio list="#{'1':'有','0':'无'}" theme="simple" name="zybwhzl.hxwzlyw"  disabled="true" value="#{zybwhzl.hxwzlyw}"/>
					</td>
					<td width="15%">${zybwhzl.hxwzljcrs}</td>
					<td width="15%">${zybwhzl.hxwzlzrs}</td>
					<td width="15%">${zybwhzl.hxwzlngs}</td>
				</tr>
				<tr>
					
					<th width="15%">物理因素类</th>
					<td width="10%">
						<s:radio list="#{'1':'有','0':'无'}" theme="simple" name="zybwhzl.wlysyw"  disabled="true" value="#{zybwhzl.wlysyw}"/>
					</td>
					<td width="15%">${zybwhzl.wlysjcrs}</td>
					<td width="15%">${zybwhzl.wlyszrs}</td>
					<td width="15%">${zybwhzl.wlysngs}</td>
				</tr>
				<tr>
					<th width="15%">放射性物质类</th>
					<td width="10%">
						<s:radio list="#{'1':'有','0':'无'}" theme="simple" name="zybwhzl.fsxwzlyw" disabled="true"  value="#{zybwhzl.fsxwzlyw}"/>
					</td>
					<td width="15%">${zybwhzl.fsxwzljcrs}</td>
					<td width="15%">${zybwhzl.fsxwzlzrs}</td>
					<td width="15%">${zybwhzl.fsxwzlngs}</td>
				</tr>
				<tr>
					<th width="15%">其他</th>
					<td width="10%">
						<s:radio list="#{'1':'有','0':'无'}" theme="simple" name="zybwhzl.qtyw" disabled="true"  value="#{zybwhzl.qtyw}"/>
					</td>
					<td width="15%">${zybwhzl.qtjcrs}</td>
					<td width="15%">${zybwhzl.qtzrs}</td>
					<td width="15%">${zybwhzl.qtngs}</td>
				</tr>
			</table>
			<table>
				<tr>
					<th width="15%">填报人</th>
					<td width="35%">${zybwhzl.tbr}</td>
					<th width="15%">联系电话</th>
					<td width="35%">${zybwhzl.lxdh}</td>
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

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
					<th width="20%">填报单位</th>
					<td width="20%">${jcgzzb.szzname}</td>
					<th width="20%">统计日期</th>
					<td width="40%" colspan="2">
						<fmt:formatDate type="date" value="${jcgzzb.tjkssj}" />
						至
						<fmt:formatDate type="date" value="${jcgzzb.tjjssj}" />
						</td>
				</tr>
				<tr>
					<th width="20%">计划执法检查</th>
					<td width="40%" colspan="2">${jcgzzb.jhzfjccs}次</td>
					<td width="40%" colspan="2">${jcgzzb.jhzfjcjs}家</td>
				</tr>
				<tr>
					<th width="20%" rowspan="5">执法检查</th>
					<th width="20%">组织执法检查（次）</th>
					<td width="20%">${jcgzzb.zzzfjc}</td>
					<th width="20%">检查单位（家）</th>
					<td width="20%">${jcgzzb.jcdw}</td>
				</tr>
				<tr>
					<th width="20%">发现安全隐患（处）</th>
					<td width="20%">${jcgzzb.fxaqyh}</td>
					<th width="20%">督促整改隐患（处）</th>
					<td width="20%">${jcgzzb.dczgxx}</td>
				</tr>
				<tr>
					<th width="20%">其中：重大安全隐患（处）</th>
					<td width="20%">${jcgzzb.zdaqyh}</td>
					<th width="20%">其中：整改重大安全隐患（处）</th>
					<td width="20%">${jcgzzb.zgzdaqyh}</td>
				</tr>
				<tr>
					<th width="20%">现场检查记录（份）</th>
					<td width="20%">${jcgzzb.xcjcjl}</td>
					<th width="20%">整改复查意见书（份）</th>
					<td width="20%">${jcgzzb.zgfcyjs}</td>
				</tr>
				<tr>
					<th width="20%">责令限期整改指令书（份）</th>
					<td width="20%">${jcgzzb.zlxqzgzls}</td>
					<th width="20%">强制措施决定书（份）</th>
					<td width="20%">${jcgzzb.qzcsjds}</td>
				</tr>
				<tr>
					<th width="20%">建议实施行政处罚</th>
					<td width="80%" colspan="4"><textarea readOnly name="jcgzzb.jyssxzcf" style="width:100%;height:120px">${jcgzzb.jyssxzcf}</textarea></td>
				</tr>
				<tr>
					<th width="20%">主要安全隐患</th>
					<td width="80%" colspan="4"><textarea readOnly name="jcgzzb.zyaqyh" style="width:100%;height:120px">${jcgzzb.zyaqyh}</textarea></td>
				</tr>
				<tr>
					<th width="20%">其它</th>
					<td width="80%" colspan="4"><textarea readOnly name="jcgzzb.qt" style="width:100%;height:120px">${jcgzzb.qt}</textarea></td>
				</tr>
				<tr>
					<th width="20%">填报人</th>
					<td width="20%">${jcgzzb.tbr}</td>
					<th width="20%">填报日期</th>
					<td width="20%"><fmt:formatDate type="date" value="${jcgzzb.tbrq}" /></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="5" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body style="overflow: auto;">
	<form name="myform" method="post">
		<div class="page_dialog" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div  style="width: ${tableWidth}">
			<table width="100%" border="0">
			<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 22.0pt">
						建设项目职业病危害预评价报告
					</td>	
				</tr>
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 22.0pt">
						备案通知书
					</td>	
				</tr>
				<tr>
					<td height="20px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋_GB2312;font-size: 14.0pt">
						（${proEvaNot.proNo}）安职预备字〔${proEvaNot.preparatoryWord}〕第 ${proEvaNot.arranging} 号
					</td>
				</tr>
				<tr>
					<td>
						<hr style="height:5px;border:none;border-top:5px double;"/>
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					<cus:hxlabel codeName="企业属地" itemValue="${proEvaNot.areaId}" />${proEvaNot.companyName}:
				</tr>
				<tr>
					<td height="20px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					   &nbsp;&nbsp;&nbsp; 你单位&nbsp;  ${proEvaNot.proDate} &nbsp; 提请备案的&nbsp;   ${proEvaNot.projectName}&nbsp;职业病危害预评价报告、自行组织评审意见和情况报告以及其他相关文件、资料收悉。经核实，提交的文件、资料齐全，符合有关规定，现予以备案。
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					经 办人&nbsp;：${proEvaNot.conPeople}
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					联系电话：${proEvaNot.conPhone}
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋_GB2312;font-size: 16.0pt">
						备案部门名称（盖章）
					</td>
				</tr>
				<tr>
				<td style="text-align: right;font-family:仿宋_GB2312;font-size: 16.0pt">
				<fmt:formatDate type="date" value="${proEvaNot.createTime}" pattern="yyyy-MM-dd"/>
				</td>
				</tr>
				<tr>
					<td>
						<hr style="height:5px;border:none;border-top:3px double;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 10.5pt">
						&nbsp;&nbsp;本文书一式3份。一份送申请单位，一份送所在地安监部门，一份备案部门存档。
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_proEvaNot');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>

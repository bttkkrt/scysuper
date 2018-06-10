<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>打印</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		$(function(){
			window.print();
			window.close();
		});
	</script>
</head>
<body>
		<div style="position: absolute;width: 80%; left: 10%">
			<table width="100%">
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 42.0pt;">
						抚顺市安全生产监督管理局
					</td>
				</tr>
				<tr>
					<td>
						<hr style="height:5px;border:none;border-top:5px double;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 22.0pt">
						职业病危害现状评价报告回执
					</td>	
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋;font-size: 16.0pt">
						相安监职评回[${occupationalEvaluateReceipt.requiredTwo}]
						${occupationalEvaluateReceipt.requiredThree}号
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋;font-size: 16.0pt">
						${occupationalEvaluateReceipt.companyName}:
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋;font-size: 16.0pt">
						&nbsp;&nbsp;&nbsp;${occupationalEvaluateReceipt.userName}
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋;font-size: 16.0pt">
						（备案部门盖章）
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋;font-size: 16.0pt">
						${occupationalEvaluateReceipt.sendTime}&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
</body>
</html>

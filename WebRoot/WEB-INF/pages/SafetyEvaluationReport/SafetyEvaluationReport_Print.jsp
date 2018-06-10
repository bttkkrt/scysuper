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
						危险化学品从业单位安全现状评价报告通知书
					</td>	
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family: 仿宋;font-size: 16.0pt">
						备案编号:${safetyEvaluationReport.safetyPlanId}
					</td>	
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋;font-size: 16.0pt">
						${safetyEvaluationReport.companyName}:
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋;font-size: 16.0pt">
						&nbsp;&nbsp;&nbsp;${safetyEvaluationReport.userName}
					</td>
				</tr>
				<tr>
					<td height="100px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋;font-size: 16.0pt">
						（备案机关签章）
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋;font-size: 16.0pt">
						${safetyEvaluationReport.sendTime}&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
</body>
</html>

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
						建设项目职业病危害预评价报告表备案通知书
					</td>	
				</tr>
				<tr>
					<td height="30px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋;font-size: 16.0pt">
						(${occupationalEvaluatePlan.requiredOne})
						安职控备字(${occupationalEvaluatePlan.requiredTwo})
						第${occupationalEvaluatePlan.requiredThree}号
					</td>
				</tr>
				<tr>
					<td height="30px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋;font-size: 16.0pt">
						${occupationalEvaluatePlan.companyName}:
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋;font-size: 16.0pt">
						&nbsp;&nbsp;&nbsp;${occupationalEvaluatePlan.userName}
					</td>
				</tr>
				<tr>
					<td height="30px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋;font-size: 16.0pt">
						经办人：${occupationalEvaluatePlan.contact}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话：${occupationalEvaluatePlan.tel}
					</td>
				</tr>
				<tr>
					<td height="30px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋;font-size: 16.0pt">
						（备案部门盖章）
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋;font-size: 16.0pt">
						${occupationalEvaluatePlan.sendTime}&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<hr style="height:3px;border:none;border-top:5px double;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋;font-size: 10.5pt">
						&nbsp;&nbsp;本文书一式3份。一份送达申请单位，一份送所在地安监部门，一份备案部门存档。
					</td>
				</tr>
			</table>
		</div>
</body>
</html>

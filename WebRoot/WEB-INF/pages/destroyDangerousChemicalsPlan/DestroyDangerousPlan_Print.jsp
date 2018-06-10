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
						危险化学品重大危险源核销告知书
					</td>	
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋;font-size: 16.0pt">
						${destroyDangerousPlan.companyName}:
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋;font-size: 16.0pt">
						&nbsp;&nbsp;&nbsp;${destroyDangerousPlan.userName}
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>	
					<td style="text-align: left;font-family:仿宋;font-size: 16.0pt">
						联系人：${destroyDangerousPlan.contact}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话：${destroyDangerousPlan.tel}
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋;font-size: 16.0pt">
						（承办机构盖章）
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋;font-size: 16.0pt">
						${destroyDangerousPlan.sendTime}&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
</body>
</html>

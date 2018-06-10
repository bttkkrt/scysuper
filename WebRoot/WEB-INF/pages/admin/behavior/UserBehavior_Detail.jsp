<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>查看</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<body>
		<div class="page_dialog">
			<form name="myform" method="post">
				<div class="cell">
					<table>
						<tr>
							<th width="15%">
								类别
							</th>
							<td width="35%">
								<cus:hxlabel codeName="用户行为类别"
									itemValue="${userBehavior.behaviorType}" />
							</td>
						</tr>
						<tr>
							<th width="15%">
								用户行为名称
							</th>
							<td width="35%">
								${userBehavior.behaviorName}
							</td>
						</tr>
						<tr>
							<th width="15%">
								用户行为地址
							</th>
							<td width="35%">
								${userBehavior.behaviorUrl}
							</td>
						</tr>
						<tr>
							<th width="15%">
								用户行为处理服务
							</th>
							<td width="35%">
								${userBehavior.behaviorService}
							</td>
						</tr>
						<tr>
							<th width="15%">
								用户行为默认日志
							</th>
							<td width="35%"
								style="height: 120px; vertical-align: top; padding-top: 8px;">
								${userBehavior.defaultLog}
							</td>
						</tr>
						<tr>
							<th width="15%">
								是否到指定的URL
							</th>
							<td width="35%">
								<cus:hxlabel codeName="是或否"
									itemValue="${userBehavior.isContinue}" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="btn_area_setc">
									<a href="#" class="btn_01" type="submit"
										onclick="parent.close_win('behavoirWindow');">关闭<b></b> </a>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>

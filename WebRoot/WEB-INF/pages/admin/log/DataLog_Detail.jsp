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
				<div class="inner6px">
					<div class="cell">
						<table>
							<tr>
								<th width="15%">
									数据对象名称
								</th>
								<td width="35%" style="word-wrap: break-word;">
									${dataLog.entityName}
								</td>
								<th width="15%">
									操作者
								</th>
								<td width="35%">
									${dataLog.oprator.displayName}
								</td>

							</tr>
							<tr>
								<th width="15%">
									记录数
								</th>
								<td width="35%">
									${dataLog.recordNum}
								</td>
								<th width="15%">
									操作时长(ms)
								</th>
								<td width="35%">
									${dataLog.opDuration}
								</td>
							</tr>
							<tr>
								<th width="15%">
									操作开始时间
								</th>
								<td width="35%">
									${dataLog.createTime}
								</td>
								<th width="15%">
									操作结束时间
								</th>
								<td width="35%">
									${dataLog.updateTime}
								</td>
							</tr>
							<tr>
								<th width="15%">
									操作类型
								</th>
								<td width="85%" colspan="3">
									<cus:hxlabel codeName="数据操作类型" itemValue="${dataLog.opType}" />
								</td>
							</tr>
							<tr>
								<th width="15%">
									操作日志
								</th>
								<td width="85%" colspan="3" style="height: 120px;vertical-align: top;padding-top: 8px;">
									${dataLog.opLog}
								</td>
							</tr>
							<tr>
								<td colspan="4" height="100px" style="text-align: center">
									<div class="btn_area_setc">
										<a href="###" class="btn_01"
											onclick="parent.close_win('dataLogWindow');">关闭<b></b> </a>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>

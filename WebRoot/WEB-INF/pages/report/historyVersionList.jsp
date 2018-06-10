<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报表历史版本管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">		
			function openFile(attachmentId){
				var url = "<c:url value='/report/downloadAttatchment.action'/>";
				window.location.href = url+"?attachmentId="+attachmentId;
			}
		</script>
	</head>

	<body>
		<c:set var="curr_path" value="报表历史版本管理"></c:set>
		<form action="" method="post" id="hisReportForm">
		<input type="hidden" name="reportId" value="${reportId}">
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td align="center">
						<div class="outputdata">
							<table cellspacing="0"  cellpadding="0"
								width="100%" border="0">
								<tr>
									<td>
										<display:table name="pagination" cellspacing="0"
											cellpadding="0" requestURI="findHistoryVersions.action"
											defaultsort="1" id="row" pagesize="10" export="false"
											htmlId="checkTable" style="width:100%">
											<display:column style="text-align:center"
												title="报表名称">
												<c:out value="${row.reportName}" />
											</display:column>
											<display:column style="text-align:center"
												title="版本号">
												<c:out value="${row.version}" />
											</display:column>
											<display:column style="text-align:center"
												title="发布时间">
												<fmt:formatDate value="${row.createTime}" type="date"
													pattern="yyyy-MM-dd HH:mm:ss" />
											</display:column>
											<display:column style="text-align:center" title="下载">
												<a href="###" onclick="openFile('${row.id }')" title="下载">
													<u>${row.fileName}</u>
												</a>
											</display:column>
											<display:setProperty name="export.excel.filename"
												value="feeList.xls" />
											<display:setProperty name="export.csv.filename"
												value="feeList.csv" />
											<display:setProperty name="export.pdf.filename"
												value="feeList.pdf" />
										</display:table>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

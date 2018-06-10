<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Error</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>
	<c:set var="curr_path" value="操作结果"></c:set>
	<body>
		<div class="page_content">
			<div class="box_01">
				<div class="inner6px">
						<div class="o_tips_say">
							${flag}
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

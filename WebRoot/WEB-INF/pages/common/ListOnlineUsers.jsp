<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="curr_path" value="在线用户列表"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户查询</title>
<script type="text/javascript" src="${ctx}/webResources/js/common.js"></script>
<script>
        function dosearch(ff)
        {
        	ff.action = "queryOnlineUsers.action";
            ff.target = "_self";
            ff.submit();
        }
        function send_message(id,receiver){
        	popupCenter("", "message", "400", "200",
                    "no", "no", "no", "yes", "yes","no");
        }
    </script>

<link rel="stylesheet" type="text/css"
	href="${ctx}/webResources/themes/${curr_user.cssId}/main.css">

</head>
<body>
<c:set var="curr_path" value="在线用户"></c:set>
<form name="informationFrm" method="post">
	<div class="Div_ControlNoTop_Class">
	<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
	<td  width="15%" align="right"><b>关键字：</b></td>
	<td width="85%">&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="loginId" value="${loginId}" class="inputtxt" ><input type="button"
			value="查询" class="btnbg" onclick="dosearch(this.form);"><!-- font color=red>${fn:length(userList)}</font>结果-->
	</td>
	</tr>
	</table>
	</div>
	</form>
		<table width="98%" border="0" cellspacing="0" cellpadding="0"
			align="center" class="DataGridStyle">
			<!-- 表头开始  -->
			<tr height="22px" class="DataGridHeadStyle">
				<th width="80" align="center">姓名</th>
				<th  align="center">用户ID</th>
				<th  align="center">登录时间</th>
			</tr>
				<!-- 表头结束  -->
				<c:set var="i" value="0" />
				<c:forEach items="${userList}" var="user">
					<c:if test="${i%2==0}">
						<tr class="DataGridRowStyle">
					</c:if>
					<c:if test="${i%2==1}">
						<tr class="DataGridAlternatingItemStyle">
					</c:if>
					<td class="tb_02" align="center" width="80"><img src="${ctx}/webResources/images/user.gif"><a href="#" onclick="send_message('')">${user.displayName }</a>
					</td>
					<td class="tb_02" align="center">${user.loginId}</td>
					<td class="tb_02" align="center">
					    <fmt:formatDate value="${user.logTime}" pattern="yyyy.MM.dd"/>
					</td>
					</tr>
					<c:set var="i" value="${i+1}" />
				</c:forEach>
			<!-- DATA Ends -->
		</table>
</body>
</html>
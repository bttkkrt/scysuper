<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>CARDONEINFO管理</title>
	<link href="${ctx}/webResources/spCss/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
				<c:forEach var="carEqu" items="${carEquList}" varStatus="status">
					<div class="list_header" onclick="changeLb('${status.count}')"><b class="list_icon_01" title="${carEqu.comname}"></b>
						<c:if test="${fn:length(carEqu.comname)>18 }">
							${fn:substring(carEqu.comname, 0, 18)}
            			</c:if>
            			<c:if test="${fn:length(carEqu.comname)<=18 }">
            				${carEqu.comname}
            			</c:if>
					<i id="lb${status.count}" class="show_up"></i></div>
					<div id="splb${status.count}" class="list_show" style="display:none">
						<ul>
							<c:forEach var="item" items="${carEqu.videoList}">
            					<li><b class="camera_01"></b><a href="#" onclick="viewVideo('${item.guid}','${item.streamId}','${item.detailName}')">${item.detailName}</a></li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
</body>
</html>

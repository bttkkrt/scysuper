<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>苏州工业园区综合行政执法局</title>
<link href="${ctx}/webResources/wzCss/css/index.css" rel="stylesheet" type="text/css"/>
<script>
//下载附件
function downloadFile(attachId){
        location.href = "${ctx}/jsp/photoPic/saveFile.action?fileId="+attachId;
}
</script>
</head>

<body>
<div class="wrapper">
<!--header-->
	<div class="header">
    	<div class="logo fleft"><a href="#"><img src="${ctx}/webResources/wzCss/images/logo.png"/></a></div>
        <div class="erweima fright"><img src="${ctx}/webResources/wzCss/images/Android.png"/><a href="#">安卓</a></div>
        <div class="myclear"></div>
    </div>
<!--/header end-->

<!--nav-->
    <div id="navBg">
        <div class="nav">
             <ul>
                 <li><a class="navnow" href="${ctx}/wzxx/index.action" >首页</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=6">组织机构</a></li>
                <li><a href="${ctx}/wzxx/gzdtList.action?pageNum=1">工作动态</a></li>
                <li><a href="${ctx}/wzxx/newsList.action?pageNum=1">通知公告</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=7">信息公开</a></li>
                <li><a href="${ctx}/wzxx/zhzfWzList.action">综合执法</a></li>
                <li><a href="${ctx}/wzxx/csglWzList.action">城市管理</a></li>                 
                <li><a href="${ctx}/wzxx/aqwhList.action">安全生产</a></li>
                <li><a href="${ctx}/wzxx/jgdlWzList.action">机关党建</a></li>
                <li><a href="${ctx}/wzxx/flfgList.action">法律常规</a></li>             
                 <li><a href="${ctx}/wzxx/lxwmList.action">联系我们</a></li>        
          </ul>
        </div>	
    </div>
<!--/nav end-->	
<div class="main">
	<div class="location"><p>您现在的位置：<a href="${ctx}/wzxx/index.action">首页</a>&gt;<a href="${ctx}/wzxx/bgxzList.action">表格下载</a>&gt;<a href="#">表格详情</a></p></div>
    <div class="newscon">
            <div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">
						表格名称
					</th>
					<td width="35%">
						${bgxz.infoTitle}
					</td>
					<th width="15%">
						表格种类
					</th>
					<td width="35%">
						<s:if test="bgxz.bgzl == 1">
							城市管理
						</s:if>
						<s:elseif test="bgxz.bgzl == 2">
							安全生产
						</s:elseif>
						<s:elseif test="bgxz.bgzl == 3">
							行政执法
						</s:elseif>
						<s:else>
							其他
						</s:else>
					</td>
				</tr>
				<tr>
					<th width="15%">
						内容概述
					</th>
					<td width="85%" colspan="3">
						${bgxz.nrgs}
					</td>
				</tr>
				<tr>
					<th width="15%">表格下载</th>
					<td width="85%" colspan="3">
						<table>
							<c:forEach var="item" items="${picList}">
								<tr>
								   <td><a href="javascript:downloadFile('${item.id}');">${item.fileName}</a></td>
								</tr>
							</c:forEach>
						</table>
				    </td>
				</tr>
			</table>
		</div> 
     </div> 
</div>


<!--/bottom-->
<div class="bottom">
    <span>Copyright ©2015</span>
    <span>苏州工业园区综合行政执法局 版权所有.</span>
    <span>ALL RIGHTS RESERVED.</span>
    <p><span>苏ICP备09024432</span><span><a href="#">技术支持：南京拓构软件有限公司</a></span></p>
</div>
<!--/bottom end-->

	
</div>

</body>
</html>



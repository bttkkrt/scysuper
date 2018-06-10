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
                 <li><a  href="${ctx}/wzxx/index.action" >首页</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=6">组织机构</a></li>
                <li><a href="${ctx}/wzxx/gzdtList.action?pageNum=1">工作动态</a></li>
                <li><a class="navnow" href="${ctx}/wzxx/newsList.action?pageNum=1">通知公告</a></li>
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
		<div class="location"><p>您现在的位置：<a href="${ctx}/wzxx/index.action">首页</a>&gt;<a href="${ctx}/wzxx/newsList.action">通知公告</a>&gt;<a href="#">公告详情</a></p></div>
	</div>
<div class="wrap govopen">
        <div class="side-col">
            <div class="box sp-info-box mb10" style="min-height:270px;">
                <div class="hd">
                    <span class="title">工作动态</span><a href="${ctx}/wzxx/gzdtList.action?pageNum=1" class="more">更多&gt;&gt;</a></div>
                <div class="bd">
                    <ul class="infolist">
                    	<c:forEach var="gzdt" items="${gzdtList}">
                       	 	<li><a href="${ctx}/wzxx/gzdtContent.action?gzdt.id=${gzdt.id}">${gzdt.infoTitle}</a></li>                      	
						</c:forEach>    
                    </ul>
                </div>
            </div>
            <div class="box sp-info-box mb10" style="min-height:270px;">
                <div class="hd">
                    <span class="title">通知公告</span><a href="${ctx}/wzxx/newsList.action?pageNum=1" class="more">更多&gt;&gt;</a></div>
                <div class="bd">
                    <ul class="infolist">
                        <c:forEach var="tzgg" items="${tzggList}">
                       	 	<li><a href="${ctx}/wzxx/newsContent.action?tzgg.id=${tzgg.id}">${tzgg.infoTitle}</a></li>                    	
						</c:forEach>        
                    </ul>
                </div>
            </div>
            <div class="box sp-info-box mb10" style="min-height:270px;">
                <div class="hd">
                    <span class="title">媒体关注</span><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=4" class="more">更多&gt;&gt;</a></div>
                <div class="bd">
                    <ul class="infolist">
                        <c:forEach items="${wxhxpList}" var="map">
                    		<c:if test="${fn:length(map.infotitle)>20 }">
	    						<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }"+ >${fn:substring(map.infotitle, 0, 20)}...</a></li>
                    		</c:if>
                    		<c:if test="${fn:length(map.infotitle)<=20 }">
	    						<li><a href="${ctx}/wzxx/awhgzContent.action?wzInfoManage.id=${map.row_id }"+ >${map.infotitle}</a></li>
                    		</c:if>
    					</c:forEach>    
                    </ul>
                </div>
            </div>
        </div>
        <div class="main-col mb10">
             <div class="newsdetail" style="min-height:800px;">
             	<h1 class="title">
             	<c:if test="${fn:length(tzgg.firTitle)>0 }">
				<span class="smtitle">${tzgg.firTitle}</span><br/>
				</c:if>
				${tzgg.infoTitle}
				</h1>
				<div class="meta">
					本文被阅读次数：${tzgg.readNum}次&nbsp;&nbsp;
					发表时间：<fmt:formatDate type="date" value="${tzgg.publicDate}" pattern="yyyy-MM-dd"/>
				</div>
				<div class="content" id="newscontent">
					${tzgg.infoContent}
					<br/>
					<br/>
				    <c:forEach var="item" items="${picList}">
						附件：<a href="javascript:downloadFile('${item.id}');">${item.fileName}</a><br/>
					</c:forEach>
			</div>
        </div>
        </div>
        <div class="clear">
        </div>

    </div>
    <div class="wrap footer">
        <div class="copyrights">
           <span>Copyright ©2015</span>
    		<span>苏州工业园区综合行政执法局 版权所有.</span>
    		<span>ALL RIGHTS RESERVED.</span>
    		<p><span>苏ICP备09024432</span><span><a href="#">技术支持：南京拓构软件有限公司</a></span></p>
    	</div>
	</div>

</body>
</html>



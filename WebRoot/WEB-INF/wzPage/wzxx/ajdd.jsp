<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>苏州工业园区综合行政执法局</title>
<link href="${ctx}/webResources/wzCss/css/index.css" rel="stylesheet" type="text/css">
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
                 <li><a  class="navnow" href="${ctx}/wzxx/index.action" >首页</a></li>
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
	<div class="location"><p>您现在的位置：<a href="${ctx}/wzxx/index.action">首页</a>&gt;<a href="#">安全生产监察大队</a></p></div>
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
				安全生产监察大队
				</h1>
				<div class="meta">
				</div>
				<div class="content" id="newscontent">
					<br/>
					<p style="text-indent:2em">
					该大队主要职能为：根据园区综合行政执法局委托，负责功能区及社工委区域内的安全生产、国土资源、城镇燃气及相关市政管网等的日常监管及综合执法工作（各功能区及社工委按照属地管理职能继续承担安全生产等的日常监管工作）。
					</p>
					<br/>
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



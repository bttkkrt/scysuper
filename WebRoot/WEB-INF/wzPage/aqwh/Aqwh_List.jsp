<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>苏州工业园区综合行政执法局</title>
<!-- jquery & easyui js -->
<script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<link href="${ctx}/webResources/wzCss/css/index.css" rel="stylesheet" type="text/css">
<link href="${ctx}/webResources/wzCss/keleyidivpager/keleyidivpager.css" type="text/css" rel="Stylesheet" />
<script type="text/javascript" src="${ctx}/webResources/wzCss/keleyidivpager/keleyidivpager.js"></script>
<script type="text/javascript">
$(function()
{
		var totalPage = "${totalPage}";
        var totalRecords = "${totalCount}";
        var pageNo = getParameter('pageNum'); //这里设置参数名
        if (!pageNo) {
            pageNo = 1;
        }
        //生成分页控件 根据分页的形式在这里设置
        kkpager.init({
            pno: pageNo,
            //总页码
            total: totalPage,
            //总数据条数
            totalRecords: totalRecords,
            //链接前部
            hrefFormer: '${ctx}/wzxx/aqwhList',
            //链接尾部
            hrefLatter: '.action',
            getLink: function (n) {
                return this.hrefFormer + this.hrefLatter + "?pageNum=" + n; //参数名跟上面相同
            }
        });
        kkpager.generPageHtml();
});
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
                <li><a href="${ctx}/wzxx/index.action" >首页</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=6">组织机构</a></li>
                <li><a href="${ctx}/wzxx/gzdtList.action?pageNum=1">工作动态</a></li>
                <li><a href="${ctx}/wzxx/newsList.action?pageNum=1">通知公告</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=7">信息公开</a></li>
                <li><a href="${ctx}/wzxx/zhzfWzList.action">综合执法</a></li>
                <li><a href="${ctx}/wzxx/csglWzList.action">城市管理</a></li>   
                <li><a class="navnow" href="${ctx}/wzxx/aqwhList.action">安全生产</a></li>
                <li><a href="${ctx}/wzxx/jgdlWzList.action">机关党建</a></li>
                <li><a href="${ctx}/wzxx/flfgList.action">法律常规</a></li>             
                 <li><a href="${ctx}/wzxx/lxwmList.action">联系我们</a></li>         
          </ul>
        </div>	
    </div>
<!--/nav end-->	
<div class="main">
	<div class="location"><p>您现在的位置：<a href="${ctx}/wzxx/index.action">首页</a>&gt;<a href="#">安全生产</a></p></div>
    <div class="news_list">
    <ul>
    	<c:forEach var="aqwh" items="${aqwhList}">
    	  <c:if test="${fn:length(aqwh.infoTitle)>40 }">
        	<li><a href="${ctx}/wzxx/aqwhContent.action?safCul.id=${aqwh.id}">${fn:substring(aqwh.infoTitle, 0, 40)}...</a><fmt:formatDate type="date" value="${aqwh.publicDate}" pattern="yyyy-MM-dd"/></li>
          </c:if>
           <c:if test="${fn:length(aqwh.infoTitle)<=40 }">
           <li><a href="${ctx}/wzxx/aqwhContent.action?safCul.id=${aqwh.id}">${aqwh.infoTitle}</a><fmt:formatDate type="date" value="${aqwh.publicDate}" pattern="yyyy-MM-dd"/></li>
            </c:if>
        </c:forEach>
    </ul>
    <!--pages-->
    <!--pages-->
    <div id="div_pager">
    </div>
    <!--/pages结束-->
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

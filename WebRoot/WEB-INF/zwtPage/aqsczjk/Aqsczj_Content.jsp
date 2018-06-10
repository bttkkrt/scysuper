<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>苏州工业园区综合行政执法局</title>
<link href="${ctx}/webResources/wzCss/css/index.css" rel="stylesheet" type="text/css">

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
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=7">专项整治</a></li>
                <li><a href="${ctx}/wzxx/aqwhList.action">安全文化</a></li>
                <li><a href="${ctx}/wzxx/flfgList.action">法律规章</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=2">政务信息</a></li>
                <li><a href="${ctx}/wzxx/lxwmList.action">联系我们</a></li>     
          </ul>
        </div>	
    </div>
<!--/nav end-->	
<div class="main">
	<div class="location"><p>您现在的位置：<a href="${ctx}/wzxx/index.action">首页</a>&gt;<a href="${ctx}/wzxx/aqsczjList.action">安全生产专家查询</a>&gt;<a href="#">安全生产专家详情</a></p></div>
    <div class="newscon">
            <div class="cell">
				<table width="90%" style="border:solid #add9c0; border-width:1px 0px 0px 1px">
					
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">姓名：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${safetyExperts.safetyName}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">电子邮箱：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${safetyExperts.email}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">性别：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><cus:hxlabel codeName="性别" itemValue="${safetyExperts.sex}" /></td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">出生年月：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><fmt:formatDate type="date" value="${safetyExperts.birth}"  pattern="yyyy-MM"/></td>
						</tr>
				        <tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">所学专业：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${safetyExperts.professional}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">学历：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><cus:hxlabel codeName="学历" itemValue="${safetyExperts.education}" /></td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">毕业时间：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><fmt:formatDate type="date" value="${safetyExperts.graduationTime}" pattern="yyyy-MM-dd"/></td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">职称：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${safetyExperts.jobTitle}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">工作单位：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${safetyExperts.employer}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">专长：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${safetyExperts.specialty}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">住址：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${safetyExperts.address}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">联系电话：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${safetyExperts.mobile}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">工作记录：</td>
							<td colspan="3" style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${safetyExperts.workRecord}</td>
							
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">教育情况：</td>
							<td colspan="3" style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${safetyExperts.educationSec}</td>
							
						</tr>
				</table>
					</div>
            <p>${safCul.infoContent}</p>              
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



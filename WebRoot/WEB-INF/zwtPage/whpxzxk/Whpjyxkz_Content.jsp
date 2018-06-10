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
	<div class="location"><p>您现在的位置：<a href="${ctx}/wzxx/index.action">首页</a>&gt;<a href="${ctx}/wzxx/whpjyxkzList.action">危化品经营许可证查询</a>&gt;<a href="#">危化品经营许可证详情</a></p></div>
    <div class="newscon">
            <div class="cell">
				<table width="90%" style="border:solid #add9c0; border-width:1px 0px 0px 1px">
					
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">所在区域：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><cus:hxlabel codeName="企业属地" itemValue="${cheManLic.areaId}" /></td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">企业名称：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.companyName}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">经营方式：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><cus:hxlabel codeName="危险化学品经营方式" itemValue="${cheManLic.operateWay}" /></td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">经营类型：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><cus:hxlabel codeName="危险化学品经营类型" itemValue="${cheManLic.operateType}" /></td>
						</tr>
				        <tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">材料接收日期：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><fmt:formatDate type="date" value="${cheManLic.receiveDate}" pattern="yyyy-MM-dd"/></td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">受理材料日期：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><fmt:formatDate type="date" value="${cheManLic.dealDate}" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">许可证有效期：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><fmt:formatDate type="date" value="${cheManLic.licenseValid}" pattern="yyyy-MM-dd"/></td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">发证单位：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.lssuingUnit}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">材料接收人员：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.receivePerson}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">材料审查人员：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.checkPerson}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">档案编号：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.archivesNo}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">本次领证情况：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.licenseCondition}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">申请材料是否齐全：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><cus:hxlabel codeName="是或否" itemValue="${cheManLic.applyCondition}" /></td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">签字领导：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.signLeader}</td>
							
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">街道预审意见：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.streetComment}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">现场核查部门：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.checkDepart}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">核查结论：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.checkConclusion}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">是否储存涉及：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><cus:hxlabel codeName="是或否" itemValue="${cheManLic.storageCondition}" /></td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">材料审查情况：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.materialCondition}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">行政许可建议：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.adminSuggest}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">评价机构：</td>
							<td colspan="3" style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${cheManLic.ratingAgencies}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">会审记录：</td>
							<td colspan="3" style="border:solid #add9c0; border-width:0px 1px 1px 0px;">
							<table>
							<c:forEach var="item" items="${picList}">
				            <tr>
					        <td><a href="javascript:downloadFile('${item.id}');">${item.fileName}</a></td>
				            </tr>
			                </c:forEach>
							</td>
							</table>
						</tr>
						
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">危险化学品经营许可证申请：</td>
							<td colspan="3" style="border:solid #add9c0; border-width:0px 1px 1px 0px;">
							<table>
							<c:forEach var="item" items="${picList2}">
				            <tr>
					        <td><a href="javascript:downloadFile('${item.id}');">${item.fileName}</a></td>
				            </tr>
			                </c:forEach>
							</td>
							</table>
						</tr>
						
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">危险化学品经营许可证申请材料：</td>
							<td colspan="3" style="border:solid #add9c0; border-width:0px 1px 1px 0px;">
							<table>
							<c:forEach var="item" items="${picList3}">
				            <tr>
					        <td><a href="javascript:downloadFile('${item.id}');">${item.fileName}</a></td>
				            </tr>
			                </c:forEach>
							</td>
							</table>
						</tr>
						
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">带有存储设施经营危险化学品企业（包括加油站），应提供具有法定资质的安全评价机构出具的安全评价报告及安全评价报告备案证明：</td>
							<td colspan="3" style="border:solid #add9c0; border-width:0px 1px 1px 0px;">
							<table>
							<c:forEach var="item" items="${picList4}">
				            <tr>
					        <td><a href="javascript:downloadFile('${item.id}');">${item.fileName}</a></td>
				            </tr>
			                </c:forEach>
							</td>
							</table>
						</tr>
						
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">行政审批文件：</td>
							<td colspan="3" style="border:solid #add9c0; border-width:0px 1px 1px 0px;">
							<table>
							<c:forEach var="item" items="${picList5}">
				            <tr>
					        <td><a href="javascript:downloadFile('${item.id}');">${item.fileName}</a></td>
				            </tr>
			                </c:forEach>
							</td>
							</table>
						</tr>
						
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">危险化学品经营许可证副本：</td>
							<td colspan="3" style="border:solid #add9c0; border-width:0px 1px 1px 0px;">
							<table>
							<c:forEach var="item" items="${picList6}">
				            <tr>
					        <td><a href="javascript:downloadFile('${item.id}');">${item.fileName}</a></td>
				            </tr>
			                </c:forEach>
							</td>
							</table>
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



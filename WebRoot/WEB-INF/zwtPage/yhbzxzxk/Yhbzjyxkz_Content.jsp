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
	<div class="location"><p>您现在的位置：<a href="${ctx}/wzxx/index.action">首页</a>&gt;<a href="${ctx}/wzxx/yhbzjyxkzList.action">烟花爆竹经营许可证查询</a>&gt;<a href="#">烟花爆竹经营许可证详情</a></p></div>
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
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">档案号：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.itemNo}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">局审会意见：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.agencyComment}</td>
						</tr>
				        <tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">材料审查人员：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.checkPerson}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">材料接收人员：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.receivePerson}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">材料接收日期：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><fmt:formatDate type="date" value="${firLic.receiveDate}" pattern="yyyy-MM-dd"/></td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">受理材料日期：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><fmt:formatDate type="date" value="${firLic.dealDate}" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">许可证有效期：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><fmt:formatDate type="date" value="${firLic.licenseValid}" pattern="yyyy-MM-dd"/></td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">发证单位：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.lssuingUnit}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">本次领证情况：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.licenseCondition}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">申请材料是否齐全：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;"><cus:hxlabel codeName="是或否" itemValue="${firLic.applyCondition}" /></td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">签字领导：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.signLeader}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">预审意见：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.preComment}</td>
							
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">现场检查部门：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.checkDepart}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">核查结论：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.checkConclusion}</td>
						</tr>
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">材料审查情况：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.materialCondition}</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">行政许可建议：</td>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;">${firLic.adminSuggest}</td>
						</tr>
						
						<tr>
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">烟花爆竹经营许可证申请书：</td>
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
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">烟花爆竹经营许可证申请材料：</td>
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
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">行政审批文件：</td>
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
							<td style="border:solid #add9c0; border-width:0px 1px 1px 0px;width:15%">烟花爆竹经营许可证扫描件：</td>
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



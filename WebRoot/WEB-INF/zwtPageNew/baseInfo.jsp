<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>企业基本信息列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8" />
    <script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/windowOnMove.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.css" media="screen" />
	<link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	
	<script>
	//图片放大
	$(document).ready(function() {
		$("a[rel=example_group]").fancybox({
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'titlePosition' 	: 'over'
		});

	});
	
	//下载附件
	function downloadFile(attachId){
        location.href = "/yqzhaj/jsp/photoPic/saveFile.action?fileId="+attachId;
	}
	</script>
</head>
<body>
<div class="baseCell">                         
                    <table width="100%">
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%">${entBaseInfo.enterpriseName}</td>
					<th width="15%">工商注册号</th>
					<td width="35%">${entBaseInfo.registrationNumber}</td>
				</tr>
				<tr>
					<th width="15%">组织机构代码</th>
					<td width="35%">${entBaseInfo.enterpriseCode}</td>
					<th width="15%">注册地址</th>
					<td width="35%">${entBaseInfo.enterpriseAddress}</td>
				</tr>
				<tr>
					<th width="15%">生产经营地址</th>
					<td width="35%">${entBaseInfo.factoryAddress}</td>
					<th width="15%">企业属地</th>
					<td width="35%"><cus:hxlabel codeName="企业属地" itemValue="${entBaseInfo.enterprisePossession}" /></td>
				</tr>
				<tr>	
					<th width="15%">邮政编码</th>
					<td width="35%">${entBaseInfo.enterpriseZipcode}</td>
					<th width="15%">企业性质</th>
					<td width="35%"><cus:hxlabel codeName="企业性质" itemValue="${entBaseInfo.enterpriseNature}" /></td>
				</tr>
				<tr>	
					<th width="15%">企业规模</th>
					<td width="35%"><cus:hxlabel codeName="企业规模" itemValue="${entBaseInfo.enterpriseScale}" /></td>	
					<th width="15%">企业分类</th>
					<td width="35%"><cus:hxmulselectlabel codeName="企业分类" itemValue="${entBaseInfo.enterpriseType}" /></td>	
				</tr>
				<tr>
					<th width="15%">行业类别</th>
					<td width="35%"><cus:hxlabel codeName="行业类别" itemValue="${entBaseInfo.enterpriseCategory}" /></td>	
					<th width="15%">投资方国籍</th>
					<td width="35%">${entBaseInfo.enterpriseNationnality}</td>	
				</tr>
				<tr>
					<th width="15%">法人代表姓名</th>
					<td width="35%">${entBaseInfo.enterpriseLegalName}</td>
					<th width="15%">法人代表性别</th>
					<td width="35%"><cus:hxlabel codeName="性别" itemValue="${entBaseInfo.enterpriseLegalSex}" /></td>	
				</tr>
				<tr>
					<th width="15%">法人代表年龄</th>
					<td width="35%">${entBaseInfo.enterpriseLegalAge}</td>
					<th width="15%">法人代表证件号码</th>
					<td width="35%">${entBaseInfo.enterpriseLegalCardnum}</td>
				</tr>
				<tr>
					<th width="15%">法人代表电子邮箱</th>
					<td width="35%">${entBaseInfo.enterpriseLegalEmail}</td>
					<th width="15%">法人代表职务</th>
					<td width="35%">${entBaseInfo.enterpriseLegalZw}</td>
				</tr>
				<tr>
					<th width="15%">企业设立日期</th>
					<td width="35%"><fmt:formatDate type="date" value="${entBaseInfo.enterpriseFoundDate}" /></td>
					<th width="15%">企业投产日期</th>
					<td width="35%"><fmt:formatDate type="date" value="${entBaseInfo.enterpriseProductDate}" /></td>
				</tr>
				<tr>
					<th width="15%">注册资本</th>
					<td width="35%">${entBaseInfo.enterpriseRegisterMoney}<cus:hxlabel codeName="货币种类" itemValue="${entBaseInfo.enterpriseRegisterMoneyDw}" /></td>
					<th width="15%">投资总额</th>
					<td width="35%">${entBaseInfo.enterpriseInvestMoney}<cus:hxlabel codeName="货币种类" itemValue="${entBaseInfo.enterpriseInvestMoneyDw}" /></td>
				</tr>
				<tr>
					<th width="15%">固定资产总额</th>
					<td width="35%">${entBaseInfo.enterpriseFixedassetMoney}<cus:hxlabel codeName="货币种类" itemValue="${entBaseInfo.enterpriseFixedassetMoneyDw}" /></td>
					<th width="15%">占地面积（m2）</th>
					<td width="35%">${entBaseInfo.enterpriseFloorArea}</td>
				</tr>
				<tr>
					<th width="15%">办公楼建筑面积（m2）</th>
					<td width="35%">${entBaseInfo.enterpriseOfficeArea}</td>
					<th width="15%">车间厂房建筑面积（m2）</th>
					<td width="35%">${entBaseInfo.enterpriseWorkshopArea}</td>
				</tr>
				<tr>
					<th width="15%">仓库建筑面积（m2）</th>
					<td width="35%">${entBaseInfo.enterpriseWearhouseArea}</td>
					<th width="15%">厂房归属</th>
					<td width="35%"><cus:hxlabel codeName="厂房归属" itemValue="${entBaseInfo.enterprisWorkshopOwn}" /></td>
				</tr>
				<tr>
					<th width="15%">员工总数</th>
					<td width="35%">${entBaseInfo.enterpriseStaffCount}</td>
					<th width="15%">管理人员数</th>
					<td width="35%">${entBaseInfo.enterpriseManagerCount}</td>
				</tr>
				<tr>
					<th width="15%">工人数</th>
					<td width="35%">${entBaseInfo.enterpriseWorkerCount}</td>
					<th width="15%">所属网格</th>
					<td width="35%" >${entBaseInfo.gridName}</td>
				</tr>
				<tr>
					<th width="15%">网格管理中队</th>
					<td width="35%">${entBaseInfo.gridManageteamName}</td>
					<th width="15%">网格管理人员</th>
					<td width="35%" >${comGriMan.gridManagePersonName}</td>
				</tr>
				<tr>
					<th width="15%">经营范围</th>
					<td width="85%" colspan="3">
						${entBaseInfo.enterpriseScope}
					</td>
				</tr>
				<tr>
					<th width="15%">厂区平面图</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
							<table>
							  	<c:forEach var="item" items="${picList}">
									<tr id='${item.id}' style="text-align: center">
								  		<td width="70%">
								   			<c:choose>
												<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
														<a href="${item.httpUrl}/upload/photo/${item.picName}" rel="example_group">	
															<img src="${item.httpUrl}/upload/photo/${item.picName}"
															border='0' width='220' height='150'/>
														</a>
												</c:when> 
												<c:otherwise> 
													&nbsp;&nbsp;&nbsp;${item.fileName}
												</c:otherwise>
											</c:choose>
								   		</td>
								   		<td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;</td>
									</tr>
							  	</c:forEach>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">审核记录</th>
					<td width="85%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
			</table>
</div>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	 <script type="text/javascript">
		 $(function(){
			//window.frames["map"].LocateComPany(JSON.stringify("${entBaseInfo.enterpriseCode}","企业"));	//调用展示企业打点方法  ${codeId}
		});
	</script>
	<style type="text/css">
		fieldset{
			    width: 96%;
			    margin: 0 auto;
			    border: 2px solid #17B3E1;
			    border-radius: 5px;
			    padding: 0 10;
		}
		legend{
		    margin-left: 50px;
		    line-height: 40px;
		    font-size: 18px;
		    font-style: italic;
		    color: black;
		    text-align: center;
		}
		h2{
			text-align: center;
    		color: red;
		}
	</style>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
			<div class="inner6px">
				<h2>${entBaseInfo.enterpriseName}</h2>
				<div class="cell" style="width: ${tableWidth}">
				<fieldset>
					<legend>&nbsp;企业基础信息&nbsp;</legend>
					<table width="100%">
						<tr>
							<th width="15%">企业名称</th>
							<td width="35%">${entBaseInfo.enterpriseName}</td>
							<th width="15%">工商注册号</th>
							<td width="35%">${entBaseInfo.registrationNumber}</td>
						</tr>
						<tr>
							<th width="15%">组织机构代码/统一社会信用编码</th>
							<td width="35%">${entBaseInfo.enterpriseCode}</td>
							<th width="15%">企业注册类型</th>
							<td width="35%"><cus:hxlabel codeName="企业注册类型" itemValue="${entBaseInfo.qyzclx}" /></td>
						</tr>
						<tr>
							
							<th width="15%">注册地址</th>
							<td width="75%">${entBaseInfo.enterpriseAddress}</td>
						</tr>
						<tr>
							<th width="15%">生产经营地址</th>
							<td width="75%">${entBaseInfo.factoryAddress}</td>
							
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
							<td width="35%"><cus:hxlabel codeName="行业类别" itemValue="${entBaseInfo.enterpriseCategory}" />/${entBaseInfo.jjlxname}</td>	
							<th width="15%">企业属地</th>
							<td width="35%">${entBaseInfo.enterprisePossessionName}</td>
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
							<th width="15%">法人代表联系电话</th>
							<td width="35%">${entBaseInfo.enterpriseLegalPhone}</td>
						</tr>
						<tr>	
							<th width="15%">法人代表证件号码</th>
							<td width="35%">${entBaseInfo.enterpriseLegalCardnum}</td>
							<th width="15%">法人代表电子邮箱</th>
							<td width="35%">${entBaseInfo.enterpriseLegalEmail}</td>
						</tr>
						<tr>
							<th width="15%">法人代表职务</th>
							<td width="35%">${entBaseInfo.enterpriseLegalZw}</td>
							<th width="15%">企业设立日期</th>
							<td width="35%"><fmt:formatDate type="date" value="${entBaseInfo.enterpriseFoundDate}" /></td>
						</tr>
						<tr>
							<th width="15%">企业投产日期</th>
							<td width="35%"><fmt:formatDate type="date" value="${entBaseInfo.enterpriseProductDate}" /></td>
							<th width="15%">注册资本</th>
							<td width="35%">${entBaseInfo.enterpriseRegisterMoney}<cus:hxlabel codeName="货币种类" itemValue="${entBaseInfo.enterpriseRegisterMoneyDw}" /></td>
						</tr>
						<tr>
							<th width="15%">投资总额</th>
							<td width="35%">${entBaseInfo.enterpriseInvestMoney}<cus:hxlabel codeName="货币种类" itemValue="${entBaseInfo.enterpriseInvestMoneyDw}" /></td>
							<th width="15%">固定资产总额</th>
							<td width="35%">${entBaseInfo.enterpriseFixedassetMoney}<cus:hxlabel codeName="货币种类" itemValue="${entBaseInfo.enterpriseFixedassetMoneyDw}" /></td>
						</tr>
						<tr>
							<th width="15%">占地面积（m2）</th>
							<td width="35%">${entBaseInfo.enterpriseFloorArea}</td>
							<th width="15%">办公楼建筑面积（m2）</th>
							<td width="35%">${entBaseInfo.enterpriseOfficeArea}</td>
						</tr>
						<tr>
							<th width="15%">车间厂房建筑面积（m2）</th>
							<td width="35%">${entBaseInfo.enterpriseWorkshopArea}</td>
							<th width="15%">仓库建筑面积（m2）</th>
							<td width="35%">${entBaseInfo.enterpriseWearhouseArea}</td>
						</tr>
						<tr>
							<th width="15%">厂房归属</th>
							<td width="35%"><cus:hxlabel codeName="厂房归属" itemValue="${entBaseInfo.enterprisWorkshopOwn}" /></td>
							<th width="15%">员工总数</th>
							<td width="35%">${entBaseInfo.enterpriseStaffCount}</td>
						</tr>
						<tr>
							<th width="15%">管理人员数</th>
							<td width="35%">${entBaseInfo.enterpriseManagerCount}</td>
							<th width="15%">工人数</th>
							<td width="35%">${entBaseInfo.enterpriseWorkerCount}</td>
						</tr>
						<tr>
							<th width="15%">所属网格</th>
							<td width="35%" >${entBaseInfo.gridName}</td>
							<th width="15%">网格管理中队</th>
							<td width="35%">${entBaseInfo.gridManageteamName}</td>
						</tr>
						<tr>
							<th width="15%">网格管理人员</th>
							<td width="35%" >${comGriMan.gridManagePersonName}</td>
						</tr>
						<tr>
							<th width="15%">经营范围</th>
							<td width="85%" colspan="3">
								<textarea id="entBaseInfo.enterpriseScope" name="entBaseInfo.enterpriseScope" style="width:96%;height:120px" readonly="readonly">${entBaseInfo.enterpriseScope}</textarea>
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
						<%-- <tr>
							<th width="15%">地图</th>
							<td width="85%" colspan="3">
								<div style="width:100%;">
						        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index.html"  style="height:1000px;width:100%; border:hidden; "scrolling="no" ></iframe>
		
						    	</div>
							</td>
						</tr> --%>
						<!-- <tr>
							<td colspan="4" height="100px" style="text-align:center">
							    <a href="#" class="btn_01" onclick="closeLayer();">关闭<b></b></a>
							</td>
						</tr> -->
					</table>
				</fieldset>
				
				</div>
			</div>
		</div>
	</form>
</body>
</html>

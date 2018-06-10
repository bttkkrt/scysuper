<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="entBaseInfoCheckSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="entBaseInfo.id" value="${entBaseInfo.id}">
		<input type="hidden" name="checkRecord.infoId" value="${entBaseInfo.id}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%">${entBaseInfo.enterpriseName}</td>
					<th width="15%">工商注册号</th>
					<td width="35%">${entBaseInfo.registrationNumber}</td>
				</tr>
				<tr>
					<th width="15%">企业注册类型</th>
					<td width="35%"><cus:hxlabel codeName="企业注册类型" itemValue="${entBaseInfo.qyzclx}" /></td>
					<th width="15%">组织机构代码/统一社会信用编码</th>
					<td width="35%">${entBaseInfo.enterpriseCode}</td>
				</tr>
				<tr>
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
					<td width="35%"><cus:hxlabel codeName="行业类别" itemValue="${entBaseInfo.enterpriseCategory}" />/${entBaseInfo.jjlxname}</td>	
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
					<td width="35%">
					<s:if test='entBaseInfo.grid != null && entBaseInfo.grid != ""'>
						${entBaseInfo.gridName}
					</s:if>
					<s:else>
						<cus:SelectOneTag property="entBaseInfo.grid" defaultText='请选择' codeSql="select t.row_id,t.GRID_NAME from COM_GRI_MAN t where t.delflag = 0" datatype="*1-127" errormsg='所属网格必须是1到127位字符！' nullmsg='所属网格不能为空！' sucmsg='所属网格填写正确！'  maxlength="127"/><font style='color:red'>*</font>
					</s:else>
					</td>
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
				<s:if test='entBaseInfo.grid != null && entBaseInfo.grid != ""'>
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				<tr>
					<th width="15%">审核结果</th>
					<td width="96%" colspan="3">
					<s:select name="checkRecord.checkResult" list="#{'审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
					</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="96%" colspan="3"><textarea name="checkRecord.checkRemark" style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)"></textarea></td>
				</tr>
				</s:if>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_entBaseInfo');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

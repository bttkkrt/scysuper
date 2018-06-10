<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		  uploadPicOnly("uploadify","${entBaseInfo.linkId}","qyxx","cqtp","fileQueue");
		});
		
	$(function(){
		var obj = document.getElementById("enterprisWorkshopOwn").value;
		if(obj == 2)
		{
			$('.clsdiv').each(function(){
     			$(this).removeData("dataIgnore");
			});
		}
		else
		{
			$('.clsdiv').each(function(){
     			$(this).data("dataIgnore","dataIgnore");
			});
		}
	});
		
	function changeOwn(obj)
	{
		if(obj == 2)
		{
			document.getElementById('ownerdiv').style.display = "";
			$('.clsdiv').each(function(){
     			$(this).removeData("dataIgnore");
			});
		}
		else
		{
			document.getElementById('ownerdiv').style.display = "none";
			$('.clsdiv').each(function(){
     			$(this).data("dataIgnore","dataIgnore");
			});
		}
	}
	
	function clearNoNum(event,obj){ 
        //响应鼠标事件，允许左右方向键移动 
        event = window.event||event; 
        if(event.keyCode == 37 | event.keyCode == 39){ 
            return; 
        } 
        //先把非数字的都替换掉，除了数字和. 
        obj.value = obj.value.replace(/[^\d.]/g,""); 
        //必须保证第一个为数字而不是. 
        obj.value = obj.value.replace(/^\./g,""); 
        //保证只有出现一个.而没有多个. 
        obj.value = obj.value.replace(/\.{2,}/g,"."); 
        //保证.只出现一次，而不能出现两次以上 
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
        if(obj.value.length >= 2 && obj.value.substring(0,1) == "0" && obj.value.substring(1,2) != ".")
        {
       		obj.value = obj.value.substring(1,obj.value.length);
       	}
    } 
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="entBaseInfoSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="entBaseInfo.id" value="${entBaseInfo.id}">
		<input type="hidden" name="entBaseInfo.createTime" value="<fmt:formatDate type="both" value="${entBaseInfo.createTime}" />">
		<input type="hidden" name="entBaseInfo.updateTime" value="${entBaseInfo.updateTime}">
		<input type="hidden" name="entBaseInfo.createUserID" value="${entBaseInfo.createUserID}">
		<input type="hidden" name="entBaseInfo.updateUserID" value="${entBaseInfo.updateUserID}">
		<input type="hidden" name="entBaseInfo.deptId" value="${entBaseInfo.deptId}">
		<input type="hidden" name="entBaseInfo.delFlag" value="${entBaseInfo.delFlag}">
		
		<input type="hidden" name="entBaseInfo.loginId" value="${entBaseInfo.loginId}">
		<input type="hidden" name="entBaseInfo.password" value="${entBaseInfo.password}">
		<input type="hidden" name="entBaseInfo.gridManageteamCode" value="${entBaseInfo.gridManageteamCode}">
		<input type="hidden" name="entBaseInfo.gridManageteamName" value="${entBaseInfo.gridManageteamName}">
		<input type="hidden" name="entBaseInfo.gridManageId" value="${entBaseInfo.gridManageId}">
		<input type="hidden" name="entBaseInfo.gridManageName" value="${entBaseInfo.gridManageName}">
		<input type="hidden" name="entBaseInfo.grid" value="${entBaseInfo.grid}">
		<input type="hidden" name="entBaseInfo.gridName" value="${entBaseInfo.gridName}">
		<input type="hidden" name="entBaseInfo.linkId" value="${entBaseInfo.linkId}">
		<input type="hidden" name="entBaseInfo.remark" value="${entBaseInfo.remark}">
		<input type="hidden" name="entBaseInfo.basePass" value="${entBaseInfo.basePass}">
		<input type="hidden" id="mapKey" name="comGriMan.mapKey" value="${comGriMan.mapKey}">
		<input type="hidden" name="entBaseInfo.ifCz" value="${entBaseInfo.ifCz}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="entBaseInfo.enterpriseName" value="${entBaseInfo.enterpriseName}" type="text" datatype="*1-127" errormsg='企业名称必须是1到127位字符！' nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'  maxlength="127" style="width:50%"><font style='color:red'>*</font></td>
					<th width="15%">工商注册号</th>
					<td width="35%"><input name="entBaseInfo.registrationNumber" value="${entBaseInfo.registrationNumber}" type="text" datatype="*1-127" errormsg='工商注册号必须是1到127位字符！' nullmsg='工商注册号不能为空！' sucmsg='工商注册号填写正确！'  maxlength="127" style="width:50%"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">组织机构代码</th>
					<td width="35%"><input name="entBaseInfo.enterpriseCode" value="${entBaseInfo.enterpriseCode}" type="text" datatype="*1-127" errormsg='组织机构代码必须是1到127位字符！' nullmsg='组织机构代码不能为空！' sucmsg='组织机构代码填写正确！'  maxlength="127" style="width:50%"><font style='color:red'>*</font></td>
					<th width="15%">注册地址</th>
					<td width="35%"><input name="entBaseInfo.enterpriseAddress" value="${entBaseInfo.enterpriseAddress}" type="text" datatype="*1-127" errormsg='注册地址必须是1到127位字符！' nullmsg='注册地址不能为空！' sucmsg='注册地址填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">生产经营地址</th>
					<td width="35%"><input name="entBaseInfo.factoryAddress" value="${entBaseInfo.factoryAddress}" type="text" datatype="*1-127" errormsg='生产经营地址必须是1到127位字符！' nullmsg='生产经营地址不能为空！' sucmsg='生产经营地址填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
					<th width="15%">企业属地</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterprisePossession" defaultText='请选择' codeName="企业属地" value="${entBaseInfo.enterprisePossession}" datatype="*1-127" errormsg='企业属地必须是1到127位字符！' nullmsg='企业属地不能为空！' sucmsg='企业属地填写正确！'  maxlength="127" style="width:50%" ignore="ignore"/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">邮政编码</th>
					<td width="35%"><input name="entBaseInfo.enterpriseZipcode" value="${entBaseInfo.enterpriseZipcode}" type="text" datatype="p" errormsg='邮政编码格式错误！' nullmsg='邮政编码不能为空！' sucmsg='邮政编码填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
					<th width="15%">企业性质</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseNature" defaultText='请选择' codeName="企业性质" value="${entBaseInfo.enterpriseNature}" datatype="*1-127" errormsg='企业性质必须是1到127位字符！' nullmsg='企业性质不能为空！' sucmsg='企业性质填写正确！'  maxlength="127" style="width:50%" ignore="ignore"/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">企业分类</th>
					<td width="85%" colspan="3"><cus:hxcheckbox property="entBaseInfo.enterpriseType" codeName="企业分类" value="${entBaseInfo.enterpriseType}" datatype="*1-127" errormsg='企业分类必须是1到127位字符！' nullmsg='企业分类不能为空！' sucmsg='企业分类填写正确！'  maxlength="127" ignore="ignore"/><font style='color:red'>*（可多选）</font></td>	
				</tr>
				<tr>
					<th width="15%">企业规模(<a href="${ctx}/qygm.docx" style="text-decoration:underline;color:red">查看定义</a>)</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseScale" defaultText='请选择' codeName="企业规模" value="${entBaseInfo.enterpriseScale}" datatype="*1-127" errormsg='企业规模必须是1到127位字符！' nullmsg='企业规模不能为空！' sucmsg='企业规模填写正确！'  maxlength="127" style="width:50%" ignore="ignore"/><font style='color:red'>*</font></td>	
					<th width="15%">行业类别(<a href="${ctx}/hyfl.xlsx" style="text-decoration:underline;color:red">查看定义</a>)</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseCategory" defaultText='请选择' codeName="行业类别" value="${entBaseInfo.enterpriseCategory}" datatype="*1-127" errormsg='行业类别必须是1到127位字符！' nullmsg='行业类别不能为空！' sucmsg='行业类别填写正确！'  maxlength="127" style="width:50%" ignore="ignore"/><font style='color:red'>*</font></td>	
				</tr>
				<tr>
					<th width="15%">投资方国籍</th>
					<td width="35%"><input name="entBaseInfo.enterpriseNationnality" value="${entBaseInfo.enterpriseNationnality}" type="text" datatype="*1-127" errormsg='投资方国籍必须是1到127位字符！' nullmsg='投资方国籍不能为空！' sucmsg='投资方国籍填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
					<th width="15%">法人代表姓名</th>
					<td width="35%"><input name="entBaseInfo.enterpriseLegalName" value="${entBaseInfo.enterpriseLegalName}" type="text" datatype="*1-127" errormsg='法人姓名必须是1到127位字符！' nullmsg='法人姓名不能为空！' sucmsg='法人姓名填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">法人代表性别</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseLegalSex" defaultText='请选择' codeName="性别" value="${entBaseInfo.enterpriseLegalSex}" datatype="*1-127" errormsg='法人性别必须是1到127位字符！' nullmsg='法人性别不能为空！' sucmsg='法人性别填写正确！'  maxlength="127" style="width:50%" ignore="ignore"/><font style='color:red'>*</font></td>
					<th width="15%">法人代表年龄</th>
					<td width="35%"><input name="entBaseInfo.enterpriseLegalAge" value="${entBaseInfo.enterpriseLegalAge}" type="text" datatype="n1-127" errormsg='法人年龄必须是1到127位字符！' nullmsg='法人年龄不能为空！' sucmsg='法人年龄填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">法人代表联系电话</th>
					<td width="35%"><input name="entBaseInfo.enterpriseLegalPhone" value="${entBaseInfo.enterpriseLegalPhone}" type="text" datatype="*1-127" errormsg='法人联系电话格式错误！' nullmsg='法人联系电话不能为空！' sucmsg='法人联系电话填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
					<th width="15%">法人代表证件号码</th>
					<td width="35%"><input name="entBaseInfo.enterpriseLegalCardnum" value="${entBaseInfo.enterpriseLegalCardnum}" type="text" datatype="*1-127" errormsg='法人证件号码必须是1到127位字符！' nullmsg='法人证件号码不能为空！' sucmsg='法人证件号码填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">法人代表电子邮箱</th>
					<td width="35%"><input name="entBaseInfo.enterpriseLegalEmail" value="${entBaseInfo.enterpriseLegalEmail}" type="text" datatype="e" errormsg='法人电子邮箱格式错误！' nullmsg='法人电子邮箱不能为空！' sucmsg='法人电子邮箱填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
					<th width="15%">法人代表职务</th>
					<td width="35%"><input name="entBaseInfo.enterpriseLegalZw" value="${entBaseInfo.enterpriseLegalZw}" type="text" datatype="*1-127" errormsg='法人职务必须是1到127位字符！' nullmsg='法人职务不能为空！' sucmsg='法人职务填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">企业设立日期</th>
					<td width="35%"><input name="entBaseInfo.enterpriseFoundDate" value="<fmt:formatDate type='date' value='${entBaseInfo.enterpriseFoundDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127" errormsg='企业设立日期必须是1到127位字符！' nullmsg='企业设立日期不能为空！' sucmsg='企业设立日期填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
					<th width="15%">企业投产日期</th>
					<td width="35%"><input name="entBaseInfo.enterpriseProductDate" value="<fmt:formatDate type='date' value='${entBaseInfo.enterpriseProductDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127" errormsg='企业投产日期必须是1到127位字符！' nullmsg='企业投产日期不能为空！' sucmsg='企业投产日期填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">注册资本</th>
					<td width="35%">
						<input name="entBaseInfo.enterpriseRegisterMoney" value="${entBaseInfo.enterpriseRegisterMoney}" type="text" datatype="*1-127" errormsg='注册资本必须是1到127位字符！' nullmsg='注册资本不能为空！' sucmsg='注册资本填写正确！'  maxlength="127" style="width:30%" onKeyUp="clearNoNum(event,this)" ignore="ignore">
						<cus:SelectOneTag property="entBaseInfo.enterpriseRegisterMoneyDw" defaultText='请选择' codeName="货币种类" value="${entBaseInfo.enterpriseRegisterMoneyDw}" datatype="*1-127" errormsg='注册资本单位必须是1到127位字符！' nullmsg='注册资本单位不能为空！' sucmsg='注册资本单位填写正确！'  maxlength="127" style="width:20%"/>
						<font style='color:red'>*</font>
					</td>
					<th width="15%">投资总额</th>
					<td width="35%">
						<input name="entBaseInfo.enterpriseInvestMoney" value="${entBaseInfo.enterpriseInvestMoney}" type="text" datatype="*1-127" errormsg='投资总额必须是1到127位字符！' nullmsg='投资总额不能为空！' sucmsg='投资总额填写正确！'  maxlength="127" style="width:30%" onKeyUp="clearNoNum(event,this)" ignore="ignore">
						<cus:SelectOneTag property="entBaseInfo.enterpriseInvestMoneyDw" defaultText='请选择' codeName="货币种类" value="${entBaseInfo.enterpriseInvestMoneyDw}" datatype="*1-127" errormsg='投资总额必须是1到127位字符！' nullmsg='投资总额单位不能为空！' sucmsg='投资总额单位填写正确！'  maxlength="127" style="width:20%"/>
						<font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">固定资产总额</th>
					<td width="35%">
						<input name="entBaseInfo.enterpriseFixedassetMoney" value="${entBaseInfo.enterpriseFixedassetMoney}" type="text" datatype="*1-127" errormsg='固定资产总额必须是1到127位字符！' nullmsg='固定资产总额不能为空！' sucmsg='固定资产总额填写正确！'  maxlength="127" style="width:30%" onKeyUp="clearNoNum(event,this)" ignore="ignore">
						<cus:SelectOneTag property="entBaseInfo.enterpriseFixedassetMoneyDw" defaultText='请选择' codeName="货币种类" value="${entBaseInfo.enterpriseFixedassetMoneyDw}" datatype="*1-127" errormsg='固定资产总额单位必须是1到127位字符！' nullmsg='固定资产总额单位不能为空！' sucmsg='固定资产总额单位填写正确！'  maxlength="127" style="width:20%"/>
						<font style='color:red'>*</font>
					</td>
					<th width="15%">占地面积（m2）</th>
					<td width="35%"><input name="entBaseInfo.enterpriseFloorArea" value="${entBaseInfo.enterpriseFloorArea}" type="text" datatype="*1-127" errormsg='占地面积必须是1到127位字符！' nullmsg='占地面积不能为空！' sucmsg='占地面积填写正确！'  maxlength="127" style="width:50%" onKeyUp="clearNoNum(event,this)" ignore="ignore"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">办公楼建筑面积（m2）</th>
					<td width="35%"><input name="entBaseInfo.enterpriseOfficeArea" value="${entBaseInfo.enterpriseOfficeArea}" type="text" datatype="*1-127" errormsg='办公楼面积必须是1到127位字符！' nullmsg='办公楼面积不能为空！' sucmsg='办公楼面积填写正确！'  maxlength="127" style="width:50%" onKeyUp="clearNoNum(event,this)" ignore="ignore"><font style='color:red'>*</font></td>
					<th width="15%">车间厂房建筑面积（m2）</th>
					<td width="35%"><input name="entBaseInfo.enterpriseWorkshopArea" value="${entBaseInfo.enterpriseWorkshopArea}" type="text" datatype="*1-127" errormsg='车间厂房面积必须是1到127位字符！' nullmsg='车间厂房面积不能为空！' sucmsg='车间厂房面积填写正确！'  maxlength="127" style="width:50%" onKeyUp="clearNoNum(event,this)" ignore="ignore"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">仓库建筑面积（m2）</th>
					<td width="35%"><input name="entBaseInfo.enterpriseWearhouseArea" value="${entBaseInfo.enterpriseWearhouseArea}" type="text" datatype="*1-127" errormsg='仓库面积必须是1到127位字符！' nullmsg='仓库面积不能为空！' sucmsg='仓库面积填写正确！'  maxlength="127" style="width:50%" onKeyUp="clearNoNum(event,this)" ignore="ignore"><font style='color:red'>*</font></td>
					<th width="15%">厂房归属</th>
					<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterprisWorkshopOwn" defaultText='请选择' codeName="厂房归属" value="${entBaseInfo.enterprisWorkshopOwn}" datatype="*1-127" errormsg='厂房归属必须是1到127位字符！' nullmsg='厂房归属不能为空！' sucmsg='厂房归属填写正确！'  maxlength="127" style="width:50%" onchange="changeOwn(this.value)" ignore="ignore"/><font style='color:red'>*</font></td>
				</tr>
				<tr id="ownerdiv" <s:if test="entBaseInfo.enterprisWorkshopOwn != 2">style="display:none"</s:if>>
					<th width="15%">出租方</th>
					<td width="35%"><input name="entBaseInfo.houseOwner" value="${entBaseInfo.houseOwner}" type="text" datatype="*1-127" errormsg='出租方必须是1到127位字符！' nullmsg='出租方不能为空！' sucmsg='出租方填写正确！'  maxlength="127" style="width:50%" class="clsdiv" ignore="ignore"><font style='color:red'>*</font></td>
					<th width="15%">出租方联系方式</th>
					<td width="35%"><input name="entBaseInfo.ownerTel" value="${entBaseInfo.ownerTel}" type="text" datatype="*1-127" errormsg='出租方联系方式必须是1到127位字符！' nullmsg='出租方联系方式不能为空！' sucmsg='出租方联系方式填写正确！'  maxlength="127" style="width:50%" class="clsdiv" ignore="ignore"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">管理人员数</th>
					<td width="35%"><input name="entBaseInfo.enterpriseManagerCount" value="${entBaseInfo.enterpriseManagerCount}" type="text" datatype="n1-127" errormsg='管理人员数必须是1到127位字符！' nullmsg='管理人员数不能为空！' sucmsg='管理人员数填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
					<th width="15%">工人数</th>
					<td width="35%"><input name="entBaseInfo.enterpriseWorkerCount" value="${entBaseInfo.enterpriseWorkerCount}" type="text" datatype="n1-127" errormsg='工人数必须是1到127位字符！' nullmsg='工人数不能为空！' sucmsg='工人数填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">经营范围</th>
					<td width="85%" colspan="3">
						<textarea id="entBaseInfo.enterpriseScope" name="entBaseInfo.enterpriseScope" style="width:80%;height:120px" datatype="*1-2000" errormsg='经营范围必须是1到2000位字符！' nullmsg='经营范围不能为空！' sucmsg='经营范围填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" ignore="ignore">${entBaseInfo.enterpriseScope}</textarea><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%" rowspan="2">厂区平面图</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="cqtp">
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
											||fn:endsWith(fn:toLowerCase(item.picName),'.png')
											||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
												<img src="${item.httpUrl}/upload/photo/${item.picName}"
												border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:when> 
											<c:otherwise> 
												&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:otherwise>
										</c:choose>
								   </td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								   <a href="javascript:del('${item.id}');">删除</a></td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<s:if test="entBaseInfo.basePass == 2">
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				</s:if>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

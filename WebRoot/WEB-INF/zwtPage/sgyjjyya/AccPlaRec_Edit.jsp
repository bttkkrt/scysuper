<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
		$(document).ready(function() {
			  uploadPic("uploadify","${accPlaRec.linkId}","sgyjjyya","sgyjjyyafj","fileQueue");
			  
		});
		function queryQy()
		{
			var szzid = document.getElementById('areaId').value;
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=idmcqy&entBaseInfo.enterprisePossession="+szzid, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		
        function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
        
        function changDiv(obj)
        {
        	if(obj == 1)
        	{
        		document.getElementById('div1').style.display = "";
        	}
        	else
        	{
        		document.getElementById('div1').style.display = "none";
        	}
        }
        
        function showSgInfo(obj)
        {
        	if(obj == 1)
        	{
        		document.getElementById('sgdiv1').style.display = "";
        		document.getElementById('sgdiv2').style.display = "";
        		document.getElementById('sgdiv3').style.display = "";
        	}
        	else
        	{
        		document.getElementById('sgdiv1').style.display = "none";
        		document.getElementById('sgdiv2').style.display = "none";
        		document.getElementById('sgdiv3').style.display = "none";
        	}
        }
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="accPlaRecSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="accPlaRec.id" value="${accPlaRec.id}">
		<input type="hidden" name="accPlaRec.createTime" value="<fmt:formatDate type="both" value="${accPlaRec.createTime}" />">
		<input type="hidden" name="accPlaRec.updateTime" value="${accPlaRec.updateTime}">
		<input type="hidden" name="accPlaRec.createUserID" value="${accPlaRec.createUserID}">
		<input type="hidden" name="accPlaRec.updateUserID" value="${accPlaRec.updateUserID}">
		<input type="hidden" name="accPlaRec.deptId" value="${accPlaRec.deptId}">
		<input type="hidden" name="accPlaRec.delFlag" value="${accPlaRec.delFlag}">
		<input type="hidden" name="accPlaRec.linkId" value="${accPlaRec.linkId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%"   property="accPlaRec.areaId" defaultText='请选择' codeName="企业属地" value="${accPlaRec.areaId}" onchange="clearCompany()" dataType="*1-127" nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="accPlaRec.companyName" style="width: 60%" value="${accPlaRec.companyName}" type="text" readonly="readonly" onclick="queryQy()" dataType="*1-127"  nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="accPlaRec.companyId" value="${accPlaRec.companyId}"/>
					</td> 
				</tr>
				<tr>
					<th width="15%">备案机构</th>
					<td width="35%"><input name="accPlaRec.recordAgency" style="width: 60%" value="${accPlaRec.recordAgency}" type="text" maxlength="127"></td>
					<th width="15%">备案日期</th>
					<td width="35%"><input name="accPlaRec.recordDate" style="width: 60%" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${accPlaRec.recordDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
				<th width="15%">备案编号</th>
					<td width="35%"><input name="accPlaRec.recordNum" style="width: 60%" value="${accPlaRec.recordNum}" dataType="*1-127" errormsg='备案编号必须是1到127位字符！' nullmsg='备案编号不能为空！' sucmsg='备案编号填写正确！' type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">备案内容</th>
					<td width="85%" colspan="3"><textarea name="accPlaRec.recordContent" style="width:96%;height:60px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${accPlaRec.recordContent}</textarea></td>
				</tr>
				<tr>
					<th width="10%">附件</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="sgyjjyyafj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

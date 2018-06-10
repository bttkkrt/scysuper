<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		$(document).ready(function() {
			  uploadPic("uploadify","${hazDet.linkId}","whysjc","zycswhysjcbgfj","fileQueue");
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="hazDetSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="hazDet.id" value="${hazDet.id}">
		<input type="hidden" name="hazDet.createTime" value="<fmt:formatDate type="both" value="${hazDet.createTime}" />">
		<input type="hidden" name="hazDet.updateTime" value="${hazDet.updateTime}">
		<input type="hidden" name="hazDet.createUserID" value="${hazDet.createUserID}">
		<input type="hidden" name="hazDet.updateUserID" value="${hazDet.updateUserID}">
		<input type="hidden" name="hazDet.deptId" value="${hazDet.deptId}">
		<input type="hidden" name="hazDet.delFlag" value="${hazDet.delFlag}">
		<input type="hidden" name="hazDet.linkId" value="${hazDet.linkId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">检测危害因素</th>
					<td width="35%"><input name="hazDet.detectionRiskFactors" style="width:60%" errormsg='检测危害因素必须是1到127位字符！' nullmsg='检测危害因素不能为空！' sucmsg='检测危害因素填写正确！'  datatype="*1-127" value="${hazDet.detectionRiskFactors}" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">检测点数</th>
					<td width="35%"><input name="hazDet.monitoringPoints" style="width:60%" value="${hazDet.monitoringPoints}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">不合格点点数</th>
					<td width="35%"><input name="hazDet.unqualifiedPoints" style="width:60%" value="${hazDet.unqualifiedPoints}" type="text" maxlength="127"></td>
					<th width="15%">不合格点的危害因素名称</th>
					<td width="35%"><input name="hazDet.hazardFactorName" style="width:60%" value="${hazDet.hazardFactorName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">检测机构</th>
					<td width="35%"><input name="hazDet.detectionMechanism" style="width:60%" value="${hazDet.detectionMechanism}" type="text" maxlength="127"></td>
					<th width="15%">检测日期</th>
					<td width="35%"><input name="hazDet.testDate" style="width:60%" value="<fmt:formatDate type='date' pattern="yyyy-MM-dd" value='${hazDet.testDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="10%">作业场所危害因素检测报告</th>
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
					<td width="85%" colspan="3"  style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="zycswhysjcbgfj">
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_hazDet');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

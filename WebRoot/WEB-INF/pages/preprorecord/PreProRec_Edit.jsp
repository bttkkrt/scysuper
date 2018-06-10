<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		$(document).ready(function() {
		  uploadPic("uploadify1","${preProRec.linkId}","preprorecord1","preprorecordfj1","fileQueue1");
		  uploadPic("uploadify2","${preProRec.linkId}","preprorecord2","preprorecordfj2","fileQueue2");
		  uploadPic("uploadify3","${preProRec.linkId}","preprorecord3","preprorecordfj3","fileQueue3");
		  uploadPic("uploadify4","${preProRec.linkId}","preprorecord4","preprorecordfj4","fileQueue4");
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
   
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="preProRecSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="preProRec.id" value="${preProRec.id}">
		<input type="hidden" name="preProRec.createTime" value="<fmt:formatDate type="both" value="${preProRec.createTime}" />">
		<input type="hidden" name="preProRec.updateTime" value="${preProRec.updateTime}">
		<input type="hidden" name="preProRec.createUserID" value="${preProRec.createUserID}">
		<input type="hidden" name="preProRec.updateUserID" value="${preProRec.updateUserID}">
		<input type="hidden" name="preProRec.deptId" value="${preProRec.deptId}">
		<input type="hidden" name="preProRec.delFlag" value="${preProRec.delFlag}">
		<input type="hidden" name="preProRec.linkId" value="${preProRec.linkId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="preProRec.areaId" style="width: 60%" defaultText='请选择' codeName="企业属地" value="${preProRec.areaId}"  onchange="clearCompany()" dataType="*1-127" nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="preProRec.companyName" style="width: 60%" value="${preProRec.companyName}" type="text" readonly="readonly" onclick="queryQy()" dataType="*1-127"  nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="preProRec.companyId" value="${preProRec.companyId}"/>
					</td>
				</tr> 
				<tr>
					<th width="15%">发证机关</th>
					<td width="35%"><input name="preProRec.authority" value="${preProRec.authority}" style="width: 60%" type="text" datatype="*1-127" errormsg='发证机关必须是1到127位字符！' nullmsg='发证机关不能为空！' sucmsg='发证机关填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">备案日期</th>
					<td width="35%"><input name="preProRec.recordDate" style="width: 60%" value="<fmt:formatDate type='date' value='${preProRec.recordDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">有效期至</th>
					<td width="35%"><input name="preProRec.validity" style="width: 60%" value="<fmt:formatDate type='date' value='${preProRec.validity}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">易制毒化学品</th>
					<td width="35%"><input name="preProRec.precursorChemical" style="width: 60%" value="${preProRec.precursorChemical}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">材料接收人员</th>
					<td width="35%"><input name="preProRec.receptName" style="width: 60%" value="${preProRec.receptName}" type="text" maxlength="127"></td>
					<th width="15%">材料审查人员</th>
					<td width="35%"><input name="preProRec.reviewName" style="width: 60%" value="${preProRec.reviewName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">档案编号</th>
					<td width="35%"><input name="preProRec.fileNo" style="width: 60%" value="${preProRec.fileNo}" type="text" maxlength="127"></td>
					<th width="15%">申请材料是否齐全</th>
					<td width="35%"><cus:SelectOneTag property="preProRec.isComplete" style="width: 60%" defaultText='请选择' codeName="是或否" value="${preProRec.isComplete}" /></td>
				</tr>
				<tr>
					<th width="15%">签字领导</th>
					<td width="35%"><input name="preProRec.signLeader" style="width: 60%" value="${preProRec.signLeader}" type="text" maxlength="127"></td>
					<th width="15%">材料审查情况</th>
					<td width="35%"><input name="preProRec.materialReview" style="width: 60%" value="${preProRec.materialReview}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">本次备案情况</th>
					<td width="35%"><input name="preProRec.filingCase" style="width: 60%" value="${preProRec.filingCase}" type="text" maxlength="127"></td>
				</tr>
				
				<tr>
					<th width="15%">非药品类易制毒化学品备案申请书</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue1"/>
				    	<input type="file" name="uploadify1" id="uploadify1"/>
			    		<a href="javascript:jQuery('#uploadify1').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify1').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="preprorecordfj1" style="padding:0 0 0 0;"> 
							  <c:forEach var="item" items="${picList1}">
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
				
				
				<tr>
					<th width="15%">非药品类易制毒化学品备案申请材料</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue2"/>
				    	<input type="file" name="uploadify2" id="uploadify2"/>
			    		<a href="javascript:jQuery('#uploadify2').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify2').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="preprorecordfj2">
							  <c:forEach var="item" items="${picList2}">
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
				
				<tr>
					<th width="15%">集体会审记录</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue3"/>
				    	<input type="file" name="uploadify3" id="uploadify3"/>
			    		<a href="javascript:jQuery('#uploadify3').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify3').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="preprorecordfj3">
							  <c:forEach var="item" items="${picList3}">
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
				
				<tr>
					<th width="15%">非药品类易制毒化学品备案证明副本</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue4"/>
				    	<input type="file" name="uploadify4" id="uploadify4"/>
			    		<a href="javascript:jQuery('#uploadify4').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify4').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="preprorecordfj4">
							  <c:forEach var="item" items="${picList3}">
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
				
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_preProRec');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

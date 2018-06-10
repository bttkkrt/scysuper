<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
		<script>
	function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
	function queryQy()
		{
			var szzid = document.getElementById('areaId').value;
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=idmcqy&entBaseInfo.enterprisePossession="+szzid, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
	</script>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify1","${firLic.linkId}","yhbzxzxk","xzxk1","fileQueue1");
		});
		
</script>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify2","${firLic.linkId}","yhbzxzxk","xzxk2","fileQueue2");
		});
		
</script>
<script>
	$(document).ready(function() {
		  uploadPic("uploadify3","${firLic.linkId}","yhbzxzxk","xzxk3","fileQueue3");
		});
		
</script>
<script>
	$(document).ready(function() {
		  uploadPic("uploadify4","${firLic.linkId}","yhbzxzxk","xzxk4","fileQueue4");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="firLicSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="firLic.id" value="${firLic.id}">
		<input type="hidden" name="firLic.createTime" value="<fmt:formatDate type="both" value="${firLic.createTime}" />">
		<input type="hidden" name="firLic.updateTime" value="${firLic.updateTime}">
		<input type="hidden" name="firLic.createUserID" value="${firLic.createUserID}">
		<input type="hidden" name="firLic.updateUserID" value="${firLic.updateUserID}">
		<input type="hidden" name="firLic.deptId" value="${firLic.deptId}">
		<input type="hidden" name="firLic.delFlag" value="${firLic.delFlag}">
		<input type="hidden" name="firLic.linkId" value="${firLic.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%" property="firLic.areaId" defaultText='请选择' codeName="企业属地" value="${firLic.areaId}" onchange="clearCompany()" datatype="*1-127" errormsg='所在区域必须是1到127位字符！' nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'  maxlength="127"/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="firLic.companyName" style="width: 60%" value="${firLic.companyName}" type="text" readonly="readonly" onclick="queryQy()"/>
						<input type="hidden" id="companyId" name="firLic.companyId" value="${firLic.companyId}" datatype="*1-127" errormsg='企业名称必须是1到127位字符！' nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'  maxlength="127"/><font style='color:red'>*</font>
					</td>
				</tr>	
				<tr>
					<th width="15%">档案号</th>
					<td width="35%"><input name="firLic.itemNo" value="${firLic.itemNo}" style="width: 60%" type="text" datatype="*1-127" errormsg='档案号必须是1到127位字符！' nullmsg='档案号不能为空！' sucmsg='档案号填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">局审会意见</th>
					<td width="35%"><input name="firLic.agencyComment" style="width: 60%" value="${firLic.agencyComment}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">材料审查人员</th>
					<td width="35%"><input name="firLic.checkPerson" style="width: 60%" value="${firLic.checkPerson}" type="text" datatype="*1-127" errormsg='材料审查人员必须是1到127位字符！' nullmsg='材料审查人员不能为空！' sucmsg='材料审查人员填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">材料接收人员</th>
					<td width="35%"><input name="firLic.receivePerson" style="width: 60%" value="${firLic.receivePerson}" type="text" datatype="*1-127" errormsg='材料接收人员必须是1到127位字符！' nullmsg='材料接收人员不能为空！' sucmsg='材料接收人员填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">材料接收日期</th>
					<td width="35%"><input name="firLic.receiveDate" style="width: 60%"  value="<fmt:formatDate type='date' value='${firLic.receiveDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  ></td>
					<th width="15%">受理材料日期</th>
					<td width="35%"><input name="firLic.dealDate" style="width: 60%"  value="<fmt:formatDate type='date' value='${firLic.dealDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  ></td>
				</tr>
				<tr>
					<th width="15%">证书编号</th>
					<td width="35%"><input name="firLic.zsbh" value="${firLic.zsbh}" style="width: 60%" type="text" maxlength="127"></td>
					<th width="15%">仓库设施地址</th>
					<td width="35%"><input name="firLic.ckssdz" value="${firLic.ckssdz}" style="width: 60%" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">许可证有效起始日期</th>
					<td width="35%"><input name="firLic.licenseValid" id="licenseValid" style="width: 60%" value="<fmt:formatDate type='date' value='${firLic.licenseValid}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='许可证有效起始日期不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'licenseValidEnd\')}'})"  ><font style='color:red'>*</font></td>
					<th width="15%">许可证有效期截至日期</th>
					<td width="35%"><input name="firLic.licenseValidEnd" id="licenseValidEnd" style="width: 60%" value="<fmt:formatDate type='date' value='${firLic.licenseValidEnd}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='许可证有效截止日期不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'licenseValid\')}'})"  ><font style='color:red'>*</font></td>
				</tr>
				<tr>
				    <th width="15%">发证日期</th>
					<td width="35%"><input name="firLic.fzrq" style="width: 60%" value="<fmt:formatDate type='date' value='${firLic.fzrq}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='发证日期不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  ><font style='color:red'>*</font></td>
					<th width="15%">发证单位</th>
					<td width="35%"><input name="firLic.lssuingUnit" style="width: 60%" value="${firLic.lssuingUnit}" type="text" maxlength="127"></td>
				</tr>
				
				<tr>
					<th width="15%">本次领证情况</th>
					<td width="35%"><input name="firLic.licenseCondition" style="width: 60%" value="${firLic.licenseCondition}" type="text" maxlength="127"></td>
					<th width="15%">申请材料是否齐全</th>
					<td width="35%"><cus:SelectOneTag property="firLic.applyCondition" style="width: 60%" defaultText='请选择' codeName="是或否" value="${firLic.applyCondition}" datatype="*1-127" errormsg='必须是1到127位字符！' nullmsg='不能为空！' sucmsg='填写正确！'  maxlength="127"/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">签字领导</th>
					<td width="35%"><input name="firLic.signLeader" style="width: 60%" value="${firLic.signLeader}" type="text" maxlength="127"></td>
					<th width="15%">预审意见</th>
					<td width="35%"><input name="firLic.preComment" style="width: 60%" value="${firLic.preComment}" type="text" datatype="*1-127" errormsg='预审意见必须是1到127位字符！' nullmsg='预审意见不能为空！' sucmsg='预审意见填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">现场检查部门</th>
					<td width="35%"><input name="firLic.checkDepart" style="width: 60%" value="${firLic.checkDepart}" type="text" maxlength="127"></td>
					<th width="15%">核查结论</th>
					<td width="35%"><input name="firLic.checkConclusion" style="width: 60%" value="${firLic.checkConclusion}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">材料审查情况</th>
					<td width="35%"><input name="firLic.materialCondition" style="width: 60%" value="${firLic.materialCondition}" type="text" maxlength="127"></td>
					<th width="15%">行政许可建议</th>
					<td width="35%"><input name="firLic.adminSuggest" style="width: 60%" value="${firLic.adminSuggest}" type="text" maxlength="127"></td>
				</tr>
				<tr>
				    <th width="15%">变更日期</th>
					<td width="35%"><input name="firLic.bgrq" style="width: 60%" value="<fmt:formatDate type='date' value='${firLic.bgrq}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='变更日期不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  ><font style='color:red'>*</font></td>
					<th width="15%">经营范围</th>
					<td width="35%"><input name="firLic.jyfw" style="width: 60%" value="${firLic.jyfw}" type="text"  datatype="*1-100" errormsg='经营范围必须是1到100位字符！' nullmsg='经营范围不能为空！' maxlength="100"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">烟花爆竹经营许可证申请书</th>
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
						<table id="xzxk1">
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
				<tr>
					<th width="15%">烟花爆竹经营许可证申请材料</th>
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
						<table id="xzxk2">
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
					<th width="15%">行政审批文件</th>
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
						<table id="xzxk3">
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
					<th width="15%">烟花爆竹经营许可证扫描件</th>
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
						<table id="xzxk4">
							  <c:forEach var="item" items="${picList4}">
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
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

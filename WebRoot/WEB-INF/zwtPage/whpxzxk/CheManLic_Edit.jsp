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
		  uploadPic("uploadify1","${cheManLic.linkId}","whpxzxk","xzxk1","fileQueue1");
		});
		
</script>
	<script>
	$(document).ready(function() {
		  uploadPic("uploadify2","${cheManLic.linkId}","whpxzxk","xzxk2","fileQueue2");
		});
		
</script>
<script>
	$(document).ready(function() {
		  uploadPic("uploadify3","${cheManLic.linkId}","whpxzxk","xzxk3","fileQueue3");
		});
		
</script>
<script>
	$(document).ready(function() {
		  uploadPic("uploadify4","${cheManLic.linkId}","whpxzxk","xzxk4","fileQueue4");
		});
		
</script>
<script>
	$(document).ready(function() {
		  uploadPic("uploadify5","${cheManLic.linkId}","whpxzxk","xzxk5","fileQueue5");
		});
		
</script>
<script>
	$(document).ready(function() {
		  uploadPic("uploadify6","${cheManLic.linkId}","whpxzxk","xzxk6","fileQueue6");
		});
		
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">

	<form name="myform1" method="post" enctype="multipart/form-data" action="cheManLicSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="cheManLic.id" value="${cheManLic.id}">
		<input type="hidden" name="cheManLic.createTime" value="<fmt:formatDate type="both" value="${cheManLic.createTime}" />">
		<input type="hidden" name="cheManLic.updateTime" value="${cheManLic.updateTime}">
		<input type="hidden" name="cheManLic.createUserID" value="${cheManLic.createUserID}">
		<input type="hidden" name="cheManLic.updateUserID" value="${cheManLic.updateUserID}">
		<input type="hidden" name="cheManLic.deptId" value="${cheManLic.deptId}">
		<input type="hidden" name="cheManLic.delFlag" value="${cheManLic.delFlag}">
		<input type="hidden" name="cheManLic.linkId" value="${cheManLic.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag property="cheManLic.areaId" style="width: 60%"  defaultText='请选择' codeName="企业属地" value="${cheManLic.areaId}" onchange="clearCompany()" datatype="*1-127" errormsg='所在区域必须是1到127位字符！' nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'  maxlength="127"/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="cheManLic.companyName" style="width: 60%"  value="${cheManLic.companyName}" type="text" readonly="readonly" datatype="*1-127" errormsg='企业名称必须是1到127位字符！' nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'  maxlength="127" onclick="queryQy()"/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="cheManLic.companyId" value="${cheManLic.companyId}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">经营方式</th>
					<td width="35%"><cus:SelectOneTag property="cheManLic.operateWay" defaultText='请选择' codeName="危险化学品经营方式" style="width: 60%" value="${cheManLic.operateWay}" datatype="*1-127" errormsg='经营方式必须是1到127位字符！' nullmsg='经营方式不能为空！' sucmsg='经营方式填写正确！'  maxlength="127"/><font style='color:red'>*</font></td>
					<th width="15%">经营类型</th>
					<td width="35%"><cus:hxcheckbox property="cheManLic.operateType" codeName="危险化学品经营类型" value="${cheManLic.operateType}" datatype="*1-127" errormsg='经营类型必须是1到127位字符！' nullmsg='经营类型不能为空！' sucmsg='经营类型填写正确！'  maxlength="127"/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">材料接收日期</th>
					<td width="35%"><input name="cheManLic.receiveDate" style="width: 60%" value="<fmt:formatDate type='date' value='${cheManLic.receiveDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  ></td>
					<th width="15%">受理材料日期</th>
					<td width="35%"><input name="cheManLic.dealDate" style="width: 60%" value="<fmt:formatDate type='date' value='${cheManLic.dealDate}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  ></td>
				</tr>
				<tr>
					<th width="15%">评价机构</th>
					<td width="35%"><input name="cheManLic.ratingAgencies" style="width: 60%" value="${cheManLic.ratingAgencies}" type="text" maxlength="127"></td>
					<th width="15%">许可证编号</th>
					<td width="35%"><input name="cheManLic.xkzbh" style="width: 60%" value="${cheManLic.xkzbh}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">许可证有效起始日期</th>
					<td width="35%"><input name="cheManLic.licenseValid" id="licenseValid"  style="width: 60%" value="<fmt:formatDate type='date' value='${cheManLic.licenseValid}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='许可证有效起始日期不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'licenseValidEnd\')}'})"  ><font style='color:red'>*</font></td>
				    <th width="15%">许可证有效截止日期</th>
					<td width="35%"><input name="cheManLic.licenseValidEnd" id="licenseValidEnd"  style="width: 60%" value="<fmt:formatDate type='date' value='${cheManLic.licenseValidEnd}' pattern="yyyy-MM-dd"/>" type="text"  datatype="*"  nullmsg='许可证有效截止日期不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'licenseValid\')}'})"  ><font style='color:red'>*</font></td>
				</tr>
				
				<tr>
				    <th width="15%">发证日期</th> 
					<td width="35%"><input name="cheManLic.fzrq" style="width: 60%" value="<fmt:formatDate type='date' value='${cheManLic.fzrq}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='发证日期不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  ><font style='color:red'>*</font></td>
					<th width="15%">发证单位</th>
					<td width="35%"><input name="cheManLic.lssuingUnit" style="width: 60%" value="${cheManLic.lssuingUnit}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">材料接收人员</th>
					<td width="35%"><input name="cheManLic.receivePerson" style="width: 60%" value="${cheManLic.receivePerson}" type="text" datatype="*1-127" errormsg='材料接收人员必须是1到127位字符！' nullmsg='材料接收人员不能为空！' sucmsg='材料接收人员填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">材料审查人员</th>
					<td width="35%"><input name="cheManLic.checkPerson" style="width: 60%" value="${cheManLic.checkPerson}" type="text" datatype="*1-127" errormsg='材料审查人员必须是1到127位字符！' nullmsg='材料审查人员不能为空！' sucmsg='材料审查人员填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">档案编号</th>
					<td width="35%"><input name="cheManLic.archivesNo" style="width: 60%" value="${cheManLic.archivesNo}" type="text" datatype="*1-127" errormsg='档案编号必须是1到127位字符！' nullmsg='档案编号不能为空！' sucmsg='档案编号填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">本次领证情况</th>
					<td width="35%"><input name="cheManLic.licenseCondition" style="width: 60%" value="${cheManLic.licenseCondition}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">申请材料是否齐全</th>
					<td width="35%"><cus:SelectOneTag property="cheManLic.applyCondition" style="width: 60%" defaultText='请选择' codeName="是或否" value="${cheManLic.applyCondition}" datatype="*1-127" errormsg='必须是1到127位字符！' nullmsg='不能为空！' sucmsg='填写正确！'  maxlength="127"/><font style='color:red'>*</font></td>
					<th width="15%">签字领导</th>
					<td width="35%"><input name="cheManLic.signLeader" style="width: 60%" value="${cheManLic.signLeader}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">街道预审意见</th>
					<td width="35%"><input name="cheManLic.streetComment" style="width: 60%" value="${cheManLic.streetComment}" type="text" maxlength="127"></td>
					<th width="15%">现场核查部门</th>
					<td width="35%"><input name="cheManLic.checkDepart" style="width: 60%" value="${cheManLic.checkDepart}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">核查结论</th>
					<td width="35%"><input name="cheManLic.checkConclusion" style="width: 60%" value="${cheManLic.checkConclusion}" type="text" maxlength="127"></td>
					<th width="15%">是否储存涉及</th>
					<td width="35%"><cus:SelectOneTag property="cheManLic.storageCondition" style="width: 60%" defaultText='请选择' codeName="是或否" value="${cheManLic.storageCondition}" /></td>
				</tr>
				<tr>
					<th width="15%">材料审查情况</th>
					<td width="35%"><input name="cheManLic.materialCondition" style="width: 60%" value="${cheManLic.materialCondition}" type="text" maxlength="127"></td>
					<th width="15%">行政许可建议</th>
					<td width="35%"><input name="cheManLic.adminSuggest" style="width: 60%" value="${cheManLic.adminSuggest}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">经营范围</th>
					<td width="35%"><input name="cheManLic.jyfw" value="${cheManLic.jyfw}" style="width: 60%" type="text"  datatype="*1-100" errormsg='经营范围必须是1到100位字符！' nullmsg='经营范围不能为空！' maxlength="100"><font style='color:red'>*</font></td>
					
				</tr>
				<tr>
					<th width="15%">会审记录</th>
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
					<td width="85%" colspan="3"  style="padding:0 0 0 0;">
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
					<th width="15%">危险化学品经营许可证申请</th>
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
					<td width="85%" colspan="3"  style="padding:0 0 0 0;">
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
					<th width="15%">危险化学品经营许可证申请材料</th>
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
					<td width="85%" colspan="3"  style="padding:0 0 0 0;">
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
					<th width="15%">带有存储设施经营危险化学品企业（包括加油站），应提供具有法定资质的安全评价机构出具的安全评价报告及安全评价报告备案证明</th>
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
					<td width="85%" colspan="3"  style="padding:0 0 0 0;">
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
					<th width="15%">行政审批文件</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue5"/>
				    	<input type="file" name="uploadify5" id="uploadify5"/>
			    		<a href="javascript:jQuery('#uploadify5').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify5').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3"  style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="xzxk5">
							  <c:forEach var="item" items="${picList5}">
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
					<th width="15%">危险化学品经营许可证副本</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue6"/>
				    	<input type="file" name="uploadify6" id="uploadify6"/>
			    		<a href="javascript:jQuery('#uploadify6').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify6').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3"  style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="xzxk6">
							  <c:forEach var="item" items="${picList6}">
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

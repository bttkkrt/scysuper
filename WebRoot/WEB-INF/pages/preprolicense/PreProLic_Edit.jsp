<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		$(document).ready(function() {
			  uploadPic("uploadify1","${preProLic.linkId}","preprolicense","hsjlfj","fileQueue1");
			  uploadPic("uploadify2","${preProLic.linkId}","preprolicense","fyplyzdhxpscxkzsqsfj","fileQueue2");
			  uploadPic("uploadify3","${preProLic.linkId}","preprolicense","gsyyzzfj","fileQueue3");
			  uploadPic("uploadify4","${preProLic.linkId}","preprolicense","yzdhxpglzdaqscglzdfj","fileQueue4");
			  uploadPic("uploadify5","${preProLic.linkId}","preprolicense","dwfddbrhzyfzrhjsglryjbyzdhxpygzsdzmclyjwdpfzjldzmclfj","fileQueue5");
			  uploadPic("uploadify6","${preProLic.linkId}","preprolicense","dwfddbrhzyfzrhaqscglryjaqscjdglbmpxhghbfdaqzgzshzyzgzsfj","fileQueue6");
			  uploadPic("uploadify7","${preProLic.linkId}","preprolicense","hjtfsjyjyafj","fileQueue7");
			  uploadPic("uploadify8","${preProLic.linkId}","preprolicense","bmcpdmchhxmhtymhxfzshcfdcpbzsmshsysmsfj","fileQueue8");
			  uploadPic("uploadify9","${preProLic.linkId}","preprolicense","sywxhxpscqydhxtjwxhxpscyqaaqscxkzhwxhxpdjzfj","fileQueue9");
			  uploadPic("uploadify10","${preProLic.linkId}","preprolicense","scsbccsshwrwclssqksmclfj","fileQueue10");
			  uploadPic("uploadify11","${preProLic.linkId}","preprolicense","flfghgzgddqttjdzmwjzlfj","fileQueue11");
			  
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="preProLicSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="preProLic.id" value="${preProLic.id}">
		<input type="hidden" name="preProLic.createTime" value="<fmt:formatDate type="both" value="${preProLic.createTime}" />">
		<input type="hidden" name="preProLic.updateTime" value="${preProLic.updateTime}">
		<input type="hidden" name="preProLic.createUserID" value="${preProLic.createUserID}">
		<input type="hidden" name="preProLic.updateUserID" value="${preProLic.updateUserID}">
		<input type="hidden" name="preProLic.deptId" value="${preProLic.deptId}">
		<input type="hidden" name="preProLic.delFlag" value="${preProLic.delFlag}">
		<input type="hidden" name="preProLic.linkId" value="${preProLic.linkId}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%"   property="preProLic.areaId" defaultText='请选择' codeName="企业属地" value="${preProLic.areaId}" onchange="clearCompany()" dataType="*1-127" nullmsg='所在区域不能为空！' sucmsg='所在区域填写正确！'/><font style='color:red'>*</font></td>
					<th width="15%">企业名称</th>
					<td width="35%"><input id="companyName" name="preProLic.companyName" style="width: 60%" value="${preProLic.companyName}" type="text" readonly="readonly" onclick="queryQy()" dataType="*1-127"  nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'/><font style='color:red'>*</font>
						<input type="hidden" id="companyId" name="preProLic.companyId" value="${preProLic.companyId}"/>
					</td>
				</tr> 
				<tr>
					<th width="15%">许可证编号</th>
					<td width="35%"><input name="preProLic.licenseNumber" style="width: 60%" value="${preProLic.licenseNumber}" type="text" maxlength="127"></td>
					<th width="15%">签字领导</th>
					<td width="35%"><input name="preProLic.signLeader" style="width: 60%" value="${preProLic.signLeader}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">许可证有效起始日期</th>
					<td width="35%"><input name="preProLic.licenseValid" id="licenseValid" style="width: 60%" value="<fmt:formatDate type='both' pattern="yyyy-MM-dd" value='${preProLic.licenseValid}' />" type="text" datatype="*"  nullmsg='许可证有效起始日期不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'licenseValidEnd\')}'})"><font style='color:red'>*</font></td>
					<th width="15%">许可证有效截止日期</th>
					<td width="35%"><input name="preProLic.licenseValidEnd" id="licenseValidEnd" style="width: 60%" value="<fmt:formatDate type='both' pattern="yyyy-MM-dd" value='${preProLic.licenseValidEnd}' />" type="text" datatype="*"  nullmsg='许可证有效截止日期不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'licenseValid\')}'})"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">发证机关</th>
					<td width="35%"><input name="preProLic.issuingAuthority" style="width: 60%" value="${preProLic.issuingAuthority}" type="text" maxlength="127"></td>
					<th width="15%">发证日期</th>
					<td width="35%"><input name="preProLic.issuingDate" style="width: 60%" value="<fmt:formatDate type='both' pattern="yyyy-MM-dd"  value='${preProLic.issuingDate}' />" type="text" datatype="*"  nullmsg='发证日期不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">材料接收日期</th>
					<td width="35%"><input name="preProLic.receptDate" style="width: 60%" value="<fmt:formatDate type='both' pattern="yyyy-MM-dd"  value='${preProLic.receptDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
					<th width="15%">材料接收人员</th>
					<td width="35%"><input name="preProLic.receptName" style="width: 60%" value="${preProLic.receptName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">材料审查人员</th>
					<td width="35%"><input name="preProLic.reviewName" style="width: 60%" value="${preProLic.reviewName}" type="text" maxlength="127"></td>
					<th width="15%">材料审查情况</th>
					<td width="35%"><input name="preProLic.materialReview" style="width: 60%" value="${preProLic.materialReview}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">档案编号</th>
					<td width="35%"><input name="preProLic.fileNo" style="width: 60%" dataType="*1-127" errormsg='档案编号必须是1到127位字符！' nullmsg='档案编号不能为空！' sucmsg='档案编号填写正确！'  value="${preProLic.fileNo}" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">申请材料是否齐全</th>
					<td width="35%"><cus:SelectOneTag style="width: 60%"   property="preProLic.isComplete" defaultText='请选择' codeName="是或否" value="${preProLic.isComplete}" /></td>
				</tr>
				<tr>
					<th width="15%">产品流向</th>
					<td width="35%"><input name="preProLic.productFlow" style="width: 60%" value="${preProLic.productFlow}" type="text" maxlength="127"></td>
					<th width="15%">经营范围</th>
					<td width="35%"><input name="preProLic.jyfw" style="width: 60%" value="${preProLic.jyfw}" type="text"  datatype="*1-100" errormsg='经营范围必须是1到100位字符！' nullmsg='经营范围不能为空！' maxlength="100"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">许可内容</th>
					<td width="96%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="preProLic.licenseContent" style="width:96%;height:60px">${preProLic.licenseContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">本次备案情况</th>
					<td width="96%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="preProLic.filingCase" style="width:96%;height:60px">${preProLic.filingCase}</textarea></td>
				</tr>
				<tr>
					<th width="15%">行政许可建议</th>
					<td width="96%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="preProLic.proposal" style="width:96%;height:60px">${preProLic.proposal}</textarea></td>
				</tr>
				
				<tr>
					<th width="10%">会审记录</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue1"/>
				    	<input type="file" name="uploadify1" id="uploadify1"/>
			    		<a href="javascript:jQuery('#uploadify1').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify1').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="hsjlfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="10%">非药品类易制毒化学品生产许可证申请书</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue2"/>
				    	<input type="file" name="uploadify2" id="uploadify2"/>
			    		<a href="javascript:jQuery('#uploadify2').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify2').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="fyplyzdhxpscxkzsqsfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="10%">工商营业执照副本</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue3"/>
				    	<input type="file" name="uploadify3" id="uploadify3"/>
			    		<a href="javascript:jQuery('#uploadify3').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify3').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="gsyyzzfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="10%">易制毒化学品管理制度、安全生产管理制度</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue4"/>
				    	<input type="file" name="uploadify4" id="uploadify4"/>
			    		<a href="javascript:jQuery('#uploadify4').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify4').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="yzdhxpglzdaqscglzdfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="10%">单位法定代表人或主要负责人和技术、管理人员具备易制毒化学品有关知识的证明材料以及无毒品犯罪记录的证明材料</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue5"/>
				    	<input type="file" name="uploadify5" id="uploadify5"/>
			    		<a href="javascript:jQuery('#uploadify5').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify5').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="dwfddbrhzyfzrhjsglryjbyzdhxpygzsdzmclyjwdpfzjldzmclfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				
				<tr>
					<th width="10%">单位法定代表人或主要负责人和安全生产管理人员经安全生产监督管理部门培训合格后颁发的安全资格证书和执业资格证书</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue6"/>
				    	<input type="file" name="uploadify6" id="uploadify6"/>
			    		<a href="javascript:jQuery('#uploadify6').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify6').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="dwfddbrhzyfzrhaqscglryjaqscjdglbmpxhghbfdaqzgzshzyzgzsfj">
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
								   <a href="javascript:del('${item.id}');">删除</a>
								   </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="10%">环境突发事件应急预案</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue7"/>
				    	<input type="file" name="uploadify7" id="uploadify7"/>
			    		<a href="javascript:jQuery('#uploadify7').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify7').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="hjtfsjyjyafj">
							  <c:forEach var="item" items="${picList7}">
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
					<th width="10%">标明产品的名称（含学名和通用名）、化学分子式和成分的产品包装说明和使用说明书</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue8"/>
				    	<input type="file" name="uploadify8" id="uploadify8"/>
			    		<a href="javascript:jQuery('#uploadify8').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify8').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="bmcpdmchhxmhtymhxfzshcfdcpbzsmshsysmsfj">
							  <c:forEach var="item" items="${picList8}">
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
					<th width="10%">属于危险化学品生产企业的，还需提交危险化学品生产企业安全生产许可证和危险化学品登记证</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue9"/>
				    	<input type="file" name="uploadify9" id="uploadify9"/>
			    		<a href="javascript:jQuery('#uploadify9').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify9').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="sywxhxpscqydhxtjwxhxpscyqaaqscxkzhwxhxpdjzfj">
							  <c:forEach var="item" items="${picList9}">
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
					<th width="10%">生产设备、仓储设施和污染物处理设施情况说明材料</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue10"/>
				    	<input type="file" name="uploadify10" id="uploadify10"/>
			    		<a href="javascript:jQuery('#uploadify10').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify10').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="scsbccsshwrwclssqksmclfj">
							  <c:forEach var="item" items="${picList10}">
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
					<th width="10%">法律、法规和规章规定的其他条件的证明文件、资料</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue11"/>
				    	<input type="file" name="uploadify11" id="uploadify11"/>
			    		<a href="javascript:jQuery('#uploadify11').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify11').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="10%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="flfghgzgddqttjdzmwjzlfj">
							  <c:forEach var="item" items="${picList11}">
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_preProLic');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

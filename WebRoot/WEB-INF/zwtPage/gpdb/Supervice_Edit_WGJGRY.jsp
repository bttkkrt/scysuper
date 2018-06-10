<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script type="text/javascript">
		$(document).ready(function() {
			  uploadPic("uploadify5","${linkId}","gpdb","fcwsfj","fileQueue5");
			  uploadPicOnly("uploadify6","${linkId}","gpdb","zghtpfj","fileQueue6");
			  
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
        
        $(function(){

		$("#money").keyup(function(event){
			var txt = $(this).val();
			var keyCode = event.which;
			if ( txt.length<=0 ) {
				$("#fixval").hide();
			}else{
				//先把非数字的都替换掉，除了数字和. 
        		txt = txt.replace(/[^\d.]/g,""); 
        		//必须保证第一个为数字而不是. 
       	 		txt = txt.replace(/^\./g,""); 
        		//保证只有出现一个.而没有多个. 
        		txt = txt.replace(/\.{2,}/g,"."); 
        		//保证.只出现一次，而不能出现两次以上 
        		txt = txt.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
        		if(txt.length >= 2 && txt.substring(0,1) == "0" && txt.substring(1,2) != ".")
        		{
       				txt = txt.substring(1,txt.length);
       			}
       			$("#money").val(txt);
				txt = formatNum(txt);
				
				$("#fixval").html(txt).show();
			}
			
			
		});
	});
	
	
	function formatNum(str){
		var newStr = "";
		var count = 0;
		
		if(str.indexOf(".")==-1){
		   for(var i=str.length-1;i>=0;i--){
		 if(count % 3 == 0 && count != 0){
		   newStr = str.charAt(i) + "," + newStr;
		 }else{
		   newStr = str.charAt(i) + newStr;
		 }
		 count++;
		   }
		   str = newStr + ".00"; //自动补小数点后两位
		}
		else
		{
		   for(var i = str.indexOf(".")-1;i>=0;i--){
		 if(count % 3 == 0 && count != 0){
		   newStr = str.charAt(i) + "," + newStr;
		 }else{
		   newStr = str.charAt(i) + newStr; //逐个字符相接起来
		 }
		 count++;
		   }
		   str = newStr + (str + "00").substr((str + "00").indexOf("."),3);
		 }
		 return str;
	}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="superviceSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="supervice.id" value="${supervice.id}">
		<input type="hidden" name="supervice.createTime" value="<fmt:formatDate type="both" value="${supervice.createTime}" />">
		<input type="hidden" name="supervice.updateTime" value="${supervice.updateTime}">
		<input type="hidden" name="supervice.createUserID" value="${supervice.createUserID}">
		<input type="hidden" name="supervice.updateUserID" value="${supervice.updateUserID}">
		<input type="hidden" name="supervice.deptId" value="${supervice.deptId}">
		<input type="hidden" name="supervice.delFlag" value="${supervice.delFlag}">
		<input type="hidden" name="supervice.linkId" value="${supervice.linkId}">
		<input type="hidden" name="linkId" value="${linkId}">
		<input type="hidden" name="supervice.rectificationState" value="已整改待审核">
		
		<input type="hidden" name="supervice.areaId" value="${supervice.areaId}">
		<input type="hidden" name="supervice.companyName" value="${supervice.companyName}">
		<input type="hidden" name="supervice.companyId" value="${supervice.companyId}">
		<input type="hidden" name="supervice.dangerName" value="${supervice.dangerName}">
		<input type="hidden" name="supervice.listingTime" value="<fmt:formatDate type='both' value='${supervice.listingTime}' />" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		<input type="hidden" name="supervice.dangerSort" value="${supervice.dangerSort}">
		<input type="hidden" name="supervice.dangerLevel" value="${supervice.dangerLevel}">
		<input type="hidden" name="supervice.rectificationLevel" value="${supervice.rectificationLevel}">
		<input type="hidden" name="supervice.responsibleUnit" value="${supervice.responsibleUnit}">
		<input type="hidden" name="supervice.responsible" value="${supervice.responsible}">
		<input type="hidden" name="supervice.responsibleMobile" value="${supervice.responsibleMobile}">
		<input type="hidden" name="supervice.address" value="${supervice.address}">
		<input type="hidden" name="supervice.rectificationTerm" value="<fmt:formatDate type='both' value='${supervice.rectificationTerm}' />" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${supervice.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${supervice.companyName}</td>
				</tr>
				<tr>
					<th width="15%">隐患名称</th>
					<td width="35%" >${supervice.dangerName}</td>
					<th width="15%">挂牌时间</th>
					<td width="35%" ><fmt:formatDate type="both"  pattern="yyyy-MM-dd"  value="${supervice.listingTime}" /></td>
				</tr>
				<tr>
					<th width="15%">隐患类别</th>
					<td width="35%" ><cus:hxlabel codeName="隐患类别" itemValue="${supervice.dangerSort}" /></td>
					<th width="15%">隐患级别</th>
					<td width="35%" ><cus:hxlabel codeName="隐患级别" itemValue="${supervice.dangerLevel}" /></td>
				</tr>
				
				<tr>
					<th width="15%">整改级别</th>
					<td width="35%"><cus:hxlabel codeName="挂牌督办整改级别" itemValue="${supervice.rectificationLevel}" /></td>
					<th width="15%">责任单位</th>
					<td width="35%">${supervice.responsibleUnit}</td>
				</tr>
				<tr>
					<th width="15%">责任人</th>
					<td width="35%">${supervice.responsible}</td>
					<th width="15%">责任人联系电话</th>
					<td width="35%">${supervice.responsibleMobile}</td>
				</tr>
				<tr>
					<th width="15%">地址</th>
					<td width="35%">${supervice.address}</td>
					<th width="15%">整改期限</th>
					<td width="35%"><fmt:formatDate type="both"  pattern="yyyy-MM-dd" value="${supervice.rectificationTerm}" /> </td>
				</tr>
				<tr>
					<th width="15%">隐患内容</th>
					<td width="85%" colspan="3"><textarea readOnly style="width:96%;height:60px;" name="supervice.dangerContent" >${supervice.dangerContent}</textarea></td>
				
				<tr>
				
				<tr>
					<th width="15%">检查文书</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList1}">
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
					<th width="15%">整改前图片</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList2}">
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
					<th width="15%">整改方案</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList3}">
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
					<th width="15%">防范措施</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${picList4}">
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
				<!-- 整改历史记录 -->
				<c:forEach var="rect" items="${rectInfos}">
					<tr>
						<th width="15%">整改资金（元）</th>
						<td width="35%" >
							${rect.money }
						</td>
						<th width="15%">隐患整改数</th>
						<td width="35%" >${rect.state }</td>
					</tr>
					<tr>
						<th width="15%">整改完成时间</th>
						<td width="35%" >${rect.rectTime }</td>
						<th width="15%">验收时间</th>
						<td width="35%" >${rect.insertTime }</td>
					</tr>
					<tr>
					<th width="15%">复查文书</th>
					<td width="85%" colspan="3">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${rect.fcwsfj}">
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
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">整改后图片</th>
					<td width="85%" colspan="3">
						<div style="color:green;overflow:auto;height:175px;">
						<table>
							  <c:forEach var="item" items="${rect.zghtpfj}">
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
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(checkRecords)>0}">
				<tr >
					<td colspan="4" style="text-align:center;font-weight:bold;font-size:22px;">审核记录</td>
				</tr>
				<tr>
					<td colspan="4">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				
				</c:if>
				<!-- 整改信息上报 -->
				<tr >
					<td colspan="4" style="text-align:center;font-weight:bold;font-size:22px;">整改上报</td>
				</tr>
				<tr>
					<th width="15%">整改资金（元）</th>
					<td width="35%" ><input name="supervice.rectificationMoney" style="width:60%"  dataType="*1-127"  nullmsg='整改资金（元）不能为空！' id='money' type="text" maxlength="127"><font style='color:red'>*</font><span id="fixval"></span></td>
					<th width="15%">隐患整改数</th>
					<td width="35%"><input name="supervice.danrecNum" style="width:60%"   dataType="n1-127" errormsg='隐患整改数必须是数字！' nullmsg='隐患整改数不能为空！' sucmsg='隐患整改数填写正确！' type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">整改完成时间</th>
					<td width="35%"><input name="supervice.recfinishTime"  style="width:60%"   dataType="*1-50"  nullmsg='整改完成时间不能为空！'    type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style='color:red'>*</font></td>
					<th width="15%">验收时间</th>
					<td width="35%"><input name="supervice.acceptTime"  style="width:60%"   dataType="*1-50"  nullmsg='验收时间不能为空！'  type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="10%">复查文书</th>
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
						<table id="fcwsfj">
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
					<th width="10%">整改后图片</th>
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
						<table id="zghtpfj">
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
												border='0' width='220' height='175'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
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
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
						</s:else>						
 					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

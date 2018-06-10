<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function saveSelText(i,text){
			$("#"+i).val(text);
		}
		function queryQy()
		{
			var szzid = document.getElementById('areaId').value;
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=idmcqy&entBaseInfo.enterprisePossession="+szzid, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
        
        $(document).ready(function() {
		  uploadPicOnly("uploadify","${troMan.linkId}","troMan","troManfj","fileQueue");
		});
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
			<font style='color:red'>*若此隐患自己无法整改需安监协助的，是否上报安监选择是，其余情况请选择否</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="troManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="troMan.id" value="${troMan.id}">
		<input type="hidden" name="troMan.createTime" value="<fmt:formatDate type="both" value="${troMan.createTime}" />">
		<input type="hidden" name="troMan.updateTime" value="${troMan.updateTime}">
		<input type="hidden" name="troMan.createUserID" value="${troMan.createUserID}">
		<input type="hidden" name="troMan.updateUserID" value="${troMan.updateUserID}">
		<input type="hidden" name="troMan.deptId" value="${troMan.deptId}">
		<input type="hidden" name="troMan.delFlag" value="${troMan.delFlag}">
		<input type="hidden" name="troMan.taskId" value="${troMan.taskId}">
		<input type="hidden" name="troMan.ifCorrected" value="${troMan.ifCorrected}">
		<input name="troMan.linkId" value="${troMan.linkId}" type="hidden" maxlength="127">
		<input id="deptName" name="troMan.dealDeptName" value="${troMan.dealDeptName}" type="hidden" maxlength="127">
		<input name="troMan.userId" value="${troMan.userId}" type="hidden" maxlength="127">
		<input name="troMan.userName" value="${troMan.userName}" type="hidden" maxlength="127">
		<input name="troMan.recfinishTime" value="<fmt:formatDate type='both' value='${troMan.recfinishTime}' />" type="hidden" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">	
		<input name="troMan.rectificationMoney" value="${troMan.rectificationMoney}" type="hidden" maxlength="127">	
		<input name="troMan.rectificationState" value="${troMan.rectificationState}" type="hidden" maxlength="127">
		<input name="troMan.checkItem" value="${troMan.checkItem}" type="hidden" maxlength="127">
		<input name="troMan.areaId" value="${troMan.areaId}" type="hidden" maxlength="127">
		<input name="troMan.areaName" value="${troMan.areaName}" type="hidden" maxlength="127">
		<input name="troMan.companyId" value="${troMan.companyId}" type="hidden" maxlength="127">
		<input name="troMan.companyName" value="${troMan.companyName}" type="hidden" maxlength="127">
							
			<table width="100%" border="0">
				<tr>
					<th width="15%">隐患名称</th>
					<td width="35%"><input name="troMan.troubleName" datatype="*1-127" errormsg='隐患名称必须是1到127位字符！' nullmsg='隐患名称不能为空！' sucmsg='隐患名称填写正确！'  value="${troMan.troubleName}" type="text" maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">隐患来源</th>
					<td width="35%">
						<input name="troMan.troubleSource" value="${troMan.troubleSource}" type="hidden" maxlength="127">
						${troMan.troubleSource}
					</td>
				</tr>
				<tr>
					<th width="15%">上报时间</th>
					<td width="35%"><input name="troMan.reportTime" value="<fmt:formatDate  pattern="yyyy-MM-dd" type='date' value='${troMan.reportTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127" errormsg='上报时间必须是1到127位字符！' nullmsg='上报时间不能为空！' sucmsg='上报时间填写正确！' style="width:60%"><font style='color:red'>*</font></td>
					<th width="15%">隐患级别</th>
					<td width="35%"><cus:SelectOneTag property="troMan.troubleLevel" style="width:60%" defaultText='请选择' codeName="隐患级别" value="${troMan.troubleLevel}"  datatype="*1-127" errormsg='隐患级别必须选择！' nullmsg='隐患级别必须选择！' sucmsg='隐患级别选择正确！'  maxlength="127"/>
					<font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">隐患类别</th>
					<td width="35%"><cus:SelectOneTag property="troMan.troubleSort" style="width:60%" defaultText='请选择' codeName="隐患类别" value="${troMan.troubleSort}" datatype="*1-127" errormsg='隐患类别必须选择！' nullmsg='隐患类别必须选择！' sucmsg='隐患类别选择正确！'  maxlength="127"/><font style='color:red'>*</font></td>
					<th width="15%">整改期限</th>
					<td width="35%">
						<input name="troMan.rectificationTerm"  type="text" class="Wdate" value="<fmt:formatDate  pattern="yyyy-MM-dd" type="date" value='${troMan.rectificationTerm}' />" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127" errormsg='整改期限必须是1到127位字符！' nullmsg='整改期限不能为空！' sucmsg='整改期限填写正确！' style="width:60%"><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">是否上报安监局</th>
					<td width="35%"><cus:SelectOneTag property="troMan.ifReportAwh" style="width:60%"  codeName="是或否" value="${troMan.ifReportAwh}" datatype="*"/><font style='color:red'>*</font></td>
					<th width="15%">隐患地点</th>
					<td width="35%"><input name="troMan.troubleAdd" style="width:60%" value="${troMan.troubleAdd}" type="text" maxlength="127" datatype="*1-127" errormsg='隐患地点必须是1到127位字符！' nullmsg='隐患地点不能为空！' sucmsg='隐患地点填写正确！'><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">整改责任部门</th>
					<td width="35%"><input name="troMan.rectDept" style="width:60%" datatype="*1-127" errormsg='整改责任部门必须是1到127位字符！' nullmsg='整改责任部门不能为空！' sucmsg='整改责任部门填写正确！'  value="${troMan.rectDept}" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">整改责任人</th>
					<td width="35%"><input name="troMan.rectPerson" style="width:60%" datatype="*1-127" errormsg='整改责任人必须是1到127位字符！' nullmsg='整改责任人不能为空！' sucmsg='整改责任人填写正确！'  value="${troMan.rectPerson}" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">整改责任人联系方式</th>
					<td width="35%"><input name="troMan.rectTel" style="width:60%" datatype="m" errormsg='整改责任人联系方式必须是手机号！' nullmsg='整改责任人联系方式不能为空！' sucmsg='整改责任人联系方式填写正确！'  value="${troMan.rectTel}" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">隐患详情</th>
					<td width="35%" colspan="3">
						<textarea style="width:80%;height:100px;" name="troMan.introduce" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" datatype="*1-2000" errormsg='隐患详情必须是1到127位字符！' nullmsg='隐患详情不能为空！' sucmsg='隐患详情填写正确！'>${troMan.introduce}</textarea><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">整改前图片上传</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加图片</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="troManfj">
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
												border='0' width='220' height='150'/>
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_troMan');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

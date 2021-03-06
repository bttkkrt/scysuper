<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="honRecCheckSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="honRec.id" value="${honRec.id}">
		<input type="hidden" name="checkRecord.infoId" value="${honRec.id}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${honRec.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${honRec.companyName}</td>
				</tr>
				<tr>
					<th width="15%">批准文号</th>
					<td width="35%" >${honRec.approvalNumber}</td>
					<th width="15%">表彰部门</th>
					<td width="35%" >${honRec.recognitionDept}</td>
				</tr>
				<tr>
					<th width="15%">荣誉称号</th>
					<td width="35%" >${honRec.honor}</td>
					<th width="15%">地区</th>
					<td width="35%" >${honRec.area}</td>
				</tr>
				<tr>
					<th width="15%">年度</th>
					<td width="35%" ><fmt:formatDate type="date" value="${honRec.bzyear}" pattern="yyyy"/></td>
					<th width="15%">表彰日期</th>
					<td width="35%" >${honRec.recognitionDate}</td>
				</tr>
				<tr>
				<th width="15%">表彰内容</th>
					<td width="35%" colspan="3"><textarea readOnly name="honRec.recognitionContent" style="width:96%;height:90px">${honRec.recognitionContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="honRec.intelligenceRemark" style="width:96%;height:90px">${honRec.intelligenceRemark}</textarea></td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
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
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords}" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				<tr>
					<th width="15%">审核结果</th>
					<td width="96%" colspan="3">
					<s:select name="checkRecord.checkResult" list="#{'审核通过':'审核通过','审核未通过':'审核未通过','审核通过并上报信用平台':'审核通过并上报信用平台'}" theme="simple" cssStyle="width:60%"/>
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="35%" colspan="3"><textarea name="checkRecord.checkRemark" style="width:96%;height:90px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${checkRecord.checkRemark}</textarea></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
						 <a href="#" class="btn_01" onclick="parent.close_win('win_honRec');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</form>
	</div></div></div>
</body>
</html>


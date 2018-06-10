<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">隐患名称</th>
					<td width="35%" >${troMan.troubleName}</td>
					<th width="15%">隐患来源</th>
					<td width="35%" >${troMan.troubleSource}</td>
				</tr>
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${troMan.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${troMan.companyName}</td>
				</tr>
				<tr>
					<th width="15%">上报人员名称</th>
					<td width="35%" >${troMan.userName}</td>
					<th width="15%">上报时间</th>
					<td width="35%" ><fmt:formatDate pattern="yyyy-MM-dd" type="both" value="${troMan.reportTime}" /></td>
				</tr>
				<tr>
					<th width="15%">隐患级别</th>
					<td width="35%" ><cus:hxlabel codeName="隐患级别" itemValue="${troMan.troubleLevel}" /></td>
					<th width="15%">隐患类别</th>
					<td width="35%" ><cus:hxlabel codeName="隐患类别" itemValue="${troMan.troubleSort}" /></td>
				</tr>
				<tr>
					<s:if test="wxyType == 0 ">
					<th width="15%">巡检点名称</th>
					<td width="35%" >
						<cus:hxlabel codeSql="select t.row_id as id,t.dot_name as dotName from dot t where t.delflag=0 and t.row_id='${troMan.checkItem}' " itemValue="${troMan.checkItem}" />
					</td>
					</s:if>
					<s:if test="wxyType == 1">
				   		<th width="15%">危险源名称</th>
						<td width="35%" >
							<cus:hxlabel codeSql="select row_id as id,danger_name as name  from COM_DAN_IDE  where delflag = 0 and row_id='${troMan.checkItem}' " itemValue="${troMan.checkItem}" />
						</td>
					</s:if>
					<th width="15%">整改期限</th>
					<td width="35%" ><fmt:formatDate  pattern="yyyy-MM-dd" type="date" value="${troMan.rectificationTerm}" /></td>
				</tr>
				
				<tr>
					<th width="15%">是否上报安监局</th>
					<td width="35%"><cus:hxlabel  codeName="是或否" itemValue="${troMan.ifReportAwh}" /></td>
			
				</tr>
				<tr>
					<th width="15%">隐患地点</th>
					<td width="35%">${troMan.troubleAdd}</td>
					<th width="15%">整改责任部门</th>
					<td width="35%">${troMan.rectDept}</td>
				</tr>
				<tr>
					<th width="15%">整改责任人</th>
					<td width="35%">${troMan.rectPerson}</td>
					<th width="15%">整改责任人联系方式</th>
					<td width="35%">${troMan.rectTel}</td>
				</tr>
				<tr>
					<th width="15%">隐患详情</th>
					<td width="35%" colspan="3">
						${troMan.introduce}
					</td>
				</tr>
				<tr>
					<th width="15%">整改前图片</th>
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
				<c:forEach var="rect" items="${rectInfos}">
					<c:if test="${rect.state=='整改完成' }">
						<tr>
							<th width="15%">整改完成时间</th>
							<td width="35%" >
								${rect.rectTime }
							</td>
							<th width="15%">整改资金（元）</th>
							<td width="35%" >${rect.money }</td>
						</tr>
					</c:if>
						<tr>
							<th width="15%">整改结果</th>
							<td width="35%" >${rect.state }</td>
							<th width="15%">备注</th>
							<td width="35%" >${rect.remark }</td>
						</tr>
					<tr>
					<th width="15%">整改图片</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="troManRect">
							  <c:forEach var="item" items="${rect.photos}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
											||fn:endsWith(fn:toLowerCase(item.picName),'.png')
											||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
												<a href="${item.httpUrl}/upload/photo/${item.picName}" rel="example_group">	
													<img src="${item.httpUrl}/upload/photo/${item.picName}" href="${item.httpUrl}/upload/photo/${item.picName}" rel="example_group"	
													border='0' width='220' height='150'/>
												</a>
											</c:when> 
											<c:otherwise> 
												&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:otherwise>
										</c:choose>
								   </td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								  </td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(checkRecords) >0}">
				<tr>
					<th width="15%">审核记录</th>
					<td width="96%" colspan="3">
						<c:forEach items="${checkRecords }" var="cr">
							<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
						</c:forEach>
					
					</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_troMan');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>

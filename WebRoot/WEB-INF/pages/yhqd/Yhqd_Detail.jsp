<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>隐患查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">被检查单位名称</th>
					<td width="35%">${yhqd.basic.company.companyname}</td>
					<th width="15%">检查时间</th>
					<td width="35%"><fmt:formatDate type="both" value="${yhqd.checktime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">隐患内容</th>
					<td colspan="3"><div readOnly name="yhqd.yhContent" style="width:100%;height:120px">${yhqd.yhContent}</div></td>
				</tr>
				<tr>
					<th width="15%">监管部门跟踪责任人</th>
					<td width="35%">${yhqd.jgzrrNames}</td>
					<th width="15%">整改期限</th>
					<td width="35%"><fmt:formatDate type="both" value="${yhqd.zgqx}"pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">企业责任人</th>
					<td width="35%">${yhqd.qyzrr}</td>
					<th width="15%">企业责任人联系电话</th>
					<td width="35%">${yhqd.qyzrrlxdh}</td>
				</tr>
				<tr>
					<th width="15%">所需资金</th>
					<td width="35%">${yhqd.sxzj}</td>
				</tr>
				<tr> 
					<th width="15%">
						已传附件
					</th>
					<td colspan="3"> 
						 <table>
						 <!-- 迭代附件 -->
						 <s:iterator id="photoPic" value="#request.yhqd.picList" status="cst">
							<tr>
								<td width="40%">
										<c:choose>
											<c:when
												test="${fn:endsWith(fn:toLowerCase(picName),'.jpg')
										    	||fn:endsWith(fn:toLowerCase(picName),'.bmp')
										    	||fn:endsWith(fn:toLowerCase(picName),'.png')
										    	||fn:endsWith(fn:toLowerCase(picName),'.jpeg')
										    	||fn:endsWith(fn:toLowerCase(picName),'.gif')}"> 
											  	  &nbsp;&nbsp;&nbsp;
											    	 <a href="/upload/photo/${picName}"
													rel="example_group"> <img
														src="/upload/photo/${picName}" border='0' width='220'
														height='150' /> </a>
											</c:when>
											<c:otherwise> 
									     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;${fileName}</FONT>
											</c:otherwise>
										</c:choose>
								</td>
								<td width="30%">
									 ${taskRemark} 
								</td>
							</tr>
						</s:iterator>
						 </table>
					</td>
				</tr>
				<tr>
					<th width="15%">整改方案</th>
					<td colspan="3"><div readOnly name="yhqd.zgfa" style="width:100%;height:120px">${yhqd.zgfa}</div></td>
				</tr>
				<tr>
					<th width="15%">防范措施</th>
					<td colspan="3"><div readOnly name="yhqd.ffcs" style="width:100%;height:120px">${yhqd.ffcs}</div></td>
				</tr>
				<tr>
					<th width="15%">当前状态  </th>
					<td width="35%" colspan="3"> 
							<s:if test="yhqd.dealFlag == 0"> 
							待处理
							</s:if>
							<s:elseif test="yhqd.dealFlag == 1">  
								<s:if test="yhqd.passFlag == 0">驳回待处理</s:if>
								<s:elseif test="yhqd.passFlag == 1">整改通过验收</s:elseif>
								<s:else>已处理待验收</s:else>
							</s:elseif>
					</td>
				</tr>
				<tr>
					<th width="15%">整改完说明</th>
					<td colspan="3"><div readOnly name="yhqd.zgInfo" style="width:100%;height:120px">${yhqd.zgInfo}</div></td>
				</tr>
				<tr> 
					<th width="15%">
						整改附件
					</th>
					<td colspan="3"> 
						 <table>
						 <!-- 迭代附件 -->
						 <s:iterator id="photoPic" value="#request.yhqd.zgpicList" status="cst">
							<tr>
								<td width="40%">
										<c:choose>
											<c:when
												test="${fn:endsWith(fn:toLowerCase(picName),'.jpg')
										    	||fn:endsWith(fn:toLowerCase(picName),'.bmp')
										    	||fn:endsWith(fn:toLowerCase(picName),'.png')
										    	||fn:endsWith(fn:toLowerCase(picName),'.jpeg')
										    	||fn:endsWith(fn:toLowerCase(picName),'.gif')}"> 
											  	  &nbsp;&nbsp;&nbsp;
											    	 <a href="/upload/photo/${picName}"
													rel="example_group"> <img
														src="/upload/photo/${picName}" border='0' width='220'
														height='150' /> </a>
											</c:when>
											<c:otherwise> 
									     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;${fileName}</FONT>
											</c:otherwise>
										</c:choose>
								</td>
								<td width="30%">
									 ${taskRemark} 
								</td>
							</tr>
						</s:iterator>
						 </table>
					</td>
				</tr>
				<tr>
					<th>历史流程</th>
					<td colspan="3" style="padding:0px">
					<c:if test="${empty yhqd.zgysr}">无</c:if>
					<c:if test="${not empty yhqd.zgysr}"> 
					<s:generator val="yhqd.zgysr" separator="‖" id="zgysrList"/> 
					<c:set value="${ fn:split(yhqd.yssj, '‖') }" var="yssjList" />
					<c:set value="${ fn:split(yhqd.resultContent, '‖') }" var="resultContentList" />
					<table>
					<tr>
						<th style="text-align: left;"> 验收人</th>
						<th style="text-align: left;"> 验收时间</th>
						<th style="text-align: left;"> 结论</th>
					</tr>
					<s:iterator var="zgysr"  value="zgysrList" status="st"   >
					<tr>
						<td> ${zgysr}</td>
						<td> ${yssjList[st.index]}</td>
						<td> ${resultContentList[st.index]}</td>
					</tr>
					</s:iterator>
					</table>
					</c:if>
					</td>
				</tr>
				<%-- <tr>
					<th width="15%">整改验收人</th>
					<td width="35%">${yhqd.zgysr}</td>
					<th width="15%">验收时间</th>
					<td width="35%">${yhqd.yssj} </td>
				</tr>
				<tr>
					<th width="15%">结论</th>
					<td colspan="3"><textarea readOnly name="yhqd.resultContent" style="width:100%;height:120px">${yhqd.resultContent}</textarea></td>
				</tr> --%>
				<tr>
					<td colspan="4" height="100px" align="center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close()||parent.close_win()">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

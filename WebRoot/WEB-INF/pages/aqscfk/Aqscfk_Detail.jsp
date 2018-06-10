<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.css" media="screen" />
	<script type="text/javascript">
		$(document).ready(function() {
			$("a[rel=example_group]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over'
			});

		});
		function savepic(i){
        	window.location.href="${ctx}/jsp/aqscfk/aqscfkSaveFile.action?picName="+i;
		}
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">${aqscfk.szzname}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${aqscfk.qymc}</td>
				</tr>
				<tr>
					<th width="15%">执法文书号</th>
					<td width="35%">${aqscfk.zfwsh}</td>
					<th width="15%">处罚金额（万元）</th>
					<td width="35%">${aqscfk.cfje}</td>
				</tr>
				<tr>
					<th width="15%">罚款分类</th>
					<td id="showTd1" width="35%">
						<s:if test="aqscfk.fkfl == 0">
							事故处罚
						</s:if>
						<s:else>
							监督监察处罚
						</s:else>
					
					</td>
					
					<s:if test="aqscfk.fkfl == 0">
						 
					
						<th width="15%" id="showTh1">事故分类</th>
						<td width="35%" id="showTd2">
							<s:if test="aqscfk.sgfl == 0">
								一般事故
							</s:if>
							<s:elseif test="aqscfk.sgfl == 1" >
								较大事故
							</s:elseif>
							<s:elseif test="aqscfk.sgfl == 2" >
								重大事故
							</s:elseif>
							<s:elseif test="aqscfk.sgfl == 3" >
								特大事故
							</s:elseif>
						</td>
					
					</s:if>
						
					
				</tr>
				
				
				<tr>
					<th width="15%">企业类型</th>
					<td width="35%">
						<s:if test="aqscfk.companyType == 0">
							化工生产企业
						</s:if>
						<s:elseif test="aqscfk.companyType == 1" >
							危化品经营企业
						</s:elseif>
						<s:elseif test="aqscfk.companyType == 2" >
							烟花爆竹企业
						</s:elseif>
						<s:elseif test="aqscfk.companyType == 3" >
							职业危害企业
						</s:elseif>
						<s:elseif test="aqscfk.companyType == 4" >
							其它工贸企业
						</s:elseif>
						<s:elseif test="aqscfk.companyType == 5" >
							其它
						</s:elseif>
					
					</td>
					<th width="15%">行政处罚对象</th> 
					<td width="35%">
					
					<s:if test="aqscfk.objectType == 0">
							生产经营单位
						</s:if>
						<s:elseif test="aqscfk.objectType == 1" >
							生产经营单位主要负责人
						</s:elseif>
						 
		 			</td>
				</tr>
				
				<tr>
					<th width="15%">实际收缴罚款（万元）</th>
					<td width="35%">${aqscfk.sjfk}</td>
					<th width="15%">责令停产停业整顿生产经营单位</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${aqscfk.zltc}" /></td>
				</tr>
				
				<tr>
					<th width="15%">执法文书类型</th>
					<td width="85%" colspan="3"> 
					<cus:hxmulselectlabel codeName="执法文书类型" itemValue="${aqscfk.fileType}" />
				
				 </td>
				</tr>
				
				
				<tr>
					<th width="15%">处罚事由</th>
					<td width="85%" colspan="3"><textarea readOnly name="aqscfk.cfsy" style="width:100%;height:120px">${aqscfk.cfsy}</textarea></td>
				</tr>
				<tr>
					<th width="15%">执法文书</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									<s:iterator id="taskPic" value="%{picList1}" status="sta">
									<tr>
										<td width="70%">
												<c:choose>
													<c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
														&nbsp;&nbsp;&nbsp;
														<a href="/upload/file/${taskPic.picName}" rel="example_group">	
														<img src="/upload/file/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>')">下载</a>&nbsp;&nbsp;
												</td>
										 </tr>
									</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="15%">取证材料</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									<s:iterator id="taskPic" value="%{picList2}" status="sta">
										<tr>
										<td width="70%">
												<c:choose>
													<c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
														&nbsp;&nbsp;&nbsp;
														<a href="/upload/file/${taskPic.picName}" rel="example_group">	
														<img src="/upload/file/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>')">下载</a>&nbsp;&nbsp;
												</td>
										 </tr>
									</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">调查报告</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									<s:iterator id="taskPic" value="%{picList3}" status="sta">
										<tr>
										<td width="70%">
												<c:choose>
													<c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
														&nbsp;&nbsp;&nbsp;
														<a href="/upload/file/${taskPic.picName}" rel="example_group">	
														<img src="/upload/file/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>')">下载</a>&nbsp;&nbsp;
												</td>
										 </tr>
									</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">行政处罚告知书</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									<s:iterator id="taskPic" value="%{picList4}" status="sta">
										<tr>
										<td width="70%">
												<c:choose>
													<c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
														&nbsp;&nbsp;&nbsp;
														<a href="/upload/file/${taskPic.picName}" rel="example_group">	
														<img src="/upload/file/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>')">下载</a>&nbsp;&nbsp;
												</td>
										 </tr>
									</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">陈述人笔录</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									<s:iterator id="taskPic" value="%{picList5}" status="sta">
										<tr>
										<td width="70%">
												<c:choose>
													<c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
														&nbsp;&nbsp;&nbsp;
														<a href="/upload/file/${taskPic.picName}" rel="example_group">	
														<img src="/upload/file/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>')">下载</a>&nbsp;&nbsp;
												</td>
										 </tr>
									</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">行政处罚决定书</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									<s:iterator id="taskPic" value="%{picList6}" status="sta">
										<tr>
										<td width="70%">
												<c:choose>
													<c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
														&nbsp;&nbsp;&nbsp;
														<a href="/upload/file/${taskPic.picName}" rel="example_group">	
														<img src="/upload/file/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>')">下载</a>&nbsp;&nbsp;
												</td>
										 </tr>
									</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">处罚决定书回证</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									<s:iterator id="taskPic" value="%{picList7}" status="sta">
										<tr>
										<td width="70%">
												<c:choose>
													<c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
														&nbsp;&nbsp;&nbsp;
														<a href="/upload/file/${taskPic.picName}" rel="example_group">	
														<img src="/upload/file/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>')">下载</a>&nbsp;&nbsp;
												</td>
										 </tr>
									</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">结案审批材料</th>
					<td width="85%" colspan="3">
					  	<div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									<s:iterator id="taskPic" value="%{picList8}" status="sta">
										<tr>
										<td width="70%">
												<c:choose>
													<c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
														||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
														&nbsp;&nbsp;&nbsp;
														<a href="/upload/file/${taskPic.picName}" rel="example_group">	
														<img src="/upload/file/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>')">下载</a>&nbsp;&nbsp;
												</td>
										 </tr>
									</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">审核记录</th>
					<td width="85%" colspan=3>
						<table>
							<s:iterator  value="shenheList" status="sta">
		    					<tr>
		    	 					<th> ${sta.index+1}</th>
		    	 					<td><s:property  /></td>
		    					</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">结案时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${aqscfk.jasj}" /></td>
					<th width="15%">审核结果</th>
					<td width="35%">
						<s:if test="aqscfk.state == 1">
							待审核
						</s:if>
						<s:elseif test="aqscfk.state == 2">
							审核不通过
						</s:elseif>
						<s:else>
							审核通过
						</s:else>
						
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="85%" colspan=3>
						<textarea id="remark" name="aqscfk.remark" rows="5" style="width: 80%" readonly="readonly">${aqscfk.remark}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

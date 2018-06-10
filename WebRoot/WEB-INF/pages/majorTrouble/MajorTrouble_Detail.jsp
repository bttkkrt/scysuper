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
	 function savepic(i,j){
        		window.location.href="majorTroubleSaveFile.action?picName="+i+"&fileName="+j;
			}
	 function updateStatus(){
	    	document.myform.action="majorTroubleUpdateStatus.action";
			document.myform.submit();
	    }
	 </script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
	<input type="hidden" id="cid" name="majorTrouble.id" value="${majorTrouble.id}">
		<div class="submitdata" style="overflow:auto;">
			<table width="100%">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%"><cus:hxlabel codeName="相城地址" itemValue="${majorTrouble.szz}" /></td>
					<th width="15%">填报部门</th>
					<td width="35%"><cus:hxlabel codeName="填报部门" itemValue="${majorTrouble.tbbm}" /></td>
				</tr>
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%">${majorTrouble.qymc}</td>
					<th width="15%">挂牌时间</th>
					<td width="35%">${majorTrouble.gpsj}</td>
				</tr>
				<tr>
					<th width="15%">隐患名称</th>
					<td width="35%">${majorTrouble.yhmc}</td>
					<th width="15%">隐患数</th>
					<td width="35%">${majorTrouble.yhs}</td>
				</tr>
				<tr>
					<th width="15%">整改数</th>
					<td width="35%">${majorTrouble.zgs}</td>
					<th width="15%">责任人</th>
					<td width="35%">${majorTrouble.zrr}</td>
				</tr>
				<tr>
					<th width="15%">整改资金</th>
					<td width="35%">${majorTrouble.zgzj}</td>
					<th width="15%">整改完成日期</th>
					<td width="35%">${majorTrouble.zgwcrq}</td>
				</tr>
				<tr>
					<th width="15%">验收人</th>
					<td width="35%">${majorTrouble.ysr}</td>
					<th width="15%">验收时间</th>
					<td width="35%">${majorTrouble.yssj}</td>
				</tr>
				<tr>
					<th width="15%">文书文号01</th>
					<td width="35%">${majorTrouble.wswh01}</td>
					<th width="15%">文书文号02</th>
					<td width="35%">${majorTrouble.wswh02}</td>
				</tr>
				<tr>
					<th width="15%">执法文书1</th>
					<td width="35%">
						  <div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
							   
									    <s:iterator id="taskPic" value="%{zfwsList01}" status="sta">
											 <tr>
											<td width="70%">
													<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    	<a href="/upload/photo/${taskPic.picName}" rel="example_group">
															    <img src="/upload/photo/${taskPic.picName}"
																		border='0' width='220' height='150'/>
																		</a>
															    </c:when>
															     <c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													    		</c:otherwise>
														   </c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
												</td>
											</tr>
										</s:iterator>
							    
							</table>
						</div>
					</td>
					<th width="15%">执法文书2</th>
					<td width="35%">
						  <div style="color:green;overflow:auto; height:200px;">
						   <table width="100%">
									    <s:iterator id="taskPic" value="%{zfwsList02}" status="sta">
											 <tr>
											<td width="70%">
													<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    	<a href="/upload/photo/${taskPic.picName}" rel="example_group">
															    <img src="/upload/photo/${taskPic.picName}"
																		border='0' width='220' height='150'/>
																		</a>
															    </c:when>
															     <c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													    		</c:otherwise>
														   </c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
												</td>
											</tr>
										</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">整改方案</th>
					<td width="35%">
						  <div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									    <s:iterator id="taskPic" value="%{picList01}" status="sta">
											 <tr>
											<td width="70%">
													<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    	<a href="/upload/photo/${taskPic.picName}" rel="example_group">
															    <img src="/upload/photo/${taskPic.picName}"
																		border='0' width='220' height='150'/>
																		</a>
															    </c:when>
															     <c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													    		</c:otherwise>
														   </c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
												</td>
											</tr>
										</s:iterator>
							</table>
						</div>
					</td>
					<th width="15%">监控措施</th>
					<td width="35%">
						 <div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									    <s:iterator id="taskPic" value="%{picList02}" status="sta">
											 <tr>
											<td width="70%">
													<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    	<a href="/upload/photo/${taskPic.picName}" rel="example_group">
															    <img src="/upload/photo/${taskPic.picName}"
																		border='0' width='220' height='150'/>
																		</a>
															    </c:when>
															     <c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													    		</c:otherwise>
														   </c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
												</td>
											</tr>
										</s:iterator>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="15%">整改前图片</th>
					<td width="35%">
						 <div style="color:green;overflow:auto;height:200px;">
						    <table width="100%">
									    <s:iterator id="taskPic" value="%{picList03}" status="sta">
											 <tr>
											<td width="70%">
													<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    	<a href="/upload/photo/${taskPic.picName}" rel="example_group">
															    <img src="/upload/photo/${taskPic.picName}"
																		border='0' width='220' height='150'/>
																		</a>
															    </c:when>
															     <c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													    		</c:otherwise>
														   </c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
												</td>
											</tr>
										</s:iterator>
							</table>
						</div>
					</td>
					<th width="15%">整改后图片</th>
					<td width="35%">
						 <div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
									    <s:iterator id="taskPic" value="%{picList04}" status="sta">
											 <tr>
											<td width="70%">
													<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    	<a href="/upload/photo/${taskPic.picName}" rel="example_group">
															    <img src="/upload/photo/${taskPic.picName}"
																		border='0' width='220' height='150'/>
																		</a>
															    </c:when>
															     <c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													    		</c:otherwise>
														   </c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
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
				<s:if test="type == 2 and majorTrouble.shzt == 3 ">
					<tr>
						<th width="15%">审核状态</th>
						<td width="35%">
							<s:select name="majorTrouble.shzt" list="#{'1':'审核通过','2':'审核未通过'}" theme="simple"/>
						</td>
					</tr>
					<tr>
						<th width="15%">审核备注</th>
						<td colspan="3"><textarea  name="majorTrouble.remark" style="width:100%;height:120px"></textarea></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td colspan="4" height="100px" align="center" style="text-align:center;">
					   	  	<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="updateStatus();">保存</a>
					   	  	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<th width="15%">审核状态</th>
						<td width="35%">
							<s:if test="majorTrouble.shzt == 0">待整改</s:if>
							<s:if test="majorTrouble.shzt == 1">审核通过</s:if>
							<s:if test="majorTrouble.shzt == 2">审核未通过</s:if>
							<s:if test="majorTrouble.shzt == 3">已整改待审核</s:if>
						</td>
					</tr>
					<tr>
						<th width="15%">审核备注</th>
						<td colspan="3"><textarea readOnly name="majorTrouble.remark" style="width:100%;height:120px">${majorTrouble.remark}</textarea></td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td colspan="4" height="100px" align="center" style="text-align:center;">
					   	  	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
						</td>
					</tr>
				</s:else>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

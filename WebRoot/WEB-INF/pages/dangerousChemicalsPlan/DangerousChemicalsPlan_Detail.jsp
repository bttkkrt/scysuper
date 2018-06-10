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
        		window.location.href="dangerousChemicalsPlanSaveFile.action?picName="+i+"&fileName="+j;
			}
	 </script>
</head>
<body>
	<div style="position: absolute;width: 80%; left: 10%">
			<table width="100%">
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 42.0pt;">
						抚顺市安全生产监督管理局
					</td>
				</tr>
				<tr>
					<td>
						<hr style="height:5px;border:none;border-top:5px double;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 22.0pt">
						危险化学品重大危险源通知书
					</td>	
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
						${dangerousChemicalsPlan.companyName}:
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
						${dangerousChemicalsPlan.userName}
					</td>
				</tr>
				<tr>
					<td height="20px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
						联系人：${dangerousChemicalsPlan.contact}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话：${dangerousChemicalsPlan.tel}
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					  危险源级别：${dangerousChemicalsPlan.dangerLevel}
					</td>
				</tr>
				<<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						危险化学品单位类型：<cus:hxmulselectlabel codeName="危险化学品单位类型" itemValue="${dangerousChemicalsPlan.dangerType}" />
				   </td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						备案日期：${dangerousChemicalsPlan.planDate}&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						备案编号：${dangerousChemicalsPlan.planId}
					</td>
				</tr>
				<tr>
					<td>
						危险化学品重大危险源基本特征表
					</td>
				</tr>
				<tr>
					<td>
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
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
												</td>
											 </tr>
										</s:iterator>
							   
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						危险化学品重大危险源备案申请表
					</td>
				</tr>
				<tr>
					<td>
					   <div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
							 <s:iterator id="taskPic" value="%{picList02}" status="sta">
									   <tr>
											<td>
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
											<td>
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
												</td>
											 </tr>
										</s:iterator>
							   
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						危险化学品重大危险源备案登记表
					</td>
				</tr>
				<tr>
					<td>
					   <div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
							 <s:iterator id="taskPic" value="%{picList03}" status="sta">
									   <tr>
											<td>
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
											<td>
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
												</td>
											 </tr>
										</s:iterator>
							   
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋_GB2312;font-size: 16.0pt">
						（承办机构盖章）
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋_GB2312;font-size: 16.0pt">
						${dangerousChemicalsPlan.sendTime}&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td height="100px" style="text-align:center">
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
</body>
</html>

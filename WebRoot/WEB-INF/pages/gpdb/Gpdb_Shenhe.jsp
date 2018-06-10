<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>审核</title>
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
		function save(){
			var state = document.getElementById('state').value;
			var remark = document.getElementById('remark').value;
			if(state == "2" && remark == "")
			{
				alert("审核不通过必须注明理由！");
			}
			else
			{
				document.myform.action="${ctx}/jsp/gpdb/gpdbShenheSave.action";
				document.myform.submit();
			}
		}
		
		function savepic(i){
        	window.location.href="${ctx}/jsp/gpdb/gpdbSaveFile.action?picName="+i;
		}
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
	<s:token />
		<input type="hidden" name="gpdb.id" value="${gpdb.id}">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">${gpdb.szzname}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${gpdb.qymc}</td>
				</tr>
				<tr>
					<th width="15%">隐患名称</th>
					<td width="35%">${gpdb.yhmc}</td>
					<th width="15%">挂牌时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${gpdb.gpsj}" /></td>
				</tr>
				<tr>
					<th width="15%">隐患类别</th>
					<td width="35%"><cus:hxlabel codeName="隐患类别" itemValue="${gpdb.yhlb}" /></td>
					<th width="15%">隐患数</th>
					<td width="35%">${gpdb.yhs}</td>
				</tr>
				<tr>
					<th width="15%">检查文书</th>
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
														<a href="/upload/photo/${taskPic.picName}" rel="example_group">
														<img src="/upload/photo/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</a>
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
					<th width="15%">整改前图片</th>
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
														<a href="/upload/photo/${taskPic.picName}" rel="example_group">
														<img src="/upload/photo/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</a>
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
					<th width="15%">责任人</th>
					<td width="35%">${gpdb.zrr}</td>
					<th width="15%">整改资金（万元）</th>
					<td width="35%">${gpdb.zgzj}</td>
				</tr>
				<tr>
					<th width="15%">整改完成时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${gpdb.zgwcsj}" /></td>
					<th width="15%">验收时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${gpdb.yssj}" /></td>
				</tr>
				<tr>
					<th width="15%">复查文书</th>
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
														<a href="/upload/photo/${taskPic.picName}" rel="example_group">
														<img src="/upload/photo/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</a>
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
					<th width="15%">整改方案</th>
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
														<a href="/upload/photo/${taskPic.picName}" rel="example_group">
														<img src="/upload/photo/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</a>
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
					<th width="15%">监控措施</th>
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
														<a href="/upload/photo/${taskPic.picName}" rel="example_group">
														<img src="/upload/photo/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</a>
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
					<th width="15%">整改后图片</th>
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
														<a href="/upload/photo/${taskPic.picName}" rel="example_group">
														<img src="/upload/photo/${taskPic.picName}"
														border='0' width='220' height='150'/>
														</a>
													</c:when>
													<c:otherwise> 
													     &nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													</c:otherwise>
												</c:choose>
											</a>
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
					<th width="15%">隐患整改数</th>
					<td width="35%">${gpdb.yhzgs}</td>
					<th width="15%">审核结果</th>
					<td width="35%">
						<s:select id="state" listKey="key" listValue="value"  theme="simple" list="#{'3':'审核通过','2':'审核不通过'}" name="gpdb.state" value="{gpdb.state}"/>
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
					<th width="15%">备注</th>
					<td width="85%" colspan=3>
						<textarea id="remark" name="gpdb.remark" rows="5" style="width: 80%">${gpdb.remark}</textarea>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">确定</a>&nbsp;
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

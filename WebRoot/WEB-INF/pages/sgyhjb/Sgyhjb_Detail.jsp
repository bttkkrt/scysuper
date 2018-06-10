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
        		window.location.href="${ctx}/jsp/sgyhjb/sgyhjbSaveFile.action?picName="+i;
			}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">举报人</th>
					<td width="35%">${sgyhjb.jbr}</td>
					<th width="15%">举报人电话</th>
					<td width="35%">${sgyhjb.jbrdh}</td>
				</tr>
				<tr>
					<th width="15%">举报所在地</th>
					<td width="85%" colspan="3">${sgyhjb.jbszd}</td>
				</tr>
				<tr>
					<th width="15%">收信时间</th>
					<td width="35%">${sgyhjb.sxsj}</td>
					<th width="15%">隐患等级</th>
					<td width="35%"><cus:hxlabel codeName="隐患等级" itemValue="${sgyhjb.yhjb}" /></td>
				</tr>
				<tr>
					<th width="15%">举报内容</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.jbnr" style="width:80%;height:120px" disabled>${sgyhjb.jbnr}</textarea></td>
				</tr>
				<tr>
					<th width="15%">举报编号</th>
					<td width="35%">${sgyhjb.jbbh}</td>
					<th width="15%">来电类别</th>
					<td width="35%"><cus:hxlabel codeName="来电类别" itemValue="${sgyhjb.ldlb}" /></td>
				</tr>
				<tr>
					<th width="15%">举报所在镇</th>
					<td width="35%"><cus:hxlabel codeName="相城地址" itemValue="${sgyhjb.jbszz}" /></td>
					<th width="15%">举报企业或其它</th>
					<td width="35%">${sgyhjb.jbqy}</td>
				</tr>
				<tr>
					<th width="15%">首次举报</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${sgyhjb.scjb}" /></td>
					<td width="50%" colspan="2">
						<table id="tt" <s:if test="sgyhjb.scjb != 0">style="display:none"</s:if>>
							<th width="15%">举报次数</th>
							<td width="35%">${sgyhjb.jbcs}</td>
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">记录人</th>
					<td width="35%">${sgyhjb.jlr}</td>
					<th width="15%">来自何部门</th>
					<td width="35%">${sgyhjb.lzbm}</td>
				</tr>
				<tr>
					<th width="15%">交办时间</th>
					<td width="35%">${sgyhjb.jbsj}</td>
					<th width="15%">要求办结时间</th>
					<td width="35%">${sgyhjb.yqbjsj}</td>
				</tr>
				<tr>
					<th width="15%">领导批示</th>
					<td width="35%">${sgyhjb.ldps}</td>
					<th width="15%">领导批示时间</th>
					<td width="35%">${sgyhjb.ldpssj}</td>
				</tr>
				<tr>
					<th width="15%">分管领导意见</th>
					<td width="35%">${sgyhjb.fgldyj}</td>
					<th width="15%">分管领导批示时间</th>
					<td width="35%">${sgyhjb.fgldpssj}</td>
				</tr>
				<tr>
					<th width="15%">交办部门</th>
					<td width="35%">${sgyhjb.jbbmname}</td>
					<th width="15%">交办部门接收时间</th>
					<td width="35%">${sgyhjb.jbbmjssj}</td>
				</tr>
				<s:if test="sgyhjb.state != 0">
				<tr>
					<th width="15%">交办部门处理情况</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.jbbmclqk" style="width:80%;height:120px" disabled>${sgyhjb.jbbmclqk}</textarea></td>
				</tr>
				<tr>
					<th width="15%">交办部门处理时间</th>
					<td width="35%">
						${sgyhjb.jbbmclsj}
					</td>
					<th width="15%">处理部门</th>
					<td width="35%">
						${sgyhjb.clbmname}
					</td>
				</tr>
				<tr>
					<th width="15%">处理部门接收时间</th>
					<td width="35%">
						${sgyhjb.clbmjssj}
					</td>
				</tr>
				</s:if>
				<s:if test="sgyhjb.state != 0 && sgyhjb.state != 1">
				<tr>
					<th width="15%">处理结果</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.cljg" style="width:80%;height:120px" disabled>${sgyhjb.cljg}</textarea></td>
				</tr>
				<tr>
					<th width="15%">处理人</th>
					<td width="35%">${sgyhjb.clr}</td>
					<th width="15%">处理时间</th>
					<td width="35%">
						${sgyhjb.clsj}
					</td>
				</tr>
				<tr>
					<th width="15%">隐患照片</th>
					<td width="85%" colspan="3">
									<div style="color:green;overflow:auto;height:200px;">
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
																    	<a href="/upload/photo/${item.picName}" rel="example_group">
																    	<img src="/upload/photo/${item.picName}"
																			border='0' width='220' height='150'/></a>
																    </c:when> 
																    <c:otherwise> 
																     ${item.fileName}
																    </c:otherwise>
															   </c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}');">下载</a>
															</td>
														</tr>
													</c:forEach>
											</table>
									</div>
					</td>
				</tr>
				<tr>
					<th width="15%">整改完成时间</th>
					<td width="35%">
						${sgyhjb.zgwcsj}
					</td>
					<th width="15%">奖励金额</th>
					<td width="35%">${sgyhjb.jlje}</td>
				</tr>
				<tr>
					<th width="15%">整改后照片</th>
					<td width="85%" colspan="3">
									<div style="color:green;overflow:auto;height:200px;">
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
																    	<a href="/upload/photo/${item.picName}" rel="example_group">
																    	<img src="/upload/photo/${item.picName}"
																			border='0' width='220' height='150'/></a>
																    </c:when> 
																    <c:otherwise> 
																     ${item.fileName}
																    </c:otherwise>
															   </c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}');">下载</a>
															</td>
														</tr>
													</c:forEach>
											</table>
									</div>
					</td>
				</tr>
				</s:if>
				<s:if test="sgyhjb.state != 0 && sgyhjb.state != 1 && sgyhjb.state != 2">
				<tr>
					<th width="15%">交办部门负责人</th>
					<td width="35%">${sgyhjb.jbbmfzr}</td>
					<th width="15%">审查时间</th>
					<td width="35%">
						${sgyhjb.jbbmyjsj}
					</td>
				</tr>
				<tr>
					<th width="15%">交办部门意见</th>
					<td width="85%" colspan="3"><textarea name="sgyhjb.jbbmyj" style="width:80%;height:120px" disabled>${sgyhjb.jbbmyj}</textarea></td>
				</tr>
				</s:if>
				<s:if test="sgyhjb.state == 5">
				<tr>
					<th width="15%">审查结果</th>
					<td width="35%"><cus:hxlabel codeName="审核结果" itemValue="${sgyhjb.scjg}" /></td>
					<th width="15%">得分</th>
					<td width="35%"><cus:hxlabel codeName="得分" itemValue="${sgyhjb.df}" /></td>
				</tr>
				</s:if>
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

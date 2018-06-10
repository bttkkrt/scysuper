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
        		window.location.href="zjjtzsbSavePic.action?picName="+i+"&fileName="+j;
			}
	 </script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">单位名称</th>
					<td width="35%">${zjjtzsb.qymc}</td>
					<th width="15%">单位地址</th>
					<td width="35%">${zjjtzsb.dwdz}</td>
				</tr>
				<tr>
					<th width="15%">所属地区</th>
					<td width="35%">${zjjtzsb.szzname}</td>
					<th width="15%">设备类别</th>
					<td width="35%">${zjjtzsb.sblb}</td>
				</tr>
				<tr>
					<th width="15%">设备档案号</th>
					<td width="35%">${zjjtzsb.sbdah}</td>
					<th width="15%">注册代码</th>
					<td width="35%">${zjjtzsb.zcdm}</td>
				</tr>
				<tr>
					<th width="15%">检验日期</th>
					<td width="35%">${zjjtzsb.jyrq}</td>
					<th width="15%">检验结论</th>
					<td width="35%">${zjjtzsb.jyjl}</td>
				</tr>
				<tr>
					<th width="15%">检查日期</th>
					<td width="35%">${zjjtzsb.jcrq}</td>
					<th width="15%">检查人员</th>
					<td width="35%">${zjjtzsb.jcry}</td>
				</tr>
				<tr>
					
					<th width="15%">单位联系人</th>
					<td width="35%">${zjjtzsb.dwlxr}</td>
					<th width="15%">电话</th>
					<td width="35%">${zjjtzsb.dh}</td>
				</tr>
				<tr>
					<th width="15%">主要问题</th>
					<td width="85%" colspan="3">
						<textarea readOnly name="zjjtzsb.zywt" style="width:100%;height:120px">${zjjtzsb.zywt}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3">
						<textarea readOnly name="zjjtzsb.bz" style="width:100%;height:120px">${zjjtzsb.bz}</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">整改情况</th>
					<td width="85%" colspan="3">
						<textarea readOnly name="zjjtzsb.zgqk" style="width:100%;height:120px">${zjjtzsb.zgqk}</textarea>
					</td>					
				</tr>
				<tr>
					<th width="15%">整改后图片</th>
					<td width="85%" colspan="3">
						 <div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
							    
									    <s:iterator id="taskPic" value="%{picList}" status="sta">
											<tr>
											<td width="70%">
												<a href="/upload/photo/${taskPic.picName}" rel="example_group">
													<img src="/upload/photo/${taskPic.picName}" border='0' width='220' height='150'/>
												</a>
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
					<th width="15%">整改完成时间</th>
					<td width="35%">${zjjtzsb.zgwcsj}</td>
					<th width="15%">审核状态</th>
					<td width="35%">
						<s:if test="zjjtzsb.state == 0">
							待整改
						</s:if>
						<s:elseif test="zjjtzsb.state == 1">
							待核实
						</s:elseif>
						<s:elseif test="zjjtzsb.state == 2">
							待审核
						</s:elseif>
						<s:elseif test="zjjtzsb.state == 3">
							已办结
						</s:elseif>
						<s:else>
							整改不合格
						</s:else>
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="85%" colspan=3>
						<textarea readOnly name="zjjtzsb.remark" style="width:100%;height:120px">${zjjtzsb.remark}</textarea>
					</td>
				</tr>
				<tr>
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

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
        		window.location.href="${ctx}/ajj/jsp/safetysheet/safesheetDownFile.action?picName="+i+"&fileName="+j;
			}
				</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
				<th width="15%">所属街道</th>
					<td width="35%">${dailyinspection.szz}</td>
					<th width="15%">公司名称</th>
					<td width="35%">${dailyinspection.comname}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3"><textarea readOnly name="dailyinspection.remark" style="width:100%;height:120px">${dailyinspection.remark}</textarea></td>
				</tr>
				<tr>
						<th width="15%">相关图片</th>
					<td width="35%" colspan=3>
					
							<div style="color:green;overflow:auto; height:200px;">
										  <table id="aqjsbz" width="80%">
													<c:forEach var="item" items="${list}" varStatus="status"> 
													
													   <c:if test="${status.count eq 1 || (status.count-1) % 2 eq 0}">    
                                                	<tr id='${item.id}' style="text-align: center">  
                                                      </c:if>  
													
															<td width="70%">
																<a href="/upload/photo/${item.picName}" rel="example_group">	
																<img src="/upload/photo/${item.picName}"
															border='0' width='220' height='150'/>
																</a>
															</td>
															<td width="30%">
																<a style="color:red" href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
															</td>
															   <c:if test="${status.count % 2 eq 0 || status.count eq count}">    
                                                        </tr>    
                                                    </c:if>
													</c:forEach>
											</table></div>
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

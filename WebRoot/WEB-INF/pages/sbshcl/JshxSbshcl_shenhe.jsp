<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>信息审核</title>
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
		  function deleteImage()
        {
          var all=document.getElementsByName("choice");
          
          var picName="";
	for(var i=0;i<all.length;i++)
	{
		if(all[i].checked==true)
		{
			picName+=all[i].value+"|";
		}
	}
	if(picName!="")
	{
        picName = picName.substring(0, picName.length - 1);
        }
       if(picName == ""){
			   alert('至少选择一张删除！');
			}else{
			
			        if(window.confirm("确定要删除吗?")){
		               jQuery.ajax({
		                	url : "jshxSbshclImageDel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	picName : picName
		                    },
		                    error: function(){
		                    	alert('删除时出错0！');
		                    },
		                    success: function(data){
		                        if(data.result=='true'){		                      
		                        	window.location.href = window.location.href ;
		                        }else{
		                        	alert('删除时出错！');
		                        }
		                    }
		                });
			}
			}
        }
        
        function savepic(i,j){
        		window.location.href="jshxSbshclSaveFile.action?picName="+i+"&fileName="+j;
			}
	 </script>
	 <script type="text/javascript">
		 function save(){
				var state = document.getElementById('state').value;
				var remark = document.getElementById('shbs').value;
				if(state == "2" && remark == "")
				{
					alert("请填写不通过理由！");
				}
				else
				{
					document.myform.submit();
				}
			}
	 </script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form id="myform" name="myform" method="post" action="${ctx}/jsp/sbshcl/sbshClshenheSave.action">
		<s:token />
		<input type="hidden" name="jshxSbshcl.id" value="${jshxSbshcl.id}" id="id"/>
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">标题</th>
					<td colspan="3">${jshxSbshcl.jshxTitle}</td>
				</tr>
				<tr>
					<th width="15%">证书扫描件</th>
					<td colspan="3">
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
				
				<s:if test="jshxSbshcl.state!=1||jshxSbshcl.dutyFlag!=1" >
					<c:if test="${deptCodeLenth==6}">
					<c:if test="${ifzsqy==0}">
						<tr>
							<th width="15%" style="color:red"></th>
							<td width="100%" colspan="4">
								<font style="color:red">县级审核:</font>
								<c:if test="${xjshState==1}">
									&nbsp;&nbsp;&nbsp;通过
								</c:if>
								<c:if test="${xjshState==2}">
									&nbsp;&nbsp;&nbsp;未通过
								</c:if>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font style="color:red">备注:</font>&nbsp;&nbsp;&nbsp;${xjBack}
							</td>
						</tr>
					</c:if>
					
					<tr>
						<th width="15%" style="color:red"></th>
						<td width="100%" colspan="4">
							<font style="color:red">市级审核:</font><cus:SelectOneTag property="jshxSbshcl.state" defaultText='请选择' codeName="审核结果"  dataType="Require" msg="此项为必选" /><font color="color:red">*</font>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font><input id="shbs"
								name="jshxSbshcl.shbs" 
								style="width: 50%">
							</input><font style="color:red">*(若未通过，请填写原由。)</font>
						</td>
					</tr>
					</c:if>
					
					<c:if test="${ifzsqy==0}">
						<c:if test="${deptCodeLenth==9}">
					<tr>
						<th width="15%" style="color:red"></th>
						<td width="100%" colspan="4">
							<font style="color:red">县级审核:</font><cus:SelectOneTag property="jshxSbshcl.state" defaultText='请选择' codeName="审核结果"  dataType="Require" msg="此项为必选" /><font color="color:red">*</font>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font><input id="shbs"
								name="jshxSbshcl.shbs"  
								style="width: 50%">
							</input><font style="color:red">*(若未通过，请填写原由。)</font>
						</td>
					</tr>
					
					</c:if>
					</c:if>
				
				</s:if>
				<!-- 
				<tr>
					<th width="15%">审核结果</th>
					<td width="85%">
						<s:select id="state" name="jshxSbshcl.state" list="#{'0':'通过','1':'不通过'}" theme="simple"/>
					</td>
				</tr>
				<tr>
					<th width="15%">审核备注</th>
					<td width="85%">
						<textarea id="shbs" name="jshxSbshcl.shbs" style="width:100%;height:120px">${jshxSbshcl.shbs}</textarea>
					</td>
				</tr>
				 -->
				<tr>
					<td colspan="4" height="100px" style="text-align:center;">
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

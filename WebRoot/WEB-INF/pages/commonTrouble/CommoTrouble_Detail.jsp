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
		                	url : "commoTroubleImageDel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	picName : picName
		                    },
		                    error: function(){
		                    	alert('删除时出错！');
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
        		window.location.href="commoTroubleSaveFile.action?picName="+i+"&fileName="+j;
			}
	    function updateStatus(){
	    	document.myform.action="commoTroubleUpdateStatus.action";
			document.myform.submit();
	    }
        </script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<input type="hidden" id="cid" name="commoTrouble.id" value="${commoTrouble.id}">
		<div class="submitdata" style="overflow:auto;">
			<table width="100%">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">${commoTrouble.county}${commoTrouble.szzname}</td>
					<th width="15%">检查企业名称</th>
					<td width="35%">${commoTrouble.qymc}</td>
				</tr>
				<tr>
					<th width="15%">检查时间</th>
					<td width="35%">${commoTrouble.jcsj}</td>
					<th width="15%">检查人员</th>
					<td width="35%">${commoTrouble.jcry}</td>
				</tr>
				<tr>
					<th width="15%">出动人次</th>
					<td width="35%">${commoTrouble.cdcs}</td>
					<th width="15%">隐患数</th>
					<td width="35%">${commoTrouble.yhs}</td>
				</tr>
				<tr>
					<th width="15%">重大隐患数</th>
					<td width="35%">${commoTrouble.zdyhs}</td>
					<th width="15%">执法文书</th>
					<td width="35%"><cus:hxlabel codeName="执法文书" itemValue="${commoTrouble.zfws}" /></td>
				</tr>
				<tr>	
					<th width="15%">执法文书号</th>
					<td width="35%">${commoTrouble.zfwsh}</td>
					<th width="15%">隐患内容</th>
					<td width="35%">${commoTrouble.yhnr}</td>
				</tr>
				<tr>
					<th width="15%">隐患整改数</th>
					<td width="35%">${commoTrouble.yhzgs}</td>
					<th width="15%">重大隐患整改数</th>
					<td width="35%">${commoTrouble.zdyhzgs}</td>
				</tr>
				<tr>
					<th width="15%">整改资金</th>
					<td width="35%">${commoTrouble.zgzj}</td>
					<th width="15%">整改完成时间</th>
					<td width="35%">${commoTrouble.zgwcsj}</td>
				</tr>
				
				<tr>
					<th width="15%">执法文书或图片</th>
					<td width="35%" colspan="3">
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
				</tr>
				
				<tr>
					<th width="15%">整改情况</th>
					<td width="35%" colspan="3">
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
							    </tr>
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
				<s:if test="type == 2 and commoTrouble.shzt == 3 ">
					<tr>
						<th width="15%">审核状态</th>
						<td width="35%">
							<s:select name="commoTrouble.shzt" list="#{'1':'审核通过','2':'审核未通过'}" theme="simple"/>
						</td>
					</tr>
					<tr>
						<th width="15%">审核备注</th>
						<td colspan="3"><textarea  name="commoTrouble.remark" style="width:100%;height:120px"></textarea></td>
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
							<s:if test="commoTrouble.shzt == 0">待整改</s:if>
							<s:if test="commoTrouble.shzt == 1">审核通过</s:if>
							<s:if test="commoTrouble.shzt == 2">审核未通过</s:if>
							<s:if test="commoTrouble.shzt == 3">已整改待审核</s:if>
						</td>
					</tr>
					<tr>
						<th width="15%">审核备注</th>
						<td colspan="3"><textarea readOnly name="commoTrouble.remark" style="width:100%;height:120px">${commoTrouble.remark}</textarea></td>
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
		
		<iframe name="demo" style="display:none"></iframe> 
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

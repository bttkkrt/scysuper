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
		                	url : "whpClscImageDel.action",
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
        		window.location.href="whpClscSaveFile.action?picName="+i+"&fileName="+j;
			}
		function updateStatus(){
			var state = document.getElementById('state').value;
			var remark =  document.getElementById('remark').value;
			if(state == "2" && remark == "")
			{
				alert("审核不通过必须填写审核备注！");
			}
			else
			{
				document.myform.action="whpClscUpdateStatus.action";
				document.myform.submit();
			}
	    }
	    
	    function showUpload(obj)
		{
			if(obj == '0')
			{
				document.getElementById("upload").style.display="";
				document.getElementById("isfinish").value="0";
			}
			else
			{
				document.getElementById("upload").style.display="none";
				document.getElementById("isfinish").value="1";
			}
			
		}
		
		function showop(obj)
		{
			if(obj == 1)
			{
				document.getElementById("op").style.display="";
			}
			else
			{
				document.getElementById("op").style.display="none";
			}
		}
		
		function init1()
        {
	        if($('#isCaiLiao').val()==1)
			{
			    document.getElementById("showCaiLiao").innerHTML="是";
			}else if($('#isCaiLiao').val()==0)
			{
			    document.getElementById("showCaiLiao").innerHTML="否";
			}
			
	        if($('#isCunChu').val()==1)
			{
			    document.getElementById("showCunChu").innerHTML="是";
			}else if($('#isCunChu').val()==0)
			{
			    document.getElementById("showCunChu").innerHTML="否";
			}
			
			
		}
	 </script>
</head>
<body onload="init1()">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
	<input type="hidden" id="cid" name="whpClsc.id" value="${whpClsc.id}">
	<input type="hidden" id="isfinish" name="whpClsc.isfinish" value="${whpClsc.isfinish}">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="30%" colspan="2">所在镇 </th>
					<td width="25%">${whpClsc.szzname}</td>
					<th width="20%">企业名称</th>
					<td width="25%">${whpClsc.qymc}</td>
				</tr>
				
				
				
				 
				
				
				<tr>
					<th width="30%" colspan="2">发证机关</th>
					<td width="70%"  colspan="3">
					${whpClsc.fzjg}
					</td>
				</tr>
					<tr>
					<th width="30%" colspan="2">备案日期</th>
					<td width="70%"  colspan="3">
					  <fmt:formatDate type="date" value="${whpClsc.barq}" pattern="yyyy-MM-dd"/> 
					</td>
				</tr>
				<tr>
					<th width="30%" colspan="2">有效期</th>
					<td width="70%"  colspan="3">
					 <fmt:formatDate type="date" value="${whpClsc.yxq}" pattern="yyyy-MM-dd"/> 
					</td>
				</tr>
			 
					<tr>
					<th width="30%" colspan="2">易制毒化学品</th>
					<td width="70%"  colspan="3">
					<cus:hxmulselectlabel codeName="易制毒化学品" itemValue="${whpClsc.yzdhxp}" />
					</td>
				</tr>
			
				<tr>	
					<th width="15%" colspan="2">材料接收人员</th>
					<td  colspan="3">
					 ${whpClsc.cljsry} 
					</td>
				</tr>
				
				<tr>	
					<th width="15%" colspan="2">材料审查人员</th>
					<td  colspan="3">
					 ${whpClsc.clscry} 
					</td>
				</tr>
				
				<tr>	
					<th width="15%" colspan="2">档案编号</th>
					<td  colspan="3">
					 ${whpClsc.fileId} 
					</td>
				</tr>
				<tr>
					<th width="15%"  colspan="2">申请材料是否齐全</th>
					<td colspan="3" id="showCaiLiao"></td>
				</tr>
				
				<tr>
					<th width="15%" colspan="2" >签字领导</th>
					<td colspan="3">
					${whpClsc.qzld}
					</td>
				</tr>
				
				
				
				
				<tr>
					<th width="15%"  colspan="2">材料审查情况</th>
					<td  colspan="3">
					 ${whpClsc.clscqk} 
					</td>
				 
				</tr>
				
				<tr>
					<th width="15%"  colspan="2">本次备案情况</th>
					<td  colspan="3">
					 ${whpClsc.bclzqk} 
					</td>
				 
				</tr>
				
				
				
				
			<s:iterator id="image" value="%{images}" status="status">
				<tr>
					<th width="5%" style="text-align:center;">${status.count}</th>
					<th width="25%"><s:property value="#image.name"/><s:property value='#image.images.picName'/></th>
					<td width="70%" colspan="3">
					   <div style="color:green;overflow:auto;height:200px;">
						    <table width="100%">
							    
									<s:iterator id="taskPic" value="#image.images" status="sta">
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
				</s:iterator>
				<!-- 
				<tr>
					<th width="30%" colspan="2">审核记录</th>
					<td width="70%" colspan="3">
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
					<th width="30%" colspan="2">审核结果</th>
					<td width="70%" colspan="3">
						<s:select id="state" name="whpClsc.state" list="#{'1':'审核通过','2':'审核未通过'}" theme="simple" onchange="showop(this.value)"/>
					</td>
				</tr>
					
				<tr>
					<th width="30%" colspan="2">审核备注</th>
					<td width="70%" colspan="3"><textarea id="remark" name="whpClsc.remark" style="width:100%;height:120px" ></textarea></td>
				</tr>
					
					
				<tr id="op">
					<th width="30%" colspan="2">操作</th>
					<td width="25%">
              			<input type="radio" name="sex" checked=true onClick="showUpload('1')">完结
						<input type="radio" name="sex" onClick="showUpload('0')" >上报
					 </td>
             		<td width="45%" colspan="2">
              			<div id="upload" style="display:none; font-weight: bold">
              				选择审核人员
							<s:select  theme="simple" cssStyle="width:100px;" id="shuserid" list="%{userList}" listKey="id"
							name="whpClsc.shuserid" listValue="displayName" dataType="Require" msg="此项为必填">
							</s:select><font style="color:red">*</font>
						</div>
             		</td>
             	</tr>
				<tr>
				</tr>
				 -->
				 <s:if test="whpClsc.state!=1||whpClsc.dutyFlag!=1" >
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
							<font style="color:red">市级审核:</font><cus:SelectOneTag property="whpClsc.state" defaultText='请选择' codeName="审核结果"  dataType="Require" msg="此项为必选" /><font color="color:red">*</font>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font><input id="remark"
								name="whpClsc.remark" 
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
							<font style="color:red">县级审核:</font><cus:SelectOneTag property="whpClsc.state" defaultText='请选择' codeName="审核结果"  dataType="Require" msg="此项为必选" /><font color="color:red">*</font>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font><input id="remark"
								name="whpClsc.remark"  
								style="width: 50%">
							</input><font style="color:red">*(若未通过，请填写原由。)</font>
						</td>
					</tr>
					
					</c:if>
					</c:if>
				
				</s:if>
				<tr>
					<td colspan="5" height="100px" align="center" style="text-align:center;">
					     <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="updateStatus();">保存</a>
					   	  <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

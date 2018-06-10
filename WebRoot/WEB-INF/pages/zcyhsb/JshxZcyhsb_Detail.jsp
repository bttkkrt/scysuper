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
		var yhdl1 = "${qyzcyhglb1.yhdl}";
		var yhzl1 = "${qyzcyhglb1.yhzl}";
		var yhdl2 = "${qyzcyhglb2.yhdl}";
		var yhzl2 = "${qyzcyhglb2.yhzl}";
		var yhdl3 = "${qyzcyhglb3.yhdl}";
		var yhzl3 = "${qyzcyhglb3.yhzl}";
		var yhdl4 = "${qyzcyhglb4.yhdl}";
		var yhzl4 = "${qyzcyhglb4.yhzl}";
		var yhdl5 = "${qyzcyhglb5.yhdl}";
		var yhzl5 = "${qyzcyhglb5.yhzl}";
		var jb = "${jshxZcyhsb.jb}";
		var mqzt = "${jshxZcyhsb.mqzt}";
		var a = new Array(' ','资质证照','安全生产管理机构及人员','安全生产责任制',
				'安全生产管理制度','安全操作规程','教育培训','安全生产管理档案','安全生产投入',
				'应急管理','特征设备基础管理','职业卫生基础管理','相关方基础管理','其他基础管理');//14
		var b = new Array(' ','特种设备现场管理','生产设备设施及工艺','场所环境',
				'从业人员操作行为','消防安全','用电安全','职业卫生现场安全','有限空间现场安全',
				'辅助动力系统','相关方现场管理','其他现场管理');//12
		function initLB(){
			var title11 = document.getElementById('yhdl1'); 
			if(title11 != null)
			{
				for(var i=0;i<title11.options.length;i++){
			   		if(title11.options[i].value == yhdl1){   
			    		title11.options[i].selected = true;   
			   	 		break;   
			    	}   
				}
				changeZL(yhdl1,'1');
			}  
			var title12 = document.getElementById('yhdl2'); 
			if(title12 != null)
			{
				for(var i=0;i<title12.options.length;i++){
			   		if(title12.options[i].value == yhdl2){   
			    		title12.options[i].selected = true;   
			    		break;   
			    	}   
				}
				changeZL(yhdl2,'2');
			}  
			
			var title13 = document.getElementById('yhdl3'); 
			if(title13 != null)
			{
				for(var i=0;i<title13.options.length;i++){
			   		if(title13.options[i].value == yhdl3){   
			    		title13.options[i].selected = true;   
			    		break;   
			   	 	}   
				}
				changeZL(yhdl3,'3');
			}  

			var title14 = document.getElementById('yhdl4');  
			if(title14 != null)
			{
				for(var i=0;i<title14.options.length;i++){
			   		if(title14.options[i].value == yhdl4){   
			    		title14.options[i].selected = true;   
			    		break;   
			    	}   
				}
				changeZL(yhdl4,'4');
			}  
			var title15 = document.getElementById('yhdl5');   
			if(title15 != null)
			{
				for(var i=0;i<title15.options.length;i++){
			  		 if(title15.options[i].value == yhdl5){   
			   			 title15.options[i].selected = true;   
			    		break;   
			    	}   
				}
				changeZL(yhdl5,'5');
			}  
			var title21 = document.getElementById('yhzl1'); 
			if(title21 != null)
			{
				for(var i=0;i<title21.options.length;i++){
			   		if(title21.options[i].value == yhzl1){   
			    		title21.options[i].selected = true;   
			   			break;   
			    	}   
				} 
			}    
			var title22 = document.getElementById('yhzl2'); 
			if(title22 != null)
			{
				for(var i=0;i<title22.options.length;i++){
			   		if(title22.options[i].value == yhzl2){   
			   			title22.options[i].selected = true;   
			    		break;   
			    	}   
				} 
			}    
			var title23 = document.getElementById('yhzl3');   
			if(title23 != null)
			{
				for(var i=0;i<title23.options.length;i++){
			   		if(title23.options[i].value == yhzl3){   
			    		title23.options[i].selected = true;   
			    		break;   
			    	}   
				} 
			}  
			var title24 = document.getElementById('yhzl4');   
			if(title24 != null)
			{
				for(var i=0;i<title24.options.length;i++){
			   		if(title24.options[i].value == yhzl4){   
			   			title24.options[i].selected = true;   
			    		break;   
			    	}   
				} 
			}  
			var title25 = document.getElementById('yhzl5');   
			if(title25 != null)
			{
				for(var i=0;i<title25.options.length;i++){
			   		if(title25.options[i].value == yhzl5){   
			    		title25.options[i].selected = true;   
			    		break;   
			    	}   
				} 
			}  
			var title2 = document.getElementById('zgzt');   
			for(var i=0;i<title2.options.length;i++){
			   if(title2.options[i].value == mqzt){   
			    title2.options[i].selected = true;   
			    break;   
			    }   
			}
			if(mqzt == '1'){
				document.getElementById("zt_0").style.display="none";
				document.getElementById("zt_1").style.display="none";
			}else{
				document.getElementById("zt_0").style.display="block";
				document.getElementById("zt_1").style.display="block";
			}
			var title3 = document.getElementById('jb');   
			for(var i=0;i<title3.options.length;i++){
			   if(title3.options[i].value == jb){   
			    title3.options[i].selected = true;   
			    break;   
			    }   
			}
		}
		function changeZL(obj,type){
				var selectContainer = $('#yhzl'+type); 
				 selectContainer.empty();
				 if(obj == '0'){
				 	for(var i=0; i<1; i++){
							var option = $('<option></option>').text("").val("0"); 
							selectContainer.append(option); 
						 }
				 }else if(obj == '1'){
				 	for(var i=0; i<14; i++){
							var option = $('<option></option>').text(a[i]).val("1_"+i);
							
							selectContainer.append(option); 
			 			}
				 }else if(obj == '2'){
				 	for(var i=0; i<12; i++){
						var option = $('<option></option>').text(b[i]).val("2_"+i); 
						selectContainer.append(option); 
			 		}
				 }
		}
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
		                	url : "jshxZcyhsbImageDel.action",
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
        		window.location.href="jshxZcyhsbSaveFile.action?picName="+i+"&fileName="+j;
			}
        
        </script>
</head>
<body onload="initLB();">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所属企业</th>
					<td width="35%">${jshxZcyhsb.qymc}</td>
					<%-- <th width="15%">检查类别</th>
					<td width="35%">
						<s:radio disabled="true"  list="#{'0':'企业自查','1':'专家检查','2':'企业互评互查'}" theme="simple" name="jshxZcyhsb.jclb"  value="#{jshxZcyhsb.jclb}"/>
					</td> --%>
				</tr>
				<tr>
					<th width="15%">检查时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${jshxZcyhsb.jcsj}"/></td>
					<th width="15%">检查人员</th>
					<td width="35%">${jshxZcyhsb.jcry}</td>
				</tr>
				<tr>
					<td colspan="4">
						<s:if test="jshxZcyhsb.yhsl == 0">
							<div id="more0" class="submitdata">
								<table>
									<tr>
										<th width="15%">零隐患原因</th>
										<td width="85%"><s:select id="yy" listKey="key" listValue="value"  theme="simple" list="#{'0':'本月全厂停产','1':'本月未查到安全隐患'}" name="jshxZcyhsb.yy" value="{jshxZcyhsb.yy}" disabled="true" /></td>
									</tr>
								</table>
							<div>
						</s:if>
						<s:if test="%{qyzcyhglb1 != null}">
						<div id="more1" class="submitdata">
							<table  width="100%">
								<tr>
									<th width="15%">隐患一被检查部位</th>
									<td width="35%">${qyzcyhglb1.jcbw}</td>
									<th width="15%">隐患一整改完成时间</th>
									<td width="35%">${qyzcyhglb1.yhzgwcsj}</td>
								</tr>
								<tr>
									<th width="15%">隐患一隐患大类</th>
									<td width="35%">
										<s:select disabled="true"  id="yhdl1" listKey="key" listValue="value" theme="simple" list="#{'0':' ','1':'基础管理','2':'现场管理'}" name="qyzcyhglb1.yhdl" value="#{qyzcyhglb1.yhdl}"/>
									</td>
									<th width="15%">隐患一隐患中类</th>
									<td width="35%">
										<select disabled="true"  id="yhzl1" name="qyzcyhglb1.yhzl">
											<option  value="0">&nbsp;&nbsp;&nbsp;</option>
										</select>
									</td>
								</tr>
								<tr>
									<th width="15%">隐患一整改责任部门</th>
									<td width="35%">${qyzcyhglb1.zgzrbm}</td>
									<th width="15%">隐患一整改责任人</th>
									<td width="35%">${qyzcyhglb1.zgzrr}</td>
								</tr>
								<tr>
									<th width="15%">隐患一整改前图片</th>
									<td width="85%" colspan="3">
					  			 		<div style="color:green;overflow:auto; height:200px;">
						    				<table width="100%">
							    				
									    			<s:iterator id="taskPic" value="%{picList11}" status="sta">
													<tr>
													<td width="70%">
															<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/photo/${taskPic.fileName}" rel="example_group">	
															    <img src="/upload/photo/${taskPic.fileName}"
																		border='0' width='220' height='150'/>
																</a>
															    </c:when>
															     <c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													    		</c:otherwise>
														   </c:choose>
													</td>
													<%-- <td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
												 --%></td>
													</tr>
													</s:iterator>
							    				
											</table>
										</div>
									</td>
								</tr>
								
								<tr>
									<th width="15%">隐患一整改后图片</th>
									<td width="85%" colspan="3">
					   					<div style="color:green;overflow:auto; height:200px;">
						    				<table width="100%">
									    			<s:iterator id="taskPic" value="%{picList12}" status="sta">
														<tr>
													<td width="70%">
														 	<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/photo/${taskPic.fileName}" rel="example_group">	
															    <img src="/upload/photo/${taskPic.fileName}"
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
							</table>
						</div>
					</s:if>
					<s:if test="%{qyzcyhglb2 != null}">
					<div id="more2" class="submitdata">
							<table width="100%">
								<tr>
									<th width="15%">隐患二被检查部位</th>
									<td width="35%">${qyzcyhglb2.jcbw}</td>
									<th width="15%">隐患二整改完成时间</th>
									<td width="35%">${qyzcyhglb2.yhzgwcsj}</td>
								</tr>
								<tr>
									<th width="15%">隐患二隐患大类</th>
									<td width="35%">
										<s:select disabled="true"  id="yhdl2" listKey="key" listValue="value" theme="simple" list="#{'0':' ','1':'基础管理','2':'现场管理'}" name="qyzcyhglb2.yhdl" value="#{qyzcyhglb2.yhdl}"/>
									</td>
									<th width="15%">隐患二隐患中类</th>
									<td width="35%">
										<select disabled="true"  id="yhzl2" name="qyzcyhglb2.yhzl">
											<option  value="0">&nbsp;&nbsp;&nbsp;</option>
										</select>
									</td>
								</tr>
								<tr>
									<th width="15%">隐患二整改责任部门</th>
									<td width="35%">${qyzcyhglb2.zgzrbm}</td>
									<th width="15%">隐患二整改责任人</th>
									<td width="35%">${qyzcyhglb2.zgzrr}</td>
								</tr>
								<tr>
									<th width="15%">隐患二整改前图片</th>
									<td width="85%" colspan="3">
					  			 		<div style="color:green;overflow:auto; height:200px;">
						    				<table width="100%">
									    			<s:iterator id="taskPic" value="%{picList21}" status="sta">
													<tr>
													<td width="70%">
															<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/photo/${taskPic.fileName}" rel="example_group">	
															    <img src="/upload/photo/${taskPic.fileName}"
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
									<th width="15%">隐患二整改后图片</th>
									<td width="85%" colspan="3">
					   					<div style="color:green;overflow:auto; height:200px;">
						    				<table width="100%">
									    			<s:iterator id="taskPic" value="%{picList22}" status="sta">
														<tr>
													<td width="70%">
															<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/photo/${taskPic.fileName}" rel="example_group">	
															    <img src="/upload/photo/${taskPic.fileName}"
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
							</table>
						</div>
						</s:if>
						
						<s:if test="%{qyzcyhglb3 != null}">
						<div id="more1" class="submitdata">
							<table width="100%">
								<tr>
									<th width="15%">隐患三被检查部位</th>
									<td width="35%">${qyzcyhglb3.jcbw}</td>
									<th width="15%">隐患三整改完成时间</th>
									<td width="35%">${qyzcyhglb3.yhzgwcsj}</td>
								</tr>
								<tr>
									<th width="15%">隐患三隐患大类</th>
									<td width="35%">
										<s:select disabled="true"  id="yhdl3" listKey="key" listValue="value" theme="simple" list="#{'0':' ','1':'基础管理','2':'现场管理'}" name="qyzcyhglb3.yhdl" value="#{qyzcyhglb3.yhdl}"/>
									</td>
									<th width="15%">隐患三隐患中类</th>
									<td width="35%">
										<select disabled="true"  id="yhzl3" name="qyzcyhglb3.yhzl">
											<option  value="0">&nbsp;&nbsp;&nbsp;</option>
										</select>
									</td>
								</tr>
								<tr>
									<th width="15%">隐患三整改责任部门</th>
									<td width="35%">${qyzcyhglb3.zgzrbm}</td>
									<th width="15%">隐患三整改责任人</th>
									<td width="35%">${qyzcyhglb3.zgzrr}</td>
								</tr>
								<tr>
									<th width="15%">隐患三整改前图片</th>
									<td width="85%" colspan="3">
					  			 		<div style="color:green;overflow:auto; height:200px;">
						    				<table width="100%">
									    			<s:iterator id="taskPic" value="%{picList31}" status="sta">
													<tr>
													<td width="70%">
															<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/photo/${taskPic.fileName}" rel="example_group">	
															    <img src="/upload/photo/${taskPic.fileName}"
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
								<th width="15%">隐患三整改后图片</th>
								<td width="85%" colspan="3">
					   					<div style="color:green;overflow:auto; height:200px;">
						    				<table width="100%">
									    			<s:iterator id="taskPic" value="%{picList32}" status="sta">
														<tr>
													<td width="70%">
															<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/photo/${taskPic.fileName}" rel="example_group">	
															    <img src="/upload/photo/${taskPic.fileName}"
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
							</table>
						</div>
						</s:if>
						
						
						<s:if test="%{qyzcyhglb4 != null}">
					<div id="more4" class="submitdata" >
							<table width="100%">
								<tr>
									<th width="15%">隐患四被检查部位</th>
									<td width="35%">${qyzcyhglb4.jcbw}</td>
									<th width="15%">隐患四整改完成时间</th>
									<td width="35%">${qyzcyhglb4.yhzgwcsj}</td>
								</tr>
								<tr>
									<th width="15%">隐患四隐患大类</th>
									<td width="35%">
										<s:select disabled="true"  id="yhdl4" listKey="key" listValue="value" theme="simple" list="#{'0':' ','1':'基础管理','2':'现场管理'}" name="qyzcyhglb4.yhdl" value="#{qyzcyhglb4.yhdl}"/>
									</td>
									<th width="15%">隐患四隐患中类</th>
									<td width="35%">
										<select disabled="true"  id="yhzl4" name="qyzcyhglb4.yhzl">
											<option  value="0">&nbsp;&nbsp;&nbsp;</option>
										</select>
									</td>
								</tr>
								<tr>
									<th width="15%">隐患四整改责任部门</th>
									<td width="35%">${qyzcyhglb4.zgzrbm}</td>
									<th width="15%">隐患四整改责任人</th>
									<td width="35%">${qyzcyhglb4.zgzrr}</td>
								</tr>
								<tr>
									<th width="15%">隐患四整改前图片</th>
									<td width="85%" colspan="3">
					  			 		<div style="color:green;overflow:auto; height:200px;">
						    				<table width="100%">
									    			<s:iterator id="taskPic" value="%{picList41}" status="sta">
													<tr>
													<td width="70%">
															<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/photo/${taskPic.fileName}" rel="example_group">	
															    <img src="/upload/photo/${taskPic.fileName}"
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
								<th width="15%">隐患四整改后图片</th>
								<td width="85%" colspan="3">
					   					<div style="color:green;overflow:auto; height:200px;">
						    				<table width="100%">
									    			<s:iterator id="taskPic" value="%{picList42}" status="sta">
													<tr>
													<td width="70%">
															<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/photo/${taskPic.fileName}" rel="example_group">	
															    <img src="/upload/photo/${taskPic.fileName}"
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
							</table>
						</div>
						
						</s:if>
						<s:if test="%{qyzcyhglb5 != null}">
					<div id="more5" class="submitdata">
							<table width="100%">
								<tr>
									<th width="15%">隐患五被检查部位</th>
									<td width="35%">${qyzcyhglb5.jcbw}</td>
									<th width="15%">隐患五整改完成时间</th>
									<td width="35%">${qyzcyhglb5.yhzgwcsj}</td>
								</tr>
								<tr>
									<th width="15%">隐患五隐患大类</th>
									<td width="35%">
										<s:select disabled="true"  id="yhdl5" listKey="key" listValue="value" theme="simple" list="#{'0':' ','1':'基础管理','2':'现场管理'}" name="qyzcyhglb5.yhdl" value="#{qyzcyhglb5.yhdl}"/>
									</td>
									<th width="15%">隐患五隐患中类</th>
									<td width="35%">
										<select disabled="true"  id="yhzl5" name="qyzcyhglb5.yhzl">
											<option  value="0">&nbsp;&nbsp;&nbsp;</option>
										</select>
									</td>
								</tr>
								<tr>
									<th width="15%">隐患五整改责任部门</th>
									<td width="35%">${qyzcyhglb5.zgzrbm}</td>
									<th width="15%">隐患五整改责任人</th>
									<td width="35%">${qyzcyhglb5.zgzrr}</td>
								</tr>
								<tr>
									<th width="15%">隐患五整改前图片</th>
									<td width="85%" colspan="3">
					  			 		<div style="color:green;overflow:auto; height:200px;">
						    				<table width="100%">
									    			<s:iterator id="taskPic" value="%{picList51}" status="sta">
													<tr>
													<td width="70%">
															<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/photo/${taskPic.fileName}" rel="example_group">	
															    <img src="/upload/photo/${taskPic.fileName}"
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
								<th width="15%">隐患五整改后图片</th>
								<td width="85%" colspan="3">
					   					<div style="color:green;overflow:auto; height:200px;">
						    				<table width="100%">
									    			<s:iterator id="taskPic" value="%{picList52}" status="sta">
														<tr>
													<td width="70%">
															<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/photo/${taskPic.fileName}" rel="example_group">	
															    <img src="/upload/photo/${taskPic.fileName}"
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
							</table>
						</div>
						</s:if>
					</td>
					</tr>	
					<tr>
					<th width="15%">计划完成时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${jshxZcyhsb.jhwcsj}"/></td>
					<th width="15%">目前状态</th>
					<td width="35%">
						<s:select disabled="true"  id="zgzt" theme="simple" list="#{'0':'整改完成','1':'整改中'}" name="jshxZcyhsb.mqzt" value="#{jshxZcyhsb.mqzt}"></s:select>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<table width="100%" id="zt_0">
							<th width="15%">复查时间</th>
							<td width="35%">
								${jshxZcyhsb.fcsj}
							</td>
							<th width="15%">复查人</th>
							<td width="35%">${jshxZcyhsb.fcr}</td>
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">级别</th>
					<td width="35%">
						<s:select disabled="true" id="jb" theme="simple" list="#{'0':'一般隐患','1':'一级隐患','2':'二级隐患','3':'三级隐患'}" name="jshxZcyhsb.jb" value="#{jshxZcyhsb.jb}"></s:select>
					</td>
					<th width="15%">整改投入资金(万元)</th>
					<td width="35%">${jshxZcyhsb.zgtrzj}</td>
				</tr>
				<tr>
					<th width="15%">存在问题和隐患</th>
					<td colspan="3"><textarea readOnly name="jshxZcyhsb.wtyh" style="width:100%;height:120px">${jshxZcyhsb.wtyh}</textarea></td>
				</tr>
				<tr>
					<th width="15%">具体情况及整改措施方案</th>
					<td colspan="3"><textarea readOnly name="jshxZcyhsb.csfa" style="width:100%;height:120px">${jshxZcyhsb.csfa}</textarea></td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<table width="100%" id="zt_1">
							<th width="15%">复查验收情况</th>
							<td colspan="3"><textarea readOnly name="jshxZcyhsb.fcysqk" style="width:100%;height:120px">${jshxZcyhsb.fcysqk}</textarea></td>
						</table>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px"  style="text-align:center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeLayer();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

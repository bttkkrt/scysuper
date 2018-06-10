<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				document.myform1.action="zdwxyjbtzSave.action";
				document.myform1.submit();
			}
		}
		
		
		 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUpload.action?safetysheet.linkid=${zdwxyjbtz.linkid}&type="+obj);
        	}
		    function savepic(i,j){
        		window.location.href="${ctx}/jsp/safetysheet/safesheetDownFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "${ctx}/jsp/safetysheet/safesheetImgDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
		    }
		    
		    function download(type)
        {
        	location.href = "${ctx}/jsp/qysb/fileDownload.action?type=" + type;
        }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zdwxyjbtz.id" value="${zdwxyjbtz.id}">
		<input type="hidden" name="zdwxyjbtz.createTime" value="<fmt:formatDate type="both" value="${zdwxyjbtz.createTime}" />">
		<input type="hidden" name="zdwxyjbtz.updateTime" value="${zdwxyjbtz.updateTime}">
		<input type="hidden" name="zdwxyjbtz.createUserID" value="${zdwxyjbtz.createUserID}">
		<input type="hidden" name="zdwxyjbtz.updateUserID" value="${zdwxyjbtz.updateUserID}">
		<input type="hidden" name="zdwxyjbtz.deptId" value="${zdwxyjbtz.deptId}">
		<input type="hidden" name="zdwxyjbtz.delFlag" value="${zdwxyjbtz.delFlag}">
		<input type="hidden" name="zdwxyjbtz.szzname" value="${zdwxyjbtz.szzname}">
		<input type="hidden" name="zdwxyjbtz.qymc" value="${zdwxyjbtz.qymc}">
		<input type="hidden" name="zdwxyjbtz.szzid" value="${zdwxyjbtz.szzid}">
		<input type="hidden" name="zdwxyjbtz.qyid" value="${zdwxyjbtz.qyid}">
		<input type="hidden" name="zdwxyjbtz.qylx" value="${zdwxyjbtz.qylx}">
		<input type="hidden" name="zdwxyjbtz.hyfl" value="${zdwxyjbtz.hyfl}">
		<input type="hidden" name="zdwxyjbtz.qygm" value="${zdwxyjbtz.qygm}">
		<input type="hidden" name="zdwxyjbtz.qyzclx" value="${zdwxyjbtz.qyzclx}">
		<input type="hidden" name="zdwxyjbtz.ifwhpqylx" value="${zdwxyjbtz.ifwhpqylx}">
		<input type="hidden" name="zdwxyjbtz.ifzywhqylx" value="${zdwxyjbtz.ifzywhqylx}">
		<input type="hidden" name="zdwxyjbtz.ifyhbzjyqy" value="${zdwxyjbtz.ifyhbzjyqy}">
			<input type="hidden" name="zdwxyjbtz.linkid" value="${zdwxyjbtz.linkid}">
		<input type="hidden" name="zdwxyjbtz.szc" value="${zdwxyjbtz.szc}">
		<input type="hidden" name="zdwxyjbtz.szcname" value="${zdwxyjbtz.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
			<tr>
					<th width="15%">企业名称</th>
					<td width="85%">${zdwxyjbtz.qymc}</td>
				</tr>
				<tr>
					<th width="15%">基本特征</th>
					<td width="85%" >
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zdwxyjbtz')" title="点击上传基本特征文件">&nbsp;&nbsp;选择基本特征文件上传</a><br/>
									<a style="color:red;" href="javascript:download('jbtz')" title="点击下载基本特征模板">&nbsp;&nbsp;下载基本特征模板</a>
								</td>
								<td width="80%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zdwxyjbtz">
													<c:forEach var="item" items="${list1}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																<c:choose>
													    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
													   				&nbsp;&nbsp;&nbsp;<img src="/upload/file/${item.picName}"
																	border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
													    		</c:when> 
													    		<c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;${item.fileName}
													    		</c:otherwise>
												  		 	</c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
																<a href="javascript:del('${item.id}');">删除</a>
															</td>
														</tr>
													</c:forEach>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">备案申请</th>
					<td width="85%" >
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zdwxybasqb')" title="点击上传备案申请文件">&nbsp;&nbsp;选择备案申请文件上传</a><br/>
									<a style="color:red;" href="javascript:download('basq')" title="点击下载备案申请模板">&nbsp;&nbsp;下载备案申请模板</a>
								</td>
								<td width="80%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zdwxybasqb">
													<c:forEach var="item" items="${list2}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																	<c:choose>
													    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
													   				&nbsp;&nbsp;&nbsp;<img src="/upload/file/${item.picName}"
																	border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
													    		</c:when> 
													    		<c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;${item.fileName}
													    		</c:otherwise>
												  		 	</c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
																<a href="javascript:del('${item.id}');">删除</a>
															</td>
														</tr>
													</c:forEach>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">备案登记</th>
					<td width="85%" >
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zdwxybadjb')" title="点击上传备案登记文件">&nbsp;&nbsp;选择文件备案登记上传</a><br/>
									<a style="color:red;" href="javascript:download('badj')" title="点击下载备案登记模板">&nbsp;&nbsp;下载备案登记模板</a>
								</td>
								<td width="80%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zdwxybadjb">
													<c:forEach var="item" items="${list3}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																<c:choose>
													    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
													   				&nbsp;&nbsp;&nbsp;<img src="/upload/file/${item.picName}"
																	border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
													    		</c:when> 
													    		<c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;${item.fileName}
													    		</c:otherwise>
												  		 	</c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
																<a href="javascript:del('${item.id}');">删除</a>
															</td>
														</tr>
													</c:forEach>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="2" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

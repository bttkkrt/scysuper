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
				
				document.myform1.action="maintenpSave.action";
				document.myform1.submit();
			}
		}
		
		 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUpload.action?safetysheet.linkid=${maintenp.linkid}&type="+obj);
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
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="maintenp.id" value="${maintenp.id}">
		<input type="hidden" name="maintenp.createTime" value="<fmt:formatDate type="both" value="${maintenp.createTime}" />">
		<input type="hidden" name="maintenp.updateTime" value="${maintenp.updateTime}">
		<input type="hidden" name="maintenp.createUserID" value="${maintenp.createUserID}">
		<input type="hidden" name="maintenp.updateUserID" value="${maintenp.updateUserID}">
		<input type="hidden" name="maintenp.deptId" value="${maintenp.deptId}">
		<input type="hidden" name="maintenp.delFlag" value="${maintenp.delFlag}">
		<input type="hidden" name="maintenp.linkid" value="${maintenp.linkid}">
		
			<input type="hidden" name="maintenp.deptName" value="${maintenp.deptName}">
		<input type="hidden" name="maintenp.comName"  value="${maintenp.comName}">
		<input type="hidden" name="maintenp.comId"  value="${maintenp.comId}">
		
			<input type="hidden" name="maintenp.qylx" value="${maintenp.qylx}">
		<input type="hidden" name="maintenp.hyfl" value="${maintenp.hyfl}">
		<input type="hidden" name="maintenp.qygm" value="${maintenp.qygm}">
		<input type="hidden" name="maintenp.qyzclx" value="${maintenp.qyzclx}">
		
		<input type="hidden" name="maintenp.ifwhpqylx" value="${maintenp.ifwhpqylx}">
		<input type="hidden" name="maintenp.ifzywhqylx" value="${maintenp.ifzywhqylx}">
		<input type="hidden" name="maintenp.ifyhbzjyqy" value="${maintenp.ifyhbzjyqy}">
		<input type="hidden" name="maintenp.szc" value="${maintenp.szc}">
		<input type="hidden" name="maintenp.szcname" value="${maintenp.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">制度名称</th>
					<td width="85%"><cus:SelectOneTag property="maintenp.producename" defaultText='请选择' codeName="检修作业规程" value="${maintenp.producename}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">文件上传</th>
					<td width="85%">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('jxzy')" title="点击上传文件">&nbsp;&nbsp;选择文件上传</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="jxzy">
													<c:forEach var="item" items="${list}">
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

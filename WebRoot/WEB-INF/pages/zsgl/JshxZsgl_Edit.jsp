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
				
				document.myform1.action="jshxZsglSave.action";
				document.myform1.submit();
			}
		}
		 //图片上传 lj 2013-08-15 
        function uploadPhoto(){
        		window.open("jshxZsglUpload.action?jshxZsgl.linkId=${jshxZsgl.linkId}");
        	}
         function savepic(i,j){
        		window.location.href="jshxZsglSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "jshxZsglImageDel.action",
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
		<input type="hidden" id="linkId" name="jshxZsgl.linkId" value="${jshxZsgl.linkId}"/>
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxZsgl.id" value="${jshxZsgl.id}">
		<input type="hidden" name="jshxZsgl.createTime" value="<fmt:formatDate type="both" value="${jshxZsgl.createTime}" />">
		<input type="hidden" name="jshxZsgl.updateTime" value="${jshxZsgl.updateTime}">
		<input type="hidden" name="jshxZsgl.createUserID" value="${jshxZsgl.createUserID}">
		<input type="hidden" name="jshxZsgl.updateUserID" value="${jshxZsgl.updateUserID}">
		<input type="hidden" name="jshxZsgl.deptId" value="${jshxZsgl.deptId}">
		<input type="hidden" name="jshxZsgl.delFlag" value="${jshxZsgl.delFlag}">
		
		<input type="hidden" name="jshxZsgl.szzname" value="${jshxZsgl.szzname}">
		<input type="hidden" name="jshxZsgl.qymc" value="${jshxZsgl.qymc}">
		<input type="hidden" name="jshxZsgl.szzid" value="${jshxZsgl.szzid}">
		<input type="hidden" name="jshxZsgl.qyid" value="${jshxZsgl.qyid}">
		<input type="hidden" name="jshxZsgl.qylx" value="${jshxZsgl.qylx}">
		<input type="hidden" name="jshxZsgl.hyfl" value="${jshxZsgl.hyfl}">
		<input type="hidden" name="jshxZsgl.qygm" value="${jshxZsgl.qygm}">
		<input type="hidden" name="jshxZsgl.qyzclx" value="${jshxZsgl.qyzclx}">
		
		<input type="hidden" name="jshxZsgl.ifwhpqylx" value="${jshxZsgl.ifwhpqylx}">
		<input type="hidden" name="jshxZsgl.ifzywhqylx" value="${jshxZsgl.ifzywhqylx}">
		<input type="hidden" name="jshxZsgl.ifyhbzjyqy" value="${jshxZsgl.ifyhbzjyqy}">
		
		<input type="hidden" name="jshxZsgl.szc" value="${jshxZsgl.szc}">
		<input type="hidden" name="jshxZsgl.szcname" value="${jshxZsgl.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">证书类型</th>
					<td width="85%"><cus:SelectOneTag property="jshxZsgl.jshxType" defaultText='请选择' codeName="证书类型" value="${jshxZsgl.jshxType}" /></td>
				</tr>
				<tr>
					<th width="15%">证书持有人姓名</th>
					<td width="85%"><input name="jshxZsgl.linkman" value="${jshxZsgl.linkman}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">联系电话</th>
					<td width="85%"><input name="jshxZsgl.telephone" value="${jshxZsgl.telephone}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%"><textarea  name="jshxZsgl.remark" style="width:100%;height:100px">${jshxZsgl.remark}</textarea></td>
				</tr>
				<tr>
					<th width="15%">证书扫描件</th>
					<td width="85%">
						<table width="100%">
							<tr>
								<td width="15%">
									<a style="color:blue;" href="javascript:uploadPhoto()" title="点击上传附件">选择证书扫描件</a>
								</td>
								<td width="85%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zsgl">
													<c:forEach var="item" items="${picList01}">
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
					<td colspan="2" height="100px" align="center" style="text-align:center;">
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

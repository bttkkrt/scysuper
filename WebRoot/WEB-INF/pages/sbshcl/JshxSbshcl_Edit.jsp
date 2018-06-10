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
				
				document.myform1.action="jshxSbshclSave.action";
				document.myform1.submit();
			}
		}
		//图片上传 lj 2013-08-16 
        function uploadPhoto(){
        		window.open("jshxSbshclUpload.action?jshxSbshcl.linkId=${jshxSbshcl.linkId}");
        	}
        	  function savepic(i,j){
        		window.location.href="jshxSbshclSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "jshxSbshclImageDel.action",
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
		<input type="hidden" id="linkId" name="jshxSbshcl.linkId" value="${jshxSbshcl.linkId}"/>
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxSbshcl.id" value="${jshxSbshcl.id}">
		<input type="hidden" name="jshxSbshcl.createTime" value="<fmt:formatDate type="both" value="${jshxSbshcl.createTime}" />">
		<input type="hidden" name="jshxSbshcl.updateTime" value="${jshxSbshcl.updateTime}">
		<input type="hidden" name="jshxSbshcl.createUserID" value="${jshxSbshcl.createUserID}">
		<input type="hidden" name="jshxSbshcl.updateUserID" value="${jshxSbshcl.updateUserID}">
		<input type="hidden" name="jshxSbshcl.deptId" value="${jshxSbshcl.deptId}">
		<input type="hidden" name="jshxSbshcl.delFlag" value="${jshxSbshcl.delFlag}">
		
		<input type="hidden" name="jshxSbshcl.szzname" value="${jshxSbshcl.szzname}">
		<input type="hidden" name="jshxSbshcl.qymc" value="${jshxSbshcl.qymc}">
		<input type="hidden" name="jshxSbshcl.szzid" value="${jshxSbshcl.szzid}">
		<input type="hidden" name="jshxSbshcl.qyid" value="${jshxSbshcl.qyid}">
		<input type="hidden" name="jshxSbshcl.qylx" value="${jshxSbshcl.qylx}">
		<input type="hidden" name="jshxSbshcl.hyfl" value="${jshxSbshcl.hyfl}">
		<input type="hidden" name="jshxSbshcl.qygm" value="${jshxSbshcl.qygm}">
		<input type="hidden" name="jshxSbshcl.qyzclx" value="${jshxSbshcl.qyzclx}">
		
		<input type="hidden" name="jshxSbshcl.ifwhpqylx" value="${jshxSbshcl.ifwhpqylx}">
		<input type="hidden" name="jshxSbshcl.ifzywhqylx" value="${jshxSbshcl.ifzywhqylx}">
		<input type="hidden" name="jshxSbshcl.ifyhbzjyqy" value="${jshxSbshcl.ifyhbzjyqy}">
		<input type="hidden" name="jshxSbshcl.szc" value="${jshxSbshcl.szc}">
		<input type="hidden" name="jshxSbshcl.szcname" value="${jshxSbshcl.szcname}">
		<input type="hidden" name="jshxSbshcl.state" value="${jshxSbshcl.state}">
		<input type="hidden" name="jshxSbshcl.shbs" value="${jshxSbshcl.shbs}">
		
		<input name="jshxSbshcl.ifzsqy" value="${jshxSbshcl.ifzsqy}" type="hidden" >
		<input name="jshxSbshcl.zsqytype" value="${jshxSbshcl.zsqytype}" type="hidden" >
		<input name="jshxSbshcl.dutyFlag" value="${jshxSbshcl.dutyFlag}" type="hidden" >
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">审查材料标题</th>
					<td width="85%">
					<input name="jshxSbshcl.jshxTitle" value="${jshxSbshcl.jshxTitle}" type="text" dataType="Require" msg="此项为必填" maxlength="255" style="width:15%">
					<input type="text" style="display: none;">
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
					<td colspan="2" height="100px" style="text-align:center;">
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

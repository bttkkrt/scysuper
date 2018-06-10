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
				
				document.myform1.action="jshxXkzsbSave.action";
				document.myform1.submit();
			}
		}
		//图片上传 lj 2013-08-16 
        function uploadPhoto(){
        		window.open("jshxXkzsbUpload.action?jshxXkzsb.linkId=${jshxXkzsb.linkId}");
        	}
        	  function savepic(i,j){
        		window.location.href="jshxXkzsbSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "jshxXkzsbImageDel.action",
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
		<input type="hidden" id="linkId" name="jshxXkzsb.linkId" value="${jshxXkzsb.linkId}"/>
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxXkzsb.id" value="${jshxXkzsb.id}">
		<input type="hidden" name="jshxXkzsb.createTime" value="<fmt:formatDate type="both" value="${jshxXkzsb.createTime}" />">
		<input type="hidden" name="jshxXkzsb.updateTime" value="${jshxXkzsb.updateTime}">
		<input type="hidden" name="jshxXkzsb.createUserID" value="${jshxXkzsb.createUserID}">
		<input type="hidden" name="jshxXkzsb.updateUserID" value="${jshxXkzsb.updateUserID}">
		<input type="hidden" name="jshxXkzsb.deptId" value="${jshxXkzsb.deptId}">
		<input type="hidden" name="jshxXkzsb.delFlag" value="${jshxXkzsb.delFlag}">
		
		<input type="hidden" name="jshxXkzsb.szzname" value="${jshxXkzsb.szzname}">
		<input type="hidden" name="jshxXkzsb.qymc" value="${jshxXkzsb.qymc}">
		<input type="hidden" name="jshxXkzsb.szzid" value="${jshxXkzsb.szzid}">
		<input type="hidden" name="jshxXkzsb.qyid" value="${jshxXkzsb.qyid}">
		<input type="hidden" name="jshxXkzsb.qylx" value="${jshxXkzsb.qylx}">
		<input type="hidden" name="jshxXkzsb.hyfl" value="${jshxXkzsb.hyfl}">
		<input type="hidden" name="jshxXkzsb.qygm" value="${jshxXkzsb.qygm}">
		<input type="hidden" name="jshxXkzsb.qyzclx" value="${jshxXkzsb.qyzclx}">
		
		<input type="hidden" name="jshxXkzsb.ifwhpqylx" value="${jshxXkzsb.ifwhpqylx}">
		<input type="hidden" name="jshxXkzsb.ifzywhqylx" value="${jshxXkzsb.ifzywhqylx}">
		<input type="hidden" name="jshxXkzsb.ifyhbzjyqy" value="${jshxXkzsb.ifyhbzjyqy}">
		<input type="hidden" name="jshxXkzsb.szc" value="${jshxXkzsb.szc}">
		<input type="hidden" name="jshxXkzsb.szcname" value="${jshxXkzsb.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">许可证类型</th>
					<td width="85%"><cus:SelectOneTag property="jshxXkzsb.jshxType" defaultText='请选择' codeName="行政许可" value="${jshxXkzsb.jshxType}" /></td>
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

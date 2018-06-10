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
				
				document.myform1.action="jshxGwznbSave.action";
				document.myform1.submit();
			}
		}
		 //图片上传 lj 2013-08-15 
        function uploadPhoto(){
        		window.open("jshxGwznbUpload.action?jshxGwznb.linkId=${jshxGwznb.linkId}");
        	}
        	 function savepic(i,j){
        		window.location.href="jshxGwznbSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "jshxGwznbImageDel.action",
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
		<input type="hidden" id="linkId" name="jshxGwznb.linkId" value="${jshxGwznb.linkId}"/>
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxGwznb.id" value="${jshxGwznb.id}">
		<input type="hidden" name="jshxGwznb.createTime" value="<fmt:formatDate type="both" value="${jshxGwznb.createTime}" />">
		<input type="hidden" name="jshxGwznb.updateTime" value="${jshxGwznb.updateTime}">
		<input type="hidden" name="jshxGwznb.createUserID" value="${jshxGwznb.createUserID}">
		<input type="hidden" name="jshxGwznb.updateUserID" value="${jshxGwznb.updateUserID}">
		<input type="hidden" name="jshxGwznb.deptId" value="${jshxGwznb.deptId}">
		<input type="hidden" name="jshxGwznb.delFlag" value="${jshxGwznb.delFlag}">
		
		<input type="hidden" name="jshxGwznb.szzname" value="${jshxGwznb.szzname}">
		<input type="hidden" name="jshxGwznb.qymc" value="${jshxGwznb.qymc}">
		<input type="hidden" name="jshxGwznb.szzid" value="${jshxGwznb.szzid}">
		<input type="hidden" name="jshxGwznb.qyid" value="${jshxGwznb.qyid}">
		<input type="hidden" name="jshxGwznb.qylx" value="${jshxGwznb.qylx}">
		<input type="hidden" name="jshxGwznb.hyfl" value="${jshxGwznb.hyfl}">
		<input type="hidden" name="jshxGwznb.qygm" value="${jshxGwznb.qygm}">
		<input type="hidden" name="jshxGwznb.qyzclx" value="${jshxGwznb.qyzclx}">
		
		<input type="hidden" name="jshxGwznb.ifwhpqylx" value="${jshxGwznb.ifwhpqylx}">
		<input type="hidden" name="jshxGwznb.ifzywhqylx" value="${jshxGwznb.ifzywhqylx}">
		<input type="hidden" name="jshxGwznb.ifyhbzjyqy" value="${jshxGwznb.ifyhbzjyqy}">
		<input type="hidden" name="jshxGwznb.szc" value="${jshxGwznb.szc}">
		<input type="hidden" name="jshxGwznb.szcname" value="${jshxGwznb.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">岗位</th>
					<td width="85%"><input name="jshxGwznb.gw" value="${jshxGwznb.gw}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">责任制附件</th>
					<td width="85%">
						<table width="100%">
							<tr>
									<td>
										<center><a style="color:blue;" href="javascript:uploadPhoto()" title="点击上传附件">选择责任制文件</a></center>
									</td>
								</tr>
								<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="gwznb">
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
					<td colspan="2" height="100px" align="center"  style="text-align:center;">
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

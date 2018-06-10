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
				
				document.myform1.action="factorypictureSave.action";
				document.myform1.submit();
			}
		}
	 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUpload.action?safetysheet.linkid=${factorypicture.linkid}&type="+obj);
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
		<input type="hidden" name="factorypicture.id" value="${factorypicture.id}">
		<input type="hidden" name="factorypicture.createTime" value="<fmt:formatDate type="both" value="${factorypicture.createTime}" />">
		<input type="hidden" name="factorypicture.updateTime" value="${factorypicture.updateTime}">
		<input type="hidden" name="factorypicture.createUserID" value="${factorypicture.createUserID}">
		<input type="hidden" name="factorypicture.updateUserID" value="${factorypicture.updateUserID}">
		<input type="hidden" name="factorypicture.deptId" value="${factorypicture.deptId}">
		<input type="hidden" name="factorypicture.delFlag" value="${factorypicture.delFlag}">
		<input type="hidden" name="factorypicture.linkid" value="${factorypicture.linkid}">
			<input type="hidden" name="factorypicture.deptName" value="${factorypicture.deptName}">
		<input type="hidden" name="factorypicture.comName"  value="${factorypicture.comName}">
		<input type="hidden" name="factorypicture.comId"  value="${factorypicture.comId}">
			<input type="hidden" name="factorypicture.qylx" value="${factorypicture.qylx}">
		<input type="hidden" name="factorypicture.hyfl" value="${factorypicture.hyfl}">
		<input type="hidden" name="factorypicture.qygm" value="${factorypicture.qygm}">
		<input type="hidden" name="factorypicture.qyzclx" value="${factorypicture.qyzclx}">
		
		<input type="hidden" name="factorypicture.ifwhpqylx" value="${factorypicture.ifwhpqylx}">
		<input type="hidden" name="factorypicture.ifzywhqylx" value="${factorypicture.ifzywhqylx}">
		<input type="hidden" name="factorypicture.ifyhbzjyqy" value="${factorypicture.ifyhbzjyqy}">
		
		<input type="hidden" name="factorypicture.szc" value="${factorypicture.szc}">
		<input type="hidden" name="factorypicture.szcname" value="${factorypicture.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">厂区实物名称</th>
					<td width="85%"><input name="factorypicture.physicalplantname"  size=50 value="${factorypicture.physicalplantname}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">文件上传</th>
					<td width="85%">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('cqtp')" title="点击上传文件">&nbsp;&nbsp;选择文件上传</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="cqtp">
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
					<td colspan="2" style="text-align:center">
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

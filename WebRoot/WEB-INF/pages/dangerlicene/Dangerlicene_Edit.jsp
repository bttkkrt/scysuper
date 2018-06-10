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
				
				document.myform1.action="dangerliceneSave.action";
				document.myform1.submit();
			}
		}
		
			 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUpload.action?safetysheet.linkid=${dangerlicene.linkid}&type="+obj);
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
		<input type="hidden" name="dangerlicene.id" value="${dangerlicene.id}">
		<input type="hidden" name="dangerlicene.createTime" value="<fmt:formatDate type="both" value="${dangerlicene.createTime}" />">
		<input type="hidden" name="dangerlicene.updateTime" value="${dangerlicene.updateTime}">
		<input type="hidden" name="dangerlicene.createUserID" value="${dangerlicene.createUserID}">
		<input type="hidden" name="dangerlicene.updateUserID" value="${dangerlicene.updateUserID}">
		<input type="hidden" name="dangerlicene.deptId" value="${dangerlicene.deptId}">
		<input type="hidden" name="dangerlicene.delFlag" value="${dangerlicene.delFlag}">
			<input type="hidden" name="dangerlicene.szz" value="${dangerlicene.szz}">
		<input type="hidden" name="dangerlicene.qymc"  value="${dangerlicene.qymc}">
		<input type="hidden" name="dangerlicene.comid"  value="${dangerlicene.comid}">
			<input type="hidden" name="dangerlicene.linkid" value="${dangerlicene.linkid}">
			<input type="hidden" name="dangerlicene.qylx" value="${dangerlicene.qylx}">
		<input type="hidden" name="dangerlicene.hyfl" value="${dangerlicene.hyfl}">
		<input type="hidden" name="dangerlicene.qygm" value="${dangerlicene.qygm}">
		<input type="hidden" name="dangerlicene.qyzclx" value="${dangerlicene.qyzclx}">
			<input type="hidden" name="dangerlicene.ifwhpqylx" value="${dangerlicene.ifwhpqylx}">
		<input type="hidden" name="dangerlicene.ifzywhqylx" value="${dangerlicene.ifzywhqylx}">
		<input type="hidden" name="dangerlicene.ifyhbzjyqy" value="${dangerlicene.ifyhbzjyqy}">
		
		<input type="hidden" name="dangerlicene.szc" value="${dangerlicene.szc}">
		<input type="hidden" name="dangerlicene.szcname" value="${dangerlicene.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">扫描件名称</th>
					<td width="85%"><cus:SelectOneTag property="dangerlicene.smjmc" defaultText='请选择' codeName="危险化学品建设项目行政" value="${dangerlicene.smjmc}" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">文件上传</th>
					<td width="85%">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('wxhxpjsxm')" title="点击上传文件">&nbsp;&nbsp;选择文件上传</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="wxhxpjsxm">
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

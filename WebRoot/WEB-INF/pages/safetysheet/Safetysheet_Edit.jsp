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
				
				document.myform1.action="safetysheetSave.action";
				document.myform1.submit();
			}
		}
		 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("safesheetUpload.action?safetysheet.linkid=${safetysheet.linkid}&type="+obj);
        	}
		    function savepic(i,j){
        		window.location.href="safesheetDownFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "safesheetImgDel.action",
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
		<input type="hidden" name="safetysheet.id" value="${safetysheet.id}">
		<input type="hidden" name="safetysheet.createTime" value="<fmt:formatDate type="both" value="${safetysheet.createTime}" />">
		<input type="hidden" name="safetysheet.updateTime" value="${safetysheet.updateTime}">
		<input type="hidden" name="safetysheet.createUserID" value="${safetysheet.createUserID}">
		<input type="hidden" name="safetysheet.updateUserID" value="${safetysheet.updateUserID}">
		<input type="hidden" name="safetysheet.deptId" value="${safetysheet.deptId}">
		<input type="hidden" name="safetysheet.delFlag" value="${safetysheet.delFlag}">
		<input type="hidden" name="safetysheet.linkid" value="${safetysheet.linkid}">
			<input type="hidden" name="safetysheet.deptName" value="${safetysheet.deptName}">
		<input type="hidden" name="safetysheet.comName"  value="${safetysheet.comName}">
		<input type="hidden" name="safetysheet.comId"  value="${safetysheet.comId}">
		
			<input type="hidden" name="safetysheet.qylx" value="${safetysheet.qylx}">
		<input type="hidden" name="safetysheet.hyfl" value="${safetysheet.hyfl}">
		<input type="hidden" name="safetysheet.qygm" value="${safetysheet.qygm}">
		<input type="hidden" name="safetysheet.qyzclx" value="${safetysheet.qyzclx}">
		
		<input type="hidden" name="safetysheet.ifwhpqylx" value="${safetysheet.ifwhpqylx}">
		<input type="hidden" name="safetysheet.ifzywhqylx" value="${safetysheet.ifzywhqylx}">
		<input type="hidden" name="safetysheet.ifyhbzjyqy" value="${safetysheet.ifyhbzjyqy}">
		
		<input type="hidden" name="safetysheet.szc" value="${safetysheet.szc}">
		<input type="hidden" name="safetysheet.szcname" value="${safetysheet.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">制度名称</th>
					<td width="85%"><cus:SelectOneTag property="safetysheet.systemname" defaultText='请选择' codeName="安全生产规章制度" value="${safetysheet.systemname}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">文件上传</th>
					<td width="85%">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('aqsc')" title="点击上传文件">&nbsp;&nbsp;选择文件上传</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="aqsc">
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
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

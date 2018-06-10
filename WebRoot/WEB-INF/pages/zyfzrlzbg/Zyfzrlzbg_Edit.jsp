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
				
				document.myform1.action="zyfzrlzbgSave.action";
				document.myform1.submit();
			}
		}
			 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUpload.action?safetysheet.linkid=${zyfzrlzbg.linkid}&type="+obj);
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
		<input type="hidden" name="zyfzrlzbg.id" value="${zyfzrlzbg.id}">
		<input type="hidden" name="zyfzrlzbg.createTime" value="<fmt:formatDate type="both" value="${zyfzrlzbg.createTime}" />">
		<input type="hidden" name="zyfzrlzbg.updateTime" value="${zyfzrlzbg.updateTime}">
		<input type="hidden" name="zyfzrlzbg.createUserID" value="${zyfzrlzbg.createUserID}">
		<input type="hidden" name="zyfzrlzbg.updateUserID" value="${zyfzrlzbg.updateUserID}">
		<input type="hidden" name="zyfzrlzbg.deptId" value="${zyfzrlzbg.deptId}">
		<input type="hidden" name="zyfzrlzbg.delFlag" value="${zyfzrlzbg.delFlag}">
		
			<input type="hidden" name="zyfzrlzbg.szz" value="${zyfzrlzbg.szz}">
		<input type="hidden" name="zyfzrlzbg.qymc"  value="${zyfzrlzbg.qymc}">
		<input type="hidden" name="zyfzrlzbg.comid"  value="${zyfzrlzbg.comid}">
			<input type="hidden" name="zyfzrlzbg.linkid" value="${zyfzrlzbg.linkid}">
			<input type="hidden" name="zyfzrlzbg.qylx" value="${zyfzrlzbg.qylx}">
		<input type="hidden" name="zyfzrlzbg.hyfl" value="${zyfzrlzbg.hyfl}">
		<input type="hidden" name="zyfzrlzbg.qygm" value="${zyfzrlzbg.qygm}">
		<input type="hidden" name="zyfzrlzbg.qyzclx" value="${zyfzrlzbg.qyzclx}">
		<input type="hidden" name="zyfzrlzbg.ifwhpqylx" value="${zyfzrlzbg.ifwhpqylx}">
		<input type="hidden" name="zyfzrlzbg.ifzywhqylx" value="${zyfzrlzbg.ifzywhqylx}">
		<input type="hidden" name="zyfzrlzbg.ifyhbzjyqy" value="${zyfzrlzbg.ifyhbzjyqy}">
		<input type="hidden" name="zyfzrlzbg.uploadtime" value="${zyfzrlzbg.uploadtime}">
		<input type="hidden" name="zyfzrlzbg.szc" value="${zyfzrlzbg.szc}">
		<input type="hidden" name="zyfzrlzbg.szcname" value="${zyfzrlzbg.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">主要负责人</th>
					<td width="35%"><input name="zyfzrlzbg.fzr" value="${zyfzrlzbg.fzr}" type="text" maxlength="255"></td>
					<th width="15%">履职报告</th>
					<td width="35%"><input name="zyfzrlzbg.bgmc" value="${zyfzrlzbg.bgmc}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">是否有危险工艺</th>
					<td width="35%"><cus:hxradio property="zyfzrlzbg.sfywxgy" codeName="是或否" value="${zyfzrlzbg.sfywxgy}" /></td>
					<th width="15%">是否构成重大危险源</th>
					<td width="35%"><cus:hxradio property="zyfzrlzbg.sfgczdwxy" codeName="是或否" value="${zyfzrlzbg.sfgczdwxy}" /></td>
				</tr>
				<tr>
				<th width="15%">文件上传</th>
					<td  colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zyfzrlzbg')" title="点击上传文件">&nbsp;&nbsp;选择文件上传</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zyfzrlzbg">
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
					<td colspan="4" height="100px" style="text-align:center">
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

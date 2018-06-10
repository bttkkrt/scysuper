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
				
				document.myform1.action="zdwxphxdjSave.action";
				document.myform1.submit();
			}
		}
			 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUpload.action?safetysheet.linkid=${zdwxphxdj.linkid}&type="+obj);
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
		<input type="hidden" name="zdwxphxdj.id" value="${zdwxphxdj.id}">
		<input type="hidden" name="zdwxphxdj.createTime" value="<fmt:formatDate type="both" value="${zdwxphxdj.createTime}" />">
		<input type="hidden" name="zdwxphxdj.updateTime" value="${zdwxphxdj.updateTime}">
		<input type="hidden" name="zdwxphxdj.createUserID" value="${zdwxphxdj.createUserID}">
		<input type="hidden" name="zdwxphxdj.updateUserID" value="${zdwxphxdj.updateUserID}">
		<input type="hidden" name="zdwxphxdj.deptId" value="${zdwxphxdj.deptId}">
		<input type="hidden" name="zdwxphxdj.delFlag" value="${zdwxphxdj.delFlag}">
		<input type="hidden" name="zdwxphxdj.szzname" value="${zdwxphxdj.szzname}">
		<input type="hidden" name="zdwxphxdj.qymc" value="${zdwxphxdj.qymc}">
		<input type="hidden" name="zdwxphxdj.szzid" value="${zdwxphxdj.szzid}">
		<input type="hidden" name="zdwxphxdj.qyid" value="${zdwxphxdj.qyid}">
		<input type="hidden" name="zdwxphxdj.qylx" value="${zdwxphxdj.qylx}">
		<input type="hidden" name="zdwxphxdj.hyfl" value="${zdwxphxdj.hyfl}">
		<input type="hidden" name="zdwxphxdj.qygm" value="${zdwxphxdj.qygm}">
		<input type="hidden" name="zdwxphxdj.qyzclx" value="${zdwxphxdj.qyzclx}">
		<input type="hidden" name="zdwxphxdj.tbdwdz" value="${zdwxphxdj.tbdwdz}">
		<input type="hidden" name="zdwxphxdj.yzbm" value="${zdwxphxdj.yzbm}">
		<input type="hidden" name="zdwxphxdj.ifwhpqylx" value="${zdwxphxdj.ifwhpqylx}">
		<input type="hidden" name="zdwxphxdj.ifzywhqylx" value="${zdwxphxdj.ifzywhqylx}">
		<input type="hidden" name="zdwxphxdj.ifyhbzjyqy" value="${zdwxphxdj.ifyhbzjyqy}">
				<input type="hidden" name="zdwxphxdj.linkid" value="${zdwxphxdj.linkid}">
		<input type="hidden" name="zdwxphxdj.szc" value="${zdwxphxdj.szc}">
		<input type="hidden" name="zdwxphxdj.szcname" value="${zdwxphxdj.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">企业名称</th>
					<td width="85%">${zdwxphxdj.qymc}</td>
				</tr>
				<tr>
					<th width="15%">文件上传</th>
					<td width="85%" >
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zdwxyhxdjb')" title="点击上传文件">&nbsp;&nbsp;选择文件上传</a><br/>
									<a style="color:red;" href="javascript:download('hxdj')" title="点击下载模板">&nbsp;&nbsp;下载模板</a>
								</td>
								<td width="80%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zdwxyhxdjb">
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
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

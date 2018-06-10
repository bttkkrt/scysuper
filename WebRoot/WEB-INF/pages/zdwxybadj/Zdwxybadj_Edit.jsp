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
				
				document.myform1.action="zdwxybadjSave.action";
				document.myform1.submit();
			}
		}
		
			 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUpload.action?safetysheet.linkid=${zdwxybadj.linkid}&type="+obj);
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
		<input type="hidden" name="zdwxybadj.id" value="${zdwxybadj.id}">
		<input type="hidden" name="zdwxybadj.createTime" value="<fmt:formatDate type="both" value="${zdwxybadj.createTime}" />">
		<input type="hidden" name="zdwxybadj.updateTime" value="${zdwxybadj.updateTime}">
		<input type="hidden" name="zdwxybadj.createUserID" value="${zdwxybadj.createUserID}">
		<input type="hidden" name="zdwxybadj.updateUserID" value="${zdwxybadj.updateUserID}">
		<input type="hidden" name="zdwxybadj.deptId" value="${zdwxybadj.deptId}">
		<input type="hidden" name="zdwxybadj.delFlag" value="${zdwxybadj.delFlag}">
		<input type="hidden" name="zdwxybadj.szzname" value="${zdwxybadj.szzname}">
		<input type="hidden" name="zdwxybadj.qymc" value="${zdwxybadj.qymc}">
		<input type="hidden" name="zdwxybadj.szzid" value="${zdwxybadj.szzid}">
		<input type="hidden" name="zdwxybadj.qyid" value="${zdwxybadj.qyid}">
		<input type="hidden" name="zdwxybadj.qylx" value="${zdwxybadj.qylx}">
		<input type="hidden" name="zdwxybadj.hyfl" value="${zdwxybadj.hyfl}">
		<input type="hidden" name="zdwxybadj.qygm" value="${zdwxybadj.qygm}">
		<input type="hidden" name="zdwxybadj.qyzclx" value="${zdwxybadj.qyzclx}">
		<input type="hidden" name="zdwxybadj.tbdwdz" value="${zdwxybadj.tbdwdz}">
		<input type="hidden" name="zdwxybadj.yzbm" value="${zdwxybadj.yzbm}">
		<input type="hidden" name="zdwxybadj.ifwhpqylx" value="${zdwxybadj.ifwhpqylx}">
		<input type="hidden" name="zdwxybadj.ifzywhqylx" value="${zdwxybadj.ifzywhqylx}">
		<input type="hidden" name="zdwxybadj.ifyhbzjyqy" value="${zdwxybadj.ifyhbzjyqy}">
			<input type="hidden" name="zdwxybadj.linkid" value="${zdwxybadj.linkid}">
		<input type="hidden" name="zdwxybadj.szc" value="${zdwxybadj.szc}">
		<input type="hidden" name="zdwxybadj.szcname" value="${zdwxybadj.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">企业名称</th>
					<td width="85%">${zdwxybadj.qymc}</td>
				</tr>
				<tr>
					<th width="15%">文件上传</th>
					<td width="85%" >
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zdwxybadjb')" title="点击上传文件">&nbsp;&nbsp;选择文件上传</a><br/>
									<a style="color:red;" href="javascript:download('badj')" title="点击下载模板">&nbsp;&nbsp;下载模板</a>
								</td>
								<td width="80%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zdwxybadjb">
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
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

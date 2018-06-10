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
				
				document.myform1.action="safetywariningSave.action";
				document.myform1.submit();
			}
		}
		
		 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUploadNone.action?safetysheet.linkid=${safetywarining.linkid}&type="+obj);
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
		<input type="hidden" name="safetywarining.id" value="${safetywarining.id}">
		<input type="hidden" name="safetywarining.createTime" value="<fmt:formatDate type="both" value="${safetywarining.createTime}" />">
		<input type="hidden" name="safetywarining.updateTime" value="${safetywarining.updateTime}">
		<input type="hidden" name="safetywarining.createUserID" value="${safetywarining.createUserID}">
		<input type="hidden" name="safetywarining.updateUserID" value="${safetywarining.updateUserID}">
		<input type="hidden" name="safetywarining.deptId" value="${safetywarining.deptId}">
		<input type="hidden" name="safetywarining.delFlag" value="${safetywarining.delFlag}">
		
		<input type="hidden" name="safetywarining.uploadtime" value="${safetywarining.uploadtime}">
		<input type="hidden" name="safetywarining.qymc" value="${safetywarining.qymc}">
		<input type="hidden" name="safetywarining.szz" value="${safetywarining.szz}">
		<input type="hidden" name="safetywarining.comId" value="${safetywarining.comId}">
		<input type="hidden" name="safetywarining.linkid" value="${safetywarining.linkid}">
		<input type="hidden" name="safetywarining.qylx" value="${safetywarining.qylx}">
		<input type="hidden" name="safetywarining.hyfl" value="${safetywarining.hyfl}">
		<input type="hidden" name="safetywarining.qygm" value="${safetywarining.qygm}">
		<input type="hidden" name="safetywarining.qyzclx" value="${safetywarining.qyzclx}">
		
		<input type="hidden" name="safetywarining.ifwhpqylx" value="${safetywarining.ifwhpqylx}">
		<input type="hidden" name="safetywarining.ifzywhqylx" value="${safetywarining.ifzywhqylx}">
		<input type="hidden" name="safetywarining.ifyhbzjyqy" value="${safetywarining.ifyhbzjyqy}">
		
		<input type="hidden" name="safetywarining.szc" value="${safetywarining.szc}">
		<input type="hidden" name="safetywarining.szcname" value="${safetywarining.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">企业名称</th>
					<td width="85%">${safetywarining.qymc}</td>
				</tr>
				<tr>
					<th width="15%">文件上传</th>
					<td width="85%" >
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('aqjsbz')" title="点击上传文件">&nbsp;&nbsp;选择文件上传</a><br/>
									<a style="color:red;" href="javascript:download('aqjs')" title="点击下载模板">&nbsp;&nbsp;下载模板</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="aqjsbz">
													<c:forEach var="item" items="${list}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																${item.fileName}
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

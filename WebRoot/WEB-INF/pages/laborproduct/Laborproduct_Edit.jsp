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
				
				document.myform1.action="laborproductSave.action";
				document.myform1.submit();
			}
		}
		
		//图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUploadNone.action?safetysheet.linkid=${laborproduct.linkid}&type="+obj);
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
		<input type="hidden" name="laborproduct.id" value="${laborproduct.id}">
		<input type="hidden" name="laborproduct.createTime" value="<fmt:formatDate type="both" value="${laborproduct.createTime}" />">
		<input type="hidden" name="laborproduct.updateTime" value="${laborproduct.updateTime}">
		<input type="hidden" name="laborproduct.createUserID" value="${laborproduct.createUserID}">
		<input type="hidden" name="laborproduct.updateUserID" value="${laborproduct.updateUserID}">
		<input type="hidden" name="laborproduct.deptId" value="${laborproduct.deptId}">
		<input type="hidden" name="laborproduct.delFlag" value="${laborproduct.delFlag}">
		
		<input type="hidden" name="laborproduct.qymc" value="${laborproduct.qymc}">
		<input type="hidden" name="laborproduct.szz" value="${laborproduct.szz}">
		<input type="hidden" name="laborproduct.comId" value="${laborproduct.comId}">
		<input type="hidden" name="laborproduct.linkid" value="${laborproduct.linkid}">
		<input type="hidden" name="laborproduct.qylx" value="${laborproduct.qylx}">
		<input type="hidden" name="laborproduct.hyfl" value="${laborproduct.hyfl}">
		<input type="hidden" name="laborproduct.qygm" value="${laborproduct.qygm}">
		<input type="hidden" name="laborproduct.qyzclx" value="${laborproduct.qyzclx}">
		
		<input type="hidden" name="laborproduct.ifwhpqylx" value="${laborproduct.ifwhpqylx}">
		<input type="hidden" name="laborproduct.ifzywhqylx" value="${laborproduct.ifzywhqylx}">
		<input type="hidden" name="laborproduct.ifyhbzjyqy" value="${laborproduct.ifyhbzjyqy}">
		<input type="hidden" name="laborproduct.szc" value="${laborproduct.szc}">
		<input type="hidden" name="laborproduct.szcname" value="${laborproduct.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">企业名称</th>
					<td width="85%">${laborproduct.qymc}</td>
				</tr>
				<tr>
					<th width="15%">文件上传</th>
					<td width="85%" >
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('ldfhyppb')" title="点击上传文件">&nbsp;&nbsp;选择文件上传</a>
									<br/>
									<a style="color:red;" href="javascript:download('ldfh')" title="点击下载模板">&nbsp;&nbsp;下载模板</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="ldfhyppb">
													<c:forEach var="item" items="${list}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																&nbsp;${item.fileName}
															</td>
															<td width="30%">
																<a style="color:red" href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
																<a style="color:red" href="javascript:del('${item.id}');">删除</a>
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
					<td colspan="2" height="100px"  style="text-align:center">
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

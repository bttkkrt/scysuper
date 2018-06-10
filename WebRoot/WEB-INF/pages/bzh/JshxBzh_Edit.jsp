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
				var score = document.getElementById('score').value;
				if(score > 100)
				{
					alert('考评份数必须为百分制!');
				}
				else
				{
					document.myform1.action="jshxBzhSave.action";
					document.myform1.submit();
				}
			}
		}
		function validateNum(event,obj)
        {
        	event = window.event||event; 
       	 	if(event.keyCode == 37 | event.keyCode == 39){ 
           		return; 
       	 	} 
        	 //先把非数字的都替换掉，除了数字和. 
	        obj.value = obj.value.replace(/[^\d.]/g,""); 
	        //必须保证第一个为数字而不是. 
	        obj.value = obj.value.replace(/^\./g,""); 
	        //保证只有出现一个.而没有多个. 
	        obj.value = obj.value.replace(/\.{2,}/g,"."); 
	        //保证.只出现一次，而不能出现两次以上 
	        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	        if(obj.value.length >= 2 && obj.value.substring(0,1) == "0" && obj.value.substring(1,2) != ".")
        	{
        		obj.value = obj.value.substring(1,obj.value.length);
        	}
        }
        
         //图片上传 lj 2013-09-22    
        function uploadPhoto(obj){
        		window.open("jshxBzhUpload.action?jshxBzh.linkId=${jshxBzh.linkId}&type="+obj);
       }
        function savepic(i,j){
        		window.location.href="jshxBzhSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "jshxBzhImageDel.action",
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
		<input type="hidden" name="jshxBzh.id" value="${jshxBzh.id}">
		<input type="hidden" name="jshxBzh.createTime" value="<fmt:formatDate type="both" value="${jshxBzh.createTime}" />">
		<input type="hidden" name="jshxBzh.updateTime" value="${jshxBzh.updateTime}">
		<input type="hidden" name="jshxBzh.createUserID" value="${jshxBzh.createUserID}">
		<input type="hidden" name="jshxBzh.updateUserID" value="${jshxBzh.updateUserID}">
		<input type="hidden" name="jshxBzh.deptId" value="${jshxBzh.deptId}">
		<input type="hidden" name="jshxBzh.delFlag" value="${jshxBzh.delFlag}">
		
		<input type="hidden" name="jshxBzh.szzname" value="${jshxBzh.szzname}">
		<input type="hidden" name="jshxBzh.qymc" value="${jshxBzh.qymc}">
		<input type="hidden" name="jshxBzh.szzid" value="${jshxBzh.szzid}">
		<input type="hidden" name="jshxBzh.qyid" value="${jshxBzh.qyid}">
		<input type="hidden" name="jshxBzh.qylx" value="${jshxBzh.qylx}">
		<input type="hidden" name="jshxBzh.hyfl" value="${jshxBzh.hyfl}">
		<input type="hidden" name="jshxBzh.qygm" value="${jshxBzh.qygm}">
		<input type="hidden" name="jshxBzh.qyzclx" value="${jshxBzh.qyzclx}">
		
		<input type="hidden" name="jshxBzh.ifwhpqylx" value="${jshxBzh.ifwhpqylx}">
		<input type="hidden" name="jshxBzh.ifzywhqylx" value="${jshxBzh.ifzywhqylx}">
		<input type="hidden" name="jshxBzh.ifyhbzjyqy" value="${jshxBzh.ifyhbzjyqy}">
		<input type="hidden" name="jshxBzh.linkId" value="${jshxBzh.linkId}">
		<input type="hidden" name="jshxBzh.szc" value="${jshxBzh.szc}">
		<input type="hidden" name="jshxBzh.szcname" value="${jshxBzh.szcname}">
		<input type="hidden" name="jshxBzh.whpqylx" value="${jshxBzh.whpqylx}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">发证机关</th>
					<td width="35%"><input name="jshxBzh.fzjg" value="${jshxBzh.fzjg}" type="text" maxlength="255"></td>
					<th width="15%">考评分数</th>
					<td width="35%"><input id="score" name="jshxBzh.score" value="${jshxBzh.score}" type="text" maxlength="255" onKeyUp="validateNum(event,this)" dataType="Require" msg="此项为必填">
					<font style="color:red">*(百分制)</font>
					</td>
				</tr>
				<tr>
					<th width="15%">达标级别</th>
					<td width="35%">
						<cus:SelectOneTag property="jshxBzh.dbjb" defaultText='请选择' codeName="证书级别" value="${jshxBzh.dbjb}" />
					</td>
					<th width="15%">证书号</th>
					<td width="35%"><input name="jshxBzh.zsh" value="${jshxBzh.zsh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">有效期</th>
					<td width="35%">
					<input type="text" name="jshxBzh.yxq"
											class="Wdate"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											value="${jshxBzh.yxq}"/></td>
					<th width="15%">发证日期</th>
					<td width="35%">
						<input type="text" name="jshxBzh.fzrq"
											class="Wdate"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											value="${jshxBzh.fzrq}"/></td>
				</tr>
				<tr>
					<th width="15%">自评报告</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('bzhzpbg')" title="点击上传附件">&nbsp;&nbsp;选择文件</a>
								</td>
								<td width="80%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="bzhzpbg">
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
					<th width="15%">评审报告</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('bzhhchp')" title="点击上传附件">&nbsp;&nbsp;选择文件</a>
								</td>
								<td width="80%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="bzhhchp">
													<c:forEach var="item" items="${picList02}">
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
					<th width="15%">审核报告</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('bzhshbg')" title="点击上传附件">&nbsp;&nbsp;选择文件</a>
								</td>
								<td width="80%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="bzhshbg">
													<c:forEach var="item" items="${picList03}">
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
					<td colspan="4" height="100px" align="center" style="text-align:center;">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeLayer();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

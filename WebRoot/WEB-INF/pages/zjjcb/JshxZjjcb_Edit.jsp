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
	$(function(){
			if("${flag}"=="1"){
				layer.alert('更新成功', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					}, function(){
						window.opener=null;
						window.open('','_self');
						window.close();
					});
			}else if("${flag}"=="2"){
				layer.alert('更新失败');
			}
	});
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="jshxZjjcbSave.action";
				document.myform1.submit();
			}
		}
		 //图片上传 lj 2013-08-15 
        function uploadPhoto(){
        		window.open("jshxZjjcbUpload.action?jshxZjjcb.linkId=${jshxZjjcb.linkId}");
        	}
         function savepic(i,j){
        		window.location.href="jshxZjjcbSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "jshxZjjcbImageDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
        }
        
         function validate(event,obj)
        {
        	event = window.event||event; 
        	if(event.keyCode == 37 | event.keyCode == 39){ 
           	 	return; 
        	} 
        	obj.value = obj.value.replace(/[^\d]/g,""); 
        	if(obj.value.length >= 2 && obj.value.substring(0,1) == "0")
        	{
        		obj.value = obj.value.substring(1,obj.value.length);
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
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" id="linkId" name="jshxZjjcb.linkId" value="${jshxZjjcb.linkId}"/>
		<input type="hidden" name="jshxZjjcb.id" value="${jshxZjjcb.id}">
		<input type="hidden" name="jshxZjjcb.createTime" value="<fmt:formatDate type="both" value="${jshxZjjcb.createTime}" />">
		<input type="hidden" name="jshxZjjcb.updateTime" value="${jshxZjjcb.updateTime}">
		<input type="hidden" name="jshxZjjcb.createUserID" value="${jshxZjjcb.createUserID}">
		<input type="hidden" name="jshxZjjcb.updateUserID" value="${jshxZjjcb.updateUserID}">
		<input type="hidden" name="jshxZjjcb.deptId" value="${jshxZjjcb.deptId}">
		<input type="hidden" name="jshxZjjcb.delFlag" value="${jshxZjjcb.delFlag}">
		
		
		<input type="hidden" name="jshxZjjcb.szzname" value="${jshxZjjcb.szzname}">
		<input type="hidden" name="jshxZjjcb.qymc" value="${jshxZjjcb.qymc}">
		<input type="hidden" name="jshxZjjcb.szzid" value="${jshxZjjcb.szzid}">
		<input type="hidden" name="jshxZjjcb.qyid" value="${jshxZjjcb.qyid}">
		<input type="hidden" name="jshxZjjcb.qylx" value="${jshxZjjcb.qylx}">
		<input type="hidden" name="jshxZjjcb.hyfl" value="${jshxZjjcb.hyfl}">
		<input type="hidden" name="jshxZjjcb.qygm" value="${jshxZjjcb.qygm}">
		<input type="hidden" name="jshxZjjcb.qyzclx" value="${jshxZjjcb.qyzclx}">
		
		<input type="hidden" name="jshxZjjcb.ifwhpqylx" value="${jshxZjjcb.ifwhpqylx}">
		<input type="hidden" name="jshxZjjcb.ifzywhqylx" value="${jshxZjjcb.ifzywhqylx}">
		<input type="hidden" name="jshxZjjcb.ifyhbzjyqy" value="${jshxZjjcb.ifyhbzjyqy}">
		
		<input type="hidden" name="jshxZjjcb.szc" value="${jshxZjjcb.szc}">
		<input type="hidden" name="jshxZjjcb.szcname" value="${jshxZjjcb.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">专家姓名</th>
					<td width="35%"><input name="jshxZjjcb.zjName" value="${jshxZjjcb.zjName}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font color="red">*</font></td>
					<th width="15%">职称</th>
					<td width="35%"><input name="jshxZjjcb.zc" value="${jshxZjjcb.zc}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">从事专业</th>
					<td width="35%"><input name="jshxZjjcb.zy" value="${jshxZjjcb.zy}" type="text" maxlength="255"></td>
					<th width="15%">专家人数</th>
					<td width="35%"><input name="jshxZjjcb.zjsl" value="${jshxZjjcb.zjsl}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">隐患数</th>
					<td width="35%"><input name="jshxZjjcb.yhs" value="${jshxZjjcb.yhs}" type="text" maxlength="255" onKeyUp="validate(event,this)" ></td>
					<th width="15%">其中重大隐患数</th>
					<td width="35%"><input name="jshxZjjcb.zdyhs" value="${jshxZjjcb.zdyhs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">隐患整改数</th>
					<td width="35%"><input name="jshxZjjcb.yhzgs" value="${jshxZjjcb.yhzgs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="15%">其中重大隐患整改数</th>
					<td width="35%"><input name="jshxZjjcb.zdyhzgs" value="${jshxZjjcb.zdyhzgs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">整改费用（万元）</th>
					<td width="35%"><input name="jshxZjjcb.zgfy" value="${jshxZjjcb.zgfy}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
					<th width="15%">检查日期</th>
					<td width="35%"><input name="jshxZjjcb.jcrq" value="<fmt:formatDate type='date' value='${jshxZjjcb.jcrq}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">本次检查存在隐患及问题</th>
					<td colspan="3"><textarea name="jshxZjjcb.question" style="width:100%;height:120px">${jshxZjjcb.question}</textarea></td>
				</tr>
				<tr>
					<th width="15%">整改建议</th>
					<td colspan="3"><textarea name="jshxZjjcb.advice" style="width:100%;height:120px">${jshxZjjcb.advice}</textarea></td>
				</tr>
				<tr>
					<th width="15%">上次隐患整改情况</th>
					<td colspan="3"><textarea name="jshxZjjcb.zgqk" style="width:100%;height:120px">${jshxZjjcb.zgqk}</textarea></td>
				</tr>
				<tr>
					<th width="15%">扫描件文件</th>
					<td width="85%"  colspan="3">
						<table width="100%">
							<tr>
									<td>
										<center><a style="color:blue;" href="javascript:uploadPhoto()" title="点击上传附件">选择扫描件文件</a></center>
									</td>
								</tr>
								<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zjjcb">
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
					<td colspan="4" height="100px"   style="text-align:center;">
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

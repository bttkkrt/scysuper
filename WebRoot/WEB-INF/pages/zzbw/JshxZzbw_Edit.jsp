<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<link href="<c:url value='/webResources/js/uploadify.css'/>" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src='<c:url value="/webResources/js/swfobject.js"/>'></script>
	<script type="text/javascript" src='<c:url value="/webResources/js/jquery.uploadify.v2.1.4.js"/>'></script>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="jshxZzbwSave.action";
				document.myform1.submit();
			}
		}
		
		
		 //图片上传 lt 2013-02-26    
        function uploadPhoto(){
        	var type = "${jshxZzbw.proId}" + ",0";
        	window.open("${ctx}/jsp/zzbw/zzbwUpload.action?jshxZzbw.proId="+type);
        }
        
         //图片上传 lt 2013-02-26    
        function uploadPhotos(){
        	var type = "${jshxZzbw.proId}" + ",1";
        	window.open("${ctx}/jsp/zzbw/zzbwUploads.action?jshxZzbw.proId="+type);
        }
		
		//删除附件
		function del(aid){
			$.ajax({url: "${ctx}/jsp/zzbw/deleteFile.action"
				,data:{fileId:aid}
				,type: "POST",
				success:function(){
					$("tr").remove("tr[id="+aid+"]");
				}
		    });
        }
        
        //下载附件
        function downloadFile(attachId){
                location.href = "${ctx}/jsp/zzbw/download.action?fileId="+attachId;
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
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxZzbw.id" value="${jshxZzbw.id}">
		<input type="hidden" name="jshxZzbw.createTime" value="<fmt:formatDate type="both" value="${jshxZzbw.createTime}" />">
		<input type="hidden" name="jshxZzbw.updateTime" value="${jshxZzbw.updateTime}">
		<input type="hidden" name="jshxZzbw.createUserID" value="${jshxZzbw.createUserID}">
		<input type="hidden" name="jshxZzbw.updateUserID" value="${jshxZzbw.updateUserID}">
		<input type="hidden" name="jshxZzbw.deptId" value="${jshxZzbw.deptId}">
		<input type="hidden" name="jshxZzbw.delFlag" value="${jshxZzbw.delFlag}">
		<input type="hidden" name="jshxZzbw.szzname" value="${jshxZzbw.szzname}">
		<input type="hidden" name="jshxZzbw.qymc" value="${jshxZzbw.qymc}">
		<input type="hidden" name="jshxZzbw.szzid" value="${jshxZzbw.szzid}">
		<input type="hidden" name="jshxZzbw.qyid" value="${jshxZzbw.qyid}">
		<input type="hidden" name="jshxZzbw.proId" value="${jshxZzbw.proId}">
		<input type="hidden" name="jshxZzbw.qylx" value="${jshxZzbw.qylx}">
		<input type="hidden" name="jshxZzbw.hyfl" value="${jshxZzbw.hyfl}">
		<input type="hidden" name="jshxZzbw.qygm" value="${jshxZzbw.qygm}">
		<input type="hidden" name="jshxZzbw.qyzclx" value="${jshxZzbw.qyzclx}">
		<input type="hidden" name="jshxZzbw.ifwhpqylx" value="${jshxZzbw.ifwhpqylx}">
		<input type="hidden" name="jshxZzbw.ifzywhqylx" value="${jshxZzbw.ifzywhqylx}">
		<input type="hidden" name="jshxZzbw.ifyhbzjyqy" value="${jshxZzbw.ifyhbzjyqy}">
		<input type="hidden" name="jshxZzbw.szc" value="${jshxZzbw.szc}">
		<input type="hidden" name="jshxZzbw.szcname" value="${jshxZzbw.szcname}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="30%">关键装置、重点部位名称</th>
					<td width="70%"><input name="jshxZzbw.gjzzmc" value="${jshxZzbw.gjzzmc}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="30%">主要危险因素</th>
					<td width="70%"><input name="jshxZzbw.zywxys" value="${jshxZzbw.zywxys}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="30%">责任人</th>
					<td width="70%"><input name="jshxZzbw.zrr" value="${jshxZzbw.zrr}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="30%">岗位员工数量</th>
					<td width="70%"><input name="jshxZzbw.gwygsl" value="${jshxZzbw.gwygsl}" type="text" maxlength="255" dataType="Require" msg="此项为必填"  onKeyUp="validate(event,this)"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="30%">安全操作规程</th>
					<td width="70%">
						<a style="color:blue;" href="javascript:uploadPhoto()" title="点击上传安全操作规程">&nbsp;&nbsp;选择安全操作规程</a>
						<br/>
						<div style="color:green;overflow:auto; height:200px;">
						<table id="more">
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">${item.fileName}</td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								   <a href="javascript:del('${item.id}');">删除</a></td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<th width="30%">现场图片</th>
					<td width="70%">
						<a style="color:blue;" href="javascript:uploadPhotos()" title="点击上传现场图片">&nbsp;&nbsp;选择现场图片</a>
						<br/>
						<div style="color:green;overflow:auto; height:200px;">
						<table id="more1">
							  <c:forEach var="item" items="${picList1}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%"><img src="/upload/file/${item.picName}"
										border='0' width='220' height='150'/></td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								   <a href="javascript:del('${item.id}');">删除</a></td>
								</tr>
							  </c:forEach>
						</table>
						</div>
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

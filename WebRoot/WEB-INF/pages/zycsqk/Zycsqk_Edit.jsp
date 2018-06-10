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
				var r1 = document.getElementsByName("zywhysmc");
				var zywhysmcid = "";
				var zywhysmc = "";
	            for (var i = 0; i < r1.length; i++)
	            {
	            	if (r1[i].checked)
	            	{
	                	zywhysmcid = zywhysmcid + r1[i].value+",";
	                    zywhysmc = zywhysmc + r1[i].title +",";
	               	}
	            }
	            if(zywhysmcid != "")
	            {	
	            	zywhysmcid = zywhysmcid.substr(0,zywhysmcid.length-1);
	            	zywhysmc = zywhysmc.substr(0,zywhysmc.length-1);
	            }
	            document.getElementById('zywhysmcid').value=zywhysmcid;
	            document.getElementById('zywhysmc').value=zywhysmc;
				document.myform1.action="zycsqkSave.action";
				document.myform1.submit();
			}
		}
		
		function uploadPhoto(obj){
        	window.open("${ctx}/jsp/zycsqk/zycsqkUpload.action?zycsqk.linkid=${zycsqk.linkid}&type="+obj);
        }
		function savepic(i,j){
        	window.location.href="${ctx}/jsp/zycsqk/zycsqkSaveFile.action?picName="+i+"&fileName="+j;
		}
			//删除附件
		function del(picName){
			$.ajax({
				url: "${ctx}/jsp/zycsqk/zycsqkImageDel.action",
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
		<input type="hidden" name="zycsqk.id" value="${zycsqk.id}">
		<input type="hidden" name="zycsqk.createTime" value="<fmt:formatDate type="both" value="${zycsqk.createTime}" />">
		<input type="hidden" name="zycsqk.updateTime" value="${zycsqk.updateTime}">
		<input type="hidden" name="zycsqk.createUserID" value="${zycsqk.createUserID}">
		<input type="hidden" name="zycsqk.updateUserID" value="${zycsqk.updateUserID}">
		<input type="hidden" name="zycsqk.deptId" value="${zycsqk.deptId}">
		<input type="hidden" name="zycsqk.delFlag" value="${zycsqk.delFlag}">
		<input id="zywhysmc" type="hidden" name="zycsqk.zywhysmc" value="${zycsqk.zywhysmc}">
		<input id="zywhysmcid" type="hidden" name="zycsqk.zywhysmcid" value="${zycsqk.zywhysmcid}">
		<input type="hidden" name="zycsqk.linkid" value="${zycsqk.linkid}">
		<input type="hidden" name="zycsqk.szzname" value="${zycsqk.szzname}">
		<input type="hidden" name="zycsqk.qymc" value="${zycsqk.qymc}">
		<input type="hidden" name="zycsqk.szzid" value="${zycsqk.szzid}">
		<input type="hidden" name="zycsqk.qyid" value="${zycsqk.qyid}">
		<input type="hidden" name="zycsqk.qylx" value="${zycsqk.qylx}">
		<input type="hidden" name="zycsqk.hyfl" value="${zycsqk.hyfl}">
		<input type="hidden" name="zycsqk.qyzclx" value="${zycsqk.qyzclx}">
		<input type="hidden" name="zycsqk.szc" value="${zycsqk.szc}">
		<input type="hidden" name="zycsqk.szcname" value="${zycsqk.szcname}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">车间名称</th>
					<td width="35%"><input name="zycsqk.cjmc" value="${zycsqk.cjmc}" type="text" dataType="Require" msg="此项为必填" maxlength="4000"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">无尘车间</th>
					<td width="35%"><s:select cssStyle="width:100px" id="wccj" listKey="key" listValue="value"  theme="simple" list="#{'是':'是','否':'否'}" name="zycsqk.wccj" value="{zycsqk.wccj}"/><font color="red">*</font></td>
					<th width="15%">岗位</th>
					<td width="35%"><input name="zycsqk.gw" value="${zycsqk.gw}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">设备状态</th>
					<td width="35%"><s:select cssStyle="width:100px" id="sbzt" listKey="key" listValue="value"  theme="simple" list="#{'封闭':'封闭','半封闭':'半封闭','敞开':'敞开'}" name="zycsqk.sbzt" value="{zycsqk.sbzt}"/><font color="red">*</font></td>
					<th width="15%">操作方式</th>
					<td width="35%"><s:select cssStyle="width:100px" id="czfs" listKey="key" listValue="value"  theme="simple" list="#{'机械化':'机械化','半机械化':'半机械化','人工':'人工'}" name="zycsqk.czfs" value="{zycsqk.czfs}"/><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">操作接触人数</th>
					<td width="35%"><input name="zycsqk.jcrs" value="${zycsqk.jcrs}" type="text" maxlength="255" readonly></td>
					<th width="15%">当班累积接触时间</th>
					<td width="35%"><input name="zycsqk.ljjcsj" value="${zycsqk.ljjcsj}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">防护设施名称</th>
					<td width="35%"><s:select cssStyle="width:100px" id="fhssmc" listKey="key" listValue="value"  theme="simple" list="#{'排风扇':'排风扇','通风罩':'通风罩'}" name="zycsqk.fhssmc" value="{zycsqk.fhssmc}"/><font color="red">*</font></td>
					<th width="15%">个人防护用品</th>
					<td width="35%"><s:select cssStyle="width:100px" id="grfhyp" listKey="key" listValue="value"  theme="simple" list="#{'头盔':'头盔','手套':'手套','其他':'其他'}" name="zycsqk.grfhyp" value="{zycsqk.grfhyp}"/><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">职业病危害因素名称</th>
					<td width="85%" colspan="3">
						<c:forEach var="zywhys" items="${zywhysList}"> 
							<input type="checkbox" name="zywhysmc" value="${zywhys.id}" <c:if test="${fn:contains(zycsqk.zywhysmcid, zywhys.id)}">checked</c:if> title="${zywhys.name}">${zywhys.name}&nbsp;
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th width="15%">岗位图片</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zycsqk')" title="点击上传岗位图片">&nbsp;&nbsp;选择岗位图片上传</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zycsqk">
													<c:forEach var="item" items="${picList}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																<img src="/upload/file/${item.picName}"
																border='0' width='220' height='150'/>&nbsp;&nbsp;&nbsp;${item.fileName}
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

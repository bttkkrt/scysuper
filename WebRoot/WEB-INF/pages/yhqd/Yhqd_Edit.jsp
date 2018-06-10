<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
			<link href="<c:url value='/webResources/js/uploadify.css'/>"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src='<c:url value="/webResources/js/swfobject.js"/>'></script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/jquery.uploadify.v2.1.4.js"/>'></script>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="yhqdSave.action";
				document.myform1.submit();
			}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="yhqd.id" value="${yhqd.id}">
		<input type="hidden" name="yhqd.createTime" value="<fmt:formatDate type="both" value="${yhqd.createTime}" />">
		<input type="hidden" name="yhqd.updateTime" value="${yhqd.updateTime}">
		<input type="hidden" name="yhqd.createUserID" value="${yhqd.createUserID}">
		<input type="hidden" name="yhqd.updateUserID" value="${yhqd.updateUserID}">
		<input type="hidden" name="yhqd.deptId" value="${yhqd.deptId}">
		<input type="hidden" name="yhqd.delFlag" value="${yhqd.delFlag}">
		<input type="hidden" name="yhqd.basic.id" value="${yhqd.basic.id}">
		<input type="hidden" name="yhqd.ended" value="${yhqd.ended}">
		<input type="hidden" name="yhqd.passFlag" value="${yhqd.passFlag}">
		<input type="hidden" name="yhqd.zgysr" value="${yhqd.zgysr}">
		<input type="hidden" name="yhqd.dealFlag" value="${yhqd.dealFlag}">
		<input type="hidden" name="yhqd.defId" value="${yhqd.defId}">
		<input type="hidden" name="yhqd.yssj" value="${yhqd.yssj}">
		<input type="hidden" name="yhqd.jgzrrIds" value="${yhqd.jgzrrIds}">
		<input type="hidden" name="yhqd.resultContent" value="${yhqd.resultContent}">
		<input type="hidden" name="yhqd.zgInfo" value="${yhqd.zgInfo}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">被检查单位名称</th>
					<td width="35%">${yhqd.basic.company.companyname}</td>
					<th width="15%">检查时间</th>
					<td width="35%"><input name="yhqd.checktime" value="<fmt:formatDate type='both' value='${yhqd.checktime}' pattern="yyyy-MM-dd"/>" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">隐患内容</th>
					<td colspan="3"><textarea name="yhqd.yhContent" style="width:100%;height:120px">${yhqd.yhContent}</textarea></td>
				</tr>
				<tr>
					<th width="15%">监管部门跟踪责任人</th>
					<td width="35%">
					<input name="yhqd.jgzrrNames" value="${yhqd.jgzrrNames}" type="text" dataType="Require" msg="此项为必填" maxlength="255" readonly="readonly">
					<input type="hidden" dataType="Require" msg="此项为必填" name="yhqd.jgzrrIds"  value="${yhqd.jgzrrIds}" >
					<a href="#" class="easyui-linkbutton" onclick="selectUser(this)" iconCls="icon-save">选择</a>
					
					<font color="red">*</font></td>
					<th width="15%">企业责任人</th>
					<td width="35%"><input name="yhqd.qyzrr" value="${yhqd.qyzrr}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">企业责任人联系电话</th>
					<td width="35%"><input name="yhqd.qyzrrlxdh" value="${yhqd.qyzrrlxdh}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
					<th width="15%">整改期限</th>
					<td width="35%"><input name="yhqd.zgqx" value="<fmt:formatDate type='both' value='${yhqd.zgqx}' pattern="yyyy-MM-dd"/>" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">所需资金</th>
					<td width="35%"><input name="yhqd.sxzj" value="${yhqd.sxzj}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr> 
					<th width="15%">
						上传附件
					</th>
					<td colspan="3">
						<div id="fileQueue" />
							<input type="file" name="uploadify" id="uploadify"/>
							<a href="javascript:$('#uploadify').uploadifyUpload()"  >开始上传</a>&nbsp;
							<a href="javascript:$('#uploadify').uploadifyClearQueue()"  >取消所有上传</a>
					</td>
				</tr>
				<tr> 
					<th width="15%">
						已传附件
					</th>
					<td colspan="3"> 
						 <table id="more">
						 <!-- 迭代附件 -->
						 <s:iterator id="photoPic" value="#request.yhqd.picList" status="cst">
							<tr>
								<td width="40%">
										<c:choose>
											<c:when
												test="${fn:endsWith(fn:toLowerCase(picName),'.jpg')
										    	||fn:endsWith(fn:toLowerCase(picName),'.bmp')
										    	||fn:endsWith(fn:toLowerCase(picName),'.png')
										    	||fn:endsWith(fn:toLowerCase(picName),'.jpeg')
										    	||fn:endsWith(fn:toLowerCase(picName),'.gif')}"> 
											  	  &nbsp;&nbsp;&nbsp;
											    	 <a href="/upload/photo/${picName}"
													rel="example_group"> <img
														src="/upload/photo/${picName}" border='0' width='220'
														height='150' /> </a>
											</c:when>
											<c:otherwise> 
									     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;${fileName}</FONT>
											</c:otherwise>
										</c:choose>
								</td>
								<td width="30%">
									<textarea style="height: 30px;width: 150px;" name='yhqd.picList[<s:property value="#cst.index" />].taskRemark'>${taskRemark}</textarea>
									<input type="hidden" name="yhqd.picList[<s:property value="#cst.index" />].taskProId" value="${taskProId}">
										<input type="hidden" name="yhqd.picList[<s:property value="#cst.index" />].id" value="${id}">
										<input type="hidden" class='delFlag'name="yhqd.picList[<s:property value="#cst.index" />].delFlag" value="${delFlag}">
								</td>
								<td>
									<a href="javascript:void(0)" onclick="del(this)">删除</a>
								</td>
							</tr>
						</s:iterator>
						 </table>
					</td>
				</tr>
				<tr>
					<th width="15%">整改方案</th>
					<td colspan="3"><textarea name="yhqd.zgfa"dataType="Require" msg="此项为必填" style="width:100%;height:120px">${yhqd.zgfa}</textarea><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">防范措施</th>
					<td colspan="3"><textarea name="yhqd.ffcs"dataType="Require" msg="此项为必填" style="width:100%;height:120px">${yhqd.ffcs}</textarea><font color="red">*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close()||parent.close_win()">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<script type="text/javascript">

	var data = '${ctx}/jsp/checkBasic/savePhoto.action?type=check';
	$('#uploadify').uploadify({
	'uploader': '<c:url value="/webResources/js/uploadify.swf"/>', 
	'buttonImg': '<c:url value="/webResources/js/browse_sc.jpg"/>', 
	'script':data,
	'cancelImg': '<c:url value="/webResources/js/cancel.png"/>',                  
	'queueID' : 'fileQueue', //和存放队列的DIV的id一致  
	'auto'  : false, //是否自动开始  
	'multi': false, //是否支持多文件上传  
	'buttonText': 'BROWSE', //按钮上的文字  
	'simUploadLimit' : 1, //一次同步上传的文件数目  
	'sizeLimit': 19871202, //设置单个文件大小限制，单位为byte  
	'queueSizeLimit' : 10, //队列中同时存在的文件个数限制  
	'fileDesc': '所有文件：*.*', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
	'fileExt': '*.*;',//允许的格式
	'onComplete': function (event, queueID, fileObj, response, data) {
	     addmore(response);
	},
	 'onAllComplete': function (event, data) { 
	 	alert("上传成功");
	},  
	'onError': function(event, queueID, fileObj) {  
		alert("文件:" + fileObj.name + "上传失败");  
	},  
	'onCancel':function(event, queueID, fileObj){}
	});

	String.prototype.endWith=function(str){     
		  var reg=new RegExp(str+"$");     
		  return reg.test(this);        
		}
	   function addmore(data){
		   data=eval("(" + data + ")");
		   var pid = data.pid;
		   var picName = data.picName;
		   var linkId = data.linkId;
		   var $table = $("#more");
		   var index = $table.find("tr").size();
		   var $tr=$("<tr></tr>");
		   var $td3=$("<td width='30%'><a href='javascript:void(0)' onclick='del(this)'>删除</a></td>");
		   var td1Text ='';
		   if(picName.endWith(".jpg")||picName.endWith(".bmp")||picName.endWith(".png")||picName.endWith(".jpeg")||picName.endWith(".gif")){
			   td1Text ='&nbsp;&nbsp;&nbsp;<a href="/upload/photo/'+picName+'"rel="example_group"> <img src="/upload/photo/'+picName+'" border="0" width="220" height="150" /> </a>';
		   }else{
			   td1Text='&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;'+picName +'</FONT>';
		   }
		   var $td1=$("<td width='40%'>"+td1Text+"</td>");
		   var $td2=$('<td width="30%">'
				   +'<textarea style="height: 30px;width: 150px;" name="yhqd.picList['+index+'].taskRemark"> </textarea>'
			+'<input type="hidden" name="yhqd.picList['+index+'].taskProId" value="'+linkId+'">'
			+'<input type="hidden" name="yhqd.picList['+index+'].id" value="'+pid+'">'
			+'<input type="hidden" class ="delFlag"name="yhqd.picList['+index+'].delFlag" value="0"></td>'); 
		   $tr.append($td1);
		   $tr.append($td2);
		   $tr.append($td3);
		   $table.append($tr);
		  
		}
		function del(self){
			$(self).parent().parent().hide();
			$(self).parent().prev().find(".delFlag").eq(0).val("1");
		}
		function selectUser(self)
		{
			var str = showModalDialog("${ctx}/jsp/checkBasic/selectUsers.action?checkBasic.id=${checkBasic.id}",window,'dialogWidth:650px;dialogHeight:500px;scroll:no;center:yes');
			if(str != null && str.length > 0)
			{
				$(self).prev().val(str[0]);
				$(self).prev().prev().val(str[1]);
			}
			
		}
</script>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

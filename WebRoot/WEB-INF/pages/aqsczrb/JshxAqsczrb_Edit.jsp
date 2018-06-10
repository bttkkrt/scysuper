<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<style>  
  .inputText4 {  
    font-family:Arial,Helvetica,sans-serif;   
    background:none repeat scroll 0 0 #F5F7FD;   
    border:1px solid #B8BFE9;   
    padding:5px 7px;   
    width:100px;   
    vertical-align:middle;   
    height:20px;   
    font-size:12px;   
    margin:0;   
    list-style:none outside none;   
    }   
  </style>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="jshxAqsczrbSave.action";
				document.myform1.submit();
			}
		}
		 //图片上传 lj 2013-08-15 
        function uploadPhoto(){
        		window.open("jshxAqsczrbUpload.action?jshxAqsczrb.linkId=${jshxAqsczrb.linkId}");
        	}
         function savepic(i,j){
        		window.location.href="jshxAqsczrbSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "jshxAqsczrbImageDel.action",
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
		<input type="hidden" id="linkId" name="jshxAqsczrb.linkId" value="${jshxAqsczrb.linkId}"/>
		<input type="hidden" name="jshxAqsczrb.id" value="${jshxAqsczrb.id}">
		<input type="hidden" name="jshxAqsczrb.createTime" value="<fmt:formatDate type="both" value="${jshxAqsczrb.createTime}" />">
		<input type="hidden" name="jshxAqsczrb.updateTime" value="${jshxAqsczrb.updateTime}">
		<input type="hidden" name="jshxAqsczrb.createUserID" value="${jshxAqsczrb.createUserID}">
		<input type="hidden" name="jshxAqsczrb.updateUserID" value="${jshxAqsczrb.updateUserID}">
		<input type="hidden" name="jshxAqsczrb.deptId" value="${jshxAqsczrb.deptId}">
		<input type="hidden" name="jshxAqsczrb.delFlag" value="${jshxAqsczrb.delFlag}">
		
		<input type="hidden" name="jshxAqsczrb.szzname" value="${jshxAqsczrb.szzname}">
		<input type="hidden" name="jshxAqsczrb.qymc" value="${jshxAqsczrb.qymc}">
		<input type="hidden" name="jshxAqsczrb.szzid" value="${jshxAqsczrb.szzid}">
		<input type="hidden" name="jshxAqsczrb.qyid" value="${jshxAqsczrb.qyid}">
		<input type="hidden" name="jshxAqsczrb.qylx" value="${jshxAqsczrb.qylx}">
		<input type="hidden" name="jshxAqsczrb.hyfl" value="${jshxAqsczrb.hyfl}">
		<input type="hidden" name="jshxAqsczrb.qygm" value="${jshxAqsczrb.qygm}">
		<input type="hidden" name="jshxAqsczrb.qyzclx" value="${jshxAqsczrb.qyzclx}">
		
		<input type="hidden" name="jshxAqsczrb.szc" value="${jshxAqsczrb.szc}">
		<input type="hidden" name="jshxAqsczrb.szcname" value="${jshxAqsczrb.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="35%" colspan="2">企业总人数</th>
					<td width="65%"><input  class="inputText4" name="jshxAqsczrb.count" value="${jshxAqsczrb.count}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="35%" colspan="2">一线员工数</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.onecount" value="${jshxAqsczrb.onecount}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%" rowspan="3">安全生产责任险（包含雇主责任、人身意外伤害、公众责任和事故救援等因素）</th>
					<th width="15%">参保人数</th>
					<td width="65%"><input class="inputText4" name="jshxAqsczrb.cbcount" value="${jshxAqsczrb.cbcount}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">总保费(万元)</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.totalfee" value="${jshxAqsczrb.totalfee}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">总保额(万元)</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.totalmoney" value="${jshxAqsczrb.totalmoney}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="20%" rowspan="3">雇主责任险</th>
					<th width="15%">参保人数</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.personCount" value="${jshxAqsczrb.personCount}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">总保费(万元)</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.totalfee02" value="${jshxAqsczrb.totalfee02}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">总保额(万元)</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.totalmoney02" value="${jshxAqsczrb.totalmoney02}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="20%" rowspan="2" >团体人身意外伤害险</th>
					<th width="15%">总保费(万元)</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.totalfee03" value="${jshxAqsczrb.totalfee03}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">总保额(万元)</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.totalmoney03" value="${jshxAqsczrb.totalmoney03}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="20%" rowspan="2" >公众责任险</th>
					<th width="15%">总保费(万元)</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.totalfee04" value="${jshxAqsczrb.totalfee04}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">总保额((万元))</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.totalmoney04" value="${jshxAqsczrb.totalmoney04}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="35%" colspan="2">保险公司</th>
					<td width="65%"><input class="inputText4"  name="jshxAqsczrb.bxgs" value="${jshxAqsczrb.bxgs}" type="text" maxlength="255"></td>
				</tr>
					<tr>
					<th width="35%">责任险发票扫描件</th>
					<td width="65%"  colspan="2">
						<table width="100%">
							<tr>
									<td>
										<center><a style="color:blue;" href="javascript:uploadPhoto()" title="点击上传附件">选择责任险发票扫描件</a></center>
									</td>
								</tr>
								<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="aqsczrb">
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
					<td colspan="4" height="100px"  style="text-align:center;">
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

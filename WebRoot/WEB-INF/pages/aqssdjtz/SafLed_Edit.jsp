<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>  
	$(document).ready(function() {
		  uploadPic("uploadify","${safLed.linkId}","aqssdjtz","aqssdjtzfj","fileQueue");
		});
	
	function save(){
		
		if($("#category1").val()==""&&$("#category2").val()==""&&$("#category3").val()=="")
		{
		alert("请选择类别");
		return false;
		}
		
			
				document.myform1.action="safLedSave.action";
				document.myform1.submit();
			
		}
	 $().ready(function(){
		        changeType($("#accountType").val());
		    })
	    function changeType(obj)  
		    {
		    for(var i=1;i<4;i++)
		    {
		    
		    if(i==obj)
		   $("#div"+obj).css("display","block");
		    else
		   $("#div"+i).css("display","none");
		    }
		    }
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" >
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="safLed.id" value="${safLed.id}">
		<input type="hidden" name="safLed.createTime" value="<fmt:formatDate type="both" value="${safLed.createTime}" />">
		<input type="hidden" name="safLed.updateTime" value="${safLed.updateTime}">
		<input type="hidden" name="safLed.createUserID" value="${safLed.createUserID}">
		<input type="hidden" name="safLed.updateUserID" value="${safLed.updateUserID}">
		<input type="hidden" name="safLed.deptId" value="${safLed.deptId}">
		<input type="hidden" name="safLed.delFlag" value="${safLed.delFlag}">
		<input type="hidden" name="safLed.category" id="category" value="${safLed.category1}">
		<input type="hidden" name="safLed.linkId" value="${safLed.linkId}">
			<table width="100%" border="0">
			
				<tr>
					<th width="15%">台账类型</th>
					<td width="85%"><cus:SelectOneTag property="safLed.accountType" style="width:60%;" defaultText='请选择' codeName="安全设施登记台账类别" value="${safLed.accountType}" onchange="changeType(this.value)" datatype="*"  nullmsg='台账类型不能为空！'/><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">类别</th>
					<td width="85%">
						<div id="div1" style="display:none"><cus:SelectOneTag style="width:60%;" property="safLed.category1"   defaultText='请选择' codeName="预防事故设施台帐" value="${safLed.category}"  /><font style="color:red">*</font></div>
						<div id="div2" style="display:none"><cus:SelectOneTag style="width:60%;" property="safLed.category2"   defaultText='请选择' codeName="控制事故设施台帐" value="${safLed.category}"  /><font style="color:red">*</font></div>
						<div id="div3" style="display:none"><cus:SelectOneTag style="width:60%;" property="safLed.category3"  defaultText='请选择' codeName="减少与消除事故影响设施台帐" value="${safLed.category}" /><font style="color:red">*</font></div>
					</td>
				</tr>
				<tr>
					<th width="15%">附件上传</th>
					<td width="85%" colspan="3">
				   		<div id="fileQueue"/>
				    	<input type="file" name="uploadify" id="uploadify"/>
			    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
						<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
						<font style='color:red'>上传附件最大上限为50M</font>
				    </td>
				</tr>
				<tr>
					<th width="15%">已添加附件</th>
					<td width="85%" colspan="3" style="padding:0 0 0 0;">
						<div style="color:green;overflow:auto;height:175px;">
						<table id="aqssdjtzfj">
							  <c:forEach var="item" items="${picList}">
								<tr id='${item.id}' style="text-align: center">
								   <td width="70%">
								   		<c:choose>
											<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
											||fn:endsWith(fn:toLowerCase(item.picName),'.png')
											||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
											||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
												<img src="${item.httpUrl}/upload/photo/${item.picName}"
												border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:when> 
											<c:otherwise> 
												&nbsp;&nbsp;&nbsp;${item.fileName}
											</c:otherwise>
										</c:choose>
								   </td>
								   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
								   <a href="javascript:del('${item.id}');">删除</a></td>
								</tr>
							  </c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" onclick="save()" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" onclick="save()" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_safLed');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

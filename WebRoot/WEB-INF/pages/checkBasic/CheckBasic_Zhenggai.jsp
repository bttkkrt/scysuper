<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>整改隐患</title>
	<%@include file="/common/jsLib.jsp"%>
				<link href="<c:url value='/webResources/js/uploadify.css'/>"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src='<c:url value="/webResources/js/swfobject.js"/>'></script>
		<script type="text/javascript"
			src='<c:url value="/webResources/js/jquery.uploadify.v2.1.4.js"/>'></script>
</head>
<body>

 
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post" action="">
	<input type="hidden" name="yhqd.id" value="${yhqd.id}">
	<input type="hidden" name="taskId" value="${taskId}">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">被检查单位名称</th>
					<td width="35%">${yhqd.basic.company.companyname}</td>
					<th width="15%">检查时间</th>
					<td width="35%"><fmt:formatDate type="both" value="${yhqd.checktime}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">隐患内容</th>
					<td colspan="3"><div readOnly name="yhqd.yhContent" style="width:100%;height:120px">${yhqd.yhContent}</div></td>
				</tr>
				<tr>
					<th width="15%">监管部门跟踪责任人</th>
					<td width="35%">${yhqd.jgzrrNames}</td>
					<th width="15%">整改期限</th>
					<td width="35%"><fmt:formatDate type="both" value="${yhqd.zgqx}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th width="15%">企业责任人</th>
					<td width="35%">${yhqd.qyzrr}</td>
					<th width="15%">企业责任人联系电话</th>
					<td width="35%">${yhqd.qyzrrlxdh}</td>
				</tr>
				<tr>
					<th width="15%">所需资金</th>
					<td width="35%">${yhqd.sxzj}</td>
				</tr>
				<tr> 
					<th width="15%">
						已传附件
					</th>
					<td colspan="3" style="padding: 0"> 
						 <table>
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
									 ${taskRemark} 
								</td>
							</tr>
						</s:iterator>
						 </table>
					</td>
				</tr>
				<tr>
					<th width="15%">整改方案</th>
					<td colspan="3"><div readOnly name="yhqd.zgfa" style="width:100%;height:120px">${yhqd.zgfa}</div></td>
				</tr>
				<tr>
					<th width="15%">防范措施</th>
					<td colspan="3"><div readOnly name="yhqd.ffcs" style="width:100%;height:120px">${yhqd.ffcs}</div></td>
				</tr>
				<tr>
					<th width="15%">整改说明</th>
					<td colspan="3"><textarea   name="yhqd.zgInfo" style="width:100%;height:120px"></textarea></td>
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
						整改附件
					</th>
					<td colspan="3"> 
						 <table id="more">
						 <!-- 迭代附件 -->
						 <s:iterator id="photoPic" value="#request.yhqd.zgpicList" status="cst">
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
									<textarea style="height: 30px;width: 150px;" name='yhqd.zgpicList[<s:property value="#cst.index" />].taskRemark'>${taskRemark}</textarea>
									<input type="hidden" name="yhqd.zgpicList[<s:property value="#cst.index" />].taskProId" value="${taskProId}">
										<input type="hidden" name="yhqd.zgpicList[<s:property value="#cst.index" />].id" value="${id}">
										<input type="hidden" class='delFlag'name="yhqd.zgpicList[<s:property value="#cst.index" />].delFlag" value="${delFlag}">
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
					<th>历史流程</th>
					<td colspan="3" style="padding: 0px">
					<c:if test="${empty yhqd.zgysr}">无</c:if>
					<c:if test="${not empty yhqd.zgysr}"> 
					<s:generator val="yhqd.zgysr" separator="‖" id="zgysrList"/> 
					<c:set value="${ fn:split(yhqd.yssj, '‖') }" var="yssjList" />
					<c:set value="${ fn:split(yhqd.resultContent, '‖') }" var="resultContentList" />
					<table>
						<tr>
							<th style="text-align: left;"> 验收人</th>
							<th style="text-align: left;"> 验收时间</th>
							<th style="text-align: left;"> 结论</th>
						</tr>
						<s:iterator var="zgysr"  value="zgysrList" status="st"   >
						<tr>
							<td> ${zgysr}</td>
							<td> ${yssjList[st.index]}</td>
							<td> ${resultContentList[st.index]}</td>
						</tr>
						</s:iterator>
					</table>
					</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center">
					<a href="#" class="easyui-linkbutton" onclick="zhenggai()" iconCls="icon-save">整改</a>&nbsp;
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
		<script>
		function zhenggai(){
				document.myform.action="${ctx}/jsp/checkBasic/doZhenggai.action";
				document.myform.submit();
		}
		
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
			   var td1Text ="";
			   if(picName.endWith(".jpg")||picName.endWith(".bmp")||picName.endWith(".png")||picName.endWith(".jpeg")||picName.endWith(".gif")){
				   td1Text ='&nbsp;&nbsp;&nbsp;<a href="/upload/photo/'+picName+'"rel="example_group"> <img src="/upload/photo/'+picName+'" border="0" width="220" height="150" /> </a>';
			   }else{
				   td1Text='&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;'+picName +'</FONT>';
			   } 
			   var $td1=$("<td width='40%'>"+td1Text+"</td>");
			   var $td2=$('<td width="30%">'
					   +'<textarea style="height: 30px;width: 150px;" name="yhqd.zgpicList['+index+'].taskRemark"> </textarea>'
				+'<input type="hidden" name="yhqd.zgpicList['+index+'].taskProId" value="'+linkId+'">'
				+'<input type="hidden" name="yhqd.zgpicList['+index+'].id" value="'+pid+'">'
				+'<input type="hidden" class ="delFlag"name="yhqd.zgpicList['+index+'].delFlag" value="0"></td>'); 
			   $tr.append($td1);alert(31);
			   $tr.append($td2);
			   $tr.append($td3);
			   $table.append($tr);
			  
			}
			function del(self){
				$(self).parent().parent().hide();
				$(self).parent().prev().find(".delFlag").eq(0).val("1");
			}
	</script>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>复查</title>
		<%@include file="/common/jsLib.jsp"%>
			<link href="<c:url value='/webResources/js/uploadify.css'/>"
				rel="stylesheet" type="text/css" />
			<script type="text/javascript"
				src='<c:url value="/webResources/js/swfobject.js"/>'></script>
			<script type="text/javascript"
				src='<c:url value="/webResources/js/jquery.uploadify.v2.1.4.js"/>'></script>
		<script>
			function createSaveCheckDoc(id)
			{
				myform.action = "${ctx}/jsp/checkBasic/createSaveCheckDoc.action?checkBasic.id="+id;
				myform.submit();
			}
			function createFieldCheckDoc(id)
			{
				myform.action = "${ctx}/jsp/checkBasic/createFieldCheckDoc.action?checkBasic.id="+id;
				myform.submit();
			}
			function createRectifyCheckDoc(id)
			{
				myform.action = "${ctx}/jsp/checkBasic/createRectifyCheckDoc.action?checkBasic.id="+id;
				myform.submit();
			}
			function createTDRectifyCheckDoc(id)
			{
				myform.action = "${ctx}/jsp/checkBasic/createTDRectifyCheckDoc.action?checkBasic.id="+id;
				myform.submit();
			}
			function save(){
					document.myform.action="checkBasicFucha.action";
					document.myform.submit();
			}
			
			$(document).ready(function() {
				  var picNames="";
				  var data = '${ctx}/jsp/checkBasic/savePhoto.action?type=rectify';
				　$("#uploadify").uploadify({
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
				});
				String.prototype.endWith=function(str){     
				  var reg=new RegExp(str+"$");     
				  return reg.test(this);        
				}
				function del(self){
					$(self).parent().parent().hide();
					$(self).parent().prev().find(".delFlag").eq(0).val("1");
				}
			   function addmore(data){
				   data=eval("(" + data + ")");
				   var pid = data.pid;
				   var picName = data.picName;
				   var linkId = data.linkId;
				   var index = $("#rectifyOpinionTable tr").size();
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
						   +'<textarea style="height: 30px;width: 150px;" name="checkBasic.rectifyOpinionList['+index+'].opinion"> </textarea>'
					+'<input type="hidden" name="checkBasic.rectifyOpinionList['+index+'].linkid" value="'+linkId+'">'
					+'<input type="hidden" class ="delFlag"name="checkBasic.rectifyOpinionList['+index+'].delFlag" value="0"></td>'); 
				   $tr.append($td1);
				   $tr.append($td2);
				   $tr.append($td3);
				   $("#rectifyOpinionTable").append($tr);
				}
		</script>
	</head>
	<body>
		<%@include file="/WEB-INF/template/content_title.jsp"%>
		<form name="myform" method="post" enctype="multipart/form-data"
			id="myform1">
			<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="checkBasic.id" value="${checkBasic.id}">
		<input type="hidden" name="checkBasic.createTime" value="<fmt:formatDate type="both" value="${checkBasic.createTime}" />">
		<input type="hidden" name="checkBasic.updateTime" value="${checkBasic.updateTime}">
		<input type="hidden" name="checkBasic.createUserID" value="${checkBasic.createUserID}">
		<input type="hidden" name="checkBasic.updateUserID" value="${checkBasic.updateUserID}">
		<input type="hidden" name="checkBasic.deptId" value="${checkBasic.deptId}">
		<input type="hidden" name="checkBasic.delFlag" value="${checkBasic.delFlag}">
		<input type="hidden" name="checkBasic.checkTime" value="${checkBasic.checkTime}">
		<input type="hidden" name="checkBasic.checker.id" value="${checkBasic.checker.id}">
		<input type="hidden" name="checkBasic.rectifyFlag" value="${checkBasic.rectifyFlag}" id="rectifyFlag">
			<div class="easyui-tabs" style="overflow-x: hidden; width: 1200px;">
				<div title="安全生产执法检查表" style="overflow-x: hidden;" class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						<table style="width: 98%">
							<tr>
								<th width="15%">
									检查人
								</th>
								<td width="35%">
									${checkBasic.checker.displayName}
								</td>
								<th width="15%">
									检查时间
								</th>
								<td width="35%">
									<fmt:formatDate type='both' value='${checkBasic.checkTime}'
										pattern="yyyy-MM-dd HH:MM:ss" />
								</td>
							</tr>
							<tr>
								<th width="15%">
									企业名称
								</th>
								<td colspan="3">
									${checkBasic.company.companyname}
								</td>
							</tr>
							<tr>
								<th width="15%">
									地 址
								</th>
								<td colspan="3">
									${checkBasic.company.dwdz}
								</td>
							</tr>
							<tr>
								<th width="15%">
									主要负责人
								</th>
								<td width="35%">
									${checkBasic.company.fddbr}
								</td>
								<th width="15%">
									联系方式
								</th>
								<td width="35%">
									${checkBasic.company.fddbrlxhm}
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align: center;" width="100%">
									<table>
										<tr>
											<td width="10%" style="text-align: center;">
												序号
											</td>
											<td width="50%" style="text-align: center;">
												检查内容
											</td>
											<td width="10%" style="text-align: center;">
												检查结果
											</td>
											<td width="30%" style="text-align: center;">
												备注
											</td>
										</tr>
										<s:iterator value="crList" status="st" id="category">
											<tr>
												<td style="text-align: center;">
													<s:property value="#category.indexNum" />
												</td>
												<td style="text-align: center;" colspan="3">
													<s:property value="#category.categoryContent" />
												</td>
											</tr>
											<s:iterator value="#category.contentList" status="sc"
												id="content">
												<tr>
													<td style="text-align: center;">
														<s:property value="#sc.index+1" />
													</td>
													<td style="text-align: center;">
														<s:property value="#content.content" />
													</td>
													<td style="text-align: center;">
														<s:if test="#content.checkResult == 0">
															良好
														</s:if>
														<s:elseif test="#content.checkResult == 1">
															一般
														</s:elseif>
														<s:else>
															较差
														</s:else>
													</td>
													<td style="text-align: center;">
														<s:property value="#content.remark" />
													</td>
												</tr>
											</s:iterator>
										</s:iterator>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="4" height="100px" style="text-align: center;">
									<a href="#" class="easyui-linkbutton" iconCls="icon-add"
										onclick="createSaveCheckDoc('${checkBasic.id}');">生成安全生产执法检查表</a>
									<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
										onclick="window.close();">关闭</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div title="现场检查记录" closable="false" style="overflow-x: hidden;"
					class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						<table style="width: 98%">
							<tr>
								<th width="15%">
									被检查单位
								</th>
								<td colspan="3">
									${checkBasic.company.companyname}
								</td>
							</tr>
							<tr>
								<th width="15%">
									地 址
								</th>
								<td colspan="3">
									${checkBasic.company.dwdz}
								</td>
							</tr>
							<tr>
								<th width="15%">
									法定代表人(负责人)
								</th>
								<td width="35%">
									${checkBasic.company.fddbr}
								</td>
								<th width="15%">
									联系方式
								</th>
								<td width="35%">
									${checkBasic.company.fddbrlxhm}
								</td>
							</tr>
							<tr>
								<th width="15%">
									检查场所
								</th>
								<td colspan="3">
									${checkBasic.checkPlace}
								</td>
							</tr>
							<tr>
								<th width="15%">
									检查时间
								</th>
								<td colspan="3">
									<fmt:formatDate type='both' value='${checkBasic.checkSta}'
										pattern="yyyy-MM-dd" />
									至
									<fmt:formatDate type='both' value='${checkBasic.checkEnd}'
										pattern="yyyy-MM-dd" />
								</td>
							</tr>
							<tr>
								<th>
									执法机构
								</th>
								<td>
									${ checkBasic.checkOrganization}
								</td>
							</tr>
							<tr>
								<th width="15%">
									执法人员
								</th>
								<td width="35%">
									${lawEnforceName}
								</td>
								<th width="15%">
									证件号码
								</th>
								<td width="35%">
									${idNum}
								</td>
							</tr>
							<tr>
								<th>
									检查情况
								</th>
								<td colspan="3">
									<table width="100%">
										<s:iterator id="situation" value="situationList">
											<tr>
												<td width="70%">
													<s:iterator id="attach" value="#situation.pathList">
														<c:choose>
															<c:when
																test="${fn:endsWith(fn:toLowerCase(attach),'.jpg')
														    	||fn:endsWith(fn:toLowerCase(attach),'.bmp')
														    	||fn:endsWith(fn:toLowerCase(attach),'.png')
														    	||fn:endsWith(fn:toLowerCase(attach),'.jpeg')
														    	||fn:endsWith(fn:toLowerCase(attach),'.gif')}"> 
															  	  &nbsp;&nbsp;&nbsp;
															    	 <a href="/upload/photo/${attach}"
																	rel="example_group"> <img
																		src="/upload/photo/${attach}" border='0' width='220'
																		height='150' /> </a>
															</c:when>
															<c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;非图片</FONT>
															</c:otherwise>
														</c:choose>
													</s:iterator>
												</td>
												<td width="30%">
													${situation.discreption}
												</td>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="4" height="100px" style="text-align: center;">
									<a href="#" class="easyui-linkbutton" iconCls="icon-add"
										onclick="createFieldCheckDoc('${checkBasic.id}');">生成现场检查记录表</a>
									<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
										onclick="window.close();">关闭</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div title="责令限期整改指令书" closable="false" style="overflow-x: hidden;"
					class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						&nbsp;&nbsp;${checkBasic.company.companyname}:
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp; 经查，你单位存在下列问题：
						<br>
						<br>
						<table width="100%">
							<s:iterator id="rectify" value="rectifyList">
								<tr>
									<td width="70%">
										<s:iterator id="attach" value="#rectify.pathList">
											<c:choose>
												<c:when
													test="${fn:endsWith(fn:toLowerCase(attach),'.jpg')
													    	||fn:endsWith(fn:toLowerCase(attach),'.bmp')
													    	||fn:endsWith(fn:toLowerCase(attach),'.png')
													    	||fn:endsWith(fn:toLowerCase(attach),'.jpeg')
													    	||fn:endsWith(fn:toLowerCase(attach),'.gif')}"> 
														  	  &nbsp;&nbsp;&nbsp;
														    	 <a href="/upload/photo/${attach}"
														rel="example_group"> <img
															src="/upload/photo/${attach}" border='0' width='220'
															height='150' /> </a>
												</c:when>
												<c:otherwise> 
												     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;非图片</FONT>
												</c:otherwise>
											</c:choose>
										</s:iterator>
									</td>
									<td width="30%">
										${rectify.discreption}
									</td>
								</tr>
							</s:iterator>
						</table>
						<br>
						<br>
						&nbsp;&nbsp;&nbsp;&nbsp;现责令你单位对上述第${checkBasic.rectifyTerm}项问题于
						<fmt:formatDate type='both' value='${checkBasic.rectifyDate}'
							pattern="yyyy-MM-dd" />
						前整改完毕， 达到有关法律法规规章和标准规定的要求。逾期不整改或达不到要求的，依法给予行政处罚；由此造成事故的，
						依法追究有关人员的责任。如果不服本指令，可以依法在60日内向苏州市太仓市人民政
						府或者苏州市安全生产监督管理局申请行政复议，或者在三个月内依法向苏州市太仓市人民法院提起行政诉讼，但本指
						令不停止执行，法律另有规定的除外。
						<table>
							<tr>
								<td height="100px" style="text-align: center;">
									<a href="#" class="easyui-linkbutton" iconCls="icon-add"
										onclick="createRectifyCheckDoc('${checkBasic.id}');">生成责令限期整改指令书</a>
									<a href="#" class="easyui-linkbutton" iconCls="icon-add"
										onclick="createTDRectifyCheckDoc('${checkBasic.id}');">生成套打责令限期整改指令书</a>
									<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
										onclick="window.close();">关闭</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div title="整改复查意见书" closable="false" style="overflow-x: hidden;"
					class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						<table style="width: 98%;">
							&nbsp;&nbsp;${checkBasic.company.companyname}:
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp; 本机关于
							<input   name="checkBasic.rectifyBeginTime" class="Wdate" type="text"   style='width: 120px;'dataType="Require" msg="此项为必填" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})"value="<fmt:formatDate type='both' value='${checkBasic.rectifyBeginTime}'
										pattern="yyyy-MM-dd " />"   placeholder="年-月-日">
							作出了<input type="text" value="${checkBasic.reviewContent}" name='checkBasic.reviewContent'>的决定【太仓安监管责改[<input   name="checkBasic.rectifyYear" class="Wdate" type="text"   style='width: 60px;'dataType="Require" msg="此项为必填" onfocus="WdatePicker({dateFmt:'yyyy'})"value="${checkBasic.rectifyYear}"   placeholder="年">]<input type="text" style='width: 60px;'value="${checkBasic.rectifyNum}" name='checkBasic.rectifyNum'>号】，经对你单位整改情况进
							行复查，提出如下意见:
						</table>
						<table>
							<tr>
								<th width="15%" style="text-align: center;">
									整改意见:
								</th>
								<td>
								<div id="fileQueue" />
								<input type="file" name="uploadify" id="uploadify" />
								<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
								<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
								
								</td>
								</tr>
								<tr>
								<th></th>
								<td width="85%">
									<table width="100%" id="rectifyOpinionTable">
										<s:iterator id="opList" value="opinionList" status="st">
											<tr>
												<td width="50%">
													<s:iterator id="attach" value="#opList.pathList">
														<c:choose>
															<c:when
																test="${fn:endsWith(fn:toLowerCase(attach),'.jpg')
														    	||fn:endsWith(fn:toLowerCase(attach),'.bmp')
														    	||fn:endsWith(fn:toLowerCase(attach),'.png')
														    	||fn:endsWith(fn:toLowerCase(attach),'.jpeg')
														    	||fn:endsWith(fn:toLowerCase(attach),'.gif')}"> 
															  	  &nbsp;&nbsp;&nbsp;
															    	 <a href="/upload/photo/${attach}"
																	rel="example_group"> <img
																		src="/upload/photo/${attach}" border='0' width='220'
																		height='150' /> </a>
															</c:when>
															<c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color: red;">&nbsp;&nbsp;&nbsp;非图片</FONT>
															</c:otherwise>
														</c:choose>
													</s:iterator>
												</td>
												<td width="30%">
													<textarea style="height: 30px;width: 150px;" name='checkBasic.rectifyOpinionList[<s:property value="#st.index" />].opinion'>${opList.opinion}</textarea>
													<input type="hidden" name="checkBasic.rectifyOpinionList[<s:property value="#st.index" />].linkid" value="${opList.linkid}">
														<input type="hidden" name="checkBasic.rectifyOpinionList[<s:property value="#st.index" />].id" value="${opList.id}">
														<input type="hidden" class='delFlag'name="checkBasic.rectifyOpinionList[<s:property value="#st.index" />].delFlag" value="${opList.delFlag}">
											
												</td>
												<td width="20%">
												<a href="javascript:void(0)" onclick="del(this)">删除</a>
												</td>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
						</table>
						<table>
							<tr>
								<td height="100px" style="text-align: center;">
										<a href="#" class="easyui-linkbutton" onclick="save()"
									iconCls="icon-save">提交</a>&nbsp;
									<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
										onclick="window.close();">关闭</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>
		<%@include file="/WEB-INF/template/pagefoot.jsp"%>
	</body>
</html>

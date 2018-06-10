<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML >
<html>
	<head>
		<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
		<style type="text/css">
		.yhinfo{
		 padding: 0px;margin: 0px; 
		}</style>
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
		<form name="myform1" method="post" enctype="multipart/form-data"
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
		<%-- <input type="hidden" name="checkBasic.checkTime" value="${checkBasic.checkTime}"> --%>
		<input type="hidden" name="checkBasic.checker.id" value="${checkBasic.checker.id}">
		<input type="hidden" name="checkBasic.rectifyFlag" value="${checkBasic.rectifyFlag}" id="rectifyFlag">
		
			<div class="easyui-tabs" style="overflow-x: hidden; width: 1200px;">
				<div title="安全生产执法检查表" style="overflow-x: hidden;" class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						<table style="width: 98%">
							<tr>
								<th width="15%">
									企业名称
								</th>
								<td >
									<input id="qymc" name="checkBasic.company.companyname" type="text"  dataType="Require" msg="此项为必填" value="${checkBasic.company.companyname}" readonly="readonly"><font style="color:red">*</font>
					<input name="checkBasic.company.id" value="${checkBasic.company.id}"  type="hidden" id="qyid" type="text"  >
					<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>
					
								</td>
								<th width="15%">
									地 址
								</th>
								<td  >
									<input id="dwdz2" name="checkBasic.company.dwdz2" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${checkBasic.company.dwdz2}" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th width="15%">
									主要负责人
								</th>
								<td width="35%">
									<input id="fddbr" name="checkBasic.company.fddbr" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${checkBasic.company.fddbr}" readonly="readonly">
								</td>
								<th width="15%">
									联系方式
								</th>
								<td width="35%">
									<input id="fddbrlxhm" name="checkBasic.company.fddbrlxhm" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${checkBasic.company.fddbrlxhm}" readonly="readonly">
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
										</tr><%int listIndex=0; %>
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
														<input type="hidden" name='checkBasic.resultList[<%=listIndex%>].id' value="<s:property value="#content.indexNum" />">
														<input type="hidden" name='checkBasic.resultList[<%=listIndex%>].checkBasic.id' value="${checkBasic.id}">
														<input type="hidden" name='checkBasic.resultList[<%=listIndex%>].checkBasic.delFlag' value="<s:property value="#content.delFlag" />">
													</td>
													<td style="text-align: center;"> 
														<s:property value="#content.content" />
														<input type="hidden" name='checkBasic.resultList[<%=listIndex%>].content.id' value="<s:property value="#content.id" />">
													</td>
													<td style="text-align: center;">
													<select name='checkBasic.resultList[<%=listIndex%>].checkResult'>
														<option value="0"
														<s:if test="#content.checkResult == 0">
															selected
														</s:if>
														>良好</option>
														<option value="1"
														<s:if test="#content.checkResult == 1">
															selected
														</s:if>
														>一般</option>
														<option value="2"
														<s:if test="#content.checkResult == 2">
															selected
														</s:if>
														>较差</option>
													</select>
														
														 
													</td>
													<td style="text-align: center;">
													<textarea style="height: 30px;width: 150px;" name='checkBasic.resultList[<%=listIndex%>].remark'><s:property value="#content.remark" /></textarea>
													<%++listIndex;%>
													</td>
												</tr>
											</s:iterator>
										</s:iterator>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div title="隐患排查任务" closable="false" style="overflow-x: hidden;"
					class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						<table style="width: 98%">
							<tr>
								<th width="15%">
									检查场所
								</th>
								<td  >
									<input   name="checkBasic.checkPlace" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${checkBasic.checkPlace}"  >
								</td>
								<th width="15%">
									检查时间
								</th>
								<td  >
								 <input   name="checkBasic.checkTime"  class="Wdate" type="text"  dataType="Require" msg="此项为必填" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate type='both' value='${checkBasic.checkTime}'
										pattern="yyyy-MM-dd" />"  >  
								</td>
							</tr>
							<tr>
								<th width="15%">
									监管部门主管领导
								</th>
								<td>
									<input   name="checkBasic.jgld" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${checkBasic.jgld}"  >
								</td>
							<th width="15%">
									参检专家
								</th>
								<td width="35%"  >
									<input   name="checkBasic.cjzj" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${checkBasic.cjzj}"  >
								</td>
							</tr>
							<tr>
								<th width="15%">
									公司领导
								</th>
								<td width="35%" colspan="3" >
									<input   name="checkBasic.gsld" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${checkBasic.gsld}"  >
								</td>
								 
							</tr>
								<%-- <th width="15%">
									检查人
								</th>
								<td  >
								 <input   name="checkBasic.checker" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${checkBasic.checker.displayName}" >  
								</td> --%>
			<%-- 				<tr>
								<th>
									执法机构
								</th>
								<td>
									<input   name="checkBasic.checkOrganization" type="text"   dataType="Require" msg="此项为必填" value="${checkBasic.checkOrganization}"  >
								</td>
							</tr>
							<tr>
								<th width="15%">
									执法人员
								</th>
								<td width="35%">
									<input   id="userNames"name="lawEnforceName" type="text"   dataType="Require" msg="此项为必填" value="${lawEnforceName}"  readonly="readonly">
								<input id="userIds" value="${lawEnforceIds}" name="lawEnforceIds"  type="hidden" id="qyid"    >
					
								<a href="#" class="easyui-linkbutton" onclick="javascript:selectUser()" iconCls="icon-save">选择</a>
								</td>
								<th width="15%">
									证件号码
								</th>
								<td width="35%">
									<input   name="idNum" type="text" maxlength="18" dataType="Require" msg="此项为必填" value="${idNum}"  >
								</td>
							</tr>--%>
							<tr>
								<th width="15%">
									管理方面
								</th>
								<td colspan="3"><textarea rows="6" cols="150" name="checkBasic.glfm" dataType="Require" msg="此项为必填">${checkBasic.glfm}</textarea>
								</td>
							</tr>
							<tr>
								<th width="15%">
									行为方面
								</th>
								<td colspan="3"><textarea rows="6" cols="150" name="checkBasic.xwfm" dataType="Require" msg="此项为必填">${checkBasic.xwfm}</textarea>
								</td>
							</tr>
							<tr>
								<th width="15%">
									作业环境及设备设施
								</th>
								<td colspan="3"><textarea rows="6" cols="150" name="checkBasic.zyhjsb" dataType="Require" msg="此项为必填">${checkBasic.zyhjsb}</textarea>
								</td>
							</tr>
					
		<%-- 			<tr> 
						<th width="15%">
							检查情况
						</th>
						<td colspan="3">
							<div id="fileQueue" />
								<input type="file" name="uploadify" id="uploadify" />
								<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
								<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
								
						</td>
					</tr>
							<tr>
								<th>
									
								</th>
								<td colspan="3">
									<table width="100%" id="situationTable">
										<s:iterator id="situation" value="situationList" status="st">
											<tr>
												<td width="40%">
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
													<textarea style="height: 30px;width: 150px;" name='checkBasic.situationList[<s:property value="#st.index" />].discreption'>${situation.discreption}</textarea>
													<input type="hidden" name="checkBasic.situationList[<s:property value="#st.index" />].linkid" value="${situation.linkid}">
														<input type="hidden" name="checkBasic.situationList[<s:property value="#st.index" />].id" value="${situation.id}">
														<input type="hidden" class='delFlag'name="checkBasic.situationList[<s:property value="#st.index" />].delFlag" value="${situation.delFlag}">
												</td>
												<td>
													是否须整改<select name="checkBasic.situationList[<s:property value="#st.index" />].checkFlag">
													<option value="0" 
													<c:if test="${situation.checkFlag ==0}">selected</c:if>
													>否</option>
													<option value="1" 
													<c:if test="${situation.checkFlag ==1}">selected</c:if>
													>是</option>
													</select>&nbsp;&nbsp;&nbsp;
													<a href="javascript:void(0)" onclick="del(this)">删除</a>
												</td>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr> --%>
						</table>
					</div>
				</div>
					<div title="隐患清单" closable="false" style="overflow-x: hidden;"
					class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						隐患信息如下：
						<table style="width: 98%">
							<tr  style="padding: 0px;margin: 0px;" > 
						<th width="15%">
							是否存在隐患
						</th>
						<td colspan="3">
						<input type="radio" name="isExistYh" checked="checked" value="1" >是
						<input type="radio" name="isExistYh"  value="0">否
						</td>
					</tr>
					<tr>
					<th colspan="4" id="hylist">
					<table>
					<tbody>
						<!-- 隐患详情模版start -->
						<tr id="copyTemplete" class="yhinfo"  style=" display: none">
						<td   colspan="4"  style="padding: 0px;margin: 0px; ">
							<table>
								<tr> 
									<td  colspan="3" style="text-align:center">隐患
									</td>
									<td style="text-align:center" ><input type="hidden" name="checkBasic.yhqdList[templeteindex].dealFlag" value="0"><a href="#"onclick="removeYhinfo(this)">删除</a><input type="hidden" name="checkBasic.yhqdList[templeteindex].delFlag" value="0"></td>
								</tr>
								<tr> 
									<th width="15%">隐患内容
									</th>
									<td colspan="3"><textarea rows="6" cols="150" name="checkBasic.yhqdList[templeteindex].yhContent" dataType="Require" msg="此项为必填"></textarea>
									</td>
								</tr>
								<tr> 
									<th width="15%">监管部门跟踪责任人
									</th>
									<td > 
										<input type="text" 	 dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[templeteindex].jgzrrNames"  value="${jgzrr}" readonly="readonly">
										<input type="hidden" dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[templeteindex].jgzrrIds"  value="${jgzrrIds}" >
										<a href="#" class="easyui-linkbutton" onclick="selectUser(this)" iconCls="icon-save">选择</a>
									</td>
									<th width="15%">检查时间</th>
									<td width="35%"><input name="checkBasic.yhqdList[templeteindex].checktime" value="" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})"></td>
								</tr>
								<tr> 
									<th width="15%">企业责任人
									</th>
									<td > <input type="text" name="checkBasic.yhqdList[templeteindex].qyzrr" maxlength="20" dataType="Require" msg="此项为必填">
									</td>
									<th width="15%">企业责任人联系电话
									</th>
									<td > <input type="text" name="checkBasic.yhqdList[templeteindex].qyzrrlxdh" maxlength="20" dataType="Require" msg="此项为必填">
									</td>
								</tr>
								<tr> 
									<th width="15%">整改期限
									</th>
									<td >  <input   name="checkBasic.yhqdList[templeteindex].zgqx"  class="Wdate" type="text"  dataType="Require" msg="此项为必填" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  >  
									</td>
									<th width="15%">所需资金
									</th>
									<td colspan="3"> <input type="text" name="checkBasic.yhqdList[templeteindex].sxzj" maxlength="20" dataType="Require" msg="此项为必填">
									</td>
								</tr>
								<tr> 
									<th width="15%">
										上传附件
									</th>
									<td colspan="3">
										<div id="fileQueuetempleteindex" />
											<input type="file" name="uploadify" id="uploadifytempleteindex"/>
											<a href="javascript:$('#uploadifytempleteindex').uploadifyUpload()"  >开始上传</a>&nbsp;
											<a href="javascript:$('#uploadifytempleteindex').uploadifyClearQueue()"  >取消所有上传</a>
									</td>
								</tr>
								<tr> 
									<th width="15%">
										已传附件
									</th>
									<td colspan="3">
										 <table></table>
									</td>
								</tr>
								<tr> 
									<th width="15%">整改方案
									</th>
									<td colspan="3"><textarea rows="6" cols="150" name="checkBasic.yhqdList[templeteindex].zgfa" dataType="Require" msg="此项为必填"></textarea>
									</td>
								</tr>
								<tr> 
									<th width="15%">防范措施
									</th>
									<td colspan="3"><textarea rows="6" cols="150" name="checkBasic.yhqdList[templeteindex].ffcs" dataType="Require" msg="此项为必填"></textarea>
									</td>
								</tr>
							</table>
						</td>
							</tr>
						<!-- 隐患详情模版end -->
						
						
						
						<s:iterator id="yhqd" value="checkBasic.yhqdList" status="st"  >
						<tr   class="yhinfo"  >
						<td   colspan="4"  style="padding: 0px;margin: 0px; ">
							<table>
								<tr> 
									<td  colspan="3" style="text-align:center">隐患 
									<!-- 数据隐藏区域 -->
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].id" value="${id}" >
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].zgysr" value="${zgysr}" >
									 <input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].yssj" value="${yssj}" >
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].resultContent" value="${resultContent}" >
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].ended" value="${ended}" >
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].dealFlag" value="${dealFlag}" >
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].passFlag" value="${passFlag}" >
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].deptId" value="${deptId}" >
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].createTime" value="${createTime}" >
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].updateTime" value="${updateTime}" > 
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].createUserID" value="${createUserID}" >
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].defId" value="${defId}" >
									<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].basic.id" value="${checkBasic.id}">
									<!-- 数据隐藏区域 -->
									</td>
									<td style="text-align:center" > <a href="#"onclick="removeYhinfo(this)">删除</a><input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].delFlag" value="${delFlag}"></td>
								</tr>
								<tr> 
									<th width="15%">隐患内容
									</th>
									<td colspan="3"><textarea rows="6" cols="150" dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[<s:property value="#st.index" />].yhContent">${yhContent}</textarea>
									</td>
								</tr>
								<tr> 
									<th width="15%">监管部门跟踪责任人
									</th>
									<td > 
										<input type="text" 	 dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[<s:property value="#st.index" />].jgzrrNames"  value="${jgzrrNames}" readonly="readonly">
										<input type="hidden" dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[<s:property value="#st.index" />].jgzrrIds"  value="${jgzrrIds}" >
										<a href="#" class="easyui-linkbutton" onclick="selectUser(this)" iconCls="icon-save">选择</a>
									</td>
									<th width="15%">检查时间</th>
									<td width="35%"><input name="checkBasic.yhqdList[<s:property value="#st.index" />].checktime" value="<fmt:formatDate type='both' value='${checktime}' pattern="yyyy-MM-dd"/>" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})"></td>
								</tr>
								<tr> 
									<th width="15%">企业责任人
									</th>
									<td > <input type="text" dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[<s:property value="#st.index" />].qyzrr" maxlength="20" value="${qyzrr}">
									</td>
									<th width="15%">企业责任人联系电话
									</th>
									<td > <input type="text" dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[<s:property value="#st.index" />].qyzrrlxdh" maxlength="20" value="${qyzrrlxdh}">
									</td>
								</tr>
								<tr> 
									<th width="15%">整改期限
									</th>
									<td >  <input   dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[<s:property value="#st.index" />].zgqx"  class="Wdate" type="text"  dataType="Require" msg="此项为必填" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  value="<fmt:formatDate type='both' value='${zgqx}' pattern="yyyy-MM-dd" />"  >  
									</td>
									<th width="15%">所需资金
									</th>
									<td colspan="3"> <input type="number" dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[<s:property value="#st.index" />].sxzj" maxlength="20" value="${sxzj}">
									</td>
								</tr>
								<tr> 
									<th width="15%">
										上传附件
									</th>
									<td colspan="3">
										<div id="fileQueue<s:property value="#st.index" />" />
											<input type="file" name="uploadify" id="uploadify<s:property value="#st.index" />"/>
											<a href="javascript:$('#uploadify<s:property value="#st.index" />').uploadifyUpload()"  >开始上传</a>&nbsp;
											<a href="javascript:$('#uploadify<s:property value="#st.index" />').uploadifyClearQueue()"  >取消所有上传</a>
									</td>
								</tr>
								<tr> 
									<th width="15%">
										已传附件
									</th>
									<td colspan="3"> 
										 <table>
										 <!-- 迭代附件 -->
										 <s:iterator id="photoPic" value="#yhqd.picList" status="cst">
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
													<textarea style="height: 30px;width: 150px;" name='checkBasic.yhqdList[<s:property value="#st.index" />].picList[<s:property value="#cst.index" />].taskRemark'>${taskRemark}</textarea>
													<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].picList[<s:property value="#cst.index" />].linkid" value="${taskProId}">
														<input type="hidden" name="checkBasic.yhqdList[<s:property value="#st.index" />].picList[<s:property value="#cst.index" />].id" value="${id}">
														<input type="hidden" class='delFlag'name="checkBasic.yhqdList[<s:property value="#st.index" />].picList[<s:property value="#cst.index" />].delFlag" value="${delFlag}">
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
									<th width="15%">整改方案
									</th>
									<td colspan="3"><textarea rows="6" cols="150" dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[<s:property value="#st.index" />].zgfa">${zgfa}</textarea>
									</td>
								</tr>
								<tr> 
									<th width="15%">防范措施
									</th>
									<td colspan="3"><textarea rows="6" cols="150" dataType="Require" msg="此项为必填" name="checkBasic.yhqdList[<s:property value="#st.index" />].ffcs">${ffcs}</textarea>
									</td>
								</tr>
							</table>
						</td>
							</tr>
							</s:iterator>
						 
						<tr style="padding: 0px;margin: 0px;">
						<td   id="addMoreYdBtn" colspan="4"  style="text-align:center;padding: 0px;margin: 0px;"><a href="#" onclick="addYhinfoTemplete(this)">增加</a>
						</td>
						</tr>
						</tbody>
						</table>
						</th>
					</tr>
						</table>
					</div>
				</div>
<%-- 				<div title="责令限期整改指令书" closable="false" style="overflow-x: hidden;"
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
						&nbsp;&nbsp;&nbsp;&nbsp;现责令你单位对上述第<input  name="checkBasic.rectifyTerm" style='width: 80px;'type="number" maxlength="3" dataType="Require" msg="此项为必填" value="${checkBasic.rectifyTerm}"  >项问题于
							<input   name="checkBasic.rectifyDate" class="Wdate" type="text"   style='width: 120px;'dataType="Require" msg="此项为必填" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})"value="<fmt:formatDate type='both' value='${checkBasic.rectifyDate}'
										pattern="yyyy-MM-dd " />"   placeholder="年-月-日">
						前整改完毕， 达到有关法律法规规章和标准规定的要求。逾期不整改或达不到要求的，依法给予行政处罚；由此造成事故的，
						依法追究有关人员的责任。如果不服本指令，可以依法在60日内向苏州市太仓市人民政
						府或者苏州市安全生产监督管理局申请行政复议，或者在三个月内依法向苏州市太仓市人民法院提起行政诉讼，但本指
						令不停止执行，法律另有规定的除外。
					</div>
				</div> --%>
				<!-- 复查显示 -->
				<%-- <div title="整改复查意见书" closable="false" style="overflow-x: hidden;"
					class="tab">
					<div class="submitdata"
						style="overflow-x: hidden; width: 99%; height: 99%;">
						<table style="width: 98%;">
							&nbsp;&nbsp;${checkBasic.company.companyname}:
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp; 本机关于
							<fmt:formatDate type='both'
								value='${checkBasic.rectifyBeginTime}' pattern="yyyy-MM-dd" />
							作出了${checkBasic.reviewContent}的决定【吴安监管责改[${checkBasic.rectifyYear}]${checkBasic.rectifyNum}号】，经对你单位整改情况进
							行复查，提出如下意见:
						</table>
						<table>
							<tr>
								<td width="15%" style="text-align: center;">
									整改意见:
								</td>
								<td width="85%">
									<table width="100%">
										<s:iterator id="opList" value="opinionList">
											<tr>
												<td width="70%">
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
													${opList.opinion}
												</td>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</div> --%>
				<p style="height: 80px;text-align: center;vertical-align: middle;line-height: 80px;">
				 <s:if test="flag=='add'">
								<a href="#" class="easyui-linkbutton" onclick="save()"
									iconCls="icon-save">添加</a>&nbsp;
							</s:if>
							<s:else>
								<a href="#" class="easyui-linkbutton" onclick="save()"
									iconCls="icon-save">更新</a>&nbsp;
						</s:else>
							<a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
								onclick="window.close();">关闭</a>
				</p>
			</div>
		</form>
		<script type="text/javascript">
		function queryQy()
		{
			var OpenWindow = window.showModalDialog("${ctx}/jsp/company/queryCompanyList.action?company.dwdz1=${curr_user.deptCode}", '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
			if(typeof(OpenWindow) != 'undefined')
			{
				var sonValue = OpenWindow.split(";");
				document.getElementById('qyid').value = sonValue[0]; 
				document.getElementById('qymc').value = sonValue[1];
				document.getElementById('dwdz2').value = sonValue[4];
				document.getElementById('fddbr').value = sonValue[5];
				document.getElementById('fddbrlxhm').value = sonValue[6];
			}
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
		function save(){
			if(Validator.Validate(document.myform1,3)){
				if($("#situationTable tr").size()>0){
					$("#rectifyFlag").val(1);
				}else{
					$("#rectifyFlag").val(0);
				}
				document.myform1.action="checkBasicSave.action";
				document.myform1.submit();
			 }
		}
		
		
		function initUpload(target,divId){
			var data = '${ctx}/jsp/checkBasic/savePhoto.action?type=check';
			$(target).uploadify({
			'uploader': '<c:url value="/webResources/js/uploadify.swf"/>', 
			'buttonImg': '<c:url value="/webResources/js/browse_sc.jpg"/>', 
			'script':data,
			'cancelImg': '<c:url value="/webResources/js/cancel.png"/>',                  
			'queueID' : divId, //和存放队列的DIV的id一致  
			'auto'  : false, //是否自动开始  
			'multi': false, //是否支持多文件上传  
			'buttonText': 'BROWSE', //按钮上的文字  
			'simUploadLimit' : 1, //一次同步上传的文件数目  
			'sizeLimit': 19871202, //设置单个文件大小限制，单位为byte  
			'queueSizeLimit' : 10, //队列中同时存在的文件个数限制  
			'fileDesc': '所有文件：*.*', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
			'fileExt': '*.*;',//允许的格式
			'onComplete': function (event, queueID, fileObj, response, data) {
		         addmore(response,divId);
			},
			 'onAllComplete': function (event, data) { 
			 	alert("上传成功");
			},  
			'onError': function(event, queueID, fileObj) {  
				alert("文件:" + fileObj.name + "上传失败");  
			},  
			'onCancel':function(event, queueID, fileObj){}
			});
		}
		var  $yhinfoTemplete ;
		$(document).ready(function() { 
			$yhinfoTemplete = $("#copyTemplete").clone();
			$("#copyTemplete").remove();
			
			//初始化迭代出来的上传按钮
			$("input[name='uploadify']").each(function(index,element){
			    	initUpload(element,'fileQueue'+index);
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
		   function addmore(data,divID){
			   data=eval("(" + data + ")");
			   var pid = data.pid;
			   var picName = data.picName;
			   var linkId = data.linkId;
			   var $table = $("#"+divID).parent().parent().next().find("table");
			   var index = $table.find("tr").size();
			   var parentIndex =  divID.replace("fileQueue",'');
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
					   +'<textarea style="height: 30px;width: 150px;" name="checkBasic.yhqdList['+parentIndex+'].picList['+index+'].taskRemark"> </textarea>'
				+'<input type="hidden" name="checkBasic.yhqdList['+parentIndex+'].picList['+index+'].taskProId" value="'+linkId+'">'
				+'<input type="hidden" name="checkBasic.yhqdList['+parentIndex+'].picList['+index+'].id" value="'+pid+'">'
				+'<input type="hidden" class ="delFlag"name="checkBasic.yhqdList['+parentIndex+'].picList['+index+'].delFlag" value="0"></td>'); 
			   $tr.append($td1);
			   $tr.append($td2);
			   $tr.append($td3);
			   $table.append($tr);
			  
			}
		   
		function addYhinfoTemplete(self){
			var $yhinfo = $yhinfoTemplete.clone();
			$yhinfo.removeAttr("id");
			var yhinfoHtmlStr = $yhinfo.html();
			var templeteindex = $(".yhinfo").size();
			var rr = new RegExp('templeteindex','g');
			yhinfoHtmlStr = yhinfoHtmlStr.replace(rr,templeteindex);
			$yhinfo.html(yhinfoHtmlStr);
			$(self).parent().parent().before($yhinfo);
			$yhinfo.show();
			var uploadifyInput = $yhinfo.find("input[name='uploadify']");
			initUpload(uploadifyInput,'fileQueue'+templeteindex);
		}
		
		function removeYhinfo(self){
			$(self).parents(".yhinfo").hide();
			$(self).next().val(1);
		}
		
		$('input:radio[name="isExistYh"]').change( function(){
			if($(this).val()=="1"){
				$("#hylist").show();
			}else{
				$("#hylist").hide();
			}
		});
				
		</script>
		<%@include file="/WEB-INF/template/pagefoot.jsp"%>
	</body>
</html>

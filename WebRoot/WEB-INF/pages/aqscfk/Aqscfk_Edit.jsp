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
				
				document.myform1.action="aqscfkSave.action";
				document.myform1.submit();
			}
		}
		
		function queryQy()
		{
			var szzid = document.getElementById('szzid').value;
			var OpenWindow = window.showModalDialog("${ctx}/jsp/company/queryCompanyList.action?company.dwdz1="+szzid, '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
			if(typeof(OpenWindow) != 'undefined')
			{
				var sonValue = OpenWindow.split(";");
				document.getElementById('qyid').value = sonValue[0];
				document.getElementById('qymc').value = sonValue[1];
				document.getElementById('szzid').value = sonValue[2];
			}
		}
		 //图片上传 lj 2013-08-31
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/aqscfk/aqscfkUpload.action?aqscfk.linkId=${aqscfk.linkId}&flag="+obj);
        	}
         function savepic(i){
        		window.location.href="${ctx}/jsp/aqscfk/aqscfkSaveFile.action?picName="+i;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "${ctx}/jsp/aqscfk/aqscfkImageDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
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
        
        function clearCompany(){
        	document.getElementById("qymc").value="";
        	document.getElementById("qyid").value="";
        }
        
        function selFkfl()
        {
        	if($("#fkfl").val()=='1')
        	{
        		$("#showTr1").hide();
        		$("#sgfl").val("");
        	}
        	else
        	{
        		$("#showTr1").show();
        	}
        }
        
	</script>
	
</head>
<body onload="selFkfl()">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="aqscfk.id" value="${aqscfk.id}">
		<input type="hidden" name="aqscfk.createTime" value="<fmt:formatDate type="both" value="${aqscfk.createTime}" />">
		<input type="hidden" name="aqscfk.updateTime" value="${aqscfk.updateTime}">
		<input type="hidden" name="aqscfk.createUserID" value="${aqscfk.createUserID}">
		<input type="hidden" name="aqscfk.updateUserID" value="${aqscfk.updateUserID}">
		<input type="hidden" name="aqscfk.deptId" value="${aqscfk.deptId}">
		<input type="hidden" name="aqscfk.delFlag" value="${aqscfk.delFlag}">
		<input type="hidden" name="aqscfk.linkId" value="${aqscfk.linkId}">
		<input type="hidden" name="aqscfk.remark" value="${aqscfk.remark}">
		<input type="hidden" name="aqscfk.shenhe" value="${aqscfk.shenhe}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">
						<s:if test="type == 1">
							<cus:SelectOneTag property="aqscfk.szzid" defaultText='请选择' codeName="相城地址" value="${aqscfk.szzid}" disabled="true"/>
						</s:if>
						<s:else>
							<cus:SelectOneTag property="aqscfk.szzid" defaultText='请选择' codeName="相城地址" value="${aqscfk.szzid}" onchange="clearCompany()" dataType="Require" msg="此项为必填"/><font style="color:red">*</font>
						</s:else>
					</td>
					<th width="15%">企业名称</th>
					<td width="35%">
					<input id="qymc" name="aqscfk.qymc" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${aqscfk.qymc}"><font style="color:red">*</font>
					<input name="aqscfk.qyid" value="${aqscfk.qyid}"  type="hidden" id="qyid" type="text" maxlength="255">
					<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>
					</td>
				</tr>
				<tr>
					<th width="15%">执法文书号</th>
					<td width="35%"><input name="aqscfk.zfwsh" value="${aqscfk.zfwsh}" type="text" maxlength="255"></td>
					<th width="15%">处罚金额（万元）</th>
					<td width="35%"><input name="aqscfk.cfje" value="${aqscfk.cfje}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">罚款分类</th>
					<td width="35%"><s:select id="fkfl" listKey="key" onblur="selFkfl()" onchange ="selFkfl()" listValue="value"  theme="simple" list="#{'0':'事故处罚','1':'监督监察处罚'}" name="aqscfk.fkfl" value="{aqscfk.fkfl}"/>	</td>
					<th width="15%">结案时间</th>
					<td width="35%"><input name="aqscfk.jasj" value="<fmt:formatDate type='date' value='${aqscfk.jasj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				
				<tr id="showTr1">
					<th width="15%">事故分类</th>
					<td width="35%"><s:select id="sgfl" listKey="key" listValue="value"  theme="simple" list="#{'0':'一般事故','1':'较大事故','2':'重大事故','3':'特大事故'}" name="aqscfk.sgfl" value="{aqscfk.sgfl}"/>	</td>
				</tr>
				
				<tr>
					<th width="15%">企业类型</th>
					<td width="35%"><s:select id="companyType" listKey="key" listValue="value"  theme="simple" list="#{'0':'化工生产企业','1':'危化品经营企业','2':'烟花爆竹企业','3':'职业危害企业','4':'其它工贸企业','5':'其它'}" name="aqscfk.companyType" value="{aqscfk.companyType}"/>	</td>
					<th width="15%">行政处罚对象</th> 
					<td width="35%"><s:select id="objectType" listKey="key" listValue="value"  theme="simple" list="#{'0':'生产经营单位','1':'生产经营单位主要负责人'}" name="aqscfk.objectType" value="{aqscfk.objectType}"/>	</td>
				</tr>
				<tr>
					<th width="15%">实际收缴罚款（万元）</th>
					<td width="35%"><input name="aqscfk.sjfk" value="${aqscfk.sjfk}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
					<th width="15%">责令停产停业整顿生产经营单位</th>
					<td width="35%"><cus:hxradio property="aqscfk.zltc" codeName="是或否" value="${aqscfk.zltc}"/></td>
				</tr>
				
				<tr>
					<th width="15%">执法文书类型</th>
					<td width="85%" colspan="3"> <cus:hxcheckbox property="aqscfk.fileType" codeName="执法文书类型" value="${aqscfk.fileType}"/> </td>
				</tr>
				
				<tr>
					<th width="15%">处罚事由</th>
					<td width="85%" colspan="3"><textarea name="aqscfk.cfsy" style="width:100%;height:120px">${aqscfk.cfsy}</textarea></td>
				</tr>
				<tr>
					<th width="15%">执法文书</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('aqscfk1')" title="点击上传附件">选择执法文书</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="aqscfk1">
												<c:forEach var="item" items="${picList1}">
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
															<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
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
					<th width="15%">取证材料</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('aqscfk2')" title="点击上传附件">选择取证材料</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="aqscfk2">
												<c:forEach var="item" items="${picList2}">
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
															<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
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
					<th width="15%">调查报告</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('aqscfk3')" title="点击上传附件">选择调查报告</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="aqscfk3">
												<c:forEach var="item" items="${picList3}">
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
															<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
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
					<th width="15%">行政处罚告知书</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('aqscfk4')" title="点击上传附件">选择行政处罚告知书</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="aqscfk4">
												<c:forEach var="item" items="${picList4}">
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
															<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
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
					<th width="15%">陈述人笔录</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('aqscfk5')" title="点击上传附件">选择陈述人笔录</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="aqscfk5">
												<c:forEach var="item" items="${picList5}">
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
															<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
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
					<th width="15%">行政处罚决定书</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('aqscfk6')" title="点击上传附件">选择行政处罚决定书</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="aqscfk6">
												<c:forEach var="item" items="${picList6}">
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
															<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
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
					<th width="15%">处罚决定书回证</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('aqscfk7')" title="点击上传附件">选择处罚决定书回证</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="aqscfk7">
												<c:forEach var="item" items="${picList7}">
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
															<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
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
					<th width="15%">结案审批材料</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('aqscfk8')" title="点击上传附件">选择结案审批材料</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="aqscfk8">
												<c:forEach var="item" items="${picList8}">
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
															<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
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

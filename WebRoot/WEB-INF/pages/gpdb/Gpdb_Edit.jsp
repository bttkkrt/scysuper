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
				
				document.myform1.action="${ctx}/jsp/gpdb/gpdbSave.action";
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
        		window.open("${ctx}/jsp/gpdb/gpdbUpload.action?gpdb.linkid=${gpdb.linkid}&flag="+obj);
        	}
         function savepic(i){
        		window.location.href="${ctx}/jsp/gpdb/gpdbSaveFile.action?picName="+i;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "${ctx}/jsp/gpdb/gpdbImageDel.action",
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
        
        function clearCompany(){
        	document.getElementById("qymc").value="";
        	document.getElementById("qyid").value="";
        }
        $(function(){
			var tempDeptCode = $("#tempDeptCode").val();
			var szzidhu = $("#szzidhu").val();
			if(tempDeptCode != "1"){
		    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+tempDeptCode,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#szzid'); 
						selectContainer.empty();
						var option = $('<option></option>').text("--请选择--").val(""); 
						selectContainer.append(option); 
		  				for(var i=0; i<json.length; i++){
					    	if(szzidhu==json[i].id){
						    	option = $('<option></option>').text(json[i].name).val(json[i].id).attr("selected","selected");
								selectContainer.append(option); 
					    	}else{
						    	option = $('<option></option>').text(json[i].name).val(json[i].id);
								selectContainer.append(option); 
					    	}
					 	}
					},
					dateType:"json"
				});			
			}
		});
        function querySzz(obj)
	    {
	    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#szzid'); 
						selectContainer.empty();
						var option = $('<option></option>').text("").val(""); 
						selectContainer.append(option); 
		  				for(var i=0; i<json.length; i++){
					    	option = $('<option></option>').text(json[i].name).val(json[i].id);
							selectContainer.append(option); 
					 	}
					},
					dateType:"json"
				});
	    }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="type" value="${type}">
		<input type="hidden" name="tt" value="${tt}">
		<input type="hidden" name="gpdb.id" value="${gpdb.id}">
		<input type="hidden" name="gpdb.createTime" value="<fmt:formatDate type="both" value="${gpdb.createTime}" />">
		<input type="hidden" name="gpdb.updateTime" value="${gpdb.updateTime}">
		<input type="hidden" name="gpdb.createUserID" value="${gpdb.createUserID}">
		<input type="hidden" name="gpdb.updateUserID" value="${gpdb.updateUserID}">
		<input type="hidden" name="gpdb.deptId" value="${gpdb.deptId}">
		<input type="hidden" name="gpdb.delFlag" value="${gpdb.delFlag}">
		<input type="hidden" name="gpdb.linkid" value="${gpdb.linkid}">
		<input type="hidden" name="gpdb.state" value="${gpdb.state}">
		<input type="hidden" name="gpdb.remark" value="${gpdb.remark}">
		<input type="hidden" name="gpdb.username" value="${gpdb.username}">
		<input type="hidden" name="gpdb.shenhe" value="${gpdb.shenhe}">
		<input type="hidden" id="tempDeptCode" name="tempDeptCode" value="${tempDeptCode}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">
						<c:if test="${tempDeptCode != 1}">
							<select id="szzid" name="gpdb.szzid" style="width:135px;" onchange="clearCompany()" ><option value="">请选择</option></select><font style="color:red">*</font>
						</c:if>
						<c:if test="${tempDeptCode == 1}">
							<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
							<select id="szzid" name="gpdb.szzid" style="width:150px;" ><option value="">请选择</option></select>
							<font color="red">*</font>
						</c:if>
					<!-- 
						<s:if test="types == 1">
							<cus:SelectOneTag property="gpdb.szzid" defaultText='请选择' codeName="相城地址" value="${gpdb.szzid}" disabled="true"/>
							<input type="hidden" name="gpdb.szzid" value="${gpdb.szzid}"/>
						</s:if>
						<s:else>
							<cus:SelectOneTag property="gpdb.szzid" defaultText='请选择' codeName="相城地址" value="${gpdb.szzid}" onchange="clearCompany()"/>
						</s:else>
					 -->
					</td>
					<th width="15%">企业名称</th>
					<td width="35%">
					<input id="qymc" name="gpdb.qymc" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${gpdb.qymc}"><font style="color:red">*</font>
					<input name="gpdb.qyid" value="${gpdb.qyid}"  type="hidden" id="qyid" type="text" maxlength="255">
					<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>
					</td>
				</tr>
				<tr>
					<th width="15%">隐患名称</th>
					<td width="35%"><input name="gpdb.yhmc" value="${gpdb.yhmc}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
					<th width="15%">挂牌时间</th>
					<td width="35%"><input name="gpdb.gpsj" value="<fmt:formatDate type='date' value='${gpdb.gpsj}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">隐患类别</th>
					<td width="35%"><cus:SelectOneTag property="gpdb.yhlb" defaultText='请选择' codeName="隐患类别" value="${gpdb.yhlb}" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
					<th width="15%">隐患数</th>
					<td width="35%"><input name="gpdb.yhs" value="${gpdb.yhs}" type="text" dataType="Require" msg="此项为必填" maxlength="255" onKeyUp="validate(event,this)"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">检查文书</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('gpdb1')" title="点击上传附件">选择检查文书</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="gpdb1">
												<c:forEach var="item" items="${picList1}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
															<c:choose>
													    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
													   				&nbsp;&nbsp;&nbsp;<img src="/upload/photo/${item.picName}"
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
					<th width="15%">整改前图片</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('gpdb2')" title="点击上传附件">选择整改前图片</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="gpdb2">
												<c:forEach var="item" items="${picList2}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
															<c:choose>
													    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
													   				&nbsp;&nbsp;&nbsp;<img src="/upload/photo/${item.picName}"
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
					<th width="15%">整改方案</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('gpdb4')" title="点击上传附件">选择整改方案</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="gpdb4">
												<c:forEach var="item" items="${picList4}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
															<c:choose>
													    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
													   				&nbsp;&nbsp;&nbsp;<img src="/upload/photo/${item.picName}"
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
					<th width="15%">防范措施</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('gpdb5')" title="点击上传附件">选择防范措施</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="gpdb5">
												<c:forEach var="item" items="${picList5}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
															<c:choose>
													    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
													   				&nbsp;&nbsp;&nbsp;<img src="/upload/photo/${item.picName}"
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
					<th width="15%">责任人</th>
					<td width="35%"><input name="gpdb.zrr" value="${gpdb.zrr}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				<s:if test="%{type == 1 || tt == 1}">
					<th width="15%">整改资金（万元）</th>
					<td width="35%"><input name="gpdb.zgzj" value="${gpdb.zgzj}" type="text" dataType="Require" msg="此项为必填" maxlength="255" onKeyUp="validateNum(event,this)"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">整改完成时间</th>
					<td width="35%"><input name="gpdb.zgwcsj" value="<fmt:formatDate type='date' value='${gpdb.zgwcsj}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font color="red">*</font></td>
					<th width="15%">验收时间</th>
					<td width="35%"><input name="gpdb.yssj" value="<fmt:formatDate type='date' value='${gpdb.yssj}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">隐患整改数</th>
					<td width="35%"><input name="gpdb.yhzgs" value="${gpdb.yhzgs}" type="text" dataType="Require" msg="此项为必填" maxlength="255" onKeyUp="validate(event,this)"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">复查文书</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('gpdb3')" title="点击上传附件">选择复查文书</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="gpdb3">
												<c:forEach var="item" items="${picList3}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
															<c:choose>
													    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
													   				&nbsp;&nbsp;&nbsp;<img src="/upload/photo/${item.picName}"
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
					<th width="15%">整改后图片</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center><a style="color:blue;" href="javascript:uploadPhoto('gpdb6')" title="点击上传附件">选择整改后图片</a></center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="gpdb6">
												<c:forEach var="item" items="${picList6}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
															<c:choose>
													    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
													   				&nbsp;&nbsp;&nbsp;<img src="/upload/photo/${item.picName}"
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
				</s:if>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:elseif test="type == 1">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">上报</a>&nbsp;
						</s:elseif>
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

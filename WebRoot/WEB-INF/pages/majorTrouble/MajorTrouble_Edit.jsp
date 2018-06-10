<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script src="${ctx}/webResources/My97DatePicker/WdatePicker.js"></script>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="majorTroubleSave.action";
				document.myform1.submit();
			}
		}
		 //图片上传 lj 2013-02-26
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/majorTrouble/majorTroubleUpload.action?majorTrouble.linkId=${majorTrouble.linkId}&type="+obj);
        	}
        	 function deleteImage(){
		          var all=document.getElementsByName("choice");
		          var picName="";
					for(var i=0;i<all.length;i++)
					{
						if(all[i].checked==true)
						{
							picName+=all[i].value+"|";
						}
					}
					alert(picName);
					if(picName!="")
					{
				       picName = picName.substring(0, picName.length - 1);
				     }
		      		 if(picName == ""){
					   alert('至少选择一张删除！');
					}else{
					        if(window.confirm("确定要删除吗?")){
				               jQuery.ajax({
				                	url : "majorTroubleImageDel.action",
				                	type: 'post',
				                    dataType: 'json',
				                    async : false,
				                 	 data:{ 
		                    			  picName : picName
		                    			},
				                    error: function(){
				                    	alert('删除时出错！');
				                    },
				                    success: function(data){
				                        if(data.result=='true'){		                      
				                        	window.location.href = '';
				                        	  jQuery.ajax({
				                	url : "majorTroubleImageDel.action?picName="+picName,
				                	type: 'post',
				                    dataType: 'json',
				                    async : false,
				                    data: $('#myform1').serialize(),
				                    error: function(){
				                    	alert('删除时出错！');
				                    },
				                    success: function(data){
				                        if(data.result=='true'){		                      
				                        	window.location.href = 'majorTroubleInitEdit.action?flag=mod';
				                        }else{
				                        	alert('删除时出错！');
				                        }
				                    }
				                });
				                        }else{
				                        	alert('删除时出错！');
				                        }
				                    }
				                });
					}
					}
		        }
        function savepic(i,j){
        		window.location.href="majorTroubleSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "majorTroubleImageDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
        }
          //选择企业
        function choseCompany(){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/commonTrouble/commoTroubleChoseCompany.action?func=setCompany",900,600);
          		/* var ah = screen.availHeight - 30;
    			var aw = screen.availWidth - 10;
    			var xc = (aw - 800) / 2;
    			var yc = (ah - 700) / 2;
        		createWindow("setCompany","选择企业",xc,yc,"800","600","${ctx}/jsp/commonTrouble/commoTroubleChoseCompany.action?func=setCompany");
         */}
         function pp_close_win(){
        	$("#setCompany").window("close");
        }
        function clearCompany(){
        	document.getElementById("qymc").value="";
        	document.getElementById("qymcid").value="";
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
        $(function(){
        	if("${flag}"=="1"){
				layer.alert('更新成功', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					}, function(){
						window.opener=null;
						window.open('','_self');
						window.close();
					});
			}else if("${flag}"=="2"){
				layer.alert('更新失败');
			}
        	
        	var tempCounty = "";
        	var tempDeptCode = $("#tempDeptCode").val();
			var szzidhu = '${majorTrouble.szz}';
			if(tempDeptCode == "1"){
				tempCounty = '${majorTrouble.county}';
			}else{
				tempCounty = tempDeptCode;
			}
	    	$.ajax({
				type:"POST",
				url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+tempCounty,
				success:function(json){
					json = eval('(' + json + ')');
					var selectContainer = $('#szzid'); 
					selectContainer.empty();
					var option = $('<option></option>').text("请选择").val(""); 
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
						var option = $('<option></option>').text("请选择").val(""); 
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
		<input type="hidden" id="linkId" name="majorTrouble.linkId" value="${majorTrouble.linkId}"/>
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="majorTrouble.id" value="${majorTrouble.id}">
		<input type="hidden" name="majorTrouble.shzt" value="${majorTrouble.shzt}">
		<input type="hidden" name="majorTrouble.remark" value="">
		<input type="hidden" name="majorTrouble.createTime" value="<fmt:formatDate type="both" value="${majorTrouble.createTime}" />">
		<input type="hidden" name="majorTrouble.updateTime" value="${majorTrouble.updateTime}">
		<input type="hidden" name="majorTrouble.createUserID" value="${majorTrouble.createUserID}">
		<input type="hidden" name="majorTrouble.updateUserID" value="${majorTrouble.updateUserID}">
		<input type="hidden" name="majorTrouble.deptId" value="${majorTrouble.deptId}">
		<input type="hidden" name="majorTrouble.delFlag" value="${majorTrouble.delFlag}">
		<input type="hidden" name="majorTrouble.szzname" value="${majorTrouble.szzname}">
		<input type="hidden" name="majorTrouble.qylx" value="${majorTrouble.qylx}">
		<input type="hidden" name="majorTrouble.hyfl" value="${majorTrouble.hyfl}">
		<input type="hidden" name="majorTrouble.qygm" value="${majorTrouble.qygm}">
		<input type="hidden" name="majorTrouble.qyzclx" value="${majorTrouble.qyzclx}">
		<input type="hidden" name="majorTrouble.ifwhpqylx" value="${majorTrouble.ifwhpqylx}">
		<input type="hidden" name="majorTrouble.ifzywhqylx" value="${majorTrouble.ifzywhqylx}">
		<input type="hidden" name="majorTrouble.ifyhbzjyqy" value="${majorTrouble.ifyhbzjyqy}">
		<input type="hidden" name="majorTrouble.shenhe" value="${majorTrouble.shenhe}">
		
		<input type="hidden" name="majorTrouble.szc" value="${majorTrouble.szc}">
		<input type="hidden" id="szz" name="majorTrouble.szz" value="${majorTrouble.szz}">
		<input type="hidden" name="majorTrouble.szcname" value="${majorTrouble.szcname}">
		<input type="hidden" id="tempDeptCode" name="tempDeptCode" value="${tempDeptCode}">
		<div class="submitdata" style="overflow:auto;">
			<table width="100%" border="0">
				<%-- <tr>
					<th width="15%">所在镇</th>
					<td width="85%"  colspan="3">
						<c:if test="${tempDeptCode != 1}">
							<select id="szzid" name="majorTrouble.szz" style="width:135px;" onchange="clearCompany()" ><option value="">请选择</option></select><font style="color:red">*</font>
						</c:if>
						<c:if test="${tempDeptCode == 1}">
							<cus:SelectOneTag property="majorTrouble.county" value="${majorTrouble.county}" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
							<select id="szzid" name="majorTrouble.szz" style="width:150px;" ><option value="">请选择</option></select>
							<font color="red">*</font>
						</c:if>
						<!-- 
						<cus:SelectOneTag onchange="clearCompany()" property="commoTrouble.szz" defaultText='请选择' codeName="相城地址" value="${commoTrouble.szz}"/>
						 -->
					</td>
				</tr> --%>
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%">
						<input name="majorTrouble.qymc" id="qymc" value="${majorTrouble.qymc}" type="text" maxlength="255">
						<a href="#" class="easyui-linkbutton" onclick="choseCompany()" iconCls="icon-save">选择</a>&nbsp;
						<input type="hidden" id="qymcid" name="majorTrouble.qymcId" value="${majorTrouble.qymcId}"/></td>
					<th width="15%">挂牌时间</th>
					<td width="35%">
						<input id="gpsj" type="text" name="majorTrouble.gpsj"
											class="Wdate"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											value="${majorTrouble.gpsj}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">隐患名称</th>
					<td width="35%"><input name="majorTrouble.yhmc" value="${majorTrouble.yhmc}" type="text" maxlength="255"></td>
					<th width="15%">隐患数</th>
					<td width="35%"><input name="majorTrouble.yhs" value="${majorTrouble.yhs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">责任人</th>
					<td width="35%"><input name="majorTrouble.zrr" value="${majorTrouble.zrr}" type="text" maxlength="255"></td>
					<th width="15%">文书文号01</th>
					<td width="35%"><input name="majorTrouble.wswh01" value="${majorTrouble.wswh01}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">执法文书1</th>
					<td width="35%" >
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zfws01')" title="点击上传附件">选择文书</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div style="overflow:auto;">
												<table id="zfws01">
													<c:forEach var="item" items="${zfwsList01}">
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
																<a href="javascript:savepic('${item.picName}','${item.fileName}');">下载</a>&nbsp;&nbsp;
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
					
					<th width="15%">整改前图片</th>
					<td width="35%">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zgqtp')" title="点击上传图片">&nbsp;&nbsp;选择图片</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div style="overflow:auto;">
										    <table id="zgqtp">
												<c:forEach var="item" items="${picList03}">
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
															<a href="javascript:savepic('${item.picName}','${item.fileName}');">下载</a>&nbsp;&nbsp;
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
					<td width="35%">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zgfa')" title="点击上传附件">&nbsp;&nbsp;选择方案</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div style="overflow:auto;">
										    <table id="zgfa">
												<c:forEach var="item" items="${picList01}">
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
															<a href="javascript:savepic('${item.picName}','${item.fileName}');">下载</a>&nbsp;&nbsp;
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
					<th width="15%">监控措施</th>
					<td width="35%">
						<table width="100%">
							<tr>
								<td  width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('jkcs')" title="点击上传附件">&nbsp;&nbsp;选择措施</a>
								</td>
								<td  width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div style="overflow:auto;">
										   <table id="jkcs">
												<c:forEach var="item" items="${picList02}">
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
															<a href="javascript:savepic('${item.picName}','${item.fileName}');">下载</a>&nbsp;&nbsp;
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
				<s:if test="majorTrouble.shzt eq 3 or flag eq 'upload' ">
				<tr id="zg_show">
					<td colspan="4">
						<table>
							<tr>
								<th width="15%">整改数</th>
								<td width="35%"><input name="majorTrouble.zgs" value="${majorTrouble.zgs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
								<th width="15%">整改资金</th>
								<td width="35%"><input name="majorTrouble.zgzj" value="${majorTrouble.zgzj}" type="text" maxlength="255" onKeyUp="validateNum(event,this)">（万元）</td>
							</tr>
							<tr>
								<th width="15%">验收人</th>
								<td width="35%"><input name="majorTrouble.ysr" value="${majorTrouble.ysr}" type="text" maxlength="255"></td>
								<th width="15%">验收时间</th>
								<td width="35%">
									<input type="text" name="majorTrouble.yssj"
														class="Wdate"
														onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
														value="${majorTrouble.yssj}"/>
								</td>
							</tr>
							<tr>
								<th width="15%">整改完成日期</th>
								<td width="35%">
									<input type="text" name="majorTrouble.zgwcrq"
														class="Wdate"
														onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
														value="${majorTrouble.zgwcrq}"/>
								</td>
								<th width="15%">文书文号02</th>
								<td width="35%"><input name="majorTrouble.wswh02" value="${majorTrouble.wswh02}" type="text" maxlength="255"></td>
							</tr>
							
				<tr>
					<th width="15%">执法文书2</th>
					<td width="35%">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zfws02')" title="点击上传附件">&nbsp;&nbsp;选择文书</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div style="overflow:auto;">
										   <table id="zfws02">
												<c:forEach var="item" items="${zfwsList02}">
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
															<a href="javascript:savepic('${item.picName}','${item.fileName}');">下载</a>&nbsp;&nbsp;
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
					<th width="15%">整改后图片</th>
					<td width="35%">
									<table width="100%">
										<tr>
											<td width="20%">
												<a style="color:blue;" href="javascript:uploadPhoto('zghtp')" title="点击上传图片">&nbsp;&nbsp;选择图片</a>
											</td>
											<td width="70%">
												<div style="color:green;overflow:auto;height:160px;">
												   <div style="overflow:auto;">
													  <table id="zghtp">
															<c:forEach var="item" items="${picList04}">
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
																		<a href="javascript:savepic('${item.picName}','${item.fileName}');">下载</a>&nbsp;&nbsp;
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
						</table>
					</td>
				</tr>
				</s:if>
				<tr>
					<th width="15%">填报部门</th>
					<td width="85%"  colspan="3"><cus:SelectOneTag property="majorTrouble.tbbm" defaultText='请选择' codeName="填报部门" value="${majorTrouble.tbbm}" /></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center" style="text-align:center;">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:elseif test="flag=='upload'">
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

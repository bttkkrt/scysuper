<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<link rel="stylesheet" type="text/css" href='${ctx}/webResources/validatorJs/style/validator.css' />
	<script src="${ctx}/webResources/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/webResources/validatorJs/formValidator_min.js"></script>
	<script src="${ctx}/webResources/validatorJs/formValidatorRegex.js"></script>
	<script>
	$(document).ready(function(){
	if('${type}' == '3'){
		var h = screen.availHeight/3;
	    var w = screen.availWidth;
	    var dialogDiv = document.getElementById("CoverDiv");
	    if(!dialogDiv)
	    {
	     var dialogDiv = document.createElement("div");
	     dialogDiv.id="CoverDiv";
	     dialogDiv.style.cssText="font-family: Arial, Helvetica, sans-serif;font-size: 12px;line-height: normal;color: #FF0000;text-transform: capitalize;height: "+h+"px;width:"+w+"px;padding: 5px;margin: 0px;border: 1px solid #CCCCCC;top: 0px;position:absolute;left:0px;right:0px;bottom:0px;background-color: #F0F0F0;z-Index:1000;filter: Alpha(Opacity=0);";
	     dialogDiv.innerHTML="";
	     window.parent.parent.document.body.appendChild(dialogDiv);
	     }
	     else
	     {
	      dialogDiv.innerHTML="";
	     }
	}
	//$.formValidator.initConfig({formid:"myform1",debug:false,submitonce:true,
		//onerror:function(msg,obj,errorlist){
			//$.map(errorlist,function(msg1){alert(msg1)});
			//alert(msg);
	     	//}
		//});
	//$("#csny").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入的出生日期",onfocus:"请输入的出生日期，不能全部是0哦",oncorrect:"你输入的日期合法"}).inputValidator({min:"1900-01-01 00:00:00",max:"2000-01-01 00:00:00",type:"date",onerror:"日期必须在\"1900-01-01\"和\"2000-01-01\"之间"});//.defaultPassed();
	//$("#Tel_country").formValidator({onshow:"请输入国家区号",onfocus:"国家区号2位数字",oncorrect:"恭喜你,你输对了",defaultvalue:"86"}).regexValidator({regexp:"^\\d{2}$",onerror:"国家区号不正确"});
		});
	function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="commoTroubleSave.action";
				document.myform1.submit();
			}
		}
		 //图片上传 lj 2013-02-26    
        function uploadPhoto(obj){
        		window.open("commoTroubleUpload.action?commoTrouble.linkId=${commoTrouble.linkId}&type="+obj);
        	}
	  function deleteImage()
        {
          var all=document.getElementsByName("choice");
          
          var picName="";
	for(var i=0;i<all.length;i++)
	{
		if(all[i].checked==true)
		{
			picName+=all[i].value+"|";
		}
	}
	if(picName!="")
	{
        picName = picName.substring(0, picName.length - 1);
        }
       if(picName == ""){
			   alert('至少选择一张删除！');
			}else{
			        if(window.confirm("确定要删除吗?")){
		               jQuery.ajax({
		                	url : "commoTroubleImageDel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	picName : picName
		                    },
		                    error: function(){
		                    	alert('删除时出错0！');
		                    },
		                    success: function(data){
		                        if(data.result=='true'){		                      
		                        	window.location.href = window.location.href ;
		                        }else{
		                        	alert('删除时出错！');
		                        }
		                    }
		                });
			}
			}
        }
        function savepic(i,j){
        		window.location.href="commoTroubleSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "commoTroubleImageDel.action",
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
       */  }
         function pp_close_win(){
        	$("#setCompany").window("close");
        }
        function clearCompany(){
        	document.getElementById("qymc").value="";
        	document.getElementById("qymcid").value="";
        }
        function showDiv(obj){
        	if(obj == '1'){
        		$("#div_zgqk").show();
        	}else{
        		$("#div_zgqk").hide();
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
			var szzidhu = '${commoTrouble.szz}';
			if(tempDeptCode == "1"){
				tempCounty = '${commoTrouble.county}';
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
        function setSzz(obj){
        	$("#szz").val(obj);
        }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" id="linkId" name="commoTrouble.linkId" value="${commoTrouble.linkId}"/>
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" id="cid" name="commoTrouble.id" value="${commoTrouble.id}">
		<input type="hidden"  name="commoTrouble.shzt" value="${commoTrouble.shzt}">
		<input type="hidden" name="commoTrouble.createTime" value="<fmt:formatDate type="both" value="${commoTrouble.createTime}" />">
		<input type="hidden" name="commoTrouble.updateTime" value="${commoTrouble.updateTime}">
		<input type="hidden" name="commoTrouble.createUserID" value="${commoTrouble.createUserID}">
		<input type="hidden" name="commoTrouble.updateUserID" value="${commoTrouble.updateUserID}">
		<input type="hidden" name="commoTrouble.deptId" value="${commoTrouble.deptId}">
		<input type="hidden" name="commoTrouble.delFlag" value="${commoTrouble.delFlag}">
		<input type="hidden" name="commoTrouble.remark" value="">
		<input type="hidden" name="commoTrouble.szzname" value="${commoTrouble.szzname}">
		<input type="hidden" id="szz" name="commoTrouble.szz" value="${commoTrouble.szz}">
		<input type="hidden" name="commoTrouble.qylx" value="${commoTrouble.qylx}">
		<input type="hidden" name="commoTrouble.hyfl" value="${commoTrouble.hyfl}">
		<input type="hidden" name="commoTrouble.qygm" value="${commoTrouble.qygm}">
		<input type="hidden" name="commoTrouble.qyzclx" value="${commoTrouble.qyzclx}">
		<input type="hidden" name="commoTrouble.ifwhpqylx" value="${commoTrouble.ifwhpqylx}">
		<input type="hidden" name="commoTrouble.ifzywhqylx" value="${commoTrouble.ifzywhqylx}">
		<input type="hidden" name="commoTrouble.ifyhbzjyqy" value="${commoTrouble.ifyhbzjyqy}">
		<input type="hidden" name="commoTrouble.shenhe" value="${commoTrouble.shenhe}">
		<input type="hidden" name="commoTrouble.szc" value="${commoTrouble.szc}">
		<input type="hidden" name="commoTrouble.szcname" value="${commoTrouble.szcname}">
		<input type="hidden" id="tempDeptCode" name="tempDeptCode" value="${tempDeptCode}">
		<div class="submitdata" style="overflow:auto;">
			<table width="100%" border="0">
				<!-- <tr>
					<th>校验</th>
					<td>
						<input type="text" id="Tel_country">
						<input type="text" id="csny" name="csny" style="width:120px" value="1982-09-21" />
					</td>
					<td colspan="2"><div id="Tel_countryTip"></div></td>
				</tr> -->
				
				<tr>
					<%-- <th width="15%">所在镇</th>
					<td width="35%">
						<c:if test="${tempDeptCode != 1}">
							<select id="szzid" name="commoTrouble.szz" style="width:135px;" onchange="clearCompany()" ><option value="">请选择</option></select><span style="color:red">*</span>
						</c:if>
						<c:if test="${tempDeptCode == 1}">
							<cus:SelectOneTag property="commoTrouble.county" defaultText='请选择' codeName="地址" value="${commoTrouble.county}" onchange="querySzz(this.value);"  style="width:150px;" />
							<select id="szzid" name="commoTrouble.szz" style="width:150px;" onchange="setSzz(this.value);"><option value="">请选择</option></select>
							<span color="red">*</span>
						</c:if>
						<!-- 
						<cus:SelectOneTag onchange="clearCompany()" property="commoTrouble.szz" defaultText='请选择' codeName="相城地址" value="${commoTrouble.szz}"/>
						 -->
					</td> --%>
					<th width="15%">检查企业名称</th>
					<td width="35%">
						<input name="commoTrouble.qymc" id="qymc" value="${commoTrouble.qymc}" type="text" maxlength="255">
						<a href="#" class="easyui-linkbutton" onclick="choseCompany()" iconCls="icon-save">选择</a>&nbsp;
						<input type="hidden" id="qymcid" name="commoTrouble.qymcId" value="${commoTrouble.qymcId}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">检查时间</th>
					<td width="35%">
						<input type="text" name="commoTrouble.jcsj"
											class="Wdate"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											value="${commoTrouble.jcsj}"/>
					</td>
					<th width="15%">检查人员</th>
					<td width="35%"><input name="commoTrouble.jcry" value="${commoTrouble.jcry}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">出动人次</th>
					<td width="35%"><input name="commoTrouble.cdcs" value="${commoTrouble.cdcs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="15%">重大隐患数</th>
					<td width="35%"><input name="commoTrouble.zdyhs" value="${commoTrouble.zdyhs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>	
					<th width="15%">隐患数</th>
					<td width="35%"><input name="commoTrouble.yhs" value="${commoTrouble.yhs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="15%">执法文书</th>
					<td width="35%"><cus:SelectOneTag property="commoTrouble.zfws" defaultText='请选择' codeName="执法文书" value="${commoTrouble.zfws}" /><br></td>
				</tr>
				<tr>
					<th width="15%">执法文书号</th>
					<td width="35%"><input name="commoTrouble.zfwsh" value="${commoTrouble.zfwsh}" type="text" maxlength="255"></td>
					<th width="15%">隐患内容</th>
					<td width="35%"><input name="commoTrouble.yhnr" value="${commoTrouble.yhnr}" type="text" maxlength="255"></td>
				</tr>
				
				<tr>
					<th width="15%">执法文书或图片</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zftp')" title="点击上传附件">&nbsp;&nbsp;选择文书或图片</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zftp">
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
				</tr>
				<tr>
					<th width="15%">是否企业上报整改信息</th>
					<td width="35%">
						<s:if test="commoTrouble.shzt eq 3 or type eq 3">
							<input type="hidden" name="commoTrouble.isqysb" value="${commoTrouble.isqysb}"/>
							<s:radio theme="simple" list="#{'0':'是','1':'否'}" name="d" disabled="true" value="#{commoTrouble.isqysb}"/>
						</s:if>
						<s:else>
							<s:radio theme="simple" list="#{'0':'是','1':'否'}" name="commoTrouble.isqysb" value="#{commoTrouble.isqysb}" onclick="showDiv(this.value)"/>
						</s:else>
						
					</td>
				</tr>
				<s:if test="(commoTrouble.isqysb eq 1 and commoTrouble.shzt eq 3 or flag eq 'upload') 
				or (commoTrouble.isqysb eq 0 and commoTrouble.shzt eq 3 and type eq 3) or flag eq 'upload'">
				<tr>
					<td colspan="4">
					<table id="div_zgqk">
						<tr>
							<th width="15%">隐患整改数</th>
							<td width="35%"><input name="commoTrouble.yhzgs" value="${commoTrouble.yhzgs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
							<th width="15%">重大隐患整改数</th>
							<td width="35%"><input name="commoTrouble.zdyhzgs" value="${commoTrouble.zdyhzgs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
						</tr>
						<tr>
							<th width="15%">整改资金</th>
							<td width="35%"><input name="commoTrouble.zgzj" value="${commoTrouble.zgzj}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
							<th width="15%">整改完成时间</th>
							<td width="35%">
								<input type="text" name="commoTrouble.zgwcsj"
													class="Wdate"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
													value="${commoTrouble.zgwcsj}"/>
								</td>
						</tr>
						<tr>
							<th width="15%">整改情况</th>
							<td width="85%" colspan="3">
								<table width="100%">
									<tr>
										<td width="20%">
											<a style="color:blue;" href="javascript:uploadPhoto('zgqk')" title="点击上传附件">&nbsp;&nbsp;选择整改材料</a>
										</td>
										<td width="70%">
											<div style="color:green;overflow:auto;height:160px;">
												<div style="overflow:auto;">
											    	  <table id="zgqk">
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
					</table>
				</td></tr>
				</s:if>
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

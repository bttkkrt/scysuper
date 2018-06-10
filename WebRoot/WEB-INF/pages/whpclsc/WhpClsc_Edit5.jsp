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
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="whpClscSave.action";
				document.myform1.submit();
			}
		}
		 //图片上传 lj 2013-09-22    
        function uploadPhoto(obj){
        		window.open("whpClscUpload.action?whpClsc.linkId=${whpClsc.linkId}&type="+obj);
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
		                	url : "whpClscImageDel.action",
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
        		window.location.href="whpClscSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "whpClscImageDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
        }
        
        function queryQy()
		{
			var szzid = document.getElementById('szzid').value;
			var tempDeptCode = $("#tempDeptCode").val();
			var OpenWindow = window.showModalDialog("${ctx}/jsp/company/queryCompanyList.action?company.dwdz1="+szzid+"&tempDeptCode="+tempDeptCode+"&company.county="+$("#county").val(), '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
			if(typeof(OpenWindow) != 'undefined')
			{
				var sonValue = OpenWindow.split(";");
				document.getElementById('qyid').value = sonValue[0];
				document.getElementById('qymc').value = sonValue[1];
				if(tempDeptCode != '1'){
					document.getElementById('szzid').value = sonValue[2];
				}else{
					document.getElementById('county').value = sonValue[3];
					querySzc(sonValue[3],sonValue[2]);
				}
			}
		}
		
		function clearCompany(){
        	document.getElementById("qymc").value="";
        	document.getElementById("qyid").value="";
        }
        
        
        function changeCaiLiao()
		{  
			var val = $("input[name='isCaiLiao']:checked").val(); //获得选中的radio的值             
			if(val=='1'){    
				$('#isCaiLiao').val(1);    
			}else{   
				$('#isCaiLiao').val(0);  
			}                    
		} 
		      
		function changeCunChu()
		{
			var val = $("input[name='isCunChu']:checked").val(); //获得选中的radio的值             
			if(val=='1'){    
				$('#isCunChu').val(1);    
			}else{   
				$('#isCunChu').val(0);  
			}                    
		}     
	
	
	
		function init1()
        {
        	
	        if($('#isCaiLiao').val()==1)
			{
			    document.getElementById("isCaiLiao1").checked=true;
			    document.getElementById("isCaiLiao0").checked=false;
			}else if($('#isCaiLiao').val()==0)
			{
			    document.getElementById("isCaiLiao1").checked=false;
			    document.getElementById("isCaiLiao0").checked=true;
			}
			
	        if($('#isCunChu').val()==1)
			{
			    document.getElementById("isCunChu1").checked=true;
			    document.getElementById("isCunChu0").checked=false;
			}else if($('#isCunChu').val()==0)
			{
			    document.getElementById("isCunChu1").checked=false;
			    document.getElementById("isCunChu0").checked=true;
			}
		}
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
	    function querySzc(obj,tempdwdz1)
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
	  					var option = "";
				    	if(tempdwdz1 == json[i].id){
							option = $('<option></option>').text(json[i].name).val(json[i].id).attr("selected","selected"); 
				    	}else{
				    		option = $('<option></option>').text(json[i].name).val(json[i].id);
				    	}
						selectContainer.append(option); 
				 	}
				},
				dateType:"json"
			});
	    } 
	</script>
	
</head>
<body onload="init1()">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" id="linkId" name="whpClsc.linkId" value="${whpClsc.linkId}"/>
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="whpClsc.id" value="${whpClsc.id}">
		<input type="hidden" name="whpClsc.sclx" value="${whpClsc.sclx}">
		<input type="hidden" name="whpClsc.createTime" value="<fmt:formatDate type="both" value="${whpClsc.createTime}" />">
		<input type="hidden" name="whpClsc.updateTime" value="${whpClsc.updateTime}">
		<input type="hidden" name="whpClsc.createUserID" value="${whpClsc.createUserID}">
		<input type="hidden" name="whpClsc.updateUserID" value="${whpClsc.updateUserID}">
		<input type="hidden" name="whpClsc.deptId" value="${whpClsc.deptId}">
		<input type="hidden" name="whpClsc.delFlag" value="${whpClsc.delFlag}">
		
		<input type="hidden" name="whpClsc.shenhe" value="${whpClsc.shenhe}">
		
		
		<input type="hidden" id="isCaiLiao" name="whpClsc.isCaiLiao" value="${whpClsc.isCaiLiao}">
		<input type="hidden" id="isCunChu" name="whpClsc.isCunChu" value="${whpClsc.isCunChu}">
		
		<input type="hidden" id="tempDeptCode" name="tempDeptCode" value="${tempDeptCode}">
		<input type="hidden" id="szzidhu" value="${whpClsc.szzid}">
		<input name="whpClsc.ifzsqy" value="${whpClsc.ifzsqy}" type="hidden" >
		<input name="whpClsc.zsqytype" value="${whpClsc.zsqytype}" type="hidden" >
		<input name="whpClsc.dutyFlag" value="${whpClsc.dutyFlag}" type="hidden" >
		<input name="whpClsc.shuserid" value="${whpClsc.shuserid}" type="hidden" >
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="30%" colspan="2">所在镇</th>
					<td width="70%">
						<c:if test="${tempDeptCode != 1}">
							<select id="szzid" name="whpClsc.szzid" style="width:135px;" onchange="clearCompany()" ><option value="">请选择</option></select><font style="color:red">*</font>
						</c:if>
						<c:if test="${tempDeptCode == 1}">
							<cus:SelectOneTag property="whpClsc.county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
							<select id="szzid" name="whpClsc.szzid" style="width:150px;" ><option value="">请选择</option></select>
							<font color="red">*</font>
						</c:if><!-- 
						<cus:SelectOneTag property="whpClsc.szzid" defaultText='请选择' codeName="相城地址" value="${whpClsc.szzid}" dataType="Require" msg="此项为必填" onchange="clearCompany()"/><font style="color:red">*</font>
						 -->
					</td>
				</tr>
				<tr>
					<th width="30%" colspan="2">企业名称</th>
					<td width="70%">
					<input id="qymc" name="whpClsc.qymc" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${whpClsc.qymc}"><font style="color:red">*</font>
					<input name="whpClsc.qyid" value="${whpClsc.qyid}"  type="hidden" id="qyid" type="text" maxlength="255">
					<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>
					</td>
				</tr>
		   
		  
					<tr>
					<th width="30%" colspan="2">项目内容</th>
					<td   width="70%">
					
					<textarea id="xmnr"  
						name="whpClsc.xmnr" rows="4"
						style="width: 80%">${whpClsc.xmnr}</textarea>
								
					</td>
				 
				</tr>
				
				<tr>	
					<th width="30%" colspan="2">备案编号</th>
					<td width="70%">
					<input name="whpClsc.babh" value="${whpClsc.babh}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>
				
				
				<tr>
					<th width="30%" colspan="2"> 备案日期</th>
					<td width="70%">
				    <input name="whpClsc.barq" id="barq"  
				   value="<fmt:formatDate type="date" value="${whpClsc.barq}" pattern="yyyy-MM-dd"/>"
					type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
	 
		 		 
		 		 
		 		 			
	 
		 			
		 		<tr>
					<th width="30%" colspan="2">项目性质 </th>
					<td width="70%">
					
					<s:select id="xmxz" listKey="key" listValue="value" 
					 theme="simple" list="#{'0':'新建','1':'改建','2':'扩建'}"
					 name="whpClsc.xmxz" value="{whpClsc.xmxz}"/>
					 
					</td>
				</tr>	
		 			
		 		 
				<tr>
					<th width="30%" colspan="2">项目类型</th>
					<td width="70%">
				<cus:hxcheckbox property="whpClsc.xmlx" defaultText='请选择' codeName="项目类型" 
				value="${whpClsc.xmlx}"   /> 
					</td>
				</tr>
				
				<tr>	
					<th width="30%" colspan="2">材料接收人员</th>
					<td width="70%">
					<input name="whpClsc.cljsry" value="${whpClsc.cljsry}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>	
				
				<tr>	
					<th width="30%" colspan="2">材料审查人员</th>
					<td width="70%">
					<input name="whpClsc.clscry" value="${whpClsc.clscry}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>	
				
				<tr>	
					<th width="30%" colspan="2">档案编号</th>
					<td width="70%">
					<input name="whpClsc.fileId" value="${whpClsc.fileId}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>	
				
				<tr>
					<th width="30%"  colspan="2">申请材料是否齐全</th>
					<td width="70%">
					
					<input type="radio" onchange="changeCaiLiao()" name="isCaiLiao" id="isCaiLiao0" value="0"  class="filetype1">否
                   	<input type="radio" onchange="changeCaiLiao()" name="isCaiLiao" id="isCaiLiao1" value="1"  class="filetype1">是 
                     	
					</td>
					
					</tr>
					
					
				<tr>	
					<th width="30%" colspan="2">签字领导</th>
					<td width="70%">
					<input name="whpClsc.qzld" value="${whpClsc.qzld}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>
				
				
				
				<tr>
					<th width="30%" colspan="2">材料审查情况</th>
					<td   width="70%">
					<textarea id="clscqk"  
						name="whpClsc.clscqk" rows="4"
						style="width: 80%">${whpClsc.clscqk}</textarea>
					</td>
				 
				</tr>
				
				
				
				<tr>
					<th width="30%" colspan="2">行政许可建议</th>
					<td   width="70%">
					<textarea id="xzxkjy"  
						name="whpClsc.xzxkjy" rows="4"
						style="width: 80%">${whpClsc.xzxkjy}</textarea>
					</td>
				 
				</tr>
				
				
				<tr>
					<th width="30%" colspan="2">局会审记录</th>
					<td   width="70%">
					<textarea id="jhsjl"  
						name="whpClsc.jhsjl" rows="4"
						style="width: 80%">${whpClsc.jhsjl}</textarea>
					</td>
				 
				</tr>
				
				
				
				
				
				
				
				<c:forEach items="${images}" var="items" varStatus="status">
				<tr>
					<th width="5%" style="text-align:center;">${status.index+1}</th>
					<th width="25%">${items.name}</th>
					<td width="70%">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('${code}00${status.index+1}')" title="点击上传附件">&nbsp;&nbsp;选择文件</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="${code}00${status.index+1}">
													<c:forEach var="item" items="${items.images}">
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
				</c:forEach>
				<!-- 
				<s:if test="whpClsc.state == 2">
				<tr>
					<th width="30%" colspan="2">审核记录</th>
					<td width="70%" colspan="3">
						<table>
							<s:iterator  value="shenheList" status="sta">
	    						<tr>
	    	 						<th> ${sta.index+1}</th>
	    	 						<td><s:property  /></td>
	    						</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				</s:if>
				<tr>
					<th width="30%" colspan="2">选择审核人员</th>
					<td width="70%">
						<s:select  theme="simple" cssStyle="width:100px;" id="shuserid" list="%{userList}" listKey="id"
							name="whpClsc.shuserid" listValue="displayName"  dataType="Require" msg="此项为必填">
						</s:select><font style="color:red">*</font>
					</td>
				</tr>
				 -->
				
				<tr>
					<td colspan="3" height="100px"   style="text-align:center;">
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

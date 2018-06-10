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
				
				document.myform1.action="zywsxzxkSave.action";
				document.myform1.submit();
			}
		}
		
		 //图片上传 lj 2013-08-31
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/zywsxzxk/zywsxzxkUpload.action?zywsxzxk.linkid=${zywsxzxk.linkid}&flag="+obj);
        	}
         function savepic(i){
        		window.location.href="${ctx}/jsp/zywsxzxk/zywsxzxkSaveFile.action?picName="+i;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "${ctx}/jsp/zywsxzxk/zywsxzxkImageDel.action",
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
		function showDiv(obj){
			if(obj!=1){
				document.getElementById("showDiv").style.display = "block";
			}else{
				document.getElementById("showDiv").style.display = "none";
			}
		}
		
		function queryPjjg()
		{
			document.getElementById('pjjgxz').style.display="";
		}
		
		function choosePjjg(obj)
		{
			if(obj == '请选择')
			{
				obj = "";
			}
			document.getElementById('pjjg').value = obj;
			document.getElementById('pjjgxz').style.display="none";
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
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zywsxzxk.id" value="${zywsxzxk.id}">
		<input type="hidden" name="zywsxzxk.createTime" value="<fmt:formatDate type="both" value="${zywsxzxk.createTime}" />">
		<input type="hidden" name="zywsxzxk.updateTime" value="${zywsxzxk.updateTime}">
		<input type="hidden" name="zywsxzxk.createUserID" value="${zywsxzxk.createUserID}">
		<input type="hidden" name="zywsxzxk.updateUserID" value="${zywsxzxk.updateUserID}">
		<input type="hidden" name="zywsxzxk.deptId" value="${zywsxzxk.deptId}">
		<input type="hidden" name="zywsxzxk.delFlag" value="${zywsxzxk.delFlag}">
		<input type="hidden" name="zywsxzxk.linkid" value="${zywsxzxk.linkid}">
		<input type="hidden" name="zywsxzxk.type" value="${zywsxzxk.type}">
		<input type="hidden" name="zywsxzxk.remark" value="${zywsxzxk.remark}">
		<input type="hidden" name="zywsxzxk.shenhe" value="${zywsxzxk.shenhe}">
		
		<input type="hidden" id="isCaiLiao" name="zywsxzxk.isCaiLiao" value="${zywsxzxk.isCaiLiao}">
		<input type="hidden" id="isCunChu" name="zywsxzxk.isCunChu" value="${zywsxzxk.isCunChu}">
		
		<input type="hidden" id="tempDeptCode" name="tempDeptCode" value="${tempDeptCode}">
		<input type="hidden" id="szzidhu" value="${zywsxzxk.szzid}">
		<input name="zywsxzxk.ifzsqy" value="${zywsxzxk.ifzsqy}" type="hidden" >
		<input name="zywsxzxk.zsqytype" value="${zywsxzxk.zsqytype}" type="hidden" >
		<input name="zywsxzxk.dutyFlag" value="${zywsxzxk.dutyFlag}" type="hidden" >
		<input name="zywsxzxk.shuserid" value="${zywsxzxk.shuserid}" type="hidden" >
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">
						<c:if test="${tempDeptCode != 1}">
							<select id="szzid" name="zywsxzxk.szzid" style="width:135px;" onchange="clearCompany()" ><option value="">请选择</option></select><font style="color:red">*</font>
						</c:if>
						<c:if test="${tempDeptCode == 1}">
							<cus:SelectOneTag property="zywsxzxk.county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
							<select id="szzid" name="zywsxzxk.szzid" style="width:150px;" ><option value="">请选择</option></select>
							<font color="red">*</font>
						</c:if>
						<!-- 
						<cus:SelectOneTag property="whpClsc.szzid" defaultText='请选择' codeName="相城地址" value="${whpClsc.szzid}" dataType="Require" msg="此项为必填" onchange="clearCompany()"/><font style="color:red">*</font>
						 -->
					</td>
					<th width="15%">企业名称</th>
					<td width="35%">
					<input id="qymc" name="zywsxzxk.qymc" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${zywsxzxk.qymc}"><font style="color:red">*</font>
					<input name="zywsxzxk.qyid" value="${zywsxzxk.qyid}"  type="hidden" id="qyid" type="text" maxlength="255">
					<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>
					</td>
				</tr>
				<!-- lj 2014-06-30  start-->
				<tr>
					
					<th width="15%">
						
						<s:if test="zywsxzxk.type == 4">
							评价机构
						</s:if>	
						<s:if test="zywsxzxk.type == 3">
							评价单位
						</s:if>	
						<s:if test="zywsxzxk.type == 2">
							设计单位
						</s:if>	
						<s:if test="zywsxzxk.type == 1">
							评价机构
						</s:if>	
					
					</th>
					<td width="85%" colspan="3">
						<input type="text" id="pjjg" name="zywsxzxk.pjjg" value="${zywsxzxk.pjjg}">
						<s:if test="zywsxzxk.type != 2">
						<a href="#" class="easyui-linkbutton" onclick="javascript:queryPjjg()" iconCls="icon-save">选择</a>
						<cus:SelectOneTag property="pjjgxz" defaultText='请选择' codeName="安全评价机构" value="${zywsxzxk.pjjg}" style="display:none" onchange="choosePjjg(this.options[this.selectedIndex].text)"/>
						</s:if>	
					</td>
				</tr>
				
				
				<tr>	
					<th width="15%" >材料接收人员</th>
					<td width="85%" colspan="3">
					<input name="zywsxzxk.cljsry" value="${zywsxzxk.cljsry}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>	
				
				<tr>	
					<th width="15%" >材料审查人员</th>
					<td width="85%" colspan="3">
					<input name="zywsxzxk.clscry" value="${zywsxzxk.clscry}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>	
				
				<s:if test="zywsxzxk.type == 4 || zywsxzxk.type == 2 || zywsxzxk.type == 1">
				
				<tr>	
					<th width="15%" >档案编号</th>
					<td width="85%" colspan="3">
					<input name="zywsxzxk.fileId" value="${zywsxzxk.fileId}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>	
				</s:if>
				
				
				
				<s:if test="zywsxzxk.type == 3 || zywsxzxk.type == 2 || zywsxzxk.type == 1">
				<tr>
					<th width="15%">行业类别</th>
					<td width="35%"><cus:SelectOneTag property="zywsxzxk.hyfl" defaultText='请选择' codeName="企业行业分类" value="${zywsxzxk.hyfl}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
					
					<th width="15%">项目性质</th>
					<td width="35%">
						<s:select theme="simple" list="#{1:'新建',2:'改建',3:'扩建'}" name="zywsxzxk.xmxz" listKey="key" listValue="value"  headerKey="0" headerValue="--请选择--"></s:select>
					</td>
				</tr>
				
				<tr>
					<th width="15%">项目内容</th>
					<td width="85%" colspan="3">
					
					<textarea id="projectContent"  
						name="zywsxzxk.projectContent" rows="4"
						style="width: 80%">${zywsxzxk.projectContent}</textarea>
								
					</td>
				</tr>
				<s:if test="zywsxzxk.type == 3 || zywsxzxk.type == 2 || zywsxzxk.type == 1">
				<tr>
					<th width="15%">职业病危害风险分类</th>
					<td width="85%" colspan="3">
						<s:select onChange="showDiv(this.value)" theme="simple" list="#{1:'一般',2:'较重',3:'严重'}" name="zywsxzxk.fxfl" listKey="key" listValue="value"  headerKey="0" headerValue="--请选择--"></s:select>
					</td>
				 </s:if>
				</tr>
				<tr>
					<td width="100%" colspan="4">
				<div id="showDiv">
				<s:if test="zywsxzxk.fxfl != 1">
				<table width="100%">
				<tr>
					<th width="15%">
						<s:if test="zywsxzxk.type == 3">
							验收专家
						</s:if>	
						<s:if test="zywsxzxk.type == 2">
							审查专家
						</s:if>	
						<s:if test="zywsxzxk.type == 1">
							审查专家
						</s:if>	
					</th>
					<td width="35%">
						<input name="zywsxzxk.yszj" value="${zywsxzxk.yszj}" size= 50 type="text"   maxlength="255">
					</td>
					<th width="15%">
						<s:if test="zywsxzxk.type == 3">
							验收日期
						</s:if>	
						<s:if test="zywsxzxk.type == 2">
							审查日期
						</s:if>	
						<s:if test="zywsxzxk.type == 1">
							审查日期
						</s:if>	
					</th>
					<td width="35%">
					<input name="zywsxzxk.ysrq" value="${zywsxzxk.ysrq}" size= 50 type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				</tr>
				<s:if test="zywsxzxk.type == 1">
				<tr>
					<th width="15%">审查结果</th>
					<td width="85%" colspan="3">
					
					<textarea id="scjg"  
						name="zywsxzxk.scjg" rows="4"
						style="width: 80%">${zywsxzxk.scjg}</textarea>
								
					</td>
				</tr>
				</s:if>
				</table>
				</s:if>
				</div>
					</td>
				</tr>
				<tr>
					<th width="15%">审批编号</th>
					<td width="35%">
					<input name="zywsxzxk.spbh" value="${zywsxzxk.spbh}" size= 50 type="text"   maxlength="255">
					</td>
					<th width="15%">审批日期</th>
					<td width="35%">
					<input name="zywsxzxk.sprq" value="${zywsxzxk.sprq}" size= 50 type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				</tr>
				</s:if>
				<s:else>
					<tr>
					<th width="15%">材料接收日期</th>
					<td width="35%">
					<input name="zywsxzxk.jsrq" value="${zywsxzxk.jsrq}" size= 50 type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
					<th width="15%">材料上报市局日期</th>
					<td width="35%">
					<input name="zywsxzxk.sjrq" value="${zywsxzxk.sjrq}" size= 50 type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
					</td>
				</tr>
				</s:else>
				<!-- end -->
				<!-- 注释 lj 2014-07-07 
				
				
				<tr>
					<th width="15%">本次领证情况</th>
					<td width="85%" colspan="3">
					<s:select id="bclzqk" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','延期换证':'延期换证','变更法人':'变更法人','变更企业类型':'变更企业类型','变更营业执照':'变更营业执照','变更生产品种':'变更生产品种','新发证':'新发证'}" name="zywsxzxk.bclzqk" value="{zywsxzxk.bclzqk}"/>
					</td>
				 
				</tr>
				<tr>
					<th width="15%">申请材料是否齐全</th>
					<td width="35%">
					
					<input type="radio" onchange="changeCaiLiao()" name="isCaiLiao" id="isCaiLiao0" value="0"  class="filetype1">否
                   	<input type="radio" onchange="changeCaiLiao()" name="isCaiLiao" id="isCaiLiao1" value="1"  class="filetype1">是 
                     	
                     	
					</td>
					<th width="15%">签字领导</th>
					<td width="35%">
					<input name="zywsxzxk.qzld" value="${zywsxzxk.qzld}" size= 50 type="text"   maxlength="255">
					</td>
				</tr>
				
				<tr>
					<th width="15%">乡镇预审意见</th>
					<td width="85%" colspan="3">
					
					<textarea id="xzysyj"  
						name="zywsxzxk.xzysyj" rows="4"
						style="width: 80%">${zywsxzxk.xzysyj}</textarea>
								
					</td>
				 
				</tr>
				<tr>
					<th width="15%">现场核查部门</th>
					<td width="85%" colspan="3">
					<input name="zywsxzxk.xchcbm" value="${zywsxzxk.xchcbm}" size= 50 type="text"   maxlength="255">
				
					</td>
				 
				</tr>
				
				<tr>
					<th width="15%">核查结论</th>
					<td width="85%" colspan="3">
					<textarea id="hcjl"  
						name="zywsxzxk.hcjl" rows="4"
						style="width: 80%">${zywsxzxk.hcjl}</textarea>
					</td>
				 
				</tr>
				
				<tr>
					<th width="15%">是否储存涉及</th>
					<td width="85%"  colspan="3">
					
					<input type="radio" name="isCunChu" onchange="changeCunChu()" id="isCunChu0" value="0"  class="filetype2">否
                   	<input type="radio" name="isCunChu" onchange="changeCunChu()" id="isCunChu1" value="1"  class="filetype2">是
					</td>
				 
				</tr>
				
				<tr>
					<th width="15%">材料审查情况</th>
					<td width="85%" colspan="3">
					<textarea id="clscqk"  
						name="zywsxzxk.clscqk" rows="4"
						style="width: 80%">${zywsxzxk.clscqk}</textarea>
					</td>
				 
				</tr>
				
				
				<tr>
					<th width="15%">行政许可建议</th>
					<td width="85%" colspan="3">
					<textarea id="xzxkjy"  
						name="zywsxzxk.xzxkjy" rows="4"
						style="width: 80%">${zywsxzxk.xzxkjy}</textarea>
					</td>
				 
				</tr>
				
				
				<tr>
					<th width="15%">局会审记录</th>
					<td width="85%" colspan="3">
					<textarea id="jhsjl"  
						name="zywsxzxk.jhsjl" rows="4"
						style="width: 80%">${zywsxzxk.jhsjl}</textarea>
					</td>
				 
				</tr>
				end lj 2014-07-07 -->
				
				
				<tr>
					<th width="15%">
						<s:if test="zywsxzxk.type == 1">
							职业病危害预评价报告
						</s:if>
						<s:elseif test="zywsxzxk.type == 2">
							职业病防护设施设计专篇
						</s:elseif>
						<s:elseif test="zywsxzxk.type == 3">
							职业病防护设施竣工验收报告
						</s:elseif>
						<s:else>
							职业病危害控制效果评价报告
						</s:else>
					</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center>
									<a style="color:blue;" href="javascript:uploadPhoto('zywsxzxk1')" title="点击上传附件">
										<s:if test="zywsxzxk.type == 1">
											选择职业病危害预评价报告
										</s:if>
										<s:elseif test="zywsxzxk.type == 2">
											选择职业病防护设施设计专篇
										</s:elseif>
										<s:elseif test="zywsxzxk.type == 3">
											选择职业病防护设施竣工验收报告
										</s:elseif>
										<s:else>
											选择职业病危害控制效果评价报告
										</s:else>
									</a>
									</center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="zywsxzxk1">
												<c:forEach var="item" items="${picList1}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
													     	${item.fileName}
														</td>
														<td width="25%">
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
					<th width="15%">
						<s:if test="zywsxzxk.type == 1">
							职业病危害预评价报告专家审查意见及审查会签到表
						</s:if>
						<s:elseif test="zywsxzxk.type == 2">
							职业病防护设施设计专家审查意见及审查会签到表
						</s:elseif>
						<s:elseif test="zywsxzxk.type == 3">
							职业病防护设施竣工验收报告专家审查意见及审查会签到表
						</s:elseif>
						<s:else>
							其它申报材料
						</s:else>
					</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center>
									<a style="color:blue;" href="javascript:uploadPhoto('zywsxzxk2')" title="点击上传附件">
										<s:if test="zywsxzxk.type == 1">
											选择职业病危害预评价报告专家审查意见及审查会签到表
										</s:if>
										<s:elseif test="zywsxzxk.type == 2">
											选择职业病防护设施设计专家审查意见及审查会签到表
										</s:elseif>
										<s:elseif test="zywsxzxk.type == 3">
											选择职业病防护设施竣工验收报告专家审查意见及审查会签到表
										</s:elseif>
										<s:else>
											选择其它申报材料
										</s:else>
									</a>
									</center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="zywsxzxk2">
												<c:forEach var="item" items="${picList2}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
													     	${item.fileName}
														</td>
														<td width="25%">
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
					<th width="15%">
						<s:if test="zywsxzxk.type == 1">
							建设项目职业病危害预评价报告备案通知书或审批文件
						</s:if>
						<s:elseif test="zywsxzxk.type == 2">
							建设项目职业病防护设施安监部门的审批文件
						</s:elseif>
						<s:elseif test="zywsxzxk.type == 3">
							建设项目职业病防护设施竣工验收报告备案通知书或审批文件
						</s:elseif>
						<s:else>
							区安监局行政许可审查表
						</s:else>
					</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td>
									<center>
									<a style="color:blue;" href="javascript:uploadPhoto('zywsxzxk3')" title="点击上传附件">
										<s:if test="zywsxzxk.type == 1">
											选择建设项目职业病危害预评价报告备案通知书或审批文件
										</s:if>
										<s:elseif test="zywsxzxk.type == 2">
											选择建设项目职业病防护设施安监部门的审批文件
										</s:elseif>
										<s:elseif test="zywsxzxk.type == 3">
											选择建设项目职业病防护设施竣工验收报告备案通知书或审批文件
										</s:elseif>
										<s:else>
											选择区安监局行政许可审查表
										</s:else>
									</a>
									</center>
								</td>
							</tr>
							<tr>
								<td>
									<div style="color:green;overflow:auto;height:160px;">
									  	<div>
										  	<table id="zywsxzxk3">
												<c:forEach var="item" items="${picList3}">
													<tr id='${item.id}' style="text-align: center">
														<td width="70%">
													     	${item.fileName}
														</td>
														<td width="25%">
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
				<!-- 
				<s:if test="zywsxzxk.state == 2">
				<tr>
					<th width="15%">审核记录</th>
					<td width="85%" colspan=3>
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
					<th width="15%">选择审核人员</th>
					<td width="85%" colspan="3">
						<s:select  theme="simple" cssStyle="width:100px;" id="shuserid" list="%{userList}" listKey="id"
							name="zywsxzxk.shuserid" listValue="displayName" dataType="Require" msg="此项为必填">
						</s:select><font style="color:red">*</font>
					</td>
				</tr>
				 -->
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
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

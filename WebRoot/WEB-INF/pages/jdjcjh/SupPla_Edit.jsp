<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	function queryQy()
		{
			popupCenter("${ctx}/jsp/jdjcjh/selectCompanys.action", "setUser", "800", "600", "no", "no", "no", "no", "no","no");
		}
	 function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
        
        function saveSelText(i,text){
			$("#"+i).val(text);
			$("#companyIds").val("");
			$("#companyNames").val("");
		}
		
		function saveSelText1(i,text){
			$("#"+i).val(text);
		}
		
		
		function queryQy2()
		{
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?supPla.id=${supPla.id}&flag=idmc", "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		function queryQy3()
		{
		var planId=document.getElementById("supPla_planId").value;
		var checkUserId=document.getElementById("supPla_checkUserId").value;
		if(checkUserId==""){
		   	alert("请先选择承办人");
		}else if(planId==""){
			alert("请先选择所属年计划");
		}
		else{
			popupCenter("${ctx}/jsp/jdjcjh/selectCompanys2.action?supPla.checkUserId="+checkUserId+"&supPla.planId="+planId, "setUser", "800", "600", "no", "no", "no", "no", "no","no");
		}
		}
		
		function check(){
		var vs=$("#checkItemType").val();
		
	
         	$.ajax({
		                	url : "checks.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
			"supPla.checkItemType":vs
		                    },
		                   success: function(data){
		                     if(data.result){

		                        }else{
		                        	$.messager.alert('提示','该检查项类型没有检查项，请先添加检查项');
		                        	$("#checkItemType").val("");
		                        }
		                      }
		});
         
         
         
		}
		
		function changeplanType(obj){
	
	     if(obj=='interim'){
	     
	       $("#tab1").hide();
	        $("#tab2").show();
	    
	     }else{
	       $("#tab2").hide();
	        $("#tab1").show();
	      
	     }
      
		  
		}
        
         
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="supPlaSave.action" >
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="supPla.id" value="${supPla.id}">
		<input type="hidden" name="supPla.createTime" value="<fmt:formatDate type="both" value="${supPla.createTime}" />">
		<input type="hidden" name="supPla.updateTime" value="${supPla.updateTime}">
		<input type="hidden" name="supPla.createUserID" value="${supPla.createUserID}">
		<input type="hidden" name="supPla.updateUserID" value="${supPla.updateUserID}">
		<input type="hidden" name="supPla.deptId" value="${supPla.deptId}">
		<input type="hidden" name="supPla.delFlag" value="${supPla.delFlag}">
		
		<input type="hidden" name="supPla.ifywc" value="${supPla.ifywc}">
		<input type="hidden" id="checkDeptName" name="supPla.checkDeptName" value="${supPla.checkDeptName}"/>
		<input type="hidden" id="checkUserName" name="supPla.checkUserName" value="${supPla.checkUserName}"/>	
		<input type="hidden" id="xbUserName" name="supPla.xbUserName" value="${supPla.xbUserName}"/>	
		<input type="hidden" id="addType" name="supPla.addType" value="${supPla.addType}">
			<table width="100%" border="0">
			<s:if test="mark == 0 ">
			    
				<tr>
					<th width="15%">计划名称</th>
					<td width="35%"><input name="supPla.planName" value="${supPla.planName}" style="width:60%" type="text" datatype="*1-15" errormsg='计划名称必须是1到15位字符！' nullmsg='计划名称不能为空！' sucmsg='计划名称填写正确！'  maxlength="15"><font style='color:red'>*</font></td>
					<th width="15%">计划类型</th>
					<td width="35%"><s:select  id="planType"  name="supPla.planType" cssStyle="width:60%" list="#{'year':'年计划','interim':'临时计划'}" theme="simple" onchange="changeplanType(this.value);"/></td>
				</tr>
				
				<tr>
					<th width="15%">计划开始时间</th>
					<td width="35%"><input id="startDate" name="supPla.planStartTime" style="width:60%" value="<fmt:formatDate type='both' value='${supPla.planStartTime}' pattern="yyyy-MM-dd"/>" type="text"  datatype="*"  nullmsg='计划开始时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})"><font style='color:red'>*</font></td>
					<th width="15%">计划结束时间</th>
					<td width="35%"><input id="endDate" name="supPla.planEndTime" style="width:60%" value="<fmt:formatDate type='both' value='${supPla.planEndTime}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='计划结束时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})"><font style='color:red'>*</font></td>
				</tr>
				
				<tr>
					<th width="15%">检查对象名称</th>
					<td width="70%" colspan="3" style="padding: 0 0 0 0;">
						<table>
							<tr>
								<td width="90%" ><input type="hidden" id="companyIds" name="supPla.checkCompanyId" value="${supPla.checkCompanyId}"/>
						<textarea style="width:90%;height:100px;" readonly="readonly" id="companyNames" name="supPla.checkCompanyName" datatype="*"  nullmsg='检查对象不能为空！'>${supPla.checkCompanyName}</textarea></td>
								
								<td width="10%">
								<s:if test="supPla.addType != 1">
								<a href="###" class="btn_01_mini1" onclick="queryQy()">选择</a>
								</s:if>
								</td>
								
							</tr>
						</table>
					</td>
				</tr>
				
			</s:if>
			
			
			<s:if test="mark == 1 ">
				<tr>
					<th width="15%">计划开始时间</th>
					<td width="35%"><input id="startDate" name="supPla.planStartTime" style="width:60%" value="<fmt:formatDate type='both' value='${supPla.planStartTime}' pattern="yyyy-MM-dd"/>" type="text"  datatype="*"  nullmsg='计划开始时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})"><font style='color:red'>*</font></td>
					<th width="15%">计划结束时间</th>
					<td width="35%"><input id="endDate" name="supPla.planEndTime" style="width:60%" value="<fmt:formatDate type='both' value='${supPla.planEndTime}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='计划结束时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">所属年计划</th>
						<td width="35%">
							<s:select theme="simple" list="planlist" cssStyle="width:60%" listKey="planId" listValue="planName" name="supPla.planId"
	 						 headerKey="" headerValue="--请选择--"  datatype="*"  nullmsg='所属年计划不能为空！'  onchange="saveSelText('planName',jQuery('#supPla_planId  option:selected').text())">
	 					    </s:select>
	 					    <input type="hidden" id="planName" name="supPla.yearPlanName" value="${supPla.yearPlanName}"/>	
							<input name="supPla.planName" value="${supPla.planName}" type="hidden"  />
	 					    <font style='color:red'>*</font>
						</td>
				</tr>		
				<tr>
					
					<th width="15%">检查项类型</th>
					<td width="85%" colspan="3">
						<cus:hxcheckbox property="supPla.checkItemType"  codeSql="select t.row_id,t.PATROL_TYPE_NAME from PAT_TYP_MAN t where t.delflag = 0" value="${supPla.checkItemType}" datatype="*"  nullmsg='检查类型不能为空！' />
						<font style='color:red'>*</font>
					</td>
				</tr>		
				<tr>
					<th width="15%">承办人</th>
					<td width="35%">
						<s:select theme="simple" list="persons" listKey="id" cssStyle="width:60%" listValue="name"  name="supPla.checkUserId"
	 								  headerKey="" headerValue="--请选择--"  datatype="*"  nullmsg='承办人不能为空！'  onchange="saveSelText('checkUserName',jQuery('#supPla_checkUserId  option:selected').text())">
	 						    </s:select>
	 					<font style='color:red'>*</font>
					</td>
					<th width="15%">协办人</th>
					<td width="35%">
						<s:select theme="simple" list="persons" listKey="id" cssStyle="width:60%" listValue="name"  name="supPla.xbUserId"
	 								  headerKey="" headerValue="--请选择--"  datatype="*"  nullmsg='协办人不能为空！'  onchange="saveSelText1('xbUserName',jQuery('#supPla_xbUserId  option:selected').text())">
	 						    </s:select>
	 					<font style='color:red'>*</font>
					</td>
				<tr>
				</tr>
				<tr>
					<th width="15%">检查对象名称</th>
					<td width="85%" colspan="3" style="padding: 0 0 0 0;">
					<table>
							<tr>
								<td width="90%" ><input type="hidden" id="companyIds" name="supPla.checkCompanyId" value="${supPla.checkCompanyId}"/>
						<textarea style="width:90%;height:100px;" readonly="readonly" id="companyNames" name="supPla.checkCompanyName" datatype="*"  nullmsg='检查对象不能为空！'>${supPla.checkCompanyName}</textarea></td>
								
								<td width="10%">
									<a href="###" class="btn_01_mini1" onclick="queryQy3()">选择</a>
									<input type="hidden" name="supPla.planType" value="week">
								</td>
								
							</tr>
						</table>
					</td>
				</tr>
				
			</s:if>
			
			<s:if test="mark == 2 ">
			  <tr>
					<th width="15%">计划名称</th>
						<td width="35%"><input name="supPla.planName" style="width:60%" value="${supPla.planName}" type="text" datatype="*1-15" errormsg='计划名称必须是1到15位字符！' nullmsg='计划名称不能为空！' sucmsg='计划名称填写正确！'  maxlength="15"><font style='color:red'>*</font></td>
				
					
						<th width="15%">检查部门名称</th>
						<td width="35%">
							<s:select theme="simple" list="depts" listKey="code" cssStyle="width:60%" listValue="name" name="supPla.checkDeptId"
	 								 headerKey="" headerValue="--请选择--" datatype="*"  nullmsg='检查部门不能为空！' onchange="saveSelText('checkDeptName',jQuery('#supPla_checkDeptId  option:selected').text())">
	 					    </s:select>
	 					    <font style='color:red'>*</font>
	 					    
						</td>
				</tr>
				<tr>
					<th width="15%">计划开始时间</th>
					<td width="35%"><input id="startDate" name="supPla.planStartTime" style="width:60%" value="<fmt:formatDate type='both' value='${supPla.planStartTime}' pattern="yyyy-MM-dd"/>" type="text"  datatype="*"  nullmsg='计划开始时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})"><font style='color:red'>*</font></td>
					<th width="15%">计划结束时间</th>
					<td width="35%"><input id="endDate" name="supPla.planEndTime" style="width:60%" value="<fmt:formatDate type='both' value='${supPla.planEndTime}' pattern="yyyy-MM-dd"/>" type="text" datatype="*"  nullmsg='计划结束时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})"><font style='color:red'>*</font></td>
				</tr>
				<th width="15%">检查项类型</th>
				<td width="85%" colspan="3">
						<cus:hxcheckbox property="supPla.checkItemType"  codeSql="select t.row_id,t.PATROL_TYPE_NAME from PAT_TYP_MAN t where t.delflag = 0" value="${supPla.checkItemType}" datatype="*"  nullmsg='检查类型不能为空！' />
						<font style='color:red'>*</font>
				</td>
				</tr>
				<tr>
				<th width="15%">检查对象名称</th>
					<td width="85%" colspan="3"><input id="companyName" style="width:60%" name="supPla.checkCompanyName" value="${supPla.checkCompanyName}" type="text" readonly="readonly" onclick="queryQy2()"/>
						<input type="hidden" id="companyId" name="supPla.checkCompanyId" value="${supPla.checkCompanyId}"/>
					</td>
				</tr>
				<input type="hidden" name="supPla.planType" value="day">
			</s:if>
				
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit"  >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_supPla');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
		function saveSelText(i,text){
			$("#"+i).val(text);
		}
		function check(){
		var vs=$("#checkType").val();
         	$.ajax({
		                	url : "${ctx}/jsp/jdjcrw/check.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
			"supTas.checkItemId":vs
		                    },
		                   success: function(data){
		                     if(data.result){

		                        }else{
		                        	$.messager.alert('提示','该巡查项类型没有检查项，请先添加巡查项');
		                        	$("#checkItemId").val("");
		                        }
		                      }
		});
         
         
         
		}
		function setName(name){
			$("#checkPeopleName").val(name);
		}
		
		function changecheckKind(obj){
	
	     if(obj=='日常巡查'){
	     
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
	<form name="myform1" method="post" enctype="multipart/form-data" action="danTasManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="danTasMan.id" value="${danTasMan.id}">
		<input type="hidden" name="danTasMan.createTime" value="<fmt:formatDate type="both" value="${danTasMan.createTime}" />">
		<input type="hidden" name="danTasMan.updateTime" value="${danTasMan.updateTime}">
		<input type="hidden" name="danTasMan.createUserID" value="${danTasMan.createUserID}">
		<input type="hidden" name="danTasMan.updateUserID" value="${danTasMan.updateUserID}">
		<input type="hidden" name="danTasMan.deptId" value="${danTasMan.deptId}">
		<input type="hidden" name="danTasMan.delFlag" value="${danTasMan.delFlag}">
		<input type="hidden" name="danTasMan.assPlanNo" value="${danTasMan.assPlanNo}">
		<input type="hidden" name="danTasMan.chenkNo" value="${danTasMan.chenkNo}">
		<input type="hidden" name="danTasMan.result" value="待巡查">
		
		<input type="hidden"  name="danTasMan.areaId" value="${danTasMan.areaId}">
		<input type="hidden"  name="danTasMan.companyId" value="${danTasMan.companyId}">
		<input type="hidden"  name="danTasMan.areaName" value="${danTasMan.areaName}">
		<input type="hidden"  name="danTasMan.companyName" value="${danTasMan.companyName}">
		<input type="hidden"  id="checkPeopleName" name="danTasMan.checkPeopleName" value="${danTasMan.checkPeopleName}">
		<input type="hidden" id="dangerName" name="danTasMan.dangerName" value="${danTasMan.dangerName}">
		<input type="hidden" id="checkRodeName" name="danTasMan.checkRodeName" value="${danTasMan.checkRodeName}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">巡查单号</th>
					<td width="85%">${danTasMan.chenkNo}</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%">
						${danTasMan.areaName}
					</td>
					<th width="15%">企业名称</th>
					<td width="35%">
						${danTasMan.companyName}
					</td>
				</tr>
				<tr>
			
					<th width="15%">任务名称</th>
					<td width="35%"><input name="danTasMan.taskName" style="width:60%" value="${danTasMan.taskName}" type="text"  maxlength="25" errormsg='任务名称必须是1到25位字符！' nullmsg='任务名称不能为空！' sucmsg='任务名称填写正确!'  datatype="*1-25"  type="text" maxlength="25"><font style='color:red'>*</font></td>
					</td>
					<th width="15%">巡查人员</th>
					<td width="35%">
						<cus:SelectOneTag property="danTasMan.checkPeopleId" style="width:60%" codeSql="select t.ROW_ID id,t.USER_NAME name from PATROL_USER t where t.COMPANY_ID= '${danTasMan.companyId}' and t.delflag = 0  " value="${danTasMan.checkPeopleId}" defaultText='请选择' datatype="*1-127" errormsg='巡查人员必须选择！' nullmsg='巡查人员必须选择！' sucmsg='巡查人员选择正确！'  maxlength="127" onchange="setName(this.options[this.selectedIndex].text);" /><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">巡查开始时间</th>
					<td width="35%"><input id="startDate"  style="width:60%" name="danTasMan.checkTime" value="<fmt:formatDate type='date' value='${danTasMan.checkTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${startDate}',maxDate:'#F{$dp.$D(\'endDate\')}'})" nullmsg='开始时间不能为空!' sucmsg='开始时间填写正确!'  datatype="*" ><font style='color:red'>*</font></td>
					<th width="15%">巡查结束时间</th>
					<td width="35%"><input id="endDate" style="width:60%" name="danTasMan.checkTimeEnd" value="<fmt:formatDate type='date' value='${danTasMan.checkTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})" nullmsg='开始时间不能为空!' sucmsg='开始时间填写正确!'  datatype="*" ><font style='color:red'>*</font></td>
				</tr>
				<tr >
				  	<th width="15%">巡检点名称</th>
					<td colspan="3" width="85%">
						<cus:hxcheckbox property="danTasMan.checkId" codeSql="select t.ROW_ID as id,t.DOT_NAME as checkName from dot t where t.delflag=0 and t.createUserID= '${danTasMan.createUserID}'" value="${danTasMan.checkId}" datatype="*" nullmsg='巡检点不能为空！' sucmsg='巡检点填写正确！' /><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
 					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

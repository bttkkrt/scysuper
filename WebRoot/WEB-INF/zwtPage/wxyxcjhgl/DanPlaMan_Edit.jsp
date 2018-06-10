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
		
		var vs=$("#checkTypeId").val();
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
			<font style='color:red'>*是否上报审核选择是后，需安监审核通过才会生成任务，一般情况下请选择否</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="danPlaManSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="danPlaMan.id" value="${danPlaMan.id}">
		<input type="hidden" name="danPlaMan.createTime" value="<fmt:formatDate type="both" value="${danPlaMan.createTime}" />">
		<input type="hidden" name="danPlaMan.updateTime" value="${danPlaMan.updateTime}">
		<input type="hidden" name="danPlaMan.createUserID" value="${danPlaMan.createUserID}">
		<input type="hidden" name="danPlaMan.updateUserID" value="${danPlaMan.updateUserID}">
		<input type="hidden" name="danPlaMan.deptId" value="${danPlaMan.deptId}">
		<input type="hidden" name="danPlaMan.delFlag" value="${danPlaMan.delFlag}">
		
		<input type="hidden" id="companyName" name="danPlaMan.companyName" value="${danPlaMan.companyName}">
		<input type="hidden" id="areaName" name="danPlaMan.areaName" value="${danPlaMan.areaName}">
		<input type="hidden"  name="danPlaMan.companyId" value="${danPlaMan.companyId}">
		<input type="hidden"  name="danPlaMan.areaId" value="${danPlaMan.areaId}">
		<input type="hidden" id="dangerName" name="danPlaMan.dangerName" value="${danPlaMan.dangerName}">
		<input type="hidden" id="checkRodeName" name="danPlaMan.checkRodeName" value="${danPlaMan.checkRodeName}">
		<input type="hidden"  id="checkPeopleName" name="danPlaMan.checkPeopleName" value="${danPlaMan.checkPeopleName}">
		<input type="hidden" name="danPlaMan.auditResult" value="待审核">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%">
						${danPlaMan.areaName}
					</td>
					<th width="15%">企业名称</th>
					<td width="35%">
						${danPlaMan.companyName}
					</td>
				</tr>
				<tr>
				  
				    <th width="15%">计划名称</th>
					<td width="35%"><input name="danPlaMan.planName" style="width:60%"  value="${danPlaMan.planName}" type="text" maxlength="25" errormsg='计划名称必须是1到25位字符！' nullmsg='计划名称不能为空！' sucmsg='计划名称填写正确!'  datatype="*1-25"  type="text" maxlength="25"><font style='color:red'>*</font></td>
					<th width="15%">是否上报审核</th>
					<td width="35%">
						<s:select  cssStyle="width:60%" name="danPlaMan.isAudit" list="#{'1':'否','0':'是'}" theme="simple" ></s:select>
					</td>
				</tr>
				<tr>
					<th width="15%">计划开始时间</th>
					<td width="35%"><input id="startDate" name="danPlaMan.planStartTime" style="width:60%"  value="<fmt:formatDate type='date' value='${danPlaMan.planStartTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${startDate}',maxDate:'#F{$dp.$D(\'endDate\')}'})" nullmsg='开始时间不能为空!' sucmsg='开始时间填写正确!'  datatype="*" ><font style='color:red'>*</font></td>
					<th width="15%">计划结束时间</th>
					<td width="35%"><input id="endDate" name="danPlaMan.planEndTime" style="width:60%"  value="<fmt:formatDate type='date' value='${danPlaMan.planEndTime}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})" nullmsg='结束时间不能为空!' sucmsg='结束时间填写正确!'  datatype="*" ><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">巡查频率</th>
					<td width="35%">
						<cus:SelectOneTag property="danPlaMan.checkFrequency"  style="width:60%" codeName="巡查频率" value="${danPlaMan.checkFrequency}" /></td>
					<th width="15%">巡查人员</th>
					<td width="35%">
						<cus:SelectOneTag property="danPlaMan.checkPeopleId" style="width:60%"  codeSql="select t.ROW_ID id,t.USER_NAME name from PATROL_USER t where t.COMPANY_ID= '${danPlaMan.companyId}' and t.delflag = 0  " value="${danPlaMan.checkPeopleId}" defaultText='请选择' datatype="*1-127" errormsg='巡查人员必须选择！' nullmsg='巡查人员必须选择！' sucmsg='巡查人员选择正确！'  maxlength="127" onchange="setName(this.options[this.selectedIndex].text);" /><font style='color:red'>*</font>
					</td>
				</tr>
				<tr>
				  	<th width="15%">巡检点</th>
					<td colspan="3" width="85%">
                 		<cus:hxcheckbox property="danPlaMan.checkId" codeSql="select t.ROW_ID as id,t.DOT_NAME as checkName from dot t where t.delflag=0 and t.createUserID= '${danPlaMan.createUserID}'" value="${danPlaMan.checkId}" datatype="*" nullmsg='巡检点不能为空！' sucmsg='巡检点填写正确！' /><font style='color:red'>*</font>
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

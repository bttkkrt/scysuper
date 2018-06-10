<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	  $(document).ready(function() {
		  uploadPic("uploadify1","${supTas.linkId}","jdjcjg1","jdjcjgfj1","fileQueue1");
		   uploadPicOnly("uploadify2","${supTas.linkId}","jdjcjg2","jdjcjgfj2","fileQueue2");
		});
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
		<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="supTasUpdate.action">
		<s:token />
		<input type="hidden" name="supTas.id" value="${supTas.id}">
		<input type="hidden" name="supTas.updateTime" value="${supTas.updateTime}">
		<input type="hidden" name="supTas.updateUserID" value="${supTas.updateUserID}">
		<input type="hidden" name="supTas.linkId" value="${supTas.linkId}">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所属网格</th>
					<td width="35%" >${supTas.areaName}</td>
					<th width="15%">企业名称</th>
					<td width="35%" >${supTas.companyName}</td>
				</tr>
				<tr>
				    <th width="15%">任务名称</th>
					<td width="35%" >${supTas.taskName}</td>
					<th width="15%">任务编号</th>
					<td width="35%" >${supTas.taskNum}</td>
				</tr>
				<tr>
				    <th width="15%">任务类型</th>
					<td width="35%" >${supTas.taskType}</td>
					<th width="15%">检查人员名称</th>
					<td width="35%" >${supTas.checkUsername}</td>
				</tr>
				<tr>
					<th width="15%">检查开始时间</th>
					<td width="35%"><input style="width:60%"  id="checkTime"  name="supTas.checkTime" value="<fmt:formatDate type='both' value='${supTas.checkTime}' />" type="text" datatype="*"  nullmsg='检查开始时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'checkTimeEnd\')}'})"><font style='color:red'>*</font></td>
					<th width="15%">检查结束时间</th>
					<td width="35%"><input style="width:60%"  id="checkTimeEnd" name="supTas.checkTimeEnd" value="<fmt:formatDate type='both' value='${supTas.checkTimeEnd}' />" type="text" datatype="*"  nullmsg='检查结束时间不能为空！' class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'checkTime\')}'})"><font style='color:red'>*</font></td>
				
				</tr>
				<tr>
				  	<th width="15%">巡查结果</th>
					<td colspan="3">
						<table>
							<tr>
								<th style="text-align: center;">巡查项</th>
								<th style="text-align: center;"> 结果</th>
								<th style="text-align: center;">备注</th>
							</tr>
							<c:forEach items="${supTasResultlist }" var="result" varStatus="vstatus">
							<tr>
								<td>${result.patMan.patrolName }<input type="hidden" name="supTasResultlist[${vstatus.index}].xcxId" value="${result.patMan.id }"/></td>
								<td>
									<input type="hidden" name="supTasResultlist[${vstatus.index}].id" value="${result.id }"/>
									<select name="supTasResultlist[${vstatus.index}].xcxResult" >
										<option value="合格" <c:if test="${result.xcxResult=='合格'}"> selected</c:if>>合格</option>
										<option value="不合格" <c:if test="${result.xcxResult=='不合格'}">selected</c:if>>不合格</option>
									</select>
								</td>
								<td><input type="text" name="supTasResultlist[${vstatus.index}].remark" value="${result.remark}" maxlength="127"/></td>
							</tr>
							</c:forEach>
							
						</table>
					</td>
                  
				</tr>
					
				<tr>
				  <th width="15%">检查记录</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="supTas.checkRecord" style="width:96%;height:60px">${supTas.checkRecord}</textarea></td>
				</tr>
				<tr>
				  <th width="15%">内容备注</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="supTas.remark" style="width:96%;height:60px">${supTas.remark}</textarea></td>
				</tr>
				
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="supTas.taskState == '未完成'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>	
						<a href="#" class="btn_01"  onclick="parent.close_win('win_supTas');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

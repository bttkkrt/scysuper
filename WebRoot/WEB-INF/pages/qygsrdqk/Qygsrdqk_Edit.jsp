<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="qygsrdqkSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="qygsrdqk.id" value="${qygsrdqk.id}">
		<input type="hidden" name="qygsrdqk.createTime" value="<fmt:formatDate type="both" value="${qygsrdqk.createTime}" />">
		<input type="hidden" name="qygsrdqk.updateTime" value="${qygsrdqk.updateTime}">
		<input type="hidden" name="qygsrdqk.createUserID" value="${qygsrdqk.createUserID}">
		<input type="hidden" name="qygsrdqk.updateUserID" value="${qygsrdqk.updateUserID}">
		<input type="hidden" name="qygsrdqk.deptId" value="${qygsrdqk.deptId}">
		<input type="hidden" name="qygsrdqk.delFlag" value="${qygsrdqk.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">月份</th>
					<td width="35%"><input name="qygsrdqk.monthTime" style="width: 60%" value="<fmt:formatDate type='both' value='${qygsrdqk.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">工伤申报总数</th>
					<td width="35%"><input name="qygsrdqk.gssbzs" style="width: 60%" value="${qygsrdqk.gssbzs}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">其中个人申报</th>
					<td width="35%"><input name="qygsrdqk.qzgrsb" style="width: 60%" value="${qygsrdqk.qzgrsb}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>不同认定依据分类</strong></td>
				</tr>
				<tr>
					<th width="15%">14.1</th>
					<td width="35%"><input name="qygsrdqk.btrd1" value="${qygsrdqk.btrd1}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
			
					<th width="15%">14.2</th>
					<td width="35%"><input name="qygsrdqk.btrd2" value="${qygsrdqk.btrd2}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					</tr>
				<tr>
					<th width="15%">14.3</th>
					<td width="35%"><input name="qygsrdqk.btrd3" value="${qygsrdqk.btrd3}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				
					<th width="15%">14.4</th>
					<td width="35%"><input name="qygsrdqk.btrd4" value="${qygsrdqk.btrd4}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">14.5</th>
					<td width="35%"><input name="qygsrdqk.btrd5" value="${qygsrdqk.btrd5}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				
					<th width="15%">14.6</th>
					<td width="35%"><input name="qygsrdqk.btrd6" value="${qygsrdqk.btrd6}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">15.1</th>
					<td width="35%"><input name="qygsrdqk.btrd7" value="${qygsrdqk.btrd7}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				
					<th width="15%">15.2</th>
					<td width="35%"><input name="qygsrdqk.btrd8" value="${qygsrdqk.btrd8}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">15.3</th>
					<td width="35%"><input name="qygsrdqk.btrd9" value="${qygsrdqk.btrd9}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">轻伤</th>
					<td width="35%"><input name="qygsrdqk.qdss" value="${qygsrdqk.qdss}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">重伤</th>
					<td width="35%"><input name="qygsrdqk.zdss" value="${qygsrdqk.zdss}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">工亡</th>
					<td width="35%"><input name="qygsrdqk.gzsw" value="${qygsrdqk.gzsw}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">生产</th>
					<td width="35%"><input name="qygsrdqk.sclb" value="${qygsrdqk.sclb}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">交通</th>
					<td width="35%"><input name="qygsrdqk.jtlb" value="${qygsrdqk.jtlb}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">其他</th>
					<td width="35%"><input name="qygsrdqk.qtlb" value="${qygsrdqk.qtlb}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_qygsrdqk');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

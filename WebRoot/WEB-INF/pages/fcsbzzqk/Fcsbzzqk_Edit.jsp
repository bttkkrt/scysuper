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
	<form name="myform1" method="post" enctype="multipart/form-data" action="fcsbzzqkSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="fcsbzzqk.id" value="${fcsbzzqk.id}">
		<input type="hidden" name="fcsbzzqk.createTime" value="<fmt:formatDate type="both" value="${fcsbzzqk.createTime}" />">
		<input type="hidden" name="fcsbzzqk.updateTime" value="${fcsbzzqk.updateTime}">
		<input type="hidden" name="fcsbzzqk.createUserID" value="${fcsbzzqk.createUserID}">
		<input type="hidden" name="fcsbzzqk.updateUserID" value="${fcsbzzqk.updateUserID}">
		<input type="hidden" name="fcsbzzqk.deptId" value="${fcsbzzqk.deptId}">
		<input type="hidden" name="fcsbzzqk.delFlag" value="${fcsbzzqk.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">年度</th>
					<td width="35%"><input name="fcsbzzqk.yearTime" style="width: 60%" value="<fmt:formatDate type='both' value='${fcsbzzqk.yearTime}' pattern="yyyy"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					
					<td width="35%"><s:select  id="areaName" cssStyle="width: 60%" name="fcsbzzqk.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','国际商务区':'国际商务区','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">原有企业</th>
					<td width="35%"><input name="fcsbzzqk.yyqy" value="${fcsbzzqk.yyqy}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">现有企业</th>
					<td width="35%"><input name="fcsbzzqk.xyqy" value="${fcsbzzqk.xyqy}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">当年新增</th>
					<td width="35%"><input name="fcsbzzqk.dnxz" value="${fcsbzzqk.dnxz}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">当年关闭</th>
					<td width="35%"><input name="fcsbzzqk.dngb" value="${fcsbzzqk.dngb}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">当年取缔</th>
					<td width="35%"><input name="fcsbzzqk.dnqd" value="${fcsbzzqk.dnqd}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>当年累计检查情况</strong></td>
				</tr>
				<tr>
					<th width="15%">省级检查</th>
					<td width="35%"><input name="fcsbzzqk.sjjc" value="${fcsbzzqk.sjjc}" style="width: 60%" type="text" maxlength="127"></td>
				
					<th width="15%">市级检查</th>
					<td width="35%"><input name="fcsbzzqk.shjjc" value="${fcsbzzqk.shjjc}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">区级检查</th>
					<td width="35%"><input name="fcsbzzqk.qjjc" value="${fcsbzzqk.qjjc}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				  <td width="15%" colspan="4" style="text-align:center"><strong>视频监控</strong></td>
				</tr>
				<tr>
					<th width="15%">已安装</th>
					<td width="35%"><input name="fcsbzzqk.yaz" value="${fcsbzzqk.yaz}" type="text" style="width: 60%" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">当年计划安装数</th>
					<td width="35%"><input name="fcsbzzqk.dnjhazs" value="${fcsbzzqk.dnjhazs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
				    <th width="15%">正在施工数 </th>
					<td width="35%"><input name="fcsbzzqk.zzsgs" value="${fcsbzzqk.zzsgs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">当年安装数 </th>
					<td width="35%"><input name="fcsbzzqk.dnazs" value="${fcsbzzqk.dnazs}" style="width: 60%" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
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
						<a href="#" class="btn_01"  onclick="parent.close_win('win_fcsbzzqk');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

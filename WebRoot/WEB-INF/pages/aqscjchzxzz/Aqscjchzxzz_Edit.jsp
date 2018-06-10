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
	<form name="myform1" method="post" enctype="multipart/form-data" action="aqscjchzxzzSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="aqscjchzxzz.id" value="${aqscjchzxzz.id}">
		<input type="hidden" name="aqscjchzxzz.createTime" value="<fmt:formatDate type="both" value="${aqscjchzxzz.createTime}" />">
		<input type="hidden" name="aqscjchzxzz.updateTime" value="${aqscjchzxzz.updateTime}">
		<input type="hidden" name="aqscjchzxzz.createUserID" value="${aqscjchzxzz.createUserID}">
		<input type="hidden" name="aqscjchzxzz.updateUserID" value="${aqscjchzxzz.updateUserID}">
		<input type="hidden" name="aqscjchzxzz.deptId" value="${aqscjchzxzz.deptId}">
		<input type="hidden" name="aqscjchzxzz.delFlag" value="${aqscjchzxzz.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">月份</th>
					<td width="35%"><input name="aqscjchzxzz.monthTime"  style="width:60%" value="<fmt:formatDate type='both' value='${aqscjchzxzz.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
					<th width="15%">区域</th>
					
					<td width="35%"><s:select  id="areaName"   cssStyle="width:60%" name="aqscjchzxzz.areaName" list="#{'':'请选择','一中队':'一中队','二中队':'二中队','三中队':'三中队','娄葑街道':'娄葑街道','斜塘街道':'斜塘街道','唯亭街道':'唯亭街道','胜浦街道':'胜浦街道'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">监管企业数</th>
					<td width="35%"><input name="aqscjchzxzz.jgqys"  style="width:60%" value="${aqscjchzxzz.jgqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">检查企业目标</th>
					<td width="35%"><input name="aqscjchzxzz.jcqymb"  style="width:60%" value="${aqscjchzxzz.jcqymb}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">检查企业数</th>
					<td width="35%"><input name="aqscjchzxzz.jcqys"  style="width:60%" value="${aqscjchzxzz.jcqys}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">一般隐患排查数</th>
					<td width="35%"><input name="aqscjchzxzz.ybyhpcs"  style="width:60%" value="${aqscjchzxzz.ybyhpcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				
					<th width="15%">一般隐患已整治数</th>
					<td width="35%"><input name="aqscjchzxzz.ybyhyzzs"  style="width:60%" value="${aqscjchzxzz.ybyhyzzs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">重大隐患排查数</th>
					<td width="35%"><input name="aqscjchzxzz.zdyhpcs"  style="width:60%" value="${aqscjchzxzz.zdyhpcs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				
					<th width="15%">重大隐患已整治数</th>
					<td width="35%"><input name="aqscjchzxzz.zdyhyzzs"  style="width:60%" value="${aqscjchzxzz.zdyhyzzs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">打击非法违法行为</th>
					<td width="35%"><input name="aqscjchzxzz.djffwfxw"  style="width:60%" value="${aqscjchzxzz.djffwfxw}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
			
					<th width="15%">整治违规违法行为</th>
					<td width="35%"><input name="aqscjchzxzz.zzwgwfxw"  style="width:60%" value="${aqscjchzxzz.zzwgwfxw}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
					<th width="15%">处罚总起数（违法）</th>
					<td width="35%"><input name="aqscjchzxzz.cfzqs"  style="width:60%" value="${aqscjchzxzz.cfzqs}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">处罚总起数（事故）</th>
					<td width="35%"><input name="aqscjchzxzz.cfzqssg"  style="width:60%" value="${aqscjchzxzz.cfzqssg}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">已结案事故（违法）</th>
					<td width="35%"><input name="aqscjchzxzz.yjasg"  style="width:60%" value="${aqscjchzxzz.yjasg}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">已结案事故（事故）</th>
					<td width="35%"><input name="aqscjchzxzz.yjasgsg"  style="width:60%" value="${aqscjchzxzz.yjasgsg}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
					<th width="15%">处罚金额（违法）</th>
					<td width="35%"><input name="aqscjchzxzz.cfje"  style="width:60%" value="${aqscjchzxzz.cfje}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<th width="15%">处罚金额（事故）</th>
					<td width="35%"><input name="aqscjchzxzz.cfjesg"  style="width:60%" value="${aqscjchzxzz.cfjesg}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">追究刑事责任</th>
					<td width="35%"><input name="aqscjchzxzz.zjxszr"  style="width:60%" value="${aqscjchzxzz.zjxszr}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_aqscjchzxzz');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

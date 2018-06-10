<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
		function clearNoNum(event,obj){ 
        //响应鼠标事件，允许左右方向键移动 
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
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
			<font style='color:red'>*若提取比较频繁，则可将一段时间费用累加后填写一次，在用途中详细描述这笔费用的具体用途</font>
	<form name="myform1" method="post" enctype="multipart/form-data" action="secProFeeExtSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="secProFeeExt.id" value="${secProFeeExt.id}">
		<input type="hidden" name="secProFeeExt.createTime" value="<fmt:formatDate type="both" value="${secProFeeExt.createTime}" />">
		<input type="hidden" name="secProFeeExt.updateTime" value="${secProFeeExt.updateTime}">
		<input type="hidden" name="secProFeeExt.createUserID" value="${secProFeeExt.createUserID}">
		<input type="hidden" name="secProFeeExt.updateUserID" value="${secProFeeExt.updateUserID}">
		<input type="hidden" name="secProFeeExt.deptId" value="${secProFeeExt.deptId}">
		<input type="hidden" name="secProFeeExt.delFlag" value="${secProFeeExt.delFlag}">
		
			<table width="100%" border="0">
				
				<tr>
					<th width="15%">提取费用</th>
					<td width="35%"><input name="secProFeeExt.feeExtractFee" style="width:60%"value="${secProFeeExt.feeExtractFee}" type="text" datatype="*1-127" errormsg='提取费用必须数字！' nullmsg='提取费用不能为空！' sucmsg='提取费用填写正确！'  maxlength="127" onKeyUp="clearNoNum(event,this)"><font style='color:red'>*</font></td>
					<th width="15%">提取时间</th>
					<td width="35%"><input name="secProFeeExt.feeExtractTime" style="width:60%" value="<fmt:formatDate type='date' value='${secProFeeExt.feeExtractTime}' pattern="yyyy-MM-dd"/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" datatype="*1-127"  nullmsg='提取时间不能为空！' sucmsg='提取时间填写正确！'  maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="15%">发票号</th>
					<td width="35%"><input name="secProFeeExt.fpNum" style="width:60%" value="${secProFeeExt.fpNum}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">用途</th>
					<td width="35%" colspan="3"><textarea onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" name="secProFeeExt.feeExtractRemark" style="width:96%;height:60px">${secProFeeExt.feeExtractRemark}</textarea></td>
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

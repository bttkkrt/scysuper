<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
		
        
        function changDiv(obj)
        {
        	if(obj == 1)
        	{
        		document.getElementById('div1').style.display = "";
        	}
        	else
        	{
        		document.getElementById('div1').style.display = "none";
        	}
        }
        
        function showSgInfo(obj)
        {
        	if(obj == 1)
        	{
        		document.getElementById('sgdiv1').style.display = "";
        		document.getElementById('sgdiv2').style.display = "";
        		document.getElementById('sgdiv3').style.display = "";
        	}
        	else
        	{
        		document.getElementById('sgdiv1').style.display = "none";
        		document.getElementById('sgdiv2').style.display = "none";
        		document.getElementById('sgdiv3').style.display = "none";
        	}
        }
	</script>
</head>

<body validform="true">
   <div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="caseInfoNrsxxwSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="caseInfo.id" value="${caseInfo.id}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">失信等级</th>
					<td width="35%"><cus:SelectOneTag style="width:135px;" property="dishonesty.creditRating" defaultText='请选择' codeName="失信等级" value="${dishonesty.creditRating}" /></td>
					<th width="15%">是否公示</th>
					<td width="35%"><cus:SelectOneTag style="width:135px;" property="dishonesty.isPublic" defaultText='请选择' codeName="是或否" value="${dishonesty.isPublic}" /></td>
				</tr>
				<tr>
					<th width="15%">公示起日期</th>
					<td width="35%"><input id="publicStartDate" name="dishonesty.publicStartDate" value="<fmt:formatDate type='both'  pattern="yyyy-MM-dd" value='${dishonesty.publicStartDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'publicEndDate\')}'})"></td>
					<th width="15%">公示止日期</th>
					<td width="35%"><input id="publicEndDate" name="dishonesty.publicEndDate" value="<fmt:formatDate type='both' pattern="yyyy-MM-dd"  value='${dishonesty.publicEndDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'publicStartDate\')}'})"></td>
				</tr>
				<tr>
					<th width="15%">执行情况</th>
					<td width="85%" colspan="3">
						<textarea name="dishonesty.implementation" style="width:96%;height:120px"  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${dishonesty.implementation}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

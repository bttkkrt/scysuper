<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>请假申请</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.leaveApplyForm,3)){
				document.leaveApplyForm.action="applyLeaveInfo.action";
				document.leaveApplyForm.submit();
			}
		}
	</script>
</head>
<body>
	<div class="page_dialog">
		<form name="leaveApplyForm" method="post">
			<div class="inner6px">
				<div class="cell">
					<table width="100%">
						<tr>
							<th width="15%">请假开始时间</th>
							<td width="35%"><input name="leaveInfo.starttime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
							<th width="15%">请假结束时间</th>
							<td width="35%"><input name="leaveInfo.endtime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
						</tr>
						<tr>
							<th width="15%">请假类型</th>
							<td width="35%"><cus:SelectOneTag property="leaveInfo.leaveType" defaultText='请选择' codeName="请假类型"/></td>
						</tr>
						<tr>
							<th width="15%" colspan="1">请假原因</th>
							<td colspan="3"><textarea style="width:95%;height:200px;" id="leaveReason" name="leaveInfo.reason"></textarea></td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="btn_area_setc">
									<a href="###" class="btn_01" onclick="save()">提交<b></b></a>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
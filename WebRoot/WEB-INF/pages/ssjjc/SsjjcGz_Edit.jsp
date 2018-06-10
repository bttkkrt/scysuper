<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>双随机检查规则设定</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	function selectAllLd(obj)
	{
		var ldids = document.getElementsByName('ssjjcGz.ldids');
		if(obj.checked)
		{
			for(var i = 0; i < ldids.length; i++)
			{
				ldids[i].checked = true;
			}
		}
		else
		{	
			for(var i = 0; i < ldids.length; i++)
			{
				ldids[i].checked = false;
			}
		}
	}
	
	function selectAllDy(obj)
	{
		var ryids = document.getElementsByName('ssjjcGz.ryids');
		if(obj.checked)
		{
			for(var i = 0; i < ryids.length; i++)
			{
				ryids[i].checked = true;
			}
		}
		else
		{	
			for(var i = 0; i < ryids.length; i++)
			{
				ryids[i].checked = false;
			}
		}
	}
	
	
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="ssjjcGzSave.action">
		<s:token />
		<input type="hidden" name="ssjjcGz.id" value="${ssjjcGz.id}"/> 
			<table width="100%" border="0">
				<tr>
					<th width="15%">是否按照网格随机</th>
					<td width="85%" colspan="3"><s:select name="ssjjcGz.ifWg" list="#{'1':'是','0':'否'}" theme="simple" /></td>
				</tr>
				<tr>
					<th width="15%">参与带队领导</th>
					<td width="85%" colspan="3">
						<input type="checkbox" onclick="selectAllLd(this)">全选&nbsp;
						<cus:hxcheckbox property="ssjjcGz.ldids" codeSql="select t.row_id,t.display_name from users t where t.del_flag =  0 and t.dept_code in ('002001001','002001002','002001003')" value="${ssjjcGz.ldids}" maxlength="127"/></td>
				</tr>
				<tr>
					<th width="15%">参与中队人员</th>
					<td width="85%" colspan="3">
					<input type="checkbox" onclick="selectAllDy(this)">全选&nbsp;
					<cus:hxcheckbox property="ssjjcGz.ryids" codeSql="select t.row_id,t.display_name from users t where t.del_flag =  0 and len(t.dept_code) = 12 and t.dept_code != '002001004004'" value="${ssjjcGz.ryids}" maxlength="127"/></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit" >保存<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

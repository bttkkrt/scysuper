<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">所在区域</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${secProMaj.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%" >${secProMaj.companyName}</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%" >${secProMaj.majordomoName}</td>
					<th width="15%">性别</th>
					<td width="35%" ><cus:hxlabel codeName="性别" itemValue="${secProMaj.majordomoSex}" /></td>
				</tr>
				<tr>
					<th width="15%">国籍</th>
					<td width="35%" >${secProMaj.majordomoNationnality}</td>
					<th width="15%">最高学历</th>
					<td width="35%" ><cus:hxlabel codeName="学历" itemValue="${secProMaj.majordomoHighestSchool}" /></td>
				</tr>
				<tr>
					<th width="15%">最高学位</th>
					<td width="35%" ><cus:hxlabel codeName="学位" itemValue="${secProMaj.majordomoHighestDegree}" /></td>
					<th width="15%">毕业院校</th>
					<td width="35%" >${secProMaj.majordomoSchool}</td>
				</tr>
				<tr>
					<th width="15%">专业</th>
					<td width="35%" >${secProMaj.majordomoSpecialized}</td>
					<th width="15%">职称</th>
					<td width="35%" >${secProMaj.majordomoTitle}</td>
				</tr>
				<tr>
					<th width="15%">安全生产管理员资格证号</th>
					<td width="35%" >${secProMaj.majordomoSpecializedNum}</td>
					<th width="15%">进入本单位日期</th>
					<td width="35%" ><fmt:formatDate type="both"  pattern="yyyy-MM-dd" value="${secProMaj.majordomoDutyDate}" /></td>
				</tr>
				<tr>
					<th width="15%">从事本岗位时间</th>
					<td width="35%" ><fmt:formatDate type="both"  pattern="yyyy-MM-dd" value="${secProMaj.majordomoPostDate}" /></td>
					<th width="15%">联系电话1</th>
					<td width="35%" >${secProMaj.majordomoPhone}</td>
				</tr>
				<tr>
					<th width="15%">联系电话2</th>
					<td width="35%" >${secProMaj.majordomoPhone2}</td>
					<th width="15%">电子邮箱</th>
					<td width="35%" >${secProMaj.majordomoEmail}</td>
				</tr>
				<tr>
					<th width="15%">住址</th>
					<td width="96%" colspan="3" >${secProMaj.majordomoAddress}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="35%" colspan="3" ><textarea readOnly name="secProMaj.majordomoRemark" style="width:96%;height:60px">${secProMaj.majordomoRemark}</textarea></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_secProMaj');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>

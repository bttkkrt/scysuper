<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
	
		$(function(){
			var tab=document.getElementById("tab");
			var name=""; 
			for(var i=0,j=0;i <tab.rows.length;i++,j++) {
				if(name==tab.rows[i].cells[0].innerHTML) {
					tab.rows[i].deleteCell(0);
					tab.rows[i].deleteCell(3);
				} else {
					name=tab.rows[i].cells[0].innerHTML;
					tab.rows[i].cells[0].setAttribute("rowspan",Number(tab.rows[i].cells[5].innerHTML));
					tab.rows[i].cells[4].setAttribute("rowspan",Number(tab.rows[i].cells[5].innerHTML));
					//tab.rows[i].cells[0].rowSpan=Number(tab.rows[i].cells[5].innerHTML);
					//alert(7);
					//tab.rows[i].cells[4].rowSpan=Number(tab.rows[i].cells[5].innerHTML);
				} 
			} 
			
			var num1=document.getElementById("num1").innerHTML;
			var num2=document.getElementById("num2").innerHTML;
			var num3=document.getElementById("num3").innerHTML;
			var num4=document.getElementById("num4").innerHTML;
			var num5=document.getElementById("num5").innerHTML;
			var t=Number(num1)+Number(num2)+Number(num3)+Number(num4)+Number(num5);
			document.getElementById("total").innerHTML=t;
		});
	</script>
	<script > </script >
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%"所在区域称</th>
					<td width="35%" ><cus:hxlabel codeName="企业属地" itemValue="${occHazBas.areaId}" /></td>
					<th width="15%">企业名称</th>
					<td width="35%">${occHazBas.companyName}</td>
				</tr>
				<tr>
					<th width="15%">粉尘类</th>
					<td width="35%" ><cus:hxlabel codeName="有或无" itemValue="${occHazBas.ifDust}" /></td>
					<th width="15%">粉尘类接触人数</th>
					<td width="35%"  id="num1">${occHazBas.dustContactNumber}</td>
				</tr>
				<tr>
					<th width="15%">化学物质类</th>
					<td width="35%" ><cus:hxlabel codeName="有或无" itemValue="${occHazBas.ifChemical}" /></td>
					<th width="15%">化学物质类接触人数</th>
					<td width="35%"  id="num2">${occHazBas.chemicalContactNumber}</td>
				</tr>
				<tr>
					<th width="15%">物理因素类</th>
					<td width="35%" ><cus:hxlabel codeName="有或无" itemValue="${occHazBas.ifPhysical}" /></td>
					<th width="15%">物理因素类接触人数</th>
					<td width="35%"  id="num3">${occHazBas.physicalContactNumber}</td>
				</tr>
				<tr>
					<th width="15%">放射性物质类</th>
					<td width="35%" ><cus:hxlabel codeName="有或无" itemValue="${occHazBas.ifRadioactivity}" /></td>
					<th width="15%">放射性物质类接触人数</th>
					<td width="35%"  id="num4">${occHazBas.radioactivityContactNumber}</td>
				</tr>
				<tr>
					<th width="15%">其他</th>
					<td width="35%" ><cus:hxlabel codeName="有或无" itemValue="${occHazBas.ifOther}" /></td>
					<th width="15%">其他接触人数</th>
					<td width="35%"  id="num5">${occHazBas.otherContactNumber}</td>
				</tr>
				<tr>
					<th width="15%">接触职业病危害总人数</th>
					<td width="35%" id="total"></td>
					<th></th>
					<td></td>
					<!-- <th width="15%">合计</th>
					<td width="35%" >${occHazBas.total}</td> -->
				</tr>
				<tr>
				</tr>
				<tr style="display:none">
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_occHazBas');">关闭<b></b></a>
					</td>
				</tr>
			</table>
			<br/>
			<p style="text-align:center;font-size:22px;">职业病危害因素分布情况</p>
			<table style="text-align:center" id="tab">
				<tr >
					<th style="text-align:center">作业场所名称</th>
					<th style="text-align:center">职业病危害因素名称</th>
					<th style="text-align:center">现场浓度</th>
					<th style="text-align:center">接触人数（可重复）</th>
					<th style="text-align:center" >接触人数（不可重复）</th>
					<th style="display:none">hidden</th>
				</tr>
				<c:forEach items="${fbqkList }" var="f" varStatus="s">
				<tr >
					<td>${f.WORK_PLACE }</td>
					<td>${f.OCCUPATIONAL_DISEASE_NAME }</td>
					<td>${f.FIELD_CONCENTRATION }</td>
					<td>${f.CONTACT_NUMBER  }</td>
					<td>${f.CONTACT_NUM }</td>
					<td style="display:none">${f.COU }</td>
				</tr>
				</c:forEach>
			</table>
		</div></div></div>
	</form>
</body>
</html>

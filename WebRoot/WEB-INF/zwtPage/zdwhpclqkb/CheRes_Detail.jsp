<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="18%">所在区域</th>
					<td width="32%" ><cus:hxlabel codeName="企业属地" itemValue="${cheRes.areaId}" /></td>
					<th width="18%">企业名称</th>
					<td width="32%" >${cheRes.companyName}</td>
				</tr>
				<tr>
					<th width="18%">危险化学品名称</th>
					<td width="32%" >${cheRes.dangerousChemicalName}</td>
					<th width="18%">危规号</th>
					<td width="32%" >${cheRes.riskGauge}</td>
				</tr>
				<tr>
					<th width="18%">UN号</th>
					<td width="32%" >${cheRes.unNumber}</td>
					<th width="18%">年使用量</th>
					<td width="32%" >${cheRes.annualUsage}</td>
				</tr>
				<tr>
					<th width="18%">最大贮存量</th>
					<td width="32%" >${cheRes.maximumStorageCapacity}</td>
					<th width="18%">贮存方式</th>
					<td width="32%" >${cheRes.storageMode}</td>
				</tr>
				<tr>
					<th width="18%">贮存地点</th>
					<td width="32%" >${cheRes.storageLocation}</td>
					<th width="18%">包装方式</th>
					<td width="32%" >${cheRes.packing}</td>
				</tr>
				<tr>
					<th width="18%">是否易制毒化学品</th>
					<td width="32%" ><cus:hxlabel codeName="是或否" itemValue="${cheRes.isChemical}" /></td>
					<th width="18%">是否重点监管化学品</th>
					<td width="32%" ><cus:hxlabel codeName="是或否" itemValue="${cheRes.isRegulatorChemical}" /></td>
				</tr>
				<tr>
					<th width="18%">是否易制爆化学品</th>
					<td width="32%" ><cus:hxlabel codeName="是或否" itemValue="${cheRes.isExplosiveChemical}" /></td>
					<th width="18%">是否剧毒化学品</th>
					<td width="32%" ><cus:hxlabel codeName="是或否" itemValue="${cheRes.isToxicChemical}" /></td>
				</tr>
				<tr>
					<th width="18%">现有储存量</th>
					<td width="32%" >${cheRes.storage}</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>

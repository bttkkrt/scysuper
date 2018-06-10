<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%">${zjjtzsbzyry.mc}</td>
					<th width="15%">性别</th>
					<td width="35%">
						${zjjtzsbzyry.sex}
					</td>
				</tr>
				<tr>	
					<th width="15%">乡镇</th>
					<td width="35%">
						 ${zjjtzsbzyry.szzname}
					</td>
					<th width="15%">文化程度</th>
					<td width="35%">
						${zjjtzsbzyry.xl}
					</td>
				</tr>
				<tr>
					<th width="15%">身份证</th>
					<td width="35%">${zjjtzsbzyry.sfz}</td>
					<th width="15%">联系电话</th>
					<td width="35%">${zjjtzsbzyry.lxdh}</td>
				</tr>
				<tr>	
					<th width="15%">证书类型</th>
					<td width="35%">
						<s:select disabled="true" id="xm" listKey="key" listValue="value"  theme="simple" list="#{'A3':'锅炉压力容器压力管道安全管理','A4':'电梯安全管理','A5':'起重机械安全管理','A8':'场（厂）内专用机动车辆安全管理','G1':'一级锅炉司炉','G2':'二级锅炉司炉','G4':'一级锅炉水质处理','G5':'二级锅炉水质处理（电站锅炉除外）','R1':'固定式压力容器操作','T3':'电梯司机','Q3':'起重机械指挥','Q4':'桥门式起重机司机','Q6':'门座式起重机司机','Q8':'流动式起重机司机','Q10':'机械式停车设备司机','N2':'叉车司机','N5':'蓄电池观光车司机'}" name="zjjtzsbzyry.xm" value="{zjjtzsbzyry.xm}"/>
					</td>
					<th width="15%">用人单位</th>
					<td width="35%">${zjjtzsbzyry.pydw}</td>
				</tr>
				<tr>
					<th width="15%">单位地址</th>
					<td width="35%">${zjjtzsbzyry.dwdz}</td>
					<th width="15%">单位联系电话</th>
					<td width="35%">${zjjtzsbzyry.dwlxdh}</td>
				</tr>
				<tr>
					<th width="15%">首次领证日期</th>
					<td width="35%">
						${zjjtzsbzyry.pzrq}
					</td>
					<th width="15%">当前证书有效期</th>
					<td width="35%">
						${zjjtzsbzyry.yxrq}
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="zjjtzsbzyrySave.action";
				document.myform1.submit();
			}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zjjtzsbzyry.id" value="${zjjtzsbzyry.id}">
		<input type="hidden" name="zjjtzsbzyry.createTime" value="<fmt:formatDate type="both" value="${zjjtzsbzyry.createTime}" />">
		<input type="hidden" name="zjjtzsbzyry.updateTime" value="${zjjtzsbzyry.updateTime}">
		<input type="hidden" name="zjjtzsbzyry.createUserID" value="${zjjtzsbzyry.createUserID}">
		<input type="hidden" name="zjjtzsbzyry.updateUserID" value="${zjjtzsbzyry.updateUserID}">
		<input type="hidden" name="zjjtzsbzyry.deptId" value="${zjjtzsbzyry.deptId}">
		<input type="hidden" name="zjjtzsbzyry.delFlag" value="${zjjtzsbzyry.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="zjjtzsbzyry.mc" value="${zjjtzsbzyry.mc}" type="text" dataType="Require" msg="此项为必填" maxlength="4000"><font color="red">*</font></td>
					<th width="15%">性别</th>
					<td width="35%">
						<s:select listKey="key" listValue="value"  theme="simple" list="#{'男':'男','女':'女'}" name="zjjtzsbzyry.sex" value="{zjjtzsbzyry.sex}"/>
					</td>
				</tr>
				<tr>	
					<th width="15%">乡镇</th>
					<td width="35%">
						<s:select id="szzname" listKey="key" listValue="value"  theme="simple" list="#{'高铁新城':'高铁新城','度假区':'度假区','开发区':'开发区','北桥街道':'北桥街道'
						,'黄桥街道':'黄桥街道','太平街道':'太平街道','元和街道':'元和街道','阳澄湖镇':'阳澄湖镇','渭塘镇':'渭塘镇','黄埭镇':'黄埭镇','望亭镇':'望亭镇','其它':'其它'}" name="zjjtzsbzyry.szzname" value="{zjjtzsbzyry.szzname}"/>
					</td>
					<th width="15%">文化程度</th>
					<td width="35%">
						<s:select listKey="key" listValue="value"  theme="simple" list="#{'初中以下':'初中以下','初中以上':'初中以上'
						,'初中':'初中','技校':'技校','中专':'中专','中技':'中技','职高':'职高','高中':'高中','大专':'大专','本科':'本科','硕士':'硕士','博士':'博士'}" name="zjjtzsbzyry.xl" value="{zjjtzsbzyry.xl}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">身份证</th>
					<td width="35%"><input name="zjjtzsbzyry.sfz" value="${zjjtzsbzyry.sfz}" type="text" maxlength="4000"></td>
					<th width="15%">联系电话</th>
					<td width="35%"><input name="zjjtzsbzyry.lxdh" value="${zjjtzsbzyry.lxdh}" type="text" maxlength="255"></td>
				</tr>
				<tr>	
					<th width="15%">证书类型</th>
					<td width="35%">
						<s:select id="xm" listKey="key" listValue="value"  theme="simple" list="#{'A3':'锅炉压力容器压力管道安全管理','A4':'电梯安全管理','A5':'起重机械安全管理','A8':'场（厂）内专用机动车辆安全管理','G1':'一级锅炉司炉','G2':'二级锅炉司炉','G4':'一级锅炉水质处理','G5':'二级锅炉水质处理（电站锅炉除外）','R1':'固定式压力容器操作','T3':'电梯司机','Q3':'起重机械指挥','Q4':'桥门式起重机司机','Q6':'门座式起重机司机','Q8':'流动式起重机司机','Q10':'机械式停车设备司机','N2':'叉车司机','N5':'蓄电池观光车司机'}" name="zjjtzsbzyry.xm" value="{zjjtzsbzyry.xm}"/>
					</td>
					<th width="15%">用人单位</th>
					<td width="35%"><input name="zjjtzsbzyry.pydw" value="${zjjtzsbzyry.pydw}" type="text" maxlength="4000"></td>
				</tr>
				<tr>
					<th width="15%">单位地址</th>
					<td width="35%"><input name="zjjtzsbzyry.dwdz" value="${zjjtzsbzyry.dwdz}" type="text"  maxlength="4000"></td>
					<th width="15%">单位联系电话</th>
					<td width="35%"><input name="zjjtzsbzyry.dwlxdh" value="${zjjtzsbzyry.dwlxdh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">首次领证日期</th>
					<td width="35%">
						<input id="pzrq" name="zjjtzsbzyry.pzrq"  value='${zjjtzsbzyry.pzrq}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
					<th width="15%">当前证书有效期</th>
					<td width="35%">
						<input id="yxrq" name="zjjtzsbzyry.yxrq"  value='${zjjtzsbzyry.yxrq}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

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
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="cheResSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="cheRes.id" value="${cheRes.id}">
		<input type="hidden" name="cheRes.createTime" value="<fmt:formatDate type="both" value="${cheRes.createTime}" />">
		<input type="hidden" name="cheRes.updateTime" value="${cheRes.updateTime}">
		<input type="hidden" name="cheRes.createUserID" value="${cheRes.createUserID}">
		<input type="hidden" name="cheRes.updateUserID" value="${cheRes.updateUserID}">
		<input type="hidden" name="cheRes.deptId" value="${cheRes.deptId}">
		<input type="hidden" name="cheRes.delFlag" value="${cheRes.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="18%">危险化学品名称</th>
					<td width="32%"><input name="cheRes.dangerousChemicalName" style="width:60%" value="${cheRes.dangerousChemicalName}" errormsg='危险化学品名称必须是1到127位字符！' nullmsg='危险化学品名称不能为空！' sucmsg='危险化学品名称填写正确！'  datatype="*1-127"  type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="18%">危规号</th>
					<td width="32%"><input name="cheRes.riskGauge" style="width:60%" value="${cheRes.riskGauge}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="18%">UN号</th>
					<td width="32%"><input name="cheRes.unNumber" style="width:60%" value="${cheRes.unNumber}"  errormsg='UN号必须是1到127位字符！' nullmsg='UN号不能为空！' sucmsg='UN号填写正确！'  datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="18%">年使用量</th>
					<td width="32%"><input name="cheRes.annualUsage" style="width:60%" value="${cheRes.annualUsage}"  errormsg='年使用量必须是1到127位字符！' nullmsg='年使用量不能为空！' sucmsg='年使用量填写正确！'  datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="18%">最大贮存量</th>
					<td width="32%"><input name="cheRes.maximumStorageCapacity" style="width:60%" value="${cheRes.maximumStorageCapacity}"  errormsg='最大贮存量必须是1到127位字符！' nullmsg='最大贮存量不能为空！' sucmsg='最大贮存量填写正确！'  datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="18%">贮存方式</th>
					<td width="32%"><input name="cheRes.storageMode" style="width:60%" value="${cheRes.storageMode}"  errormsg='贮存方式必须是1到127位字符！' nullmsg='贮存方式不能为空！' sucmsg='贮存方式填写正确！'  datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="18%">贮存地点</th>
					<td width="32%"><input name="cheRes.storageLocation" style="width:60%" value="${cheRes.storageLocation}"  errormsg='贮存地点必须是1到127位字符！' nullmsg='贮存地点不能为空！' sucmsg='贮存地点填写正确！'  datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
					<th width="18%">包装方式</th>
					<td width="32%"><input name="cheRes.packing" style="width:60%" value="${cheRes.packing}"  errormsg='包装方式必须是1到127位字符！' nullmsg='包装方式不能为空！' sucmsg='包装方式填写正确！'  datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<th width="18%">是否易制毒化学品</th>
					<td width="32%"><cus:SelectOneTag style="width:60%"  property="cheRes.isChemical" defaultText='请选择' codeName="是或否" value="${cheRes.isChemical}" /></td>
					<th width="18%">是否重点监管化学品</th>
					<td width="32%"><cus:SelectOneTag style="width:60%"  property="cheRes.isRegulatorChemical" defaultText='请选择' codeName="是或否" value="${cheRes.isRegulatorChemical}" /></td>
				</tr>
				<tr>
					<th width="18%">是否易制爆化学品</th>
					<td width="32%"><cus:SelectOneTag style="width:60%"  property="cheRes.isExplosiveChemical" defaultText='请选择' codeName="是或否" value="${cheRes.isExplosiveChemical}" /></td>
					<th width="18%">是否剧毒化学品</th>
					<td width="32%"><cus:SelectOneTag style="width:60%"  property="cheRes.isToxicChemical" defaultText='请选择' codeName="是或否" value="${cheRes.isToxicChemical}" /></td>
				</tr>
				<tr>
					<th width="18%">现有储存量</th>
					<td width="32%"><input name="cheRes.storage" style="width:60%" value="${cheRes.storage}"  errormsg='现有储存量必须是1到127位字符！' nullmsg='现有储存量不能为空！' sucmsg='现有储存量填写正确！'  datatype="*1-127" type="text" maxlength="127"><font style='color:red'>*</font></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_cheRes');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

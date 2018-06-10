<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
		function queryQy(){
			var szzid = document.getElementById('areaId').value;
			popupCenter("${ctx}/jsp/qyjbxx/queryCompanyList.action?flag=idmcqy&entBaseInfo.enterprisePossession="+szzid, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
		}
		function clearCompany(){
        	document.getElementById("companyName").value="";
        	document.getElementById("companyId").value="";
        }
	</script>
</head>

<body validform="true">
   <div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ">
	<form name="myform1" method="post" enctype="multipart/form-data" action="companyScoreSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="companyScore.id" value="${companyScore.id}">
		<input type="hidden" name="companyScore.createTime" value="<fmt:formatDate type="both" value="${companyScore.createTime}" />">
		<input type="hidden" name="companyScore.updateTime" value="${companyScore.updateTime}">
		<input type="hidden" name="companyScore.createUserID" value="${companyScore.createUserID}">
		<input type="hidden" name="companyScore.updateUserID" value="${companyScore.updateUserID}">
		<input type="hidden" name="companyScore.deptId" value="${companyScore.deptId}">
		<input type="hidden" name="companyScore.delFlag" value="${companyScore.delFlag}">
		<input type="hidden" name="companyScore.companyId" value="${companyScore.companyId}">
		<input type="hidden" name="companyScore.scoreYear" value="${companyScore.scoreYear}">
		<input type="hidden" name="companyScore.systemScore" value="${companyScore.systemScore}">
		
		<font style="color:blue;font-size:28px;">${companyScore.entBaseInfo.enterpriseName}&nbsp;&nbsp;&nbsp;${companyScore.scoreYear}年度评分</font>
			<table width="100%" border="0"  align="center" >
				<tr >
					<th width="5%" style="text-align:center" >序号</th>
					<th width="50%" style="text-align:center" >企业达标标准</th>
					<th width="10%" style="text-align:center">标准分值</th>
					<th width="7.5%" style="text-align:center">系统评分</th>
					<th width="7.5%" style="text-align:center">见证评分</th>
					<th width="20%" style="text-align:center">备注</th>
				</tr>
				<tr>
					<td style="text-align:center">1</td>
					<td style="text-align:left">建立安全生产目标的管理制度，明确目标与指标的制定、分解、实施、考核等环节内容。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[0] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[0] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">2</td>
					<td style="text-align:left">按照安全生产目标管理制度的规定，制定文件化的总体和年度安全生产目标。</td>
					<td style="text-align:center">6</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[1] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[1] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[1] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">3</td>
					<td style="text-align:left">根据所属基层单位和部门在安全生产中的职能，分解年度安全生产目标，并制定实施计划和考核办法。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[2] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[2] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">4</td>
					<td style="text-align:left">按照制度规定，对安全生产目标和指标实施计划的执行情况进行监测，并保存有关监测记录资料。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[3] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[3] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				
				
				
				
				
				<tr>
					<td style="text-align:center">5</td>
					<td style="text-align:left">定期对安全生产目标的完成效果进行评估和考核，依据评估考核结果，及时调整安全生产目标和指标的实施计划。评估报告和实施计划的调整、修改记录应形成文件并加以保存。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[4] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[4] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">6</td>
					<td style="text-align:left">建立设置安全管理机构、配备安全管理人员的管理制度。   </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[5] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[5] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">7</td>
					<td style="text-align:left">按照相关规定设置安全管理机构或配备安全管理人员。</td>
					<td style="text-align:center">3</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[6] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[6] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[6] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">8</td>
					<td style="text-align:left">根据有关规定和企业实际，设立安全生产委员会或安全生产领导机构。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[7] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[7] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[7] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">9</td>
					<td style="text-align:left">安委会或安全生产领导机构每季度应至少召开一次安全专题会，协调解决安全生产问题。会议纪要中应有工作要求并保存。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[8] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[8] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">10</td>
					<td style="text-align:left">主要负责人全面负责安全生产工作，并履行下列主要职责：
						（1）组织建立、健全本单位的安全生产责任制，并保证有效执行；
						（2）组织制定安全生产规章制度和操作规程，并保证其有效实施；
						（3）保证本单位安全生产投入的有效实施；                   
						（4）督促检查本单位安全生产工作，及时消除生产安全事故隐患；
						（5）组织制定并实施本单位的生产安全事故应急救援预案；
						（6）及时、如实报告生产安全事故。
 					</td>
					<td style="text-align:center">10</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[9] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[9] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">11</td>
					<td style="text-align:left">建立针对安全生产责任制的制定、沟通、培训、评审、修订及考核等环节内容的管理制度。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[10] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[10] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">12</td>
					<td style="text-align:left"> 建立、健全安全生产责任制，并对落实情况进行考核。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[11] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[11] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">13</td>
					<td style="text-align:left">对各级管理层进行安全生产责任制与权限的培训。</td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[12] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[12] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">14</td>
					<td style="text-align:left">定期对安全生产责任制进行适宜性评审与更新。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[13] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[13] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">15</td>
					<td style="text-align:left">建立安全生产费用提取和使用管理制度。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[14] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[14] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">16</td>
					<td style="text-align:left">保证安全生产费用投入，专款专用，并建立安全生产费用使用台账。 </td>
					<td style="text-align:center">12</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[15] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[15] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[15] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">17</td>
					<td style="text-align:left">制定包含以下方面的安全生产费用的使用计划：
						（1）完善、改造和维护安全防护设备设施；
						（2）安全生产教育培训和配备劳动防护用品；
						（3）安全评价、重大危险源监控、事故隐患评估和整改；
						（4）设备设施安全性能检测检验；
						（5）应急救援器材、装备的配备及应急救援演练；
						（6）安全标志及标识；
						（7）其他与安全生产直接相关的
						物品或者活动。
						制定职业危害防治，职业危害因素检测、监测和职业健康体检费用的使用计划。
				 </td>
					<td style="text-align:center">12</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[16] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[16] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">18</td>
					<td style="text-align:left">建立员工工伤保险、安全生产责任保险的管理制度。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[17] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[17] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">19</td>
					<td style="text-align:left">足额缴纳工伤保险费、安全生产责任保险费。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[18] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[18] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">20</td>
					<td style="text-align:left">保障死亡、受伤员工获取相应的保险与赔付。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[19] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[19] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">21</td>
					<td style="text-align:left">建立识别、获取、评审、更新安全生产法律法规与其他要求的管理制度。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[20] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[20] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">22</td>
					<td style="text-align:left">各职能部门应定期识别和获取本部门适用的安全生产法律法规与其他要求，并向归口部门汇总。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[21] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[21] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">23</td>
					<td style="text-align:left">定期识别和获取使用的安全生产法律法规与其他要求，并发布清单。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[22] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[22] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">24</td>
					<td style="text-align:left"> 及时将适用的安全生产法律法规与其他要求传达给从业人员，并进行相关培训和考核。</td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[23] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[23] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">25</td>
					<td style="text-align:left">遵守安全生产法律法规与其他要求，并将相关要求及时转化为本单位的规章制度，贯彻到各项工作中。 </td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[24] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[24] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">26</td>
					<td style="text-align:left"> 建立规章制度的管理制度，确保安全生产规章制度和操作规程编制、发布、使用、评审、修订等效力。</td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[25] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[25] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">27</td>
					<td style="text-align:left"> 按照相关规定建立和发布健全的安全生产规章制度，至少包含下列内容：安全目标管理、安全生产责任制管理、法律法规标准规范管理、领导现场带班、班组岗位达标、安全生产投入管理、文件和档案管理、风险评估和控制管理、安全教育培训管理、特种作业人员管理、设备设施安全管理、建设项目安全设施“三同时”管理、生产设备设施验收管理、生产设备设施报废管理、施工和检（维）修安全管理、危险物品及重大危险源管理、作业安全管理、相关方及外用工（单位）管理、职业健康管理、劳动防护用品（具）和保健品管理、安全检查及隐患治理、应急管理、事故管理、安全绩效评定管理、消防安全管理等。</td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[26] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[26] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">28</td>
					<td style="text-align:left">将安全生产规章制度发放到相关工作岗位，并对员工进行培训和考核。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[27] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[27] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">29</td>
					<td style="text-align:left">基于岗位生产特点中的特定风险的辨识，编制齐全、适用的岗位安全操作规程。 </td>
					<td style="text-align:center">12</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[28] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[28] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				
				
				
				<tr>
					<td style="text-align:center">30</td>
					<td style="text-align:left">向员工下发岗位安全操作规程，并对员工进行培训和考核。 </td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[29] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[29] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">31</td>
					<td style="text-align:left">每年至少一次对安全生产法律法规、标准规范、规章制度、操作规程的执行情况和适用情况进行检查、评估。 </td>
					<td style="text-align:center">12</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[30] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[30] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">32</td>
					<td style="text-align:left">根据评估情况、安全检查反馈的问题、生产安全事故案例、绩效评定结果等，对安全生产管理规章制度和操作规程进行修订，确保其有效和适用。 </td>
					<td style="text-align:center">12</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[31] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[31] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">33</td>
					<td style="text-align:left">建立文件和档案的管理制度，明确责任部门、人员、流程、形式、权限及各类安全生产档案及保存要求等。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[32] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[32] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">34</td>
					<td style="text-align:left">确保安全规章制度和操作规程编制、使用、评审、修订的效力。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[33] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[33] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">35</td>
					<td style="text-align:left"> 对下列主要安全生产资料进行档案管理：安全生产会议记录（含纪要）、安全费用提取使用记录、员工安全教育培训记录、劳动防护用品采购发放记录、危险源管理台帐、安全生产检查记录、授权作业指令单、事故调查处理报告、事故隐患整改记录、安全生产奖惩记录、特种作业人员登记记录、特种设备管理记录、外来施工队伍安全管理记录、安全设备设施管理台账（包括安装、运行、维护等）、有关强制性检测检验报告或记录、新改扩建项目“三同时”、风险评价信息、职业健康检查与监护记录、应急演习信息、技术图纸等。</td>
					<td style="text-align:center">10</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[34] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[34] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">36</td>
					<td style="text-align:left">建立安全教育培训的管理制度。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[35] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[35] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">37</td>
					<td style="text-align:left">确定安全教育培训主管部门，定期识别安全教育培训需求，制定各类人员的培训计划。 </td>
					<td style="text-align:center">5</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[36] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[36] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">38</td>
					<td style="text-align:left">按计划进行安全教育培训，对安全培训效果进行评估和改进。做好培训记录，并建立档案。 </td>
					<td style="text-align:center">10</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[37] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[37] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">39</td>
					<td style="text-align:left">主要负责人和安全生产管理人员，应具备与本单位所从事的生产经营活动相适应的安全生产知识和管理能力，经培训考核合格后方可任职。 </td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[38] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[38] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">40</td>
					<td style="text-align:left">对岗位操作人员进行安全教育和生产技能培训和考核，考核不合格人员，不得上岗；进行上岗前的职业健康培训和在岗期间的定期职位健康培训。
					    <br/>对新员工进行“三级”安全教育。					
					    <br/>在新工艺、新技术、新材料、新设备设施投入使用前，应对有关岗位操作人员进行专门的安全教育和培训。					
					   <br/> 岗位操作人员转岗和离岗一年重新上岗者，应进行车间(工段)、班组安全教育培训，经考核合格后，方可上岗工作。					
					   <br/> 从事特种作业人员和特种设备作业人员应取得特种作业操作资格证书，方可上岗作业。				
					</td>
					<td style="text-align:center">12</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[39] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[39] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">41</td>
					<td style="text-align:left">对相关方进行安全教育培训，培训合格后，取得入厂证后方可入厂工作。作业人员进入作业现场前，应由作业现场所在单位对其进行进入有针对性的现场前的安全教育培训。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[40] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[40] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">42</td>
					<td style="text-align:left">对外来参观、学习等人员进行有关安全规定、可能接触到的危害及应急知识等内容的安全教育和告知，并由专人带领。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[41] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[41] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">43</td>
					<td style="text-align:left">采取多种形式的活动来促进企业的安全文化建设，促进安全生产工作。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[42] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[42] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">44</td>
					<td style="text-align:left">建立新、改、扩建工程“三同时”管理制度。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[43] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[43] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[43] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">45</td>
					<td style="text-align:left">1、新、改、扩建工程“三同时”管理制度；<br/>
						2、“三同时”进行评估、审核认可手续；<br/>
						3、按规定进行安全预评价或安全验收评价资料；<br/>
						4、初步设计安全专篇或安全专篇审查通过材料；<br/>
						5、变更安全设备设施经设计单位书面同意材料；<br/>
						6、隐蔽工程验收材料；<br/>
						7、安全预评价报告、安全专篇、安全验收评价报告报安全生产监督管理部门备案材料。
					</td>
					<td style="text-align:center">12</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[44] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[44] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">46</td>
					<td style="text-align:left">厂址选择应遵循《工业企业总平面设计规范》（GB50187）的规定。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[45] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[45] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">47</td>
					<td style="text-align:left">平面布置应合理安排车流、人流、物流，保证安全顺行。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[46] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[46] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">48</td>
					<td style="text-align:left">厂房的照明，应符合《建筑采光设计标准》（GB/T50033）和《建筑照明设计标准》（GB50034）的规定。 照明电气的选型与作业场所相适应：一般作业场所可选用开启式照明电气，潮湿场所应选用密闭式防水照明电气，有腐蚀性场所应选用耐酸碱型照明电气，易燃物品存放场所不得使用聚光灯、碘钨灯等灯具，有限空间、高温、有导电灰尘、离地不足2.5米的固定式照明电源不得大于36伏，潮湿场所和易触及的照明电源不得大于24伏，室外220伏灯具距离地面不低于3米，室内不低于2.5米，普通灯具与易燃物品距离不得小于300毫米，灯头绝缘外壳无破损、无漏电现象。 </td>
					<td style="text-align:center">5</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[47] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[47] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">49</td>
					<td style="text-align:left">主要生产场所的火灾危险性分类及建构筑物防火最小安全间距，应遵循《建筑设计防火规范》（GB50016）。 </td>
					<td style="text-align:center">5</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[48] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[48] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">50</td>
					<td style="text-align:left">厂区内的建构筑物，应按《建筑物防雷设计规范》（GB50057）的规定设置防雷设施，并定期检查，确保防雷设施完好。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[49] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[49] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">51</td>
					<td style="text-align:left">厂内休息室、浴室、更衣室应设在安全区域，各种操作室、值班室不应设在可能泄漏有毒有害气体的危险区域。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[50] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[50] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">52</td>
					<td style="text-align:left">安全出入口（疏散门）不应采用侧拉门（库房除外），严禁采用转门。厂房、梯子的出入口和人行道，不宜正对车辆、设备运行频繁的地点，否则应设防护装置或悬挂醒目的警告标志。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[51] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[51] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">53</td>
					<td style="text-align:left"> 直梯、斜梯、防护栏杆和工作平台应符合《固定式钢梯及平台安全要求》 （GB4053.1-3）的规定。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[52] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[52] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">54</td>
					<td style="text-align:left">移动梯台应符合：操作平台护栏完好符合规定，斜撑无变形，铰接可靠，防滑措施齐全、完好，轮子的限位、防移动装置完好有效，结构件无松脱、裂纹、扭曲、腐蚀等严重变形，不得有裂纹。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[53] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[53] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">55</td>
					<td style="text-align:left">电气室（包括计算机房）、电缆夹层，应设有火灾自动报警器、烟雾火警信号装置、监视装置、灭火装置和防止小动物进入的措施；电缆穿线孔等应用防火材料进行封堵。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[54] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[54] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">56</td>
					<td style="text-align:left">设置用发电机房。自备发电机不应与供电网联接，并可靠接地。柴油发电机的环境温度及柴油机的运行温度定子不得超过75℃（E级）、转子不得超过80℃（B级）。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[55] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[55] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">57</td>
					<td style="text-align:left">胶（皮）带运输机应有如下安全防护装置并确保有效：
					（1）防打滑、防跑偏、防纵向撕裂；
					（2）拉线事故开关；
					（3）防压料自动停车装置；
					（4）头轮、尾轮、增面轮及拉紧装置应有防护罩或防护栏杆。
				 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[56] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[56] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">58</td>
					<td style="text-align:left">产生大量蒸汽、腐蚀性气体、粉尘等的场所，应采用封闭式电气设备；有爆炸危险的气体或粉尘的作业场所，应采用防爆型电气设备。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[57] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[57] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">59</td>
					<td style="text-align:left">使用表压超过0.1MPa的油、水、煤气、蒸汽、空气和其他气体的设备和管道系统，应安装压力表、安全阀等安全装置，并应定期检定。</td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[58] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[58] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">60</td>
					<td style="text-align:left">不同介质的管线，应按照《工业管道的基本识别色、识别符号和安全标识》（GB7231）的规定注明介质名称和流向。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[59] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[59] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">61</td>
					<td style="text-align:left">起重机应标明起重吨位，并应设有下列安全装置：  <br/>       
					（1）限位器；<br/>            
					（2）缓冲器； <br/>           
					（3）防碰撞装置；<br/>        
					（4）超载限制器；<br/>        
					（5）连锁保护装置；<br/> 
					（6）轨道端部止挡； <br/>
					（7）定位装置；    <br/> 
					（8）其他：零位保护、安全钩、扫轨板、电气安全装置等；  <br/>
					（9）走台栏杆、防护罩、滑线防护板、防雨罩（露天）等防护装置；<br/>
					（10）大型起重机械安全监控管理系统、安全信息提示和报警装置；<br/>
					（11）滑线指示灯、通电指示灯、桥下和驾驶室照明灯等。
					</td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[60] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[60] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">62</td>
					<td style="text-align:left"> 车间电气室、地下油库、地下液压站、地下润滑站、地下加压站等要害部门，其出入口应不少于两个（室内面积小于6m2而无人值班的，可设一个），门应向外开。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[61] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[61] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">63</td>
					<td style="text-align:left">设有集中监视和显示的火警信号。</td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[62] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[62] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">64</td>
					<td style="text-align:left">所有设备设施建设应符合有关法律法规、标准规范要求。</td>
					<td style="text-align:center">10</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[63] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[63] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">65</td>
					<td style="text-align:left">按规定对项目建议书、可行性研究、初步设计、总体开工方案、开工前安全条件确认和竣工验收等阶段进行规范管理。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[64] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[64] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">66</td>
					<td style="text-align:left">建立生产设备设施变更管理制度，履行变更程序，并对变更的全过程进行隐患控制。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[65] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[65] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[65] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">67</td>
					<td style="text-align:left">建立设备设施的检修、维护、保养管理制度。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[66] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[66] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[66] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">68</td>
					<td style="text-align:left">建立设备设施运行台账，制定检（维）修计划。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[67] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[67] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">69</td>
					<td style="text-align:left">按规定对有关设备、设施、仪器仪表、工具等进行检测检验检定，并归档保存有关资料。 </td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[68] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[68] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">70</td>
					<td style="text-align:left">吊车的滑线应布置在吊车司机室的另一侧；若布置在同一侧，应采取安全防护措施。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[69] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[69] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">71</td>
					<td style="text-align:left">吊具应有专人管理，在其安全系数允许范围内使用。钢丝绳和链条的安全系数和钢丝绳的报废标准，应符合《起重机械安全规程》（GB6067）的有关规定。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[70] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[70] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">72</td>
					<td style="text-align:left">吊运物行走的安全路线，不应跨越有人操作的固定岗位或经常有人停留的场所，且不应随意越过主体设备。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[71] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[71] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">73</td>
					<td style="text-align:left">设置的CO等有毒有害气体、物质报警仪应定期检验，确保其处于安全状态。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[72] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[72] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">74</td>
					<td style="text-align:left">压力容器应满足：压力容器本体及安全附件在检验有效期内使用，本体完好；连接元件无异常振动、磨擦、松动；安全附件、显示装置、报警装置、联锁装置完好，调试、更换记录齐全；运行和使用符合相关规定，无超压、超温等现象。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[73] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[73] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">75</td>
					<td style="text-align:left">锅炉与辅机锅炉应满足：“三证”齐全；安全附件完好，安全阀、水位表、压力表齐全、灵敏、可靠，排污装置无泄漏；按规定合理设置报警和连锁保护装置；给水设备完好，匹配合理；炉墙无严重漏风、漏烟，油、气、煤粉炉防爆式装置好；水质处理应能达到指标要求，炉内水垢在1.5mm以下；各类管道无泄漏，保温层完好无损，管道构架牢固可靠；其他辅机设备应符合机械安全要求。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[74] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[74] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">76</td>
					<td style="text-align:left">工业气瓶应满足：储存仓库状态良好，安全标志完善，气瓶存放位置、间距、标志及存放量符合要求。各种护具及消防器材齐全可靠。气瓶在检验期内使用，外观无缺陷及腐蚀，漆色及标志正确、明显，安全附件齐全、完好。气瓶使用时的防倾倒措施可靠，工作场地存放量符合规定，与明火的间距符合规定。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[75] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[75] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">77</td>
					<td style="text-align:left">厂内机动车辆应满足：在检验有效期内使用，动力系统运转平稳，无漏电、漏水、漏油。灯光电气完好，仪表、照明、信号及各附属安全装置性能良好。轮胎无损伤。制动距离符合要求。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[76] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[76] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">78</td>
					<td style="text-align:left">低压临时线路应满足：有完备的临时接线装置审批手续，不超期使用。使用绝缘良好，并有与负荷匹配的护套软管，敷设符合安全要求。装有总开关控制和漏电保护装置，每分路应装设与负荷匹配的熔断器。临时用电设备PE连接可靠。严禁在有爆炸和火灾危险场所设临时线路。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[77] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[77] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">79</td>
					<td style="text-align:left">低压电气线路（固定线路）应满足：定期进行电缆线路的预防性实验记录。线路的安全距离符合要求；线路的导电性能和机械强度符合要求；线路的保护装置齐全可靠；线路绝缘、屏护良好，无发热和渗漏油现象；电杆直立、拉线、横担瓷瓶及金属构架等符合安全要求；线路相序、相色正确、标志齐全、清晰；线路排列整齐、无影响线路安全的障碍物。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[78] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[78] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">80</td>
					<td style="text-align:left">电网接地系统应满足：电源系统接地制式的运行应满足其结构的整体性，独立性的安全要求；各接地装置的电阻检测合格；TN系统重复接地布设合理；接地装置的连接必须保证电气接触可靠；有足够的机械强度，并能防腐蚀，防损伤或者有附加保护措施；接地装置编号、标识明晰，定期检测报告有效，资料完整。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[79] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[79] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">81</td>
					<td style="text-align:left">金属切削机床应满足：防护罩、盖、栏应完备可靠；防止夹具、卡具松动或脱落的装置完好；各种限位、联锁、操作手柄要求灵敏可靠；机床PE连接规范可靠；机床照明符合要求；机床电器箱，柜与线路符合要求；未加罩旋转部位的楔、销、键，原则上不许突出；备有清除切屑的专用工具。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[80] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[80] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">82</td>
					<td style="text-align:left">冲、剪、压机械应满足：离合器动作灵敏、可靠，无连冲；制动器工作可靠；紧急停止按钮灵敏、醒目，在规定位置安装有效；传动外露部分的防护装置齐全可靠；脚踏开关应有完备的防护罩且防滑；机床PE可靠，电气控制有效；安全防护装置可靠有效，使用专用工具符合安全要求；剪板机等压料脚应平整，危险部位有可靠的防护。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[81] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[81] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">83</td>
					<td style="text-align:left"> 砂轮机应满足:安装地点应保证人员和设备的安全；砂轮机的防护罩应符合国家标准；档屑板应有足够的强度且可调；砂轮无裂纹无破损；托架安装牢固可调；法兰盘与软垫应符合安全要求；砂轮机运行必须平稳可靠，砂轮磨损量不超标，且在有效期内使用；PE连接可靠，控制电器符合规定。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[82] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[82] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">84</td>
					<td style="text-align:left">工业机器人应满足：装有限位装置，在额定负荷、最高速度和最大伸长量时使机器停止；采用手动操作时，运动时速应设定在250毫米/秒以下；当进行运送工作时，紧急开关启动后，立即停止运行；作业区域有隔离的安全护罩，覆盖全部危险区域；防护罩无锐边和凸出部分；护罩应有足够强度，能抵抗机器人最大突击能量；防护罩应永久固定，只有借助工具方可拆卸；防护罩的舱门应有机械式安全锁或门禁装置，钥匙或专用工具应由专业人员保管；危险区域内装有紧急停止开关，并符合相关标准。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[83] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[83] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">85</td>
					<td style="text-align:left">移动电气设备应满足：定期对绝缘电阻进行检测，绝缘电阻应小于1兆欧，电源线应采用三芯或四芯多股橡胶电缆，无接头，不得跨越通道，绝缘层无破损，长度不得超过5米，PE线连接可靠，防护罩等完好，无松动，开关可靠、灵敏，与负载匹配。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[84] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[84] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">86</td>
					<td style="text-align:left">电气设备(特别是手持式电动工具)的金属外壳和电线的金属保护管，应有良好的保护接零(或接地)装置。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[85] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[85] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">87</td>
					<td style="text-align:left">传动部位应按照如下情况，设置防护罩、盖或栏：   <br/>     
					（1）以操纵人员站立平面为基准，高度在2米以下的外露传动部位；<br/>
					（2）旋转的键、销、楔等突出大于3毫米的部位；<br/>
					（3）产生切屑、磨屑、冷却液等飞溅，可能触及人体或造成设备与环境污染的部位；<br/>
					（4）产生射线或弧光的部位；<br/>
					（5）伸入通道的超长工件；<br/>
					（6）超长设备后端300毫米以上的工件；<br/>
					（7）容易伤人的设备往复运动部位；<br/>
					（8）悬挂输送装置跨越通道的下部；<br/>
					（9）高于地面0.7米的操作平台。
					</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[86] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[86] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">88</td>
					<td style="text-align:left">危险化学品库应满足：库房符合安全标准的要求，库内有应急预案。危险化学品按危险性进行分类、分区、分库储存。库内有隔热、降温、通风等措施，消防设施齐全，消防通道畅通。采用相应等级的防爆电器。有效处理废弃物品或包装容器。 </td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[87] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[87] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">89</td>
					<td style="text-align:left">油库、油罐应满足：油槽车需持有专用许可证，进入库区，必须装设专用排气阻火器；油罐无腐蚀、泄漏；油罐上的液位计、呼吸阀齐全可靠、动作灵敏；罐体、胶质输油管等应有可靠的防雷接地和防静电接地；罐体与罐体之间或其它建筑物、管网、干道应留有足够的间距；库房的电气设施均应防爆；油库内应按贮存物品的种类和数量，配置足够的消防器材和灭火设施，并有相应的报警装置；库内使用的工具应是不产生火花的防爆工具；库内外应有醒目的安全警示标志和油品的名称、特性、数量、灭火方法等。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[88] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[88] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">90</td>
					<td style="text-align:left">专用设备应符合有关法律法规、标准规范要求：防护罩、盖、栏应完整可靠；各联锁、紧停、控制装置灵敏可靠；局部照明应为安全电压；PE等电器完好可靠；梯台符合要求。 </td>
					<td style="text-align:center">16</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[89] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[89] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">91</td>
					<td style="text-align:left">设备设施检维修前应制定方案。检维修方案应包含作业行为分析和控制措施。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[90] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[90] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">92</td>
					<td style="text-align:left">按检（维）修计划定期对设备设施，包括安全设备设施进行检（维）修。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[91] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[91] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">93</td>
					<td style="text-align:left">安全设备设施不得随意拆除、挪用或弃置不用；确因检维修拆除的，应采取临时安全措施，检维修完毕后立即复原。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[92] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[92] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">94</td>
					<td style="text-align:left">设备的全生命周期，应符合相关法律法规、标准规范的要求，确保安全。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[93] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[93] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">95</td>
					<td style="text-align:left">建立设备设施验收和设备设施拆除、报废的管理制度。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[94] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[94] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[94] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">96</td>
					<td style="text-align:left">按规定对设备设施进行验收，确保使用质量合格、设计符合要求的设备设施。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[95] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[95] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">97</td>
					<td style="text-align:left">按规定对不符合要求的设备设施进行报废或拆除。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[96] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[96] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">98</td>
					<td style="text-align:left">对生产现场和生产过程、环境存在的风险和隐患进行辨识、评估分级，并制定相应的控制措施。 </td>
					<td style="text-align:center">20</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[97] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[97] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">99</td>
					<td style="text-align:left">严禁架空电线跨越爆炸和火灾危险场所。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[98] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[98] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">100</td>
					<td style="text-align:left">非经允许，禁止与生产无关人员进入生产操作现场。应划出非岗位操作人员行走的安全路线。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[99] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[99] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">101</td>
					<td style="text-align:left">行灯电压不应大于36V，在金属容器内或潮湿场所，则电压不应大于12V。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[100] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[100] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">102</td>
					<td style="text-align:left">设应急照明，正常照明中断时，应急照明应能自动启动。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[101] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[101] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">103</td>
					<td style="text-align:left">易燃、可燃或有毒介质导管不应直接进入仪表操作室或有人值守、休息的房间，应通过变送器把信号引进仪表操作室。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[102] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[102] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">104</td>
					<td style="text-align:left">建立对“三违”行为的管理制度，明确监控的责任、方法、记录、考核等事项。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[103] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[103] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">105</td>
					<td style="text-align:left">建立至少包括下列危险作业的安全管理制度，明确责任部门、人员、许可范围、审批程序、许可签发人员等：<br/>
					（1）危险区域动火作业；<br/>
					（2）进入受限空间作业；<br/>
					（3）能源介质作业；<br/>
					（4）高处作业；<br/>
					（5）大型吊装作业；<br/>
					（6）交叉作业；<br/>
					（7）其他危险作业。
					</td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[104] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[104] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">106</td>
					<td style="text-align:left">对危险作业的安全管理工作实施作业许可。作业许可证应包含危害因素分析和安全措施等内容。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[105] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[105] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">107</td>
					<td style="text-align:left"> 对生产作业过程中人的不安全行为进行辨识，并制定相应的控制措施。</td>
					<td style="text-align:center">20</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[106] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[106] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">108</td>
					<td style="text-align:left">对危险性大的作业实行许可制、工作票制。 </td>
					<td style="text-align:center">20</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[107] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[107] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">109</td>
					<td style="text-align:left">要害岗位及电气、机械等设备，应实行操作牌制度。 </td>
					<td style="text-align:center">20</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[108] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[108] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">110</td>
					<td style="text-align:left">应当为从业人员配备与工作岗位相适应的符合国家标准或者行业标准的劳动防护用品，并监督、教育从业人员按照使用规则佩戴、使用。 </td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[109] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[109] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">111</td>
					<td style="text-align:left">在易燃易爆区不宜动火，设备需要动火检修时，应尽量移到动火区进行。 </td>
					<td style="text-align:center">10</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[110] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[110] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">112</td>
					<td style="text-align:left"> 在有毒物质的设备、管道和容器内检修时，应符合以下规定：<br/>
					（1）应可靠地切断物料进出口，有毒物质的浓度应小于允许值，同时含氧量应在18%～22%（体积百分浓度）范围内；<br/>
					（2）监护人不应少于2人，应备好防毒面具和防护用品，检修人员应熟悉防毒面具的性能和使用方法；<br/>
					（3）设备内照明电压应小于等于36V，在潮湿容器、狭小容器内作业应小于等于12V。
					</td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[111] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[111] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">113</td>
					<td style="text-align:left">对易燃、易爆或易中毒物质的设备动火或进入内部工作时，监护人不应少于2人。安全分析取样时间不应早于工作前半小时，工作中应每两小时重新分析一次，工作中断半小时以上也应重新分析。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[112] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[112] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">114</td>
					<td style="text-align:left">在全部停电或部分停电的电气设备上作业，应遵守下列规定：<br/>
					（1）拉闸断电，并采取开关箱加锁等措施；<br/>
					（2）验电、放电；<br/>
					（3）各相短路接地；<br/>
					（4）悬挂“禁止合闸，有人工作”的标示牌和装设遮拦。
					 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[113] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[113] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">115</td>
					<td style="text-align:left">建立警示标志和安全防护的管理制度。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[114] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[114] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">116</td>
					<td style="text-align:left">在有较大危险因素的作业场所或有关设备上，设置符合《安全标志》（GB2894）和《安全色》（GB2893）规定的安全警示标志和安全色。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[115] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[115] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">117</td>
					<td style="text-align:left">危险化学品专用仓库、特种设备、产生严重职业危害的作业岗位，应按照有关规定设置标识及警示标志。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[116] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[116] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">118</td>
					<td style="text-align:left">在设备设施检维修、施工、吊装等作业现场设置警戒区域和警示标志，在检维修现场的坑、井、洼、沟、陡坡等场所设置围栏和警示标志。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[117] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[117] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">119</td>
					<td style="text-align:left">设备裸露的运转部分，应设有防护罩、防护栏杆或防护挡板。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[118] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[118] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">120</td>
					<td style="text-align:left">吊装孔应设置防护盖板或栏杆，并应设警示标志。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[119] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[119] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">121</td>
					<td style="text-align:left">煤气容易泄露和积聚的场所，应设醒目的警示标志。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[120] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[120] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">122</td>
					<td style="text-align:left">建立有关承包商、供应商等相关方的管理制度。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[121] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[121] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">123</td>
					<td style="text-align:left">对承包商、供应商等相关方的资格预审、选择、服务前准备、作业过程监督、提供的产品、技术服务、表现评估、续用等进行管理，建立相关方的名录和档案。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[122] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[122] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">124</td>
					<td style="text-align:left">根据相关方提供的服务作业性质和行为定期识别服务行为风险，采取行之有效的风险控制措施，并对其安全绩效进行监测。</td>
					<td style="text-align:center">12</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[123] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[123] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">125</td>
					<td style="text-align:left">甲方应统一协调管理同一作业区域内的多个相关方的交叉作业。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[124] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[124] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">126</td>
					<td style="text-align:left">不应将工程项目发包给不具备相应资质的单位。工程项目承包协议应当明确规定双方的安全生产责任和义务。 </td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[125] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[125] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">127</td>
					<td style="text-align:left"> 建立有关人员、机构、工艺、技术、设施、作业过程及环境变更的管理制度，并制定实施计划。</td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[126] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[126] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">128</td>
					<td style="text-align:left">对变更的实施进行审批和验收管理，并对变更过程及变更后所产生的风险和隐患进行辨识、评估和控制。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[127] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[127] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">129</td>
					<td style="text-align:left">变更安全设施，在建设阶段应经设计单位书面同意，在投用后应经安全管理部门书面同意。重大变更的，还应报安全生产监督管理部门备案。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[128] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[128] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">130</td>
					<td style="text-align:left">建立隐患排查治理的管理制度，明确责任部门、人员、方法。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[129] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[129] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[129] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">131</td>
					<td style="text-align:left">对隐患进行分析评估，确定隐患等级，登记建档。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[130] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[130] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">132</td>
					<td style="text-align:left"> 法律法规、标准规范发生变更或有新的公布，以及企业操作条件或工艺改变，新建、改建、扩建项目建设，相关方进入、撤出或改变，对事故、事件或其他信息有新的认识，组织机构发生大的调整的，应及时组织隐患排查。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[131] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[131] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">133</td>
					<td style="text-align:left">制定隐患排查工作方案，明确排查的目的、范围、方法和要求等。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[132] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[132] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">134</td>
					<td style="text-align:left">按照方案进行隐患排查工作。 </td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[133] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[133] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">135</td>
					<td style="text-align:left">隐患排查的范围应包括所有与生产经营场所、环境、人员、设备设施和活动。 </td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[134] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[134] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">136</td>
					<td style="text-align:left">采用综合检查、专业检查、季节性检查、节假日检查、日常检查等方式进行隐患排查工作。 </td>
					<td style="text-align:center">10</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[135] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[135] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">137</td>
					<td style="text-align:left">根据隐患排查的结果，制定隐患治理方案，对隐患进行治理。方案内容应包括目标和任务、方法和措施、经费和物资、机构和人员、时限和要求。重大事故隐患在治理前应采取临时控制措施并制定应急预案。隐患治理措施应包括工程技术措施、管理措施、教育措施、防护措施、应急措施等。 </td>
					<td style="text-align:center">20</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[136] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[136] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">138</td>
					<td style="text-align:left"> 在重大事故隐患治理完成后对治理情况进行验证和效果评估。</td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[137] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[137] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">139</td>
					<td style="text-align:left"> 按规定对隐患排查和治理情况进行统计分析并向安全监管部门和有关部门报送书面统计分析表。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[138] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[138] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">140</td>
					<td style="text-align:left">企业应根据生产经营状况及隐患排查治理情况，采用技术手段、仪器仪表及管理方法等，建立安全预警指数系统，每月进行一次安全生产风险分析。 </td>
					<td style="text-align:center">8</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[139] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[139] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">141</td>
					<td style="text-align:left">  建立危险源的管理制度，明确辨识与评估的职责、方法、范围、流程、控制原则、回顾、持续改进等。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[140] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[140] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[140] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">142</td>
					<td style="text-align:left">按相关规定对本单位的生产设施或场所进行危险源辨识、分类和风险评价、分级，确定危险源及重大危险源（包括企业确定的重大危险源）。 </td>
					<td style="text-align:center">10</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[141] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[141] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">143</td>
					<td style="text-align:left">对确认的危险源及时登记建档。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[142] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[142] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">144</td>
					<td style="text-align:left">按照相关规定，将重大危险源向安全监管部门和相关部门备案。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[143] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[143] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">145</td>
					<td style="text-align:left">计量检测用的放射源应当按照有关规定取得放射物品使用许可证。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[144] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[144] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">146</td>
					<td style="text-align:left">对危险源（包括企业确定的重大危险源）采取措施进行监控，包括技术措施（设计、建设、运行、维护、检查、检验等）和组织措施（职责明确、人员培训、防护器具配置、作业要求等）。 </td>
					<td style="text-align:center">20</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[145] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[145] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">147</td>
					<td style="text-align:left">在危险源现场设置明显的安全警示标志和危险源点警示牌（内容包含名称、地点、责任人员、事故模式、控制措施等）。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[146] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[146] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">148</td>
					<td style="text-align:left">相关人员应按规定对危险源进行检查，并在检查记录本上签字。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[147] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[147] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">149</td>
					<td style="text-align:left">建立职业健康的管理制度。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[148] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[148] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">150</td>
					<td style="text-align:left">按有关要求，为员工提供符合职业健康要求的工作环境和条件：<br/>
					（1）生产布局合理，有害作业与无害作业分开；           <br/>
					（2）作业场所与生活场所分开，作业场所不得住人；      <br/>  
					（3）有与职业危害防治工作相适应的有效防护设施；        <br/>
					（4）职业危害强度或浓度符合国家标准、行业标准。
					 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[149] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[149] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">151</td>
					<td style="text-align:left">  所有产尘设备和尘源点，应严格密闭，并设除尘系统。作业场所粉尘和有害物质的浓度，应符合《工业企业设计卫生标准》（GBZ1）、《工业场所有害因素职业接触限值 化学有害因素》（GBZ2.1）、《工业场所有害因素职业接触限值 物理因素》（GBZ2.2）的规定。 </td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[150] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[150] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">152</td>
					<td style="text-align:left">建立健全职业卫生档案和员工健康监护档案。 对接触职业危害的作业人员，每1～2年应进行一次职业危害体检，体检结果记入“职业健康监护档案”。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[151] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[151] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">153</td>
					<td style="text-align:left">对职业病患者按规定给予及时的治疗、疗养。对患有职业禁忌症的，应及时调整到合适岗位。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[152] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[152] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">154</td>
					<td style="text-align:left">定期识别作业场所职业危害因素，并进行检测，将检测结果公布、存入档案。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[153] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[153] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">155</td>
					<td style="text-align:left">对可能发生急性职业危害的有毒、有害工作场所，应当设置报警装置，制定应急预案，配置现场急救用品和必要的泄险区。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[154] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[154] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">156</td>
					<td style="text-align:left">产生粉尘、毒物的生产过程和设备，应尽量考虑机械化和自动化，加强密闭，避免直接操作。应结合生产工艺采取通风措施。产生粉尘、毒物等有害物质的工作场所，应有冲洗地面、墙壁的设施。</td>
					<td style="text-align:center">6</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[155] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[155] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">157</td>
					<td style="text-align:left">多尘、散发有毒气体的厂房或甲、乙类生产厂房内的空气不应循环使用。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[156] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[156] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">158</td>
					<td style="text-align:left">对封闭性的放射源，应根据剂量强度、照射时间以及照射源距离，采取有效的防护措施；具有辐射作业场所的生产过程应根据危害性质配置必要的监测仪表。维护和检修放射线、放射性同位素仪器和设备的人员应配备个人专用防护器具。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[157] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[157] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">159</td>
					<td style="text-align:left"> 利用放射性同位素进行检测、计量时，应遵守下列规定：<br/>
						（1）有确保放射源不致丢失的措施；<br/>
						（2）可能受到射线危害的有关人员应配带检测仪表，及时检测和统计、建档，以控制其接受剂量不超标。
						</td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[158] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[158] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">160</td>
					<td style="text-align:left">各种防护器具应定点存放在安全、便于取用的地方，并有专人负责保管，定期校验和维护。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[159] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[159] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">161</td>
					<td style="text-align:left">对现场急救物品、设备和防护用品等进行经常性的检维修，定期校验其性能，确保发生事故时可靠有效。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[160] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[160] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">162</td>
					<td style="text-align:left">与从业人员订立劳动合同（含聘用合同）时，应将保障从业人员劳动安全和工作过程中可能产生的职业危害及其后果、职业危害防护措施、待遇等如实以书面形式告知从业人员，并在劳动合同中写明。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[161] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[161] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">163</td>
					<td style="text-align:left">对员工及相关方宣传和培训生产过程中的职业危害、预防和应急处理措施。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[162] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[162] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">164</td>
					<td style="text-align:left">对存在严重职业危害的作业岗位，按照《工业场所职业病危害警示标识》（GBZ158）要求，在醒目位置设置警示标志和警示说明。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[163] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[163] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">165</td>
					<td style="text-align:left"> 按规定，及时、如实地向当地主管部门申报生产过程存在的职业危害因素。</td>
					<td style="text-align:center">3</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[164] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[164] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[164] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">166</td>
					<td style="text-align:left">下列事项发生重大变化时，应向原申报主管部门申请变更：<br/>
					（1）新、改、扩建项目；<br/>
					（2）因技术、工艺或材料等发生变化导致原申报的职业危害因素及其相关内容发生重大变化；<br/>
					（3）企业名称、法定代表人或主要负责人发生变化。
					 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[165] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[165] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">167</td>
					<td style="text-align:left">建立事故应急救援制度。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[166] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[166] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">168</td>
					<td style="text-align:left">按相关规定建立安全生产应急管理机构或指定专人负责安全生产应急管理工作。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[167] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[167] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">169</td>
					<td style="text-align:left">建立与本单位生产安全特点相适应的专兼职应急救援队伍或指定专兼职应急救援人员。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[168] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[168] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">170</td>
					<td style="text-align:left">定期组织专兼职应急救援队伍和人员进行训练。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[169] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[169] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">171</td>
					<td style="text-align:left">按应急预案编制导则，结合企业实际制定生产安全事故应急预案，包括综合预案、专项应急预案和处置方案。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[170] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[170] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">172</td>
					<td style="text-align:left">建立火灾、爆炸和毒物逸散等重大事故的专项应急预案。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[171] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[171] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">173</td>
					<td style="text-align:left">根据有关规定将应急预案报当地主管部门备案，并通报有关应急协作单位。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[172] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[172] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">174</td>
					<td style="text-align:left">生产安全事故应急预案的评审、发布、培训、演练和修订应符合《生产安全事故应急预案管理办法》（国家安全监管总局令第17号）。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[173] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[173] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">175</td>
					<td style="text-align:left">按应急预案的要求，建立应急设施，配备应急装备，储备应急物资。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[174] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[174] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">176</td>
					<td style="text-align:left">对应急设施、装备和物资进行经常性的检查、维护、保养，确保其完好可靠。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[175] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[175] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">177</td>
					<td style="text-align:left">制定应急预案演练计划，每年至少组织一次综合应急预案演练或者专项应急预案演练，每半年至少组织一次现场处置方案演练。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[176] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[176] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">178</td>
					<td style="text-align:left">对应急演练的效果进行评估。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[177] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[177] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">179</td>
					<td style="text-align:left">发生事故后，应立即启动相关应急预案，积极开展事故救援。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[178] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[178] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">180</td>
					<td style="text-align:left">应急结束后，应编制应急救援报告。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[179] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[179] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">181</td>
					<td style="text-align:left">建立事故的管理制度，明确报告、调查、统计与分析、回顾、书面报告样式和表格等内容。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[180] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[180] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">182</td>
					<td style="text-align:left">发生事故后，主要负责人或其代理人应立即到现场组织抢救，采取有效措施，防止事故扩大。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[181] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[181] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">183</td>
					<td style="text-align:left">按规定及时向上级单位和有关政府部门报告，并保护事故现场及有关证据。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[182] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[182] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">184</td>
					<td style="text-align:left"> 对事故进行登记建档管理。</td>
					<td style="text-align:center">2</td>
					<td style="text-align:center">${fn:split(companyScore.systemScore,',')[183] }</td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[183] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[183] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">185</td>
					<td style="text-align:left">按照相关法律法规、管理制度的要求，组织事故调查组或配合有关政府行政部门对事故、事件进行调查。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[184] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[184] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">186</td>
					<td style="text-align:left">按照《企业职工伤亡事故分类标准》（GB6441）和《企业职工伤亡事故调查分析规则》（GB6442）定期对事故、事件进行统计、分析。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[185] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[185] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">187</td>
					<td style="text-align:left">对本单位的事故及其他单位的有关事故进行回顾、学习。 </td>
					<td style="text-align:center">3</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[186] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[186] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">188</td>
					<td style="text-align:left">建立安全生产标准化绩效评定的管理制度，明确对安全生产目标完成情况、现场安全状况与标准化规范的符合情况、安全管理实施计划的落实情况的测量评估的方法、组织、周期、过程、报告与分析等要求，测量评估应得出可量化的绩效指标。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[187] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[187] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">189</td>
					<td style="text-align:left">通过评估与分析，发现安全管理过程中的责任履行、系统运行、检查监控、隐患整改、考评考核等方面存在的问题，由安全生产委员会或安全生产领导机构讨论提出纠正、预防的管理方案，并纳入下一周期的安全工作实施计划中。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[188] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[188] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">190</td>
					<td style="text-align:left">将安全生产标准化工作评定报告向所有部门、所属单位和从业人员通报。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[189] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[189] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">191</td>
					<td style="text-align:left">每年至少一次对安全生产标准化实施情况进行评定，并形成正式的评定报告。发生死亡事故或生产工艺发生重大变化应重新进行评定。 </td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[190] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[190] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">192</td>
					<td style="text-align:left">将安全生产标准化实施情况的评定结果，纳入部门、所属单位、员工年度安全绩效考评。 </td>
					<td style="text-align:center">2</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[191] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[191] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">193</td>
					<td style="text-align:left"> 根据安全生产标准化的评定结果和安全预警指数系统，对安全生产目标与指标、规章制度、操作规程等进行修改完善，制定完善安全生产标准化的工作计划和措施，实施计划、执行、检查、改进（PDCA）循环，不断提高安全绩效。</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;" dataType="/^(-)?\d+(\.\d+)?$/i"  type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[192] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[192] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				<tr>
					<td style="text-align:center">194</td>
					<td style="text-align:left"> 安全生产标准化的评定结果要明确下列事项：  <br/>           
						（1）系统运行效果；<br/>
						（2）系统运行中出现的问题和缺陷，所采取的改进措施；     <br/>
						（3）统计技术、信息技术等在系统中的使用情况和效果；       <br/>
						（4）系统中各种资源的使用效果；<br/>
						（5）绩效监测系统的适宜性以及结果的准确性；<br/>
						（6）与相关方的关系。
						</td>
					<td style="text-align:center">4</td>
					<td style="text-align:center"></td>
					<td style="text-align:center"><input style="width:90%;"   dataType="/^(-)?\d+(\.\d+)?$/i" type="text" name="companyScore.checkScore" value="${fn:split(companyScore.checkScore,',')[193] }"</td>
					<td style="text-align:left"><input type="text" style="width:90%;" name="companyScore.remarks" value="${fn:split(companyScore.remarks,',')[193] }" onkeyup='javascript:this.value=this.value.replace(/,/g,"，");' onblur='javascript:this.value=this.value.replace(/,/g,"，");' maxlength='100'/></td>
				</tr>
				
				
				<tr>
					<td colspan="5" height="100px" style="text-align:center">
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

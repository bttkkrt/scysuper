<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>审核记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		$(function(){
        	$("input[name='enterpriseGrade.ajScore']").keyup(function(){  
            var c=$(this);  
            if(/[^0-9]*/.test(c.val())){//替换非数字字符  
              var temp_amount=c.val().replace(/[^0-9]*/g,'');  
              if(c.val().substring(0,1) == "-")
	          {
	            	temp_amount = "-"+temp_amount;
	          }
              $(this).val(temp_amount);  
            }  
         });
        });
        
         function getScore()
        {
        	$.ajax({
					cache: false,
					type: "POST",
					dataType: 'json',
					url:"getScore.action", //把表单数据发送到entBaseInfoSaveRegister.action
					data:$('#myform1').serialize(), //要发送的是ajaxFrm表单中的数据
					async: false,
					error: function(request) {
						alert("计算失败");
					},
					success: function(data) {
 						if(data.result){
        					document.getElementById('score').innerHTML = data.score;
        				}else{
        					alert("计算失败");
        				}
					}
 
				});
        }
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: ">
	<form id="myform1" name="myform1" method="post" enctype="multipart/form-data" action="enterpriseGradeSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="enterpriseGrade.id" value="${enterpriseGrade.id}">
		<input type="hidden" name="enterpriseGrade.createTime" value="<fmt:formatDate type="both" value="${enterpriseGrade.createTime}" />">
		<input type="hidden" name="enterpriseGrade.updateTime" value="${enterpriseGrade.updateTime}">
		<input type="hidden" name="enterpriseGrade.createUserID" value="${enterpriseGrade.createUserID}">
		<input type="hidden" name="enterpriseGrade.updateUserID" value="${enterpriseGrade.updateUserID}">
		<input type="hidden" name="enterpriseGrade.deptId" value="${enterpriseGrade.deptId}">
		<input type="hidden" name="enterpriseGrade.delFlag" value="${enterpriseGrade.delFlag}">
		<input type="hidden" name="enterpriseGrade.companyId" value="${enterpriseGrade.companyId}">
		<input type="hidden" name="enterpriseGrade.companyName" value="${enterpriseGrade.companyName}">
		<input type="hidden" name="enterpriseGrade.areaId" value="${enterpriseGrade.areaId}">
		<input type="hidden" name="enterpriseGrade.areaName" value="${enterpriseGrade.areaName}">
		<input type="hidden" name="enterpriseGrade.startTime" value="${enterpriseGrade.startTime}">
		<input type="hidden" name="enterpriseGrade.endTime" value="${enterpriseGrade.endTime}">
		<input type="hidden" name="enterpriseGrade.systemScore" value="${enterpriseGrade.systemScore}">
		<input type="hidden" name="enterpriseGrade.checkScore" value="${enterpriseGrade.checkScore}">
		<input type="hidden" name="enterpriseGrade.qyremark" value="${enterpriseGrade.qyremark}">
		<input type="hidden" name="enterpriseGrade.zpzf" value="${enterpriseGrade.zpzf}">
		<font style="color:blue;font-size:28px;">${enterpriseGrade.companyName}&nbsp;&nbsp;&nbsp;安全生产信用评分</font>
			<table width="100%" border="0"  align="center" >
				<tr >
					<th width="5%" style="text-align:center">序号</th>
					<th width="10%" style="text-align:center">要素</th>
					<th width="5%" style="text-align:center">分值</th>
					<th width="20%" style="text-align:center">基本规范要求</th>
					<th width="20%" style="text-align:center">评审办法</th>
					<th width="10%" style="text-align:center">自评分</th>
					<th width="15%" style="text-align:center">扣分说明</th>
					<th width="10%" style="text-align:center">评审分</th>
					<th width="15%" style="text-align:center">扣分说明</th>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="4">1</td>
					<td width="10%" style="text-align:center" rowspan="4">基本条件</td>
					<td width="5%" style="text-align:center" rowspan="4">20</td>
					<td width="20%" style="text-align:center">企业3年内未发生死亡或1次3人（含）以上重伤生产安全责任事故。</td>
					<td width="20%" style="text-align:center">近3年内发生死亡或一次3人（含）以上重伤生产安全责任事故的，扣10分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[0] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[0] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[0] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[0] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">安全生产标准化三级企业。</td>
					<td width="20%" style="text-align:center">未通过安全生产标准化三级及以上的，扣10分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[1] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[1] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[1] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[1] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">近三年未在安全生产方面受到行政处罚。</td>
					<td width="20%" style="text-align:center">近3年内在安全生产方面受到行政处罚的，扣10分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[2] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[2] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[2] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[2] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">加分项： </td>
					<td width="20%" style="text-align:center">安全生产标准化二级企业加5分；安全生产标准化一级企业加10分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[3] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[3] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[3] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[3] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="2">2</td>
					<td width="10%" style="text-align:center" rowspan="2">安全方针</td>
					<td width="5%" style="text-align:center" rowspan="2">4</td>
					<td width="20%" style="text-align:center">有符合企业实际情况的科学的安全理念的具体表述，突出“安全第一”、“以人为本”，并在企业内部认真贯彻。（2分）</td>
					<td width="20%" style="text-align:center">企业有成文的安全理念或能够体现企业安全管理发展意愿的口号式语句的得2分，否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[4] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[4] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[4] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[4] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">建立有特色的企业安全文化，在企业内部有良好的安全氛围，企业能够不定期的开展各类安全活动。（2分）</td>
					<td width="20%" style="text-align:center">在企业内部有良好的安全氛围，并通过标语、口号、橱窗等不同形式加以营造的得1分，否则不得分；企业年内不定期开展安全活动的得1分，否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[5] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[5] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[5] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[5] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="2">3</td>
					<td width="10%" style="text-align:center" rowspan="2">组织保障</td>
					<td width="5%" style="text-align:center" rowspan="2">8</td>
					<td width="20%" style="text-align:center">按要求和标准设置安全生产管理机构和配备管理人员（8分）。</td>
					<td width="20%" style="text-align:center">企业建立专门的安全管理机构的得4分，如安全管理机构与其他部门联合办公的得2分，否则不得分。安全管理人员按要求配备的得4分；未按要求配备每缺一人扣0.5分至4分扣完；应配安全生产总监但未配的不得分；应配专职人员但未配的不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[6] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[6] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[6] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[6] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">加分项： </td>
					<td width="20%" style="text-align:center">如安全管理人员超标配备，每多配一人加0.2分；每配备一名注册安全工程师，加0.5分。本项累计加分不得超过2分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[7] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[7] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[7] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[7] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="2">4</td>
					<td width="10%" style="text-align:center" rowspan="2">安全投入</td>
					<td width="5%" style="text-align:center" rowspan="2">4</td>
					<td width="20%" style="text-align:center">按规定提取、使用安全生产费用，把安全生产经费纳入年度费用计划，保证安全生产的投入（2分）。</td>
					<td width="20%" style="text-align:center">安全生产投入符合国家标准或满足企业安全生产需要可得分，否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[8] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[8] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[8] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[8] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">企业是否为员工购买工伤社会保险，是否购买安全生产责任险（2分）。</td>
					<td width="20%" style="text-align:center">企业为员工全员购买工伤社会保险的得1分，否则不得分；企业购买安全生产责任险（或类似险种）得1分，否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[9] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[9] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[9] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[9] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="5">5</td>
					<td width="10%" style="text-align:center" rowspan="5">安全制度</td>
					<td width="5%" style="text-align:center" rowspan="5">15</td>
					<td width="20%" style="text-align:center">建立健全安全生产责任制，领导层、管理层、车间、班组和岗位安全生产责任明确，逐级签订《安全生产责任书》。（4分）</td>
					<td width="20%" style="text-align:center">抽查企业安全生产责任制签订情况，每缺一份扣0.5分，至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[10] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[10] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[10] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[10] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">建有健全科学完善的安全生产各项规章制度。（5分）</td>
					<td width="20%" style="text-align:center">检查企业的安全制度清单，对安全生产事故调查处理制度、安全生产奖惩制度、安全生产投入制度、安全生产保险制度、劳动防护用品管理制度、承包商管理制度、租赁企业安全管理制度、警示标识管理制度、安全设施维保制度、四新评估管理制度、危险作业管理制度、重大危险源管理制度每缺一项的扣0.5分。至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[11] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[11] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[11] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[11] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">各项安全生产制度有效落实，有效果，有记录（3分）。</td>
					<td width="20%" style="text-align:center">抽查各项安全生产制度的落实情况，着重查看台账资料，发现一次不落实的情况扣0.5分，至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[12] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[12] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[12] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[12] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">建有健全科学完善的安全生产操作规程。（3分）</td>
					<td width="20%" style="text-align:center">抽检企业的岗位操作规程，缺一项操作规程扣0.1分，至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[13] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[13] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[13] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[13] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">加分项： </td>
					<td width="20%" style="text-align:center">如企业通过OHSAS18000体系认证加2分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[14] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[14] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[14] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[14] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="3">6</td>
					<td width="10%" style="text-align:center" rowspan="3">隐患排查</td>
					<td width="5%" style="text-align:center" rowspan="3">12</td>
					<td width="20%" style="text-align:center">制定安全检查制度和隐患排查整治理及效果评估制度，制定年度隐患排查治理计划。（2分）</td>
					<td width="20%" style="text-align:center">企业制定隐患排查治理制度的得1分；企业制定年度隐患排查治理计划的得1分。否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[15] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[15] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[15] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[15] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">认真按照计划开展隐患排查治理工作，定期开展安全检查。各类隐患能够闭环管理，有效果，有记录。（10分）</td>
					<td width="20%" style="text-align:center">企业每月安全检查少于1次的，每次扣0.3分；车间每周组织检查少于1次的，每次扣0.2分；班组、岗位职工每天检查少于1次的，每次扣0.1分。抽查隐患的整改情况，每条隐患未形成闭环管理的扣0.1分。以上扣分项至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[16] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[16] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[16] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[16] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">加分项： </td>
					<td width="20%" style="text-align:center">注册江苏省安全生产事故隐患排查治理信息系统的加1分；正常使用系统的加1分；（适用于2016年评定）</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[17] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[17] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[17] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[17] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="6">7</td>
					<td width="10%" style="text-align:center" rowspan="6">宣传教育</td>
					<td width="5%" style="text-align:center" rowspan="6">15</td>
					<td width="20%" style="text-align:center">制定中长期安全教育计划和年度教育计划，并按照计划开展行之有效的培训。（3分）</td>
					<td width="20%" style="text-align:center">制定有中长期安全教育计划的得1分，制定有年度安全教育计划的得1分，认真按照教育计划执行的得1分，否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[18] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[18] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[18] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[18] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">企业主要负责人、安全管理人员、特种作业人员、班组长100%依法参加安全生产培训，经考核合格后持证上岗；从业人员100%依法培训并取得上岗资格，特殊工种持证上岗率100%，特殊岗位考核选拔上岗。（6分）</td>
					<td width="20%" style="text-align:center">企业主要负责人、安全管理人员、特种作业人员未依法参加安全生产培训每一人扣2分，从业员工（包括派遣工）未参加上岗培训的每一人扣0.2分，特殊工种每一人无证上岗的扣1分。以上扣分项至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[19] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[19] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[19] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[19] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">企业夯实安全管理基础，加强基层班组负责人的安全培训。（3分）。</td>
					<td width="20%" style="text-align:center">检查企业年度内委派班组长培训情况，每应参加而未参加一人扣0.2分，至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[20] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[20] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[20] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[20] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">企业编有员工安全常识手册（或类似手册），员工理解并熟练掌握掌握其中内容。（2分）</td>
					<td width="20%" style="text-align:center">企业编有员工安全常识手册的得1分，员工熟练掌握手册内容的得1分，否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[21] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[21] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[21] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[21] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">积极组织开展安全生产月各项活动，有方案、有总结。（1分）</td>
					<td width="20%" style="text-align:center">企业开展安全生产月各项活动的得0.5分，有方案、有总结的得0.5分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[22] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[22] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[22] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[22] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">加分项： </td>
					<td width="20%" style="text-align:center">每年至少一次外聘专家或培训机构对员工进行安全知识讲座加1分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[23] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[23] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[23] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[23] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="7">8</td>
					<td width="10%" style="text-align:center" rowspan="7">全员参与</td>
					<td width="5%" style="text-align:center" rowspan="7">14</td>
					<td width="20%" style="text-align:center">员工熟知企业的安全管理情况，愿意主动积极参与到企业安全管理工作中来。（1分）</td>
					<td width="20%" style="text-align:center">抽查企业员工是否熟知企业的安全管理制度和安全现状，每一人不了解的扣0.2分，至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[24] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[24] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[24] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[24] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">从业人员熟知、理解企业的安全规章制度和岗位安全操作规程等，并严格正确执行，具有良好的安全作业行为规范。（4分）</td>
					<td width="20%" style="text-align:center">抽查企业员工是否熟练掌握和执行岗位操作规程，每一人不了解或不执行的扣0.5分，至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[25] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[25] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[25] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[25] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">为从业人员配备与作业环境和作业风险相匹配的安全防护用品，从业人员能按国家标准或行业标准要求自觉佩戴劳动保护用品。（2分）</td>
					<td width="20%" style="text-align:center">现场检查，每发现一位员工不按要求佩戴合适的劳动防护用品的扣0.5分，至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[26] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[26] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[26] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[26] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">员工熟悉反违章的要求，杜绝实际作业过程中的违章现象。（2分）</td>
					<td width="20%" style="text-align:center">现场检查，发现一例员工违章现象扣0.5分，至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[27] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[27] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[27] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[27] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">每年至少举办一次全员应急演练活动和风险（隐患或危险源）辨识活动。（2分）</td>
					<td width="20%" style="text-align:center">每年至少举办一次应急演练的得1分，每年至少举办一次风险辨识活动的得一分，否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[28] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[28] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[28] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[28] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">加强工伤事故管理，保障员工生命健康安全。（3分）</td>
					<td width="20%" style="text-align:center">检查企业年度内发生工伤事故的起数（以申报为准），每发生一起扣0.2分，至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[29] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[29] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[29] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[29] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">加分项： </td>
					<td width="20%" style="text-align:center">企业能够有效推行5S管理或STOP管理法（或类似的先进管理方法）的加2分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[30] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[30] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[30] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[30] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="3">9</td>
					<td width="10%" style="text-align:center" rowspan="3">安全诚信</td>
					<td width="5%" style="text-align:center" rowspan="3">4</td>
					<td width="20%" style="text-align:center">企业及主要负责人作出安全承诺，签订《安全生产承诺书》；《安全生产承诺书》格式规范，内容全面、具体，承诺人签字。（2分）</td>
					<td width="20%" style="text-align:center">企业及主要负责人签订《安全生产承诺书》的得1分，否则不得分；内容符合企业的实际情况、主要负责人签字的得1分，否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[31] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[31] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[31] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[31] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">企业积极履行社会责任，具有良好的社会形象。（2分）</td>
					<td width="20%" style="text-align:center">企业积极履行社会责任，没有社会负面新闻的得2分，否则不得分。年度内有社会投诉的每次扣0.2分，至本项扣完为止。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[32] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[32] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[32] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[32] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">加分项： </td>
					<td width="20%" style="text-align:center">企业按要求设置“五项公示”牌的加2分</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[33] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[33] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[33] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[33] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="2">10</td>
					<td width="10%" style="text-align:center" rowspan="2">安全激励</td>
					<td width="5%" style="text-align:center" rowspan="2">4</td>
					<td width="20%" style="text-align:center">制定安全绩效考核制度，设置明确的安全绩效考核指标，并把安全绩效考核纳入企业的收入分配制度。（2分）</td>
					<td width="20%" style="text-align:center">企业有健全的安全绩效考核制度的得1分，否则不得分；企业将安全绩效考核与员工收入分配挂钩的得1分，否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[34] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[34] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[34] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[34] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">对安全生产工作方面有突出表现的人员给予表彰奖励，树立榜样典型；对违反企业安全管理要求的员工采取必要的惩戒措施。（2分）</td>
					<td width="20%" style="text-align:center">企业对员工有安全生产奖励制度的得1分，否则不得分；企业有安全生产惩戒制度的得1分，否则不得分。</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[35] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[35] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[35] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[35] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center" rowspan="4">11</td>
					<td width="10%" style="text-align:center" rowspan="4">加分项</td>
					<td width="5%" style="text-align:center" rowspan="3">20</td>
					<td style="text-align:center" rowspan="3">上级表彰</td>
					<td width="20%" style="text-align:center">1.上年度企业获得安全生产领域区级表彰的加3分，市级表彰的加5分，省级及以上表彰的加10分。（不累加）</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[36] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[36] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[36] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[36] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">2.上年度企业员工获得安全生产领域区级表彰的加1分，市级表彰的加3分，省级及以上表彰的加5分。（不累加）</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[37] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[37] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[37] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[37] }" </td>
				</tr>
				<tr>
					<td width="20%" style="text-align:center">3.上年度企业或企业员工在省级及以上媒体发表安全生产类学术论文的，每一篇加1分。（可累加）</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[38] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[38] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[38] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[38] }" </td>
				</tr>
				<tr>
					<td width="5%" style="text-align:center">10</td>
					<td width="20%" style="text-align:center">智慧安监应用</td>
					<td width="20%" style="text-align:center">注册智慧安监管理系统的加5分；正常使用系统的加5分；（适用于2017年评定）</td>
					<td width="10%" style="text-align:center">${fn:split(enterpriseGrade.checkScore,',')[39] }</td>
					<td width="20%" style="text-align:center">${fn:split(enterpriseGrade.qyremark,',')[39] }</td>
					<td width="10%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:80%" name="enterpriseGrade.ajScore" value="${fn:split(enterpriseGrade.ajScore,',')[39] }" </td>
					<td width="15%" style="text-align:center;padding: 0 0 0 0;"><input type="text" style="text-align:center;width:90%" name="enterpriseGrade.remarks" value="${fn:split(enterpriseGrade.remarks,',')[39] }" </td>
				</tr>
				<tr>
					<td colspan="5" style="text-align:center">得分</td>
					<td colspan="2" style="text-align:center">${enterpriseGrade.zpzf}</td>
					<td colspan="2" style="text-align:center"><span id="score"></span></td>
				</tr>
				<tr>
					<td colspan="9" height="100px" style="text-align:center">
						<a href="#" class="btn_01" onclick="getScore();" >计算得分<b></b></a>&nbsp;
						<a href="#" class="btn_01" type="submit" >提交<b></b></a>&nbsp;
						<a href="#" class="btn_01"  onclick="parent.close_win('win_enterpriseGrade');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

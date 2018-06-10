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
		<div class="submitdata"  style="overflow:auto;width:100%;">
			<table width="100%">
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%">${jshxAqscb.qymc}</td>
					<th width="15%">填报月份</th>
					<td width="35%">${jshxAqscb.jshxYear}</td>
				</tr>
				<tr>
					<th width="15%">企业负责人</th>
					<td width="35%">${jshxAqscb.qyfzr}</td>
					<th width="15%">填表人</th>
					<td width="35%">${jshxAqscb.tbr}</td>
				</tr>
				<tr>
					<th width="15%">联系电话</th>
					<td width="35%">${jshxAqscb.telephone}</td>
					<th width="15%">填表时间</th>
					<td width="35%"><fmt:formatDate type="both" value="${jshxAqscb.tbsj}" /></td>
				</tr>
					<tr>
					<td colspan="4">
						<table width="100%" border="1">
							<tr>
								<th  width="5%" style="text-align:center;">序号</th>
								<th  width="8%" style="text-align:center;">类别</th>
								<th  width="20%" style="text-align:center;">项目</th>
								<th  width="8%" style="text-align:center;">本月实际支出数额（万元）</th>
								<th  width="8%" style="text-align:center;">上月实际支出数额（万元）</th>
								<th  width="20%" style="text-align:center;">备注</th>
							</tr>
							<tr>
								<th  width="5%" style="text-align:center;" rowspan="5">1</th>
								<th  width="5%" style="text-align:center;" rowspan="5">安全设施</th>
								<th  width="20%" style="text-align:center;">消防设施配备及维护费用</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_1}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_1}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_1}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">自控联锁和监控报警装置安装及维护费用</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_2}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_2}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_2}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">防雷接地设施安装及维护费用</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_3}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_3}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_3}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">特种设备安全维护和检测费用</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_4}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_4}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_4}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">安全防护装置安装及维护费用</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_5}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_5}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_5}</td>
							</tr>
							
							<tr>
								<th  width="5%" style="text-align:center;" rowspan="4">2</th>
								<th  width="5%" style="text-align:center;" rowspan="4">安全活动</th>
								<th  width="20%" style="text-align:center;">安全培训和宣传教育活动经费</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_6}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_6}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_6}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">安全生产综合奖励经费</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_7}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_7}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_7}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">安全隐患排查整改工作奖励经费</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_8}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_8}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_8}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">应急救援演练经费</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_9}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_9}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_9}</td>
							</tr>
							
							<tr>
								<th  width="5%" style="text-align:center;" rowspan="6">3</th>
								<th  width="5%" style="text-align:center;" rowspan="6">安全保障</th>
								<th  width="20%" style="text-align:center;">劳动防护用品购置费用</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_10}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_10}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_10}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">应急救援装备物资购置与储备经费</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_11}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_11}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_11}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">员工职业卫生体检费用</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_12}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_12}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_12}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">作业场所安全检测费用</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_13}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_13}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_13}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">安全生产评价与评估费用</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_14}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_14}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_14}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">专家检查咨询活动经费</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_15}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_15}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_15}</td>
							</tr>
							<tr>
								<th  width="5%" style="text-align:center;" rowspan="4">4</th>
								<th  width="5%" style="text-align:center;" rowspan="4">安全保险</th>
								<th  width="20%" style="text-align:center;">安全生产责任保险支出</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_16}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_16}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_16}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">社保工伤保险支出</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_17}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_17}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_17}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">员工人身意外伤害保险支出</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_18}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_18}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_18}</td>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">企业公众责任险支出</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_19}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_19}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_19}</td>
							</tr>
							
							<tr>
								<th  width="5%" style="text-align:center;">5</th>
								<th  width="5%" style="text-align:center;">安全治理</th>
								<th  width="20%" style="text-align:center;">用于隐患整改的改造投资</th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_20}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_20}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_20}</td>
							</tr>
							<tr>
								<th  width="5%" style="text-align:center;">6</th>
								<th  width="5%" style="text-align:center;">其他安全费用</th>
								<th  width="20%" style="text-align:center;"></th>
								<td  width="8%" style="text-align:center;">${aqscData01.data_21}</td>
								<td  width="8%" style="text-align:center;">${aqscData02.data_21}</td>
								<td  width="20%" style="text-align:center;">${aqscData03.data_21}</td>
							</tr>
							<tr>
								<th  width="5%" style="text-align:center;"></th>
								<th  width="5%" style="text-align:center;" colspan="2">合    计</th>
								<th  width="8%" style="text-align:center;">${aqscData01.data_22}</th>
								<th  width="8%" style="text-align:center;">${aqscData02.data_22}</th>
								<th  width="20%" style="text-align:center;">${aqscData03.data_22}</th>
							</tr>
							<tr>
								<td colspan="6" height="100px"   style="text-align:center;">
								    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

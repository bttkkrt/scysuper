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
					<th width="15%">填报月份</th>
					<td width="35%">${jshxQyjcb.jshxMonth}</td>
					<th width="15%">企业负责人</th>
					<td width="35%">${jshxQyjcb.manager}</td>
				</tr>
				<tr>
					<th width="15%">填表人</th>
					<td width="35%">${jshxQyjcb.tbr}</td>
					<th width="15%">联系电话</th>
					<td width="35%">${jshxQyjcb.telephone}</td>
				</tr>
				<tr>
					<th width="15%">填表时间</th>
					<td width="35%"><fmt:formatDate type="both" value="${jshxQyjcb.tbsj}" /></td>
				</tr>
					<tr>
					<td colspan="4">
						<table width="100%" style="border:1px solid #AABAA9;">
								<tr>
									<th style="text-align:center;" rowspan="2" style="width:170px;">隐患类别</th>
									<th style="text-align:center;" rowspan="2">上月结转待整改隐患</th>
									<th style="text-align:center;">&nbsp;</th>
									<th style="text-align:center;" rowspan="2">本月排查隐患</th>
									<th style="text-align:center;">&nbsp;</th>
									<th style="text-align:center;" rowspan="2">本月整改隐患</th>
									<th style="text-align:center;">&nbsp;</th>
									<th style="text-align:center;"rowspan="2">本月结转待整改隐患</th>
									<th style="text-align:center;">&nbsp;</th>
									<th style="text-align:center;" rowspan="2">本月投入整改资金</th>
									<th style="text-align:center;"></th>
									<th style="text-align:center;" rowspan="2">本年累计排查隐患</th>
									<th style="text-align:center;"></th>
									<th style="text-align:center;" rowspan="2">本年累计整改隐患</th>
									<th style="text-align:center;"></th>
									<th style="text-align:center;" rowspan="2">本年累计已投入整改资金</th>
									<th style="text-align:center;"></th>
								</tr>
								<tr>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
								</tr>
							<c:forEach items="${datas}" var="item" varStatus="status">
							      <tr> 
							      	<th style="width:170px;">${item}</th>
							      		<c:forEach   items="${qyjcbDatas}" var="data" varStatus="statu"> 
							     		<td style="width:50px;border:1px solid #AABAA9;">
											<c:if test="${status.index eq 0 }">${data.data_1}</c:if>
								     		<c:if test="${status.index eq 1}">${data.data_2}</c:if>
								     		<c:if test="${status.index eq 2}">${data.data_3}</c:if>
								     		<c:if test="${status.index eq 3}">${data.data_4}</c:if>
								     		<c:if test="${status.index eq 4}">${data.data_5}</c:if>
								     		<c:if test="${status.index eq 5}">${data.data_6}</c:if>
								     		<c:if test="${status.index eq 6}">${data.data_7}</c:if>
								     		<c:if test="${status.index eq 7}">${data.data_8}</c:if>
								     		<c:if test="${status.index eq 8}">${data.data_9}</c:if>
								     		<c:if test="${status.index eq 9}">${data.data_10}</c:if>
								     		<c:if test="${status.index eq 10}">${data.data_11}</c:if>
								     		<c:if test="${status.index eq 11}"><font  style="color:green;font-size:14px;font-weight:bold;">${data.data_12}</font></c:if>	
										</td>
							      	</c:forEach> 
							      <tr> 
							</c:forEach>
							<tr>
								<td colspan="17">
									本月班组岗位自查<font  style="color:blue;">&nbsp;${jshxQyjcb.count_1}&nbsp;</font>次、
									车间自查<font  style="color:blue;">&nbsp;${jshxQyjcb.count_2}&nbsp;</font>次、
									企业负责人检查<font  style="color:blue;">&nbsp;${jshxQyjcb.count_3}&nbsp;</font>次；
									专家检查<font  style="color:blue;">&nbsp;${jshxQyjcb.count_4}&nbsp;</font>次；
									安监部门检查<font  style="color:blue;">&nbsp;${jshxQyjcb.count_5}&nbsp;</font>次。
									本月参加安全培训<font  style="color:blue;">&nbsp;${jshxQyjcb.count_6}&nbsp;</font>人，
									其中法定培训<font  style="color:blue;">&nbsp;${jshxQyjcb.count_7}&nbsp;</font>人。
								</td>
							</tr>
							<tr>
								<td colspan="17">
									说明：本表统计包括企业自查、专家检查和部门检查发现的需要整改的安全生产隐患与问题。统计单位：隐患为“项”、资金为“万元”。
								</td>
							</tr>
						</table>
					</td>
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

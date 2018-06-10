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
					<th width="15%">年度</th>
					<td width="35%">${jshxSgtj.qynd}</td>
					<th width="15%">填表人</th>
					<td width="35%">${jshxSgtj.tbr}</td>
				</tr>
				<tr>
					<th width="15%">企业负责人</th>
					<td width="35%">${jshxSgtj.qyfzr}</td>
					<th width="15%">联系电话</th>
					<td width="35%">${jshxSgtj.telephone}</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%">
							<center>
								<tr>
									<th style="text-align:center;width:10%;">损工事故起数</th>
									<th style="text-align:center;width:10%;" colspan="2">非计划停止作业</th>
									<td style="text-align:center;width:5%;">${jshxSgtj.fjhqs}</td>
									<th style="text-align:center;width:10%;" colspan="2">损失工时</th>
									<td style="text-align:center;width:5%;">${jshxSgtj.fjhxs}</td>
									<th style="text-align:center;width:10%;" colspan="2">一般伤害事故</th>
									<td style="text-align:center;width:5%;">${jshxSgtj.shsgqs}</td>
									<th style="text-align:center;width:10%;" colspan="2">损失工时</th>
									<td style="text-align:center;width:5%;">${jshxSgtj.shsgxs}</td>
									<th style="text-align:center;width:10%;">工伤事故</th>
									<td style="text-align:center;width:5%;">${jshxSgtj.sgsgqs}</td>
									<th style="text-align:center;width:10%;">损失工时</th>
									<td style="text-align:center;width:5%;">${jshxSgtj.sgsgxs}</td>
								</tr>
								<tr>
									<th style="text-align:center;width:10%;" rowspan="2">伤害事故分级</th>
									<th style="text-align:center;width:10%;" colspan="2">伤害未遂事故</th>
									<th style="text-align:center;width:10%;" colspan="2">非损工事故</th>
									<th style="text-align:center;width:10%;" colspan="2">一般损工事故</th>
									<th style="text-align:center;width:10%;" colspan="2">轻伤事故</th>
									<th style="text-align:center;width:10%;" colspan="2">重伤事故</th>
									<th style="text-align:center;width:10%;" colspan="2">死亡事故</th>
									<th style="text-align:center;width:15%;" rowspan="2" colspan="2">主要人身伤害部位（按受伤部位分类或致病器官填写）</th>
									<th style="text-align:center;width:15%;" rowspan="2" colspan="2">发生伤害事故主要作业位置</th>
								</tr>
								
								<tr>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
								</tr>
							</center>
							<c:forEach items="${datas}" var="item" varStatus="status">
							      <tr> 
							      	<th style="width:10%;">${item}</th>
							      	<c:forEach  items="${sgtjDatas}" var="data" varStatus="statu"> 
							     			<c:if test="${statu.index ne  13 and statu.index ne 12}">
							     				<td style="width:5%;">
							     			</c:if>
							     			<c:if test="${statu.index eq  13 or statu.index eq 12}">
							     				<td style="width:15%;" colspan="2">
								     		</c:if>
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
								     		<c:if test="${status.index eq 11}">${data.data_12}</c:if>
								     		<c:if test="${status.index eq 12}">${data.data_13}</c:if>
							     		</td>
							     	 </c:forEach> 
							      <tr> 
							</c:forEach> 
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">平均职工人数</th>
					<td width="35%">${jshxSgtj.zgs}</td>
					<th width="15%">实际工作日</th>
					<td width="35%">${jshxSgtj.sjgzr}</td>
				</tr>
				<tr>
					<th width="15%">百万工时损工事故率</th>
					<td width="35%">${jshxSgtj.sgl}</td>
					<th width="15%">百万工时损工严重率</th>
					<td width="35%">${jshxSgtj.yzl}</td>
				</tr>
				<tr>
					<th width="15%">20万工时损工事故率</th>
					<td width="35%">${jshxSgtj.sgl2}</td>
					<th width="15%">20万工时损工严重率</th>
					<td width="35%">${jshxSgtj.yzl2}</td>
				</tr>
				<tr>
					<th width="15%">实际总工时</th>
					<td width="35%">${jshxSgtj.sjgs}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px"  style="text-align:center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

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
					<th width="15%">企业名称</th>
					<td width="35%" colspan="2">${zywsqk.qymc}</td>
					<th width="15%">组织机构代码</th>
					<td width="35%" colspan="2">${zywsqk.zzjgdm}</td>
				</tr>
				<tr>
					<th width="15%">注册地址</th>
					<td width="35%" colspan="2">${zywsqk.zcdz}</td>
					<th width="15%">工作场所地址</th>
					<td width="35%" colspan="2">${zywsqk.gzcsdz}</td>
				</tr>
				<tr>
					<th width="15%">法定代表人</th>
					<td width="35%" colspan="2">${zywsqk.fddbr}</td>
					<th width="15%">联系电话</th>
					<td width="35%" colspan="2">${zywsqk.fddbrlxdh}</td>
				</tr>
				<tr>
					<th width="15%">联系人</th>
					<td width="20%">${zywsqk.lxr}</td>
					<th width="15%">联系电话</th>
					<td width="15%">${zywsqk.lxrlxdh}</td>
					<th width="15%">统计年份</th>
					<td width="20%">${zywsqk.tjnf}</td>
				</tr>
				<tr>
					<th width="15%">所属行业</th>
					<td width="20%"><cus:hxlabel codeName="企业行业分类" itemValue="${zywsqk.hyfl}" /></td>
					<th width="15%">登记注册类型</th>
					<td width="15%"><cus:hxlabel codeName="注册类型" itemValue="${zywsqk.qyzclx}" /></td>
					<th width="15%">企业规模</th>
					<td width="20%"><cus:hxlabel codeName="企业规模" itemValue="${zywsqk.qygm}" /></td>
				</tr>
				<tr>
					<th width="15%">从业人员数(人)</th>
					<td width="20%">${zywsqk.cyrys}</td>
					<th width="15%">接触职业病危害因素人数(人)</th>
					<td width="15%">${zywsqk.jcrs}</td>
					<th width="15%">合同告知职业病危害人数(人)</th>
					<td width="20%">${zywsqk.htrs}</td>
				</tr>
				<tr>
					<th width="15%">建立职业健康监护档案人数(人)</th>
					<td width="20%">${zywsqk.jdrs}</td>
					<th width="15%">职业病危害作业岗位数(个)</th>
					<td width="15%">${zywsqk.zygws}</td>
					<th width="15%">设置警示标识岗位数(个)</th>
					<td width="20%">${zywsqk.szgws}</td>
				</tr>
				<tr>
					<th width="15%">应职业卫生培训人数(人)</th>
					<td width="20%">${zywsqk.yzrs}</td>
					<th width="15%">实际职业卫生培训人数(人)</th>
					<td width="15%">${zywsqk.sjrs}</td>
					<th width="15%">专职职业卫生管理人数(人)</th>
					<td width="20%">${zywsqk.zzrs}</td>
				</tr>
				<tr>
					<th width="15%">兼职职业卫生管理人数(人)</th>
					<td width="20%">${zywsqk.jzrs}</td>
					<th width="15%">应职业病危害预评价项目数(个)</th>
					<td width="15%">${zywsqk.yzxms}</td>
					<th width="15%">实际职业病危害预评价项目数(个)</th>
					<td width="20%">${zywsqk.sjxms}</td>
				</tr>
				<tr>
					<th width="15%">应职业病危害控制效果评价项目数(个)</th>
					<td width="20%">${zywsqk.yzpjxms}</td>
					<th width="15%">实际职业病危害控制效果评价项目数(个)</th>
					<td width="15%">${zywsqk.sjpjxms}</td>
					<th width="15%">职业病危害申报</th>
					<td width="20%"><cus:hxlabel codeName="职业病危害申报" itemValue="${zywsqk.zybwhsb}" /></td>
				</tr>
				<tr>
					<th width="15%" rowspan="2">主要负责人职业卫生培训</th>
					<td width="20%" rowspan="2"><cus:hxlabel codeName="主要负责人职业卫生培训" itemValue="${zywsqk.zyfzrzywspx}" /></td>
					<th width="32%" colspan="2" style="text-align:center">应职业健康检查人数(人)</th>
					<th width="33%" colspan="2" style="text-align:center">实际职业健康检查人数(人)</th>
				</tr>
				<tr>
					<td width="65%" colspan="4">
						<table width="100%">
							<tr>
								<td width="16%">岗前</th>
								<td width="16%">在岗</th>
								<td width="18%">离岗</th>
								<td width="16%">岗前</th>
								<td width="16%">在岗</th>
								<td width="18%">离岗</th>
							</tr>
							<tr>
								<td width="16%">${zywsqk.gqyzjcrs}</td>
								<td width="16%">${zywsqk.zgyzjcrs}</td>
								<td width="18%">${zywsqk.lgyzjcrs}</td>
								<td width="16%">${zywsqk.gqsjjcrs}</td>
								<td width="16%">${zywsqk.zgsjjcrs}</td>
								<td width="18%">${zywsqk.lgsjjcrs}</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th width="50%" colspan="3" style="text-align:center">新发职业病病例数(人)</th>
					<th width="50%" colspan="3" style="text-align:center">累计职业病病例数(人)</th>
				</tr>
				<tr>
					<td width="50%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">合计</th>
								<td width="20%">尘肺</th>
								<td width="20%">职业中毒</th>
								<td width="20%">噪声聋</th>
								<td width="20%">职业性皮肤病</th>
							</tr>
							<tr>
								<td width="20%">${zywsqk.xfhj}</td>
								<td width="20%">${zywsqk.xfcf}</td>
								<td width="20%">${zywsqk.xfzd}</td>
								<td width="20%">${zywsqk.xfsz}</td>
								<td width="20%">${zywsqk.xfpfb}</td>
							</tr>
						</table>
					</td>
					<td width="50%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">合计</th>
								<td width="20%">尘肺</th>
								<td width="20%">职业中毒</th>
								<td width="20%">噪声聋</th>
								<td width="20%">职业性皮肤病</th>
							</tr>
							<tr>
								<td width="20%">${zywsqk.ljhj}</td>
								<td width="20%">${zywsqk.ljcf}</td>
								<td width="20%">${zywsqk.ljzd}</td>
								<td width="20%">${zywsqk.ljsl}</td>
								<td width="20%">${zywsqk.ljpfb}</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="6">
						<table width="100%">
							<tr>
								<td width="40%" colspan="2" style="text-align:center">职业病危害因素</td>
								<td width="20%" style="text-align:center">接触人数(人)</td>
								<td width="20%" style="text-align:center">检测点数（个)</td>
								<td width="20%" style="text-align:center">达标点数(个)</td>						
							</tr>
							<tr>
								<td width="20%">1.粉尘(小计)</td>
								<td width="20%">FC</td>
								<td width="20%">${zywsqk.fcjcrshj}</td>
								<td width="20%">${zywsqk.fcjcdshj}</td>
								<td width="20%">${zywsqk.fcdbdshj}</td>						
							</tr>
							<c:forEach var="zywhglb" items="${fcList}">
								<tr>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhmc}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhid}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.jcrs}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.jcds}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.dbds}</td>
								</tr>
							</c:forEach>
							<tr>
								<td>2.化学毒物(小计)</td>
								<td>HX</td>
								<td>${zywsqk.hxjcrshj}</td>
								<td>${zywsqk.hxjcdshj}</td>
								<td>${zywsqk.hxdbdshj}</td>						
							</tr>
							<c:forEach var="zywhglb" items="${hxList}">
								<tr>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhmc}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhid}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.jcrs}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.jcds}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.dbds}</td>
								</tr>
							</c:forEach>
							<tr>
								<td>3.物理因素(小计)</td>
								<td>WL</td>
								<td>${zywsqk.wljcrshj}</td>
								<td>${zywsqk.wljcdshj}</td>
								<td>${zywsqk.wldbdshj}</td>						
							</tr>
							<c:forEach var="zywhglb" items="${wlList}">
								<tr>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhmc}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhid}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.jcrs}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.jcds}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.dbds}</td>
								</tr>
							</c:forEach>
							<tr>
								<td>4.生物因素(小计)</td>
								<td>SW</td>
								<td>${zywsqk.swjcrshj}</td>
								<td>${zywsqk.swjcdshj}</td>
								<td>${zywsqk.swdbdshj}</td>						
							</tr>
							<c:forEach var="zywhglb" items="${swList}">
								<tr>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhmc}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhid}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.jcrs}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.jcds}</td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.dbds}</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">负责人</th>
					<td width="20%">${zywsqk.fzr}</td>
					<th width="15%">填表人</th>
					<td width="15%">${zywsqk.tbr}</td>
					<th width="15%">联系电话</th>
					<td width="20%">${zywsqk.tbrlxdh}</td>
				</tr>
				<tr>
					<td colspan="6" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

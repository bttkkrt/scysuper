<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body >
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata" style="overflow-y: scroll;overflow:auto">
			<table width="100%">
				<tr>
					<th width="15%">单位名称</th>
					<td width="85%" colspan="3">${company.companyname}</td>
				</tr>
				<tr>
					<th width="15%">法定代表人</th>
					<td width="35%">${company.fddbr}</td>
					<th width="15%">法定代表人联系号码</th>
					<td width="35%">${company.fddbrlxhm}</td>
				</tr>
				<tr>
					<th width="15%">所属区域</th>
					<td width="35%">${company.dwdz}${company.szcname}</td>
					<th width="15%">详细地址</th>
					<td width="35%">${company.dwdz2}</td>
				</tr>
				<tr>
					<th width="15%">工商注册号</th>
					<td width="35%">${company.gszch}</td>
					<th width="15%">企业类型</th>
					<td width="35%"><cus:hxlabel codeName="企业类型" itemValue="${company.qylx}" /></td>
				</tr>
				<tr>
					<th width="15%">行业分类</th>
					<td width="35%"><cus:hxlabel codeName="行业分类" itemValue="${company.hyfl}" /></td>
					<th width="15%">安全管理员</th>
					<td width="35%">${company.lxr}</td>
				</tr>
				<tr>
					<th width="15%">手机号码</th>
					<td width="35%">${company.mobile}</td>
					<th width="15%">联系方式</th>
					<td width="35%">${company.lxfs}</td>
				</tr>
				<tr>
					<th width="15%">组织机构代码</th>
					<td width="35%">${company.zzjgdm}</td>
					<th width="15%">企业邮箱</th>
					<td width="35%">${company.qyyx}</td>
    			</tr>						
				<tr>
					<th width="15%">企业规模</th>
					<td width="35%"><cus:hxlabel codeName="企业规模" itemValue="${company.qygm}" /></td>
					<th width="15%">企业成立时间</th>
					<td width="35%"><fmt:formatDate type="date" value="${company.qyclsj}" /></td>
    			</tr>
				<tr>
					<th width="15%">企业注册类型</th>
					<td width="35%"><cus:hxlabel codeName="企业注册类型" itemValue="${company.qyzclx}" /></td>
					<th width="15%">注册资金（万元）</th>
					<td width="35%">${company.zczj}</td>
				</tr>
				<tr>
					<th width="15%">是否危化品企业类型</th>
					<td width="35%">	
						<s:if test="company.ifwhpqylx==1">		
		 					<input type="radio">否
                  			<input type="radio" checked="checked">是
                 		</s:if>
          				<s:else>
           					<input type="radio" checked="checked">否
             				<input type="radio">是
                 		</s:else> 
                 	</td>
                 	<td width="50%" colspan="2">
		 				<cus:hxmulselectlabel codeName="危化品企业类型" itemValue="${company.whpqylx}" />
					</td>
				</tr>
				<tr>
					<th width="15%">是否职业危害企业类型</th>
					<td width="35%">
						<s:if test="company.ifzywhqylx==1">
		 					<input type="radio">否
                  			<input type="radio" checked="checked">是
                  		</s:if>
                		<s:else>
                  			<input type="radio" checked="checked">否
                  			<input type="radio">是
                  		</s:else>
                 	</td>
                 	<td width="50%" colspan="2">
                  		<cus:hxmulselectlabel codeName="职业危害企业类型" itemValue="${company.zywhqylx}" />
					</td>
				</tr>
				<tr>
					<th width="15%">是否烟花爆竹经营企业</th>
					<td width="35%">
						<s:if test="company.ifyhbzjyqy==1">
		 					<input type="radio">否
                  			<input type="radio" checked="checked">是
                  		</s:if>
                  		<s:else>
                  			<input type="radio" checked="checked">否
                  			<input type="radio">是
                  		</s:else>
                	</td>
                 	<td width="50%" colspan="2">
                  		<cus:hxmulselectlabel codeName="烟花爆竹企业" itemValue="${company.yhbzjyqy}" />
				 	</td>
				</tr>
				<tr>
					<th width="15%">固定电话</th>
					<td width="35%">${company.gddh}</td>
					<th width="15%">传       真</th>
					<td width="35%">${company.cz}</td>
				</tr>
				<tr>
					<th width="15%">邮政编码</th>
					<td width="35%">${company.yzbm}</td>
					<th width="15%">上年销售收入（万元）</th>
					<td width="35%">${company.snxssr}</td>
				</tr>
				<tr>
					<th width="15%">上年上交税收（万元）</th>
					<td width="35%">${company.snsjss}</td>
					<th width="15%">上年固定资产（万元）</th>
					<td width="35%">${company.sngdzc}</td>
				</tr>
				<tr>
					<th width="15%">上年安全投入（万元）</th>
					<td width="35%">${company.snwqtr}</td>
					<th width="15%">上年安全生产费用提取数（万元）</th>
					<td width="35%">${company.snaqscf}</td>
				</tr>
				<tr>
					<th width="15%">是否设立安全管理机构</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${company.sfaqjg}" /></td>
					<th width="15%">安全管理员（人）</th>
					<td width="35%">${company.aqglr}</td>
				</tr>
				<tr>
					<th width="15%">是否设立职业卫生管理机构</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${company.sfzywsjg}" /></td>
					<th width="15%">职业卫生管理人员（人）</th>
					<td width="35%">${company.zywsglry}</td>
				</tr>
				<tr>
					<th width="15%">否为专职或兼职职业卫生管理员</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${company.sfqzwsgly}" /></td>
					<th width="15%">占地面积（m2）</th>
					<td width="35%">${company.zdmj}</td>
				</tr>
				<tr>
					<th width="15%">建筑面积（m2）</th>
					<td width="35%">${company.jzmj}</td>
					<th width="15%">从业人员（人）</th>
					<td width="35%">${company.cyry}</td>
				</tr>
				<tr>
					<th width="15%">是否有员工宿舍</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${company.sfyygss}" /></td>
					<th width="15%">安全生产标准化达标级别</th>
					<td width="35%"><cus:hxlabel codeName="标准化达标级别" itemValue="${company.aqbzdbjb}" /></td>
				</tr>
				<tr>
					<th width="15%">经营范围</th>
					<td width="65%" colspan=3>
						<textarea id="company.jyfw" disabled
							name="company.jyfw" rows="5"
							style="width: 80%">${company.jyfw}
						</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">网址</th>
					<td width="35%">
						<s:if test="company.ifurl==1">
							<input type="radio">无
                  			<input type="radio" checked="checked">有
                  		</s:if>
                  		<s:else>
                  			<input type="radio" checked="checked">无
                  			<input type="radio">有
                  		</s:else>
                 	</td>
                	<td width="50%" colspan="2">
						${company.url}
					</td>
				</tr>
				<s:if test="company.basePass!=1&&type==1" >
				<tr>
					<th width="15%" style="color:red">审核</th>
					<td width="35%"><cus:hxlabel codeName="审核结果" itemValue="${company.basePass}" /></td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan=3>
						<textarea id="baseRemark"
							name="company.baseRemark" rows="5"
							style="width: 80%">${company.baseRemark}
						</textarea><font style="color:red">*(若未通过，请填写原由。)</font>
					</td>
				</tr>
				</s:if>
				<s:else>
				<tr>
					<th width="15%" style="color:red">基本信息审核状态</th>
					<td width="35%" style="color:red"><cus:hxlabel codeName="基本信息审核状态" itemValue="${company.basePass}" /></td>
				</tr>
				<s:if test="company.basePass==2" >
				<tr>
					<th width="15%">备注</th>
					<td width="65%" colspan=3>
						<textarea id="baseRemark" disabled
							name="company.baseRemark" rows="5"
							style="width: 80%">${company.baseRemark}
						</textarea>
					</td>
				</tr>
				</s:if>
				</s:else>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=XA9GjGiTQiDS54abo0ga2iEd"></script>
	
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
			var map;
    		$(function(){
    			// 百度地图API功能
				map = new BMap.Map("allmap");
    			var longitude ="${wzcompany.longitude}";
    			var latitude = "${wzcompany.latitude}";
    			if(longitude != null && longitude != "")
    			{
    				var point = new BMap.Point(longitude, latitude);
					map.centerAndZoom(point, 16);
					var marker = new BMap.Marker(point);  // 创建标注
					map.addOverlay(marker);              // 将标注添加到地图中
    			}
    			else
    			{
    				var myCity = new BMap.LocalCity();
					myCity.get(myFun);
    			}
    		});
    		
    	function myFun(result){
   	 		var cityName = result.name;
    		map.centerAndZoom(cityName,12);
		}
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">企业名称</th>
					<td width="85%" colspan="3">${wzcompany.companyname}</td>				
				</tr>
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%"  colspan="3">${wzcompany.szzname}</td>
					<!-- 
					<th width="15%">所在村</th>
					<td width="35%">${wzcompany.szcname}</td>
					 -->
				</tr>
				<tr>
				 	<th width="15%">负责人</th>
					<td width="35%">${wzcompany.fzr}</td>
					<th width="15%">手机</th>
					<td width="35%">${wzcompany.mobile}</td>
				</tr>
				<tr>
					<th width="15%">传       真</th>
					<td width="35%">${wzcompany.cz}</td>
					<th width="15%">身份证号码</th>
					<td width="35%">${wzcompany.sfz}</td>
				</tr>
				<tr>
					<th width="15%">行业分类</th>
					<td width="35%"><cus:hxlabel codeName="企业行业分类" itemValue="${wzcompany.hyfl}" /></td>
					<th width="15%">是否为五小企业</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${wzcompany.ifwxqy}" /></td>
				</tr>
				<tr>
					<th width="15%">是否危化品企业类型</th>
					<td width="35%">			
					 	<s:if test="wzcompany.ifwhpqylx==1">
		 					<input type="radio">否
                  			<input type="radio" checked="checked">是
                  		</s:if>
                		<s:else>
                  			<input type="radio" checked="checked">否
                  			<input type="radio">是
                  		</s:else>
                    </td>
                    <td width="50%" colspan="2">
                    	<cus:hxmulselectlabel codeName="危化品企业类型" itemValue="${wzcompany.whpqylx}" />
					</td>
				</tr>
				<tr>
			    	<th width="15%">是否职业危害企业类型</th>
					<td width="35%">
					 	<s:if test="wzcompany.ifzywhqylx==1">
		 					<input type="radio">否
                  			<input type="radio" checked="checked">是
                  		</s:if>
                		<s:else>
                  			<input type="radio" checked="checked">否
                  			<input type="radio">是
                  		</s:else>
                    </td>
                    <td width="50%" colspan="2">
                    	<cus:hxmulselectlabel codeName="职业危害企业类型" itemValue="${wzcompany.zywhqylx}" />
					</td>
				</tr>
				<tr>
					<th width="15%">员工数</th>
					<td width="35%">${wzcompany.ygs}（人）</td>
					<th width="15%">年销售收入</th>
					<td width="35%">${wzcompany.nxssr}（万元）</td>
				</tr>
				<tr>
					<th width="15%">占地面积</th>
					<td width="35%">${wzcompany.zdmj}（m2）</td>
					<th width="15%">建筑面积</th>
					<td width="35%">${wzcompany.jzmj}（m2）</td>
				</tr>
				<tr>
					<th width="15%">经营场所性质</th>
					<td width="35%"><cus:hxlabel codeName="经营场所性质" itemValue="${wzcompany.jycsxz}" /></td>
				 	<th width="15%">房东姓名</th>
					<td width="35%">${wzcompany.fdxm}</td>
				</tr>
				<tr>
					<th width="15%">房东手机</th>
					<td width="35%">${wzcompany.fdsjh}</td>
					<th width="15%">房东身份证号码</th>
					<td width="35%">${wzcompany.fdsfz}</td>
				</tr>
				<tr>
					<th width="15%">经营范围</th>
					<td width="85%" colspan="3">
						<textarea id="wzcompany.jyfw" disabled
							name="wzcompany.jyfw" rows="5"
							style="width: 80%">${wzcompany.jyfw}
						</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<div id="allmap" style="height:300px;"></div>
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

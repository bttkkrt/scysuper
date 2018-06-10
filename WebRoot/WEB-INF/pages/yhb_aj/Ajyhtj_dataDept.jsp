<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title></title>
	<script>
	  function qyTotal(dwdz,flags){
	   
	    var url="/jsp/zcyhsb/turnToCompany.action?szzid="+dwdz+"&jshxZcyhsb.szzid=${jshxZcyhsb.szzid}&jshxZcyhsb.qymc="+encodeURIComponent('${jshxZcyhsb.qymc}')+"&jshxZcyhsb.whpqylx=${jshxZcyhsb.whpqylx}&jshxZcyhsb.aqbzdbjb=${jshxZcyhsb.aqbzdbjb}&jshxZcyhsb.ifzywhqylx=${jshxZcyhsb.ifzywhqylx}&flags="+flags;
     	var id="newWindow1";
		var text = "查看企业总数信息";
	
		window.parent.addTab(id,text,url);
	  }
	  function Sbqy(dwdz,flags){
	  
	    var url="/jsp/zcyhsb/turnToCompany.action?szzid="+dwdz+"&jshxZcyhsb.szzid=${jshxZcyhsb.szzid}&jshxZcyhsb.qymc="+encodeURIComponent('${jshxZcyhsb.qymc}')+"&jshxZcyhsb.whpqylx=${jshxZcyhsb.whpqylx}&jshxZcyhsb.aqbzdbjb=${jshxZcyhsb.aqbzdbjb}&jshxZcyhsb.ifzywhqylx=${jshxZcyhsb.ifzywhqylx}&flags="+flags+"&starttime=${starttime}&endtime=${endtime}";
     	var id="newWindow2";
		var text = "查看上报企业信息";
		window.parent.addTab(id,text,url);
	  
	  }
	  function Wsbqy(dwdz,flags){
	  
	    var url="/jsp/zcyhsb/turnToCompany.action?szzid="+dwdz+"&jshxZcyhsb.szzid=${jshxZcyhsb.szzid}&jshxZcyhsb.qymc="+encodeURIComponent('${jshxZcyhsb.qymc}')+"&jshxZcyhsb.whpqylx=${jshxZcyhsb.whpqylx}&jshxZcyhsb.aqbzdbjb=${jshxZcyhsb.aqbzdbjb}&jshxZcyhsb.ifzywhqylx=${jshxZcyhsb.ifzywhqylx}&flags="+flags+"&starttime=${starttime}&endtime=${endtime}";
     	var id="newWindow2";
		var text = "查看未上报企业信息";
		window.parent.addTab(id,text,url);
	  
	  }
	   
	
	</script>
		<style>
	th
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 2px 0 0;
	}
	td
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 0 0 2px;
		background:#fff
	}
	tr
	{
		border:1px solid #d5dbdc;
		height:24px
	}
	</style>
</head>
<body >
                <input type="hidden" name="jshxZcyhsb.szzid" value="${jshxZcyhsb.szzid}">
				<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0;">
				<table style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
					<tr>
						<th>序号</th>
						<c:if test="${tongjiType=='dept' }"><th>部门及镇、街道</th></c:if>
						<c:if test="${tongjiType=='grid' }"><th>网格名称</th></c:if>
						<th>企业总数</th>
						<!--<th>辖区内企业（单位）数</th>
						--><th>上报企业（单位数）</th>
						<th>未上报企业（单位数）</th>
						<th>上报次数</th>
						<!--<th>隐患排查数</th>
						--><th>整改隐患数</th>
						<th>未完成隐患数</th>
						<th>整改率</th>
					</tr>
					<s:iterator value="tjyhList" id="tjyh" status="sta">
						<tr>
							<td><s:property value='#sta.count'/></td>
							<td><s:property value='#tjyh.dwdz'/></td>
							<td><s:property value='#tjyh.qynum'/></td>
							<!--<td><s:property value='#tjyh.qyTotal'/></span></td>
							--><td><s:property value='#tjyh.sbqy'/></span></td>
							<td><s:property value='#tjyh.wsbqy'/></span></td>
							<td><s:property value='#tjyh.sbcs'/></td>
							<!--<td><s:property value='#tjyh.sbcs'/></td>
							--><td><s:property value='#tjyh.zgwc'/></td>
							<td><s:property value='#tjyh.zgwwc'/></td>
							<td><s:property value='#tjyh.zgl'/></td>
						</tr>
					</s:iterator>
					<tr>
						<th colspan="2">合计</th>
						<th>${tjyhBean.qynum}</th>
						<!--<th>${tjyhBean.qyTotal}</span></th>
						--><th>${tjyhBean.sbqy}</a></th>
						<th>${tjyhBean.wsbqy}</a></th>
						<th>${tjyhBean.sbcs}</th>
						<!--<th>${tjyhBean.sbcs}</th>
						--><th>${tjyhBean.zgwc}</th>
						<th>${tjyhBean.zgwwc}</th>
						<th>${tjyhBean.zgl}</th>
					</tr>
				</table>
			</div>
</body>
</html>

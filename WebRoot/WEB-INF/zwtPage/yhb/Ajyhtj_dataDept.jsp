<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<script src="${ctx}/webResources/zwt/layer/layer.js"></script>


	<script>
	  function Sbcs(dwdz,flags){
	  	  var dt=new Date();
	  	  createSimpleWindow("win_secProIns","查看隐患情况","${ctx}/zwt/aqscyhView.action?troMan.areaId="+dwdz+"&flag=1"+ flags + "&dt="+dt.getTime()+"&starttime=${starttime}&endtime=${endtime}",1100,500);
	  }
	  
	  
	  function Sbqy(dwdz,flags){
	  	  var dt=new Date();
	  	  createSimpleWindow("win_secProIns","查看上报企业情况","${ctx}/zwt/yhcomView.action?troMan.areaId="+dwdz+"&flag=10"+ flags + "&dt="+dt.getTime()+"&starttime=${starttime}&endtime=${endtime}",1100,500);
	  }
	  
	  function Wsbqy(dwdz,flags){
	  	  var dt=new Date();
	  	  createSimpleWindow("win_secProIns","查看未上报企业情况","${ctx}/zwt/yhcomView.action?troMan.areaId="+dwdz+"&flag=11"+ flags + "&dt="+dt.getTime()+"&starttime=${starttime}&endtime=${endtime}",1100,500);
	  }
	  
	  function createSimpleWindow(a,b,c,d,e){
        	var dt=new Date();
        	var index = layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: b,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: c
		    });
		    layer.full(index);
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
	<!-- Wrap all page content here -->
	<div class="sipac_wrap" >
<!-- head style -->

<!-- body style -->
        <div class="sipac_body" >
            <div class="sipac_container" id="tableDiv" style="margin: 0 0 0 0px;">
            	<div class="sipac_tableBox">
                	<table style="width:100%;">
                    	<thead>
                        <tr>
                        	<th>序号</th>
							<c:if test="${tongjiType=='dept' }"><th>街道</th></c:if>
							<c:if test="${tongjiType=='grid' }"><th>网格名称</th></c:if>
							<th>企业总数</th>
							<th>上报企业（单位数）</th>
							<th>未上报企业（单位数）</th>
							<th>上报次数</th>
							<th>整改隐患数</th>
							<th>未完成隐患数</th>
							<th>整改率</th>
                        </tr>
  						</thead>
                    	<tbody id="tbody">
                    		<s:iterator value="tjyhList" id="tjyh" status="sta">
							<tr>
								<td><s:property value='#sta.count'/></td>
								<td><s:property value='#tjyh.dwdz'/></td>
								<td><s:property value='#tjyh.qynum'/></td>
								<td><span style='color:blue;cursor:pointer' onclick="Sbqy('${tjyh.szzid}','${tongjiType}');"><s:property value='#tjyh.sbqy'/></span></td>
								<td><span style='color:blue;cursor:pointer' onclick="Wsbqy('${tjyh.szzid}','${tongjiType}');"><s:property value='#tjyh.wsbqy'/></span></td>
								<td><span style='color:blue;cursor:pointer' onclick="Sbcs('${tjyh.szzid}','${tongjiType}');"><s:property value='#tjyh.sbcs'/></span></td>
								<td><s:property value='#tjyh.zgwc'/></td>
								<td><s:property value='#tjyh.zgwwc'/></td>
								<td><s:property value='#tjyh.zgl'/></td>
							</tr>
							</s:iterator>
						<tr>
							<th colspan="2">合计</th>
							<th>${tjyhBean.qynum}</th>
							<th><span style='color:blue;cursor:pointer' onclick="Sbqy('','${tongjiType}');">${tjyhBean.sbqy}</span></th>
							<th><span style='color:blue;cursor:pointer' onclick="Wsbqy('','${tongjiType}');">${tjyhBean.wsbqy}</span></th>
							<th><span style='color:blue;cursor:pointer' onclick="Sbcs('','${tongjiType}');">${tjyhBean.sbcs}</span></th>
							<th>${tjyhBean.zgwc}</th>
							<th>${tjyhBean.zgwwc}</th>
							<th>${tjyhBean.zgl}</th>
						</tr>
  						</tbody>
                    </table>
            	</div>
            </div>
        </div>
    </div> 
</body>
</html>

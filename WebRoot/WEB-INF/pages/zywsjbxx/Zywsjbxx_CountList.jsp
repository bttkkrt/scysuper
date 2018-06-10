<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>职业病危害企业统计</title>
    
	<%@include file="/common/jsLib.jsp"%>
	<script>
        
        //清除查询表单中的搜索条件
        function clear_form(ff){
            var elements = ff.elements;
            for(i=0;i<elements.length;i++){
                var element = elements[i];
                if(element.type=="text"){
                    element.value = "";
                }else if(element.type=="radio" || element.type=="checkbox"){
                	element.checked = false;
                }else if(element.options!=null){
                	element.options[0].selected  = true;
                }
            }
        }
        
        function search_tj(){
        	document.myform.action = "${ctx}/jsp/zywsjbxx/zywsjbxxCountList.action";
        	document.myform.submit();
        }
        
        function exportData()
        {
        	document.myform.action = "${ctx}/jsp/zywsjbxx/zywsjbxxCountListExport.action";
        	document.myform.submit();
        }
        
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
<form name="myform" method="post">
<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				 <th width="15%">开始时间</th>
				 <td width="35%">
				 	<input name="queryStartTime" id="queryStartTime" value="${queryStartTime}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryEndTime\')}'})" >
				 </td>
				 <th width="15%">结束时间</th>
				 <td width="35%">
					<input name="queryEndTime" id="queryEndTime" value="${queryEndTime}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryStartTime\')}'})" ></td>
				 </td>
			</tr>
			<tr>
				<td colspan="4" align="center" style="text-align:center;"><h1>
				<a href="###" class="easyui-linkbutton" onclick="search_tj()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;			
				<a href="###" class="easyui-linkbutton" onclick="exportData()" iconCls="icon-add">导出</a>&nbsp;	
				</td>
			</tr>
		</table>
	</div>
</form>
	<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0">
		<table style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
			<tr style="border:1px solid #d5dbdc;height:24px">
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">属地</th>
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">企业总数</th>
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">职工人数</th>
				<th width="15%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">接触职业危害人数</th>
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">职业病人数</th>
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">检测点数</th>
				<th width="12%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">监测点合格率</th>
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">体检员工数</th>
				<th width="13%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">体检异常人数</th>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">合计</td>
				<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${zybwhqytj.qyzs}</td>
				<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${zybwhqytj.zgrs}</td>
				<td width="15%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${zybwhqytj.jcrs}</td>
				<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${zybwhqytj.zybrs}</td>
				<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${zybwhqytj.jcds}</td>
				<td width="12%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${zybwhqytj.jcdhgl}%</td>
				<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${zybwhqytj.tjygs}</td>
				<td width="13%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${zybwhqytj.tjycs}</td>
			</tr>
			<c:forEach var="item" items="${zybwhqytjList}">
				<tr style="border:1px solid #d5dbdc;height:24px">
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.szzname}</td>
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.qyzs}</td>
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.zgrs}</td>
					<td width="15%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.jcrs}</td>
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.zybrs}</td>
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.jcds}</td>
					<td width="12%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff" >${item.jcdhgl}%</td>
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.tjygs}</td>
					<td width="13%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.tjycs}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

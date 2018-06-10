<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>按企业列表</title>
    
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
        	document.myform.action = "${ctx}/jsp/zycsjcry/qylbList.action";
        	document.myform.submit();
        }
        
        function exportData()
        {
        	document.myform.action = "${ctx}/jsp/zycsjcry/qylbListExport.action";
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
				 <th width="15%">单位名称</th>
				 <td width="35%">
				 	<input name="qylb.qymc" id="qymc" value="${qylb.qymc}" type="text">
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
				<th rowspan="2" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">企业名称</th>
				<th rowspan="2" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">劳动者总人数</th>
				<th rowspan="2" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">职业病累计人数</th>
				<th colspan="6" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">接触职业病危害人数</th>
			</tr>
			<tr style="border:1px solid #d5dbdc;height:24px">
				<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">总人数</th>
				<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">粉尘类</th>
				<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">化学性</th>
				<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">物理性</th>
				<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">放射性</th>
				<th style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">其他类</th>
			</tr>
			<c:forEach var="item" items="${qylbList}">
				<tr style="border:1px solid #d5dbdc;height:24px">
					<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.qymc}</td>
					<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.ldzzs}</td>
					<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.zybrs}</td>
					<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.jcrs}</td>
					<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.fcrs}</td>
					<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.hxrs}</td>
					<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff" >${item.wlrs}</td>
					<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.fsrs}</td>
					<td style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.qtrs}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

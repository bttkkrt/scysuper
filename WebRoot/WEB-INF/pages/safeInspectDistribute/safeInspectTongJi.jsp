<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>安全生产行政执法文书使用情况</title>
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
        
        function search_aqscfk(){
        	document.myform.action = "${ctx}/jsp/aqscfk/aqscfkReport2.action";
        	document.myform.submit();
        }
      
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<div class="submitdata">
		<table width="100%">
			<tr>
				 
				<th width="15%">上报时间</th>
				<td width="35%"><input name="queryJasjStart" id="queryJasjStart" value="<fmt:formatDate type='date' value='${queryJasjStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJasjEnd\')}'})" >
					-<input name="queryJasjEnd" id="queryJasjEnd" value="<fmt:formatDate type='date' value='${queryJasjEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJasjStart\')}'})" ></td>
				<th width="15%">所在镇</th>
					<td width="35%"><cus:SelectOneTag property="aqscfk.szzid" defaultText='请选择' codeName="地址" value="${aqscfk.szzid}" /></td>
			</tr>
		  
			<tr>
				<td colspan="4" style="text-align:center">
				<a href="###" class="easyui-linkbutton" onclick="" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				</td>
			</tr>
		</table>
	</div>
</form>
<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0">
		<table style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
			<tr style="border:1px solid #d5dbdc;height:24px">
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">安全检查任务</th>
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">计划检查数</th>
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">已检查数</th>
				<th width="15%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">未检查数</th>
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">合格数</th>
				<th width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">未合格数</th>
				<th width="12%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 2px 0 0;">合格率</th>
			</tr>
			<c:forEach var="item" items="${datas}">
				<tr style="border:1px solid #d5dbdc;height:24px">
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.title}</td>
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.total}</td>
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.donenum}</td>
					<td width="15%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.notnum}</td>
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.rightnum}</td>
					<td width="10%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff">${item.wrongnum}</td>
					<td width="12%" style="border:1px solid #d5dbdc;height:24px;text-align:center;padding:0 0 0 2px;background:#fff" >${item.goodrate}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
	$(function(){
		var temp = "";
		$.ajax({
		    url: '${ctx}/jsp/admin/code/findCodeValue.action',
		    type: 'post',
		    dataType: 'json',
		    async : false,
		    data:{        
		    	"codeValue.itemValue" : ${inspectItem.inspectType},
		        "codeValue.codeId" : "402881e7497d66f001497d7f65a40007"    
		    },
		    error: function(){
		        $.messager.alert('提示','获取一维代码错误！');
		    },
		    success: function(data){
	        	temp = data.itemText;
	    	}
		});
		$("#inspectType").html(temp);
		
	});
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">检测类型标识</th>
					<td width="35%" colspan="3" id="inspectType"></td>
				</tr>
				<tr>
					<th width="15%">巡检项</th>
					<td width="35%" colspan="3" >${inspectItem.item}</td>
				</tr>
				<tr>
					<th width="15%">巡检要求</th>
					<td width="35%" colspan="3" >${inspectItem.requirement}</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align: center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

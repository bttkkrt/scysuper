<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<%@ taglib prefix="ww" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>生成代码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@include file="/common/jsLib.jsp"%>
<script>
        
         function genCode()
        {
        	//parent.$("#win").attr("src","${ctx}/jsp/admin/form/genCode.action?tableIds=${param.thistableIds}");
             //parent.close_win('formWindow');
				document.myform.action = "${ctx}/jsp/admin/form/genCode.action";
		        document.myform.submit();
			
        }
        
         function genSimpleCode()
        {
        
				document.myform.action = "${ctx}/jsp/admin/form/genSimpleCode.action?genType=1";
		        document.myform.submit();
			
        }
        
        function genSimpleCode2()
        {
        	parent.$("#win").attr("src","${ctx}/jsp/admin/form/genCode.action?genSimpleCode.action?genType=2&flowType=-1&tableIds=${param.thistableIds}");
            parent.close_win();
            
				//document.myform.action = "genSimpleCode.action?genType=2&&flowType=-1";
		        //document.myform.submit();
			
        }
        
        function genSimpleCode3()
        {
            
				popupCenter("<%=basePath%>/bizProc/selectWorkflow.action?selectType=1", "Category", "800", "600", 
                    "no", "no", "no", "yes", "yes","no");
				
			
        }
        
        function getSelectFlowType(flowtype)
        {
        		
        		document.myform.action = "${ctx}/jsp/admin/form/genSimpleCode.action?genType=2&&flowType="+flowtype;
		        document.myform.submit();
        }
        
    </script>
</head>

<body>
	<div class="box_01 boxBmargin12 submitdata">
		<div class="inner6px">
			<div class="cell">
				<c:set var="curr_path" value="代码生成"></c:set>
				<form name="myform" method="post">
					<input type="hidden" name="tableIds" value="${param.thistableIds}" />


					<table cellspacing="0" cellpadding="0" width="300" border="0">
						<tr class="DataGridHeadStyle">
							<th>类型</th>
							<th>说明</th>
						</tr>
						<tr >
							<td width="25%" height="50">1.Hibernate模式</td>
							<td width="60%">完整的SSH框架三层结构代码</td>
						</tr>
						<tr>
						    <td colspan="2" align="center"><a href="#"
								class="btn_01" onclick="genCode()">执行<b></b></a></td>
						</tr>
					</table>


				</form>
			</div>
		</div>
	</div>
	<script>
				if('${message}'!='')
				{
					alert('${message}');	
					opener.location=opener.location;			
				}
			</script>
</body>
</html>

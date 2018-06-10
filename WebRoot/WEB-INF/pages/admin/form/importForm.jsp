<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<%@ taglib prefix="ww" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>导入表单</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>

		$(function(){
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'表单类别列表',
				queryParams:{
					modelphysicalName : $("#modelphysicalName").val()
				},
				url:'getimportForm.action',
				idField:'TABLE_NAME',
				columns:[[
	                      {field:'id',checkbox:true},
				          {field:'TABLE_NAME',title:'数据表名称',width:fixWidth(0.3)}				          
				        ]]
			}));
		});
		function search_table(){
			$('#pagination').datagrid('clearSelections');
			var queryParams = {
					modelphysicalName : $("#modelphysicalName").val()
				};        	
	        	$('#pagination').datagrid('options').queryParams = queryParams;
	        	$("#pagination").datagrid('load'); 
        }
        function importAll(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].TABLE_NAME+"|";
			}
			
			if(rows.length<1){
			    $.messager.alert('提示','请选择要纳入管理的表单！');
			}else{
			    $.messager.confirm("提示","导入选中的数据表会做如下改变：\r\n1.将会将主键名称改为Row_ID\r\n2.将加入6个默认字段\r\n确认导入操作？",function(result){
			        if(result){
		                $.ajax({
		                	url : "importForm.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ids
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','导入时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$('#pagination').datagrid('clearSelections');
		                        	$.messager.alert('提示','导入成功！');
		                        	location.href = "${ctx}/jsp/admin/form/formTableInitList.action?random=" + (new Date()).getTime();
		                        }else{
		                        	$.messager.alert('错误','导入时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
    </script>
	</head>

	<body>
		<div class="page_content">
			<div class="box_01 submitdata">
				<div class="inner12px">
					<div class="cell  boxBmargin12">
						<form name="myform" method="post">
							<table width="100%">
								<tr>
									<th>
										物理表名:
									</th>
									<td>
										<input name="modelphysicalName" type="text" class="form_text"
											id="modelphysicalName"
											value="<ww:property value="modelphysicalName"/>" />
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<a href="###" class="btn_01" onclick="search_table()">查询<b></b>
										</a>
										<a href="###" class="btn_01" onclick="importAll()">导入选中的表单<b></b>
										</a>
										<a href="###" class="btn_01"
											onclick="location.href = '${ctx}/jsp/admin/form/formTableInitList.action?random=' + (new Date()).getTime();">返回表单列表<b></b>
										</a>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="pagination"></div>
				</div>
			</div>
		</div>
	</body>
</html>

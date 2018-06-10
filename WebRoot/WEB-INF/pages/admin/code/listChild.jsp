<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/jsLib.jsp"%>
		<script>
       	function edit_codeValue(id){
       		createSimpleWindow("codeValueWindow","修改","${ctx}/jsp/admin/code/editCodeValue.action?codeValue.id="+id, 400, 220);
        }
        function add_codeValue(id){
       		createSimpleWindow("codeValueWindow","新增","${ctx}/jsp/admin/code/editCodeValue.action?flag=add&codeValue.codeId=${codeValue.codeId}&codeValue.parentItem.id=${codeValue.parentItem.id}", 400, 220);
        }
		function del(){
			var rows = $('#pagination').datagrid('getSelections');
			if(0 == rows.length){
				$.messager.alert('警告','请选择要删除的条目!','warning');
				return false;
			}
        	$.messager.confirm('删除代码项', '确定要删除代码项？', function(result){
				if (result){
					var id = [];
					
					for(var i=0;i<rows.length;i++){
						id.push(rows[i].id);
					}
					if(0 == rows.length){
						$.messager.alert('警告','请选择要删除的条目!','warning');
					}else{
						var paraIds = "";
						for(var i=0;i<rows.length;i++){
							if("" == paraIds){
								paraIds = "id="+id[i];
							}else{
								paraIds += "&id="+id[i];
							}
						}
		                $.ajax({
		                	url : "deleteCodeValue.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data: paraIds,
		                    error: function(){
		                    	$.messager.alert('错误','删除代码项时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除代码项成功！');
		                        	queryCodeValue();
		                        	
		                        }else{
		                        	$.messager.alert('错误','删除代码项时出错！');
		                        }
		                    }
		                });							
					}
				}
			});
		}
		function queryCodeValue(){
			var queryParams = {
				"codeValue.itemValue" : $("#itemValue").val(),
				"codeValue.itemText" : $("#itemText").val(),
				"codeValue.parentItem.id" : window.currentParentItemID||""
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
        	$(function(){
	            $('#codeTree').tree({
	                checkbox: false,
	                url: 'findChildNode.action?codeValue.codeId='+"${codeValue.codeId}", 
	                onBeforeExpand:function(node,param){
	                    $('#codeTree').tree('options').url = "findChildNode.action?selNode="+node.id+'&codeValue.codeId='+"${codeValue.codeId}";
                 	},
                 	onClick:function(node){
                 		if(node.id && node.id.indexOf("|")!=-1)
                 			window.currentParentItemID=node.id.split("|")[1];
                 		else
                 			window.currentParentItemID="";
                 		clear_form(document.myform);
                 		queryCodeValue();
               		}
	            });   
	        });
	        
	        window.autoDatagridHeight = <%=(String)session.getAttribute("autoDatagridHeight")%>;
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				toolbar:[{
					text:'添加',
					iconCls:'icon-add',
					handler:add_codeValue
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:del
				}],
				title:'代码项列表',
				url:'listChild.action?codeValue.codeId='+$("#codeId").val(),
				columns:[[
						  {field:'id',checkbox:true},
				          {field:'itemCode',title:'代码编号',width:fixWidth(0.12)},
				          {field:'itemValue',title:'代码项值',width:fixWidth(0.12)},
				          {field:'itemText',title:'代码项显示值',width:fixWidth(0.22)},
				          {field:'sortSQ',title:'同级排序',width:fixWidth(0.12)},
				          {field:'op',title:'操作',width:fixWidth(0.32),formatter:function(value,rec){
				          		return "<a class='btn_01_mini' onclick='edit_codeValue(\""+rec.id+"\")'>修改<b></b></a>";
						  }}
				        ]]
			}));
		});	
	</script>
	</head>
	<body>
		<div class="page_content">
			<div class="layout_01_left">
			<div class="layout_overflow">
				<ul id="codeTree"></ul>
			</div>
			</div>
			<div class="layout_01_right">
			<div class="layout_overflow">
				<div class="inner6px submitdata">
					<div class="boxBmargin12 cell">
						<form name="myform" method="post">
							<input type="hidden" name="codeValue.codeId" id="codeId" value="${codeValue.codeId}">
							<c:if test="${not empty codeValue.parentItem}">
								<c:if test="${codeValue.parentItem.itemCode !=''}">
									<input type="hidden" name="codeValue.parentItem.itemCode" value="${codeValue.parentItem.itemCode}">
								</c:if>
							</c:if>
							<table>
								<tr>
									<th width="15%">代码项值</th>
									<td width="35%"><input id="itemValue" name="codeValue.itemValue" value="${codeValue.itemValue}"></td>
									<th width="15%">代码项显示值</th>
									<td width="35%"><input id="itemText" name="codeValue.itemText" value="${codeValue.itemText}"></td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
											<a href="###" class="btn_01" onclick="queryCodeValue();">查询<b></b></a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</div>
					
					<div id="pagination"></div>
				</div>
			</div>
			</div>
		</div>
	</body>
</html>
<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
        <%@include file="/common/jsLib.jsp"%>
		<script>
       	function edit_code(id){
       		createSimpleWindow("codeWindow","修改","${ctx}/jsp/admin/code/editCode.action?flag=edit&code.id="+id, 400, 190);
        }
		function add_code(id){
			createSimpleWindow("codeWindow","新增","${ctx}/jsp/admin/code/editCode.action?flag=add", 400, 190);
        }
		function del(){
        	parent.$.messager.confirm('删除一维代码', '确定要删除一维代码？', function(result){
				if (result){
					var id = [];
					var rows = $('#pagination').datagrid('getSelections');
					for(var i=0;i<rows.length;i++){
						id.push(rows[i].id);
					}
					if(0 == rows.length){
						parent.$.messager.alert('警告','请选择要删除的条目!','warning');
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
		                	url : "deleteCode.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data: paraIds,
		                    error: function(){
		                    	parent.$.messager.alert('错误','删除一维代码时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	parent.$.messager.alert('提示','删除一维代码成功！');
		                        	queryCode();
		                        }else{
		                        	parent.$.messager.alert('错误','删除一维代码时出错！');
		                        }
		                    }
		                });							
					}
				}
			});
		}
		function queryCode(){
			var queryParams = {
				"code.codeName" : $("#codeName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load'); 
        }
        function editCodeValue(id){
        	parent.addTab("viewCodeValue","查看多级代码","/jsp/admin/code/initListChild.action?codeValue.codeId="+id+"&codeValue.parentItem.itemCode=null");
        }
        function clickModule(obj,index){
    		window.parent.clickModule(obj,index);
    	}
        $(function(){
			$('#pagination').datagrid($.extend(dg_cm_pp,{
				toolbar:[{
					text:'新增',
					iconCls:'icon-add',
					handler:add_code
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:del
				}],
				title:'一维代码列表',
				url:'listCode.action',
				columns:[[
						  {field:'id',checkbox:true},
				          {field:'codeName',title:'代码名称',width:1},
				          {field:'sortSQ',title:'同级排序',width:1},
				          {field:'op',title:'操作',width:1,formatter:function(value,rec){
				          		return "<a href='#' class='btn_02_mini' onclick='edit_code(\""+rec.id+"\")'>修改<b></b></a>"
				          			  +"<a href='#' class='btn_03_mini' data-title='查看多级代码' _href='${ctx}/jsp/admin/code/initListChild.action?codeValue.codeId="+rec.id+"&codeValue.parentItem.itemCode=null' onclick='clickModule(this,1)'>查看多级代码<b></b></a>";
						  }}
				        ]]
			}));
		});
		</script>
	</head>

	<body>
		<div class="page_content">
			<div class="box_01 submitdata">
				<div class="inner12px">
					<div class="boxBmargin12 cell">
						<form name="myform" method="post">
							<table>
								<tr>
									<th width="15%">代码名称</th>
									<td width="35%"><input id="codeName" name="code.codeName"></td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="btn_area_setc">
											<a href="###" class="btn_01" onclick="clear_form(document.myform);">清空<b></b></a>
											<a href="###" class="btn_01" onclick="queryCode();">查询<b></b></a>
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
	</body>
</html>
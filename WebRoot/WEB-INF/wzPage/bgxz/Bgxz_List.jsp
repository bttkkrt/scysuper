<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>表格管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
        function addNew(){
        	createSimpleWindow("win_bgxz","添加表格","${ctx}/jsp/bgxz/bgxzInitEdit.action?flag=add", 700, 500);
        }
        function edit(row_Id){
        	createSimpleWindow("win_bgxz","修改表格","${ctx}/jsp/bgxz/bgxzInitEdit.action?flag=mod&bgxz.id="+row_Id, 700, 500);
        }
        function view(row_Id){
        	createSimpleWindow("win_bgxz","查看表格","${ctx}/jsp/bgxz/bgxzView.action?bgxz.id="+row_Id, 700, 500);
        }
        
        function del(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+"|";
			}
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "bgxzDel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ids
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','删除时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除成功！');
		                        	search_bgxz();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_bgxz(){
        	var queryParams = {
					"bgxz.infoTitle": $("#infoTitle").val(),
					"bgxz.bgzl": $("#bgzl").val(),	
					"queryPublicDateStart" :$("#queryPublicDateStart").val(),
					"queryPublicDateEnd" :$("#queryPublicDateEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'表格列表',
				url:'bgxzQuery.action',
				queryParams:{
					"bgxz.infoTitle": $("#infoTitle").val(),
					"bgxz.bgzl": $("#bgzl").val(),	
					"queryPublicDateStart" :$("#queryPublicDateStart").val(),
					"queryPublicDateEnd" :$("#queryPublicDateEnd").val()
				},
				frozenColumns: [[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				    {field:'infoTitle',title:'表格名称',width:0.3,formatter:function(value,rec){
			             if(rec.infoTitle.length>20){
			                var temp=rec.infoTitle.substr(0,20)+'...';
			             	return "<span title="+rec.infoTitle+">"+temp+"</span>";
			             }	
			             else{
			                var temp1=rec.infoTitle;
			              	return "<span title="+rec.infoTitle+">"+temp1+"</span>";
			             }  
				    }},
				    {field:'bgzl',title:'表格种类',width:0.1,formatter:function(value,rec){
			             if(value == 1){
			               return "城市管理";
			             }	
			             else if(value == 2){
			               return "安全生产";
			             }	
			             else if(value == 3){
			               return "行政执法";
			             }	
			             else
			             {
			             	return "其他";
			             }
				    }},
					{field:'publicDate',title:'上传日期',width:0.2,formatter:function(value,rec){
						return value.substring(0,10);					
					}},
		            {field:'op',title:'操作',width:0.2,formatter:function(value,rec){
		            
                      return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
                    }}
				]],
				toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				},{
					id:'btncut',
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						del();
					}
				}]
			}));
		});
    </script>
	</head>

	<body>
		<div class="page_content">
			<div class="box_01 submitdata">
				<div class="inner12px">
					<form name="myform" method="post">
						<div class="cell boxBmargin12">
							<table>
								<tbody>
									<tr>
										<th width="15%">
											表格名称
										</th>
										<td width="35%">
											<input name="bgxz.infoTitle" id="infoTitle"
												class="form_text" value="${bgxz.infoTitle}"
												type="text" maxlength="127" style="width:50%">
										</td>
										<th width="15%">
											表格种类
										</th>
										<td width="35%">
											<s:select id="bgzl" cssStyle="width:50%" name="bgxz.bgzl" list="#{'':'---请选择----','1':'危险化学品','2':'职业危害','3':'行政执法证书','4':'其他'}" theme="simple"/>
										</td>
									</tr>
									<tr>
										<th width="15%">
											上传日期
										</th>
										<td width="35%">
											<input style="width:35%;" name="queryPublicDateStart" id="queryPublicDateStart"
												value="<fmt:formatDate type='date' value='${queryPublicDateStart}' />"
												type="text" class="Wdate"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryPublicDateEnd\')}'})">
											-
											<input style="width:35%;" name="queryPublicDateEnd" id="queryPublicDateEnd"
												value="<fmt:formatDate type='date' value='${queryPublicDateEnd}' />"
												type="text" class="Wdate"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryPublicDateStart\')}'})">
										</td>
									</tr>
									<tr>
										<td colspan="4">
											<div class="btn_area_setc">
												<a href="###" class="btn_01"
													onclick="search_bgxz()">查询<b></b> </a>
												<a href="###" class="btn_01"
													onclick="clear_form(document.myform);">清空<b></b> </a>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="pagination">
						</div>
					</form>
				</div>
				
			</div>
		</div>
		</div>
	</body>
</html>

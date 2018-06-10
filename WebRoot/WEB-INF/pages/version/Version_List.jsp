<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>版本管理</title>
		<%@include file="/common/jsLib.jsp"%>
		<script>
        function addNew(){
        	createSimpleWindow("versionWindow","添加版本","${ctx}/jsp/version/versionInitEdit.action?flag=add", 400, 280);        	
        }
        function edit(row_Id){
    		createSimpleWindow("versionWindow","修改版本","${ctx}/jsp/version/versionInitEdit.action?flag=mod&version.id="+row_Id,400, 280);        	
        }
        function view(row_Id){
        	createSimpleWindow("versionWindow","查看版本","${ctx}/jsp/version/versionView.action?version.id="+row_Id, 400, 280);
        }
        function downloadFile(versionId){
			document.myform.action = "download.action?version.id="+versionId;
			document.myform.submit();
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_version();
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
		                	url : "versionDel.action",
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
		                        	search_version();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
        function search_version(){
        	var queryParams = {
				"version.versionNumber": $("#versionNumber").val(),
				"version.versionPlatform": $("#versionPlatform").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('load'); 
        }
        $(function(){
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'版本列表',
				url:'versionQuery.action',
				toolbar:[{
					text:'新建',
					iconCls:'icon-add',
					handler:addNew
				},
				{
					text:'删除',
					iconCls:'icon-remove',
					handler:del
				}],
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'versionNumber',title:'版本号',width:0.3},
						  {field:'versionPlatform',title:'版本平台',width:0.3,formatter:function(value,rec){
						  var temp = '';
							    $.ajax({
							    url: '${ctx}/jsp/admin/code/findCodeValue.action',
							    type: 'post',
							    dataType: 'json',
							    async : false,
							    data:{        "codeValue.itemValue" : rec.versionPlatform,
							        "codeValue.codeId" : "0464f18f318d260101318d2f404e0004"    },
							    error: function(){
							        $.messager.alert('提示','获取一维代码错误！');
							    },
							    success: function(data){
							        temp = data.itemText;
							    }});
							    return temp;}
						  },
				          {field:'op',title:'操作',width:0.2,formatter:function(value,rec){
				          	var restr="<a href='#' onclick=\"view('"+rec.id+"')\" class='btn_02_mini'>查看<b></b></a><a href='#' onclick=\"edit('"+rec.id+"')\" class='btn_03_mini'>修改<b></b></a><a href='#' onclick=\"downloadFile('"+rec.id+"')\" class='btn_04_mini'>下载版本<b></b></a>";
	                        return restr;
                          }}
				        ]]
			}));
		});
    </script>
	</head>
	<body>
		<div class="page_content">
			<div class="box_01  submitdata">
				<div class="inner12px">
					<form name="myform" method="post">
						<div class="cell boxBmargin12">
							<table>
								<tr>
									<th width="15%">
										版本号
									</th>
									<td width="35%">
										<input name="version.versionNumber" class="form_text"
											id="versionNumber" value="${version.versionNumber}"
											type="text" maxlength="127" style="width: 50%" >
									</td>
									<th width="15%">
										版本平台
									</th>
									<td width="35%">
										<cus:SelectOneTag property="version.versionPlatform"
											defaultText='请选择' codeName="终端类型" datatype="*"
											value="${version.versionPlatform}" style="width: 50%" />
									</td>
								</tr>
								<tr>
									<td colspan="4" style="text-align: center">
										<div class="btn_area_setc">
											<a class="btn_01" onclick="search_version()"
												iconCls="icon-search">查询<b></b> </a>
											<a class="btn_01" onclick="clear_form(document.myform);"
												iconCls="icon-undo">清空<b></b> </a>
										</div>

									</td>
								</tr>
							</table>
						</div>
						<div id="pagination"></div>
				</div>
			</div>
			</form>
		</div>
		</div>

	</body>
</html>

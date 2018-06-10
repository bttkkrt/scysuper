<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>数据管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<SCRIPT language=JavaScript
			src="<%=basePath%>/webResources/js/docorder.js"></SCRIPT>
		<script src="${ctx}/webResources/My97DatePicker/WdatePicker.js"></script>
		<%@include file="/common/jsLib.jsp"%>
		
		<script>
		$(function(){
			$('#pagination').datagrid({
				title:'${table.tableName}记录列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'viewRecord.action',
				queryParams:{
					${map["queryParams"]}
				},
				idField:'ROW_ID',
				remoteSort: false,
				frozenColumns:[[
				    {field:'ROW_ID',checkbox:true}
				]],
				columns:[[
				          ${map["column"]},
				          {field:'op',title:'操作',width:100,formatter:function(value,rec){
	                             return "<span style='color:red' onclick=\"view('"+rec.ROW_ID+"')\">查看</span>&nbsp;<span style='color:red' onclick=\"update('"+rec.ROW_ID+"')\">修改</span>";
                          }}
				        ]],
				pagination:true,
				rownumbers:true,
				pageList:[10,20,30],
				onLoadSuccess:tabOnloadSuccess2,
				onLoadError:tabOnloadSuccess2,
				onHeaderContextMenu: function(e, field){
					e.preventDefault();
					if (!$('#tmenu').length){
						createColumnMenu();
					}
					$('#tmenu').menu('show', {
						left:e.pageX,
						top:e.pageY
					});
				}
			});
		});

        var titles = new Array();
        function createColumnMenu(){
			var tmenu = $('<div id="tmenu" style="width:150px;"></div>').appendTo('body');
			var fields = $('#pagination').datagrid('getColumnFields');
			
			for(var i=0; i<fields.length; i++){
				var option = $('#pagination').datagrid('getColumnOption',fields[i]);
				var obj = {};
				obj.title = option.title;
				obj.field = fields[i];
				titles[i] = obj;
			}			
			for(var i=0; i<titles.length; i++){
				$('<div iconCls="icon-ok"/>').html(titles[i].title).appendTo(tmenu);
			}
			tmenu.menu({
				onClick: function(item){
					if (item.iconCls=='icon-ok'){
						var field;
						for(var i=0; i<titles.length; i++){
							if(titles[i].title==item.text){
								field = titles[i].field;
							}
						}
						$('#pagination').datagrid('hideColumn', field);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-empty'
						});
					} else {
						var field;
						for(var i=0; i<titles.length; i++){
							if(titles[i].title==item.text){
								field = titles[i].field;
							}
						}
						$('#pagination').datagrid('showColumn', field);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-ok'
						});
					}
				}
			});
		}
        
        function close_win(){
        	$("#newWindow").window("close");
        }
        
        function reloadDate(){
        	$("#pagination").datagrid('reload'); 
        }
		
        function search_record(){
        	var queryParams = {
        			${map["queryParams"]}
    		};    
        	$('#pagination').datagrid('clearSelections');
            $('#pagination').datagrid('options').queryParams = queryParams;
            $("#pagination").datagrid('load'); 
        }
        
      	function addNew(){
      		createSimpleWindow("win_agencyInfo","","${ctx}/jsp/admin/form/formRecordAdd.action?tableId=<s:property value='tableId'/>",900,600);
      		/* var location = getCenterLocation(600,260);
      		openparentWindow("newWindow","添加记录",location.left,location.top,"700","450","${ctx}/jsp/admin/form/formRecordAdd.action?tableId=<s:property value='tableId'/>",true,true,true,false,true,"win");	
 		 */}
        function update(row_Id){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/admin/form/formRecordEdit.action?tableId=<s:property value='tableId'/>&rowId="+row_Id,900,600);
        	/* var location = getCenterLocation(700,450);
        	openparentWindow("newWindow","修改记录",location.left,location.top,"700","450","${ctx}/jsp/admin/form/formRecordEdit.action?tableId=<s:property value='tableId'/>&rowId="+row_Id,true,true,true,false,true,"win");
         */}
        function view(row_Id){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/admin/form/formRecordView.action?tableId=<s:property value='tableId'/>&rowId="+row_Id,900,600);
        	/* var location = getCenterLocation(700,450);
        	openparentWindow("newWindow","查看记录",location.left,location.top,"700","450","${ctx}/jsp/admin/form/formRecordView.action?tableId=<s:property value='tableId'/>&rowId="+row_Id,true,true,true,false,true,"win");
		 */}
        function del(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].ROW_ID+"|";
			}
			
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "deleteRecord.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ids,
		                    	tableId : "${table.id}"
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','删除时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除成功！');
		                        	reloadDate();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
        function  importExcel(tableId){
        	createSimpleWindow("win_agencyInfo","","getimportExcel.action?tableId="+tableId,900,600);
        	/* var location = getCenterLocation(320,150);
        	openparentWindow("newWindow","导入Excel",lcation.left,location.top,"320","150","getimportExcel.action?tableId="+tableId,true,true,true,false,true,"win");
         */}
    </script>
	</head>

	<body>
			<input type="hidden" name="tableId" value="${tableId }" />
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td align="center">
						  <div class="submitdata">
							<table width="100%">
								<tr>
									<edp:queryRecord codeMapName="codeMap"
										queryListName="queryList" valueMapName="valueMap"
										tablePhyName="${tablePhyName}" />
								</tr>
								<tr>
									<th colspan="4" class="set_c">
									    <a href="###" class="easyui-linkbutton" onclick="search_record();" iconCls="icon-search">查询</a>
										&nbsp;
										<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>
										&nbsp;
										<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
										&nbsp;
										<a href="###" class="easyui-linkbutton" onclick="importExcel('${tableId}');" iconCls="icon-reload">导入数据</a>
										&nbsp;
									</th>

								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tr>
					<td >
					    <div id="pagination" style="background:#efefef;border:1px solid #ccc;"></div>
					</td>
				</tr>
			</table>
	</body>
</html>
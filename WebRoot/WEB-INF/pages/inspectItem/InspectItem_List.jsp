<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>安全检查项配置管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 600) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","添加安全检查项配置",xc,yc,"600","350","${ctx}/jsp/inspectItem/inspectItemInitEdit.action?flag=add&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 600) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","修改安全检查项配置",xc,yc,"600","350","${ctx}/jsp/inspectItem/inspectItemInitEdit.action?flag=mod&inspectItem.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function view(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 600) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","查看安全检查项配置",xc,yc,"600","350","${ctx}/jsp/inspectItem/inspectItemView.action?inspectItem.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_inspectItem();
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
		                	url : "inspectItemDel.action",
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
		                        	search_inspectItem();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
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
        
        function search_inspectItem(){
        	var queryParams = {
				"inspectItem.companyFlag": $("#companyFlag").val(),
"inspectItem.inspectType": $("#inspectType").val(),
"inspectItem.item": $("#item").val(),
"inspectItem.requirement": $("#requirement").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'安全检查项配置列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'inspectItemQuery.action',
				queryParams:{
					"inspectItem.companyFlag": $("#companyFlag").val(),
					"inspectItem.inspectType": $("#inspectType").val(),
					"inspectItem.item": $("#item").val(),
					"inspectItem.requirement": $("#requirement").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
							{field:'inspectType',title:'检测类型',width:fixWidth(0.2),align:'center',formatter:function(value,rec){
			  						var temp = '';
								    $.ajax({
									    url: '${ctx}/jsp/admin/code/findCodeValue.action',
									    type: 'post',
									    dataType: 'json',
									    async : false,
									    data:{        
									    	"codeValue.itemValue" : rec.inspectType,
									        "codeValue.codeId" : "402881e7497d66f001497d7f65a40007"    
									    },
									    error: function(){
									        $.messager.alert('提示','获取一维代码错误！');
									    },
									    success: function(data){
								        	temp = data.itemText;
								    	}
									});
								    return temp;
					    		}
							},
							{field:'item',title:'巡检项',width:fixWidth(0.2),align:'center'},
							{field:'requirement',title:'巡检要求',width:fixWidth(0.4),align:'center'},

				          {field:'op',title:'操作',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
	                             return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
                          }}
				        ]],
				pagination:true,
				onLoadSuccess:tabOnloadSuccess,
				onLoadError:tabOnloadSuccess,
				rownumbers:true,
				pageList:[10,20,30],
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
		$(window).resize(function(){
            $('#pagination').datagrid('resize',{
            	width: document.body.clientWidth-20
            });
        });
        
         function import_inspectItem(){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 600) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","导入安全检查项配置",xc,yc,"600","250","${ctx}/jsp/inspectItem/inspectItemInitImport.action?dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<input type="hidden" id="companyFlag" name="companyFlag" value="${companyFlag}">
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">检测类型标识</th>
				<td width="35%"><cus:SelectOneTag property="inspectItem.inspectType" defaultText='请选择' codeName="安全检测类型" value="${inspectItem.inspectType}" /></td>
				<th width="15%">巡检项</th>
				<td width="35%"><input name="inspectItem.item" id="item" value="${inspectItem.item}" type="text"></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center;">
				<a href="###" class="easyui-linkbutton" onclick="search_inspectItem()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
				<a href="###" class="easyui-linkbutton" onclick="import_inspectItem();" iconCls="icon-add">导入</a>&nbsp;
				</td>
			</tr>
		</table>
	</div>
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr>
				<td>
				<div id="pagination" style="background:#efefef;border:1px solid #ccc;">
				
				</div>
				</td>
			</tr>
		</table>
</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>剧毒化学品名录管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function view(hxname){
        	window.returnValue=hxname;
			window.close();
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_jdhxp();
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
        
        function search_jdhxp(){
        	var queryParams = {
				"jdhxp.hxname": $("#hxname").val(),
"jdhxp.bname": $("#bname").val(),
"jdhxp.casname": $("#casname").val(),
"jdhxp.unname": $("#unname").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'剧毒化学品名录列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'jdhxpQuery.action',
				queryParams:{
					"jdhxp.hxname": $("#hxname").val(),
"jdhxp.bname": $("#bname").val(),
"jdhxp.casname": $("#casname").val(),
"jdhxp.unname": $("#unname").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'hxname',title:'化学名',width:fixWidth(0.3)},
{field:'bname',title:'别名',width:fixWidth(0.3)},
{field:'casname',title:'CAS号',width:fixWidth(0.1)},
{field:'unname',title:'UN号',width:fixWidth(0.1)},

				          {field:'op',title:'操作',width:fixWidth(0.1),formatter:function(value,rec){
	                             return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.hxname+"')\">选择</span>";
                          }}
				        ]],
				pagination:true,
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
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">化学名</th>
				<td width="35%"><input name="jdhxp.hxname" id="hxname" value="${jdhxp.hxname}" type="text"></td>
				<th width="15%">别名</th>
				<td width="35%"><input name="jdhxp.bname" id="bname" value="${jdhxp.bname}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">CAS号</th>
				<td width="35%"><input name="jdhxp.casname" id="casname" value="${jdhxp.casname}" type="text"></td>
				<th width="15%">UN号</th>
				<td width="35%"><input name="jdhxp.unname" id="unname" value="${jdhxp.unname}" type="text"></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				<a href="###" class="easyui-linkbutton" onclick="search_jdhxp()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
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

<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@include file="/common/header.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>按企业统计</title>
	<script>
        $(function(){
			$('#pagination').datagrid({
				title:'按企业统计',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'commoTroubleDataQyQuery.action',
				queryParams:{
"queryJhwcsjStart": $("#queryJhwcsjStart").val(),
"queryJhwcsjEnd": $("#queryJhwcsjEnd").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
{field:'qymc',title:'企业名称',width:fixWidth(0.2)},
{field:'cdcs',title:'出动次数',width:fixWidth(0.1)},
{field:'ccyhs',title:'查出隐患数',width:fixWidth(0.1)},
{field:'wcyhs',title:'完成隐患数',width:fixWidth(0.1)},
{field:'yhzgl',title:'隐患整改率',width:fixWidth(0.1)},
{field:'zdyhs',title:'重大隐患数',width:fixWidth(0.1)},
{field:'wczdyhs',title:'完成重大隐患数',width:fixWidth(0.1)},
{field:'zdyhzgl',title:'重大隐患整改率',width:fixWidth(0.1)},
{field:'zgzj',title:'整改资金',width:fixWidth(0.1)}
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
    </script>
</head>

<body>
		<input type="hidden" id="queryJhwcsjStart" value="${queryJhwcsjStart}"/>
		<input type="hidden" id="queryJhwcsjEnd" value="${queryJhwcsjEnd}"/>
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr>
				<td>
				<div id="pagination" style="background:#efefef;border:1px solid #ccc;">
				
				</div>
				</td>
			</tr>
		</table>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>职业病危害档案</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zybwhda();
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
        function exportData()
        {
        	document.myform.action = "${ctx}/jsp/zycsqk/zybwhdaListExport.action";
        	document.myform.submit();
        }
        
        function search_zybwhda(){
        	var queryParams = {
"zybwhda.qymc": $("#qymc").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'职业病危害档案',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zybwhdaQuery.action',
				queryParams:{
"zybwhda.qymc": $("#qymc").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
{field:'qymc',title:'单位名称',width:fixWidth(0.2)},
{field:'zybwhys',title:'职业危害因素',width:fixWidth(0.1)},
{field:'whcs',title:'存在职业病危害场所数量',width:fixWidth(0.1)},
{field:'jcrs',title:'接触职业病危害人员数量',width:fixWidth(0.1)},
{field:'jhrs',title:'职业健康监护总人数',width:fixWidth(0.1)},
{field:'zcrs',title:'职业健康体检正常人数',width:fixWidth(0.1)},
{field:'ycrs',title:'职业相关异常人数',width:fixWidth(0.1)},
{field:'jjrs',title:'职业禁忌人数',width:fixWidth(0.1)},
{field:'ysrs',title:'疑似职业病人数',width:fixWidth(0.1)},
{field:'jcds',title:'检测点数',width:fixWidth(0.1)},
{field:'hylb',title:'职业病危害行业类别',width:fixWidth(0.1)},
{field:'whcd',title:'职业病危害程度',width:fixWidth(0.1)}
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
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="zybwhda.qymc" id="qymc" value="${zybwhda.qymc}" type="text"></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				<a href="###" class="easyui-linkbutton" onclick="search_zybwhda()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<a href="###" class="easyui-linkbutton" onclick="exportData()" iconCls="icon-add">导出</a>&nbsp;							
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

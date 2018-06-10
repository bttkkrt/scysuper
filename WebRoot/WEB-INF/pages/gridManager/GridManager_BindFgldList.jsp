<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>分管领导网格管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","添加网格",xc,yc,"400","350","${ctx}/jsp/gridManager/gridManagerInitEdit.action?flag=add&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","修改网格",xc,yc,"400","350","${ctx}/jsp/gridManager/gridManagerInitEdit.action?flag=mod&gridManager.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function view(row_Id,name){
        	var id  = "town_list";
        	var text = "绑定到"+name+"的安监办主任信息";
        	var url = "${ctx}/jsp/gridManager/gridManagerTownView.action?userId="+row_Id+"&deptCode=${deptCode}";
    		window.parent.addTab(id,text,url);
        }
         function mapView(row_Id,name){
        	var id  = "town_list_map";
        	var text = "查看"+name+"下地图信息";
        	var url = "${ctx}/jsp/gridManager/gridManagerFgldMapView.action?deptCode="+row_Id;
    		window.parent.addTab(id,text,url);
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_gridManager();
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
        
        function search_gridManager(){
        	var queryParams = {
					"gridManager.id": $("#userid").val(),
					"deptCode": '${deptCode}'
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
			$('#pagination').datagrid({
				title:'分管领导列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'gridManagerBindFgldQuery.action',
				queryParams:{
        			"gridManager.id": $("#userid").val(),
					"deptCode": '${deptCode}'
				},
				idField:'id',
				remoteSort: false,
				columns:[[
				          {field:'name',title:'分管领导',width:200},
				          {field:'count',title:'有证照企业数',width:200},
				          {field:'wzCount',title:'无证照企业数',width:200},
				          {field:'op',title:'操作',width:200,formatter:function(value,rec){
	                             return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"','"+rec.name+"')\">绑定安监办主任</span>"+
	                             "&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:blue;cursor:hand' onclick=\"mapView('"+rec.id+"','"+rec.name+"')\">地图查看</span>";
                          }}
				        ]],
				pagination:true,
				onLoadSuccess:tabOnloadSuccess,
				onLoadError:tabOnloadSuccess,
				rownumbers:true,
				pageList:[200,300],
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
				<th width="15%">分管领导</th>
				<td width="85%">
					<s:select list="users" id="userid" listKey="id" listValue="name" theme="simple"
 						 headerKey="" headerValue=""></s:select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center" style="text-align:center;">
					<a href="###" class="easyui-linkbutton" onclick="search_gridManager()" iconCls="icon-search">查询</a>&nbsp;
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

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>REGULATIONS_LEVEL管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 450) / 2;
            openparentWindow("newWindow","添加法规级别",xc,yc,"800","450","${ctx}/jsp/RegulationsLevel/regulationsLevelInitEdit.action?flag=add&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 450) / 2;
            openparentWindow("newWindow","修改法规级别",xc,yc,"800","450","${ctx}/jsp/RegulationsLevel/regulationsLevelInitEdit.action?flag=mod&regulationsLevel.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function view(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 450) / 2;
            openparentWindow("newWindow","查看法规级别",xc,yc,"800","450","${ctx}/jsp/RegulationsLevel/regulationsLevelView.action?regulationsLevel.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_regulationsLevel();
        }
        
        function del(row_id){
        	$.ajax({
        		url : "${ctx}/jsp/RegulationsLevel/checkHasChild.action",
        		type : "post",
        		dataType : "json",
        		data : {
        			ids : row_id
        		},
        		error : function(){
        		},
        		success : function(data){
        			var title = '';
        			if(data=='0'){
                    	title = '确定要删除吗?';
                    }else{
                    	title = '该类别下有子节点，确定要一起删除吗？';
                    }
                    $.messager.confirm("删除",title,function(result){
				        if(result){
			                $.ajax({
			                	url : "${ctx}/jsp/RegulationsLevel/regulationsLevelDel.action",
			                	type: 'post',
			                    dataType: 'json',
			                    async : false,
			                    data:{ 
			                    	ids : row_id
			                    },
			                    error: function(){
			                    	$.messager.alert('提示','删除时出错！');
			                    },
			                    success: function(data){
			                        if(data.result=='1'){
			                        	$.messager.alert('提示','删除成功！');
			                        	search_regulationsLevel();
			                        }else{
			                        	$.messager.alert('提示','此级别被引用,请先删除对应的法规级别！');
			                        }
			                    }
			                });
				        }
				    });
        		}
        	});
		    
        }
        
        function del11(){
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
		                	url : "regulationsLevelDel.action",
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
		                        	search_regulationsLevel();
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
        
        function search_regulationsLevel(){
        	var queryParams = {
				"regulationsLevel.levelCode": $("#levelCode").val(),
"regulationsLevel.levelName": $("#levelName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        
        $(function(){
			$('#pagination').treegrid({
				title:'法规级别列表',  
				nowrap: false,
				striped: true,
				rownumbers: true,
				collapsible:true,
				url:'regulationsLevelQuery.action',
				idField:'id',
				treeField:'name',
				remoteSort: false,
				columns:[[
							{field:'name',title:'级别名称',width:fixWidth(0.25)},
							{field:'userName',title:'操作人',width:fixWidth(0.13)},
				          	{field:'deptName',title:'操作部门',width:fixWidth(0.15)},
				          	{field:'updeTime',title:'操作时间',width:fixWidth(0.17)},
				            {field:'op',title:'操作',width:fixWidth(0.12),formatter:function(value,rec){
				            	var str = '';
				            	if(rec.name != '办公用品类别'){
				            		str = "<a href=\"javascript:void(0);\" class=\"btns\" onclick=\"view('"+rec.id+"')\" style='color:#000;cursor:hand'><b></b>查看</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" class=\"btns\" onclick=\"edit('"+rec.id+"')\" style=\'color:#000;cursor:hand\'><b></b>修改</a>&nbsp;";
				            		str +="<a href=\"javascript:void(0);\" class=\"btns\" onclick=\"del('"+rec.id+"')\" style=\'color:#000;cursor:hand\'><b></b>删除</a>";
				            	}
	                            return str;
                           }}
				        ]],
				toolbar:[]
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
			<!-- 
			<tr>
				<th width="15%">级别名称</th>
				<td width="35%" colspan="3"><input style="width: 93%;" name="regulationsLevel.levelName" id="levelName" value="${regulationsLevel.levelName}" type="text"></td>
			</tr>
			 -->
			<tr>
				<td colspan="4" align="center">
				<center>
					<!-- 
					<a href="###" class="easyui-linkbutton" onclick="search_regulationsLevel()" iconCls="icon-search">查询</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
					 -->
					<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
				</center>
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

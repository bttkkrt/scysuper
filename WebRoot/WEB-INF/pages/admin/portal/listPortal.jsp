<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<%@ taglib uri="/WEB-INF/tld/edp.tld" prefix="code"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>布局列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="布局列表">
<script src="${ctx}/webResources/js/displayTag.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/themes/display.css">
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/common.js"></script>
<script type="text/javascript" src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/${curr_user.cssId}/easyui.css"/>'> 
<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>  
<link rel="stylesheet" type="text/css"
	href="${ctx}/webResources/themes/${curr_user.cssId}/css/style.css">
<script>
    function close_win(){
        $("#newWindow").window("close");
    }
    
    function reloadDate(){
        $('#portalGrid').datagrid('clearSelections');
    	search_portal();
    }
    
    function search_portal(){
        	$('#portalGrid').datagrid('options').queryParams = queryParams;
        	$("#portalGrid").datagrid('load'); 
    }
    
    function new_portal(){
    	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/admin/portal/editPortal.action",900,600);
    	/* var location = getCenterLocation(600,520);
        openparentWindow("newWindow","添加新布局",location.left,location.top,"800","500","${ctx}/jsp/admin/portal/editPortal.action",true,true,true,false,true,"win");
    */ }    

    function del_portal(ff){
        var rows = $('#portalGrid').datagrid('getSelections');
        var ids = "";
		for(var i=0;i<rows.length;i++){
			ids += rows[i].id+"|";
		}
		if(rows.length<1){
			   $.messager.alert('提示','至少选择一项删除！');
		}else{
			   $.messager.confirm("删除","确定要删除选中的布局吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "delPortal.action",
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

    function edit_portal(id){
    	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/admin/portal/editPortal.action?portal.id="+id,900,600);
    /* 	var location = getCenterLocation(600,520);
        openparentWindow("newWindow","修改布局",location.left,location.top,"800","500","${ctx}/jsp/admin/portal/editPortal.action?portal.id="+id,true,true,true,false,true,"win");
   */  }
    
    $(function(){
     	$('#portalGrid').datagrid({
    		title:'布局列表',
	   	 	iconCls:'icon-save',
	    	width:fixWidth(1.01),
	    	nowrap: false,
	    	striped: true,
	    	collapsible:true,
	    	url:'listPortalAjax.action',	
			idField:'id',
			sortOrder: 'asc',
			remoteSort: false,
			frozenColumns:[[
				{field:'id',checkbox:true,width:fixWidth(0.1)}
			]],
			columns:[[
				{field:'title',title:'布局名称',width:fixWidth(0.1)},
				{field:'url',title:'布局url',width:fixWidth(0.3)},
				{field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
					var retStr = "<span style='color:#000;cursor:hand' onclick='edit_portal(\""+rec.id+"\")'>修改</span>&nbsp;&nbsp;";
					return retStr;
				}}
				]],
			pagination:true,
			rownumbers:true,
			pageList:[10,20,30],
			onLoadSuccess:tabOnloadSuccess,
			onLoadError:tabOnloadSuccess,
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
	     var fields = $('#portalGrid').datagrid('getColumnFields');
			
	     for(var i=0; i<fields.length; i++){
		    var option = $('#portalGrid').datagrid('getColumnOption',fields[i]);
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
					$('#portalGrid').datagrid('hideColumn', field);
					tmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-empty'
					});
				} 
				else {
					var field;
					for(var i=0; i<titles.length; i++){
						if(titles[i].title==item.text){
							field = titles[i].field;
						}
					}
					$('#portalGrid').datagrid('showColumn', field);
						tmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-ok'
					});
				}
			}
		});
	} 
</script>
</head>

<body>
<div class="outputdata">
<form name="portalFrm" method="post">
<table id="Table1" CellSpacing="0" cellpadding="1" width="100%"
	align="center" border="0">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr>
				<td valign="top">
				    <a href="###" class="easyui-linkbutton" onclick="new_portal();" iconCls="icon-add">添加新布局</a>
				    <a href="###" class="easyui-linkbutton" onclick="del_portal(this.form)" iconCls="icon-del">删除布局</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
			<div id="portalGrid"  style="background:#efefef;border:1px solid #ccc;"></div>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>待处理隐患清单</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
 
        function reloadDate(){
            doQuery();
        }
        function doQuery(){
        	var queryParams = {
        		 
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        function view_zhenggai(id,taskId){
        	var dt=new Date();
        	window.open("${ctx}/jsp/yhqd/yhqdInitEdit.action?yhqd.id="+id+"&taskId="+taskId+"&flag=zhenggai&dt="+dt.getTime());
		}
        function view_yanshou(id,taskId){
        	var dt=new Date();
        	window.open("${ctx}/jsp/yhqd/yhqdInitEdit.action?yhqd.id="+id+"&taskId="+taskId+"&flag=yanshou&dt="+dt.getTime());
		}
        
        function view(row_Id){
        	var dt=new Date();
        	window.open("${ctx}/jsp/yhqd/yhqdView.action?yhqd.id="+row_Id+"&dt="+dt.getTime());
        }
        function close_win(){
         
        	 $("#mywindow").window("close");
        }  
        
        $(function(){
			$('#pagination').datagrid({
				title:'待处理隐患清单',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'findClaimedTaskList.action',
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'name',title:'事项名称',width:fixWidth(0.15),formatter:function(value,rec){
								return rec.name;
						  }},				          
				          {field:'createTime',title:'创建时间',width:fixWidth(0.15),formatter:function(value,rec){
								if(rec.createTime==null) return;
								var date = new Date(rec.createTime.time);
								var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								return retStr;
						  }},				          
				          {field:'description',title:'描述',width:fixWidth(0.31),formatter:function(value,rec){
								return rec.description;
						  }},				          
				          {field:'claimTime',title:'签收时间',width:fixWidth(0.15),formatter:function(value,rec){
								if(rec.claimTime==null) return;
								var date = new Date(rec.claimTime.time);
								var retStr = date.format("yyyy-MM-dd hh:mm:ss");
								return retStr;
						  }},				          
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
				        	  var opt = "<span style='cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span> &nbsp;&nbsp;";
				        	  if(rec.task_type=="隐患整改"){
				        		  opt+="<a href='#' class='btn_01_mini' onclick=\"view_zhenggai('"+rec.id+"','"+rec.task_id+"')\"><b style='color:red;cursor:hand'>整改</b></a>&nbsp;";
				        	  }else{
				        		  opt+="<a href='#' class='btn_01_mini' onclick=\"view_yanshou('"+rec.id+"','"+rec.task_id+"')\"><b style='color:red;cursor:hand'>验收</b></a>&nbsp;";
				        	  }
				        	  return opt;
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
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
<form name="myform" method="post">
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

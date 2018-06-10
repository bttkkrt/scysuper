<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>DOC_REVIEW管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
      
        function view(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","查看公文",xc,yc,"400","350","${ctx}/jsp/docReview/docReviewView.action?docReview.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_docReview();
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
        
        function search_docReview(){
        	var queryParams = {
				"docReview.docTitle": $("#docTitle").val(),
"docReview.docType": $("#docType").val(),
"docReview.deptCode": $("#deptCode").val(),
"docReview.userName": $("#userName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
		function doClaimTask(taskId){
			$.messager.confirm('签收任务', '确定要签收该任务？', function(result){
				if (result){
					$.ajax({
		            	url: '${ctx}/activiti/claimTask.action',
		            	type: 'post',
		                dataType: 'json',
		                async : false,
		                data: {
		                	"taskId" : taskId
		                },
		                error: function(error){
		                	$.messager.alert('错误','签收任务时出错！');
		                },
		                success: function(data){
		                    if(data.result){
		                    	$.messager.alert('提示','签收任务成功！','info',function(){
		                    		doQuery();
		                    	});
		                    }else{
		                    	$.messager.alert('错误','签收任务时出错！');
		                    }
		                }
		            });					
				}
			});		
		}  
		function doCompleteTask(taskId){
			$.messager.confirm('审核', '确定要进行审核？', function(result){
				if (result){
					if (result){
						var location = getCenterLocation(380,320);
						openparentWindow("newWindow","公文审核",location.left,location.top,"400","380","${ctx}/activiti/initCompleteTaskFormKey.action?taskId="+taskId,true,true,true,false,true,"win");					
					}		
				}
			});	
		}     
        $(function(){
        	
			$('#pagination').datagrid({
				title:'我审核的公文列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'docReviewQuery.action',
				queryParams:{
					"docReview.docTitle": $("#docTitle").val(),
"docReview.docType": $("#docType").val(),
"docReview.deptCode": $("#deptCode").val(),
"docReview.userName": $("#userName").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'docTitle',title:'公文标题',width:fixWidth(0.15)},
				          {field:'userName',title:'上报人',width:fixWidth(0.12)},
				          {field:'time',title:'发布日期',width:fixWidth(0.2)},
				          {field:'docContent',title:'公文内容',width:fixWidth(0.35)},
				          {field:'op',title:'操作',width:fixWidth(0.08),formatter:function(value,rec){
				          		     return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>";
				          		} 
                          } ,{field:'isEnd',title:'状态',width:fixWidth(0.06),formatter:function(value,rec){
                        	  if(rec.end == '1'){
                        		  return "<span style='color:green;'>已办结</span>"; 
                        	  }else if(rec.end == '-1'){
	                             return "<span style='color:red;'>已取消</span>";
                        	  }else{
	                             return "<span style='color:red;'>未办结</span>";
                        	  }
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
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">公文标题</th>
				<td width="35%"><input name="docReview.docTitle" id="docTitle" value="${docReview.docTitle}" type="text"></td>
				<th width="15%">公文类型</th>
				<td width="35%"><input name="docReview.docType" id="docType" value="${docReview.docType}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">部门编码</th>
				<td width="35%"><input name="docReview.deptCode" id="deptCode" value="${docReview.deptCode}" type="text"></td>
				<th width="15%">用户名称</th>
				<td width="35%"><input name="docReview.userName" id="userName" value="${docReview.userName}" type="text"></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<a href="###" class="easyui-linkbutton" onclick="search_docReview()" iconCls="icon-search">查询</a>&nbsp;
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

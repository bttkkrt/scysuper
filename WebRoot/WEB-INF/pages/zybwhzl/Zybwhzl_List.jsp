<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ZYBWHZL管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        function addNew(){
        	window.open("${ctx}/jsp/zybwhzl/zybwhzlInitEdit.action?flag=add");
        }
        function edit(row_Id){
            window.open("${ctx}/jsp/zybwhzl/zybwhzlInitEdit.action?flag=mod&zybwhzl.id="+row_Id);
        }
        function view(row_Id){
            window.open("${ctx}/jsp/zybwhzl/zybwhzlView.action?zybwhzl.id="+row_Id);
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zybwhzl();
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
		                	url : "zybwhzlDel.action",
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
		                        	search_zybwhzl();
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
        
        function search_zybwhzl(){
        	var queryParams = {
        	"zybwhzl.szzid": $("#szzid").val(),
				"zybwhzl.qymc": $("#qymc").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'职业病危害因素种类列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zybwhzlQuery.action',
				queryParams:{
				"zybwhzl.szzid": $("#szzid").val(),
					"zybwhzl.qymc": $("#qymc").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				{field:'szzname',title:'所属地区',width:fixWidth(0.15)},
				          {field:'qymc',title:'企业名称',width:fixWidth(0.15)},
				          {field:'tbr',title:'填报人',width:fixWidth(0.15)},
				          {field:'lxdh',title:'联系电话',width:fixWidth(0.15)},
 						  {field:'createTime',title:'填报时间',width:fixWidth(0.2),formatter:function(value,rec){
				          	if(rec.createTime==null) {return;}
							var date = new Date(rec.createTime.time);
							var month = parseInt(date.getMonth()+1);
							return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
				          }},
				          {field:'op',title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
	                            if(flag == '1')
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
				          		}
	                            else
	                            {
	                            	return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>";
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
				<s:if test="flag==3">
				 	<th width="15%">所属地区</th>
					<td width="35%"><cus:SelectOneTag property="zybwhzl.szzid" defaultText='请选择' codeName="相城地址" value="${zybwhzl.szzid}" /></td>
				</s:if>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="zybwhzl.qymc" id="qymc" value="${zybwhzl.qymc}" type="text"></td>
			</tr>
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
				<a href="###" class="easyui-linkbutton" onclick="search_zybwhzl()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="flag == 1">
				<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
				</s:if>
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

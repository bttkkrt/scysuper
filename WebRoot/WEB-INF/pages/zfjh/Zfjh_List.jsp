<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ZFJH管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zfjh/zfjhInitEdit.action?flag=add",900,600);
        	/* var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 900) / 2;
    		var yc = (ah - 800) / 2;
            openparentWindow("newWindow","添加ZFJH",xc,yc,"900","800","${ctx}/jsp/zfjh/zfjhInitEdit.action?flag=add&dt="+dt.getTime(),true,true,true,false,true,"win");
        	 */
        }
        function edit(row_Id){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zfjh/zfjhInitEdit.action?flag=mod&zfjh.id="+row_Id,900,600);
        	/* var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 900) / 2;
    		var yc = (ah - 800) / 2;
            openparentWindow("newWindow","修改ZFJH",xc,yc,"900","800","${ctx}/jsp/zfjh/zfjhInitEdit.action?flag=mod&zfjh.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	 */
        }
        function view(row_Id){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zfjh/zfjhView.action?zfjh.id="+row_Id,900,600);
        	/* var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 900) / 2;
    		var yc = (ah - 800) / 2;
            openparentWindow("newWindow","查看ZFJH",xc,yc,"900","800","${ctx}/jsp/zfjh/zfjhView.action?zfjh.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	 */
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zfjh();
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
		                	url : "zfjhDel.action",
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
		                        	search_zfjh();
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
        
        function search_zfjh(){
        	var queryParams = {
				"zfjh.proId": $("#proId").val(),
"zfjh.zfjhYear": $("#zfjhYear").val(),
"zfjh.zfjhWeek": $("#zfjhWeek").val(),
"zfjh.zgqyName": $("#zgqyName").val(),
"zfjh.zgqyDay": $("#zgqyDay").val(),
"zfjh.zgqyContent": $("#zgqyContent").val(),
"zfjh.hylyName": $("#hylyName").val(),
"zfjh.hylyDay": $("#hylyDay").val(),
"zfjh.hylyContent": $("#hylyContent").val(),
"zfjh.zhjgName": $("#zhjgName").val(),
"zfjh.zhjgDay": $("#zhjgDay").val(),
"zfjh.zhjgContent": $("#zhjgContent").val(),
"zfjh.xqzfName": $("#xqzfName").val(),
"zfjh.xqzfDay": $("#xqzfDay").val(),
"zfjh.xqzfContent": $("#xqzfContent").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'执法计划列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zfjhQuery.action',
				queryParams:{
					"zfjh.proId": $("#proId").val(),
"zfjh.zfjhYear": $("#zfjhYear").val(),
"zfjh.zfjhWeek": $("#zfjhWeek").val(),
"zfjh.zgqyName": $("#zgqyName").val(),
"zfjh.zgqyDay": $("#zgqyDay").val(),
"zfjh.zgqyContent": $("#zgqyContent").val(),
"zfjh.hylyName": $("#hylyName").val(),
"zfjh.hylyDay": $("#hylyDay").val(),
"zfjh.hylyContent": $("#hylyContent").val(),
"zfjh.zhjgName": $("#zhjgName").val(),
"zfjh.zhjgDay": $("#zhjgDay").val(),
"zfjh.zhjgContent": $("#zhjgContent").val(),
"zfjh.xqzfName": $("#xqzfName").val(),
"zfjh.xqzfDay": $("#xqzfDay").val(),
"zfjh.xqzfContent": $("#xqzfContent").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'proId',title:'关联编号',width:100},
{field:'zfjhYear',title:'年',width:100},
{field:'zfjhWeek',title:'周次',width:100},
{field:'zgqyName',title:'直管企业名称',width:100},
{field:'zgqyDay',title:'直管企业工作日',width:100},
{field:'zgqyContent',title:'直管企业检查内容',width:100},
{field:'hylyName',title:'行业领域名称',width:100},
{field:'hylyDay',title:'行业领域工作日',width:100},
{field:'hylyContent',title:'行业领域检查内容',width:100},
{field:'zhjgName',title:'综合监管名称',width:100},
{field:'zhjgDay',title:'综合监管工作日',width:100},
{field:'zhjgContent',title:'综合监管检查内容',width:100},
{field:'xqzfName',title:'县区政府名称',width:100},
{field:'xqzfDay',title:'县区政府工作日',width:100},
{field:'xqzfContent',title:'县区政府检查内容',width:100},

				          {field:'op',title:'操作',width:100,formatter:function(value,rec){
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
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">关联编号</th>
				<td width="35%"><input name="zfjh.proId" id="proId" value="${zfjh.proId}" type="text"></td>
				<th width="15%">年</th>
				<td width="35%"><input name="zfjh.zfjhYear" id="zfjhYear" value="${zfjh.zfjhYear}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">周次</th>
				<td width="35%"><input name="zfjh.zfjhWeek" id="zfjhWeek" value="${zfjh.zfjhWeek}" type="text"></td>
				<th width="15%">直管企业名称</th>
				<td width="35%"><input name="zfjh.zgqyName" id="zgqyName" value="${zfjh.zgqyName}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">直管企业工作日</th>
				<td width="35%"><input name="zfjh.zgqyDay" id="zgqyDay" value="${zfjh.zgqyDay}" type="text"></td>
				<th width="15%">直管企业检查内容</th>
				<td width="35%"><input name="zfjh.zgqyContent" id="zgqyContent" value="${zfjh.zgqyContent}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">行业领域名称</th>
				<td width="35%"><input name="zfjh.hylyName" id="hylyName" value="${zfjh.hylyName}" type="text"></td>
				<th width="15%">行业领域工作日</th>
				<td width="35%"><input name="zfjh.hylyDay" id="hylyDay" value="${zfjh.hylyDay}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">行业领域检查内容</th>
				<td width="35%"><input name="zfjh.hylyContent" id="hylyContent" value="${zfjh.hylyContent}" type="text"></td>
				<th width="15%">综合监管名称</th>
				<td width="35%"><input name="zfjh.zhjgName" id="zhjgName" value="${zfjh.zhjgName}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">综合监管工作日</th>
				<td width="35%"><input name="zfjh.zhjgDay" id="zhjgDay" value="${zfjh.zhjgDay}" type="text"></td>
				<th width="15%">综合监管检查内容</th>
				<td width="35%"><input name="zfjh.zhjgContent" id="zhjgContent" value="${zfjh.zhjgContent}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">县区政府名称</th>
				<td width="35%"><input name="zfjh.xqzfName" id="xqzfName" value="${zfjh.xqzfName}" type="text"></td>
				<th width="15%">县区政府工作日</th>
				<td width="35%"><input name="zfjh.xqzfDay" id="xqzfDay" value="${zfjh.xqzfDay}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">县区政府检查内容</th>
				<td width="35%"><input name="zfjh.xqzfContent" id="xqzfContent" value="${zfjh.xqzfContent}" type="text"></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center" >
				<a href="###" class="easyui-linkbutton" onclick="search_zfjh()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
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

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>COMPANY管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function view(row_Id){
        	var dt=new Date();
    		
    		window.open("${ctx}/jsp/company/companyView.action?company.id="+row_Id+"&dt="+dt.getTime());
        	
        }
        //选择企业
        function choseCompany(row_Id,name,dwdz,gpsj){
        	window.parent.document.getElementById("qymcid").value=row_Id;
        	window.parent.document.getElementById("qymc").value=name;
        	window.parent.document.getElementById("szz").value=dwdz;
        	if(window.parent.document.getElementById("gpsj")!=null)
        	window.parent.document.getElementById("gpsj").value=gpsj;
        	parent.pp_close_win();
        	
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_company();
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
		                	url : "companyDel.action",
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
		                        	search_company();
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
        
        function search_company(){
        	var queryParams = {
				"company.companyname": $("#companyname").val(),
				"company.fddbr": $("#fddbr").val(),
				"company.zzjgdm": $("#zzjgdm").val(),
				"company.gszch": $("#gszch").val(),
				"company.qylx": $("#qylx").val(),
				"company.hyfl": $("#hyfl").val(),
				"company.qyzclx": $("#qyzclx").val(),
				"company.whpqylx": $("#whpqylx").val(),
				"company.zywhqylx": $("#zywhqylx").val(),
				 "queryQyclsjStart" :$("#queryQyclsjStart").val(),
				 "queryQyclsjEnd" :$("#queryQyclsjEnd").val(),
				  "company.pass" :$("#pass").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
			$('#pagination').datagrid({
				title:'企业信息管理列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'commoTroubleCompanyQuery.action',
				queryParams:{
					"company.companyname": $("#companyname").val(),
					"company.fddbr": $("#fddbr").val(),
					"company.zzjgdm": $("#zzjgdm").val(),
					"company.gszch": $("#gszch").val(),
					"company.qylx": $("#qylx").val(),
					"company.hyfl": $("#hyfl").val(),
					"company.qyzclx": $("#qyzclx").val(),
					"company.whpqylx": $("#whpqylx").val(),
					"company.zywhqylx": $("#zywhqylx").val(),
					 "queryQyclsjStart" :$("#queryQyclsjStart").val(),
					 "queryQyclsjEnd" :$("#queryQyclsjEnd").val(),
					 "company.pass" :$("#pass").val()
				},
				idField:'id',
				remoteSort: false,
				columns:[[
				          {field:'companyname',title:'单位名称',width:fixWidth(0.3)},
							{field:'qylx',title:'企业类型',width:fixWidth(0.2),formatter:function(value,rec){
							  var temp = '';
							    $.ajax({
							    url: '${ctx}/jsp/admin/code/findCodeValue.action',
							    type: 'post',
							    dataType: 'json', 
							    async : false,
							    data:{        "codeValue.itemValue" : rec.qylx,
							        "codeValue.codeId" : "4028e56c3ff0d189013ff0e6b99e000c"    },
							    error: function(){
							        $.messager.alert('提示','获取一维代码错误！');
							    },
							    success: function(data){
							        temp = data.itemText;
							    }});
							    return temp;}
							},
						{field:'hyfl',title:'行业分类',width:fixWidth(0.25),formatter:function(value,rec){
						  var temp = '';
						    $.ajax({
						    url: '${ctx}/jsp/admin/code/findCodeValue.action',
						    type: 'post',
						    dataType: 'json',
						    async : false,
						    data:{        "codeValue.itemValue" : rec.hyfl,
						        "codeValue.codeId" : "402880484076bce30140a04236590a02"    },
						    error: function(){
						        $.messager.alert('提示','获取一维代码错误！');
						    },
						    success: function(data){
						        temp = data.itemText;
						    }});
						    return temp;}
						},

				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
				          	var gpsj = "";
				          	if(rec.qyclsj != null)
				          	{
				          		var date = new Date(rec.qyclsj.time);
								var month = parseInt(date.getMonth()+1);
								gpsj =  date.getFullYear()+'-'+month+'-'+date.getDate();
				          	}
	                        return "<span style='color:red;cursor:hand' onclick=\"choseCompany('"+rec.id+"','"+rec.companyname+"','"+rec.dwdz1+"','"+gpsj+"')\">选择</span>&nbsp;";
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
				<th width="10%">单位名称</th>
				<td width="10%"><input name="company.companyname" id="companyname" value="${company.companyname}" type="text"></td>
				<th width="10%">法定代表人</th>
				<td width="10%"><input name="company.fddbr" id="fddbr" value="${company.fddbr}" type="text"></td>
			</tr>
			<tr>
				<th width="10%">组织机构代码</th>
				<td width="10%"><input name="company.zzjgdm" id="zzjgdm" value="${company.zzjgdm}" type="text"></td>
				<th width="10%">工商注册号</th>
				<td width="10%"><input name="company.gszch" id="gszch" value="${company.gszch}" type="text"></td>
			</tr>
			<tr>
				<th width="5%">企业类型</th>
				<td width="10%"><cus:SelectOneTag property="company.qylx" defaultText='请选择' codeName="企业类型" value="${company.qylx}" /></td>
				<th width="5%">行业分类</th>
				<td width="10%"><cus:SelectOneTag property="company.hyfl" defaultText='请选择' codeName="行业分类" value="${company.hyfl}" /></td>
			</tr>
			<tr>
				<th width="5%">企业注册类型</th>
				<td width="10%"><cus:SelectOneTag property="company.qyzclx" defaultText='请选择' codeName="企业注册类型" value="${company.qyzclx}" /></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="8" style="text-align:center">
				<a href="###" class="easyui-linkbutton" onclick="search_company()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<!--<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp; -->
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

<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>企业账号管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_company();
        }
        function del(id){
			    $.messager.confirm("重置密码","确定要将密码重置为123456吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "updatePass.action?company.id="+id,
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','重置密码时出错！');
		                    },
		                    success: function(data){
		                        if(data.result == 'true'){
		                        	$.messager.alert('提示','重置密码成功！');
		                        	search_company();
		                        }else{
		                        	$.messager.alert('错误','重置密码时出错！');
		                        }
		                    }
		                });
			        }
			    });
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
"company.gszch": $("#gszch").val(),
  "company.loginname" :$("#loginname").val(),
  "company.dwdz1":$("#dwdz1").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'企业账号管理列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'companyQuerys.action',
				queryParams:{
					"company.companyname": $("#companyname").val(),
"company.gszch": $("#gszch").val(),
  "company.loginname" :$("#loginname").val(),
  "company.dwdz1":$("#dwdz1").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'companyname',title:'单位名称',width:fixWidth(0.3)},
{field:'gszch',title:'工商注册号',width:fixWidth(0.25)},
{field:'loginname',title:'用户名',width:fixWidth(0.25)},
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
				          		return "<span style='color:red;cursor:hand' onclick=\"del('"+rec.id+"')\">重置密码</span>&nbsp;";
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
		function querySzz(obj)
	    {
	    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#dwdz1'); 
						selectContainer.empty();
						var option = $('<option></option>').text("").val(""); 
						selectContainer.append(option); 
		  				for(var i=0; i<json.length; i++){
					    	option = $('<option></option>').text(json[i].name).val(json[i].id);
							selectContainer.append(option); 
					 	}
					},
					dateType:"json"
				});
	    }
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">所属地区</th>
				<td width="35%">
						<cus:SelectOneTag property="company.county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
						<select id="dwdz1" name="company.dwdz1" style="width:150px;" ><option value="">请选择</option></select>
						
						<!--hanxc 2014/11/11 
					<cus:SelectOneTag property="company.dwdz1" defaultText='请选择' codeName="相城地址" value="${company.dwdz1}" />
						 -->
				</td>
				<th width="15%">单位名称</th>
				<td width="35%"><input name="company.companyname" id="companyname" value="${company.companyname}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">工商注册号</th>
				<td width="35%"><input name="company.gszch" id="gszch" value="${company.gszch}" type="text"></td>
				<th width="15%">用户名</th>
				<td width="35%"><input name="company.loginname" id="loginname" value="${company.loginname}" type="text"></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
					<a href="###" class="easyui-linkbutton" onclick="search_company()" iconCls="icon-search">查询</a>&nbsp;
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

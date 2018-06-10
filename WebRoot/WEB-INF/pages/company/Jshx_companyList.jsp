<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>企业选择</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
	function choose(id,name,szzid,county,dwdz2,fddbr,fddbrlxhm)
		{
			window.returnValue=id + ";" + name + ";" + szzid+";"+county+";"+dwdz2+";"+ fddbr+";"+fddbrlxhm;
			window.close();
		}
		
		$(function(){
			var dwddz = $('#companyDwdz1').val();
			var tempDeptCode = $("#tempDeptCode").val();
			if(tempDeptCode != '1'){
		    	$.ajax({
					type:"POST", 
	      				async:false,
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+tempDeptCode,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#dwdz1'); 
						selectContainer.empty();
						var option = $('<option></option>').text("--请选择--").val(""); 
						selectContainer.append(option); 
		  				for(var i=0; i<json.length; i++){
		  					if(dwddz==json[i].id){
						    	option = $('<option></option>').text(json[i].name).val(json[i].id).attr("selected","selected");
								selectContainer.append(option); 
		  					}else{
		  						option = $('<option></option>').text(json[i].name).val(json[i].id);
								selectContainer.append(option); 
		  					}
					 	}
					},
					dateType:"json"
				});
			}else{
				var count = $("#county").val();
				$.ajax({
					type:"POST", 
	      				async:false,
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+count,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#dwdz1'); 
						selectContainer.empty();
						var option = $('<option></option>').text("--请选择--").val(""); 
						selectContainer.append(option); 
		  				for(var i=0; i<json.length; i++){
		  					if(dwddz==json[i].id){
						    	option = $('<option></option>').text(json[i].name).val(json[i].id).attr("selected","selected");
								selectContainer.append(option); 
		  					}else{
		  						option = $('<option></option>').text(json[i].name).val(json[i].id);
								selectContainer.append(option); 
		  					}
					 	}
					},
					dateType:"json"
				});
			}
				datagrid();
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
		
		
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_company();
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
  				"company.dwdz1":$("#dwdz1").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        function datagrid() {
        	
			$('#pagination').datagrid({
				title:'企业信息管理列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'companyListQuery.action',
				queryParams:{
					"company.companyname": $("#companyname").val(),
  					"company.dwdz1":$("#dwdz1").val()
				},
				idField:'id',
				remoteSort: false,
				columns:[[
				          {field:'companyname',title:'单位名称',width:fixWidth(0.2)},
{field:'fddbr',title:'法定代表人',width:fixWidth(0.1)},
{field:'gszch',title:'工商注册号',width:fixWidth(0.15)},
{field:'qylx',title:'企业类型',width:fixWidth(0.15),formatter:function(value,rec){
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
{field:'hyfl',title:'行业分类',width:fixWidth(0.15),formatter:function(value,rec){
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
				          {field:'op',title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
				        	  return "<span style='color:red;cursor:hand' onclick=\"choose('"+rec.id+"','"+ rec.companyname +"','"+ rec.dwdz1 +"','"+ rec.county +"','"+ rec.dwdz2 +"','"+ rec.fddbr +"','"+ rec.fddbrlxhm +"')\">选择</span>";
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
		}

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
        $(document).ready(function(){
        });
        
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<input type="hidden" id="tempDeptCode" name="tempDeptCode" value="${tempDeptCode}">
	<input type="hidden" id="companyDwdz1" name="company.dwdz1" value="${company.dwdz1}">
	
	<div class="submitdata">
		<table width="100%">
			<tr>
				<s:if test="flag == 1">
				<th width="15%">所属地区</th>
				<td width="35%">
					<c:if test="${tempDeptCode != 1}">
						<select id="dwdz1" name="company.dwdz1" style="width:100px;" ><option value="">请选择</option></select><font style="color:red">*</font>
					</c:if>
					<c:if test="${tempDeptCode == 1}">
						<cus:SelectOneTag property="company.county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);" value="${company.county}" style="width:100px;" />
						<select id="dwdz1" name="company.dwdz1" style="width:100px;" ><option value="">请选择</option></select><font style="color:red">*</font>
					</c:if>
					<!-- 
					<cus:SelectOneTag property="company.dwdz1" defaultText='请选择' codeName="相城地址" value="${company.dwdz1}" />
				 	-->
				</td>
				</s:if>
				<th width="15%">单位名称</th>
				<td width="35%"><input name="company.companyname" id="companyname" value="${company.companyname}" type="text"></td>
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

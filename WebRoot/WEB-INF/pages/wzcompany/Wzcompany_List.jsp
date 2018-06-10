<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>无证照企业登记基本信息管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	window.open("${ctx}/jsp/wzcompany/wzcompanyInitEdit.action?flag=add");
        }
        function edit(row_Id){
        	window.open("${ctx}/jsp/wzcompany/wzcompanyInitEdit.action?flag=mod&wzcompany.id="+row_Id);
        }
        function view(row_Id){
        	window.open("${ctx}/jsp/wzcompany/wzcompanyView.action?wzcompany.id="+row_Id);
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_wzcompany();
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
		                	url : "wzcompanyDel.action",
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
		                        	search_wzcompany();
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
        function querySzc(obj)
    	{
    		$.ajax({
				type:"POST",
				url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
				success:function(json){
					json = eval('(' + json + ')');
					var selectContainer = $('#szc'); 
					selectContainer.empty();
					var option = $('<option></option>').text("").val(""); 
					selectContainer.append(option); 
	  				for(var i=0; i<json.length; i++){
						var option = $('<option></option>').text(json[i].name).val(json[i].id); 
						selectContainer.append(option); 
				 	}
				},
				dateType:"json"
			});
    	}   
        function search_wzcompany(){
        	var queryParams = {
        		"wzcompany.companyname": $("#companyname").val(),
        		"wzcompany.fzr": $("#fzr").val(),
        		"wzcompany.szzid": $("#szzid").val(),
        		"wzcompany.szc": $("#szc").val(),
        		"wzcompany.hyfl": $("#hyfl").val(),
        		"wzcompany.jycsxz": $("#jycsxz").val(),
				"wzcompany.ifwhpqylx": $("#ifwhpqylx").val(),
				"wzcompany.ifzywhqylx": $("#ifzywhqylx").val(),
				"wzcompany.ifwxqy": $("#ifwxqy").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'无证照企业登记基本信息列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'wzcompanyQuery.action',
				queryParams:{
					"wzcompany.companyname": $("#companyname").val(),
        		"wzcompany.fzr": $("#fzr").val(),
        		"wzcompany.szzid": $("#szzid").val(),
        		"wzcompany.szc": $("#szc").val(),
        		"wzcompany.hyfl": $("#hyfl").val(),
        		"wzcompany.jycsxz": $("#jycsxz").val(),
				"wzcompany.ifwhpqylx": $("#ifwhpqylx").val(),
				"wzcompany.ifzywhqylx": $("#ifzywhqylx").val(),
				"wzcompany.ifwxqy": $("#ifwxqy").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'companyname',title:'企业名称',width:fixWidth(0.2)},
				          {field:'countyName',title:'所在区县',width:fixWidth(0.1)},
				          {field:'szzname',title:'所在乡镇',width:fixWidth(0.1)},
				          {field:'fzr',title:'负责人',width:fixWidth(0.1)},
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
						  {field:'jycsxz',title:'经营场所性质',width:fixWidth(0.15),formatter:function(value,rec){
  							var temp = '';
    						$.ajax({
    							url: '${ctx}/jsp/admin/code/findCodeValue.action',
   			 					type: 'post',
    							dataType: 'json',
    							async : false,
    							data:{        "codeValue.itemValue" : rec.jycsxz,
        						"codeValue.codeId" : "4028804846a8ac0d0146b36852801075"    },
    							error: function(){
        							$.messager.alert('提示','获取一维代码错误！');
    							},
    							success: function(data){
        							temp = data.itemText;
    							}});
    						return temp;}
						  },
				          {field:'op',title:'操作'title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
				        	      /*
								 var str="";
					        	  $.ajax({
					        		    url: '${ctx}/jsp/admin/role/buttons.action',
					        		    type: 'post',
					        		    dataType: 'json',
					        		    async : false,
					        		    data:{    
					        		    	"function":"",
					        		    	"num":"8", 
					        		    	"moduleId":"${currModuleCode}",
					        		    	"functionName":"查看" 
					        		    	},
					        		    error: function(){
					        		        $.messager.alert('提示','获取一维代码错误！');
					        		    },
					        		    success: function(data){
					        		    	if(""!=data.result){
					        		        str+= "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;";
					        		    	}
					        		    }});
					        	  $.ajax({
					        		    url: '${ctx}/jsp/admin/role/buttons.action',
					        		    type: 'post',
					        		    dataType: 'json',
					        		    async : false,
					        		    data:{    
					        		    	"function":"",
					        		    	"num":"3", 
					        		    	"moduleId":"${currModuleCode}",
					        		    	"functionName":"修改" 
					        		    	},
					        		    error: function(){
					        		        $.messager.alert('提示','获取一维代码错误！');
					        		    },
					        		    success: function(data){
					        		    	if(""!=data.result){
					        		        str+= "<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
					        		    	}
					        		    }});
					        	 return str;*/
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
		function querySzz(obj)
	    {
	    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#szzid'); 
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
				<th width="15%">企业名称</th>
				<td width="35%"><input id="companyname" name="wzcompany.companyname" value="${wzcompany.companyname}" type="text" style="width:90%;"></td>				
				<th width="15%">负责人</th>
				<td width="35%"><input id="fzr" name="wzcompany.fzr" value="${wzcompany.fzr}" type="text" style="width:90%;"></td>
			</tr>
			<tr>
				<th width="15%">所在镇</th>
				<td width="35%">
						<cus:SelectOneTag property="wzcompany.county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
						<select id="szzid" name="wzcompany.szzid"   style="width:150px;" ><option value="">请选择</option></select>
						<!--hanxc 2014/11/11 
						<cus:SelectOneTag property="wzcompany.szzid" defaultText='请选择' codeName="相城地址" value="${wzcompany.szzid}" onchange="querySzc(this.value);" style="width:90%;"/>
						 -->
				</td>
				<!-- 去除查询条件 GY-UPDATE 2015-02-03 
				<th width="15%">所在村</th>
				<td width="35%">
					<s:select theme="simple" cssStyle="width:90%;" id="szc" emptyOption= "true" name="wzcompany.szc" list="%{deptlist}" listKey="deptCode" listValue="deptName" value="{wzcompany.szc}"></s:select>
				</td>
				 -->
				 <th width="5%">是否为五小企业</th>
				<td width="15%"><s:select id="ifwxqy" cssStyle="width:90%;" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','1':'是','0':'否'}" name="wzcompany.ifwxqy"/></td>
			</tr>
			<tr>
				<th width="15%">行业分类</th>
				<td width="35%"><cus:SelectOneTag property="wzcompany.hyfl" defaultText='请选择' codeName="企业行业分类" value="${wzcompany.hyfl}" style="width:90%;"/></td>
				<th width="15%">经营场所性质</th>
				<td width="35%"><cus:SelectOneTag property="wzcompany.jycsxz" defaultText='请选择' codeName="经营场所性质" value="${wzcompany.jycsxz}" style="width:90%;"/></td>
			</tr>
			<tr>
				<th width="5%">是否危化品企业</th>
				<td width="15%"><s:select id="ifwhpqylx" cssStyle="width:90%;" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','1':'是','0':'否'}" name="wzcompany.ifwhpqylx"/></td>
				<th width="5%">是否职业危害企业</th>
				<td width="15%"><s:select id="ifzywhqylx" cssStyle="width:90%;" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','1':'是','0':'否'}" name="wzcompany.ifzywhqylx"/></td>
			</tr>
			<!-- 去除查询条件 GY-UPDATE 2015-02-03 
			<tr>
				<th width="5%">是否为五小企业</th>
				<td width="15%"><s:select id="ifwxqy" cssStyle="width:90%;" listKey="key" listValue="value"  theme="simple" list="#{'':'请选择','1':'是','0':'否'}" name="wzcompany.ifwxqy"/></td>
			</tr>
			-->
			<tr>
				<td colspan="4" style="text-align:center">
				<a href="###" class="easyui-linkbutton" onclick="search_wzcompany()" iconCls="icon-search">查询</a>&nbsp;
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

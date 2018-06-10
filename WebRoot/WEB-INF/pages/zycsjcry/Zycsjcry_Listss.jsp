<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>职业健康监护管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
	 	var flag = "${flag}";
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zycsjcry();
        }
        function view(row_Id){
            location.href = "${ctx}/jsp/zycsjcry/zycsjcryLists.action?zycsjcry.qyid="+row_Id;
        	
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
        
        function search_zycsjcry(){
        	var queryParams = {
"zycsjcry.szzid": $("#szzid").val(),
"zycsjcry.qymc": $("#qymc").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'职业健康监护列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zycsjcryQuerys.action',
				queryParams:{
					"zycsjcry.szzid": $("#szzid").val(),
"zycsjcry.qymc": $("#qymc").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
{field:'szzname',title:'所属地区',width:fixWidth(0.3)},
{field:'qymc',title:'企业名称',width:fixWidth(0.3)},
				          {field:'op',title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
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
					        	  
					        	 return str;*/
								 return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.qyid+"')\">查看</span>";
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
			  	<s:if test="flag == 2">
				 	<th width="10%">所属地区</th>
					<td width="15%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szzid" name="zycsjcry.szzid" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					<cus:SelectOneTag property="zycsjcry.szzid" defaultText='请选择' codeName="相城地址" value="${zycsjcry.szzid}" /></td>
					 -->
				</s:if>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="zycsjcry.qymc" id="qymc" value="${zycsjcry.qymc}" type="text"></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				
				<a href="###" class="easyui-linkbutton" onclick="search_zycsjcry()" iconCls="icon-search">查询</a>&nbsp;
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

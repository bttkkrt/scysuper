<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>JSHX_BZH管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
	 	function view(row_Id){
        	window.open("${ctx}/jsp/bzh/jshxBzhView.action?jshxBzh.id="+row_Id);
        }
         function exportdata()
        {
        	document.myform.action = "${ctx}/jsp/bzh/jshxBzhExport.action";
        	document.myform.submit();
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_jshxBzh();
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
        
        function search_jshxBzh(){
        	var queryParams = {
        	"jshxBzh.szzid": $("#szzid").val(),
					"jshxBzh.szzname": $("#szzname").val(),
					"jshxBzh.qymc": $("#qymc").val(),
					"jshxBzh.dbjb": $("#dbjb").val(),
					"jshxBzh.whpqylx": $("#whpqylx").val(),
 "queryYxqStart" :$("#queryYxqStart").val(),
 "queryYxqEnd" :$("#queryYxqEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'安全生产标准化达标列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'jshxBzhQuerys.action',
				queryParams:{
				"jshxBzh.szzid": $("#szzid").val(),
					"jshxBzh.szzname": $("#szzname").val(),
					"jshxBzh.qymc": $("#qymc").val(),
					"jshxBzh.dbjb": $("#dbjb").val(),
					"jshxBzh.whpqylx": $("#whpqylx").val(),
 "queryYxqStart" :$("#queryYxqStart").val(),
 "queryYxqEnd" :$("#queryYxqEnd").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'szzname',title:'所属地区',width:fixWidth(0.1)},
							{field:'qymc',title:'企业名称',width:fixWidth(0.15)},
							{field:'score',title:'考评分数',width:fixWidth(0.08)},
							{field:'dbjb',title:'达标级别',width:fixWidth(0.08),formatter:function(value,rec){
							  var temp = '';
							    $.ajax({
							    url: '${ctx}/jsp/admin/code/findCodeValue.action',
							    type: 'post',
							    dataType: 'json',
							    async : false,
							    data:{ "codeValue.itemValue" : rec.dbjb,
							        "codeValue.codeId" : "4028804840b9689c0140c43a1d6a0333"},
							    error: function(){
							        $.messager.alert('提示','获取一维代码错误！');
							    },
							    success: function(data){
							        temp = data.itemText;
							    }});
							    return temp;}
							},
							{field:'yxq',title:'有效期',width:fixWidth(0.1)},
							{field:'fzrq',title:'发证日期',width:fixWidth(0.1)},
							{field:'zsh',title:'证书号',width:fixWidth(0.1)},
							{field:'fzjg',title:'发证机关',width:fixWidth(0.15)},
{field:'whpqylx',title:'危化品企业类型',width:fixWidth(0.1),formatter:function(value,rec){
  var temp = '';
  var a = rec.whpqylx.split(",");
  for(var i=0;i<a.length;i++)
  {
  	$.ajax({
    url: '${ctx}/jsp/admin/code/findCodeValue.action',
    type: 'post',
    dataType: 'json',
    async : false,
    data:{        "codeValue.itemValue" : $.trim(a[i]),
        "codeValue.codeId" : "4028e56c40a9a6750140a9c91e2f0007"    },
    error: function(){
        $.messager.alert('提示','获取一维代码错误！');
    },
    success: function(data){
    	if(i == a.length - 1)
    	{
    		temp += data.itemText;
    	}
    	else
    	{
    		temp += data.itemText + ",";
    	}
    }});
  }
    return temp;}
},
				          {field:'op',title:'操作',width:fixWidth(0.1),formatter:function(value,rec){
				          		return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;";
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
<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">所属地区</th>
				<td width="35%"><cus:SelectOneTag property="jshxBzh.szzid" defaultText='请选择' codeName="相城地址" value="${jshxBzh.szzid}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="jshxBzh.qymc" id="qymc" value="${jshxBzh.qymc}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">达标级别</th>
				<td width="35%">
					<cus:SelectOneTag property="jshxBzh.dbjb" defaultText='请选择' codeName="证书级别" value="${jshxBzh.dbjb}" />
				</td>
				<th width="5%">危化品企业类型</th>
				<td width="15%"><cus:SelectOneTag property="jshxBzh.whpqylx" defaultText='请选择' codeName="危化品企业类型" value="${jshxBzh.whpqylx}"/></td>
			</tr>
			<tr>
				<th width="15%">有效期</th>
				<td width="35%"><input name="queryYxqStart" id="queryYxqStart" value="${queryYxqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryYxqEnd\')}'})" >
					-<input name="queryYxqEnd" id="queryYxqEnd" value="${queryYxqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryYxqStart\')}'})" ></td>
			</tr>
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
				<a href="###" class="easyui-linkbutton" onclick="search_jshxBzh()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<a href="###" class="easyui-linkbutton" onclick="exportdata();" iconCls="icon-add">导出</a>&nbsp;
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

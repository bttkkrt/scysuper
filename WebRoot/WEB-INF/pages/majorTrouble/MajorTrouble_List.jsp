<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>MAJOR_TROUBLE管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
          var type = '${type}';
          var flag = '${flag}';
          var createUserID = "${createUserID}";
          var deptId = '${deptId}';
        function addNew(){
        	var dt=new Date();
        	window.open("${ctx}/jsp/majorTrouble/majorTroubleInitEdit.action?flag=add&dt="+dt.getTime());
        }
        function edit(row_Id){
        	var dt=new Date();
        	window.open("${ctx}/jsp/majorTrouble/majorTroubleInitEdit.action?flag=mod&majorTrouble.id="+row_Id+"&dt="+dt.getTime());
        }
         function uploadZgxx(row_Id){
        	var dt=new Date();
        	window.open("${ctx}/jsp/majorTrouble/majorTroubleInitEdit.action?flag=upload&majorTrouble.id="+row_Id+"&dt="+dt.getTime());
        }
        function view(row_Id){
        	var dt=new Date();
            window.open("${ctx}/jsp/majorTrouble/majorTroubleView.action?majorTrouble.id="+row_Id+"&dt="+dt.getTime());
        }
         //审核操作
          function shenhe(row_Id){
        	var dt=new Date();
      		window.open("${ctx}/jsp/majorTrouble/majorTroubleView.action?majorTrouble.id="+row_Id+"&dt="+dt.getTime()+"&type="+type);
      }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_majorTrouble();
        }
        function del(){
        	var rows = document.getElementsByName("xxx");
        	var ids = "";
        	var len = rows.length;
			for(var i=0;i<len;i++){
				if(rows[i].checked){
					ids += rows[i].value+"|";
				}
			}
			if(ids == ""){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "majorTroubleDel.action",
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
		                        	search_majorTrouble();
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
        
        function search_majorTrouble(){
        	var queryParams = {
				"majorTrouble.szz": $("#szz").val(),
				"majorTrouble.shzt": $("#shzt").val(),
				"majorTrouble.qymc": $("#qymc").val(),
				"gpBeginDate" : $("#gpBeginDate").val(),
				"gpEndDate" : $("#gpEndDate").val(),
				"wcBeginDate" : $("#wcBeginDate").val(),
				"wcEndDate" : $("#wcEndDate").val(),
				"ysBeginDate" : $("#ysBeginDate").val(),
				"ysEndDate" : $("#ysEndDate").val(),
				"majorTrouble.szc": $("#szc").val(),
				"majorTrouble.deptId": $("#deptId").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
           function querySzc(obj)
    {
    	$.ajax({
				type:"POST",
				url:"${ctx}/jsp/company/companyQuerySzc.action?mode=ajaxJson&company.county="+obj,
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
        $(function(){
        	
			$('#pagination').datagrid({
				title:'重大安全隐患列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'majorTroubleQuery.action',
				queryParams:{
					"majorTrouble.szz": $("#szz").val(),
					"majorTrouble.shzt": $("#shzt").val(),
					"majorTrouble.qymc": $("#qymc").val(),
					"gpBeginDate" : $("#gpBeginDate").val(),
					"gpEndDate" : $("#gpEndDate").val(),
					"wcBeginDate" : $("#wcBeginDate").val(),
					"wcEndDate" : $("#wcEndDate").val(),
					"ysBeginDate" : $("#ysBeginDate").val(),
					"ysEndDate" : $("#ysEndDate").val(),
				"majorTrouble.szc": $("#szc").val(),
				"majorTrouble.deptId": $("#deptId").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				          {field:'id',width:30,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if(rec.createUserID != createUserID){//这里判断是不是已审核通过 
								opt = ''; 
						    } 
						    return opt ; 

				    }}
				]],
				columns:[[
				            {field:'qymc',title:'企业名称',width:fixWidth(0.2)},
				            {field:'yhmc',title:'隐患名称',width:fixWidth(0.1)},
							{field:'yhs',title:'隐患数',width:fixWidth(0.05)},
							{field:'zrr',title:'责任人',width:fixWidth(0.1)},
							{field:'zgs',title:'整改数',width:fixWidth(0.05)},
							{field:'zgwcrq',title:'整改完成日期',width:fixWidth(0.1)},
							{field:'tbbm',title:'填报部门',width:fixWidth(0.1),formatter:function(value,rec){
							  var temp = '';
							    $.ajax({
							    url: '${ctx}/jsp/admin/code/findCodeValue.action',
							    type: 'post',
							    dataType: 'json',
							    async : false,
							    data:{        "codeValue.itemValue" : rec.tbbm,
							        "codeValue.codeId" : "4028e5723ff45a92013ff46619910007"    },
							    error: function(){
							        $.messager.alert('提示','获取一维代码错误！');
							    },
							    success: function(data){
							        temp = data.itemText;
							    }});
							    return temp;}
							},

				         {field:'shzt',title:'审核状态',width:fixWidth(0.1),formatter:function(value,rec){
								if(rec.shzt == '0'){
									return '<font color:red>待整改</font>';
								}else if(rec.shzt == '1'){
									return '<font color:red>审核通过</font>';
								}else if(rec.shzt == '2'){
									return '<font color:red>审核未通过</font>';
								}else if(rec.shzt == '3'){
									return '<font color:red>已整改待审核</font>';
								}
							}},
				          {field:'op',title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
				          		if(rec.shzt != '1'&&createUserID == rec.createUserID){//科员 查看 状态 ：待审核 或者 审核未通过
				          			if(rec.shzt == '3'){
				          				return "<span style='color:green;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;"+
				          				"&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
				          			}else if(rec.shzt == '0'){
				          				return "<span style='color:green;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;"+
				          				"&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>"+
				          				"&nbsp;<span style='color:blue;cursor:hand' onclick=\"uploadZgxx('"+rec.id+"')\">上传整改信息</span>";
				          			}
				          			else
				          			{
				          				return "<span style='color:green;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;"+
				          				"&nbsp;<span style='color:blue;cursor:hand' onclick=\"uploadZgxx('"+rec.id+"')\">修改</span>";
				          			}
				          		}else if(rec.shzt == '3'&&type == '2' && rec.deptId == deptId){//科长 查看 审核操作
				          			return "<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
				          		}else{//仅查看角色 审核通过
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;";
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
		function querySzz(obj)
	    {
	    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#szz'); 
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
				<th width="15%">所属区域</th>
				<td width="35%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szz" name="majorTrouble.szz" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					
					<cus:SelectOneTag property="majorTrouble.szz" defaultText='请选择' codeName="相城地址" value="${majorTrouble.szz}" onchange="querySzc(this.value);"/>
					<s:select theme="simple" cssStyle="width:100px;" id="szc" emptyOption= "true" name="majorTrouble.szc" list="%{deptlist}" listKey="deptCode" listValue="deptName" dataType="Require" msg="此项为必选" value="{majorTrouble.szc}"></s:select>
					<font style="color:red">*</font>
					 -->
				</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="majorTrouble.qymc" id="qymc" value="${majorTrouble.qymc}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">审核状态</th>
				<td width="35%">
					<s:select id="shzt" list='#{"":"--请选择---","0":"待整改","1":"审核通过","2":"审核未通过","3":"已整改待审核"}' theme="simple" value="#request.shzt"></s:select>
				</td>
				<th width="15%">整改完成日期</th>
				<td width="35%">
					<input type="text" name="wcBeginDate" id="wcBeginDate"
						class="Wdate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'wcEndDate\')}'})"
						value="${wcBeginDate}">
					~
					<input type="text" name="wcEndDate" id="wcEndDate" class="Wdate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'wcBeginDate\')}'})"
						value="${wcEndDate}">
				</td>
			</tr>
			<tr>
				<th width="15%">检查部门</th>
				<td width="35%"><s:select theme="simple" cssStyle="width:100px;" id="deptId" emptyOption= "true" name="majorTrouble.deptId" list="%{deptList}" listKey="id" listValue="deptName" value="{majorTrouble.deptId}"></s:select></td>
			</tr>
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
				
				<a href="###" class="easyui-linkbutton" onclick="search_majorTrouble()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="type==1">
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

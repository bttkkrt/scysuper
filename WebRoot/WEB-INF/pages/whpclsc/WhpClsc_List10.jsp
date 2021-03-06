<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>WHP_CLSC管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var type="${type}";
        var createUserID = "${createUserID}";
        function addNew(){
            window.open("${ctx}/jsp/whpclsc/whpClscInitEdit.action?flag=add&type=${type}");
        }
        function edit(row_Id){
        	window.open("${ctx}/jsp/whpclsc/whpClscInitEdit.action?flag=mod&type=${type}&whpClsc.id="+row_Id);
        }
        function view(row_Id){
        		window.open("${ctx}/jsp/whpclsc/whpClscView.action?type=${type}&whpClsc.id="+row_Id);
        }
        
        function shenhe(row_Id)
        {
        	window.open("${ctx}/jsp/whpclsc/whpClscShenhe.action?type=${type}&whpClsc.id="+row_Id);
        }
         function exportdata()
        {
        	document.myform.action = "${ctx}/jsp/whpclsc/whpClscExport.action";
        	document.myform.submit();
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_whpClsc();
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
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "whpClscDel.action",
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
		                        	search_whpClsc();
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
        
        function search_whpClsc(){
        	var queryParams = {
        		"whpClsc.pjjg":$("#pjjg").val(),
				"whpClsc.szzid": $("#szzid").val(),
				"whpClsc.qymc": $("#qymc").val(),
				"whpClsc.state": $("#state").val(),
				"createTimeStart" :$("#createTimeStart").val(),
			 "createTimeEnd" :$("#createTimeEnd").val(),
			 
			 "createTimeStartJs" :$("#createTimeStartJs").val(),
			 "createTimeEndJs" :$("#createTimeEndJs").val(),
			 "createTimeStartSb" :$("#createTimeStartSb").val(),
			 "createTimeEndSb" :$("#createTimeEndSb").val(),
			 "whpClsc.sclx": "10"
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'${title}列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'whpClscQuery.action',
				queryParams:{
				"whpClsc.pjjg":$("#pjjg").val(),
					"whpClsc.szzid": $("#szzid").val(),
					"whpClsc.qymc": $("#qymc").val(),
					"whpClsc.state": $("#state").val(),
			"createTimeStart" :$("#createTimeStart").val(),
			 "createTimeEnd" :$("#createTimeEnd").val(),
			 "createTimeStartJs" :$("#createTimeStartJs").val(),
			 "createTimeEndJs" :$("#createTimeEndJs").val(),
			 "createTimeStartSb" :$("#createTimeStartSb").val(),
			 "createTimeEndSb" :$("#createTimeEndSb").val(),
			 "whpClsc.sclx": "10"
			 
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',width:30,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if(rec.createUserID != createUserID){//这里判断是否是自己创建
								opt = ''; 
						    } 
						    return opt ; 
				    }}
				]],
				columns:[[
					 	{field:'szzname',title:'所属地区',width:fixWidth(0.15)},
						  {field:'qymc',title:'企业名称',width:fixWidth(0.2)},
						  {field:'fileId',title:'档案编号',width:fixWidth(0.1)},
				          {field:'state',title:'审查状态',width:fixWidth(0.15),formatter:function(value,rec){
				          		var str = "";
		        		    	if(rec.state==2 && rec.dutyFlag==1){
		        		    		str = "<span style='color:red'>未通过</span>";
		        		    	}else if(rec.state==1 && rec.dutyFlag==1){
		        		    		str = "<span style='color:blue'>通过</span>";
		        		    	}else{
									str = "<span style='color:green'>待审核</span>";
		        		    	}
		        		    	return str;
				          		/**
				          		if(rec.state =='0'){
				          			value = "待审核";
				          		}else if(rec.state == '2'){
				          			value =  "审核不通过";
				          		}else {
				          			value =  "审核通过";
				          		}
				          		return value;
				          		**/
                          }},
				          {field:'op',title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
	                            /**
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
				        		    $.ajax({
					        		    url: '${ctx}/jsp/admin/role/buttons.action',
					        		    type: 'post',
					        		    dataType: 'json',
					        		    async : false,
					        		    data:{    
					        		    	"function":"",
					        		    	"num":"7", 
					        		    	"moduleId":"${currModuleCode}",
					        		    	"functionName":"审核" 
					        		    	},
					        		    error: function(){
					        		        $.messager.alert('提示','获取一维代码错误！');
					        		    },
					        		    success: function(data){
					        		    	if(""!=data.result){
					        		        str+= "<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
					        		    	}
					        		    }});
					        	 return str;
	                            **/
	                            /**
	                            value = '';
				          		if(rec.state != '1' && rec.createUserID == createUserID){//状态 ：待整改 或者 审核未通过
				          			value =  "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;"
				          			+"&nbsp;<span style='color:green;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
				          		}else if(rec.state == '0'&& rec.shuserid == createUserID){//审核操作
				          			value =  "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
				          		}else{//查看
				          			value =  "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;";
				          		}
				          		return value;
				          		**/
				          		var tempPassFlag = "9";
								$.ajax({
				       				url: '${ctx}/jsp/diggingsinfo/findState.action',
				        		    type: 'post',
				        		    dataType: 'json',
				        		    async : false,
				        		    data:{    
				        		    	"ids":rec.id
				        		    	},
				        		    error: function(){
				        		    },
				        		    success: function(data){
				        		    	if(data.state==1){
				        		    		tempPassFlag="1";
				        		    	}else if(data.state==0 || data.state==2){
				        		    		tempPassFlag="2";
				        		    	}else{
				        		    		tempPassFlag="3";
					        		    }
				        		    }});
	                            
	                            value = '';
				          		if((rec.state == 1 || rec.state == 2) && rec.createUserID == createUserID){//状态 ：待整改 或者 审核未通过
				          			value =  "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;"
				          			+"<span style='color:green;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>&nbsp;";
				          		}
				          		if(${deptflag}=='1'&&tempPassFlag=='2'&&!(rec.state == '1'&&rec.dutyFlag=='1')){//审核操作
				          			value +=  "<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
				          		}
				          		return value;
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
<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="whpClsc.qymc" id="qymc" value="${whpClsc.qymc}" type="text"></td>
				<th width="15%">审查状态</th>
				<td width="35%">
					<s:select id="state" list='#{"":"--请选择---","0":"待审核","1":"审核通过","2":"审核未通过"}' theme="simple"></s:select>
				</td>
			</tr>
			<tr>
				<th width="15%">所在镇</th>
				<td width="35%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szzid" name="whpClsc.szzid" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					<cus:SelectOneTag property="whpClsc.szzid" defaultText='请选择' codeName="相城地址" value="${whpClsc.szzid}" /></td>
					 -->
			</tr>
			
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
				
				<a href="###" class="easyui-linkbutton" onclick="search_whpClsc()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="flag == 1">
					<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>&nbsp;
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

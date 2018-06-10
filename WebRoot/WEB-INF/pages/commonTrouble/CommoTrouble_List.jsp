<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>COMMO_TROUBLE管理</title>
    <%@include file="/common/jsLib.jsp"%>
    <script src="${ctx}/webResources/My97DatePicker/WdatePicker.js"></script>
	<script>
        var type = '${type}';
        var createUserID = "${createUserID}";
          var flag = '${flag}';
          var deptId = '${deptId}';
        function addNew(){
        	var dt=new Date();
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/factorypicture/factorypictureInitEdit.action?flag=add",900,600);
        	//window.open("${ctx}/jsp/commonTrouble/commoTroubleInitEdit.action?flag=add&dt="+dt.getTime());
        }
        function edit(row_Id){
        	var dt=new Date();
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/commonTrouble/commoTroubleInitEdit.action?flag=mod&commoTrouble.id="+row_Id+"&dt="+dt.getTime(),900,600);
        	//window.open("${ctx}/jsp/commonTrouble/commoTroubleInitEdit.action?flag=mod&commoTrouble.id="+row_Id+"&dt="+dt.getTime());
        }
        function uploadInfo(row_Id){//上传整改信息
        	var dt=new Date();
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/commonTrouble/commoTroubleInitEdit.action?flag=upload&commoTrouble.id="+row_Id+"&dt="+dt.getTime(),900,600);
        	//window.open("${ctx}/jsp/commonTrouble/commoTroubleInitEdit.action?flag=upload&commoTrouble.id="+row_Id+"&dt="+dt.getTime());
        }
        function view(row_Id){
        	var dt=new Date();
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/commonTrouble/commoTroubleView.action?commoTrouble.id="+row_Id+"&dt="+dt.getTime(),900,600);
        	//window.open("${ctx}/jsp/commonTrouble/commoTroubleView.action?commoTrouble.id="+row_Id+"&dt="+dt.getTime());
        }
        //审核操作
          function shenhe(row_Id){
        	var dt=new Date();
        	window.open("${ctx}/jsp/commonTrouble/commoTroubleView.action?commoTrouble.id="+row_Id+"&dt="+dt.getTime()+"&type="+type);
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_commoTrouble();
        }
        function del(){
        	//var rows = $('#pagination').datagrid('getSelections');
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
		                	url : "commoTroubleDel.action",
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
		                        	search_commoTrouble();
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
        
        function search_commoTrouble(){
        	var queryParams = {
				"commoTrouble.szz": $("#szz").val(),
				"commoTrouble.qymc": $("#qymc").val(),
				"commoTrouble.jcsj": $("#jcsj").val(),
				"commoTrouble.jcry": $("#jcry").val(),
				"commoTrouble.shzt": $("#shzt").val(),
				"commoTrouble.zfws": $("#zfws").val(),
				"jcBeginDate" : $("#jcBeginDate").val(),
				"jcEndDate" : $("#jcEndDate").val(),
				"wcBeginDate" : $("#wcBeginDate").val(),
				"wcEndDate" : $("#wcEndDate").val(),
				"commoTrouble.szc": $("#szc").val(),
				"commoTrouble.deptId": $("#deptId").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
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
        $(function(){
        	
			$('#pagination').datagrid({
				title:'一般安全隐患列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'commoTroubleQuery.action',
				queryParams:{
					"commoTrouble.szz": $("#szz").val(),
					"commoTrouble.qymc": $("#qymc").val(),
					"commoTrouble.jcsj": $("#jcsj").val(),
					"commoTrouble.jcry": $("#jcry").val(),
					"commoTrouble.shzt": $("#shzt").val(),
					"commoTrouble.zfws": $("#zfws").val(),
					"jcBeginDate" : $("#jcBeginDate").val(),
					"jcEndDate" : $("#jcEndDate").val(),
					"wcBeginDate" : $("#wcBeginDate").val(),
					"wcEndDate" : $("#wcEndDate").val(),
				"commoTrouble.szc": $("#szc").val(),
				"commoTrouble.deptId": $("#deptId").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',width:30,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if(rec.createUserID != createUserID){//这里判断已审核通过 或者不是科员角色登录
								opt = ''; 
						    } 
						    return opt ; 

				    }}
				]],
				columns:[[
				          {field:'szz',title:'所在镇',width:fixWidth(0.1),formatter:function(value,rec){
						  var temp = '';
						    $.ajax({
						    url: '${ctx}/jsp/admin/code/findCodeValue.action',
						    type: 'post',
						    dataType: 'json',
						    async : false,
						    data:{        "codeValue.itemValue" : rec.szz,
						        "codeValue.codeId" : "4028e56c4014f290014014f981510003"    },
						    error: function(){
						        $.messager.alert('提示','获取一维代码错误！');
						    },
						    success: function(data){
						        temp = data.itemText;
						    }});
						    return temp;}
						},
							{field:'qymc',title:'检查企业名称',width:fixWidth(0.2)},
							{field:'jcry',title:'检查人员',width:fixWidth(0.15)},
							{field:'jcsj',title:'检查时间',width:fixWidth(0.1)},
							{field:'zfws',title:'执法文书',width:fixWidth(0.1),formatter:function(value,rec){
								var temp = '';
						   	 	$.ajax({
						    		url: '${ctx}/jsp/admin/code/findCodeValue.action',
						    		type: 'post',
						    		dataType: 'json',
						    		async : false,
						    		data:{        "codeValue.itemValue" : rec.zfws,
						        		"codeValue.codeId" : "4028e5723feb1752013feb1e54030006"    },
						    		error: function(){
						       		 	$.messager.alert('提示','获取一维代码错误！');
						    		},
						    		success: function(data){
						        		temp = data.itemText;
						    		}});
						    	return temp;
							}},
							{field:'isqysb',title:'是否企业上报整改信息',width:fixWidth(0.15),formatter:function(value,rec){
								var temp = "";
								if(rec.isqysb == '0')
								{
									temp = "是";
								}
								else
								{
									temp = "否";
								}
						    	return temp;
							}},
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
					        	 return str;
				          		**/
				          		
				          		value = '';
				          		if((rec.shzt == '0' && createUserID == rec.createUserID) || (rec.isqysb=='1'&&(rec.shzt == '0' || rec.shzt == '3')&& createUserID == rec.createUserID)||(type == '3'&&rec.shzt == '3'&&rec.isqysb == '0')){//科员 查看 状态 ：待整改 
				          			value =  "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;"
				          			+"&nbsp;<span style='color:green;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
				          		}
				          		else if((rec.isqysb=='1' && createUserID == rec.createUserID)||(type == '3'&&rec.shzt == '2'&&rec.isqysb == '0')){//科员 查看 状态 ：审核未通过
				          			value =  "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;"
				          			+"&nbsp;<span style='color:green;cursor:hand' onclick=\"uploadInfo('"+rec.id+"')\">修改</span>";
				          		}
				          		else if(rec.shzt == '3'&&type == '2' && rec.deptId == deptId){//科长 查看 审核操作
				          			value =  "<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
				          		}else{//角色仅查看 或审核通过
				          			value =  "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;";
				          		}
				          		if(rec.shzt == '0' &&type != '2'){//如果状态不是已整改待审核
				          			if((rec.isqysb == '0'&&type=='3')||(rec.isqysb=='1'&&createUserID == rec.createUserID))
				          			value = value
				          			+"&nbsp;<span style='color:blue;cursor:hand' onclick=\"uploadInfo('"+rec.id+"')\">上传整改信息</span>";
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
				<th width="15%">所在区域</th>
				<td width="35%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szz" name="commoTrouble.szz" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					
					<cus:SelectOneTag property="commoTrouble.szz" defaultText='请选择' codeName="相城地址" value="${commoTrouble.szz}" onchange="querySzc(this.value);"/>
					<s:select theme="simple" cssStyle="width:100px;" id="szc" emptyOption= "true" name="commoTrouble.szc" list="%{deptlist}" listKey="deptCode" listValue="deptName" dataType="Require" msg="此项为必选" value="{commoTrouble.szc}"></s:select><font style="color:red">*</font>
					 -->
				</td>
				<th width="15%">检查企业名称</th>
				<td width="35%"><input name="commoTrouble.qymc" id="qymc" value="${commoTrouble.qymc}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">检查时间</th>
				<td width="35%">
					<input type="text" name="jcBeginDate" id="jcBeginDate"
						class="Wdate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'jcEndDate\')}'})"
						value="${jcBeginDate}">
					~
					<input type="text" name="jcEndDate" id="jcEndDate" class="Wdate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'jcBeginDate\')}'})"
						value="${jcEndDate}">
				</td>
				<th width="15%">检查人员</th>
				<td width="35%"><input name="commoTrouble.jcry" id="jcry" value="${commoTrouble.jcry}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">执法文书</th>
				<td width="35%"><cus:SelectOneTag property="commoTrouble.zfws" defaultText='请选择' codeName="执法文书" value="${commoTrouble.zfws}" /></td>
				<th width="15%">审核状态</th>
				<td width="35%">
					<s:select id="shzt" list='#{"":"--请选择---","0":"待整改","3":"已整改待审核","1":"审核通过","2":"审核未通过"}' theme="simple" value="#request.shzt"></s:select>
				</td>
			</tr>
			<tr>
				<th width="15%">检查部门</th>
				<td width="35%"><s:select theme="simple" cssStyle="width:100px;" id="deptId" emptyOption= "true" name="commoTrouble.deptId" list="%{deptList}" listKey="id" listValue="deptName" value="{commoTrouble.deptId}"></s:select></td>
			</tr>
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
				
				<a href="###" class="easyui-linkbutton" onclick="search_commoTrouble()" iconCls="icon-search">查询</a>&nbsp;
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

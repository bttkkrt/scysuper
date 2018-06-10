<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全设施登记台账管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
         function changeType(obj)  
		    {
		    for(var i=1;i<4;i++)
		    {
		    
		    if(i==obj)
		   $("#div"+obj).css("display","block");
	
		    else
		   $("#div"+i).css("display","none");
		    }
		    if(obj!=''){
		    $("#start").css("display","none");
		    }
		    if(obj==''){
		    $("#start").css("display","block");
		    }
		    
		    }
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_safLed","添加安全设施登记台账","${ctx}/jsp/aqssdjtz/safLedInitEdit.action?flag=add&dt="+dt.getTime(),700,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_safLed","修改安全设施登记台账","${ctx}/jsp/aqssdjtz/safLedInitEdit.action?flag=mod&safLed.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_safLed","查看安全设施登记台账","${ctx}/jsp/aqssdjtz/safLedView.action?safLed.id="+row_Id+"&dt="+dt.getTime(),700,400);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_safLed();
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
		                	url : "safLedDel.action",
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
		                        	search_safLed();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_safLed(){
        	var queryParams = {
				"safLed.areaId": $("#areaId").val(),
"safLed.areaName": $("#areaName").val(),
"safLed.companyName": $("#companyName").val(),
"safLed.accountType": $("#accountType").val(),
"safLed.category1": $("#category1").val(),
"safLed.category2": $("#category2").val(),
"safLed.category3": $("#category3").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var toolbar = [];
       	 var frozen=[];
        	if('${roleName}'=='0'){//判断登录角色
				toolbar = [{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				},{
					id:'btncut',
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						del();
					}
				}];
				frozen=[[
				    {field:'id',checkbox:true}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全设施登记台账列表',
				url:'safLedQuery.action',
				queryParams:{
					"safLed.areaId": $("#areaId").val(),
"safLed.areaName": $("#areaName").val(),
"safLed.companyName": $("#companyName").val(),
"safLed.accountType": $("#accountType").val(),
"safLed.category": $("#category").val(),
"safLed.category1": $("#category1").val(),
"safLed.category2": $("#category2").val(),
"safLed.category3": $("#category2").val()
				},
				frozenColumns:frozen,
				columns:[[
				 {field:'areaName',title:'所在区域',width:100},
				          {field:'companyName',title:'企业名称',width:100},
 {field:'accountType',title:'台账类型',width:100,formatter:function(value,rec){
  var temp = '';
            if(rec.accountType==1){
            temp="预防事故设施台帐";
            } else if(rec.accountType==2){
            temp="控制事故设施台帐";
            }else if(rec.accountType==3){
             temp="减少与消除事故影响设施台帐";
            }
    return temp;}
    },
    {field:'category',title:'类别',width:100,formatter:function(value,rec){
var temps = '';
            if(rec.accountType==1&&(rec.category==1)){
            temps="检测、报警设施";
            } else if(rec.accountType==1&&(rec.category==2)){
            temps="设备安全防护设施";
            }else if(rec.accountType==1&&(rec.category==3)){
             temps="防爆设施";
            }else if(rec.accountType==1&&(rec.category==4)){
             temps="作业场所防护设施";
            }else if(rec.accountType==1&&(rec.category==5)){
             temps="安全警示标志";
            }else if(rec.accountType==2&&(rec.category==1)){
             temps="泄压和止逆设施";
            }else if(rec.accountType==2&&(rec.category==2)){
             temps="紧急处理设施";
            }else if(rec.accountType==3&&(rec.category==1)){
             temps="防止火灾蔓延设施";
            }
            else if(rec.accountType==3&&(rec.category==2)){
             temps="灭火设施";
            }
            else if(rec.accountType==3&&(rec.category==3)){
             temps="紧急个体处置设施";
            }
            else if(rec.accountType==3&&(rec.category==4)){
             temps="应急救援设施";
            }
            else if(rec.accountType==3&&(rec.category==5)){
             temps="逃生避难设施";
            }
            else if(rec.accountType==3&&(rec.category==6)){
             temps="劳动防护用品和装备";
            }
    return temps;}
    },
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='0'){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}

}}				        ]],
				toolbar:toolbar
				})); 
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaName');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
		});
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
			<s:if test='roleName!="0"'>
			<tr>
					

				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag style="width:50%;" property="safLed.areaId" defaultText='请选择' codeName="企业属地" value="${safLed.areaId}"  maxlength="127"/></td>
			
				<th width="15%">企业名称</th>
				<td width="35%"><input name="safLed.companyName" style="width:50%;" id="companyName" value="${safLed.companyName}" type="text" maxlength="127"></td>
			</tr>
			</s:if>
			<tr>
				<th width="15%">台账类型</th>
				<td id ="type" width="35%"><cus:SelectOneTag style="width:50%;" property="safLed.accountType" defaultText='请选择' codeName="安全设施登记台账类别" value="${safLed.accountType}" onchange="changeType(this.value)"/></td>
				<th width="15%">类别</th>
				<td width="35%">
					<div id="start"><s:select name="" list="#{'请选择':'请选择'}"  theme="simple" cssStyle="width:50%"/></div>
					<div id="div1" style="display:none"><cus:SelectOneTag style="width:50%;" property="safLed.category1"   defaultText='请选择' codeName="预防事故设施台帐" value="${safLed.category}"  /></div>
					<div id="div2" style="display:none"><cus:SelectOneTag style="width:50%;" property="safLed.category2"   defaultText='请选择' codeName="控制事故设施台帐" value="${safLed.category}"  /></div>
					<div id="div3" style="display:none"><cus:SelectOneTag style="width:50%;" property="safLed.category3"  defaultText='请选择' codeName="减少与消除事故影响设施台帐" value="${safLed.category}"  /></div>
				</td>
			</tr>
		    </tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_safLed()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;				
					</td>
				</tr>
			</table>
		</div>
		
		<div id="pagination" >
		</div>
		</div>
		</div>
	</div>
</body>
</html>

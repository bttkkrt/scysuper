<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>模块管理</title>
   <%@include file="/common/jsLib.jsp"%>
	<script>
		function addTab(id,text,url){
				$.ajax({
					"url": window.checkSessionUrl,
					dataType: "json",
					data: {
						"currDate": (new Date()).getTime()
					},
					"success": function(data){
						if(data.result){
							$(".opened").removeClass("opened");
							//$("#drawer").hide();
							//$(".nav li:first").unbind("mouseenter").unbind("mouseleave");
							
							if(url=="")return;
							var width = $(".pageFrame").width() -4;
							var height = $(".pageFrame").height()-34;
							var existname="";
							var existid=""
							$("div[class='panel'] iframe").each(function (i,dom){
						    	if(text==$(dom).attr("existname")){
						    		existname=$(dom).attr("existname");
							    	existid=$(dom).attr("id");
						    	}
							});
							url="${ctx}/"+url;
							if(url.indexOf("?")==-1)
								url += "?";
							else
							{	
								if(url.indexOf("companyName")==-1)
								{
									url += "&";
								}
								else
								{
									var companyName = document.getElementById('companyName').value;
									url += companyName + "&";
								}
							}
							var randomnumber=Math.floor(Math.random()*100000);
							if(existname==""){
								$('#tabdiv').tabs('add',{
									id:'tab_'+id,  
									title:text,
									width:width,
									height:height,
									closable:true,
									tools:[{   
								        iconCls:'icon-mini-refresh',   
								        handler:function(){ 
								        	var tab = $('#tabdiv').tabs('getSelected');  // get selected panel
											tab.panel('refresh');
								        }   
								    }],
									content:'<iframe name="iframe_'+id+'" existname="'+text+'" id="iframe_'+id+'" width="100%" height="100%"  name="iframe_'+id+'" frameborder="0"  src="'+url+"random="+randomnumber+'&currModuleCode=' + id + '"></iframe>'  
								});
						 	  	}else{
						 	  		$('#tabdiv').tabs('select',existname);
						     			//重载要访问的地址
						      		document.getElementById(existid).src = url + "random=" + randomnumber;
						  		}
						}else{
							$.messager.alert("提示","登录超时，请重新登录","确定", function(){
								$("#ajaxLogin").window("open");
							});
						}
					
					}
				});    
			}
			
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				height:220,
				title:"<a href='javaScript:aTab()'>安全生产管理机构</a>",
				url:'${ctx}/jsp/aqscgljg/proManOrgQuery.action',
				queryParams:{
"proManOrg.companyName": $("#companyName").val()
				},
				columns:[[
{field:'orgenizationName',title:'机构名称',width:fixWidth(0.15)},
{field:'orgenizationResponsibility',title:'机构职责',width:fixWidth(0.2)},
{field:'orgenizationMenberCount',title:'成员数量',width:fixWidth(0.15)},
{field:'orgenizationCharge',title:'负责人',width:fixWidth(0.1)}
				        ]]
				})); 
				
				$('#pagination1').datagrid($.extend(window.dg_cm_pp,{
				height:220,
				collapsed:true,
				title:"<a href='#' style='color:red' >安全生产分管负责人</a>",
				url:'${ctx}/jsp/aqscfgfzr/secProChaQuery.action',
				queryParams:{
"proManOrg.companyName": $("#companyName").val()
				},
				columns:[[
{field:'chargerName',title:'姓名',width:fixWidth(0.1)},
{field:'chargerSpecializedNum',title:'安全生产管理员资格证号',width:fixWidth(0.2)},
{field:'chargerPhone',title:'联系方式',width:fixWidth(0.1)}
				        ]]
				})); 
				
				$('#pagination2').datagrid($.extend(window.dg_cm_pp,{
				height:220,
				collapsed:true,
				title:"<a href='#' style='color:red' >企业主要负责人</a>",
				url:'${ctx}/jsp/aqscfgfzr/secProChaQuery.action',
				queryParams:{
"proManOrg.companyName": $("#companyName").val()
				},
				columns:[[
{field:'chargerName',title:'姓名',width:fixWidth(0.1)},
{field:'chargerSpecializedNum',title:'安全生产管理员资格证号',width:fixWidth(0.1)},
{field:'chargerPhone',title:'联系方式',width:fixWidth(0.1)}
				        ]]
				})); 
				
				$('#pagination3').datagrid($.extend(window.dg_cm_pp,{
				height:220,
				collapsed:true,
				title:"<a href='#' style='color:red' >安全生产领导机构</a>",
				url:'${ctx}/jsp/aqscfgfzr/secProChaQuery.action',
				queryParams:{
"proManOrg.companyName": $("#companyName").val()
				},
				columns:[[
{field:'chargerName',title:'姓名',width:fixWidth(0.1)},
{field:'chargerSpecializedNum',title:'安全生产管理员资格证号',width:fixWidth(0.1)},
{field:'chargerPhone',title:'联系方式',width:fixWidth(0.1)}
				        ]]
				})); 
				
				$('#pagination4').datagrid($.extend(window.dg_cm_pp,{
				height:220,
				collapsed:true,
				title:"<a href='#' style='color:red' >安全生产管理人员</a>",
				url:'${ctx}/jsp/aqscfgfzr/secProChaQuery.action',
				queryParams:{
"proManOrg.companyName": $("#companyName").val()
				},
				columns:[[
{field:'chargerName',title:'姓名',width:fixWidth(0.1)},
{field:'chargerSpecializedNum',title:'安全生产管理员资格证号',width:fixWidth(0.1)},
{field:'chargerPhone',title:'联系方式',width:fixWidth(0.1)}
				        ]]
				})); 
				
				$('#pagination5').datagrid($.extend(window.dg_cm_pp,{
				height:220,
				collapsed:true,
				title:"<a href='#' style='color:red' >安全生产总监</a>",
				url:'${ctx}/jsp/aqscfgfzr/secProChaQuery.action',
				queryParams:{
"proManOrg.companyName": $("#companyName").val()
				},
				columns:[[
{field:'chargerName',title:'姓名',width:fixWidth(0.1)},
{field:'chargerSpecializedNum',title:'安全生产管理员资格证号',width:fixWidth(0.1)},
{field:'chargerPhone',title:'联系方式',width:fixWidth(0.1)}
				        ]]
				})); 
				
				$('#pagination6').datagrid($.extend(window.dg_cm_pp,{
				height:220,
				collapsed:true,
				title:"<a href='#' style='color:red' >安全生产管理经理</a>",
				url:'${ctx}/jsp/aqscfgfzr/secProChaQuery.action',
				queryParams:{
"proManOrg.companyName": $("#companyName").val()
				},
				columns:[[
{field:'chargerName',title:'姓名',width:fixWidth(0.1)},
{field:'chargerSpecializedNum',title:'安全生产管理员资格证号',width:fixWidth(0.1)},
{field:'chargerPhone',title:'联系方式',width:fixWidth(0.1)}
				        ]]
				})); 
				
				$('#pagination7').datagrid($.extend(window.dg_cm_pp,{
				height:220,
				collapsed:true,
				title:"<a href='#' style='color:red' >特种作业人员</a>",
				url:'${ctx}/jsp/aqscfgfzr/secProChaQuery.action',
				queryParams:{
"proManOrg.companyName": $("#companyName").val()
				},
				columns:[[
{field:'chargerName',title:'姓名',width:fixWidth(0.1)},
{field:'chargerSpecializedNum',title:'安全生产管理员资格证号',width:fixWidth(0.1)},
{field:'chargerPhone',title:'联系方式',width:fixWidth(0.1)}
				        ]]
				})); 
				
				$('#pagination8').datagrid($.extend(window.dg_cm_pp,{
				height:220,
				collapsed:true,
				title:"<a href='#' style='color:red' >安全生产分管负责人</a>",
				url:'${ctx}/jsp/aqscfgfzr/secProChaQuery.action',
				queryParams:{
"proManOrg.companyName": $("#companyName").val()
				},
				columns:[[
{field:'chargerName',title:'姓名',width:fixWidth(0.1)},
{field:'chargerSpecializedNum',title:'安全生产管理员资格证号',width:fixWidth(0.1)},
{field:'chargerPhone',title:'联系方式',width:fixWidth(0.1)}
				        ]]
				})); 
				
				$('#pagination9').datagrid($.extend(window.dg_cm_pp,{
				pageList:[5,10,15],
				pageSize:5,
				height:220,
				collapsed:true,
				title:"<a href='#' style='color:red' >安全生产分管负责人</a>",
				url:'${ctx}/jsp/aqscfgfzr/secProChaQuery.action',
				queryParams:{
"proManOrg.companyName": $("#companyName").val()
				},
				columns:[[
{field:'chargerName',title:'姓名',width:fixWidth(0.1)},
{field:'chargerSpecializedNum',title:'安全生产管理员资格证号',width:fixWidth(0.1)},
{field:'chargerPhone',title:'联系方式',width:fixWidth(0.1)}
				        ]]
				})); 
				
				
		});
    </script>
</head>
<body style="overflow: auto;">
    <div style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
	
	    <table style="width:98%">
	    	<tr>
	    		<td><div id="pagination" >
		</div></td>
	    	</tr>
		<tr>
	    		<td><div id="pagination1" >
		</div></td>
	    	</tr>
		<tr>
	    		<td><div id="pagination2" >
		</div></td>
	    	</tr>
		<tr>
	    		<td><div id="pagination3" >
		</div></td>
	    	</tr>
		<tr>
	    		<td><div id="pagination4" >
		</div></td>
	    	</tr>
		<tr>
	    		<td><div id="pagination5" >
		</div></td>
	    	</tr>
		<tr>
	    		<td><div id="pagination6" >
		</div></td>
	    	</tr>
		<tr>
	    		<td><div id="pagination7" >
		</div></td>
	    	</tr>
		<tr>
	    		<td><div id="pagination8" >
		</div></td>
	    	</tr>
		<tr>
	    		<td><div id="pagination9" >
		</div></td>
	    	</tr>
		</table>
	</div>
</body>
</html>
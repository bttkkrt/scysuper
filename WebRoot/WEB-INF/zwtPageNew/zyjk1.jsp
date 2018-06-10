<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>职业健康1</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8" />
    <link href="${ctx}/webResources/zwt/css/chosen.min.css" rel="stylesheet">
    <link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/windowOnMove.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<!-- easyui css -->
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/easyui/themes/icon.css">

<script src="${ctx}/webResources/zwt/layer/layer.js"></script>

<style>
html{background-color:#FDFDFD; font-size:14px; color:#000; font-family:'微软雅黑'}
a,a:hover{ text-decoration:none;}
pre{font-family:'微软雅黑'}
.box{padding:20px; background-color:#fff; margin:50px 100px; border-radius:5px;}
.box a{padding-right:15px;}
#about_hide{display:none}
.layer_text{background-color:#fff; padding:20px;}
.layer_text p{margin-bottom: 10px; text-indent: 2em; line-height: 23px;}
.button{display:inline-block; *display:inline; *zoom:1; line-height:30px; padding:0 20px; background-color:#56B4DC; color:#fff; font-size:14px; border-radius:3px; cursor:pointer; font-weight:normal;}
.photos-demo img{width:200px;}
</style>
    <!--[if lt IE 8]>
    <![endif]-->
    <script type="text/javascript">
    	var ps=50;
    //i=1:管理制度,2：防治经费,3:基本信息,4:作业点布局,5:基本因素
    	 $(function(){
    	 	//$("#div1").load("${ctx}/zwt/occHeaManQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('1',1,ps,"${ctx}/zwt/occHeaManQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('2',1,ps,"${ctx}/zwt/occDisPreQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('3',1,ps,"${ctx}/zwt/occHeaInfoQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('4',1,ps,"${ctx}/zwt/equAndFacQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('5',1,ps,"${ctx}/zwt/occHazBasQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	
		});
		 function getSpanByName(name)
        {
        	return "<span title='" + name + "' style='cursor:pointer'>" + name + "</span>";
        }
		function getList(i,num,size,url){ 
			ajaxData.pageNo=num;
			ajaxData.pageSize=size;
			ajaxData.searchLike="${searchLike}";
			ajaxData.ids="${ids}";
			$("#currPage"+i).val(num);
			$.ajax({
		                	url : url,
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:ajaxData,
		                    error: function(){
		                    },
		                    success: function(data){
		                    	var trs=getTrs(data,i);
		                    	document.getElementById('tbody'+i).innerHTML=trs;
		                    	document.getElementById('pageNo'+i).innerHTML=data.pageNo;
		                    	document.getElementById('totalPage'+i).innerHTML=data.totalPage;
		                        document.getElementById('count'+i).innerHTML=data.count;
		                    }
		                });
		}
		
		
		function getTrs(data,i){
			var t='';
			if(i=='1'){
				var index=(data.pageNo-1)*ps+1;
			    $.each(data.result,function(idx,item){ 
					//获取操作按钮
			         var tr="";
			         if(idx%2==0){
			             tr="<tr class='row_1'><td>"+index+++"</td>";
			         }else{
			             tr="<tr><td>"+index+++"</td>";
			         }
			         tr+="<td><a onclick=view1('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"
		               +getSpanByName(item.systemName)+"</a></td></tr>"
			          t+=tr;
				});
				
			}else if(i=='2'){
				var index=(data.pageNo-1)*ps+1;
			    $.each(data.result,function(idx,item){ 
		            //获取操作按钮
		            var tr="";
		            if(idx%2==0){
		            	tr="<tr class='row_1'><td>"+index+++"</td>";
		            }else{
		                tr="<tr><td>"+index+++"</td>";
		            }
		            tr+="<td><a onclick=view2('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.jshxYear)+"</a></td>"
		            tr+="<td>"+getSpanByName(item.jshxUse)+"</td>"
		            tr+="<td>"+getSpanByName(item.attachment)+"</td></tr>"
		            t+=tr;			          
			          
				});
				
			}else if(i=='3'){
				var index=(data.pageNo-1)*ps+1;
			    $.each(data.result,function(idx,item){ 

		            //获取操作按钮
		            var tr="";
		            if(idx%2==0){
		            	tr="<tr class='row_1'><td>"+index+++"</td>";
		            }else{
		                tr="<tr><td>"+index+++"</td>";
		            }
		           tr+="<td><a onclick=view3('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"  +getSpanByName(item.hazardIndustryCategory)+"</a></td>"
		           tr+="<td>"  +getSpanByName(item.femaleWorkersDiseasesNumber)+"</td></tr>"
		            t+=tr;			          
			          
				});
				
			}else if(i=='4'){
				var index=(data.pageNo-1)*ps+1;
			    $.each(data.result,function(idx,item){ 

		            //获取操作按钮
		            var tr="";
		            if(idx%2==0){
		            	tr="<tr class='row_1'><td>"+index+++"</td>";
		            }else{
		                tr="<tr><td>"+index+++"</td>";
		            }
		            tr+="<td><a onclick=view4('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.equipmentName)+"</a></td>"
		            tr+="<td>"+getSpanByName(item.equipmentNumber)+"</td></tr>"
		            t+=tr;			          
			          
				});
				
			}else if(i=='5'){
				var index=(data.pageNo-1)*ps+1;
			    $.each(data.result,function(idx,item){ 
		            //获取操作按钮
		            var tr="";
		            if(idx%2==0){
		            	tr="<tr class='row_1'><td>"+index+++"</td>";
		            }else{
		                tr="<tr><td>"+index+++"</td>";
		            }
		            tr+="<td><a onclick=view5('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.ifDust)+"</a></td>";
		            tr+="<td>"+getSpanByName(item.ifChemical)+"</td>";
		            tr+="<td>"+getSpanByName(item.ifPhysical)+"</td>";
		            tr+="<td>"+getSpanByName(item.ifRadioactivity)+"</td>";
		            tr+="<td>"+getSpanByName(item.ifOther)+"</td></tr>";
		            t+=tr;			          
			          
				});
				
			}else {}
			
			return t;
		}
		
		
		 function view1(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看管理制度","${ctx}/zwt/occHeaManView.action?occHeaMan.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
		
		function view2(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看防治经费","${ctx}/zwt/occDisPreView.action?occDisPre.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        
		function view3(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看职业健康基本信息","${ctx}/zwt/occHeaInfoView.action?occHeaInfo.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
		
		function view4(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看作业点布局","${ctx}/zwt/equAndFacView.action?equAndFac.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        
         function view5(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看基本因素","${ctx}/zwt/occHazBasView.action?occHazBas.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
		
		function first(i,url){
			getList(i,1,ps,url);
		}
		function previous(i,url){
			var curPage= parseInt(document.getElementById('pageNo'+i).innerHTML);
			if(curPage==1){
				return false;
			}else{
				getList(i,curPage-1,ps,url);			
			}
		}
		
		function next(i,url){
			var curPage= parseInt(document.getElementById('pageNo'+i).innerHTML);
			var totalPage= parseInt(document.getElementById('totalPage'+i).innerHTML);
			if(curPage==totalPage){
				return false;
			}else{
				getList(i,curPage+1,ps,url);	
			}
		}
		
		function last(i,url){
			var totalPage= parseInt(document.getElementById('totalPage'+i).innerHTML);
			getList(i,totalPage,ps,url);	
		}
		
		function createSimpleWindow(a,b,c,d,e){
        	var dt=new Date();
        	var index = layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: b,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: c
		    });
		    layer.full(index);
        }
    </script>
</head>
<body>
<!-- Wrap all page content here -->
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>管理制度</span></div>
                        </div>
                      <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:90%"/>
          				</colgroup>
	                    	<thead>
		                        <tr>
		                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
		                        	--><th><span>序号</span></th>
		                        	<th><span>制度名称</span></th>
	                        	</tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:90%"/>
         				</colgroup>
                    	<tbody id="tbody1">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo1">1</span>页 / 共<span id="totalPage1"></span>页，共<span id="count1"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('1','${ctx}/zwt/occHeaManQuery.action')">首页</a><a href="###"  onclick="previous('1','${ctx}/zwt/occHeaManQuery.action')"  >上一页</a><a href="###" onclick="next('1','${ctx}/zwt/occHeaManQuery.action')" >下一页</a><a href="###" onclick="last('1','${ctx}/zwt/occHeaManQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->
                 <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>防治经费</span></div>
                        </div>
                      <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:20%"/>
              				<col style="width:50%"/>
              				<col style="width:20%"/>
          				</colgroup>
	                    	<thead>
		                        <tr>
		                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
		                        	--><th><span>序号</span></th>
		                        	<th><span>年度</span></th>
		                        	<th><span>用途</span></th>
		                        	<th><span>经费</span></th>
		                        	
		                        </tr>
		  					</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:20%"/>
              				<col style="width:50%"/>
              				<col style="width:20%"/>
         				</colgroup>
                    	<tbody id="tbody2">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo2">1</span>页 / 共<span id="totalPage2"></span>页，共<span id="count2"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('2','${ctx}/zwt/occDisPreQuery.action')">首页</a><a href="###"  onclick="previous('2','${ctx}/zwt/occDisPreQuery.action')"  >上一页</a><a href="###" onclick="next('2','${ctx}/zwt/occDisPreQuery.action')" >下一页</a><a href="###" onclick="last('2','${ctx}/zwt/occDisPreQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>职业健康基本信息</span></div>
                        </div>
                      <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:45%"/>
              				<col style="width:45%"/>
          				</colgroup>
	                    	<thead>
		                       <tr>
		                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
		                        	--><th><span>序号</span></th>
		                        	<th><span>职业病危害行业类别</span></th>
		                        	<th><span>接触职业病危害因素女工人数</span></th>
		                        	
		                        </tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:45%"/>
              				<col style="width:45%"/>
         				</colgroup>
                    	<tbody id="tbody3">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo3">1</span>页 / 共<span id="totalPage3"></span>页，共<span id="count3"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('3','${ctx}/zwt/occHeaInfoQuery.action')">首页</a><a href="###"  onclick="previous('3','${ctx}/zwt/occHeaInfoQuery.action')"  >上一页</a><a href="###" onclick="next('3','${ctx}/zwt/occHeaInfoQuery.action')" >下一页</a><a href="###" onclick="last('3','${ctx}/zwt/occHeaInfoQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->   
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>作业点布局</span></div>
                        </div>
                      <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:45%"/>
              				<col style="width:45%"/>
          				</colgroup>
	                    	<thead>
		                        <tr>
	                        		<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
		                        	--><th><span>序号</span></th>
		                        	<th><span>设备名称</span></th>
		                        	<th><span>设备编号</span></th>
		                        	
	                        	</tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:45%"/>
              				<col style="width:45%"/>
         				</colgroup>
                    	<tbody id="tbody4">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo4">1</span>页 / 共<span id="totalPage4"></span>页，共<span id="count4"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('4','${ctx}/zwt/equAndFacQuery.action')">首页</a><a href="###"  onclick="previous('4','${ctx}/zwt/equAndFacQuery.action')"  >上一页</a><a href="###" onclick="next('4','${ctx}/zwt/equAndFacQuery.action')" >下一页</a><a href="###" onclick="last('4','${ctx}/zwt/equAndFacQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end--> 
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>基本因素</span></div>
                        </div>
                       <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:15%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
              				<col style="width:15%"/>
          				</colgroup>
	                    	<thead>
		                        <tr>
		                        	<th><span>序号</span></th>
		                        	<th><span>粉尘类</span></th>
		                        	<th><span>化学物质类</span></th>
		                        	<th><span>物理因素类</span></th>
		                        	<th><span>放射性物质类</span></th>
		                        	<th><span>其他</span></th>
	                        	</tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:15%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
              				<col style="width:15%"/>
         				</colgroup>
                    	<tbody id="tbody5">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo5">1</span>页 / 共<span id="totalPage5"></span>页，共<span id="count5"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('5','${ctx}/zwt/occDisQuery.action')">首页</a><a href="###"  onclick="previous('5','${ctx}/zwt/occDisQuery.action')"  >上一页</a><a href="###" onclick="next('5','${ctx}/zwt/occDisQuery.action')" >下一页</a><a href="###" onclick="last('5','${ctx}/zwt/occDisQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->                


</body>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/main.js"></script>
</html>

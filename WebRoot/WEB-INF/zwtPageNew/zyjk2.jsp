<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>职业健康2</title>
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
    //i=1:危害因素检测  ,2检测超标管理,3:职业健康体检,4:体检不合格记录,5:调岗记录
    	 $(function(){
    	 	//$("#div1").load("${ctx}/zwt/hazDetQuery.action?entBaseInfo.id=${entBaseInfo.id}&searchLike=${entBaseInfo.enterpriseName}");
    	 	getList('1',1,ps,"${ctx}/zwt/hazDetQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('2',1,ps,"${ctx}/zwt/detOverStaManQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('3',1,ps,"${ctx}/zwt/occHeaExaQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('4',1,ps,"${ctx}/zwt/phyUnqRecQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	getList('5',1,ps,"${ctx}/zwt/proRecQuery.action?entBaseInfo.id=${entBaseInfo.id}");
    	 	
		});
		 function getSpanByName(name)
        {
        	return "<span title='" + name + "' style='cursor:pointer'>" + name + "</span>";
        }
		function getList(i,num,size,url){ 
			ajaxData.pageNo=num;
			ajaxData.pageSize=size;
			ajaxData.searchLike="${searchLike}";
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
			         tr+="<td><a onclick=view1('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.detectionRiskFactors)+"</a></td>";
		                    		tr+="<td>"+getSpanByName(item.monitoringPoints)+"</td>";
		                    		tr+="<td>"+getSpanByName(item.testDate.substring(0,10))+"</td></tr>";
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
		            tr+="<td><a onclick=view2('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.detectionMechanism)+"</a></td>";
		                    		tr+="<td>"+getSpanByName(item.detectionCategory)+"</td></tr>";
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
		            tr+="<td><a onclick=view3('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.medicalInstitutionName)+"</a></td>";
		                    		tr+="<td>"+getSpanByName(item.createTime.substring(0,10))+"</td></tr>";
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
		            tr+="<td><a onclick=view4('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.jshxName)+"</a></td>";
		                    		tr+="<td>"+getSpanByName(item.identification)+"</td>";
		                    		tr+="<td>"+getSpanByName(item.medicalExaminationDate.substring(0,10))+"</td>";
		                    		tr+="<td>"+getSpanByName(item.physicalExaminatioResults)+"</td></tr>";
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
		            tr+="<td><a onclick=view5('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.jshxName)+"</a></td>";
		                    		tr+="<td>"+getSpanByName(item.identification)+"</td>";
		                    		tr+="<td>"+getSpanByName(item.jshxTime.substring(0,10))+"</td></tr>";
		            t+=tr;			          
			          
				});
				
			}else {}
			
			return t;
		}
		
		
		 function view1(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看危害因素检测","${ctx}/zwt/hazDetView.action?hazDet.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
		
		function view2(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看检测超标管理","${ctx}/zwt/detOverStaManView.action?detOverStaMan.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        
		function view3(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看职业健康体检","${ctx}/zwt/occHeaExaView.action?occHeaExa.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
		
		function view4(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看体检不合格记录","${ctx}/zwt/phyUnqRecView.action?phyUnqRec.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
        }
        
         function view5(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_opePro","查看调岗记录","${ctx}/zwt/proRecView.action?proRec.id="+row_Id+"&dt="+dt.getTime(),700,500);
        	
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
                            <div class="sipac_table_menu"><span>危害因素检测</span></div>
                        </div>
                       <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:50%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
          				</colgroup>
	                    	<thead>
	                        <tr>
	                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
	                        	--><th><span>序号</span></th>
	                        	<th><span>检测危害因素</span></th>
	                        	<th><span>检测点数</span></th>
	                        	<th><span>检测日期</span></th>
	                        	
	                        </tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:50%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
         				</colgroup>
                    	<tbody id="tbody1">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo1">1</span>页 / 共<span id="totalPage1"></span>页，共<span id="count1"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('1','${ctx}/zwt/hazDetQuery.action')">首页</a><a href="###"  onclick="previous('1','${ctx}/zwt/hazDetQuery.action')"  >上一页</a><a href="###" onclick="next('1','${ctx}/zwt/hazDetQuery.action')" >下一页</a><a href="###" onclick="last('1','${ctx}/zwt/hazDetQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>检测超标管理</span></div>
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
		                        	<th><span>检测机构</span></th>
		                        	<th><span>检测类别</span></th>
		                        	
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
                    	<tbody id="tbody2">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo2">1</span>页 / 共<span id="totalPage2"></span>页，共<span id="count2"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('2','${ctx}/zwt/detOverStaManQuery.action')">首页</a><a href="###"  onclick="previous('2','${ctx}/zwt/detOverStaManQuery.action')"  >上一页</a><a href="###" onclick="next('2','${ctx}/zwt/detOverStaManQuery.action')" >下一页</a><a href="###" onclick="last('2','${ctx}/zwt/detOverStaManQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>职业健康体检</span></div>
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
		                        	<th><span>体检机构</span></th>
		                        	<th><span>录入时间</span></th>
		                        	
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
	                        <div class="s_p_btn"><a href="###" onclick="first('3','${ctx}/zwt/occHeaExaQuery.action')">首页</a><a href="###"  onclick="previous('3','${ctx}/zwt/occHeaExaQuery.action')"  >上一页</a><a href="###" onclick="next('3','${ctx}/zwt/occHeaExaQuery.action')" >下一页</a><a href="###" onclick="last('3','${ctx}/zwt/occHeaExaQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->   
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>体检不合格记录</span></div>
                        </div>
                     <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:30%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
          				</colgroup>
	                    	<thead>
			                    <tr>
		                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
		                        	--><th><span>序号</span></th>
		                        	<th><span>姓名</span></th>
		                        	<th><span>身份证</span></th>
		                        	<th><span>体检日期</span></th>
		                        	<th><span>体检结果</span></th>
		                        	
		                        </tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:30%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
         				</colgroup>
                    	<tbody id="tbody4">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo4">1</span>页 / 共<span id="totalPage4"></span>页，共<span id="count4"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('4','${ctx}/zwt/phyUnqRecQuery.action')">首页</a><a href="###"  onclick="previous('4','${ctx}/zwt/phyUnqRecQuery.action')"  >上一页</a><a href="###" onclick="next('4','${ctx}/zwt/phyUnqRecQuery.action')" >下一页</a><a href="###" onclick="last('4','${ctx}/zwt/phyUnqRecQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end--> 
                    <div style="height:10px"/>   
                    <!--bgtable-->
                    <div class="sipac_tableBox" >
                       <div class="sipac_tableBar">
                            <div class="sipac_table_menu"><span>调岗记录</span></div>
                        </div>
                       <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:10%"/>
              				<col style="width:30%"/>
              				<col style="width:30%"/>
              				<col style="width:30%"/>
          				</colgroup>
	                    	<thead>
		                        <tr>
		                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
		                        	--><th><span>序号</span></th>
		                        	<th><span>姓名</span></th>
		                        	<th><span>身份证</span></th>
		                        	<th><span>调岗时间</span></th>
		                        </tr>
	  						</thead>
	                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
         					<col style="width:10%"/>
              				<col style="width:30%"/>
              				<col style="width:30%"/>
              				<col style="width:30%"/>
         				</colgroup>
                    	<tbody id="tbody5">
  						</tbody>
                    </table>
                </div>
	                	<div class="sipac_pagebox">
	                        <div class="s_p_info">第<span id="pageNo5">1</span>页 / 共<span id="totalPage5"></span>页，共<span id="count5"></span>条数据</div> 
	                        <div class="s_p_btn"><a href="###" onclick="first('5','${ctx}/zwt/proRecQuery.action')">首页</a><a href="###"  onclick="previous('5','${ctx}/zwt/proRecQuery.action')"  >上一页</a><a href="###" onclick="next('5','${ctx}/zwt/proRecQuery.action')" >下一页</a><a href="###" onclick="last('5','${ctx}/zwt/proRecQuery.action')" >尾页</a></div>
	                	</div>
                    </div>
                    <!--bgtable end-->                


</body>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/main.js"></script>
</html>

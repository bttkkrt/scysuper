<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>安全生产隐患</title>
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
    var no=1;
    var ps=50;
    var ajaxData={ };
    	 $(function(){
    	 	//初始化样式
    	 		ajaxData={ 
		              pageNo :$("#currPage").val(),
		              pageSize:ps,
		              "troman.troubleSource":"${troman.troubleSource}",
		              "troMan.areaId":"${troMan.areaId}",
		              "troMan.taskId":"${troMan.taskId}",
		              starttime:"${starttime}",
		              endtime:"${endtime}",
		              flag:"${flag}"
		      };
			
			getList(1,ps);
			
		});
		
		function first(){
			getList(1,ps);
		}
		function previous(){
			var curPage= parseInt(document.getElementById('pageNo').innerHTML);
			if(curPage==1){
				return false;
			}else{
				getList(curPage-1,ps);			
			}
		}
		
		function next(){
			var curPage= parseInt(document.getElementById('pageNo').innerHTML);
			var totalPage= parseInt(document.getElementById('totalPage').innerHTML);
			if(curPage==totalPage){
				return false;
			}else{
				getList(curPage+1,ps);	
			}
		}
		
		function last(){
			var totalPage= parseInt(document.getElementById('totalPage').innerHTML);
			getList(totalPage,ps);	
		}
		 function getSpanByName(name)
        {
        	return "<span title='" + name + "' style='cursor:pointer'>" + name + "</span>";
        }
		function getList(num,size){ 
			ajaxData.pageNo=num;
			ajaxData.pageSize=size;
			$("#currPage").val(num);
			$.ajax({
		                	url : "aqscyhLists.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:ajaxData,
		                    error: function(){
		                    },
		                    success: function(data){
		                    	var trs="";
		                    	var index=(data.pageNo-1)*ps+1;
		                    	$.each(data.result,function(idx,item){ 
									
									var zhuangtai=getState(item.rectificationState,item);
		                    		 
		                    		//获取操作按钮
		                    		var tr="";
		                    		if(idx%2==0){
		                    			tr="<tr class='row_1'><td>"+index+++"</td>";
		                    		}else{
		                    			tr="<tr><td>"+index+++"</td>";
		                    		}
		                    		tr+="<td><a href='javascript:;' onclick=view('"+item.id+"') style='color:#27a9e3'>"+getSpanByName(item.troubleName)+"</a></td><td>"+getSpanByName(item.areaId)+"</td><td>"
		                    		+getSpanByName(item.companyName)+"</td><td>"
		                    		+getSpanByName(item.troubleLevel)+"</td><td>"+getSpanByName(item.troubleSort)+"</td><td>"
		                    		+getSpanByName(zhuangtai)+"</td></tr>";
		                    		trs+=tr;
								});
		                    	
		                    	document.getElementById('tbody').innerHTML=trs;
		                    	document.getElementById('pageNo').innerHTML=data.pageNo;
		                    	document.getElementById('totalPage').innerHTML=data.totalPage;
		                        document.getElementById('count').innerHTML=data.count;
		                    }
		                });
		}
		
        function getState(value,rec){
			if(value=='1'){
				return "审核未通过";
			}else if(value=='4'||value=='6'||value=='11'){
				return "待整改";
			}else if(value=='2'||value=='3'||value=='5'||value=='7'||value=='20'||value=='21'){//20是转接，也算审核；21 待安委会审核
				return "待审核";
			}else{
				if(rec.dealState=='整改未完成'){
					return "整改未完成";
				}else{
					return "整改完成 ";
				}
			}
		}     
		
        
        function view(row_Id){
        	window.open("${ctx}/zwt/troManViewAJ.action?troMan.id="+row_Id);
        	
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
		        content: c,
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
		    layer.full(index);
        }
        
        
    </script>
</head>
<body>
<input type="hidden" id="searchType" value="0"/>
<input type="hidden" id="showType" value="0"/>
<input type="hidden" id="currPage" value="1"/>
<!-- Wrap all page content here -->
	<div class="sipac_wrap" >
<!-- head style -->

<!-- body style -->
        <div class="sipac_body" >
            <div class="sipac_container" id="tableDiv" style="margin: 0 0 0 0px;">
            	<div class="sipac_tableBox">
                	<div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:5%"/>
              				<col style="width:20%"/>
              				<col style="width:10%"/>
              				<col style="width:20%"/>
              				<col style="width:15%"/>
              				<col style="width:15%"/>
              				<col style="width:15%"/>
         				</colgroup>
                    	<thead>
                        <tr>
                        	<th><span>序号</span></th>
                        	<th><span>隐患名称 </span></th>
                        	<th><span>所在区域  </span></th>
                        	<th><span>企业名称  </span></th>
                        	<th><span>隐患级别 </span></th>
                        	<th><span>隐患类别   </span></th>
                        	<th><span>整改状态  </span></th>
                        </tr>
  						</thead>
                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
             				<col style="width:5%"/>
              				<col style="width:20%"/>
              				<col style="width:10%"/>
              				<col style="width:20%"/>
              				<col style="width:15%"/>
              				<col style="width:15%"/>
              				<col style="width:15%"/>
         				</colgroup>
                    	<tbody id="tbody">
  						</tbody>
                    </table>
                </div>
                	<div class="sipac_pagebox">
                        <div class="s_p_info">第<span id="pageNo">1</span>页 / 共<span id="totalPage"></span>页，共<span id="count"></span>条数据</div> 
                        <div class="s_p_btn"><a href="#" onclick="first()">首页</a><a href="#"  onclick="previous()"  >上一页</a><a href="#" onclick="next()" >下一页</a><a href="#" onclick="last()" >尾页</a></div>
                	</div>
            	</div>
            </div>
        </div>
    </div> 
    

</body>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/zwt/js/main.js"></script>
</html>

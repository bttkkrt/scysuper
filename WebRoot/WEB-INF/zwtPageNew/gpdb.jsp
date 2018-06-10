<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>挂牌督办</title>
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
    var no=1;
    var ps=50;
    var ajaxData={ };
    	 $(function(){
    	 	//初始化样式
    	 	if($("#searchType").val()==1){
    	 		ajaxData={ 
		                    	pageNo :$("#currPage").val(),
		                    	pageSize:ps,
		                    	searchLike:"${searchLike}"
		                    	
		                    };
    	 	}else{
    	 		ajaxData={
    				pageNo : $("#currPage").val(),
		            pageSize:ps,
		            searchLike:"${searchLike}"
		         };
    	 	}
    	 	
    		//$("body").addClass("sipac_full");
			//$(this).addClass("hide");
			///$(".frame_Btn .f_more").removeClass("hide");
			
			getList(1,ps);
			
			$('.sipac_searchbar .s_s_text').click(function(){
		        var email = $(this).val();
		        if(email == "搜索企业名称、证书名称或编号"){
		            $(this).val("");
		        }
		    }).blur(function(){
		        var email = $(this).val();
		        if(email == ""){
		            $(this).val("搜索企业名称、证书名称或编号");
		        }
		    });
		});
		
		function search(){
			$("#searchType").val(1);
			ajaxData={ 
		                    	pageNo : no,
		                    	pageSize:ps,
		                    	searchLike:"${searchLike}"
		                    	
		                    };
			getList(1,ps);
		}
		
		function searchByNameOrCode(){
			$("#searchType").val(0);
			ajaxData={ 
		                 pageNo : no,
		                 pageSize:ps,
		                 searchLike:"${searchLike}"
		                 
		              };
		    getList(1,ps);
		}
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
		                	url : "zwtSuperviceQuery.action",
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
									
									//获取操作按钮
		                    		var button=getButton(item); 
		                    		
		                    		var tr="";
		                    		if(idx%2==0){
		                    			tr="<tr class='row_1'><td>"+index+++"</td>";
		                    		}else{
		                    			tr="<tr><td>"+index+++"</td>";
		                    		}
		                    		tr+="<td><a onclick=view('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.dangerName)+"</a></td><td>"+getSpanByName(item.areaId)+"</td><td>"
		                    		+getSpanByName(item.companyName)+"</td><td>"
		                    		+getSpanByName(item.dangerSort)+"</td><td>"+getSpanByName(item.dangerLevel)+"</td><td>"
		                    		+getSpanByName(item.rectificationLevel)+"</td><td>"+getSpanByName(item.rectificationState)+"</td>"
		                    		//+"<td class='s_t_control'><a class='s_t_Btn stb_3' href='javascript:;' onclick=view('"+item.id+"','"+item.enterpriseName+"')>详情</a></tr>";
		                    		+"<td class='s_t_control'>"+button+"</td></tr>";
		                    		trs+=tr;
								});
		                    	
		                    	document.getElementById('tbody').innerHTML=trs;
		                    	document.getElementById('pageNo').innerHTML=data.pageNo;
		                    	document.getElementById('totalPage').innerHTML=data.totalPage;
		                        document.getElementById('count').innerHTML=data.count;
		                    }
		                });
		}
		
        
        function getButton(rec){
        	var button1="<a class='s_t_Btn stb_1' href='javascript:;' onclick=edit('"+rec.id+"')>修改<b></b></a>";
			var button2="<a class='s_t_Btn stb_1' href='javascript:;' onclick=check1('"+rec.id+"')>审核<b></b></a>";
			var button3="<a class='s_t_Btn stb_1' href='javascript:;' onclick=check2('"+rec.id+"')>审批<b></b></a>";
			var button4="";
			var button5="<a class='s_t_Btn stb_1' href='javascript:;' onclick=report('"+rec.id+"')>整改上报<b></b></a>";
			if("${roleCode}"=="A02"&&(rec.nextOperation=="check2"||rec.nextOperation=="check4")){
				return button3;
			}else if("${roleCode}"=="A03"&&(rec.nextOperation=="check1"||rec.nextOperation=="check3")){
				return button2;
			}else if("${roleCode}"=="A04"&&rec.nextOperation=="update1"){
				return button1;
			}else if("${roleCode}"=="A04"&&rec.nextOperation=="update2"){
				return button5;
			}else{
				return button4;
			}
        }
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_supervice","添加挂牌督办","${ctx}/zwt/superviceInitEdit.action?flag=add&dt="+dt.getTime(),900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supervice","修改挂牌督办","${ctx}/zwt/superviceInitEdit.action?flag=mod&supervice.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function check1(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supervice","审核挂牌督办","${ctx}/zwt/superviceCheck.action?flag=check1&supervice.id="+row_Id+"&dt="+dt.getTime(),900,500);
        }
        function check2(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supervice","审批挂牌督办","${ctx}/zwt/superviceCheck.action?flag=check2&supervice.id="+row_Id+"&dt="+dt.getTime(),900,500);
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supervice","查看挂牌督办","${ctx}/zwt/superviceView.action?supervice.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        
        function report(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supervice","上报整改信息","${ctx}/zwt/superviceInitEdit.action?flag=report&supervice.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
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
                <!--<div class="sipac_crumbs"><a class="s_c_back" href="#" title="返回">&lt;</a><div class="s_c_cont">已选条件: 普通应用</div></div>-->
            	<div class="sipac_tableBox">
            		<c:if test="${roleCode=='A04'}">
	                    <div class="sipac_tableBar">
	                        <div class="sipac_table_menu"><a href="javascript:addNew();">新建</a><em></em><!--<a href="#">批量删除</a><em></em><a href="#">通过</a><em></em><a href="#">禁用</a><em></em><a href="#">启用</a> --> 
	                       </div>
	                    </div>
	                   </c:if>
	                   
	                  <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:5%"/>
             				<col style="width:20%"/>
              				<col style="width:10%"/>
              				<col style="width:15%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
         				</colgroup>
                    	<thead>
                        <tr>
                        	<th><span>序号</span></th>
                        	<th><span>隐患名称    </span></th>
                        	<th><span>所在区域 </span></th>
                        	<th><span>企业名称 </span></th>
                        	<th><span>隐患类别  </span></th>
                        	<th><span>隐患级别  </span></th>
                        	<th><span>整改级别   </span></th>
                        	<th><span>整改状态   </span></th>
                        	<th ><span>操作项</span></th>
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
              				<col style="width:15%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
              				<col style="width:10%"/>
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

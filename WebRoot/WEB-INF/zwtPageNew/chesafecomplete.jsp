<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>危险化学品建设项目安全设施竣工验收管理</title>
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
		                	url : "cheSafComQuery.action",
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
		                    			tr="<tr class='row_1'>";
		                    		}else{
		                    			tr="<tr>";
		                    		}
		                    		if('${roleName}'=='1')
		                    		{
		                    			if(item.createUserID == "${sessionScope['LOGIN_USER_ID']}"){
		                    				tr+="<td><input type='checkbox' name='xxx' value='"+item.id+"'></td>";
		                    			}
		                    			else
		                    			{
		                    				tr+="<td>&nbsp;</td>";
		                    			}
		                    		}
		                    		tr+="<td>"+index+++"</td>";
		                    		tr+="<td><a onclick=view('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.evaluationUnit)+"</a></td>"
		                    		+"<td>"+getSpanByName(item.areaName)+"</td>"
		                    		+ "<td>"+getSpanByName(item.companyName)+"</td>"
		                    		+ "<td>"+getSpanByName(item.approvalDate.substring(0,10))+"</td>"
		                    		+ "<td>"+getSpanByName(item.acceptanceDate.substring(0,10))+"</td>";
		                    		if('${roleName}'=='1')
		                    		{
		                    			tr += "<td class='s_t_control'>"+button+"</td>";
		                    		}
		                    		tr += "</tr>";
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
			var button2="";
			if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID&&'${roleName}'=='1'){
				return  button1;
			}else{
				return button2;
			}
        }
        
        
        
      function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_cheSafCom","添加危险化学品建设项目安全设施竣工验收","${ctx}/zwt/cheSafComInitEdit.action?flag=add&dt="+dt.getTime(),900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheSafCom","修改危险化学品建设项目安全设施竣工验收","${ctx}/zwt/cheSafComInitEdit.action?flag=mod&cheSafCom.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheSafCom","查看危险化学品建设项目安全设施竣工验收","${ctx}/zwt/cheSafComView.action?cheSafCom.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
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
			    alert('至少选择一项删除！');
			}else{
			   var a= confirm("确定要删除吗?");
			   if(a==true){
		                $.ajax({
		                	url : "cheSafComDel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ids
		                    },
		                    error: function(){
		                    	alert('删除时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	alert('删除成功！');
		                        	getList($("#currPage").val(),ps);
		                        }else{
		                        	alert('删除时出错！');
		                        }
		                    }
		                });
			    }
			}
        }
        
        
        function selectAll(){
        	var coll = document.getElementsByName('xxx');
        	var collid = document.getElementById("all");
  			if (collid.checked){
    			for(var i = 0; i < coll.length; i++)
       				coll[i].checked = true;
  			}else{
     			for(var i = 0; i < coll.length; i++){
       				coll[i].checked = false;
  				}
  			}
  
        }
        
        function refresh(){
        	getList($("#currPage").val(),ps);
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
	                <c:if test="${roleName=='1'}">
	                    <div class="sipac_tableBar">
	                        <div class="sipac_table_menu"><a href="javascript:addNew();">新建</a><em></em><a href="javascript:del();">批量删除</a><!--<em></em><a href="#">修改</a><em></em><a href="#">通过</a><em></em><a href="#">禁用</a><em></em><a href="#">启用</a> --><em></em><a href="javascript:refresh();">刷新</a>
	                       </div>
	                    </div>
	                    <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
                        	<col style="width:5%"/>
                        	<col style="width:5%"/>
                        	<col style="width:15%"/>
             				<col style="width:10%"/>
              				<col style="width:25%"/>
              				<col style="width:15%"/>
              				<col style="width:10%"/>
              				<col style="width:15%"/>
          				</colgroup>
                    	<thead>
                        <tr>
                        	<th><span><input type='checkbox' id="all" onclick="selectAll()"/></span></th>
                        	<th><span>序号</span></th>
                        	<th><span>评价单位 </span></th>
                        	<th><span>所在区域 </span></th>
                        	<th><span>企业名称 </span></th>
                        	<th><span>审批日期 </span></th>
                        	<th><span>验收日期</span></th>
                        	<th ><span>操作项</span></th>
                        </tr>
  						</thead>
                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
                        	<col style="width:5%"/>
                        	<col style="width:5%"/>
                        	<col style="width:15%"/>
             				<col style="width:10%"/>
              				<col style="width:25%"/>
              				<col style="width:15%"/>
              				<col style="width:10%"/>
              				<col style="width:15%"/>
         				</colgroup>
                    	<tbody id="tbody">
  						</tbody>
                    </table>
                </div>
	                </c:if>
	                <c:if test="${roleName!='1'}">
	                <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
                        	<col style="width:10%"/>
                        	<col style="width:15%"/>
             				<col style="width:15%"/>
              				<col style="width:30%"/>
              				<col style="width:15%"/>
              				<col style="width:15%"/>
          				</colgroup>
                    	<thead>
                        <tr>
                        	<th><span>序号</span></th>
                        	<th><span>评价单位 </span></th>
                        	<th><span>所在区域 </span></th>
                        	<th><span>企业名称 </span></th>
                        	<th><span>审批日期 </span></th>
                        	<th><span>验收日期</span></th>
                        </tr>
  						</thead>
                    	</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
                        	<col style="width:10%"/>
                        	<col style="width:15%"/>
             				<col style="width:15%"/>
              				<col style="width:30%"/>
              				<col style="width:15%"/>
              				<col style="width:15%"/>
         				</colgroup>
                    	<tbody id="tbody">
  						</tbody>
                    </table>
                </div>
	                </c:if>
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

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
		                	url : "aqscyhList.action",
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
		                    		var button=getButton(item); 
		                    		//var button="<a class='s_t_Btn stb_3' href='javascript:;' onclick=view('"+item.id+"') >查看<b></b></a>&nbsp;";
		                    		var tr="";
		                    		if(idx%2==0){
		                    			tr="<tr class='row_1'><td>"+index+++"</td>";
		                    		}else{
		                    			tr="<tr><td>"+index+++"</td>";
		                    		}
		                    		tr+="<td><a onclick=view('"+item.id+"') href='javascript:;' style='color:#27a9e3' >"+getSpanByName(item.troubleName)+"</a></td><td>"
		                    		+getSpanByName(item.troubleSource)+"</td><td>"
		                    		+getSpanByName(item.companyName)+"</td><td>"+getSpanByName(item.userName)+"</td><td>"
		                    		+getSpanByName(zhuangtai)+"</td>"
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
		
        function getButton(rec){
			
			var button1="";
			var button2="<a class='s_t_Btn stb_1' href='javascript:;' onclick=edit('"+rec.id+"')>修改<b></b></a>";
			var button3="<a class='s_t_Btn stb_1' href='javascript:;' onclick=deal('"+rec.id+"')>处理<b></b></a>";
			var button4="<a class='s_t_Btn stb_1' href='javascript:;' onclick=deal('"+rec.id+"')>修改<b></b></a>";
			var button5="<a class='s_t_Btn stb_1' href='javascript:;' onclick=report('"+rec.id+"')>上报整改信息<b></b></a>";//上报
			var button6="<a class='s_t_Btn stb_1' href='javascript:;' onclick=check('"+rec.id+"')>审核<b></b></a>";//审核
			var button7="<a class='s_t_Btn stb_1' href='javascript:;' onclick=editCXXG('"+rec.id+"')>修改<b></b></a>";
			var button8="<a class='s_t_Btn stb_1' href='javascript:;' onclick=report('"+rec.id+"')>重新整改<b></b></a>";//上报
			var button9 = "<a class='s_t_Btn stb_1' href='javascript:;' onclick=createXcjl('"+rec.id+"') >现场检查记录<b></b></a>";
			var button10 = "<a class='s_t_Btn stb_1' href='javascript:;' onclick=createCase('"+rec.id+"') >立案<b></b></a>";
			var button = "";
			//隐患上报隐患人
			if("${ids}"==rec.createUserID&&(rec.rectificationState=="1"||rec.rectificationState=="3")){
				if("审核通过"==rec.auditResult){
					button = button1;
				}else if("审核未通过"==rec.auditResult){
					button = button7;
				}
			}
			else if((rec.rectificationState=="1"||rec.rectificationState=="2"||rec.rectificationState=="3")&&'${userType}'=='7'){
				button = button1;
			}else
			//安委会办公室的人，在状态是2(2的时候必须是上报安委会办公室的)或4的时候，可以进行修改操作
			if(((rec.rectificationState=="2"&&rec.ifReportAwh=="1"))&&'${userType}'=='1'){
				button = button1;//11-07 费谦修改（提交之后不能修改，只有审核不通过可以修改）
			}else
			//安委会办公室的人，在状态是21，可以进行审核操作  （转接审核）
			if(((rec.rectificationState=="21"))&&'${userType}'=='1'){
				button = button6;//2016-1-22 费谦修改（转接审核） 
			}else
			//安委会职能部门的人，在状态是4的时候，可以进行查看和上报整改信息操作
			if(rec.rectificationState=="4"&&'${userType}'=='6'&&rec.dealDeptId=='${deptId}'){
					button = button5;
			}else
			
			//安监中队队长，在状态是2的时候，可以进行查看和审核操作 20转接相同
			if((rec.rectificationState=="2"||rec.rectificationState=="20")&&'${userType}'=='3'){
				button = button6;
			}else
			
			//网格管理员，在状态是6的时候，可以进行查看和上报整改信息操作
			if(rec.rectificationState=="6"&&'${userType}'=='2'){
				//if("整改未通过"==rec.auditResult&&"${ids}"==rec.userId){
				if("整改未通过"==rec.auditResult){
					button = button8;
				}else{
					button = button5;
				}
			}else
			
			//网格管理员，在状态是3的时候，可以进行审核信息操作
			if(rec.rectificationState=="3"&&'${userType}'=='2'){
				button = button6;
						
			}else
			
			//监察大队队长，在状态是5的时候，可以进行查看和审核信息操作
			if(rec.rectificationState=="5"&&'${userType}'=='4'){
				if(rec.troubleLevel=='重大'){
					button = button6;
				}else if(rec.troubleLevel=='特别重大'&&rec.ifCorrected=='0'){
					button = button6;
				}else{
					button = button1;	
				}
			}else
			
			//局领导，在状态是7的时候，可以进行查看和审核信息操作
			if(rec.rectificationState=="7"&&'${userType}'=='5'){
				if(rec.troubleLevel=='特别重大'){
					button = button6;
				}else{
					button = button1;	
				}
				button = button6;
			}
			else{
				button = button1;	
			}
			if("${ids}"==rec.createUserID&& rec.rectificationState==0)
			{
				button = button+button9;
			}
			
			if(rec.dealState=='整改未完成'&&'${roleName}'=='1'&&rec.ifla != '1')
			{
				button = button+button10;
			}
			return button;
        }
        
        
        
          function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_troMan","添加隐患","${ctx}/zwt/troManInitEditAJ.action?flag=add&dt="+dt.getTime(),900,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","修改隐患","${ctx}/zwt/troManInitEditAJ.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
         function editCXXG(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","修改隐患","${ctx}/zwt/troManInitEditAJ.action?flag=cxxg&troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","查看隐患","${ctx}/zwt/troManViewAJ.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        //上传整改信息
       function uploadRect(row_Id){
       		var dt=new Date();
            createSimpleWindow("win_troMan","上传整改信息","${ctx}/zwt/troManUploadRectAJ.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
       }
       //审核信息
       function audit(row_Id){
       		var dt=new Date();
            createSimpleWindow("win_troMan","审核信息","${ctx}/zwt/troManAuditAJ.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
       }
       
       function createXcjl(row_Id)
       {
       		var dt=new Date();
            createSimpleWindow("win_troMan","现场检查记录","${ctx}/zwt/troManXcjc.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
       }
       
       function createCase(row_Id)
       {
       		var dt=new Date();
            createSimpleWindow("win_troMan","添加案件","${ctx}/zwt/troManCase.action?troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
       }
       
       function deal(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","修改隐患表","${ctx}/zwt/troManInitEditAJ.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function report(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","上报整改信息","${ctx}/zwt/troManInitEditAJ.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        
        function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_troMan","审核隐患信息","${ctx}/zwt/troManAuditAJ.action?flag=check&troMan.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        
        function exprtXls(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+"|";
			}
			$("#ids").val(ids);
			document.myform1.action="${ctx}/zwt/troManExportXls.action";
        	document.myform1.submit();
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
	                    <div class="sipac_tableBar">
	                        <div class="sipac_table_menu"><a href="javascript:addNew();">新建</a><em></em><!--<a href="#">批量删除</a><em></em><a href="#">通过</a><em></em><a href="#">禁用</a><em></em><a href="#">启用</a> --> 
	                       </div>
	                    </div>
	                    
	                   <div class="table-head">
                	<table style="width:100%;">
                		<colgroup>
             				<col style="width:5%"/>
              				<col style="width:15%"/>
              				<col style="width:10%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
              				<col style="width:10%"/>
              				<col style="width:20%"/>
         				</colgroup>
                    	<thead>
                        <tr>
                        	<th><span>序号</span></th>
                        	<th><span>隐患名称 </span></th>
                        	<th><span>隐患来源 </span></th>
                        	<!--<th><span>所在区域  </span></th>-->
                        	<th><span>企业名称  </span></th>
                        	<th><span>上报人员名称   </span></th>
                        	<!--<th><span>隐患级别 </span></th>-->
                        	<!--<th><span>隐患类别   </span></th>-->
                        	<th><span>整改状态  </span></th>
                        	<th    ><span>操作项</span></th>
                        </tr>
  						</thead>
  					</table>
     			</div>
     			<div class="table-body">
     				<table style="width:100%;">
         				<colgroup>
             				<col style="width:5%"/>
              				<col style="width:15%"/>
              				<col style="width:10%"/>
              				<col style="width:20%"/>
              				<col style="width:20%"/>
              				<col style="width:10%"/>
              				<col style="width:20%"/>
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

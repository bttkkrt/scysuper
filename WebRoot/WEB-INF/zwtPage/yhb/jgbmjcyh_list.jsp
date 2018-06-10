<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>监管部门检查隐患查询</title>
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
		                    	"troMan.troubleSource":"非企业"
		                    	
		                    };
    	 	}else{
    	 		ajaxData={
    				pageNo : $("#currPage").val(),
		            pageSize:ps,
		            "troMan.troubleSource":"非企业",
		            ids:$("#ids").val()
		         };
    	 	}
    	 	
    		//$("body").addClass("sipac_full");
			//$(this).addClass("hide");
			///$(".frame_Btn .f_more").removeClass("hide");
			
			getList(1,ps);
			
			$('.sipac_searchbar .s_s_text').click(function(){
		        var email = $(this).val();
		        if(email == "搜索企业名称或隐患名称"){
		            $(this).val("");
		        }
		    }).blur(function(){
		        var email = $(this).val();
		        if(email == ""){
		            $(this).val("搜索企业名称或隐患名称");
		        }
		    });
		});
		
		function search(){
			$("#searchType").val(1);
			ajaxData={ 
		                    	pageNo : no,
		                    	pageSize:ps,
		                    	"troMan.troubleSource":"非企业"
		                    	
		                    };
			getList(1,ps);
		}
		
		function searchByNameOrCode(){
			$("#searchType").val(0);
			ajaxData={ 
		                 pageNo : no,
		                 pageSize:ps,
		                 "troMan.troubleSource":"非企业",
		                 "troMan.companyName":$("#nameString").val()
		                 
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
		
		function getList(num,size){
			ajaxData.pageNo=num;
			ajaxData.pageSize=size;
			$("#currPage").val(num);
			$.ajax({
		                	url : "zwtQyzcyhList.action",
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
		                    		//转换状态
		                    		var zhuangtai=getState(item.rectificationState,item);
		                    		
		                    		//获取操作按钮
		                    		var button=getButton(item);
		                    		//var button="<a style='color:blue' href='#' onclick=view('"+item.id+"') >查看<b></b></a>&nbsp;";
		                    	
		                    		var tr="";
		                    		if(idx%2==0){
		                    			tr="<tr class='row_1'><td>"+index+++"</td>";
		                    		}else{
		                    			tr="<tr><td>"+index+++"</td>";
		                    		}
		                    		tr+="<td>"+item.companyName+"</td><td>"
		                    		+item.troubleName+"</td><td>"+item.troubleSource+"</td><td>"
		                    		+item.userName+"</td><td>"+item.troubleLevel+"</td><td>"
		                    		+item.troubleSort+"</td><td>"+zhuangtai+"</td>"
		                    		//+"<td class='s_t_control'><a style='color:blue' href='#' onclick=view('"+item.id+"','"+item.enterpriseName+"')>详情</a></tr>";
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
			if(value=='1'||value=='3'||value=='4'||value=='6'||value=='11'||value=='00'){
				return "待整改";
			}else if(value=='2'||value=='5'||value=='7'||value=='20'||value=='21'){//20是转接，也算审核；21 待安委会审核
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
        	var button1="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;";
			var button2="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a style='color:blue' href='#' onclick=edit('"+rec.id+"')>修改<b></b></a>";
			var button3="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a style='color:blue' href='#' onclick=deal('"+rec.id+"')>处理<b></b></a>";
			var button4="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a style='color:blue' href='#' onclick=deal('"+rec.id+"')>修改<b></b></a>";
			var button5="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a style='color:blue' href='#' onclick=report('"+rec.id+"')>上报整改信息<b></b></a>";//上报
			var button6="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a style='color:blue' href='#' onclick=check('"+rec.id+"')>审核<b></b></a>";//审核
			var button7="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a style='color:blue' href='#' onclick=editCXXG('"+rec.id+"')>审核未通过请修改<b></b></a>";
			var button8="<a style='color:blue' href='#' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a style='color:blue' href='#' onclick=report('"+rec.id+"')>重新整改<b></b></a>";//上报
				//alert(rec.rectificationState);alert('${userType}');
			//隐患上报隐患人
			if("${ids}"==rec.createUserID&&(rec.rectificationState=="1"||rec.rectificationState=="3")){
				if("审核通过"==rec.auditResult){
					return button1;
				}else if("审核未通过"==rec.auditResult){
					return button7;
				}else{
					if((rec.rectificationState=="1"||rec.rectificationState=="2"||rec.rectificationState=="3")&&'${userType}'=='7'){
						return button1;
					}else
					//安委会办公室的人，在状态是3的时候，选择是否需要安监处理，需要，则状态变为2，不需要，则状态变为3，并选择相关部门进行处理
					if(rec.rectificationState=="3"&&'${userType}'=='1'){
						return button3;
					}else
					//安委会办公室的人，在状态是2(2的时候必须是上报安委会办公室的)或4的时候，可以进行修改操作
					if(((rec.rectificationState=="2"&&rec.ifReportAwh=="1"))&&'${userType}'=='1'){
						//return button4;
						return button1;//11-07 费谦修改（提交之后不能修改，只有审核不通过可以修改）
					}else
					//安委会办公室的人，在状态是21，可以进行审核操作  （转接审核）
					if(((rec.rectificationState=="21"))&&'${userType}'=='1'){
						return button6;//2016-1-22 费谦修改（转接审核） 
					}else
					//安委会职能部门的人，在状态是4的时候，可以进行查看和上报整改信息操作
					if(rec.rectificationState=="4"&&'${userType}'=='6'){
							return button5;
					}else
					
					//安监中队队长，在状态是2的时候，可以进行查看和审核操作 20转接相同
					if((rec.rectificationState=="2"||rec.rectificationState=="20")&&'${userType}'=='3'){
						return button6;
					}else
					
					//网格管理员，在状态是6的时候，可以进行查看和上报整改信息操作
					if(rec.rectificationState=="6"&&'${userType}'=='2'){
						//if("整改未通过"==rec.auditResult&&"${ids}"==rec.userId){
						if("整改未通过"==rec.auditResult){
							return button8;
						}else{
							return button5;
						}
					}else
					
					//监察大队队长，在状态是5的时候，可以进行查看和审核信息操作
					if(rec.rectificationState=="5"&&'${userType}'=='4'){
						if(rec.troubleLevel=='重大'){
							return button6;
						}else if(rec.troubleLevel=='特别重大'&&rec.ifCorrected=='0'){
							return button6;
						}else{
							return button1;	
						}
					}else
					
					//局领导，在状态是7的时候，可以进行查看和审核信息操作
					if(rec.rectificationState=="7"&&'${userType}'=='5'){
						if(rec.troubleLevel=='特别重大'){
							return button6;
						}else{
							return button1;	
						}
						return button6;
					}
					else{
						return button1;	
					}
				}
			}
			if((rec.rectificationState=="1"||rec.rectificationState=="2"||rec.rectificationState=="3")&&'${userType}'=='7'){
				return button1;
			}else
			//安委会办公室的人，在状态是3的时候，选择是否需要安监处理，需要，则状态变为2，不需要，则状态变为3，并选择相关部门进行处理
			if(rec.rectificationState=="3"&&'${userType}'=='1'){
				return button3;
			}else
			//安委会办公室的人，在状态是2(2的时候必须是上报安委会办公室的)或4的时候，可以进行修改操作
			if(((rec.rectificationState=="2"&&rec.ifReportAwh=="1"))&&'${userType}'=='1'){
				//return button4;
				return button1;//11-07 费谦修改（提交之后不能修改，只有审核不通过可以修改）
			}else
			//安委会办公室的人，在状态是21，可以进行审核操作  （转接审核）
			if(((rec.rectificationState=="21"))&&'${userType}'=='1'){
				return button6;//2016-1-22 费谦修改（转接审核） 
			}else
			//安委会职能部门的人，在状态是4的时候，可以进行查看和上报整改信息操作
			if(rec.rectificationState=="4"&&'${userType}'=='6'){
					return button5;
			}else
			
			//安监中队队长，在状态是2的时候，可以进行查看和审核操作 20转接相同
			if((rec.rectificationState=="2"||rec.rectificationState=="20")&&'${userType}'=='3'){
				return button6;
			}else
			
			//网格管理员，在状态是6的时候，可以进行查看和上报整改信息操作
			if(rec.rectificationState=="6"&&'${userType}'=='2'){
				//if("整改未通过"==rec.auditResult&&"${ids}"==rec.userId){
				if("整改未通过"==rec.auditResult){
					return button8;
				}else{
					return button5;
				}
			}else
			
			//监察大队队长，在状态是5的时候，可以进行查看和审核信息操作
			if(rec.rectificationState=="5"&&'${userType}'=='4'){
				if(rec.troubleLevel=='重大'){
					return button6;
				}else if(rec.troubleLevel=='特别重大'&&rec.ifCorrected=='0'){
					return button6;
				}else{
					return button1;	
				}
			}else
			
			//局领导，在状态是7的时候，可以进行查看和审核信息操作
			if(rec.rectificationState=="7"&&'${userType}'=='5'){
				if(rec.troubleLevel=='特别重大'){
					return button6;
				}else{
					return button1;	
				}
				return button6;
			}
			else{
				return button1;	
			}
        }
        
        
        function addNew(){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "添加隐患",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/troManInitEditAJ.action?flag=add&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
        }
        
        function edit(row_Id){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "修改隐患",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/troManInitEditAJ.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
        }
         function editCXXG(row_Id){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "修改隐患",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/troManInitEditAJ.action?flag=cxxg&troMan.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
        }
        
        function view(row_Id){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "查看隐患",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/troManViewAJ.action?troMan.id="+row_Id+"&dt="+dt.getTime()
		    });
        	
        }
        //上传整改信息
       function uploadRect(row_Id){
       		var dt=new Date();
       		layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "上传整改信息",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/troManUploadRectAJ.action?troMan.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
       }
       //审核信息
       function audit(row_Id){
       		var dt=new Date();
       		layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "审核信息",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/troManAuditAJ.action?troMan.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
       }
        function deal(row_Id){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "修改隐患信息",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/troManInitEditAJ.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
        }
        function report(row_Id){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "上报整改信息",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/troManInitEditAJ.action?flag=mod&troMan.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
        }
        
        function check(row_Id){
        	var dt=new Date();
        	layer.open({
		        type: 2,
		        skin: 'layui-layer-lan',
		        title: "审核隐患信息",
		        fix: false,
		        shadeClose: true,
		        maxmin: true,
		        area: ['1000px', '500px'],
		        content: "${ctx}/zwt/troManAuditAJ.action?flag=check&troMan.id="+row_Id+"&dt="+dt.getTime(),
		        end: function(){
		        		getList(ajaxData.pageNo,ajaxData.pageSize);
		        	}
		    });
        	
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
    	<div class="sipac_head">
        	<a href="#" class="s_h_logo"><i></i><span>管理系统名称</span></a>
            <div class="s_h_nav">
                <ul>
                	<li>
                		<a  href="${ctx }/zwt/recPlaList.action">专项整治计划管理</a>
                    </li>
                    <li>
                		<a href="${ctx }/zwt/recResList.action">专项整治检查记录管理</a>
                	</li>
                	<li>
                		<a class="active" href="${ctx }/zwt/jgbmjcyhListZwt.action">安全生产隐患管理</a>
                	</li>
                	<li>
                		<a href="${ctx }/zwt/superviceList.action">挂牌督办管理</a>
                	</li>
                </ul>
            </div>
             
            <div class="sipac_rtbox">
                <ul class="sipac_adminbox">
                    <li style="display:none;">
                		<a class="help" title="帮助" href="#"></a>
                	</li>
                	<li>
                		<!--<a class="manage" title="管理" href="#"></a>
                        --><div class="dropdown_nav">
                        	<em></em>
                            <ul>
                            	<li class="frame_Btn">
                                <a class="f_more hide" href="javascript:void(0);">展开菜单</a><a class="f_less"  href="javascript:void(0);">隐藏菜单</a></li>
                            	<li><a href="#">帮助</a></li>
                            </ul>
                        </div>
                	</li>
                </ul>
            	<div class="sipac_searchbar">
                	<input class="s_s_text" type="text" id="nameString" value="搜索企业名称或隐患名称"><input class="s_s_btn" type="submit" onclick="searchByNameOrCode();"><!-- 
                	<div class="s_s_mBox" style='display:none' ><a href="#" class="s_s_morebtn"></a>
                	--></div>
                </div>
            </div>
    	</div>

<!-- body style -->
        <div class="sipac_body">
        	<div class="sipac_menu">
            	<ul>
                	<li>
                    	<a class="m_icon m_i_0" href="index.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="index.html">返回主界面</a>
                        </div>
                    </li>
                	<!--li>
                    	<a class="m_icon m_i_1" href="style_guide.html"></a>
                        <div class="dropdown_nav">
                        	<a class="s_m_tit" href="style_guide.html">样式规范</a>
                        	<ul>
                        	<li><a href="">图标</a></li>
                        	<li><a href="">按钮</a></li>
                        	</ul>
                        </div>
                    </li-->
                	<li class="dropdown_active">
                    	<a class="m_icon m_i_2" href="search_table.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="search_table.html">例：表格+搜索</a>
                        </div>
                    </li>
                	<li>
                    	<a class="m_icon m_i_3" href="form.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="form.html">例：表单示例</a>
                        </div>
                    </li>
                	<li>
                    	<a class="m_icon m_i_4" href="simple_page.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="simple_page.html">例：简洁框架</a>
                        </div>
                    </li>
                </ul>
                
            </div>
            <div class="sipac_container" id="tableDiv">
            	<div class="sipac_searchBox sipac_form" id="searchForm">
                	<!--<table>
                    	<tr>
                        	<th>企业名称</th>
                            <td width="20%"><input class="s_input" id="enterpriseName" type="text" value=""></td>
                        	<th>工商注册号</th>
                            <td width="20%"><input class="s_input" id="registrationNumber" type="text" value=""></td>
                            <th >企业性质</th>
							<td width="20%"><cus:SelectOneTag property="entBaseInfo.enterpriseNature" defaultText='请选择' codeName="企业性质" class="s_select" /></td>
                        </tr>
                        <tr>
                        	<th>组织机构代码</th>
                            <td width="20%"><input class="s_input" id="enterpriseCode" type="text" value=""></td>
                        	<th>法人姓名</th>
                            <td width="20%"><input class="s_input" id="enterpriseLegalName" type="text" value=""></td>
                        	
                            <th>企业属地</th>
							<td width="20%"><cus:SelectOneTag property="entBaseInfo.enterprisePossession" defaultText='请选择' codeName="企业属地" class="s_select" /></td>
                        </tr>
                        <tr>
                        	<th>所属网格</th>
                            <td width="20%"><input class="s_input" id="gridName" type="text" value=""></td>
                        </tr>
                        
                    </table>
                    --><div class="s_s_bar"><button class="sipac_btn s_b_submit" onclick="search();">搜索</button></div>
                    <div class="s_s_close"><a href="#"></a></div>
                </div>
                <!--<div class="sipac_crumbs"><a class="s_c_back" href="#" title="返回">&lt;</a><div class="s_c_cont">已选条件: 普通应用</div></div>-->
            	<div class="sipac_tableBox">
                   <!-- <div class="sipac_tableBar">
                        <div class="sipac_table_menu"><a href="#">新建</a><em></em><a href="#">修改</a><em></em><a href="#">批量删除</a><em></em><a href="#">通过</a><em></em><a href="#">禁用</a><em></em><a href="#">启用</a><em></em><a href="#">刷新</a>
                        </div>
                    </div>-->
                	<table>
                    	<thead>
                        <tr>
                        	<!--<th style="width:40px;"><span><input type="checkbox"></span></th>
                        	--><th><span>序号</span></th>
                        	<th><span>企业名称</span></th>
                        	<th><span>隐患名称 </span></th>
                        	<th><span>隐患来源  </span></th>
                        	<th><span>上报人员名称</span></th>
                        	<th><span>隐患级别 </span></th>
                        	<th><span>隐患类别 </span></th>
                        	<th><span>整改状态  </span></th>
                        	<th ><span>操作项</span></th>
                        </tr>
  						</thead>
                    	<tbody id="tbody">
  						</tbody>
                    </table>
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

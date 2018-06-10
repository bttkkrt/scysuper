<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>查询统计</title>
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
<script src="${ctx}/webResources/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
    	 	//用户登录，保存session
    	 	$.ajax({
		                	url : "${ctx}/ajaxLoginMd5.action",
		                	type: 'post',
		                	dataType: 'json',
		                    data: {loginId:"${u.loginId}",password:"${u.password}"},
		                    async : false,
		                    error: function(){
		                    },
		                    success: function(data){
		                    }
		                });
    	 	$("#mainContent").load("${ctx}/zwt/jshxZcyhsbDataDept.action?tongjiType=dept");
		   
		});
		
        
        function bind1(title,obj)
        {
        	$(".sipac_menu li").removeClass('dropdown_active');
	        $(obj).addClass('dropdown_active');  
	        changeMenu1( title); //一级菜单切换
        }
        
        function bind2(title,obj)
        {
        	$(".s_sb_nav a").removeClass('active');
	        $(obj).addClass('active');  
	        changeMenu3(title); //三级菜单切换
        }
        
        var aMenu3="<a class='active' href='javascript:;' onclick=bind2('所属部门',this)>所属部门</a>"+
			             			 "<a href='javascript:;' onclick=bind2('所属网格',this)>所属网格</a>"+
			             		  	 "<a href='javascript:;' onclick=bind2('所属企业',this)>所属企业</a>";
			             		 	 
		var bMenu3="<a class='active' href='javascript:;' onclick=bind2('所属部门',this)>所属部门</a>"+
			             			 "<a href='javascript:;' onclick=bind2('所属网格',this)>所属网格</a>"+
			             		  	 "<a href='javascript:;' onclick=bind2('上报人员',this)>上报人员</a>";
	             			
        var menuType="qy";
        function changeMenu1(str){
        	if(str=='企业自查自纠统计'){
        		 menuType="qy";
        		 $("#title").html('企业自查自纠统计');
	             $(".s_sb_nav").html(aMenu3); 
        	}else if(str=='安监隐患统计'){
        		 menuType="aj";
        		 $("#title").html('安监隐患统计');
	             $(".s_sb_nav").html(bMenu3); 
        	}
        	changeMenu3('所属部门');
        	
        }
        
    
     	var loadUrl="${ctx}/zwt/jshxZcyhsbDataDept.action?tongjiType=dept";
     	var searchType="ssbm";
     	function  changeMenu3(str){
     		var starttime=$("#starttime").val();
		    var endtime=$("#endtime").val();
        	if(str=='所属部门'){
        		if(menuType=="qy"){
        			loadUrl="${ctx}/zwt/jshxZcyhsbDataDept.action?tongjiType=dept";
        		}else{
        			loadUrl="${ctx}/zwt/ajyhDataDept.action?tongjiType=dept";
        		}
        	}else if(str=='所属网格'){
        		if(menuType=="qy"){
	        		loadUrl="${ctx}/zwt/jshxZcyhsbDataDept.action?tongjiType=grid";
	        		
        		}else{
        			loadUrl="${ctx}/zwt/ajyhDataDept.action?tongjiType=grid";
        		}
        	}else if(str=='所属企业'){
        		loadUrl="${ctx}/zwt/cxtjSsqy.action?ids=1";
        	}else if(str=='上报人员'){
        		loadUrl="${ctx}/zwt/cxtjSbry.action?ids=1";
        	}
        	var u = loadUrl;
        	if(null!=starttime&&''!=starttime){
		    	u+="&starttime="+encodeURIComponent(starttime);
		    }
		    if(null!=endtime&&''!=endtime){
		    	u+="&endtime="+encodeURIComponent(endtime);
		    }
		    $("#mainContent").load(u);
        }
        
        function searchSubmit(){
		    var starttime=$("#starttime").val();
		    var endtime=$("#endtime").val();
		    var u = loadUrl;
		    if(null!=starttime&&''!=starttime){
		    	u+="&starttime="+encodeURIComponent(starttime);
		    }
		    if(null!=endtime&&''!=endtime){
		    	u+="&endtime="+encodeURIComponent(endtime);
		    }
		    $("#mainContent").load(u);
	    }
    </script>
</head>
<body>
	<div class="sipac_wrap">
    	<div class="sipac_head" style="display:none">
        	<a href="javaScript:selectCompany()" class="s_h_logo"><i></i><span></span></a>
             
            
    	</div>
        <div class="sipac_body">
        	<div class="sipac_menu">
            	<ul>
                	<li class="dropdown_active" onclick=bind1('企业自查自纠统计',this)>
                    	<a class="m_icon m_i_1 " href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">企业自查自纠统计</a>
                        </div>
                    </li>
                    <li onclick=bind1('安监隐患统计',this)>
                    	<a class="m_icon m_i_2" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">安监隐患统计</a>
                        </div>
                    </li>
                </ul>
                
            </div>
            <div class="sipac_container">
                <div class="sipac_crumbs" >
                	<div class="s_c_cont" id="title">企业自查自纠统计</div>
                	
                	
                	
                	<div class="sipac_rtbox">
		            	<div class="sipac_searchbar">
							<input style="background:transparent;border:1px solid #ffffff;width:80px;" value='上报时间：'>
               	 			<input name="starttime" id="starttime" value="${starttime}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime\')}'})" >
							<input style="background:transparent;border:1px solid #ffffff;width:10px;" value='~'>
							<input name="endtime" id="endtime" value="${endtime}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime\')}'})" >
            				<input class="s_s_btn" type="button" onclick="searchSubmit();">
		                </div>
            		</div>
                 </div>
                <div class="sipac_selectbox">
                	<div class="s_sb_nav">
                		<a class="active" href="#" onclick=bind2('所属部门',this)>所属部门</a><a href="#" onclick=bind2('所属网格',this)>所属网格</a><a href="#" onclick=bind2('所属企业',this)>所属企业</a>
                	</div>
                    <div class="s_sb_cont sipac_form" id="mainContent">                         
                         	数据加载中，请稍等...
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

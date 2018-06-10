<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>公共信息管理</title>
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
    var searchText="搜索预案名称或预案编号";
    	 $(function(){
    	 
    	 	$('.sipac_searchbar .s_s_text').click(function(){
		        var email = $(this).val();
		        if(email == searchText){
		            $(this).val("");
		        }
		    }).blur(function(){
		        var email = $(this).val();
		        if(email == ""){
		            $(this).val(searchText);
		        }
		    });
    	 	
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
		    
		    
    	 	$("#mainContent").load("${ctx}/zwt/zfyjjyya.action?type=1");
		   
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
       
        var aMenu3="<a class='active' href='javascript:;' onclick=bind2('政府应急救援预案',this)>政府应急救援预案</a>";
			             		 	 
		var bMenu3="<a class='active' href='javascript:;' onclick=bind2('安全生产专家库',this)>安全生产专家库</a>";
	             			
	    var cMenu3="<a class='active' href='javascript:;' onclick=bind2('医疗卫生机构',this)>医疗卫生机构</a>"+
			             			 "<a href='javascript:;' onclick=bind2('消防组织机构',this)>消防组织机构</a>"+
			             			 "<a href='javascript:;' onclick=bind2('公安武警组织机构',this)>公安武警组织机构</a>";	
	             					 
	    var dMenu3="<a class='active' href='javascript:;' onclick=bind2('救援物资管理及提醒',this)>救援物资管理及提醒</a>";
			             			 
		var eMenu3="<a class='active' href='javascript:;' onclick=bind2('物品危险性知识库',this)>物品危险性知识库</a>";
	             					            			 
		
        
        function changeMenu1(str){
        	if(str=='政府应急救援预案'){
	             document.getElementById('ejcd').style.display="none"; 
	             changeMenu3('政府应急救援预案');
		     $("#title").html('政府应急救援预案');
        	}else if(str=='安全生产专家库'){
	             document.getElementById('ejcd').style.display="none"; 
		     $("#title").html('安全生产专家库');
	             changeMenu3('安全生产专家库');
        	}else if(str=='公共机构'){
        		document.getElementById('ejcd').style.display=""; 
	             $(".s_sb_nav").html(cMenu3); 
		     $("#title").html('公共机构');
	             changeMenu3('医疗卫生机构');
        	}else if(str=='救援物资'){
	            document.getElementById('ejcd').style.display="none"; 
		     $("#title").html('救援物资管理及提醒');
	             changeMenu3('救援物资管理及提醒');
        	}else if(str=='物品危险性知识库'){
        		 document.getElementById('ejcd').style.display="none"; 
			 $("#title").html('物品危险性知识库');
	             changeMenu3('物品危险性知识库');
        	}
        	
        	
        }
        
    	var loadUrl='${ctx}/zwt/zfyjjyya.action?type=1';
     	function  changeMenu3(str){
        	if(str=='政府应急救援预案'){
        		searchText="搜索预案名称或预案编号";
        		loadUrl="${ctx}/zwt/zfyjjyya.action?type=1";
        	}else if(str=='安全生产专家库'){
        		searchText="搜索姓名或联系电话";
        		loadUrl="${ctx}/zwt/aqsczjk.action";
        	}else if(str=='医疗卫生机构'){
        		searchText="搜索机构编号、机构名称或联系电话";
        		loadUrl="${ctx}/zwt/ylwsjg.action";
        	}else if(str=='消防组织机构'){
        		searchText="搜索机构编号、机构名称或联系电话";
        		loadUrl="${ctx}/zwt/xfzzjg.action";
        	}else if(str=='公安武警组织机构'){
        		searchText="搜索机构编号、机构名称或联系电话";
        		loadUrl="${ctx}/zwt/gawjzzjg.action";
        	}else if(str=='救援物资管理及提醒'){
        		searchText="搜索物资名称";
        		loadUrl="${ctx}/zwt/jywzgljtx.action?type=1";
        	}else if(str=='物品危险性知识库'){
        		searchText="搜索物品名称";
        		loadUrl="${ctx}/zwt/wpwxxzskAJ.action";
        	}
        	$("#mainContent").load(loadUrl);
        	$("#searchText").val(searchText);
        }
    
    
    	function searchUrl(){
        	var s=$("#searchText").val();
        	if(s.indexOf("搜索") < 0){
        		var u='';
        		if(loadUrl.indexOf('?')>=0){
        			u=loadUrl+"&searchLike="+encodeURIComponent(s);
        		}else{
        			u=loadUrl+"?searchLike="+encodeURIComponent(s);
        		} 
	        	$("#mainContent").load(u);
        	}else{
        		$("#mainContent").load(loadUrl);
        	}
        }
  
    
    </script>
</head>
<body>

	<div class="sipac_wrap">
    	<div class="sipac_body">
        	<div class="sipac_menu">
            	<ul>
                	
                
                	<li class="dropdown_active" onclick=bind1('政府应急救援预案',this)>
                    	<a class="m_icon m_i_1 " href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">政府应急救援预案</a>
                        </div>
                    </li>
                    <li onclick=bind1('安全生产专家库',this)>
                    	<a class="m_icon m_i_2" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">安全生产专家库</a>
                        </div>
                    </li>
		    <li onclick=bind1('公共机构',this)>
                    	<a class="m_icon m_i_3" href="#"></a>
                        <div class="dropdown_nav">
                        	<a class="s_m_tit" href="#">公共机构</a>
                        </div>
                    </li>
                	<li onclick=bind1('救援物资',this)>
                    	<a class="m_icon m_i_4" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">救援物资</a>
                        </div>
                    </li>
		    <li onclick=bind1('物品危险性知识库',this)>
                    	<a class="m_icon m_i_5" href="#"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="#">物品危险性知识库</a>
                        </div>
                    </li>
                </ul>
                
            </div>
            <div class="sipac_container">
                <div class="sipac_crumbs" >
                	<div class="s_c_cont" id="title">政府应急救援预案</div>
                	
                	
                	
                	<div class="sipac_rtbox">
		            	<div class="sipac_searchbar"><input class="s_s_text" id="searchText" type="text" value="搜索预案名称或预案编号"><input class="s_s_btn" type="button" onclick="searchUrl();">
		                </div>
            		</div>
                 </div>
                <div class="sipac_selectbox">
                	<div id="ejcd" class="s_sb_nav" style="display:none">
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

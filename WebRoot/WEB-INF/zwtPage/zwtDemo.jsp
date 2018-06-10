<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>企业基本信息列表</title>
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
<style type="text/css">
    .sipac_table_menu span { float: left;padding: 5px 10px 0; font: 14px/30px Arial;text-align: center;font-weight:bold;}
    </style>
    
    <script>
    	$(function(){
	    	
	    	$(".s_h_nav a").each(function () {//id="divUserList"的DIV的子元素中，凡带有“code”属性的都被遍历  
	            $(this).bind("click", function () {//绑定事件  
	            	$(".s_h_nav a").removeClass('active');
	                $(this).addClass('active');  
	            });  
	              
	           // $(this).css("cursor","pointer");//设置样式：鼠标指针  
	        });
	        
	        $(".s_sb_nav a").each(function () {//id="divUserList"的DIV的子元素中，凡带有“code”属性的都被遍历  
	            $(this).bind("click", function () {//绑定事件  
	            	$(".s_sb_nav a").removeClass('active');
	                $(this).addClass('active');  
	            });  
	              
	           // $(this).css("cursor","pointer");//设置样式：鼠标指针  
	        });
	     });
        
    </script>
</head>
<body>
<!-- Wrap all page content here -->
	<div class="sipac_wrap">
    	<div class="sipac_head">
        	<a href="index.html" class="s_h_logo"><i></i><span>苏州玉田精密模具有限公司</span></a>
             
            <div class="sipac_rtbox">
                <ul class="sipac_adminbox">
                    <li style="display:none;">
                		<a class="help" title="帮助" href="#"></a>
                	</li>
                	<li>
                		<a class="manage" title="管理" href="#"></a>
                        <div class="dropdown_nav">
                        	<em></em>
                            <ul>
                            	<li class="frame_Btn">
                                <a class="f_more hide" href="javascript:void(0);">展开菜单</a><a class="f_less"  href="javascript:void(0);">隐藏菜单</a></li>
                            	<li><a href="#">帮助</a></li>
                            </ul>
                        </div>
                	</li>
                </ul>
            	<div class="sipac_searchbar"><input class="s_s_text" type="text" value="搜索相关信息"><input class="s_s_btn" type="submit"><div class="s_s_mDrop"><a href="#" class="s_s_morebtn"></a><a href="#" class="s_s_lessbtn"></a>
                        <div class="s_s_popup">
                        	<div class="s_s_p_tit"><h1>综合搜索</h1><a class="close">×</a></div>
                                <div class="sipac_editbox sipac_form">
                                    <table>
                                        <tr><th>条件一</th>
                                            <td><input class="s_input" type="text" value="XXX"></td>
                                        </tr>
                                        <tr>
                                            <th>条件二</th>
                                            <td><input class="s_input" type="text" value="XXX"></td>
                                        </tr>
                                        <tr>
                                            <th></th>
                                            <td><button class="sipac_btn s_b_submit">确认</button><button class="sipac_btn s_b_cancel">清空</button></td>
                                        </tr>
                                     </table>
                                </div>
                        </div>
                	</div></div>
            </div>
    	</div>
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
                	<li >
                    	<a class="m_icon m_i_2 " href="search_table.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="search_table.html">基础信息</a>
                        </div>
                    </li>
                	<li>
                    	<a class="m_icon m_i_3" href="form.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="form.html">安全生产</a>
                        </div>
                    </li>
                	<li>
                    	<a class="m_icon m_i_4" href="simple_page.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="simple_page.html">隐患排查</a>
                        </div>
                    </li>
                    <li class="dropdown_active">
                    	<a class="m_icon m_i_1" href="simple_page.html"></a>
                        <div class="dropdown_nav">
                         <a class="s_m_tit" href="simple_page.html">其他</a>
                        </div>
                    </li>
                </ul>
                
            </div>
            <div class="sipac_container">
                <div class="sipac_crumbs">
                	<div class="s_h_nav">
                	<ul>
	                	<li>
	                		<a href="#">职业健康</a>
	                    </li>
	                	<li>
	                		<a class="active" href="#">危化信息</a>
	                    </li>
	                	<li>
	                		<a href="#">涉爆粉尘信息</a>
	                	</li>
	                </ul>
                </div>
                	
                 </div>
                <div class="sipac_selectbox">
                	<div class="s_sb_nav"><a class="active" href="#">危险源信息</a><a href="#">相关信息</a><a href="#">栏目信息</a></div>
                     
                   <div class="sipac_tableBox" style="border-right:1px solid #ECECEC;border-left:1px solid #ECECEC;border-top:1px solid #ECECEC">
                    <div class="sipac_tableBar">
                    	
                        <div class="sipac_table_menu" ><span>危险源备案</span>
                        </div>
                    </div>
                    
                	<table>
                    	<thead>
                        <tr>
                        	<th style="width:40px;"><span><input type="checkbox"></span></th>
                        	<th><span>序号</span><span class="s_form_btn s_f_up"></span></th>
                        	<th><span>应用类型</span><span class="s_form_btn s_f_arrow"></span></th>
                        	<th><span>发送应用</span><span class="s_form_btn s_f_down"></span></th>
                        	<th class="s_t_date"><span>接收日期</span></th>
                        	<th><span>通知内容</span></th>
                        	<th style="width:100px;" class="s_t_control"><span>操作项</span></th>
                        </tr>
  						</thead>
                    	<tbody>
                        <tr class="row_1">
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
                        <tr>
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
                        <tr class="row_1">
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
                        <tr>
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
  						</tbody>
                    </table>
                	<div class="sipac_pagebox">
                        <div class="s_p_info">第1页 / 共17页，共162条数据</div> 
                        <div class="s_p_btn"><a href="#">首页</a><a href="#">上一页</a><a href="#">下一页</a><a href="#">尾页</a></div>
                	</div>
                	
                	
            	</div>
            	
            	<div class="sipac_tableBox" style="border:1px solid #ECECEC">
                    <div class="sipac_tableBar">
                    	
                        <div class="sipac_table_menu" ><span>危险源核销</span>
                        </div>
                    </div>
                    
                	<table>
                    	<thead>
                        <tr>
                        	<th style="width:40px;"><span><input type="checkbox"></span></th>
                        	<th><span>序号</span><span class="s_form_btn s_f_up"></span></th>
                        	<th><span>应用类型</span><span class="s_form_btn s_f_arrow"></span></th>
                        	<th><span>发送应用</span><span class="s_form_btn s_f_down"></span></th>
                        	<th class="s_t_date"><span>接收日期</span></th>
                        	<th><span>通知内容</span></th>
                        	<th style="width:100px;" class="s_t_control"><span>操作项</span></th>
                        </tr>
  						</thead>
                    	<tbody>
                        <tr class="row_1">
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
                        <tr>
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
                        <tr class="row_1">
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
                        <tr>
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
  						</tbody>
                    </table>
                	<div class="sipac_pagebox">
                        <div class="s_p_info">第1页 / 共17页，共162条数据</div> 
                        <div class="s_p_btn"><a href="#">首页</a><a href="#">上一页</a><a href="#">下一页</a><a href="#">尾页</a></div>
                	</div>
                	
                	
            	</div>
            	
            	
                	<div class="sipac_tableBox" style="border:1px solid #ECECEC">
                    <div class="sipac_tableBar">
                    	
                        <div class="sipac_table_menu" ><span>危化品储量</span>
                        </div>
                    </div>
                    
                	<table>
                    	<thead>
                        <tr>
                        	<th style="width:40px;"><span><input type="checkbox"></span></th>
                        	<th><span>序号</span><span class="s_form_btn s_f_up"></span></th>
                        	<th><span>应用类型</span><span class="s_form_btn s_f_arrow"></span></th>
                        	<th><span>发送应用</span><span class="s_form_btn s_f_down"></span></th>
                        	<th class="s_t_date"><span>接收日期</span></th>
                        	<th><span>通知内容</span></th>
                        	<th style="width:100px;" class="s_t_control"><span>操作项</span></th>
                        </tr>
  						</thead>
                    	<tbody>
                        <tr class="row_1">
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
                        <tr>
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
                        <tr class="row_1">
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
                        <tr>
                        	<td><span><input type="checkbox"></span></td>
                        	<td>1</td>
                        	<td>文字</td>
                        	<td>文字</td>
                        	<td class="s_t_date">2015-12-31 16:09:47</td>
                        	<td>文字</td>
                        	<td class="s_t_control">
                            	<a class="s_t_Btn stb_1" href="#">编辑</a>
                                <a class="s_t_Btn stb_2" href="#">删除</a>
                            </td>
                        </tr>
  						</tbody>
                    </table>
                	<div class="sipac_pagebox">
                        <div class="s_p_info">第1页 / 共17页，共162条数据</div> 
                        <div class="s_p_btn"><a href="#">首页</a><a href="#">上一页</a><a href="#">下一页</a><a href="#">尾页</a></div>
                	</div>
                	
                	
            	</div>
                	
                		
                 
                	
	
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

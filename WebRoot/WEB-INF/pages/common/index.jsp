<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<link rel="shortcut icon" href="${ctx}/webResources/images/favicon.ico">
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!-- <LINK rel="Bookmark" href="h-ui/favicon.ico" >
<LINK rel="Shortcut Icon" href="h-ui/favicon.ico" /> -->
<%@include file="/h-ui/lib-css.jsp" %>
  <style type="text/css">
#msg_win{border:1px solid #A67901;background:#EAEAEA;width:240px;position:absolute;right:0;font-size:12px;font-family:Arial;margin:0px;display:none;overflow:hidden;z-index:99;}
#msg_win .icos{position:absolute;top:2px;*top:0px;right:2px;z-index:9;}
.icos a{float:left;color:#833B02;margin:1px;text-align:center;font-weight:bold;width:14px;height:22px;line-height:22px;padding:1px;text-decoration:none;font-family:webdings;}
.icos a:hover{color:#fff;}
#msg_title{background:#FECD00;border-bottom:1px solid #A67901;border-top:1px solid #FFF;border-left:1px solid #FFF;color:#000;height:25px;line-height:25px;text-indent:5px;}
#msg_content{margin:5px;margin-right:0;width:230px;height:133px;overflow:hidden;}
</style>
 <link rel="stylesheet" type="text/css" href="${ctx}/h-ui/menu/css/menu-css.css">
   <link rel="stylesheet" type="text/css" href="${ctx}/h-ui/1.0.8/iconfont.css">
<title> 智慧安监管理平台 </title>
</head>
<body>
<header class="Hui-header cl">
	<div style="width:100%;height:100%;">
	
	
		<a class="Hui-logo l" title="智慧安监" href="javascript:location.replace(location.href);">智慧安监</a>   <span class="Hui-subtitle l"> 云管理平台 </span>
		<ul class="Hui-userbar" style="margin-top: 15px;position: inherit;float: right;">
			<li>欢迎您 </li>
			<li class="dropDown dropDown_hover"><a href="javascript:;"class="dropDown_A">${curr_user.displayName} <i class="Hui-iconfont">&#xe6d5;</i></a>
				<ul class="dropDown-menu radius box-shadow">
					<li ><a href="javascript:void(0)" onclick="Hui_admin_tab(this)" _href="${ctx}/personal/changePassword.jsp" data-title='修改密码'><span class="am-icon-expeditedssl"></span>修改密码</a></li>
					<li><a  href="javascript:void(0)" onclick="logout()" >退出</a></li>
				</ul> 
			</li>
			<!-- <li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
				<ul class="dropDown-menu radius box-shadow">
					<li><a href="javascript:;" data-val="blue" title="默认（蓝色）">默认（蓝色）</a></li>
					<li><a href="javascript:;" data-val="default" title="黑色">黑色</a></li>
					<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
					<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
					<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
					<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
				</ul>
			</li> -->
		</ul>
		<%-- <div class="topbox">
		   <a href="javascript:void(0)" onclick="Hui_admin_tab(this)" _href="/yqzhaj/jsp/carDoneInfo/getDevList.action" data-title="视频中心">
		   	<div class="icon"><img src="${ctx}/webResources/style/images/index/spzx.png"></div><p>视频中心</p>
		   </a>
		   <a  href="javascript:void(0)" onclick="Hui_admin_tab(this)" _href="/yqzhaj/jsp/yhb/troManList.action?type=111" data-title="日常监管">
		   	<div class="icon"><img src="${ctx}/webResources/style/images/index/rcjg.png"></div><p>监管中心</p>
		   </a>
		   <a  href="javascript:void(0)" onclick="Hui_admin_tab(this)" _href="/yqzhaj/jsp/qyjbxx/entBaseInfoList.action" data-title="数据中心">
		  	 <div class="icon"><img src="${ctx}/webResources/style/images/index/sjzx.png"></div><p>数据中心</p>
		   </a>
		</div>--%>
		<a href="javascript:;" class="Hui-nav-toggle Hui-iconfont" aria-hidden="false">&#xe667;</a>  
	</div>
</header>


<!-- 左侧菜单栏 start-->
<aside class="Hui-aside">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
        <div class="menu"  >
            <ul style="margin-bottom: 5px;">
            	<li class='menus'>
					<a href='javascript:void(0)'  onclick="load_default();" data-title='${module.moduleName}' ><icon class="Hui-iconfont Hui-iconfont-home"></icon>首页</a>
				</li> 
 				<%-- <c:forEach items="${moduleList}" var="module"  >
					<c:if test="${not empty  module.moduleAddr}">
						<c:set var="moduleAddr"   value="${ctx}/${module.moduleAddr}?moduleNames=${module.moduleName}"/>
					</c:if>
 					<c:if test="${module.hasChild>0}">
						<li class='menus'>
							<a href='javascript:void(0)'  onclick="getMenuData($(this),'${module.moduleCode}','${module.moduleName}')" 	_href="${module.moduleAddr}" data-title='${module.moduleName}' ><icon class="${module.smallIconAddr}"></icon>${module.moduleName} <i class='Hui-iconfont Hui-iconfont-arrow2-right'></i></a>
						</li>
					</c:if>
  					<c:if test="${module.hasChild==0}">
						<li class='item'>
							<a href='javascript:void(0)'  onclick="Hui_admin_tab(this)" _href="${ctx}/${module.moduleAddr}" data-title='${module.moduleName}' ><icon class="${module.smallIconAddr}"></icon>${module.moduleName} <i></i></a>
						</li>
					</c:if>	
 				</c:forEach>    --%>    
 				
 				<c:forEach items="${items}" var="moduleitem" varStatus="status1">
						<c:if test="${fn:length(moduleitem.children)>0}">
							<li class='menus'>
								<a href='javascript:void(0)' data-title='${moduleitem.text}' ><icon class="${moduleitem.iconCls }"></icon>${moduleitem.text} <i class='Hui-iconfont Hui-iconfont-arrow2-right'></i></a>
								<ul >
									<c:forEach items="${moduleitem.children}" var="child" varStatus="status2">
										<c:if test="${fn:length(child.children)>0}">
											<li class='menus'>
												<a href='javascript:void(0)'  data-title='${child.text}' >${child.text} <i class='Hui-iconfont Hui-iconfont-arrow2-right'></i></a>
												<ul>
														<c:forEach items="${child.children}" var="child2" varStatus="status3">
															<c:if test="${fn:length(child2.children)>0}">
																<li class='menus'>
																	<a href='javascript:void(0)'  data-title='${child2.text}' >${child2.text} <i class='Hui-iconfont Hui-iconfont-arrow2-right'></i></a>
																	<ul>
																		<c:forEach items="${child2.children}" var="child3" varStatus="status4">
																			<li class='item'>
																				<c:if test="${empty child3.attributes}">
																					<a href='javascript:void(0)'  onclick="Hui_admin_tab(this)"	_href="${ctx}/personal/error_developing.html" data-title='${child3.text}' >${child3.text}</a>
																				</c:if>
																				<c:if test="${not empty child3.attributes}">
																					<a href='javascript:void(0)'  onclick="Hui_admin_tab(this)"	_href="${ctx}/${child3.attributes}" data-title='${child3.text}' >${child3.text}</a>
																				</c:if>
																			</li>
																		</c:forEach>
																	</ul>
																</li>
															</c:if>
															<c:if test="${fn:length(child2.children)==0}">
																<li class='item'>
																	<c:if test="${empty child2.attributes}">
																		<a href='javascript:void(0)'  onclick="Hui_admin_tab(this)"	_href="${ctx}/personal/error_developing.html" data-title='${child2.text}' >${child2.text}</a>
																	</c:if>
																	<c:if test="${not empty child2.attributes}">
																		<a href='javascript:void(0)'  onclick="Hui_admin_tab(this)"	_href="${ctx}/${child2.attributes}" data-title='${child2.text}' >${child2.text}</a>
																	</c:if>
																</li>
															</c:if>
														</c:forEach>
												</ul>
											</li>
											</c:if>
											<c:if test="${fn:length(child.children)==0}">
												<li class='item'>
													<c:if test="${empty child.attributes}">
														<a href='javascript:void(0)'  onclick="Hui_admin_tab(this)"	_href="${ctx}/personal/error_developing.html" data-title='${child.text}' >${child.text}</a>
													</c:if>
													<c:if test="${not empty child.attributes}">
														<a href='javascript:void(0)'  onclick="Hui_admin_tab(this)"	_href="${ctx}/${child.attributes}" data-title='${child.text}' >${child.text}</a>
													</c:if>
												</li>
											</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:if>
						<c:if test="${fn:length(moduleitem.children)==0}">
							<li class='item'>
								<a href='javascript:void(0)'  onclick="clickModule(this,1)" _href="${ctx}/${moduleitem.attributes}" data-title='${moduleitem.text}' ><icon class="${moduleitem.iconCls }"></icon>${moduleitem.text} <i></i></a>
							</li>
						</c:if>
				</c:forEach>
 				 
            </ul>
       
       
       
        </div>
	</div>
</aside>
<!-- 左侧菜单栏 end-->

<!-- 左侧菜单栏收缩条 start-->
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<!-- 左侧菜单栏收缩条 end-->

<!-- 主面板区域 start-->
<section class="Hui-article-box">

	<!-- 主面板标题区域 start-->
	<div id="Hui-tabNav" class="Hui-tabNav">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="我的桌面" data-href="${ctx}/jsp/information/contentInformationsList.action?moduleNames=最新公告">首页</span><em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<!-- 主面板标题区域 end-->

	<!-- 主面板内容区域 start-->
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div id="tabdiv">
				</div>
				<div id="zdkj" style="position:absolute;top:0;left:0;right:0;bottom:0;z-index: 1;display:none"> 
					<iframe frameBorder='0' marginHeight='0' marginWidth='0' style='position: absolute; visibility: inherit; top: 0px; left: 0px; width: 100%; height: 100%; z-index: -1; filter: alpha(opacity = 0);'></iframe>
					</iframe>
				</div>
			<!-- <div style="display:none" class="loading"></div> -->
			<iframe scrolling="yes" frameborder="0" src="${ctx}/welcome.action"></iframe>
		 </div>
	</div>
	<!-- 主面板内容区域 end-->
</section>
<!-- 主面板区域 end-->


<footer class="footer">
  <p>©2016 技术支持：南京拓构软件有限公司  </p>
</footer>

<!-- message -->
   <div id="msg_win" style="display:none;top:230px;visibility:visible;opacity:1;z-index: 1111; ">
<div class="icos"><a id="msg_min" title="最小化" href="javascript:void 0">_</a><a id="msg_close" title="关闭" href="javascript:void 0">×</a></div>
<div id="msg_title">待处理检查 </div>
<div id="msg_content"> </div>
</div>
<!-- message -->






<%@include file="/h-ui/lib-js.jsp" %>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
<script src="${ctx}/h-ui/menu/js/menu_min.js"></script>
<script type="text/javascript">
window.ajaxLoginUrl = "${ctx}/ajaxLogin.action";
window.checkSessionUrl = "${ctx}/checkSession.action";
function load_default(){
	$("#min_title_list li").first().click();
}
function startTime() {
    var today=new Date()
	var years=today.getFullYear();
	var months=today.getMonth();
	var d=today.getDate()
	var h=today.getHours()
	var m=today.getMinutes()
	var s=today.getSeconds()
	// add a zero in front of numbers<10
	months=months+1
	months=checkTime(months)
	d=checkTime(d)
	m=checkTime(m)
	s=checkTime(s)
	var weekday=new Array(7)
	weekday[0]="星期日"
	weekday[1]="星期一"
	weekday[2]="星期二"
	weekday[3]="星期三"
	weekday[4]="星期四"
	weekday[5]="星期五"
	weekday[6]="星期六"
	var w=weekday[today.getDay()]
	document.getElementById('ShowTime').innerHTML=years+"年"+months+"月"+d+"日 "+"<br>"+w+" "+h+":"+m;
	t=setTimeout('startTime()',500)
}
function checkTime(i) {
    if (i < 10) {
        i = "0" + i
    }
    return i
}
$(function(){
    $(".menu ul li").menu();
    $('#tabdiv').tabs({
		width:$('.pageFrame').width(),
		height:$('.pageFrame').height(),
		tools:[{
			iconCls:'icon-clean',
			handler: function(){
				var selectedtab = $('#tabdiv').tabs('getSelected');
				var tabs = $('#tabdiv').tabs('tabs');
				$(tabs).each(function (i,dom){
					if($(dom).is(":hidden")&&$(dom).panel('options').title!='首页'){
						var index = $('#tabdiv').tabs('getTabIndex',dom);
						$('#tabdiv').tabs('close',index);
					}
				 });
			}
		}],
		onSelect:function(){
			close_win();
		}
	});
	$('#tabdiv').tabs({ 
		border:false, 
		onSelect:function(title){ 
			if(title != '首页' && title != '企业基本信息'  && title.indexOf("综合查询") == -1 && title.indexOf("粉尘视频") == -1)
			{
				var tab = $(this).tabs('getTab', title);  // get selected panel
				var url = $(tab.panel('options').content).attr('src');
				tab.panel('refresh');
			}
		} 
	});
});
    
    var $loading;
    //获取菜单节点数据
    function getMenuData(self,moduleCode,moduleName){
    	/* var target = self.parent();
    	//打开或创建tab
    	//Hui_admin_tab(self[0]);
    	if(target.children().size()<2){
    		$loading=$('<span><i class="layui-layer-ico16"></i>加载中</span>');
    		target.append($loading);
	        $.get("${ctx}/findModuleChildTree.action?selModule=" +moduleCode,function (result){
	        	addNode(target,result,moduleName);
	        });   
    	} 	 */
    }
//新增节点
    function addNode(target,data,parentModuleName){
    	var $ul = $("<ul></ul>");
    	$.each(data,function (index,item){
    		var $li;
   			var moduleAddr = "";
   			if(item.moduleAddr !=null && item.moduleAddr != ""){
   				var index = item.moduleAddr.indexOf("?");
   				if(index > 0){
   					moduleAddr = "${ctx}/" + item.moduleAddr +"&moduleNames="+parentModuleName+"," + item.name;
   				}else{
   					moduleAddr = "${ctx}/" + item.moduleAddr;
   				}
   			}
    		if(item.hasChild > 0){
    		  $li = $("<li class='menus'><a href='javascript:void(0)' onclick=\"getMenuData($(this),'" + item.moduleCode + "','" + parentModuleName  + "," + item.name + "')\" _href='"+ moduleAddr +","+ item.name +"' data-title='" + item.name + "' ><icon class='" + item.smallIconAddr + "'></icon> " + item.name + "<i class='Hui-iconfont Hui-iconfont-arrow2-right'></i></a></li>");
    		}else{  
    		  $li = $("<li class='item'><a href='javascript:void(0)'  onclick=\"Hui_admin_tab(this)\" _href='"+ moduleAddr  +"' data-title='" + item.name + "' ><icon class='" + item.smallIconAddr + "'></icon> " + item.name + "<i></i></a></li>");
    		}
    		$ul.append($li);
    	});
    	
    	$loading.remove();
    	target.append($ul);
    	 $(".menu ul li").menu();
    	 target.children(0).click();
    }
    
    function addTab(id,text,url){
    	document.getElementById('zdkj').style.display="none";
    	$.ajax({
    		"url": window.checkSessionUrl,
    		dataType: "json",
    		data: {
    			"currDate": (new Date()).getTime()
    		},
    		"success": function(data){
    			if(data.result){
    				$(".opened").removeClass("opened");
    				//$("#drawer").hide();
    				//$(".nav li:first").unbind("mouseenter").unbind("mouseleave");
    				
    				if(url=="")return;
    				var width = $(".pageFrame").width() -4;
    				var height = $(".pageFrame").height()-34;
    				var existname="";
    				var existid=""
    				$("div[class='panel'] iframe").each(function (i,dom){
    			    	if(text==$(dom).attr("existname")){
    			    		existname=$(dom).attr("existname");
    				    	existid=$(dom).attr("id");
    			    	}
    				});
    				url="${ctx}/"+url;
    				if(url.indexOf("?")==-1)
    					url += "?";
    				else
    				{	
    					url += "&";
    				}
    				var randomnumber=Math.floor(Math.random()*100000);
    				if(existname==""){
    					$('#tabdiv').tabs('add',{
    						id:'tab_'+id,  
    						title:text,
    						width:width,
    						height:height,
    						closable:true,
    						tools:[{   
    					        iconCls:'icon-mini-refresh',   
    					        handler:function(){ 
    					        	var tab = $('#tabdiv').tabs('getSelected');  // get selected panel
    								tab.panel('refresh');
    					        }   
    					    }],
    						content:'<iframe name="iframe_'+id+'" existname="'+text+'" id="iframe_'+id+'" width="100%" height="100%"  name="iframe_'+id+'" frameborder="0"  src="'+url+"random="+randomnumber+'&currModuleCode=' + id + '"></iframe>'  
    					});
    			 	  	}else{
    			 	  		$('#tabdiv').tabs('select',existname);
    			     			//重载要访问的地址
    			      		document.getElementById(existid).src = url + "random=" + randomnumber;
    			  		}
    			}else{
    				$.messager.alert("提示","登录超时，请重新登录","确定", function(){
    					$("#ajaxLogin").window("open");
    				});
    			}
    		
    		}
    	});    
    }

function logout() {
	layer.confirm('确认注销吗？', {icon: 3, title:'提示'}, function(index){
		parent.parent.location='<c:url value="/logout.action"/>';
	});
}
function changePwd(self){
	Hui_admin_tab(self);
}
var Message={
		set: function() {//最小化与恢复状态切换
		var set=this.minbtn.status == 1?[0,1,'block',this.char[0],'最小化']:[1,0,'none',this.char[1],'展开'];
		this.minbtn.status=set[0];
		this.win.style.borderBottomWidth=set[1];
		this.content.style.display =set[2];
		this.minbtn.innerHTML =set[3]
		this.minbtn.title = set[4];
		this.win.style.top = this.getY().top;
		},
		close: function() {//关闭
		this.win.style.display = 'none';
		window.onscroll = null;
		},
		setOpacity: function(x) {//设置透明度
		var v = x >= 100 ? '': 'Alpha(opacity=' + x + ')';
		this.win.style.visibility = x<=0?'hidden':'visible';//IE有绝对或相对定位内容不随父透明度变化的bug
		this.win.style.filter = v;
		this.win.style.opacity = x / 100;
		},
		show: function() {//渐显
		clearInterval(this.timer2);
		var me = this,fx = this.fx(0, 100, 0.1),t = 0;
		this.timer2 = setInterval(function() {
		t = fx();
		me.setOpacity(t[0]);
		if (t[1] == 0) {clearInterval(me.timer2) }
		},10);
		},
		fx: function(a, b, c) {//缓冲计算
		var cMath = Math[(a - b) > 0 ? "floor": "ceil"],c = c || 0.1;
		return function() {return [a += cMath((b - a) * c), a - b]}
		},
		getY: function() {//计算移动坐标
		var d = document,b = document.body, e = document.documentElement;
		var s = Math.max(b.scrollTop, e.scrollTop);
		var h = /BackCompat/i.test(document.compatMode)?b.clientHeight:e.clientHeight;
		var h2 = this.win.offsetHeight;
		return {foot: s + h + h2 + 2+'px',top: s + h - h2 - 2+'px'}
		},
		moveTo: function(y) {//移动动画
		clearInterval(this.timer);
		var me = this,a = parseInt(this.win.style.top)||0;
		var fx = this.fx(a, parseInt(y));
		var t = 0 ;
		this.timer = setInterval(function() {
		t = fx();
		me.win.style.top = t[0]+'px';
		if (t[1] == 0) {
		clearInterval(me.timer);
		me.bind();
		}
		},10);
		},
		bind:function (){//绑定窗口滚动条与大小变化事件
		var me=this,st,rt;
		window.onscroll = function() {
		clearTimeout(st);
		clearTimeout(me.timer2);
		me.setOpacity(0);
		st = setTimeout(function() {
		me.win.style.top = me.getY().top;
		me.show();
		},600);
		};
		window.onresize = function (){
		clearTimeout(rt);
		rt = setTimeout(function() {me.win.style.top = me.getY().top},100);
		}
		},
		init: function() {//创建HTML
		function $(id) {return document.getElementById(id)};
		this.win=$('msg_win');
		var set={minbtn: 'msg_min',closebtn: 'msg_close',title: 'msg_title',content: 'msg_content'};
		for (var Id in set) {this[Id] = $(set[Id])};
		var me = this;
		this.minbtn.onclick = function() {me.set();this.blur()};
		this.closebtn.onclick = function() {me.close()};
		this.char=navigator.userAgent.toLowerCase().indexOf('firefox')+1?['_','::','×']:['0','2','r'];//FF不支持webdings字体
		this.minbtn.innerHTML=this.char[0];
		this.closebtn.innerHTML=this.char[2];
		setTimeout(function() {//初始化最先位置
		me.win.style.display = 'none';
		me.win.style.top = me.getY().foot;
		top.$(document).scrollTop(1);
		 	var d = top.$(document)[0],b = document.body, e = document.documentElement;
		var h = /BackCompat/i.test(document.compatMode)?b.clientHeight:e.clientHeight;   
		var h2 = 170;
		 me.moveTo(  h - h2 - 2+'px');
		},0);
		return this;
		}
	};
		function showMessage(){
			var $ul = $("<ul></ul>")
			$.ajax({
				url : "${ctx}/jsp/notify/notifyQuery.action",
				type : "post",
				data : {
				"length":6
				},
		   	dataType : "json",    
				success : function(result) {
					if(result.recordsTotal==0){
						return ;
					}
						 $.each(result.data,function (index,item){
							 var $li = $("<li><a   onclick=\"view('" + item.linkId + "')\" href='javascript:void 0'>" + item.notifyTitle + "</a></li>");
							 $ul.append($li);
						 });
						 $ul.append($('<a href="javascript:;"onclick="Hui_admin_tab(this)" _href="${ctx}/jsp/notify/notifyList.action?moduleNames=消息" data-title="消息" title="消息">查看更多</a>'));
						 var $message = $("#msg_content");
						 $message.html("");
						 $message.append($ul);
						 $("#msg_win").fadeIn("slow").delay(30*1000).fadeOut("slow");
							 
				} 
			}); 
		}
		  
  function view(link_Id){
	  var dt=new Date();
	  top.layer_show("查看安全检查","${ctx}/jsp/distributeItem/distributeItemView.action?safeInspectDistribute.id="+link_Id+"&dt="+dt.getTime()); 
	  }  
  function clickModule(obj,index){
	   /* if(index == 1){
		   $("#indexUl li:nth-child(2) a").click();
		   $("#indexUl li:nth-child(2)").children("ul").children("li").first().click();
	   } */
	   var href = $(obj).attr("_href");
	   if("/fsajj/"==href){
		   return;
	   }
	   Hui_admin_tab(obj);
	}
 function close_win(windowId){
		if(!windowId || windowId=="" || windowId == "null"){
			$("#win").attr("src", "<c:url value='/blank.html'/>");
			$("#newWindow").window("close", true);
		}else{
			$("#"+windowId+"_frm").attr("src", "<c:url value='/blank.html'/>");
			$("#"+windowId).window("close", true);
			$("#"+windowId).remove();
		}
	} 
</script> 
</body>
</html>
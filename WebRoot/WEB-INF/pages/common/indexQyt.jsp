<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/jsLib.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/webResources/style/css/default/login.css" />
		<title>智慧安监平台</title>
		<script>
		window.ajaxLoginUrl = "${ctx}/ajaxLogin.action";
		window.checkSessionUrl = "${ctx}/checkSession.action";
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
			function logout() {
				document.getElementById('zdkj').style.display="";
				$.messager.confirm("注销", "确认注销吗？", function(result){
					if(result){
						parent.parent.location='<c:url value="/logout.action"/>';
					}
					else
					{
						document.getElementById('zdkj').style.display="none";
					}
				});
				$('.panel-tool-close').hide();
		    }
			$(document).ready(function() {
				//判断初始密码为99999的情况，提示修改(在system.properties中设置开启状态下)
				var isChangeInitPassword = "<cus:sysProp propName="isChangeInitPassword" />";
				if("true"==isChangeInitPassword){
					var password = "${sessionScope.noMd5Password}";
					if(password.length==32){
						$("#exit").hide();
						$("#funMap").hide();
						
					}
					var ls = 0;
 					if(password.match(/([a-z])+/)){
					
     					ls++;
  					}
 					if(password.match(/([0-9])+/)){

      			 		ls++;  
 					}
 					if(password.match(/([A-Z])+/)){

        				ls++;
  					}
  					if(password.match(/[^a-zA-Z0-9]+/)){

        				ls++;
    					}
					if(password.length>30){

        				ls++;
    					}
    				if(ls < 3){
						$.messager.alert('','登录密码必须是大写字母、小写字母、数字和特殊字符中任意三个组合，请修改！', 'warning',function(){
							//跳转至修改密码页面
			    			//addTab('password','修改密码','/personal/changePassword.jsp');
							
							//弹出修改密码的madol
							
							$("#win").attr("src", "${ctx}/personal/changePasswords.jsp");
							$("#newWindow").window({
								title: "修改密码",
								shadow: true,
							    modal: true,
							    minimizable: false,
							    maximizable: false,
							    collapsible: false,
							    closable: false
							});
							$("#newWindow").window("open")
						});					
					}				
				} 

				$('#rollBtn').click(hideLeftMenu);
				//$('#headBtn').click(hideHeadMenu);
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
				addBubble();
				load_default();
			    $(".sub_01").bind("click",
					     function(event) {
					if(!$(event.target).parents(".sub_00").hasClass("opened")){
						$(".opened").removeClass("opened");
						showZd();
					}
					else
					{
						document.getElementById('zdkj').style.display="none";
					}
					$(event.target).parents(".sub_00").toggleClass("opened");
				});
			
				indexbody=$('body');
				$("body").mousedown(function(e){
					if($(e.target).hasClass("panel window")||$(e.target).hasClass("panel-title"))
						addWaterfall();
				});
				$("body").mouseup(function(){
					removeWaterfall();
				});
				function showZd()
				{
					document.getElementById('zdkj').style.display="";
				}
				function addWaterfall(){
					$("<div id='waterfall'></div>").appendTo(indexbody);
					$("#waterfall").css( {   
						"height" : "100%",   
						"width" : "100%",   
						"filter" : "alpha(opacity = 0)",   
						"-moz-opacity" : "0.1",   
						"opacity" : "0",   
						"background-color" : "#fff",   
						"position" : "absolute",   
						"left" : "0px",   
						"top" : "0px",
						"z-index":8000
					});
				}
				function removeWaterfall(){
					$("#waterfall").remove();
				}
				$(".page_left_bottom .rolldown").click(function(){
					var top_offset = $(".nav").offset().top;
					var top_offset2 = $(".nav li.sub_00:last").offset().top;
					var top_offset3 = $(".page_left_bottom").offset().top;
					if(top_offset2+45>top_offset3)
					$(".nav").offset({top:top_offset-45});
				});
				$(".page_left_bottom .rollup").click(function(){
					var top_offset = $(".nav").offset().top;
					if(top_offset<64)
					$(".nav").offset({top:top_offset+45});
				});
				$(".sub_02 .rolldown").click(function(){
					var top_offset = $(this).parent().prev().offset().top;
					var top_offset2 =$(this).parent().prev().find(".sub_03:last").offset().top;
					var top_offset3 = $(this).parent().offset().top;
					if(top_offset2+45>top_offset3)
					$(this).parent().prev().offset({top:top_offset-45});
				});
				$(".sub_02 .rollup").click(function(){
					var top_offset = $(this).parent().prev().offset().top;
					if(top_offset<64)
					$(this).parent().prev().offset({top:top_offset+45});
				});
				
			 });
			function getCurrTab(){
				var tab = $('#tabdiv').tabs('getSelected');
				return tab;
			}
			function tabdivResize(){	
			 	var width=$('.pageFrame').width();
				var height=$('.pageFrame').height();
				var pp = $('#tabdiv').tabs('getSelected');
				var tabtitle = pp.panel('options').title;
				$('#tabdiv').tabs({
					width:width,
					height:height});
				$('#tabdiv').tabs("select",tabtitle);
			 }
			function load_default(){
			    addTab("tab_index","首页","<cus:sysProp propName='indexPage' />");
			}
			
			function addTabs(ids,texts,urls)
			{
				var id = ids.split(",");
				var text = texts.split(",");
				var url = urls.split(",");
				for(var i=0;i<id.length;i++)
				{
					addTab(id[i],text[i],url[i]);
				}
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
			function hideLeftMenu(){
				if($("#rollBtn").hasClass("roll_01")){
					$(".page_left").hide();
					$("#rollBtn").removeClass("roll_01");
					$("#rollBtn").addClass("roll_02");
					$("#page_right").removeClass("page_right");
					$("#drawer_content").removeClass("drawer_content");
					$("#page_right").addClass("page_right_shrinked");
					$("#drawer_content").addClass("drawer_content_shrinked");
				}else{
					$(".page_left").show();
					$("#rollBtn").addClass("roll_01");
					$("#rollBtn").removeClass("roll_02");
					$("#page_right").addClass("page_right");
					$("#drawer_content").addClass("drawer_content");
					
					$("#page_right").removeClass("page_right_shrinked");
					$("#drawer_content").removeClass("drawer_content_shrinked");
					
				}
				tabdivResize();
			}
			$(window).resize(function() {
				tabdivResize();
			});
			function hideHeadMenu(){
				if($("#headBtn").hasClass("header_hover")){
					document.getElementById('zdkj').style.display="none";
					$("#headBtn").removeClass("header_hover");
				}else{
					document.getElementById('zdkj').style.display="";
					$("#headBtn").addClass("header_hover");
				}
			}
			function addBubble(){
				$('.icon-clean').attr("title","关闭其它选项")
				$('.icon-clean').poshytip({
					alignTo:'target',
					alignX: 'left',
					alignY: 'center',
					offsetX: 30,
					showTimeout: 1,
					fade: false,
					slide: false
				});
			}
			
			function showGndt()
			{
				document.getElementById('zdkj').style.display="";
				drawer.style.display='block';
			}
			
			function closeGndt()
			{
				document.getElementById('zdkj').style.display="none";
				drawer.style.display='none';
			}
		</script>
	</head>
	<body onLoad="startTime()">
		<!--收缩按钮-->
		<a href="#" id="rollBtn" class="roll_01"></a>
		<!--全局左边-->
		<div class="page_left">

			<!--用户中心-->
			<div class="header" id="headBtn" title="快捷菜单">
				<div class="header_ico"></div>
				<div class="header_name" style="text-overflow:ellipsis; white-space: nowrap; overflow: hidden;" title="${curr_user.displayName}">
					${curr_user.displayName}
				</div>
				<div class="header_center">
					<div class="header_center_area">
						<div class="header_caption header_caption_01">
							${curr_user.displayName}
						</div>
						<div class="header_content">
							<a href="javaScript:logout()" title="退出"><img
									src="<c:url value='/webResources/style/images/default/header_center_icon_01.png'/>"
									width="48" height="48" />退出</a>
							<a
								href="javaScript:addTab('password','修改密码','/personal/changePassword.jsp')" title="修改密码"><img
									src="<c:url value='/webResources/style/images/default/header_center_icon_02.png'/>"
									width="48" height="48" />修改密码</a>
							<a href="/upload/wjxz/用户手册.zip"  title="帮助"><img
									src="<c:url value='/webResources/style/images/default/header_center_icon_03.png'/>"
									width="48" height="48" />帮助</a>
						</div>
						<div class="header_content_blank">
						</div>
						<div class="header_caption header_caption_02">
							快捷方式
						</div>
						<div class="header_content">
							<c:forEach items="${startList}" var="quicklyStart">
								<a
									href="javaScript:addTab('${quicklyStart.module.id}','${quicklyStart.module.moduleName}','${quicklyStart.module.moduleAddr}')"><img
										src="<c:url value='/webResources/style/images/default/header_center_icon_04.png'/>"
										width="48" height="48" />${quicklyStart.module.moduleName}</a>
							</c:forEach>
						</div>
						<div class="header_content_blank">
						</div>
					</div>
				</div>
			</div>

			<!--导航-->
			<ul class="nav">
				<li class="sub_00">
					<a style="display:block;line-height:45px;display:block;text-indent:5px;height:45px;font-size:12px;color:#000000;cursor:pointer;font-weight:bold;position:relative" href="#" onclick="load_default()"><b><i>1</i>
					</b><span>首页</span> </a>
				</li>
				<c:forEach items="${items}" var="moduleitem" varStatus="status1">
					<li class="sub_00">
						<c:if test="${fn:length(moduleitem.children)>0}">
							<a class="sub_01" href="#"><b><i>${status1.index+2}</i> </b><span>${moduleitem.text}</span>
								<h1></h1> </a>
							<ul class="sub_02">
								<div class="level_t_l2">
									<b>${status1.index+2}</b>${moduleitem.text}
								</div>
								<div class="level_m">
									<c:forEach items="${moduleitem.children}" var="child"
										varStatus="status2">
										<li>
											<c:if test="${fn:length(child.children)>0}">
												<a class="sub_03" href="javaScript:addTabs('${child.ids}','${child.texts}','${child.attributess}');"><b><i>${status2.index+1}</i>
												</b><span>${child.text}</span>
													 </a>

												<ul>
													<div class="level_t_l3">
														<b>${status2.index+1}</b>${child.text}
													</div>
													<div class="level_m">
														<c:forEach items="${child.children}" var="child2"
															varStatus="status3">
															<li>
																<a
																	href="javaScript:addTab('${child2.id}','${child2.text}','${child2.attributes}')"><b><i>${status3.index+1}</i>
																</b>${child2.text}</a>
															</li>
														</c:forEach>
													</div>
												</ul>
											</c:if>
											<c:if test="${fn:length(child.children)==0}">
												<a class="sub_03"
													href="javaScript:addTab('${child.id}','${child.text}','${child.attributes}')"><b><i>${status2.index+1}</i>
												</b><span>${child.text}</span> </a>
											</c:if>
										</li>
									</c:forEach>
								</div>
								<div class="level_b">
									<a href="#" class="rollup"></a>
									<a href="#" class="rolldown"></a>
								</div>
							</ul>
						</c:if>
						<c:if test="${fn:length(moduleitem.children)==0}">
							<a class="sub_01"
								href="javaScript:addTab('${moduleitem.id}','${moduleitem.text}','${moduleitem.attributes}')"><b><i>${status1.index+2}</i>
							</b><span>${moduleitem.text}</span> </a>
						</c:if>
					</li>
				</c:forEach>
			</ul>
			<div class="page_left_bottom">
				<div class="level_c">
					<a href="#" class="rollup"></a><a href="#" class="rolldown"></a>
				</div>
			</div>
		</div>




		<!--全局右边-->
		<div class="page_right" id="page_right">
			<div class="top">
				<div class="logo">
				</div>
				<div class="user_info">
					<div class="time" id="ShowTime">
						2013年5月31日
						<br>
						下午5：31
					</div>
					<div class="drawer"  id="funMap">
						<a href="#" onClick="showGndt();" title="功能地图"></a>
					</div>
					<div class="userhelp">
						<a href="/upload/wjxz/用户手册.zip" title="使用手册"></a>
					</div>
					

					<div class="exit" id="exit">
						<a href="#" onclick="logout()" title="注销"></a>
					</div>
					
				</div>
			</div>
			<div class="pageFrame">
				<div id="tabdiv">
				</div>
				<div id="zdkj" style="position:absolute;top:0;left:0;right:0;bottom:0;z-index: 1;display:none"> 
					<iframe frameBorder='0' marginHeight='0' marginWidth='0' style='position: absolute; visibility: inherit; top: 0px; left: 0px; width: 100%; height: 100%; z-index: -1; filter: alpha(opacity = 0);'></iframe>
					</iframe>
				</div> 
			</div>
		</div>
		<div id="drawer" style="display: none">
			<div class="drawer_content" id="drawer_content">
				<a href="javascript:;" onClick="closeGndt();"
					class="drawer_close" title="回到登录页面。"></a>
				<div class="drawer_frame">
					<c:forEach items="${items}" var="moduleitem" varStatus="status1">
						<div class="drawer_sub">
							<div class="drawer_caption">
								<c:if test="${fn:length(moduleitem.children)>0}">
									<a href="#">${moduleitem.text}</a>
								</c:if>
								<c:if test="${fn:length(moduleitem.children)==0}">
									<a
										href="javaScript:addTab('${moduleitem.id}','${moduleitem.text}','${moduleitem.attributes}')">
										${moduleitem.text} </a>
								</c:if>
							</div>
							<div class="drawer_map_sub">
								<ul>
									<c:forEach items="${moduleitem.children}" var="child"
										varStatus="status2">
										<li>
											<c:if test="${fn:length(child.children)>0}">
												<a href="#">${child.text}<b></b> </a>
												<div>
													<c:forEach items="${child.children}" var="child2"
														varStatus="status3">
														<a
															href="javaScript:addTab('${child2.id}','${child2.text}','${child2.attributes}')"><i></i>${child2.text}</a>
													</c:forEach>
												</div>
											</c:if>
											<c:if test="${fn:length(child.children)==0}">
												<a
													href="javaScript:addTab('${child.id}','${child.text}','${child.attributes}')">${child.text}</a>
											</c:if>
										</li>
									</c:forEach>

								</ul>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>


		<div id="newWindow" class="easyui-window" closed="true"
			style="width: 550px; height: 350px">
			<iframe id="win" scrolling="no" style="width: 100%; height: 100%"></iframe>
		</div>
		<div id="ajaxLogin" class="easyui-window" title="登录" closed="true"  data-options="iconCls:'icon-save'" style="width:300px;height:320px;padding:0px;">
        	
        	<div class="login_frame">
					<div class="login_frame_t"></div>
					<div class="login_frame_m">
						<div class="sub_name">
							<img src="${ctx}/webResources/style/images/default/login_icon_01.png" width="16" height="16" />用户名
						</div>
						<div class="sub_input">
							<input name="loginId" id="loginId" />
						</div>
						<div class="sub_name">
							<img src="${ctx}/webResources/style/images/default/login_icon_02.png" width="16" height="16" />密码
						</div>
						<div class="sub_input">
							<input name="password" id="password" type="password" onkeydown="if(event.keyCode==13){doAjaxLogin();}" />
						</div>
					</div>
					<div class="login_frame_b">
						<a href="#" class="login_btn" onclick="doAjaxLogin()"></a>
					</div>
				</div>
    	</div>
	</body>
</html>
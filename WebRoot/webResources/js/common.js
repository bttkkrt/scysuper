//========================
//
//========================
$(document).ready(function() {
	$(document).bind("click",function(e){
		var target  = $(e.target);
		if(target.parents(".nav").size()==0){
			window.top.$(".opened").removeClass("opened");
		}
	})
});
function CheckAll(parentCheckBox,checkboxname)
{
	var participants = document.getElementsByTagName("input");
    //alert(parentCheckBox);
	for ( var i=0; i < participants.length; i++ ) 
	{
		if(participants[i].name.indexOf(checkboxname)!=-1 && !participants[i].disabled)
		{				
			var participant = participants[i];
			if ( participant != null ) {
				participant.checked = parentCheckBox.checked;
			}
		}
	}
}

function OpenDialog(Url,Width,Height)
{
	var strFeatures="dialogWidth="+ Width +"px;dialogHeight="+ Height +"px;center=yes;middle=yes ;help=no;status=no";
	var today=new Date();
	if(Url.indexOf("?")!=-1)
	{
		Url+="&Time="+today.getMinutes()+"_"+today.getMilliseconds();
	}
	else
	{
		Url+="?Time="+today.getMinutes()+"_"+today.getMilliseconds();
	}
	var WindowResult= window.showModalDialog(Url,'',strFeatures); 
	return WindowResult;
}

function getCenterLocation(width, height){
	var location = {};
	var ah = screen.availHeight;
	var aw = screen.availWidth;
	var xc = (aw - width) / 2;
	var yc = (ah - height) / 2;
	location.top = yc;
	location.left = xc;
	return location;
}

function OpenDialog_Param(Url,Param,Width,Height)
{
	var strFeatures="dialogWidth="+ Width +"px;dialogHeight="+ Height +"px;center=yes;middle=yes ;help=no;status=no";
	var today=new Date();
	if(Url.indexOf("?")!=-1)
	{
		Url+="&Time="+today.getMinutes()+"_"+today.getMilliseconds();
	}
	else
	{
		Url+="?Time="+today.getMinutes()+"_"+today.getMilliseconds();
	}
	var WindowResult= window.showModalDialog(Url,Param,strFeatures); 
	return WindowResult;
}
function remove_all_option(object){
	var length = object.options.length;
	for(var i=length-1;i>=0;i--){
		object.options.remove(i);
	}
}

function add_option(sel,txt,val,position){
	if(val==""||txt=="")
		return false;
	for(i=0;i<sel.options.length;i++){
		if(val==sel.options[i].value){
			alert("This option "+txt+" exists!");
			return false;
		}
	}
	
	if(position==null||position>sel.options.length){
		var option = new Option(txt,val);
		sel.options[sel.options.length] = option;
	}
	else{
		var option = document.createElement("option"); 
		option.value = val; 
		option.innerText = txt; 
		sel.insertBefore(option, sel.options[position-1]);		
	}
}

function del_option(sel){
	sel.options.remove(sel.selectedIndex);
}

function popupCenter(url, name, width, height, menubar, toolbar, location, resize, scroll,status) {
    var str = "height=" + height + ",innerHeight=" + height;
    str += ",width=" + width + ",innerWidth=" + width;
    if (window.screen) {
		var ah = screen.availHeight - 30;
		var aw = screen.availWidth - 10;
		var xc = (aw - width) / 2;
		var yc = (ah - height) / 2;
		str += ",left=" + xc + ",screenX=" + xc;
		str += ",top=" + yc + ",screenY=" + yc;
    }
    str+= ",menubar="+menubar+",toolbar="+toolbar+",location="+location+",resizable=no,scrollbars="+scroll+",status="+status;
    var win= window.open(url, name, str);

    try {
        win.focus();
    }
    	catch (ex) {
    }
    return win;
}

function createWindow(divName,title,left,top,width,height,href){
	var top = window;
	var i = 0;
	while($(top.document).find("#newWindow").length==0){
		top = top.parent;
		i++;
		if(i>6){
			//alert("break");
			break;
		}
	}
	if($(top.document).find("#"+divName).length==0){
		var div = "<div id='"+divName+"' class='easyui-window' closed='true'>"+
		"<iframe id='"+divName+"_frm' width='"+width+"px' scrolling='no' frameborder='0' style='margin-top: -6px !important;margin-left: -6px !important;width: "+width+"px; height: "+height+"px;' onload='autoAdjust(\""+title+"\",\""+divName+"\", \""+divName+"_frm\")'></iframe>"+
	    "</div>";
	    $(top.document.body).append(div);
	}
	top.$("#"+divName).window({
		title: 			title,
		left: 			left,
		top:			top,
		width:			width,
		height:			height,
		minimizable: false
	});
	top.$("#"+divName+"_frm").attr("src",href);
	top.$("#"+divName).window('refresh');
	top.$("#"+divName).window('open');
}


function createSimpleWindow(divName, title, href, width, height){
    /**
	var ah = screen.availHeight - 30;
	var aw = screen.availWidth - 10;
	var left = (aw - width) / 2;
	var top1 = (ah - height) / 2;
	*/
	//add by wuhuaqing
	layer.open({
		  type: 2,
		  title: title,
		  maxmin: true,
		  shadeClose: true, //点击遮罩关闭层
		  area : [width, height],
		  content: href,
		  end: function () {
              
          }
	});
	//end
	/*var location = getCenterLocation(width, height);
	var left = location.left;
	var top1 = location.top;
	
	var top = window;
	var i = 0;
	while($(top.document).find("#newWindow").length==0){
		top = top.parent;
		i++;
		if(i>6){
			//alert("break");
			break;
		}
	}
	if($(top.document).find("#"+divName).length==0){
		var div = "<div id='"+divName+"' class='easyui-window' closed='true'>";
		if(!width || !height)
		    div += "<iframe id='"+divName+"_frm' scrolling='no' frameborder='0' style='width: 100%; height: 100%;' onload='autoAdjust(\""+title+"\", \""+divName+"\", \""+divName+"_frm\")'></iframe>";
	    else
	        div += "<iframe id='"+divName+"_frm' scrolling='no' frameborder='0' style='width: 100%; height: 100%;' ></iframe>"
	    div += "</div>";
	    $(top.document.body).append(div);
	}
	if(!width || !height){
	    
	}else{
	    top.$("#"+divName).window({
		    title: 			title,
		    //left: 			left,
		    //top:			top1,
		    width:			width,
		    height:			height,
		    minimizable: false,
		    modal:true,
		    onBeforeClose:function(){
		        
			    top.close_win(divName);
		    }
	    });
	}
	
	top.$("#"+divName+"_frm").attr("src",href);
	top.$("#"+divName).window('refresh');
	if(width && height)
	    top.$("#"+divName).window('open');*/
}

function createSimpleWindowOld(divName, title, href, width, height){
	var location = getCenterLocation(width, height);
	var left = location.left;
	var top1 = location.top;
	
	var top = window;
	var i = 0;
	while($(top.document).find("#newWindow").length==0){
		top = top.parent;
		i++;
		if(i>6){
			//alert("break");
			break;
		}
	}
	if($(top.document).find("#"+divName).length==0){
		var div = "<div id='"+divName+"' class='easyui-window' closed='true'>";
		if(!width || !height)
		    div += "<iframe id='"+divName+"_frm' scrolling='no' frameborder='0' style='width: 100%; height: 100%;' onload='autoAdjust(\""+title+"\", \""+divName+"\", \""+divName+"_frm\")'></iframe>";
	    else
	        div += "<iframe id='"+divName+"_frm' scrolling='no' frameborder='0' style='width: 100%; height: 100%;' ></iframe>"
	    div += "</div>";
	    $(top.document.body).append(div);
	}
	if(!width || !height){
	    
	}else{
	    top.$("#"+divName).window({
		    title: 			title,
		    //left: 			left,
		    //top:			top1,
		    width:			width,
		    height:			height,
		    minimizable: false,
		    modal:true,
		    onBeforeClose:function(){
		        
			    top.close_win(divName);
		    }
	    });
	}
	
	top.$("#"+divName+"_frm").attr("src",href);
	top.$("#"+divName).window('refresh');
	if(width && height)
	    top.$("#"+divName).window('open');
}

/************
 add by YuWeitao 2011-06-20
 ***********/
function openWindow(divName, title, left, top, width, height, href, resizable, 
					collapsible, maximizable, minimizable, closable,iframeName){
	if(iframeName!=null){
		$('#'+divName).window({
			title: 			title,
			left: 			left,
			top:			top,
			width:			width,
			height:			height,
			//href:			href,
			resizable:		true,
			collapsible:	collapsible,
			maximizable:	maximizable,
			minimizable:	minimizable,
			closable:		closable
		});
		$("#"+iframeName).attr("src",href);
	}else{
		$('#'+divName).window({
			title: 			title,
			left: 			left,
			top:			top,
			width:			width,
			height:			height,
			href:			href,
			resizable:		true,
			collapsible:	collapsible,
			maximizable:	maximizable,
			minimizable:	minimizable,
			closable:		closable
		});
	}
	$('#'+divName).window('refresh');
	$('#'+divName).window('open');
}

function openparentWindow(divName, title, left, top, width, height, href, resizable, 
		collapsible, maximizable, minimizable, closable,iframeName){
	createSimpleWindow("win_agencyInfo","",href,900,600);
	/*var divDom=$("#"+divName);
	if(iframeName!=null)
   		var iframeDom=$("#"+iframeName);
	var divParent=parent;
	var i=0;
	while(divDom.size()==0&&i<=5){
		divDom=$("#"+divName,divParent.document);
		if(iframeName!=null)
			iframeDom=$("#"+iframeName,divParent.document);
		divParent=divParent.parent;
		i=i+1;
	}
    if(iframeName!=null){
    	divParent.$("#"+iframeName).attr("src","");
    	divParent.$("#"+divName).window({
    		title:title,
    		left:left,
    		top:top,
    		width:width,
    		height:height,
			resizable:true,
			onBeforeClose:function(){
			    divParent.close_win(divName);
		    }
    	});
    	divParent.$("#"+iframeName).attr("src",href);
    }else{
    	divParent.$("#"+divName).window({
    		title:title,
    		left:left,
    		top:top,
    		width:width,
    		height:height,
    	    resizable:true
    	});
    	divParent.$("#"+divName).window('refresh');
    }
	divParent.$("#"+divName).window('open');*/
}
  
  function getPageSize(){
    var xScroll, yScroll;
    if (window.innerHeight && window.scrollMaxY) { 
      xScroll = document.body.scrollWidth;
      yScroll = window.innerHeight + window.scrollMaxY;
    } else if (
      document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
      xScroll = document.body.scrollWidth;
      yScroll = document.body.scrollHeight;
    } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
      xScroll = document.body.offsetWidth;
      yScroll = document.body.offsetHeight;
    }
    
    var windowWidth, windowHeight;
    if (self.innerHeight) { // all except Explorer
      windowWidth = self.innerWidth;
      windowHeight = self.innerHeight;
    } else if (
      document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
      windowWidth = document.documentElement.clientWidth;
      windowHeight = document.documentElement.clientHeight;
    } else if (document.body) { // other Explorers
      windowWidth = document.body.clientWidth;
      windowHeight = document.body.clientHeight;
    } 
    
    // for small pages with total height less then height of the viewport
    if(yScroll < windowHeight){
      pageHeight = windowHeight;
    } else { 
      pageHeight = yScroll;
    }
    
    // for small pages with total width less then width of the viewport
    if(xScroll < windowWidth){ 
      pageWidth = windowWidth;
    } else {
      pageWidth = xScroll;
    }
    
    arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight);
    return arrayPageSize;
  }
  
  function locate_center(obj){    
      var arrayPageSize = getPageSize();
      var objleft, objtop, left, top; 
      objleft = obj.style.width.substring(0,obj.style.width.length-2);
      objtop = obj.style.height.substring(0,obj.style.height.length-2);
      if(objleft<arrayPageSize[0])
        left = (arrayPageSize[0]-objleft)/2;
      if(objtop<arrayPageSize[1])
        top = (arrayPageSize[1]-objtop)/2;
      obj.style.left = left;
	  obj.style.top = top;	  
  }
  
  function formatDate(v){  
	  if(typeof v == 'string') v = parseDate(v);  
	  if(v instanceof Date){  
	    var y = v.getFullYear();  
	    var m = v.getMonth() + 1;  
	    var d = v.getDate();  
	    var h = v.getHours();  
	    var i = v.getMinutes();  
	    var s = v.getSeconds();  
	    var ms = v.getMilliseconds();     
	    if(ms>0) return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s + '.' + ms;  
	    if(h>0 || i>0 || s>0) return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s;  
	    return y + '-' + m + '-' + d;  
	  }  
	  return '';  
	}  
  
  function getAppUrl(){
	  var url = window.location.href;
	  var port = window.location.port;
	  var pos = url.indexOf(port);
	  var server = url.substr(0,parseInt(pos+port.length+1));
	  var url1 = url.substr(parseInt(pos+port.length)+1,url.length);
	  pos = url1.indexOf("\/");
	  url1 = url1.substr(0,pos);
	  url = server + url1;
      return url;
  }
  function OpenWin(Url,Width,Height){
  var loc_x=(screen.availWidth-parseInt(Width))/2;
  var loc_y=(screen.availHeight-parseInt(Height))/2;
  
	var Feature='Width='+ Width +',Height='+ Height +',Left='+ loc_x +',Top='+ loc_y +',help:no,status:no,scrollbars=yes,resizable=yes';
	var win=window.open(Url,'',Feature);
	win.focus();
}


//é¡µé¢ç­å¾çjs
var t_id;
var pos = 0;
var dir = 2;
var len = 0;
function animate() {
var elem = document.getElementById('progress');
if(elem != null) {
if (pos == 0) len += dir;
if (len > 32 || pos > 99) pos += dir;
if (pos > 99) len -= dir;
if (pos > 99 && len == 0) pos = 0;
elem.style.left = pos;
elem.style.width = len;
}
}
function remove_loading() {
this.clearInterval(t_id);
var targelem = document.getElementById('loader_container');
targelem.style.display = 'none';
targelem.style.visibility = 'hidden';
} 

function clear_form(ff){
	if(ff){
		var elements = ff.elements;
	    for(i=0;i<elements.length;i++){
	        var element = elements[i];
	        if(element.className=="pagination-num")
	        	continue;
	        if(element.type=="text"){
	            element.value = "";
	        }else if(element.type=="radio" || element.type=="checkbox"){
	        	element.checked = false;
	        }else if(element.options!=null){
	        	element.options[0].selected  = true;
	        }
	    }
	    $("#areaId").val("");
	}else{
		var inputs = $(".submitdata input");
		var length = inputs.length;	
		for(var i = 0; i <length; i++){
			var element = inputs[i];
			if(element.type=="text"){
	            element.value = "";
	        }else if(element.type=="radio" || element.type=="checkbox"){
	        	element.checked = false;
	        }
		}
		var selects = $(".submitdata select");
		length = selects.length;	
		for(var i = 0; i <length; i++){
			var element = selects[i];
			if(!element.hidden){
				element.options[0].selected  = true;
			}
		}
		$("#areaId").val("");
	}   
}
function loading_wait(){
	var o = document.getElementById('loader_wait');
	o.innerHTML="<div id='loader_container'><div id='loader'><div align='center'>é¡µé¢æ­£å¨å è½½ä¸­ ...è¯·ç¨å</div><div id='loader_bg'><div id='progress'> </div></div></div></div>";
	t_id = setInterval(animate,20)
}

var target;
var autoSubmit = false;
function   showTable()   
{ 
	var div = document.all.mydate;
	if(div!=null)
		return false;
	div = document.createElement("div");
	div.id = "mydate";
	div.style.display = "";
	div.style.position = "absolute";
	div.style.zIndex = "20";
	var html = "";
html += ("	 <table   width=200 style='font-size:12px;font-face:Arial'  border='0'   cellspacing='0'   style='border:1px   solid   #0066FF;   background-color:#EDF2FC;filter:\"progid:DXImageTransform.Microsoft.Shadow(direction=135,color=#999999,strength=3)\"'>");   
html += ("	 <tr>");   
html += ("	 <td   colspan='3'   align='center'     style='border-bottom:1px   solid   #0066FF;   background-color:#C7D8FA   ;font-family:Verdana;   font-size:12px'>");   
html += ("	 <input   type='button'   name='minus'   value='   -   '   style='height:18'     onClick='minus()'>   <input   type='text'   name='selectYear'   size='4'   value=''   style='height=18'>   <input   type='button'   name='add'   value='   +   '   style='height:18'   onClick='add()'>");   
html += ("	 </td>");   
html += ("	 <td   style='border-bottom:1px   solid   #0066FF;   background-color:#C7D8FA;   font-weight:bold;   font-family:Wingdings   2,Wingdings,Webdings;   font-size:16px;   padding-top:2px;   color:#4477FF;   cursor:hand'   align='center'   title='å³é­'   onClick='javascript:divClose()'>");   
html += ("	 <img border='0' src='/images/close.gif'>");   
html += ("	 </td>");   
html += ("	 </tr>");   
html += ("	 <tr>");   
html += ("	 <td   align='center'   onClick=\"setYear('01')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"     class=\"month\">1æ</td>");   
html += ("	 <td   align='center'   onClick=\"setYear('02')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"     class=\"month\">2æ</td>");   
html += ("	 <td   align='center'   onClick=\"setYear('03')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"     onmouseout=\"this.style.backgroundColor='#EDF2FC'\"     class=\"month\">3æ</td>");   
html += ("	 <td   align='center'   onClick=\"setYear('04')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"     class=\"month\">4æ</td>");   
html += ("	 </tr>");   
html += ("	 <tr>");   
html += ("	 <td   align='center'   onClick=\"setYear('05')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"   class=\"month\">5æ</td>");   
html += ("	 <td   align='center'   onClick=\"setYear('06')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"     class=\"month\">6æ</td>");   
html += ("	 <td   align='center'   onClick=\"setYear('07')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"   class=\"month\">7æ</td>");   
html += ("	 <td   align='center'   onClick=\"setYear('08')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"   class=\"month\">8æ</td>");   
html += ("	 </tr>");   
html += ("	 <tr>");   
html += ("	 <td   align='center'   onClick=\"setYear('09')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"   class=\"month\">9æ</td>");   
html += ("	 <td   align='center'   onClick=\"setYear('10')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"   class=\"month\">10æ</td>");   
html += ("	 <td   align='center'   onClick=\"setYear('11')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"   class=\"month\">11æ</td>");   
html += ("	 <td   align='center'   onClick=\"setYear('12')\"   onmouseover=\"this.style.backgroundColor='#4A83E1'\"   onmouseout=\"this.style.backgroundColor='#EDF2FC'\"   class=\"month\">12æ</td>");   
html += ("	 </tr>");   
html += ("	 </table>");   
div.innerHTML = html; 
document.body.appendChild(div);
}   
function   show_month(obj,flag)   
{   
	target = obj;
showTable();   
var minical = document.all.mydate;   
var x, y;				

x = (document.layers) ? loc.pageX : event.clientX;
y = (document.layers) ? loc.pageY : event.clientY;
autoSubmit = flag;
minical.style.top	= y+10;
minical.style.left	= x-10;
minical.style.display = (minical.style.display == "block") ? "none" : "block";   
init();   
}   
function   init()   
{   
      var   now   =   new   Date();   
      var   nowYear   =   now.getYear();  
      document.all.selectYear.value   =   nowYear;   
}   
function   minus()   
{   
        var   year   =   parseInt(document.all.selectYear.value)-1;   
if(year<1900)   
{   
alert("å¹´ä»½éæ©ä¸åç");   
return;   
}   
document.all.selectYear.value=year;   
}   
function   add()   
{   
        var   year   =   parseInt(document.all.selectYear.value)+1;   
if(year>2100)   
{   
        alert("å¹´ä»½éæ©ä¸åç");   
return;   
}   
document.all.selectYear.value=year;   
}   
function   divClose()   
{   
        document.getElementById("mydate").style.display="none";   
}   
function   setYear(year)   
{   
        target.value=document.all.selectYear.value+"."+year;   
        if(autoSubmit){
        	target.form.onsubmit();
        }
        autoSubmit = false;
divClose();   
}  

function ltrim(s){ 
	return s.replace( /^\s*/, ""); 
}
function rtrim(s){ 
	return s.replace( /\s*$/, ""); 
} 
function trim(s){ 
return rtrim(ltrim(s)); 
}

	function showCurrTime(){
		var Digital=new Date();
		var hours=Digital.getHours();
		var minutes=Digital.getMinutes();
		var seconds=Digital.getSeconds();
		if(minutes<=9)
			minutes="0"+minutes;
		if(seconds<=9)
			seconds="0"+seconds;
		myclock=""+hours+":"+minutes+":"+seconds;
		if(document.layers){
			document.layers.showTime.document.write(myclock);
		    document.layers.showTime.document.close();
		}else if(document.all)
		  document.all.showTime.innerHTML=myclock;
		setTimeout("showCurrTime()",1000);
	}
	
	/**
	 * Cookieçå¤ç
	 */
	var Cookies = {};
	/**
	 * è®¾ç½®Cookies
	 */
	Cookies.set = function(name, value){
	     var argv = arguments;
	     var argc = arguments.length;
	     var expires = (argc > 2) ? argv[2] : null;
	     var path = (argc > 3) ? argv[3] : '/';
	     var domain = (argc > 4) ? argv[4] : null;
	     var secure = (argc > 5) ? argv[5] : false;
	     document.cookie = name + "=" + escape (value) +
	       ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
	       ((path == null) ? "" : ("; path=" + path)) +
	       ((domain == null) ? "" : ("; domain=" + domain)) +
	       ((secure == true) ? "; secure" : "");
	};
	/**
	 * è¯»åCookies
	 */
	Cookies.get = function(name){
	    var arg = name + "=";
	    var alen = arg.length;
	    var clen = document.cookie.length;
	    var i = 0;
	    var j = 0;
	    while(i < clen){
	        j = i + alen;
	        if (document.cookie.substring(i, j) == arg)
	            return Cookies.getCookieVal(j);
	        i = document.cookie.indexOf(" ", i) + 1;
	        if(i == 0)
	            break;
	    }
	    return null;
	};
	/**
	 * æ¸é¤Cookies
	 */
	Cookies.clear = function(name) {
	  if(Cookies.get(name)){
	    var expdate = new Date(); 
	    expdate.setTime(expdate.getTime() - (86400 * 1000 * 1)); 
	    Cookies.set(name, "", expdate); 
	  }
	};

	Cookies.getCookieVal = function(offset){
	   var endstr = document.cookie.indexOf(";", offset);
	   if(endstr == -1){
	       endstr = document.cookie.length;
	   }
	   return unescape(document.cookie.substring(offset, endstr));
	};
function fixWidth(percent, configedWidth){
	if(!configedWidth)
		configedWidth = 20;
	return parseInt(($("#pagination").width() - configedWidth) * percent);
}

function setClumWidth(width){
    var curWidth= document.body.clientWidth;
    return curWidth*width;
}

function ajaxReturnRefresh(){
	var currTab = parent.document.frames["frm"].getCurrTab();
   	var frmId = currTab[0].innerHTML.substring(11,currTab[0].innerHTML.length);
   	frmId = frmId.substring(0,frmId.indexOf(" "));
   	var frm = parent.document.frames["frm"].document.frames[frmId];
   	var frm1 = frm.document.frames["_right"];
   	if(frm1){
   		frm1.reloadDate();
   	}else
   		frm.reloadDate();	
}

function hidden_parent_scroll(){
	//alert($("#hideParentScroll").length+"   "+$("#hideParentScroll",parent.document).length);
	try{
		if($("#hideParentScroll").length=='0' && $("#hideParentScroll",parent.document).length=='0' && parent!=null){
			//alert($(parent.document).find("iframe").length);
			if($(parent.document).find("iframe").length>0){
				//alert("hello  ");
				$(parent.document.body).attr("style","overflow : hidden");
				//parent.document.body.scrolling = "no";
				//parent.document.body.style.overflow = "hidden";
			}			
		}	
	}catch(e){
		
	}
}
//window.attachEvent("onload",hidden_parent_scroll);

//$(document).ready(function(){
//	hidden_parent_scroll();
//});

function openLoginWin(){
	var p = parent;
	var i = 0;
	while(p.parent!=null){
		p = p.parent;
		if(p.frames.length>0){
			if($(p.frames["frm"]).length>0){
				break;
			}
		}
		i++;
		if(i>10)
			break;
	}
	if(i>10){
		var pos = this.location.href.indexOf("/jsp");
		window.open(this.location.href.substring(0,pos)+"/login1.jsp","_self");
	}else{
		var location = getCenterLocation(250,200);
		p.openWindow("loginWin", "登录", location.left, location.top, 250, 200, null, true,true,true,false,true);
		p.setImgScr("img1");
	}
}

/**
 * 向后台发送导出Excel请求
 * 
 * @param grid  页面数据区域DOM控件的ID
 * @param fileName 输出的文件名
 * @param colNames 需要导出的列，格式为"列1中文名|列1字段名,列2中文名|列2字段名"，如不穿的话表示从页面列表中读取所有的列
 * @param codeId 一维代码的id，多个以“,”隔开
 * @param codeFields 一维代码对应的字段名，多个以“,”隔开，且顺序与codeId一致
 * @param isAllData 是否导出所有数据，如果设置为false或者不设置只导出的话当前页面的数据
 */
function outputExcel(grid, fileName, colNames, codeId, codeFields, isAllData){
	var options = grid.datagrid('getPager').data("pagination").options;   
	var total = options.total;  //总记录数
	
	options = grid.datagrid("options");
	var url = options.url;
	
	
	if(!colNames || colNames == ""){
		var columns = options.columns[0];
		colNames = new Array();
		var j = 0;
		for(i=0;i<columns.length;i++){
			column = columns[i];
			if(column.title=='操作'||column.field=='id')
				continue;
			else{
				colNames[j] = column.title+"|"+column.field;
				j++;
			}
		}
	}
	var form1 = document.createElement("form"); 
	form1.action = "./" + url;
	document.body.appendChild(form1);  
		
	var queryParams = options.queryParams;	
	var input;
	$.each(queryParams, function(key,value){		
		input = document.createElement("input"); 	
		input.type = "text";  
		input.name = key;  
		input.value = value;  
		form1.appendChild(input);
	});
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "rows";  
	input.value = total;  
	form1.appendChild(input);
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "outputType";  
	input.value = "excel";  
	form1.appendChild(input);
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "colNames";  
	input.value = colNames;  
	form1.appendChild(input);
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "fileName";  
	input.value = fileName;  
	form1.appendChild(input);
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "codeId";  
	input.value = codeFields;  
	form1.appendChild(input);
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "codeFields";  
	input.value = codeFields;  
	form1.appendChild(input);
	
	if(isAllData){
		input = document.createElement("input"); 	
		input.type = "text";  
		input.name = "isAllData";  
		input.value = isAllData;  
		form1.appendChild(input);
	}
	
	form1.method = "post";
	form1.submit();
	document.body.removeChild(form1);  
}
function tabOnloadSuccess(){
}
function tabOnloadSuccess2(){
	tabOnloadSuccess();
}
function tabOnloadSuccess3(){
	tabOnloadSuccess();
}
/**
 * 向后台发送导出PDF请求
 * 
 * @param grid  页面数据区域DOM控件的ID
 * @param fileName 输出的文件名
 * @param colNames 需要导出的列，格式为"列1中文名|列1字段名,列2中文名|列2字段名"，如不穿的话表示从页面列表中读取所有的列
 * @param codeId 一维代码的id，多个以“,”隔开
 * @param codeFields 一维代码对应的字段名，多个以“,”隔开，且顺序与codeId一致
 * @param isAllData 是否导出所有数据，如果设置为false或者不设置只导出的话当前页面的数据
 */
function outputPDF(grid, fileName, colNames, codeId, codeFields, isAllData){
	var options = grid.datagrid('getPager').data("pagination").options;   
	var total = options.total;  //总记录数
	
	options = grid.datagrid("options");
	var url = options.url;
	
	if(!colNames || colNames == ""){
		var columns = options.columns[0];
		var colNames = new Array();
		var j = 0;
		for(i=0;i<columns.length;i++){
			column = columns[i];
			if(column.title=='操作'||column.field=='id')
				continue;
			else{
				colNames[j] = column.title+"|"+column.field;
				j++;
			}
		}
	}
	
	
	var form1 = document.createElement("form"); 
	form1.action = "./" + url;
	document.body.appendChild(form1);  
		
	var queryParams = options.queryParams;	
	var input;
	$.each(queryParams, function(key,value){		
		input = document.createElement("input"); 	
		input.type = "text";  
		input.name = key;  
		input.value = value;  
		form1.appendChild(input);
	});
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "rows";  
	input.value = total;  
	form1.appendChild(input);
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "outputType";  
	input.value = "pdf";  
	form1.appendChild(input);
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "colNames";  
	input.value = colNames;  
	form1.appendChild(input);
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "fileName";  
	input.value = fileName;  
	form1.appendChild(input);
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "codeId";  
	input.value = codeFields;  
	form1.appendChild(input);
	
	input = document.createElement("input"); 	
	input.type = "text";  
	input.name = "codeFields";  
	input.value = codeFields;  
	form1.appendChild(input);
	
	if(isAllData){
		input = document.createElement("input"); 	
		input.type = "text";  
		input.name = "isAllData";  
		input.value = isAllData;  
		form1.appendChild(input);
	}
	
	form1.method = "post";
	form1.submit();
	document.body.removeChild(form1);  
}

/**
 * 2013/01/21
 * 修改Datagrid加载数据的方法以适应不同的浏览器
 * 
 */
function reloadData(id){
	parent.location.reload();
	/*var top=parent;
	while(!top.getCurrTab){
		top=top.parent;
		break;
	}
	var currTab=top.getCurrTab();
	currTab.panel('refresh');
	var windowId=id;
	if(windowId=="null")
		windowId=parent.$("#"+window.name).parent().attr("id");
	parent.close_win(windowId);*/
	
}

/**
 * 加载内容后根据内容大小调整窗口
 * 
 * @param windowId
 * @param frmId
 */
function autoAdjust(title, windowId, frmId){
	var mainheight = parseInt(parseInt($("#"+frmId).contents().find("table").height()) + parseInt(50));
	var mainwidth = parseInt(parseInt($("#"+frmId).contents().find("table").width()) + parseInt(15));
	
	if(!mainheight || !mainwidth || mainheight <= 50 || mainwidth <= 15){
		mainheight = $("#"+frmId).contents().find("div").height() + 50;
		mainwidth = $("#"+frmId).contents().find("div").width() + 15;
	}
	
	if(isNaN(mainheight)||isNaN(mainwidth)){
		
	}else{
	    if($("#"+frmId).attr("src")==""||$("#"+frmId).attr("src").indexOf("blank.html")!=-1)
	        return false;
		$("#"+frmId).width(mainwidth);
		$("#"+frmId).height(mainheight);
		
		var location = getCenterLocation(mainwidth + 15, mainheight + 50);
		
		
		$('#'+windowId).window({
		    title : title,
			width : mainwidth + 17,
			height : mainheight + 50,
			left : location.left,
			top : location.top,
			minimizable: false,
		    onBeforeClose:function(){
			    close_win(windowId);
		    }
		});
		$("#"+windowId).window('open');
	}
}

Date.prototype.format = function(format) //author: meizz 
{ 
  var o = { 
    "M+" : this.getMonth()+1, //month 
    "d+" : this.getDate(),    //day 
    "h+" : this.getHours(),   //hour 
    "m+" : this.getMinutes(), //minute 
    "s+" : this.getSeconds(), //second 
    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter 
    "S" : this.getMilliseconds() //millisecond 
  } 
  if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
    (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
  for(var k in o)if(new RegExp("("+ k +")").test(format)) 
    format = format.replace(RegExp.$1, 
      RegExp.$1.length==1 ? o[k] : 
        ("00"+ o[k]).substr((""+ o[k]).length)); 
  return format; 
} 

/**
 * 当页面ready后，对当前页面中的form自动执行Validform的初始化
*/
 function showLoading()
  {
  	if($("#over").size()==0)
 		$("body").prepend('<div id="over" class="over"></div><div id="layout" class="layout"><img src="/yqzhaj/webResources/style/images/default/loading.gif" alt="" /></div>');
      document.getElementById("over").style.display = "block";
      document.getElementById("layout").style.display = "block";  
  }
function hideLoading()
{
    document.getElementById("over").style.display = "none";
    document.getElementById("layout").style.display = "none";  
}

window.beforeSubmitCallback=function(){
	showLoading();
}

$(function(){
	if($("body[validform=true] form").length==0)
		return;

	$("body[validform=true] form").Validform({
		"btnSubmit": "*[type=submit]",
		 postonce:true,
		 datatype:{
				"idcard":function(gets,obj,curform,regxp){
				var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
	    
				if(!gets || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(gets)){
					return false;
				}
				else if(!city[gets.substr(0,2)]){
					return false;
				}
				else{
					//18位身份证需要验证最后一位校验位
					if(gets.length == 18){
						gets = gets.split('');
						//∑(ai×Wi)(mod 11)
						//加权因子
						var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
						//校验位
						var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
						var sum = 0;
						var ai = 0;
						var wi = 0;
						for (var i = 0; i < 17; i++)
						{
							ai = gets[i];
							wi = factor[i];
							sum += ai * wi;
						}
						var last = parity[sum % 11];
						if(parity[sum % 11] != gets[17]){
							return false;
						}
						return true;
						}
					}
				},
				"lxfs":function(gets,obj,curform,regxp){
					if(!/^1[3|4|5|7|8][0-9]{9}$/.test(gets) && !/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,8}$/.test(gets)){
						return false;
					}
				}
			},
		"tiptype": function(msg,o,cssctl){
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;

			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var tip=$(o.obj).next(".validform_tip");
				if(tip.length==0)
					tip=$("<div class='validform_tip'><span class='Validform_checktip'></span><div class='validform_dec'><s class='validform_dec1'>&#9670;</s><s class='validform_dec2'>&#9670;</s></div></div>").insertAfter($(o.obj));
				
				var checktip=tip.find(".Validform_checktip").text(msg);
				cssctl(checktip,o.type);
				
				if(o.type==2){
					setTimeout(function(){
						tip.fadeOut(200);
					},2000);
				}else{
					tip.css({"display":"inline-block"});
				}
					
			}
		},
		"ajaxPost": $("body[validform=true] form").attr("ajax")||false,
		"callback": window.formAjaxCallback||null,
		"beforeSubmit": window.beforeSubmitCallback||null,
		"usePlugin":{
			passwordstrength:{
				minLen:6,
				maxLen:18
			}
		}
	});
});
$(function(){
	if($("#pagination").length==0)
		return;
	if (window.autoDatagridHeight) 
		window.datagridHeight="auto";
	else 
		window.datagridHeight=$(".submitdata").parent().height() - $(".submitdata").outerHeight(true)-20;
	window.dg_cm_pp={
		height: window.datagridHeight,
		striped: true,
		rownumbers:true,
		fitColumns:true,
		pagination:true,
		pageList:[10,20,30],
		onBeforeLoad: function (param) {
			var top = window;
			var i = 0;
			while($(top.document).find("#newWindow").length==0){
				top = top.parent;
				i++;
				if(i>6){
					break;
				}
			}
			//验证是否Session过期
			$.ajax({
				"url": top.window.checkSessionUrl,
				dataType: "json",
				"success": function(data){
					if(data.result){
						return true;
					}else{
						$.messager.alert("提示","登录超时，请重新登录","确定", function(){
							top.$("#ajaxLogin").window("open");						
						});
						return false;
					}
				}
			});
	    },
		onLoadSuccess:function(){
			$(window).trigger("resize");
		}
	}

});

$(window).resize(function() {
	$('#pagination').datagrid('resize',{
    	height: window.datagridHeigh
	});
});

function doAjaxLogin(){
	var top = window;
	var i = 0;
	while($(top.document).find("#newWindow").length==0){
		top = top.parent;
		i++;
		if(i>6){
			break;
		}
	}
	$.ajax({
		url: window.ajaxLoginUrl,
		type: "post",
		dataType: "json",
		data: {
			loginId: top.$("#loginId").val(),
			password: top.$("#password").val()
		},
		success: function(data){
			if(data.result){
				$.messager.alert("提示", "登录成功", "确定", function(){
					top.$("#ajaxLogin").window("close");
				})
			}else{
				$.messager.alert("提示", data.error, "确定");
			}
		}
	});
}


//附件上传
function uploadPic(divId,linkId,mbType,picType,queueId)
{
	var flag = linkId + ";" + mbType + ";" + picType;
	var data = '/yqzhaj/jsp/photoPic/savePhoto.action?flag='+flag;
	$("#" + divId).uploadify({
	　'uploader': '/yqzhaj/webResources/js/uploadify.swf',  
	  'buttonImg': '/yqzhaj/webResources/js/browse_sc.jpg', 
	　'script':data,
	　'cancelImg': '/yqzhaj/webResources/js/cancel.png',                  
	　'queueID' : queueId, //和存放队列的DIV的id一致  
	　'auto'  : true, //是否自动开始  
	　'multi': true, //是否支持多文件上传  
	  'buttonText': 'BROWSE', //按钮上的文字  
	　'simUploadLimit' : 1, //一次同步上传的文件数目  
	　'sizeLimit': 52428800, //设置单个文件大小限制，单位为byte  
	　'queueSizeLimit' : 10, //队列中同时存在的文件个数限制  
	　'fileDesc': '所有文件：*.*', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
	　'fileExt': '*.*;',//允许的格式
	　'onComplete': function (event, queueID, fileObj, response, data) {
    		response = eval("("+response+")");
		 	picName= response.picName;
		 	var pid = response.pid;
		 	var filename = fileObj.name;
		 	var table = document.getElementById(picType);
		 	var tr = table.insertRow();
		 	tr.id=pid;
		 	tr.style.textAlign = "center";
			var td1 = document.createElement("td");
			td1.width="70%";
			var span1 =document.createElement("span");
			var comFile = filename.toLowerCase();
			var url = response.url;
			if(comFile.indexOf('.jpg')>0||
				comFile.indexOf('.bmp')>0||
				comFile.indexOf('.png')>0||
				comFile.indexOf('.jpeg')>0||
				comFile.indexOf('.gif')>0){
					var img = '<img src="'+url+'/upload/photo/'+picName+'"';
					span1.innerHTML=img+" border='0' width='220' height='150'/>";
			}else{
				span1.innerHTML="&nbsp;&nbsp;<br>"+filename;
			}
			td1.appendChild(span1);
			tr.appendChild(td1);
			var td2 = document.createElement("td");
			td2.width="30%";
			var aa1 =document.createElement("a");
			aa1.href="javascript:downloadFile('"+ pid + "');";
			var text1=document.createTextNode("下载");
			aa1.appendChild(text1); 
			td2.appendChild(aa1);
			var span2 =document.createElement("span");
			span2.innerHTML="&nbsp;&nbsp;";
			td2.appendChild(span2);
			var aa2 =document.createElement("a");
			aa2.href="javascript:del('"+ pid + "');";
			var text2=document.createTextNode("删除");
			aa2.appendChild(text2); 
			td2.appendChild(aa2);
			tr.appendChild(td2);
	　},
	 'onAllComplete': function (event, data) { 
	 	alert("上传成功");
	　},  
	　'onError': function(event, queueID, fileObj) {  
	　	alert("文件:" + fileObj.name + "上传失败");  
	　},  
	　'onCancel':function(event, queueID, fileObj){}
	　});
}

//附件上传
function uploadPicOnly(divId,linkId,mbType,picType,queueId)
{
	var flag = linkId + ";" + mbType + ";" + picType;
	var data = '/yqzhaj/jsp/photoPic/savePhoto.action?flag='+flag;
	$("#" + divId).uploadify({
	　'uploader': '/yqzhaj/webResources/js/uploadify.swf',  
	  'buttonImg': '/yqzhaj/webResources/js/browse_sc.jpg', 
	　'script':data,
	　'cancelImg': '/yqzhaj/webResources/js/cancel.png',                  
	　'queueID' : queueId, //和存放队列的DIV的id一致  
	　'auto'  : true, //是否自动开始  
	　'multi': true, //是否支持多文件上传  
	  'buttonText': 'BROWSE', //按钮上的文字  
	　'simUploadLimit' : 1, //一次同步上传的文件数目  
	　'sizeLimit': 52428800, //设置单个文件大小限制，单位为byte  
	　'queueSizeLimit' : 10, //队列中同时存在的文件个数限制  
	　'fileDesc': '图片文件：*.png;*.gif;*.bmp;*.tiff;*.jpeg;*.tga;*.jpg;', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
	　'fileExt': '*.png;*.gif;*.bmp;*.tiff;*.jpeg;*.tga;*.jpg;',//允许的格式
	　'onComplete': function (event, queueID, fileObj, response, data) {
    		response = eval("("+response+")");
		 	picName= response.picName;
		 	var pid = response.pid;
		 	var filename = fileObj.name;
		 	var table = document.getElementById(picType);
		 	var tr = table.insertRow();
		 	tr.id=pid;
		 	tr.style.textAlign = "center";
			var td1 = document.createElement("td");
			td1.width="70%";
			var span1 =document.createElement("span");
			var comFile = filename.toLowerCase();
			var url = response.url;
			if(comFile.indexOf('.jpg')>0||
				comFile.indexOf('.bmp')>0||
				comFile.indexOf('.png')>0||
				comFile.indexOf('.jpeg')>0||
				comFile.indexOf('.gif')>0){
					var img = '<img src="'+url+'/upload/photo/'+picName+'"';
					span1.innerHTML=img+" border='0' width='220' height='150'/>";
			}else{
				span1.innerHTML="&nbsp;&nbsp;<br>"+filename;
			}
			td1.appendChild(span1);
			tr.appendChild(td1);
			var td2 = document.createElement("td");
			td2.width="30%";
			var aa1 =document.createElement("a");
			aa1.href="javascript:downloadFile('"+ pid + "');";
			var text1=document.createTextNode("下载");
			aa1.appendChild(text1); 
			td2.appendChild(aa1);
			var span2 =document.createElement("span");
			span2.innerHTML="&nbsp;&nbsp;";
			td2.appendChild(span2);
			var aa2 =document.createElement("a");
			aa2.href="javascript:del('"+ pid + "');";
			var text2=document.createTextNode("删除");
			aa2.appendChild(text2); 
			td2.appendChild(aa2);
			tr.appendChild(td2);
	　},
	 'onAllComplete': function (event, data) { 
	 	alert("上传成功");
	　},  
	　'onError': function(event, queueID, fileObj) {  
	　	alert("文件:" + fileObj.name + "上传失败");  
	　},  
	　'onCancel':function(event, queueID, fileObj){}
	　});
}


//附件上传
function uploadPdfOnly(divId,linkId,mbType,picType,queueId)
{
	var flag = linkId + ";" + mbType + ";" + picType;
	var data = '/yqzhaj/jsp/photoPic/savePhoto.action?flag='+flag;
	$("#" + divId).uploadify({
	　'uploader': '/yqzhaj/webResources/js/uploadify.swf',  
	  'buttonImg': '/yqzhaj/webResources/js/browse_sc.jpg', 
	　'script':data,
	　'cancelImg': '/yqzhaj/webResources/js/cancel.png',                  
	　'queueID' : queueId, //和存放队列的DIV的id一致  
	　'auto'  : true, //是否自动开始  
	　'multi': true, //是否支持多文件上传  
	  'buttonText': 'BROWSE', //按钮上的文字  
	　'simUploadLimit' : 1, //一次同步上传的文件数目  
	　'sizeLimit': 52428800, //设置单个文件大小限制，单位为byte  
	　'queueSizeLimit' : 1, //队列中同时存在的文件个数限制  
	　'fileDesc': 'pdf文件：*.pdf;', //如果配置了以下的'fileExt'属性，那么这个属性是必须的  
	　'fileExt': '*.pdf;',//允许的格式
	　'onComplete': function (event, queueID, fileObj, response, data) {
    		response = eval("("+response+")");
		 	picName= response.picName;
		 	var pid = response.pid;
		 	var filename = fileObj.name;
		 	var table = document.getElementById(picType);
		 	var tr = table.insertRow();
		 	tr.id=pid;
		 	tr.style.textAlign = "center";
			var td1 = document.createElement("td");
			td1.width="70%";
			var span1 =document.createElement("span");
			var comFile = filename.toLowerCase();
			var url = response.url;
			span1.innerHTML="&nbsp;&nbsp;<br>"+filename;
			td1.appendChild(span1);
			tr.appendChild(td1);
			var td2 = document.createElement("td");
			td2.width="30%";
			var aa1 =document.createElement("a");
			aa1.href="javascript:downloadFile('"+ pid + "');";
			var text1=document.createTextNode("下载");
			aa1.appendChild(text1); 
			td2.appendChild(aa1);
			var span2 =document.createElement("span");
			span2.innerHTML="&nbsp;&nbsp;";
			td2.appendChild(span2);
			var aa2 =document.createElement("a");
			aa2.href="javascript:del('"+ pid + "');";
			var text2=document.createTextNode("删除");
			aa2.appendChild(text2); 
			td2.appendChild(aa2);
			tr.appendChild(td2);
	　},
	 'onAllComplete': function (event, data) { 
	 	alert("上传成功");
	　},  
	　'onError': function(event, queueID, fileObj) {  
	　	alert("文件:" + fileObj.name + "上传失败");  
	　},  
	　'onCancel':function(event, queueID, fileObj){}
	　});
}

//删除附件
function del(aid){
	var a=confirm("删除后无法恢复，确认删除该附件吗？");
	if(a==true)
	{
		$.ajax({url: "/yqzhaj/jsp/photoPic/imageDel.action"
			,data:{fileId:aid}
			,type: "POST",
			success:function(){
				alert('删除成功！');
				$("tr").remove("tr[id="+aid+"]");
			}
	    });	
	}
}

//下载附件
function downloadFile(attachId){
        location.href = "/yqzhaj/jsp/photoPic/saveFile.action?fileId="+attachId;
}

//图片放大
$(document).ready(function() {
	$("a[rel=example_group]").fancybox({
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'titlePosition' 	: 'over'
	});

});

function openLayerWindow(title,href,width,height){
	layer.open({
		  type: 2,
		  title: title,
		  maxmin: true,
		  shadeClose: true, //点击遮罩关闭层
		  area : [width, height],
		  content: href
	});
}

var CookieUtil = {
		  // 设置cookie
		  set : function (name, value, expires, domain, path, secure) {
		    var cookieText = "";
		    cookieText += encodeURIComponent(name) + "=" + encodeURIComponent(value);
		    if (expires instanceof Date) {
		      cookieText += "; expires=" + expires.toGMTString();
		    }
		    path="/"
		    if (path) {
		      cookieText += "; path=" + path;
		    }
		    if (domain) {
		      cookieText += "; domain=" + domain;
		    }
		    if (secure) {
		      cookieText += "; secure";
		    }
		    document.cookie = cookieText;
		  },
		  // name=value; expires=expiration_time; path=domain_path; domain=domain_name; secure
		  // 获取cookie
		  get : function (name) {
		    var cookieName = encodeURIComponent(name) + "=",
		      cookieStart = document.cookie.indexOf(cookieName),
		      cookieValue = "";
		    if (cookieStart > -1) {
		      var cookieEnd = document.cookie.indexOf (";", cookieStart);
		      if (cookieEnd == -1) {
		        cookieEnd = document.cookie.length;
		      }
		      cookieValue = decodeURIComponent(document.cookie.substring(cookieStart + cookieName.length, cookieEnd));
		    }
		    return cookieValue; 
		  },
		  // 删除cookie
		  unset : function (name, domain, path, secure) {
		    this.set(name, "", Date(0), domain, path, secure);
		  }
		};

function closeLayer(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭 
}

/**
 * 获取地址栏的参数
 * @param name
 * @returns
 */
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.parent.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

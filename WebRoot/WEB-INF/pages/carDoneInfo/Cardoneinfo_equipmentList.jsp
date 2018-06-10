<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>CARDONEINFO管理</title>
<link href="${ctx}/webResources/spCss/css/style.css" rel="stylesheet" type="text/css" />
    
	<!-- jquery & easyui js -->
<script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
     window.autoDatagridHeight= <%=(String) session.getAttribute("autoDatagridHeight")%>;
</script>
	
	<script>
		$(function(){
			var url = "${ctx}/jsp/carDoneInfo/carEquipmentLists.action";
      		$("#equip").load(url);
			var player = document.getElementById("player");
 		    var oldpass = "${oldpass}";
 		    var account = "${account}";
 		   	player.LogOut();
 		    player.SetContextMenuMode(4);
 		    var aa=player.LoginEx2("58.223.251.4","10000",account,oldpass,0);	
 		    if(aa == 200 || aa == 500)
		    { 
		    	player.SetDivMode(4);
		    }
 		   	else if(aa == 402)
		    {
		    	alert('此帐户已经在别处登录,请稍后再登');
		    }
		    else if(aa == 405)
		    {
		    	alert('达到最大企业用户登录数,请重新登录');
		    }
 		    else
 		    {
 		    	alert('全球眼登录失败，请检查账号密码是否正确');
 		    }    		
		});
		
		var arr = new Array();
      	function viewVideo(guid,streamId,name)
      	{     
			var aa = player.GetCurSelectWindow();
			var i = parseInt(aa);
			if(i >= 0 && i <= 8)
			{
				arr[i] = guid + "," + streamId;
				player.OpenGUStreamEx3 (guid, streamId, 0,aa);
				$.ajax({
  					url : "${ctx}/jsp/spgl/spglSave.action?spgl.guid="+guid+"&spgl.videoName="+encodeURIComponent(name),
             		data: {
	        		},
	          	 	type: 'post',
	            	dataType: 'json',
	            	error: function(){
	            	},
	            	success: function(data){
	            	}
	        	});
			}
			else
			{
				"请先选中播放窗口!";
			}
      	}

      	function viewVideos(guid,streamId)
      	{
      		window.open("${ctx}/jsp/carDoneInfo/viewVideo.action?carEquipment.guid="+guid+"&carEquipment.streamId="+streamId,"_blank");
      	}
      	
      	function searchEquip()
      	{
      		var detailName = $("#detailName").val();
      		var url = "${ctx}/jsp/carDoneInfo/carEquipmentLists.action?carEquipment.detailName="+encodeURIComponent(detailName);
      		$("#equip").load(url);
      	}
      	function goptz(type)
		{
			var player = document.getElementById("player");
			var aa = player.GetCurSelectWindow();
			var i = parseInt(aa);
			if(i >= 0 && i <= 8)
			{
				if(i <= arr.length)
				{
					var bb = arr[i];
					if(bb != "" && bb != null)
					{
						var guid = bb.split(",")[0];
						player.SendPTZControlTCP(guid,type);
					}
					else
					{
						alert("请选择正确播放窗口!");
					}
				}
				else
				{
					alert("请选择正确播放窗口!");
				}
			}
			else
			{
				alert("请先选中播放窗口!");
			}
		}
		
		
		function getpic()
		{
			var player = document.getElementById("player");
			player.CapPicEx(1,"");
		}
		
		function startRecord()
		{
			var player = document.getElementById("player");
			var aa = player.GetCurSelectWindow();
			var i = parseInt(aa);
			if(i >= 0 && i <= 8)
			{
				if(i <= arr.length)
				{
					var bb = arr[i];
					if(bb != "" && bb != null)
					{
						var cc = bb.split(",");
						var guid = cc[0];
						var streamId = cc[1];
						var aa = player.GetDefaultRecordPath();
						alert("录像存储路径为:" + aa);
						player.StartLocalRecord(guid,streamId);
					}
					else
					{
						alert("请选择正确播放窗口!");
					}
				}
				else
				{
					alert("请选择正确播放窗口!");
				}
			}
			else
			{
				alert("请先选中播放窗口!");
			}
		}
		
		function stopRecord()
		{
			var player = document.getElementById("player");
			var aa = player.GetCurSelectWindow();
			var i = parseInt(aa);
			if(i >= 0 && i <= 8)
			{
				if(i <= arr.length)
				{
					var bb = arr[i];
					if(bb != "" && bb != null)
					{
						var cc = bb.split(",");
						var guid = cc[0];
						var streamId = cc[1];
						player.StopLocalRecord(guid,streamId);
					}
					else
					{
						alert("请选择正确播放窗口!");
					}
				}
				else
				{
					alert("请选择正确播放窗口!");
				}
			}
			else
			{
				alert("请先选中播放窗口!");
			}
		}
		
		function stopVideo()
		{
			var player = document.getElementById("player");
			player.Close(); 
		}
		
		function changeView(obj)
		{
			if(obj == '1')
			{
				player.SetDivMode(1);
			}
			else if(obj == '4')
			{
				player.SetDivMode(4);
			}
			else if(obj == '6')
			{
				player.SetDivMode(6);
			}else if(obj == '9')
			{
				player.SetDivMode(9);
			}
		}
		
		function changeLb(obj)
		{
			var cname = $("#lb"+obj).attr("class");
			if(cname == 'show_up')
			{
				$("#lb"+obj).attr("class","show_down");
				document.getElementById('splb'+obj).style.display = "";
			}
			else
			{
				$("#lb"+obj).attr("class","show_up");
				document.getElementById('splb'+obj).style.display = "none";
			}
		}
    </script>
   
</head>

<body>
<div class="left">
	<OBJECT id="player" CLASSID="clsid:AB70953C-CD32-4CE0-BEA8-07365E319C2B" style="z-index:-1;" height="100%" width="100%">
		 <param value="opaque" name="wmode">
     </OBJECT>	
</div>

<div class="search">
<a href="#" class="search_btn" onclick="searchEquip();">搜索</a>
<input type="text" id="detailName">
</div>


<div class="list" id="equip">
</div>


<div class="bottom">
<div class="screenset">
<a href="#" onClick="changeView('1');"><b class="icon_01"></b></a>
<a href="#" onClick="changeView('4');"><b class="icon_02"></b></a>
<a href="#" onClick="changeView('6');"><b class="icon_03"></b></a>
<a href="#" onClick="changeView('9');"><b class="icon_04"></b></a>
</div>


<div class="tools">
<ul>
<li><a href="#" onClick="getpic();"><b class="icon_01"></b></a><div class="popup">拍照</div></li>
<li><a href="#" onClick="startRecord();"><b class="icon_02"></b></a><div class="popup">开始录像</div></li>
<li><a href="#" onClick="stopRecord();"><b class="icon_03"></b></a><div class="popup">停止录像</div></li>
<li><a href="#" onMouseDown="goptz(5);" onMouseUp="goptz(25);"><b class="icon_04"></b></a><div class="popup">拉近</div></li>
<li><a href="#" onMouseDown="goptz(6);" onMouseUp="goptz(26);"><b class="icon_05"></b></a><div class="popup">拉远</div></li>
</ul>
</div>
</div>

<div class="romote">
<a href="#" class="r_up" title="向上" onMouseDown="goptz(3);" onMouseUp="goptz(23);"></a>
<a href="#" class="r_down" title="向下" onMouseDown="goptz(4);" onMouseUp="goptz(24);"></a>
<a href="#" class="r_left" title="向左" onMouseDown="goptz(1);" onMouseUp="goptz(21);"></a>
<a href="#" class="r_right" title="向右" onMouseDown="goptz(2);" onMouseUp="goptz(22);"></a>
<a href="#" class="r_center" title="暂停" onClick="stopVideo();"></a>
</div>

</body>
</html>

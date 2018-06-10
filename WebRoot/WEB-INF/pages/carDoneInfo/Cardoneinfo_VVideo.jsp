<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看实时监控</title>
	<%@include file="/common/jsLib.jsp"%>
	
	<script>
		$(function(){
			var guid = "${carEquipment.guid}";
			var player = document.getElementById("player");
 		    var oldpass = "${oldpass}";
 		    var streamId = "${carEquipment.streamId}";
 		    var account = "${account}";
 		   	player.LogOut();
 		    player.SetContextMenuMode(4);
 		    var aa=player.LoginEx2("58.223.251.4","10000",account,oldpass,0);	
 		    if(aa == 200 || aa == 500)
		    { 
 		    	var b = player.OpenGUStreamEx2(guid,streamId, 0);		  
 	     		if(b == 200)
 	     		{
 	     		    
 	     		}
 	     		else
 	     		{
 	     		    alert('开启实时监控失败');
 	     		} 
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
		
		function closeCamera()
		{
			var player = document.getElementById("player");
			player.LogOut();
			var type = "${str}";
			parent.document.getElementById(type).value = "0";
			if(type == "a")
			{
				parent.document.getElementById("div1").src = "";
			}
			else if(type == "b")
			{
				parent.document.getElementById("div2").src = "";
			}
			else if(type == "c")
			{
				parent.document.getElementById("div3").src = "";
			}
			else if(type == "d")
			{
				parent.document.getElementById("div4").src = "";
			}
		}
		
		function goptz(type)
		{
			var guid = "${carEquipment.guid}";
			var player = document.getElementById("player");
			player.SendPTZControlTCP(guid,type);
		}
		
		
		function getpic()
		{
			var player = document.getElementById("player");
			player.CapPicEx(1,"");
		}
		
		function startRecord()
		{
			var guid = "${carEquipment.guid}";
			var streamId = "${carEquipment.streamId}";
			var player = document.getElementById("player");
			var aa = player.GetDefaultRecordPath();
			alert("录像存储路径为:" + aa);
			player.StartLocalRecord(guid,streamId);
		}
		
		function stopRecord()
		{
			var guid = "${carEquipment.guid}";
			var streamId = "${carEquipment.streamId}";
			var player = document.getElementById("player");
			player.StopLocalRecord(guid,streamId);
		}
	</script>
</head>
<body>
  <table width="100%" height="100%">
	 <tr>
	 	<td vertical-align="middle" align="center">
        	<div>
            	<OBJECT id="player" CLASSID="clsid:AB70953C-CD32-4CE0-BEA8-07365E319C2B" height="180" width="100%">
            	</OBJECT>
        	</div>
    	</td>   	
    </tr>
    <tr>
		<td style="text-align: center;">	
			<input type="button" value="向左" onclick="goptz(1);" />
			<input type="button" value="向左停" onclick="goptz(21);" />
			<input type="button" value="向右" onclick="goptz(2);" />
			<input type="button" value="向右停" onclick="goptz(22);" />
			<input type="button" value="向上" onclick="goptz(3);" />
			<input type="button" value="向上停" onclick="goptz(23);" />
			<input type="button" value="向下" onclick="goptz(4);" />
			<input type="button" value="向下停" onclick="goptz(24);" />
		</td>
	</tr>
	<tr>
		<td style="text-align: center;">	
			<input type="button" value="拍照" onclick="getpic();" />
			<input type="button" value="拉近" onclick="goptz(5);" />
			<input type="button" value="拉近停" onclick="goptz(25);" />
			<input type="button" value="拉远" onclick="goptz(6);" />
			<input type="button" value="拉远停" onclick="goptz(26);" />
			<input type="button" value="开始录像" onclick="startRecord();" />
			<input type="button" value="停止录像" onclick="stopRecord();" />
			<input type="button" value="关闭" onclick="closeCamera();" />
		</td>
	</tr>
    </table>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看视频录像</title>
	
    
	<%@include file="/common/jsLib.jsp"%>
	
	<script>

		$(function(){
			var player = document.getElementById("player");
			var username = "yqajj";
			var password = "yqajj@111111";
			var playserver ="58.223.251.4";
			var playport = "10000";
			player.LogOut();
			player.SetContextMenuMode(4);
			var aa = player.LoginEx2("58.223.251.4","10000",username,password,0);
			if(aa == 200 || aa == 500)
			{
				var bb = player.OpenRecordTaskEx("${splx.taskId}", "${splx.guid}", "${startTime}", "${endTime}");
				if(bb == 200)
				{
				}
				else
				{
					alert("播放录像失败");
				}
			}
		});		
		
		function closeWin()
		{
			var player = document.getElementById("player");
			player.Close();
			player.LogOut();
			window.close();
		}
	
	</script>
</head>

<body bgcolor="#66CCFF">
	 <table width="100%" height="100%"align="center">
	 <tr align="center">
	 	<td vertical-align="middle">
        	<div>
            	<OBJECT id="player" CLASSID="clsid:AB70953C-CD32-4CE0-BEA8-07365E319C2B" height="500" width="800">
            	</OBJECT>
        	</div>	
    	</td>   	
    </tr>
    <tr>
		<td style="text-align: center;">	
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeWin();">关闭</a>
		</td>
	</tr>
    </table>
</div>
</body>
</html>

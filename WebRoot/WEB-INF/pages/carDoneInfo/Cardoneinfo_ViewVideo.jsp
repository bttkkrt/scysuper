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
	 		    	var b = player.OpenGUStreamEx2(guid,streamId,0);
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
		
		
		function closeEquip()
		{
			var player = document.getElementById("player");
			player.LogOut();
			window.close();
		}
		
	</script>
</head>

<body bgcolor="#66CCFF">
<%@include file="/WEB-INF/template/content_title.jsp"%>
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
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeEquip();">关闭</a>
		</td>
	</tr>
    </table>
</div>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

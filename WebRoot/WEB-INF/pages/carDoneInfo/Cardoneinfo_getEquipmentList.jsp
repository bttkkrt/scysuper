<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>获取设备列表</title>
    
	<%@include file="/common/jsLib.jsp"%>
	<script>
		var account = "${account}";
		var oldpass = "${oldpass}";
		var newpass = "${newpass}";
		var s = "";
		$(function(){
			if(document.all.player.object == null)
			{
				document.getElementById('cj').style.display = "";
			}
			else
			{
				if(account == null || account == "")
				{
					alert("未获取到全球眼列表");
				}
				else
				{
					document.getElementById('loading').style.display = "none";
					var player = document.getElementById("player");
					player.LogOut();
					player.SetContextMenuMode(4);
					var aa = player.LoginEx2("58.223.251.4","10000",account,oldpass,0);
					if(aa == 200 || aa == 500)
					{
						alert("全球眼登录成功,获取视频列表中");
						if(newpass != null && newpass != "")
						{
							var bb = player.ChangePassword(oldpass,newpass);
							if(bb == 200)
							{
								var cc = player.ChangePassword(newpass,oldpass);
								if(cc == 200)
								{
									$.ajax({
              							url : "${ctx}/jsp/carDoneInfo/savePass.action?newpass="+newpass,
              							type: 'post',
                						dataType: 'json',
                						async : false,
               			 				data:{ 
                						},
                						error: function(){
                  							alert('错误','修改密码时出错！');
                						},
                						success: function(data){
                    						if(data.result == 'true'){	
                    							player.GetDeviceListEx();
                							}
                						}
           							});
								}
							}
						}
						else
						{
							player.GetDeviceListEx();
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
	 		   	 		var cc = player.LoginEx2("58.223.251.4","10000",account,newpass,0);
						if(cc == 200 || cc == 500)
						{
							alert("全球眼登录成功,获取视频列表中");
							if(newpass != null && newpass != "")
							{
								var cc = player.ChangePassword(newpass,oldpass);
								if(cc == 200)
								{
									$.ajax({
              							url : "${ctx}/jsp/carDoneInfo/savePass.action?newpass="+newpass,
              							type: 'post',
                						dataType: 'json',
                						async : false,
               			 				data:{ 
                						},
                						error: function(){
                  							alert('错误','修改密码时出错！');
                						},
                						success: function(data){
                    						if(data.result == 'true'){	
                    							player.GetDeviceListEx();
                							}
                						}
           							});
								}
							}
							else
							{
								player.GetDeviceListEx();
							}
						}
	 				}   
	 				player.LogOut();
				}
			}
		});
		
		function createXml(str){ 
	        if(document.all){ 
	       		var xmlDom=new ActiveXObject("Microsoft.XMLDOM"); 
	       	　　xmlDom.loadXML(str); 
	       	　　return xmlDom ;
	       	} 
	       	else 
	       	　	return new DOMParser().parseFromString(str, "text/xml"); 
        } 
    </script>
   	<script language="JavaScript" FOR="player" event="DeviceListReturnEx(nStatusCode,nCurIndex,nTotalCount,sDeviceList)">   
	{
		if(nStatusCode == 200)
		{
			s += sDeviceList;
			if(nCurIndex == nTotalCount)
			{
				var domxml= createXml(s); 
				var groups = domxml.getElementsByTagName("group");
				var str = "";
				for(var i=0;i<groups.length;i++)
				{
					var name = groups[i].getAttribute("name");  
					var cameras = groups[i].getElementsByTagName("Camera");
					for(var j=0;j<cameras.length;j++)
           			{
           				var status = cameras[j].getElementsByTagName("status")[0].firstChild.nodeValue;
           				if(status == 'online')
           				{
           					var streamid = cameras[j].getElementsByTagName("StreamID")[0].firstChild.nodeValue;
           					var puid = cameras[j].getAttribute("puid");
           					var guid = cameras[j].getAttribute("guid"); 
           					var guname = cameras[j].getAttribute("guname"); 
	           				str += puid + "," + guid + "," + guname + "," +streamid + "," +name + ";";
           				}
	       			}
				}
	       		if(str == "")
	       		{
	       			document.getElementById('loading').style.display = "none";
	       			alert('无通道列表');
	       		}
			else
			{
				$.ajax({
  					url : "${ctx}/jsp/carDoneInfo/saveDevList.action",
             		data: {
      	        		str : str
	        		},
	          	 	type: 'post',
	            	dataType: 'json',
	            	error: function(){
	               	alert('通道列表保存错误');
	            	},
	            	success: function(data){
	            		if(data.result == "true"){
	            			player.LogOut();  
	                 	    location.href = "${ctx}/jsp/carDoneInfo/carEquipmentList.action";
	                	}else{
	                		document.getElementById('loading').style.display = "none";
	                		alert('通道列表保存错误');
	               		}
	            	}
	        	});
			}
  				
			}
		}
	}
</script>
</head>

<body>
	<div style="position: absolute;top: 30%;padding: 2px;z-index: 20001;height:30%;width: 40%;left: 30%;text-align:center">
		<div id="cj" style="color:red;font-size:16px;display:none">
			您还未安装视频插件,请点击<a href="${ctx}/upload/MOCPLAYERV.exe" style="font-size:16px;color:blue;text-decoration:underline">此处</a>下载安装,安装完成后请刷新页面
			<br/>
			若您已安装插件，则表示该浏览器不支持观看远程视频，请更换IE浏览器
		</div>
		<div id="loading" style="color:red;font-size:16px;display:none">
		视频加载中，请稍后......
		<img src='<c:url value="${ctx}/webResources/images/loadimage.gif"/>'
			width="80" height="80"
			style="margin-right: 8px; float: left; vertical-align: top; position: absolute;left: 40%; top: 40%" />
		</div>
	</div>
	<div style="display:none">
		 <OBJECT id="player" CLASSID="clsid:AB70953C-CD32-4CE0-BEA8-07365E319C2B" height="383" width="451">
         </OBJECT>
	</div>
</body>
</html>

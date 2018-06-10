<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>时间选择</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
	var num=1;
	var player;
	var username = "yqajj";
	var password = "yqajj@111111";
	var guid="${guid}";
	var begintime="${begintime}";
	var endtime="${endtime}";
	
	var s = "";
	$(function(){
			if(document.all.player.object == null)
			{
				document.getElementById('cj').style.display = "";
			}
			else
			{
				document.getElementById('loading').style.display = "none";
				player = document.getElementById("player");
				player.LogOut();
				player.SetContextMenuMode(4);
				var aa = player.LoginEx2("58.223.251.4","10000",username,password,0);
				if(aa == 200 || aa == 500)
				{
					alert("获取录像列表中,请稍等！");
					player.QueryRecordTask(guid,"1",begintime,endtime,num);
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

<script language="JavaScript" FOR="player" event="QueryRecordReturn (nQueryType,sGUID,sQueryResult)">   
{
   var domxml= createXml(sQueryResult); 
   var status = domxml.getElementsByTagName("Status")[0].getAttribute("errorcode");
   var totalPage = domxml.getElementsByTagName("TotalPage")[0].firstChild.nodeValue;
   var curPage = domxml.getElementsByTagName("CurPage")[0].firstChild.nodeValue;
  
    
   if(status == '200')
   {
   		var recs = domxml.getElementsByTagName("rec");
   		for(var i=0;i<recs.length;i++)
   		{
   			var taskId = recs[i].getElementsByTagName("TaskID")[0].firstChild.nodeValue;
   			var fileName = recs[i].getElementsByTagName("FileName")[0].firstChild.nodeValue;
   			var beginTime = recs[i].getElementsByTagName("BeginTime")[0].firstChild.nodeValue;
   			var endTime = recs[i].getElementsByTagName("EndTime")[0].firstChild.nodeValue;
   			s += taskId + "," + fileName + "," + beginTime + "," +endTime + ";";
   		}
   		if(totalPage != curPage)
   		{
   			num++;
            player.QueryRecordTask(guid,"1",begintime,endtime,num);
   		}
   		else
   		{
   			if(s == "")
	       	{
	       		document.getElementById('loading').style.display = "none";
	       		alert('无录像');
	       	}
	       	else
	       	{
	       		$.ajax({
  					url : "${ctx}/jsp/splx/saveSplxList.action",
             		data: {
      	        		s : s,
      	        		guid : guid
	        		},
	          	 	type: 'post',
	            	dataType: 'json',
	            	error: function(){
	               	alert('视频录像保存错误');
	            	},
	            	success: function(data){
	            		player.LogOut();
	            		location.href = "${ctx}/jsp/splx/splxList.action?guid="+guid;
	            	}
	        	});
	       	}
   		}
   }
   else
	{
		alert('无录像');
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
		录像加载中，请稍后......
		<img src='<c:url value="/webResources/images/loadimage.gif"/>'
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

<%@ Page Language="C#" AutoEventWireup="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>QS应用系统开发框架模板</title>
    <style type="text/css">
    html, body {
	    height: 100%;
	    overflow: auto;
    }
    body {
	    padding: 0;
	    margin: 0;
    }
    #silverlightControlHost {
	    height: 100%;
	    text-align:center;
    }
    </style>
    <script type="text/javascript" src="Silverlight.js"></script>
    <script type="text/javascript">
        function onSilverlightError(sender, args) {
            var appSource = "";
            if (sender != null && sender != 0) {
              appSource = sender.getHost().Source;
            }
            
            var errorType = args.ErrorType;
            var iErrorCode = args.ErrorCode;

            if (errorType == "ImageError" || errorType == "MediaError") {
              return;
            }

            var errMsg = "Silverlight 应用程序中未处理的错误 " +  appSource + "\n" ;

            errMsg += "代码: "+ iErrorCode + "    \n";
            errMsg += "类别: " + errorType + "       \n";
            errMsg += "消息: " + args.ErrorMessage + "     \n";

            if (errorType == "ParserError") {
                errMsg += "文件: " + args.xamlFile + "     \n";
                errMsg += "行: " + args.lineNumber + "     \n";
                errMsg += "位置: " + args.charPosition + "     \n";
            }
            else if (errorType == "RuntimeError") {           
                if (args.lineNumber != 0) {
                    errMsg += "行: " + args.lineNumber + "     \n";
                    errMsg += "位置: " +  args.charPosition + "     \n";
                }
                errMsg += "方法名称: " + args.methodName + "     \n";
            }

            throw new Error(errMsg);
        }

        function onSymLoad(sender, args) {
            var logon, sl;

            //获取用户名密码
            var username = getQueryString("username");
            var psd = getQueryString("psd");

            //获取SL对象
            sl = document.getElementById("qsSym");

            //获取与JS交互的SL注册对象
            logon = sl.Content.Bootstrapper;

            //设置是否需要通过登录界面来进行加载
            if (username == null && psd == null) {
                logon.IsNeedLogon = true;//当URL中不包含username和psd时默认设置为需要登录界面
            }
            else {
                logon.IsNeedLogon = false;//不需要登录界面，直接根据url中携带的ID+PSD进行登录加载
                document.cookie = "Xusername=" + escape(username);
                document.cookie = "Xpsd=" + escape(psd);
            }

            //设定参数-----------此处设置是否需要加载默认的系统配置文件
            //此处设置为true后，无论有否登录界面或外部授权，都只加载本地的默认配置信息
            logon.IsLocalConfig = true;

            //运行QS框架系统
            logon.Run("");
        }

        //提取参数
        function getQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        }


    </script>

      <script type="text/javascript">
          function detectOS() {
              var str;
              var agent = navigator.userAgent.toLowerCase();
              if (agent.indexOf("mac") > -1)
                  str = 'ClientBin/downloadSL/Silverlight5_MacOS.dmg';
              else if (agent.indexOf("win64") > -1 || agent.indexOf("wow64") > -1)
                  str = 'ClientBin/downloadSL/Silverlight_x64.exe';
              else
                  str = 'ClientBin/downloadSL/Silverlight_x32.exe'
              window.open(str);
          }
    </script>
</head>
<body>
    <form id="form1" runat="server" style="height:100%">
    <div id="silverlightControlHost">
      <object id="qsSym" data="data:application/x-silverlight-2," type="application/x-silverlight-2" width="100%" height="100%">
		  <param name="source" value="ClientBin/Geone_QS.xap"/>
		  <param name="onError" value="onSilverlightError" />
		  <param name="background" value="white" />
		  <param name="minRuntimeVersion" value="5.0.61118.0" />
          <param name="onload" value="onSymLoad" />
		  <param name="autoUpgrade" value="true" />
          <a href="javascript: detectOS()" style="text-decoration:none">
 		      <img src="ClientBin/downloadSL/SLMedallion_CHS.png" alt="获取 Microsoft Silverlight" style="border-style:none"/>
	     </a>
	    </object><iframe id="_sl_historyFrame" style="visibility:hidden;height:0px;width:0px;border:0px"></iframe></div>
    </form>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<c:set var="curr_path" value="修改记录"/>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>修改记录</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script src="${ctx}/webResources/My97DatePicker/WdatePicker.js"></script>
		<SCRIPT language="JavaScript" src="<%=basePath%>/webResources/js/docorder.js"></SCRIPT>
		<script type='text/javascript' src='<s:url value="/dwr/engine.js"/>'></script>
		<script type='text/javascript' src='<s:url value="/dwr/util.js"/>'></script>
		<script type='text/javascript'
			src='<%=basePath%>/dwr/interface/attachfileManager.js'> </script>
		<%@include file="/common/jsLib.jsp"%>
		
		<script>
		
		 function del_big_file(index, attachId){
        	attachfileManager.delbyId(attachId, function(){
        		var div = document.getElementById("big_file_"+index);
                div.parentNode.removeChild(div);
                alert("文件删除成功！");
            });
        }
        function open_file(attachId){
            window.open("readAttFile.action?rowguid="+attachId,"_blank");
        }

        function get_all_file(index){
        	attachfileManager.getAttFilesByGuID(index, displayAllFiles);
        }

		function displayAllFiles(list)
		{
			var htmlvar = "";
			for (var filei = 0; filei < list.length; filei++) 
			{ 
				att = list[filei]; 
				//alert(attachfileModel.attachName);
				htmlvar+="<div id='big_file_" + filei + "'>";

				htmlvar+="<a href='#' onclick='open_file(\""
						+ att.rowguid + "\")'>"
						+ att.attachName + "</a> &nbsp;|";

				htmlvar+="<input type='button' onclick=\"del_big_file("
								+ filei
								+ ",'"
								+ att.id
								+ "')\" value='删除'></div>";
			}
			document.getElementById("AllFiles").innerHTML = htmlvar;
			
		}
		
function recordSave(){
	document.myform.action="formRecordSave.action";
	if(Validator.Validate(document.myform,3)){
		var files = document.getElementsByName("files");
		var v ="";
		for(var i=0;i<files.length;i++){
		    if(files[i].value!=""){
				v = v + ",1";
			}else{
				v = v + ",0";
			}
		}
		
		document.getElementById("fileNames").value=v;
		document.myform.submit();
		//window.close();
	}
	
}    
function recordGetNew(){
		document.myform.action="formRecordAdd.action";
		document.myform.submit();
}

 
function recordUpdate(){
	if(Validator.Validate(document.myform,3)){
		var files = document.getElementsByName("files");
		var v ="";
		for(var i=0;i<files.length;i++){
		    if(files[i].value!=""){
				v = v + ",1";
			}else{
				v = v + ",0";
			}
		}
	document.getElementById("fileNames").value=v;
	document.myform.action="formRecordUpdate.action";
	document.myform.submit();
	}
}
function patrn_check(patrnStr,obj,str){

	setTimeout(function(){var reg = new RegExp(patrnStr);
		var rsobj = obj.value.match(reg);
	if(rsobj==null||rsobj.length==0){
        alert(str+"不符合正则表达式校验规则!"+"\r\n"+patrnStr);
        obj.focus();}},200);

}
</script>
	</head>

	<body class="PageBgColor" style="overflow: auto">
	<c:set var="curr_path" value="添加/修改记录"></c:set>
		<form name="myform" method="post" enctype="multipart/form-data">
			<input type="hidden" name="rowId"
				value="<s:property value='rowId'/>">
			
			<input type="hidden" name="tableId" value="${tableId }" />
			<input type="hidden" id="fileNames" name="fileNames" />

			<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<edp:editRecord codeMapName="codeMap"
						columnListName="columnList" valueMapName="valueMap"
						tablePhyName="${tablePhyName}"  tableId="${tableId}"  rowId="${rowId}"/>
				</tr>
				<tr>
					<td colspan="4" align="center" height="10px"
						style="color: red; font-size: 14px; font-weight: bold">
						<s:property value="msg" />
					</td>
				</tr>
				<tr>
					<th colspan="4" height="100px" class="set_c">
					    
						<c:if test="${not empty rowId}">
						<a href="#" class="easyui-linkbutton" onclick="recordUpdate();" iconCls="icon-save">更新</a>
							&nbsp;
						</c:if>
						<c:if test="${empty rowId}">
						<a href="#" class="easyui-linkbutton" onclick="recordSave();" iconCls="icon-save">添加</a>&nbsp;
						</c:if>
						
					    <a href="#" class="easyui-linkbutton" onclick="parent.close_win();" iconCls="icon-close">关闭</a>
					</th>
				</tr>
			</table>
			</div>

		</form>
		<script>
				if('${message}'!='')
				{
					//opener.location=opener.location;
					//refresh will cause No tableId input 
					$.messager.alert("提示",'${message}');				
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
			</script>
	</body>
</html>

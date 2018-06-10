<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="gpqyyhzgSave.action";
				document.myform1.submit();
			}
		}
		
		function getData()
		{
			var tjyf = document.getElementById('tjyf').value;
			if(tjyf != null && tjyf != "")
			{
		        $.ajax({
					type:"POST",
					url:"queryData.action?gpqyyhzg.tjyf="+tjyf,
					success:function(json){
						var obj = eval('(' + json + ')');
						document.getElementById('sgps').value = obj.sgps;
		            	document.getElementById('syzg').value = obj.syzg;
						document.getElementById('szgz').value = obj.szgz;
						document.getElementById('swzg').value = obj.swzg;
						document.getElementById('qgps').value = obj.qgps;
						document.getElementById('qyzg').value = obj.qyzg;
						document.getElementById('yzgz').value = obj.yzgz;
						document.getElementById('qwzg').value = obj.qwzg;
						document.getElementById('zgps').value = obj.zgps;
						document.getElementById('zyzg').value = obj.zyzg;
						document.getElementById('zzgz').value = obj.zzgz;
						document.getElementById('zwzg').value = obj.zwzg;
						document.getElementById('jcqys').value = obj.jcqys;
						document.getElementById('fxyhs').value = obj.fxyhs;
						document.getElementById('zgyhs').value = obj.zgyhs;
					},
					dateType:"json"
				});
			}
			else
			{
				alert("请先选择统计月份");
			}
		}
		
		function changNum(obj)
		{
			var ndmb = document.getElementById('ndmb').value;
			var wcrs = document.getElementById('wcrs').value;
			if(ndmb != null && ndmb != "" && wcrs != null && wcrs != "")
			{
				var a = accDiv(wcrs,ndmb);
				var b = parseFloat(a*100);
				document.getElementById('wcl').value = b.toFixed(2);
			}
			else
			{
				document.getElementById('wcl').value = "";
			}
		}
		
		function validate(event,obj)
        {
        	event = window.event||event; 
        	if(event.keyCode == 37 | event.keyCode == 39){ 
           	 	return; 
        	} 
        	obj.value = obj.value.replace(/[^\d]/g,""); 
        	if(obj.value.length >= 2 && obj.value.substring(0,1) == "0")
        	{
        		obj.value = obj.value.substring(1,obj.value.length);
        	}
        }
        
        //除法
		function accDiv(arg1, arg2) {
         var t1 = 0, t2 = 0, r1, r2;
         try { t1 = arg1.toString().split(".")[1].length } catch (e) { }
         try { t2 = arg2.toString().split(".")[1].length } catch (e) { }
         with (Math) {
             r1 = Number(arg1.toString().replace(".", ""))
             r2 = Number(arg2.toString().replace(".", ""))
             return (r1 / r2) * pow(10, t2 - t1);
         }
     }
	</script>
	<style>
	th
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 2px 0 0;
	}
	td
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:left;
		padding:0 0 0 2px;
		background:#fff
	}
	tr
	{
		border:1px solid #d5dbdc;
		height:24px
	}
	</style>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="gpqyyhzg.id" value="${gpqyyhzg.id}">
		<input type="hidden" name="gpqyyhzg.createTime" value="<fmt:formatDate type="both" value="${gpqyyhzg.createTime}" />">
		<input type="hidden" name="gpqyyhzg.updateTime" value="${gpqyyhzg.updateTime}">
		<input type="hidden" name="gpqyyhzg.createUserID" value="${gpqyyhzg.createUserID}">
		<input type="hidden" name="gpqyyhzg.updateUserID" value="${gpqyyhzg.updateUserID}">
		<input type="hidden" name="gpqyyhzg.deptId" value="${gpqyyhzg.deptId}">
		<input type="hidden" name="gpqyyhzg.delFlag" value="${gpqyyhzg.delFlag}">
		<input type="hidden" name="gpqyyhzg.szzid" value="${gpqyyhzg.szzid}">
		<input type="hidden" name="gpqyyhzg.szzname" value="${gpqyyhzg.szzname}">
		
		<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0">
			<table width="100%" border="0" style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
				<tr>
					<th width="20%">统计月份</th>
					<td width="20%">
						<input id="tjyf" name="gpqyyhzg.tjyf" value="${gpqyyhzg.tjyf}" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"><font color="red">*</font>
						<input type="button" value="获取数据" onclick="getData();"/>
					</td>
					<th width="20%">填报人</th>
					<td width="40%"><input name="gpqyyhzg.tbr" value="${gpqyyhzg.tbr}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="20%" rowspan="2">责任书签订情况</th>
					<th width="40%" colspan="2">政府与村</th>
					<td width="40%"><input name="gpqyyhzg.zfyc" value="${gpqyyhzg.zfyc}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%" colspan="2">村与企业</th>
					<td width="40%"><input name="gpqyyhzg.cyqy" value="${gpqyyhzg.cyqy}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%" rowspan="12">挂牌督办完成情况</th>
					<th width="20%" rowspan="4">市</th>
					<th width="20%">挂牌数</th>
					<td width="40%"><input id="sgps" name="gpqyyhzg.sgps" value="${gpqyyhzg.sgps}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">已整改</th>
					<td width="40%"><input id="syzg" name="gpqyyhzg.syzg" value="${gpqyyhzg.syzg}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">整改中</th>
					<td width="40%"><input id="szgz" name="gpqyyhzg.szgz" value="${gpqyyhzg.szgz}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">未整改</th>
					<td width="40%"><input id="swzg" name="gpqyyhzg.swzg" value="${gpqyyhzg.swzg}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%" rowspan="4">区</th>
					<th width="20%">挂牌数</th>
					<td width="40%"><input id="qgps" name="gpqyyhzg.qgps" value="${gpqyyhzg.qgps}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">已整改</th>
					<td width="40%"><input id="qyzg" name="gpqyyhzg.qyzg" value="${gpqyyhzg.qyzg}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">整改中</th>
					<td width="40%"><input id="yzgz" name="gpqyyhzg.yzgz" value="${gpqyyhzg.yzgz}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">未整改</th>
					<td width="40%"><input id="qwzg" name="gpqyyhzg.qwzg" value="${gpqyyhzg.qwzg}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%" rowspan="4">镇</th>
					<th width="20%">挂牌数</th>
					<td width="40%"><input id="zgps" name="gpqyyhzg.zgps" value="${gpqyyhzg.zgps}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">已整改</th>
					<td width="40%"><input id="zyzg" name="gpqyyhzg.zyzg" value="${gpqyyhzg.zyzg}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">整改中</th> 
					<td width="40%"><input id="zzgz" name="gpqyyhzg.zzgz" value="${gpqyyhzg.zzgz}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%">未整改</th>
					<td width="40%"><input id="zwzg" name="gpqyyhzg.zwzg" value="${gpqyyhzg.zwzg}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%" rowspan="3">检查情况</th>
					<th width="40%" colspan="2">检查企业数</th>
					<td width="40%"><input id="jcqys" name="gpqyyhzg.jcqys" value="${gpqyyhzg.jcqys}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="40%" colspan="2">发现隐患数</th>
					<td width="40%"><input id="fxyhs" name="gpqyyhzg.fxyhs" value="${gpqyyhzg.fxyhs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="40%" colspan="2">整改隐患数</th>
					<td width="40%"><input id="zgyhs" name="gpqyyhzg.zgyhs" value="${gpqyyhzg.zgyhs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="20%" rowspan="3">培训情况</th>
					<th width="40%" colspan="2">年度目标(人)</th>
					<td width="40%"><input id="ndmb" name="gpqyyhzg.ndmb" value="${gpqyyhzg.ndmb}" type="text" maxlength="255" onKeyUp="validate(event,this);changNum(this);"></td>
				</tr>
				<tr>
					<th width="40%" colspan="2">完成人数（人）</th>
					<td width="40%"><input id="wcrs" name="gpqyyhzg.wcrs" value="${gpqyyhzg.wcrs}" type="text" maxlength="255" onKeyUp="validate(event,this);changNum(this);"></td>
				</tr>
				<tr>
					<th width="40%" colspan="2">完成率%</th>
					<td width="40%"><input id="wcl" name="gpqyyhzg.wcl" value="${gpqyyhzg.wcl}" type="text" maxlength="255" readonly="readonly"></td>
				</tr>
				<tr>
					<th width="20%">备注</th>
					<td width="80%" colspan="3"><textarea name="gpqyyhzg.bz" style="width:100%;height:120px">${gpqyyhzg.bz}</textarea></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

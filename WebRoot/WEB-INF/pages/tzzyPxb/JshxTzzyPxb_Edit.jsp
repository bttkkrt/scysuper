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
		var selects=[];
		selects[0]=new Array(
			new Option('','0'));
		selects['1']=new Array(
			new Option('','0'),
			new Option('高压电工作业','1'),
			new Option('低压电工作业','2'),
			new Option('防爆电气作业','3')
		);
		selects['2']=new Array(
			new Option('','0'),
			new Option('熔化焊接与热切割作业','1'),
			new Option('压力焊作业','2'),
			new Option('钎焊作业','3')
		);
		selects['3']=new Array(
			new Option('','0'),
			new Option('登高架设作业','1'),
			new Option('高处安装、维护、拆除作业','2')
		);
		selects['4']=new Array(
			new Option('','0'),
			new Option('制冷与空调设备运行操作作业','1'),
			new Option('制冷与空调设备安装修理作业','2')
		);
		selects['5']=new Array(
			new Option('','0'),
			new Option('煤气作业','1')
		);
		selects['6']=new Array(
			new Option('','0'),
			new Option('光气及光气化工艺作业','1'),
			new Option('氯碱电解工艺作业','2'),
			new Option('氯化工艺作业','3'),
			new Option('硝化工艺作业','4'),
			new Option('合成氨工艺作业','5'),
			new Option('裂解（裂化）工艺作业','6'),
			new Option('氟化工艺作业','7'),
			new Option('加氢工艺作业','8'),
			new Option('重氮化工艺作业','9'),
			new Option('氧化工艺作业','10'),
			new Option('过氧化工艺作业','11'),
			new Option('胺基化工艺作业','12'),
			new Option('磺化工艺作业','13'),
			new Option('聚合工艺作业','14'),
			new Option('烷基化工艺作业','15'),
			new Option('化工自动化控制仪表作业','16')
		);
		selects['7']=new Array(
			new Option('','0'),
			new Option('烟火药制造作业','1'),
			new Option('黑火药制造作业','2'),
			new Option('引火线制造作业','3'),
			new Option('烟花爆竹产品涉药作业','4'),
			new Option('烟花爆竹储存作业','5')
		);
		function save(){
			if(Validator.Validate(document.myform1,3)){
				var title = document.getElementById('gz');
				var title1 = document.getElementById('gzxl');
				if(title == '0' || title == '')
				{
					alert("工种大类不能为空");
					return false;
				}
				else if(title1 == '0' || title1 == '')
				{
					alert("工种小类不能为空");
					return false;
				}
				else
				{
					document.myform1.action="jshxTzzyPxbSave.action";
					document.myform1.submit();
				}
			}
		}
		
		
		function saves(){
			if(Validator.Validate(document.myform1,3)){
				var title = document.getElementById('gz');
				var title1 = document.getElementById('gzxl');
				if(title == '0' || title == '')
				{
					alert("工种大类不能为空");
					return false;
				}
				else if(title1 == '0' || title1 == '')
				{
					alert("工种小类不能为空");
					return false;
				}
				else
				{
					document.myform1.action="jshxTzzyPxbSaves.action?flag=add";
					document.myform1.submit();
				}
			}
		}
		
		function changeZL(obj){
			var gzxl=document.getElementById("gzxl");
			if(obj != null && obj != "")
			{
				gzxl.options.length=0;
   				for(var i=0;i<selects[obj].length;i++){
   					gzxl.options.add(selects[obj][i]);
   				}
			}
		}
		
		function init()
		{	
			var gz = "${jshxTzzyPxb.gz}";
			var gzxl = "${jshxTzzyPxb.gzxl}";
			var title = document.getElementById('gz');
			for(var i=0;i<title.options.length;i++){
			   if(title.options[i].value == gz){   
			    title.options[i].selected = true;   
			    break;   
			    }   
			} 
			changeZL(gz);
			var title1 = document.getElementById('gzxl');
			for(var i=0;i<title1.options.length;i++){
			   if(title1.options[i].value == gzxl){   
			    title1.options[i].selected = true;   
			    break;   
			    }   
			} 
		}
		
	</script>
	
</head>
<body onload="init();">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxTzzyPxb.id" value="${jshxTzzyPxb.id}">
		<input type="hidden" name="jshxTzzyPxb.createTime" value="<fmt:formatDate type="both" value="${jshxTzzyPxb.createTime}" />">
		<input type="hidden" name="jshxTzzyPxb.updateTime" value="${jshxTzzyPxb.updateTime}">
		<input type="hidden" name="jshxTzzyPxb.createUserID" value="${jshxTzzyPxb.createUserID}">
		<input type="hidden" name="jshxTzzyPxb.updateUserID" value="${jshxTzzyPxb.updateUserID}">
		<input type="hidden" name="jshxTzzyPxb.deptId" value="${jshxTzzyPxb.deptId}">
		<input type="hidden" name="jshxTzzyPxb.delFlag" value="${jshxTzzyPxb.delFlag}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">
						<s:select id="szzname" listKey="key" listValue="value"  theme="simple" list="#{'高铁新城':'高铁新城','度假区':'度假区','开发区':'开发区','北桥街道':'北桥街道'
						,'黄桥街道':'黄桥街道','太平街道':'太平街道','元和街道':'元和街道','阳澄湖镇':'阳澄湖镇','渭塘镇':'渭塘镇','黄埭镇':'黄埭镇','望亭镇':'望亭镇','其它':'其它'}" name="jshxTzzyPxb.szzname" value="{jshxTzzyPxb.szzname}"/>
					</td>
					<th width="15%">工作单位</th>
					<td width="35%">
						<input id="qymc" name="jshxTzzyPxb.qymc" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${jshxTzzyPxb.qymc}"><font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="jshxTzzyPxb.personName" value="${jshxTzzyPxb.personName}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">性别</th>
					<td width="35%">
						<s:select listKey="key" listValue="value"  theme="simple" list="#{'男':'男','女':'女'}" name="jshxTzzyPxb.sex" value="{jshxTzzyPxb.sex}"/>
					</td>
				</tr>
				<tr>
					<th width="15%">学历</th>
					<td width="35%">
						<s:select listKey="key" listValue="value"  theme="simple" list="#{'博士':'博士','硕士':'硕士','本科':'本科','大专':'大专','中专':'中专','高中':'高中','其它':'其它'}" name="jshxTzzyPxb.xl" value="{jshxTzzyPxb.xl}"/>
					</td>
					<th width="15%">理论成绩</th>
					<td width="35%"><input name="jshxTzzyPxb.llcj" value="${jshxTzzyPxb.llcj}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">实践成绩</th>
					<td width="35%"><input name="jshxTzzyPxb.sjcj" value="${jshxTzzyPxb.sjcj}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">特种证号</th>
					<td width="35%"><input name="jshxTzzyPxb.tzzh" value="${jshxTzzyPxb.tzzh}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">培训时间</th>
					<td width="35%"><input name="jshxTzzyPxb.sj" value="${jshxTzzyPxb.sj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">身份证</th>
					<td width="35%"><input name="jshxTzzyPxb.sfz" value="${jshxTzzyPxb.sfz}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">工种大类</th>
					<td width="35%">
						<s:select id="gz" listKey="key" listValue="value"  theme="simple" list="#{'0':' ','1':'电工作业','2':'焊接与热切割作业','3':'高处作业','4':'制冷与空调作业','5':'冶金（有色）生产安全作业','6':'危险化学品安全作业','7':'烟花爆竹安全作业'}" name="jshxTzzyPxb.gz" value="#{jshxTzzyPxb.gz}" onchange="changeZL(this.value);"/>
					</td>
					<th width="15%">工种小类</th>
					<td width="35%">
						<select name="jshxTzzyPxb.gzxl" id="gzxl">
      						<option value="0" selected="selected">&nbsp;&nbsp;</option>
   						</select>
					</td>
				</tr>
				<tr>
					<th width="15%">企业类型</th>
					<td width="35%">
						<s:select listKey="key" listValue="value"  theme="simple" list="#{'化工生产企业':'化工生产企业','危险化学品经营企业':'危险化学品经营企业','烟花爆竹经营企业':'烟花爆竹经营企业','其它工贸企业':'其它工贸企业'}" name="jshxTzzyPxb.qylx" value="{jshxTzzyPxb.qylx}"/>
					</td>
					<th width="15%">领证日期</th>
					<td width="35%"><input name="jshxTzzyPxb.lzrq" value="${jshxTzzyPxb.lzrq}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">有效期</th>
					<td width="35%"><input name="jshxTzzyPxb.yxsj" value="${jshxTzzyPxb.yxsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
					<th width="15%">备注</th>
					<td width="35%"><input name="jshxTzzyPxb.bz" value="${jshxTzzyPxb.bz}" type="text"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="saves()" iconCls="icon-save">继续添加</a>&nbsp;
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

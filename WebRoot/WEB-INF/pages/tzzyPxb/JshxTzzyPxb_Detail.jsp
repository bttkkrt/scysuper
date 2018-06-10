<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
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
		
		function chsel(obj){
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
			chsel(gz);
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
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">
						${jshxTzzyPxb.szzname}
					</td>
					<th width="15%">工作单位</th>
					<td width="35%">
						${jshxTzzyPxb.qymc}
					</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%">${jshxTzzyPxb.personName}</td>
					<th width="15%">性别</th>
					<td width="35%">
						${jshxTzzyPxb.sex}
					</td>
				</tr>
				<tr>
					<th width="15%">学历</th>
					<td width="35%">
						${jshxTzzyPxb.xl}
					</td>
					<th width="15%">理论成绩</th>
					<td width="35%">${jshxTzzyPxb.llcj}</td>
				</tr>
				<tr>
					<th width="15%">实践成绩</th>
					<td width="35%">${jshxTzzyPxb.sjcj}</td>
					<th width="15%">特种证号</th>
					<td width="35%">${jshxTzzyPxb.tzzh}</td>
				</tr>
				<tr>
					<th width="15%">培训时间</th>
					<td width="35%">${jshxTzzyPxb.sj}</td>
					<th width="15%">身份证</th>
					<td width="35%">${jshxTzzyPxb.sfz}</td>
				</tr>
				<tr>
					<th width="15%">工种大类</th>
					<td width="35%">
						<s:select disabled="true" id="gz" listKey="key" listValue="value"  theme="simple" list="#{'0':' ','1':'电工作业','2':'焊接与热切割作业','3':'高处作业','4':'制冷与空调作业','5':'冶金（有色）生产安全作业','6':'危险化学品安全作业','7':'烟花爆竹安全作业'}" name="jshxTzzyPxb.gz" value="#{jshxTzzyPxb.gz}"/>
					</td>
					<th width="15%">工种小类</th>
					<td width="35%">
						<select disabled="true" name="jshxTzzyPxb.gzxl" id="gzxl">
      						<option value="0" selected="selected">&nbsp;&nbsp;</option>
   						</select>
					</td>
				</tr>
				<tr>
					<th width="15%">领证日期</th>
					<td width="35%">${jshxTzzyPxb.lzrq}</td>
					<th width="15%">有效期</th>
					<td width="35%">${jshxTzzyPxb.yxsj}</td>
				</tr>
				<tr>
					<th width="15%">备注</th>
					<td width="85%" colspan="3">
						<textarea style="width:600px;" name="jshxTzzyPxb.bz" disabled="true">${jshxTzzyPxb.bz}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>

<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="whpjyxkSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="whpjyxk.id" value="${whpjyxk.id}">
		<input type="hidden" name="whpjyxk.createTime" value="<fmt:formatDate type="both" value="${whpjyxk.createTime}" />">
		<input type="hidden" name="whpjyxk.updateTime" value="${whpjyxk.updateTime}">
		<input type="hidden" name="whpjyxk.createUserID" value="${whpjyxk.createUserID}">
		<input type="hidden" name="whpjyxk.updateUserID" value="${whpjyxk.updateUserID}">
		<input type="hidden" name="whpjyxk.deptId" value="${whpjyxk.deptId}">
		<input type="hidden" name="whpjyxk.delFlag" value="${whpjyxk.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="10%">月份</th>
					<td width="35%" colspan="11"><input name="whpjyxk.monthTime" style="width: 60%" value="<fmt:formatDate type='both' value='${whpjyxk.monthTime}' pattern="yyyy-MM"/>" datatype="*1-30" errormsg='此项为必填' type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM'})"><font style='color:red'>*</font></td>
				</tr>
				
				   
				<tr>
				     <th rowspan="3" width="10%">类目</th>
				    <td rowspan="2" colspan="2"  width="16%">区域分布</td>
				     <td colspan="9" width="72%">经营许可证</td>
				</tr>
				
				<tr>
				     <td rowspan="2" width="8%">总计</th>
				    <td colspan="3"  width="24%">园区发证</td>
				     <td colspan="5" width="40%">苏州市局发证</td>
				</tr>
				
				<tr>
				     <td width="8%">中新区</th>
				     <td width="8%">街道</td>
				     <td width="8%">无储存</td>
				     <td width="8%">零售</td>
				     <td width="8%">设储存</td>
				     <td width="8%">加油站</td>
				     <td width="8%">剧毒品</td>
				     <td width="8%">易制爆</td>
				     <td width="8%">分装充装稀释</td>
				     <td width="8%">仓储储存</td>
				</tr>     
				
				
				<tr>
					<th width="10%">上月底持证数</th>
					<td width="8%"><input name="whpjyxk.zxqsyd" style="width:60%" value="${whpjyxk.zxqsyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdsyd" style="width:60%" value="${whpjyxk.jdsyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.zjsyd"  style="width:60%" value="${whpjyxk.zjsyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqwccsyd" style="width:60%" value="${whpjyxk.yqwccsyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqlssyd" style="width:60%"  value="${whpjyxk.yqlssyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqsccsyd" style="width:60%" value="${whpjyxk.yqsccsyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jyzsyd" style="width:60%" value="${whpjyxk.jyzsyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdpsyd" style="width:60%" value="${whpjyxk.jdpsyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yzbsyd" style="width:60%" value="${whpjyxk.yzbsyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.fzsyd"  style="width:60%" value="${whpjyxk.fzsyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.ccsyd" style="width:60%" value="${whpjyxk.ccsyd}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
				</tr>
				
				<tr>
					<th width="10%">本月新领证数</th>
					<td width="8%"><input name="whpjyxk.zxqbyxl" style="width:60%" value="${whpjyxk.zxqbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdbyxl" style="width:60%" value="${whpjyxk.jdbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.zjbyxl" style="width:60%" value="${whpjyxk.zjbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqwccbyxl" style="width:60%" value="${whpjyxk.yqwccbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqlsbyxl" style="width:60%" value="${whpjyxk.yqlsbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqsccbyxl" style="width:60%" value="${whpjyxk.yqsccbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jyzbyxl" style="width:60%" value="${whpjyxk.jyzbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdpbyxl" style="width:60%" value="${whpjyxk.jdpbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yzbbyxl" style="width:60%" value="${whpjyxk.yzbbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.fzbyxl" style="width:60%" value="${whpjyxk.fzbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.ccbyxl" style="width:60%" value="${whpjyxk.ccbyxl}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
				</tr>
			
				<tr>
				
					
					<th width="10%">本月换领证数</th>
					<td width="8%"><input name="whpjyxk.zxqbyhz" style="width:60%" value="${whpjyxk.zxqbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdbyhz" style="width:60%" value="${whpjyxk.jdbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.zjbyhz" style="width:60%" value="${whpjyxk.zjbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqwccbyhz" style="width:60%" value="${whpjyxk.yqwccbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqlsbyhz" style="width:60%" value="${whpjyxk.yqlsbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqsccbyhz" style="width:60%" value="${whpjyxk.yqsccbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jyzbyhz" style="width:60%" value="${whpjyxk.jyzbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdpbyhz" style="width:60%" value="${whpjyxk.jdpbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yzbbyhz" style="width:60%" value="${whpjyxk.yzbbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.fzbyhz" style="width:60%" value="${whpjyxk.fzbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.ccbyhz" style="width:60%" value="${whpjyxk.ccbyhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
				</tr>
				
				<tr>
					<th width="10%">本月过期许可证数</th>
					<td width="8%"><input name="whpjyxk.zxqbygq" style="width:60%" value="${whpjyxk.zxqbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdbygq" style="width:60%" value="${whpjyxk.jdbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.zjbygq" style="width:60%" value="${whpjyxk.zjbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqwccbygq" style="width:60%" value="${whpjyxk.yqwccbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqlsbygq" style="width:60%" value="${whpjyxk.yqlsbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqsccbygq" style="width:60%" value="${whpjyxk.yqsccbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jyzbygq" style="width:60%" value="${whpjyxk.jyzbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdpbygq" style="width:60%" value="${whpjyxk.jdpbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yzbbygq" style="width:60%" value="${whpjyxk.yzbbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.fzbygq" style="width:60%" value="${whpjyxk.fzbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.ccbygq" style="width:60%" value="${whpjyxk.ccbygq}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
					
					
					
					
					
					
				
				<tr>
					<th width="10%">其中:申报换证数</th>
					<td width="8%"><input name="whpjyxk.zxqsbhz" style="width:60%" value="${whpjyxk.zxqsbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdsbhz" style="width:60%" value="${whpjyxk.jdsbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.zjsbhz" style="width:60%" value="${whpjyxk.zjsbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqwccsbhz" style="width:60%" value="${whpjyxk.yqwccsbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqlssbhz" style="width:60%" value="${whpjyxk.yqlssbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqsccsbhz" style="width:60%" style="width:60%" value="${whpjyxk.yqsccsbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jyzsbhz" style="width:60%"  value="${whpjyxk.jyzsbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdpsbhz" style="width:60%" value="${whpjyxk.jdpsbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yzbsbhz" style="width:60%" value="${whpjyxk.yzbsbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.fzsbhz" style="width:60%" value="${whpjyxk.fzsbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.ccsbhz" style="width:60%" value="${whpjyxk.ccsbhz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
				</tr>
				
				<tr>
					<th width="10%">本月注销证数</th>
					<td width="8%"><input name="whpjyxk.zxqbyzx" style="width:60%" value="${whpjyxk.zxqbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdbyzx" style="width:60%" value="${whpjyxk.jdbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.zjbyzx" style="width:60%" value="${whpjyxk.zjbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqwccbyzx" style="width:60%" value="${whpjyxk.yqwccbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqlsbyzx" style="width:60%" value="${whpjyxk.yqlsbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqsccbyzx" style="width:60%" value="${whpjyxk.yqsccbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jyzbyzx" style="width:60%" value="${whpjyxk.jyzbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdpbyzx" style="width:60%" value="${whpjyxk.jdpbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yzbbyzx" style="width:60%" value="${whpjyxk.yzbbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.fzbyzx" style="width:60%" value="${whpjyxk.fzbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.ccbyzx" style="width:60%" value="${whpjyxk.ccbyzx}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
					
					
				<tr>
					
					
					<th width="10%">本月底持证数</th>
					<td width="8%"><input name="whpjyxk.zxqbycz" style="width:60%" value="${whpjyxk.zxqbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdbycz" style="width:60%" value="${whpjyxk.jdbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.zjbycz" style="width:60%" value="${whpjyxk.zjbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
					<td width="8%"><input name="whpjyxk.yqwccbycz" style="width:60%" value="${whpjyxk.yqwccbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqlsbycz" style="width:60%" value="${whpjyxk.yqlsbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yqsccbycz" style="width:60%" value="${whpjyxk.yqsccbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jyzbycz" style="width:60%" value="${whpjyxk.jyzbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.jdpbycz" style="width:60%" value="${whpjyxk.jdpbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.yzbbycz" style="width:60%" value="${whpjyxk.yzbbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.fzbycz" style="width:60%" value="${whpjyxk.fzbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					<td width="8%"><input name="whpjyxk.ccbycz"  style="width:60%"value="${whpjyxk.ccbycz}" type="text" datatype="*1-127" errormsg='此项为必填' maxlength="127"><font style='color:red'>*</font></td>
					
					
					
				</tr>
			
				<tr>
					<td colspan="12" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_whpjyxk');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>

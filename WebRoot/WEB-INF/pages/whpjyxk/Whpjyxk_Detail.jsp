<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
	<form name="myform" method="post">
		<div class="page_dialog">
		<div class="inner6px">
			<div class="cell" style="width: ${tableWidth}">
			<table width="100%">
				<tr>
					<th width="15%">月份</th>
					<td width="35%" colspan="11"><fmt:formatDate type="both" value="${whpjyxk.monthTime}" pattern="yyyy-MM"/></td>
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
					<td width="8%" >${whpjyxk.zxqsyd}</td>
					<td width="8%" >${whpjyxk.jdsyd}</td>
					<td width="8%" >${whpjyxk.zjsyd}</td>
					<td width="8%" >${whpjyxk.yqwccsyd}</td>
					<td width="8%" >${whpjyxk.yqlssyd}</td>
					<td width="8%" >${whpjyxk.yqsccsyd}</td>
					<td width="8%" >${whpjyxk.jyzsyd}</td>
					<td width="8%" >${whpjyxk.jdpsyd}</td>
					<td width="8%" >${whpjyxk.yzbsyd}</td>
					<td width="8%" >${whpjyxk.fzsyd}</td>
					<td width="8%" >${whpjyxk.ccsyd}</td>
				</tr>
			
				<tr>
					<th width="10%">本月新领证数</th>
					<td width="8%" >${whpjyxk.zxqbyxl}</td>
					<td width="8%" >${whpjyxk.jdbyxl}</td>
					<td width="8%" >${whpjyxk.zjbyxl}</td>
					<td width="8%" >${whpjyxk.yqwccbyxl}</td>
					<td width="8%" >${whpjyxk.yqlsbyxl}</td>
					<td width="8%" >${whpjyxk.yqsccbyxl}</td>
					<td width="8%" >${whpjyxk.jyzbyxl}</td>
					<td width="8%" >${whpjyxk.jdpbyxl}</td>
					<td width="8%" >${whpjyxk.yzbbyxl}</td>
					<td width="8%" >${whpjyxk.fzbyxl}</td>
					<td width="8%" >${whpjyxk.ccbyxl}</td>
				</tr>
				
				<tr>
					<th width="10%">本月换证数</th>
					<td width="8%" >${whpjyxk.zxqbyhz}</td>
					<td width="8%" >${whpjyxk.jdbyhz}</td>
					<td width="8%" >${whpjyxk.zjbyhz}</td>
					<td width="8%" >${whpjyxk.yqwccbyhz}</td>
					<td width="8%" >${whpjyxk.yqlsbyhz}</td>
					<td width="8%" >${whpjyxk.yqsccbyhz}</td>
					<td width="8%" >${whpjyxk.jyzbyhz}</td>
					<td width="8%" >${whpjyxk.jdpbyhz}</td>
					<td width="8%" >${whpjyxk.yzbbyhz}</td>
					<td width="8%" >${whpjyxk.fzbyhz}</td>
					<td width="8%" >${whpjyxk.ccbyhz}</td>
					
				</tr>
		
				<tr>
					<th width="10%">本月过期许可证数</th>
					<td width="8%" >${whpjyxk.zxqbygq}</td>
					<td width="8%" >${whpjyxk.jdbygq}</td>
					<td width="8%" >${whpjyxk.zjbygq}</td>
					<td width="8%" >${whpjyxk.yqwccbygq}</td>
					<td width="8%" >${whpjyxk.yqlsbygq}</td>
					<td width="8%" >${whpjyxk.yqsccbygq}</td>
					<td width="8%" >${whpjyxk.jyzbygq}</td>
					<td width="8%" >${whpjyxk.jdpbygq}</td>
					<td width="8%" >${whpjyxk.yzbbygq}</td>
					<td width="8%" >${whpjyxk.fzbygq}</td>
					<td width="8%" >${whpjyxk.ccbygq}</td>
					
				</tr>
				
				<tr>
				
					
					<th width="10%">其中：申报换证数</th>
					<td width="8%" >${whpjyxk.zxqsbhz}</td>
					<td width="8%" >${whpjyxk.jdsbhz}</td>
					<td width="8%" >${whpjyxk.zjsbhz}</td>
					<td width="8%" >${whpjyxk.yqwccsbhz}</td>
					<td width="8%" >${whpjyxk.yqlssbhz}</td>
					<td width="8%" >${whpjyxk.yqsccsbhz}</td>
					<td width="8%" >${whpjyxk.jyzsbhz}</td>
					<td width="8%" >${whpjyxk.jdpsbhz}</td>
					<td width="8%" >${whpjyxk.yzbsbhz}</td>
					<td width="8%" >${whpjyxk.fzsbhz}</td>
					<td width="8%" >${whpjyxk.ccsbhz}</td>
				</tr>
				
				<tr>
					<th width="10%">本月注销证数</th>
					<td width="8%" >${whpjyxk.zxqbyzx}</td>
					<td width="8%" >${whpjyxk.jdbyzx}</td>
					<td width="8%" >${whpjyxk.zjbyzx}</td>
					<td width="8%" >${whpjyxk.yqwccbyzx}</td>
					<td width="8%" >${whpjyxk.yqlsbyzx}</td>
					<td width="8%" >${whpjyxk.yqsccbyzx}</td>
					<td width="8%" >${whpjyxk.jyzbyzx}</td>
					<td width="8%" >${whpjyxk.jdpbyzx}</td>
					<td width="8%" >${whpjyxk.yzbbyzx}</td>
					<td width="8%" >${whpjyxk.fzbyzx}</td>
					<td width="8%" >${whpjyxk.ccbyzx}</td>
					
				</tr>
			
				
				<tr>
					<th width="10%">本月底持证数</th>
					<td width="8%" >${whpjyxk.zxqbycz}</td>
					<td width="8%" >${whpjyxk.jdbycz}</td>
					<td width="8%" >${whpjyxk.zjbycz}</td>
					<td width="8%" >${whpjyxk.yqwccbycz}</td>
					<td width="8%" >${whpjyxk.yqlsbycz}</td>
					<td width="8%" >${whpjyxk.yqsccbycz}</td>
					<td width="8%" >${whpjyxk.jyzbycz}</td>
					<td width="8%" >${whpjyxk.jdpbycz}</td>
					<td width="8%" >${whpjyxk.yzbbycz}</td>
					<td width="8%" >${whpjyxk.fzbycz}</td>
					<td width="8%" >${whpjyxk.ccbycz}</td>
				</tr>
				
				<tr>
					<td colspan="12" height="100px" style="text-align:center">
					    <a href="#" class="btn_01" onclick="parent.close_win('win_whpjyxk');">关闭<b></b></a>
					</td>
				</tr>
			</table>
		</div></div></div>
	</form>
</body>
</html>

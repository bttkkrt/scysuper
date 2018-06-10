<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>乡镇检查危险化学品企业、烟花爆竹企业和其它工贸企业统计表</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
        function search_tj(){
        	 
          	document.myform.action ="${ctx}/jsp/commonTrouble/commoTroubleXzjcTongJi.action";
          	document.myform.submit();
          	 }
          	 
         //清除查询表单中的搜索条件
        function clear_form(ff){
     
            var elements = ff.elements;
            for(i=0;i<elements.length;i++){
                var element = elements[i];
                if(element.type=="text"){
                    element.value = "";
                }else if(element.options!=null){
                	element.options[0].selected  = true;
                }
            }
        }
        
        function exportdata()
        {
        	document.myform.action = "${ctx}/jsp/commonTrouble/commoTroubleXzjcExport.action";
        		document.myform.submit();
        	
        }
    </script>
</head>

<body>
<form name="myform" method="post">
	<input type="hidden" name="flag" value="1"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">检查时间</th>
				<td width="35%"><input name="queryJhwcsjStart" id="queryJhwcsjStart" value='${queryJhwcsjStart}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryJhwcsjEnd\')}'})" >
					-<input name="queryJhwcsjEnd" id="queryJhwcsjEnd" value='${queryJhwcsjEnd}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryJhwcsjStart\')}'})" ></td>
			</tr>
			
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
					<a href="###" class="easyui-linkbutton" onclick="search_tj()" iconCls="icon-search">查询</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;	
					<a href="###" class="easyui-linkbutton" onclick="exportdata()" iconCls="icon-add">导出</a>&nbsp;							
				</td>
			</tr>
		</table>
	</div>
	
	
</form>


<form>
	<title></title>
		<style>
	th
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 2px 0;
		white-space: nowrap;
	}
	td
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 2px 0;
		background:#fff;
		white-space: nowrap;
	}
	tr
	{
		border:1px solid #d5dbdc;
		height:24px
	}
	</style>
				<div style="background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0;overflow:scroll;width:100%;">
				<table style="border-collapse:collapse;border:1px solid #d5dbdc;">
					<tr>
						<th rowspan="3">序号</th>
						<th rowspan="3">部门</th>
						<th rowspan="3">现场检查记录</th>
						<th rowspan="3">责令限期整改指令书</th>
						<th rowspan="3">整改复查意见书</th>
						<th rowspan="3">强制措施决定书</th>
						<th colspan="3">检查发现隐患数</th>
						<th colspan="3">隐患整改数</th>
						<th colspan="3">未整改隐患数</th>
						<th rowspan="3">重大隐患整改率</th>
						<th rowspan="3">整改率</th>
						<th colspan="16">化工生产企业</th>
						<th colspan="16">危险化学品经营企业</th>
						<th colspan="16">烟花爆竹经营企业</th>
						<th colspan="16">工贸企业</th>
						<th colspan="6">危险化学品使用企业</th>
						<th colspan="6">安全生产标准化达标企业</th>
						<th colspan="6">职业危害企业</th>
					</tr>
					<tr>
						<th rowspan="2">隐患总数</th>
						<th rowspan="2">重大隐患数</th>
						<th rowspan="2">其中：挂牌重大隐患数</th>
						<th rowspan="2">隐患总数</th>
						<th rowspan="2">重大隐患数</th>
						<th rowspan="2">其中：挂牌重大隐患数</th>
						<th rowspan="2">隐患总数</th>
						<th rowspan="2">重大隐患数</th>
						<th rowspan="2">其中：挂牌重大隐患数</th>
						<th rowspan="2">化工生产企业数</th>
						<th rowspan="2">检查化工生产企业个数</th>
						<th rowspan="2">检查化工生产企业覆盖率</th>
						<th rowspan="2">检查化工生产企业次数</th>
						<th rowspan="2">检查化工生产企业复查率</th>
						<th colspan="3">发现隐患数</th>
						<th colspan="3">隐患整改数</th>
						<th colspan="3">未整改隐患数</th>
						<th rowspan="2">重大隐患整改率</th>
						<th rowspan="2">隐患整改率</th>
						<th rowspan="2">危化品经营企业数</th>
						<th rowspan="2">检查危化品经营企业个数</th>
						<th rowspan="2">检查危化品经营企业覆盖率</th>
						<th rowspan="2">检查危化品经营企业次数</th>
						<th rowspan="2">检查危化品经营企业复查率</th>
						<th colspan="3">发现隐患数</th>
						<th colspan="3">隐患整改数</th>
						<th colspan="3">未整改隐患数</th>
						<th rowspan="2">重大隐患整改率</th>
						<th rowspan="2">隐患整改率</th>
						<th rowspan="2">烟花爆竹经营企业数</th>
						<th rowspan="2">检查烟花爆竹经营企业个数</th>
						<th rowspan="2">检查烟花爆竹覆盖率</th>
						<th rowspan="2">检查烟花爆竹经营企业次数</th>
						<th rowspan="2">检查烟花爆竹复查率</th>
						<th colspan="3">发现隐患数</th>
						<th colspan="3">隐患整改数</th>
						<th colspan="3">未整改隐患数</th>
						<th rowspan="2">重大隐患整改率</th>
						<th rowspan="2">隐患整改率</th>
						<th rowspan="2">工贸企业数</th>
						<th rowspan="2">检查工贸企业个数</th>
						<th rowspan="2">检查工贸企业覆盖率</th>
						<th rowspan="2">检查工贸企业次数</th>
						<th rowspan="2">检查工贸企业复查率</th>
						<th colspan="3">发现隐患数</th>
						<th colspan="3">隐患整改数</th>
						<th colspan="3">未整改隐患数</th>
						<th rowspan="2">重大隐患整改率</th>
						<th rowspan="2">隐患整改率</th>
						<th rowspan="2">危险化学品使用企业数</th>
						<th rowspan="2">检查企业数</th>
						<th rowspan="2">发现隐患数</th>
						<th rowspan="2">隐患整改数</th>
						<th rowspan="2">未整改隐患数</th>
						<th rowspan="2">隐患整改率</th>
						<th rowspan="2">标准化达标企业总数</th>
						<th rowspan="2">检查标准化达标企业数</th>
						<th rowspan="2">发现隐患数</th>
						<th rowspan="2">隐患整改数</th>
						<th rowspan="2">未整改隐患数</th>
						<th rowspan="2">隐患整改率</th>
						<th rowspan="2">职业危害企业数</th>
						<th rowspan="2">检查企业数</th>
						<th rowspan="2">发现隐患数</th>
						<th rowspan="2">隐患整改数</th>
						<th rowspan="2">未整改隐患数</th>
						<th rowspan="2">隐患整改率</th>
					</tr>
					<tr>
						<th>隐患总数</th>
						<th>重大隐患数</th>
						<th>其中：挂牌重大隐患数</th>
						<th>隐患整改数</th>
						<th>重大隐患整改数</th>
						<th>其中：挂牌重大隐患整改数</th>
						<th>未整改隐患数</th>
						<th>重大隐患未整改数</th>
						<th>其中：挂牌重大隐患未整改数</th>
						<th>隐患总数</th>
						<th>重大隐患数</th>
						<th>其中：挂牌重大隐患数</th>
						<th>隐患整改数</th>
						<th>重大隐患整改数</th>
						<th>其中：挂牌重大隐患整改数</th>
						<th>未整改隐患数</th>
						<th>重大隐患未整改数</th>
						<th>其中：挂牌重大隐患未整改数</th>
						<th>隐患总数</th>
						<th>重大隐患数</th>
						<th>其中：挂牌重大隐患数</th>
						<th>隐患整改数</th>
						<th>重大隐患整改数</th>
						<th>其中：挂牌重大隐患整改数</th>
						<th>未整改隐患数</th>
						<th>重大隐患未整改数</th>
						<th>其中：挂牌重大隐患未整改数</th>
						<th>隐患总数</th>
						<th>重大隐患数</th>
						<th>其中：挂牌重大隐患数</th>
						<th>隐患整改数</th>
						<th>重大隐患整改数</th>
						<th>其中：挂牌重大隐患整改数</th>
						<th>未整改隐患数</th>
						<th>重大隐患未整改数</th>
						<th>其中：挂牌重大隐患未整改数</th>
					</tr>
					<s:iterator value="ksjcList" id="ksjcBean" status="sta">
						<tr>
							<td><s:property value='#sta.count'/></td>
							<td><s:property value='#ksjcBean.szzname'/></td>
							<td><s:property value='#ksjcBean.data1'/></td>
							<td><s:property value='#ksjcBean.data2'/></td>
							<td><s:property value='#ksjcBean.data3'/></td>
							<td><s:property value='#ksjcBean.data4'/></td>
							<td><s:property value='#ksjcBean.data5'/></td>
							<td><s:property value='#ksjcBean.data6'/></td>
							<td><s:property value='#ksjcBean.data7'/></td>
							<td><s:property value='#ksjcBean.data8'/></td>
							<td><s:property value='#ksjcBean.data9'/></td>
							<td><s:property value='#ksjcBean.data10'/></td>
							<td><s:property value='#ksjcBean.data11'/></td>
							<td><s:property value='#ksjcBean.data12'/></td>
							<td><s:property value='#ksjcBean.data13'/></td>
							<td><s:property value='#ksjcBean.data14'/></td>
							<td><s:property value='#ksjcBean.data15'/></td>
							<td><s:property value='#ksjcBean.data16'/></td>
							<td><s:property value='#ksjcBean.data17'/></td>
							<td><s:property value='#ksjcBean.data18'/></td>
							<td><s:property value='#ksjcBean.data19'/></td>
							<td><s:property value='#ksjcBean.data20'/></td>
							<td><s:property value='#ksjcBean.data21'/></td>
							<td><s:property value='#ksjcBean.data22'/></td>
							<td><s:property value='#ksjcBean.data23'/></td>
							<td><s:property value='#ksjcBean.data24'/></td>
							<td><s:property value='#ksjcBean.data25'/></td>
							<td><s:property value='#ksjcBean.data26'/></td>
							<td><s:property value='#ksjcBean.data27'/></td>
							<td><s:property value='#ksjcBean.data28'/></td>
							<td><s:property value='#ksjcBean.data29'/></td>
							<td><s:property value='#ksjcBean.data30'/></td>
							<td><s:property value='#ksjcBean.data31'/></td>
							<td><s:property value='#ksjcBean.data32'/></td>
							<td><s:property value='#ksjcBean.data33'/></td>
							<td><s:property value='#ksjcBean.data34'/></td>
							<td><s:property value='#ksjcBean.data35'/></td>
							<td><s:property value='#ksjcBean.data36'/></td>
							<td><s:property value='#ksjcBean.data37'/></td>
							<td><s:property value='#ksjcBean.data38'/></td>
							<td><s:property value='#ksjcBean.data39'/></td>
							<td><s:property value='#ksjcBean.data40'/></td>
							<td><s:property value='#ksjcBean.data41'/></td>
							<td><s:property value='#ksjcBean.data42'/></td>
							<td><s:property value='#ksjcBean.data43'/></td>
							<td><s:property value='#ksjcBean.data44'/></td>
							<td><s:property value='#ksjcBean.data45'/></td>
							<td><s:property value='#ksjcBean.data46'/></td>
							<td><s:property value='#ksjcBean.data47'/></td>
							<td><s:property value='#ksjcBean.data48'/></td>
							<td><s:property value='#ksjcBean.data49'/></td>
							<td><s:property value='#ksjcBean.data50'/></td>
							<td><s:property value='#ksjcBean.data51'/></td>
							<td><s:property value='#ksjcBean.data52'/></td>
							<td><s:property value='#ksjcBean.data53'/></td>
							<td><s:property value='#ksjcBean.data54'/></td>
							<td><s:property value='#ksjcBean.data55'/></td>
							<td><s:property value='#ksjcBean.data56'/></td>
							<td><s:property value='#ksjcBean.data57'/></td>
							<td><s:property value='#ksjcBean.data58'/></td>
							<td><s:property value='#ksjcBean.data59'/></td>
							<td><s:property value='#ksjcBean.data60'/></td>
							<td><s:property value='#ksjcBean.data61'/></td>
							<td><s:property value='#ksjcBean.data62'/></td>
							<td><s:property value='#ksjcBean.data63'/></td>
							<td><s:property value='#ksjcBean.data64'/></td>
							<td><s:property value='#ksjcBean.data65'/></td>
							<td><s:property value='#ksjcBean.data66'/></td>
							<td><s:property value='#ksjcBean.data67'/></td>
							<td><s:property value='#ksjcBean.data68'/></td>
							<td><s:property value='#ksjcBean.data69'/></td>
							<td><s:property value='#ksjcBean.data70'/></td>
							<td><s:property value='#ksjcBean.data71'/></td>
							<td><s:property value='#ksjcBean.data72'/></td>
							<td><s:property value='#ksjcBean.data73'/></td>
							<td><s:property value='#ksjcBean.data74'/></td>
							<td><s:property value='#ksjcBean.data75'/></td>
							<td><s:property value='#ksjcBean.data76'/></td>
							<td><s:property value='#ksjcBean.data77'/></td>
							<td><s:property value='#ksjcBean.data78'/></td>
							<td><s:property value='#ksjcBean.data79'/></td>
							<td><s:property value='#ksjcBean.data80'/></td>
							<td><s:property value='#ksjcBean.data81'/></td>
							<td><s:property value='#ksjcBean.data82'/></td>
							<td><s:property value='#ksjcBean.data83'/></td>
							<td><s:property value='#ksjcBean.data84'/></td>
							<td><s:property value='#ksjcBean.data85'/></td>
							<td><s:property value='#ksjcBean.data86'/></td>
							<td><s:property value='#ksjcBean.data87'/></td>
							<td><s:property value='#ksjcBean.data88'/></td>
							<td><s:property value='#ksjcBean.data89'/></td>
							<td><s:property value='#ksjcBean.data90'/></td>
							<td><s:property value='#ksjcBean.data91'/></td>
							<td><s:property value='#ksjcBean.data92'/></td>
							<td><s:property value='#ksjcBean.data93'/></td>
							<td><s:property value='#ksjcBean.data94'/></td>
							<td><s:property value='#ksjcBean.data95'/></td>
							<td><s:property value='#ksjcBean.data96'/></td>
							<td><s:property value='#ksjcBean.data97'/></td>
						</tr>
					</s:iterator>
					<tr>
						<th colspan="2">合计</th>
						<th>${ksjcBean.data1}</th>
						<th>${ksjcBean.data2}</th>
						<th>${ksjcBean.data3}</th>
						<th>${ksjcBean.data4}</th>
						<th>${ksjcBean.data5}</th>
						<th>${ksjcBean.data6}</th>
						<th>${ksjcBean.data7}</th>
						<th>${ksjcBean.data8}</th>
						<th>${ksjcBean.data9}</th>
						<th>${ksjcBean.data10}</th>
						<th>${ksjcBean.data11}</th>
						<th>${ksjcBean.data12}</th>
						<th>${ksjcBean.data13}</th>
						<th>${ksjcBean.data14}</th>
						<th>${ksjcBean.data15}</th>
						<th>${ksjcBean.data16}</th>
						<th>${ksjcBean.data17}</th>
						<th>${ksjcBean.data18}</th>
						<th>${ksjcBean.data19}</th>
						<th>${ksjcBean.data20}</th>
						<th>${ksjcBean.data21}</th>
						<th>${ksjcBean.data22}</th>
						<th>${ksjcBean.data23}</th>
						<th>${ksjcBean.data24}</th>
						<th>${ksjcBean.data25}</th>
						<th>${ksjcBean.data26}</th>
						<th>${ksjcBean.data27}</th>
						<th>${ksjcBean.data28}</th>
						<th>${ksjcBean.data29}</th>
						<th>${ksjcBean.data30}</th>
						<th>${ksjcBean.data31}</th>
						<th>${ksjcBean.data32}</th>
						<th>${ksjcBean.data33}</th>
						<th>${ksjcBean.data34}</th>
						<th>${ksjcBean.data35}</th>
						<th>${ksjcBean.data36}</th>
						<th>${ksjcBean.data37}</th>
						<th>${ksjcBean.data38}</th>
						<th>${ksjcBean.data39}</th>
						<th>${ksjcBean.data40}</th>
						<th>${ksjcBean.data41}</th>
						<th>${ksjcBean.data42}</th>
						<th>${ksjcBean.data43}</th>
						<th>${ksjcBean.data44}</th>
						<th>${ksjcBean.data45}</th>
						<th>${ksjcBean.data46}</th>
						<th>${ksjcBean.data47}</th>
						<th>${ksjcBean.data48}</th>
						<th>${ksjcBean.data49}</th>
						<th>${ksjcBean.data50}</th>
						<th>${ksjcBean.data51}</th>
						<th>${ksjcBean.data52}</th>
						<th>${ksjcBean.data53}</th>
						<th>${ksjcBean.data54}</th>
						<th>${ksjcBean.data55}</th>
						<th>${ksjcBean.data56}</th>
						<th>${ksjcBean.data57}</th>
						<th>${ksjcBean.data58}</th>
						<th>${ksjcBean.data59}</th>
						<th>${ksjcBean.data60}</th>
						<th>${ksjcBean.data61}</th>
						<th>${ksjcBean.data62}</th>
						<th>${ksjcBean.data63}</th>
						<th>${ksjcBean.data64}</th>
						<th>${ksjcBean.data65}</th>
						<th>${ksjcBean.data66}</th>
						<th>${ksjcBean.data67}</th>
						<th>${ksjcBean.data68}</th>
						<th>${ksjcBean.data69}</th>
						<th>${ksjcBean.data70}</th>
						<th>${ksjcBean.data71}</th>
						<th>${ksjcBean.data72}</th>
						<th>${ksjcBean.data73}</th>
						<th>${ksjcBean.data74}</th>
						<th>${ksjcBean.data75}</th>
						<th>${ksjcBean.data76}</th>
						<th>${ksjcBean.data77}</th>
						<th>${ksjcBean.data78}</th>
						<th>${ksjcBean.data79}</th>
						<th>${ksjcBean.data80}</th>
						<th>${ksjcBean.data81}</th>
						<th>${ksjcBean.data82}</th>
						<th>${ksjcBean.data83}</th>
						<th>${ksjcBean.data84}</th>
						<th>${ksjcBean.data85}</th>
						<th>${ksjcBean.data86}</th>
						<th>${ksjcBean.data87}</th>
						<th>${ksjcBean.data88}</th>
						<th>${ksjcBean.data89}</th>
						<th>${ksjcBean.data90}</th>
						<th>${ksjcBean.data91}</th>
						<th>${ksjcBean.data92}</th>
						<th>${ksjcBean.data93}</th>
						<th>${ksjcBean.data94}</th>
						<th>${ksjcBean.data95}</th>
						<th>${ksjcBean.data96}</th>
						<th>${ksjcBean.data97}</th>
					</tr>
				</table>
			</div>
</form>
</body>
</html>
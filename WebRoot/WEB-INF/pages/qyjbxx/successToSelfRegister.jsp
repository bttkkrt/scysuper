<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

 <script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
 <script src="${ctx}/webResources/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link href="${ctx}/webResources/qyzc/css/form.css" rel="stylesheet" type="text/css" />
<script>
$(function(){
        for(var i=1;i<3;i++)
        	{
        		document.getElementById('tab'+i).style.visibility="hidden";
        		$("#tab"+i).hide();
        	}
        })

        function chanageDiv(obj){
        	for(var i=0;i<3;i++)
        	{
        		document.getElementById('tab'+i).style.visibility="hidden";
        		$("#tab"+i).hide();
        	}
        	document.getElementById(obj).style.visibility="visible";
        	$("#"+obj).show();
        }
        function save(){
				
				document.myform1.action="${ctx}/jsp/qyjbxx/entBaseInfoSaveRegister.action";
				document.myform1.submit();
		}
</script>


</head>

<body >
<form name="myform1" method="post" enctype="multipart/form-data">
<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="entBaseInfo.id" value="${entBaseInfo.id}">
		<input type="hidden" name="entBaseInfo.createTime" value="<fmt:formatDate type="both" value="${entBaseInfo.createTime}" />">
		<input type="hidden" name="entBaseInfo.updateTime" value="${entBaseInfo.updateTime}">
		<input type="hidden" name="entBaseInfo.createUserID" value="${entBaseInfo.createUserID}">
		<input type="hidden" name="entBaseInfo.updateUserID" value="${entBaseInfo.updateUserID}">
		<input type="hidden" name="entBaseInfo.deptId" value="${entBaseInfo.deptId}">
		<input type="hidden" name="entBaseInfo.delFlag" value="${entBaseInfo.delFlag}">
<div class="topbar">
	<a href="#"><img src="${ctx}/webResources/qyzc/images/default/reg_logo.png"/></a>
    <div class="user">新会员注册</div>
</div>

<div class="af_add" >
    <div class="padd20">
    <!--添加步骤1-->
    <div  class="addcontent">
        <div class="steps steps-3">
            <ul>
                <li   onclick="chanageDiv('tab0');"><i>1</i>企业用户信息填写</li>
                <li   onclick="chanageDiv('tab1');"><i>2</i>填写详细信息</li>
                <li  class="active"><i>3</i>完成注册</li>
            </ul>	
        </div>
        <div  class="steps_main" >
            <div id="tab0" class="steps_01" style="overflow-y:auto; height:400px;visibility:hidden;">
                <table width="80%"  border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <th width="18%"><span class="red">*</span>用户名：</th>
                        <td width="62%"><input name="entBaseInfo.loginId" value="${entBaseInfo.loginId}" type="text" style="width:300px"></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>密码：</th>
                        <td><input name="entBaseInfo.password" value="${entBaseInfo.password}" type="password" style="width:300px"></td>
                    </tr>
                    <tr>
                        <th><span class="red">*</span>重新输入密码：</th>
                        <td><input name="" type="text" style="width:300px"/></td>
                    </tr>  
                    <tr>
                        <th><span class="red">*</span>企业名称：</th>
                        <td><input name="entBaseInfo.enterpriseName" value="${entBaseInfo.enterpriseName}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>组织机构代码：</th>
                        <td><input name="entBaseInfo.enterpriseCode" value="${entBaseInfo.enterpriseCode}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th></th>
                        <td><a onclick="chanageDiv('tab1');" class="btn_next">下一步</a></td>
                    </tr>                                        
                </table>
            </div>            
        
    
    <!--/添加步骤1 end-->
    
    <!--添加步骤2-->
   
            <div id="tab1" class="steps_01" style="overflow-y:auto; height:565px;visibility:hidden;">
                <table width="80%"  border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <th width="18%"><span class="red">*</span>工商注册号：</th>
                        <td width="62%"><input name="entBaseInfo.registrationNumber" value="${entBaseInfo.registrationNumber}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>地址：</th>
                        <td><input name="entBaseInfo.enterpriseAddress" value="${entBaseInfo.enterpriseAddress}" type="text" style="width:300px"/></td>
                    </tr>
                    <tr>
                        <th><span class="red">*</span>企业属地：</th>
                        <td><cus:SelectOneTag property="entBaseInfo.enterprisePossession" defaultText='请选择' codeName="企业属地" value="${entBaseInfo.enterprisePossession}" style="width:300px"/></td>
                    </tr>  
                    <tr>
                        <th><span class="red">*</span>邮政编码：</th>
                        <td><input name="entBaseInfo.enterpriseZipcode" value="${entBaseInfo.enterpriseZipcode}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>企业性质：</th>
                        <td><cus:SelectOneTag property="entBaseInfo.enterpriseNature" defaultText='请选择' codeName="企业性质" value="${entBaseInfo.enterpriseNature}" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>企业规模：</th>
                        <td><cus:SelectOneTag property="entBaseInfo.enterpriseScale" defaultText='请选择' codeName="企业规模" value="${entBaseInfo.enterpriseScale}" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>企业分类：</th>
                        <td><cus:hxcheckbox property="entBaseInfo.enterpriseType" codeName="企业分类" value="${entBaseInfo.enterpriseType}" /></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>行业类别：</th>
                        <td><cus:SelectOneTag property="entBaseInfo.enterpriseCategory" defaultText='请选择' codeName="行业类别" value="${entBaseInfo.enterpriseCategory}" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>经营范围：</th>
                        <td width="85%" colspan="3">
						<textarea id="entBaseInfo.enterpriseScope" name="entBaseInfo.enterpriseScope" rows="5" style="width: 80%">${entBaseInfo.enterpriseScope}</textarea>
					    </td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>投资方国籍：</th>
                        <td><cus:SelectOneTag property="entBaseInfo.enterpriseNationnality" defaultText='请选择' codeName="国籍" value="${entBaseInfo.enterpriseNationnality}" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>法人代表姓名：</th>
                        <td><input name="entBaseInfo.enterpriseLegalName" value="${entBaseInfo.enterpriseLegalName}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>法人代表性别：</th>
                        <td><cus:SelectOneTag property="entBaseInfo.enterpriseLegalSex" defaultText='请选择' codeName="性别" value="${entBaseInfo.enterpriseLegalSex}" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>法人代表年龄：</th>
                        <td><input name="entBaseInfo.enterpriseLegalAge" value="${entBaseInfo.enterpriseLegalAge}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>法人代表联系电话：</th>
                        <td><input name="entBaseInfo.enterpriseLegalPhone" value="${entBaseInfo.enterpriseLegalPhone}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>法人代表证件号码：</th>
                        <td><input name="entBaseInfo.enterpriseLegalCardnum" value="${entBaseInfo.enterpriseLegalCardnum}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>法人代表电子邮箱：</th>
                        <td><input name="entBaseInfo.enterpriseLegalEmail" value="${entBaseInfo.enterpriseLegalEmail}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>法人代表职务：</th>
                        <td><input name="entBaseInfo.enterpriseLegalZw" value="${entBaseInfo.enterpriseLegalZw}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>企业设立日期：</th>
                        <td><input name="entBaseInfo.enterpriseFoundDate" value="<fmt:formatDate type='date' value='${entBaseInfo.enterpriseFoundDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:300px"></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>企业投产日期：</th>
                        <td><input name="entBaseInfo.enterpriseProductDate" value="<fmt:formatDate type='date' value='${entBaseInfo.enterpriseProductDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:300px"></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>注册资本：</th>
                        <td><input name="entBaseInfo.enterpriseRegisterMoney" value="${entBaseInfo.enterpriseRegisterMoney}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>投资总额：</th>
                        <td><input name="entBaseInfo.enterpriseInvestMoney" value="${entBaseInfo.enterpriseInvestMoney}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>固定资产总额：</th>
                        <td><input name="entBaseInfo.enterpriseFixedassetMoney" value="${entBaseInfo.enterpriseFixedassetMoney}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>占地面积：</th>
                        <td><input name="entBaseInfo.enterpriseFloorArea" value="${entBaseInfo.enterpriseFloorArea}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>建筑面积：</th>
                        <td><input name="entBaseInfo.enterpriseBuildArea" value="${entBaseInfo.enterpriseBuildArea}"type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>办公楼建筑面积：</th>
                        <td><input name="entBaseInfo.enterpriseOfficeArea" value="${entBaseInfo.enterpriseOfficeArea}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>车间厂房建筑面积：</th>
                        <td><input name="entBaseInfo.enterpriseWorkshopArea" value="${entBaseInfo.enterpriseWorkshopArea}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>仓库建筑面积：</th>
                        <td><input name="entBaseInfo.enterpriseWearhouseArea" value="${entBaseInfo.enterpriseWearhouseArea}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>员工总数：</th>
                        <td><input name="entBaseInfo.enterpriseStaffCount" value="${entBaseInfo.enterpriseStaffCount}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>管理人员数：</th>
                        <td><input name="entBaseInfo.enterpriseManagerCount" value="${entBaseInfo.enterpriseManagerCount}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>工人数：</th>
                        <td><input name="entBaseInfo.enterpriseWorkerCount" value="${entBaseInfo.enterpriseWorkerCount}" type="text" style="width:300px"/></td>
                    </tr> 
                    <tr>
                        <th></th>
                        <td><a href="#" class="btn_next" onclick="save()">提交</a></td>
                    </tr> 
                                       
                </table>
            </div>

    <!--/添加步骤2 end-->    
    
    <!--添加步骤3-->
  
      

            <div id="tab2" class="steps_03" style="overflow-y:auto; height:400px;visibility:visible;">
            	<img src="${ctx}/webResources/qyzc/images/default/icon_ok.png"/>
				<p>恭喜您，添加成功！</p>
            </div>            
       
   
    <!--/添加步骤3 end-->    
       </div>
      </div>  
    </div>
</div>

<!--底部-->
<div class="bottom">
    <span>Copyright ©2015</span>
    <span>苏州工业园区安全生产监督管理局  版权所有.</span>
    <span>ALL RIGHTS RESERVED.</span>
    <p><span>苏ICP备08103496号-4</span>
    <span><a href="#">技术支持：南京拓构软件有限公司</a></span>
    </p>
</div>
<!--/底部-->
</form>
</body>
</html>

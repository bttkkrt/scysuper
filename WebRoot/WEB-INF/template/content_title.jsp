<%@ page language="java" pageEncoding="UTF-8"%>

<script>
function doReturnOrClose(backtimes) {
    if (this.parent != null && this.opener == null) {
        history.go(backtimes);
    }
    else {
        window.close();
    }
}
function showError() {
  if (document.getElementById("errorMessages") != null) {
            document.getElementById("errorMessages").style.display = 'block';
        }

}
</script>
<div class="pages_right_main">
<!-- 
<div class="location">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td class="header01"></td>
		<td class="headerbg" valign="middle" align="left">&nbsp;<img src="${ctx}/webResources/themes/${curr_user.cssId}/images/default/location_bg_01.jpg" /><edp:showCurrPath moduleCode="${currModuleCode}" customPath="${curr_path}"/>&nbsp;
		</td>
		<td class="header01">
		<div style="cursor: hand; width: 100%; height: 100%;" title="查看异常"
			onclick='javascript:showError();'> </div>
		</td>
		<td class="header02">
		<div style="cursor: hand; width: 100%; height: 100%;" title="页面回退"
			onclick='javascript:doReturnOrClose(-1);'></div>
		</td>
	
	</tr>
</table>
</div> -->
<table width="100%" style="height:95%" border="0" cellspacing="0"
	cellpadding="0">
	<tr>
		<td class="content0201"></td>
		<td valign="top" height="100%">
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
</script>
<div class="pages_right_main">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<tr>
		<td class="content0101"></td>
		<td class="content0102" valign="middle" align="left"></td>
		<td class="content0103"></td>
	</tr>
	<tr>
		<td class="content0201" height="100%"></td>
		<td valign="top">
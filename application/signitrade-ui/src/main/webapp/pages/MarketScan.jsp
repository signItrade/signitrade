<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
</head>
 
<body>

<h2>Market Scan</h2>
<s:if test="securityDetailsEODList.size() > 0">
<table border="1px" cellpadding="8px">
	<tr>
		<th>MA</th>
		<th>MACD</th>
		<th>STO</th>
		<th>CHA</th>
	</tr>
	<s:iterator value="securityDetailsEODList" status="userStatus">
		<tr>
			<td><s:property value="ma" /></td>
			<td><s:property value="macd" /></td>
			<td><s:property value="sto" /></td>
			<td><s:property value="cha" /></td>
		</tr>
	</s:iterator>
</table>
</s:if>
<br/>
<br/>

</body>
</html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
</head>
 
<body>

<h2>Market Scan</h2>
<s:form action="marketscan" >
  <s:textfield name="ma" label="MA" value="" />
  <s:submit />
</s:form>
<s:if test="securityDetailsEODList.size() > 0">
<table border="1px" cellpadding="8px">
	<tr>
		<th>Code</th>
		<th>Company Name</th>
		<th>Sector</th>
		<th>Close</th>
		<th>Day Change</th>
		<th>Signal</th>
		<th>RSI</th>
		<th>MACD</th>
		<th>STO</th>
		<th>Avg Turnover</th>
	</tr>
	<s:iterator value="securityDetailsEODList" status="userStatus">
		<tr>
			<td><s:property value="securityDefinationReference.code" /></td>
			<td><s:property value="securityDefinationReference.name" /></td>
			<td><s:property value="securityDefinationReference.SectorInformationReference.name" /></td>
			<td><s:property value="ma" /></td>
			<td><s:property value="ma" /></td>
			<td><s:property value="ma" /></td>
			<td><s:property value="rsi" /></td>
			<td><s:property value="macd" /></td>
			<td><s:property value="sto" /></td>
			<td><s:property value="ma" /></td>
		</tr>
	</s:iterator>
</table>
</s:if>
<br/>
<br/>

</body>
</html>
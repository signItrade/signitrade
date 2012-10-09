<html>
<head>
</head>
<body>
<c:url value="/j_spring_security_check" var="formUrlSecurityCheck"/>
<form method="post" action="${formUrlSecurityCheck}">
    <div id="errorArea" class="errorBox"> 
       <c:if test="${not empty param.error}">
          ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
      </c:if>
  </div>
    <label for="loginName">
        Username:           
        <input style="width:125px;" tabindex="1" id="login" name="j_username" />
    </label>

    <label for="password">
        Password:           
        <input style="width:125px;" tabindex="2" id="password" name="j_password" type="password" />
    </label>
    <input type="submit" tabindex="3" name="login" class="formButton" value="Login" />
</form>
</body>
</html>



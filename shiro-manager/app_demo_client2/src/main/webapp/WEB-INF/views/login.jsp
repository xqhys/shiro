<%--
  Created by IntelliJ IDEA.
  User: HYS17
  Date: 2018/8/3
  Time: 4:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <fmt:message key="language.username"/>：<input type="text" name="username"/><br/>
    <fmt:message key="language.password"/>：<input type="text" name="password"/><br/>
    记住我：<input type="checkbox" name="rememberMe" value="true"><br/>
    <button type="submit">登陆</button>
    Language:
    <a href="?locale=zh_CN">中文</a>&nbsp;&nbsp;
    <a href="?locale=en_US">英文</a><br/>
    当前语言: ${pageContext.response.locale}
    <p style="color: red;">${errormessage}</p>
</form>
</body>
</html>

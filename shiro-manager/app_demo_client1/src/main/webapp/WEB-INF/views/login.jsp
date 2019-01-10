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
    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <fmt:message key="language.username"/>：<input type="text" name="username"/><br/>
    <fmt:message key="language.password"/>：<input type="text" name="password"/><br/>
    请输入验证码：<input name="captchaCode" id="captchaCode" placeholder="输入验证码" />
    <img id="cimg" src="${pageContext.request.contextPath}/captcha/getCaptchaCode" onclick="changeCaptcha(this)" title="看不清？点击更换另一个。"/>

    <br>记住我：<input type="checkbox" name="rememberMe" value="true"><br/>
    <button type="button" onclick="checkCaptcha()">登陆</button>
    Language:
    <a href="?locale=zh_CN">中文</a>&nbsp;&nbsp;
    <a href="?locale=en_US">英文</a><br/>
    当前语言: ${pageContext.response.locale}
    <p style="color: red;">${errormessage}</p>
</form>

<script type="application/javascript">

    // 获取验证码图片
    function changeCaptcha(obj) {
        $(obj).attr("src", "${pageContext.request.contextPath}" + "/captcha/getCaptchaCode");
    }

    //验证输入的验证码
    function checkCaptcha(){
        var captchaCode = $("#captchaCode").val();
        $.ajax({
            type:'post',
            async : false,
            url:'${pageContext.request.contextPath}/captcha/checkCaptchaCode',
            data:{"captchaCode" : captchaCode},
            success:function(res){
                if(res == "success"){
                    document.forms[0].submit();
                }else {
                    alert("验证码错误！！！");
                }
            }
        });
    }
</script>
</body>
</html>

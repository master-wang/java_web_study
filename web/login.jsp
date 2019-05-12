<%--
  Created by IntelliJ IDEA.
  User: master
  Date: 19-5-9
  Time: 上午12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <form action="/wang/loginServeletjsp" method="post">
        用户名
        <input type="text" name="username">
        <br>
        密码
        <input type="text" name="password">
        <br>
        <!--  验证码的制作-->
        验证码
        <input type="text" name="checkcode">
        <br>
        <img id="checkCode" src="/wang/checkCodeServlet?1" alt=""><a id="change" >看不清，换一张！</a>
        <br>
        <input type="submit" value="登录">
    </form>
    <div><%= request.getAttribute("cc_error") ==null? "":request.getAttribute("cc_error") %></div>
    <div><%= request.getAttribute("login_error") ==null?"": request.getAttribute("login_error") %></div>
    <script>
        window.onload=function () {
            var img = document.getElementById("checkCode");
            img.onclick=aa;
            var a = document.getElementById("change");
            a.onclick=aa;
            function aa() {
                var date = new Date();
                img.src="/wang/checkCodeServlet?"+date;
            }
        }
    </script>
</body>
</html>

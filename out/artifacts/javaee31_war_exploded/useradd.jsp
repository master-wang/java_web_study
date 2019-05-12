<%--
  Created by IntelliJ IDEA.
  User: master
  Date: 19-5-9
  Time: 下午4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加</title>
</head>
<body>
    <form action="/wang/addUserServletjsp" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1">用户名</label>
            <input type="text" class="form-control" name="username" id="exampleInputEmail1" placeholder="username">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">密码</label>
            <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="password">
        </div>
        <button type="submit" class="btn btn-default">提交</button>
    </form>
</body>
</html>

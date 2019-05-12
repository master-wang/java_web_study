<%--
  Created by IntelliJ IDEA.
  User: master
  Date: 19-5-12
  Time: 下午12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
    <h1>上传文件</h1>
    <form action="/wang/fileUploadTestjsp" method="post" enctype="multipart/form-data">
        <input type="file" name="upload">
        <input type="submit" value="上传">
    </form>
</body>
</html>

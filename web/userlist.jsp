<%@ page import="java.util.List" %>
<%@ page import="cn.it.web.dao.UserDao2" %>
<%@ page import="cn.it.web.dao.impl.UserDao2Impl" %>
<%@ page import="cn.it.web.domain.User" %>
<%@ page import="cn.it.web.domain.PageBean" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>
</head>
<body>
<%

//    UserDao2 dao = new UserDao2Impl();
//    List list = dao.userList();
//    request.setAttribute("list",list);
    String currentPage = request.getParameter("currentPage");//当前页码
    String rows = request.getParameter("rows"); //每页的记录数

    if (currentPage == null || "".equals(currentPage)){
        currentPage = "1";
    }
    if (rows == null || "".equals(rows)){
        rows ="5";
    }

    //获取条件查询的参数
    Map<String, String[]> condition = request.getParameterMap();



    UserDao2 dao = new UserDao2Impl();
    PageBean<User> pb = new PageBean<User>();

    pb.setCurrentPage(Integer.parseInt(currentPage));
    pb.setRows(Integer.parseInt(rows));

    int totalCount = dao.findTotalCount(condition);

    pb.setTotalCount(totalCount);
    //计算开始的记录缩影
    int start = (Integer.parseInt(currentPage)-1)*Integer.parseInt(rows);
    List<User>  list = dao.findByPage(start,Integer.parseInt(rows),condition);
    pb.setList(list);

    int totalPage = totalCount % Integer.parseInt(rows) == 0 ? totalCount /Integer.parseInt(rows):totalCount /Integer.parseInt(rows)+1 ;
    pb.setTotalPage(totalPage);
    System.out.println(pb);
    request.setAttribute("pb",pb);

%>
<form action="/wang/FindUserByPage?currentPage=1&rows=5">
    id:<input type="text" name="id" value="${condition.id[0]}">
    name: <input type="text" name="username" value="${condition.username[0]}">
    pwd: <input type="text" name="password" value="${condition.password[0]}">
    <input type="submit" class="btn btn-default" value="查询">
</form>
<div>
    <a href="/wang/useradd.jsp">添加联系人</a>
    <a href="javascript:void(0);" id="udelSeleted">删除选中</a>
</div>

<form id="uform" action="/wang/delUserManyServletjsp" method="post">
<table border="1" width="500" align="center">
    <tr>
        <th><input type="checkbox" id="ufirstCheck"></th>
        <th>id</th>
        <th>name</th>
        <th>pwd</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${pb.list}" var="user" varStatus="s">
        <tr>
            <td><input type="checkbox" name="uid" value="${user.id}"></td>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td><a href="/wang/findUserservletByidjsp?id=${user.id}">修改</a>|| <a href="/wang/deleteUserservletjsp?id=${user.id}">删除</a></td>
        </tr>
    </c:forEach>
</table>
</form>

<nav aria-label="Page navigation">
    <ul class="pagination">
        <li>
            <c:if test="${pb.currentPage-1<=0}">
                <a href="${pageContext.request.contextPath}/FindUserByPage?currentPage=1&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.password[0]}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </c:if>
            <c:if test="${pb.currentPage-1>0}">
                <a href="${pageContext.request.contextPath}/FindUserByPage?currentPage=${pb.currentPage-1}&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.password[0]}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </c:if>

        </li>
        <c:forEach begin="1" end="${pb.totalPage}" var="i">
            <c:if test="${pb.currentPage==i}">
                <li><a class="active" style="z-index: 3;
                            color: #fff;
                            cursor: default;
                            background-color: #337ab7;
                            border-color: #337ab7;" href="${pageContext.request.contextPath}/FindUserByPage?currentPage=${i}&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.password[0]}">${i}</a></li>
            </c:if>
            <c:if test="${pb.currentPage!=i}">
                <li><a href="${pageContext.request.contextPath}/FindUserByPage?currentPage=${i}&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.password[0]}">${i}</a></li>
            </c:if>
        </c:forEach>
        <li>
            <c:if test="${pb.currentPage-1>=pb.totalPage}">
                <a href="${pageContext.request.contextPath}/FindUserByPage?currentPage=${pb.totalPage}&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.password[0]}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </c:if>
            <c:if test="${pb.currentPage<pb.totalPage}">
                <a href="${pageContext.request.contextPath}/FindUserByPage?currentPage=${pb.currentPage+1}&rows=${pb.rows}&id=${condition.id[0]}&username=${condition.username[0]}&password=${condition.password[0]}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </c:if>
        </li>
        <spn>共${pb.totalCount}条记录，共${pb.totalPage}页</spn>
    </ul>
</nav>
<script>
    window.onload=function () {
        document.getElementById("udelSeleted").onclick=function () {
            document.getElementById("uform").submit();
        }
        document.getElementById("ufirstCheck").onclick=function (ev) {
            var cbs = document.getElementsByName("uid");
            for (var i = 0 ;i< cbs.length;i++){
                cbs[i].checked = this.checked;
            }
        }
    }
</script>
</body>
</html>

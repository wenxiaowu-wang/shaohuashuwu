<%--
  Created by IntelliJ IDEA.
  User: 耿建强
  Date: 2020/8/30
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>


</head>
<body>
    <a>hahhhaaha</a>

    <c:forEach items="${list}" var="account">
        ${account.name}
    </c:forEach>



    <img src="../images/img1.png">
<br>
    <a href="testtwo">two</a>







</body>
</html>

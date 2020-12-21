<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/9/15
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录成功</title>
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
</head>
<body>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<p align="center">恭喜您<%=session.getAttribute("phone_number") %>登录成功，但是现在啥都没有</p>
<div id="admin" >


<p>hello2</p>
    <br>
</div>

<script src="../js/userLogin.js"></script>
</body>
</html>

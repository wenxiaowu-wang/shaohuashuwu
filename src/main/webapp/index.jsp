<%--
  Created by IntelliJ IDEA.
  User: 耿建强
  Date: 2020/8/30
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/elementui.css">
</head>
<body id="app">

<a href="account/findAll">cceshi</a>

<form action="account/save" method="post">
    姓名：<input type="text" name="name"/><br/>
    金额：<input type="text" name="money"/><br/>
    <input type="submit" value="保存">
</form>

<hr>
<p>
    测试
</p>
<hr>

<form  action="account/findworksAll" method="post">

    <input type="submit" value="查找全部作品">

</form>


<a href="WEB-INF/pages/worksMangagementInterface.html">全部作品</a>
<input type ="button" value="跳转" onclick="window.location.href='WEB-INF/pages/worksMangagementInterface.jsp'">


<br>
<br>
<br>
<br>
<form  action="account/findallworks" method="post">

    <input type="submit" value="测试跳转界面">

</form>
<br>
<br>
<br>
<br>
<form  action="account/addfindallworks" method="post">

    <input type="submit" value="测试添加界面">

</form>

<form  action="account/addfindallworks" method="post">

    <input type="submit" value="测试添加界面">

</form>

<form  action="account/usermain" method="post">

    <input type="submit" value="用户主界面">

</form>
<form  action="account/mangagement" method="post">

    <input type="submit" value="作者作品设置">

</form>
<form  action="account/adminhtml" method="post">

    <input type="submit" value="管理员界面">

</form>
<form  action="account/jubao" method="post">

    <input type="submit" value="举报信息">

</form>
<form  action="account/chuli" method="post">

    <input type="submit" value="处理信息">

</form>
<form  action="account/paihang" method="post">

    <input type="submit" value="排行信息">

</form>

<form  action="account/loginusermain" method="post">

    <input type="submit" value="登录">

</form>
<button @click="testgotochatpter(34)" value="用户主界面">11111</button>


</body>
<script src="../js/vue.js"></script>
<script src="../js/elementui.js"></script>
<script src="../js/axios.js"></script>



</html>

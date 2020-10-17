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
    <link rel="stylesheet" href="css/elementui.css">
</head>
<body>

<a href="account/findAll">cceshi</a>

<form action="account/save" method="post">
    姓名：<input type="text" name="name"/><br/>
    金额：<input type="text" name="money"/><br/>
    <input type="submit" value="保存">
</form>

<form action="account/workbenchInterfacce" method="post">
    <input type="submit" value="跳转">
</form>



<form action="account/test" method="post">
    <input type="submit" value="跳转">
</form>

<a href="account/testtwo">two</a>

<p>/*********************************************/</p>

<form action="adminInfoController/adminLogin" method="post" enctype="multipart/form-data">
    账号：<input type="text" name="admin_id"/><br/>
    密码：<input type="text" name="admin_password"/><br/>
    <input type="submit" value="提交">
</form>
<p>/*********************************************/</p>

<a href="elementTest.jsp">go to element ui test</a>
<br>
<a href="eletest.jsp">element ui test</a>
<br>
<%--在前头加上"/"tomcat就不自动添加application context路径了--%>
<a href="transactionInfoController/topUpsInterface">购买金豆</a>
<br>
<a href="transactionInfoController/rewardInterface">打赏作品（小说打赏）</a>
<br>
<a href="transactionInfoController/voteInterface">投票作品（投推荐票）</a>
<br>
<a href="attentionInfoController/toPayAttemtion">关注作者（理论页面）</a>
<br>
<a href="adminInfoController/adminLoginInterface">管理员登录页面（未分离）</a>
<br>
<a href="adminInfoController/adminLoginInterface2">管理员登录页面(静态资源分离)</a>



<script src="js/axios.js"></script>
<script src="js/vue.js"></script>
<script src="js/elementui.js"></script>
<script>
    new Vue({
        methods:{
            hello(){
                axios.post("userSessionsaveUser/" +
                    11+"/"+encodeURI(encodeURI("我吃西红柿"))).then(response =>{
                    alert(JSON.stringify(response.data));
                }).catch(error =>{
                    alert(JSON.stringify(error));
                })
            }
        },
        created(){
            alert("发送post请求")
            axios.post("userSession/saveUser/" +
                11+"/"+encodeURI("我吃唐家土豆")).then(response =>{
                alert(JSON.stringify(response.data));
                console.log("用户ID和name装载完毕");
                console.log("结论：可以发送int（Integer）类型的数据，编码发送到后端的中文会自动解码")
            }).catch(error =>{
                alert(JSON.stringify(error));
            });
            axios.post("worksSession/saveWork/" +
                28+"/"+encodeURI("斗罗大陆")).then(response =>{
                alert(JSON.stringify(response.data));
                console.log("作品ID和name装载完毕");
            }).catch(error =>{
                alert(JSON.stringify(error));
            });
        }
    })
</script>
</body>
</html>

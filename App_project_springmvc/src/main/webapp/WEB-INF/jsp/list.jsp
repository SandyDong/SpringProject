<%--
  Created by IntelliJ IDEA.
  User: dongwq
  Date: 2019/4/21
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>访问远程接口</title>
</head>
<body>
<a>${login.name}于${login.date}访问成功！</a><br>
<a>客户端请求方IP：${login.remoteIp}</a><br>
<a>客户端请求方端口port：${login.remotePort}</a><br>
<a>请求文本内容类型：${login.contentType}</a><br>
<a>服务端IP：${login.serverName}</a><br>
<a>服务端端口port：${login.serverPort}</a>
<a>请求地址URL：${login.requestUrl}</a>

</body>
</html>

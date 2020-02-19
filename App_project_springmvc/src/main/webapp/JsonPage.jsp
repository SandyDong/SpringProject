<%--
  Created by IntelliJ IDEA.
  User: dongwq
  Date: 2019/5/21
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsonTest</title>
    <%--<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>--%>
    <%--这种方式可以在线引入前端库--%>
    <script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script type="text/javascript">
        //请求ijson，输出json
        //非jquery式请求访问
        function requestjson() {
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/getProductMessage.action',
                contentType:'application/json;charset=utf-8',
                //数据格式是json串
                data:'{"name":"","price":"99.0"}',
                success:function(data) {
                    //返回json结果
                    console.log(data);
                    $('#request1').html(data.price+','+data.name)
                }
            });
        }
        //jquery式请求访问
        //请求key/value
        function responsejson() {
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/getProductInfoV1.action',
                //提交默认类型(key,value)，故再次省略 contentType:'application/json;charset=utf-8',
                //数据格式是json串
                data:'name=电脑&&price=1122',
                success:function(data) {
                    //返回json结果
                    console.log(data);
                    $('#respose1').html(data.price+','+data.name)

                }
            });
        }
    </script>
</head>
<body>
<input type="button" onclick="requestjson()" value="请求ijson，输出json">

<p id="request1"></p>
<input type="button" onclick="responsejson()" value="请求key/value">

<p id="respose1"></p>
</body>
</html>

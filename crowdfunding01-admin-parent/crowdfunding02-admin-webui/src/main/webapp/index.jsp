<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#ajaxTest").click(function (){
                $.ajax({
                    url:"ajaxTest.html",
                    type:"post",
                    data:{
                        "array":[1,5,6]
                    },
                    dataType:"text ",
                    success:function (response) {
                        alert(response);
                    },
                    error:function (response) {
                        alert(response);
                    }
                });
            })
        })
    </script>
</head>
<body>
<h2><a href="admin/to/login/page.html">来不及解释了</a></h2>
<button id="ajaxTest">send [1,5,6]</button>
</body>
</html>

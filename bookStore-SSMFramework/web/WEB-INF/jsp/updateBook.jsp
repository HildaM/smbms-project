<%--
  Created by IntelliJ IDEA.
  User: Quan
  Date: 2021/11/10
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%-- bootstrap 美化界面：使用在线CDN   --%>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍 ----- Quan</small>
                </h1>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
        <%-- 我们提交了修改SQL的请求，但是修改失败
             初次考虑：是事务问题，但是配置完成后，仍然失败
             看了一下SQL语句，发现执行失败。原来自己并没有在验证SQL可以执行成功下，就直接写上去了
        --%>
        <%-- 前端传递隐藏域 --%>
        <input type="hidden" name="bookID" value="${book.bookID}">
        书籍名称：<input type="text" name="bookName" value="${book.bookName}"><br><br><br>
        书籍数量：<input type="text" name="bookCounts" value="${book.bookCounts}"><br><br><br>
        书籍详情：<input type="text" name="detail" value="${book.detail}"><br><br><br>
        <input type="submit" value="添加">
    </form>

</div>

</body>
</html>

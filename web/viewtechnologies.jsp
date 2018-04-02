<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>Content</title>
</head>
<body>
    <link rel="stylesheet" type="text/css" href="css/style.css">

    <%@page import="com.javaeehandbook.dao.TechnologyDao,com.javaeehandbook.bean.JavaEETechnology,java.util.List"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <a class="href" href="index.jsp">Back</a><br/>

    <h2>Technologies List</h2>

    <%
        List<JavaEETechnology> list = TechnologyDao.getAllTechnologies();
        request.setAttribute("list", list);
    %>

    <table border="1" width="90%">
        <tr>
            <th>Name</th>
            <th>Version for Java 4</th>
            <th>Version for Java 5</th>
            <th>Version for Java 6</th>
            <th>Version for Java 7</th>
            <th>Version for Java 8</th>
            <th>Description</th>
            <th colspan="2"></th>
        </tr>

        <c:forEach items="${list}" var="u">
            <tr>
                <td>${u.name}</td>
                <td>${u.versionForJava4}</td>
                <td>${u.versionForJava5}</td>
                <td>${u.versionForJava6}</td>
                <td>${u.versionForJava7}</td>
                <td>${u.versionForJava8}</td>
                <td>${u.description}</td>
                <td><a href="edittechnologyform.jsp?id=${u.id}">Edit</a></td>
                <td><a href="deletetechnology.jsp?id=${u.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/><a class="href" href="addtechnologyform.jsp">Add New Technology</a>
</body>
</html>

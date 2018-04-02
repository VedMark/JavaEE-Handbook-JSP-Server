<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<head>
    <title>Edit Technology entry form</title>
</head>
<body>
    <%@page import="com.javaeehandbook.dao.TechnologyDao, com.javaeehandbook.bean.JavaEETechnology" %>

    <%
        String id = request.getParameter("id");
        JavaEETechnology technology = TechnologyDao.getTechnologyById(Integer.parseInt(id));
    %>

    <h2>Edit Technology</h2>
    <form action="edituser.jsp" method="post">
        <input type="hidden" name="id" value="<%=technology.getId() %>"/>

        <label> Name:
            <input type="text" name="name" value="<%=technology.getName()%>"/>
        </label>
        <label> Version for java 4:
            <input type="text" name="versionForJava4" value="<%=technology.getVersionForJava4()%>"/>
        </label>
        <label> Version for java 5:
            <input type="text" name="versionForJava5" value="<%=technology.getVersionForJava5()%>"/>
        </label>
        <label> Version for java 6:
            <input type="text" name="versionForJava6" value="<%=technology.getVersionForJava6()%>"/>
        </label>
        <label> Version for java 7:
            <input type="text" name="versionForJava7" value="<%=technology.getVersionForJava7()%>"/>
        </label>
        <label> Version for java 8:
            <input type="text" name="versionForJava8" value="<%=technology.getVersionForJava8()%>"/>
        </label>
        <label> Description
            <textarea name="description" cols="40" rows="4" content="<%=technology.getDescription()%>"></textarea>
        </label>
        <div class="centered">
            <input type="button" value="Edit Technology"/>
        </div>
    </form>
</body>
</html>

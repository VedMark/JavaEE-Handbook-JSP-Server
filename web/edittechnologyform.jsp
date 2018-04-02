<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<link rel="stylesheet" type="text/css" href="css/style.css">
<head>
    <title>Edit Technology entry form</title>
</head>
<body>
    <a class="href" href="viewtechnologies.jsp">Back</a><br/>

    <%@page import="com.javaeehandbook.dao.TechnologyDao, com.javaeehandbook.bean.JavaEETechnology" %>

    <%
        String id = request.getParameter("id");
        JavaEETechnology technology = TechnologyDao.getTechnologyById(Integer.parseInt(id));
    %>

    <h2>Edit Technology</h2>
    <form action="edittechnology.jsp" method="post">
        <input type="hidden" name="id" value="<%=technology.getId() %>"/>

        <label> Name:
            <input type="text" name="name" value="<%=technology.getName() != null ? technology.getName(): ""%>"/>
        </label>
        <label> Version for java 4:
            <input type="text" name="versionForJava4" value="<%=technology.getVersionForJava4() != null ? technology.getVersionForJava4(): ""%>"/>
        </label>
        <label> Version for java 5:
            <input type="text" name="versionForJava5" value="<%=technology.getVersionForJava5() != null ? technology.getVersionForJava5(): ""%>"/>
        </label>
        <label> Version for java 6:
            <input type="text" name="versionForJava6" value="<%=technology.getVersionForJava6() != null ? technology.getVersionForJava6(): ""%>"/>
        </label>
        <label> Version for java 7:
            <input type="text" name="versionForJava7" value="<%=technology.getVersionForJava7() != null ? technology.getVersionForJava7(): ""%>"/>
        </label>
        <label> Version for java 8:
            <input type="text" name="versionForJava8" value="<%=technology.getVersionForJava8() != null ? technology.getVersionForJava8(): ""%>"/>
        </label>
        <label> Description
            <textarea name="description" cols="40" rows="4"><%=technology.getDescription() != null ? technology.getDescription(): ""%></textarea>
        </label>
        <div class="centered">
            <input type="submit" value="Submit"/>
        </div>
    </form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" import="com.javaeehandbook.dao.TechnologyDao" %>
<jsp:useBean id="tech" class="com.javaeehandbook.bean.JavaEETechnology"/>
<jsp:setProperty property="*" name="tech"/>

<%
    int status = TechnologyDao.addTechnology(tech);

    if (status > 0) {
        response.sendRedirect("addtechnology-success.jsp");
    } else {
        response.sendRedirect("addtechnology-error.jsp");
    }
%>

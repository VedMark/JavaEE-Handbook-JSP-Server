<%@page import="com.javaeehandbook.dao.TechnologyDao"%>
<jsp:useBean id="tech" class="com.javaeehandbook.bean.JavaEETechnology"/>
<jsp:setProperty property="*" name="tech"/>
<%
    TechnologyDao.updateTechnology(tech);
    response.sendRedirect("viewtechnologies.jsp");
%>

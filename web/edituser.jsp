<%@page import="com.javaeehandbook.dao.TechnologyDao"%>
<jsp:useBean id="u" class="com.javaeehandbook.bean.JavaEETechnology"/>
<jsp:setProperty property="*" name="u"/>
<%
    TechnologyDao.updateTechnology(u);
    response.sendRedirect("viewtechnologies.jsp");
%>

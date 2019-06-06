<%-- 
    Document   : course-list
    Created on : 05-jun-2019, 17:30:43
    Author     : dandada
--%>

<%@page import="com.academik.mvc.model.Course" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Cursos"/>
</jsp:include>

<!-- 
Etiqueta que declara el objeto list_of_students 
proveniente del StudentController, método doGet.
-->
<jsp:useBean 
    id="list_of_courses"
    scope="request" 
    class="List<Course>" />
    
<table class="table">
    <thead>
        <tr>
            <th>Nombre</th>
            <th>Descripcion</th>
            <th>Creditos</th>
        </tr>
    </thead>
    <tbody>
        <% for(Course s : list_of_courses) { %> 
        <tr>
            <td><%= s.getNombre() %></td>
            <td><%= s.getDescripcion() %></td>
            <td><%= s.getCreditos() %></td>
            <td>
                <a class="btn btn-primary" href="courses/edit?id=<%= s.getCodigo() %>">Editar</a>
                <a class="btn btn-primary" href="courses/view?id=<%= s.getCodigo() %>">Ver</a>
            </td>
        </tr>
        <%}%>
    </tbody>
</table>

<%@include file="../templates/footer.jsp" %>
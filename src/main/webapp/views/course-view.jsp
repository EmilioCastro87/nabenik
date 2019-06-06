<%-- 
    Document   : course-view
    Created on : 05-jun-2019, 17:21:50
    Author     : dandada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean 
    id="single_course" 
    scope="request" 
    class="com.academik.mvc.model.Course"/>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | ${single_course.nombre}"/>
</jsp:include>

<h1>Información del curso</h1>

    <div class="row">
        <div class="col">Nombres</div>
        <div class="col">${single_course.nombre}</div>
    </div>
    <div class="row">
        <div class="col">Descipcion</div>
        <div class="col">${single_course.descripcion}</div>
    </div>
    <div class="row">
        <div class="col">Creditos</div>
        <div class="col">${single_course.creditos}</div>
    </div>
    <a class="btn btn-primary" href="edit?id=${single_course.codigo}">Editar</a>
    <input class="btn btn-danger" type="submit" form="form-to-delete" value="Eliminar">
    <form method="POST" name="form-to-delete" id="form-to-delete">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="hidden" name="codigo" value="${single_course.codigo}"/>
    </form>

<%@include file="../templates/footer.jsp" %>
<%-- 
    Document   : course-edit
    Created on : 05-jun-2019, 18:17:14
    Author     : dandada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean 
    id="single_course" 
    scope="request" 
    class="com.academik.mvc.model.Course"/>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Editar curso"/>
</jsp:include>

<h1>Editar curso</h1>
<form method="POST">
    <input type="hidden" name="_method" value="PUT"/>
    <input type="hidden" name="codigo" value="${single_course.codigo}"/>
    <div class="form-group">
        <label for="s_nombre">Nombre</label>
        <input class="form-control" type="text" name="s_nombre" value="${single_course.nombre}"/>
    </div>
    <div class="form-group">
        <label for="s_descripcion">Descripcion</label>
        <input class="form-control" type="text" name="s_descripcion" value="${single_course.descripcion}"/>
    </div>
    
    <div class="form-group">
        <label for="s_creditos">Creditos</label>
        <input class="form-control" type="text" name="s_creditos" value="${single_course.creditos}"/>
    </div>
    
    <input class="btn btn-primary" type="submit" value="Guardar"/>
</form>

<%@include file="../templates/footer.jsp" %>
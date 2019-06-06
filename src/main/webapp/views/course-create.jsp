<%-- 
    Document   : course-create
    Created on : 05-jun-2019, 18:54:27
    Author     : dandada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Crear curso"/>
</jsp:include>

    <h1>Nuevo estudiante</h1>
    <form method="POST">
        <div class="form-group">
            <label for="s_nombre">Nombre del curso</label>
            <input class="form-control" type="text" name="s_nombre"/>
        </div>
        <div class="form-group">
            <label for="s_descripcion">Descripcion</label>
            <input class="form-control" type="text" name="s_descripcion"/>
        </div>
        <div class="form-group">
            <label for="s_creditos">Creditos</label>
            <input class="form-control" type="text" name="s_creditos"/>
        </div>
        <input class="btn btn-primary" type="submit" value="Crear"/>
    </form>

<%@include file="../templates/footer.jsp" %>

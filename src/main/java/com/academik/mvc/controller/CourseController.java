/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.academik.mvc.controller;
import com.academik.mvc.dao.CourseDAO;
import com.academik.mvc.model.Course;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author dandada
 */
@WebServlet("/courses/*")
public class CourseController extends HttpServlet{
    
    CourseDAO dao = new CourseDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String complement = req.getPathInfo();
        if(complement == null)
            complement = "";
        System.err.println(complement);
        String redirectPage;
        switch(complement) {
            case "/create":
                redirectPage = "course-create.jsp";
                break;
            case "/view":
                //Obtengo el parametro desde la URL
                long idToView = Long.parseLong(req.getParameter("id"));
                Course sToView = dao.findById(idToView);
                req.setAttribute("single_course", sToView);
                redirectPage = "course-view.jsp";
                break;
            case "/edit":
                long idToEdit = Long.parseLong(req.getParameter("id"));
                Course sToEdit = dao.findById(idToEdit);
                req.setAttribute("single_course", sToEdit);
                redirectPage = "course-edit.jsp";
                break;
            case "/list":
            case "/":
            case "":
                List<Course> allCourses = dao.queryAll();
                req.setAttribute("list_of_courses", allCourses);
                redirectPage = "course-list.jsp";
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/courses");
                return;
        }

        //Renderice este JSP
        RequestDispatcher rd = req.getRequestDispatcher(
                "/views/" + redirectPage
        );
        //Adelante con la renderización
        rd.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("PUT".equals(req.getParameter("_method"))){
            doPut(req, resp);
            return;
        }
        if("DELETE".equals(req.getParameter("_method"))){
            doDelete(req, resp);
            return;
        }
            
        System.out.println("Creating a new course");
        //Variable vacia
        Course noob = new Course();
        
        //Valores para las propiedades que vienen desde el formulario HTML
        noob.setNombre(req.getParameter("s_nombre"));
        noob.setDescripcion(req.getParameter("s_descripcion"));
        noob.setCreditos(Integer.parseInt(req.getParameter("s_creditos")));
        
        //Utilizar el DAO para guardar la informacion en la base de datos
        dao.create(noob);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Editing an course...");
        //Variable vacia
        Course edited = new Course();
        
        //Valores NUEVOS para las propiedades que vienen desde el formulario HTML
        edited.setNombre(req.getParameter("s_nombre"));
        edited.setDescripcion(req.getParameter("s_descripcion"));
        edited.setCreditos(Integer.parseInt(req.getParameter("s_creditos")));
        
        //Utilizar el DAO para guardar la informacion en la base de datos
        dao.edit(Long.parseLong(req.getParameter("codigo")), edited);
        //dao.edit(Integer.parseInt(req.getParameter("codigo")), edited);

        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("Deleting courses...");
        long id = Long.parseLong(req.getParameter("codigo"));
        dao.delete(id);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }
}

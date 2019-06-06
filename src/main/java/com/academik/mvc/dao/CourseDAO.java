/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.academik.mvc.dao;


import com.academik.mvc.model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dandada
 */
public class CourseDAO implements GeneralDAO<Course> {

    @Override
    public List<Course> queryAll() {
        List<Course> temp = new ArrayList<>();
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery("SELECT codigo, nombre, descripcion, creditos FROM course");
            while (result.next()) {
                Course s = new Course();
                s.setCodigo(result.getLong("codigo"));
                s.setNombre(result.getString("nombre"));
                s.setDescripcion(result.getString("descripcion"));
                s.setCreditos(result.getInt("creditos"));
                temp.add(s);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    @Override
    public Course findById(long id) {
    
        Course s = null;
        try {
            Connection conn = CONN_WRAPPER.getConnection();

            PreparedStatement stmnt = conn.prepareStatement("SELECT codigo, nombre, "
                    + "descripcion, creditos FROM course WHERE codigo = ?");
            stmnt.setLong(1, id);

            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                s = new Course();
                s.setCodigo(result.getLong("codigo"));
                s.setNombre(result.getString("nombre"));
                s.setDescripcion(result.getString("descripcion"));
                s.setCreditos(result.getInt("creditos"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return s;
    }
    

    @Override
    public void create(Course noob) {
       try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(
                "INSERT INTO course"
              + " (nombre, descripcion, creditos)"
              + " VALUES (?, ?, ?)"
            );
            stmnt.setString(1, noob.getNombre());
            stmnt.setString(2, noob.getDescripcion());
            stmnt.setInt(3, noob.getCreditos());
            stmnt.execute();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(long id, Course edited) {
       
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(
                "UPDATE course SET "
              + " nombre = ?,"
              + " descripcion = ?,"
              + " creditos = ?"
              + " WHERE codigo = ?"
            );
            stmnt.setString(1, edited.getNombre());
            stmnt.setString(2, edited.getDescripcion());
            stmnt.setInt(3, edited.getCreditos());
            stmnt.setLong(4, id);
            stmnt.execute();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            
            PreparedStatement stmnt = conn.prepareStatement(
                    "DELETE FROM course WHERE codigo = ?"
            );
            stmnt.setLong(1, id);
            stmnt.executeUpdate();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}

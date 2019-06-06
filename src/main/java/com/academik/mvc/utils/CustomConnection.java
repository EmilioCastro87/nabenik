package com.academik.mvc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomConnection {
    
    public static synchronized CustomConnection getInstance() {
        if(instance == null) {
            instance = new CustomConnection();
        }
        return instance;
    }
    
    private static CustomConnection instance;
    
    private CustomConnection() { }
    
    
    
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String HOST = "localhost";
    private static final String DATA_BASE = "nabenik";
    private static final int PORT = 5432;
    private static final String URL = "jdbc:postgresql://" + HOST + ':' + PORT + '/' + DATA_BASE;
    private static final String USER = "postgres";
    private static final String PASS = "1234qwer";
    
    
    /*
    //private static final String DRIVER = "org.postgresql.Driver";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "localhost";
    private static final String DATA_BASE = "academik";
    private static final int PORT = 3306;
    //private static final String URL = "jdbc:postgresql://" + HOST + ':' + PORT + '/' + DATA_BASE;
    private static final String URL = "jdbc:mysql://" + HOST + ':' + PORT + '/' + DATA_BASE; // + "?use_ssl=false"
    private static final String USER = "root";
    private static final String PASS = "Dirty789";
*/
    
    public Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        System.out.println("Driver encontrado: " + DRIVER);
        
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Conexión exitosa: " + URL);
        return conn;
    }
    
}

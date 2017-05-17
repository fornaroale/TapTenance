/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fornaro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ale
 */
@WebService(serviceName = "authenticator")
public class authenticator {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public int login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        
        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/5a_fornaro_taptenance";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        //  Object for connection
        Connection conn = null;
        
        //  Object for SQL query
        Statement stmt = null;
        
        // Variabile per ID utente di default a -1, se non lo trova rimane -1 
        int IdUtente = -1; 
        
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "";

            sql = "SELECT IdUtente FROM utente WHERE username='" + username + "' AND password='" + password + "'";
            System.out.println(sql);

            ResultSet rs;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                IdUtente = rs.getInt("IdUtente");
            }
            rs.close();
            
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return IdUtente;
    }
    
    public int register(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "foto") String foto) {
        
        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/5a_fornaro_taptenance";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        //  Object for connection
        Connection conn = null;
        
        //  Object for SQL query
        Statement stmt = null;
        
        // Variabile per ID utente di default a -1, se non lo trova rimane -1 
        int IdUtente = -1; 
        
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "";

            sql = "SELECT IdUtente FROM utente WHERE username='" + username + "' AND password='" + password + "'";
            System.out.println(sql);

            ResultSet rs;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                IdUtente = rs.getInt("IdUtente");
            }
            rs.close();
            
            //Se l'utente non esiste, lo registro
            if(IdUtente==-1){
                sql = "INSERT INTO utente(username,password,foto) VALUES ('" + username + "','" + password + "','" + foto + "')";
                System.out.println(sql);
                stmt.executeUpdate(sql);
            }
            
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return IdUtente;
    }
}

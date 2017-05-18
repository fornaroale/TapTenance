/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.giodabg.calculator;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;

/**
 *
 * @author Christian
 */
@WebService(serviceName = "PushWS")
public class PushWS {
    
     // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/5a_dellatorre_taptenance";

    //  Credenziali Database
    static final String USER = "root";
    static final String PASS = "";
    

    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "push")
    public int push(@WebParam(name = "titolo")
            String titolo, @WebParam(name = "descrizione_problema")
                    String descrizione_problema, @WebParam(name = "lotto")
                            String lotto, @WebParam(name = "utente")
                                    String utente) {
        
           Connection conn = null;
           Statement stmt = null;
           try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO Segnalazione(titolo, descrizione, lotto, id_utente) " +
                         "VALUES ("+titolo+", "+descrizione_problema+", "+lotto+", "+utente+")";
            
            stmt.executeUpdate(sql);
            
           } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
           } catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
           } finally {
            //finally block used to close resources
            try {
                if(stmt!=null)
                conn.close();
            } catch(SQLException se) {
            }// do nothing
            try {
                if(conn!=null)
                conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }//end finally try
           }//end try
        System.out.println("Goodbye!");
            
              
        return 0;
    }
    
    
}

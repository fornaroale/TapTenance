package org.marzorati;

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
 * @author Marzorati Stefano
 */
@WebService(serviceName = "gestoreSegnalazioni")
public class viewDone {

    public ArrayList<String> getDone() {
        
        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/taptenance";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        //  Object for connection
        Connection conn = null;
        
        //  Object for SQL query
        Statement stmt = null;
        
        ArrayList<String> arrayList = new ArrayList<>();//butto dentro i risultati
        
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

            sql = "SELECT titolo, classe FROM segnalazione WHERE stato='fatto'";
            System.out.println(sql);

            ResultSet rs;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                arrayList.add(rs.getString("titolo") + "~" + rs.getString("classe"));
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
            }
        }

        return arrayList;
    }
}

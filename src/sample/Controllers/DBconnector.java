package sample.Controllers;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnector {

    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/prak?serverTimezone=UTC","root","77322850nN%");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }


}

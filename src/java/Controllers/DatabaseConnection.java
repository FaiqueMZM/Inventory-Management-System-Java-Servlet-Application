/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Inas
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static volatile Connection conc;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/cccp", "root", "");
        } catch (Exception e) {

        }
    }

    public Connection getConnection() {
        return conc;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        } else if (instance.getConnection().isClosed()) {
            synchronized (DatabaseConnection.class) {
                if (instance.getConnection().isClosed()) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

//    private static DatabaseConnection instance;
//    private Connection con;
//
//    private DatabaseConnection() {
//        String url = "jdbc:mysql://localhost:3306/cccp";
//        String username = "root";
//        String password = "";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection(url, username, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static DatabaseConnection getInstance() {
//        if (instance == null) {
//            instance = new DatabaseConnection();
//        }
//        return instance;
//    }
//
//    public Connection getConnection() {
//        return con;
//    }
}

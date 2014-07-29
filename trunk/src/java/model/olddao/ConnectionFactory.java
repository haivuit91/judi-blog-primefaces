/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.olddao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ConnectionFactory {
    private static Connection connection;
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + util.DataFile.db_name + "", util.DataFile.user, util.DataFile.pass);
        }
        return connection;
    }
}

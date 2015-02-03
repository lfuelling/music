package net.k40s;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DatabaseHandler {
    private Connection connect = null;
    private String dbUsername = Storage.getDbUsername();
    private String dbPassword = Storage.getDbPassword();
    private String dbName = Storage.getDbName();
    private String connectionURI = "jdbc:mysql://localhost/" + dbName + "?" + "user=" + dbUsername + "&password=" + dbPassword;


    public ResultSet executeQuery(String query) throws Exception {
        try {song
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(connectionURI);

            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (Exception e) {
            throw e;
        }

    }
    
    public void executeStatement(String statement) throws SQLException, ClassNotFoundException {
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection(connectionURI);
        PreparedStatement preparedStmt = connect.prepareStatement(statement);
        preparedStmt.execute();

        connect.close();
        
    }
}
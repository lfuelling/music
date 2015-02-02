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
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
  private String dbUsername = Storage.getDbUsername();
  private String dbPassword = Storage.getDbPassword();
  private String dbName = Storage.getDbName();

  public void readDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/" + dbName + "?"
              + "user=" + dbUsername + "&password=" + dbPassword);
      // Statements allow to issue SQL queries to the database
      //   statement = connect.createStatement();
      // Result set get the result of the SQL query
      //   resultSet = statement
      //       .executeQuery("select * from feedback.comments");
      //   writeResultSet(resultSet);

      // PreparedStatements can use variables and are more efficient
      //   preparedStatement = connect
      //       .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
      // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
      // Parameters start with 1
      //   preparedStatement.setString(1, "Test");
      //   preparedStatement.setString(2, "TestEmail");
      //   preparedStatement.setString(3, "TestWebpage");
      //   preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
      //   preparedStatement.setString(5, "TestSummary");
      //   preparedStatement.setString(6, "TestComment");
      //   preparedStatement.executeUpdate();

      //   preparedStatement = connect
      //       .prepareStatement("SELECT myuser, webpage, datum, summery, COMMENTS from feedback.comments");
      //   resultSet = preparedStatement.executeQuery();
      //   writeResultSet(resultSet);

      // Remove again the insert comment
      //   preparedStatement = connect
      //   .prepareStatement("delete from feedback.comments where myuser= ? ; ");
      //   preparedStatement.setString(1, "Test");
      //   preparedStatement.executeUpdate();
      
      //   resultSet = statement
      //   .executeQuery("select * from feedback.comments");
      //   writeMetaData(resultSet);
      
    } catch (Exception e) {
      throw e;
    } 

  }
}
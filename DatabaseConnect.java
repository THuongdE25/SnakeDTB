package SnakeGame;

import java.sql.*;

public class DatabaseConnect {
    private static final String URL="jdbc:sqlserver://localhost:1433;databaseName=SnakeGameDB;encrypt=true;trustServerCertificate=true";
    private static final String USER="sa";
    private static final String PASSWORD="123456";
    private Connection conn;
    public DatabaseConnect(){
        try {
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConn() {
        return conn;
    }
    public void close()throws SQLException{
        if(conn!=null) conn.close();
    }
}

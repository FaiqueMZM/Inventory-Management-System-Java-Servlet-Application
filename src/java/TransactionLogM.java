
import Controllers.DatabaseConnection;
import Controllers.TransactionLog;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Inas
 */
public class TransactionLogM {

//    private static final DatabaseConnection dbConnection = DatabaseConnection.getInstance();

    public List<TransactionLog> retrieveTransactionLog() throws SQLException {
        List<TransactionLog> transactionLogs = new ArrayList<>();

        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "SELECT * FROM transactionlog";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String type = resultSet.getString("TypeOfTransaction");
            String itemName = resultSet.getString("ItemName");
            int itemQuantity = resultSet.getInt("ItemQuantity");
            String empName = resultSet.getString("EmpName");

            TransactionLog transactionLog = new TransactionLog(type, itemName, itemQuantity, empName);
            transactionLogs.add(transactionLog);
        }
        connection.close();
        return transactionLogs;
    }
    
    public List<TransactionLog> retrieveTransactionLog(String empName) throws SQLException {
        List<TransactionLog> transactionLogs = new ArrayList<>();

        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "SELECT * FROM transactionlog WHERE EmpName = '"+ empName + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String type = resultSet.getString("TypeOfTransaction");
            String itemName = resultSet.getString("ItemName");
            int itemQuantity = resultSet.getInt("ItemQuantity");

            TransactionLog transactionLog = new TransactionLog(type, itemName, itemQuantity, empName);
            transactionLogs.add(transactionLog);
        }
        connection.close();
        return transactionLogs;
    }

}

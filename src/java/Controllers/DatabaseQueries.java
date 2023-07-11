/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Inas
 */
public class DatabaseQueries {

    //Check id 
    public boolean checkID(int ID) throws SQLException {
        Connection conc = DatabaseConnection.getInstance().getConnection();
        Statement stmt = conc.createStatement();
        boolean result = false;
        String query = "SELECT * FROM item WHERE ID = " + ID;
        ResultSet resultSet = stmt.executeQuery(query);
        if (resultSet.next()) {
            result = true;
        }
        conc.close();
        return result;
    }

    //Check employee
    public boolean checkEmp(String EmpName) throws SQLException {
        Connection conc = DatabaseConnection.getInstance().getConnection();
        Statement stmt = conc.createStatement();
        boolean result = false;
        String query = "SELECT * FROM employee WHERE EmpName = '" + EmpName + "'";
        ResultSet resultSet = stmt.executeQuery(query);
        if (resultSet.next()) {
            result = true;
        }
        conc.close();
        return result;
    }

    //Enter new item to DB
    public boolean addItemToDB(Item newItem) throws SQLException {
        Connection conc = DatabaseConnection.getInstance().getConnection();
        Statement stmt = conc.createStatement();
        String sql = "INSERT INTO item (ID, Name, Quantity, Price, EmpName, DateCreated) VALUES ('" + newItem.getID() + "', '" + newItem.getName() + "', '" + newItem.getQuantity() + "', '" + newItem.getPrice() + "', '" + newItem.getEmpName() + "', '" + LocalDateTime.now() + "')";
        stmt.executeUpdate(sql);
//        conc.close();
        return false;
    }

    //Add stock to an item
    public boolean addStockToItem(Item newItem) throws SQLException {
        Connection conc = DatabaseConnection.getInstance().getConnection();
        Statement stmt = conc.createStatement();
        String sql = "UPDATE item SET Quantity =" + newItem.getQuantity() + " WHERE ID =" + newItem.getID();
        stmt.executeUpdate(sql);
//        conc.close();
        return false;
    }

    //Remove stock from an item
    public boolean removeStockFromItem(Item newItem) throws SQLException {
        Connection conc = DatabaseConnection.getInstance().getConnection();
        Statement stmt = conc.createStatement();
        String sql = "UPDATE item SET Quantity =" + newItem.getQuantity() + " WHERE ID =" + newItem.getID();
        stmt.executeUpdate(sql);
//        conc.close();
        return false;
    }

    //Transaction entry to DB
    public boolean addTransactionToDB(TransactionLog newTransaction) throws SQLException {
        Connection conc = DatabaseConnection.getInstance().getConnection();
        Statement stmt = conc.createStatement();
        String sql = "INSERT INTO transactionlog (TypeOfTransaction, ItemID, ItemName, ItemPrice, ItemQuantity, EmpName, DateAdded) VALUES ('" + newTransaction.getTypeofTransaction() + "', '" + newTransaction.getItemID() + "', '" + newTransaction.getItemName() + "', '" + newTransaction.getItemPrice() + "', '" + newTransaction.getItemQuantity() + "', '" + newTransaction.getEmpName() + "', '" + LocalDateTime.now() + "')";
        stmt.executeUpdate(sql);
//        conc.close();
        return false;
    }

    // Retrieve inventory details
    public List<Item> retInventoryR() throws SQLException {
        Connection conc = DatabaseConnection.getInstance().getConnection();
        List<Item> inventoryItems = new ArrayList<>();
        Statement stmt = conc.createStatement();
        String sql = "SELECT * FROM item";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("Name");
            int quantity = rs.getInt("Quantity");
            float price = rs.getFloat("Price");

            Item item = new Item(id, name, quantity, price);
            inventoryItems.add(item);
        }
        conc.close();
        return inventoryItems;
    }

    //Add employee to DB
    public boolean addEmployeeToDB(Employee employee) throws SQLException {
        Connection conc = DatabaseConnection.getInstance().getConnection();
        Statement stmt = conc.createStatement();
        String sql = "INSERT into employee (EmpID, EmpName) VALUES ('" + employee.getEmpID() + "', '" + employee.getEmpName() + "')";
        stmt.executeUpdate(sql);
        conc.close();
        return false;

    }
}

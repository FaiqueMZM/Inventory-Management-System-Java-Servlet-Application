
import Controllers.DatabaseConnection;
import Controllers.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class FinancialReport {

//    DatabaseConnection dbConnection = DatabaseConnection.getInstance();

    public List<Item> generateReport() throws SQLException {
        List<Item> inventoryItems = new ArrayList<>();

        Connection connection = DatabaseConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            int quantity = resultSet.getInt("Quantity");
            float price = resultSet.getFloat("Price");

            Item item = new Item(id, name, quantity, price);
            inventoryItems.add(item);
        }
        connection.close();
        return inventoryItems;
    }

    public float calculateTotalCost(List<Item> items) {
        float totalCost = 0;
        for (Item item : items) {
            totalCost += item.getQuantity() * item.getPrice();
        }
        return totalCost;
    }
}

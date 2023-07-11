/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.time.LocalDateTime;

/**
 *
 * @author Inas
 */
public class TransactionLog {

    private String TypeOfTransaction;
    private int ItemID;
    private String ItemName;
    private float ItemPrice;
    private int ItemQuantity;
    private String EmpName;
    private LocalDateTime DateAdded;

    public String getTypeofTransaction() {
        return TypeOfTransaction;
    }

    public void setTypeofTransaction(String typeofTransaction) {
        TypeOfTransaction = typeofTransaction;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public float getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(float itemPrice) {
        ItemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return ItemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        ItemQuantity = itemQuantity;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public LocalDateTime getDateAdded() {
        return DateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        DateAdded = dateAdded;
    }

    public TransactionLog(String typeOfTransaction, int itemID, String itemName, float itemPrice, int itemQuantity,
            String empName, LocalDateTime dateAdded) {

        TypeOfTransaction = typeOfTransaction;
        ItemID = itemID;
        ItemName = itemName;
        ItemPrice = itemPrice;
        ItemQuantity = itemQuantity;
        EmpName = empName;
        DateAdded = dateAdded;
    }

    public TransactionLog(String TypeOfTransaction, String ItemName, int ItemQuantity, String EmpName) {
        this.TypeOfTransaction = TypeOfTransaction;
        this.ItemName = ItemName;
        this.ItemQuantity = ItemQuantity;
        this.EmpName = EmpName;
    }

    

}

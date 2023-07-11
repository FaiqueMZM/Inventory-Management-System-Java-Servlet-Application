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
public class TransactionLogBuilder {

    private String TypeOfTransaction;
    private int ItemID;
    private String ItemName;
    private float ItemPrice;
    private int ItemQuantity;
    private String EmpName;
    private LocalDateTime DateAdded;

    public TransactionLogBuilder setTypeOfTransaction(String typeOfTransaction) {
        TypeOfTransaction = typeOfTransaction;
        return this;
    }

    public TransactionLogBuilder setItemID(int itemID) {
        ItemID = itemID;
        return this;
    }

    public TransactionLogBuilder setItemName(String itemName) {
        ItemName = itemName;
        return this;
    }

    public TransactionLogBuilder setItemPrice(float itemPrice) {
        ItemPrice = itemPrice;
        return this;
    }

    public TransactionLogBuilder setItemQuantity(int itemQuantity) {
        ItemQuantity = itemQuantity;
        return this;
    }

    public TransactionLogBuilder setEmpName(String empName) {
        EmpName = empName;
        return this;
    }

    public TransactionLogBuilder setDateAdded(LocalDateTime dateAdded) {
        DateAdded = dateAdded;
        return this;
    }

    public TransactionLog build() {
        return new TransactionLog(TypeOfTransaction, ItemID, ItemName, ItemPrice, ItemQuantity, EmpName, DateAdded);
    }

}


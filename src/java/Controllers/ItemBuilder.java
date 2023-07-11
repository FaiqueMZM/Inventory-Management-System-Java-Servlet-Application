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
public class ItemBuilder {

    private int ID;
    private String Name;
    private int Quantity;
    private float Price;
    private String EmpName;
    private LocalDateTime DateCreated;

    public ItemBuilder setID(int iD) {
        ID = iD;
        return this;
    }

    public ItemBuilder setName(String name) {
        Name = name;
        return this;
    }

    public ItemBuilder setQuantity(int quantity) {
        Quantity = quantity;
        return this;
    }

    public ItemBuilder setPrice(float price) {
        Price = price;
        return this;
    }

    public ItemBuilder setEmpName(String empName) {
        EmpName = empName;
        return this;
    }

    public ItemBuilder setDateCreated(LocalDateTime dateCreated) {
        DateCreated = dateCreated;
        return this;
    }

    public Item build() {
        return new Item(ID, Name, Quantity, Price, EmpName, DateCreated);
    }
}


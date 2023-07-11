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
public class Item {

    private int ID;
    private String Name;
    private int Quantity;
    private float Price;
    private String EmpName;
    private LocalDateTime DateCreated;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public LocalDateTime getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        DateCreated = dateCreated;
    }

    public Item(int iD, String name, int quantity, Float price, String empName, LocalDateTime dateCreated) {
        super();
        ID = iD;
        Name = name;
        Quantity = quantity;
        Price = price;
        EmpName = empName;
        DateCreated = dateCreated;
    }

    public Item(int ID, String Name, int Quantity, float Price) {
        this.ID = ID;
        this.Name = Name;
        this.Quantity = Quantity;
        this.Price = Price;
    }
    
}


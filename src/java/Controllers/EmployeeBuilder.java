/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author Inas
 */
public class EmployeeBuilder {

    private int EmpID;
    private String EmpName;

    public EmployeeBuilder setEmpID(int empID) {
        EmpID = empID;
        return this;
    }

    public EmployeeBuilder setEmpName(String empName) {
        EmpName = empName;
        return this;
    }

    public Employee build() {
        return new Employee(EmpID, EmpName);
    }

}

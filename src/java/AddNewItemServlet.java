/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Controllers.DatabaseConnection;
import Controllers.DatabaseQueries;
import Controllers.Item;
import Controllers.ItemBuilder;
import Controllers.TransactionLog;
import Controllers.TransactionLogBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Inas
 */
public class AddNewItemServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewItemServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewItemServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static DatabaseQueries dbQueries = new DatabaseQueries();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract form data from the request

        try {
            final int itemId = Integer.parseInt(request.getParameter("itemId"));
            final String itemName = request.getParameter("itemName");
            final int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
            final float itemPrice = Float.parseFloat(request.getParameter("itemPrice"));
            final String empName = request.getParameter("empName");

            // Check ID and employee existence
            boolean checkID = dbQueries.checkID(itemId);
            boolean checkEmp = dbQueries.checkEmp(empName);

            if (itemId <= 0) {
                // Handle invalid item ID
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>ERROR: Entered ID is not valid</h1>");
                out.println("<button><a href=\"addNewItem.html\">Add New Item</a></button>");
                out.println("</body></html>");
                out.close();
            } else if (checkID) {
                // Handle duplicate item ID
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>ERROR: Entered ID is already taken</h1>");
                out.println("<button><a href=\"addNewItem.html\">Add New Item</a></button>");
                out.println("</body></html>");
                out.close();
            } else if (!checkEmp) {
                // Handle non-existent employee
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>ERROR: Employee is not registered in the system</h1>");
                out.println("<button><a href=\"addNewItem.html\">Add New Item</a></button>");
                out.println("</body></html>");
                out.close();
            } else {
                // Create a new instance of the Runnable task
                Runnable addNewItemTask = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Create a new item and add it to the database
                            Item newItem = new ItemBuilder()
                                    .setID(itemId)
                                    .setName(itemName)
                                    .setQuantity(itemQuantity)
                                    .setPrice(itemPrice)
                                    .setEmpName(empName)
                                    .build();

                            dbQueries.addItemToDB(newItem);

                            // Create a new transaction entry
                            TransactionLog newTransaction = new TransactionLogBuilder()
                                    .setTypeOfTransaction("New")
                                    .setItemID(itemId)
                                    .setItemName(itemName)
                                    .setItemPrice(itemPrice)
                                    .setItemQuantity(itemQuantity)
                                    .setEmpName(empName)
                                    .build();

                            dbQueries.addTransactionToDB(newTransaction);
                        } catch (SQLException e) {
                            // Handle database errors
                            e.printStackTrace();
                        }
                    }
                };

                // Create a thread pool and submit the task for execution
                ExecutorService executor = Executors.newCachedThreadPool();
                executor.submit(addNewItemTask);

                // Send a success response
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>New Item has been added</h1>");
                out.println("<button><a href=\"index.html\">Go to Menu</a></button>");
                out.println("</body></html>");
                out.close();

                // Shutdown the executor after all tasks are completed
                executor.shutdown();
            }
        } catch (SQLException e) {
            // Handle database errors
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.println(e);
        }
    }

}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}

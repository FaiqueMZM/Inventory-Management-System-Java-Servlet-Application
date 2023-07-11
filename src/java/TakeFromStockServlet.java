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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import jakarta.servlet.http.HttpServlet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Inas
 */
public class TakeFromStockServlet extends HttpServlet {

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
            out.println("<title>Servlet TakeFromStock</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TakeFromStock at " + request.getContextPath() + "</h1>");
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
    @Override
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
    private static final DatabaseQueries dbQueries = new DatabaseQueries();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract form data from the request
        final int itemId = Integer.parseInt(request.getParameter("itemId"));
        final String itemEmpName = request.getParameter("empName");
        final int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));

        try {
            boolean checkEmp = dbQueries.checkEmp(itemEmpName);
            if (!checkEmp) {
                // Employee not registered in the system
                response.setContentType("text/html");
                response.getWriter().println("<h1>ERROR: Employee is not registered in the system</h1>");
                response.getWriter().println("<button><a href=\"takeFromStock.html\">Take From Stock</a></button>");
                return;
            }

            Connection conc = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT * FROM item WHERE ID = " + itemId;
            Statement stmt = conc.prepareStatement(sql);
            final ResultSet rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                // Item not found in the database
                response.setContentType("text/html");
                response.getWriter().println("<h1>ERROR: Item not found in the database</h1>");
                response.getWriter().println("<button><a href=\"takeFromStock.html\">Take From Stock</a></button>");
                return;
            }

            final String itemName = rs.getString("Name");
            int currentQuantity = rs.getInt("Quantity");
            final int newQuantity = currentQuantity - itemQuantity;

            if (itemQuantity < 0 || itemEmpName.isEmpty()) {
                // Invalid input: quantity below 0 or empty employee name
                response.setContentType("text/html");
                response.getWriter().println("<h1>ERROR: Invalid input - quantity below 0 or empty employee name</h1>");
                response.getWriter().println("<button><a href=\"takeFromStock.html\">Take From Stock</a></button>");
                return;
            }

            // Create a new instance of the Runnable task
            Runnable takeFromStockTask = new Runnable() {
                @Override
                public void run() {
                    try {
                        // Update the quantity of the item in the database
                        Item newItem = new ItemBuilder().setID(itemId).setQuantity(newQuantity).build();
                        dbQueries.addStockToItem(newItem);

                        // Create transaction entry
                        String type = "Removed";
                        TransactionLog newTransaction = new TransactionLogBuilder()
                                .setTypeOfTransaction(type)
                                .setItemID(itemId)
                                .setItemName(itemName)
                                .setItemPrice(rs.getFloat("Price"))
                                .setItemQuantity(itemQuantity)
                                .setEmpName(itemEmpName)
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
            executor.submit(takeFromStockTask);

            response.setContentType("text/html");
            response.getWriter().println("<h1>" + itemQuantity + " items have been removed from Item ID: " + itemId
                    + " on " + LocalDateTime.now() + "</h1>");
            response.getWriter().println("<button><a href=\"index.html\">Go To Menu</a></button>");

            // Shutdown the executor after all tasks are completed
            executor.shutdown();
        } catch (SQLException e) {
            // Handle database exception
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("<h1>Error occurred while taking items from stock</h1>");
            response.getWriter().println("<button><a href=\"takeFromStock.html\">Take From Stock</a></button>");
            PrintWriter out = response.getWriter();
            out.println(e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

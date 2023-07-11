/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Controllers.DatabaseQueries;
import Controllers.TransactionLog;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Inas
 */
public class PersonalUsageReportServlet extends HttpServlet {

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
            out.println("<title>Servlet PersonalUsageReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PersonalUsageReport at " + request.getContextPath() + "</h1>");
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
    private static DatabaseQueries dbQueries = new DatabaseQueries();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TransactionLogM logManager = new TransactionLogM();
            String empName = request.getParameter("empName");
            boolean checkEmp = dbQueries.checkEmp(empName);
            
            try {
                if (!checkEmp) {
                    // Handle non-existent employee
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<html><body>");
                    out.println("<h1>ERROR: Employee is not registered in the system</h1>");
                    out.println("<button><a href=\"personalUsage.jsp\">Personal Usage Report</a></button>");
                    out.println("</body></html>");
                    out.close();
                } else {
                    List<TransactionLog> transactionLogs = logManager.retrieveTransactionLog(empName);
                    request.setAttribute("transactionLogs", transactionLogs);
                    request.getRequestDispatcher("personalUsage.jsp").forward(request, response);
                }
//            List<TransactionLog> transactionLogs = logManager.retrieveTransactionLog(empName);
//            request.setAttribute("transactionLogs", transactionLogs);
//            request.getRequestDispatcher("personalUsage.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception and display an error page
                // You can redirect to an error page or show an error message
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonalUsageReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

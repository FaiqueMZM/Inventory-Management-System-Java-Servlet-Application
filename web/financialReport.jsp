<%-- 
    Document   : financialReport
    Created on : Jun 7, 2023, 2:55:17 PM
    Author     : Inas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Financial Report</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                font-family: Arial, sans-serif;
                text-align: center;
            }
            a {
                color: black;
                text-decoration: none; /* Remove underline from links */
                padding: 10px; /* Add some spacing around the links */
            }
            .center-table {
                display: flex;
                justify-content: center;
            }
            table {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <header>
            <h1>Inventory Management System</h1>
            <h2>Financial Report</h2>
        </header>

        <main>
            <form action="FinancialReportServlet" method="get"><input type="submit" value="Retrieve Financial Report"></form>
            <div class="center-table">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${inventoryItems}">
                            <tr>
                                <td>${item.getID()}</td>
                                <td>${item.getName()}</td>
                                <td>${item.getQuantity()}</td>
                                <td>${item.getPrice()}</td>
                                <td>${item.getQuantity() * item.getPrice()}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="4">Total Cost of the Inventory:</td>
                            <td>${totalCost}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div><button><a href="index.html">Go to Menu</a></button></div>
        </main>

        <footer>
        </footer>
    </body>
</html>


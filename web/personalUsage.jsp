<%-- 
    Document   : personalUsage
    Created on : Jun 7, 2023, 7:16:47 PM
    Author     : Inas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Personal Usage</title>
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
            <h2>Personal Usage</h2>
        </header>

        <main>
            <form action="PersonalUsageReportServlet" method="get">
                <label for="empName">Employee Name:</label>
                <input type="text" id="empName" name="empName" required><br><br>

                <input type="submit" value="Submit">
                <div class="center-table">
                    <table>
                        <thead>
                            <tr>
                                <th>Type</th>
                                <th>Item</th>
                                <th>Quantity</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="transactionLog" items="${transactionLogs}">
                                <tr>
                                    <td><c:out value="${transactionLog.getTypeofTransaction()}"/></td>
                                    <td><c:out value="${transactionLog.getItemName()}"/></td>
                                    <td><c:out value="${transactionLog.getItemQuantity()}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div><button><a href="index.html">Go to Menu</a></button></div>
        </main>

        <footer>
        </footer>
    </body>
</html>



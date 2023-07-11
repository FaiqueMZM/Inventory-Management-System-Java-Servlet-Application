<%-- 
    Document   : transactionLog
    Created on : Jun 7, 2023, 3:58:47 PM
    Author     : Inas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Transaction Log</title>
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
            <h2>Transaction Log</h2>
        </header>

        <main>
            <form action="TransactionLogServlet" method="get"><input type="submit" value="Retrieve Transaction Log"></form>
            <div class="center-table">
                <table>
                    <thead>
                        <tr>
                            <th>Type</th>
                            <th>Item</th>
                            <th>Quantity</th>
                            <th>Employee</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="transactionLog" items="${transactionLogs}">
                            <tr>
                                <td><c:out value="${transactionLog.getTypeofTransaction()}"/></td>
                                <td><c:out value="${transactionLog.getItemName()}"/></td>
                                <td><c:out value="${transactionLog.getItemQuantity()}"/></td>
                                <td><c:out value="${transactionLog.getEmpName()}"/></td>
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


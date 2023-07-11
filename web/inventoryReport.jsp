<%-- 
    Document   : inventoryReport
    Created on : Jun 7, 2023, 1:55:12 PM
    Author     : Inas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Inventory Report</title>
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
            <h2>Inventory Report</h2>
        </header>

        <main>
            <form action="InventoryReportServlet" method="get"><input type="submit" value="Retrieve Inventory"></form>
            <div class="center-table">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${inventoryItems}">
                            <tr>
                                <td><c:out value="${item.getID()}"/></td>
                                <td><c:out value="${item.getName()}"/></td>
                                <td><c:out value="${item.getQuantity()}"/></td>
                                <td><c:out value="${item.getPrice()}"/></td>
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
